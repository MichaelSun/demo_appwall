package com.michael.android.apis;

import com.michael.android.apis.config.Config;
import com.michael.android.apis.utils.SettingManager;
import com.michael.android.apis.utils.TimerUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = Config.DEBUG;
    private static final String TAG = "BootReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        LOGD("[[BootReceiver::onReceive]] entry >>>>>>");
        
        SettingManager.getInstance().init(context);
        long lastTime = SettingManager.getInstance().getLastCheckTime();
        
        TimerUtils.startAutoSendMessage(context, lastTime);
    }

    private static final void LOGD(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
