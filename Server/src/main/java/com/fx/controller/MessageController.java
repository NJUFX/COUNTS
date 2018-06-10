package com.fx.controller;

import com.fx.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 14:42 2018/6/10/010
 */
@Controller
@RequestMapping("/counts/message")
@CrossOrigin
public class MessageController {
    @RequestMapping(
            value = "getMessage",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"}

    )
    @ResponseBody
    public List<Message> getMessageByUsername(String username){
        System.out.println(1);
        return null;
    }

}
