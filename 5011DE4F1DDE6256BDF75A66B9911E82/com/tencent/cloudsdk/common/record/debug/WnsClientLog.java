package com.tencent.cloudsdk.common.record.debug;

import com.tencent.cloudsdk.ah;
import com.tencent.cloudsdk.common.record.info.Global;
import com.tencent.cloudsdk.y;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class WnsClientLog extends ah {
    public static WnsClientLog instance;

    static {
        instance = null;
    }

    public WnsClientLog() {
        this.fileTracer = new y(CLIENT_CONFIG);
    }

    public static final void d(String r3_String, String r4_String) {
        if (getInstance() != null) {
            getInstance().trace(XListViewHeader.STATE_REFRESHING, r3_String, r4_String, null);
        }
    }

    public static final void d(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (getInstance() != null) {
            getInstance().trace(XListViewHeader.STATE_REFRESHING, r2_String, r3_String, r4_Throwable);
        }
    }

    public static final void e(String r3_String, String r4_String) {
        if (getInstance() != null) {
            getInstance().trace(Base64.URL_SAFE, r3_String, r4_String, null);
        }
    }

    public static final void e(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (getInstance() != null) {
            getInstance().trace(Base64.URL_SAFE, r2_String, r3_String, r4_Throwable);
        }
    }

    public static void ensureLogsToFile() {
        if (getInstance() != null) {
            getInstance().flush();
        }
    }

    public static WnsClientLog getInstance() {
        if (Global.getContext() == null) {
            instance = null;
            return null;
        } else {
            if (instance == null) {
                synchronized (WnsClientLog.class) {
                    if (instance == null) {
                        instance = new WnsClientLog();
                    }
                }
            }
            return instance;
        }
    }

    public static final void i(String r3_String, String r4_String) {
        if (getInstance() != null) {
            getInstance().trace(XListViewFooter.STATE_NODATA, r3_String, r4_String, null);
        }
    }

    public static final void i(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (getInstance() != null) {
            getInstance().trace(XListViewFooter.STATE_NODATA, r2_String, r3_String, r4_Throwable);
        }
    }

    public static final void v(String r3_String, String r4_String) {
        WnsClientLog r0_WnsClientLog = getInstance();
        if (r0_WnsClientLog != null) {
            r0_WnsClientLog.trace(1, r3_String, r4_String, null);
        }
    }

    public static final void v(String r2_String, String r3_String, Throwable r4_Throwable) {
        WnsClientLog r0_WnsClientLog = getInstance();
        if (r0_WnsClientLog != null) {
            r0_WnsClientLog.trace(1, r2_String, r3_String, r4_Throwable);
        }
    }

    public static final void w(String r3_String, String r4_String) {
        if (getInstance() != null) {
            getInstance().trace(Base64.DONT_BREAK_LINES, r3_String, r4_String, null);
        }
    }

    public static final void w(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (getInstance() != null) {
            getInstance().trace(Base64.DONT_BREAK_LINES, r2_String, r3_String, r4_Throwable);
        }
    }
}