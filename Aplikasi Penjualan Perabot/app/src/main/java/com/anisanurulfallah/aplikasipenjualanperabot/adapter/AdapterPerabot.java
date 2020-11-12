package com.anisanurulfallah.aplikasipenjualanperabot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anisanurulfallah.aplikasipenjualanperabot.R;
import com.anisanurulfallah.aplikasipenjualanperabot.model.ModelPerabot;
import com.anisanurulfallah.aplikasipenjualanperabot.server.BaseUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPerabot extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelPerabot> item;

    public AdapterPerabot(Activity activity, List<ModelPerabot> item) {
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
            convertView = inflater.inflate(R.layout.content_perabot, null);


        TextView kodeperabot        = (TextView) convertView.findViewById(R.id.txtkodeperabot);
        TextView namaperabot        = (TextView) convertView.findViewById(R.id.txtnamaperabot);
        TextView jumlahperabot      = (TextView) convertView.findViewById(R.id.txtjumlahperabot);
        TextView hargaperabot       = (TextView) convertView.findViewById(R.id.txtharga);
        ImageView gambarperabot            = (ImageView) convertView.findViewById(R.id.gambarperabot);

        kodeperabot.setText(item.get(position).getKodeBarang());
        namaperabot.setText(item.get(position).getNamabarang());
        jumlahperabot.setText(item.get(position).getJumlahbarang());
        hargaperabot.setText("Rp." + item.get(position).getHargabarang());
        Picasso.get().load(BaseUrl.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarperabot);
        return convertView;
    }
}
