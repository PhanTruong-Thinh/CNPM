package com.example.budget.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budget.dao.AppDatabase;
import com.example.budget.dao.ThuDao;
import com.example.budget.entity.Thu;

import java.util.List;

public class ThuRepository {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;


    public ThuRepository(Application application) {
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu = mThuDao.findAll();
    }

    // dinh nghia getter danh sach cac thu
    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

    //. 1
    public void insert(Thu thu) {
        new InsertAsyncTask(mThuDao).execute(thu);
    }

    public void delete(Thu thu) {
        new DeleteAsynTcask(mThuDao).execute(thu);
    }

    public void update(Thu thu) {
        new UpdateAsyncTask(mThuDao).execute(thu);
    }


    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;
        public UpdateAsyncTask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.update(thus[0]);
            return null;
        }
    }

    class InsertAsyncTask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;
        // .2
        public InsertAsyncTask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }
        // .3
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.insert(thus[0]);
            return null;
        }
    }

    class DeleteAsynTcask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;
        // .2
        public DeleteAsynTcask(ThuDao thuDao) {
            this.mThuDao = thuDao;
        }
        // .3
        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.delete(thus[0]);
            return null;
        }
    }

}

