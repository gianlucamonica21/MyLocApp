package com.example.gianlucamonica.mylocapp.activities.myLocationManager.locAlgInterface;

import android.app.Activity;
import android.view.View;

public interface LocalizationAlgorithmInterface {

    Object getBuildClass(Activity activity);

    <T extends View> T build(Class<T> type);

    <T> T locate();

    void checkPermissions();

}
