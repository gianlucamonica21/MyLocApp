package com.example.gianlucamonica.mylocapp.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.activities.indoorChoice.IndoorChoiceActivity;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorLoc.OutdoorLocActivity;

public class MainActivity extends AppCompatActivity {

    Switch simpleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate a Switch
        simpleSwitch = (Switch) findViewById(R.id.switch1);

    }

    void openActivity(View view){
        // check current state of a Switch (true or false).
        Boolean switchState = simpleSwitch.isChecked();

        if(switchState){
            openMyLocAct(view);
        }else{
            openOutdoorLocAct(view);
        }

    }

    void openOutdoorLocAct(View view){
        Intent intent = new Intent(this, OutdoorLocActivity.class);
        startActivity(intent);
    }

    void openMyLocAct(View view){
        Intent intent = new Intent(this, IndoorChoiceActivity.class);
        startActivity(intent);
    }
}
