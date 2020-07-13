package com.arismrd.whyroms.model;

/**
 /**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juli 2020
 *
 * */

public class ModelMagisk {
    private String mVersi;
    private String mStatus;
    private String mImage;
    private String mLink;

    public ModelMagisk(
            String versi, String status, String image, String link
            )
    {
        mVersi  = versi;
        mStatus = status;
        mImage  = image;
        mLink   = link;
    }

    public String getmVersi() {
        return mVersi;
    }
    public void setmVersi(String mVersi) {
        this.mVersi = mVersi;
    }


    public String getmStatus() {
        return mStatus;
    }
    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }


    public String getmImage() {
        return mImage;
    }
    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmLink() {
        return mLink;
    }
    public void setmLink(String mLink) {
        this.mLink = mLink;
    }


}
