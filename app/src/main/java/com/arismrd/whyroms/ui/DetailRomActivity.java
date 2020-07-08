package com.arismrd.whyroms.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arismrd.whyroms.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.arismrd.whyroms.ui.MainActivity.EXTRA_FOTO;
import static com.arismrd.whyroms.ui.MainActivity.EXTRA_TITLE;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 23 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class DetailRomActivity extends AppCompatActivity {

    private TextView mTextViewDev;
    private TextView mTextViewDesc;
    private TextView mTextViewRev;
    private TextView mTextViewWeb;
    private TextView mTextViewUrl;


    private RequestQueue mRequestQueue;
    private String titleName;
    private String fotoUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rom);

        Intent intent = getIntent();
        titleName = intent.getStringExtra(EXTRA_TITLE);
        fotoUrl = intent.getStringExtra(EXTRA_FOTO);

        TextView textViewCreator = findViewById(R.id.txtNama);
        ImageView imageView = findViewById(R.id.imgLogo);
        Picasso.with(this).load(fotoUrl).fit().centerInside().into(imageView);
        textViewCreator.setText(titleName);

        mTextViewDev = findViewById(R.id.txtIsiDev);
        mTextViewDesc = findViewById(R.id.txtIsiDesc);
        mTextViewRev = findViewById(R.id.txtIsiRev);
        mTextViewWeb = findViewById(R.id.txtIsiWeb);
        mTextViewUrl = findViewById(R.id.txtIsiUrl);

        mRequestQueue = Volley.newRequestQueue(this);
        jsonParse();


    }

    private void jsonParse() {

        String url = "https://whyroms.000webhostapp.com/roms";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int j;
                        String nama, tDev , tDesc,
                                tRev, tWeb, tUrl, fotos;
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                nama = hit.getString("nama_roms");
                                tDev = hit.getString("developer_roms");
                                tDesc = hit.getString("deskripsi_roms");
                                tRev = hit.getString("review_roms");
                                tWeb = hit.getString("web_roms");
                                tUrl = hit.getString("url_roms");
                                fotos = hit.getString("logo_roms");

                                if (titleName.equals(nama) && fotoUrl.equals(fotos)) {
                                    mTextViewDev.append(tDev);
                                    mTextViewDesc.append(tDesc);
                                    mTextViewRev.append(tRev);
                                    mTextViewWeb.append(tWeb);
                                    mTextViewUrl.append(tUrl);
                                }
                            }
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


}
