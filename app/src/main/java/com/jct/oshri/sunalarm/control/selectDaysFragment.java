package com.jct.oshri.sunalarm.control;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.jct.oshri.sunalarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class selectDaysFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {


    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private ToggleButton toggleButton4;
    private ToggleButton toggleButton5;
    private ToggleButton toggleButton6;
    private ToggleButton toggleButton7;


    private byte days = 0;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-31 13:51:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(Activity activity) {
        toggleButton1 = activity.findViewById( R.id.toggleButton_1 );
        toggleButton2 = activity.findViewById( R.id.toggleButton_2 );
        toggleButton3 = activity.findViewById( R.id.toggleButton_3 );
        toggleButton4 = activity.findViewById( R.id.toggleButton_4 );
        toggleButton5 = activity.findViewById( R.id.toggleButton_5 );
        toggleButton6 = activity.findViewById( R.id.toggleButton_6 );
        toggleButton7 = activity.findViewById( R.id.toggleButton_7 );

        toggleButton1.setOnCheckedChangeListener( this );
        toggleButton2.setOnCheckedChangeListener( this );
        toggleButton3.setOnCheckedChangeListener( this );
        toggleButton4.setOnCheckedChangeListener( this );
        toggleButton5.setOnCheckedChangeListener( this );
        toggleButton6.setOnCheckedChangeListener( this );
        toggleButton7.setOnCheckedChangeListener( this );
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews(this.getActivity());
    }





    public selectDaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_days, container, false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        byte value = Byte.parseByte(buttonView.getTag().toString());

        if (isChecked) {
            // The toggle is enabled
            setDays((byte) (getDays() | value));
        } else {
            // The toggle is disabled

            int temp =  value ^ -1; // value xor 11111..

            setDays((byte) (getDays() & temp));
        }

        Log.d("test bit", "days: " + getDays());
    }

    public byte getDays() {
        return days;
    }

    public void setDays(byte days) {
        this.days = days;
    }
}
