package com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorChoice.IndoorChoiceActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.offline.OfflineActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.online.OnlineActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorLoc.OutdoorLocActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc.OutdoorMyLocActivity;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;

import static com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName.MAGNETIC_FP;
import static com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName.WIFI_RSS_FP;

public class IndoorMyLocActivity extends AppCompatActivity {

    private MyLocationManager myLocationManager;
    private AlgorithmName chosenAlgoName;
    // UI stuffs
    private Button onlineBtn;
    private Button offlineBtn;
    private int onlineBtnVisibility;
    private int offlineBtnVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_my_loc);

        // retrieving and instatiating myLoc with chosen alg
        chosenAlgoName = MyApp.getChosenAlgoName();
        Toast.makeText(this,"instantiating " +  String.valueOf(chosenAlgoName),Toast.LENGTH_SHORT).show();
        myLocationManager = new MyLocationManager(chosenAlgoName,this);
        MyApp.setMyLocationManagerInstance(myLocationManager);
        // build activity layout according to chosenAlgoName
        buildUI();
        createAndShowAlertDialog();
    }

    public void createAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("It seems you are entering a building");
        builder.setMessage("Do you want to do indoor localization?");
        final Activity a = this;
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void buildUI(){
        switch (chosenAlgoName){
            case WIFI_RSS_FP:
                onlineBtn = findViewById(R.id.onlineBtn);
                offlineBtn = findViewById(R.id.offlineBtn);
                onlineBtnVisibility = 1;
                offlineBtnVisibility = 1;
                onlineBtn.setVisibility(onlineBtnVisibility);
                offlineBtn.setVisibility(offlineBtnVisibility);
                break;
            case MAGNETIC_FP:
                onlineBtn = findViewById(R.id.onlineBtn);
                offlineBtn = findViewById(R.id.offlineBtn);
                onlineBtnVisibility = 1;
                offlineBtnVisibility = 1;
                onlineBtn.setVisibility(onlineBtnVisibility);
                offlineBtn.setVisibility(offlineBtnVisibility);
                break;
        }
    }

    public void openOnlineActivity(View view){
        Intent intent = new Intent(this, OnlineActivity.class);
        startActivity(intent);
    }

    public void openOfflineActivity(View view){
        Intent intent = new Intent(this, OfflineActivity.class);
        startActivity(intent);
    }
}
