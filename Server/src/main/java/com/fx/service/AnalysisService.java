package com.fx.service;

import com.fx.bean.*;

import java.util.List;

/**
 * Created by thinkpad on 2018/4/1.
 */
public interface AnalysisService {
    /**
     * 获得不同等级的工作者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getWorkerLevelChart();

    /**
     * 获得不同等级发布者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getRequestorLevelChart();

    /**
     * 获得工作者的地理位置图表
     * @return
     */
    public UserLocationChart getWorkerLocationChart();

    /**
     * 获得发布者的地理位置图表
     * @return
     */
    public UserLocationChart getRequestorLocationChart();

    public int getWorkerNumber();

    public int getRequestorNumber();

    public MissionMonthChart getMissionMonthChart();


    public List<Integer> getBoxChart();

    public float getPredictChart();

    /**
     * 得到总用户数近30天的变化图
     * @return
     */
    public LineChart getMemberLine();

    /**
     * 得到新增用户近30天的变化图
     * @return
     */
    public LineChart getNewMemberLine();
    /**
     * 得到近30天活跃用户的变化图
     */
    public LineChart getActiveMemberLine();

    /**
     * 得到推荐命中率的图
     * @return
     */
    public LineChart getRecommendRate();

    /**
     * 得到总体推荐平均的权重值
     * @return
     */
    public int[] getRecommendWeight();

    /**
     * 得到目标用户的总体推荐权重的用户值
     * @param username
     * @return
     */
    public int[] getRecommendWeight(String username);

    /**
     * work的热力图
     * @param username
     * @return
     */
    public LineChart getWorkerChart(String username);

    /**
     * requestor
     * @param username
     * @return
     */
    public LineChart getRequestorChart(String username);
}
