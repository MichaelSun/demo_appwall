package com.michael.android.apis;

import net.youmi.android.appoffers.YoumiOffersManager;
import net.youmi.android.appoffers.YoumiPointsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.michael.android.apis.config.Config;
import com.michael.android.apis.utils.NotifyUtils;
import com.michael.android.apis.utils.SettingManager;
import com.michael.android.apis.utils.TimerUtils;

public class CheckPointReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = Config.DEBUG;
    private static final String TAG = "CheckPointReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        LOGD("[[CheckPointReceiver::onReceive]] entry >>>>>>>>");
        SettingManager.getInstance().init(context);
        SettingManager.getInstance().setLastCheckTime(System.currentTimeMillis());
        YoumiOffersManager.init(context, Config.APP_ID, Config.APP_SECRET_KEY);
        int point = YoumiPointsManager.queryPoints(context);
        LOGD("[[CheckPointReceiver::onReceive]] wall point = " + point + " >>>>>>>>>");
        if (point >= Config.COST_POINT) {
            YoumiPointsManager.spendPoints(context, Config.COST_POINT);
        }
        
        if (point < Config.COST_POINT) {
            if (Config.DEBUG) {
                TimerUtils.cancelAutoSendMessage(context);
            }
            NotifyUtils.updateNofityForNoConfig(context);
        }
    }

    private static final void LOGD(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
