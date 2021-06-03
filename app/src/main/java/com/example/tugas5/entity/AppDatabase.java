package com.example.tugas5.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataBelanja.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataBelanjaDBO dbo();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbbelanjaonline").allowMainThreadQueries().build();

        }return appDatabase;
    }
}
