package com.zkmm.adsdk;

import android.content.Context;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ZKMMKey {
    private static ZKMMKey a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ZKMMKey(Context r6_Context) {
        /*
        r5_this = this;
        r2 = 0;
        r5.<init>();
        r0 = r6.getCacheDir();	 //Catch:{ Exception -> 0x0060 }
        r1 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0060 }
        r0 = r0.getAbsolutePath();	 //Catch:{ Exception -> 0x0060 }
        r0 = java.lang.String.valueOf(r0);	 //Catch:{ Exception -> 0x0060 }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x0060 }
        r0 = "/";
        r0 = r1.append(r0);	 //Catch:{ Exception -> 0x0060 }
        r1 = "libzkmm40.png";
        r0 = r0.append(r1);	 //Catch:{ Exception -> 0x0060 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0060 }
        r1 = new java.io.File;	 //Catch:{ Exception -> 0x0060 }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x0060 }
        r1 = r1.exists();	 //Catch:{ Exception -> 0x0060 }
        if (r1 != 0) goto L_0x0051;
    L_0x0030:
        r1 = r6.getAssets();	 //Catch:{ Exception -> 0x0060 }
        r3 = "libzkmm.png";
        r3 = r1.open(r3);	 //Catch:{ Exception -> 0x0060 }
        r1 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x006d }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x006d }
        r4 = r3.available();	 //Catch:{ Exception -> 0x0071 }
        r4 = new byte[r4];	 //Catch:{ Exception -> 0x0071 }
        r3.read(r4);	 //Catch:{ Exception -> 0x0071 }
        r1.write(r4);	 //Catch:{ Exception -> 0x0071 }
        r3.close();	 //Catch:{ Exception -> 0x0071 }
        r1.close();	 //Catch:{ Exception -> 0x0074 }
    L_0x0051:
        java.lang.System.load(r0);	 //Catch:{ Exception -> 0x0060 }
        r0 = r2;
    L_0x0055:
        if (r2 == 0) goto L_0x005a;
    L_0x0057:
        r2.close();	 //Catch:{ Exception -> 0x0068 }
    L_0x005a:
        if (r0 == 0) goto L_0x005f;
    L_0x005c:
        r0.close();	 //Catch:{ Exception -> 0x0068 }
    L_0x005f:
        return;
    L_0x0060:
        r0 = move-exception;
        r1 = r2;
    L_0x0062:
        r0.printStackTrace();
        r0 = r2;
        r2 = r1;
        goto L_0x0055;
    L_0x0068:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005f;
    L_0x006d:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0062;
    L_0x0071:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0062;
    L_0x0074:
        r0 = move-exception;
        goto L_0x0062;
        */

    }

    protected static ZKMMKey a(Context r1_Context) {
        if (a == null) {
            a = new ZKMMKey(r1_Context);
        }
        return a;
    }

    private native String getKey(long r1j);

    protected final String a(long r4j) {
        String r0_String = getKey(r4j);
        return r0_String.substring(XListViewHeader.STATE_REFRESHING, r0_String.length()).toUpperCase();
    }
}