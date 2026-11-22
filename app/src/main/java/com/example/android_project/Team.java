package com.example.android_project;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    String teamName;
    int teamNumber;
    double averageScore;
    ArrayList<RobotAtGame> teamGames;

    public Team() {}


    public Team(String teamName, int teamNumber) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        averageScore = 0;
        teamGames = new ArrayList<>();
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

    public double getAverageScore() {
        int sumScore = 0;
        for (RobotAtGame robotAtGame : teamGames) {
            sumScore += robotAtGame.robotScore;
        }
        return (double)sumScore / teamGames.size();
    }

    public ArrayList<RobotAtGame> getTeamGames() {
        return teamGames;
    }

    public RobotAtGame getGame(int gameNumber) {
        for (RobotAtGame robotAtGame : teamGames) {
            if (robotAtGame.gameNumber == gameNumber) {
                return robotAtGame;
            }
        }

        return new RobotAtGame();
    }

    public void addTeamGame(RobotAtGame robotAtGame) {
        teamGames.add(robotAtGame);
    }

    public void removeTeamGame(RobotAtGame robotAtGame) {
        teamGames.remove(robotAtGame);
    }
}