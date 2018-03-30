package redroid.storage;

import android.content.Context;
import android.support.annotation.StringDef;

import redroid.util.SharedPrefs;

/**
 * 存储App数据
 *
 * @author RobinVanYang created at 2017-07-04 01:51
 */
public class AppPreferences {
    private static final String PREFERENCE_NAME = "app";
    private static final String KEY_APP_TYPE = "type";

    @StringDef({AppType.APP_TYPE_TECHNICIAN, AppType.APP_TYPE_STORE})
    public @interface AppType{
        String APP_TYPE_TECHNICIAN = "1";
        String APP_TYPE_STORE = "2";
    }

    private SharedPrefs mAppPref;

    public static AppPreferences getInstance(Context context) {
        return new AppPreferences(context);
    }

    private AppPreferences(Context context) {
        mAppPref = new SharedPrefs.Builder(context).preferenceName(PREFERENCE_NAME).build();
    }

    public void setAppType(@AppType String appType) {
        mAppPref.editMode().setStringPreference(KEY_APP_TYPE, appType).save();
    }

    public String getAppType() {
        return mAppPref.getStringPreference(KEY_APP_TYPE);
    }
}
