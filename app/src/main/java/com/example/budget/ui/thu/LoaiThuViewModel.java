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
        // 4.3 lấy danh sách loại khoản thu
        mAllLoaiThu = mLoaiThuRepository.getAllLoaiThu();
    }
    // 4.5 tra ve danh sach loai khoan thu
    // 6.5
    // 9.5
    // 10.5
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    // chen loai thu moi vao csdl


    // 6.2
    public  void insert(LoaiThu loaiThu) {
        mLoaiThuRepository.insert(loaiThu);
    }
    //10.2
    public void delete(LoaiThu loaiThu) {
        mLoaiThuRepository.delete(loaiThu);
    }
    // 9.2
    public void update(LoaiThu loaiThu) {
        mLoaiThuRepository.update(loaiThu);
    }
}