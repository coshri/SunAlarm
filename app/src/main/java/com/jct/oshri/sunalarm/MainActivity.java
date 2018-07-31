package com.jct.oshri.sunalarm;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.shredzone.commons.suncalc.SunTimes;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener {

    private  final int P_ACCESS_COARSE_LOCATION_CODE = 1;

    private Calendar calendar;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location lastLocation = null;



    TextView titleTextView;
Button addAlarmButton;

    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
                String stateName = addresses.get(0).getAddressLine(1);
                String countryName = addresses.get(0).getAddressLine(2);
                return stateName + "\n" + cityName + "\n" + countryName;
            }

            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, P_ACCESS_COARSE_LOCATION_CODE);

        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                lastLocation = location;


                                String toastMsg = String.format("Place: %s", getPlace(lastLocation));

                                Date date = new Date();// date of calculation

                                SunTimes times = SunTimes.compute()
                                        .on(date)       // set a date
                                        .at(lastLocation.getLatitude(), lastLocation.getLongitude())   // set a location
                                        .execute();     // get the results
                                toastMsg += "\nSunrise: " + times.getRise();
                                toastMsg += "\nSunset: " + times.getSet();

                                titleTextView.setText(toastMsg);
                                // Logic to handle location object

                            }
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == P_ACCESS_COARSE_LOCATION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.titleTextView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        getLocation();


        addAlarmButton = findViewById(R.id.addAlarmButton);
        addAlarmButton.setOnClickListener(this);


        // Set the alarm to start at 8:30 a.m.
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 23);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                SystemClock.elapsedRealtime() + 1* 60 * 1000, pendingIntent);

        // setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.


//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 , pendingIntent);
    }

    @Override
    public void onClick(View v) {
        if(v==addAlarmButton )
        {
            startActivity(new Intent(this,AddAlarmActivity.class));
        }
    }
}
