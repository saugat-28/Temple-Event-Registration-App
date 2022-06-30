package com.project.templeeventregistration.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.templeeventregistration.R;
import com.project.templeeventregistration.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding adminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(adminBinding.getRoot());

        adminBinding.addPoojaButton.setOnClickListener(v -> {

        });

        adminBinding.poojaListButton.setOnClickListener(v -> {

        });

        adminBinding.viewRegistrationButton.setOnClickListener(v -> {

        });

        adminBinding.logoutButton.setOnClickListener(v -> {

        });
    }
}