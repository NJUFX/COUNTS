package com.fx.controller;

import com.fx.bean.CaptionResult;
import com.fx.bean.ClassificationResult;
import com.fx.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 16:42 2018/5/29/029
 */
@Controller
@RequestMapping("/counts/result")
@CrossOrigin
public class ResultController {
    @Autowired
    ResultService resultService;
    /**
     * 获得分类标注的接口  需要任务ID
     */
    @RequestMapping(
            value = "/getClassificationResult",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public List<ClassificationResult> getClassificationResult(@RequestParam int missionID){
        System.out.println("get classification Result");
        return resultService.getClassificationResult(missionID);
    }
    /**
     * 获得整体标注的结果 需要任务id
     */
    @RequestMapping(
            value = "getCaptionResult",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public List<CaptionResult> getCaptionResult(int missionID){
        System.out.println("get caption Result");
        return resultService.getCaptionResult(missionID);
    }
}
