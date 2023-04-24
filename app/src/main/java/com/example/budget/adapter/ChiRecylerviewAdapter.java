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
import com.example.budget.entity.Chi;

import java.util.List;

public class ChiRecylerviewAdapter extends RecyclerView.Adapter<ChiRecylerviewAdapter.ChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Chi> mList;

    public static ItemClickListener itemEditClickListener;

    public ChiRecylerviewAdapter(Context context) {
        mlayoutInflater = LayoutInflater.from(context);
    }

    public static void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ChiRecylerviewAdapter.itemEditClickListener = itemEditClickListener;
    }



    @NonNull
    // tao view tu layout recylerview_chi_item
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recylerview_chi_item, parent, false);
        // tao ra ChiViewHolder va truyen vao view
        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mList != null) {
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText("" +mList.get(position).sotien + " Đồng");
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

    public Chi getItem(int position) {
        if(mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvAmount;
        public ImageView ivEdit;
        public CardView cv;
        public int position;
        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
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
