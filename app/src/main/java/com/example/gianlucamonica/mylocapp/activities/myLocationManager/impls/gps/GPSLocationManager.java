package com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.gps;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorChoice.IndoorChoiceActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.IndoorMyLocActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc.OutdoorMyLocActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc.fragments.MapsActivity;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.locAlgInterface.LocalizationAlgorithmInterface;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;

import java.util.Map;

import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;
import static android.provider.Settings.ACTION_WIFI_SETTINGS;

public class GPSLocationManager extends Service implements LocalizationAlgorithmInterface, LocationListener {

    private final Context mContext;
    private Activity activity;

    boolean checkGPS = false;
    boolean checkNetwork = false;
    boolean canGetLocation = false;

    Location loc;
    double latitude;
    double longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;//10;
    private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1;

    protected LocationManager locationManager;

    /**
     *
     * @param mContext
     */
    public GPSLocationManager(Context mContext,Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        getLocation();
    }

    private Location getLocation() {

        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // get GPS status
            checkGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // get network provider status
            checkNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isProviderEnabled()) {
                Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (checkGPS) {

                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            latitude = loc.getLatitude();
                            longitude = loc.getLongitude();
                        }
                    }


                }


                if (checkNetwork) {


                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    }

                    if (loc != null) {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return loc;
    }

    public Location getLoc() {
        return loc;
    }

    public double getLongitude() {
        if (loc != null) {
            longitude = loc.getLongitude();
        }
        return longitude;
    }

    public double getLatitude(){
        if (loc != null) {
        latitude = loc.getLatitude();
        }
        return latitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }



    public void stopListener() {
        if (locationManager != null) {

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(GPSLocationManager.this);
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public Object getBuildClass(Activity activity) {
        return null;
    }

    @Override
    public <T extends View> T build(Class<T> type) {
        return null;
    }

    @Override
    public Location locate() {
         return getLoc();
    }

    @Override
    public void checkPermissions() {

    }

    public boolean isProviderEnabled() {
        if(!checkGPS && !checkNetwork){
            return false;
        }
        return true;
    }

    /**
     No GPS signal: indoors (or tunnel, garage)
     hor accuracy > 20m : indoors
     hor accuracy <= 10m:  outdoors
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.i("onLocationChanged lat", String.valueOf(location.getLatitude()));
        Log.i("onLocationChanged lng", String.valueOf(location.getLongitude()));
        Log.i("gps accuracy", String.valueOf(location.getAccuracy()));
        if(location.getAccuracy() > 10){
            Toast.makeText(MyApp.getContext(),"You are entering a building",Toast.LENGTH_SHORT).show();
            //createAndShowAlertDialog();
            Intent i = new Intent(this.activity, IndoorMyLocActivity.class);
            this.activity.startActivity(i);
            stopListener();
        }else{

        }
        loc = location;
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //This method is called when a provider is unable to fetch a location or if the
        // provider has recently become available after a period of unavailability.
        boolean redirect = false;
        switch (status){
            case LocationProvider.OUT_OF_SERVICE:
                redirect = true;
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                redirect = true;
                break;
        }

        if(redirect){
            Toast.makeText(MyApp.getContext(),"You are entering a building",Toast.LENGTH_SHORT).show();
            //createAndShowAlertDialog();
            Intent i = new Intent(this.activity, IndoorMyLocActivity.class);
            this.activity.startActivity(i);
            stopListener();
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
