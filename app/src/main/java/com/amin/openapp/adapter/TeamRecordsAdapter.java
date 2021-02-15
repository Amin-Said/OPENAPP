package com.amin.openapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amin.openapp.R;
import com.amin.openapp.databinding.TeamRecordItemBinding;
import com.amin.openapp.model.Team;

import java.util.ArrayList;

public class TeamRecordsAdapter extends RecyclerView.Adapter<TeamRecordsAdapter.ViewHolder> {

    ArrayList<Team> itemsList = new ArrayList<>();
    Context context;
    String TAG = "TeamRecordsAdapter" ;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TeamRecordItemBinding teamRecordItemBinding;


        public ViewHolder(@NonNull TeamRecordItemBinding teamRecordItemBinding) {
            super(teamRecordItemBinding.getRoot());
            this.teamRecordItemBinding = teamRecordItemBinding;
        }
    }

    public TeamRecordsAdapter(ArrayList<Team> list, Context context) {
        this.itemsList = list;
        this.context = context;
    }

    public TeamRecordsAdapter( Context context) {
        this.context = context;
    }
    @Override
    public TeamRecordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        TeamRecordItemBinding teamRecordItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.team_record_item, parent, false);
        return new TeamRecordsAdapter.ViewHolder(teamRecordItemBinding);
    }

    @Override
    public void onBindViewHolder(TeamRecordsAdapter.ViewHolder holder, int position) {

        final Team model = itemsList.get(position);
        model.setRecord(position+1);

        // set values to views
        holder.teamRecordItemBinding.setTeam(model);

    }

    public void setList(ArrayList<Team> list){
        this.itemsList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
