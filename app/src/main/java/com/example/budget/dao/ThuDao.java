package com.example.budget.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budget.entity.ThongKeLoaiThu;
import com.example.budget.entity.Thu;

import java.util.List;

@Dao
public interface ThuDao {
    @Query("SELECT * FROM thu")
    LiveData<List<Thu>> findAll();

    @Query("SELECT sum(sotien) FROM thu")
    LiveData<Float> sumTongThu();

    // Danh sach Loai thu theo tong tien loai thu
    @Query("SELECT b.lid, b.ten, sum(a.sotien) as tong FROM thu a INNER JOIN loaithu b on a.ltid = b.lid"
            + " GROUP BY b.lid, b.ten")
    LiveData<List<ThongKeLoaiThu>> sumByLoaiThu();

    @Insert
    void insert(Thu thu);

    @Update
    void update(Thu thu);

    @Delete
    void delete(Thu thu);
}
