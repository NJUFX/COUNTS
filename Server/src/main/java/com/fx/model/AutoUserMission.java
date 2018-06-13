package com.fx.model;

/**
 * Created by thinkpad on 2018/6/12.
 */
public class AutoUserMission {
    private String missionId;
    private int trainStart;
    private int trainEnd;
    private int testStart;
    private int testEnd;
    private boolean finishTrain;
    private boolean finishTest;

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public int getTrainStart() {
        return trainStart;
    }

    public void setTrainStart(int trainStart) {
        this.trainStart = trainStart;
    }


    public int getTestStart() {
        return testStart;
    }

    public void setTestStart(int testStart) {
        this.testStart = testStart;
    }

    public int getTrainEnd() {
        return trainEnd;
    }

    public void setTrainEnd(int trainEnd) {
        this.trainEnd = trainEnd;
    }

    public int getTestEnd() {
        return testEnd;
    }

    public void setTestEnd(int testEnd) {
        this.testEnd = testEnd;
    }

    public boolean isFinishTrain() {
        return finishTrain;
    }

    public void setFinishTrain(boolean finishTrain) {
        this.finishTrain = finishTrain;
    }

    public boolean isFinishTest() {
        return finishTest;
    }

    public void setFinishTest(boolean finishTest) {
        this.finishTest = finishTest;
    }
}
