package com.jct.oshri.sunalarm.control;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jct.oshri.sunalarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTimeFragment extends Fragment {


    public SelectTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_time, container, false);
    }

}
