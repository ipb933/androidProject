package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddRobotGameActivity extends AppCompatActivity implements View.OnClickListener{
    String scouterName;

    EditText etGameNumber, etRobotScore;
    Button btnSend, btnBack;

    AutoCompleteTextView actvRobotNumber;
    ArrayList<Game> games = new ArrayList<>();
    ArrayList<Team> robots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_robot_in_game);
        etGameNumber = (EditText) findViewById(R.id.etGameNumber);
        actvRobotNumber = (AutoCompleteTextView) findViewById(R.id.actvRobotNumber);
        etRobotScore = (EditText) findViewById(R.id.etRobotScore);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        if (getIntent().getSerializableExtra("scouterName") != null) {
            scouterName = (String) getIntent().getSerializableExtra("scouterName");
        }

        if (getIntent().getSerializableExtra("games") != null) {
            games = (ArrayList<Game>) getIntent().getSerializableExtra("games");
        }

        if (getIntent().getSerializableExtra("robots") != null) {
            robots = (ArrayList<Team>) getIntent().getSerializableExtra("robots");
        }

        etGameNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateRobotSuggestions(s.toString());
            }
        });

        updateRobotSuggestions("");

        actvRobotNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) actvRobotNumber.showDropDown();
        });
        actvRobotNumber.setOnClickListener(v -> actvRobotNumber.showDropDown());
    }

    private void updateRobotSuggestions(String gameNumStr) {
        ArrayList<String> suggestions = new ArrayList<>();
        boolean gameFound = false;

        if (!gameNumStr.isEmpty()) {
            try {
                int gameNum = Integer.parseInt(gameNumStr);
                for (Game g : games) {
                    if (g.getGameNumber() == gameNum) {
                        gameFound = true;
                        suggestions.add("Blue 1: " + g.getBlue1Number());
                        suggestions.add("Blue 2: " + g.getBlue2Number());
                        suggestions.add("Blue 3: " + g.getBlue3Number());
                        suggestions.add("Red 1: " + g.getRed1Number());
                        suggestions.add("Red 2: " + g.getRed2Number());
                        suggestions.add("Red 3: " + g.getRed3Number());
                        break;
                    }
                }
            } catch (NumberFormatException ignored) {

            }
        }

        if (!gameFound) {
            for (Team r : robots) {
                suggestions.add(String.valueOf(r.getTeamNumber()));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, suggestions);
        actvRobotNumber.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend) {
            String inputText = actvRobotNumber.getText().toString().trim();
            if (inputText.isEmpty() || etGameNumber.getText().toString().isEmpty() || etRobotScore.getText().toString().isEmpty()) {
                android.widget.Toast.makeText(this, "Please fill all fields", android.widget.Toast.LENGTH_SHORT).show();
                return;
            }

            int gameNumber = Integer.parseInt(etGameNumber.getText().toString());
            int robotScore = Integer.parseInt(etRobotScore.getText().toString());
            int robotNumber;

            if (inputText.contains(":")) {
                robotNumber = Integer.parseInt(inputText.split(":")[1].trim());
            } else {
                robotNumber = Integer.parseInt(inputText);
            }

            RobotAtGame robotGame = new RobotAtGame(gameNumber, robotNumber, robotScore, scouterName);
            Intent resultIntent = new Intent();

            for (Team team : robots) {
                if (team.teamNumber == robotNumber) {
                    team.addTeamGame(robotGame);
                }
            }

            resultIntent.putExtra("robotGame", robotGame);
            finish();
        }

        if (view == btnBack){
            finish();
        }
    }
}