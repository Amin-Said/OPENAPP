package com.amin.openapp.repository;

import com.amin.openapp.api.LeagueApiService;
import com.amin.openapp.model.TeamRecords;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    @Inject
    public Repository(LeagueApiService leagueApiService) {
        this.leagueApiService = leagueApiService;
    }

    private LeagueApiService leagueApiService;

    public Observable<TeamRecords> getTeamRecords(String api_key,String api_host){
        return leagueApiService.getRecords(api_key,api_host);
    }

}
