package com.project.templeeventregistration.activities.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.templeeventregistration.adapters.PoojaListAdapter;
import com.project.templeeventregistration.databinding.ActivityShowPoojaListBinding;
import com.project.templeeventregistration.models.PoojaItem;

import java.util.ArrayList;

public class ShowPoojaListActivity extends AppCompatActivity {
    ActivityShowPoojaListBinding showPoojaListBinding;
    DatabaseReference poojaListRef;
    private PoojaListAdapter poojaListAdapter;
    private ArrayList<PoojaItem> poojaItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPoojaListBinding = ActivityShowPoojaListBinding.inflate(getLayoutInflater());
        setContentView(showPoojaListBinding.getRoot());

        poojaListRef = FirebaseDatabase.getInstance().getReference().child("PoojaList");
        poojaItemsList = new ArrayList<>();

        poojaListAdapter = new PoojaListAdapter(this, poojaItemsList);
        showPoojaListBinding.poojaListRv.setLayoutManager(new LinearLayoutManager(this));
        showPoojaListBinding.poojaListRv.setAdapter(poojaListAdapter);

        poojaListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                poojaItemsList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PoojaItem poojaItem = dataSnapshot.getValue(PoojaItem.class);
                    poojaItemsList.add(poojaItem);
                }
                poojaListAdapter.setmList(poojaItemsList);
                poojaListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        showPoojaListBinding.homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(this, AdminActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }

}