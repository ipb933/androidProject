package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText et1;
    Button btnLogIn,btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        et1 = (EditText) findViewById(R.id.etName);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn) {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("name", et1.getText());
            startActivity(intent);
        }
        if (view == btnQuit) {
            finish();
        }
    }
}