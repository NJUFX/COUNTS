package com.fx.service;

import com.fx.bean.LineChart;
import com.fx.service.impl.AnalysisServiceImpl;
import org.junit.Test;


/**
 * Description:
 * Created by Hanxinhu at 20:27 2018/6/21/021
 */
public class AnalysisServiceTest {
    AnalysisService analysisService = new AnalysisServiceImpl();
    @Test
    public void test1(){
       LineChart l =  analysisService.getActiveMemberLine();
        System.out.println(l.getX());
        System.out.println(l.getY());
    }
}