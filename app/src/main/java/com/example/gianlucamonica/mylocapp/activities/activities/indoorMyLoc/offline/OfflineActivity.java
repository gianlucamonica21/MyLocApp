package com.example.gianlucamonica.mylocapp.activities.activities.indoorMyLoc.offline;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import com.example.gianlucamonica.mylocapp.R;
import com.example.gianlucamonica.mylocapp.activities.myLocationManager.MyLocationManager;
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

        // setting the map view
        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.cL);
        LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        MapView v = (MapView) myLocationManager.build(MapView.class);
        constraintLayout.addView(v);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);


        Button button = new Button(this);
        button.setText("Redo scan");
        button.setBackgroundColor(Color.parseColor("#88e1bf"));

        constraintSet.connect(button.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.RIGHT, 0);
        constraintSet.constrainWidth(button.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(button.getId(), ConstraintSet.WRAP_CONTENT);

        constraintSet.connect(button.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraintSet.connect(button.getId(), ConstraintSet.TOP, v.getId(), ConstraintSet.TOP);
        constraintSet.connect(button.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.constrainDefaultHeight(button.getId(), 200);
        constraintSet.applyTo(constraintLayout);
        constraintLayout.addView(button,lpView);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("redoscan","redoscan");
                Toast.makeText(MyApp.getContext(),
                        "Deleting entries",
                        Toast.LENGTH_SHORT).show();
                //deleteFP();

                // refreshing the mapview
                MapView mapView = (MapView) myLocationManager.build(MapView.class);
                constraintLayout.addView(mapView);
            }
        });


    }
}
