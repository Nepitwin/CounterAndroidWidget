/*
 * Copyright 2016 Andreas Sekulski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.widget;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Clock timer ui component.
 *
 * @author Andreas Sekulski
 */
public class Counter extends LinearLayout {

    /**
     * Starting time from clock timer.
     */
    private long startTime;

    /**
     * Handler to call an thread to update ui.
     */
    private Handler handler;

    /**
     * Default timer format 00:00:00 from clock counter.
     */
    private static String DEFAULT_TIMER_FORMAT = "%02d:%02d:%02d";

    /**
     * Update interval in ms.
     */
    private static int UPDATE_INTERVAL = 1000;

    /**
     * Textview counter to update timer interval.
     */
    private TextView counter;

    /**
     * Indicator if clock is running.
     */
    private boolean isTicking;

    /**
     * Constructor to create an counter.
     * @param context Application context.
     */
    public Counter(Context context) {
        this(context, null);
    }

    /**
     * Constructor to create an counter.
     * @param context Application context.
     * @param attrs Attribute set.
     */
    public Counter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor to create an counter.
     * @param context Application context.
     * @param attrs Attribute set.
     * @param defStyleAttr Definition style attributes.
     */
    public Counter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Runs timer if not already running.
     */
    public void runTimer() {
        if(!isTicking) {
            startTime = SystemClock.uptimeMillis();
            handler.post(updateTimerThread);
            isTicking = true;
        }
    }

    /**
     * Stops timer if runs.
     */
    public void stopTimer() {
        if(isTicking) {
            handler.removeCallbacks(updateTimerThread);
            isTicking = false;
        }
    }

    /**
     * Runnable class to run an update timer thread.
     */
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
        counter.setText(calculateTime());
        handler.postDelayed(this, UPDATE_INTERVAL);
        }
    };

    /**
     * Calculates given time period to update ui.
     * @return Calculated timer in an given local format string 00:00:00.
     */
    private String calculateTime() {
        long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

        int secs = (int) (timeInMilliseconds / UPDATE_INTERVAL);
        int min = secs / 60;
        int hour = min / 60;

        hour = hour % 100;
        min = min % 60;
        secs = secs % 60;

        return String.format(DEFAULT_TIMER_FORMAT, hour, min, secs);
    }

    /**
     * Initialization method.
     * @param context Application context.
     */
    private void init(Context context) {
        //Inflate and attach the content
        LayoutInflater.from(context).inflate(R.layout.counter, this);

        counter = (TextView) findViewById(R.id.clockValue);

        isTicking = false;
        handler = new Handler();
        startTime = 0L;
    }
}