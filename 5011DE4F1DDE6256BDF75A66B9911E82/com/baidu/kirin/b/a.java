package com.baidu.kirin.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.baidu.kirin.KirinConfig;
import com.baidu.kirin.d.d;
import java.lang.ref.SoftReference;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements OnSharedPreferenceChangeListener {
    private static SoftReference<a> a;
    private final Context b;
    private final SharedPreferences c;

    static {
        a = null;
    }

    private a(Context r4_Context) {
        this.b = r4_Context.getApplicationContext();
        this.c = f().getSharedPreferences(f().getPackageName() + ".kirin_strategy_control_pref", 0);
    }

    public static synchronized a a_(Context r4_Context) {
        a r0_a;
        synchronized (a.class) {
            try {
                r0_a = a == null ? null : (a) a.get();
                if (r0_a == null) {
                    synchronized (a.class) {
                        r0_a = a == null ? null : (a) a.get();
                        if (r0_a == null) {
                            r0_a = new a(r4_Context);
                            a = new SoftReference(r0_a);
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return r0_a;
    }

    private boolean a_(String r3_String, String r4_String) {
        return c(r3_String) > c(r4_String);
    }

    private int c(String r5_String) {
        return (Integer.parseInt(r5_String.split(":")[0]) * 60) * 60 + Integer.parseInt(r5_String.split(":")[1]) * 60 + Integer.parseInt(r5_String.split(":")[2]);
    }

    private Context f() {
        return this.b;
    }

    private boolean g() {
        long r1j = new Date().getTime();
        int r3i = this.c.getInt("kirin_update_freqency", -1);
        long r4j = this.c.getLong("kirin_strategy_record_time", -1);
        if (r4j == -1) {
            return true;
        }
        if ((r1j - r4j) / 1000 >= ((long) r3i)) {
            d.a(r1j + " --> exceed interval : " + r3i);
            return true;
        } else {
            d.a(r1j + " --> don't exceed interval : " + r3i);
            return false;
        }
    }

    private boolean h() {
        long r1j = new Date().getTime();
        long r3j = this.c.getLong("kirin_strategy_record_time", -1);
        if (r3j == -1) {
            return true;
        }
        if ((r1j - r3j) / 1000 > ((long) KirinConfig.DEFAULT_UPDATE_INTERVAL)) {
            d.a(r1j + " --> exceed interval : " + KirinConfig.DEFAULT_UPDATE_INTERVAL);
            return true;
        } else {
            d.a(r1j + " --> don't exceed interval : " + KirinConfig.DEFAULT_UPDATE_INTERVAL);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
        r14_this = this;
        r4 = 0;
        r2 = 1;
        r3 = 0;
        r0 = r14.e();
        r5 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r1 = "\\|";
        r1 = r0.split(r1);
        r6 = r1[r3];
        r1 = "\\|";
        r0 = r0.split(r1);
        r7 = r0[r2];
        r0 = java.lang.System.out;
        r0.println(r6);
        r0 = java.lang.System.out;
        r0.println(r7);
        r0 = new java.util.Date;
        r0.<init>();
        r8 = r0.getTime();
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r0.<init>(r1);
        r1 = java.lang.Long.valueOf(r8);
        r0 = r0.format(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1 = r1.append(r0);
        r10 = " ";
        r1 = r1.append(r10);
        r1 = r1.append(r6);
        r1 = r1.toString();
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r10.append(r0);
        r10 = " ";
        r0 = r0.append(r10);
        r0 = r0.append(r7);
        r0 = r0.toString();
        r10 = java.lang.System.out;
        r10.println(r1);
        r10 = java.lang.System.out;
        r10.println(r0);
        r10 = new java.text.SimpleDateFormat;
        r11 = "yyyy-MM-dd HH:mm:ss";
        r10.<init>(r11);
        r1 = r10.parse(r1);	 //Catch:{ ParseException -> 0x00bf }
        r0 = r10.parse(r0);	 //Catch:{ ParseException -> 0x00d9 }
        r4 = java.lang.System.out;	 //Catch:{ ParseException -> 0x00de }
        r10 = new java.lang.StringBuilder;	 //Catch:{ ParseException -> 0x00de }
        r10.<init>();	 //Catch:{ ParseException -> 0x00de }
        r11 = r1.getTime();	 //Catch:{ ParseException -> 0x00de }
        r10 = r10.append(r11);	 //Catch:{ ParseException -> 0x00de }
        r11 = " - ";
        r10 = r10.append(r11);	 //Catch:{ ParseException -> 0x00de }
        r11 = r0.getTime();	 //Catch:{ ParseException -> 0x00de }
        r10 = r10.append(r11);	 //Catch:{ ParseException -> 0x00de }
        r10 = r10.toString();	 //Catch:{ ParseException -> 0x00de }
        r4.println(r10);	 //Catch:{ ParseException -> 0x00de }
    L_0x00a7:
        r10 = r1.getTime();
        r0 = r0.getTime();
        r4 = r14.a(r7, r6);
        if (r4 == 0) goto L_0x00ca;
    L_0x00b5:
        r4 = (r8 > r10 ? 1 : (r8 == r10? 0 : -1));
        if (r4 < 0) goto L_0x00c8;
    L_0x00b9:
        r0 = (r8 > r0 ? 1 : (r8 == r0? 0 : -1));
        if (r0 > 0) goto L_0x00c8;
    L_0x00bd:
        r0 = r2;
    L_0x00be:
        return r0;
    L_0x00bf:
        r0 = move-exception;
        r1 = r4;
        r13 = r4;
        r4 = r0;
        r0 = r13;
    L_0x00c4:
        r4.printStackTrace();
        goto L_0x00a7;
    L_0x00c8:
        r0 = r3;
        goto L_0x00be;
    L_0x00ca:
        r4 = (long) r5;
        r4 = r10 - r4;
        r4 = (r8 > r4 ? 1 : (r8 == r4? 0 : -1));
        if (r4 < 0) goto L_0x00d7;
    L_0x00d1:
        r0 = (r8 > r0 ? 1 : (r8 == r0? 0 : -1));
        if (r0 > 0) goto L_0x00d7;
    L_0x00d5:
        r0 = r2;
        goto L_0x00be;
    L_0x00d7:
        r2 = r3;
        goto L_0x00d5;
    L_0x00d9:
        r0 = move-exception;
        r13 = r0;
        r0 = r4;
        r4 = r13;
        goto L_0x00c4;
    L_0x00de:
        r4 = move-exception;
        goto L_0x00c4;
        */

    }

    public void a_(boolean r12z, JSONObject r13_JSONObject) {
        long r1j = new Date().getTime();
        Editor r3_Editor = this.c.edit();
        if (r12z) {
            try {
                int r0i = r13_JSONObject.getInt("updateSwitch");
                int r4i = r13_JSONObject.getInt("updateFrequency");
                int r5i = r13_JSONObject.getInt("popFrequency");
                String r6_String = r13_JSONObject.getString("openPeriod");
                d.a("write to strategy controller data is  :  success : " + r12z + "; updateSwith : " + r0i + "; updateFrequency : " + r4i + "; popFrequency : " + r5i);
                r3_Editor.putLong("kirin_strategy_record_time", r1j);
                r3_Editor.putInt("kirin_update_switcher", r0i);
                r3_Editor.putInt("kirin_update_freqency", r4i * 86400);
                r3_Editor.putInt("kirin_update_remind_freqency", r5i * 86400);
                r3_Editor.putString("kirin_open_peroid", r6_String);
            } catch (JSONException e) {
                e.printStackTrace();
                r3_Editor.putLong("kirin_strategy_record_time", r1j);
                r3_Editor.putInt("kirin_update_switcher", 0);
                r3_Editor.putInt("kirin_update_freqency", KirinConfig.DEFAULT_UPDATE_INTERVAL);
                r3_Editor.putInt("kirin_update_remind_freqency", KirinConfig.DEFAULT_POP_INTERVAL);
                r3_Editor.putString("kirin_open_peroid", KirinConfig.DEFAULT_OPEN_PEROID);
            }
        } else {
            r3_Editor.putLong("kirin_strategy_record_time", r1j);
            r3_Editor.putInt("kirin_update_switcher", 0);
            r3_Editor.putInt("kirin_update_freqency", KirinConfig.DEFAULT_UPDATE_INTERVAL);
            r3_Editor.putInt("kirin_update_remind_freqency", KirinConfig.DEFAULT_POP_INTERVAL);
            r3_Editor.putString("kirin_open_peroid", KirinConfig.DEFAULT_OPEN_PEROID);
        }
        r3_Editor.commit();
    }

    public boolean a_() {
        if (!i()) {
            return false;
        }
        int r2i = this.c.getInt("kirin_update_switcher", -1);
        if (g() || r2i != 1) {
            if (r2i == 0) {
                d.a("else if(switcher == 0)");
                return h();
            } else {
                d.a("else!");
                return true;
            }
        } else {
            d.a("!isExceedServerUpdateInterval() && switcher == 1");
            return false;
        }
    }

    public boolean a_(String r3_String) {
        if (!i()) {
            return false;
        }
        if (r3_String.equals(KirinConfig.ATSTART)) {
            return a();
        }
        if (r3_String.equals(KirinConfig.ATSETTING)) {
            return b();
        }
        return false;
    }

    public void b(String r3_String) {
        Editor r0_Editor = this.c.edit();
        r0_Editor.putString("kirin_log_id", r3_String);
        r0_Editor.commit();
    }

    public boolean b() {
        return i();
    }

    public boolean c() {
        if (!i()) {
            return false;
        }
        int r2i = this.c.getInt("kirin_update_switcher", -1);
        return r2i == -1 || r2i == 1;
    }

    public String d() {
        return this.c.getString("kirin_log_id", "0");
    }

    public String e() {
        return this.c.getString("kirin_open_peroid", KirinConfig.DEFAULT_OPEN_PEROID);
    }

    public void onSharedPreferenceChanged(SharedPreferences r3_SharedPreferences, String r4_String) {
        if (r3_SharedPreferences == this.c) {
            d.a(r4_String + " : has changed");
        }
    }
}