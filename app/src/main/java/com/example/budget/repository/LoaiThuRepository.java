package com.example.budget.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budget.dao.AppDatabase;
import com.example.budget.dao.LoaiThuDao;
import com.example.budget.entity.LoaiThu;

import java.util.List;

public class LoaiThuRepository {
    private LoaiThuDao mLoaiThuDao;
    private LiveData<List<LoaiThu>> mAllLoaiThu;


    public LoaiThuRepository(Application application) {
        this.mLoaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllLoaiThu = mLoaiThuDao.findAll();
    }
    // dinh nghia getter danh sach cac loai thu
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    //. 1
    public void insert(LoaiThu loaiThu) {
        new InsertAsynTask(mLoaiThuDao).execute(loaiThu);
    }
     // dinh nghia lop thuc hien bat dong bo
    // thuc hien phuong thuc insert loaithu vao csdl
    class InsertAsynTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDao mLoaiThuDao;
        // .2
        public InsertAsynTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }

}
