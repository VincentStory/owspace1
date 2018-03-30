package redroid.storage;

import android.content.Context;

import redroid.util.SharedPrefs;

/**
 * 指示层状态
 * <p>
 * Created by Administrator on 2018/1/11.
 */

public class GuidePreference {
    private static final String PREFERENCE_NAME = "guide";
    private static final String TECHNICIAN_HAVEORDER_STATUS = "haveOrderStatus";
    private static final String TECHNICIAN_CONSTRUCTION_STATUS = "constructionStatus";

    private SharedPrefs mUserPref;

    public static GuidePreference getInstance(Context context) {
        return new GuidePreference(context);
    }

    private GuidePreference(Context context) {
        mUserPref = new SharedPrefs.Builder(context).preferenceName(PREFERENCE_NAME).build();
    }

    public GuidePreference editMode() {
        mUserPref.editMode();
        return this;
    }

    public void clear() {
        mUserPref.clear();
    }

    public String getHaveOrder() {
        return mUserPref.getStringPreference(TECHNICIAN_HAVEORDER_STATUS);
    }

    public GuidePreference setHaveOrder(String s) {
        mUserPref.setStringPreference(TECHNICIAN_HAVEORDER_STATUS, s);
        return this;
    }

    public String getConstruction() {
        return mUserPref.getStringPreference(TECHNICIAN_CONSTRUCTION_STATUS);
    }

    public GuidePreference setConstruction(String s) {
        mUserPref.setStringPreference(TECHNICIAN_CONSTRUCTION_STATUS, s);
        return this;
    }


    public void save() {
        mUserPref.save();
    }
}
