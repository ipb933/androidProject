package com.example.android_project;

import java.io.Serializable;

public class RobotAtGame implements Serializable {
    int robotNumber;
    int gameNumber;
    int robotScore;

    public RobotAtGame() {}

    public RobotAtGame(int robotNumber, int gameNumber, int robotScore) {
        this.robotNumber = robotNumber;
        this.gameNumber = gameNumber;
        this.robotScore = robotScore;
    }

    public int getRobotNumber() {
        return robotNumber;
    }

    public void setRobotNumber(int robotNumber) {
        this.robotNumber = robotNumber;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getRobotScore() {
        return robotScore;
    }

    public void setRobotScore(int robotScore) {
        this.robotScore = robotScore;
    }
}
