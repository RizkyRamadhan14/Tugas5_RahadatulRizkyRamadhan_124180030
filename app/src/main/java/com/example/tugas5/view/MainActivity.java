package com.example.tugas5.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas5.R;
import com.example.tugas5.entity.AppDatabase;
import com.example.tugas5.entity.DataBelanja;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnSubmit;
    private RecyclerView recyclerView;
    private EditText etEcommerce, etBarang, etHarga;

    private int id = 0;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = findViewById(R.id.btn_submit);
        etEcommerce = findViewById(R.id.et_ecommerce);
        etBarang = findViewById(R.id.et_barang);
        etHarga = findViewById(R.id.et_harga);
        recyclerView = findViewById(R.id.rc_utama);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter( this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this,"Berhasil ditambahkan", Toast.LENGTH_SHORT);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Berhasil dihapus", Toast.LENGTH_SHORT);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etEcommerce.setText("");
        etBarang.setText("");
        etHarga.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void getData(List<DataBelanja> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataBelanja item) {
        etEcommerce.setText(item.getEcommerce());
        etBarang.setText(item.getBarang());
        etHarga.setText(item.getHarga());
        id = item.getId();
        edit = true;
        btnSubmit.setText("Edit Data");
    }

    @Override
    public void deleteData(DataBelanja item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Apakah anda yakin ingin menghapus data?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {
        if (etEcommerce.getText().toString().equals("") ||
        etBarang.getText().toString().equals("") ||
        etHarga.getText().toString().equals("")) {
            Toast.makeText(this, "Harap Semua Data diisi!", Toast.LENGTH_SHORT).show();
        }else {
            if (edit){
                mainPresenter.insertData(etEcommerce.getText().toString(),
                        etBarang.getText().toString(),
                        Integer.parseInt(etHarga.getText().toString()), appDatabase);
            }else{
                mainPresenter.editData(etEcommerce.getText().toString(),
                        etBarang.getText().toString(),
                        Integer.parseInt(etHarga.getText().toString()), id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}