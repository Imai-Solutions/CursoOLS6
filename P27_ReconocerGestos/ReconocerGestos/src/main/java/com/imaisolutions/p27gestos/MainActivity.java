package com.imaisolutions.p27gestos;


import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity  extends Activity implements GestureOverlayView.OnGesturePerformedListener {
        GestureLibrary gLibrary;
        GestureOverlayView mView;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            GestureLibraries GestureLibraries;
            gLibrary = android.gesture.GestureLibraries.fromRawResource(this, R.raw.gestures);
            if(gLibrary != null)
            {
                if(!gLibrary.load())
                {
                    Log.e("GestureSample", "Gesture library was not loaded…");
                    finish();
                }
                else
                {
                    mView = (GestureOverlayView) findViewById(R.id.gestureOverlayView);
                    mView.addOnGesturePerformedListener(this);
                }
            }
        }

        @Override
        public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
            // TODO Auto-generated method stub
            ArrayList<Prediction> predictions = gLibrary.recognize(gesture);

            // one prediction needed
            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                // checking prediction
                if (prediction.score > 1.0) {
                    // and action
                    Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
                }
            }
        }
 }


