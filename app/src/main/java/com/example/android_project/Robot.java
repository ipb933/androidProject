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

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addScore(double score) {
        averageScore = (averageScore*gamesPlayed + score)/(gamesPlayed + 1);
        gamesPlayed++;
    }

    public void updateScore(double oldScore, double newScore) {
        if (gamesPlayed <= 0) {
            addScore(newScore);
            return;
        }
        double totalScore = averageScore * gamesPlayed - oldScore + newScore;
        averageScore = totalScore / gamesPlayed;
    }

    public void removeScore(double score) {
        if (this.gamesPlayed <= 1) {
            this.averageScore = 0;
            this.gamesPlayed = 0;
            return;
        }
        double totalScore = this.averageScore * this.gamesPlayed - score;
        this.gamesPlayed--;
        this.averageScore = totalScore / this.gamesPlayed;
    }
}