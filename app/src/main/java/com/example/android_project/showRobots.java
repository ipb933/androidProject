package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class showRobots extends AppCompatActivity implements View.OnClickListener{
    Button btnBack;
    ListView lvRobots;

    ArrayList<Robot> robots = new ArrayList<>();
    RobotAdapter robotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_robots);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        lvRobots  = (ListView) findViewById(R.id.lvRobots);
        Intent intent = getIntent();
        robots = (ArrayList<Robot>) intent.getSerializableExtra("robots");
        robotAdapter = new RobotAdapter(this, 0, 0, robots);
        lvRobots.setAdapter(robotAdapter);
        registerForContextMenu(lvRobots);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            finish();
        }
    }
}