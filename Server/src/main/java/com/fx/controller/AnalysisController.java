
package com.fx.controller;

import com.fx.bean.*;
import com.fx.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by thinkpad on 2018/4/1.
 */

@Controller
@RequestMapping("/counts/analysis")
@CrossOrigin
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;

    /**
     * 获得不同等级的工作者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    @RequestMapping(
            value = "/levelchart/worker",
            method = RequestMethod.GET,
            produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public List<UserLevelChart> getWorkerLevelChart(){

        return analysisService.getWorkerLevelChart();
    }

    /**
     * 获得不同等级发布者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    @RequestMapping(
            value = "/levelchart/requestor",
            method = RequestMethod.GET,
            produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public List<UserLevelChart> getRequestorLevelChart(){
        return analysisService.getRequestorLevelChart();
    }

    /**
     * 获得工作者的地理位置图表
     * @return
     */
    @RequestMapping(
            value = "/locationchart",
            method = RequestMethod.GET,
            produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public HashMap<String,Integer> getWorkerLocationChart(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("北京",2);
        map.put("南京",4);
        map.put("西安",6);
        map.put("武汉",6);
        map.put("长春",6);
        map.put("吉林",6);
        map.put("鄂尔多斯",6);
        map.put("无锡",6);
        return map;
    }

    /**
     * 获得发布者的地理位置图表
     * @return
     */
    @RequestMapping(
            value = "/locationchart/requestor",
            method = RequestMethod.GET,
            produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public UserLocationChart getRequestorLocationChart(){
        return analysisService.getRequestorLocationChart();
    }


    /**
     * 获得工作者总数
     * @return
     */
    @RequestMapping(
            value = "getnumber/worker",
            method = RequestMethod.GET
    )
    @ResponseBody
    public int getWorkerNumber(){
        return analysisService.getWorkerNumber();
    }

    /**
     * 获得发布者总数
     * @return
     */
    @RequestMapping(
            value = "getnumber/requestor",
            method = RequestMethod.GET
    )
    @ResponseBody
    public int getRequestorNumber(){

        return analysisService.getRequestorNumber();
    }

    @RequestMapping(
            value = "getmission/monthcart",
            method = RequestMethod.GET
    )
    @ResponseBody
    public MissionMonthChart getMissionMonthChart(){

        return analysisService.getMissionMonthChart();
    }

    @RequestMapping(
        value = "/boxchart",
        method = RequestMethod.GET
    )
    @ResponseBody
    public List<List<Integer>> getBoxChart(){

        return analysisService.getBoxChart();
    }

    @RequestMapping(
        value = "/predictchart",
        method = RequestMethod.GET
    )
    @ResponseBody
    public float getPredictChart(){

        return analysisService.getPredictChart();
    }

    @RequestMapping(
        value = "/newMemberLine",
        method = RequestMethod.GET
    )
    @ResponseBody
    public LineChart getNewMemberLine(){

        return analysisService.getNewMemberLine();
    }
    @RequestMapping(
        value = "/MemberLine",
        method = RequestMethod.GET
    )
    @ResponseBody
    public LineChart getMemberLine(){

        return analysisService.getMemberLine();
    }
    @RequestMapping(
        value = "/activeMemberLine",
        method = RequestMethod.GET
    )
    @ResponseBody
    public LineChart getActiveMember(){
        return analysisService.getActiveMemberLine();
    }
    @RequestMapping(
        value = "/getRecommendRate",
        method = RequestMethod.GET
    )
    @ResponseBody
    public LineChart getRecommendRate(){
        List<String> x = new ArrayList<>();
        x.add("top5推荐");
        x.add("内容推荐");
        x.add("协调过滤推荐");
        x.add("众包推荐");
        x.add("总体");
        List<Integer> y = new ArrayList<>();
        y.add(20);
        y.add(70);
        y.add(60);
        y.add(80);
        y.add(90);
        LineChart lineChart = new LineChart();
        lineChart.setY(y);
        lineChart.setX(x);
        return lineChart;
    }

    @RequestMapping(
        value = "/getWorkerMap",
        method = RequestMethod.POST,
        produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public LineChart getWorkerMap(String username){
        return analysisService.getWorkerChart(username);
    }
    @RequestMapping(
        value = "/getRequestorMap",
        method = RequestMethod.POST,
        produces =  {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public LineChart getRequestorMap(String username){
        return analysisService.getRequestorChart(username);
    }

}

