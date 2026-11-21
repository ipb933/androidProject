package com.example.android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RobotsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack, btnAddRobot;
    RecyclerView rvRobots;
    ArrayList<Robot> robots = new ArrayList<>();
    ArrayList<RobotAtGame> robotsAtGame = new ArrayList<>();
    RobotAdapter robotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_robots);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnAddRobot = findViewById(R.id.btnAddRobot);
        btnAddRobot.setOnClickListener(this);
        rvRobots = findViewById(R.id.rvRobots);
        rvRobots.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getSerializableExtra("robots") != null) {
            robots = (ArrayList<Robot>) getIntent().getSerializableExtra("robots");
        }

        if (getIntent().getSerializableExtra("robotsAtGame") != null) {
            robotsAtGame = (ArrayList<RobotAtGame>) getIntent().getSerializableExtra("robotsAtGame");
        }

        robotAdapter = new RobotAdapter(robots, robotsAtGame, robotDetailsLauncher);
        rvRobots.setAdapter(robotAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("robots", robots);
            resultIntent.putExtra("robotsAtGame", robotsAtGame);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        if (view == btnAddRobot) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Enter Team Number and Name");
            final EditText numberInput = new EditText(view.getContext());
            numberInput.setHint("team number");
            final EditText nameInput = new EditText(view.getContext());
            nameInput.setHint("team name");
            final LinearLayout linearLayout = new LinearLayout(view.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(nameInput);
            linearLayout.addView(numberInput);
            builder.setView(linearLayout);
            builder.setPositiveButton("Save", (dialog, which) -> {
                String name = nameInput.getText().toString().trim();
                String numStr = numberInput.getText().toString().trim();
                if (numStr.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter team number", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int teamNum = Integer.parseInt(numStr);
                    robots.add(new Robot(name, teamNum));
                    robotAdapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    Toast.makeText(view.getContext(), "Invalid team number", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        }
    }

    private final ActivityResultLauncher<Intent> robotDetailsLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            ArrayList<RobotAtGame> updatedList =
                                    (ArrayList<RobotAtGame>) result.getData().getSerializableExtra("robotsAtGame");
                            Robot updatedRobot =
                                    (Robot) result.getData().getSerializableExtra("robot");
                            int targetRobotNumber = result.getData().getIntExtra("robotNumber", -1);

                            if (updatedRobot != null) {
                                if (targetRobotNumber == -1) {
                                    targetRobotNumber = updatedRobot.getTeamNumber();
                                }
                                for (int i = 0; i < robots.size(); i++) {
                                    if (robots.get(i).getTeamNumber() == updatedRobot.getTeamNumber()) {
                                        robots.set(i, updatedRobot);
                                        break;
                                    }
                                }
                            }

                            if (updatedList != null && targetRobotNumber != -1) {
                                for (int i = robotsAtGame.size() - 1; i >= 0; i--) {
                                    if (robotsAtGame.get(i).getRobotNumber() == targetRobotNumber) {
                                        robotsAtGame.remove(i);
                                    }
                                }
                                robotsAtGame.addAll(updatedList);
                                if (!updatedList.isEmpty()) {
                                    int lastScore = updatedList.get(updatedList.size() - 1).getRobotScore();
                                    Toast.makeText(this, "Last score: " + lastScore, Toast.LENGTH_SHORT).show();
                                }
                            }

                            robotAdapter.notifyDataSetChanged();
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}