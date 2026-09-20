package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ShowRobots extends AppCompatActivity implements View.OnClickListener {
    Button btnBack;
    RecyclerView rvRobots;
    ArrayList<Robot> robots = new ArrayList<>();
    RobotAdapter robotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_robots);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        rvRobots = findViewById(R.id.rvRobots);
        rvRobots.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getSerializableExtra("robots") != null) {
            robots = (ArrayList<Robot>) getIntent().getSerializableExtra("robots");
        }

        robotAdapter = new RobotAdapter(robots);
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
    }
}