package com.example.android_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RobotsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack, btnAddRobot;
    RecyclerView rvRobots;
    ArrayList<Team> robots = new ArrayList<>();
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
            robots = (ArrayList<Team>) getIntent().getSerializableExtra("robots");
        }

        robotAdapter = new RobotAdapter(robots, new ArrayList<>() ,robotDetailsLauncher);
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
            numberInput.setHint("team number");
            final EditText nameInput = new EditText(view.getContext());
            nameInput.setHint("team name");
            final LinearLayout linearLayout = new LinearLayout(view.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(nameInput);
            linearLayout.addView(numberInput);
            builder.setView(linearLayout);
            builder.setPositiveButton("Save", (dialog, which) -> {
                String name = nameInput.getText().toString().trim();
                String numStr = numberInput.getText().toString().trim();
                if (numStr.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter team number", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int teamNum = Integer.parseInt(numStr);
                    robots.add(new Team(name, teamNum));
                    robotAdapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    Toast.makeText(view.getContext(), "Invalid team number", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        }
    }

    private final ActivityResultLauncher<Intent> robotDetailsLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            ArrayList<Team> robots =
                                    (ArrayList<Team>) result.getData().getSerializableExtra("robots");
                            if (robots != null) {
                                this.robots = robots;
                            }

                            robotAdapter.notifyDataSetChanged();
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Toast.makeText(this, "Cancel by User", Toast.LENGTH_SHORT).show();
                        }
                    });
}