package com.amin.openapp.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amin.openapp.model.Team;
import com.amin.openapp.model.TeamRecords;
import com.amin.openapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TeamRecordsViewModel extends ViewModel {
    String TAG = "TeamRecordsViewModel";
    private Repository repository;
    MutableLiveData<ArrayList<Team>> teamsList = new MutableLiveData();
    private LiveData<List<Team>> favTeamList=null;

    public LiveData<List<Team>> getFavTeamList() {
        return favTeamList;
    }

    @ViewModelInject
    public TeamRecordsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Team>> getTeamsList() {
        return teamsList;
    }

    public void getTeamRecords(String api_key,String api_host) {
        repository.getTeamRecords(api_key,api_host).subscribeOn(Schedulers.io())
                .map(new io.reactivex.rxjava3.functions.Function<TeamRecords, ArrayList<Team>>() {
                    @Override
                    public ArrayList<Team> apply(TeamRecords teamRecords) throws Throwable {
                        ArrayList<Team> list = (ArrayList<Team>) teamRecords.getRecords();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> teamsList.setValue(result), error -> Log.e(TAG, error.getMessage()));
    }

    public void insertTeam(Team team){repository.insertTeam(team);}
    public void deleteTeam(int record){repository.deleteTeam(record);}
    public void getFavTeams(){favTeamList = repository.getFavTeams();}

}
