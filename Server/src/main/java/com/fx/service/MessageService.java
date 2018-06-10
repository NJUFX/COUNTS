package com.fx.service;

import com.fx.model.Message;
import com.fx.util.ResultMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 15:02 2018/6/10/010
 */

public interface MessageService {
    /**
     * 添加单条信息
     */
    public ResultMessage addMessage(Message message);

    /**
     * 添加多条信息
     * @param messages
     * @return
     */
    public ResultMessage addMessages(List<Message> messages);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public List<Message> findMessageByUsername(String username);

    /**
     * 更新已读未读
     * @param id
     * @return
     */
    public ResultMessage updateMessage(int id);

}
