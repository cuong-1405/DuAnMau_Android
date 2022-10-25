package com.example.NguyenCuong.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.MainActivity;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.databinding.FragmentDangxuatBinding;


public class dangxuat extends Fragment {
    Toolbar toolbar;

private FragmentDangxuatBinding binding;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDangxuatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        toolbar = root.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().finish();
            }
        },1500);

    return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}