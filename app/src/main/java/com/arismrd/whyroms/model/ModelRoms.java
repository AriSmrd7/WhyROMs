package com.arismrd.whyroms.model;

/**
 /**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class ModelRoms {
    private String mNamaRom;
    private String mLogoRom;
    private String mDevRom;
    private String mDescRom;
    private String mReviewRom;
    private String mWebRom;
    private String mUrlRom;


    public ModelRoms(
                     String nama_roms, String logo_roms, String developer_roms,
                     String deskripsi_roms, String review_roms, String web_roms, String url_roms
    )
    {
    //public ModelRoms(String nama, String deskripsi){
        mNamaRom = nama_roms;
        mLogoRom = logo_roms;
        mDevRom = developer_roms;
        mDescRom = deskripsi_roms;
        mReviewRom = review_roms;
        mWebRom = web_roms;
        mUrlRom = url_roms;
    }

    public String getmNamaRom() {
        return mNamaRom;
    }

    public void setmNamaRom(String mNamaRom) {
        this.mNamaRom = mNamaRom;
    }


    public String getmLogoRom() {
        return mLogoRom;
    }

    public void setmLogoRom(String mLogoRom) {
        this.mLogoRom = mLogoRom;
    }


    public String getmDevRom() {
        return mDevRom;
    }

    public void setmDevRom(String mDevRom) {
        this.mDevRom = mDevRom;
    }

    public String getmDescRom() { return mDescRom; }

    public void setmDescRom(String mDescRom) {
        this.mDescRom = mDescRom;
    }

    public String getmWebRom() {
        return mWebRom;
    }

    public void setmWebRom(String mWebRom) {
        this.mWebRom = mWebRom;
    }

    public String getmUrlRom() {
        return mUrlRom;
    }

    public void setmUrlRom(String mUrlRom) {
        this.mUrlRom = mUrlRom;
    }

}
