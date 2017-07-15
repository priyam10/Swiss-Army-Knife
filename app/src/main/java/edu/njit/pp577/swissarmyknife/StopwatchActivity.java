package edu.njit.pp577.swissarmyknife;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private TextView stopwatch_time;
    private ImageView start_view, stop_view, reset_view;
    private Handler stopwatchHandler;
    long start_time = 0L, time_millis = 0L, time_buffer = 0L, updated_time = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        stopwatchHandler = new Handler();
        stopwatch_time = (TextView) findViewById(R.id.stopwatch_timer);
        start_view = (ImageView) findViewById(R.id.start_image);
        stop_view = (ImageView) findViewById(R.id.stop_image);
        reset_view = (ImageView) findViewById(R.id.reset_image);
        start_view.setEnabled(true);
        stop_view.setEnabled(false);
        reset_view.setEnabled(false);
        start_view.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                StartTimer();
            }
        });

        stop_view.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                StopTimer();
            }
        });

        reset_view.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ResetTimer();
            }
        });
    }

    public void StartTimer(){
        start_view.setEnabled(false);
        stop_view.setEnabled(true);
        reset_view.setEnabled(false);
        start_time = SystemClock.uptimeMillis();
        stopwatchHandler.postDelayed(mRunnable, 0);
    }

    public void StopTimer(){
        start_view.setEnabled(true);
        stop_view.setEnabled(false);
        reset_view.setEnabled(true);
        time_buffer+= time_millis;
        stopwatchHandler.removeCallbacks(mRunnable);
    }

    public void ResetTimer(){
        start_time = 0L;
        time_millis = 0L;
        time_buffer = 0L;
        updated_time = 0L;
        start_view.setEnabled(true);
        stop_view.setEnabled(false);
        reset_view.setEnabled(false);
        stopwatch_time.setText(String.format("%02d:%02d:%02d", 0, 0, 0));
        stopwatchHandler.removeCallbacks(mRunnable);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run(){
            time_millis = SystemClock.uptimeMillis() - start_time;
            updated_time = time_buffer + time_millis;
            int seconds = (int) (updated_time/1000);
            int minutes = seconds/60;
            seconds%= 60;
            int milliseconds = (int) (updated_time%1000);
            stopwatch_time.setText(String.format("%02d:%02d:%02d", minutes, seconds, milliseconds));
            stopwatchHandler.postDelayed(this, 0);
        }
    };
}
