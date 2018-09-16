package com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.offline;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.impls.wifi.db.fingerPrint.WifiFingerPrintDAO;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.db.DatabaseManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.map.MapView;

public class OfflineActivity extends AppCompatActivity {

    private MyLocationManager myLocationManager;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        myLocationManager = MyApp.getMyLocationManagerInstance();
        databaseManager = new DatabaseManager(this);

        final ViewGroup mLinearLayout = (ViewGroup) findViewById(R.id.cL);

        // setting the map view
        MapView v = (MapView) myLocationManager.build(MapView.class);
        mLinearLayout.addView(v);

        // setting redo scan button
        Button button = (Button) findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("redoscan","redoscan");
                Toast.makeText(MyApp.getContext(),
                        "Deleting entries",
                        Toast.LENGTH_SHORT).show();
                deleteFP();

                // refreshing the mapview
                MapView mapView = (MapView) myLocationManager.build(MapView.class);
                mLinearLayout.addView(mapView);
            }
        });
    }

    public void deleteFP(){
        WifiManager wifiManager = (WifiManager) MyApp.getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        if(wifiInfo != null){
            WifiFingerPrintDAO wifiFingerPrintDAO = databaseManager.getAppDatabase().getFingerPrintDAO();
            wifiFingerPrintDAO.deleteByAPSsid(wifiInfo.getSSID());
        }
    }
}
