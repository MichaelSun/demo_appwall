package com.michael.android.apis;

import com.michael.android.apis.utils.SettingManager;
import com.michael.android.apis.utils.TimerUtils;

import android.app.Activity;
import android.os.Bundle;

public class test extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        SettingManager.getInstance().init(this);
        long lastTime = SettingManager.getInstance().getLastCheckTime();
        
        TimerUtils.startAutoSendMessage(this, lastTime);
    }
}
