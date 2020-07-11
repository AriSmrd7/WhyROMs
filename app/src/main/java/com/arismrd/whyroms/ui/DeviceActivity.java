package com.arismrd.whyroms.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.arismrd.whyroms.adapter.DevicesAdapter;
import com.arismrd.whyroms.model.ModelDevices;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : -
 *
 * */
public class DeviceActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private DevicesAdapter mDevAdapter;
    private ArrayList<ModelDevices> mDevList;
    private RequestQueue mRequestQueue;

    @Override
    int getContentViewId() {
        return R.layout.activity_device;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigaton_device;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rvListDevices);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDevList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        getData();

    }

    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://whyroms.000webhostapp.com/devices";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    String tName, tCode, tImg;

                    try {
                        JSONArray jsonArray = response.getJSONArray("devices");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject hit = jsonArray.getJSONObject(i);

                            tName = hit.getString("nama_devices");
                            tCode = hit.getString("kode_devices");
                            tImg = hit.getString("img_devices");
                            mDevList.add(new ModelDevices
                                    (
                                           tName, tCode, tImg
                                    )
                            );
                        }
                        mDevAdapter = new DevicesAdapter(DeviceActivity.this, mDevList);
                        mRecyclerView.setAdapter(mDevAdapter);
                        mDevAdapter.notifyDataSetChanged();

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
                    final long cacheHitButRefreshed = 20 * 60 * 1000; // in 20 minutes cache will be hit, but also refreshed on background
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