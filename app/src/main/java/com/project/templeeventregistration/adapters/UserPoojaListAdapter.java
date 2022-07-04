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
import com.project.templeeventregistration.models.PoojaItem;

import java.util.ArrayList;

public class UserPoojaListAdapter extends RecyclerView.Adapter<UserPoojaListAdapter.PoojaItemViewHolder> {

    Context context;
    ArrayList<PoojaItem> poojaList;

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

    private void register(PoojaItem poojaItem) {
        Toast.makeText(context, "Pooja Item Registered:" + poojaItem, Toast.LENGTH_LONG).show();
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

}

