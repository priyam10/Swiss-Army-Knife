package edu.njit.pp577.swissarmyknife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ppatel on 1/29/2017.
 */


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent alarmIntent) {
        Toast.makeText(context, "Alarm Received!", Toast.LENGTH_LONG).show();

    }

}