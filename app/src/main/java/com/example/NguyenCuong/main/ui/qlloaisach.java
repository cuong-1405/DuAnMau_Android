package com.example.NguyenCuong.main.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.DAO.LoaiSachDAO;
import com.example.NguyenCuong.DAO.SachDAO;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.adapter.AdapterLoaiSach;
import com.example.NguyenCuong.databinding.FragmentQlloaisachBinding;
import com.example.NguyenCuong.model.LoaiSach;
import com.example.NguyenCuong.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class qlloaisach extends Fragment {

private FragmentQlloaisachBinding binding;

    FloatingActionButton fab;
    LoaiSachDAO dao;
    ListView listView;
    List<LoaiSach> list;
    LoaiSach loaiSach;
    AdapterLoaiSach adapterLoaiSach;
    int a;
    int temp=0;

    EditText txtnameuser, txtname, txtpass;
    TextInputLayout tilusername, tilname, tilpass;

    List<Sach> sachList;
    SachDAO sachDAO;

    Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQlloaisachBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        toolbar = root.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar();


        fab = root.findViewById(R.id.qlloaisach_fab);
        listView = root.findViewById(R.id.qlloaisach_listview);

        sachList = new ArrayList<>();
        sachDAO = new SachDAO(getActivity());




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
                openDialog(Gravity.CENTER);
            }
        });

        loadTable();

    return root;
    }

    public void loadTable(){
        dao = new LoaiSachDAO(getActivity());
        list = dao.getAll();
        adapterLoaiSach = new AdapterLoaiSach(getActivity(),R.layout.item_lv_addtt,list);
        listView.setAdapter(adapterLoaiSach);
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

        TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtile);

        txtnameuser = dialog.findViewById(R.id.item_txtnameuser);
        txtname = dialog.findViewById(R.id.item_txtname);
        txtpass = dialog.findViewById(R.id.item_txtpass);

        tilusername = dialog.findViewById(R.id.add_til_username);
        tilname = dialog.findViewById(R.id.add_til_name);
        tilpass = dialog.findViewById(R.id.add_til_pass);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);

        dao = new LoaiSachDAO(getActivity());

        if (a==-1){
            tvTile.setText("THÊM LOẠI SÁCH");

            tilusername.setHint("Mã Loại Sách");
            tilname.setHint("Nhà Sản Xuất");
            tilpass.setHint("Tên Loại");

            txtnameuser.setEnabled(false);

            if (list.size()==0){
                txtnameuser.setText("1");
            }else {
                loaiSach = dao.getAll().get(list.size() - 1);
                txtnameuser.setText(String.valueOf(loaiSach.maLoai + 1));
            }

            btnadd.setOnClickListener(new View.OnClickListener() {
                LoaiSach loaiSach = new LoaiSach();
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp==0){
                        loaiSach.nhaSX = txtname.getText().toString();
                        loaiSach.tenLoai = txtpass.getText().toString();
                        if (dao.insert(loaiSach)>0){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }else{
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
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
            tvTile.setText("Sửa/Xóa Loại Sách");

            tilusername.setHint("Mã Loại Sách");
            tilname.setHint("Nhà Sản Xuất Loại Sách");
            tilpass.setHint("Tên Loại Sách");

            btnadd.setText("Sửa");
            btncancel.setText("Xoá");

            loaiSach = list.get(a);

            txtnameuser.setText(String.valueOf(loaiSach.maLoai));
            txtnameuser.setEnabled(false);
            txtname.setText(loaiSach.nhaSX);
            txtpass.setText(loaiSach.tenLoai);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp==0){
                        loaiSach = new LoaiSach();
                        loaiSach.maLoai = Integer.parseInt(txtnameuser.getText().toString());
                        loaiSach.nhaSX = txtname.getText().toString();
                        loaiSach.tenLoai = txtpass.getText().toString();
                        if (dao.update(loaiSach)<0){
                            Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }
                    }else{
                        temp=0;
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sachList = sachDAO.getAll();

                    for (int i = 0; i < sachList.size(); i++) {
                        if (sachList.get(i).maLoai == loaiSach.maLoai){
                            Toast.makeText(getActivity(), "Không thể xoá loại sách có trong sách", Toast.LENGTH_SHORT).show();
                            temp++;
                            break;
                        }
                    }
                    if (temp==0){
                        if (dao.delete(String.valueOf(loaiSach.maLoai))<0){
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
        if(txtname.getText().length()==0){
            tilname.setError("Nhà Sản Xuất không được để trống");
            temp++;
        }else{
            tilname.setError("");
        }
        if(txtpass.getText().length()==0){
            tilpass.setError("Tên Loại sách không được để trống");
            temp++;
        }else{
            tilpass.setError("");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.maingiaodien, menu);

        MenuItem menuItem = menu.findItem(R.id.action_settings);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập tìm kiếm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterLoaiSach.filter(newText);
                listView.setAdapter(adapterLoaiSach);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}