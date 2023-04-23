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
import com.example.budget.entity.LoaiThu;

import java.util.List;

public class LoaiThuRecylerviewAdapter extends RecyclerView.Adapter<LoaiThuRecylerviewAdapter.LoaiThuViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<LoaiThu> mList;

    public static ItemClickListener itemEditClickListener;

    public LoaiThuRecylerviewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public static void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiThuRecylerviewAdapter.itemEditClickListener = itemEditClickListener;
    }



    @NonNull
    // tao view tu layout recylerview_loai_thu_item
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recylerview_loai_thu_item, parent, false);
        // tao ra LoaiThuViewHolder va truyen vao view
        return new LoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

    public LoaiThu getItem(int position) {
        if(mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<LoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit;
        public CardView cv;
        public int position;
        public LoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;

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
