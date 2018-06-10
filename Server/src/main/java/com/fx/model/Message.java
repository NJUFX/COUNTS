package com.fx.model;

/**
 * Description:
 * Created by Hanxinhu at 17:10 2018/6/6/006
 */
public class Message {
    //唯一识别标注
    int id;
    //信息的用户名
    String username;
    //已读
    boolean isRead;
    // 类型
    int type;
    // 自动标注的任务
    int missionID;
    //xxxx-xx-xx xx:xx:xx
    String time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }
}
