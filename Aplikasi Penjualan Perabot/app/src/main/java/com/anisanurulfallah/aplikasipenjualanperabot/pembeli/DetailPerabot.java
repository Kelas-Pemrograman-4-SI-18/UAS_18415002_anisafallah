package com.anisanurulfallah.aplikasipenjualanperabot.pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.anisanurulfallah.aplikasipenjualanperabot.R;
import com.anisanurulfallah.aplikasipenjualanperabot.server.BaseUrl;
import com.squareup.picasso.Picasso;

public class DetailPerabot extends AppCompatActivity {

    EditText edtkodeperabot, edtnamaperabot, edtjumlah, edthargaperabot;
    ImageView imgGambarPerabot;

    String strkodeperabot, strnamaperabot, strjumlah, strhargaperabot, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perabot);


        edtkodeperabot = (EditText) findViewById(R.id.edtkodeperabot);
        edtnamaperabot = (EditText) findViewById(R.id.edtnamaperabot);
        edtjumlah = (EditText) findViewById(R.id.edtjumlah);
        edthargaperabot = (EditText) findViewById(R.id.edthargaperabot);

        imgGambarPerabot = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strkodeperabot = i.getStringExtra("kodeperabot");
        strnamaperabot = i.getStringExtra("namaperabot");
        strjumlah = i.getStringExtra("jumlah");
        strhargaperabot = i.getStringExtra("hargaperabot");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");
        edtkodeperabot.setText(strkodeperabot);
        edthargaperabot.setText(strnamaperabot);
        edtjumlah.setText(strjumlah);
        edthargaperabot.setText(strhargaperabot);
        Picasso.get().load(BaseUrl.baseUrl + "gambar/" + strGambar)
                .into(imgGambarPerabot);
    }
}