package com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.db.magneticFingerPrint.MagneticFingerPrint;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.magnetic.db.magneticFingerPrint.MagneticFingerPrintDAO;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.AP.AP;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.AP.APDAO;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.fingerPrint.WifiFingerPrint;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.fingerPrint.WifiFingerPrintDAO;


@Database(entities = {AP.class, WifiFingerPrint.class, MagneticFingerPrint.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {

    public abstract APDAO getAPDAO();
    public abstract WifiFingerPrintDAO getFingerPrintDAO();
    public abstract MagneticFingerPrintDAO getMagneticFingerPrintDAO();

}