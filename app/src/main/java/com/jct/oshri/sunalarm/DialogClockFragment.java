package com.jct.oshri.sunalarm;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogClockFragment extends DialogFragment {
    Ringtone ringtone;

    public DialogClockFragment() {
        // Required empty public constructor

    }

    @Override
    public void onResume() {
        super.onResume();
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), uri);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        ringtone.play();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("שעון")
                .setPositiveButton("עצור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ringtone.stop();

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    @Override
    public void onPause() {
        super.onPause();
        ringtone.stop();
    }
}
