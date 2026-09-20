package com.example.android_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RobotAtGamesAdapter extends RecyclerView.Adapter<RobotAtGamesAdapter.ViewHolder> {

    ArrayList<RobotAtGame> robotAtGames;

    public RobotAtGamesAdapter(ArrayList<RobotAtGame> robotAtGames) {
        this.robotAtGames = robotAtGames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_robot_at_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RobotAtGame game = robotAtGames.get(position);
        holder.tvGameNumber.setText(String.valueOf(game.getGameNumber()));
        holder.tvScouterName.setText(game.getScouterName());
        holder.tvRobotScore.setText(String.valueOf(game.getRobotScore()));
    }

    @Override
    public int getItemCount() {
        return robotAtGames.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameNumber, tvScouterName, tvRobotScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGameNumber = itemView.findViewById(R.id.tvGameNumber);
            tvScouterName = itemView.findViewById(R.id.tvScouterName);
            tvRobotScore = itemView.findViewById(R.id.tvRobotScore);
        }
    }
}