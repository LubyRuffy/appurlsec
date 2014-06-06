package com.tencent.qc.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.HandlerThread;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.StatLogger;
import com.tencent.qc.stat.common.User;
import com.tencent.qc.stat.event.Event;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import qsbk.app.push.Utils;

// compiled from: ProGuard
public class StatStore {
    private static StatLogger e;
    private static StatStore f;
    Handler a;
    volatile int b;
    User c;
    private i d;
    private HashMap g;

    static {
        e = StatCommonHelper.b();
        f = null;
    }

    private StatStore(Context r5_Context) {
        this.a = null;
        this.b = 0;
        this.c = null;
        this.g = new HashMap();
        HandlerThread r0_HandlerThread = new HandlerThread("StatStore");
        r0_HandlerThread.start();
        e.d("Launch store thread:" + r0_HandlerThread);
        this.a = new Handler(r0_HandlerThread.getLooper());
        Context r0_Context = r5_Context.getApplicationContext();
        this.d = new i(r0_Context);
        this.d.getWritableDatabase();
        this.d.getReadableDatabase();
        b(r0_Context);
        e();
        this.a.post(new g(this));
    }

    public static StatStore a(Context r1_Context) {
        if (f == null) {
            f = new StatStore(r1_Context);
        }
        return f;
    }

    private void b(List r11_List) {
        e.b("Delete " + r11_List.size() + " sent events in thread:" + Thread.currentThread());
        try {
            this.d.getWritableDatabase().beginTransaction();
            Iterator r1_Iterator = r11_List.iterator();
            while (r1_Iterator.hasNext()) {
                int r2i = this.b;
                SQLiteDatabase r3_SQLiteDatabase = this.d.getWritableDatabase();
                String[] r6_StringA = new String[1];
                r6_StringA[0] = Long.toString(((s) r1_Iterator.next()).a);
                this.b = r2i - r3_SQLiteDatabase.delete("events", "event_id = ?", r6_StringA);
            }
            this.d.getWritableDatabase().setTransactionSuccessful();
            this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
            this.d.getWritableDatabase().endTransaction();
        } catch (SQLiteException e) {
            e.b(e);
            this.d.getWritableDatabase().endTransaction();
        }
    }

