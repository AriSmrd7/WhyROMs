package com.arismrd.whyroms.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arismrd.whyroms.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.arismrd.whyroms.ui.RomActivity.EXTRA_FOTO;
import static com.arismrd.whyroms.ui.RomActivity.EXTRA_LINK;
import static com.arismrd.whyroms.ui.RomActivity.EXTRA_TITLE;

/**
 * Nama : Ari Sumardi
 * NIM  : 10117162
 * Coding : 23 Juni 2020, 21.00 - 01.28 WIB
 *
 * */

public class DetailRomActivity extends AppCompatActivity {

    private TextView mTextViewDev;
    private TextView mTextViewDesc;
    private TextView mTextViewWeb;
    private TextView mTextViewUrl;


    private RequestQueue mRequestQueue;
    private String namaROM;
    private String logoROM;
    private String linkROM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rom);

        Intent intent = getIntent();
        namaROM = intent.getStringExtra(EXTRA_TITLE);
        logoROM = intent.getStringExtra(EXTRA_FOTO);
        linkROM = intent.getStringExtra(EXTRA_LINK);

        findViewById(R.id.btnDownload).setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(linkROM));
            startActivity(i);
        });


        TextView textViewCreator = findViewById(R.id.txtNama);
        ImageView imageView = findViewById(R.id.imgLogo);
        Picasso.with(this).load(logoROM).fit().centerInside().into(imageView);
        textViewCreator.setText(namaROM);

        mTextViewDev = findViewById(R.id.txtIsiDev);
        mTextViewDesc = findViewById(R.id.txtIsiDesc);
        mTextViewWeb = findViewById(R.id.txtIsiWeb);
        mTextViewUrl = findViewById(R.id.txtIsiUrl);

        mRequestQueue = Volley.newRequestQueue(this);
        getDetailROM();


    }

    private void getDetailROM() {

        String url = "https://whyroms.000webhostapp.com/roms";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    String nama, tDev , tDesc,
                           tWeb, tUrl, fotos;
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);
                            nama = hit.getString("nama_roms");
                            tDev = hit.getString("developer_roms");
                            tDesc = hit.getString("deskripsi_roms");
                            tWeb = hit.getString("web_roms");
                            tUrl = hit.getString("url_roms");
                            fotos = hit.getString("logo_roms");

                            if (namaROM.equals(nama) && logoROM.equals(fotos)) {
                                mTextViewDev.append(tDev);
                                mTextViewDesc.append(tDesc);
                                mTextViewWeb.append(tWeb);
                                mTextViewUrl.append(tUrl);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        mRequestQueue.add(request);

    }

}
