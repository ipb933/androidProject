package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Games extends AppCompatActivity  implements View.OnClickListener{
    Button btnAddGame, btnBack;
    RecyclerView rvGames;

    ArrayList<Game> games = new ArrayList<>();
    GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        btnAddGame = (Button) findViewById(R.id.btnAddGame);
        btnAddGame.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        rvGames  = findViewById(R.id.rvGames);
        Intent intent = getIntent();
        games = (ArrayList<Game>) intent.getSerializableExtra("games");
        gameAdapter = new GameAdapter(games);
        rvGames.setAdapter(gameAdapter);
        rvGames.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        registerForContextMenu(rvGames);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAddGame) {
            Intent intent = new Intent(this, AddGame.class);
            addGame.launch(intent);
        }
        if (view == btnBack) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("games", games);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private final ActivityResultLauncher<Intent> addGame =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                Game game = (Game) result.getData().getSerializableExtra("game");
                                if (games == null) {
                                    return;
                                }
                                games.add(game);
                                gameAdapter.notifyDataSetChanged();
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}