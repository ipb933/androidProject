package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText etScouterName, etPassword;
    Button btnLogIn,btnQuit;

    private final HashMap<String, String> accounts = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etScouterName = findViewById(R.id.etScouterName);
        etPassword = findViewById(R.id.etPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(this);
    }

    private String findExistingKey(String name) {
        for (String key : accounts.keySet()) {
            if (key.equalsIgnoreCase(name)) {
                return key;
            }
        }
        return null;
    }

    private void loginOrCreateAccount(String scouterName, String password) {
        String existingKey = findExistingKey(scouterName);
        if (existingKey == null) {
            accounts.put(scouterName, password);
            Toast.makeText(this, "Account created for " + scouterName, Toast.LENGTH_SHORT).show();
        } else {
            String savedPassword = accounts.get(existingKey);
            if (savedPassword == null || !savedPassword.equals(password)) {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Welcome back, " + existingKey, Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("scouterName", scouterName);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn) {
            String name = etScouterName.getText().toString().trim();
            String password = etPassword.getText().toString();
            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter name and password", Toast.LENGTH_SHORT).show();
                return;
            }
            loginOrCreateAccount(name, password);
        }
        if (view == btnQuit) {
            finish();
        }
    }
}