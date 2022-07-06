package com.project.templeeventregistration.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.templeeventregistration.R;
import com.project.templeeventregistration.models.PoojaRegistrationAdminItem;

import java.util.List;

public class AdminRegistrationAdapter extends ArrayAdapter<PoojaRegistrationAdminItem> {
    Context mContext;
    int mResource;

    public AdminRegistrationAdapter(@NonNull Context context, int resource, @NonNull List<PoojaRegistrationAdminItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String poojaName = getItem(position).getPoojaName();
        String poojaDate = getItem(position).getPoojaDate();
        String personName = getItem(position).getUserName();
        String personPhone = getItem(position).getUserPhone();

        PoojaRegistrationAdminItem modelAllReg = new PoojaRegistrationAdminItem(poojaName, poojaDate, personName, personPhone);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView listPoojaName = (TextView) convertView.findViewById(R.id.iar_pooja_name);
        TextView listPoojaDate = (TextView) convertView.findViewById(R.id.iar_pooja_date);
        TextView listName = (TextView) convertView.findViewById(R.id.iar_user_name);
        TextView listPhone = (TextView) convertView.findViewById(R.id.iar_user_phone);

        listPoojaName.setText(poojaName);
        listPoojaDate.setText(poojaDate);
        listName.setText(personName);
        listPhone.setText(personPhone);

        return convertView;
    }
}
