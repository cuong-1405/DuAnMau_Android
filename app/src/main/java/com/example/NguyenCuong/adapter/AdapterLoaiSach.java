package com.example.NguyenCuong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterLoaiSach extends ArrayAdapter<LoaiSach> {

    private Context context;
    private int resource;
    private List<LoaiSach> objects;
    private LayoutInflater inflater;
    private ArrayList<LoaiSach> arrayList;


    public AdapterLoaiSach(Context context, int resource, List<LoaiSach> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

        this.arrayList  = new ArrayList<LoaiSach>();
        this.arrayList.addAll(objects);

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_loaisach,null);
            holder.tvma = (TextView)convertView.findViewById(R.id.item_lv_maloai);
            holder.tvnsx = (TextView)convertView.findViewById(R.id.item_lv_nsx);
            holder.m_Tenloai = (TextView)convertView.findViewById(R.id.item_lv_tenloai);

            holder.temp1 = (TextView)convertView.findViewById(R.id.tv_maloai);
            holder.temp2 = (TextView)convertView.findViewById(R.id.tv_nsx);
            holder.temp3 = (TextView)convertView.findViewById(R.id.tv_tenloai);

            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        LoaiSach ls = objects.get(position);
        holder.tvma.setText(String.valueOf(ls.maLoai));
        holder.tvnsx.setText(ls.nhaSX);
        holder.m_Tenloai.setText(ls.tenLoai);

/*        if (ls.nhaSX.contains("A")&&ls.nhaSX.contains("N")){
            holder.tvtentv.setTextColor(Color.YELLOW);
        }else if (ls.nhaSX.contains("A")){
            holder.tvtentv.setTextColor(Color.RED);
        }else if (ls.nhaSX.contains("N")){
            holder.tvtentv.setTextColor(Color.GREEN);
        }*/
        if(ls.tenLoai.contains("A")){
            holder.m_Tenloai.setTextColor(Color.RED);
        }else if(ls.tenLoai.contains("N")){
            holder.m_Tenloai.setTextColor(Color.GREEN);
        }else {
            holder.m_Tenloai.setTextColor(Color.BLACK);
        }

        holder.temp1.setText("Mã Loại Sách: ");
        holder.temp2.setText("Nhà Sản Xuất: ");
        holder.temp3.setText("Tên Loại: ");

        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        objects.clear();
        if (charText.length() == 0){
            objects.addAll(arrayList);
        }else {
            for (LoaiSach ls : arrayList) {
                if (ls.nhaSX.toLowerCase(Locale.getDefault()).contains(charText)){
                    objects.add(ls);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView tvma,tvnsx,m_Tenloai,temp1,temp2,temp3;
    }
}
