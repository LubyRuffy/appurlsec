package com.tencent.cloudsdk;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import org.apache.cordova.NetworkManager;
import org.apache.http.conn.util.InetAddressUtils;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class em {
    private static final String a;

    static {
        a = em.class.getName();
    }

    private em() {
    }

    public static String a(String r2_String) {
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) GlobalContext.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            r2_String = (r0_NetworkInfo == null || r0_NetworkInfo.getType() != 1) ? new StringBuilder(String.valueOf(r2_String)).append(a()).toString() : new StringBuilder(String.valueOf(r2_String)).append(d()).toString();
        } catch (Exception e) {
        }
        return r2_String;
    }

    public static short a() {
        short r0s;
        if (GlobalContext.getContext() != null) {
            String r0_String = ((TelephonyManager) GlobalContext.getContext().getSystemService("phone")).getSimOperator();
            if (r0_String != null) {
                if (r0_String.equals("46000") || r0_String.equals("46002") || r0_String.equals("46007") || r0_String.equals("46020")) {
                    r0s = (short) 4;
                } else if (r0_String.equals("46001") || r0_String.equals("46006")) {
                    r0s = (short) 2;
                } else if (r0_String.equals("46003") || r0_String.equals("46005")) {
                    r0s = (short) 1;
                }
            }
            r0s = (short) 0;
        } else {
            r0s = (short) 0;
        }
        return r0s;
    }

    public static int b() {
        int r0i;
        try {
            if (GlobalContext.getContext() != null) {
                ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) GlobalContext.getContext().getSystemService("connectivity");
                if (r0_ConnectivityManager == null) {
                    r0i = 0;
                    return r0i;
                } else {
                    NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
                    if (r0_NetworkInfo == null) {
                        r0i = -1;
                        return r0i;
                    } else {
                        if (r0_NetworkInfo.isAvailable() && r0_NetworkInfo.isConnected()) {
                            if (r0_NetworkInfo.getType() == 1) {
                                r0i = 1;
                            } else if (r0_NetworkInfo.getType() == 0) {
                                switch (r0_NetworkInfo.getSubtype()) {
                                    case XListViewHeader.STATE_READY:
                                    case XListViewHeader.STATE_REFRESHING:
                                    case XListViewFooter.STATE_NODATA:
                                    case ShareUtils.SHARE_COLLECT:
                                    case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                                        r0i = XListViewHeader.STATE_REFRESHING;
                                        break;
                                    case XListViewFooter.STATE_NOMORE:
                                    case ShareUtils.SHARE_SMS:
                                    case ShareUtils.SHARE_COPY:
                                    case Base64.DONT_BREAK_LINES:
                                    case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                                    case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                                    case NearbySelectView.TIME_15MIN:
                                        r0i = XListViewFooter.STATE_NOMORE;
                                        break;
                                    case REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
                                        r0i = XListViewFooter.STATE_NODATA;
                                        break;
                                }
                                r0i = 0;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = -1;
                        }
                        return r0i;
                    }
                }
            }
        } catch (Exception e) {
            WnsClientLog.e(a, new StringBuilder(">>> getNetworkType()\u5f02\u5e38:").append(e.getMessage()).toString());
        }
        r0i = -1;
        return r0i;
    }

    public static String c() {
        String r0_String;
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) GlobalContext.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            r0_String = (r0_NetworkInfo == null || r0_NetworkInfo.getType() != 1) ? String.valueOf(a()) : d();
        } catch (Exception e) {
            r0_String = "0";
        }
        return r0_String;
    }

    public static String d() {
        String r0_String = f();
        return (r0_String == null || r0_String.equals("0")) ? e() : r0_String;
    }

    public static String e() {
        try {
            Iterator r1_Iterator = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (r1_Iterator.hasNext()) {
                Iterator r2_Iterator = Collections.list(((NetworkInterface) r1_Iterator.next()).getInetAddresses()).iterator();
                while (r2_Iterator.hasNext()) {
                    InetAddress r0_InetAddress = (InetAddress) r2_Iterator.next();
                    if (!r0_InetAddress.isLoopbackAddress()) {
                        String r0_String = r0_InetAddress.getHostAddress();
                        if (InetAddressUtils.isIPv4Address(r0_String)) {
                            return r0_String;
                        }
                    }
                }
            }
        } catch (SocketException e) {
        }
        return "0";
    }

    public static String f() {
        try {
            WifiInfo r0_WifiInfo = ((WifiManager) GlobalContext.getContext().getSystemService(NetworkManager.WIFI)).getConnectionInfo();
            return r0_WifiInfo == null ? "0" : r0_WifiInfo.getBSSID();
        } catch (SecurityException e) {
            return "0";
        }
    }
}