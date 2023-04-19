package com.example.budget.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;

import java.util.List;

@Dao
public interface LoaiChiDao {
    @Query("SELECT * FROM loaichi")
    LiveData<List<LoaiChi>> findAll();

    @Insert
    void insert(LoaiChi loaiChi);

    @Update
    void update(LoaiChi loaiChi);
}
