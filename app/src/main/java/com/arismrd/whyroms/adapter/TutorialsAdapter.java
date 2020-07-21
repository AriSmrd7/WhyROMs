package com.arismrd.whyroms.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arismrd.whyroms.R;
import com.arismrd.whyroms.model.ModelTutorials;
import com.arismrd.whyroms.ui.PlayTutorialActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 */

public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.TutorialViewHolder> {

    private Context mContext;
    private ArrayList<ModelTutorials> mTutorialList;

    public TutorialsAdapter(Context context, ArrayList<ModelTutorials> tutorialList) {
        mContext = context;
        mTutorialList = tutorialList;
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_tutorial, parent, false);
        return new TutorialViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        ModelTutorials currentItem = mTutorialList.get(position);
        String titleName = currentItem.getmJudul();
        String imageUrl = currentItem.getmThumbnail();

        holder.mTextViewTitle.setText(titleName);
        Picasso.with(mContext).load(imageUrl).into(holder.mImageView);

        holder.vv.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putSerializable("videoData", mTutorialList.get(position));
            Intent i = new Intent(mContext, PlayTutorialActivity.class);
            i.putExtras(b);
            v.getContext().startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return mTutorialList.size();
    }

    public static class TutorialViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewTitle;
        View vv;

        public TutorialViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.videoTitle);
            mImageView = itemView.findViewById(R.id.videoThumbnail);
            vv = itemView;

        }
    }

}