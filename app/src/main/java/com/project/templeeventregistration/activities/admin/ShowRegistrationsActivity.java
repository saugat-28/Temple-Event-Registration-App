package com.project.templeeventregistration.activities.admin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.templeeventregistration.R;
import com.project.templeeventregistration.adapters.AdminRegistrationAdapter;
import com.project.templeeventregistration.databinding.ActivityShowRegistrationsBinding;
import com.project.templeeventregistration.models.PoojaRegistrationItem;

import java.util.ArrayList;
import java.util.List;

public class ShowRegistrationsActivity extends AppCompatActivity {
    ActivityShowRegistrationsBinding registrationsBinding;
    FirebaseFirestore firestore;
    private static final String TAG = "FirestoreSearchActivity";
    ArrayList<PoojaRegistrationItem> regList;
    AdminRegistrationAdapter registrationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationsBinding = ActivityShowRegistrationsBinding.inflate(getLayoutInflater());
        setContentView(registrationsBinding.getRoot());

        firestore = FirebaseFirestore.getInstance();
        regList = new ArrayList<>();
        registrationAdapter = new AdminRegistrationAdapter(this, R.layout.item_admin_registration, regList);

        registrationsBinding.registrationsListView.setAdapter(registrationAdapter);
        CollectionReference reference = firestore.collection("AllRegistration");
        reference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot doc : documents) {

                        PoojaRegistrationItem poojaItem = doc.toObject(PoojaRegistrationItem.class);
                        poojaItem.setId(doc.getId());
                        regList.add(poojaItem);
                    }
                    registrationAdapter.notifyDataSetChanged();
                    String countText = "Total count: " + registrationsBinding.registrationsListView.getCount();
                    registrationsBinding.count.setText(countText);
                }
            }
        });
    }

    private void searchData(String s) {
        firestore.collection("AllRegistrations").whereEqualTo("name", s)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        regList.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            PoojaRegistrationItem model = new PoojaRegistrationItem(
                                    doc.getString("name"),
                                    (String) doc.get("date"),
                                    doc.getString("userName"),
                                    doc.getString("userPhone"));
                            regList.add(model);
                        }
                        registrationsBinding.registrationsListView.setAdapter(registrationAdapter);
                        String countText = "Total count: " + registrationsBinding.registrationsListView.getCount();
                        registrationsBinding.count.setText(countText);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                searchData(text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}