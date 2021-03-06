package com.example.gray;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Pincurrent extends AppCompatActivity {
private static final int Request_location=1;
    Button getlocation;
    Button sendlocation;
    TextView showlocation;
    LocationManager locationManager;
    String latitude,longitude;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincurrent);

    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_location);
    showlocation=findViewById(R.id.show_location);
    getlocation=findViewById(R.id.get_location);
    sendlocation=findViewById(R.id.send_location);
    getlocation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                OnGPS();
            } else {
                getLocation();
            }
        }

        private void getLocation() {

            if (ActivityCompat.checkSelfPermission(Pincurrent.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Pincurrent.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Pincurrent.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_location);

            } else {
                Location LocationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                Location LoacationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                if (LocationGPS != null) {
                    double lat = LocationGPS.getLatitude();
                    double lon = LocationGPS.getLongitude();
                    latitude = String.valueOf(lat);
                    longitude = String.valueOf(lon);
                    showlocation.setText("Your location:" + "\n" + "latitude=" + latitude + "\n" + "longitude=" + longitude);

                } else if (LocationNetwork != null) {
                    double lat = LocationNetwork.getLatitude();
                    double lon = LocationNetwork.getLongitude();
                    latitude = String.valueOf(lat);
                    longitude = String.valueOf(lon);
                    showlocation.setText("Your location:" + "\n" + "latitude=" + latitude + "\n" + "longitude=" + longitude);

                } else if (LocationGPS != null) {
                    double lat = LoacationPassive.getLatitude();
                    double lon = LoacationPassive.getLongitude();
                    latitude = String.valueOf(lat);
                    longitude = String.valueOf(lon);
                    showlocation.setText("Your location:" + "\n" + "latitude=" + latitude + "\n" + "longitude=" + longitude);

                } else {
                    Toast.makeText(Pincurrent.this, "cannot get your location", Toast.LENGTH_SHORT).show();
                }
            }
        }
        private void OnGPS() {
            final AlertDialog.Builder builder=new AlertDialog.Builder(Pincurrent.this);
            builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    });
    sendlocation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String phonenumber = "6281165036";
                String message = "Latitude =" + latitude + "Longitude" + longitude;
                SmsManager smsManager = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                    smsManager = SmsManager.getDefault();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                    smsManager.sendTextMessage(phonenumber, null, message, null, null);
                }
                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
}
