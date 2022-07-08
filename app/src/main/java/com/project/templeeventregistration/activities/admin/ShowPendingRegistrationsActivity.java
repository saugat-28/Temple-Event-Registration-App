package com.project.templeeventregistration.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.templeeventregistration.R;
import com.project.templeeventregistration.databinding.ActivityShowPendingRegistrationsBinding;
import com.project.templeeventregistration.databinding.ActivityShowRegistrationsBinding;

public class ShowPendingRegistrationsActivity extends AppCompatActivity {
    ActivityShowPendingRegistrationsBinding pendingRegistrationsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pendingRegistrationsBinding = ActivityShowPendingRegistrationsBinding.inflate(getLayoutInflater());
        setContentView(pendingRegistrationsBinding.getRoot());


    }
}