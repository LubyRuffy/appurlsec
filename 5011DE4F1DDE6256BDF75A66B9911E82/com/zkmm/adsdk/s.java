package com.zkmm.adsdk;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class s {
    protected static int a;
    protected static int b;
    protected static String c;
    protected static String d;
    static Handler e;
    static LinkedList f;
    private static Intent g;
    private static String h;
    private static final String[][] i;
    private static volatile boolean j;

    static {
        a = 15000;
        b = -1;
        g = null;
        h = null;
        c = "http://r2.adwo.com/adweb4";
        d = "http://r2.adwo.com/adfs3";
        int[] r0_intA = new int[5];
        r0_intA[0] = 216;
        r0_intA[1] = 320;
        r0_intA[2] = 480;
        r0_intA[3] = 640;
        r0_intA[4] = 720;
        r0_intA = new int[5];
        r0_intA[0] = 38;
        r0_intA[1] = 50;
        r0_intA[2] = 80;
        r0_intA[3] = 100;
        r0_intA[4] = 110;
        String[][] r0_StringAA = new String[27][];
        String[] r1_StringA = new String[2];
        r1_StringA[0] = ".3gp";
        r1_StringA[1] = "video/3gpp";
        r0_StringAA[0] = r1_StringA;
        r1_StringA = new String[2];
        r1_StringA[0] = ".apk";
        r1_StringA[1] = "application/vnd.android.package-archive";
        r0_StringAA[1] = r1_StringA;
        r1_StringA = new String[2];
        r1_StringA[0] = ".asf";
        r1_StringA[1] = "video/x-ms-asf";
        r0_StringAA[2] = r1_StringA;
        r1_StringA = new String[2];
        r1_StringA[0] = ".avi";
        r1_StringA[1] = "video/x-msvideo";
        r0_StringAA[3] = r1_StringA;
        r1_StringA = new String[2];
        r1_StringA[0] = ".bmp";
        r1_StringA[1] = "image/bmp";
        r0_StringAA[4] = r1_StringA;
        String[] r2_StringA = new String[2];
        r2_StringA[0] = ".gif";
        r2_StringA[1] = "image/gif";
        r0_StringAA[5] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".htm";
        r2_StringA[1] = "text/html";
        r0_StringAA[6] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".html";
        r2_StringA[1] = "text/html";
        r0_StringAA[7] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".jpeg";
        r2_StringA[1] = "image/jpeg";
        r0_StringAA[8] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = Util.PHOTO_DEFAULT_EXT;
        r2_StringA[1] = "image/jpeg";
        r0_StringAA[9] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".m4v";
        r2_StringA[1] = "video/x-m4v";
        r0_StringAA[10] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mov";
        r2_StringA[1] = "video/quicktime";
        r0_StringAA[11] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mp2";
        r2_StringA[1] = "audio/x-mpeg";
        r0_StringAA[12] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mp3";
        r2_StringA[1] = "audio/x-mpeg";
        r0_StringAA[13] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mp4";
        r2_StringA[1] = "video/mp4";
        r0_StringAA[14] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mpe";
        r2_StringA[1] = "video/mpeg";
        r0_StringAA[15] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mpeg";
        r2_StringA[1] = "video/mpeg";
        r0_StringAA[16] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mpg";
        r2_StringA[1] = "video/mpeg";
        r0_StringAA[17] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mpg4";
        r2_StringA[1] = "video/mp4";
        r0_StringAA[18] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".mpga";
        r2_StringA[1] = "audio/mpeg";
        r0_StringAA[19] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".ogg";
        r2_StringA[1] = "audio/ogg";
        r0_StringAA[20] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".png";
        r2_StringA[1] = "image/png";
        r0_StringAA[21] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".rmvb";
        r2_StringA[1] = "audio/x-pn-realaudio";
        r0_StringAA[22] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".wav";
        r2_StringA[1] = "audio/x-wav";
        r0_StringAA[23] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".wma";
        r2_StringA[1] = "audio/x-ms-wma";
        r0_StringAA[24] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = ".wmv";
        r2_StringA[1] = "audio/x-ms-wmv";
        r0_StringAA[25] = r2_StringA;
        r2_StringA = new String[2];
        r2_StringA[0] = RContactStorage.PRIMARY_KEY;
        r2_StringA[1] = "*/*";
        r0_StringAA[26] = r2_StringA;
        i = r0_StringAA;
        j = false;
        e = new Handler();
        f = new LinkedList();
    }

    protected static int a(Context r3_Context, String r4_String) {
        SharedPreferences r1_SharedPreferences = r3_Context.getSharedPreferences("ADWO_AD_COUNT", 0);
        return r1_SharedPreferences.contains(r4_String) ? r1_SharedPreferences.getInt(r4_String, 1) : 0;
    }

    protected static String a(String r8_String, String r9_String) {
        String r0_String;
        HttpClient r2_HttpClient = new DefaultHttpClient();
        try {
            HttpResponse r0_HttpResponse = r2_HttpClient.execute(new HttpGet(r8_String));
            if (r0_HttpResponse.getStatusLine().getStatusCode() == 200) {
                byte[] r3_byteA = new byte[4096];
                ZipInputStream r4_ZipInputStream = new ZipInputStream(new BufferedInputStream(r0_HttpResponse.getEntity().getContent()));
                r0_String = null;
                while (true) {
                    ZipEntry r5_ZipEntry = r4_ZipInputStream.getNextEntry();
                    if (r5_ZipEntry == null) {
                        return r0_String;
                    }
                    r0_String = new StringBuilder(String.valueOf(r9_String)).append(File.separator).append(r5_ZipEntry.getName()).toString();
                    File r6_File = new File(r0_String);
                    if (r5_ZipEntry.isDirectory()) {
                        r6_File.mkdirs();
                    } else {
                        File r5_File = r6_File.getParentFile();
                        if (!r5_File.exists()) {
                            r5_File.mkdirs();
                        }
                        FileOutputStream r5_FileOutputStream = new FileOutputStream(r6_File);
                        while (true) {
                            int r6i = r4_ZipInputStream.read(r3_byteA);
                            if (r6i <= 0) {
                                r5_FileOutputStream.close();
                            } else {
                                r5_FileOutputStream.write(r3_byteA, 0, r6i);
                            }
                        }
                    }
                    r4_ZipInputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            IOException r0_IOException = e;
            Log.e("Adwo SDK", "An error has occurred during unzipping full ad file.");
            r0_IOException.printStackTrace();
        }
        r2_HttpClient.getConnectionManager().closeExpiredConnections();
        r2_HttpClient.getConnectionManager().shutdown();
        r0_String = null;
        return r0_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static String a(String r9_String, String r10_String, String r11_String, String r12_String, String r13_String) {
        /*
        r2 = 0;
        r0 = new java.net.URL;	 //Catch:{ Exception -> 0x00cc }
        r0.<init>(r10);	 //Catch:{ Exception -> 0x00cc }
        r0 = r0.openConnection();	 //Catch:{ Exception -> 0x00cc }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x00cc }
        r1 = 1;
        r0.setDoInput(r1);	 //Catch:{ Exception -> 0x00d1 }
        r1 = 1;
        r0.setDoOutput(r1);	 //Catch:{ Exception -> 0x00d1 }
        r1 = 0;
        r0.setUseCaches(r1);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "POST";
        r0.setRequestMethod(r1);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "Charset";
        r3 = "utf-8";
        r0.setRequestProperty(r1, r3);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "content-type";
        r3 = "text/html";
        r0.setRequestProperty(r1, r3);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "Connection";
        r3 = "Keep-Alive";
        r0.setRequestProperty(r1, r3);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "adid";
        r0.setRequestProperty(r1, r11);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "pid";
        r0.setRequestProperty(r1, r12);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "devid";
        r0.setRequestProperty(r1, r13);	 //Catch:{ Exception -> 0x00d1 }
        r1 = "platform";
        r3 = "Android";
        r0.setRequestProperty(r1, r3);	 //Catch:{ Exception -> 0x00d1 }
        r4 = new java.io.DataOutputStream;	 //Catch:{ Exception -> 0x00d1 }
        r1 = r0.getOutputStream();	 //Catch:{ Exception -> 0x00d1 }
        r4.<init>(r1);	 //Catch:{ Exception -> 0x00d1 }
        r3 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x00d8 }
        r3.<init>(r9);	 //Catch:{ Exception -> 0x00d8 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 //Catch:{ Exception -> 0x00bd }
    L_0x005a:
        r5 = r3.read(r1);	 //Catch:{ Exception -> 0x00bd }
        r6 = -1;
        if (r5 != r6) goto L_0x00b8;
    L_0x0061:
        r4.flush();	 //Catch:{ Exception -> 0x00bd }
        r1 = r0.getInputStream();	 //Catch:{ Exception -> 0x00bd }
        r5 = new java.io.BufferedReader;	 //Catch:{ Exception -> 0x00bd }
        r6 = new java.io.InputStreamReader;	 //Catch:{ Exception -> 0x00bd }
        r7 = "utf-8";
        r6.<init>(r1, r7);	 //Catch:{ Exception -> 0x00bd }
        r5.<init>(r6);	 //Catch:{ Exception -> 0x00bd }
        r1 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00bd }
        r1.<init>();	 //Catch:{ Exception -> 0x00bd }
    L_0x0079:
        r6 = r5.readLine();	 //Catch:{ Exception -> 0x00bd }
        if (r6 != 0) goto L_0x00c6;
    L_0x007f:
        r5 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x00bd }
        r1 = r1.toString();	 //Catch:{ Exception -> 0x00bd }
        r5.<init>(r1);	 //Catch:{ Exception -> 0x00bd }
        r1 = "mediaUrl";
        r2 = r5.getString(r1);	 //Catch:{ Exception -> 0x00bd }
        r1 = java.lang.System.out;	 //Catch:{ Exception -> 0x00bd }
        r5 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00bd }
        r6 = "MediaFileUrl--->";
        r5.<init>(r6);	 //Catch:{ Exception -> 0x00bd }
        r5 = r5.append(r2);	 //Catch:{ Exception -> 0x00bd }
        r5 = r5.toString();	 //Catch:{ Exception -> 0x00bd }
        r1.println(r5);	 //Catch:{ Exception -> 0x00bd }
        r4.close();	 //Catch:{ Exception -> 0x00bd }
        r3.close();	 //Catch:{ Exception -> 0x00bd }
    L_0x00a8:
        if (r4 == 0) goto L_0x00ad;
    L_0x00aa:
        r4.close();	 //Catch:{ IOException -> 0x00ca }
    L_0x00ad:
        if (r3 == 0) goto L_0x00b2;
    L_0x00af:
        r3.close();	 //Catch:{ IOException -> 0x00ca }
    L_0x00b2:
        if (r0 == 0) goto L_0x00b7;
    L_0x00b4:
        r0.disconnect();	 //Catch:{ IOException -> 0x00ca }
    L_0x00b7:
        return r2;
    L_0x00b8:
        r6 = 0;
        r4.write(r1, r6, r5);	 //Catch:{ Exception -> 0x00bd }
        goto L_0x005a;
    L_0x00bd:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x00c1:
        r0.printStackTrace();
        r0 = r1;
        goto L_0x00a8;
    L_0x00c6:
        r1.append(r6);	 //Catch:{ Exception -> 0x00bd }
        goto L_0x0079;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x00b7;
    L_0x00cc:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        goto L_0x00c1;
    L_0x00d1:
        r1 = move-exception;
        r3 = r2;
        r4 = r2;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x00c1;
    L_0x00d8:
        r1 = move-exception;
        r3 = r2;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x00c1;
        */

    }

    private static void a(int r2i, byte r3b) {
        if (r2i > 7) {
        } else {
            m.v = (byte) (((byte) (1 << r2i)) | m.v);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void a(Context r8_Context, int r9i) {
        /*
        r2 = 0;
        r0 = r8.getPackageName();	 //Catch:{ Exception -> 0x00af }
        r1 = r8.getPackageManager();	 //Catch:{ Exception -> 0x00af }
        r3 = 0;
        r0 = r1.getPackageInfo(r0, r3);	 //Catch:{ Exception -> 0x00af }
        r1 = r0.packageName;	 //Catch:{ Exception -> 0x00af }
        r0 = r0.versionName;	 //Catch:{ Exception -> 0x00af }
        r3 = new java.lang.StringBuffer;	 //Catch:{ Exception -> 0x00af }
        r4 = "http://static.adwo.com/tr/dl";
        r3.<init>(r4);	 //Catch:{ Exception -> 0x00af }
        r4 = "?app=";
        r4 = r3.append(r4);	 //Catch:{ Exception -> 0x00af }
        r5 = "UTF-8";
        r1 = java.net.URLEncoder.encode(r1, r5);	 //Catch:{ Exception -> 0x00af }
        r4.append(r1);	 //Catch:{ Exception -> 0x00af }
        r1 = "&ownid=";
        r1 = r3.append(r1);	 //Catch:{ Exception -> 0x00af }
        r4 = new java.lang.String;	 //Catch:{ Exception -> 0x00af }
        r5 = com.zkmm.adsdk.m.c;	 //Catch:{ Exception -> 0x00af }
        r6 = "UTF-8";
        r4.<init>(r5, r6);	 //Catch:{ Exception -> 0x00af }
        r1.append(r4);	 //Catch:{ Exception -> 0x00af }
        r1 = "&version=";
        r1 = r3.append(r1);	 //Catch:{ Exception -> 0x00af }
        r4 = "UTF-8";
        r0 = java.net.URLEncoder.encode(r0, r4);	 //Catch:{ Exception -> 0x00af }
        r1.append(r0);	 //Catch:{ Exception -> 0x00af }
        r0 = "&pid=";
        r0 = r3.append(r0);	 //Catch:{ Exception -> 0x00af }
        r1 = new java.lang.String;	 //Catch:{ Exception -> 0x00af }
        r4 = com.zkmm.adsdk.m.b;	 //Catch:{ Exception -> 0x00af }
        r5 = "UTF-8";
        r1.<init>(r4, r5);	 //Catch:{ Exception -> 0x00af }
        r0.append(r1);	 //Catch:{ Exception -> 0x00af }
        r0 = "&adid=";
        r0 = r3.append(r0);	 //Catch:{ Exception -> 0x00af }
        r0.append(r9);	 //Catch:{ Exception -> 0x00af }
        r0 = com.zkmm.adsdk.m.s;	 //Catch:{ Exception -> 0x00af }
        r1 = com.zkmm.adsdk.m.t;	 //Catch:{ Exception -> 0x00af }
        r0 = com.zkmm.adsdk.bz.a(r0, r1);	 //Catch:{ Exception -> 0x00af }
        r1 = "&sdv=";
        r1 = r3.append(r1);	 //Catch:{ Exception -> 0x00af }
        r1.append(r0);	 //Catch:{ Exception -> 0x00af }
        r0 = r3.toString();	 //Catch:{ Exception -> 0x00af }
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x00af }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x00af }
        r0 = r1.openConnection();	 //Catch:{ Exception -> 0x00af }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x00af }
        r1 = "GET";
        r0.setRequestMethod(r1);	 //Catch:{ Exception -> 0x00b9 }
        r1 = a;	 //Catch:{ Exception -> 0x00b9 }
        r0.setConnectTimeout(r1);	 //Catch:{ Exception -> 0x00b9 }
        r0.connect();	 //Catch:{ Exception -> 0x00b9 }
        r2 = r0.getInputStream();	 //Catch:{ Exception -> 0x00b9 }
        r1 = r0.getResponseCode();	 //Catch:{ Exception -> 0x00bf }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r3) goto L_0x00a4;
    L_0x009d:
        r1 = "Adwo SDK";
        r3 = "download tracking";
        android.util.Log.v(r1, r3);	 //Catch:{ Exception -> 0x00bf }
    L_0x00a4:
        if (r2 == 0) goto L_0x00a9;
    L_0x00a6:
        r2.close();	 //Catch:{ IOException -> 0x00b7 }
    L_0x00a9:
        if (r0 == 0) goto L_0x00ae;
    L_0x00ab:
        r0.disconnect();
    L_0x00ae:
        return;
    L_0x00af:
        r0 = move-exception;
        r1 = r2;
    L_0x00b1:
        r0.printStackTrace();
        r0 = r2;
        r2 = r1;
        goto L_0x00a4;
    L_0x00b7:
        r1 = move-exception;
        goto L_0x00a9;
    L_0x00b9:
        r1 = move-exception;
        r7 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r7;
        goto L_0x00b1;
    L_0x00bf:
        r1 = move-exception;
        r7 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r7;
        goto L_0x00b1;
        */

    }

    protected static void a(Context r4_Context, String r5_String, String r6_String) {
        try {
            Builder r0_Builder = new Builder(r4_Context);
            r0_Builder.setPositiveButton("\u4e0b\u8f7d\u66f4\u65b0", new w(r6_String, r4_Context)).setNegativeButton("\u53d6\u6d88", new y()).setTitle("\u7a0b\u5e8f\u66f4\u65b0").setMessage(r5_String);
            r0_Builder.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void a(View r9_View, int r10i) {
        Animation r0_Animation;
        switch (r10i) {
            case XListViewHeader.STATE_REFRESHING:
                r0_Animation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case XListViewFooter.STATE_NOMORE:
                r0_Animation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case XListViewFooter.STATE_NODATA:
                r0_Animation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case ShareUtils.SHARE_SMS:
                r0_Animation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case ShareUtils.SHARE_COPY:
                r0_Animation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f, 1, 0.5f, 1, 0.5f);
                r0_Animation.setDuration(1500);
                r9_View.setAnimation(r0_Animation);
                break;
            case ShareUtils.SHARE_COLLECT:
                r0_Animation = new RotateAnimation(180.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case Base64.DONT_BREAK_LINES:
                r0_Animation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                r0_Animation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                r0_Animation = new AlphaAnimation(0.0f, 1.0f);
                r0_Animation.setDuration(1500);
                r9_View.setAnimation(r0_Animation);
                break;
            case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                r0_Animation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
            case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                r0_Animation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
                r0_Animation.setDuration(Util.MILLSECONDS_OF_SECOND);
                r9_View.setAnimation(r0_Animation);
                break;
        }
    }

    protected static void a(WebView r4_WebView, int r5i) {
        if (m.j == null) {
            m.j = new StringBuilder("javascript:function adwoDoGetShowClickMonitorLinkParams(){ var adwoParamSet = new Object();adwoParamSet.uid ='").append(m.i).append("'; ad").append("woParamSet.cid='").append(m.s).append("';ad").append("woParamSet.uidtype=2;ad").append("woParamSet.ts=").append(System.currentTimeMillis()).append(";").toString();
        }
        r4_WebView.loadUrl(new StringBuilder(String.valueOf(m.j)).append("ad").append("woParamSet.adid=").append(r5i).append("; return ad").append("woParamSet;};").toString());
        String r0_String = "000_000";
        if (m.g != null) {
            r0_String = new String(m.g);
        }
        if (m.k == null) {
            m.k = new StringBuilder("javascript:function adwoDoGetSDKAttributes(){ var obj = new Object();obj.platform='Android'; obj.version='4.0';obj.systemVersion='").append(m.r).append("';obj.deviceModel='").append(new String(m.e)).append("'; obj.nettype='").append(m.m).append("';obj.mcc_mnc='").append(r0_String).append("'; obj.macAddr='").append(m.h).append("';obj.appID='").append(new String(m.f)).append("';obj.hasSDK='").append(m.q).append("';obj.isRooted='").append(m.p).append("';obj.isValidloc='").append(m.F).append("';obj.hasCameraPermission='").append(m.G).append("';obj.hasRecordPermission='").append(m.I).append("';obj.hasVibratePermission='").append(m.H).append("'; return obj;};").toString();
        }
        r4_WebView.loadUrl(m.k);
    }

    protected static void a(g r2_g, String r3_String) {
        try {
            ObjectOutputStream r1_ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(r3_String));
            r1_ObjectOutputStream.writeObject(r2_g);
            r1_ObjectOutputStream.flush();
            r1_ObjectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void a(File r4_File) {
        if (r4_File.isDirectory()) {
            File[] r1_FileA = r4_File.listFiles();
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                a(r1_FileA[r0i]);
                r0i++;
            }
            r4_File.delete();
        } else {
            r4_File.delete();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void a(String r6_String) {
        /*
        r2 = 0;
        r0 = new java.net.URL;	 //Catch:{ Exception -> 0x0044 }
        r0.<init>(r6);	 //Catch:{ Exception -> 0x0044 }
        r0 = r0.openConnection();	 //Catch:{ Exception -> 0x0044 }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x0044 }
        r1 = "GET";
        r0.setRequestMethod(r1);	 //Catch:{ Exception -> 0x004e }
        r1 = a;	 //Catch:{ Exception -> 0x004e }
        r0.setConnectTimeout(r1);	 //Catch:{ Exception -> 0x004e }
        r0.connect();	 //Catch:{ Exception -> 0x004e }
        r2 = r0.getInputStream();	 //Catch:{ Exception -> 0x004e }
        r1 = r0.getResponseCode();	 //Catch:{ Exception -> 0x0054 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r3) goto L_0x0039;
    L_0x0025:
        r1 = "Adwo SDK";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0054 }
        r4 = "Connect successfully to beacon: ";
        r3.<init>(r4);	 //Catch:{ Exception -> 0x0054 }
        r3 = r3.append(r6);	 //Catch:{ Exception -> 0x0054 }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x0054 }
        android.util.Log.v(r1, r3);	 //Catch:{ Exception -> 0x0054 }
    L_0x0039:
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 //Catch:{ IOException -> 0x004c }
    L_0x003e:
        if (r0 == 0) goto L_0x0043;
    L_0x0040:
        r0.disconnect();
    L_0x0043:
        return;
    L_0x0044:
        r0 = move-exception;
        r1 = r2;
    L_0x0046:
        r0.printStackTrace();
        r0 = r2;
        r2 = r1;
        goto L_0x0039;
    L_0x004c:
        r1 = move-exception;
        goto L_0x003e;
    L_0x004e:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
        goto L_0x0046;
    L_0x0054:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r5;
        goto L_0x0046;
        */

    }

    protected static void a(String r2_String, int r3i, Context r4_Context) {
        Editor r0_Editor = r4_Context.getSharedPreferences("ADWO_AD_COUNT", 0).edit();
        r0_Editor.putInt(r2_String, r3i);
        r0_Editor.commit();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void a(String r7_String, Activity r8_Activity) {
        /*
        r1 = 0;
        r0 = "/";
        r0 = r7.lastIndexOf(r0);
        r0 = r0 + 1;
        r0 = r7.substring(r0);
        r2 = android.os.Environment.getExternalStorageState();
        r3 = "mounted";
        r3 = r2.equals(r3);
        if (r3 != 0) goto L_0x002e;
    L_0x0019:
        r0 = "Adwo SDK";
        r1 = new java.lang.StringBuilder;
        r3 = "ExternalStorageState = ";
        r1.<init>(r3);
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.e(r0, r1);
    L_0x002d:
        return;
    L_0x002e:
        r2 = new com.zkmm.adsdk.t;
        r2.<init>(r8, r0);
        r8.runOnUiThread(r2);
        r2 = j;
        if (r2 != 0) goto L_0x002d;
    L_0x003a:
        r2 = 1;
        j = r2;
        r4 = new org.apache.http.impl.client.DefaultHttpClient;
        r4.<init>();
        r2 = new org.apache.http.client.methods.HttpGet;
        r2.<init>(r7);
        r2 = r4.execute(r2);	 //Catch:{ IOException -> 0x00d1 }
        r3 = r2.getStatusLine();	 //Catch:{ IOException -> 0x00d1 }
        r3 = r3.getStatusCode();	 //Catch:{ IOException -> 0x00d1 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 != r5) goto L_0x00d4;
    L_0x0057:
        r2 = r2.getEntity();	 //Catch:{ IOException -> 0x00d1 }
        r3 = r2.getContent();	 //Catch:{ IOException -> 0x00d1 }
        r2 = new java.io.File;	 //Catch:{ IOException -> 0x008a }
        r5 = com.zkmm.adsdk.m.O;	 //Catch:{ IOException -> 0x008a }
        r2.<init>(r5);	 //Catch:{ IOException -> 0x008a }
        r5 = r2.exists();	 //Catch:{ IOException -> 0x008a }
        if (r5 != 0) goto L_0x006f;
    L_0x006c:
        r2.mkdir();	 //Catch:{ IOException -> 0x008a }
    L_0x006f:
        r5 = new java.io.File;	 //Catch:{ IOException -> 0x008a }
        r2 = com.zkmm.adsdk.m.O;	 //Catch:{ IOException -> 0x008a }
        r5.<init>(r2, r0);	 //Catch:{ IOException -> 0x008a }
        r2 = r5.exists();	 //Catch:{ IOException -> 0x008a }
        if (r2 != 0) goto L_0x00a3;
    L_0x007c:
        r2 = r5.createNewFile();	 //Catch:{ IOException -> 0x008a }
        if (r2 != 0) goto L_0x00a3;
    L_0x0082:
        r0 = r4.getConnectionManager();	 //Catch:{ IOException -> 0x008a }
        r0.shutdown();	 //Catch:{ IOException -> 0x008a }
        goto L_0x002d;
    L_0x008a:
        r0 = move-exception;
        r2 = r3;
    L_0x008c:
        r0.printStackTrace();
    L_0x008f:
        if (r2 == 0) goto L_0x0094;
    L_0x0091:
        r2.close();	 //Catch:{ IOException -> 0x00a1 }
    L_0x0094:
        if (r1 == 0) goto L_0x0099;
    L_0x0096:
        r1.close();	 //Catch:{ IOException -> 0x00a1 }
    L_0x0099:
        r0 = r4.getConnectionManager();	 //Catch:{ IOException -> 0x00a1 }
        r0.shutdown();	 //Catch:{ IOException -> 0x00a1 }
        goto L_0x002d;
    L_0x00a1:
        r0 = move-exception;
        goto L_0x002d;
    L_0x00a3:
        r2 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x008a }
        r2.<init>(r5);	 //Catch:{ IOException -> 0x008a }
    L_0x00a8:
        r1 = r3.read();	 //Catch:{ IOException -> 0x00cd }
        r6 = -1;
        if (r1 != r6) goto L_0x00c9;
    L_0x00af:
        r3.close();	 //Catch:{ IOException -> 0x00cd }
        r2.close();	 //Catch:{ IOException -> 0x00cd }
        r1 = r5.getAbsolutePath();	 //Catch:{ IOException -> 0x00cd }
        r5 = new com.zkmm.adsdk.u;	 //Catch:{ IOException -> 0x00cd }
        r5.<init>(r8, r0, r1);	 //Catch:{ IOException -> 0x00cd }
        r8.runOnUiThread(r5);	 //Catch:{ IOException -> 0x00cd }
        r1 = r2;
        r2 = r3;
    L_0x00c3:
        r0 = 0;
        j = r0;	 //Catch:{ IOException -> 0x00c7 }
        goto L_0x008f;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x008c;
    L_0x00c9:
        r2.write(r1);	 //Catch:{ IOException -> 0x00cd }
        goto L_0x00a8;
    L_0x00cd:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x008c;
    L_0x00d1:
        r0 = move-exception;
        r2 = r1;
        goto L_0x008c;
    L_0x00d4:
        r2 = r1;
        goto L_0x00c3;
        */

    }

    public static void a(String r7_String, Context r8_Context, int r9i, boolean r10z, short r11s) {
        if (b == -1 || b != r9i) {
            a(r7_String, r8_Context, true, r9i, r10z, h, r11s);
        }
    }

    public static void a(String r9_String, Context r10_Context, boolean r11z, int r12i, boolean r13z, String r14_String, short r15s) {
        new Thread(new z(r9_String, r11z, r13z, r12i, r14_String, r15s, r10_Context)).start();
    }

    protected static void a(String r2_String, String r3_String, Context r4_Context) {
        Editor r0_Editor = r4_Context.getSharedPreferences("ADWO_STATISTIC", 0).edit();
        r0_Editor.putString(r2_String, r3_String);
        r0_Editor.commit();
    }

    protected static boolean a(Context r2_Context) {
        Editor r0_Editor = r2_Context.getSharedPreferences("ADWO_AD_COUNT", 0).edit();
        r0_Editor.clear();
        return r0_Editor.commit();
    }

    protected static byte[] a(double r9d) {
        long r0j = Double.doubleToLongBits(r9d);
        byte[] r2_byteA = new byte[8];
        r2_byteA[7] = (byte) ((int) ((r0j >> 56) & 255));
        r2_byteA[6] = (byte) ((int) ((r0j >> 48) & 255));
        r2_byteA[5] = (byte) ((int) ((r0j >> 40) & 255));
        r2_byteA[4] = (byte) ((int) ((r0j >> 32) & 255));
        r2_byteA[3] = (byte) ((int) ((r0j >> 24) & 255));
        r2_byteA[2] = (byte) ((int) ((r0j >> 16) & 255));
        r2_byteA[1] = (byte) ((int) ((r0j >> 8) & 255));
        r2_byteA[0] = (byte) ((int) (r0j & 255));
        return r2_byteA;
    }

    protected static byte[] a(int r3i) {
        byte[] r0_byteA = new byte[4];
        r0_byteA[3] = (byte) (r3i >>> 24);
        r0_byteA[2] = (byte) (r3i >>> 16);
        r0_byteA[1] = (byte) (r3i >>> 8);
        r0_byteA[0] = (byte) r3i;
        return r0_byteA;
    }

    protected static byte[] a(short r3s) {
        byte[] r0_byteA = new byte[2];
        r0_byteA[0] = (byte) (r3s & 255);
        r0_byteA[1] = (byte) ((65280 & r3s) >> 8);
        return r0_byteA;
    }

    protected static String b(String r6_String) {
        String r0_String;
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r6_String.getBytes());
            byte[] r1_byteA = r0_MessageDigest.digest();
            StringBuffer r2_StringBuffer = new StringBuffer();
            int r3i = r1_byteA.length;
            int r0i = 0;
            while (r0i < r3i) {
                int r4i = r1_byteA[r0i] & 255;
                if (r4i < 16) {
                    r2_StringBuffer.append("0");
                }
                r2_StringBuffer.append(Integer.toHexString(r4i));
                r0i++;
            }
            r0_String = r2_StringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            r0_String = null;
        }
        return r0_String;
    }

    protected static void b(Context r3_Context) {
        Map r0_Map = r3_Context.getSharedPreferences("ADWO_STATISTIC", 0).getAll();
        Editor r1_Editor = r3_Context.getSharedPreferences("ADWO_STATISTIC", 0).edit();
        r1_Editor.clear();
        r1_Editor.commit();
        new Thread(new v(r0_Map)).start();
    }

    protected static void b(Context r4_Context, String r5_String) {
        Intent r0_Intent = new Intent();
        r0_Intent.setAction("android.intent.action.VIEW");
        r0_Intent.setDataAndType(Uri.parse(r5_String), d(r5_String));
        r0_Intent.addFlags(268435456);
        try {
            r4_Context.startActivity(r0_Intent);
        } catch (ActivityNotFoundException e) {
            Log.e("Adwo SDK", new StringBuilder("Could not intent to ").append(r5_String).toString(), e);
        }
    }

    protected static void b(String r2_String, String r3_String, Context r4_Context) {
        Editor r0_Editor = r4_Context.getSharedPreferences("ADWO_DOWNLOAD_TMP_FILE", 0).edit();
        r0_Editor.putString(r2_String, r3_String);
        r0_Editor.commit();
    }

    protected static int c(Context r1_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r1_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (r0_NetworkInfo == null || (!r0_NetworkInfo.isConnected())) ? 0 : 1;
    }

    protected static g c(String r3_String) {
        try {
            FileInputStream r0_FileInputStream = new FileInputStream(r3_String);
            byte[] r2_byteA = new byte[r0_FileInputStream.available()];
            r0_FileInputStream.read(r2_byteA);
            return (g) new ObjectInputStream(new ByteArrayInputStream(r2_byteA)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static void c(Context r9_Context, String r10_String) {
        String r0_String = null;
        int r3i = 0;
        String r2_String;
        try {
            String r2_String_2;
            Intent r4_Intent = new Intent();
            r4_Intent.setAction("android.intent.action.SENDTO");
            String r1_String = r10_String.substring(r10_String.indexOf(":") + 1);
            if (r1_String.contains("?")) {
                String r5_String = r1_String.substring(r1_String.indexOf("?") + 1);
                r2_String_2 = r1_String.substring(0, r1_String.indexOf("?"));
                String[] r5_StringA = r5_String.split("&");
                int r6i = r5_StringA.length;
                r1_String = null;
                while (r3i < r6i) {
                    String r7_String = r5_StringA[r3i];
                    if (r7_String.contains("subject")) {
                        r0_String = r7_String.substring(r7_String.indexOf("=") + 1);
                    } else if (r7_String.contains("body")) {
                        r1_String = r7_String.substring(r7_String.indexOf("=") + 1);
                    }
                    r3i++;
                }
            } else {
                r2_String_2 = r1_String;
                r1_String = null;
            }
            r4_Intent.setData(Uri.parse(new StringBuilder("smsto:").append(r2_String_2).toString()));
            r4_Intent.putExtra("sms_body", r1_String);
            r4_Intent.putExtra("android.intent.extra.SUBJECT", r0_String);
            r4_Intent.addFlags(268435456);
            r9_Context.startActivity(r4_Intent);
        } catch (Exception e) {
            Log.e("Adwo SDK", new StringBuilder("Could not intent to ").append(r10_String).toString(), e);
        }
    }

    private static String d(String r5_String) {
        String r0_String = "*/*";
        int r1i = r5_String.lastIndexOf(".");
        if (r1i < 0) {
            return r0_String;
        }
        String r3_String = r5_String.substring(r1i, r5_String.length()).toLowerCase();
        if (r3_String != RContactStorage.PRIMARY_KEY) {
            r1i = 0;
            while (r1i < i.length) {
                if (r3_String.equals(i[r1i][0])) {
                    r0_String = i[r1i][1];
                }
                r1i++;
            }
        }
        return r0_String;
    }

    protected static void d(Context r4_Context, String r5_String) {
        try {
            Intent r0_Intent = new Intent();
            r0_Intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
            r0_Intent.setAction("android.intent.action.VIEW");
            r0_Intent.setData(Uri.parse(r5_String));
            r0_Intent.addFlags(268435456);
            r4_Context.startActivity(r0_Intent);
        } catch (NullPointerException e) {
            Log.e("Adwo SDK", new StringBuilder("Could not intent to ").append(r5_String).toString(), e);
        }
    }

    protected static boolean d(Context r9_Context) {
        if (r9_Context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            Log.e("Adwo SDK", "Must define permission : android.permission.INTERNET");
            Toast.makeText(r9_Context, "Must define permission : android.permission.INTERNET", 0).show();
            r0i = 0;
        } else {
            r0i = 1;
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == -1) {
            Log.e("Adwo SDK", "Must define permission : android.permission.READ_PHONE_STATE");
            Toast.makeText(r9_Context, "Must define permission : android.permission.READ_PHONE_STATE", 0).show();
            r3i = 0;
        } else {
            r3i = 1;
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            Log.e("Adwo SDK", "Must define permission : android.permission.WRITE_EXTERNAL_STORAGE");
            Toast.makeText(r9_Context, "Must define permission : android.permission.WRITE_EXTERNAL_STORAGE", 0).show();
            r4i = 0;
        } else {
            r4i = 1;
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != -1) {
            m.Q = true;
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != -1) {
            m.F = true;
            a((int)ShareUtils.SHARE_COLLECT, (byte) 1);
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != -1) {
            m.F = true;
            a((int)ShareUtils.SHARE_COPY, (byte) 1);
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.CAMERA") != -1) {
            m.G = true;
            a((int)ShareUtils.SHARE_SMS, (byte) 1);
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") != -1) {
            m.I = true;
            a((int)XListViewFooter.STATE_NODATA, (byte) 1);
        }
        if (r9_Context.checkCallingOrSelfPermission("android.permission.VIBRATE") != -1) {
            m.H = true;
            a((int)XListViewFooter.STATE_NOMORE, (byte) 1);
        }
        if (m.F) {
            Handler r5_Handler = new az(r9_Context);
            m.R = r5_Handler;
            r5_Handler.sendEmptyMessageDelayed(0, ChatEngine.mQueryUnreadInterval);
        }
        if (m.b == null) {
            String r5_String = m.a(r9_Context);
            if (r5_String == null || r5_String.length() != 32) {
                Log.e("Adwo SDK", new StringBuilder("Incorrect Adwo_PID. Should 32 [a-z,0-9] characters: ").append(r5_String).toString());
                r5i = 0;
            } else {
                r5i = 1;
            }
        } else {
            r5i = 1;
        }
        return r0i != 0 && r3i != 0 && r4i != 0 && r5i != 0;
    }

    protected static void e(Context r5_Context, String r6_String) {
        try {
            Intent r0_Intent = new Intent();
            MailTo r1_MailTo = MailTo.parse(r6_String);
            r0_Intent.setAction("android.intent.action.SEND");
            r0_Intent.setData(Uri.parse(r6_String));
            String[] r2_StringA = new String[1];
            r2_StringA[0] = r1_MailTo.getTo();
            r0_Intent.putExtra("android.intent.extra.EMAIL", r2_StringA);
            r0_Intent.putExtra("android.intent.extra.CC", r1_MailTo.getCc());
            r0_Intent.putExtra("android.intent.extra.TEXT", r1_MailTo.getBody());
            r0_Intent.putExtra("android.intent.extra.SUBJECT", r1_MailTo.getSubject());
            r0_Intent.setType("message/rfc882");
            r5_Context.startActivity(Intent.createChooser(r0_Intent, "Select Email Application:"));
        } catch (Exception e) {
            Log.e("Adwo SDK", new StringBuilder("Could not intent to ").append(r6_String).toString(), e);
        }
    }

    protected static void f(Context r4_Context, String r5_String) {
        try {
            r4_Context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(r5_String)));
        } catch (Exception e) {
            Log.e("Adwo SDK", new StringBuilder("Could not intent to ").append(r5_String).toString(), e);
        }
    }

    protected static void g(Context r3_Context, String r4_String) {
        try {
            PackageManager r0_PackageManager = r3_Context.getPackageManager();
            if (r4_String != null) {
                Intent r0_Intent = r0_PackageManager.getLaunchIntentForPackage(r4_String);
                if (r0_Intent != null) {
                    r3_Context.startActivity(r0_Intent);
                    Log.e("Adwo SDK", "Intent to packageName.");
                } else {
                    Log.e("Adwo SDK", "Get the intent failed.");
                }
            }
        } catch (Exception e) {
            Log.e("Adwo SDK", "Could not intent to destination.", e);
        }
    }

    protected static String h(Context r3_Context, String r4_String) {
        String r0_String = null;
        SharedPreferences r1_SharedPreferences = r3_Context.getSharedPreferences("ADWO_DOWNLOAD_TMP_FILE", 0);
        return r1_SharedPreferences.contains(r4_String) ? r1_SharedPreferences.getString(r4_String, r0_String) : null;
    }

    protected static boolean i(Context r2_Context, String r3_String) {
        Editor r0_Editor = r2_Context.getSharedPreferences("ADWO_DOWNLOAD_TMP_FILE", 0).edit();
        r0_Editor.remove(r3_String);
        return r0_Editor.commit();
    }

    protected static boolean j(Context r8_Context, String r9_String) {
        int r0i = r9_String.indexOf("|");
        if (r0i > 0) {
            String r3_String = r9_String.substring(0, r0i);
            h = r3_String;
            List r4_List = r8_Context.getPackageManager().getInstalledApplications(0);
            if (r4_List == null || r4_List.size() == 0) {
                return false;
            }
            List r5_List = new ArrayList();
            int r6i = r4_List.size();
            int r2i = 0;
            while (r2i < r6i) {
                ApplicationInfo r0_ApplicationInfo = (ApplicationInfo) r4_List.get(r2i);
                if ((r0_ApplicationInfo.flags & 1) == 0) {
                    r5_List.add(r0_ApplicationInfo);
                }
                r2i++;
            }
            Iterator r2_Iterator = r5_List.iterator();
            while (r2_Iterator.hasNext()) {
                if (((ApplicationInfo) r2_Iterator.next()).packageName.equals(r3_String)) {
                    return true;
                }
            }
        }
        return false;
    }
}