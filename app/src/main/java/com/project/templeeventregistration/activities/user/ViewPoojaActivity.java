package com.project.templeeventregistration.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.templeeventregistration.R;
import com.project.templeeventregistration.databinding.ActivityViewPoojaBinding;

public class ViewPoojaActivity extends AppCompatActivity {
    ActivityViewPoojaBinding viewPoojaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPoojaBinding = ActivityViewPoojaBinding.inflate(getLayoutInflater());
        setContentView(viewPoojaBinding.getRoot());


    }
}