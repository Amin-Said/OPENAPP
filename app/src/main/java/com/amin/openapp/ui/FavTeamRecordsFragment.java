package com.amin.openapp.ui;

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
import com.amin.openapp.databinding.FragmentFavTeamRecordsBinding;
import com.amin.openapp.databinding.FragmentTeamRecordsBinding;
import com.amin.openapp.model.Team;
import com.amin.openapp.viewmodel.TeamRecordsViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavTeamRecordsFragment extends Fragment {

    FragmentFavTeamRecordsBinding binding;
    View navView;

    private String TAG = "FavTeamRecordsFragment";
    private TeamRecordsViewModel viewModel;
    private TeamRecordsAdapter recordsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_fav_team_records, container, false);
        View view = binding.getRoot();
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.fav_title));


        return view;
    }
    private Observer<List<Team>> favTeamObserver = new Observer<List<Team> >() {
        @Override
        public void onChanged(List<Team> teams) {
            if (teams!=null && teams.size()>1){


                recordsAdapter = new TeamRecordsAdapter(getContext(),"fav");
                binding.favRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.favRecyclerView.setAdapter(recordsAdapter);

                // to setup swipe on teams items
                setupSwipeOnRecyclerView();

                recordsAdapter.setList((ArrayList<Team>) teams);

            }

        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.homeBtn.setOnClickListener(homeBtnClickListener);

        viewModel = new ViewModelProvider(this).get(TeamRecordsViewModel.class);

        // request fav teams records data
        viewModel.getFavTeams();

        // observer for teams
        viewModel.getFavTeamList().observe(getViewLifecycleOwner(),favTeamObserver);


    }

    View.OnClickListener homeBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NavDirections action = FavTeamRecordsFragmentDirections.actionGoToMainTeamRecords();
            navView = binding.homeBtn;
            Navigation.findNavController(navView).navigate(action);
        }
    };

    private void setupSwipeOnRecyclerView(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedTeamPosition = viewHolder.getAdapterPosition();
                Team swipedTeam = recordsAdapter.getTeamAt(swipedTeamPosition);
                viewModel.deleteTeam(swipedTeam.getRecord());
                recordsAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),"you deleted this team in the DB",Toast.LENGTH_LONG).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.favRecyclerView);
    }
}