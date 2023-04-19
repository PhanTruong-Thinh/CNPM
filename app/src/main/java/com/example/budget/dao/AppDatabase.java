package com.example.budget.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;

@Database(entities = {LoaiThu.class, LoaiChi.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract LoaiChiDao loaiChiDao();

    public static AppDatabase INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "personal_db").fallbackToDestructiveMigration().addCallback(callback).build();
            }
        }
        return INSTANCE;
    }

    public static class PopulateData extends AsyncTask<Void, Void, Void> {
        private LoaiThuDao loaiThuDao;
        private LoaiChiDao loaiChiDao;

        public PopulateData(AppDatabase db) {
            loaiThuDao = db.loaiThuDao();
            loaiChiDao = db.loaiChiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaiThus = new String[]{"Luong", "Thuong", "Co phieu"};
            for (String it : loaiThus) {
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);
            }
            String[] loaiChis = new String[]{"Luong", "Thuong", "Co phieu"};
            for (String it : loaiChis) {
                LoaiChi lc = new LoaiChi();
                lc.ten = it;
                loaiChiDao.insert(lc);
            }
            return null;
        }
    }
}
