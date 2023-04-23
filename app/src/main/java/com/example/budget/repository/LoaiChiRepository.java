package com.example.budget.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budget.dao.AppDatabase;

import com.example.budget.dao.LoaiChiDao;
import com.example.budget.dao.LoaiThuDao;
import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;


import java.util.List;

public class LoaiChiRepository {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;


    public LoaiChiRepository(Application application) {
        this.mLoaiChiDao = AppDatabase.getDatabase(application).loaiChiDao();
        mAllLoaiChi = mLoaiChiDao.findAll();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }

    //. 1
    public void insert(LoaiChi loaiChi) {
        new InsertAsynTask(mLoaiChiDao).execute(loaiChi);
    }

    public void delete(LoaiChi loaiChi) {
        new LoaiChiRepository.DeleteAsynTask(mLoaiChiDao).execute(loaiChi);
    }

    public void update(LoaiChi loaiChi) {
        new LoaiChiRepository.UpdateAsynTask(mLoaiChiDao).execute(loaiChi);
    }

    class UpdateAsynTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDao mLoaiChiDao;
        // .2
        public UpdateAsynTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.update(loaiChis[0]);
            return null;
        }
    }

    // dinh nghia lop thuc hien bat dong bo
    // thuc hien phuong thuc insert loaithu vao csdl
    class InsertAsynTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDao mLoaiChiDao;

        // .2
        public InsertAsynTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }

    class DeleteAsynTask extends AsyncTask<LoaiChi, Void, Void> {
        private LoaiChiDao mLoaiChiDao;
        // .2
        public DeleteAsynTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }

}

