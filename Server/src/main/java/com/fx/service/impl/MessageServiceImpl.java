package com.fx.service.impl;

import com.fx.model.Message;
import com.fx.repository.MessageRepository;
import com.fx.repository.impl.MessageRepositoryImpl;
import com.fx.service.MessageService;
import com.fx.util.ResultMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 15:05 2018/6/10/010
 */
@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository = new MessageRepositoryImpl();
    /**
     * 添加单条信息
     *
     * @param message
     */
    @Override
    public ResultMessage addMessage(Message message) {
        return messageRepository.addMessage(message);
    }

    /**
     * 添加多条信息
     *
     * @param messages
     * @return
     */
    @Override
    public ResultMessage addMessages(List<Message> messages) {
        return messageRepository.addMessages(messages);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public List<Message> findMessageByUsername(String username) {
        return messageRepository.findMessageByUsername(username);
    }

    /**
     * 更新已读未读
     *
     * @param id
     * @return
     */
    @Override
    public ResultMessage updateMessage(int id) {
        return messageRepository.updateMessage(id);
    }
}
