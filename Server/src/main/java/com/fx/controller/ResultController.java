package com.fx.controller;

import com.fx.model.CaptionClassificationResult;
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
     * 获得标注的结果 需要任务id
     */
    @RequestMapping(
            value = "getCaptionResult",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public List<CaptionClassificationResult> getCaptionResult(int missionID){
        System.out.println("get caption Result");
        return resultService.getCaptionResult(missionID);
    }
}
