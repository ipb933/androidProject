package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText etScouterName;
    Button btnLogIn,btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etScouterName = (EditText) findViewById(R.id.etScouterName);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn) {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("scouterName", etScouterName.getText());
            startActivity(intent);
        }
        if (view == btnQuit) {
            finish();
        }
    }
}