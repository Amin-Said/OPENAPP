package com.amin.openapp.ui;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amin.openapp.Constants;
import com.amin.openapp.R;
import com.amin.openapp.adapter.TeamRecordsAdapter;
import com.amin.openapp.databinding.FragmentTeamRecordsBinding;
import com.amin.openapp.model.Team;
import com.amin.openapp.viewmodel.TeamRecordsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TeamRecordsFragment extends Fragment {

    FragmentTeamRecordsBinding binding;
    View navView;
    private String TAG = "TeamRecordsFragment";
    private TeamRecordsViewModel viewModel;
    private TeamRecordsAdapter recordsAdapter;

    private Observer<ArrayList<Team>> teamObserver = new Observer<ArrayList<Team> >() {
        @Override
        public void onChanged(ArrayList<Team> teams) {
            if (teams!=null && teams.size()>1){

                binding.lightProgress.off();
                binding.lightProgress.setVisibility(View.GONE);

                recordsAdapter = new TeamRecordsAdapter(getContext(),"main");
                binding.mainRecordsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.mainRecordsRecyclerView.setAdapter(recordsAdapter);

                // to setup swipe on teams items
                setupSwipeOnRecyclerView();

                recordsAdapter.setList(teams);

            }

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  for loading progress animation
        if (!binding.lightProgress.isOn()) {
            binding.lightProgress.on();
        }

        binding.favBtn.setOnClickListener(favBtnClickListener);

        viewModel = new ViewModelProvider(this).get(TeamRecordsViewModel.class);

        // request team records data
        viewModel.getTeamRecords(Constants.api_key,Constants.api_host);

        // observer for teams
        viewModel.getTeamsList().observe(getViewLifecycleOwner(),teamObserver);


    }

    View.OnClickListener favBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          NavDirections action = TeamRecordsFragmentDirections.actionGoToFavRecords();
          navView = binding.favBtn;
          Navigation.findNavController(navView).navigate(action);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_team_records, container, false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.app_name));

        return view;
    }

    private void setupSwipeOnRecyclerView(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedTeamPosition = viewHolder.getAdapterPosition();
                Team swipedTeam = recordsAdapter.getTeamAt(swipedTeamPosition);
                try {
                    viewModel.insertTeam(swipedTeam);
                    recordsAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),"you inserted the team in the DB",Toast.LENGTH_LONG).show();
                }catch (SQLiteConstraintException e){
                    recordsAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),"you already added this team in the DB",Toast.LENGTH_LONG).show();
                }

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.mainRecordsRecyclerView);
    }
}