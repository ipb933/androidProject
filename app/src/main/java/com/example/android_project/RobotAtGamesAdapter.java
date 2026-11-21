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
    Robot currentRobot;

    public RobotAtGamesAdapter(ArrayList<RobotAtGame> robotAtGames, Robot currentRobot) {
        this.robotAtGames = robotAtGames != null ? robotAtGames : new ArrayList<>();
        this.currentRobot = currentRobot;
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
            int pos = holder.getBindingAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) {
                return;
            }
            RobotAtGame targetItem = robotAtGames.get(pos);

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Enter robot score");
            final EditText input = new EditText(v.getContext());
            input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            input.setText(String.valueOf(targetItem.getRobotScore()));
            builder.setView(input);

            builder.setPositiveButton("Save", (dialog, which) -> {
                String scoreStr = input.getText().toString().trim();
                if (scoreStr.isEmpty()) {
                    android.widget.Toast.makeText(v.getContext(), "Please enter a score", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int newScore = Integer.parseInt(scoreStr);
                    int oldScore = targetItem.getRobotScore();
                    targetItem.setRobotScore(newScore);
                    if (currentRobot != null) {
                        currentRobot.updateScore(oldScore, newScore);
                    }
                    notifyItemChanged(pos);
                } catch (NumberFormatException e) {
                    android.widget.Toast.makeText(v.getContext(), "Invalid score", android.widget.Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                RobotAtGame removedItem = robotAtGames.remove(pos);
                if (currentRobot != null) {
                    currentRobot.removeScore(removedItem.getRobotScore());
                }
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, robotAtGames.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return robotAtGames == null ? 0 : robotAtGames.size();
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