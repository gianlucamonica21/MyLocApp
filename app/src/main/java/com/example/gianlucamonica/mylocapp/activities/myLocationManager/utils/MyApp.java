package com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils;

import android.app.Application;
import android.content.Context;

import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.offline.WifiOfflineManager;


public class MyApp extends Application {

    private static MyApp instance;
    private static MyLocationManager myLocationManagerInstance;
    private static WifiOfflineManager wifiOfflineManagerInstance;
    private static AlgorithmName chosenAlgoName;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static AlgorithmName getChosenAlgoName() {
        return chosenAlgoName;
    }

    public static void setChosenAlgoName(AlgorithmName chosenAlgoName) {
        MyApp.chosenAlgoName = chosenAlgoName;
    }

    public static MyLocationManager getMyLocationManagerInstance() {
        return myLocationManagerInstance;
    }

    public static void setMyLocationManagerInstance(MyLocationManager myLocationManagerInstance) {
        MyApp.myLocationManagerInstance = myLocationManagerInstance;
    }

    public static WifiOfflineManager getWifiOfflineManagerInstance() {
        return wifiOfflineManagerInstance;
    }

    public static void setWifiOfflineManagerInstance(WifiOfflineManager wifiOfflineManagerInstance) {
        MyApp.wifiOfflineManagerInstance = wifiOfflineManagerInstance;
    }
}
