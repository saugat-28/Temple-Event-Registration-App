package com.project.templeeventregistration.activities.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project.templeeventregistration.R;
import com.project.templeeventregistration.adapters.UserPoojaListAdapter;
import com.project.templeeventregistration.databinding.ActivityViewPoojaBinding;
import com.project.templeeventregistration.models.PoojaItem;

import java.util.ArrayList;

public class ViewPoojaActivity extends AppCompatActivity {
    ActivityViewPoojaBinding viewPoojaBinding;
    DatabaseReference poojaRef;
    UserPoojaListAdapter poojaListAdapter;
    ArrayList<PoojaItem> poojaItemList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String userId, name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPoojaBinding = ActivityViewPoojaBinding.inflate(getLayoutInflater());
        setContentView(viewPoojaBinding.getRoot());

        viewPoojaBinding.homeButton.setOnClickListener(v -> {
            startActivity(new Intent(this, UserActivity.class));
            finish();
        });

        poojaRef =  FirebaseDatabase.getInstance().getReference().child("PoojaList");
        viewPoojaBinding.userPoojaListRv.setLayoutManager(new LinearLayoutManager(this));
        poojaItemList = new ArrayList<>();
        poojaListAdapter = new UserPoojaListAdapter(this, poojaItemList);
        viewPoojaBinding.userPoojaListRv.setAdapter(poojaListAdapter);

        poojaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PoojaItem poojaItem = dataSnapshot.getValue(PoojaItem.class);
                    poojaItemList.add(poojaItem);
                }

                poojaListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = auth.getCurrentUser().getUid();
        DocumentReference document = firestore.collection("Users").document(userId);
        document.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name = value.getString("FullName");
                email = value.getString("UserEmail");
                phone = value.getString("PhoneNumber");
                //reg_name.setText(rname);
                //email.setText(remail);
                //phone.setText(rphone);
                //Intent intent = new Intent(ShowActivityUser.this, PoojaRegister.class);

            }
        });

    }
}