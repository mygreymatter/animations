package com.mayo.animations.circularreveal.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mayo.animations.R;

public class FragmentRevealActivity extends AppCompatActivity
        implements View.OnTouchListener {

    private static final String TAG = FragmentRevealActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_reveal);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_fragment_reveal, new CircularFragment())
                .commit();
//        findViewById(R.id.activity_fragment_reveal).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        /*switch (event.getAction()){
            case
        }*/

        Log.i(TAG, event.getX() + " " + event.getY());

        return true;
    }
}
