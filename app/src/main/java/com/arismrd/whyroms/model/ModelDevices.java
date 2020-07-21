package com.arismrd.whyroms.model;

/**
 * /**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 10 Juli 2020
 */

public class ModelDevices {
    private String mNamaDevices;
    private String mKodeDevices;
    private String mImageDevices;


    public ModelDevices(
            String nama_devices, String kode_devices, String img_devices
    ) {
        mNamaDevices = nama_devices;
        mKodeDevices = kode_devices;
        mImageDevices = img_devices;
    }

    public String getmNamaDevices() {
        return mNamaDevices;
    }

    public void setmNamaDevices(String mNamaDevices) {
        this.mNamaDevices = mNamaDevices;
    }


    public String getmKodeDevices() {
        return mKodeDevices;
    }

    public void setmKodeDevices(String mKodeDevices) {
        this.mKodeDevices = mKodeDevices;
    }


    public String getmImageDevices() {
        return mImageDevices;
    }

    public void setmImageDevices(String mImageDevices) {
        this.mImageDevices = mImageDevices;
    }


}
