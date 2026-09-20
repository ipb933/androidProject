package com.example.android_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
    ArrayList<Game> games;
    public GameAdapter(ArrayList<Game> games) {
        this.games=games;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_game, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Game game = games.get(position);

        holder.tvGameNumber.setText(String.valueOf(game.getGameNumber()));
        holder.tvBlue1.setText(String.valueOf(game.getBlue1Number()));
        holder.tvBlue2.setText(String.valueOf(game.getBlue2Number()));
        holder.tvBlue3.setText(String.valueOf(game.getBlue3Number()));
        holder.tvRed1.setText(String.valueOf(game.getRed1Number()));
        holder.tvRed2.setText(String.valueOf(game.getRed2Number()));
        holder.tvRed3.setText(String.valueOf(game.getRed3Number()));
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    // ViewHolder class
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvGameNumber, tvBlue1, tvBlue2, tvBlue3, tvRed1, tvRed2, tvRed3;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            tvGameNumber = itemView.findViewById(R.id.tvGameNumber);
            tvBlue1 = itemView.findViewById(R.id.tvBlue1);
            tvBlue2 = itemView.findViewById(R.id.tvBlue2);
            tvBlue3 = itemView.findViewById(R.id.tvBlue3);
            tvRed1 = itemView.findViewById(R.id.tvRed1);
            tvRed2 = itemView.findViewById(R.id.tvRed2);
            tvRed3 = itemView.findViewById(R.id.tvRed3);
        }
    }
}