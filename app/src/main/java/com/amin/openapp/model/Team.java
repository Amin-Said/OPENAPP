package com.amin.openapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {
    public Team(String name, int played, int win, int draw, int loss, int goalsFor, int goalsAgainst, int points) {
        this.team = name;
        this.played = played;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.points = points;
    }

    public String getName() {
        return team;
    }

    public void setName(String name) {
        this.team = name;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("played")
    @Expose
    private Integer played;
    @SerializedName("win")
    @Expose
    private Integer win;
    @SerializedName("draw")
    @Expose
    private Integer draw;
    @SerializedName("loss")
    @Expose
    private Integer loss;
    @SerializedName("goalsFor")
    @Expose
    private Integer goalsFor;
    @SerializedName("goalsAgainst")
    @Expose
    private Integer goalsAgainst;
    @SerializedName("points")
    @Expose
    private Integer points;
}
