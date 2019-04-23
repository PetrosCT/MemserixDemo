package com.maliotis.mesmerixdemo.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.maliotis.mesmerixdemo.R;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UnityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UnityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UnityFragment() {
        // Required empty public constructor
    }

    protected UnityPlayer mUnityPlayer;     // don't change the name of this variable; referenced from native code
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static UnityFragment newInstance(int sectionNumber) {
        UnityFragment fragment = new UnityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    // Setup activity layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //getActivity().getWindow().takeSurface(null);
        //getActivity().setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        //getActivity().getWindow().setFormat(PixelFormat.RGB_565);

        mUnityPlayer = new UnityPlayer(getActivity());
        if (mUnityPlayer.getSettings().getBoolean("hide_status_bar", true))
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initUnityPlayer();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onWindowFocusChanged(true);
            }
        },1000);

        UnityPlayer.UnitySendMessage("Manager", "ConnectHere", "Here is the text!");
        return mUnityPlayer.getView();
    }

    private void initUnityPlayer() {
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);
    }

    //Quit Unity
    @Override
    public void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    public void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override
    public void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }

    //This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
