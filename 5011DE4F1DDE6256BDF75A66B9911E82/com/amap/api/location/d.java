package com.amap.api.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// compiled from: IGPSManager.java
class d implements LocationListener {
    final /* synthetic */ c a;

    d(c r1_c) {
        this.a = r1_c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLocationChanged(Location r9_Location) {
        /*
        r8_this = this;
        r6 = 1;
        com.amap.api.location.a.b = r6;
        r0 = java.lang.System.currentTimeMillis();
        com.amap.api.location.a.c = r0;
        r1 = 0;
        r0 = r8.a;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r0 = r0.d;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r2 = r8.a;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r2 = r2.e;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r0 = r0.a(r2);	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        if (r0 == 0) goto L_0x0056;
    L_0x001c:
        r2 = r9.getLongitude();	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r4 = r9.getLatitude();	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r2 = com.aps.p.a(r2, r4);	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r0 = new com.amap.api.location.AMapLocation;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r0.<init>(r9);	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r1 = 1;
        r3 = r2[r1];	 //Catch:{ Exception -> 0x009e, all -> 0x0099 }
        r0.setLatitude(r3);	 //Catch:{ Exception -> 0x009e, all -> 0x0099 }
        r1 = 0;
        r1 = r2[r1];	 //Catch:{ Exception -> 0x009e, all -> 0x0099 }
        r0.setLongitude(r1);	 //Catch:{ Exception -> 0x009e, all -> 0x0099 }
    L_0x0039:
        r1 = new android.os.Message;
        r1.<init>();
        r1.obj = r0;
        r0 = com.amap.api.location.a.a;
        r1.what = r0;
        r0 = r8.a;
        r0 = r0.c;
        r0.sendMessage(r1);
        com.amap.api.location.a.b = r6;
        r0 = java.lang.System.currentTimeMillis();
        com.amap.api.location.a.c = r0;
    L_0x0055:
        return;
    L_0x0056:
        r0 = new com.amap.api.location.AMapLocation;	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        r0.<init>(r9);	 //Catch:{ Exception -> 0x005c, all -> 0x007b }
        goto L_0x0039;
    L_0x005c:
        r0 = move-exception;
        r0 = r1;
    L_0x005e:
        r1 = new android.os.Message;
        r1.<init>();
        r1.obj = r0;
        r0 = com.amap.api.location.a.a;
        r1.what = r0;
        r0 = r8.a;
        r0 = r0.c;
        r0.sendMessage(r1);
        com.amap.api.location.a.b = r6;
        r0 = java.lang.System.currentTimeMillis();
        com.amap.api.location.a.c = r0;
        goto L_0x0055;
    L_0x007b:
        r0 = move-exception;
    L_0x007c:
        r2 = new android.os.Message;
        r2.<init>();
        r2.obj = r1;
        r1 = com.amap.api.location.a.a;
        r2.what = r1;
        r1 = r8.a;
        r1 = r1.c;
        r1.sendMessage(r2);
        com.amap.api.location.a.b = r6;
        r1 = java.lang.System.currentTimeMillis();
        com.amap.api.location.a.c = r1;
        throw r0;
    L_0x0099:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x007c;
    L_0x009e:
        r1 = move-exception;
        goto L_0x005e;
        */

    }

    public void onProviderDisabled(String r1_String) {
    }

    public void onProviderEnabled(String r1_String) {
    }

    public void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
    }
}