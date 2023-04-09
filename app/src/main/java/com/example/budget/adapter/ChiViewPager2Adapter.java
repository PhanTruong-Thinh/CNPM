package com.example.budget.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.budget.ui.chi.KhoanChiFragment;
import com.example.budget.ui.chi.LoaiChiFragment;

public class ChiViewPager2Adapter extends FragmentStateAdapter {
    // dinh nghia lai contracter
    public ChiViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // tao fragment
        Fragment fragment;
        // nguoi dung chon vi tri 0 thi tao ra Khoanchifragment
        if (position == 0) {
            fragment = KhoanChiFragment.newInstance();
        }else {
            // vi tri 1 thi tao ra LoaiChiFragment
            fragment = LoaiChiFragment.newInstance();
        }
        return fragment;
    }

    @Override
    // so luong fragment hien trong viewpager2 la 2
    public int getItemCount() {
        return 2;
    }
}
