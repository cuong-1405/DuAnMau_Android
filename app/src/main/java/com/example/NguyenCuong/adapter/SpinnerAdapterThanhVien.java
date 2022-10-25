package com.example.NguyenCuong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.ThanhVien;

import java.util.ArrayList;


public class SpinnerAdapterThanhVien extends ArrayAdapter<ThanhVien> {

    private Context context;
    private ArrayList<ThanhVien> objects;
    TextView tvspnma,tvspnnsx;


    public SpinnerAdapterThanhVien(Context context, ArrayList<ThanhVien> objects) {
        super(context, 0,objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.spinner_adapter_thanhvien,null);

        }
        final ThanhVien obj = objects.get(position);
        if (obj != null){
            tvspnma = holder.findViewById(R.id.item_spn_thanhvien_ma);
            tvspnma.setText(String.valueOf(obj.maTV));
            tvspnnsx = holder.findViewById(R.id.item_spn_thanhvien_nsx);
            tvspnnsx.setText(obj.hoTen);
        }
    return holder;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.spinner_adapter_thanhvien,null);

        }
        final ThanhVien obj = objects.get(position);
        if (obj != null){
            tvspnma = holder.findViewById(R.id.item_spn_thanhvien_ma);
            tvspnma.setText(String.valueOf(obj.maTV));
            tvspnnsx = holder.findViewById(R.id.item_spn_thanhvien_nsx);
            tvspnnsx.setText(obj.hoTen);
        }
        return holder;
    }

}
