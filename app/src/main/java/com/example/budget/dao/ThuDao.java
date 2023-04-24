package com.example.budget.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budget.entity.Thu;

import java.util.List;

@Dao
public interface ThuDao {
    @Query("SELECT * FROM thu")
    LiveData<List<Thu>> findAll();

    @Insert
    void insert(Thu thu);

    @Update
    void update(Thu thu);

    @Delete
    void delete(Thu thu);
}
