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

    EditText txtmaloai, txtnsx, txttenloai;
    TextInputLayout tilmaloai, tilnsx, tiltenloai;

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
        dialog.setContentView(R.layout.dialog_loaisach);

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

        TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtile1);

        txtmaloai = dialog.findViewById(R.id.item_txtmaloai);
        txtnsx = dialog.findViewById(R.id.item_txttenloai);
        txttenloai = dialog.findViewById(R.id.item_txttenloai);

        tilmaloai = dialog.findViewById(R.id.add_til_maloai);
        tilnsx = dialog.findViewById(R.id.add_til_nsx);
        tiltenloai = dialog.findViewById(R.id.add_til_tenloai);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);

        dao = new LoaiSachDAO(getActivity());

        if (a==-1){
            tvTile.setText("TH??M LO???I S??CH");

            tilmaloai.setHint("M?? Lo???i S??ch");
            tilnsx.setHint("Nh?? S???n Xu???t");
            tiltenloai.setHint("T??n Lo???i");

            txtmaloai.setEnabled(false);

            if (list.size()==0){
                txtmaloai.setText("1");
            }else {
                loaiSach = dao.getAll().get(list.size() - 1);
                txtmaloai.setText(String.valueOf(loaiSach.maLoai + 1));
            }

            btnadd.setOnClickListener(new View.OnClickListener() {
                LoaiSach loaiSach = new LoaiSach();
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp==0){
                        loaiSach.nhaSX = txtnsx.getText().toString();
                        loaiSach.tenLoai = txttenloai.getText().toString();
                        if (dao.insert(loaiSach)>0){
                            Toast.makeText(getActivity(), "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }else{
                            Toast.makeText(getActivity(), "Th??m th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        temp=0;
                    }

                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Hu??? th??m", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else{
            tvTile.setText("S???a/X??a Lo???i S??ch");

            tilmaloai.setHint("M?? Lo???i S??ch");
            tilnsx.setHint("Nh?? S???n Xu???t Lo???i S??ch");
            tiltenloai.setHint("T??n Lo???i S??ch");

            btnadd.setText("S???a");
            btncancel.setText("Xo??");

            loaiSach = list.get(a);

            txtmaloai.setText(String.valueOf(loaiSach.maLoai));
            txtmaloai.setEnabled(false);
            txtnsx.setText(loaiSach.nhaSX);
            txttenloai.setText(loaiSach.tenLoai);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp==0){
                        loaiSach = new LoaiSach();
                        loaiSach.maLoai = Integer.parseInt(txtmaloai.getText().toString());
                        loaiSach.nhaSX = txtnsx.getText().toString();
                        loaiSach.tenLoai = txttenloai.getText().toString();
                        if (dao.update(loaiSach)<0){
                            Toast.makeText(getActivity(), "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Kh??ng th??? xo?? lo???i s??ch c?? trong s??ch", Toast.LENGTH_SHORT).show();
                            temp++;
                            break;
                        }
                    }
                    if (temp==0){
                        if (dao.delete(String.valueOf(loaiSach.maLoai))<0){
                            Toast.makeText(getActivity(), "Xo?? th???t b???i", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Xo?? th??nh c??ng", Toast.LENGTH_SHORT).show();
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
        if(txtnsx.getText().length()==0){
            tilnsx.setError("Nh?? S???n Xu???t kh??ng ???????c ????? tr???ng");
            temp++;
        }else{
            tilnsx.setError("");
        }
        if(txttenloai.getText().length()==0){
            tiltenloai.setError("T??n Lo???i s??ch kh??ng ???????c ????? tr???ng");
            temp++;
        }else{
            tiltenloai.setError("");
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
        searchView.setQueryHint("Nh???p t??m ki???m");

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