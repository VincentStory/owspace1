package redroid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * App帮助类
 *
 * @author RobinVanYang created at 2017-06-28 19:03.
 */

public class AppUtils {
    private static final String TAG = "AppUtils";
    /**
     * 获取App版本标识
     * @param context
     * @return version info
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取App版本号
     *
     * @param context
     * @return app versionCode.
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            Log.d(TAG, "getVersionCode: " + packageInfo.versionCode);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isNewAppVersion(Context context) {
        int storedAppVersionCode = SharedPrefs.Builder.builder(context).defaultPref().build()
                .getIntegerPreference("appVersionCode",0);
        Log.d(TAG, "storedAppVersionCode: " + storedAppVersionCode);
        Log.d(TAG, "versionCode: " + AppUtils.getVersionCode(context));
        return AppUtils.getVersionCode(context) != storedAppVersionCode;
    }

    public static void resetNewAppVersionFlag(Context context) {
        SharedPrefs.Builder.builder(context).defaultPref().build().editMode()
                .setIntegerPreference("appVersionCode", AppUtils.getVersionCode(context)).save();
    }
}
