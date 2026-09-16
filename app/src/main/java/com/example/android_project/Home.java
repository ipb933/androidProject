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

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button btnAdd, btnShowRobots, btnShowGames;
    ArrayList<Robot> robots = new ArrayList<>();
    ArrayList<RobotAtGame> robotsAtGame = new ArrayList<>();
    RobotsAtGameAdapter robotsAdapter;
    ArrayList<Game> games = new ArrayList<>();
    GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        robotsAdapter = new RobotsAtGameAdapter(this, 0, 0, robotsAtGame);
        gameAdapter = new GameAdapter(games);
        btnAdd = (Button) findViewById(R.id.btnAddRobotInGame);
        btnAdd.setOnClickListener(this);
        btnShowRobots = (Button) findViewById(R.id.btnShowRobots);
        btnShowRobots.setOnClickListener(this);
        btnShowGames = (Button) findViewById(R.id.btnGames);
        btnShowGames.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Intent intent = new Intent(this, AddRobotInGame.class);
            addRobot.launch(intent);
        }
        if (view == btnShowRobots) {
            Intent intent = new Intent(this, ShowRobots.class);
            intent.putExtra("robots", robots);
            startActivity(intent);
        }
        if (view == btnShowGames) {
            Intent intent = new Intent(this, Games.class);
            intent.putExtra("games", robotsAtGame);
            gamesActivity.launch(intent);
        }
    }

    private final ActivityResultLauncher<Intent> addRobot =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                RobotAtGame robotAtGame = (RobotAtGame) result.getData().getSerializableExtra("robotAtGame");
                                if (robotAtGame == null) {
                                    return;
                                }
                                robotsAtGame.add(robotAtGame);
                                robotsAdapter.notifyDataSetChanged();

                                for (Robot robot : robots) {
                                    if (robot.teamNumber == robotAtGame.robotNumber){
                                        robot.addScore(robotAtGame.robotScore);
                                        return;
                                    }
                                }
                                Robot newRobot = new Robot("", robotAtGame.robotNumber);
                                newRobot.addScore(robotAtGame.robotScore);
                                robots.add(newRobot);
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });

    private final ActivityResultLauncher<Intent> gamesActivity =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                ArrayList<Game> games = (ArrayList<Game>) result.getData().getSerializableExtra("games");
                                if (games == null) {
                                    return;
                                }
                                this.games = games;
                                gameAdapter.notifyDataSetChanged();
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}