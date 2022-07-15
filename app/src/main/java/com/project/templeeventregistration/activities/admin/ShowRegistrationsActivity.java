package com.project.templeeventregistration.activities.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.templeeventregistration.R;
import com.project.templeeventregistration.adapters.AdminRegistrationAdapter;
import com.project.templeeventregistration.databinding.ActivityShowRegistrationsBinding;
import com.project.templeeventregistration.models.PoojaRegistrationAdminItem;

import java.util.ArrayList;
import java.util.List;

public class ShowRegistrationsActivity extends AppCompatActivity {
    ActivityShowRegistrationsBinding registrationsBinding;
    FirebaseFirestore firestore;
    private static final String TAG = "Show Registrations";
    ArrayList<PoojaRegistrationAdminItem> regList;
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

        CollectionReference reference = firestore.collection("Registrations");
        reference.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {

                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();

                for (DocumentSnapshot doc : documents) {

                    PoojaRegistrationAdminItem poojaItem = doc.toObject(PoojaRegistrationAdminItem.class);
                    poojaItem.setPaymentId(doc.getId());
                    Log.d(TAG, "Fetched Pooja Registration: " + poojaItem);
                    regList.add(poojaItem);
                }
                registrationAdapter.notifyDataSetChanged();
                String countText = "Total count: " + registrationsBinding.registrationsListView.getCount();
                registrationsBinding.count.setText(countText);
            }
        });
    }

    private void searchData(String s) {
        firestore.collection("Registrations").whereEqualTo("name", s)
                .get()
                .addOnCompleteListener(task -> {
                    regList.clear();
                    for (DocumentSnapshot doc : task.getResult()) {
                        PoojaRegistrationAdminItem model = new PoojaRegistrationAdminItem(
                                doc.getString("userId"),
                                doc.getString("paymentId"),
                                doc.getString("poojaName"),
                                (String) doc.get("poojaDate"),
                                doc.getString("poojaPrice"),
                                doc.getString("userName"),
                                doc.getString("userPhone"),
                                doc.getString("userEmail"),
                                doc.getString("regDate")
                        );
                        regList.add(model);
                    }
                    registrationsBinding.registrationsListView.setAdapter(registrationAdapter);
                    String countText = "Total count: " + registrationsBinding.registrationsListView.getCount();
                    registrationsBinding.count.setText(countText);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}