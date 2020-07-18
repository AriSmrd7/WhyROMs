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
import com.arismrd.whyroms.adapter.TutorialsAdapter;
import com.arismrd.whyroms.model.ModelRoms;
import com.arismrd.whyroms.model.ModelTutorials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : -
 *
 * */
public class TutorialActivity extends BaseActivity implements TutorialsAdapter.OnItemClickListener {

    public static final String EXTRA_TITLE = "titleTutor";
    public static final String EXTRA_FOTO = "thumbTutor";
    public static final String EXTRA_LINK = "sourceTutor";

    private RecyclerView mRecyclerView;
    private TutorialsAdapter mTutorAdapter;
    private ArrayList<ModelTutorials> mTutorList;
    private RequestQueue mRequestQueue;

    @Override
    public int getContentViewId() {
        return R.layout.activity_tutorial;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_tutorial;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rvListTutors);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTutorList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        String url = "https://whyroms.000webhostapp.com/tutorials";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    String tTitle, tThumbnail, tSource;

                    try {
                        JSONArray jsonArray = response.getJSONArray("tutorials");

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject hit = jsonArray.getJSONObject(i);

                            tTitle = hit.getString("judul_tutorial");
                            tThumbnail = hit.getString("thumbnail_tutorial");
                            tSource = hit.getString("url_tutorial");
                            mTutorList.add(new ModelTutorials
                                    (
                                            tTitle, tThumbnail,tSource
                                    )
                            );
                        }
                        mTutorAdapter = new TutorialsAdapter(TutorialActivity.this, mTutorList);
                        mRecyclerView.setAdapter(mTutorAdapter);
                        mTutorAdapter.notifyDataSetChanged();
                        mTutorAdapter.setOnItemClickListener(TutorialActivity.this);
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            error.printStackTrace();
            progressDialog.dismiss();
        }){
        };
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailRomActivity.class);
        ModelTutorials clickedItem = mTutorList.get(position);
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getmTitle());
        detailIntent.putExtra(EXTRA_FOTO, clickedItem.getmThumbnail());
        detailIntent.putExtra(EXTRA_LINK, clickedItem.getmSource());
        startActivity(detailIntent);
    }
}