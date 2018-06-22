package com.fx.service;

import com.fx.bean.RecommendResult;

/**
 * Description:
 * Created by Hanxinhu at 22:44 2018/6/10/010
 */
public interface RecommendService {
    public RecommendResult recommend(String username);

    /**
     * 得到总的推荐结果
     * @return
     */
    public double [] getRecommendResult();

    /**
     * 得到特定的推荐结果
     */
    public double[] getRecommendResult(String username);
}
