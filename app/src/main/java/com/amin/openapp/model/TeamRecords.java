package com.amin.openapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamRecords {
    public TeamRecords(List<Team> records) {
        this.records = records;
    }

    @SerializedName("records")
    @Expose
    private List<Team> records = null;

    public List<Team> getRecords() {
        return records;
    }

    public void setRecords(List<Team> records) {
        this.records = records;
    }
}
