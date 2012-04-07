package com.michael.android.apis;

import net.youmi.android.appoffers.YoumiOffersManager;
import net.youmi.android.appoffers.YoumiPointsManager;

import com.michael.android.apis.config.Config;
import com.michael.android.apis.utils.NotifyUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class FakeActivity extends Activity {

    private boolean mIsFirst;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        flags |= WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
        getWindow().addFlags(flags);
        
        Config.LOGD("[[FakeActivity::onCreate]]");
        YoumiOffersManager.init(this, Config.APP_ID, Config.APP_SECRET_KEY);
        int point = YoumiPointsManager.queryPoints(this);
//        if (point >= Config.COST_POINT) {
//            YoumiPointsManager.spendPoints(this, Config.COST_POINT);
//        }
        
        if (point >= Config.COST_POINT) {
            mIsFirst = true;
        }
    }
    
    public void onResume() {
        Config.LOGD("[[FakeActivity::onResume]]");
        super.onResume();
        
        if (!mIsFirst) {
            mIsFirst = true;
            Config.LOGD("[[CheckPointReceiver::onReceive]] show app wall <<<<>>>>>");
            YoumiOffersManager.showOffers(this, YoumiOffersManager.TYPE_REWARD_OFFERS);
        } else {
            int point = YoumiPointsManager.queryPoints(this);
            if (point >= Config.COST_POINT) {
                NotifyUtils.clearNotify(this);
            }
            
            finish();
        }
    }
}
