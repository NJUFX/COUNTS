package com.fx.model;

/**
 * Description:
 * Created by Hanxinhu at 14:00 2018/6/21/021
 */
public class UserLog {
    public final static String SIGN_IN = "SIGN_IN";
    public final static String REGISTER = "REGISTER";
    public final static String ACCEPT = "ACCEPT_MISSION";
    public final static String WORK = "WORK";
    public final static String RELEASE = "RELEASE_MISSION";

    /**
     * 用户名
     */
    String username;
    /**
     * 发生的时间啊
     */
    String time;
    /**
     * 做的动作
     */
    String action;

    public UserLog() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
