package com.devi.penjualankuekering.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.devi.penjualankuekering.R;
import com.devi.penjualankuekering.adapter.AdapterKue;
import com.devi.penjualankuekering.model.ModelKue;
import com.devi.penjualankuekering.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class ActivityDataKue extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterKue adapter;
    ListView list;

    ArrayList<ModelKue> newsList = new ArrayList<ModelKue>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kue);

        getSupportActionBar().setTitle("Data Kue");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterKue(ActivityDataKue.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataKue.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataKue, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data kue = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelKue kue = new ModelKue();
                                    final String _id = jsonObject.getString("_id");
                                    final String namaKue = jsonObject.getString("namaKue");
                                    final String kodeKue = jsonObject.getString("kodeKue");
                                    final String hargaKue = jsonObject.getString("hargaKue");
                                    final String gambar = jsonObject.getString("gambar");
                                    kue.setKodeKue(kodeKue);
                                    kue.setNamaKue(namaKue);
                                    kue.setHargaKue(hargaKue);
                                    kue.setGambar(gambar);
                                    kue.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataKue.this, EditKueDanHapusActivity.class);
                                            a.putExtra("kodeKue", newsList.get(position).getKodeKue());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namaKue", newsList.get(position).getNamaKue());
                                            a.putExtra("hargaKue", newsList.get(position).getHargaKue());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(kue);
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
