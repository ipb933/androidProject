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

public class AddGame extends AppCompatActivity implements View.OnClickListener{
    EditText etGameNumber, etBlue1, etBlue2, etBlue3, etRed1, etRed2, etRed3;
    Button btnSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        etGameNumber = (EditText) findViewById(R.id.etGameNumber);
        etBlue1 = (EditText) findViewById(R.id.etBlue1);
        etBlue2 = (EditText) findViewById(R.id.etBlue2);
        etBlue3 = (EditText) findViewById(R.id.etBlue3);
        etRed1 = (EditText) findViewById(R.id.etRed1);
        etRed2 = (EditText) findViewById(R.id.etRed2);
        etRed3 = (EditText) findViewById(R.id.etRed3);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend) {
            int gameNumber = Integer.parseInt(etGameNumber.getText().toString());
            int blue1Number = Integer.parseInt(etBlue1.getText().toString());
            int blue2Number = Integer.parseInt(etBlue2.getText().toString());
            int blue3Number = Integer.parseInt(etBlue3.getText().toString());
            int Red1Number = Integer.parseInt(etRed1.getText().toString());
            int Red2Number = Integer.parseInt(etRed2.getText().toString());
            int Red3Number = Integer.parseInt(etRed3.getText().toString());
            Game game = new Game(gameNumber, blue1Number, blue2Number, blue3Number, Red1Number, Red2Number, Red3Number);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("game", game);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        if (view == btnBack){
            finish();
        }
    }
}