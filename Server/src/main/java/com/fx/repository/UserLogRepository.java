package com.fx.repository;

import com.fx.model.UserLog;
import com.fx.util.ResultMessage;

import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 14:21 2018/6/21/021
 */
public interface UserLogRepository {
    public ResultMessage addUserLog(UserLog userLog);

    public ResultMessage updateUserLog(UserLog userLog);

    public List<UserLog> findUserLogByTime(String time);

    public List<UserLog> findUserLogByUser(String username);

    public List<UserLog> findUserLogByTimeAndAction(String time,String action);

    public List<UserLog> findUserLogByAction(String action);

    public List<UserLog> findUserLogByUsernameAndActionAndTime(String username,String action,String time);


}
