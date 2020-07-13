package com.arismrd.whyroms.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arismrd.whyroms.model.ModelMagisk;
import com.arismrd.whyroms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class MagiskAdapter extends RecyclerView.Adapter<MagiskAdapter.MagiskViewHolder> {

    Context mContext;
    private ArrayList<ModelMagisk> mMagiskList;

    public MagiskAdapter(Context context, ArrayList<ModelMagisk> magiskList){
        this.mContext = context;
        this.mMagiskList = magiskList;
    }

    @NonNull
    @Override
    public MagiskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_magisk, parent, false);
        return new MagiskViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MagiskViewHolder holder, int position) {
        ModelMagisk currentItem = mMagiskList.get(position);

        String tVersi  = currentItem.getmVersi();
        String tStatus = currentItem.getmStatus();
        String tImage  = currentItem.getmImage();

        holder.mLink = currentItem.getmLink();
        holder.mVersi.setText(tVersi);
        holder.mStatus.setText(tStatus);
        Picasso.with(mContext).load(tImage).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mMagiskList.size();
    }

    public static class  MagiskViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImage;
        public TextView mVersi;
        public TextView mStatus;
        public String mLink;

        public MagiskViewHolder(@NonNull View itemView) {
            super(itemView);

            mVersi = itemView.findViewById(R.id.txtMagiskVersion);
            mStatus = itemView.findViewById(R.id.txtMagiskStatus);
            mImage = itemView.findViewById(R.id.imgMagisk);

            itemView.setOnClickListener(v -> {
              Intent GoUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));
              itemView.getContext().startActivity(GoUrl);
            });
        }
    }

}