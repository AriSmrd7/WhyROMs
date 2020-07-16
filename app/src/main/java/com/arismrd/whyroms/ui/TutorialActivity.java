package com.arismrd.whyroms.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arismrd.whyroms.R;
import com.arismrd.whyroms.adapter.TutorialsAdapter;
import com.arismrd.whyroms.model.ModelTutorials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * UpdateCoding : -
 *
 * */
public class TutorialActivity extends BaseActivity {

    public static final String TAG = "TAG";
    RecyclerView videoList;
    TutorialsAdapter adapter;
    List<ModelTutorials> all_videos;

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
        all_videos = new ArrayList<>();
        videoList = findViewById(R.id.videoList);
        videoList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TutorialsAdapter(this,all_videos);
        videoList.setAdapter(adapter);

        getTutorialData();
    }

    private void getTutorialData() {
        String URL = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            Log.d(TAG, "onResponse: "+ response);
            try {
                JSONArray categories = response.getJSONArray("categories");
                JSONObject categoriesData = categories.getJSONObject(0);
                JSONArray videos = categoriesData.getJSONArray("videos");

                Log.d(TAG, "onResponse: "+ videos);

                for (int i = 0; i< videos.length();i++){
                    JSONObject video = videos.getJSONObject(i);

                    ModelTutorials v = new ModelTutorials();

                    v.setTitle(video.getString("title"));
                    v.setDescription(video.getString("description"));
                    v.setAuthor(video.getString("subtitle"));
                    v.setImageUrl(video.getString("thumb"));
                    JSONArray videoUrl = video.getJSONArray("sources");
                    v.setVideoUrl(videoUrl.getString(0));

                    all_videos.add(v);
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> Log.d(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(objectRequest);
    }


}