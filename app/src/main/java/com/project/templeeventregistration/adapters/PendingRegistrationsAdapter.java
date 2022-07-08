package com.project.templeeventregistration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.templeeventregistration.R;
import com.project.templeeventregistration.models.PoojaRegistrationAdminItem;

import java.util.ArrayList;

public class PendingRegistrationsAdapter extends RecyclerView.Adapter<PendingRegistrationsAdapter.PendingItemViewHolder>{
    ArrayList<PoojaRegistrationAdminItem> registrationItems;
    Context context;

    public PendingRegistrationsAdapter(ArrayList<PoojaRegistrationAdminItem> registrationItems, Context context) {
        this.registrationItems = registrationItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PendingItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_admin_pending_registration, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingItemViewHolder holder, int position) {
        PoojaRegistrationAdminItem registrationItem = registrationItems.get(position);
        holder.poojaName.setText(registrationItem.getPoojaName());
        holder.poojaDate.setText(registrationItem.getPoojaDate());
        holder.poojaPrice.setText(registrationItem.getPoojaDate());
        holder.userName.setText(registrationItem.getUserName());
        holder.userPhone.setText(registrationItem.getUserPhone());
        holder.userEmail.setText(registrationItem.getUserEmail());
        holder.paymentId.setText(registrationItem.getPaymentId());
        holder.approve.setOnClickListener(v -> {
            Toast.makeText(context,"Approved: " + registrationItem.getPaymentId(), Toast.LENGTH_LONG).show();
        });

        holder.reject.setOnClickListener(v -> {
            Toast.makeText(context,"Rejected" + registrationItem.getPaymentId(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return registrationItems.size();
    }

    class PendingItemViewHolder extends RecyclerView.ViewHolder{
        TextView poojaName, poojaDate, poojaPrice;
        TextView userName, userPhone, userEmail;
        TextView paymentId;
        Button approve, reject;

        public PendingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            poojaName = itemView.findViewById(R.id.ipr_pooja_name);
            poojaDate = itemView.findViewById(R.id.ipr_pooja_date);
            poojaPrice = itemView.findViewById(R.id.ipr_pooja_price);
            userName = itemView.findViewById(R.id.ipr_user_name);
            userPhone = itemView.findViewById(R.id.ipr_user_phone);
            userEmail = itemView.findViewById(R.id.ipr_user_email);
            paymentId = itemView.findViewById(R.id.ipr_payment_id);
            approve = itemView.findViewById(R.id.ipr_approve_button);
            reject = itemView.findViewById(R.id.ipr_reject_button);
        }
    }
}
