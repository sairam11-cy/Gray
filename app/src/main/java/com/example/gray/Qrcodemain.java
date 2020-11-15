package com.example.gray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Qrcodemain extends AppCompatActivity implements View.OnClickListener {

    Button btnTakePicture, btnScanBarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodemain);
        initViews();
    }

    private void initViews() {

        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        //btnTakePicture.setOnClickListener(Qrcodemain.this);
        btnScanBarcode.setOnClickListener(Qrcodemain.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScanBarcode:
                startActivity(new Intent(Qrcodemain.this, ScannedBarcode.class));
                break;
        }
    }
}


