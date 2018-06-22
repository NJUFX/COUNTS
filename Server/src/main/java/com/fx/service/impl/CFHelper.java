package com.fx.service.impl;

import com.fx.model.AcceptedMission;
import com.fx.repository.AcceptMissionRepository;
import com.fx.repository.impl.AcceptMissionRepositoryImpl;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 基于于物品的协同过滤算法(item-based collaborative filtering)
 * Created by Hanxinhu at 15:03 2018/6/17/017
 */
public class CFHelper {
    private static final String dirname = "./data/recommend";

    public CFHelper() {
        File file = new File(dirname);
        if (!file.exists())
            file.mkdir();
    }

    AcceptMissionRepository acceptMissionRepository = new AcceptMissionRepositoryImpl();
    //  Item-based的基本思想是预先根据所有用户的历史偏好数据计算物品之间的相似性，
    // 然后把与用户喜欢的物品相类似的物品推荐给用户。
    //   因为物品直接的相似性相对比较固定，所以可以预先在线下计算好不同物品之间的相似度，把结果存在表中，
    // 当推荐时进行查表，计算用户可能的打分值，可以同时解决上面两个问题
    //数据稀疏性 算法扩展性
    public void calculate() {
        List<AcceptedMission> acceptedMissions = acceptMissionRepository.getAllAcceptMission();
        List<String> usernames = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        for (AcceptedMission a : acceptedMissions) {
            String name = a.getUsername();
            if (!usernames.contains(name))
                usernames.add(name);
            int id = a.getId();
            if (!ids.contains(id))
                ids.add(id);
        }
        int[][] array = new int[usernames.size()][ids.size()];

        //生成user和missionid矩阵
        for (AcceptedMission a : acceptedMissions
            ) {
            int nameIndex = usernames.indexOf(a.getUsername());
            int idIndex = ids.indexOf(a.getId());
            array[nameIndex][idIndex] = a.getScore();
        }
        //计算调整的余弦（Adjusted Cosine）相似度计算，
        // 由于基于余弦的相似度计算没有考虑不同用户的打分情况，可能有的用户偏向于给高分，而有的用户偏向于给低分，
        // 该方法通过减去用户打分的平均值消除不同用户打分习惯的影响：
        double[][] similarity = new double[ids.size()][ids.size()];
        double[] averageUser = new double[usernames.size()];
        for (int i = 0; i < averageUser.length; i++) {
            int number = 0;
            double sum = 0;
            for (int j = 0; j < ids.size(); j++) {
                if (array[i][j] != 0) {
                    sum += array[i][j];
                    number++;
                }
            }
            //计算出用户的打分平均值
            averageUser[i] = sum / number;
        }

        //开始计算余弦相似度
        for (int i = 0; i < ids.size(); i++) {
            for (int j = i + 1; j < ids.size(); j++) {
                double sumi = 0;
                double sumj = 0;
                double sumij = 0;
                for (int k = 0; k < usernames.size(); k++) {
                    sumi += (array[k][i] - averageUser[k]) * (array[k][i] - averageUser[k]);
                    sumj += (array[k][j] - averageUser[k]) * (array[k][j] - averageUser[k]);
                    sumij += (array[k][j] - averageUser[k]) * (array[k][i] - averageUser[k]);
                }
                double sim = Math.sqrt(sumij * sumij / sumi / sumj);
                similarity[i][j] = similarity[j][i] = sim;
            }
        }


        //预估可能的打分 根据之前算好的物品之间的相似度，接下来对用户未打分的物品进行预测
        double[][] answer = new double[usernames.size()][ids.size()];
        //输出结果 加权求和。
        //
        //   通过过对用户u已打分的物品的分数进行加权求和，权值为各个物品与物品i的相似度，
        // 然后对所有物品相似度的和求平均，计算得到用户u对物品i打分
        for (int i = 0; i < usernames.size(); i++) {
            for (int j = 0; j < ids.size(); j++) {
                if (array[i][j] == 0) {
                    double sumOfsim = 0;
                    double sumOfscore = 0;
                    for (int k = 0; k < ids.size(); k++) {
                        if (k != j && array[k][j]!=0) {
                            sumOfsim += similarity[k][j];
                            sumOfscore += similarity[k][j] * array[k][j];
                        }
                    }
                  answer[i][j] = sumOfscore / sumOfsim;
                }
            }
        }
        // 如果分值大于 用户的平均分 则推荐给用户
        for (int i = 0; i < usernames.size(); i++) {
            String filename = dirname + "/" + usernames.get(i) + ".txt";
            try {
                File file = new File(filename);
                PrintWriter pw = new PrintWriter(file);
                for (int j = 0; j < ids.size(); j++) {
                    if (answer[i][j] > averageUser[i] ) {
                        int id = ids.get(j);
                        pw.println(id);
                    }
                }
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new CFHelper().calculate();
    }


}
