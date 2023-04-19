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
        new InsertAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void delete(LoaiThu loaiThu) {
        new DeleteAsynTcask(mLoaiThuDao).execute(loaiThu);
    }
    public void update(LoaiThu loaiThu) {
        new UpdateAsyncTask(mLoaiThuDao).execute(loaiThu);
    }

    class UpdateAsyncTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDao mLoaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDao mLoaiThuDao;
        // .2
        public InsertAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }

    class DeleteAsynTcask extends AsyncTask<LoaiThu, Void, Void> {
        private LoaiThuDao mLoaiThuDao;
        // .2
        public DeleteAsynTcask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        // .3
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mLoaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }

}

