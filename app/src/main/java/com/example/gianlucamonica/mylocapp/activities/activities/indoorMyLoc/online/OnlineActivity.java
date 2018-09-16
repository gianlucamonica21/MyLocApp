package com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.db.magneticFingerPrint.MagneticFingerPrint;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.fingerPrint.WifiFingerPrint;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.map.MapView;

public class OnlineActivity extends AppCompatActivity {

    private MyLocationManager myLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        myLocationManager = MyApp.getMyLocationManagerInstance();
    }

    public void locate(View view){

        switch (MyApp.getChosenAlgoName()){
            case MAGNETIC_FP:
                MagneticFingerPrint MagnComputedLocation = myLocationManager.locate();

                if(MagnComputedLocation != null){
                    Toast.makeText(this,"your position: " + MagnComputedLocation.toString(),Toast.LENGTH_SHORT).show();
                    final ViewGroup mLinearLayout = (ViewGroup) findViewById(R.id.constraintLayout);

                    // setting the map view
                    MapView mapView = new MapView(this,MagnComputedLocation.getGridName());
                    mLinearLayout.addView(mapView);
                }
                break;
            case WIFI_RSS_FP:
                WifiFingerPrint WifiComputedLocation = myLocationManager.locate();

                if(WifiComputedLocation != null){
                    Toast.makeText(this,"your position: " + WifiComputedLocation.toString(),Toast.LENGTH_SHORT).show();
                    final ViewGroup mLinearLayout = (ViewGroup) findViewById(R.id.constraintLayout);

                    // setting the map view
                    MapView mapView = new MapView(this,WifiComputedLocation.getGridName());
                    mLinearLayout.addView(mapView);
                }

                break;
        }

    }
}
