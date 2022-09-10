package com.example.firebaseproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    public BlankFragment() { }
    ListView lv;
    public FireBase fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Handler h = new Handler();
        fb = new FireBase(getActivity());
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                lv = view.findViewById(R.id.lv3);
                MyAdapter ma = new MyAdapter(fb.getSellers(),getActivity());
                lv.setAdapter(ma);
            }
        },10);

        return view;
    }
}