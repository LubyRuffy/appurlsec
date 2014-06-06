package com.google.analytics.tracking.android;

import android.content.Context;
import android.text.TextUtils;
import com.google.analytics.tracking.android.Analytics.AppOptOutCallback;
import com.google.analytics.tracking.android.AnalyticsThread.ClientIdCallback;
import com.google.android.gms.analytics.internal.Command;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import qsbk.app.utils.image.Utils;

// compiled from: GAThread.java
class o extends Thread implements AnalyticsThread {
    private static o i;
    private final LinkedBlockingQueue<Runnable> a;
    private volatile boolean b;
    private volatile boolean c;
    private volatile boolean d;
    private volatile List<Command> e;
    private volatile MetaModel f;
    private volatile String g;
    private volatile String h;
    private final ai j;
    private final Context k;

    private o(Context r3_Context) {
        super("GAThread");
        this.a = new LinkedBlockingQueue();
        this.b = false;
        this.c = false;
        this.k = r3_Context;
        this.j = new l(r3_Context, this);
        c();
    }

    static o a(Context r1_Context) {
        if (i == null) {
            i = new o(r1_Context);
        }
        return i;
    }

    private String a(Map<String, String> r2_Map_String__String) {
        String r0_String = (String) r2_Map_String__String.get("internalHitUrl");
        if (r0_String != null) {
            return r0_String;
        }
        if (am.safeParseBoolean((String) r2_Map_String__String.get(ModelFields.USE_SECURE))) {
            return "https://ssl.google-analytics.com/collect";
        }
        return "http://www.google-analytics.com/collect";
    }

    private void a(Runnable r2_Runnable) {
        this.a.add(r2_Runnable);
    }

    private void a(Map<String, String> r2_Map_String__String, String r3_String, String r4_String) {
        if (!r2_Map_String__String.containsKey(r3_String)) {
            r2_Map_String__String.put(r3_String, r4_String);
        }
    }

    private boolean a(String r5_String) {
        try {
            FileOutputStream r1_FileOutputStream = this.k.openFileOutput("gaClientId", 0);
            r1_FileOutputStream.write(r5_String.getBytes());
            r1_FileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            z.c("Error creating clientId file.");
            return false;
        } catch (IOException e_2) {
            z.c("Error writing to clientId file.");
            return false;
        }
    }

