package com.fx.repository;

import com.fx.model.Message;
import com.fx.util.ResultMessage;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 15:09 2018/6/10/010
 */
public interface MessageRepository {

    public ResultMessage addMessage(Message message);

    public ResultMessage addMessages(List<Message> messages);

    public ResultMessage updateMessage(Message message);

    public List<Message> findMessageByUsername(String username);


}
