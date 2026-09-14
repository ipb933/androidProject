package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Games extends AppCompatActivity  implements View.OnClickListener{
    Button btnBack;
    RecyclerView lvGames;

    ArrayList<Game> games = new ArrayList<>();
    GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        lvGames  = (RecyclerView) findViewById(R.id.lvGames);
        Intent intent = getIntent();
        games = (ArrayList<Game>) intent.getSerializableExtra("games");
        gameAdapter = new GameAdapter(games);
        lvGames.setAdapter(gameAdapter);
        registerForContextMenu(lvGames);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            finish();
        }
    }
}