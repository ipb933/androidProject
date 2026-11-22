package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    String scouterName;
    Button btnAdd, btnShowRobots, btnShowGames;
    ArrayList<Team> robots = new ArrayList<>();
    ArrayList<Game> games = new ArrayList<>();
    GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gameAdapter = new GameAdapter(games);
        btnAdd = (Button) findViewById(R.id.btnAddRobotInGame);
        btnAdd.setOnClickListener(this);
        btnShowRobots = (Button) findViewById(R.id.btnShowRobots);
        btnShowRobots.setOnClickListener(this);
        btnShowGames = (Button) findViewById(R.id.btnGames);
        btnShowGames.setOnClickListener(this);

        if (getIntent().getSerializableExtra("scouterName") != null) {
            scouterName = (String) getIntent().getSerializableExtra("scouterName");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Intent intent = new Intent(this, AddRobotGameActivity.class);
            intent.putExtra("scouterName", scouterName);
            intent.putExtra("games", games);
            intent.putExtra("robots", robots);
            startActivity(intent);
        }
        if (view == btnShowRobots) {
            Intent intent = new Intent(this, RobotsActivity.class);
            intent.putExtra("robots", robots);
            showRobotsActivity.launch(intent);
        }
        if (view == btnShowGames) {
            Intent intent = new Intent(this, GamesActivity.class);
            intent.putExtra("games", games);
            gamesActivity.launch(intent);
        }
    }

    private final ActivityResultLauncher<Intent> gamesActivity =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            ArrayList<Game> returnedGames = (ArrayList<Game>) result.getData().getSerializableExtra("games");
                            if (returnedGames != null) {
                                this.games = returnedGames;
                                for (Game game : this.games) {
                                    int[] teamsInGame = {
                                            game.getBlue1Number(), game.getBlue2Number(), game.getBlue3Number(),
                                            game.getRed1Number(), game.getRed2Number(), game.getRed3Number()
                                    };
                                    for (int teamNum : teamsInGame) {
                                        boolean exists = false;
                                        for (Team robot : robots) {
                                            if (robot.getTeamNumber() == teamNum) {
                                                exists = true;
                                                break;
                                            }
                                        }
                                        if (!exists) {
                                            robots.add(new Team("", teamNum));
                                        }
                                    }
                                }
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });

    private final ActivityResultLauncher<Intent> showRobotsActivity =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            ArrayList<Team> robots =
                                    (ArrayList<Team>) result.getData().getSerializableExtra("robots");
                            if (robots != null) {
                                this.robots = robots;
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}