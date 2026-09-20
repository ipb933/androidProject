package com.example.android_project;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.RobotViewHolder> {

    ArrayList<Robot> robots;

    public RobotAdapter(ArrayList<Robot> robots) {
        this.robots = robots;
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
        holder.tvTeamName.setText(robot.getTeamName() == null || robot.getTeamName().isEmpty() ? "No Name" : robot.getTeamName());
        holder.tvRobotNumber.setText(String.valueOf(robot.getTeamNumber()));
        holder.tvRobotAverageScore.setText(String.format("%.2f", robot.getAverageScore()));

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Enter Team Name");
            final EditText input = new EditText(v.getContext());
            input.setText(robot.getTeamName());
            builder.setView(input);
            builder.setPositiveButton("Save", (dialog, which) -> {
                robot.setTeamName(input.getText().toString());
                notifyItemChanged(position);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return robots.size();
    }

    static class RobotViewHolder extends RecyclerView.ViewHolder {
        TextView tvTeamName, tvRobotNumber, tvRobotAverageScore;

        public RobotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeamName = itemView.findViewById(R.id.tvTeamName);
            tvRobotNumber = itemView.findViewById(R.id.tvRobotNumber);
            tvRobotAverageScore = itemView.findViewById(R.id.tvRobotAverageScore);
        }
    }
}