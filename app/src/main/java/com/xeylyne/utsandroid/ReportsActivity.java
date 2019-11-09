package com.xeylyne.utsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReportsActivity extends AppCompatActivity {

    TextView totaltrans, debit, kredit, saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Intent intent = getIntent();
        this.setTitle("Your Report");
        totaltrans = findViewById(R.id.txtInputTrans);
        debit = findViewById(R.id.txtInputDebit);
        kredit = findViewById(R.id.txtInputKredit);
        saldo = findViewById(R.id.txtSaldoAkhir);

        totaltrans.setText(String.valueOf(intent.getIntExtra("trans",0)));
        debit.setText(String.valueOf(intent.getIntExtra("debit",0)));
        kredit.setText(String.valueOf(intent.getIntExtra("kredit",0)));
        saldo.setText(String.valueOf(intent.getIntExtra("total",0)));
    }
}
