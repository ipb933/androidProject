package com.example.android_project;

import java.io.Serializable;

public class RobotAtGame implements Serializable {
    int robotNumber;
    int gameNumber;
    int robotScore;
    String scouterName;

    public RobotAtGame() {}

    public RobotAtGame(int gameNumber, int robotNumber, int robotScore, String scouterName) {
        this.gameNumber = gameNumber;
        this.robotNumber = robotNumber;
        this.robotScore = robotScore;
        this.scouterName = scouterName;
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

    public String getScouterName() {
        return scouterName;
    }

    public void setScouterName(String scouterName) {
        this.scouterName = scouterName;
    }
}
