package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowGames extends AppCompatActivity implements View.OnClickListener{
    Button btnBack;
    ListView lvGames;
    RobotAtGame lastSelected;
    int pos;
    ArrayList<RobotAtGame> robotsAtGame = new ArrayList<>();
    RobotsAtGameAdapter robotsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_games);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        lvGames  = (ListView) findViewById(R.id.lvGames);
        Intent intent = getIntent();
        robotsAtGame = (ArrayList<RobotAtGame>) intent.getSerializableExtra("games");
        robotsAdapter = new RobotsAtGameAdapter(this, 0, 0, robotsAtGame);
        lvGames.setAdapter(robotsAdapter);
//        lvGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                lastSelected = robotsAdapter.getItem(position);
////                Intent intent = new Intent(this, .class);
//                intent.putExtra("cat", lastSelected);
//                startActivity(intent);
//            }
//        });
        registerForContextMenu(lvGames);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            finish();
        }
    }
}