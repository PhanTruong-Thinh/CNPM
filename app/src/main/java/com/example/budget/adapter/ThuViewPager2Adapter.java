package com.example.budget.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.budget.ui.thu.KhoanThuFragment;
import com.example.budget.ui.thu.LoaiThuFragment;

public class ThuViewPager2Adapter extends FragmentStateAdapter {
    public ThuViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    // 3.1
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0) {
            // 3.2 chuyển đến khoan thu
            fragment = KhoanThuFragment.newInstance();
        }else {
            // 4.1
            fragment = LoaiThuFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
