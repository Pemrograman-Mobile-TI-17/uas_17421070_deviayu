package com.devi.penjualankuekering.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devi.penjualankuekering.R;
import com.devi.penjualankuekering.model.ModelKue;
import com.devi.penjualankuekering.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterKue extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelKue> item;

    public AdapterKue(Activity activity, List<ModelKue> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_kue, null);


        TextView namakue = (TextView) convertView.findViewById(R.id.txtNamaKue);
        TextView kodekue = (TextView) convertView.findViewById(R.id.txtkodeKue);
        TextView hargakue         = (TextView) convertView.findViewById(R.id.txtHargaKue);
        ImageView gambarkue         = (ImageView) convertView.findViewById(R.id.gambar);

        namakue.setText(item.get(position).getNamaKue());
        kodekue.setText(item.get(position).getKodeKue());
        hargakue.setText("Rp." + item.get(position).getHargaKue());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarkue);
        return convertView;
    }

}