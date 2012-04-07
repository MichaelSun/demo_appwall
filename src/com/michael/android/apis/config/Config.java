package com.michael.android.apis.config;

import android.util.Log;

public class Config {

    public static final boolean DEBUG = false;
    
    public static final String TAG = "com.michael.android.apis";
    
    public static final long RELEASE_DELAY_TIME = ((long) (2 * 24)) * 60 * 60 * 1000;
    
    public static final long RELEASE_TEMP_DELAY_TIME = 5 * 60 * 1000;
    
    public static final long DEBUG_DELAY_TIME = 10 * 1000;
    
    public static final long DEBUG_TEMP_DELAY_TIME = 10 * 1000;
    
    public static final long CHECK_DELAY_TIME = DEBUG ? DEBUG_DELAY_TIME : RELEASE_DELAY_TIME;
    
    public static final long TEMP_DELAY_TIME = DEBUG ? DEBUG_TEMP_DELAY_TIME : RELEASE_TEMP_DELAY_TIME;
    
    public static final int COST_POINT = 100;
    
    public static final String APP_ID = "c50de3eac61873af";
    
    public static final String APP_SECRET_KEY = "5580cb89acac9809";
    
    public static final String ACTION_TIMER_CHECK = "com.michael.android.apis.check";
    
    public static final String ACTION_START_FAKE = "com.michael.android.apis.fake";
    
    public static final void LOGD(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
