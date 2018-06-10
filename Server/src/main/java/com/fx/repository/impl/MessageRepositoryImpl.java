package com.fx.repository.impl;

import com.fx.model.Message;
import com.fx.repository.MessageRepository;
import com.fx.util.ResultMessage;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 15:11 2018/6/10/010
 */
public class MessageRepositoryImpl implements MessageRepository {

    public MessageRepositoryImpl(){

    }
    @Override
    public ResultMessage addMessage(Message message) {
        return null;
    }

    @Override
    public ResultMessage addMessages(List<Message> messages) {
        return null;
    }

    @Override
    public ResultMessage updateMessage(Message message) {
        return null;
    }

    @Override
    public List<Message> findMessageByUsername(String username) {
        return null;
    }
}
