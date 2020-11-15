package com.example.gray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {

    EditText text_pNumber, text_message;
    Button mlocate,mupload;
    Button mimagepush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        text_message = (EditText) findViewById(R.id.txt_message);
        text_pNumber = (EditText) findViewById(R.id.txt_phone_number);

    }




    public void sendbtn(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void MyMessage() {
        String phNumber = text_pNumber.getText().toString().trim();
        String Message = text_message.getText().toString().trim();


        if (text_pNumber.getText().toString().equals("") || !text_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phNumber, null, Message, null, null);

            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Please Enter Number or Message", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



        switch (requestCode){
            case 0 :

                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else
                {
                    Toast.makeText(this, "You Dont have Required Pernission", Toast.LENGTH_SHORT).show();
                }
        }
    }

}

