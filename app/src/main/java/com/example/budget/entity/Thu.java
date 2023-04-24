package com.example.budget.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Thu {
    // truong id
    @PrimaryKey(autoGenerate = true)
    public int tid;
    // loai thu id
    @ColumnInfo(name = "ltid")
    public int ltid;

    @ColumnInfo(name = "ten")
    public String ten;

    @ColumnInfo(name = "sotien")
    public float sotien;

    @ColumnInfo(name = "ghichu")
    public String ghichu;


}
