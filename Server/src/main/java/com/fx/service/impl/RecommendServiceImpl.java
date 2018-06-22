package com.fx.service.impl;

import com.fx.bean.RecommendResult;
import com.fx.model.Mission;
import com.fx.repository.MissionRepository;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.service.MissionService;
import com.fx.service.RecommendService;
import com.fx.service.UserService;
import com.fx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    UserService userService;
    MissionRepository missionRepository = new MissionRepositoryImpl();
    public RecommendServiceImpl() {
        File file = new File(dir);
        if (!file.exists())
            file.mkdir();
    userService = new UserServiceImpl();
    }

    @Override
    public RecommendResult recommend(String username) {
        if (username == null || username.equals("")) {
            List<Mission> missions = top5recommend();
            RecommendResult recommendResult = new RecommendResult();
            recommendResult.setType(1);
            recommendResult.setMissions(missions);
        }
        int[] record = readRecord(username);
        int sum = record[0] + record[1];
        int type;
        if (sum <= 10) {
            double random = Math.random() * (sum * 1.5);
            if (random <= record[0])
                type = 1;
            else if (random <= sum)
                type = 2;
            else
                type = 4;
        } else {
          if(  record[2]==0)
              record[2] = 2;
          sum += record[2];
            int random =(int)( Math.random() * (sum * 1.5));
            if (random <= record[0])
                type = 1;
            else if (random <= record[0] + record[1])
                type = 2;
            else if (random <= sum)
                type = 3;
            else
                type = 4;
        }
        updateSum(type);
        updateSum(username,type);
        List<Mission> missions;
        switch (type) {
            case 1:
                missions = top5recommend();
                break;
            case 2:
                missions = contentRecommend(username);
                break;
            case 3:
                missions = CFRecommend(username);
                break;
            case 4:
                missions = requestorRecommend();
                break;
            default:
                missions = getDefaultMission();
        }
        RecommendResult recommendResult = new RecommendResult();
        recommendResult.setMissions(missions);
        recommendResult.setType(type);
        return recommendResult;
    }

    private List<Mission> getDefaultMission() {
        List<Mission> missions = missionRepository.findUnFinishedMission();
        while (missions.size() > 12) {
            int random = (int) (Math.random() * missions.size());
            missions.remove(random);
        }
        return missions;
    }

    /**
     * top5推荐法 推荐最热门的
     *
     * @return
     */
    //todo  最新发布的12个吧 ...
    private List<Mission> top5recommend() {
        List<Mission> missions = missionRepository.findUnFinishedMission();
        List<Mission> another = new ArrayList<>();
        for (int i = 0; i < missions.size() && i < 12 * 2; i++) {
            another.add(missions.get(missions.size() - i - 1));
        }
        while (another.size() > 12) {
            int index = (int) (Math.random() * another.size());
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
    private static final String dir = "./data/recommend";

    private List<Mission> CFRecommend(String username) {
        String filename = dir + "/" + username + ".txt";
        File file = new File(filename);
        if (!file.exists())
            return requestorRecommend();
        List<Integer> ids = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ids.add(scanner.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Mission> missions = missionRepository.findUnFinishedMission();
        List<Mission> neededMission = new ArrayList<>();
        for (int i = 0; i < missions.size(); i++) {
            if (ids.contains(missions.get(i).getID()))
                neededMission.add(missions.get(i));
        }
        while (neededMission.size() > 12) {
            int index = (int) (Math.random() * neededMission.size());
            neededMission.remove(index);
        }
        while (neededMission.size() < 12) {
            for (int i = 0; i < missions.size(); i++) {
                if (!neededMission.contains(missions.get(i))) {
                    neededMission.add(missions.get(i));
                }
            }
        }
        return neededMission;

    }

    /**
     * 基于内容的推荐 tags
     *
     * @param username
     * @return
     */
    //todo
    private List<Mission> contentRecommend(String username) {
        List<Mission> missions = missionRepository.findUnFinishedMission();
        List<Mission> neededMissions = new ArrayList<>();
        List<String> usertags = userService.findTagsByUsername(username);
        for (int i = 0; i < missions.size(); i++) {
            List<String> tags = missions.get(i).getTags();
            for ( int j = 0 ; j < usertags.size();j++) {
                if (tags.contains(usertags.get(j))) {
                    neededMissions.add(missions.get(i));
                    break;
                }
            }
        }
        while (neededMissions.size() < 12) {
            for (int i = 0; i < missions.size(); i++) {
                if (!neededMissions.contains(missions.get(i))) {
                    neededMissions.add(missions.get(i));
                }
            }
        }
        while (neededMissions.size() > 12) {
            int index = (int) (Math.random() * neededMissions.size());
            neededMissions.remove(index);
        }

        return neededMissions;
    }


    //finished 再开新坑 基于卖方市场的推荐算法 假设为 4

    /**
     * 计算影响因子 考虑因素
     * 就任务而言
     * 距离开始时间 结束时间
     * 已接收人数 总人数
     * 难度
     * 金钱奖励
     * 将要考虑的因素 发布者的等级？ 等级加权
     */
    private List<Mission> requestorRecommend() {
        List<Mission> missions = missionRepository.findUnFinishedMission();
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
        for (int i = 0; i < 12 * 2 && i < missions.size(); i++) {
            results.add(missions.get(indexs[i]));
        }
        while (results.size() > 12) {
            int index = (int) (Math.random() * results.size());
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

    /**
     * 理论上来讲4个数字 分别代表了 topFive 推荐法 基于内容的推荐法 协同标注法 基于requestor的标注法
     *
     * @param username 用户名
     * @return
     */
    // 记录了单人接受推荐的数目
    private int[] readRecord(String username) {
        String filename = dir + "/" + username + "_record.txt";
        File file = new File(filename);
        int[] sum = readNumber(file);
        if (sum[0] == 0 && sum[1] == 0) {
            sum[0] = 1;
            sum[1] = 1;
        }
        return sum;
    }

    private void writeRecord(String username, int[] records) {
        String filename = dir + "/" + username + "_record.txt";
        File file = new File(filename);
        writeNumber(file, records);
    }

    public void updateRecord(String username, int type) {
        int[] records = readRecord(username);
        records[type - 1]++;
        writeRecord(username, records);
        updateResult(type);
    }
    //单人被推荐的次数
    private int[] readSum(String username) {
        String filename = dir + "/" + username + "_sum.txt";
        File file = new File(filename);
        return readNumber(file);
    }

    private void writeSum(String username, int[] numbers) {
        String filename = dir + "/" + username + "_sum.txt";
        File file = new File(filename);
        writeNumber(file, numbers);
    }

    private void updateSum(String username, int type) {
        int[] sum = readSum(username);
        sum[type - 1]++;
        writeSum(username, sum);
    }
    //总体推荐被接受的次数
    private void updateResult(int type) {
        int[] records = readResult();
        records[type - 1]++;
        writeResult(records);
    }

    private void writeResult(int[] records) {
        File file = new File("./data/recommend/sum.txt");
        writeNumber(file, records);
    }

    private int[] readResult() {
        File file = new File("./data/recommend/result.txt");
        return readNumber(file);
    }

    private int[] readNumber(File file) {
        int[] sum = new int[4];
        try {
            if (!file.exists()) {
                file.createNewFile();
                return new int[4];
            }
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 4&&scanner.hasNextInt(); i++) {

                sum[i] = scanner.nextInt();
            }
            scanner.close();
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    private void writeNumber(File file, int[] number) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < 4; i++) {
                pw.println(number[i]);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //总体推荐的次数
    private void updateSum(int type) {
        int[] records = readSum();
        records[type - 1]++;
        writeSum(records);
    }

    private void writeSum(int[] records) {
        File file = new File("./data/recommend/sum.txt");
        writeNumber(file, records);
    }

    private int[] readSum() {
        File file = new File("./data/recommend/sum.txt");
        return readNumber(file);
    }

    /**
     * 得到总的推荐结果
     *
     * @return
     */
    @Override
    public double[] getRecommendResult() {
        int[] sum = readSum();
        int[] result = readResult();
        double[] rates = new double[sum.length + 1];
        for (int i = 0; i < 4; i++) {
            rates[i] = result[i] * 1.0 / sum[i];
        }
        return rates;
    }

    /**
     * 得到特定的推荐结果
     *
     * @param username
     */
    @Override
    public double[] getRecommendResult(String username) {
        int[] result = readRecord(username);
        int[] sum = readSum();
        return new double[0];
    }
}
