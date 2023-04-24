package com.example.budget.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chi {
    // truong id
    @PrimaryKey(autoGenerate = true)
    public int tid;
    // loai chi id
    @ColumnInfo(name = "lcid")
    public int lcid;

    @ColumnInfo(name = "ten")
    public String ten;

    @ColumnInfo(name = "sotien")
    public float sotien;

    @ColumnInfo(name = "ghichu")
    public String ghichu;


}
