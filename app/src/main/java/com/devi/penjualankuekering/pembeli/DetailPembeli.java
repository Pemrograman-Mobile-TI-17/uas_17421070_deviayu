package com.devi.penjualankuekering.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.devi.penjualankuekering.R;
import com.devi.penjualankuekering.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPembeli extends AppCompatActivity {

    EditText edtKodeKue, edtNamaKue, edtHargaKue;
    ImageView imgGambarKue;

    String strKodeKue, strNamaKue, strHargaKue, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembeli);

        edtKodeKue = (EditText) findViewById(R.id.edtKodeKue);
        edtNamaKue = (EditText) findViewById(R.id.edtNamaKue);
        edtHargaKue = (EditText) findViewById(R.id.edtHargaKue);

        imgGambarKue = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodeKue = i.getStringExtra("kodeKue");
        strNamaKue = i.getStringExtra("namaKue");
        strHargaKue = i.getStringExtra("hargaKue");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeKue.setText(strKodeKue);
        edtNamaKue.setText(strNamaKue);
        edtHargaKue.setText(strHargaKue);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambar)
                .into(imgGambarKue);
    }
}
