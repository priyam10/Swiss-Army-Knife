package edu.njit.pp577.swissarmyknife;

/**
 * Created by ppatel on 1/26/2017.
 */
import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity{

    private static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOGS  = 478;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE  = 479;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 480;
    private int knife1_id, knife2_id, knife3_id, knife4_id, knife5_id, knife6_id;
    private ImageView knife1, knife2, knife3, knife4, knife5, knife6;
    private boolean left_blades_out = false;
    private boolean right_blades_out = false;
    private Location loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        knife1 = (ImageView) findViewById(R.id.knife1);
        knife1_id = knife1.getId();
        knife1.setDrawingCacheEnabled(true);
        knife1.setOnTouchListener(changeColorListener);

        knife2 = (ImageView) findViewById(R.id.knife2);
        knife2_id = knife2.getId();
        knife2.setDrawingCacheEnabled(true);
        knife2.setOnTouchListener(changeColorListener);

        knife3 = (ImageView) findViewById(R.id.knife3);
        knife3_id = knife3.getId();
        knife3.setDrawingCacheEnabled(true);
        knife3.setOnTouchListener(changeColorListener);

        knife4 = (ImageView) findViewById(R.id.knife4);
        knife4_id = knife4.getId();
        knife4.setDrawingCacheEnabled(true);
        knife4.setOnTouchListener(changeColorListener);

        knife5 = (ImageView) findViewById(R.id.knife5);
        knife5_id = knife5.getId();
        knife5.setDrawingCacheEnabled(true);
        knife5.setOnTouchListener(changeColorListener);

        knife6 = (ImageView) findViewById(R.id.knife6);
        knife6_id = knife6.getId();
        knife6.setDrawingCacheEnabled(true);
        knife6.setOnTouchListener(changeColorListener);

        // Left & Right black button click events
        Button left_switchButton = (Button) findViewById(R.id.left_switch);
        Button right_switchButton = (Button) findViewById(R.id.right_switch);
        left_switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeftSwitchClick();
            }
        });
        right_switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightSwitchClick();
            }
        });


        LeftSwitchClick();
        RightSwitchClick();
    }

    private void LeftSwitchClick(){
        //toggle left side blades
        if(left_blades_out){
            RotateAnimation anim = new RotateAnimation(0f, 95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.setFillAfter(true);
            knife3.setEnabled(false);
            knife3.startAnimation(anim);

            RotateAnimation anim2 = new RotateAnimation(0f, -60f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(1500);
            anim2.setFillAfter(true);
            knife5.setEnabled(false);
            knife5.startAnimation(anim2);

            RotateAnimation anim3 = new RotateAnimation(0f,-50f,Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(1500);
            anim3.setFillAfter(true);
            knife6.setEnabled(false);
            knife6.startAnimation(anim3);
            left_blades_out = false;
        }else{
            RotateAnimation anim = new RotateAnimation(95f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            knife3.startAnimation(anim);
            knife3.setEnabled(true);

            RotateAnimation anim2 = new RotateAnimation(-60f, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(1500);
            knife5.startAnimation(anim2);
            knife5.setEnabled(true);

            RotateAnimation anim3 = new RotateAnimation(-50f, 0f,Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(1500);
            knife6.startAnimation(anim3);
            knife6.setEnabled(true);
            left_blades_out = true;
        }
    }

    private void RightSwitchClick(){
        //toggle right side blades
        if(right_blades_out){
            RotateAnimation anim = new RotateAnimation(0f, -35f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.setFillAfter(true);
            knife1.setEnabled(false);
            knife1.startAnimation(anim);

            RotateAnimation anim2 = new RotateAnimation(0f, -85f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 1f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(1500);
            anim2.setFillAfter(true);
            knife2.setEnabled(false);
            knife2.startAnimation(anim2);

            RotateAnimation anim3 = new RotateAnimation(0f, 75f,Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(1500);
            anim3.setFillAfter(true);
            knife4.setEnabled(false);
            knife4.startAnimation(anim3);
            right_blades_out = false;
        }
        else{
            RotateAnimation anim = new RotateAnimation(-35f, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            knife1.startAnimation(anim);
            knife1.setEnabled(true);

            RotateAnimation anim2 = new RotateAnimation(-85f, 0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 1f);
            anim2.setInterpolator(new LinearInterpolator());
            anim2.setDuration(1500);
            knife2.startAnimation(anim2);
            knife2.setEnabled(true);

            RotateAnimation anim3 = new RotateAnimation(75f, 0f,Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
            anim3.setInterpolator(new LinearInterpolator());
            anim3.setDuration(1500);
            knife4.startAnimation(anim3);
            knife4.setEnabled(true);
            right_blades_out = true;
        }
    }

    private final View.OnTouchListener changeColorListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(final View v, MotionEvent event) {

            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = 0;
            try {
                color = bmp.getPixel((int) event.getX(), (int) event.getY());
            } catch (Exception e) {
                // e.printStackTrace();
            }
            if (color == Color.TRANSPARENT)
                return false;
            else {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(v.getId() == knife1_id){
                            // Launch alarm
                            Intent alarm_intent = new Intent(MainActivity.this, AlarmActivity.class);
                            startActivity(alarm_intent);
                        }else if(v.getId() == knife2_id){
                            // Calender
                            AddEventToCalender();
                        }else if(v.getId() == knife3_id){
                            // Launch Stopwatch activity
                            Intent stopwatch_intent = new Intent(MainActivity.this, StopwatchActivity.class);
                            startActivity(stopwatch_intent);
                        }else if(v.getId() == knife4_id){
                            // Launch Maps
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<lat>,<long>?q=40.742853,-74.177029(NJIT Campus)"));
                            startActivity(intent);
                        }else if(v.getId() == knife5_id){
                            // Search for a contact
                            Intent search_intent = new Intent(MainActivity.this, SearchContactActivity.class);
                            startActivity(search_intent);
                        }else if(v.getId() == knife6_id){
                            // Redial most recent call
                            RedialLastCall();
                        }
                        break;
                    default:
                        break;
                }
                return true;

            }
        }
    };

    protected void RedialLastCall(){
        String lastDialed;
        try{
            lastDialed = CallLog.Calls.getLastOutgoingCall(getApplicationContext());
        }catch (Exception e){
            RequestCallLogPermission();
            return;
        }

        Log.d("DEBUG: ", "Last dialed: " + lastDialed);
        startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+lastDialed)));
    }

    private void RequestCallLogPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALL_LOG)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOGS);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOGS);
            }
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        String lastDialed = "";
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALL_LOGS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    lastDialed = CallLog.Calls.getLastOutgoingCall(getApplicationContext());
                    Log.d("DEBUG: ", "Last dialed: " + lastDialed);
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+lastDialed)));
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+lastDialed)));
                }
                return;
            }
        }
    }

    private void AddEventToCalender(){
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal.getTimeInMillis()+60*60*1000);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.putExtra(CalendarContract.Events.TITLE, "My Event Title");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "My Event Description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "123 Street ave, State, US 49320");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
        startActivity(intent);
    }

}
