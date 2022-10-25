package com.example.NguyenCuong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.Top;

import java.util.List;

public class AdapterTop extends ArrayAdapter<Top> {

    private Context context;
    private int resource;
    private List<Top> objects;
    private LayoutInflater inflater;
    private int temp = 0;


    public AdapterTop(Context context, int resource, List objects) {
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
            temp++;
            convertView = inflater.inflate(R.layout.item_lv_top,null);
            holder.top = convertView.findViewById(R.id.item_lv_top);
            holder.sach = (TextView)convertView.findViewById(R.id.item_lv_top_sach);
            holder.soLuong = (TextView)convertView.findViewById(R.id.item_lv_top_so);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        Top top = objects.get(position);
        holder.top.setText(String.valueOf(temp));
        holder.sach.setText(top.tenSach);
        holder.soLuong.setText(String.valueOf(top.soLuong));

        return convertView;
    }

    public class ViewHolder{
        TextView top,sach,soLuong;
    }
}
