package com.fx.controller;

import com.fx.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thinkpad on 2018/6/6.
 */
@Controller
@RequestMapping("/counts/ml")
@CrossOrigin
public class MLController {
    @Autowired
    MLService mlService;

    public void addMLMission(){
        
    }

    public void getUserMission(){

    }

    public void getTargetGoldUser(){

    }

    public void addLabel(){

    }

    public void startMakeLabel(){

    }

    public void getWrongLabel(){

    }

    public void updateLabel(){

    }

}
