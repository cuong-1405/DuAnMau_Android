package com.example.NguyenCuong.main.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.DAO.PhieuMuonDAO;
import com.example.NguyenCuong.DAO.ThuThuDAO;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.adapter.AdapterThuThu;
import com.example.NguyenCuong.databinding.FragmentThemBinding;
import com.example.NguyenCuong.model.PhieuMuon;
import com.example.NguyenCuong.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class them extends Fragment {

private FragmentThemBinding binding;

    FloatingActionButton fab;
    ThuThuDAO dao;
    ListView listView;
    AdapterThuThu adapterThuThu;
    List<ThuThu> list;
    ThuThu thuThu;
    int a;
    int temp=0;

    EditText txtnameuser, txtname, txtpass;
    TextInputLayout tilusername, tilname, tilpass;

    List<PhieuMuon> phieuMuonList;
    PhieuMuonDAO phieuMuonDAO;


    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentThemBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    fab = root.findViewById(R.id.add_fab);
    listView = root.findViewById(R.id.add_listview);

        phieuMuonList = new ArrayList<>();
        phieuMuonDAO = new PhieuMuonDAO(getActivity());

    loadTable();

    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            a=-1;
            openDialog(Gravity.CENTER);
        }
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            a = i;
            if (a!=0){
                openDialog(Gravity.CENTER);
            }else {
                Toast.makeText(getActivity(), "Không Thể Sửa ADMIN", Toast.LENGTH_SHORT).show();
            }
        }
    });


    return root;
    }

    private void loadTable(){
        dao = new ThuThuDAO(getActivity());
        list = dao.getAll();
        adapterThuThu = new AdapterThuThu(getActivity(),R.layout.item_lv_addtt,list);
        listView.setAdapter(adapterThuThu);
    }

    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themtt);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        txtnameuser = dialog.findViewById(R.id.item_txtnameuser);
        txtname = dialog.findViewById(R.id.item_txtname);
        txtpass = dialog.findViewById(R.id.item_txtpass);

        tilusername = dialog.findViewById(R.id.add_til_username);
        tilname = dialog.findViewById(R.id.add_til_name);
        tilpass = dialog.findViewById(R.id.add_til_pass);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);


        dao = new ThuThuDAO(getActivity());

        if (a==-1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                ThuThu thu = new ThuThu();
                @Override
                public void onClick(View view) {
                    validate();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).maTT.equals(txtnameuser.getText().toString())){
                            tilusername.setError("Mã Thủ Thư đã tồn tại");
                            temp++;
                            break;
                        }
                    }
                    if (temp==0){
                        thu.maTT = txtnameuser.getText().toString();
                        thu.hoTen = txtname.getText().toString();
                        thu.matKhau = txtpass.getText().toString();
                        if (dao.insert(thu)>0){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }else{
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        temp=0;
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
        }else{
            thuThu = dao.getAll().get(a);

            TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtile);
            tvTile.setText("SỬA/XOÁ THỦ THƯ");
            btnadd.setText("Sửa");
            btncancel.setText("Xoá");

            txtpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            tilpass.setPasswordVisibilityToggleEnabled(true);

            txtnameuser.setText(thuThu.maTT);
            txtnameuser.setEnabled(false);
            txtname.setText(thuThu.hoTen);
            txtpass.setText(thuThu.matKhau);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp==0){
                        thuThu = new ThuThu();
                        thuThu.maTT = txtnameuser.getText().toString();
                        thuThu.hoTen = txtname.getText().toString();
                        thuThu.matKhau = txtpass.getText().toString();
                        if (dao.update(thuThu)<0){
                            Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }
                    }else {
                        temp=0;
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phieuMuonList = phieuMuonDAO.getAll();
                    for (int i = 0; i < phieuMuonList.size(); i++) {
                        if (phieuMuonList.get(i).maTT.equals(thuThu.maTT)){
                            temp++;
                            Toast.makeText(getActivity(), "Không thể xoá thủ thư có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (temp==0){
                        if (dao.delete(thuThu.maTT)<0){
                            Toast.makeText(getActivity(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }
                    }
                }
            });
        }

        dialog.show();
    }

    private void validate(){
        if(txtnameuser.getText().length()==0){
            tilusername.setError("Mã Thủ Thư không được để trống");
            temp++;
        }else{
            tilusername.setError("");
        }
        if(txtname.getText().length()==0){
            tilname.setError("Tên Thủ Thư không được để trống");
            temp++;
        }else{
            tilname.setError("");
        }
        if(txtpass.getText().length()==0){
            tilpass.setError("Mật khẩu không được để trống");
            temp++;
        }else{
            tilpass.setError("");
        }
    }


@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}