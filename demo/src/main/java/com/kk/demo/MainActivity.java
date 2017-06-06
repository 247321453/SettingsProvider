package com.kk.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import net.kk.plus.preferences.ArraySet;
import net.kk.plus.preferences.SettingsPreference;

import java.util.Set;

public class MainActivity extends Activity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = new SettingsPreference(this, "test");
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.i("kk", "changed:" + key);
            }
        });
        Set<String> list = new ArraySet<>();
        list.add("1");
        sharedPreferences.edit().putStringSet("list", list).apply();
        startService(new Intent(this, TestService.class));

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences.edit().putBoolean("ui", false).apply();
            }
        }, 1000);
    }

}
