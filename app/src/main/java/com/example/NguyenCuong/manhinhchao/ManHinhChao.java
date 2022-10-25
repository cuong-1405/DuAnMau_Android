package com.example.NguyenCuong.manhinhchao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.NguyenCuong.DAO.ThuThuDAO;
import com.example.NguyenCuong.MainActivity;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ManHinhChao extends AppCompatActivity {
    ThuThuDAO dao;
    List<ThuThu> list;
    EditText MASV;
    Button btnchuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        dao = new ThuThuDAO(ManHinhChao.this);
        list = new ArrayList<>();
        list = dao.getAll();
        MASV=findViewById(R.id.masv);
        btnchuyen=findViewById(R.id.btn1);



        if (list.size()==0){
            dao.insertadmin();
        }

        btnchuyen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(MASV.getText().toString().equalsIgnoreCase("PH20178")){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplication(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}