package com.google.analytics.tracking.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: PersistentAnalyticsStore.java
class ag implements d {
    private static final String d;
    private long a;
    private long b;
    private boolean c;
    private final a e;
    private volatile g f;
    private final e g;
    private final Context h;
    private final String i;
    private long j;

    // compiled from: PersistentAnalyticsStore.java
    class a extends SQLiteOpenHelper {
        private boolean b;

        a(Context r4_Context, String r5_String) {
            super(r4_Context, r5_String, null, 1);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean a_(String r11_String, SQLiteDatabase r12_SQLiteDatabase) {
            /*
            r10_this = this;
            r8 = 0;
            r9 = 0;
            r1 = "SQLITE_MASTER";
            r0 = 1;
            r2 = new java.lang.String[r0];	 //Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
            r0 = 0;
            r3 = "name";
            r2[r0] = r3;	 //Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
            r3 = "name=?";
            r0 = 1;
            r4 = new java.lang.String[r0];	 //Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
            r0 = 0;
            r4[r0] = r11;	 //Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
            r5 = 0;
            r6 = 0;
            r7 = 0;
            r0 = r12;
            r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 //Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
            r0 = r1.moveToFirst();	 //Catch:{ SQLiteException -> 0x0053, all -> 0x004c }
            if (r1 == 0) goto L_0x0025;
        L_0x0022:
            r1.close();
        L_0x0025:
            return r0;
        L_0x0026:
            r0 = move-exception;
            r0 = r9;
        L_0x0028:
            r1 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x004f }
            r1.<init>();	 //Catch:{ all -> 0x004f }
            r2 = "error querying for table ";
            r1 = r1.append(r2);	 //Catch:{ all -> 0x004f }
            r1 = r1.append(r11);	 //Catch:{ all -> 0x004f }
            r1 = r1.toString();	 //Catch:{ all -> 0x004f }
            com.google.analytics.tracking.android.z.h(r1);	 //Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x0043;
        L_0x0040:
            r0.close();
        L_0x0043:
            r0 = r8;
            goto L_0x0025;
        L_0x0045:
            r0 = move-exception;
        L_0x0046:
            if (r9 == 0) goto L_0x004b;
        L_0x0048:
            r9.close();
        L_0x004b:
            throw r0;
        L_0x004c:
            r0 = move-exception;
            r9 = r1;
            goto L_0x0046;
        L_0x004f:
            r1 = move-exception;
            r9 = r0;
            r0 = r1;
            goto L_0x0046;
        L_0x0053:
            r0 = move-exception;
            r0 = r1;
            goto L_0x0028;
            */

        }

        public SQLiteDatabase getWritableDatabase() {
            if (!(this.b)) {
                return super.getWritableDatabase();
            }
            throw new SQLiteException("Database creation failed");
        }

        public void onCreate(SQLiteDatabase r2_SQLiteDatabase) {
            i.a(r2_SQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase r2_SQLiteDatabase) {
            try {
                if (!a("hits2", r2_SQLiteDatabase)) {
                    r2_SQLiteDatabase.execSQL(d);
                }
            } catch (SQLiteException e) {
                z.h("Error on database open");
                this.b = true;
            }
        }

        public void onUpgrade(SQLiteDatabase r1_SQLiteDatabase, int r2i, int r3i) {
        }
    }

    static {
        Object[] r1_ObjectA = new Object[5];
        r1_ObjectA[0] = "hits2";
        r1_ObjectA[1] = "hit_id";
        r1_ObjectA[2] = "hit_time";
        r1_ObjectA[3] = "hit_url";
        r1_ObjectA[4] = "hit_string";
        d = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL);", r1_ObjectA);
    }

    ag(e r2_e, Context r3_Context) {
        this(r2_e, r3_Context, "google_analytics_v2.db");
    }

    ag(e r4_e, Context r5_Context, String r6_String) {
        this.a = 120000;
        this.c = true;
        this.h = r5_Context.getApplicationContext();
        this.i = r6_String;
        this.g = r4_e;
        this.e = new a(this.h, this.i);
        this.f = new aj(this, e(), this.h);
        this.j = 0;
    }

