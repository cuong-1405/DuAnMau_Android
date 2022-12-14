package com.example.NguyenCuong.manhinhchao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.NguyenCuong.DAO.ThuThuDAO;
import com.example.NguyenCuong.MainActivity;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ManHinhChao extends AppCompatActivity {
    ThuThuDAO dao;
    List<ThuThu> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        dao = new ThuThuDAO(ManHinhChao.this);
        list = new ArrayList<>();
        list = dao.getAll();

        if (list.size()==0){
            dao.insertadmin();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },2000);
    }
}