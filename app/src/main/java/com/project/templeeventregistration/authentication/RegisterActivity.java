package com.project.templeeventregistration.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.templeeventregistration.R;
import com.project.templeeventregistration.admin.AdminActivity;
import com.project.templeeventregistration.databinding.ActivityRegisterBinding;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding registerBinding;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    boolean valid = true;
    private static final String TAG = "REGISTER ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        registerBinding.registerButton.setOnClickListener(v -> {
            checkField(registerBinding.registerName);
            checkField(registerBinding.registerEmail);
            checkField(registerBinding.registerPhone);
            checkField(registerBinding.registerPassword);

            if(valid){
                String name = registerBinding.registerName.getText().toString();
                String email = registerBinding.registerEmail.getText().toString();
                String password = registerBinding.registerPassword.getText().toString();
                String phone = registerBinding.registerPhone.getText().toString();

                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                    FirebaseUser user = auth.getCurrentUser();
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    DocumentReference document = fireStore.collection("Users").document(user.getUid());
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("FullName", name);
                    userInfo.put("UserEmail", email);
                    userInfo.put("PhoneNumber", phone);
                    userInfo.put("isUser", "1");
                    document.set(userInfo);
                    document.collection("Registrations");
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    finish();

                }).addOnFailureListener(e -> {
                    Log.d(TAG, e.getMessage());
                    Toast.makeText(this, "Failed To Create Account", Toast.LENGTH_SHORT).show();
                });
            }
        });

        registerBinding.goToLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
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
}