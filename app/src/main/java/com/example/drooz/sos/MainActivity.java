package com.example.drooz.sos;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        Location l = getLocation();
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

    }

    public void contactStart(View view) {

        Intent contactmanager = new Intent(MainActivity.this , Main3Activity.class);
        startActivity(contactmanager);
    }




    public void sos(View view) {



        Map<String,?> keys = pref.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            String phoneNo = entry.getValue().toString();
            Location l = getLocation();
            String sms = "Your friend is in danger ! " + "https://www.google.com/maps/search/?api=1&query=" + l.getLatitude()+  "," + l.getLongitude();
            SmsManager sosSms = SmsManager.getDefault();
            sosSms.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(this, "SOS ALERT SENT TO " + phoneNo, Toast.LENGTH_SHORT).show();
        }


    }


    Location getLocation() {
        Location location = new Location("Dummy") ;
        location.setLatitude(0);
        location.setLongitude(0);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }
        return location;
    }


    public void addContact(View view) {
        Intent contactmanager = new Intent(MainActivity.this , Main4Activity.class);
        startActivity(contactmanager);
    }

}
