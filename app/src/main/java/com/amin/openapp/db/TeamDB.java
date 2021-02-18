package com.amin.openapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.amin.openapp.model.Team;

@Database(entities = Team.class,version = 1,exportSchema = false)
public abstract class TeamDB extends RoomDatabase {
    public abstract TeamDao teamDao();
}
