package com.michael.android.apis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.michael.android.apis.R;

public class SettingManager {
    private static SettingManager gSettingManager = new SettingManager();
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    
    public static SettingManager getInstance() {
        return gSettingManager;
    }
    
    public int getAppPoint() {
        return mSharedPreferences.getInt(mContext.getString(R.string.pref_point), 0);
    }
    
    public void setAppPoint(int point) {
        mEditor.putInt(mContext.getString(R.string.pref_point), point);
        mEditor.commit();
    }
    
    public long getLastCheckTime() {
        return mSharedPreferences.getLong(mContext.getString(R.string.perf_lastcheck_time), 0);
    }
    
    public void setLastCheckTime(long time) {
        mEditor.putLong(mContext.getString(R.string.perf_lastcheck_time), time);
        mEditor.commit();
    }
    
    public void init(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mEditor = mSharedPreferences.edit();
    }
    
    private SettingManager() {
    }
}
