package com.project.templeeventregistration.activities.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.templeeventregistration.adapters.UserRegistrationAdapter;
import com.project.templeeventregistration.databinding.ActivityViewRegistrationsBinding;
import com.project.templeeventregistration.models.PoojaRegistration;

import java.util.ArrayList;
import java.util.List;

public class ViewRegistrationsActivity extends AppCompatActivity {
    ActivityViewRegistrationsBinding registrationsBinding;
    FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private UserRegistrationAdapter adapter;
    private List<PoojaRegistration> regList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationsBinding = ActivityViewRegistrationsBinding.inflate(getLayoutInflater());
        setContentView(registrationsBinding.getRoot());

        registrationsBinding.myRegistrationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        regList = new ArrayList<>();
        adapter = new UserRegistrationAdapter(this, regList);

        auth = FirebaseAuth.getInstance();
        registrationsBinding.myRegistrationsRecyclerView.setAdapter(adapter);
        String userId = auth.getCurrentUser().getUid();
        firestore = FirebaseFirestore.getInstance();
        DocumentReference reference = firestore.collection("Users").document(userId);

        reference.collection("Registrations").orderBy("date").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot snapshot : list) {

                            PoojaRegistration p = snapshot.toObject(PoojaRegistration.class);
                            p.setId(snapshot.getId());
                            regList.add(p);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}