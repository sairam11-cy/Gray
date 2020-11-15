package com.example.gray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Locate extends AppCompatActivity {
private Button mopenmaps;
private TextView mgotohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
     mopenmaps = (Button) findViewById(R.id.locate);
     mgotohome = (TextView) findViewById(R.id.editText2);



     mopenmaps.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             // Step - 1: get data from the edit text box
             EditText et = findViewById(R.id.editText2);
             String pvp = et.getText().toString();
             //Step -2 : Creat an implicit intent
             Intent i = new Intent();
             //Set the action
             i.setAction(Intent.ACTION_VIEW);
             //Set the data
             i.setData(Uri.parse("geo:0,0?q=" + pvp));
             //Step-3: pass the request to the system
             startActivity(i);
         }
     });


    mgotohome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    });





    }
}