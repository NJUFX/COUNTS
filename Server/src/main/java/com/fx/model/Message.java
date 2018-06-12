package com.fx.model;

/**
 * Description:
 * Created by Hanxinhu at 17:10 2018/6/6/006
 */
public class Message {
    //唯一识别标注
    private int id;
    //信息的用户名
    private String username;
    // 自动标注的任务
    private int missionID;

    //已读未读
    private boolean isRead;
    // 类型
    /**
     * classification 为 分类
     * capture 为整体描述
     * detection 为方框标注
     */
    private String type;
    /**
     * 消息的标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息类型 0是标注 1是审查
     */
    private int messageType;
    /**
     * 标注的文件名
     */
    private String[] filenames;

    public Message() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

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


    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public String getType() {
        return type;
    }

    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }
}
