package com.example.android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ShowRobots extends AppCompatActivity implements View.OnClickListener {
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

        robotAdapter = new RobotAdapter(robots, robotsAtGame);
        rvRobots.setAdapter(robotAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("robots", robots);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        if (view == btnAddRobot) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Enter Team Number and Name");
            final EditText numberInput = new EditText(view.getContext());
            final EditText nameInput = new EditText(view.getContext());
            final LinearLayout linearLayout = new LinearLayout(view.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(numberInput);
            linearLayout.addView(nameInput);
            builder.setView(linearLayout);
            builder.setPositiveButton("Save", (dialog, which) -> {
                robots.add(new Robot(nameInput.getText().toString(), Integer.parseInt(numberInput.getText().toString())));
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        }
    }
}