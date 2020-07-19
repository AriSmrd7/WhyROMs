package com.arismrd.whyroms.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arismrd.whyroms.R;
import com.arismrd.whyroms.adapter.AddonsAdapter;
import com.arismrd.whyroms.model.ModelAddons;
import com.arismrd.whyroms.utils.PrefHelper;
import com.jaredrummler.android.device.DeviceName;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : 19 Juli 2020, 19.00 - 22.00 WIB
 *
 * */
public class MainActivity extends BaseActivity {

    private  PrefHelper prefManager = null;

    private RecyclerView mRecyclerView;
    private AddonsAdapter mAddonsAdapter;
    private ArrayList<ModelAddons> mMagiskList;
    private RequestQueue mRequestQueue;
    CarouselView carouselView;


    int[] sampleImages = {
            R.drawable.slide_first,
            R.drawable.slide_second,
            R.drawable.slide_third
    };

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefHelper(this);

        mRecyclerView = findViewById(R.id.rvListMagisk);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mMagiskList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        getMagiskData();

        //device info
        DeviceName.with(this).request((info, error) -> {
             String Dname = info.getName();
             String Dcode   = info.model;

             final TextView devicename = findViewById(R.id.DeviceName);
             devicename.setText(Dname);
             final TextView devicecode = findViewById(R.id.CodeName);
             devicecode.setText(Dcode);
        });

        //slider
        carouselView = findViewById(R.id.carouselView);

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

    }
    // To set simple images
    ImageListener imageListener = (position, imageView) -> imageView.setImageResource(sampleImages[position]);

    private void getMagiskData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://whyroms.000webhostapp.com/addons";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    String tVersi, tStatus, tImage, tLink;

                    try {
                        JSONArray jsonArray = response.getJSONArray("addons");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject hit = jsonArray.getJSONObject(i);

                            tVersi  = hit.getString("versi");
                            tStatus = hit.getString("status");
                            tImage  = hit.getString("image");
                            tLink  = hit.getString("link");
                            mMagiskList.add(new ModelAddons
                                    (
                                            tVersi, tStatus,tImage, tLink
                                    )
                            );
                        }
                        mAddonsAdapter = new AddonsAdapter(MainActivity.this, mMagiskList);
                        mRecyclerView.setAdapter(mAddonsAdapter);
                        mAddonsAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    error.printStackTrace();
                    progressDialog.dismiss();
                }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };
        mRequestQueue.add(request);
    }

}