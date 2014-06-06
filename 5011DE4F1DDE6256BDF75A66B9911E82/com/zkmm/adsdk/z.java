package com.zkmm.adsdk;

import android.content.Context;

// compiled from: SourceFile
final class z implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ int d;
    private final /* synthetic */ String e;
    private final /* synthetic */ short f;
    private final /* synthetic */ Context g;

    z(String r1_String, boolean r2z, boolean r3z, int r4i, String r5_String, short r6s, Context r7_Context) {
        this.a = r1_String;
        this.b = r2z;
        this.c = r3z;
        this.d = r4i;
        this.e = r5_String;
        this.f = r6s;
        this.g = r7_Context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r27_this = this;
        r0 = r27;
        r2 = r0.a;
        r3 = "/";
        r2 = r2.lastIndexOf(r3);
        r0 = r27;
        r3 = r0.a;
        r2 = r2 + 1;
        r16 = r3.substring(r2);
        r2 = new java.lang.StringBuilder;
        r3 = com.zkmm.adsdk.m.O;
        r3 = java.lang.String.valueOf(r3);
        r2.<init>(r3);
        r0 = r16;
        r2 = r2.append(r0);
        r17 = r2.toString();
        r2 = new java.lang.StringBuilder;
        r3 = java.lang.String.valueOf(r17);
        r2.<init>(r3);
        r3 = ".tmp";
        r2 = r2.append(r3);
        r18 = r2.toString();
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x005d;
    L_0x0046:
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u4e0b\u8f7d\u5217\u8868\u4e2d\u5df2\u7ecf\u5b58\u5728\u6b63\u5728\u4e0b\u8f7d\u4e2d--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x005c:
        return;
    L_0x005d:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2.add(r0);
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u6dfb\u52a0\u5230\u4e0b\u8f7d\u5217\u8868\u4e2d--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
        r2 = "1";
        r0 = r27;
        r3 = r0.b;
        if (r3 != 0) goto L_0x0084;
    L_0x0082:
        r2 = "0";
    L_0x0084:
        r3 = "1";
        r0 = r27;
        r4 = r0.c;
        if (r4 != 0) goto L_0x008e;
    L_0x008c:
        r3 = "0";
    L_0x008e:
        r4 = new java.lang.StringBuilder;
        r0 = r27;
        r5 = r0.a;
        r5 = java.lang.String.valueOf(r5);
        r4.<init>(r5);
        r5 = ",,,";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = ",,,";
        r2 = r2.append(r4);
        r0 = r27;
        r4 = r0.d;
        r2 = r2.append(r4);
        r4 = ",,,";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r3 = ",,,";
        r2 = r2.append(r3);
        r0 = r27;
        r3 = r0.e;
        r2 = r2.append(r3);
        r3 = ",,,";
        r2 = r2.append(r3);
        r0 = r27;
        r3 = r0.f;
        r2 = r2.append(r3);
        r2 = r2.toString();
        r0 = r27;
        r3 = r0.g;
        r0 = r18;
        com.zkmm.adsdk.s.b(r0, r2, r3);
        r2 = android.os.Environment.getExternalStorageState();
        r3 = "mounted";
        r3 = r2.equals(r3);
        if (r3 != 0) goto L_0x013f;
    L_0x00f2:
        r3 = "Adwo SDK";
        r4 = new java.lang.StringBuilder;
        r5 = "ExternalStorageState = ";
        r4.<init>(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        android.util.Log.e(r3, r2);
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x012d;
    L_0x0110:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2.remove(r0);
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u4ece\u4e0b\u8f7d\u5217\u8868\u4e2d\u5220\u9664--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x012d:
        r2 = com.zkmm.adsdk.s.e;
        r3 = new com.zkmm.adsdk.aa;
        r0 = r27;
        r4 = r0.g;
        r0 = r27;
        r3.<init>(r0, r4);
        r2.post(r3);
        goto L_0x005c;
    L_0x013f:
        r2 = new java.io.File;
        r3 = com.zkmm.adsdk.m.O;
        r2.<init>(r3);
        r3 = r2.exists();
        if (r3 != 0) goto L_0x014f;
    L_0x014c:
        r2.mkdirs();
    L_0x014f:
        r3 = 0;
        r2 = 1;
        r5 = new org.apache.http.impl.client.DefaultHttpClient;
        r5.<init>();
        r4 = new org.apache.http.client.methods.HttpGet;
        r0 = r27;
        r6 = r0.a;
        r4.<init>(r6);
        r4 = r5.execute(r4);	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r6 = r4.getStatusLine();	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r6 = r6.getStatusCode();	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r7) goto L_0x01b3;
    L_0x016f:
        r2 = 0;
        r9 = r3;
    L_0x0171:
        r3 = r5.getConnectionManager();
        r3.closeExpiredConnections();
        r3 = r5.getConnectionManager();
        r3.shutdown();
        if (r2 == 0) goto L_0x0183;
    L_0x0181:
        if (r9 >= 0) goto L_0x01e2;
    L_0x0183:
        r2 = "Adwo SDK";
        r3 = "Failed to get the file'size.";
        android.util.Log.e(r2, r3);
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x005c;
    L_0x0194:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2.remove(r0);
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u4ece\u4e0b\u8f7d\u5217\u8868\u4e2d\u5220\u9664--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
        goto L_0x005c;
    L_0x01b3:
        r4 = r4.getEntity();	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r3 = r4.getContentLength();	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r3 = (int) r3;	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r4 = "Adwo SDK";
        r6 = new java.lang.StringBuilder;	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r7 = "The file's size is ";
        r6.<init>(r7);	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r6 = r6.append(r3);	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r6 = r6.toString();	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        android.util.Log.d(r4, r6);	 //Catch:{ ClientProtocolException -> 0x01d2, IOException -> 0x01da }
        r9 = r3;
        goto L_0x0171;
    L_0x01d2:
        r2 = move-exception;
        r4 = r2;
        r2 = 0;
        r4.printStackTrace();
        r9 = r3;
        goto L_0x0171;
    L_0x01da:
        r2 = move-exception;
        r4 = r2;
        r2 = 0;
        r4.printStackTrace();
        r9 = r3;
        goto L_0x0171;
    L_0x01e2:
        r2 = new java.io.File;
        r0 = r17;
        r2.<init>(r0);
        r3 = r2.exists();
        if (r3 == 0) goto L_0x0218;
    L_0x01ef:
        r3 = r2.length();
        r5 = (long) r9;
        r3 = (r3 > r5 ? 1 : (r3 == r5? 0 : -1));
        if (r3 >= 0) goto L_0x043b;
    L_0x01f8:
        r3 = "Adwo SDK";
        r4 = new java.lang.StringBuilder;
        r5 = "\u4e0a\u6b21\u4e0b\u8f7d\u4e0d\u5b8c\u6574\uff0c\u91cd\u65b0\u4e0b\u8f7d--->";
        r4.<init>(r5);
        r0 = r18;
        r4 = r4.append(r0);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        r3 = new java.io.File;
        r0 = r18;
        r3.<init>(r0);
        r2.renameTo(r3);
    L_0x0218:
        r2 = "Adwo SDK";
        r3 = "Ready to download.";
        android.util.Log.d(r2, r3);
        r2 = com.zkmm.adsdk.s.e;
        r3 = new com.zkmm.adsdk.ab;
        r0 = r27;
        r4 = r0.g;
        r0 = r27;
        r1 = r16;
        r3.<init>(r0, r4, r1);
        r2.post(r3);
        r0 = r27;
        r2 = r0.g;
        r3 = "notification";
        r2 = r2.getSystemService(r3);
        r8 = r2;
        r8 = (android.app.NotificationManager) r8;
        r19 = new android.app.Notification;
        r2 = 17301633; // 0x1080081 float:2.4979616E-38 double:8.5481425E-317;
        r3 = new java.lang.StringBuilder;
        r4 = java.lang.String.valueOf(r16);
        r3.<init>(r4);
        r4 = "\u4e0b\u8f7d\u4e2d";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = java.lang.System.currentTimeMillis();
        r0 = r19;
        r0.<init>(r2, r3, r4);
        r2 = 2;
        r0 = r19;
        r0.flags = r2;
        r2 = 16;
        r0 = r19;
        r0.flags = r2;
        r20 = new android.widget.RemoteViews;
        r0 = r27;
        r2 = r0.g;
        r2 = r2.getPackageName();
        r3 = 17367040; // 0x1090000 float:2.5162926E-38 double:8.580458E-317;
        r0 = r20;
        r0.<init>(r2, r3);
        r2 = 16908294; // 0x1020006 float:2.3877246E-38 double:8.353807E-317;
        r3 = 17301633; // 0x1080081 float:2.4979616E-38 double:8.5481425E-317;
        r0 = r20;
        r0.setImageViewResource(r2, r3);
        r0 = r27;
        r2 = r0.g;
        r3 = 0;
        r4 = new android.content.Intent;
        r4.<init>();
        r5 = 0;
        r21 = android.app.PendingIntent.getActivity(r2, r3, r4, r5);
        r13 = 0;
        r3 = 0;
        r11 = 0;
        r10 = 0;
        r22 = new java.io.File;
        r0 = r22;
        r1 = r18;
        r0.<init>(r1);
        r14 = 0;
        r2 = r22.exists();
        if (r2 == 0) goto L_0x02ae;
    L_0x02aa:
        r14 = r22.length();
    L_0x02ae:
        r12 = new java.io.RandomAccessFile;	 //Catch:{ SocketTimeoutException -> 0x05af, MalformedURLException -> 0x0595, IOException -> 0x0572 }
        r2 = "rw";
        r0 = r22;
        r12.<init>(r0, r2);	 //Catch:{ SocketTimeoutException -> 0x05af, MalformedURLException -> 0x0595, IOException -> 0x0572 }
        r2 = r12.getChannel();	 //Catch:{ SocketTimeoutException -> 0x05b5, MalformedURLException -> 0x059b, IOException -> 0x057f }
        r3 = 0;
        r5 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r7 = 0;
        r4 = r2.lock(r3, r5, r7);	 //Catch:{ SocketTimeoutException -> 0x05b5, MalformedURLException -> 0x059b, IOException -> 0x057f }
        r2 = new java.net.URL;	 //Catch:{ SocketTimeoutException -> 0x05bb, MalformedURLException -> 0x05a0, IOException -> 0x0584 }
        r0 = r27;
        r3 = r0.a;	 //Catch:{ SocketTimeoutException -> 0x05bb, MalformedURLException -> 0x05a0, IOException -> 0x0584 }
        r2.<init>(r3);	 //Catch:{ SocketTimeoutException -> 0x05bb, MalformedURLException -> 0x05a0, IOException -> 0x0584 }
        r2 = r2.openConnection();	 //Catch:{ SocketTimeoutException -> 0x05bb, MalformedURLException -> 0x05a0, IOException -> 0x0584 }
        r2 = (java.net.HttpURLConnection) r2;	 //Catch:{ SocketTimeoutException -> 0x05bb, MalformedURLException -> 0x05a0, IOException -> 0x0584 }
        r3 = 40000; // 0x9c40 float:5.6052E-41 double:1.97626E-319;
        r2.setConnectTimeout(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r2.setReadTimeout(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = 1;
        r2.setAllowUserInteraction(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = "Range";
        r5 = new java.lang.StringBuilder;	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r6 = "bytes=";
        r5.<init>(r6);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = r5.append(r14);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r6 = "-";
        r5 = r5.append(r6);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = r5.append(r9);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = r5.toString();	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r2.setRequestProperty(r3, r5);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = r2.getResponseCode();	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r3 != r5) goto L_0x0492;
    L_0x030c:
        r3 = "Adwo SDK";
        r5 = new java.lang.StringBuilder;	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r6 = "\u65ad\u70b9\u4e0b\u8f7d--->";
        r5.<init>(r6);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = r5.append(r14);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = r5.toString();	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        android.util.Log.d(r3, r5);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r12.seek(r14);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r6 = r14;
        r3 = r2;
    L_0x0325:
        r5 = new java.io.BufferedInputStream;	 //Catch:{ SocketTimeoutException -> 0x05c9, MalformedURLException -> 0x05ac, IOException -> 0x0590 }
        r2 = r3.getInputStream();	 //Catch:{ SocketTimeoutException -> 0x05c9, MalformedURLException -> 0x05ac, IOException -> 0x0590 }
        r5.<init>(r2);	 //Catch:{ SocketTimeoutException -> 0x05c9, MalformedURLException -> 0x05ac, IOException -> 0x0590 }
        r2 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r10 = new byte[r2];	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r6 = (int) r6;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = -1;
    L_0x0334:
        r7 = r5.read(r10);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r11 = -1;
        if (r7 != r11) goto L_0x04ce;
    L_0x033b:
        if (r6 != r9) goto L_0x03f2;
    L_0x033d:
        if (r4 == 0) goto L_0x0348;
    L_0x033f:
        r2 = r4.isValid();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        if (r2 == 0) goto L_0x0348;
    L_0x0345:
        r4.release();	 //Catch:{ IOException -> 0x0566, SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c }
    L_0x0348:
        r2 = new java.io.File;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r17;
        r2.<init>(r0);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r22;
        r0.renameTo(r2);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = android.net.Uri.fromFile(r2);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r6 = new android.content.Intent;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r6.<init>();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = "android.intent.action.VIEW";
        r6.setAction(r7);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = "application/vnd.android.package-archive";
        r6.setDataAndType(r2, r7);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r2 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = 0;
        r10 = 0;
        r2 = android.app.PendingIntent.getActivity(r2, r7, r6, r10);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r19;
        r0.contentIntent = r2;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = 17301634; // 0x1080082 float:2.497962E-38 double:8.548143E-317;
        r0 = r19;
        r0.icon = r2;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = 16908294; // 0x1020006 float:2.3877246E-38 double:8.353807E-317;
        r0 = r19;
        r8.notify(r2, r0);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = "Adwo SDK";
        r7 = new java.lang.StringBuilder;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r8 = " fileSize->";
        r7.<init>(r8);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = r7.append(r9);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = r7.toString();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        android.util.Log.d(r2, r7);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r2 = r0.b;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        if (r2 == 0) goto L_0x03c6;
    L_0x039e:
        r0 = r27;
        r2 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2.startActivity(r6);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r2 = r0.f;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        if (r2 <= 0) goto L_0x03c6;
    L_0x03ab:
        r2 = com.zkmm.adsdk.s.e;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r6 = new com.zkmm.adsdk.ac;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r7 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r8 = r0.e;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r6.<init>(r0, r7, r8);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r7 = r0.f;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = r7 * 1000;
        r7 = (long) r7;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2.postDelayed(r6, r7);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
    L_0x03c6:
        r0 = r27;
        r2 = r0.c;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        if (r2 == 0) goto L_0x03d7;
    L_0x03cc:
        r0 = r27;
        r2 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r6 = r0.d;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        com.zkmm.adsdk.s.a(r2, r6);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
    L_0x03d7:
        r0 = r27;
        r2 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r18;
        com.zkmm.adsdk.s.i(r2, r0);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2 = com.zkmm.adsdk.s.e;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r6 = new com.zkmm.adsdk.ay;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r7 = r0.g;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r0 = r27;
        r1 = r16;
        r6.<init>(r0, r7, r1);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r2.post(r6);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
    L_0x03f2:
        r5.close();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r12.close();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
    L_0x03f8:
        if (r5 == 0) goto L_0x03fd;
    L_0x03fa:
        r5.close();	 //Catch:{ IOException -> 0x057c }
    L_0x03fd:
        if (r12 == 0) goto L_0x0402;
    L_0x03ff:
        r12.close();	 //Catch:{ IOException -> 0x057c }
    L_0x0402:
        if (r4 == 0) goto L_0x040d;
    L_0x0404:
        r2 = r4.isValid();	 //Catch:{ IOException -> 0x057c }
        if (r2 == 0) goto L_0x040d;
    L_0x040a:
        r4.release();	 //Catch:{ IOException -> 0x057c }
    L_0x040d:
        if (r3 == 0) goto L_0x0412;
    L_0x040f:
        r3.disconnect();	 //Catch:{ IOException -> 0x057c }
    L_0x0412:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x005c;
    L_0x041c:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2.remove(r0);
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u4ece\u4e0b\u8f7d\u5217\u8868\u4e2d\u5220\u9664--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
        goto L_0x005c;
    L_0x043b:
        r0 = r27;
        r3 = r0.b;
        if (r3 == 0) goto L_0x0460;
    L_0x0441:
        r2 = android.net.Uri.fromFile(r2);
        r3 = new android.content.Intent;
        r3.<init>();
        r4 = "android.intent.action.VIEW";
        r3.setAction(r4);
        r4 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r3.setFlags(r4);
        r4 = "application/vnd.android.package-archive";
        r3.setDataAndType(r2, r4);
        r0 = r27;
        r2 = r0.g;
        r2.startActivity(r3);
    L_0x0460:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x0487;
    L_0x046a:
        r2 = com.zkmm.adsdk.s.f;
        r0 = r18;
        r2.remove(r0);
        r2 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;
        r4 = "\u4ece\u4e0b\u8f7d\u5217\u8868\u4e2d\u5220\u9664--->";
        r3.<init>(r4);
        r0 = r18;
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x0487:
        r0 = r27;
        r2 = r0.g;
        r0 = r18;
        com.zkmm.adsdk.s.i(r2, r0);
        goto L_0x005c;
    L_0x0492:
        r5 = 0;
        r3 = (r14 > r5 ? 1 : (r14 == r5? 0 : -1));
        if (r3 <= 0) goto L_0x049f;
    L_0x0498:
        r3 = "Adwo SDK";
        r5 = "\u4e0d\u652f\u6301\u65ad\u70b9\u4e0b\u8f7d--->";
        android.util.Log.d(r3, r5);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
    L_0x049f:
        r3 = new java.net.URL;	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r0 = r27;
        r5 = r0.a;	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3.<init>(r5);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = r3.openConnection();	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r0 = r3;
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r2 = r0;
        r3 = 40000; // 0x9c40 float:5.6052E-41 double:1.97626E-319;
        r2.setConnectTimeout(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r2.setReadTimeout(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = 1;
        r2.setAllowUserInteraction(r3);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r5 = 0;
        r10 = 0;
        r12.seek(r10);	 //Catch:{ SocketTimeoutException -> 0x05c0, MalformedURLException -> 0x05a4, IOException -> 0x0588 }
        r3 = r2;
        r25 = r5;
        r6 = r25;
        goto L_0x0325;
    L_0x04ce:
        r11 = 0;
        r12.write(r10, r11, r7);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r7 = r7 + r6;
        r13 = (double) r7;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r23 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r13 = r13 * r23;
        r0 = (double) r9;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r23 = r0;
        r13 = r13 / r23;
        r6 = (int) r13;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        r11 = java.lang.Thread.currentThread();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        monitor-enter(r11);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        if (r7 != r9) goto L_0x051c;
    L_0x04e5:
        r13 = 16908308; // 0x1020014 float:2.3877285E-38 double:8.353814E-317;
        r14 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0559 }
        r15 = java.lang.String.valueOf(r16);	 //Catch:{ all -> 0x0559 }
        r14.<init>(r15);	 //Catch:{ all -> 0x0559 }
        r15 = "\u5df2\u4e0b\u8f7d\u5b8c\u6210: ";
        r14 = r14.append(r15);	 //Catch:{ all -> 0x0559 }
        r6 = r14.append(r6);	 //Catch:{ all -> 0x0559 }
        r14 = "%";
        r6 = r6.append(r14);	 //Catch:{ all -> 0x0559 }
        r6 = r6.toString();	 //Catch:{ all -> 0x0559 }
        r0 = r20;
        r0.setTextViewText(r13, r6);	 //Catch:{ all -> 0x0559 }
        r0 = r20;
        r1 = r19;
        r1.contentView = r0;	 //Catch:{ all -> 0x0559 }
        r6 = 16908294; // 0x1020006 float:2.3877246E-38 double:8.353807E-317;
        r0 = r19;
        r8.notify(r6, r0);	 //Catch:{ all -> 0x0559 }
    L_0x0518:
        monitor-exit(r11);	 //Catch:{ all -> 0x0559 }
        r6 = r7;
        goto L_0x0334;
    L_0x051c:
        if (r2 == r6) goto L_0x0518;
    L_0x051e:
        r2 = 16908308; // 0x1020014 float:2.3877285E-38 double:8.353814E-317;
        r13 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0559 }
        r14 = java.lang.String.valueOf(r16);	 //Catch:{ all -> 0x0559 }
        r13.<init>(r14);	 //Catch:{ all -> 0x0559 }
        r14 = "\u5df2\u4e0b\u8f7d\u5b8c\u6210: ";
        r13 = r13.append(r14);	 //Catch:{ all -> 0x0559 }
        r13 = r13.append(r6);	 //Catch:{ all -> 0x0559 }
        r14 = "%";
        r13 = r13.append(r14);	 //Catch:{ all -> 0x0559 }
        r13 = r13.toString();	 //Catch:{ all -> 0x0559 }
        r0 = r20;
        r0.setTextViewText(r2, r13);	 //Catch:{ all -> 0x0559 }
        r0 = r20;
        r1 = r19;
        r1.contentView = r0;	 //Catch:{ all -> 0x0559 }
        r0 = r21;
        r1 = r19;
        r1.contentIntent = r0;	 //Catch:{ all -> 0x0559 }
        r2 = 16908294; // 0x1020006 float:2.3877246E-38 double:8.353807E-317;
        r0 = r19;
        r8.notify(r2, r0);	 //Catch:{ all -> 0x0559 }
        r2 = r6;
        goto L_0x0518;
    L_0x0559:
        r2 = move-exception;
        monitor-exit(r11);	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        throw r2;	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
    L_0x055c:
        r2 = move-exception;
        r6 = r5;
        r5 = r12;
    L_0x055f:
        r2.printStackTrace();
        r12 = r5;
        r5 = r6;
        goto L_0x03f8;
    L_0x0566:
        r2 = move-exception;
        r2.printStackTrace();	 //Catch:{ SocketTimeoutException -> 0x055c, MalformedURLException -> 0x056c, IOException -> 0x0593 }
        goto L_0x0348;
    L_0x056c:
        r2 = move-exception;
    L_0x056d:
        r2.printStackTrace();
        goto L_0x03f8;
    L_0x0572:
        r2 = move-exception;
        r4 = r11;
        r12 = r3;
        r5 = r13;
        r3 = r10;
    L_0x0577:
        r2.printStackTrace();
        goto L_0x03f8;
    L_0x057c:
        r2 = move-exception;
        goto L_0x0412;
    L_0x057f:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r13;
        goto L_0x0577;
    L_0x0584:
        r2 = move-exception;
        r3 = r10;
        r5 = r13;
        goto L_0x0577;
    L_0x0588:
        r3 = move-exception;
        r5 = r13;
        r25 = r2;
        r2 = r3;
        r3 = r25;
        goto L_0x0577;
    L_0x0590:
        r2 = move-exception;
        r5 = r13;
        goto L_0x0577;
    L_0x0593:
        r2 = move-exception;
        goto L_0x0577;
    L_0x0595:
        r2 = move-exception;
        r4 = r11;
        r12 = r3;
        r5 = r13;
        r3 = r10;
        goto L_0x056d;
    L_0x059b:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r13;
        goto L_0x056d;
    L_0x05a0:
        r2 = move-exception;
        r3 = r10;
        r5 = r13;
        goto L_0x056d;
    L_0x05a4:
        r3 = move-exception;
        r5 = r13;
        r25 = r2;
        r2 = r3;
        r3 = r25;
        goto L_0x056d;
    L_0x05ac:
        r2 = move-exception;
        r5 = r13;
        goto L_0x056d;
    L_0x05af:
        r2 = move-exception;
        r4 = r11;
        r5 = r3;
        r6 = r13;
        r3 = r10;
        goto L_0x055f;
    L_0x05b5:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r6 = r13;
        goto L_0x055f;
    L_0x05bb:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        r6 = r13;
        goto L_0x055f;
    L_0x05c0:
        r3 = move-exception;
        r5 = r12;
        r6 = r13;
        r25 = r3;
        r3 = r2;
        r2 = r25;
        goto L_0x055f;
    L_0x05c9:
        r2 = move-exception;
        r5 = r12;
        r6 = r13;
        goto L_0x055f;
        */

    }
}