    private SQLiteDatabase a(String r2_String) {
        try {
            return this.e.getWritableDatabase();
        } catch (SQLiteException e) {
            z.h(r2_String);
            return null;
        }
    }

    private void a(Map<String, String> r5_Map_String__String, long r6j, String r8_String) {
        SQLiteDatabase r0_SQLiteDatabase = a("Error opening database for putHit");
        if (r0_SQLiteDatabase == null) {
        } else {
            ContentValues r1_ContentValues = new ContentValues();
            r1_ContentValues.put("hit_string", generateHitString(r5_Map_String__String));
            r1_ContentValues.put("hit_time", Long.valueOf(r6j));
            if (r8_String == null) {
                r8_String = "http://www.google-analytics.com/collect";
            }
            if (r8_String.length() == 0) {
                z.h("empty path: not sending hit");
            } else {
                r1_ContentValues.put("hit_url", r8_String);
                try {
                    r0_SQLiteDatabase.insert("hits2", null, r1_ContentValues);
                    this.g.reportStoreIsEmpty(false);
                } catch (SQLiteException e) {
                    z.h("Error storing hit");
                }
            }
        }
    }

    private void a(Map<String, String> r1_Map_String__String, String r2_String, String r3_String) {
        if (r3_String == null) {
            r3_String = "-s1";
        }
        if (r2_String != null) {
            r1_Map_String__String.put(r2_String, r3_String);
        }
    }

    private void a(Map<String, String> r5_Map_String__String, Collection<Command> r6_Collection_Command) {
        Iterator r1_Iterator = r6_Collection_Command.iterator();
        while (r1_Iterator.hasNext()) {
            Command r0_Command = (Command) r1_Iterator.next();
            if (r0_Command.getId().equals(Command.APPEND_VERSION)) {
                a((Map)r5_Map_String__String, r0_Command.getUrlParam(), r0_Command.getValue());
                return;
            }
        }
    }

    private y e() {
        return new ah(this);
    }

    private void f() {
        int r0i = b() - 2000 + 1;
        if (r0i > 0) {
            Collection r0_Collection = peekHits(r0i);
            z.i("Store full, deleting " + r0_Collection.size() + " hits to make room");
            deleteHits(r0_Collection);
        }
    }

    public static String generateHitString(Map<String, String> r5_Map_String__String) {
        Iterable r2_Iterable = new ArrayList(r5_Map_String__String.size());
        Iterator r3_Iterator = r5_Map_String__String.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r2_Iterable.add(((String) r0_Entry.getKey()) + "=" + x.encode((String) r0_Entry.getValue()));
        }
        return TextUtils.join("&", r2_Iterable);
    }

    int a() {
        boolean r0z = true;
        long r2j = System.currentTimeMillis();
        if (r2j <= this.j + 86400000) {
            return 0;
        }
        this.j = r2j;
        SQLiteDatabase r2_SQLiteDatabase = a("Error opening database for deleteStaleHits");
        if (r2_SQLiteDatabase == null) {
            return 0;
        }
        String[] r7_StringA = new String[1];
        r7_StringA[0] = Long.toString(System.currentTimeMillis() - 2592000000L);
        int r2i = r2_SQLiteDatabase.delete("hits2", "HIT_TIME < ?", r7_StringA);
        e r3_e = this.g;
        if (b() == 0) {
            r3_e.reportStoreIsEmpty(r0z);
            return r2i;
        } else {
            r0z = false;
            r3_e.reportStoreIsEmpty(r0z);
            return r2i;
        }
    }

    int b() {
        int r0i = 0;
        SQLiteDatabase r2_SQLiteDatabase = a("Error opening database for requestNumHitsPending");
        if (r2_SQLiteDatabase == null) {
            return r0i;
        }
        try {
            Cursor r1_Cursor = r2_SQLiteDatabase.rawQuery("SELECT COUNT(*) from hits2", null);
            if (r1_Cursor.moveToFirst()) {
                r0i = (int) r1_Cursor.getLong(0);
            }
            if (r1_Cursor != null) {
                r1_Cursor.close();
            }
        } catch (SQLiteException e) {
            z.h("Error getting numStoredHits");
            if (0 != 0) {
                null.close();
            }
        }
        return r0i;
    }

