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
    // 4.4 lay danh sach loai khoan thu
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    // 6.2
    // 9.2
    // 10.2
    public void insert(LoaiThu loaiThu) {
        new InsertAsyncTask(mLoaiThuDao).execute(loaiThu);
    }
    public void delete(LoaiThu loaiThu) {
        new DeleteAsynTcask(mLoaiThuDao).execute(loaiThu);
    }
    public void update(LoaiThu loaiThu) {
        new UpdateAsyncTask(mLoaiThuDao).execute(loaiThu);
    }

    // 9.3
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
    // 6.3
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

    // 10.3
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

