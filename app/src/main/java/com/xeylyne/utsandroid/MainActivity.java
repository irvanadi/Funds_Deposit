package com.xeylyne.utsandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtTanggal;
    Spinner spnJenis;
    EditText edUraian, edNominal;
    TabunganAdapter tabunganAdapter;
    String SpinnerJenis;
    DateFormat dateFormat;
    Date date;
    int NominalDebit, NominalKredit ;

    ArrayList<Tabungan> results = new ArrayList<>();
    ArrayList<Tabungan> reports = new ArrayList<>();
    TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recTabungan);
        spnJenis = findViewById(R.id.spnJenis);
        edUraian = findViewById(R.id.edDescription);
        edNominal = findViewById(R.id.edNominal);
        textClock = findViewById(R.id.txtInputTanggal);

        date = new Date();
        dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm:ss");
        tabunganAdapter = new TabunganAdapter(MainActivity.this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tabunganAdapter);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                txtTanggal.setText(dateFormat.format(date));
//            }
//        },1000);
//

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });

        findViewById(R.id.btnReport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Report();
            }
        });
    }

    public void load(){
        SpinnerJenis = spnJenis.getSelectedItem().toString();
        Tabungan tabungan = new Tabungan();
        tabungan.setJenis(SpinnerJenis);
        tabungan.setNominal(Integer.parseInt(edNominal.getText().toString()));
        tabungan.setUraian(edUraian.getText().toString());
        tabungan.setTanggal(textClock.getText().toString());
        results.add(tabungan);
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