    private void b(List r12_List, int r13i) {
        e.b("Update " + r12_List.size() + " sending events to status:" + r13i + " in thread:" + Thread.currentThread());
        try {
            ContentValues r1_ContentValues = new ContentValues();
            r1_ContentValues.put(RMsgInfo.COL_STATUS, Integer.toString(r13i));
            this.d.getWritableDatabase().beginTransaction();
            Iterator r2_Iterator = r12_List.iterator();
            while (r2_Iterator.hasNext()) {
                s r0_s = (s) r2_Iterator.next();
                if (r0_s.d + 1 > StatConfig.f()) {
                    int r3i = this.b;
                    SQLiteDatabase r4_SQLiteDatabase = this.d.getWritableDatabase();
                    String[] r7_StringA = new String[1];
                    r7_StringA[0] = Long.toString(r0_s.a);
                    this.b = r3i - r4_SQLiteDatabase.delete("events", "event_id=?", r7_StringA);
                } else {
                    r1_ContentValues.put("send_count", Integer.valueOf(r0_s.d + 1));
                    e.b("Update event:" + r0_s.a + " for content:" + r1_ContentValues);
                    SQLiteDatabase r3_SQLiteDatabase = this.d.getWritableDatabase();
                    String[] r6_StringA = new String[1];
                    r6_StringA[0] = Long.toString(r0_s.a);
                    int r0i = r3_SQLiteDatabase.update("events", r1_ContentValues, "event_id=?", r6_StringA);
                    if (r0i <= 0) {
                        e.f("Failed to update db, error code:" + Integer.toString(r0i));
                    }
                }
            }
            this.d.getWritableDatabase().setTransactionSuccessful();
            this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
            this.d.getWritableDatabase().endTransaction();
        } catch (SQLiteException e) {
            e.b(e);
            this.d.getWritableDatabase().endTransaction();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(List r11_List, int r12i) {
        /*
        r10_this = this;
        r9 = 0;
        r0 = r10.d;	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r0 = r0.getReadableDatabase();	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r1 = "events";
        r2 = 0;
        r3 = "status=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.toString(r6);	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r4[r5] = r6;	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r5 = 0;
        r6 = 0;
        r7 = "event_id";
        r8 = java.lang.Integer.toString(r12);	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
        r6 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 //Catch:{ SQLiteException -> 0x0066, all -> 0x0059 }
    L_0x0023:
        r0 = r6.moveToNext();	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        if (r0 == 0) goto L_0x0053;
    L_0x0029:
        r0 = 0;
        r1 = r6.getLong(r0);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r0 = 1;
        r3 = r6.getString(r0);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r0 = 2;
        r4 = r6.getInt(r0);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r0 = 3;
        r5 = r6.getInt(r0);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r0 = new com.tencent.qc.stat.s;	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r0.<init>(r1, r3, r4, r5);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        r11.add(r0);	 //Catch:{ SQLiteException -> 0x0046, all -> 0x0060 }
        goto L_0x0023;
    L_0x0046:
        r0 = move-exception;
        r1 = r6;
    L_0x0048:
        r2 = e;	 //Catch:{ all -> 0x0063 }
        r2.b(r0);	 //Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();
    L_0x0052:
        return;
    L_0x0053:
        if (r6 == 0) goto L_0x0052;
    L_0x0055:
        r6.close();
        goto L_0x0052;
    L_0x0059:
        r0 = move-exception;
    L_0x005a:
        if (r9 == 0) goto L_0x005f;
    L_0x005c:
        r9.close();
    L_0x005f:
        throw r0;
    L_0x0060:
        r0 = move-exception;
        r9 = r6;
        goto L_0x005a;
    L_0x0063:
        r0 = move-exception;
        r9 = r1;
        goto L_0x005a;
    L_0x0066:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0048;
        */

    }

    private void d() {
        ContentValues r0_ContentValues = new ContentValues();
        r0_ContentValues.put(RMsgInfo.COL_STATUS, Integer.valueOf(1));
        SQLiteDatabase r1_SQLiteDatabase = this.d.getWritableDatabase();
        String[] r4_StringA = new String[1];
        r4_StringA[0] = Long.toString(2);
        r1_SQLiteDatabase.update("events", r0_ContentValues, "status=?", r4_StringA);
        this.b = (int) DatabaseUtils.queryNumEntries(this.d.getReadableDatabase(), "events");
        e.b("Total " + this.b + " unsent events.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
        r9_this = this;
        r8 = 0;
        r0 = r9.d;	 //Catch:{ SQLiteException -> 0x0045, all -> 0x003b }
        r0 = r0.getReadableDatabase();	 //Catch:{ SQLiteException -> 0x0045, all -> 0x003b }
        r1 = "keyvalues";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 //Catch:{ SQLiteException -> 0x0045, all -> 0x003b }
    L_0x0013:
        r0 = r1.moveToNext();	 //Catch:{ SQLiteException -> 0x0029 }
        if (r0 == 0) goto L_0x0035;
    L_0x0019:
        r0 = r9.g;	 //Catch:{ SQLiteException -> 0x0029 }
        r2 = 0;
        r2 = r1.getString(r2);	 //Catch:{ SQLiteException -> 0x0029 }
        r3 = 1;
        r3 = r1.getString(r3);	 //Catch:{ SQLiteException -> 0x0029 }
        r0.put(r2, r3);	 //Catch:{ SQLiteException -> 0x0029 }
        goto L_0x0013;
    L_0x0029:
        r0 = move-exception;
    L_0x002a:
        r2 = e;	 //Catch:{ all -> 0x0043 }
        r2.b(r0);	 //Catch:{ all -> 0x0043 }
        if (r1 == 0) goto L_0x0034;
    L_0x0031:
        r1.close();
    L_0x0034:
        return;
    L_0x0035:
        if (r1 == 0) goto L_0x0034;
    L_0x0037:
        r1.close();
        goto L_0x0034;
    L_0x003b:
        r0 = move-exception;
        r1 = r8;
    L_0x003d:
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        r1.close();
    L_0x0042:
        throw r0;
    L_0x0043:
        r0 = move-exception;
        goto L_0x003d;
    L_0x0045:
        r0 = move-exception;
        r1 = r8;
        goto L_0x002a;
        */

    }

    public int a() {
        return this.b;
    }

    void a(int r3i) {
        this.a.post(new b(this, r3i));
    }

    void a(Event r7_Event, q r8_q) {
        if (StatConfig.h() <= 0) {
        } else {
            if (this.b > StatConfig.h()) {
                e.c("Too many events stored in db.");
                this.b -= this.d.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
            }
            ContentValues r0_ContentValues = new ContentValues();
            String r1_String = r7_Event.d();
            r0_ContentValues.put(Utils.RESPONSE_CONTENT, r1_String);
            r0_ContentValues.put("send_count", "0");
            r0_ContentValues.put(RMsgInfo.COL_STATUS, Integer.toString(1));
            r0_ContentValues.put(AdViewUtil.PREFS_STRING_TIMESTAMP, Long.valueOf(r7_Event.b()));
            if (this.d.getWritableDatabase().insert("events", null, r0_ContentValues) == -1) {
                e.e("Failed to store event:" + r1_String);
            } else {
                this.b++;
                if (r8_q != null) {
                    r8_q.a();
                }
            }
        }
    }

    void a(r r3_r) {
        if (r3_r == null) {
        } else {
            this.a.post(new c(this, r3_r));
        }
    }

    void a(List r5_List) {
        if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
            b(r5_List);
        } else {
            this.a.post(new e(this, r5_List));
        }
    }

    void a(List r5_List, int r6i) {
        if (Thread.currentThread().getId() == this.a.getLooper().getThread().getId()) {
            b(r5_List, r6i);
        } else {
            this.a.post(new h(this, r5_List, r6i));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public User b(Context r13_Context) {
        /*
        r12_this = this;
        r0 = r12.c;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r0 = r12.c;
    L_0x0006:
        return r0;
    L_0x0007:
        r9 = 0;
        r0 = r12.d;	 //Catch:{ SQLiteException -> 0x016b, all -> 0x0178 }
        r0 = r0.getReadableDatabase();	 //Catch:{ SQLiteException -> 0x016b, all -> 0x0178 }
        r1 = "user";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 //Catch:{ SQLiteException -> 0x016b, all -> 0x0178 }
        r0 = 0;
        r2 = r1.moveToNext();	 //Catch:{ SQLiteException -> 0x0182 }
        if (r2 == 0) goto L_0x00cb;
    L_0x0022:
        r0 = 0;
        r4 = r1.getString(r0);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = 1;
        r7 = r1.getInt(r0);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = 2;
        r3 = r1.getString(r0);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = 3;
        r5 = r1.getLong(r0);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = 1;
        r8 = java.lang.System.currentTimeMillis();	 //Catch:{ SQLiteException -> 0x0182 }
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r8 = r8 / r10;
        r2 = 1;
        if (r7 == r2) goto L_0x018a;
    L_0x0041:
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r10;
        r2 = com.tencent.qc.stat.common.StatCommonHelper.a(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r8;
        r5 = com.tencent.qc.stat.common.StatCommonHelper.a(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        r2 = r2.equals(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        if (r2 != 0) goto L_0x018a;
    L_0x0055:
        r2 = 1;
    L_0x0056:
        r5 = com.tencent.qc.stat.common.StatCommonHelper.p(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = r3.equals(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        if (r3 != 0) goto L_0x0187;
    L_0x0060:
        r2 = r2 | 2;
        r6 = r2;
    L_0x0063:
        r2 = ",";
        r10 = r4.split(r2);	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = 0;
        if (r10 == 0) goto L_0x013d;
    L_0x006c:
        r2 = r10.length;	 //Catch:{ SQLiteException -> 0x0182 }
        if (r2 <= 0) goto L_0x013d;
    L_0x006f:
        r2 = 0;
        r2 = r10[r2];	 //Catch:{ SQLiteException -> 0x0182 }
        r5 = r2;
    L_0x0073:
        if (r10 == 0) goto L_0x0145;
    L_0x0075:
        r2 = r10.length;	 //Catch:{ SQLiteException -> 0x0182 }
        r11 = 2;
        if (r2 < r11) goto L_0x0145;
    L_0x0079:
        r2 = 1;
        r2 = r10[r2];	 //Catch:{ SQLiteException -> 0x0182 }
    L_0x007c:
        r10 = new com.tencent.qc.stat.common.User;	 //Catch:{ SQLiteException -> 0x0182 }
        r10.<init>(r5, r2, r6);	 //Catch:{ SQLiteException -> 0x0182 }
        r12.c = r10;	 //Catch:{ SQLiteException -> 0x0182 }
        r2 = new android.content.ContentValues;	 //Catch:{ SQLiteException -> 0x0182 }
        r2.<init>();	 //Catch:{ SQLiteException -> 0x0182 }
        r10 = "uid";
        r2.put(r10, r4);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = "user_type";
        r10 = java.lang.Integer.valueOf(r6);	 //Catch:{ SQLiteException -> 0x0182 }
        r2.put(r4, r10);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = "app_ver";
        r10 = com.tencent.qc.stat.common.StatCommonHelper.p(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        r2.put(r4, r10);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = "ts";
        r8 = java.lang.Long.valueOf(r8);	 //Catch:{ SQLiteException -> 0x0182 }
        r2.put(r4, r8);	 //Catch:{ SQLiteException -> 0x0182 }
        if (r3 == 0) goto L_0x00bd;
    L_0x00aa:
        r3 = r12.d;	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = r3.getWritableDatabase();	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = "user";
        r8 = "uid=?";
        r9 = 1;
        r9 = new java.lang.String[r9];	 //Catch:{ SQLiteException -> 0x0182 }
        r10 = 0;
        r9[r10] = r5;	 //Catch:{ SQLiteException -> 0x0182 }
        r3.update(r4, r2, r8, r9);	 //Catch:{ SQLiteException -> 0x0182 }
    L_0x00bd:
        if (r6 == r7) goto L_0x00cb;
    L_0x00bf:
        r3 = r12.d;	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = r3.getWritableDatabase();	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = "user";
        r5 = 0;
        r3.replace(r4, r5, r2);	 //Catch:{ SQLiteException -> 0x0182 }
    L_0x00cb:
        if (r0 != 0) goto L_0x0134;
    L_0x00cd:
        r2 = com.tencent.qc.stat.common.StatCommonHelper.b(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = com.tencent.qc.stat.common.StatCommonHelper.c(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        if (r3 == 0) goto L_0x0184;
    L_0x00d7:
        r0 = r3.length();	 //Catch:{ SQLiteException -> 0x0182 }
        if (r0 <= 0) goto L_0x0184;
    L_0x00dd:
        r0 = new java.lang.StringBuilder;	 //Catch:{ SQLiteException -> 0x0182 }
        r0.<init>();	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = r0.append(r2);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = ",";
        r0 = r0.append(r4);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = r0.append(r3);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = r0.toString();	 //Catch:{ SQLiteException -> 0x0182 }
    L_0x00f4:
        r4 = 0;
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ SQLiteException -> 0x0182 }
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 / r7;
        r7 = com.tencent.qc.stat.common.StatCommonHelper.p(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        r8 = new android.content.ContentValues;	 //Catch:{ SQLiteException -> 0x0182 }
        r8.<init>();	 //Catch:{ SQLiteException -> 0x0182 }
        r9 = "uid";
        r8.put(r9, r0);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = "user_type";
        r9 = java.lang.Integer.valueOf(r4);	 //Catch:{ SQLiteException -> 0x0182 }
        r8.put(r0, r9);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = "app_ver";
        r8.put(r0, r7);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = "ts";
        r5 = java.lang.Long.valueOf(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        r8.put(r0, r5);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = r12.d;	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = r0.getWritableDatabase();	 //Catch:{ SQLiteException -> 0x0182 }
        r5 = "user";
        r6 = 0;
        r0.insert(r5, r6, r8);	 //Catch:{ SQLiteException -> 0x0182 }
        r0 = new com.tencent.qc.stat.common.User;	 //Catch:{ SQLiteException -> 0x0182 }
        r0.<init>(r2, r3, r4);	 //Catch:{ SQLiteException -> 0x0182 }
        r12.c = r0;	 //Catch:{ SQLiteException -> 0x0182 }
    L_0x0134:
        if (r1 == 0) goto L_0x0139;
    L_0x0136:
        r1.close();
    L_0x0139:
        r0 = r12.c;
        goto L_0x0006;
    L_0x013d:
        r2 = com.tencent.qc.stat.common.StatCommonHelper.b(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        r5 = r2;
        r4 = r2;
        goto L_0x0073;
    L_0x0145:
        r2 = com.tencent.qc.stat.common.StatCommonHelper.c(r13);	 //Catch:{ SQLiteException -> 0x0182 }
        if (r2 == 0) goto L_0x007c;
    L_0x014b:
        r10 = r2.length();	 //Catch:{ SQLiteException -> 0x0182 }
        if (r10 <= 0) goto L_0x007c;
    L_0x0151:
        r3 = new java.lang.StringBuilder;	 //Catch:{ SQLiteException -> 0x0182 }
        r3.<init>();	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = r3.append(r5);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = ",";
        r3 = r3.append(r4);	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = r3.append(r2);	 //Catch:{ SQLiteException -> 0x0182 }
        r4 = r3.toString();	 //Catch:{ SQLiteException -> 0x0182 }
        r3 = 1;
        goto L_0x007c;
    L_0x016b:
        r0 = move-exception;
        r1 = r9;
    L_0x016d:
        r2 = e;	 //Catch:{ all -> 0x0180 }
        r2.b(r0);	 //Catch:{ all -> 0x0180 }
        if (r1 == 0) goto L_0x0139;
    L_0x0174:
        r1.close();
        goto L_0x0139;
    L_0x0178:
        r0 = move-exception;
        r1 = r9;
    L_0x017a:
        if (r1 == 0) goto L_0x017f;
    L_0x017c:
        r1.close();
    L_0x017f:
        throw r0;
    L_0x0180:
        r0 = move-exception;
        goto L_0x017a;
    L_0x0182:
        r0 = move-exception;
        goto L_0x016d;
    L_0x0184:
        r0 = r2;
        goto L_0x00f4;
    L_0x0187:
        r6 = r2;
        goto L_0x0063;
    L_0x018a:
        r2 = r7;
        goto L_0x0056;
        */

    }

    void b() {
        this.a.post(new d(this));
    }

    void b(Event r3_Event, q r4_q) {
        this.a.post(new f(this, r3_Event, r4_q));
    }
}