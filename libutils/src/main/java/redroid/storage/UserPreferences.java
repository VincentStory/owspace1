package redroid.storage;

import android.content.Context;

import redroid.util.SharedPrefs;

/**
 * 存储用户数据
 *
 * @author RobinVanYang created at 2017-07-04 01:51
 */
public class UserPreferences {
    private static final String PREFERENCE_NAME = "user";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_TOKEN = "token";
    private static final String KEY_USER_STATUS = "userStatus";
    private static final String KEY_USER_PHONE = "phone";
    private static final String KEY_USER_TYPE = "type";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_IMAGEURL = "imageUrl";
    private static final String KEY_USER_WORKNUMBER = "workNumber";
    private static final String KEY_USER_AREAID = "areaId";
    private static final String KEY_USER_AREA = "area";

    private SharedPrefs mUserPref;

    public static UserPreferences getInstance(Context context) {
        return new UserPreferences(context);
    }

    private UserPreferences(Context context) {
        mUserPref = new SharedPrefs.Builder(context).preferenceName(PREFERENCE_NAME).build();
    }

    public UserPreferences editMode() {
        mUserPref.editMode();
        return this;
    }

    public void clear() {
        mUserPref.clear();
    }

    public String getUserId() {
        return mUserPref.getStringPreference(KEY_USER_ID);
    }

    public UserPreferences setUserId(String userId) {
        mUserPref.setStringPreference(KEY_USER_ID, userId);
        return this;
    }

    public UserPreferences setUserName(String userName) {
        mUserPref.setStringPreference(KEY_USER_NAME, userName);
        return this;
    }

    public String getUserName() {
        return mUserPref.getStringPreference(KEY_USER_NAME);
    }

    public UserPreferences setToken(String token) {
        mUserPref.setStringPreference(KEY_USER_TOKEN, token);
        return this;
    }

    public String getToken() {
        return mUserPref.getStringPreference(KEY_USER_TOKEN);
    }

    public UserPreferences setUserStatus(String userStatus) {
        mUserPref.setStringPreference(KEY_USER_STATUS, userStatus);
        return this;
    }

    public String getUserStatus() {
        return mUserPref.getStringPreference(KEY_USER_STATUS);
    }

    public UserPreferences setPhone(String phone) {
        mUserPref.setStringPreference(KEY_USER_PHONE, phone);
        return this;
    }

    public String getPhone() {
        return mUserPref.getStringPreference(KEY_USER_PHONE);
    }

    public UserPreferences setUserType(String userType) {
        mUserPref.setStringPreference(KEY_USER_TYPE, userType);
        return this;
    }

    public String getUserType() {
        return mUserPref.getStringPreference(KEY_USER_TYPE);
    }

    public UserPreferences setAddress(String address) {
        mUserPref.setStringPreference(KEY_USER_ADDRESS, address);
        return this;
    }

    public String getAddress() {
        return mUserPref.getStringPreference(KEY_USER_ADDRESS);
    }

    public void save() {
        mUserPref.save();
    }

    public UserPreferences setImageUrl(String imageUrl) {
        mUserPref.setStringPreference(KEY_USER_IMAGEURL, imageUrl);
        return this;
    }

    public String getImageUrl() {
        return mUserPref.getStringPreference(KEY_USER_IMAGEURL);
    }

    public UserPreferences setWorkNumber(String workNo) {
        mUserPref.setStringPreference(KEY_USER_WORKNUMBER, workNo);
        return this;
    }

    public String getWorkNumber() {
        return mUserPref.getStringPreference(KEY_USER_WORKNUMBER);
    }

    public UserPreferences setAreaId(int areaId) {
        mUserPref.setIntegerPreference(KEY_USER_AREAID, areaId);
        return this;
    }

    public int getAreaId() {
        return mUserPref.getIntegerPreference(KEY_USER_AREAID, 0);
    }

    public UserPreferences setArea(String area) {
        mUserPref.setStringPreference(KEY_USER_AREA, area);
        return this;
    }

    public String getArea(){
        return mUserPref.getStringPreference(KEY_USER_AREA);
    }
}
