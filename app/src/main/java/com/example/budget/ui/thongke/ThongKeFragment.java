package com.example.budget.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.budget.R;
import com.example.budget.adapter.ThongKeLoaiThuRecyclerViewAdapter;
import com.example.budget.entity.ThongKeLoaiThu;

import java.util.List;

public class ThongKeFragment extends Fragment {
    private  ThongKeViewModel mThongKeViewModel;
    private EditText mEtTongThu;
    private EditText mEtTongChi;
    private RecyclerView rvThongKeLoaiThu;
    private ThongKeLoaiThuRecyclerViewAdapter mThongKeLoaiThuAdapter; // hien thi

    public ThongKeFragment() {

    }

    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);
        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongThu.setText("" +  tong);
            }
        });

        mThongKeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongChi.setText("" +  tong);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        mEtTongThu = view.findViewById(R.id.etTongThu);
        mEtTongChi = view.findViewById(R.id.etTongChi);

        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeLoaiThu);
        mThongKeLoaiThuAdapter = new ThongKeLoaiThuRecyclerViewAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity())); // thiet lap layout
        rvThongKeLoaiThu.setAdapter(mThongKeLoaiThuAdapter); // thiet lap adapter
        mThongKeViewModel.getmThongKeLoaiThu().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                mThongKeLoaiThuAdapter.setList(thongKeLoaiThus);
            }
        });

        return view;
    }
}