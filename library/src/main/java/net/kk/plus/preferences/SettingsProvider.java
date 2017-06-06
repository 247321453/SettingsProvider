package net.kk.plus.preferences;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;

import net.kk.plus.ISettingsManager;
import net.kk.plus.compact.BundleCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SettingsProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    protected SharedPreferences open(String name) {
        return getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    protected SharedPreferences getSharedPreferences(String name) {
        WeakReference<SharedPreferences> weakReference;
        synchronized (mSharedPreferencesMap) {
            weakReference = mSharedPreferencesMap.get(name);
            if (weakReference == null || weakReference.get() == null) {
                weakReference = new WeakReference<>(open(name));
                mSharedPreferencesMap.put(name, weakReference);
            }
        }
        return weakReference.get();
    }

    private final ConcurrentHashMap<String, WeakReference<SharedPreferences>>
            mSharedPreferencesMap = new ConcurrentHashMap<>();


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return uri.getPath();
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        if (Method_GetSettingsManager.equals(method)) {
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, Arg_Binder, new SettingsManager(
                    getSharedPreferences(arg)));
            return bundle;
        }
        return null;
    }

    public static final String Method_GetSettingsManager = "getSettingsManager";
    public static final String Arg_Binder = "_binder_";

    private class SettingsManager extends ISettingsManager.Stub {
        private final SharedPreferences mSharedPreferences;

        public SettingsManager(SharedPreferences sharedPreferences) {
            mSharedPreferences = sharedPreferences;
        }

        @Override
        public void clearAll() throws RemoteException {
            mSharedPreferences.edit().clear().apply();
        }

        @Override
        public void clear(String key) throws RemoteException {
            mSharedPreferences.edit().remove(key).apply();
        }

        @Override
        public boolean hasKey(String key) throws RemoteException {
            return mSharedPreferences.contains(key);
        }

        @Override
        public boolean getBoolean(String key, boolean def) throws RemoteException {
            return mSharedPreferences.getBoolean(key, def);
        }

        @Override
        public float getFloat(String key, float def) throws RemoteException {
            return mSharedPreferences.getFloat(key, def);
        }

        @Override
        public int getInt(String key, int def) throws RemoteException {
            return mSharedPreferences.getInt(key, def);
        }

        @Override
        public long getLong(String key, long def) throws RemoteException {
            return mSharedPreferences.getLong(key, def);
        }

        @Override
        public String getString(String key, String def) throws RemoteException {
            return mSharedPreferences.getString(key, def);
        }

        @Override
        public List<String> getStringArrayList(String key, List<String> def) throws RemoteException {
            Set<String> val = mSharedPreferences.getStringSet(key, null);
            List<String> res = new ArrayList<>();
            if (val == null) {
                if (def != null) {
                    res.addAll(def);
                }
            } else {
                res.addAll(val);
            }
            return res;
        }

        @Override
        public void putBoolean(String key, boolean def) throws RemoteException {
            mSharedPreferences.edit().putBoolean(key, def).apply();
        }

        @Override
        public void putFloat(String key, float def) throws RemoteException {
            mSharedPreferences.edit().putFloat(key, def).apply();
        }

        @Override
        public void putInt(String key, int def) throws RemoteException {
            mSharedPreferences.edit().putInt(key, def).apply();
        }

        @Override
        public void putLong(String key, long def) throws RemoteException {
            mSharedPreferences.edit().putLong(key, def).apply();
        }

        @Override
        public void putString(String key, String def) throws RemoteException {
            mSharedPreferences.edit().putString(key, def).apply();
        }

        @Override
        public void putStringArrayList(String key, List<String> def) throws RemoteException {
            Set<String> res = new ArraySet<>();
            if (def != null) {
                res.addAll(def);
            }
            mSharedPreferences.edit().putStringSet(key, res).apply();
        }
    }
}
