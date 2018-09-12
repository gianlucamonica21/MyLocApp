package com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc.fragments.MapsActivity;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class OutdoorMyLocActivity extends AppCompatActivity implements OnMapReadyCallback{

    public static final String EXTRA_LAT = "lat";
    public static final String EXTRA_LNG = "lng";

    private GoogleMap mMap;
    // location's stuff
    private MyLocationManager myLocationManager;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GPSactivity","on create");
        setContentView(R.layout.activity_outdoor_my_loc);

        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting location
                if (myLocationManager.getMyPermissionsManager().isGPSEnabled()) {

                    Location location = myLocationManager.locate();
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();

                    Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(OutdoorMyLocActivity.this, MapsActivity.class);
                    intent.putExtra(EXTRA_LAT, longitude);
                    intent.putExtra(EXTRA_LNG, latitude);

                    startActivity(intent);
                }
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("GPSactivity", "on resume");
        myLocationManager = new MyLocationManager(AlgorithmName.GPS, this);

        if (!myLocationManager.getMyPermissionsManager().isGPSEnabled()) {
            btn.setEnabled(false);
        }

        if (myLocationManager.getMyPermissionsManager().isGPSEnabled()) {
            btn.setEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
