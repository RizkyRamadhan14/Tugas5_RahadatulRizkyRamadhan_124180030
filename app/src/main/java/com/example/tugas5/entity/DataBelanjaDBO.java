package com.example.tugas5.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataBelanjaDBO {
    @Insert
    Long insertData(DataBelanja dataBelanja);

    @Query("Select * from belanjaonline_db")
    List<DataBelanja> getData();

    @Update
    int updateData(DataBelanja item);

    @Delete
    void deleteData(DataBelanja item);

}
