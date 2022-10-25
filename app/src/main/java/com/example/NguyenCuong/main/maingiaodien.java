package com.example.NguyenCuong.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.NguyenCuong.DAO.LoaiSachDAO;
import com.example.NguyenCuong.DAO.ThuThuDAO;
import com.example.NguyenCuong.adapter.AdapterLoaiSach;
import com.example.NguyenCuong.databinding.ActivityMaingiaodienBinding;
import com.example.NguyenCuong.model.LoaiSach;
import com.example.NguyenCuong.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.NguyenCuong.R;

import java.util.ArrayList;
import java.util.List;

public class maingiaodien extends AppCompatActivity {

    Context context = this;
    TextView nameuser;
    View view;
    ThuThu thuThu;
    ThuThuDAO thuThuDAO;
    List<ThuThu> thuThuList;

    LoaiSachDAO loaiSachDAO;
    AdapterLoaiSach adapterLoaiSach;
    List<LoaiSach> sachList;

    ListView listView;


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMaingiaodienBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sachList = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(getApplicationContext());
        sachList = loaiSachDAO.getAll();
        adapterLoaiSach = new AdapterLoaiSach(getApplicationContext(),R.layout.item_lv_addtt,sachList);

        binding = ActivityMaingiaodienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMaingiaodien.toolbar);

        thuThu = new ThuThu();
        thuThuDAO = new ThuThuDAO(getApplicationContext());
        thuThuList = new ArrayList<>();



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_qlphieuthu, R.id.nav_qlloaisach, R.id.nav_quanlysach, R.id.nav_qlthanhvien, R.id.nav_top, R.id.nav_doanhthu, R.id.nav_them, R.id.nav_matkhau, R.id.nav_dangxuat)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_maingiaodien);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        view = navigationView.getHeaderView(0);
        nameuser = view.findViewById(R.id.login_nameuser);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuList = thuThuDAO.getAll();
        if(user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.nav_them).setVisible(true);
        }
        for (int i = 0; i < thuThuList.size(); i++) {
            if (thuThuList.get(i).maTT.equals(user)){
                nameuser.setText("Xin Chào "+thuThuList.get(i).hoTen);
                return;
            }
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_maingiaodien);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}