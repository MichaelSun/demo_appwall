package com.michael.android.apis.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.michael.android.apis.CheckPointReceiver;
import com.michael.android.apis.config.Config;

public class TimerUtils {
    
    public static void startAutoSendMessage(Context context, long last_time) {
        cancelAutoSendMessage(context);
        Config.LOGD("[[startAutoSendMessage]] >>>>>>");
        Intent intent = new Intent(context, CheckPointReceiver.class);
        intent.setAction(Config.ACTION_TIMER_CHECK);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        long currentTime = System.currentTimeMillis();
        long firstTime = currentTime;
        
        if (last_time != 0) {
            if ((currentTime - last_time) > Config.CHECK_DELAY_TIME) {
                firstTime = currentTime + Config.TEMP_DELAY_TIME;
            } else {
                firstTime = last_time + Config.CHECK_DELAY_TIME;
            }
        } else {
            firstTime = currentTime + Config.TEMP_DELAY_TIME;
        }
        
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, Config.CHECK_DELAY_TIME, sender);
    }
    
    public static void cancelAutoSendMessage(Context context) {
        Config.LOGD("[[cancelAutoSendMessage]]");
        Intent intent = new Intent(context, CheckPointReceiver.class);
        intent.setAction(Config.ACTION_TIMER_CHECK);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }
}
