package com.project.templeeventregistration.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project.templeeventregistration.R;
import com.project.templeeventregistration.models.PoojaItem;
import com.project.templeeventregistration.models.PoojaRegistrationAdminItem;
import com.project.templeeventregistration.models.PoojaRegistrationUserItem;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class UserPoojaListAdapter extends RecyclerView.Adapter<UserPoojaListAdapter.PoojaItemViewHolder> {

    Context context;
    ArrayList<PoojaItem> poojaList;
    private static final String TAG = "POOJA LIST";

    public UserPoojaListAdapter(Context context, ArrayList<PoojaItem> poojaList) {
        this.context = context;
        this.poojaList = poojaList;
    }

    @NonNull
    @Override
    public PoojaItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_user_pooja, parent, false);
        return new PoojaItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PoojaItemViewHolder holder, int position) {
        PoojaItem poojaItem = poojaList.get(position);
        holder.name.setText(poojaItem.getName());
        holder.price.setText(poojaItem.getPrice());
        holder.date.setText(poojaItem.getDate());
        holder.desc.setText(poojaItem.getDesc());
        holder.register.setOnClickListener(v -> {
            register(poojaItem);
        });
    }

    @Override
    public int getItemCount() {
        return poojaList.size();
    }

    public static class PoojaItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date, desc;
        Button register;

        public PoojaItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pooja_register_name);
            price = itemView.findViewById(R.id.pooja_register_price);
            date = itemView.findViewById(R.id.pooja_register_date);
            desc = itemView.findViewById(R.id.pooja_register_desc);
            register = itemView.findViewById(R.id.pooja_register_button);
        }
    }

    private void register(PoojaItem poojaItem) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String pName = poojaItem.getName();
        String pPrice = poojaItem.getPrice();
        String pDate = poojaItem.getDate();
        String paymentId = String.valueOf(poojaList.size());
        String userId = FirebaseAuth.getInstance().getUid();

        DocumentReference userReference = firestore.collection("Users").document(userId);

        // Set Registration details for user
        PoojaRegistrationUserItem poojaRegistrationUserItem = new PoojaRegistrationUserItem(pName, pDate, pPrice, paymentId);
        userReference.collection("Registrations").document(paymentId).set(poojaRegistrationUserItem);

        userReference.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot snapshot = task.getResult();
                if(snapshot.exists()){
                    String userName = String.valueOf(snapshot.getString("FullName"));
                    String userPhone = String.valueOf(snapshot.get("PhoneNumber"));
                    PoojaRegistrationAdminItem poojaRegistrationAdminItem = new PoojaRegistrationAdminItem(paymentId, pName, pDate, pPrice, userName, userPhone);
                    DocumentReference registrationsReference = firestore.collection("Registrations").document(paymentId);
                    registrationsReference.set(poojaRegistrationAdminItem);
                    Toast.makeText(context, "Pooja Item Registered:" + poojaItem, Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d(TAG, "Snapshot doesn't exists");
                    Toast.makeText(context, "Pooja Item Registration failed for admin!", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d(TAG, "Task is not successful");
                Toast.makeText(context, "Pooja Item Registration failed for admin!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

