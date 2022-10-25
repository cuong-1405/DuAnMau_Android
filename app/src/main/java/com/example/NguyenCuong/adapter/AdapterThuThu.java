package com.example.NguyenCuong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.NguyenCuong.R;

import com.example.NguyenCuong.model.ThuThu;

import java.util.List;

public class AdapterThuThu extends ArrayAdapter<ThuThu> {

    private Context context;
    private int resource;
    private List<ThuThu> objects;
    private LayoutInflater inflater;
    String a="*";

    public AdapterThuThu(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_addtt,null);
            holder.tvusername = (TextView)convertView.findViewById(R.id.item_lv_username);
            holder.tvname = (TextView)convertView.findViewById(R.id.item_lv_name);
            holder.tvpass = (TextView)convertView.findViewById(R.id.item_lv_pass);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        ThuThu tt = objects.get(position);
        holder.tvusername.setText(tt.maTT);
        holder.tvname.setText(tt.hoTen);
        String temp = tt.matKhau;
        for (int i = 0; i < temp.length(); i++) {
            a=a.concat("*");
        }
        holder.tvpass.setText(a);
        a="";

        return convertView;
    }

    public class ViewHolder{
        TextView tvusername,tvname,tvpass;
    }
}
