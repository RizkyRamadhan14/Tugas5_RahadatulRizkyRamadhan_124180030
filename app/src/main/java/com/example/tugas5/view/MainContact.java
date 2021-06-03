package com.example.tugas5.view;

import android.view.View;

import com.example.tugas5.entity.AppDatabase;
import com.example.tugas5.entity.DataBelanja;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataBelanja> list);
        void editData(DataBelanja item);
        void deleteData(DataBelanja item);
    }

    interface presenter{
        void insertData(String ecommerce, String barang, int harga, AppDatabase appDatabase);
        void readData(AppDatabase database);
        void editData(String ecommerce, String barang, int harga, int id, AppDatabase appDatabase);
        void deleteData(DataBelanja dataBelanja, AppDatabase database);
    }
}
