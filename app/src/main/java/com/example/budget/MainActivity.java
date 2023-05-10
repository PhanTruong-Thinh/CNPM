package com.example.budget;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.budget.dialog.ChiDialog;
import com.example.budget.dialog.LoaiChiDialog;
import com.example.budget.dialog.LoaiThuDialog;
import com.example.budget.dialog.ThuDialog;
import com.example.budget.entity.Thu;
import com.example.budget.ui.chi.KhoanChiFragment;
import com.example.budget.ui.chi.LoaiChiFragment;
import com.example.budget.ui.thu.KhoanThuFragment;
import com.example.budget.ui.thu.LoaiThuFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budget.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
// 1 mở ứng dung
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        final  MainActivity currentContext = this;
        // 2 Hiển thị menu
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            // 3. Chon vào khoản thu
            public void onClick(View view) {
                // Dùng getSupportFragmentManager để gọi đến ThuFragment
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size()-1);
                if(fragment instanceof LoaiThuFragment){
                    LoaiThuDialog dialog = new LoaiThuDialog(currentContext, (LoaiThuFragment) fragment);
                    dialog.show();
                }else if (fragment instanceof KhoanThuFragment) {
                    ThuDialog dialog = new ThuDialog(currentContext, (KhoanThuFragment) fragment);
                    dialog.show();
                }
                if(fragment instanceof LoaiChiFragment){
                    LoaiChiDialog dialog = new LoaiChiDialog(currentContext, (LoaiChiFragment) fragment);
                    dialog.show();
                }else if (fragment instanceof KhoanChiFragment) {
                    ChiDialog dialog = new ChiDialog(currentContext, (KhoanChiFragment) fragment);
                    dialog.show();
                }
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //  Chức năng thoát
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.nav_thoat) {
                    finish();
                }
            }
        });
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}