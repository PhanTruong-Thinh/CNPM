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
import com.example.budget.entity.ThongKeLoaiThu;

import java.util.List;

public class ThongKeLoaiThuRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeLoaiThuRecyclerViewAdapter.ThongKeLoaiThuViewHolder>{
    private LayoutInflater mLayoutInflater; // tao layout cho adapter
    private List<ThongKeLoaiThu> mList; // danh sach chua thong tin thong ke loai thu

    public ThongKeLoaiThuRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeLoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_thongke_loaithu, parent, false);
        return new ThongKeLoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiThuViewHolder holder, int position) {
        // kiem tra thong tin cua List, neu ton tai du lieu thi thiet lap thong tin hien thi
        if (mList != null) {
            holder.tvTenLoaiThu.setText(mList.get(position).ten);
            holder.etTongLoaiThu.setText("" + mList.get(position).tong);
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
    public void setList(List<ThongKeLoaiThu> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenLoaiThu; // ten loai thu
        public EditText etTongLoaiThu; // tong thu cua loai thu

        public ThongKeLoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);

            // Tim kiem thong tin trong layout
            tvTenLoaiThu = itemView.findViewById(R.id.tvTenLoaiThu);
            etTongLoaiThu = itemView.findViewById(R.id.etTongLoaiThu);
        }
    }
}
