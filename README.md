# SettingsProvider
通过ContentProvider的call方法传递IBinder对象，使多个进程访问同一个SharedPreferences
# SettingsPreference
SettingsPreference继承SharedPreferences接口，使用只需要改对象的构造。
支持registerOnSharedPreferenceChangeListener

```
SharedPreferences sharedPreferences = new SettingsPreference(this, "test");
```