package net.kk.plus.preferences;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import net.kk.plus.ISettingsManager;
import net.kk.plus.compact.BundleCompat;
import net.kk.plus.compact.ContentProviderCompat;

import java.util.List;


public class SettingsPreference {
    private static final String AUTH = ".settings.preference";
    private ISettingsManager mSettingsManager;

    public SettingsPreference(Context context, String name) {
        this(context, context.getPackageName() + AUTH, name);
    }

    public SettingsPreference(Context context, String auth, String prefName) {
        Uri uri = Uri.parse("content://" + auth);
        Bundle res = ContentProviderCompat.call(context, uri, SettingsProvider.Method_GetSettingsManager, prefName, new Bundle());
        if (res != null) {
            IBinder clientBinder = BundleCompat.getBinder(res, SettingsProvider.Arg_Binder);
            mSettingsManager = ISettingsManager.Stub.asInterface(clientBinder);
        }
    }

    public boolean isOpen() {
        return mSettingsManager != null;
    }

    public void clearAll() {
        if (isOpen()) {
            try {
                mSettingsManager.clearAll();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear(String key) {
        if (isOpen()) {
            try {
                mSettingsManager.clear(key);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasKey(String key) {
        if (isOpen()) {
            try {
                return mSettingsManager.hasKey(key);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean getBoolean(String key, boolean def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getBoolean(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public float getFloat(String key, float def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getFloat(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public int getInt(String key, int def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getInt(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public long getLong(String key, long def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getLong(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public String getString(String key, String def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getString(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public List<String> getStringArrayList(String key, List<String> def) {
        if (isOpen()) {
            try {
                return mSettingsManager.getStringArrayList(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public SettingsPreference putBoolean(String key, boolean def) {
        if (isOpen()) {
            try {
                mSettingsManager.putBoolean(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SettingsPreference putFloat(String key, float def) {
        if (isOpen()) {
            try {
                mSettingsManager.putFloat(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SettingsPreference putInt(String key, int def) {
        if (isOpen()) {
            try {
                mSettingsManager.putInt(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SettingsPreference putLong(String key, long def) {
        if (isOpen()) {
            try {
                mSettingsManager.putLong(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SettingsPreference putString(String key, String def) {
        if (isOpen()) {
            try {
                mSettingsManager.putString(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SettingsPreference putStringArrayList(String key, List<String> def) {
        if (isOpen()) {
            try {
                mSettingsManager.putStringArrayList(key, def);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    public SettingsPreference edit() {
        return this;
    }

    public void apply() {
        //none
    }
}
