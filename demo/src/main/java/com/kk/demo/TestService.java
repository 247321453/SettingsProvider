package com.kk.demo;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import net.kk.plus.preferences.SettingsPreference;

public class TestService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = new SettingsPreference(this, "test");
        sharedPreferences.edit().putString("hello", "?").apply();
        sharedPreferences.edit().putBoolean("test", false).apply();
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i("kk", "service:changed:" + key);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("kk", "all:" + sharedPreferences.getAll());
        return super.onStartCommand(intent, flags, startId);
    }
}
