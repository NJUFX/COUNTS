package com.fx.service.impl;

import com.fx.bean.RecommendResult;
import com.fx.model.Mission;
import com.fx.service.MissionService;
import com.fx.service.RecommendService;
import com.fx.service.UserService;
import com.fx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 写在开始
 * 由于是众包市场 既要让requestor满意又要让worker找到自己喜欢的算法
 * 所以 在让requestor 的任务能够完成
 * worker也要找到自己的喜欢工作的类型 所以距离开始时间越久 结束时间越近的权重应该越大 也应该有个按比例分隔的 作为卖方市场
 * 这里就应该有个影响因子 在这里  发布时间 结束时间 已接任务人数 未接任务人数 金钱
 * <p>
 * 推荐算法的正确率怎么计算呢
 * 点击率 + 评分值
 * 点击率 = 接受任务数 + 推荐总数
 * 评分值 = average(评分值) // todo 待定
 */

/**
 * Description:
 * Created by Hanxinhu at 22:46 2018/6/10/010
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    MissionService missionService;
    @Autowired
    UserService userService;

    @Override
    public RecommendResult recommend(String username) {
        List<Mission> missions = missionService.getAllMission(0);
        RecommendResult recommendResult = new RecommendResult();
        recommendResult.setMissions(missions);
        recommendResult.setType(1);
        return recommendResult;
    }

    /**
     * top5推荐法 推荐最热门的
     *
     * @return
     */
    //todo  最新发布的12个吧 ...
    private List<Mission> top5recommend() {
        List<Mission> missions = missionService.findUnfinishedMission();
        List<Mission> another = new ArrayList<>();
        for (int i = 0; i < missions.size() && i < 12 * 1.5 ; i++) {
            another.add(missions.get(missions.size() - i - 1));
        }
        while (another.size()>12){
            int index = (int)(Math.random() * another.size());
            another.remove(index);
        }
        return another;
    }

    /**
     * 协同过滤推荐算法 基于相似的用户喜欢的任务也是相似的
     *
     * @param username
     * @return
     */
    //todo
    private List<Mission> CFrecommend(String username) {
        return null;
    }

    /**
     * 基于内容的推荐 tags
     *
     * @param username
     * @return
     */
    //todo
    private List<Mission> contentRecommend(String username) {
        List<Mission> missions = missionService.findUnfinishedMission();
        List<Mission> neededMissions = new ArrayList<>();
        List<String> usertags = userService.findTagsByUsername(username);
        for (int i = 0; i < missions.size(); i++) {
           List<String > tags =  missions.get(i).getTags();
            for (String s: usertags
                 ) {
                if (tags.contains(s)){
                    neededMissions.add(missions.get(i));
                    break;
                }
            }
        }
        while (neededMissions.size() < 12){
            for (int i = 0; i < missions.size(); i++) {
                if(!neededMissions.contains(missions.get(i))){
                    neededMissions.add(missions.get(i));
                }
            }
        }
        while (neededMissions.size()>12){
            int index = (int)(Math.random() * neededMissions.size());
            neededMissions.remove(index);
        }

        return neededMissions;
    }


    //finished 再开新坑 基于卖方市场的推荐算法 假设为 4
    /**
     *    计算影响因子 考虑因素
     *    就任务而言
     *    距离开始时间 结束时间
     *    已接收人数 总人数
     *    难度
     *    金钱奖励
     *    将要考虑的因素 发布者的等级？ 等级加权
     *
     */
    private List<Mission> requestorRecommend() {
        List<Mission> missions = missionService.findUnfinishedMission();
        double[] factors = new double[missions.size()];
        for (int i = 0; i < factors.length; i++) {
            factors[i] = calculateFactor(missions.get(i));
        }
        int[] indexs = new int[missions.size()];
        for (int i = 0; i < indexs.length; i++) {
            int maxIndex = 0;
            for (int j = 0; j < factors.length; j++) {
                if (factors[j] > factors[maxIndex]) {
                    maxIndex = j;
                }
            }
            factors[maxIndex] = -1;
            indexs[i] = maxIndex;
        }
        // 增加一点随机性 更好玩一些
        ArrayList<Mission> results = new ArrayList<>();
        for (int i = 0; i < 12 * 1.5 && i < missions.size(); i++) {
            results.add(missions.get(indexs[i]));
        }
        while (results.size()>12){
            int index = (int)(Math.random() * results.size());
            results.remove(index);
        }
        return results;

    }

    private double calculateFactor(Mission mission) {
        int level = userService.findLevelByUsername(mission.getRequestorID());
        TimeUtil timeUtil = new TimeUtil();
        int start = timeUtil.IntervalTime(new TimeUtil(mission.getBegin()));
        int end = timeUtil.IntervalTime(new TimeUtil(mission.getEnd()));
        int currentNumber = mission.getCurrentNumber();
        int maxNumber = mission.getMaxNumber();
        int difficulty = getDifficultyFactorByType(mission.getType());
        double factor = (1 - (((end * 1.0) / (start + end)) * ((end * 1.0) / (start + end))))
            * ((currentNumber + 1.0) * 5 / maxNumber) / difficulty * (level + 1);

        return factor;
    }

    private int getDifficultyFactorByType(String type) {
        switch (type) {
            case "Classification":
                return 1;
            case "Caption":
                return 2;
            case "Attribute":
                return 3;
            case "Detection":
                return 2;
            case "Segmentation":
                return 4;
            default:
                return 5;
        }

    }

}
