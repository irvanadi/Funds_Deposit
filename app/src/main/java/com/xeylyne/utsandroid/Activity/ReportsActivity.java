package com.xeylyne.utsandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.xeylyne.utsandroid.R;

public class ReportsActivity extends AppCompatActivity {

    TextView totaltrans, debit, kredit, saldo;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();

        if (i == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Intent intent = getIntent();
        this.setTitle("Report Transaksi");
        totaltrans = findViewById(R.id.txtInputTrans);
        debit = findViewById(R.id.txtInputDebit);
        kredit = findViewById(R.id.txtInputKredit);
        saldo = findViewById(R.id.txtSaldoAkhir);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totaltrans.setText(String.valueOf(intent.getIntExtra("trans",0)));
        debit.setText("Rp. " + String.valueOf(intent.getIntExtra("debit",0)));
        kredit.setText("Rp. " + String.valueOf(intent.getIntExtra("kredit",0)));
        saldo.setText("Rp. " + String.valueOf(intent.getIntExtra("total",0)));


    }
}
