package com.jct.oshri.sunalarm;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AlarmActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        findViews();

    }

    private TextView titleTextView;
    private Button snoozeButton;
    private Button stopButton;
    private TextView detailsTextView;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-30 10:10:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        snoozeButton = (Button) findViewById(R.id.snoozeButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        detailsTextView = (TextView) findViewById(R.id.detailsTextView);

        snoozeButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-07-30 10:10:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == snoozeButton) {
            // Handle clicks for snoozeButton
            finish();
        } else if (v == stopButton) {
            finish();
            // Handle clicks for stopButton
        }
    }

}
