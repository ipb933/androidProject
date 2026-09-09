package com.example.android_project;

import java.io.Serializable;

public class Game  implements Serializable {
    int gameNumber;

    int blue1Number;
    int blue2Number;
    int blue3Number;
    int red1Number;
    int red2Number;
    int red3Number;

    public Game(int gameNumber, int blue1Number, int blue2Number, int blue3Number, int red1Number, int red2Number, int red3Number) {
        this.gameNumber = gameNumber;
        this.blue1Number = blue1Number;
        this.blue2Number = blue2Number;
        this.blue3Number = blue3Number;
        this.red1Number = red1Number;
        this.red2Number = red2Number;
        this.red3Number = red3Number;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setBlue1Number(int blue1Number) {
        this.blue1Number = blue1Number;
    }

    public int getBlue1Number() {
        return blue1Number;
    }

    public void setBlue2Number(int blue2Number) {
        this.blue2Number = blue2Number;
    }

    public int getBlue2Number() {
        return blue2Number;
    }

    public void setBlue3Number(int blue3Number) {
        this.blue3Number = blue3Number;
    }

    public int getBlue3Number() {
        return blue3Number;
    }

    public void setRed1Number(int red1Number) {
        this.red1Number = red1Number;
    }

    public int getRed1Number() {
        return red1Number;
    }

    public void setRed2Number(int red2Number) {
        this.red2Number = red2Number;
    }

    public int getRed2Number() {
        return red2Number;
    }

    public void setRed3Number(int red3Number) {
        this.red3Number = red3Number;
    }

    public int getRed3Number() {
        return red3Number;
    }
}
