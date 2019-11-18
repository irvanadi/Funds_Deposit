package com.xeylyne.utsandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;

import com.google.android.material.snackbar.Snackbar;
import com.xeylyne.utsandroid.R;
import com.xeylyne.utsandroid.Class.Tabungan;
import com.xeylyne.utsandroid.Adapter.TabunganAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Spinner spnJenis;
    EditText edUraian, edNominal;
    TabunganAdapter tabunganAdapter;
    String SpinnerJenis;
    int NominalDebit, NominalKredit ;
    ConstraintLayout constraintLayout;

    ArrayList<Tabungan> results = new ArrayList<>();
    TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Personal Accounting - 17410100123");

        recyclerView = findViewById(R.id.recTabungan);
        spnJenis = findViewById(R.id.spnJenis);
        edUraian = findViewById(R.id.edDescription);
        edNominal = findViewById(R.id.edNominal);
        textClock = findViewById(R.id.txtInputTanggal);
        constraintLayout = findViewById(R.id.ConstraintLayout);

        tabunganAdapter = new TabunganAdapter(MainActivity.this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tabunganAdapter);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
            }
        });

        findViewById(R.id.btnReport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Report();
            }
        });
    }

    public void checkInput(){
        if (edUraian.getText().length() == 0){
            Snackbar snackbar = Snackbar.make(constraintLayout, "Tolong Input Uraian Terlebih Dahulu", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else if (edNominal.getText().length() == 0){
            Snackbar snackbar = Snackbar.make(constraintLayout, "Tolong Input Nominal Terlebih Dahulu", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else {
            SaveData();
        }
    }

    public void SaveData(){
        SpinnerJenis = spnJenis.getSelectedItem().toString();
        Tabungan tabungan = new Tabungan();
        tabungan.setJenis(SpinnerJenis);
        tabungan.setNominal(Integer.parseInt(edNominal.getText().toString()));
        tabungan.setUraian(edUraian.getText().toString());
        tabungan.setTanggal(textClock.getText().toString());
        results.add(tabungan);
        Snackbar snackbar = Snackbar.make(constraintLayout, "Data Berhasil di Input", Snackbar.LENGTH_SHORT);
        snackbar.show();
        tabunganAdapter = new TabunganAdapter(MainActivity.this, results);
        recyclerView.setAdapter(tabunganAdapter);
    }

    public void Report(){
        int total = 0;
        NominalDebit = 0;
        NominalKredit = 0;
        for (int i = 0; i < results.size();i++){
            Tabungan tabungan = results.get(i);
            if(tabungan.getJenis().equals("Debit")){
                NominalDebit += tabungan.getNominal();
            } else if(tabungan.getJenis().equals("Kredit")){
                NominalKredit += tabungan.getNominal();
            }
        }

        total = NominalDebit - NominalKredit;
        Intent intent = new Intent(MainActivity.this, ReportsActivity.class);
        intent.putExtra("trans",results.size());
        intent.putExtra("debit", NominalDebit);
        intent.putExtra("kredit", NominalKredit);
        intent.putExtra("total", total);
        Log.d("TESTING_RESULT",String.valueOf(results.size()));
        Log.d("TESTING_RESULT",String.valueOf(NominalDebit));
        Log.d("TESTING_RESULT",String.valueOf(NominalKredit));
        Log.d("TESTING_RESULT",String.valueOf(total));
        startActivity(intent);
    }
}
