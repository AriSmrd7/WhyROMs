package com.arismrd.whyroms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arismrd.whyroms.model.ModelDevices;
import com.arismrd.whyroms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {

    private Context mContext;
    private ArrayList<ModelDevices> mPhoneList;

    public DevicesAdapter(Context context, ArrayList<ModelDevices> phoneList){
        mContext = context;
        mPhoneList = phoneList;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_devices, parent, false);
        return new DeviceViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        ModelDevices currentItem = mPhoneList.get(position);
        String tName = currentItem.getmNamaDevices();
        String tImg = currentItem.getmImageDevices();
        String tCode = currentItem.getmKodeDevices();

        holder.mName.setText(tName);
        holder.mCode.setText(tCode);
        Picasso.with(mContext).load(tImg).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mPhoneList.size();
    }

    public static class  DeviceViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImg;
        public TextView mName;
        public TextView mCode;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.txtName);
            mCode = itemView.findViewById(R.id.txtCode);
            mImg = itemView.findViewById(R.id.imgPhone);

        }
    }

}