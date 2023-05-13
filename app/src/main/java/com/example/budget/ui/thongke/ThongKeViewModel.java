package com.example.budget.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget.entity.ThongKeLoaiThu;
import com.example.budget.repository.ChiRepository;
import com.example.budget.repository.ThuRepository;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<Float> mTongThu;
    private ChiRepository mChiRepository;
    private LiveData<Float> mTongChi;
    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus; // dinh nghia List thong ke loai thu

    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mThuRepository = new ThuRepository(application);
        mTongThu = mThuRepository.sumTongThu();
        mChiRepository = new ChiRepository(application);
        mTongChi = mChiRepository.sumTongChi();
        mThongKeLoaiThus = mThuRepository.sumByLoaiThu(); // lay thong tin tu csdl
    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }

    public LiveData<Float> getTongChi() {
        return mTongChi;
    }

    public LiveData<List<ThongKeLoaiThu>> getmThongKeLoaiThu() {
        return mThongKeLoaiThus;
    }
}
