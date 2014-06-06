package com.tencent.cloudsdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ce extends SQLiteOpenHelper {
    private static final String a;
    private static final byte[] b;
    private static final byte[] c;

    static {
        a = ce.class.getName();
        b = new byte[1];
        c = new byte[1];
    }

    protected ce(Context r4_Context) {
        super(r4_Context, "mac_ip_info", null, 5);
    }

    private synchronized void a(SQLiteDatabase r4_SQLiteDatabase) {
        if (r4_SQLiteDatabase == null) {
        } else {
            try {
                r4_SQLiteDatabase.delete("connect_fail_table", null, null);
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                WnsClientLog.e(a, r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }

    private void a(SQLiteDatabase r5_SQLiteDatabase, cj r6_cj, int r7i) {
        ContentValues r0_ContentValues = new ContentValues();
        r0_ContentValues.put("connect_fail_column_count", Integer.valueOf(r7i));
        r0_ContentValues.put("connect_fail_column_errcode", Integer.valueOf(r6_cj.c));
        r0_ContentValues.put("connect_fail_column_ip", r6_cj.a);
        r0_ContentValues.put("connect_fail_column_port", Integer.valueOf(r6_cj.b));
        r0_ContentValues.put("connect_fail_column_iptype", Integer.valueOf(r6_cj.e));
        r0_ContentValues.put("connect_fail_column_network", Integer.valueOf(r6_cj.f));
        r0_ContentValues.put("connect_fail_column_sp", Integer.valueOf(r6_cj.g));
        r0_ContentValues.put("connect_fail_column_proto", Integer.valueOf(r6_cj.h));
        r0_ContentValues.put("connect_fail_column_time", Long.valueOf(r6_cj.i));
        r5_SQLiteDatabase.insert("connect_fail_table", null, r0_ContentValues);
    }

    private void a(SQLiteDatabase r6_SQLiteDatabase, cj r7_cj, cj r8_cj, int r9i) {
        ContentValues r0_ContentValues = new ContentValues();
        r0_ContentValues.put("connect_fail_column_count", Integer.valueOf(r7_cj.d + r9i));
        r0_ContentValues.put("connect_fail_column_time", Long.valueOf(r8_cj.i));
        StringBuilder r1_StringBuilder = new StringBuilder();
        r1_StringBuilder.append("connect_fail_column_ip");
        r1_StringBuilder.append(" = ? AND ");
        r1_StringBuilder.append("connect_fail_column_port");
        r1_StringBuilder.append(" = ? AND ");
        r1_StringBuilder.append("connect_fail_column_errcode");
        r1_StringBuilder.append(" = ? ");
        String[] r2_StringA = new String[3];
        r2_StringA[0] = r7_cj.a;
        r2_StringA[1] = String.valueOf(r7_cj.b);
        r2_StringA[2] = String.valueOf(r7_cj.c);
        r6_SQLiteDatabase.update("connect_fail_table", r0_ContentValues, r1_StringBuilder.toString(), r2_StringA);
    }

    private List c(String r9_String, String r10_String, boolean r11z) {
        int r0i = 1;
        List r2_List = new ArrayList();
        SQLiteDatabase r3_SQLiteDatabase = getReadableDatabase();
        StringBuilder r4_StringBuilder = new StringBuilder();
        r4_StringBuilder.append("SELECT * FROM ");
        r4_StringBuilder.append("domain");
        r4_StringBuilder.append(" WHERE ");
        r4_StringBuilder.append("domain");
        r4_StringBuilder.append(" =? ");
        r4_StringBuilder.append(" AND ");
        r4_StringBuilder.append("sp");
        r4_StringBuilder.append(" =? ");
        r4_StringBuilder.append(" AND ");
        r4_StringBuilder.append("proto");
        r4_StringBuilder.append(" =? ;");
        try {
            String r4_String = r4_StringBuilder.toString();
            String[] r5_StringA = new String[3];
            r5_StringA[0] = r9_String;
            r5_StringA[1] = r10_String;
            int r6i = XListViewHeader.STATE_REFRESHING;
            Cursor r0_Cursor;
            ci r1_ci;
            if (r11z) {
                r5_StringA[r6i] = String.valueOf(r0i);
                r0_Cursor = r3_SQLiteDatabase.rawQuery(r4_String, r5_StringA);
                if (r0_Cursor == null || r0_Cursor.getCount() <= 0) {
                    r0_Cursor.close();
                } else {
                    r0_Cursor.moveToFirst();
                    while (true) {
                        r1_ci = new ci();
                        r1_ci.a = r0_Cursor.getInt(r0_Cursor.getColumnIndex(LocaleUtil.INDONESIAN));
                        r1_ci.b = r0_Cursor.getString(r0_Cursor.getColumnIndex("domain"));
                        r1_ci.c = r0_Cursor.getString(r0_Cursor.getColumnIndex("sp"));
                        r2_List.add(r1_ci);
                        if (!r0_Cursor.moveToNext()) {
                            r0_Cursor.close();
                        }
                    }
                }
                return r2_List;
            } else {
                r0i = 0;
                r5_StringA[r6i] = String.valueOf(r0i);
                r0_Cursor = r3_SQLiteDatabase.rawQuery(r4_String, r5_StringA);
                if (r0_Cursor == null || r0_Cursor.getCount() <= 0) {
                    r0_Cursor.close();
                } else {
                    r0_Cursor.moveToFirst();
                    while (true) {
                        r1_ci = new ci();
                        r1_ci.a = r0_Cursor.getInt(r0_Cursor.getColumnIndex(LocaleUtil.INDONESIAN));
                        r1_ci.b = r0_Cursor.getString(r0_Cursor.getColumnIndex("domain"));
                        r1_ci.c = r0_Cursor.getString(r0_Cursor.getColumnIndex("sp"));
                        r2_List.add(r1_ci);
                        if (r0_Cursor.moveToNext()) {
                            r1_ci = new ci();
                            r1_ci.a = r0_Cursor.getInt(r0_Cursor.getColumnIndex(LocaleUtil.INDONESIAN));
                            r1_ci.b = r0_Cursor.getString(r0_Cursor.getColumnIndex("domain"));
                            r1_ci.c = r0_Cursor.getString(r0_Cursor.getColumnIndex("sp"));
                            r2_List.add(r1_ci);
                            if (r0_Cursor.moveToNext()) {
                                r0_Cursor.close();
                            }
                        }
                        r0_Cursor.close();
                    }
                }
                return r2_List;
            }
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e(a, new StringBuilder("###\u67e5\u8be2\u57df\u540d\u4fe1\u606f\u5f02\u5e38\uff1a[").append(r9_String).append("][").append(r10_String).append("]").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
        }
    }

    public cj a(String r9_String, int r10i, int r11i) {
        cj r2_cj = null;
        SQLiteDatabase r0_SQLiteDatabase = getReadableDatabase();
        if (r0_SQLiteDatabase == null) {
            return r2_cj;
        }
        StringBuilder r3_StringBuilder = new StringBuilder();
        r3_StringBuilder.append("connect_fail_column_ip");
        r3_StringBuilder.append(" = ? AND ");
        r3_StringBuilder.append("connect_fail_column_port");
        r3_StringBuilder.append(" = ? AND ");
        r3_StringBuilder.append("connect_fail_column_errcode");
        r3_StringBuilder.append(" = ? ");
        String[] r4_StringA = new String[3];
        r4_StringA[0] = r9_String;
        r4_StringA[1] = String.valueOf(r10i);
        r4_StringA[2] = String.valueOf(r11i);
        Cursor r1_Cursor = r0_SQLiteDatabase.query("connect_fail_table", null, r3_StringBuilder.toString(), r4_StringA, null, null, null);
        if (r1_Cursor == null || r1_Cursor.getCount() <= 0) {
            r1_Cursor.close();
            r0_SQLiteDatabase.close();
        } else {
            r1_Cursor.moveToFirst();
            r2_cj = new cj();
            r2_cj.d = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_count"));
            r2_cj.c = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_errcode"));
            r2_cj.a = r9_String;
            r2_cj.b = r10i;
            r2_cj.e = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_iptype"));
            r2_cj.f = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_network"));
            r2_cj.g = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_sp"));
            r2_cj.h = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_proto"));
            r2_cj.i = r1_Cursor.getLong(r1_Cursor.getColumnIndex("connect_fail_column_time"));
            r1_Cursor.close();
            r0_SQLiteDatabase.close();
        }
        return r2_cj;
    }

    public List a() {
        List r8_List = new ArrayList();
        SQLiteDatabase r0_SQLiteDatabase = getWritableDatabase();
        if (r0_SQLiteDatabase == null) {
            return r8_List;
        }
        long r3j = System.currentTimeMillis();
        long r5j = bv.c().e();
        bv.c().f();
        StringBuilder r7_StringBuilder = new StringBuilder();
        r7_StringBuilder.append("connect_fail_column_time").append(" > ").append(r3j - r5j * 10);
        Cursor r1_Cursor = r0_SQLiteDatabase.query("connect_fail_table", null, r7_StringBuilder.toString(), null, null, null, null);
        while (r1_Cursor.moveToNext()) {
            cj r2_cj = new cj();
            r2_cj.d = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_count"));
            r2_cj.c = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_errcode"));
            r2_cj.a = r1_Cursor.getString(r1_Cursor.getColumnIndex("connect_fail_column_ip"));
            r2_cj.b = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_port"));
            r2_cj.e = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_iptype"));
            r2_cj.f = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_network"));
            r2_cj.g = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_sp"));
            r2_cj.h = r1_Cursor.getInt(r1_Cursor.getColumnIndex("connect_fail_column_proto"));
            r2_cj.i = r1_Cursor.getLong(r1_Cursor.getColumnIndex("connect_fail_column_time"));
            r8_List.add(r2_cj);
        }
        a(r0_SQLiteDatabase);
        r1_Cursor.close();
        r0_SQLiteDatabase.close();
        return r8_List;
    }

    public void a(int r8i) {
        synchronized (b) {
            try {
                SQLiteDatabase r2_SQLiteDatabase = getWritableDatabase();
                try {
                    String[] r4_StringA = new String[1];
                    r4_StringA[0] = String.valueOf(r8i);
                    r2_SQLiteDatabase.delete("domain", "id = ?", r4_StringA);
                    r4_StringA = new String[1];
                    r4_StringA[0] = String.valueOf(r8i);
                    r2_SQLiteDatabase.delete("ip", "d_id = ?", r4_StringA);
                    r2_SQLiteDatabase.close();
                } catch (Exception e) {
                    Throwable r0_Throwable = e;
                    WnsClientLog.e(a, new StringBuilder("###\u5220\u9664\u6570\u636e\u5f02\u5e38\uff1a").append(r8i).append(". ").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
                    r2_SQLiteDatabase.close();
                }
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void a(cj r12_cj, int r13i) {
        cj r1_cj = a(r12_cj.a, r12_cj.b, r12_cj.c);
        SQLiteDatabase r2_SQLiteDatabase = getWritableDatabase();
        if (r2_SQLiteDatabase == null) {
        } else {
            if (r1_cj == null) {
                if (r13i > 0) {
                    a(r2_SQLiteDatabase, r12_cj, r13i);
                }
            } else {
                int r0i = 0;
                long r3j = bv.c().e();
                if (r1_cj.i >= System.currentTimeMillis() - r3j * 10) {
                    r0i = r1_cj.d + r13i;
                }
                if (r0i <= 0) {
                    b(r12_cj.a, r12_cj.b, r12_cj.c);
                } else {
                    a(r2_SQLiteDatabase, r1_cj, r12_cj, r13i);
                }
            }
            r2_SQLiteDatabase.close();
        }
    }

    public void a(cl r11_cl, boolean r12z) {
        String r1_String;
        SQLiteDatabase r3_SQLiteDatabase;
        synchronized (b) {
            try {
                String r0_String = r11_cl.c;
                r1_String = "0".equals(r0_String) ? em.d() : r0_String;
                er.a(a, new StringBuilder(">>>\u5c06\u7ed3\u679c\u7f13\u5b58\u81f3\u6570\u636e\u5e93 key=").append(r11_cl.b).append(r1_String).append(",tId:").append(Thread.currentThread().getId()).toString());
                Iterator r3_Iterator = c(r11_cl.b, r1_String, r12z).iterator();
                while (r3_Iterator.hasNext()) {
                    a(((ci) r3_Iterator.next()).a);
                }
                r3_SQLiteDatabase = getWritableDatabase();
                r3_SQLiteDatabase.beginTransaction();
                ContentValues r4_ContentValues = new ContentValues();
                r4_ContentValues.put("domain", r11_cl.b);
                r4_ContentValues.put("sp", r1_String);
                r4_ContentValues.put("expire_time", Long.valueOf(r11_cl.d));
                r4_ContentValues.put("retest_ts", Integer.valueOf(r11_cl.e));
                r4_ContentValues.put("proto", Integer.valueOf(r12z ? 1 : 0));
                long r4j = r3_SQLiteDatabase.insert("domain", null, r4_ContentValues);
                Iterator r6_Iterator = r11_cl.f.iterator();
                while (r6_Iterator.hasNext()) {
                    ch r0_ch = (ch) r6_Iterator.next();
                    ContentValues r7_ContentValues = new ContentValues();
                    r7_ContentValues.put("d_id", Long.valueOf(r4j));
                    r7_ContentValues.put("ip", r0_ch.a());
                    r7_ContentValues.put("port", Integer.valueOf(r0_ch.e()));
                    r7_ContentValues.put("sp", Short.valueOf(r0_ch.c()));
                    r7_ContentValues.put(QsbkDatabase.TYPE, Short.valueOf(r0_ch.b()));
                    r7_ContentValues.put("pri", Short.valueOf(r0_ch.d()));
                    r3_SQLiteDatabase.insert("ip", null, r7_ContentValues);
                }
                r3_SQLiteDatabase.setTransactionSuccessful();
                r3_SQLiteDatabase.endTransaction();
                r3_SQLiteDatabase.close();
            } catch (Throwable th) {
            }
        }
    }

    public void a(String r6_String, String r7_String, boolean r8z) {
        int r3i = 1;
        List r0_List = c(r6_String, r7_String, r8z);
        if (r0_List == null || r0_List.size() <= 0) {
            String r0_String = a;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r6_String;
            WnsClientLog.i(r0_String, String.format("domain %s didn't exist in db.", r2_ObjectA));
        } else {
            String r1_String = a;
            Object[] r3_ObjectA = new Object[r3i];
            r3_ObjectA[0] = r6_String;
            WnsClientLog.i(r1_String, String.format("domain %s exist, delete it from db.", r3_ObjectA));
            a(((ci) r0_List.get(0)).a);
        }
    }

    public void a(String r10_String, String r11_String, boolean r12z, String r13_String, String r14_String) {
        SQLiteDatabase r2_SQLiteDatabase;
        synchronized (b) {
            try {
                er.a(a, new StringBuilder(">>>\u66f4\u65b0IP\u4f18\u5148\u7ea7.[\u539fIP:").append(r13_String).append("][\u65b0IP:").append(r14_String).append("]").toString());
                List r0_List = c(r10_String, r11_String, r12z);
                if (r0_List == null || r0_List.size() == 0) {
                    er.a(a, new StringBuilder(">>>\u66f4\u65b0IP\u4f18\u5148\u7ea7\u51fa\u9519\u3002\u6ca1\u6709\u67e5\u8be2\u5230\u57df\u540d\u4fe1\u606f\uff0c\u76f4\u63a5\u8fd4\u56de[").append(r10_String).append("][").append(r11_String).append("]").toString());
                } else {
                    r2_SQLiteDatabase = getWritableDatabase();
                    try {
                        String r3_String = "ip =? AND d_id =?";
                        int r0i = ((ci) r0_List.get(0)).a;
                        r2_SQLiteDatabase.beginTransaction();
                        ContentValues r4_ContentValues = new ContentValues();
                        r4_ContentValues.put("pri", Integer.valueOf(0));
                        String[] r6_StringA = new String[2];
                        r6_StringA[0] = r13_String;
                        r6_StringA[1] = r0i;
                        r2_SQLiteDatabase.update("ip", r4_ContentValues, r3_String, r6_StringA);
                        r4_ContentValues.put("pri", Integer.valueOf(-1));
                        r6_StringA = new String[2];
                        r6_StringA[0] = r14_String;
                        r6_StringA[1] = r0i;
                        r2_SQLiteDatabase.update("ip", r4_ContentValues, r3_String, r6_StringA);
                        r2_SQLiteDatabase.setTransactionSuccessful();
                        er.a(a, new StringBuilder(">>>\u66f4\u65b0IP\u4f18\u5148\u7ea7\u6210\u529f.[\u539fIP:").append(r13_String).append("][\u65b0IP:").append(r14_String).append("]").toString());
                        r2_SQLiteDatabase.endTransaction();
                        if (r2_SQLiteDatabase != null) {
                            r2_SQLiteDatabase.close();
                        }
                    } catch (Exception e) {
                        Throwable r0_Throwable = e;
                        WnsClientLog.e(a, new StringBuilder(">>>\u66f4\u65b0IP\u51fa\u9519[").append(r10_String).append("][").append(r11_String).append("]").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
                        er.a(a, new StringBuilder(">>>\u66f4\u65b0IP\u4f18\u5148\u7ea7\u51fa\u9519:[domain:").append(r10_String).append("][sp:").append(r11_String).append("][ERR:").append(r0_Throwable.getMessage()).append("]").toString());
                        r2_SQLiteDatabase.endTransaction();
                        if (r2_SQLiteDatabase != null) {
                            r2_SQLiteDatabase.close();
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cl b(String r13_String, String r14_String, boolean r15z) {
        /*
        r12_this = this;
        r1 = 1;
        r2 = 0;
        r0 = 0;
        r9 = b;
        monitor-enter(r9);
        r3 = a;	 //Catch:{ all -> 0x0173 }
        r4 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0173 }
        r5 = ">>>\u4eceDB\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: [domain:";
        r4.<init>(r5);	 //Catch:{ all -> 0x0173 }
        r4 = r4.append(r13);	 //Catch:{ all -> 0x0173 }
        r5 = "][sp:";
        r4 = r4.append(r5);	 //Catch:{ all -> 0x0173 }
        r4 = r4.append(r14);	 //Catch:{ all -> 0x0173 }
        r5 = "]";
        r4 = r4.append(r5);	 //Catch:{ all -> 0x0173 }
        r4 = r4.toString();	 //Catch:{ all -> 0x0173 }
        com.tencent.cloudsdk.er.a(r3, r4);	 //Catch:{ all -> 0x0173 }
        r3 = "SELECT d.expire_time,d.retest_ts,i.* FROM domain d JOIN ip i ON d.id = i.d_id WHERE d.domain= ? AND d.sp= ? AND d.proto = ? order by i.pri";
        r4 = 0;
        java.lang.Long.valueOf(r4);	 //Catch:{ all -> 0x0173 }
        r10 = r12.getReadableDatabase();	 //Catch:{ all -> 0x0173 }
        r4 = 3;
        r4 = new java.lang.String[r4];	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        r5 = 0;
        r4[r5] = r13;	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        r5 = 1;
        r4[r5] = r14;	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        r5 = 2;
        if (r15 == 0) goto L_0x00f3;
    L_0x0041:
        r1 = java.lang.String.valueOf(r1);	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        r4[r5] = r1;	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        r6 = r10.rawQuery(r3, r4);	 //Catch:{ Exception -> 0x0176, all -> 0x01f1 }
        if (r6 == 0) goto L_0x0222;
    L_0x004d:
        r1 = r6.getCount();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        if (r1 <= 0) goto L_0x0222;
    L_0x0053:
        r6.moveToFirst();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = a;	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = ">>>\u4eceDB\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: [domain:";
        r2.<init>(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = r2.append(r13);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = "][sp:";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = r2.append(r14);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = "][\u5171\u6709 ";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = r6.getCount();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = " \u6761\u6570\u636e]";
        r2 = r2.append(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        com.tencent.cloudsdk.er.a(r1, r2);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = "expire_time";
        r1 = r6.getColumnIndex(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = r6.getLong(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = java.lang.Long.valueOf(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = java.lang.System.currentTimeMillis();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r4 = r1.longValue();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r2 = (r2 > r4 ? 1 : (r2 == r4? 0 : -1));
        if (r2 <= 0) goto L_0x00f6;
    L_0x00a2:
        r2 = a;	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r4 = ">>>\u4eceDB\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: [domain:";
        r3.<init>(r4);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = r3.append(r13);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r4 = "][sp:";
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = r3.append(r14);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r4 = "][\u6570\u636e\u5df2\u7ecf\u8fc7\u671f: ";
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = r3.append(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r3 = " ]";
        r1 = r1.append(r3);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = r1.toString();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        com.tencent.cloudsdk.er.a(r2, r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = "d_id";
        r1 = r6.getColumnIndex(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = r6.getInt(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r12.a(r1);	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r1 = r10.isOpen();	 //Catch:{ all -> 0x0173 }
        if (r1 == 0) goto L_0x00e6;
    L_0x00e3:
        r10.close();	 //Catch:{ all -> 0x0173 }
    L_0x00e6:
        if (r6 == 0) goto L_0x00f1;
    L_0x00e8:
        r1 = r6.isClosed();	 //Catch:{ all -> 0x0173 }
        if (r1 != 0) goto L_0x00f1;
    L_0x00ee:
        r6.close();	 //Catch:{ all -> 0x0173 }
    L_0x00f1:
        monitor-exit(r9);	 //Catch:{ all -> 0x0173 }
    L_0x00f2:
        return r0;
    L_0x00f3:
        r1 = r2;
        goto L_0x0041;
    L_0x00f6:
        r8 = new com.tencent.cloudsdk.cl;	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r8.<init>();	 //Catch:{ Exception -> 0x020e, all -> 0x0209 }
        r8.b = r13;	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r8.c = r14;	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r1 = r1.longValue();	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r8.d = r1;	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r1 = "retest_ts";
        r1 = r6.getColumnIndex(r1);	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r1 = r6.getInt(r1);	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r8.e = r1;	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r7 = new java.util.ArrayList;	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
        r7.<init>();	 //Catch:{ Exception -> 0x0215, all -> 0x0209 }
    L_0x0116:
        r0 = new com.tencent.cloudsdk.ch;	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r1 = "ip";
        r1 = r6.getColumnIndex(r1);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r1 = r6.getString(r1);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r2 = "port";
        r2 = r6.getColumnIndex(r2);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r2 = r6.getInt(r2);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r3 = "type";
        r3 = r6.getColumnIndex(r3);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r3 = r6.getShort(r3);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r4 = "sp";
        r4 = r6.getColumnIndex(r4);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r4 = r6.getShort(r4);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r5 = "pri";
        r5 = r6.getColumnIndex(r5);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r5 = r6.getShort(r5);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r0.<init>(r1, r2, r3, r4, r5);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r7.add(r0);	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        r0 = r6.moveToNext();	 //Catch:{ Exception -> 0x021c, all -> 0x0209 }
        if (r0 != 0) goto L_0x0116;
    L_0x0156:
        r2 = r7;
        r3 = r8;
    L_0x0158:
        r0 = r10.isOpen();	 //Catch:{ all -> 0x0173 }
        if (r0 == 0) goto L_0x0161;
    L_0x015e:
        r10.close();	 //Catch:{ all -> 0x0173 }
    L_0x0161:
        if (r6 == 0) goto L_0x016c;
    L_0x0163:
        r0 = r6.isClosed();	 //Catch:{ all -> 0x0173 }
        if (r0 != 0) goto L_0x016c;
    L_0x0169:
        r6.close();	 //Catch:{ all -> 0x0173 }
    L_0x016c:
        r0 = r3;
    L_0x016d:
        if (r0 == 0) goto L_0x0171;
    L_0x016f:
        r0.f = r2;	 //Catch:{ all -> 0x0173 }
    L_0x0171:
        monitor-exit(r9);	 //Catch:{ all -> 0x0173 }
        goto L_0x00f2;
    L_0x0173:
        r0 = move-exception;
        monitor-exit(r9);	 //Catch:{ all -> 0x0173 }
        throw r0;
    L_0x0176:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x017c:
        r4 = a;	 //Catch:{ all -> 0x020b }
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x020b }
        r6 = "###\u83b7\u53d6\u6570\u636e\u5f02\u5e38\uff1a[";
        r5.<init>(r6);	 //Catch:{ all -> 0x020b }
        r5 = r5.append(r13);	 //Catch:{ all -> 0x020b }
        r6 = "][";
        r5 = r5.append(r6);	 //Catch:{ all -> 0x020b }
        r5 = r5.append(r14);	 //Catch:{ all -> 0x020b }
        r6 = "]";
        r5 = r5.append(r6);	 //Catch:{ all -> 0x020b }
        r6 = r0.getMessage();	 //Catch:{ all -> 0x020b }
        r5 = r5.append(r6);	 //Catch:{ all -> 0x020b }
        r5 = r5.toString();	 //Catch:{ all -> 0x020b }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r5, r0);	 //Catch:{ all -> 0x020b }
        r4 = a;	 //Catch:{ all -> 0x020b }
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x020b }
        r6 = ">>>\u4eceDB\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: [domain:";
        r5.<init>(r6);	 //Catch:{ all -> 0x020b }
        r5 = r5.append(r13);	 //Catch:{ all -> 0x020b }
        r6 = "][sp:";
        r5 = r5.append(r6);	 //Catch:{ all -> 0x020b }
        r5 = r5.append(r14);	 //Catch:{ all -> 0x020b }
        r6 = "][\u67e5\u8be2\u5f02\u5e38: ";
        r5 = r5.append(r6);	 //Catch:{ all -> 0x020b }
        r0 = r0.getMessage();	 //Catch:{ all -> 0x020b }
        r0 = r5.append(r0);	 //Catch:{ all -> 0x020b }
        r5 = " ]";
        r0 = r0.append(r5);	 //Catch:{ all -> 0x020b }
        r0 = r0.toString();	 //Catch:{ all -> 0x020b }
        com.tencent.cloudsdk.er.a(r4, r0);	 //Catch:{ all -> 0x020b }
        r0 = r10.isOpen();	 //Catch:{ all -> 0x0173 }
        if (r0 == 0) goto L_0x01e3;
    L_0x01e0:
        r10.close();	 //Catch:{ all -> 0x0173 }
    L_0x01e3:
        if (r1 == 0) goto L_0x016c;
    L_0x01e5:
        r0 = r1.isClosed();	 //Catch:{ all -> 0x0173 }
        if (r0 != 0) goto L_0x016c;
    L_0x01eb:
        r1.close();	 //Catch:{ all -> 0x0173 }
        r0 = r3;
        goto L_0x016d;
    L_0x01f1:
        r1 = move-exception;
        r6 = r0;
        r0 = r1;
    L_0x01f4:
        r1 = r10.isOpen();	 //Catch:{ all -> 0x0173 }
        if (r1 == 0) goto L_0x01fd;
    L_0x01fa:
        r10.close();	 //Catch:{ all -> 0x0173 }
    L_0x01fd:
        if (r6 == 0) goto L_0x0208;
    L_0x01ff:
        r1 = r6.isClosed();	 //Catch:{ all -> 0x0173 }
        if (r1 != 0) goto L_0x0208;
    L_0x0205:
        r6.close();	 //Catch:{ all -> 0x0173 }
    L_0x0208:
        throw r0;	 //Catch:{ all -> 0x0173 }
    L_0x0209:
        r0 = move-exception;
        goto L_0x01f4;
    L_0x020b:
        r0 = move-exception;
        r6 = r1;
        goto L_0x01f4;
    L_0x020e:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x017c;
    L_0x0215:
        r1 = move-exception;
        r2 = r0;
        r3 = r8;
        r0 = r1;
        r1 = r6;
        goto L_0x017c;
    L_0x021c:
        r0 = move-exception;
        r1 = r6;
        r2 = r7;
        r3 = r8;
        goto L_0x017c;
    L_0x0222:
        r2 = r0;
        r3 = r0;
        goto L_0x0158;
        */

    }

    protected void b() {
        synchronized (b) {
            SQLiteDatabase r3_SQLiteDatabase;
            try {
                r3_SQLiteDatabase = getReadableDatabase();
                try {
                    Cursor r1_Cursor = r3_SQLiteDatabase.rawQuery("SELECT * FROM domain", null);
                    if (r1_Cursor == null || r1_Cursor.getCount() <= 0) {
                        if (!r3_SQLiteDatabase.isOpen()) {
                            r3_SQLiteDatabase.close();
                        }
                        if (r1_Cursor == null || r1_Cursor.isClosed()) {
                        } else {
                            r1_Cursor.close();
                        }
                    } else {
                        r1_Cursor.moveToFirst();
                        while (true) {
                            if (System.currentTimeMillis() > r1_Cursor.getLong(r1_Cursor.getColumnIndex("expire_time"))) {
                                int r0i = r1_Cursor.getInt(r1_Cursor.getColumnIndex(LocaleUtil.INDONESIAN));
                                String[] r6_StringA = new String[1];
                                r6_StringA[0] = String.valueOf(r0i);
                                r3_SQLiteDatabase.delete("domain", "id = ?", r6_StringA);
                                r6_StringA = new String[1];
                                r6_StringA[0] = String.valueOf(r0i);
                                r3_SQLiteDatabase.delete("ip", "d_id = ?", r6_StringA);
                            }
                            if (!r1_Cursor.moveToNext()) {
                                if (r3_SQLiteDatabase.isOpen()) {
                                    if (r1_Cursor == null || r1_Cursor.isClosed()) {
                                    } else {
                                        r1_Cursor.close();
                                    }
                                } else {
                                    r3_SQLiteDatabase.close();
                                    if (r1_Cursor == null || r1_Cursor.isClosed()) {
                                    } else {
                                        r1_Cursor.close();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    WnsClientLog.e(a, "###\u6e05\u7406\u8fc7\u671f\u6570\u636e\u5f02\u5e38", e);
                    if (r3_SQLiteDatabase.isOpen()) {
                        r3_SQLiteDatabase.close();
                    }
                    if (0 == 0 || null.isClosed()) {
                    } else {
                        null.close();
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void b(String r7_String, int r8i, int r9i) {
        SQLiteDatabase r0_SQLiteDatabase = getWritableDatabase();
        if (r0_SQLiteDatabase == null) {
        } else {
            StringBuilder r1_StringBuilder = new StringBuilder();
            r1_StringBuilder.append("connect_fail_column_ip");
            r1_StringBuilder.append(" = ? AND ");
            r1_StringBuilder.append("connect_fail_column_port");
            r1_StringBuilder.append(" = ? AND ");
            r1_StringBuilder.append("connect_fail_column_errcode");
            r1_StringBuilder.append(" = ? ");
            try {
                String r1_String = r1_StringBuilder.toString();
                String[] r3_StringA = new String[3];
                r3_StringA[0] = r7_String;
                r3_StringA[1] = String.valueOf(r8i);
                r3_StringA[2] = String.valueOf(r9i);
                r0_SQLiteDatabase.delete("connect_fail_table", r1_String, r3_StringA);
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                WnsClientLog.e(a, r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }

    public void onCreate(SQLiteDatabase r2_SQLiteDatabase) {
        r2_SQLiteDatabase.execSQL("CREATE TABLE domain (id INTEGER PRIMARY KEY,domain TEXT,sp TEXT,proto INTEGER,expire_time TEXT,retest_ts INTEGER);");
        r2_SQLiteDatabase.execSQL("CREATE TABLE ip (id INTEGER PRIMARY KEY,d_id INTEGER,ip TEXT,port INTEGER,sp INTEGER,type INTEGER,pri INTEGER);");
        r2_SQLiteDatabase.execSQL("CREATE TABLE aip (id INTEGER PRIMARY KEY,ip TEXT,sp INTEGER);");
        r2_SQLiteDatabase.execSQL("CREATE TABLE connect_fail_table (connect_fail_column_id INTEGER PRIMARY KEY,connect_fail_column_ip TEXT,connect_fail_column_port INTEGER,connect_fail_column_errcode INTEGER,connect_fail_column_network INTEGER,connect_fail_column_iptype INTEGER,connect_fail_column_sp INTEGER,connect_fail_column_proto INTEGER,connect_fail_column_time INTEGER,connect_fail_column_count INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase r2_SQLiteDatabase, int r3i, int r4i) {
        if (r3i != r4i) {
            r2_SQLiteDatabase.beginTransaction();
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS domain;");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS ip;");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS acs;");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS aip;");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS connect_fail_table;");
            r2_SQLiteDatabase.setTransactionSuccessful();
            r2_SQLiteDatabase.endTransaction();
            onCreate(r2_SQLiteDatabase);
        }
    }
}