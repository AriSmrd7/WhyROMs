package com.arismrd.whyroms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arismrd.whyroms.model.ModelRoms;
import com.arismrd.whyroms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class RomsAdapter extends RecyclerView.Adapter<RomsAdapter.RomViewHolder> {

    private Context mContext;
    private ArrayList<ModelRoms> mRomList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public RomsAdapter(Context context, ArrayList<ModelRoms> romList){
        mContext = context;
        mRomList = romList;
    }

    @NonNull
    @Override
    public RomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_rom, parent, false);
        return new RomViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RomViewHolder holder, int position) {
        ModelRoms currentItem = mRomList.get(position);
        String titleName = currentItem.getmNamaRom();
        String imageUrl = currentItem.getmLogoRom();

        holder.mTextViewTitle.setText(titleName);
        Picasso.with(mContext).load(imageUrl).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mRomList.size();
    }

    public class  RomViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;

        public RomViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mImageView = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(v -> {
                if (mListener != null){
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            });

        }
    }

}