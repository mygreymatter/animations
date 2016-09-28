package com.mayo.animations.sharedelement.activityviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayo.animations.R;
import com.mayo.animations.Tag;

/**
 * Created by Mahayogi Lakshmipathi on 26/8/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */
public class DemoFragment extends Fragment {

    public DemoFragment() {

    }

    public static DemoFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Tag.POSITION, position);

        DemoFragment demoFragment = new DemoFragment();
        demoFragment.setArguments(args);

        return demoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView posView = (TextView) view.findViewById(R.id.position);
        posView.setText(String.valueOf(getArguments().getInt(Tag.POSITION)));

        ImageView profilePicture = (ImageView) view.findViewById(R.id.profile_picture);
        profilePicture.setTransitionName(Tag.TRANSITIONNAME + getArguments().getInt(Tag.POSITION));

        getActivity().startPostponedEnterTransition();
    }
}
