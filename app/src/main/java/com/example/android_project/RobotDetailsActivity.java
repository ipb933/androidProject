package com.example.android_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class RobotDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDetailsTitle;
    ImageView ivRobotImage;
    Button btnAddPhoto;
    RecyclerView rvRobotGames;
    Button btnBack;

    int currentRobotNumber;
    Team currentRobot;
    ArrayList<RobotAtGame> robotsAtGame;
    RobotAtGamesAdapter gamesAdapter;
    StorageReference storageReference;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    ivRobotImage.setImageURI(uri);
                    uploadImageToFirebase(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_details);

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        ivRobotImage = findViewById(R.id.ivRobotImage);
        btnAddPhoto = findViewById(R.id.btnAddPhoto);
        rvRobotGames = findViewById(R.id.rvRobotGames);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        storageReference = FirebaseStorage.getInstance().getReference("RobotImages");

        currentRobotNumber = getIntent().getIntExtra("robotNumber", 0);
        currentRobot = (Team) getIntent().getSerializableExtra("robot");
        robotsAtGame = (ArrayList<RobotAtGame>) getIntent().getSerializableExtra("robotsAtGame");
        if (robotsAtGame == null) {
            robotsAtGame = new ArrayList<>();
        }
        if (currentRobot == null) {
            currentRobot = new Team("", currentRobotNumber);
        }

        tvDetailsTitle.setText("Team " + currentRobotNumber + " History");
        loadRobotImage();

        btnAddPhoto.setOnClickListener(v -> mGetContent.launch("image/*"));

        rvRobotGames.setLayoutManager(new LinearLayoutManager(this));
        gamesAdapter = new RobotAtGamesAdapter(robotsAtGame, currentRobot);
        rvRobotGames.setAdapter(gamesAdapter);
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child(currentRobotNumber + ".jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                Toast.makeText(RobotDetailsActivity.this, "Image Uploaded!", Toast.LENGTH_SHORT).show()
        ).addOnFailureListener(e ->
                Toast.makeText(RobotDetailsActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show()
        );
    }

    private void loadRobotImage() {
        StorageReference fileRef = storageReference.child(currentRobotNumber + ".jpg");
        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(this).load(uri).into(ivRobotImage);
        });
    }

    private void onGameScoreEdited(RobotAtGame gameItem, int oldScore, int newScore) {
        gameItem.setRobotScore(newScore);
        if (currentRobot != null) {
            currentRobot.updateScore(oldScore, newScore);
        }
        if (gamesAdapter != null) {
            gamesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("robotsAtGame", robotsAtGame);
            resultIntent.putExtra("robot", currentRobot);
            resultIntent.putExtra("robotNumber", currentRobotNumber);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}