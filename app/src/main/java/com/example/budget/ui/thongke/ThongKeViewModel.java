package com.example.budget.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budget.repository.ChiRepository;
import com.example.budget.repository.ThuRepository;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<Float> mTongThu;

    private ChiRepository mChiRepository;
    private LiveData<Float> mTongChi;

    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mThuRepository = new ThuRepository(application);
        mTongThu = mThuRepository.sumTongThu();
        mChiRepository = new ChiRepository(application);
        mTongChi = mChiRepository.sumTongChi();

    }

    public LiveData<Float> getTongThu() {
        return mTongThu;
    }

    public LiveData<Float> getTongChi() {
        return mTongChi;
    }
}
