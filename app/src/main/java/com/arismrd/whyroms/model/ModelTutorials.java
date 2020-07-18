package com.arismrd.whyroms.model;

/**
 /**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class ModelTutorials {
    private String mTitle;
    private String mSource;
    private String mThumbnail;


    public ModelTutorials(
            String title, String source, String thumbnail
    )
    {
        mTitle = title;
        mSource = source;
        mThumbnail = thumbnail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public String getmSource() {
        return mSource;
    }

    public void setmSource(String mSource) {
        this.mSource = mSource;
    }


    public String getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }


}