    synchronized boolean c() {
        boolean r0z = true;
        synchronized (this) {
            if (this.c) {
                long r1j = System.currentTimeMillis();
                if (this.a < 120000) {
                    long r3j = r1j - this.b;
                    if (r3j > 0) {
                        this.a = Math.min(120000, r3j + this.a);
                    }
                }
                this.b = r1j;
                if (this.a >= 2000) {
                    this.a -= 2000;
                } else {
                    z.i("Excessive tracking detected.  Tracking call ignored.");
                    r0z = false;
                }
            }
        }
        return r0z;
    }

    public void clearHits() {
        SQLiteDatabase r0_SQLiteDatabase = a("Error opening database for clearHits");
        if (r0_SQLiteDatabase != null) {
            r0_SQLiteDatabase.delete("hits2", null, null);
            this.g.reportStoreIsEmpty(true);
        }
    }

    public void close() {
        try {
            this.e.getWritableDatabase().close();
        } catch (SQLiteException e) {
            z.h("Error opening database for close");
        }
    }

    public void deleteHits(Collection<w> r12_Collection_w) {
        boolean r2z = false;
        if (r12_Collection_w == null) {
            throw new NullPointerException("hits cannot be null");
        } else if (r12_Collection_w.isEmpty()) {
        } else {
            SQLiteDatabase r5_SQLiteDatabase = a("Error opening database for deleteHit");
            if (r5_SQLiteDatabase != null) {
                String[] r6_StringA = new String[r12_Collection_w.size()];
                Object[] r1_ObjectA = new Object[1];
                r1_ObjectA[0] = TextUtils.join(",", Collections.nCopies(r6_StringA.length, "?"));
                String r7_String = String.format("HIT_ID in (%s)", r1_ObjectA);
                Iterator r8_Iterator = r12_Collection_w.iterator();
                int r1i = 0;
                while (r8_Iterator.hasNext()) {
                    r6_StringA[r1i] = Long.toString(((w) r8_Iterator.next()).b());
                    r1i++;
                }
                try {
                    r5_SQLiteDatabase.delete("hits2", r7_String, r6_StringA);
                    e r0_e = this.g;
                    if (b() == 0) {
                        r2z = true;
                    }
                    r0_e.reportStoreIsEmpty(r2z);
                } catch (SQLiteException e) {
                    z.h("Error deleting hit " + r12_Collection_w);
                }
            }
        }
    }

