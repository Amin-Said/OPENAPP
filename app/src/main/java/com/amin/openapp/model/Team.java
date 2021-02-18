package com.amin.openapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "fav_team")
public class Team {

    public Team(String team, int played, int win, int draw, int loss, int goalsFor, int goalsAgainst, int points, int record, int id) {
        this.team = team;
        this.played = played;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.points = points;
        this.record = record;
        this.id = id;
    }

    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("played")
    @Expose
    private int played;
    @SerializedName("win")
    @Expose
    private int win;
    @SerializedName("draw")
    @Expose
    private int draw;
    @SerializedName("loss")
    @Expose
    private int loss;
    @SerializedName("goalsFor")
    @Expose
    private int goalsFor;
    @SerializedName("goalsAgainst")
    @Expose
    private int goalsAgainst;
    @SerializedName("points")
    @Expose
    private int points;
    @PrimaryKey
    private int record;
    private int id;

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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
