package com.example.NguyenCuong.main.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.DAO.ThuThuDAO;
import com.example.NguyenCuong.MainActivity;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.databinding.FragmentMatkhauBinding;
import com.example.NguyenCuong.model.ThuThu;
import com.google.android.material.textfield.TextInputLayout;


public class matkhau extends Fragment {

private FragmentMatkhauBinding binding;

    EditText oldpass,newpass,newpasscheck;
    Button btnsave,btncancel;
    ThuThuDAO dao;

    TextInputLayout tilOldpass,tilNewpass,tilNewpasscheck;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentMatkhauBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        oldpass = root.findViewById(R.id.pass_oldpass);
        newpass = root.findViewById(R.id.pass_newpass);
        newpasscheck = root.findViewById(R.id.pass_newpasscheck);

        tilNewpass = root.findViewById(R.id.pass_tilnewpass);
        tilNewpasscheck = root.findViewById(R.id.pass_tilnewpasscheck);
        tilOldpass = root.findViewById(R.id.pass_tilOldpass);

        btnsave = root.findViewById(R.id.pass_btnsave);
        btncancel = root.findViewById(R.id.pass_btncancel);

        dao = new ThuThuDAO(getActivity());

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setText("");
                newpass.setText("");
                newpasscheck.setText("");


                tilOldpass.setError("");
                tilNewpass.setError("");
                tilNewpasscheck.setError("");
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");

                if (user.length()==0){
                    Intent intent1 = getActivity().getIntent();
                    String user1 = intent1.getStringExtra("user");
                    user = user1;
                }

                if (validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = newpass.getText().toString();
                    dao.update(thuThu);
                    if (dao.update(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        oldpass.setText("");
                        newpass.setText("");
                        newpasscheck.setText("");
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    return root;
    }

    public int validate(){
        int check = 1;
        int temp=0;
        if (oldpass.getText().length()==0){
            tilOldpass.setError("Mật khẩu cũ không được để trống");
            temp++;
            check = -1;
        }else{
            tilOldpass.setError("");
        }
        if (newpass.getText().length()==0){
            tilNewpass.setError("Mật khẩu mới không được để trống");
            temp++;
            check = -1;
        }else{
            tilNewpass.setError("");
        }
        if (newpasscheck.getText().length()==0){
            tilNewpasscheck.setError("Nhập lại mật khẩu không được để trống");
            temp++;
            check = -1;
        }else{
            tilNewpasscheck.setError("");
        }
        if (temp==0){
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String oldPasscheck = pref.getString("PASSWORD","");
            if (oldPasscheck.length()==0){
                Intent intent = getActivity().getIntent();
                String passs = intent.getStringExtra("pass");
                oldPasscheck = passs;
            }
            String newPass = newpass.getText().toString();
            String  reNewpass = newpasscheck.getText().toString();

            if (!oldPasscheck.equals(oldpass.getText().toString())){
                tilOldpass.setError("Mật khẩu cũ sai");
                check=-1;
            }

            if (!newPass.equals(reNewpass)){
                tilNewpasscheck.setError("Mật khẩu không trùng khớp");
                check=-1;
            }
        }else{
            temp=0;
        }
        return check;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}