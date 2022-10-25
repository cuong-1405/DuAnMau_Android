package com.example.NguyenCuong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.ThanhVien;

import java.util.List;

public class AdapterThanhVien extends ArrayAdapter<ThanhVien> {

    private Context context;
    private int resource;
    private List<ThanhVien> objects;
    private LayoutInflater inflater;


    public AdapterThanhVien(Context context, int resource, List objects) {
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
            holder.tvmatv = (TextView)convertView.findViewById(R.id.item_lv_username);
            holder.tvtentv = (TextView)convertView.findViewById(R.id.item_lv_name);
            holder.tvnamsinhtv = (TextView)convertView.findViewById(R.id.item_lv_pass);
            holder.tvgioitinhtv = (TextView)convertView.findViewById(R.id.item_lv_gt);

            holder.temp1 = (TextView)convertView.findViewById(R.id.temp_1);
            holder.temp2 = (TextView)convertView.findViewById(R.id.temp_2);
            holder.temp3 = (TextView)convertView.findViewById(R.id.temp_3);
            holder.temp4 = (TextView)convertView.findViewById(R.id.temp_4);


            convertView.setTag(holder);

        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        ThanhVien tv = objects.get(position);
        holder.tvmatv.setText(String.valueOf(tv.maTV));
        holder.tvtentv.setText(tv.hoTen);
        holder.tvnamsinhtv.setText(tv.namSinh);

        holder.temp1.setText("Mã Thành Viên: ");
        holder.temp2.setText("Tên Thành Viên: ");
        holder.temp3.setText("Năm Sinh: ");
        holder.temp4.setText("Giới tính: ");
        int gt=tv.getGioiTinh();
        if(gt==0){
            holder.temp4.setText("nam");
        }else
        {
            holder.temp4.setText("nữ");
            holder.temp4.setTextColor(Color.YELLOW);
            holder.temp4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        return convertView;
    }

    public class ViewHolder{
        TextView tvmatv,tvtentv,tvnamsinhtv,tvgioitinhtv,temp1,temp2,temp3,temp4;
    }

}
