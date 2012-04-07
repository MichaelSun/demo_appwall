package com.michael.android.apis;

import net.youmi.android.appoffers.YoumiOffersManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.michael.android.apis.config.Config;
import com.michael.android.apis.utils.SettingManager;
import com.michael.android.apis.utils.TimerUtils;

public class CheckPointReceiver extends BroadcastReceiver {

    private static final boolean DEBUG = Config.DEBUG;
    private static final String TAG = "CheckPointReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        LOGD("[[CheckPointReceiver::onReceive]] entry >>>>>>>>");
        SettingManager.getInstance().init(context);
        int point = SettingManager.getInstance().getAppPoint();
        if (point < Config.COST_POINT) {
            YoumiOffersManager.init(context, Config.APP_ID, Config.APP_SECRET_KEY);
            YoumiOffersManager.showOffers(context, YoumiOffersManager.TYPE_REWARD_OFFERS);
            
            TimerUtils.cancelAutoSendMessage(context);
        }
    }

    private static final void LOGD(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
