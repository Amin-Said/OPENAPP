package com.amin.openapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.amin.openapp.model.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert
    public void insertTeam(Team team);

    @Query("delete from fav_team where record =:record")
    public void deleteTeam(int record);

    @Query("select * from fav_team")
    public LiveData<List<Team>> getFavTeams();
}
