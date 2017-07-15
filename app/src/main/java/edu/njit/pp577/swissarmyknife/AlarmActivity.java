package edu.njit.pp577.swissarmyknife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by ppatel on 1/29/2017.
 */

public class AlarmActivity extends AppCompatActivity {

    TextView alarm_text;
    Button alarmSetter;
    TimePickerDialog pick_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarm_text = (TextView) findViewById(R.id.text_alarm);
        alarmSetter = (Button) findViewById(R.id.launch_alarm);
        alarmSetter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LaunchTimePicker();
            }
        });
    }

    private void LaunchTimePicker() {
        Calendar calendar = Calendar.getInstance();
        alarm_text.setText("");
        pick_time = new TimePickerDialog(this, timePickedListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        pick_time.show();

    }

    TimePickerDialog.OnTimeSetListener timePickedListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar currentTime = Calendar.getInstance();
            Calendar chosenTime = (Calendar) currentTime.clone();

            chosenTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            chosenTime.set(Calendar.MINUTE, minute);
            chosenTime.set(Calendar.SECOND, 0);
            chosenTime.set(Calendar.MILLISECOND, 0);

            // If picked time has already passed for today, set it for tomorrow's date
            if (chosenTime.compareTo(currentTime) <= 0) {
                chosenTime.add(Calendar.DATE, 1);
            }

            alarm_text.setText(" Alarm is set for " + chosenTime.getTime() + "\n" + " Expect Toast pop for when alarm is received.");
            Intent alarmReceiver_intent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingReceiverIntent = PendingIntent.getBroadcast(getBaseContext(), 1, alarmReceiver_intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.set(AlarmManager.RTC_WAKEUP, chosenTime.getTimeInMillis(), pendingReceiverIntent);
        }
    };

}
