package com.project.templeeventregistration.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.templeeventregistration.admin.AdminActivity;
import com.project.templeeventregistration.databinding.ActivityMainBinding;
import com.project.templeeventregistration.user.UserActivity;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";
    ActivityMainBinding mainBinding;
    boolean valid = true;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        mainBinding.loginButton.setOnClickListener(v -> {
            checkField(mainBinding.loginEmail);
            checkField(mainBinding.loginPassword);
            if (valid) {
                String email = mainBinding.loginEmail.getText().toString();
                String password = mainBinding.loginPassword.getText().toString();
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                    Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    checkUserAccessLevel(authResult.getUser().getUid());
                }).addOnFailureListener(e -> {
                    Log.d(TAG, e.getMessage());
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

        });

        mainBinding.createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference document = fireStore.collection("Users").document(uid);
        // Extract data from document
        document.get().addOnSuccessListener(documentSnapshot -> {
            Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
            //identify user access level
            if (documentSnapshot.getString("isAdmin") != null) {
                // User is Admin
                Intent adminIntent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(adminIntent);
                finish();
            }
            if (documentSnapshot.getString("isUser") != null) {
                // User is Non-Admin
                Intent userIntent = new Intent(getApplicationContext(), UserActivity.class);
                String username = documentSnapshot.getString("FullName");
                userIntent.putExtra("usernameINTENT", username);
                startActivity(userIntent);
                finish();
            }
        });
    }

    private void checkField(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email and Password can't be empty!", Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            valid = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent userIntent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(userIntent);
            finish();
        }
    }
}