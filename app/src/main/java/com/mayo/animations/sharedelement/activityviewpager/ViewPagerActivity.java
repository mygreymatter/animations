package com.mayo.animations.sharedelement.activityviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mayo.animations.R;

public class ViewPagerActivity extends AppCompatActivity {
    int mCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        postponeEnterTransition();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new DemoFragmentPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(mCurrentPosition);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }
        });
    }


    private class DemoFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public DemoFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DemoFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
