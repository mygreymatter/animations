package com.mayo.animations.circularreveal.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mayo.animations.R;

public class DialogRevealActivity extends AppCompatActivity
        implements RevealDialogFragment.OnRevealListener {

    private static final String TAG = DialogRevealActivity.class.getName();
    private RevealDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_reveal);

    }

    public void revealDialog(View v) {

        Log.i(TAG, v.getX() + " " + v.getY());

        dialogFragment = new RevealDialogFragment();
        Bundle args = new Bundle();
        args.putInt("x", (int) v.getX());
        args.putInt("y", (int) v.getY());

        dialogFragment.setArguments(args);
        /*dialogFragment.setCancelable(false);*/
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    @Override
    public void removeFragment() {
       /* Animator animation = dialogFragment.prepareUnrevealAnimator();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                getSupportFragmentManager().beginTransaction()
                        .remove(dialogFragment).commit();

                //to prevent flashing the fragment before removing it, execute pending transactions inmediately
                getFragmentManager().executePendingTransactions();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animation.start();*/
        getSupportFragmentManager().beginTransaction()
                .remove(dialogFragment).commit();

        //to prevent flashing the fragment before removing it, execute pending transactions inmediately
        getFragmentManager().executePendingTransactions();

    }
}
