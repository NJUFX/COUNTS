package com.fx.model;

/**
 * Created by thinkpad on 2018/4/9.
 */

/**
 * 用户已经接受的任务
 */
public class AcceptedMission {
    /**
     * 正在进行中的任务id
     */
    private  int id;

    /**
     * 任务名
     */
    private String missionName;

    /**
     * 任务起始日期
     */
    private String begin;
    /**
     * 任务结束时间
     */
    private String end;

    /**
     * 任务类型  分别为字符串
     * Classification、Detection、Segmentation、Caption、Attribute
     */
    private String type;

    /**
     * 已经完成的图片数目
     */
    private  int finished;

    /**
     * 该任务需要标注的总图片数目
     */
    private int sum;

    public AcceptedMission() {
    }

    public AcceptedMission(int id, String missionName, String begin, String end, String type, int finished, int sum) {
        this.id = id;
        this.missionName = missionName;
        this.begin = begin;
        this.end = end;
        this.type = type;
        this.finished = finished;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int addone(){
        if (finished<sum)
            finished++;
        return finished;
    }
}