    public void dispatch() {
        z.g("dispatch running...");
        if (this.f.okToDispatch()) {
            List r0_List = peekHits(40);
            if (r0_List.isEmpty()) {
                z.g("...nothing to dispatch");
            } else {
                int r1i = this.f.dispatchHits(r0_List);
                z.g("sent " + r1i + " of " + r0_List.size() + " hits");
                deleteHits(r0_List.subList(0, Math.min(r1i, r0_List.size())));
                if (r1i != r0_List.size() || b() <= 0) {
                } else {
                    GAServiceManager.getInstance().dispatch();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public List<w> peekHits(int r16i) {
        /*
        r15_this = this;
        r1 = "Error opening database for peekHits";
        r1 = r15.a(r1);
        if (r1 != 0) goto L_0x000e;
    L_0x0008:
        r1 = new java.util.ArrayList;
        r1.<init>();
    L_0x000d:
        return r1;
    L_0x000e:
        r10 = 0;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r2 = "hits2";
        r3 = 3;
        r3 = new java.lang.String[r3];	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r4 = 0;
        r5 = "hit_id";
        r3[r4] = r5;	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r4 = 1;
        r5 = "hit_time";
        r3[r4] = r5;	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r4 = 2;
        r5 = "hit_url";
        r3[r4] = r5;	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = "%s ASC, %s ASC";
        r9 = 2;
        r9 = new java.lang.Object[r9];	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r11 = 0;
        r12 = "hit_url";
        r9[r11] = r12;	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r11 = 1;
        r12 = "hit_id";
        r9[r11] = r12;	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r8 = java.lang.String.format(r8, r9);	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r9 = java.lang.Integer.toString(r16);	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r11 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9);	 //Catch:{ SQLiteException -> 0x00d9, all -> 0x0101 }
        r10 = new java.util.ArrayList;	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r10.<init>();	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r2 = r11.moveToFirst();	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        if (r2 == 0) goto L_0x0073;
    L_0x0052:
        r2 = new com.google.analytics.tracking.android.w;	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r3 = 0;
        r4 = 0;
        r4 = r11.getLong(r4);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r6 = 1;
        r6 = r11.getLong(r6);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r2.<init>(r3, r4, r6);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r3 = 2;
        r3 = r11.getString(r3);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r2.setHitUrl(r3);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r10.add(r2);	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        r2 = r11.moveToNext();	 //Catch:{ SQLiteException -> 0x019e, all -> 0x0196 }
        if (r2 != 0) goto L_0x0052;
    L_0x0073:
        if (r11 == 0) goto L_0x0078;
    L_0x0075:
        r11.close();
    L_0x0078:
        r12 = 0;
        r2 = "hits2";
        r3 = 2;
        r3 = new java.lang.String[r3];	 //Catch:{ SQLiteException -> 0x0194 }
        r4 = 0;
        r5 = "hit_id";
        r3[r4] = r5;	 //Catch:{ SQLiteException -> 0x0194 }
        r4 = 1;
        r5 = "hit_string";
        r3[r4] = r5;	 //Catch:{ SQLiteException -> 0x0194 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = "%s ASC";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 //Catch:{ SQLiteException -> 0x0194 }
        r13 = 0;
        r14 = "hit_id";
        r9[r13] = r14;	 //Catch:{ SQLiteException -> 0x0194 }
        r8 = java.lang.String.format(r8, r9);	 //Catch:{ SQLiteException -> 0x0194 }
        r9 = java.lang.Integer.toString(r16);	 //Catch:{ SQLiteException -> 0x0194 }
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9);	 //Catch:{ SQLiteException -> 0x0194 }
        r1 = r2.moveToFirst();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        if (r1 == 0) goto L_0x00d1;
    L_0x00a8:
        r3 = r12;
    L_0x00a9:
        r1 = r2 instanceof android.database.sqlite.SQLiteCursor;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        if (r1 == 0) goto L_0x0175;
    L_0x00ad:
        r0 = r2;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = r0;
        r1 = r1.getWindow();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = r1.getNumRows();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        if (r1 <= 0) goto L_0x0108;
    L_0x00bb:
        r1 = r10.get(r3);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = (com.google.analytics.tracking.android.w) r1;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r4 = 1;
        r4 = r2.getString(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1.a(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
    L_0x00c9:
        r1 = r3 + 1;
        r3 = r2.moveToNext();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        if (r3 != 0) goto L_0x01a2;
    L_0x00d1:
        if (r2 == 0) goto L_0x00d6;
    L_0x00d3:
        r2.close();
    L_0x00d6:
        r1 = r10;
        goto L_0x000d;
    L_0x00d9:
        r1 = move-exception;
        r2 = r10;
    L_0x00db:
        r3 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x019a }
        r3.<init>();	 //Catch:{ all -> 0x019a }
        r4 = "error in peekHits fetching hitIds: ";
        r3 = r3.append(r4);	 //Catch:{ all -> 0x019a }
        r1 = r1.getMessage();	 //Catch:{ all -> 0x019a }
        r1 = r3.append(r1);	 //Catch:{ all -> 0x019a }
        r1 = r1.toString();	 //Catch:{ all -> 0x019a }
        com.google.analytics.tracking.android.z.h(r1);	 //Catch:{ all -> 0x019a }
        r1 = new java.util.ArrayList;	 //Catch:{ all -> 0x019a }
        r1.<init>();	 //Catch:{ all -> 0x019a }
        if (r2 == 0) goto L_0x000d;
    L_0x00fc:
        r2.close();
        goto L_0x000d;
    L_0x0101:
        r1 = move-exception;
    L_0x0102:
        if (r10 == 0) goto L_0x0107;
    L_0x0104:
        r10.close();
    L_0x0107:
        throw r1;
    L_0x0108:
        r1 = new java.lang.StringBuilder;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1.<init>();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r4 = "hitString for hitId ";
        r4 = r1.append(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = r10.get(r3);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = (com.google.analytics.tracking.android.w) r1;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r5 = r1.b();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = r4.append(r5);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r4 = " too large.  Hit will be deleted.";
        r1 = r1.append(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = r1.toString();	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        com.google.analytics.tracking.android.z.h(r1);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        goto L_0x00c9;
    L_0x012f:
        r1 = move-exception;
        r11 = r2;
    L_0x0131:
        r2 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0192 }
        r2.<init>();	 //Catch:{ all -> 0x0192 }
        r3 = "error in peekHits fetching hitString: ";
        r2 = r2.append(r3);	 //Catch:{ all -> 0x0192 }
        r1 = r1.getMessage();	 //Catch:{ all -> 0x0192 }
        r1 = r2.append(r1);	 //Catch:{ all -> 0x0192 }
        r1 = r1.toString();	 //Catch:{ all -> 0x0192 }
        com.google.analytics.tracking.android.z.h(r1);	 //Catch:{ all -> 0x0192 }
        r2 = new java.util.ArrayList;	 //Catch:{ all -> 0x0192 }
        r2.<init>();	 //Catch:{ all -> 0x0192 }
        r3 = 0;
        r4 = r10.iterator();	 //Catch:{ all -> 0x0192 }
    L_0x0155:
        r1 = r4.hasNext();	 //Catch:{ all -> 0x0192 }
        if (r1 == 0) goto L_0x016d;
    L_0x015b:
        r1 = r4.next();	 //Catch:{ all -> 0x0192 }
        r1 = (com.google.analytics.tracking.android.w) r1;	 //Catch:{ all -> 0x0192 }
        r5 = r1.a();	 //Catch:{ all -> 0x0192 }
        r5 = android.text.TextUtils.isEmpty(r5);	 //Catch:{ all -> 0x0192 }
        if (r5 == 0) goto L_0x018e;
    L_0x016b:
        if (r3 == 0) goto L_0x018d;
    L_0x016d:
        if (r11 == 0) goto L_0x0172;
    L_0x016f:
        r11.close();
    L_0x0172:
        r1 = r2;
        goto L_0x000d;
    L_0x0175:
        r1 = r10.get(r3);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1 = (com.google.analytics.tracking.android.w) r1;	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r4 = 1;
        r4 = r2.getString(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        r1.a(r4);	 //Catch:{ SQLiteException -> 0x012f, all -> 0x0185 }
        goto L_0x00c9;
    L_0x0185:
        r1 = move-exception;
        r11 = r2;
    L_0x0187:
        if (r11 == 0) goto L_0x018c;
    L_0x0189:
        r11.close();
    L_0x018c:
        throw r1;
    L_0x018d:
        r3 = 1;
    L_0x018e:
        r2.add(r1);	 //Catch:{ all -> 0x0192 }
        goto L_0x0155;
    L_0x0192:
        r1 = move-exception;
        goto L_0x0187;
    L_0x0194:
        r1 = move-exception;
        goto L_0x0131;
    L_0x0196:
        r1 = move-exception;
        r10 = r11;
        goto L_0x0102;
    L_0x019a:
        r1 = move-exception;
        r10 = r2;
        goto L_0x0102;
    L_0x019e:
        r1 = move-exception;
        r2 = r11;
        goto L_0x00db;
    L_0x01a2:
        r3 = r1;
        goto L_0x00a9;
        */

    }

    public void putHit(Map<String, String> r2_Map_String__String, long r3j, String r5_String, Collection<Command> r6_Collection_Command) {
        a();
        if (c()) {
            a(r2_Map_String__String, r6_Collection_Command);
            f();
            a((Map)r2_Map_String__String, r3j, r5_String);
        }
    }

    public void setDispatch(boolean r4z) {
        this.f = r4z ? new aj(this, e(), this.h) : new ad();
    }

    public void setThrottlingEnabled(boolean r1z) {
        this.c = r1z;
    }
}