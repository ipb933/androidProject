package com.example.android_project;

import java.io.Serializable;

public class Robot implements Serializable {
    String teamName;
    int teamNumber;
    double averageScore;
    int gamesPlayed;

    public Robot() {}


    public Robot(String teamName, int teamNumber) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        averageScore = 0;
        gamesPlayed = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public double getGamesPlayed() {
        return gamesPlayed;
    }

    public void addScore(double score) {
        averageScore = (averageScore*gamesPlayed + score)/(gamesPlayed + 1);
        gamesPlayed++;
    }
}