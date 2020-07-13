package com.arismrd.whyroms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

    private Context mContext;
    private ArrayList<ModelMagisk> mMagiskList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public MagiskAdapter(Context context, ArrayList<ModelMagisk> magiskList){
        mContext = context;
        mMagiskList = magiskList;
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
        String tLink  = currentItem.getmLink();

        holder.mLink.setText(tLink);
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
        public TextView mLink;

        public MagiskViewHolder(@NonNull View itemView) {
            super(itemView);

            mVersi = itemView.findViewById(R.id.txtMagiskVersion);
            mStatus = itemView.findViewById(R.id.txtMagiskStatus);
            mImage = itemView.findViewById(R.id.imgMagisk);
            mLink  = itemView.findViewById(R.id.txtMagiskLink);
        }
    }

}