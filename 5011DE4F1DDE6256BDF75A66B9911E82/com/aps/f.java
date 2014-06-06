package com.aps;

import android.util.SparseArray;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Const.java
public class f {
    static String a;
    static String b;
    static String c;
    static String d;
    static String e;
    static String f;
    static boolean g;
    static boolean h;
    static long i;
    static long j;
    static boolean k;
    static final SparseArray<String> l;
    static final String[] m;

    static {
        a = null;
        b = null;
        c = null;
        d = RContactStorage.PRIMARY_KEY;
        e = RContactStorage.PRIMARY_KEY;
        f = RContactStorage.PRIMARY_KEY;
        g = false;
        h = true;
        i = 15000;
        j = 30000;
        k = true;
        l = new SparseArray();
        l.append(0, "UNKNOWN");
        l.append(1, "GPRS");
        l.append(XListViewHeader.STATE_REFRESHING, "EDGE");
        l.append(XListViewFooter.STATE_NOMORE, "UMTS");
        l.append(XListViewFooter.STATE_NODATA, "CDMA");
        l.append(ShareUtils.SHARE_SMS, "EVDO_0");
        l.append(ShareUtils.SHARE_COPY, "EVDO_A");
        l.append(ShareUtils.SHARE_COLLECT, "1xRTT");
        l.append(Base64.DONT_BREAK_LINES, "HSDPA");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY, "HSUPA");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO, "HSPA");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE, "IDEN");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, "EVDO_B");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, "LTE");
        l.append(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, "EHRPD");
        l.append(NearbySelectView.TIME_15MIN, "HSPAP");
        String[] r0_StringA = new String[9];
        r0_StringA[0] = "android.permission.ACCESS_COARSE_LOCATION";
        r0_StringA[1] = "android.permission.ACCESS_FINE_LOCATION";
        r0_StringA[2] = "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";
        r0_StringA[3] = "android.permission.ACCESS_NETWORK_STATE";
        r0_StringA[4] = "android.permission.ACCESS_WIFI_STATE";
        r0_StringA[5] = "android.permission.CHANGE_WIFI_STATE";
        r0_StringA[6] = "android.permission.INTERNET";
        r0_StringA[7] = "android.permission.READ_PHONE_STATE";
        r0_StringA[8] = "android.permission.WRITE_EXTERNAL_STORAGE";
        m = r0_StringA;
    }

    private f() {
    }
}