package com.baidu.location;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.cordova.Globalization;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.push.Utils;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.Rotate3dAnimation;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class f extends Service {
    static String a = null;
    private static String e = null;
    private static File f = null;
    private static File g = null;
    public static final String v = "baidu_location_service";
    private long A;
    private long B;
    private boolean C;
    private c D;
    private SQLiteDatabase E;
    private String F;
    private String G;
    private String H;
    private boolean I;
    private boolean J;
    private String K;
    private int L;
    private boolean M;
    private double N;
    private double O;
    private double P;
    private long Q;
    private boolean R;
    final Handler b;
    final Messenger c;
    private String d;
    private c h;
    private b i;
    private e j;
    private d k;
    private c l;
    private c m;
    private c n;
    private com.baidu.location.c.a o;
    private com.baidu.location.c.a p;
    private com.baidu.location.c.a q;
    private Location r;
    private String s;
    private String t;
    private String u;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;


    public class a implements UncaughtExceptionHandler {
        private Context b;

        a(Context r2_Context) {
            this.b = r2_Context;
            a();
        }

        private String a_(Throwable r3_Throwable) {
            Writer r0_Writer = new StringWriter();
            PrintWriter r1_PrintWriter = new PrintWriter(r0_Writer);
            r3_Throwable.printStackTrace(r1_PrintWriter);
            r1_PrintWriter.close();
            return r0_Writer.toString();
        }

        public void a_() {
            String r0_String = null;
            try {
                File r2_File = new File((Environment.getExternalStorageDirectory().getPath() + "/traces") + "/error_fs.dat");
                if (r2_File.exists()) {
                    RandomAccessFile r3_RandomAccessFile = new RandomAccessFile(r2_File, "rw");
                    r3_RandomAccessFile.seek(280);
                    if (1326 == r3_RandomAccessFile.readInt()) {
                        String r1_String;
                        byte[] r4_byteA;
                        r3_RandomAccessFile.seek(308);
                        int r2i = r3_RandomAccessFile.readInt();
                        if (r2i <= 0 || r2i >= 2048) {
                            r1_String = null;
                        } else {
                            j.if(v, "A" + r2i);
                            r4_byteA = new byte[r2i];
                            r3_RandomAccessFile.read(r4_byteA, 0, r2i);
                            r1_String = new String(r4_byteA, 0, r2i);
                        }
                        r3_RandomAccessFile.seek(600);
                        r2i = r3_RandomAccessFile.readInt();
                        if (r2i <= 0 || r2i >= 2048) {
                            j.if(v, r1_String + r0_String);
                            if (!a(r1_String, r0_String)) {
                                r3_RandomAccessFile.seek(280);
                                r3_RandomAccessFile.writeInt(12346);
                            }
                        } else {
                            j.if(v, "A" + r2i);
                            r4_byteA = new byte[r2i];
                            r3_RandomAccessFile.read(r4_byteA, 0, r2i);
                            r0_String = new String(r4_byteA, 0, r2i);
                            j.if(v, r1_String + r0_String);
                            if (a(r1_String, r0_String)) {
                                r3_RandomAccessFile.close();
                            } else {
                                r3_RandomAccessFile.seek(280);
                                r3_RandomAccessFile.writeInt(12346);
                            }
                        }
                    }
                    r3_RandomAccessFile.close();
                }
            } catch (Exception e) {
            }
        }

        public void a_(File r5_File, String r6_String, String r7_String) {
            try {
                RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(r5_File, "rw");
                r0_RandomAccessFile.seek(280);
                r0_RandomAccessFile.writeInt(12346);
                r0_RandomAccessFile.seek(300);
                r0_RandomAccessFile.writeLong(System.currentTimeMillis());
                byte[] r1_byteA = r6_String.getBytes();
                r0_RandomAccessFile.writeInt(r1_byteA.length);
                r0_RandomAccessFile.write(r1_byteA, 0, r1_byteA.length);
                r0_RandomAccessFile.seek(600);
                r1_byteA = r7_String.getBytes();
                r0_RandomAccessFile.writeInt(r1_byteA.length);
                r0_RandomAccessFile.write(r1_byteA, 0, r1_byteA.length);
                if (!a(r6_String, r7_String)) {
                    r0_RandomAccessFile.seek(280);
                    r0_RandomAccessFile.writeInt(1326);
                }
                r0_RandomAccessFile.close();
            } catch (Exception e) {
            }
        }

        boolean a_(String r8_String, String r9_String) {
            if (!g.a(this.b)) {
                return false;
            }
            int r1i = ImageFetcher.CONNECT_TIMEOUT;
            try {
                HttpUriRequest r2_HttpUriRequest = new HttpPost(j.N);
                List r3_List = new ArrayList();
                r3_List.add(new BasicNameValuePair("e0", r8_String));
                r3_List.add(new BasicNameValuePair("e1", r9_String));
                r2_HttpUriRequest.setEntity(new UrlEncodedFormEntity(r3_List, AdViewNetFetchThread.NetEncoding));
                HttpClient r3_HttpClient = new DefaultHttpClient();
                r3_HttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(r1i));
                r3_HttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(r1i));
                j.if(v, "send begin ...");
                if (r3_HttpClient.execute(r2_HttpUriRequest).getStatusLine().getStatusCode() != 200) {
                    return false;
                }
                j.if(v, "send ok....");
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void uncaughtException(Thread r9_Thread, Throwable r10_Throwable) {
            /*
            r8_this = this;
            r1 = 0;
            r0 = com.baidu.location.j.x;
            if (r0 != 0) goto L_0x000d;
        L_0x0005:
            r0 = android.os.Process.myPid();
            android.os.Process.killProcess(r0);
        L_0x000c:
            return;
        L_0x000d:
            r2 = r8.a(r10);	 //Catch:{ Exception -> 0x00a3 }
            r0 = "baidu_location_service";
            com.baidu.location.j.if(r0, r2);	 //Catch:{ Exception -> 0x00cb }
            r0 = com.baidu.location.f.this;	 //Catch:{ Exception -> 0x00cb }
            r0.h;	 //Catch:{ Exception -> 0x00cb }
            r0 = 0;
            r0 = com.baidu.location.c.a(r0);	 //Catch:{ Exception -> 0x00cb }
            r3 = com.baidu.location.f.this;	 //Catch:{ Exception -> 0x00cb }
            r3 = r3.k;	 //Catch:{ Exception -> 0x00cb }
            if (r3 == 0) goto L_0x0043;
        L_0x0028:
            r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00cb }
            r3.<init>();	 //Catch:{ Exception -> 0x00cb }
            r0 = r3.append(r0);	 //Catch:{ Exception -> 0x00cb }
            r3 = com.baidu.location.f.this;	 //Catch:{ Exception -> 0x00cb }
            r3 = r3.k;	 //Catch:{ Exception -> 0x00cb }
            r3 = r3.byte();	 //Catch:{ Exception -> 0x00cb }
            r0 = r0.append(r3);	 //Catch:{ Exception -> 0x00cb }
            r0 = r0.toString();	 //Catch:{ Exception -> 0x00cb }
        L_0x0043:
            if (r0 == 0) goto L_0x00d0;
        L_0x0045:
            r0 = com.baidu.location.Jni.if(r0);	 //Catch:{ Exception -> 0x00cb }
        L_0x0049:
            r3 = r0;
        L_0x004a:
            r0 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00c9 }
            r0.<init>();	 //Catch:{ Exception -> 0x00c9 }
            r4 = android.os.Environment.getExternalStorageDirectory();	 //Catch:{ Exception -> 0x00c9 }
            r4 = r4.getPath();	 //Catch:{ Exception -> 0x00c9 }
            r0 = r0.append(r4);	 //Catch:{ Exception -> 0x00c9 }
            r4 = "/traces";
            r0 = r0.append(r4);	 //Catch:{ Exception -> 0x00c9 }
            r4 = r0.toString();	 //Catch:{ Exception -> 0x00c9 }
            r0 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00c9 }
            r0.<init>();	 //Catch:{ Exception -> 0x00c9 }
            r0 = r0.append(r4);	 //Catch:{ Exception -> 0x00c9 }
            r5 = "/error_fs.dat";
            r0 = r0.append(r5);	 //Catch:{ Exception -> 0x00c9 }
            r5 = r0.toString();	 //Catch:{ Exception -> 0x00c9 }
            r0 = new java.io.File;	 //Catch:{ Exception -> 0x00c9 }
            r0.<init>(r5);	 //Catch:{ Exception -> 0x00c9 }
            r5 = r0.exists();	 //Catch:{ Exception -> 0x00c9 }
            if (r5 != 0) goto L_0x00a8;
        L_0x0083:
            r5 = new java.io.File;	 //Catch:{ Exception -> 0x00c9 }
            r5.<init>(r4);	 //Catch:{ Exception -> 0x00c9 }
            r4 = r5.exists();	 //Catch:{ Exception -> 0x00c9 }
            if (r4 != 0) goto L_0x0091;
        L_0x008e:
            r5.mkdirs();	 //Catch:{ Exception -> 0x00c9 }
        L_0x0091:
            r4 = r0.createNewFile();	 //Catch:{ Exception -> 0x00c9 }
            if (r4 != 0) goto L_0x00ce;
        L_0x0097:
            r8.a(r1, r3, r2);	 //Catch:{ Exception -> 0x00c9 }
        L_0x009a:
            r0 = android.os.Process.myPid();
            android.os.Process.killProcess(r0);
            goto L_0x000c;
        L_0x00a3:
            r0 = move-exception;
            r0 = r1;
        L_0x00a5:
            r2 = r0;
            r3 = r1;
            goto L_0x004a;
        L_0x00a8:
            r1 = new java.io.RandomAccessFile;	 //Catch:{ Exception -> 0x00c9 }
            r4 = "rw";
            r1.<init>(r0, r4);	 //Catch:{ Exception -> 0x00c9 }
            r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
            r1.seek(r4);	 //Catch:{ Exception -> 0x00c9 }
            r4 = r1.readLong();	 //Catch:{ Exception -> 0x00c9 }
            r6 = java.lang.System.currentTimeMillis();	 //Catch:{ Exception -> 0x00c9 }
            r4 = r6 - r4;
            r6 = 604800000; // 0x240c8400 float:3.046947E-17 double:2.988109026E-315;
            r1 = (r4 > r6 ? 1 : (r4 == r6? 0 : -1));
            if (r1 <= 0) goto L_0x009a;
        L_0x00c5:
            r8.a(r0, r3, r2);	 //Catch:{ Exception -> 0x00c9 }
            goto L_0x009a;
        L_0x00c9:
            r0 = move-exception;
            goto L_0x009a;
        L_0x00cb:
            r0 = move-exception;
            r0 = r2;
            goto L_0x00a5;
        L_0x00ce:
            r1 = r0;
            goto L_0x0097;
        L_0x00d0:
            r0 = r1;
            goto L_0x0049;
            */

        }
    }

    private class b implements Runnable {
        private b() {
        }

        public void run() {
            if (f.this.z) {
                f.this.z = false;
                f.this.j();
            }
        }
    }

    public class c {
        public static final String for = "com.baidu.locTest.LocationServer";
        private final long b;
        private final int c;
        private AlarmManager d;
        private a e;
        private PendingIntent f;
        private Context g;
        private long h;
        private long[] i;
        private int j;
        private com.baidu.location.c.a k;
        private String l;
        private int m;
        private boolean n;
        private boolean o;
        private boolean p;
        private String q;

        public class a extends BroadcastReceiver {
            public void onReceive(Context r5_Context, Intent r6_Intent) {
                String r0_String = r6_Intent.getAction();
                if (r0_String.equals(for)) {
                    com.baidu.location.f.c.this.b.obtainMessage(AdViewNetFetchThread.FETCH_UI_START).sendToTarget();
                } else {
                    try {
                        if (r0_String.equals("android.intent.action.BATTERY_CHANGED")) {
                            int r0i = r6_Intent.getIntExtra(RMsgInfo.COL_STATUS, 0);
                            int r1i = r6_Intent.getIntExtra("plugged", 0);
                            switch (r0i) {
                                case XListViewHeader.STATE_REFRESHING:
                                    com.baidu.location.f.c.this.q = "4";
                                    switch (r1i) {
                                        case XListViewHeader.STATE_READY:
                                            com.baidu.location.f.c.this.q = "6";
                                            break;
                                        case XListViewHeader.STATE_REFRESHING:
                                            com.baidu.location.f.c.this.q = "5";
                                            break;
                                    }
                                    break;
                                case XListViewFooter.STATE_NOMORE:
                                case XListViewFooter.STATE_NODATA:
                                    com.baidu.location.f.c.this.q = "3";
                                    switch (r1i) {
                                        case XListViewHeader.STATE_READY:
                                            com.baidu.location.f.c.this.q = "6";
                                            break;
                                        case XListViewHeader.STATE_REFRESHING:
                                            com.baidu.location.f.c.this.q = "5";
                                            break;
                                    }
                                    break;
                            }
                            com.baidu.location.f.c.this.q = null;
                            switch (r1i) {
                                case XListViewHeader.STATE_READY:
                                    com.baidu.location.f.c.this.q = "6";
                                    break;
                                case XListViewHeader.STATE_REFRESHING:
                                    com.baidu.location.f.c.this.q = "5";
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        com.baidu.location.f.c.this.q = null;
                    }
                }
            }
        }

        public c(Context r9_Context) {
            this.b = 86100000;
            this.c = 200;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = 0;
            this.i = new long[20];
            this.j = 0;
            this.k = null;
            this.l = null;
            this.m = 1;
            this.n = false;
            this.o = false;
            this.p = false;
            this.q = null;
            this.g = r9_Context;
            this.h = System.currentTimeMillis();
            this.d = (AlarmManager) r9_Context.getSystemService("alarm");
            this.e = new a();
            r9_Context.registerReceiver(this.e, new IntentFilter(for));
            this.f = PendingIntent.getBroadcast(r9_Context, 0, new Intent(for), 134217728);
            this.d.setRepeating(XListViewHeader.STATE_REFRESHING, j.M, j.M, this.f);
            f.this.registerReceiver(this.e, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }

        public void a() {
            int r0i = 0;
            if();
            if (f == null) {
            } else {
                RandomAccessFile r1_RandomAccessFile;
                try {
                    r1_RandomAccessFile = new RandomAccessFile(f, "rw");
                } catch (Exception e) {
                }
                if (r1_RandomAccessFile.length() < 1) {
                    r1_RandomAccessFile.close();
                } else {
                    r1_RandomAccessFile.seek(0);
                    int r2i = r1_RandomAccessFile.readInt();
                    r1_RandomAccessFile.seek(0);
                    r1_RandomAccessFile.writeInt(r2i + 1);
                    r1_RandomAccessFile.seek((long) (r2i * 200 + 4));
                    r1_RandomAccessFile.writeLong(System.currentTimeMillis());
                    r1_RandomAccessFile.writeInt(this.m);
                    r1_RandomAccessFile.writeInt(0);
                    r1_RandomAccessFile.writeInt(this.j);
                    r1_RandomAccessFile.writeInt(this.k.do);
                    r1_RandomAccessFile.writeInt(this.k.if);
                    r1_RandomAccessFile.writeInt(this.k.for);
                    r1_RandomAccessFile.writeInt(this.k.try);
                    byte[] r2_byteA = new byte[160];
                    while (r0i < this.j) {
                        r2_byteA[r0i * 8 + 7] = (byte) ((int) this.i[r0i]);
                        r2_byteA[r0i * 8 + 6] = (byte) ((int) (this.i[r0i] >> 8));
                        r2_byteA[r0i * 8 + 5] = (byte) ((int) (this.i[r0i] >> 16));
                        r2_byteA[r0i * 8 + 4] = (byte) ((int) (this.i[r0i] >> 24));
                        r2_byteA[r0i * 8 + 3] = (byte) ((int) (this.i[r0i] >> 32));
                        r2_byteA[r0i * 8 + 2] = (byte) ((int) (this.i[r0i] >> 40));
                        r2_byteA[r0i * 8 + 1] = (byte) ((int) (this.i[r0i] >> 48));
                        r2_byteA[r0i * 8 + 0] = (byte) ((int) (this.i[r0i] >> 56));
                        r0i++;
                    }
                    if (this.j > 0) {
                        r1_RandomAccessFile.write(r2_byteA, 0, this.j * 8);
                    }
                    r1_RandomAccessFile.writeInt(this.j);
                    r1_RandomAccessFile.close();
                }
            }
        }

        public void byte() {
            int r0i = 0;
            if (this.n) {
                this.m = 1;
                j.M = (j.ac * 1000) * 60;
                j.al = j.M >> 2;
                Calendar r1_Calendar = Calendar.getInstance();
                int r2i = r1_Calendar.get(ShareUtils.SHARE_SMS);
                int r3i = r1_Calendar.get(1);
                if (r3i > 2000) {
                    r0i = r3i - 2000;
                }
                int r4i = r1_Calendar.get(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
                String r0_String = r0i + "," + (r1_Calendar.get(XListViewHeader.STATE_REFRESHING) + 1) + "," + r2i + "," + r4i + "," + r1_Calendar.get(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH) + "," + j.ac;
                if (this.o) {
                    this.l = "&tr=" + j.do + "," + r0_String;
                } else {
                    this.l += "|T" + r0_String;
                }
                j.if(v, "trace begin:" + this.l);
                try {
                    RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(g, "rw");
                    r0_RandomAccessFile.seek(12);
                    r0_RandomAccessFile.writeLong(System.currentTimeMillis());
                    r0_RandomAccessFile.writeInt(this.m);
                    r0_RandomAccessFile.close();
                    r0_RandomAccessFile = new RandomAccessFile(f, "rw");
                    r0_RandomAccessFile.seek(0);
                    r0_RandomAccessFile.writeInt(0);
                    r0_RandomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }

        public void case() {
            int r0i = 0;
            f.long();
            if (g == null) {
            } else {
                RandomAccessFile r3_RandomAccessFile;
                try {
                    r3_RandomAccessFile = new RandomAccessFile(g, "rw");
                } catch (Exception e) {
                }
                if (r3_RandomAccessFile.length() < 1) {
                    r3_RandomAccessFile.close();
                } else {
                    byte[] r4_byteA;
                    r3_RandomAccessFile.seek(0);
                    int r2i = r3_RandomAccessFile.readInt();
                    int r4i = r3_RandomAccessFile.readInt();
                    int r1i = r3_RandomAccessFile.readInt();
                    if (this.n && this.o) {
                        j.if(v, "trace new info:" + r2i + ":" + r4i + ":" + r1i);
                        int r5i = (r4i + 1) % 200;
                        r3_RandomAccessFile.seek(4);
                        r3_RandomAccessFile.writeInt(r5i);
                        r2i++;
                        if (r2i >= 200) {
                            r2i = 199;
                        }
                        if (r5i != r1i || r2i <= 0) {
                            j.if(v, "trace new info:" + r2i + ":" + r4i + ":" + r1i);
                            r1i = r5i * 800 + 24;
                            r3_RandomAccessFile.seek((long) (r1i + 4));
                            r4_byteA = this.l.getBytes();
                        } else {
                            r1i = (r1i + 1) % 200;
                            r3_RandomAccessFile.writeInt(r1i);
                            j.if(v, "trace new info:" + r2i + ":" + r4i + ":" + r1i);
                            r1i = r5i * 800 + 24;
                            r3_RandomAccessFile.seek((long) (r1i + 4));
                            r4_byteA = this.l.getBytes();
                        }
                    } else {
                        r1i = r4i * 800 + 24;
                        r3_RandomAccessFile.seek((long) (r1i + 4));
                        r4_byteA = this.l.getBytes();
                    }
                    while (r0i < r4_byteA.length) {
                        r4_byteA[r0i] = (byte) (r4_byteA[r0i] ^ 90);
                        r0i++;
                    }
                    r3_RandomAccessFile.write(r4_byteA, 0, r4_byteA.length);
                    r3_RandomAccessFile.writeInt(r4_byteA.length);
                    r3_RandomAccessFile.seek((long) r1i);
                    r3_RandomAccessFile.writeInt(r4_byteA.length);
                    if (this.n && this.o) {
                        r3_RandomAccessFile.seek(0);
                        r3_RandomAccessFile.writeInt(r2i);
                        r3_RandomAccessFile.close();
                    } else {
                        r3_RandomAccessFile.close();
                    }
                }
            }
        }

        public void do() {
            int r2i = 0;
            try {
                j.if(v, "regular expire...");
                new();
                if (this.p) {
                    this.p = false;
                } else {
                    byte();
                    this.j = 0;
                    this.k = null;
                    if (f.this.j != null) {
                        f.this.j.new();
                    }
                    if (f.this.j != null) {
                        c r4_c = f.this.j.byte();
                        if (r4_c == null || r4_c.for == null) {
                        } else {
                            int r1i = r4_c.for.size();
                            if (r1i > 20) {
                                r1i = 20;
                            }
                            int r3i = 0;
                            while (r3i < r1i) {
                                int r0i;
                                String r5_String = ((ScanResult) r4_c.for.get(r3i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY);
                                try {
                                    r0i = r2i + 1;
                                    this.i[r2i] = Long.parseLong(r5_String, Base64.URL_SAFE);
                                } catch (Exception e) {
                                    r0i = r2i;
                                }
                                r3i++;
                                r2i = r0i;
                            }
                            this.j = r2i;
                        }
                    }
                    if (f.this.h != null) {
                        this.k = f.this.h.a();
                    }
                    if (this.k != null) {
                        for();
                    }
                }
            } catch (Exception e_2) {
            }
        }

        public void for() {
            String r1_String;
            if();
            j.if(v, "trace1:" + this.l);
            try {
                RandomAccessFile r7_RandomAccessFile;
                int r8i;
                int r6i;
                int r9i;
                int r10i;
                int r11i;
                byte[] r4_byteA;
                int r5i;
                long[] r12_longA;
                int r2i;
                int r4i;
                String r4_String;
                long r2j;
                String r5_String;
                String r2_String;
                if (f.this.else()) {
                    r1_String = "y2";
                    if (this.n) {
                        try {
                            r7_RandomAccessFile = new RandomAccessFile(f, "rw");
                        } catch (Exception e) {
                        }
                        if (r7_RandomAccessFile.length() >= 1) {
                            r7_RandomAccessFile.close();
                            return;
                        } else {
                            r8i = r7_RandomAccessFile.readInt();
                            r6i = 0;
                            while (r6i < r8i) {
                                r7_RandomAccessFile.seek((long) (r6i * 200 + 4));
                                r7_RandomAccessFile.readLong();
                                r9i = r7_RandomAccessFile.readInt();
                                r10i = r7_RandomAccessFile.readInt();
                                r11i = r7_RandomAccessFile.readInt();
                                r4_byteA = new byte[200];
                                r7_RandomAccessFile.read(r4_byteA, 0, r11i * 8 + 16);
                                r5i = (((r4_byteA[7] & 255) | ((r4_byteA[6] << 8) & 65280)) | ((r4_byteA[5] << 16) & 16711680)) | ((r4_byteA[4] << 24) & -16777216);
                                r12i = (((r4_byteA[11] & 255) | ((r4_byteA[10] << 8) & 65280)) | ((r4_byteA[9] << 16) & 16711680)) | ((r4_byteA[8] << 24) & -16777216);
                                r13i = (((r4_byteA[15] & 255) | ((r4_byteA[14] << 8) & 65280)) | ((r4_byteA[13] << 16) & 16711680)) | ((r4_byteA[12] << 24) & -16777216);
                                if (this.k.do == ((((r4_byteA[3] & 255) | ((r4_byteA[2] << 8) & 65280)) | ((r4_byteA[1] << 16) & 16711680)) | ((r4_byteA[0] << 24) & -16777216)) && this.k.if == r5i && this.k.for == r12i && this.k.try == r13i) {
                                    r12_longA = new long[r11i];
                                    r2i = 0;
                                    while (r2i < r11i) {
                                        r12_longA[r2i] = ((((((((((long) r4_byteA[(r2i * 8) + 16]) & 255) << 56) | ((((long) r4_byteA[((r2i * 8) + 16) + 1]) & 255) << 48)) | ((((long) r4_byteA[((r2i * 8) + 16) + 2]) & 255) << 40)) | ((((long) r4_byteA[((r2i * 8) + 16) + 3]) & 255) << 32)) | ((((long) r4_byteA[((r2i * 8) + 16) + 4]) & 255) << 24)) | ((((long) r4_byteA[((r2i * 8) + 16) + 5]) & 255) << 16)) | ((((long) r4_byteA[((r2i * 8) + 16) + 6]) & 255) << 8)) | (((long) r4_byteA[((r2i * 8) + 16) + 7]) & 255);
                                        r2i++;
                                    }
                                    r4i = 0;
                                    r5i = 0;
                                    while (r5i < this.j) {
                                        r2i = r4i;
                                        r4i = 0;
                                        while (r4i < r11i) {
                                            if (this.i[r5i] != r12_longA[r4i]) {
                                                r2i++;
                                            }
                                            r4i++;
                                        }
                                        r5i++;
                                        r4i = r2i;
                                    }
                                    if (r4i > 5 || r4i * 8 > this.j + r11i) {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    } else if (r11i == 0 && this.j == 0) {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    } else if (r11i == 1 && this.j == 1 && this.i[0] == r12_longA[0]) {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    } else if (r11i <= 1 || this.j <= 1 || this.i[0] != r12_longA[0] || this.i[1] != r12_longA[1]) {
                                        r6i++;
                                    } else {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    }
                                    if (this.l == null) {
                                        this.l += "|" + r9i + r1_String;
                                        if (this.q == null) {
                                            this.l += this.q;
                                        }
                                    }
                                    j.if(v, "daily info:is same");
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                    break;
                                } else {
                                    r6i++;
                                }
                            }
                            r2i = 0;
                        }
                    } else {
                        r2i = 0;
                    }
                    if (r2i != 0) {
                        r4_String = (this.k.do != 460 ? "|x," : "|x460,") + this.k.if + "," + this.k.for + "," + this.k.try;
                        r2j = 0;
                        if (f.this.j == null) {
                            r5_String = f.this.j.char();
                            if (r5_String == null) {
                                try {
                                    r2j = Long.parseLong(r5_String, Base64.URL_SAFE);
                                } catch (Exception e_2) {
                                }
                            }
                        }
                        if (this.j != 1) {
                            r4_String = r4_String + "w" + Long.toHexString(this.i[0]) + "k";
                            if ((this.i[0] > r2j ? 1 : (this.i[0] == r2j? 0 : -1)) != 0) {
                            }
                        } else {
                            if (this.j <= 1) {
                                r4_String = r4_String + "w" + Long.toHexString(this.i[0]);
                                if (this.i[0] != r2j) {
                                    r4_String = r4_String + "k";
                                    r2j = 0;
                                }
                                r2_String = (r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0 ? r4_String + "," + Long.toHexString(r2j) + "k" : r4_String + "," + Long.toHexString(this.i[1]);
                            }
                        }
                        this.l += r2_String + r1_String;
                        if (this.q == null) {
                            this.l += this.q;
                        }
                        a();
                    }
                    j.if(v, "trace2:" + this.l);
                    case();
                    this.l = null;
                } else {
                    r1_String = "y1";
                    if (this.n) {
                        r2i = 0;
                    } else {
                        r7_RandomAccessFile = new RandomAccessFile(f, "rw");
                        if (r7_RandomAccessFile.length() >= 1) {
                            r8i = r7_RandomAccessFile.readInt();
                            r6i = 0;
                            while (r6i < r8i) {
                                r7_RandomAccessFile.seek((long) (r6i * 200 + 4));
                                r7_RandomAccessFile.readLong();
                                r9i = r7_RandomAccessFile.readInt();
                                r10i = r7_RandomAccessFile.readInt();
                                r11i = r7_RandomAccessFile.readInt();
                                r4_byteA = new byte[200];
                                r7_RandomAccessFile.read(r4_byteA, 0, r11i * 8 + 16);
                                r5i = (((r4_byteA[7] & 255) | ((r4_byteA[6] << 8) & 65280)) | ((r4_byteA[5] << 16) & 16711680)) | ((r4_byteA[4] << 24) & -16777216);
                                r12i = (((r4_byteA[11] & 255) | ((r4_byteA[10] << 8) & 65280)) | ((r4_byteA[9] << 16) & 16711680)) | ((r4_byteA[8] << 24) & -16777216);
                                r13i = (((r4_byteA[15] & 255) | ((r4_byteA[14] << 8) & 65280)) | ((r4_byteA[13] << 16) & 16711680)) | ((r4_byteA[12] << 24) & -16777216);
                                if (this.k.do == ((((r4_byteA[3] & 255) | ((r4_byteA[2] << 8) & 65280)) | ((r4_byteA[1] << 16) & 16711680)) | ((r4_byteA[0] << 24) & -16777216)) || this.k.if == r5i || this.k.for == r12i || this.k.try == r13i) {
                                    r6i++;
                                } else {
                                    r12_longA = new long[r11i];
                                    r2i = 0;
                                    while (r2i < r11i) {
                                        r12_longA[r2i] = ((((((((((long) r4_byteA[(r2i * 8) + 16]) & 255) << 56) | ((((long) r4_byteA[((r2i * 8) + 16) + 1]) & 255) << 48)) | ((((long) r4_byteA[((r2i * 8) + 16) + 2]) & 255) << 40)) | ((((long) r4_byteA[((r2i * 8) + 16) + 3]) & 255) << 32)) | ((((long) r4_byteA[((r2i * 8) + 16) + 4]) & 255) << 24)) | ((((long) r4_byteA[((r2i * 8) + 16) + 5]) & 255) << 16)) | ((((long) r4_byteA[((r2i * 8) + 16) + 6]) & 255) << 8)) | (((long) r4_byteA[((r2i * 8) + 16) + 7]) & 255);
                                        r2i++;
                                    }
                                    r4i = 0;
                                    r5i = 0;
                                    while (r5i < this.j) {
                                        r2i = r4i;
                                        r4i = 0;
                                        while (r4i < r11i) {
                                            if (this.i[r5i] != r12_longA[r4i]) {
                                                r4i++;
                                            } else {
                                                r2i++;
                                                r4i++;
                                            }
                                        }
                                        r5i++;
                                        r4i = r2i;
                                    }
                                    if (r4i > 5 || r4i * 8 > this.j + r11i) {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    } else if (r11i == 0 || this.j == 0) {
                                        if (r11i == 1 || this.j == 1 || this.i[0] == r12_longA[0]) {
                                            if (r11i <= 1 || this.j <= 1 || this.i[0] != r12_longA[0] || this.i[1] != r12_longA[1]) {
                                                r6i++;
                                            } else {
                                                r2i = 1;
                                                r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                                r7_RandomAccessFile.writeInt(r10i + 1);
                                            }
                                        } else {
                                            r2i = 1;
                                            r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                            r7_RandomAccessFile.writeInt(r10i + 1);
                                        }
                                    } else {
                                        r2i = 1;
                                        r7_RandomAccessFile.seek((long) (r6i * 200 + 16));
                                        r7_RandomAccessFile.writeInt(r10i + 1);
                                    }
                                    if (this.l == null) {
                                        j.if(v, "daily info:is same");
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                    } else {
                                        this.l += "|" + r9i + r1_String;
                                        if (this.q == null) {
                                            j.if(v, "daily info:is same");
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                        } else {
                                            this.l += this.q;
                                            j.if(v, "daily info:is same");
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                            break;
                                        }
                                    }
                                }
                            }
                            r2i = 0;
                        } else {
                            r7_RandomAccessFile.close();
                            return;
                        }
                    }
                    if (r2i != 0) {
                        j.if(v, "trace2:" + this.l);
                        case();
                        this.l = null;
                    } else {
                        if (this.k.do != 460) {
                        }
                        r4_String = (this.k.do != 460 ? "|x," : "|x460,") + this.k.if + "," + this.k.for + "," + this.k.try;
                        r2j = 0;
                        if (f.this.j == null) {
                            if (this.j != 1) {
                                if (this.j <= 1) {
                                } else {
                                    r4_String = r4_String + "w" + Long.toHexString(this.i[0]);
                                    if (this.i[0] != r2j) {
                                        if ((r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0) {
                                        }
                                    } else {
                                        r4_String = r4_String + "k";
                                        r2j = 0;
                                        if ((r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0) {
                                        }
                                    }
                                }
                            } else {
                                r4_String = r4_String + "w" + Long.toHexString(this.i[0]) + "k";
                                r2_String = (this.i[0] > r2j ? 1 : (this.i[0] == r2j? 0 : -1)) != 0 ? r4_String : r4_String + "k";
                            }
                            this.l += r2_String + r1_String;
                            if (this.q == null) {
                                a();
                                j.if(v, "trace2:" + this.l);
                                case();
                                this.l = null;
                            } else {
                                this.l += this.q;
                                a();
                                j.if(v, "trace2:" + this.l);
                                case();
                                this.l = null;
                            }
                        } else {
                            r5_String = f.this.j.char();
                            if (r5_String == null) {
                                if (this.j != 1) {
                                    r4_String = r4_String + "w" + Long.toHexString(this.i[0]) + "k";
                                    if ((this.i[0] > r2j ? 1 : (this.i[0] == r2j? 0 : -1)) != 0) {
                                    }
                                } else {
                                    if (this.j <= 1) {
                                        r4_String = r4_String + "w" + Long.toHexString(this.i[0]);
                                        if (this.i[0] != r2j) {
                                            r4_String = r4_String + "k";
                                            r2j = 0;
                                        }
                                        if ((r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0) {
                                        }
                                    }
                                }
                                this.l += r2_String + r1_String;
                                if (this.q == null) {
                                    this.l += this.q;
                                }
                                a();
                                j.if(v, "trace2:" + this.l);
                                case();
                                this.l = null;
                            } else {
                                r2j = Long.parseLong(r5_String, Base64.URL_SAFE);
                                if (this.j != 1) {
                                    if (this.j <= 1) {
                                    } else {
                                        r4_String = r4_String + "w" + Long.toHexString(this.i[0]);
                                        if (this.i[0] != r2j) {
                                            if ((r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0) {
                                            }
                                        } else {
                                            r4_String = r4_String + "k";
                                            r2j = 0;
                                            if ((r2j > 0 ? 1 : (r2j == 0? 0 : -1)) <= 0) {
                                            }
                                        }
                                    }
                                } else {
                                    r4_String = r4_String + "w" + Long.toHexString(this.i[0]) + "k";
                                    if ((this.i[0] > r2j ? 1 : (this.i[0] == r2j? 0 : -1)) != 0) {
                                    }
                                }
                                this.l += r2_String + r1_String;
                                if (this.q == null) {
                                    a();
                                    j.if(v, "trace2:" + this.l);
                                    case();
                                    this.l = null;
                                } else {
                                    this.l += this.q;
                                    a();
                                    j.if(v, "trace2:" + this.l);
                                    case();
                                    this.l = null;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e_3) {
                r1_String = Rotate3dAnimation.ROTATE_Y;
            }
        }

        public void if() {
            try {
                if (f.this.d != null) {
                    f = new File(f.this.d);
                    if (!f.exists()) {
                        File r0_File = new File(a);
                        if (!r0_File.exists()) {
                            r0_File.mkdirs();
                        }
                        f.createNewFile();
                        RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(f, "rw");
                        r0_RandomAccessFile.seek(0);
                        r0_RandomAccessFile.writeInt(0);
                        r0_RandomAccessFile.close();
                    }
                } else {
                    f = null;
                }
            } catch (Exception e) {
                f = null;
            }
        }

        public void int() {
        }

        public void new() {
            boolean r0z = false;
            this.n = false;
            this.o = false;
            if();
            f.long();
            try {
                RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(g, "rw");
                r1_RandomAccessFile.seek(0);
                int r2i = r1_RandomAccessFile.readInt();
                int r3i = r1_RandomAccessFile.readInt();
                r1_RandomAccessFile.readInt();
                long r4j = r1_RandomAccessFile.readLong();
                int r6i = r1_RandomAccessFile.readInt();
                if (r2i < 0) {
                    this.n = true;
                    this.o = true;
                    r1_RandomAccessFile.close();
                } else {
                    r1_RandomAccessFile.seek((long) (r3i * 800 + 24));
                    r2i = r1_RandomAccessFile.readInt();
                    if (r2i > 680) {
                        this.n = true;
                        this.o = true;
                        r1_RandomAccessFile.close();
                    } else {
                        byte[] r3_byteA = new byte[800];
                        r1_RandomAccessFile.read(r3_byteA, 0, r2i);
                        if (r2i != r1_RandomAccessFile.readInt()) {
                            j.if(v, "trace true check fail");
                            this.n = true;
                            this.o = true;
                            r1_RandomAccessFile.close();
                        }
                        while (r0z < r3_byteA.length) {
                            r3_byteA[r0z] = (byte) (r3_byteA[r0z] ^ 90);
                            r0z++;
                        }
                        this.l = new String(r3_byteA, 0, r2i);
                        if (this.l.contains("&tr=")) {
                            long r2j = System.currentTimeMillis();
                            r4j = r2j - r4j;
                            if (r4j > j.M * 3 - j.al) {
                                this.n = true;
                            } else if (r4j > j.M * 2 - j.al) {
                                this.l += "|" + r6i;
                                this.m = r6i + 2;
                            } else if (r4j > j.M - j.al) {
                                this.m = r6i + 1;
                            } else {
                                this.p = true;
                                r1_RandomAccessFile.close();
                            }
                            r1_RandomAccessFile.seek(12);
                            r1_RandomAccessFile.writeLong(r2j);
                            r1_RandomAccessFile.writeInt(this.m);
                            r1_RandomAccessFile.close();
                            RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(f, "rw");
                            r0_RandomAccessFile.seek(0);
                            if (r0_RandomAccessFile.readInt() == 0) {
                                this.n = true;
                                r0_RandomAccessFile.close();
                                j.if(v, "Day file number 0");
                            } else {
                                r0_RandomAccessFile.close();
                            }
                        } else {
                            this.n = true;
                            this.o = true;
                            r1_RandomAccessFile.close();
                        }
                    }
                }
            } catch (Exception e) {
                j.if(v, "exception!!!");
                this.n = true;
                this.o = true;
            }
        }

        public void try() {
            this.g.unregisterReceiver(this.e);
            this.d.cancel(this.f);
            f = null;
        }
    }

    public class d extends Handler {
        public void handleMessage(Message r5_Message) {
            if (f.this.w) {
                switch (r5_Message.what) {
                    case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                        f.this.a(r5_Message);
                        break;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                        f.this.b(r5_Message);
                        break;
                    case NearbySelectView.TIME_15MIN:
                        f.this.c(r5_Message);
                        break;
                    case AdViewUtil.NETWORK_TYPE_WOOBOO:
                        f.this.a(r5_Message, (int)AdViewUtil.NETWORK_TYPE_WOOBOO);
                        break;
                    case Util.BEGIN_TIME:
                        f.this.g(r5_Message);
                        break;
                    case AdViewUtil.NETWORK_TYPE_WIYUN:
                        f.this.e(r5_Message);
                        break;
                    case AdViewUtil.NETWORK_TYPE_ADCHINA:
                        f.this.a(r5_Message, (int)AdViewUtil.NETWORK_TYPE_ADCHINA);
                        break;
                    case AdViewUtil.NETWORK_TYPE_ADVIEWAD:
                        f.this.f(r5_Message);
                        break;
                    case AdViewUtil.NETWORK_TYPE_VPON:
                        f.this.d();
                        break;
                    case AdViewUtil.NETWORK_TYPE_IZPTEC:
                        f.this.e();
                        break;
                    case AdViewUtil.NETWORK_TYPE_DoubleClick:
                        f.this.c();
                        break;
                    case AdViewUtil.NETWORK_TYPE_ADLANTIS:
                        f.this.f();
                        break;
                    case AdViewUtil.NETWORK_TYPE_YUNYUN:
                        f.this.l();
                        break;
                    case AdViewUtil.NETWORK_TYPE_PUNCHBOX:
                        f.this.d(r5_Message);
                        break;
                    case BDLocation.TypeCriteriaException:
                    case BDLocation.TypeNetWorkException:
                        f.this.a((int)AdViewUtil.NETWORK_TYPE_WOOBOO);
                        break;
                    case RContact.MM_CONTACTFLAG_FAVOURCONTACT:
                    case BDLocation.TypeCacheLocation:
                        f.this.a((int)AdViewUtil.NETWORK_TYPE_ADCHINA);
                        break;
                    case 81:
                        f.this.g();
                        break;
                    case 91:
                        f.this.k();
                        break;
                    case 92:
                        f.this.n();
                        break;
                    case AdViewNetFetchThread.FETCH_UI_START:
                        if (f.this.D != null) {
                            f.this.D.do();
                        }
                        break;
                }
            }
            super.handleMessage(r5_Message);
        }
    }

    static {
        a = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata";
        e = a + "/glb.dat";
        f = null;
        g = null;
    }

    public f() {
        this.d = a + "/vm.dat";
        this.b = new d();
        this.c = new Messenger(this.b);
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.w = false;
        this.x = true;
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = 0;
        this.C = true;
        this.D = null;
        this.E = null;
        this.F = "bdcltb09";
        this.G = null;
        this.H = null;
        this.I = false;
        this.J = false;
        this.K = null;
        this.L = 0;
        this.M = true;
        this.N = 0.0d;
        this.O = 0.0d;
        this.P = 0.0d;
        this.Q = 0;
        this.R = false;
    }

    private String a(String r6_String) {
        String r0_String = null;
        j.if(v, "generate locdata ...");
        String r1_String;
        Object[] r2_ObjectA;
        String r2_String;
        if ((this.o == null || (!this.o.do())) && this.h != null) {
            this.o = this.h.a();
            this.G = this.o.a();
            if (this.o == null) {
                j.a(v, this.o.if());
            } else {
                j.a(v, "cellInfo null...");
            }
            if ((this.l == null || (!this.l.for())) && this.j != null) {
                this.l = this.j.byte();
                if (this.l == null) {
                    j.a(v, this.l.else());
                } else {
                    j.a(v, "wifi list null");
                }
                if (this.i == null || (!this.i.new())) {
                    this.r = null;
                } else {
                    this.r = this.i.try();
                }
                if (this.k == null) {
                    r0_String = this.k.byte();
                }
                if (3 != g.do(this)) {
                    r1_String = "&cn=32";
                } else {
                    r2_ObjectA = new Object[1];
                    r2_ObjectA[0] = Integer.valueOf(this.h.new());
                    r1_String = String.format("&cn=%d", r2_ObjectA);
                }
                if (!this.x) {
                    r2_String = n.if();
                    if (r2_String == null) {
                        r1_String = r1_String + r2_String;
                    }
                }
                r0_String = r1_String + r0_String;
                if (r6_String == null) {
                    r0_String = r6_String + r0_String;
                }
                return j.a(this.o, this.l, this.r, r0_String, 0);
            } else {
                if (this.l == null) {
                    j.a(v, "wifi list null");
                } else {
                    j.a(v, this.l.else());
                }
                if (this.i == null || this.i.new()) {
                    this.r = null;
                } else {
                    this.r = this.i.try();
                }
                if (this.k == null) {
                    if (3 != g.do(this)) {
                        r2_ObjectA = new Object[1];
                        r2_ObjectA[0] = Integer.valueOf(this.h.new());
                        r1_String = String.format("&cn=%d", r2_ObjectA);
                    } else {
                        r1_String = "&cn=32";
                    }
                    if (this.x) {
                        r0_String = r1_String + r0_String;
                        if (r6_String == null) {
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        }
                        r0_String = r6_String + r0_String;
                        return j.a(this.o, this.l, this.r, r0_String, 0);
                    } else {
                        r2_String = n.if();
                        if (r2_String == null) {
                            r0_String = r1_String + r0_String;
                            if (r6_String == null) {
                                r0_String = r6_String + r0_String;
                            }
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        } else {
                            r1_String = r1_String + r2_String;
                            r0_String = r1_String + r0_String;
                            if (r6_String == null) {
                                return j.a(this.o, this.l, this.r, r0_String, 0);
                            }
                            r0_String = r6_String + r0_String;
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        }
                    }
                } else {
                    r0_String = this.k.byte();
                    if (3 != g.do(this)) {
                        r1_String = "&cn=32";
                    } else {
                        r2_ObjectA = new Object[1];
                        r2_ObjectA[0] = Integer.valueOf(this.h.new());
                        r1_String = String.format("&cn=%d", r2_ObjectA);
                    }
                    if (this.x) {
                        r2_String = n.if();
                        if (r2_String == null) {
                            r1_String = r1_String + r2_String;
                        }
                    }
                    r0_String = r1_String + r0_String;
                    if (r6_String == null) {
                        r0_String = r6_String + r0_String;
                    }
                    return j.a(this.o, this.l, this.r, r0_String, 0);
                }
            }
        } else {
            this.G = this.o.a();
            if (this.o == null) {
                j.a(v, "cellInfo null...");
            } else {
                j.a(v, this.o.if());
            }
            if ((this.l == null || this.l.for()) && this.j != null) {
                this.l = this.j.byte();
                if (this.l == null) {
                    j.a(v, this.l.else());
                } else {
                    j.a(v, "wifi list null");
                }
                if (this.i == null || this.i.new()) {
                    this.r = null;
                } else {
                    this.r = this.i.try();
                }
                if (this.k == null) {
                    r0_String = this.k.byte();
                }
                if (3 != g.do(this)) {
                    r2_ObjectA = new Object[1];
                    r2_ObjectA[0] = Integer.valueOf(this.h.new());
                    r1_String = String.format("&cn=%d", r2_ObjectA);
                } else {
                    r1_String = "&cn=32";
                }
                if (this.x) {
                    r0_String = r1_String + r0_String;
                    if (r6_String == null) {
                        return j.a(this.o, this.l, this.r, r0_String, 0);
                    }
                    r0_String = r6_String + r0_String;
                    return j.a(this.o, this.l, this.r, r0_String, 0);
                } else {
                    r2_String = n.if();
                    if (r2_String == null) {
                        r0_String = r1_String + r0_String;
                        if (r6_String == null) {
                            r0_String = r6_String + r0_String;
                        }
                        return j.a(this.o, this.l, this.r, r0_String, 0);
                    } else {
                        r1_String = r1_String + r2_String;
                        r0_String = r1_String + r0_String;
                        if (r6_String == null) {
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        }
                        r0_String = r6_String + r0_String;
                        return j.a(this.o, this.l, this.r, r0_String, 0);
                    }
                }
            } else {
                if (this.l == null) {
                    j.a(v, "wifi list null");
                } else {
                    j.a(v, this.l.else());
                }
                if (this.i == null || this.i.new()) {
                    this.r = null;
                } else {
                    this.r = this.i.try();
                }
                if (this.k == null) {
                    if (3 != g.do(this)) {
                        r1_String = "&cn=32";
                    } else {
                        r2_ObjectA = new Object[1];
                        r2_ObjectA[0] = Integer.valueOf(this.h.new());
                        r1_String = String.format("&cn=%d", r2_ObjectA);
                    }
                    if (this.x) {
                        r2_String = n.if();
                        if (r2_String == null) {
                            r1_String = r1_String + r2_String;
                        }
                    }
                    r0_String = r1_String + r0_String;
                    if (r6_String == null) {
                        r0_String = r6_String + r0_String;
                    }
                    return j.a(this.o, this.l, this.r, r0_String, 0);
                } else {
                    r0_String = this.k.byte();
                    if (3 != g.do(this)) {
                        r2_ObjectA = new Object[1];
                        r2_ObjectA[0] = Integer.valueOf(this.h.new());
                        r1_String = String.format("&cn=%d", r2_ObjectA);
                    } else {
                        r1_String = "&cn=32";
                    }
                    if (this.x) {
                        r0_String = r1_String + r0_String;
                        if (r6_String == null) {
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        }
                        r0_String = r6_String + r0_String;
                        return j.a(this.o, this.l, this.r, r0_String, 0);
                    } else {
                        r2_String = n.if();
                        if (r2_String == null) {
                            r0_String = r1_String + r0_String;
                            if (r6_String == null) {
                                r0_String = r6_String + r0_String;
                            }
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        } else {
                            r1_String = r1_String + r2_String;
                            r0_String = r1_String + r0_String;
                            if (r6_String == null) {
                                return j.a(this.o, this.l, this.r, r0_String, 0);
                            }
                            r0_String = r6_String + r0_String;
                            return j.a(this.o, this.l, this.r, r0_String, 0);
                        }
                    }
                }
            }
        }
    }

    private String a(boolean r9z) {
        int r2i = XListViewFooter.STATE_NODATA;
        if (this.I) {
            String r0_String;
            Object[] r1_ObjectA;
            if (r9z) {
                r0_String = "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
                r1_ObjectA = new Object[r2i];
                r1_ObjectA[0] = Double.valueOf(this.N);
                r1_ObjectA[1] = Double.valueOf(this.O);
                r1_ObjectA[2] = Double.valueOf(this.P);
                r1_ObjectA[3] = Boolean.valueOf(true);
                return String.format(r0_String, r1_ObjectA);
            } else {
                r0_String = "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"68\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
                r1_ObjectA = new Object[r2i];
                r1_ObjectA[0] = Double.valueOf(this.N);
                r1_ObjectA[1] = Double.valueOf(this.O);
                r1_ObjectA[2] = Double.valueOf(this.P);
                r1_ObjectA[3] = Boolean.valueOf(this.M);
                return String.format(r0_String, r1_ObjectA);
            }
        } else {
            if (r9z) {
                return "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"67\"}}";
            }
            return "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"63\"}}";
        }
    }

    private void a(int r4i) {
        j.if(v, "on network exception");
        j.a(v, "on network exception");
        this.t = null;
        this.m = null;
        if (this.k != null) {
            this.k.a(b(false), r4i);
        }
        if (r4i == 21) {
            m();
        }
    }

    private void a(Message r2_Message) {
        if (this.k != null) {
            this.k.int(r2_Message);
        }
        if (this.j != null) {
            this.j.case();
        }
        if (this.C) {
            g.for(this.b);
            this.C = false;
        }
    }

    private void a(Message r12_Message, int r13i) {
        j.if(v, "on network success");
        j.a(v, "on network success");
        String r8_String = (String) r12_Message.obj;
        j.if(v, "network:" + r8_String);
        if (this.k != null) {
            this.k.a(r8_String, r13i);
        }
        if (j.do(r8_String)) {
            if (r13i == 21) {
                this.t = r8_String;
            } else {
                this.u = r8_String;
            }
        } else if (r13i == 21) {
            this.t = null;
        } else {
            this.u = null;
        }
        int r0i = j.a(r8_String, "ssid\":\"", "\"");
        if (r0i == ExploreByTouchHelper.INVALID_ID || this.m == null) {
            this.s = null;
        } else {
            this.s = this.m.if(r0i);
        }
        c(r8_String);
        double r0d = j.c(r8_String, "a\":\"", "\"");
        if (r0d != 4.9E-324d) {
            n.a(r0d, j.c(r8_String, "b\":\"", "\""), j.c(r8_String, "c\":\"", "\""), j.c(r8_String, "b\":\"", "\""));
        }
        r0i = j.a(r8_String, "rWifiN\":\"", "\"");
        if (r0i > NearbySelectView.TIME_15MIN) {
            j.Y = r0i;
        }
        r0i = j.a(r8_String, "rWifiT\":\"", "\"");
        if (r0i > 500) {
            j.S = r0i;
        }
        float r0f = j.b(r8_String, "hSpeedDis\":\"", "\"");
        if (r0f > 5.0f) {
            j.Q = r0f;
        }
        r0f = j.b(r8_String, "mSpeedDis\":\"", "\"");
        if (r0f > 5.0f) {
            j.ai = r0f;
        }
        r0f = j.b(r8_String, "mWifiR\":\"", "\"");
        if (r0f >= 1.0f || ((double) r0f) <= 0.2d) {
            if (r13i != 21) {
                m();
            }
        } else {
            j.byte = r0f;
            if (r13i != 21) {
            } else {
                m();
            }
        }
    }

    private boolean a(com.baidu.location.c.a r4_com_baidu_location_c_a) {
        boolean r0z = true;
        if (this.h == null) {
            return false;
        }
        this.o = this.h.a();
        if (this.o == r4_com_baidu_location_c_a) {
            return false;
        }
        if (this.o == null || r4_com_baidu_location_c_a == null) {
            return true;
        }
        if (!r4_com_baidu_location_c_a.a(this.o)) {
            return r0z;
        }
        r0z = false;
        return r0z;
    }

    private boolean a(c r4_c) {
        boolean r0z = true;
        if (this.j == null) {
            return false;
        }
        this.l = this.j.byte();
        if (r4_c == this.l) {
            return false;
        }
        if (this.l == null || r4_c == null) {
            return true;
        }
        if (!r4_c.a(this.l)) {
            return r0z;
        }
        r0z = false;
        return r0z;
    }

    private String b(boolean r2z) {
        if ((this.o == null || (!this.o.do())) && this.h != null) {
            this.o = this.h.a();
            b(this.o.a());
            return a(r2z);
        } else {
            b(this.o.a());
            return a(r2z);
        }
    }

    private void b(Message r2_Message) {
        if (this.k != null) {
            this.k.if(r2_Message);
        }
    }

    private void b(String r6_String) {
        if (this.E == null || r6_String == null) {
            j.if(v, "db is null...");
            this.I = false;
        } else {
            j.if(v, "LOCATING...");
            if (System.currentTimeMillis() - this.Q < 1500 || r6_String.equals(this.H)) {
            } else {
                this.I = false;
                try {
                    Cursor r0_Cursor = this.E.rawQuery("select * from " + this.F + " where id = \"" + r6_String + "\";", null);
                    this.H = r6_String;
                    this.Q = System.currentTimeMillis();
                    if (r0_Cursor != null) {
                        if (r0_Cursor.moveToFirst()) {
                            j.if(v, "lookup DB success:" + this.H);
                            this.N = r0_Cursor.getDouble(1) - 1235.4323d;
                            this.P = r0_Cursor.getDouble(XListViewHeader.STATE_REFRESHING) - 4326.0d;
                            this.O = r0_Cursor.getDouble(XListViewFooter.STATE_NOMORE) - 2367.3217d;
                            this.I = true;
                            j.if(v, "lookup DB success:x" + this.N + Rotate3dAnimation.ROTATE_Y + this.O + "r" + this.P);
                        }
                        r0_Cursor.close();
                    }
                } catch (Exception e) {
                    this.Q = System.currentTimeMillis();
                }
            }
        }
    }

    private void c() {
        if (this.i == null) {
        } else {
            j.if(v, "on new gps...");
            Location r0_Location = this.i.try();
            if ((!this.i.new()) || (!n.a(r0_Location, true)) || this.h == null || this.j == null || this.k == null) {
                if (this.k == null || (!this.i.new())) {
                } else {
                    this.k.if(this.i.int());
                }
            } else {
                if (this.j != null) {
                    this.j.a();
                }
                n.a(this.h.a(), this.j.int(), r0_Location, this.k.byte());
                if (this.k == null || this.i.new()) {
                } else {
                    this.k.if(this.i.int());
                }
            }
        }
    }

    private void c(Message r2_Message) {
        if (this.k == null || (!this.k.for(r2_Message)) || this.j == null) {
            this.t = null;
        } else {
            this.j.for();
            this.t = null;
        }
    }

    private void c(String r14_String) {
        int r1i = 1;
        int r0i = 0;
        if (this.E == null || (!this.M)) {
        } else {
            JSONObject r5_JSONObject;
            int r6i;
            double r4d;
            float r1f;
            double r2d;
            float r2f = 0.0f;
            try {
                j.if(v, "DB:" + r14_String);
                r5_JSONObject = new JSONObject(r14_String);
                r6i = Integer.parseInt(r5_JSONObject.getJSONObject("result").getString(QQDialogAuthorizeActivity.ERROR_RET));
                if (r0i == 0) {
                    ContentValues r1_ContentValues = new ContentValues();
                    r1_ContentValues.put(Globalization.TIME, Double.valueOf(r4d + 1235.4323d));
                    r1_ContentValues.put("tag", Float.valueOf(4326.0f + r1f));
                    r1_ContentValues.put(QsbkDatabase.TYPE, Double.valueOf(r2d + 2367.3217d));
                    try {
                        if (this.E.update(this.F, r1_ContentValues, "id = \"" + this.G + "\"", null) <= 0) {
                            r1_ContentValues.put(LocaleUtil.INDONESIAN, this.G);
                            this.E.insert(this.F, null, r1_ContentValues);
                            j.if(v, "insert DB success!");
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e_2) {
                j.if(v, "DB PARSE:exp!");
            }
            if (r6i == BDLocation.TypeNetWorkLocation) {
                JSONObject r6_JSONObject = r5_JSONObject.getJSONObject(Utils.RESPONSE_CONTENT);
                if (r6_JSONObject.has("clf")) {
                    String r1_String = r6_JSONObject.getString("clf");
                    if (r1_String.equals("0")) {
                        JSONObject r1_JSONObject = r6_JSONObject.getJSONObject("point");
                        r4d = Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_X));
                        r2d = Double.parseDouble(r1_JSONObject.getString(Rotate3dAnimation.ROTATE_Y));
                        r1f = Float.parseFloat(r6_JSONObject.getString("radius"));
                    } else {
                        String[] r1_StringA = r1_String.split("\\|");
                        r4d = Double.parseDouble(r1_StringA[0]);
                        r2d = Double.parseDouble(r1_StringA[1]);
                        r1f = Float.parseFloat(r1_StringA[2]);
                    }
                    j.if(v, "DB PARSE:x" + r4d + Rotate3dAnimation.ROTATE_Y + r2d + "R" + r1f);
                }
                r0i = r1i;
                r1f = r2f;
                r4d = 0.0d;
                r2d = 0.0d;
            } else {
                if (r6i == 167) {
                    this.E.delete(this.F, "id = \"" + this.G + "\"", null);
                }
                r0i = r1i;
                r1f = r2f;
                r4d = 0.0d;
                r2d = 0.0d;
            }
        }
    }

    private void d() {
        j.if(v, "on new cell ...");
    }

    private void d(Message r4_Message) {
        if (r4_Message == null || r4_Message.obj == null) {
            j.if(v, "Gps updateloation is null");
        } else {
            Location r0_Location = (Location) r4_Message.obj;
            if (r0_Location != null) {
                j.if(v, "on update gps...");
                if ((!n.a(r0_Location, true)) || this.h == null || this.j == null || this.k == null) {
                }
            }
        }
    }

    private void e() {
        j.if(v, "on new wifi ...");
        if (this.z) {
            j();
            this.z = false;
        }
    }

    private void e(Message r5_Message) {
        if (System.currentTimeMillis() - this.B < 3000) {
            j.if(v, "request too frequency ...");
            if (this.u != null) {
                this.k.a(this.u, (int)AdViewUtil.NETWORK_TYPE_ADCHINA);
                return;
            }
        }
        if (this.k != null) {
            String r0_String = a(this.k.a(r5_Message));
            if (this.s != null) {
                r0_String = r0_String + this.s;
                this.s = null;
            }
            g.do(this);
            if (g.if(r0_String, this.b)) {
                this.q = this.o;
                this.n = this.l;
            } else {
                j.if(v, "request poi error ..");
            }
            this.B = System.currentTimeMillis();
        }
    }

    private void f_() {
        j.if(v, "on switch gps ...");
        if (this.k == null) {
        } else if (this.k.for()) {
            if (this.i == null) {
                this.i = new b(this, this.b);
            }
            this.i.k();
        } else {
            if (this.i != null) {
                this.i.l();
                this.i = null;
            }
        }
    }

    private void f_(Message r3_Message) {
        if (this.k == null) {
        } else {
            this.k.a(b(true), r3_Message);
        }
    }

    private void g() {
    }

    private void g(Message r5_Message) {
        boolean r2z = true;
        j.if(v, "on request location ...");
        j.a(v, "on request location ...");
        if (this.k == null) {
        } else if (this.k.do(r5_Message) == 1 && this.i != null && this.i.new()) {
            j.if(v, "send gps location to client ...");
            this.k.a(this.i.int(), r5_Message);
        } else if (this.x) {
            j();
        } else {
            if (!this.y) {
                if (this.j == null || (!this.j.new())) {
                    j();
                } else {
                    this.z = r2z;
                    this.b.postDelayed(new b(null), 2000);
                }
            }
        }
    }

    private void h() {
        if (this.t == null || (!g.a((Context)this))) {
        } else {
            g.f();
        }
    }

    private void i() {
        File r0_File = new File(a);
        File r1_File = new File(a + "/ls.db");
        if (!r0_File.exists()) {
            r0_File.mkdirs();
        }
        if (!r1_File.exists()) {
            try {
                r1_File.createNewFile();
            } catch (Exception e) {
            }
        }
        if (r1_File.exists()) {
            this.E = SQLiteDatabase.openOrCreateDatabase(r1_File, null);
            this.E.execSQL("CREATE TABLE IF NOT EXISTS " + this.F + "(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
        }
    }

    private void j() {
        if (this.y) {
        } else {
            if (System.currentTimeMillis() - this.A < 1000) {
                j.if(v, "request too frequency ...");
                if (this.t != null) {
                    this.k.a(this.t);
                    m();
                }
            }
            j.if(v, "start network locating ...");
            j.a(v, "start network locating ...");
            this.y = true;
            this.M = a(this.p);
            if (a(this.m) || this.M || this.t == null) {
                String r0_String = a(null);
                if (r0_String == null) {
                    this.k.a("{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"62\"}}");
                    m();
                } else {
                    if (this.s != null) {
                        r0_String = r0_String + this.s;
                        this.s = null;
                    }
                    if (g.a(r0_String, this.b)) {
                        this.p = this.o;
                        this.m = this.l;
                    } else {
                        j.if(v, "request error ..");
                    }
                    if (this.x) {
                        this.x = false;
                    }
                    this.A = System.currentTimeMillis();
                }
            } else {
                this.k.a(this.t);
                m();
            }
        }
    }

    private void k() {
        if (g.a((Context)this)) {
            g.f();
        }
    }

    private void l() {
        if (this.k != null) {
            this.k.new();
        }
    }

    public static void long() {
        try {
            if (e != null) {
                g = new File(e);
                if (!g.exists()) {
                    File r0_File = new File(a);
                    if (!r0_File.exists()) {
                        r0_File.mkdirs();
                    }
                    g.createNewFile();
                    RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(g, "rw");
                    r0_RandomAccessFile.seek(0);
                    r0_RandomAccessFile.writeInt(-1);
                    r0_RandomAccessFile.writeInt(-1);
                    r0_RandomAccessFile.writeInt(0);
                    r0_RandomAccessFile.writeLong(0);
                    r0_RandomAccessFile.writeInt(0);
                    r0_RandomAccessFile.writeInt(0);
                    r0_RandomAccessFile.close();
                }
            } else {
                g = null;
            }
        } catch (Exception e) {
            g = null;
        }
    }

    private void m() {
        this.y = false;
        h();
    }

    private void n() {
        try {
            if (j.n && j.G) {
                this.D = new c(this);
            }
        } catch (Exception e) {
        }
    }

    public static String new() {
        j.if(v, "read trace log1..");
        return null;
    }

    public boolean else() {
        return ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public IBinder onBind(Intent r2_Intent) {
        return this.c.getBinder();
    }

    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new a(this));
        this.h = new c(this, this.b);
        this.j = new e(this, this.b);
        this.k = new d(this.b);
        this.h.do();
        this.j.try();
        this.w = true;
        this.y = false;
        this.z = false;
        try {
            i();
        } catch (Exception e) {
        }
        j.if(v, "OnCreate");
        Log.d(v, "baidu location service start1 ..." + Process.myPid());
    }

    public void onDestroy() {
        if (this.h != null) {
            this.h.byte();
        }
        if (this.j != null) {
            this.j.else();
        }
        if (this.i != null) {
            this.i.l();
        }
        n.a();
        this.y = false;
        this.z = false;
        this.w = false;
        if (this.D != null) {
            this.D.try();
        }
        if (this.E != null) {
            this.E.close();
        }
        j.if(v, "onDestroy");
        Log.d(v, "baidu location service stop ...");
        Process.killProcess(Process.myPid());
    }

    public int onStartCommand(Intent r3_Intent, int r4i, int r5i) {
        j.if(v, "onStratCommandNotSticky");
        return XListViewHeader.STATE_REFRESHING;
    }
}