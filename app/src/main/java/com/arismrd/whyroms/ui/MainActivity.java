package com.arismrd.whyroms.ui;

import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arismrd.whyroms.R;
import com.arismrd.whyroms.adapter.RomsAdapter;
import com.arismrd.whyroms.model.ModelRoms;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : 22 Juni 2020, 21.00 - 01.28 WIB
 *
 * */
public class MainActivity extends BaseActivity implements RomsAdapter.OnItemClickListener {

    public static final String EXTRA_TITLE = "titleNama";
    public static final String EXTRA_FOTO = "fotoUrl";
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRomList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson(){
        String url = "https://whyroms.000webhostapp.com/roms";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int j;
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
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