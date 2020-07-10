package com.arismrd.whyroms.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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
import com.arismrd.whyroms.adapter.RomsAdapter;
import com.arismrd.whyroms.model.ModelRoms;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : 22 Juni 2020, 21.00 - 01.28 WIB
 *
 * */
public class MainActivity extends BaseActivity implements RomsAdapter.OnItemClickListener {

    public static final String EXTRA_TITLE = "namaROM";
    public static final String EXTRA_FOTO = "logoROM";
    private RecyclerView mRecyclerView;
    private RomsAdapter mRomAdapter;
    private ArrayList<ModelRoms> mRomList;
    private RequestQueue mRequestQueue;

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_rom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rvListRoms);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRomList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://whyroms.000webhostapp.com/roms";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    String titleNama, tLogo, tDev,
                            tDesc, tRev, tWeb, tUrl;

                    try {
                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject hit = jsonArray.getJSONObject(i);

                            titleNama = hit.getString("nama_roms");
                            tLogo = hit.getString("logo_roms");
                            tDev = hit.getString("developer_roms");
                            tDesc = hit.getString("deskripsi_roms");
                            tRev = hit.getString("review_roms");
                            tWeb = hit.getString("web_roms");
                            tUrl = hit.getString("url_roms");
                            mRomList.add(new ModelRoms
                                    (
                                            titleNama, tLogo,tDev, tDesc, tRev,tWeb,tUrl
                                    )
                            );
                        }
                        mRomAdapter = new RomsAdapter(MainActivity.this, mRomList);
                        mRecyclerView.setAdapter(mRomAdapter);
                        mRomAdapter.notifyDataSetChanged();
                        mRomAdapter.setOnItemClickListener(MainActivity.this);
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailRomActivity.class);
        ModelRoms clickedItem = mRomList.get(position);
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getmNamaRom());
        detailIntent.putExtra(EXTRA_FOTO, clickedItem.getmLogoRom());
        startActivity(detailIntent);
    }
}