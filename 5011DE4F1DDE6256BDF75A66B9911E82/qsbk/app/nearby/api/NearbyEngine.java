package qsbk.app.nearby.api;

import android.content.Context;
import android.location.LocationManager;
import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import qsbk.app.QsbkApp;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

public class NearbyEngine {
    public static final String NEARBY_URL_PREFIX = "http://nearby.qiushibaike.com";
    public static final int RESP_NEED_INFO = -110;
    public static final int RESP_SUCCESS = 0;
    public static final String URL_CLEAR = "http://nearby.qiushibaike.com/nearby/clear_loc";
    public static final String URL_FETCH = "http://nearby.qiushibaike.com/nearby/fetch";
    public static final String URL_USER_INFO = "http://nearby.qiushibaike.com/user/%s/detail";
    private static NearbyEngine a;

    static {
        a = null;
    }

    private NearbyEngine() {
    }

    public static synchronized NearbyEngine instance() {
        NearbyEngine r0_NearbyEngine;
        synchronized (NearbyEngine.class) {
            if (a == null) {
                a = new NearbyEngine();
            }
            r0_NearbyEngine = a;
        }
        return r0_NearbyEngine;
    }

    public String getCurrentUserInfoUrl() {
        LogUtil.d("currentUser:" + QsbkApp.currentUser.userId);
        String r0_String = URL_USER_INFO;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = QsbkApp.currentUser.userId;
        return String.format(r0_String, r1_ObjectA);
    }

    public String getLocalFilterSex() {
        String r0_String = getUserLocalPrefByKey("nearby_filter_sex_%s");
        LogUtil.d("femail:" + NearbySelectView.GENDER_FEMALE.equals(r0_String));
        if (r0_String != null) {
            if (NearbySelectView.GENDER_FEMALE.equals(r0_String) || NearbySelectView.GENDER_MALE.equals(r0_String)) {
                return r0_String;
            }
        }
        return NearbySelectView.GENDER_ALL;
    }

    public int getLocalFilterTime() {
        int r1i;
        try {
            r1i = Integer.parseInt(getUserLocalPrefByKey("nearby_filter_lastlogin_%s"));
        } catch (Exception e) {
            r1i = 4320;
        }
        return (r1i == 15 || r1i == 1440 || r1i == 4320 || r1i == 60) ? r1i : NearbySelectView.TIME_3DAY;
    }

    public boolean getLocalUserFlagByKey(String r2_String) {
        return !TextUtils.isEmpty(getUserLocalPrefByKey(r2_String));
    }

    public String getUserLocalPrefByKey(String r7_String) {
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = QsbkApp.currentUser.userId;
        String r0_String = String.format(r7_String, r0_ObjectA);
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(r0_String);
        Object[] r3_ObjectA = new Object[2];
        r3_ObjectA[0] = r0_String;
        r3_ObjectA[1] = r1_String;
        LogUtil.d(String.format("get key:%s value:%s", r3_ObjectA));
        return r1_String;
    }

    public boolean isLocationServiceEnabled(Context r5_Context) {
        LocationManager r0_LocationManager = (LocationManager) r5_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        boolean r1z = r0_LocationManager.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER);
        boolean r0z = r0_LocationManager.isProviderEnabled(LocationManagerProxy.NETWORK_PROVIDER);
        LogUtil.d("gps enabled:" + r1z);
        LogUtil.d("networkd enabled:" + r0z);
        return r1z || r0z;
    }

    public boolean isNearbyInfoComplete() {
        return getLocalUserFlagByKey("nearby_complete_%s");
    }

    public boolean isNearbyNoMoreWarn() {
        return getLocalUserFlagByKey("nearby_nowarn_%s");
    }

    public void setLocalFilterLastLogin(int r3i) {
        setLocalUserFlagByKey("nearby_filter_lastlogin_%s", String.valueOf(r3i));
    }

    public void setLocalFilterSex(String r2_String) {
        setLocalUserFlagByKey("nearby_filter_sex_%s", r2_String);
    }

    public void setLocalUserFlagByKey(String r6_String, String r7_String) {
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = QsbkApp.currentUser.userId;
        String r0_String = String.format(r6_String, r0_ObjectA);
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = r0_String;
        r2_ObjectA[1] = r7_String;
        LogUtil.d(String.format("set key:%s value:%s", r2_ObjectA));
        SharePreferenceUtils.setSharePreferencesValue(r0_String, r7_String);
    }

    public void setLocalUserFlagByKey(String r4_String, boolean r5z) {
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = QsbkApp.currentUser.userId;
        String r0_String = String.format(r4_String, r0_ObjectA);
        if (r5z) {
            SharePreferenceUtils.setSharePreferencesValue(r0_String, "true");
        } else {
            SharePreferenceUtils.remove(r0_String);
        }
    }

    public void setNearbyInfoComplete(boolean r2z) {
        setLocalUserFlagByKey("nearby_complete_%s", r2z);
    }

    public void setNearbyNoMoreWarn() {
        setNearbyNoMoreWarn(true);
    }

    public void setNearbyNoMoreWarn(boolean r2z) {
        setLocalUserFlagByKey("nearby_nowarn_%s", r2z);
    }
}