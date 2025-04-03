package com.example.lexilearn.activities;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lexilearn.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView profileRecyclerView;
    private ProfileAdapter profileAdapter;
    private List<ProfileItem> profileItemList;
    private TextView userName, xpPoints;
    private ImageView profileImage, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.userName);
        xpPoints = findViewById(R.id.xpPoints);
        profileImage = findViewById(R.id.profileImage);
        backButton = findViewById(R.id.backButton);

        // Sample User Data (can be fetched from Firebase)
        userName.setText("John Doe");
        xpPoints.setText("XP Points: 1200");

        // RecyclerView Setup
        profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        profileItemList = new ArrayList<>();
        profileItemList.add(new ProfileItem("Badges & Achievements", R.drawable.badge));
        profileItemList.add(new ProfileItem("Leaderboards", R.drawable.leaderboard));
        profileItemList.add(new ProfileItem("XP Points & Levels", R.drawable.coin));

        profileAdapter = new ProfileAdapter(this, profileItemList);
        profileRecyclerView.setAdapter(profileAdapter);

        // Back Button Click
        backButton.setOnClickListener(view -> finish());
    }
}
