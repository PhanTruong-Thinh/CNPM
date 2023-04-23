package com.example.budget.ui.chi;

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
import com.example.budget.adapter.LoaiChiRecylerviewAdapter;
import com.example.budget.dialog.LoaiChiDialog;
import com.example.budget.entity.LoaiChi;



import java.util.List;

public class LoaiChiFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiChiRecylerviewAdapter mAdapter;

    private LoaiChiViewModel mViewModel;

    public static com.example.budget.ui.chi.LoaiChiFragment newInstance() {
        return new com.example.budget.ui.chi.LoaiChiFragment();
    }


    public LoaiChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new LoaiChiRecylerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        LoaiChiFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(), currentFragment, loaiChi);
                dialog.show();
            }
        });
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
                LoaiChi lc = mAdapter.getItem(position);

                Toast.makeText(getActivity(),"Loai chi da duoc xoa", Toast.LENGTH_SHORT).show();
                mViewModel.delete(lc);
            }
        });
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);
        // khi du lieu co thay doi, cap nhat adapter
        mViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
    }

}