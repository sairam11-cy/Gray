package com.example.gray;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendbtn(View view) {
        startActivity(new Intent(getApplicationContext(), Message.class));
    }

    public void pushimg(View view) {
        startActivity(new Intent(getApplicationContext(), Upload.class));
    }
    public void pinlocation(View view) {
        startActivity(new Intent(getApplicationContext(), Pincurrent.class));
    }

    public void locate(View view) {
        startActivity(new Intent(getApplicationContext(),Locate.class));

    }

    public void scan(View view) {
        startActivity(new Intent(getApplicationContext(),Qrcodemain.class));
    }
}
