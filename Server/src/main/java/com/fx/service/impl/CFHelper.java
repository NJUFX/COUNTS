package com.fx.service.impl;

import com.fx.model.AcceptedMission;
import com.fx.repository.AcceptMissionRepository;
import com.fx.repository.impl.AcceptMissionRepositoryImpl;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 15:03 2018/6/17/017
 */
public class CFHelper {
    private static final String dirname = "../data/recommend";

    public CFHelper() {
        File file = new File(dirname);
        if (!file.exists())
            file.mkdir();
    }

    AcceptMissionRepository acceptMissionRepository = new AcceptMissionRepositoryImpl();

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

        //生成矩阵
        for (AcceptedMission a : acceptedMissions
            ) {
            int nameIndex = usernames.indexOf(a.getUsername());
            int idIndex = ids.indexOf(a.getId());
            array[nameIndex][idIndex] = a.getScore();
        }
        //计算调整的余弦（Adjusted Cosine）相似度计算
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
            //用户的打分平均值
            averageUser[i] = sum / number;
        }

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


        //预估可能的打分
        double[][] answer = new double[usernames.size()][ids.size()];
        //输出结果
        for (int i = 0; i < usernames.size(); i++) {
            for (int j = 0; j < ids.size(); j++) {
                if (array[i][j] == 0) {
                    double sumOfsim = 0;
                    double sumOfscore = 0;
                    for (int k = 0; k < ids.size(); k++) {
                        if (k != j) {
                            sumOfsim += similarity[k][j];
                            sumOfscore += similarity[k][j] * array[k][j];
                        }
                    }
                  answer[i][j] = sumOfscore / sumOfsim;
                }
            }
        }
        // 如果分值大于 用户的平均分 则推荐
        for (int i = 0; i < usernames.size(); i++) {

            String filename = dirname + "/" + usernames.get(i) + ".txt";
            try {
                PrintWriter pw = new PrintWriter(filename);
                for (int j = 0; j < ids.size(); j++) {
                    if (answer[i][j] > averageUser[i]) {
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
