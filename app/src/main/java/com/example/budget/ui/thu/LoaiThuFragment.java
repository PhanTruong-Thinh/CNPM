package com.example.budget.ui.thu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.budget.R;
import com.example.budget.adapter.ItemClickListener;
import com.example.budget.adapter.LoaiThuRecylerviewAdapter;
import com.example.budget.dialog.LoaiThuDialog;
import com.example.budget.entity.LoaiThu;

import java.util.List;

public class LoaiThuFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiThuRecylerviewAdapter mAdapter;

    private LoaiThuViewModel mViewModel;

    public static LoaiThuFragment newInstance() {
        return new LoaiThuFragment();
    }

    // 4.1 Yêu cầu hiển thị Loại khoản thu

    public LoaiThuViewModel getViewModel() {
        return mViewModel;
    }

    // 4.6 Hiển thị loại khoản thu
    // 6.5
    // 9.5
    // 10.5
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new LoaiThuRecylerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        LoaiThuFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            // 5.1 Yêu cầu hiển thị hộp thoại người dùng
            // 8.1
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position);
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(), currentFragment, loaiThu);
                dialog.show();
            }
        });
        // 10. kéo sang trái || phải
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                LoaiThu lt = mAdapter.getItem(position);
                // 10.1 gọi đến delete
                mViewModel.delete(lt);
                // 11 hiện thông báo
                Toast.makeText(getActivity(),"Loai thu da duoc xoa", Toast.LENGTH_SHORT).show();
            }
        });
        helper.attachToRecyclerView(mRv);
    }
// 4.2 lấy danh sách các loại khoản thu
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
        // khi du lieu co thay doi, cap nhat adapter
        // 4,3 truy vấn dữ liệu
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
    }

}