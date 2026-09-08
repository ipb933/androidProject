package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Add extends AppCompatActivity implements View.OnClickListener{
    EditText etGameNumber, etRobotNumber, etRobotScore;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etGameNumber = (EditText) findViewById(R.id.etGameNumber);
        etRobotNumber = (EditText) findViewById(R.id.etRobotNumber);
        etRobotScore = (EditText) findViewById(R.id.etRobotScore);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            int gameNumber = Integer.parseInt(etGameNumber.getText().toString());
            int robotNumber = Integer.parseInt(etGameNumber.getText().toString());
            int RobotScore = Integer.parseInt(etGameNumber.getText().toString());
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