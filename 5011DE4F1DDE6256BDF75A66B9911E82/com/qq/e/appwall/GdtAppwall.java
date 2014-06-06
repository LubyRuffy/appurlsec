package com.qq.e.appwall;

import android.content.Context;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.plugininterfaces.AppWallADViewInterface;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public class GdtAppwall {
    private static GdtAppwall b;
    private static Context c;
    private static String d;
    private static String e;
    private static boolean f;
    private AppWallADViewInterface a;

    public GdtAppwall(Context r4_Context, String r5_String, String r6_String, boolean r7z) {
        if (a.a(r4_Context)) {
            if (StringUtil.isEmpty(r5_String) || StringUtil.isEmpty(r6_String) || r4_Context == null) {
                Object[] r1_ObjectA = new Object[3];
                r1_ObjectA[0] = r5_String;
                r1_ObjectA[1] = r6_String;
                r1_ObjectA[2] = r4_Context;
                GDTLogger.e(String.format("AppWall Contructor paras error,appid=%s,posId=%s,context=%s", r1_ObjectA));
            } else if (GDTADManager.getInstance().initWith(r4_Context, r5_String)) {
                try {
                    this.a = GDTADManager.getInstance().getPM().getAppWallViewFactory().getAppWallView(r4_Context, r5_String, r6_String, r7z);
                } catch (com.qq.e.v2.managers.plugin.a e) {
                    GDTLogger.report("Fail to init AppWall plugin", e);
                }
            } else {
                GDTLogger.report("Fail to init GDTADManager");
            }
        } else {
            GDTLogger.e("Fail to init AppWall, please check that all items are added correctly in AndroidManifest.xml");
        }
    }

    public static void init(Context r1_Context, String r2_String, String r3_String) {
        init(r1_Context, r2_String, r3_String, false);
    }

    public static void init(Context r0_Context, String r1_String, String r2_String, boolean r3z) {
        c = r0_Context;
        d = r1_String;
        e = r2_String;
        f = r3z;
    }

    public static void showAppwall() {
        if (b == null) {
            b = new GdtAppwall(c, d, e, f);
        }
        b.doShowAppWall();
    }

    public void doShowAppWall() {
        if (this.a != null) {
            this.a.showAppWall();
        }
    }
}