package com.project.templeeventregistration.admin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.templeeventregistration.databinding.ActivityShowPoojaListBinding;

public class ShowPoojaListActivity extends AppCompatActivity {
    ActivityShowPoojaListBinding showPoojaListBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPoojaListBinding = ActivityShowPoojaListBinding.inflate(getLayoutInflater());
        setContentView(showPoojaListBinding.getRoot());


    }
}