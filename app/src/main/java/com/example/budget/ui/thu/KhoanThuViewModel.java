package com.example.budget.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.budget.entity.Thu;
import com.example.budget.repository.ThuRepository;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<List<Thu>> mAllThu;

    public KhoanThuViewModel(@NonNull Application application) {
        super(application);
        // tao ra doi tuong mThuRepository
        mThuRepository = new ThuRepository(application);
        // dung doi tuong de thuc hien phuong thuc getAllThu
        mAllThu = mThuRepository.getAllThu();
    }
    // tra ve danh sach loai thu
    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }
    // chen loai thu moi vao csdl
    public  void insert(Thu thu) {
        mThuRepository.insert(thu);
    }
    public void delete(Thu thu) {
        mThuRepository.delete(thu);
    }
    public void update(Thu thu) {
        mThuRepository.update(thu);
    }
}