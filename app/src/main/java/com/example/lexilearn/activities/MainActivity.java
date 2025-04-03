package com.example.lexilearn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lexilearn.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<DashboardItem> itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample Data
        itemList = new ArrayList<>();
        itemList.add(new DashboardItem("Daily Challenges", R.drawable.challenge));
        itemList.add(new DashboardItem("Spelling Bee", R.drawable.spell_bee));
        itemList.add(new DashboardItem("Drag-and-Drop Words", R.drawable.drag));
        itemList.add(new DashboardItem("Tap the Right Word", R.drawable.tap));
        itemList.add(new DashboardItem("Word Association", R.drawable.wordgame));
        itemList.add(new DashboardItem("Drawing Board", R.drawable.drawing));

        adapter = new DashboardAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        // Profile Icon Click
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Logout Icon Click
        // Logout Icon Click
        ImageView logoutIcon = findViewById(R.id.logoutIcon);
        logoutIcon.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut(); // Sign out the user
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });


    }
}
