package com.example.gianlucamonica.mylocapp.activities.myLocationManager;

import android.Manifest;
import android.app.Activity;
import android.view.View;


import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.gps.GPSLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.MagneticFieldAlgorithm;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.WifiAlgorithm;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.locAlgInterface.LocalizationAlgorithmInterface;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.permissionsManager.MyPermissionsManager;

import java.util.Arrays;

public class MyLocationManager implements LocalizationAlgorithmInterface {

    private AlgorithmName algoName;
    private LocalizationAlgorithmInterface localizationAlgorithmInterface;
    private MyPermissionsManager myPermissionsManager;
    private String[] permissions;

    /*
     * constructor which build a new istance of a particular algorithm according to algoName
     * for each algorithm permissions and enables are checked
     * @param algoName
     * @param activity
     */
    public MyLocationManager(AlgorithmName algoName, Activity activity) {

        myPermissionsManager = new MyPermissionsManager(activity, algoName);

        switch (algoName) {
            case GPS:
                this.algoName = algoName;
                permissions = new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};
                checkPermissions();

                if ( myPermissionsManager.isGPSEnabled())
                    localizationAlgorithmInterface = new GPSLocationManager(MyApp.getContext(),activity);
                break;
            case WIFI_RSS_FP:
                this.algoName = algoName;
                permissions = new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};

                checkPermissions();

                if (myPermissionsManager.isWIFIEnabled())
                    localizationAlgorithmInterface = new WifiAlgorithm(activity);
                break;
            case MAGNETIC_FP:
                this.algoName = algoName;
                permissions = new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};

                checkPermissions();

                localizationAlgorithmInterface = new MagneticFieldAlgorithm(activity);

                break;
            default:
        }

    }

    /**
     * @param activity
     * @return algorithm build class
     */
    @Override
    public Object getBuildClass(Activity activity) {
        return localizationAlgorithmInterface.getBuildClass(activity);
    }

    /**
     * @param type
     * @param <T>
     * @return class subtype of View which is used for the build phase
     */
    @Override
    public  <T extends View> T build(Class<T> type) {
        return localizationAlgorithmInterface.build(type);
    }

    /**
     * @return Location computed with the specified algorithm
     */
    public <T> T locate() {
        return localizationAlgorithmInterface.locate();
    }

    /**
     * check permissions and turn on the providers according to the algorithm
     */
    @Override
    public void checkPermissions() {
        myPermissionsManager.requestPermission(permissions);
        myPermissionsManager.turnOnServiceIfOff();
    }

    public MyPermissionsManager getMyPermissionsManager() {
        return myPermissionsManager;
    }

    public void setMyPermissionsManager(MyPermissionsManager myPermissionsManager) {
        this.myPermissionsManager = myPermissionsManager;
    }

    @Override
    public String toString() {
        return "MyLocationManager{" +
                "algoName=" + algoName +
                ", localizationAlgorithmInterface=" + localizationAlgorithmInterface +
                ", myPermissionsManager=" + myPermissionsManager +
                ", permissions=" + Arrays.toString(permissions) +
                '}';
    }
}
