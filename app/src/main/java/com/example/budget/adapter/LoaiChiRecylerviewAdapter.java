package com.example.budget.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget.R;
import com.example.budget.entity.LoaiChi;


import java.util.List;

public class LoaiChiRecylerviewAdapter extends RecyclerView.Adapter<LoaiChiRecylerviewAdapter.LoaiChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<LoaiChi> mList;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiChiRecylerviewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemClickListener) {
        LoaiChiRecylerviewAdapter.itemEditClickListener = itemClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewListener) {
        LoaiChiRecylerviewAdapter.itemViewClickListener = itemViewListener;
    }

    @NonNull
    // tao view tu layout recylerview_loai_chi_item
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recylerview_loai_chi_item, parent, false);
        // tao ra LoaiChiViewHolder va truyen vao view
        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mList != null) {
            holder.tvName.setText(mList.get(position).ten);
            holder.position = position;
        }
    }

    @Override
    // tra vo so luong phan tu trong danh sach
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }

    public LoaiChi getItem(int position) {
        if(mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemViewClickListener != null) {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemEditClickListener != null) {
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

}
