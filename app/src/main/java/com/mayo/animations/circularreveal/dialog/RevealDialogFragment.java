package com.mayo.animations.circularreveal.dialog;


import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.mayo.animations.R;

import java.util.Arrays;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class RevealDialogFragment extends DialogFragment {
    private static final String TAG = RevealDialogFragment.class.getName();
    int finalX;
    int finalY;
    private int cx;
    private int cy;
    private boolean hasBackPressed;
    private OnRevealListener mListener;

    public RevealDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_reveal_dialog, container, false);
        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));


        // To run the animation as soon as the view is layout in the view hierarchy we add this
        // listener and remove it
        // as soon as it runs to prevent multiple animations if the view changes bounds
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                cx = getArguments().getInt("x");
                cy = getArguments().getInt("y");

                // get the hypothenuse so the radius is from one corner to the other
                int radius = (int) Math.hypot(right, bottom);

                Animator reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, radius);
                reveal.setInterpolator(new DecelerateInterpolator(2f));
                reveal.setDuration(1000);
                reveal.start();

                Log.i(TAG, "Height: " + rootView.getHeight() + " " + rootView.getWidth());

                finalX = cx + rootView.getWidth();
                finalY = cy + rootView.getHeight();

                Log.i(TAG, "Final: " + finalX + " " + finalY);


            }
        });

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.i(TAG, "BackPressed");

                    if (!hasBackPressed) {
                        hasBackPressed = true;


                        mListener.removeFragment();

                    }

                }

                return true;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnRevealListener) {
            mListener = (OnRevealListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * Get the animator to unreveal the circle
     *
     * @return Animator object that will be used for the animation
     */
    public Animator prepareUnrevealAnimator() {
        int radius = getEnclosingCircleRadius(getView(), finalX, finalY);
        Animator anim = ViewAnimationUtils.createCircularReveal(getView(), cx, cy, radius, 0);
        anim.setInterpolator(new AccelerateInterpolator(2f));
        anim.setDuration(1000);
        return anim;
    }

    /**
     * To be really accurate we have to start the circle on the furthest corner of the view
     *
     * @param v  the view to unreveal
     * @param cx center x of the circle
     * @param cy center y of the circle
     * @return the maximum radius
     */
    private int getEnclosingCircleRadius(View v, int cx, int cy) {
        int realCenterX = cx + v.getLeft();
        int realCenterY = cy + v.getTop();
        int distanceTopLeft = (int) Math.hypot(realCenterX - v.getLeft(), realCenterY - v.getTop());
        int distanceTopRight = (int) Math.hypot(v.getRight() - realCenterX, realCenterY - v.getTop());
        int distanceBottomLeft = (int) Math.hypot(realCenterX - v.getLeft(), v.getBottom() - realCenterY);
        int distanceBottomRight = (int) Math.hypot(v.getRight() - realCenterX, v.getBottom() - realCenterY);

        Integer[] distances = new Integer[]{distanceTopLeft, distanceTopRight, distanceBottomLeft,
                distanceBottomRight};

        return Collections.max(Arrays.asList(distances));
    }


    public interface OnRevealListener {
        void removeFragment();
    }

}
