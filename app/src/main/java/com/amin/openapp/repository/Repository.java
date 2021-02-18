package com.amin.openapp.repository;

import androidx.lifecycle.LiveData;

import com.amin.openapp.api.LeagueApiService;
import com.amin.openapp.db.TeamDao;
import com.amin.openapp.model.Team;
import com.amin.openapp.model.TeamRecords;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {

    private LeagueApiService leagueApiService;
    private TeamDao teamDao;

    @Inject
    public Repository(LeagueApiService leagueApiService,TeamDao teamDao) {
        this.leagueApiService = leagueApiService;
        this.teamDao = teamDao;
    }


    public Observable<TeamRecords> getTeamRecords(String api_key,String api_host){
        return leagueApiService.getRecords(api_key,api_host);
    }

    public void insertTeam(Team team){teamDao.insertTeam(team);}
    public void deleteTeam(int record){teamDao.deleteTeam(record);}
    public LiveData<List<Team>> getFavTeams(){return teamDao.getFavTeams();}

}
