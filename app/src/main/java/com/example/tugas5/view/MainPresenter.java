package com.example.tugas5.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tugas5.entity.AppDatabase;
import com.example.tugas5.entity.DataBelanja;

import java.util.List;

public class MainPresenter implements MainContact.presenter {

    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataBelanja dataBelanja;

        public InsertData(AppDatabase appDatabase, DataBelanja dataBelanja){
            this.appDatabase = appDatabase;
            this.dataBelanja = dataBelanja;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dbo().insertData(dataBelanja);
        }

        protected void onPostExecute(long along){
            super.onPostExecute(along);
            view.successAdd();

        }
    }

    @Override
    public void insertData(String ecommerce, String barang, int harga, AppDatabase appDatabase) {
        final DataBelanja dataBelanja = new DataBelanja();
        dataBelanja.setEcommerce(ecommerce);
        dataBelanja.setBarang(barang);
        dataBelanja.setHarga(harga);
        new InsertData(appDatabase, dataBelanja).execute();

    }

    @Override
    public void readData(AppDatabase database) {
        List<DataBelanja> list;
        list = database.dbo().getData();
        view.getData(list);
    }


    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataBelanja dataBelanja;

        public EditData(AppDatabase appDatabase, DataBelanja dataBelanja){
            this.appDatabase = appDatabase;
            this.dataBelanja = dataBelanja;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dbo().updateData(dataBelanja);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String ecommerce, String barang, int harga, int id, AppDatabase appDatabase) {
        final DataBelanja dataBelanja = new DataBelanja();
        dataBelanja.setEcommerce(ecommerce);
        dataBelanja.setBarang(barang);
        dataBelanja.setHarga(harga);
        dataBelanja.setId(id);
        new EditData(appDatabase, dataBelanja).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataBelanja dataBelanja;

        public DeleteData(AppDatabase appDatabase, DataBelanja dataBelanja){
            this.appDatabase = appDatabase;
            this.dataBelanja = dataBelanja;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dbo().deleteData(dataBelanja);
            return null;
        }

        protected void onPostExecute(long along){
            super.onPostExecute(along);
            view.successDelete();

        }
    }
    @Override
    public void deleteData(DataBelanja dataBelanja, AppDatabase database) {
        new DeleteData(database, dataBelanja).execute();
    }
}
