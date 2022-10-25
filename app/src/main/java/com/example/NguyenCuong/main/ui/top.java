package com.example.NguyenCuong.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.NguyenCuong.DAO.ThongKeDAO;
import com.example.NguyenCuong.R;
import com.example.NguyenCuong.adapter.AdapterTop;
import com.example.NguyenCuong.databinding.FragmentQlloaisachBinding;
import com.example.NguyenCuong.databinding.FragmentTopBinding;
import com.example.NguyenCuong.model.Top;

import java.util.List;


public class top extends Fragment {

private FragmentTopBinding binding;

    ListView listView;

    ThongKeDAO dao;
    List<Top> list;
    AdapterTop adapterTop;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentTopBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    listView = root.findViewById(R.id.top_listview);

    loadTable();

    return root;
    }

    private void loadTable(){
        dao = new ThongKeDAO(getActivity());
        list = dao.getTop();
        adapterTop = new AdapterTop(getActivity(), R.layout.item_lv_top,list);
        listView.setAdapter(adapterTop);
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}