    static String b(Context r5_Context) {
        String r0_String = null;
        try {
            FileInputStream r1_FileInputStream = r5_Context.openFileInput("gaInstallData");
            byte[] r2_byteA = new byte[8192];
            int r3i = r1_FileInputStream.read(r2_byteA, 0, Utils.IO_BUFFER_SIZE);
            if (r1_FileInputStream.available() > 0) {
                z.c("Too much campaign data, ignoring it.");
                r1_FileInputStream.close();
                r5_Context.deleteFile("gaInstallData");
                return r0_String;
            } else {
                r1_FileInputStream.close();
                r5_Context.deleteFile("gaInstallData");
                if (r3i <= 0) {
                    z.h("Campaign file is empty.");
                    return r0_String;
                } else {
                    r0_String = new String(r2_byteA, 0, r3i);
                    return r0_String;
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e_2) {
            z.c("Error reading campaign data.");
            r5_Context.deleteFile("gaInstallData");
        }
    }

    private void b(Map<String, String> r5_Map_String__String) {
        String r0_String = (String) r5_Map_String__String.get(ModelFields.RAW_EXCEPTION);
        if (r0_String == null) {
        } else {
            r5_Map_String__String.remove(ModelFields.RAW_EXCEPTION);
            try {
                ObjectInputStream r2_ObjectInputStream = new ObjectInputStream(new ByteArrayInputStream(am.a(r0_String)));
                Object r0_Object = r2_ObjectInputStream.readObject();
                r2_ObjectInputStream.close();
                if (r0_Object instanceof Throwable) {
                    r5_Map_String__String.put(ModelFields.EX_DESCRIPTION, new ak(this.k, new ArrayList()).getDescription((String) r5_Map_String__String.get(ModelFields.EXCEPTION_THREAD_NAME), (Throwable) r0_Object));
                }
            } catch (IOException e) {
                z.h("IOException reading exception");
            } catch (ClassNotFoundException e_2) {
                z.h("ClassNotFoundException reading exception");
            }
        }
    }

    private void c() {
        this.j.createService();
        this.e = new ArrayList();
        this.e.add(new Command(Command.APPEND_VERSION, "_v", "ma1b2"));
        this.e.add(new Command(Command.APPEND_QUEUE_TIME, ModelFields.QUEUE_TIME, null));
        this.e.add(new Command(Command.APPEND_CACHE_BUSTER, ModelFields.CACHE_BUSTER, null));
        this.f = new MetaModel();
        aa.set(this.f);
        start();
    }

    private boolean c(Map<String, String> r9_Map_String__String) {
        if (r9_Map_String__String.get(ModelFields.SAMPLE_RATE) != null) {
            double r2d = am.safeParseDouble((String) r9_Map_String__String.get(ModelFields.SAMPLE_RATE));
            if (r2d <= 0.0d) {
                return true;
            }
            if (r2d < 100.0d) {
                String r0_String = (String) r9_Map_String__String.get(ModelFields.CLIENT_ID);
                if (r0_String != null && ((double) (Math.abs(r0_String.hashCode()) % 10000)) >= r2d * 100.0d) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(Map<String, String> r7_Map_String__String) {
        /*
        r6_this = this;
        r0 = r6.k;
        r1 = r0.getPackageManager();
        r0 = r6.k;
        r2 = r0.getPackageName();
        r3 = r1.getInstallerPackageName(r2);
        r0 = 0;
        r4 = r6.k;	 //Catch:{ NameNotFoundException -> 0x0046 }
        r4 = r4.getPackageName();	 //Catch:{ NameNotFoundException -> 0x0046 }
        r5 = 0;
        r4 = r1.getPackageInfo(r4, r5);	 //Catch:{ NameNotFoundException -> 0x0046 }
        if (r4 == 0) goto L_0x0061;
    L_0x001e:
        r5 = r4.applicationInfo;	 //Catch:{ NameNotFoundException -> 0x0046 }
        r1 = r1.getApplicationLabel(r5);	 //Catch:{ NameNotFoundException -> 0x0046 }
        r1 = r1.toString();	 //Catch:{ NameNotFoundException -> 0x0046 }
        r0 = r4.versionName;	 //Catch:{ NameNotFoundException -> 0x005f }
    L_0x002a:
        r4 = "appName";
        r6.a(r7, r4, r1);
        r1 = "appVersion";
        r6.a(r7, r1, r0);
        r0 = "appId";
        r6.a(r7, r0, r2);
        r0 = "appInstallerId";
        r6.a(r7, r0, r3);
        r0 = "apiVersion";
        r1 = "1";
        r7.put(r0, r1);
        return;
    L_0x0046:
        r1 = move-exception;
        r1 = r2;
    L_0x0048:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Error retrieving package info: appName set to ";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r4 = r4.toString();
        com.google.analytics.tracking.android.z.c(r4);
        goto L_0x002a;
    L_0x005f:
        r4 = move-exception;
        goto L_0x0048;
    L_0x0061:
        r1 = r2;
        goto L_0x002a;
        */

    }

    private boolean d() {
        return this.k.getFileStreamPath("gaOptOut").exists();
    }

    private void e(Map<String, String> r4_Map_String__String) {
        String r0_String = am.filterCampaign((String) r4_Map_String__String.get(ModelFields.CAMPAIGN));
        if (TextUtils.isEmpty(r0_String)) {
        } else {
            Map r0_Map = am.parseURLParameters(r0_String);
            r4_Map_String__String.put(ModelFields.CAMPAIGN_CONTENT, r0_Map.get("utm_content"));
            r4_Map_String__String.put(ModelFields.CAMPAIGN_MEDIUM, r0_Map.get("utm_medium"));
            r4_Map_String__String.put(ModelFields.CAMPAIGN_NAME, r0_Map.get("utm_campaign"));
            r4_Map_String__String.put(ModelFields.CAMPAIGN_SOURCE, r0_Map.get("utm_source"));
            r4_Map_String__String.put(ModelFields.CAMPAIGN_KEYWORD, r0_Map.get("utm_term"));
            r4_Map_String__String.put(ModelFields.CAMPAIGN_ID, r0_Map.get("utm_id"));
            r4_Map_String__String.put(ModelFields.GCLID, r0_Map.get(ModelFields.GCLID));
            r4_Map_String__String.put(ModelFields.DCLID, r0_Map.get(ModelFields.DCLID));
            r4_Map_String__String.put(ModelFields.GMOB_T, r0_Map.get(ModelFields.GMOB_T));
        }
    }

    String a() {
        String r0_String = Long.toHexString((new SecureRandom().nextLong() & 9223372036854775807L) % 9223372036854775807L + 1);
        return a(r0_String) ? r0_String : r0_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    String b() {
        /*
        r6_this = this;
        r0 = 0;
        r1 = r6.k;	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r2 = "gaClientId";
        r2 = r1.openFileInput(r2);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r3 = new byte[r1];	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r1 = 0;
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r4 = r2.read(r3, r1, r4);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r1 = r2.available();	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        if (r1 <= 0) goto L_0x0029;
    L_0x001a:
        r1 = "clientId file seems corrupted, deleting it.";
        com.google.analytics.tracking.android.z.c(r1);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r2.close();	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r1 = r6.k;	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r5 = "gaInstallData";
        r1.deleteFile(r5);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
    L_0x0029:
        if (r4 > 0) goto L_0x0041;
    L_0x002b:
        r1 = "clientId file seems empty, deleting it.";
        com.google.analytics.tracking.android.z.c(r1);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r2.close();	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r1 = r6.k;	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r2 = "gaInstallData";
        r1.deleteFile(r2);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
    L_0x003a:
        if (r0 != 0) goto L_0x0040;
    L_0x003c:
        r0 = r6.a();
    L_0x0040:
        return r0;
    L_0x0041:
        r1 = new java.lang.String;	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r5 = 0;
        r1.<init>(r3, r5, r4);	 //Catch:{ FileNotFoundException -> 0x006e, IOException -> 0x004c, NumberFormatException -> 0x005a }
        r2.close();	 //Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x006b, NumberFormatException -> 0x0068 }
        r0 = r1;
        goto L_0x003a;
    L_0x004c:
        r1 = move-exception;
    L_0x004d:
        r1 = "Error reading clientId file, deleting it.";
        com.google.analytics.tracking.android.z.c(r1);
        r1 = r6.k;
        r2 = "gaInstallData";
        r1.deleteFile(r2);
        goto L_0x003a;
    L_0x005a:
        r1 = move-exception;
    L_0x005b:
        r1 = "cliendId file doesn't have long value, deleting it.";
        com.google.analytics.tracking.android.z.c(r1);
        r1 = r6.k;
        r2 = "gaInstallData";
        r1.deleteFile(r2);
        goto L_0x003a;
    L_0x0068:
        r0 = move-exception;
        r0 = r1;
        goto L_0x005b;
    L_0x006b:
        r0 = move-exception;
        r0 = r1;
        goto L_0x004d;
    L_0x006e:
        r1 = move-exception;
        goto L_0x003a;
    L_0x0070:
        r0 = move-exception;
        r0 = r1;
        goto L_0x003a;
        */

    }

    public void dispatch() {
        a(new q(this));
    }

    public LinkedBlockingQueue<Runnable> getQueue() {
        return this.a;
    }

    public Thread getThread() {
        return this;
    }

    public void requestAppOptOut(AppOptOutCallback r2_AppOptOutCallback) {
        a(new s(this, r2_AppOptOutCallback));
    }

    public void requestClientId(ClientIdCallback r2_ClientIdCallback) {
        a(new t(this, r2_ClientIdCallback));
    }

    public void run() {
        this.d = d();
        this.h = b();
        this.g = b(this.k);
        while (!(this.c)) {
            try {
                Runnable r0_Runnable = (Runnable) this.a.take();
                if (!this.b) {
                    r0_Runnable.run();
                }
            } catch (InterruptedException e) {
                z.d(e.toString());
            }
        }
    }

    public void sendHit(Map<String, String> r5_Map_String__String) {
        a(new p(this, new HashMap(r5_Map_String__String), System.currentTimeMillis()));
    }

    public void setAppOptOut(boolean r2z) {
        a(new r(this, r2z));
    }
}