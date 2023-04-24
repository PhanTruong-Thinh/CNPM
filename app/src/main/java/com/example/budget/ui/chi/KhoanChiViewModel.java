package com.example.budget.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.budget.entity.Chi;
import com.example.budget.repository.ChiRepository;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {
    private ChiRepository mChiRepository;
    private LiveData<List<Chi>> mAllChi;

    public KhoanChiViewModel(@NonNull Application application) {
        super(application);
        // tao ra doi tuong mChiRepository
        mChiRepository = new ChiRepository(application);
        // dung doi tuong de chic hien phuong chic getAllChi
        mAllChi = mChiRepository.getAllChi();
    }
    // tra ve danh sach loai chi
    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }
    // chen loai chi moi vao csdl
    public  void insert(Chi chi) {
        mChiRepository.insert(chi);
    }
    public void delete(Chi chi) {
        mChiRepository.delete(chi);
    }
    public void update(Chi chi) {
        mChiRepository.update(chi);
    }
}