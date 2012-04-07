package com.michael.android.apis.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.michael.android.apis.FakeActivity;
import com.michael.android.apis.R;

public class NotifyUtils {
    private static final int NOTIFY_ID = 1;
    
    public static void updateNofityForNoConfig(Context context) {
        Intent accountIntent = new Intent();
//        accountIntent.setAction(Config.ACTION_START_FAKE);
        accountIntent.setClass(context, FakeActivity.class);
        PendingIntent intent = PendingIntent.getActivity(context, 0, accountIntent, 0);
        
        Notification notif1 = null;
        
        notif1 = new Notification(android.R.drawable.ic_menu_help, 
                        context.getString(R.string.notify_title),
                        System.currentTimeMillis());
        
        notif1.setLatestEventInfo(context, context.getString(R.string.notify_title),
                                    context.getString(R.string.notify_tips),
                                    intent);
        notif1.defaults = Notification.DEFAULT_ALL;
        notif1.vibrate = new long[] { 100, 250, 100, 500 };
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notif1.flags |= Notification.FLAG_NO_CLEAR;
        nm.notify(NOTIFY_ID, notif1);
    }
    
    public static void clearNotify(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFY_ID);
    }
}
