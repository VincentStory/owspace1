package redroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * A pack of helpful getter and setter methods for reading/writing to {@link SharedPreferences}.
 * copied from @see <a href="https://github.com/esripdx/Android-Static-Utils"></a>
 * and do some modify.
 */
final public class SharedPrefs {
    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mEditor;

    private SharedPrefs(Builder builder) {
        mSharedPref = builder.mSharedPref;
    }

    /**
     * before call set method, you must call this method.
     * @return
     */
    public SharedPrefs editMode() {
        mEditor = mSharedPref.edit();
        return this;
    }

    /**
     * after call set method, you must call this method to save changes.
     */
    public void save() {
        mEditor.apply();
    }

    public String getStringPreference(String key) {
        return mSharedPref.getString(key, null);
    }

    public SharedPrefs setStringPreference(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            if (null == mEditor)
                throw new IllegalStateException("you must first call editMode() method before the set method");

            mEditor.putString(key, value);
        }
        return this;
    }

    public float getFloatPreference(String key, float defaultValue) {
        return mSharedPref.getFloat(key, defaultValue);
    }

    public SharedPrefs setFloatPreference(String key, float value) {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.putFloat(key, value);
        return this;
    }

    public long getLongPreference(String key, long defaultValue) {
        return mSharedPref.getLong(key, defaultValue);
    }

    public SharedPrefs setLongPreference(String key, long value) {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.putLong(key, value);
        return this;
    }

    public int getIntegerPreference(String key, int defaultValue) {
        return mSharedPref.getInt(key, defaultValue);
    }

    public SharedPrefs setIntegerPreference(String key, int value) {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.putInt(key, value);
        return this;
    }

    public boolean getBooleanPreference(String key, boolean defaultValue) {
        return mSharedPref.getBoolean(key, defaultValue);
    }

    public SharedPrefs remove(String key) {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.remove(key);
        return this;
    }

    public void clear() {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.clear();
        mEditor.apply();
    }

    public void setBooleanPreference(String key, boolean value) {
        if (null == mEditor)
            throw new IllegalStateException("you must first call editMode() method before the set method");

        mEditor.putBoolean(key, value);
    }

    public static final class Builder {
        private Context mContext;
        private SharedPreferences mSharedPref;

        public Builder(Context context) {
            this.mContext = context;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        public Builder preferenceName(String prefName) {
            if (mContext == null) throw new IllegalStateException("context == null");
            if (prefName == null) throw new IllegalStateException("preference name == null");

            mSharedPref = mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);
            return this;
        }

        public Builder defaultPref() {
            if (mContext == null) throw new IllegalStateException("context == null");

            mSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
            return this;
        }

        public SharedPrefs build() {
            if (mSharedPref == null) throw new IllegalStateException("preference == null");
            return new SharedPrefs(this);
        }
    }
}