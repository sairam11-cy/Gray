package com.example.gray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class searchactivity extends AppCompatActivity {
    TextView tv;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchactivity);
        tv=findViewById(R.id.txtview);
        tv.setText(getIntent().getStringExtra("Name"));String s=tv.getText().toString();
        dbref= FirebaseDatabase.getInstance().getReference("Products");

        searchquery(s);
    }

    private void searchquery(String s) {
        Query query=dbref.orderByChild("id").equalTo("1234");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(searchactivity.this, "Seraching..", Toast.LENGTH_SHORT).show();
                if(dataSnapshot.exists()){
                    Toast.makeText(searchactivity.this, "Found", Toast.LENGTH_SHORT).show();
                }
                else {Toast.makeText(searchactivity.this, "Not Found", Toast.LENGTH_SHORT).show();}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
