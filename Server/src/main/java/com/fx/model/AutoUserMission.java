package com.fx.model;

/**
 * Created by thinkpad on 2018/6/12.
 */
public class AutoUserMission {
    private int missionId;
    private int trainStart;
    private int trainLength;
    private int testStart;
    private int testLength;
    private boolean finishTrain;
    private boolean finishTest;

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getTrainStart() {
        return trainStart;
    }

    public void setTrainStart(int trainStart) {
        this.trainStart = trainStart;
    }

    public int getTrainLength() {
        return trainLength;
    }

    public void setTrainLength(int trainLength) {
        this.trainLength = trainLength;
    }

    public int getTestStart() {
        return testStart;
    }

    public void setTestStart(int testStart) {
        this.testStart = testStart;
    }

    public int getTestLength() {
        return testLength;
    }

    public void setTestLength(int testLength) {
        this.testLength = testLength;
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
