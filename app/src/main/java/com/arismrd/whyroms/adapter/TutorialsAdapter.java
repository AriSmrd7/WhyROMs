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
import com.arismrd.whyroms.model.ModelTutorials;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.TutorialViewHolder> {

    private Context mContext;
    private ArrayList<ModelTutorials> mTutorList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public TutorialsAdapter(Context context, ArrayList<ModelTutorials> tutorList){
        mContext = context;
        mTutorList = tutorList;
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_tutorial, parent, false);
        return new TutorialViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        ModelTutorials currentItem = mTutorList.get(position);
        String titleName = currentItem.getmTitle();
        String imageUrl = currentItem.getmThumbnail();

        holder.mTextViewTitle.setText(titleName);
        Picasso.with(mContext).load(imageUrl).into(holder.mImageView);
        Picasso.with(mContext).setLoggingEnabled(true);
    }

    @Override
    public int getItemCount() {
        return mTutorList.size();
    }

    public class  TutorialViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;

        public TutorialViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.videoTitle);
            mImageView = itemView.findViewById(R.id.videoThumbnail);
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