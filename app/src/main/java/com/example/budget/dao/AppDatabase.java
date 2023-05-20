package com.example.budget.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budget.entity.Chi;
import com.example.budget.entity.Thu;
import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;

@Database(entities = {LoaiThu.class, LoaiChi.class, Thu.class, Chi.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract LoaiChiDao loaiChiDao();
    public abstract ThuDao thuDao();
    public abstract ChiDao chiDao();

    public static AppDatabase INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };
// Tạo cơ sở dữ liệu nếu chưa có bằng Room
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
        private ThuDao thuDao;
        private ChiDao chiDao;

        //6.4
        //9.4
        //10.4
        public PopulateData(AppDatabase db) {
            loaiThuDao = db.loaiThuDao();
            loaiChiDao = db.loaiChiDao();
            thuDao = db.thuDao();
            chiDao = db.chiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaiThus = new String[]{"Luong", "Thuong", "Co phieu"};
            for (String it : loaiThus) {
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);
            }
            String[] loaiChis = new String[]{"Luong", "Tien Nha", "Co phieu"};
            for (String it : loaiChis) {
                LoaiChi lc = new LoaiChi();
                lc.ten = it;
                loaiChiDao.insert(lc);
            }
            Thu thu = new Thu();
            thu.ten = "Lương tháng 1";
            thu.sotien = 7800000;
            thu.ltid = 1;
            thu.ghichu = "Tiền lương công ty A";
            thuDao.insert(thu);


            Chi chi = new Chi();
            chi.ten = "Tiền Nhà";
            chi.sotien = 5000000;
            chi.lcid = 1;
            chi.ghichu = "Tiền Thuê Nhà";
            chiDao.insert(chi);


            Log.d("BudgetPro: ", "insert data");
            return null;
        }
    }
}
