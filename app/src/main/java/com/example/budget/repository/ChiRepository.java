package com.example.budget.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budget.dao.AppDatabase;
import com.example.budget.dao.ChiDao;
import com.example.budget.entity.Chi;

import java.util.List;

public class ChiRepository {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;


    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();
    }

    // dinh nghia getter danh sach cac chi
    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

    public LiveData<Float> sumTongChi() {
        return mChiDao.sumTongChi();
    }

    //. 1
    public void insert(Chi chi) {
        new InsertAsyncTask(mChiDao).execute(chi);
    }

    public void delete(Chi chi) {
        new DeleteAsynTcask(mChiDao).execute(chi);
    }

    public void update(Chi chi) {
        new UpdateAsyncTask(mChiDao).execute(chi);
    }

    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.update(chis[0]);
            return null;
        }
    }

    class InsertAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        // .2
        public InsertAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }
        // .3
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.insert(chis[0]);
            return null;
        }
    }

    class DeleteAsynTcask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        // .2
        public DeleteAsynTcask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }
        // .3
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.delete(chis[0]);
            return null;
        }
    }

}

