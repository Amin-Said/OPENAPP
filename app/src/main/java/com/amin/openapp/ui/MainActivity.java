package com.amin.openapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.amin.openapp.Constants;
import com.amin.openapp.R;
import com.amin.openapp.model.Team;
import com.amin.openapp.viewmodel.TeamRecordsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private TeamRecordsViewModel viewModel;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TeamRecordsViewModel.class);
        viewModel.getTeamRecords(Constants.api_key,Constants.api_host);
        viewModel.getTeamsList().observe(this, new Observer<ArrayList<Team>>() {
            @Override
            public void onChanged(ArrayList<Team> teams) {
                for (Team team: teams){
                    Log.d(TAG,team.getName());

                }

            }
        });

    }
}