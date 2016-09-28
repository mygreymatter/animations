package com.mayo.animations.sharedelement.activityviewpager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mayo.animations.R;
import com.mayo.animations.Tag;

public class CallingActivity extends AppCompatActivity {

    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        profilePicture = (ImageView) findViewById(R.id.profile_picture);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                profilePicture.setTransitionName(Tag.TRANSITIONNAME + "0");

                Intent i = new Intent(CallingActivity.this, ViewPagerActivity.class);
                Pair<View, String> p1 = Pair.create((View) profilePicture, Tag.TRANSITIONNAME + "0");

                if (!isFinishing()) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(CallingActivity.this, p1);
                    startActivity(i, options.toBundle());
                }

            }
        }, 1500);
    }

}
