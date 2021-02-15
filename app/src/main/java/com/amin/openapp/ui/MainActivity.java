package com.amin.openapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;

import com.amin.openapp.Constants;
import com.amin.openapp.R;
import com.amin.openapp.adapter.TeamRecordsAdapter;
import com.amin.openapp.databinding.ActivityMainBinding;
import com.amin.openapp.model.Team;
import com.amin.openapp.viewmodel.TeamRecordsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private TeamRecordsViewModel viewModel;
    private String TAG = "MainActivity";
    private TeamRecordsAdapter recordsAdapter;
    ActivityMainBinding binding;

    private Observer<ArrayList<Team> > teamObserver = new Observer<ArrayList<Team> >() {
        @Override
        public void onChanged(ArrayList<Team> teams) {
            if (teams!=null && teams.size()>1){

                binding.lightProgress.off();
                binding.lightProgress.setVisibility(View.GONE);

                recordsAdapter = new TeamRecordsAdapter(MainActivity.this);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.recyclerView.setAdapter(recordsAdapter);

                recordsAdapter.setList(teams);

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //  for loading progress animation
        if (!binding.lightProgress.isOn()) {
            binding.lightProgress.on();
        }

        viewModel = new ViewModelProvider(this).get(TeamRecordsViewModel.class);

        // request team records data
        viewModel.getTeamRecords(Constants.api_key,Constants.api_host);

        // observer for teams
        viewModel.getTeamsList().observe(this,teamObserver);

    }
}