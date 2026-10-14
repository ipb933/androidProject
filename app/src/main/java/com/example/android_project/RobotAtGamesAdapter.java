package com.example.android_project;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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
        RobotAtGame robotAtGame = robotAtGames.get(position);
        holder.tvGameNumber.setText("game number: " + robotAtGame.getGameNumber());
        holder.tvScouterName.setText("scouter name: " + robotAtGame.getScouterName());
        holder.tvRobotScore.setText("robot score: " + robotAtGame.getRobotScore());

        holder.btnEditName.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Enter robot score");
            final EditText input = new EditText(v.getContext());
            input.setText(robotAtGame.getRobotScore() + "");
            builder.setView(input);
            builder.setPositiveButton("Save", (dialog, which) -> {
                robotAtGame.setRobotScore(Integer.parseInt(input.getText().toString()));
                notifyItemChanged(holder.getBindingAdapterPosition());
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {

                robotAtGames.remove(pos);

                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, robotAtGames.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return robotAtGames.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEditName, btnDelete;
        TextView tvGameNumber, tvScouterName, tvRobotScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEditName = itemView.findViewById(R.id.btnEditName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            tvGameNumber = itemView.findViewById(R.id.tvGameNumber);
            tvScouterName = itemView.findViewById(R.id.tvScouterName);
            tvRobotScore = itemView.findViewById(R.id.tvRobotScore);
        }
    }
}