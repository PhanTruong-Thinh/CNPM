package com.example.budget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget.R;
import com.example.budget.entity.ThongKeLoaiChi;

import java.util.List;

public class ThongKeLoaiChiRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeLoaiChiRecyclerViewAdapter.ThongKeLoaiChiViewHolder>{
    private LayoutInflater mLayoutInflater; // tao layout cho adapter
    private List<ThongKeLoaiChi> mList; // danh sach chua thong tin thong ke loai chi

    public ThongKeLoaiChiRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeLoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_thongke_loaichi, parent, false);
        return new ThongKeLoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiChiViewHolder holder, int position) {
        // kiem tra thong tin cua List, neu ton tai du lieu thi thiet lap thong tin hien thi
        if (mList != null) {
            holder.tvTenLoaiChi.setText(mList.get(position).ten);
            holder.etTongLoaiChi.setText("" + mList.get(position).tong);
        }
    }

    // Tra ve tong so du lieu trong List
    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    // Cap nhat thong tin trong List
    public void setList(List<ThongKeLoaiChi> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenLoaiChi; // ten loai chi
        public EditText etTongLoaiChi; // tong chi cua loai chi

        public ThongKeLoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);

            // Tim kiem thong tin trong layout
            tvTenLoaiChi = itemView.findViewById(R.id.tvTenLoaiChi);
            etTongLoaiChi = itemView.findViewById(R.id.etTongLoaiChi);
        }
    }
}
