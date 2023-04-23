package com.example.budget.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget.entity.LoaiThu;
import com.example.budget.repository.LoaiThuRepository;

import java.util.List;


public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<List<LoaiThu>>  mAllLoaiThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        // tao ra doi tuong mLoaiThuRepository
        mLoaiThuRepository = new LoaiThuRepository(application);
        // dung doi tuong de thuc hien phuong thuc getAllLoaiThu
        mAllLoaiThu = mLoaiThuRepository.getAllLoaiThu();
    }
    // tra ve danh sach loai thu
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    // chen loai thu moi vao csdl
    public  void insert(LoaiThu loaiThu) {
        mLoaiThuRepository.insert(loaiThu);
    }
    public void delete(LoaiThu loaiThu) {
        mLoaiThuRepository.delete(loaiThu);
    }
    public void update(LoaiThu loaiThu) {
        mLoaiThuRepository.update(loaiThu);
    }
}