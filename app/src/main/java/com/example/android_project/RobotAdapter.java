package com.example.android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.RobotViewHolder> {

    ArrayList<Robot> robots;
    ArrayList<RobotAtGame> robotsAtGame;

    public RobotAdapter(ArrayList<Robot> robots, ArrayList<RobotAtGame> robotAtGames) {
        this.robots = robots;
        this.robotsAtGame = robotAtGames;
    }

    @NonNull
    @Override
    public RobotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_robot, parent, false);
        return new RobotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RobotViewHolder holder, int position) {
        Robot robot = robots.get(position);


        holder.tvRobotNumber.setText("Team Number: " + robot.getTeamNumber());
        holder.tvTeamName.setText("Name: " + (robot.getTeamName() == null || robot.getTeamName().isEmpty() ? "No Name" : robot.getTeamName()));

        if (robot.getGamesPlayed() == 0) {
            holder.tvRobotAverageScore.setText("Avg Score: Didn't play yet");
        } else {
            holder.tvRobotAverageScore.setText(String.format("Avg Score: %.2f", robot.getAverageScore()));
        }

        holder.btnEditName.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Enter Team Name");
            final EditText input = new EditText(v.getContext());
            input.setText(robot.getTeamName());
            builder.setView(input);
            builder.setPositiveButton("Save", (dialog, which) -> {
                robot.setTeamName(input.getText().toString());
                notifyItemChanged(holder.getBindingAdapterPosition());
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Robot robotToDelete = robots.get(pos);

                robots.remove(pos);

                if (robotsAtGame != null) {
                    for (int i = robotsAtGame.size() - 1; i >= 0; i--) {
                        if (robotsAtGame.get(i).getRobotNumber() == robotToDelete.getTeamNumber()) {
                            robotsAtGame.remove(i);
                        }
                    }
                }

                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, robots.size());
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RobotDetailsActivity.class);
            intent.putExtra("robotNumber", robot.getTeamNumber());

            ArrayList<RobotAtGame> robotAtGames = new ArrayList<>();
            if (robotsAtGame != null) {
                for (RobotAtGame game : robotsAtGame) {
                    if (game.getRobotNumber() == robot.getTeamNumber()) {
                        robotAtGames.add(game);
                    }
                }
            }

            intent.putExtra("robotAtGames", robotAtGames);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return robots.size();
    }

    static class RobotViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnEditName, btnDelete;

        TextView tvTeamName, tvRobotNumber, tvRobotAverageScore;

        public RobotViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEditName = itemView.findViewById(R.id.btnEditName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            tvTeamName = itemView.findViewById(R.id.tvTeamName);
            tvRobotNumber = itemView.findViewById(R.id.tvRobotNumber);
            tvRobotAverageScore = itemView.findViewById(R.id.tvRobotAverageScore);
        }
    }
}