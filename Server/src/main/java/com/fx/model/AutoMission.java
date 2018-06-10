package com.fx.model;

/**
 * Description:
 * Created by Hanxinhu at 16:00 2018/6/8/008
 */
public class AutoMission {
    /**
     * 唯一标识符
     */
    private int id;
    /**
     * 发布者的username
     */
    private String requestorID;
    /**
     * 是否已完成
     */
    private boolean finished;
    /**
     * 总图片数
     */
    private int size;
    /**
     *  目前分为 Classification Caption两种
     */
    private String type;
    /**
     * 该任务需要支付的总额
     */
    private int points;
    /**
     * 项目开始时间
     */
    private String beginTime;


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public AutoMission(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(String requestorID) {
        this.requestorID = requestorID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
