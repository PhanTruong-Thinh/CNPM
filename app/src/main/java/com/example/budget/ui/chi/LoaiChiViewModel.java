package com.example.budget.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;
import com.example.budget.repository.LoaiChiRepository;
import com.example.budget.repository.LoaiThuRepository;

import java.util.List;


public class LoaiChiViewModel extends AndroidViewModel {
    private LoaiChiRepository mLoaiChiRepository;
    private LiveData<List<LoaiChi>>  mAllLoaiChi;

    public LoaiChiViewModel(@NonNull Application application) {
        super(application);
        // tao ra doi tuong mLoaiThuRepository
        mLoaiChiRepository = new LoaiChiRepository(application);
        // dung doi tuong de thuc hien phuong thuc getAllLoaiThu
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi();
    }
    // tra ve danh sach loai thu
    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }
    // chen loai thu moi vao csdl
    public  void insert(LoaiChi loaiChi) {
        mLoaiChiRepository.insert(loaiChi);
    }
}