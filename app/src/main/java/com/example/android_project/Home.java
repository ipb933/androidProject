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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        robotsAdapter = new RobotsAtGameAdapter(this, 0, 0, robotsAtGame);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnShowRobots = (Button) findViewById(R.id.btnShowRobots);
        btnShowRobots.setOnClickListener(this);
        btnShowGames = (Button) findViewById(R.id.btnShowGames);
        btnShowGames.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Intent intent = new Intent(this, Add.class);
            addRobot.launch(intent);
        }
        if (view == btnShowRobots) {
            Intent intent = new Intent(this, showRobots.class);
            intent.putExtra("robots", robots);
            startActivity(intent);
        }
        if (view == btnShowGames) {
            Intent intent = new Intent(this, ShowGames.class);
            intent.putExtra("games", robotsAtGame);
            startActivity(intent);
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
                                        robot.averageScore = (robot.averageScore * robot.gamesPlayed + robotAtGame.robotScore) / (robot.gamesPlayed + 1);
                                        robot.gamesPlayed ++;
                                        return;
                                    }
                                }
                                robots.add(new Robot("", robotAtGame.robotNumber, robotAtGame.robotScore));
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}