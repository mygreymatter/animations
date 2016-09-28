package com.mayo.animations.circularreveal.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.mayo.animations.R;

/**
 * Created by Mahayogi Lakshmipathi on 28/9/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */

public class CircularFragment extends Fragment {

    public CircularFragment() {

    }

    public static CircularFragment newInstance(int x, int y) {
        CircularFragment circularFragment = new CircularFragment();
        Bundle args = new Bundle();
        args.putInt("x", x);
        args.putInt("y", y);

        circularFragment.setArguments(args);

        return circularFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_circular_reveal, container, false);

        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.darker_gray));

        // To run the animation as soon as the view is layout in the view hierarchy we add this
        // listener and remove it
        // as soon as it runs to prevent multiple animations if the view changes bounds
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int cx = 0;
                int cy = 0;

                // get the hypothenuse so the radius is from one corner to the other
                int radius = (int) Math.hypot(right, bottom);

                Animator reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, radius);
                reveal.setInterpolator(new DecelerateInterpolator(2f));
                reveal.setDuration(3000);
                reveal.start();
            }
        });

        return rootView;
    }
}
