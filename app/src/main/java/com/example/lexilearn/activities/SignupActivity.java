package com.example.lexilearn.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lexilearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText signupUsername, signupEmail, signupPhone, signupPassword;
    private Button signupButton;
    private TextView loginText;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference; // Realtime Database Reference
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth & Database Reference
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize Views
        signupUsername = findViewById(R.id.signupUsername);
        signupEmail = findViewById(R.id.signupEmail);
        signupPhone = findViewById(R.id.signupPhone);
        signupPassword = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.signupButton);
        loginText = findViewById(R.id.loginText);

        // Initialize ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating Account...");
        progressDialog.setCancelable(false);

        // Signup Button Click
        signupButton.setOnClickListener(view -> registerUser());

        // Redirect to Login Page
        loginText.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String username = signupUsername.getText().toString().trim();
        String email = signupEmail.getText().toString().trim();
        String phone = signupPhone.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(username)) {
            signupUsername.setError("Enter Username");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            signupEmail.setError("Enter Email");
            return;
        }
        if (TextUtils.isEmpty(phone) || phone.length() < 10) {
            signupPhone.setError("Enter Valid Phone Number");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            signupPassword.setError("Password must be at least 6 characters");
            return;
        }

        // Show ProgressDialog
        progressDialog.show();

        // Firebase Signup
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Get Registered User ID
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();

                            // Store Additional Data in Firebase Database
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("username", username);
                            userData.put("email", email);
                            userData.put("phone", phone);
                            userData.put("userId", userId);

                            databaseReference.child(userId)
                                    .setValue(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignupActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignupActivity.this, "Error Saving Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(SignupActivity.this, "Signup Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

