package com.maliotis.mesmerixdemo;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.maliotis.mesmerixdemo.Fragments.UnityFragment;

public class MainActivity extends AppCompatActivity
        implements UnityFragment.OnFragmentInteractionListener {

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRelativeLayout = new RelativeLayout(this);
        mRelativeLayout.setId(View.generateViewId());
        mRelativeLayout.setLayoutParams(lp);

        setContentView(mRelativeLayout);



        createFragment();

    }

    private void createFragment() {
        @SuppressLint("CommitTransaction")
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

        UnityFragment unityFragment = UnityFragment.newInstance(12);

        tr.replace(mRelativeLayout.getId(), unityFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
