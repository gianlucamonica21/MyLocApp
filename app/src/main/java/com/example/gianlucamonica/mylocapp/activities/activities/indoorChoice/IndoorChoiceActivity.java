package com.example.gianlucamonica.mylocapp.activities.activities.indoorChoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.activities.outdoorMyLoc.OutdoorMyLocActivity;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.AlgorithmName;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.utils.MyApp;

public class IndoorChoiceActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private AlgorithmName algoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_choice);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                onRadioButtonClicked(checkedId);
            }
        });
    }

    public void onRadioButtonClicked(int checkedId) {

        switch (checkedId){
            case R.id.radioWifiRSS:
                algoName = AlgorithmName.WIFI_RSS_FP;
                break;
            case R.id.radioMagnFP:
                algoName = AlgorithmName.MAGNETIC_FP;
                break;
        }

        MyApp.setChosenAlgoName(algoName);
    }

    public void openActivity(View view){

        Intent intent = new Intent(this, OutdoorMyLocActivity.class);
        startActivity(intent);
    }
}
