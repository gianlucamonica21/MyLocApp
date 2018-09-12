package com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic;

import android.app.Activity;
import android.view.View;

import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.db.magneticFingerPrint.MagneticFingerPrint;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.offline.MagneticOfflineManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.online.MagneticOnlineManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.locAlgInterface.LocalizationAlgorithmInterface;

public class MagneticFieldAlgorithm implements LocalizationAlgorithmInterface {

    private MagneticOfflineManager magneticOfflineManager;
    private MagneticOnlineManager magneticOnlineManager;
    private Activity activity;

    public MagneticFieldAlgorithm(Activity activity){
        this.activity = activity;
    }

    @Override
    public Object getBuildClass(Activity activity) {
        return null;
    }

    @Override
    public <T extends View> T build(Class<T> type)  {
        this.magneticOfflineManager = new MagneticOfflineManager(this.activity);
        return magneticOfflineManager.build(type);
    }

    @Override
    public MagneticFingerPrint locate() {
        magneticOnlineManager = new MagneticOnlineManager(activity);
        return magneticOnlineManager.locate();
    }

    @Override
    public void checkPermissions() {

    }


}
