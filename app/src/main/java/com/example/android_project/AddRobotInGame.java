package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddRobotInGame extends AppCompatActivity implements View.OnClickListener{
    EditText etGameNumber, etRobotNumber, etRobotScore;
    Button btnSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_robot_in_game);
        etGameNumber = (EditText) findViewById(R.id.etGameNumber);
        etRobotNumber = (EditText) findViewById(R.id.etRobotNumber);
        etRobotScore = (EditText) findViewById(R.id.etRobotScore);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend) {
            int gameNumber = Integer.parseInt(etGameNumber.getText().toString());
            int robotNumber = Integer.parseInt(etRobotNumber.getText().toString());
            int RobotScore = Integer.parseInt(etRobotScore.getText().toString());
            RobotAtGame robotAtGame = new RobotAtGame(gameNumber,robotNumber,RobotScore);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("robotAtGame", robotAtGame);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        if (view == btnBack){
            finish();
        }
    }
}