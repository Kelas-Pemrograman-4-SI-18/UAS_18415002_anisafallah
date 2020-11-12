package com.anisanurulfallah.aplikasipenjualanperabot.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anisanurulfallah.aplikasipenjualanperabot.R;
import com.anisanurulfallah.aplikasipenjualanperabot.adapter.AdapterPerabot;
import com.anisanurulfallah.aplikasipenjualanperabot.admin.HomeAdminActivity;
import com.anisanurulfallah.aplikasipenjualanperabot.model.ModelPerabot;
import com.anisanurulfallah.aplikasipenjualanperabot.server.BaseUrl;
import com.anisanurulfallah.aplikasipenjualanperabot.session.PrefSetting;
import com.anisanurulfallah.aplikasipenjualanperabot.session.SessionManager;
import com.anisanurulfallah.aplikasipenjualanperabot.users.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePembeli extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterPerabot adapter;
    ListView list;

    ArrayList<ModelPerabot> newsList = new ArrayList<ModelPerabot>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharedPreferences();

        session = new SessionManager(HomePembeli.this);

        prefSetting.isLogin(session, prefs);

        getSupportActionBar().setTitle("Data Perabot");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomePembeli.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        newsList.clear();
        adapter = new AdapterPerabot(HomePembeli.this, newsList);
        list.setAdapter(adapter);
        getAllPerabot();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(HomePembeli.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllPerabot() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseUrl.dataPerabot, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data makanan = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelPerabot perabot = new ModelPerabot();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodeperabot = jsonObject.getString("kodeperabot");
                                    final String namaperabot = jsonObject.getString("namaperabot");
                                    final String jumlah = jsonObject.getString("jumlah");
                                    final String hargaperabot = jsonObject.getString("hargaperabot");
                                    final String gambar = jsonObject.getString("gambar");
                                    perabot.setKodeBarang(kodeperabot);
                                    perabot.setNamabarang(namaperabot);
                                    perabot.setJumlahbarang(jumlah);
                                    perabot.setHargabarang(hargaperabot);
                                    perabot.setGambar(gambar);

                                    perabot.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeli.this, DetailPerabot.class);
                                            a.putExtra("kodeperabot", newsList.get(position).getKodeBarang());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namaperabot", newsList.get(position).getNamabarang());
                                            a.putExtra("jumlah", newsList.get(position).getJumlahbarang());
                                            a.putExtra("hargaperabot", newsList.get(position).getHargabarang());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(perabot);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}