package com.fx.service.impl;

import com.fx.bean.RecommendResult;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by Hanxinhu at 8:43 2018/6/22/022
 */
public class RecommendServiceImplTest {
    RecommendServiceImpl recommendService = new RecommendServiceImpl();
    @Ignore
    @Test
    public void create1(){
        for (int i = 0; i < 400; i++) {
            String username = "test" + (i+1);
            for (int j = 0; j < 30; j++) {
              RecommendResult recommendResult = recommendService.recommend(username);
              int type = recommendResult.getType();
                int random =(int)( Math.random() * 10);
                if(random < 7)
                {
                    recommendService.updateRecord(username,type);
                }

            }
        }

    }
}