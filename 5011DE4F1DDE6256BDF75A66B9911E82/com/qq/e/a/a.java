package com.qq.e.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import qsbk.app.widget.listview.XListViewFooter;

public final class a {
    private static String a;

    static {
        a(VERSION.SDK);
        String r0_String = Build.MODEL;
    }

    private static int a_(String r1_String) {
        try {
            return Integer.parseInt(r1_String);
        } catch (NumberFormatException e) {
            return XListViewFooter.STATE_NOMORE;
        }
    }

    public static String a_(Context r1_Context) {
        try {
            a = ((TelephonyManager) r1_Context.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
        }
        return a;
    }
}