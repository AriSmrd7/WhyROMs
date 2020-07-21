package com.arismrd.whyroms.model;

import java.io.Serializable;

/**
 * /**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 */

public class ModelTutorials implements Serializable {
    private String mJudul;
    private String mUrl;
    private String mThumbnail;


    public ModelTutorials(
            String judul_tutorial, String thumbnail_tutorial, String url_tutorial
    ) {
        mJudul = judul_tutorial;
        mUrl = url_tutorial;
        mThumbnail = thumbnail_tutorial;
    }

    public String getmJudul() {
        return mJudul;
    }

    public void setmJudul(String mJudul) {
        this.mJudul = mJudul;
    }


    public String getmUrl() {
        return mUrl;
    }

    public void setmSource(String mUrl) {
        this.mUrl = mUrl;
    }


    public String getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }


}
