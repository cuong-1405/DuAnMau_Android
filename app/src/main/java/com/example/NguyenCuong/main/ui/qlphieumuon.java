package com.example.NguyenCuong.main.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.DAO.PhieuMuonDAO;
import com.example.NguyenCuong.DAO.SachDAO;
import com.example.NguyenCuong.DAO.ThanhVienDAO;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.adapter.AdapterPhieuMuon;
import com.example.NguyenCuong.adapter.SpinnerAdapterSach;
import com.example.NguyenCuong.adapter.SpinnerAdapterThanhVien;
import com.example.NguyenCuong.databinding.FragmentQlphieumuonBinding;
import com.example.NguyenCuong.model.PhieuMuon;
import com.example.NguyenCuong.model.Sach;
import com.example.NguyenCuong.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class qlphieumuon extends Fragment {

private FragmentQlphieumuonBinding binding;

    FloatingActionButton fab;
    ListView listView;
    PhieuMuonDAO dao;
    List<PhieuMuon> list;
    AdapterPhieuMuon adapterPhieuMuon;
    PhieuMuon phieuMuon;

    int a;
    List<String> loi;
    String maThanhVien,maSach;

    List<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDao;
    SpinnerAdapterThanhVien adapterThanhVien;

    List<Sach> listSach;
    SachDAO sachDAO;
    SpinnerAdapterSach adapterSach;
    Sach sach;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


    binding = FragmentQlphieumuonBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        fab = root.findViewById(R.id.phieumuon_fab);
        listView = root.findViewById(R.id.phieumuon_listview);

        listThanhVien = new ArrayList<>();
        thanhVienDao = new ThanhVienDAO(getActivity());
        listThanhVien = thanhVienDao.getAll();

        sach = new Sach();
        listSach = new ArrayList<>();
        sachDAO = new SachDAO(getActivity());
        listSach = sachDAO.getAll();

        loi = new ArrayList<>();

        loadTable();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=-1;
                if (listSach.size()==0){
                    loi.add("sách");
                }else {
                    list.remove("sách");
                }
                if (listThanhVien.size()==0){
                    loi.add("thành viên");
                }else {
                    list.remove("thành viên");
                }
                if (loi.isEmpty()){
                    openDialog(Gravity.BOTTOM);
                }else{
                    Toast.makeText(getActivity(), "Bạn chưa thêm :"+loi, Toast.LENGTH_SHORT).show();
                    loi = new ArrayList<>();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = i;
                openDialog(Gravity.BOTTOM);
            }
        });
    return root;
    }

    private void loadTable(){
        dao = new PhieuMuonDAO(getActivity());
        list = dao.getAll();
        adapterPhieuMuon = new AdapterPhieuMuon(getActivity(),R.layout.item_lv_addtt,list);
        listView.setAdapter(adapterPhieuMuon);
    }

    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_phieumuon);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
//        else
//        {
//            dialog.setCancelable(false);
//        }

        TextView tvTile = (TextView) dialog.findViewById(R.id.dialog_phieumuon_tile);
        TextView ngayThue = (TextView) dialog.findViewById(R.id.dialog_phieumuon_ngaythue);
        TextView giaThue = (TextView) dialog.findViewById(R.id.dialog_phieumuon_tienthue);


        EditText maPM = (EditText)dialog.findViewById(R.id.dialog_phieumuon_txtmaphiuemuon);


        TextInputLayout tilmaphieumuon = (TextInputLayout)dialog.findViewById(R.id.dialog_phieumuon_til_maphieumuon);


        Spinner spnThanhVien = (Spinner) dialog.findViewById(R.id.dialog_spn_tenthanhvien);
        Spinner spnSach = (Spinner) dialog.findViewById(R.id.dialog_spn_sach);


        Button btnadd = dialog.findViewById(R.id.dialog_sach_add);
        Button btncancel = dialog.findViewById(R.id.dialog_sach_cancel);


        CheckBox checkBox = dialog.findViewById(R.id.dialog_phieumuon_checkBox);


        adapterThanhVien = new SpinnerAdapterThanhVien(getContext(), (ArrayList<ThanhVien>) thanhVienDao.getAll());

        spnThanhVien.setAdapter(adapterThanhVien);

        spnThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = String.valueOf(listThanhVien.get(i).maTV);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        adapterSach = new SpinnerAdapterSach(getContext(), (ArrayList<Sach>) sachDAO.getAll());

        spnSach.setAdapter(adapterSach);

        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = String.valueOf(listSach.get(i).maSach);
                sach = sachDAO.getAll().get(i);
                giaThue.setText(String.valueOf(sach.giaThue));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        dao = new PhieuMuonDAO(getActivity());
        tilmaphieumuon.setEnabled(false);

        String datetime = sdf.format(c.getTime());

        Intent intent = getActivity().getIntent();
        String user = intent.getStringExtra("user");

        if (a==-1){
            ngayThue.setText(datetime);
            checkBox.setEnabled(false);

            list = dao.getAll();

            if (list.size()==0){
                maPM.setText("1");
            }else{
                phieuMuon = dao.getAll().get(list.size()-1);
                maPM.setText(String.valueOf(phieuMuon.maPM+1));
            }

            btnadd.setOnClickListener(new View.OnClickListener() {
                PhieuMuon phieuMuon = new PhieuMuon();
                @Override
                public void onClick(View view) {

                    phieuMuon.maTT = user;
                    phieuMuon.maTV = Integer.parseInt(maThanhVien);
                    phieuMuon.maSach = Integer.parseInt(maSach);
                    phieuMuon.ngay = java.sql.Date.valueOf(datetime);
                    phieuMuon.tienThue = Integer.parseInt(giaThue.getText().toString());
                    phieuMuon.traSach = 0;

                    if (dao.insert(phieuMuon)>0){
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }else{
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Huỷ thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        else{
            phieuMuon = dao.getAll().get(a);

            tvTile.setText("SỬA/XOÁ PHIẾU MƯỢN");
            btnadd.setText("Sửa");
            btncancel.setText("Xoá");

            maPM.setText(String.valueOf(phieuMuon.maPM));
            ngayThue.setText(String.valueOf(phieuMuon.ngay));
            if (phieuMuon.traSach == 1){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }
            for (int i=0;i<spnThanhVien.getCount();i++){
                if (list.get(a).maTV == listThanhVien.get(i).maTV){
                    spnThanhVien.setSelection(i);
                }
            }
            for (int i=0;i<spnSach.getCount();i++){
                if (list.get(a).maSach == listSach.get(i).maSach){
                    spnSach.setSelection(i);
                }
            }

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    phieuMuon = new PhieuMuon();
                    phieuMuon.maTT = user;
                    phieuMuon.maPM = Integer.parseInt(maPM.getText().toString());
                    phieuMuon.maTV = Integer.parseInt(maThanhVien);
                    phieuMuon.maSach = Integer.parseInt(maSach);
                    phieuMuon.ngay = java.sql.Date.valueOf(ngayThue.getText().toString());
                    phieuMuon.tienThue = Integer.parseInt(giaThue.getText().toString());


                    if (checkBox.isChecked()){
                        phieuMuon.traSach = 1;
                    }else{
                        phieuMuon.traSach = 0;
                    }
                    if (dao.update(phieuMuon)<0){
                        Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }

                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (dao.delete(String.valueOf(phieuMuon.maPM))<0){
                        Toast.makeText(getActivity(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }

                }
            });
        }

        dialog.show();

    }



@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}