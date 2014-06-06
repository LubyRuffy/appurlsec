package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.b;
import com.baidu.mobstat.a.c;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.audit.Rotate3dAnimation;
import qsbk.app.widget.listview.XListViewHeader;

public class DataCore {
    private static JSONObject b;
    private static DataCore h;
    private int a;
    private JSONArray c;
    private JSONArray d;
    private JSONArray e;
    private JSONArray f;
    private boolean g;

    static {
        b = new JSONObject();
        h = new DataCore();
    }

    private DataCore() {
        this.a = 0;
        this.c = new JSONArray();
        this.d = new JSONArray();
        this.e = new JSONArray();
        this.f = new JSONArray();
        this.g = false;
    }

    private void a(boolean r1z) {
        this.g = r1z;
    }

    private boolean b() {
        return this.g;
    }

    public static DataCore getInstance() {
        return h;
    }

    public void flush(Context r6_Context) {
        c.a("stat", "flush cache to __local_stat_cache.json");
        JSONObject r1_JSONObject = new JSONObject();
        try {
            synchronized (this.c) {
                r1_JSONObject.put("pr", new JSONArray(this.c.toString()));
            }
            synchronized (this.d) {
                r1_JSONObject.put("ev", new JSONArray(this.d.toString()));
            }
            synchronized (this.f) {
                r1_JSONObject.put("ex", new JSONArray(this.f.toString()));
            }
        } catch (JSONException e) {
            c.a("stat", "flushLogWithoutHeader() construct cache error");
        } catch (Throwable th) {
            while (true) {
            }
        }
        String r0_String = "{}";
        r0_String = r1_JSONObject.toString();
        if (b()) {
            c.a("stat", "cache.json exceed 204800B,stop flush.");
        } else {
            int r1i = r0_String.getBytes().length;
            if (r1i >= 204800) {
                a(true);
            } else {
                this.a = r1i;
                c.a("stat", "flush:cacheFileSize is:" + this.a);
                b.a(false, r6_Context, "__local_stat_cache.json", r0_String, false);
            }
        }
    }

    public void getMemInfo(Context r1_Context) {
    }

    public synchronized void installHeader(Context r2_Context) {
        CooperService.a().b(r2_Context);
    }

    public boolean isPartEmpty() {
        return this.c.length() == 0 && this.d.length() == 0 && this.f.length() == 0;
    }

    public void loadLastSession(Context r5_Context) {
        c.a("stat", "LoadLastSession()");
        if (r5_Context != null && b.c(r5_Context, "__local_last_session.json")) {
            String r0_String = b.a(false, r5_Context, "__local_last_session.json");
            if (r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                c.a("stat", "loadLastSession(): last_session.json file not found.");
            } else {
                b.a(false, r5_Context, "__local_last_session.json", new JSONObject().toString(), false);
                putSession(r0_String);
                flush(r5_Context);
            }
        }
    }

    public void loadStatData(Context r12_Context) {
        boolean r0z = false;
        if (r12_Context != null && b.c(r12_Context, "__local_stat_cache.json")) {
            String r1_String = b.a(false, r12_Context, "__local_stat_cache.json");
            if (r1_String.equals(RContactStorage.PRIMARY_KEY)) {
                c.a("stat", "stat_cache file not found.");
            } else {
                c.a("stat", "loadStatData, ");
                JSONObject r6_JSONObject;
                try {
                    JSONObject r6_JSONObject_2;
                    this.a = r1_String.getBytes().length;
                    c.a("stat", "load Stat Data:cacheFileSize is:" + this.a);
                    JSONObject r2_JSONObject = new JSONObject(r1_String);
                    c.a("stat", "Load cache:" + r1_String);
                    long r3j = System.currentTimeMillis();
                    JSONArray r5_JSONArray = r2_JSONObject.getJSONArray("pr");
                    int r1i = 0;
                    while (r1i < r5_JSONArray.length()) {
                        r6_JSONObject_2 = r5_JSONArray.getJSONObject(r1i);
                        if (r3j - r6_JSONObject_2.getLong("s") > 604800000) {
                            r1i++;
                        } else {
                            putSession(r6_JSONObject_2, true);
                            r1i++;
                        }
                    }
                    r5_JSONArray = r2_JSONObject.getJSONArray("ev");
                    r1i = 0;
                    while (r1i < r5_JSONArray.length()) {
                        r6_JSONObject = r5_JSONArray.getJSONObject(r1i);
                        if (r3j - r6_JSONObject.getLong(QsbkDatabase.T) > 604800000) {
                            r1i++;
                        } else {
                            putEvent(r6_JSONObject, true);
                            r1i++;
                        }
                    }
                    JSONArray r1_JSONArray = r2_JSONObject.getJSONArray("ex");
                    while (r0z < r1_JSONArray.length()) {
                        r2_JSONObject = r1_JSONArray.getJSONObject(r0z);
                        if (r3j - r2_JSONObject.getLong(QsbkDatabase.T) > 604800000) {
                            r0z++;
                        } else {
                            putException(r2_JSONObject, true);
                            r0z++;
                        }
                    }
                } catch (JSONException e) {
                    c.a("stat", "Load stat data error:" + e);
                }
            }
        }
    }

    public void putEvent(String r5_String, String r6_String, int r7i, long r8j, long r10j) {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put("i", r5_String);
            r0_JSONObject.put("l", r6_String);
            r0_JSONObject.put(KEYS.CTXTSETTING, r7i);
            r0_JSONObject.put(QsbkDatabase.T, r8j);
            r0_JSONObject.put("d", r10j);
            putEvent(r0_JSONObject, false);
            c.a("stat", "put event:" + r0_JSONObject.toString());
        } catch (JSONException e) {
            c.a("stat", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putEvent(JSONObject r18_JSONObject, boolean r19z) {
        /*
        r17_this = this;
        if (r18 == 0) goto L_0x0036;
    L_0x0002:
        if (r19 != 0) goto L_0x0036;
    L_0x0004:
        r1 = r18.toString();
        r1 = r1.getBytes();
        r1 = r1.length;
        r2 = 3;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = "stat";
        r2[r3] = r4;
        r3 = 1;
        r4 = "putEvent:eventSize is:";
        r2[r3] = r4;
        r3 = 2;
        r4 = java.lang.Integer.valueOf(r1);
        r2[r3] = r4;
        com.baidu.mobstat.a.c.a(r2);
        r0 = r17;
        r2 = r0.a;
        r1 = r1 + r2;
        r2 = 204800; // 0x32000 float:2.86986E-40 double:1.011846E-318;
        if (r1 <= r2) goto L_0x0036;
    L_0x002e:
        r1 = "stat";
        r2 = "putEvent: size is full!";
        com.baidu.mobstat.a.c.a(r1, r2);
    L_0x0035:
        return;
    L_0x0036:
        r1 = 0;
        r2 = "i";
        r0 = r18;
        r5 = r0.getString(r2);	 //Catch:{ JSONException -> 0x00d4 }
        r2 = "l";
        r0 = r18;
        r6 = r0.getString(r2);	 //Catch:{ JSONException -> 0x00d4 }
        r2 = "t";
        r0 = r18;
        r2 = r0.getLong(r2);	 //Catch:{ JSONException -> 0x00d4 }
        r7 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r7 = r2 / r7;
        r2 = "s";
        r0 = r18;
        r2 = r0.optString(r2);	 //Catch:{ JSONException -> 0x00d4 }
        r3 = "d";
        r0 = r18;
        r1 = r0.getInt(r3);	 //Catch:{ JSONException -> 0x00dc }
    L_0x0064:
        if (r1 != 0) goto L_0x0197;
    L_0x0066:
        r0 = r17;
        r9 = r0.d;
        monitor-enter(r9);
        r0 = r17;
        r1 = r0.d;	 //Catch:{ all -> 0x00ee }
        r4 = r1.length();	 //Catch:{ all -> 0x00ee }
        if (r2 == 0) goto L_0x007d;
    L_0x0075:
        r1 = "";
        r1 = r2.equals(r1);	 //Catch:{ JSONException -> 0x00e5 }
        if (r1 == 0) goto L_0x0086;
    L_0x007d:
        r1 = "s";
        r2 = "0|";
        r0 = r18;
        r0.put(r1, r2);	 //Catch:{ JSONException -> 0x00e5 }
    L_0x0086:
        r3 = 0;
        r1 = r4;
    L_0x0088:
        if (r3 >= r4) goto L_0x01c8;
    L_0x008a:
        r0 = r17;
        r2 = r0.d;	 //Catch:{ JSONException -> 0x00fa }
        r10 = r2.getJSONObject(r3);	 //Catch:{ JSONException -> 0x00fa }
        r2 = "stat";
        r11 = new java.lang.StringBuilder;	 //Catch:{ JSONException -> 0x00fa }
        r11.<init>();	 //Catch:{ JSONException -> 0x00fa }
        r12 = ";event_iter=";
        r11 = r11.append(r12);	 //Catch:{ JSONException -> 0x00fa }
        r12 = r10.toString();	 //Catch:{ JSONException -> 0x00fa }
        r11 = r11.append(r12);	 //Catch:{ JSONException -> 0x00fa }
        r11 = r11.toString();	 //Catch:{ JSONException -> 0x00fa }
        com.baidu.mobstat.a.c.a(r2, r11);	 //Catch:{ JSONException -> 0x00fa }
        r2 = "i";
        r11 = r10.getString(r2);	 //Catch:{ JSONException -> 0x00fa }
        r2 = "l";
        r12 = r10.getString(r2);	 //Catch:{ JSONException -> 0x00fa }
        r2 = "t";
        r13 = r10.getLong(r2);	 //Catch:{ JSONException -> 0x00fa }
        r15 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r13 = r13 / r15;
        r2 = 0;
        r15 = "d";
        r2 = r10.getInt(r15);	 //Catch:{ JSONException -> 0x00f1 }
    L_0x00cb:
        r13 = (r13 > r7 ? 1 : (r13 == r7? 0 : -1));
        if (r13 != 0) goto L_0x00d1;
    L_0x00cf:
        if (r2 == 0) goto L_0x0101;
    L_0x00d1:
        r3 = r3 + 1;
        goto L_0x0088;
    L_0x00d4:
        r1 = move-exception;
        r2 = "stat";
        com.baidu.mobstat.a.c.a(r2, r1);
        goto L_0x0035;
    L_0x00dc:
        r3 = move-exception;
        r3 = "stat";
        r4 = "old version data, No duration Tag";
        com.baidu.mobstat.a.c.a(r3, r4);
        goto L_0x0064;
    L_0x00e5:
        r1 = move-exception;
        r1 = "stat";
        r2 = "event put s fail";
        com.baidu.mobstat.a.c.a(r1, r2);	 //Catch:{ all -> 0x00ee }
        goto L_0x0086;
    L_0x00ee:
        r1 = move-exception;
        monitor-exit(r9);	 //Catch:{ all -> 0x00ee }
        throw r1;
    L_0x00f1:
        r15 = move-exception;
        r15 = "stat";
        r16 = "old version data, No duration Tag";
        com.baidu.mobstat.a.c.a(r15, r16);	 //Catch:{ JSONException -> 0x00fa }
        goto L_0x00cb;
    L_0x00fa:
        r2 = move-exception;
    L_0x00fb:
        r10 = "stat";
        com.baidu.mobstat.a.c.a(r10, r2);	 //Catch:{ all -> 0x00ee }
        goto L_0x00d1;
    L_0x0101:
        r2 = "stat";
        r13 = new java.lang.StringBuilder;	 //Catch:{ JSONException -> 0x00fa }
        r13.<init>();	 //Catch:{ JSONException -> 0x00fa }
        r14 = ";event_iter=";
        r13 = r13.append(r14);	 //Catch:{ JSONException -> 0x00fa }
        r14 = r10.toString();	 //Catch:{ JSONException -> 0x00fa }
        r13 = r13.append(r14);	 //Catch:{ JSONException -> 0x00fa }
        r13 = r13.toString();	 //Catch:{ JSONException -> 0x00fa }
        com.baidu.mobstat.a.c.a(r2, r13);	 //Catch:{ JSONException -> 0x00fa }
        r2 = r11.equals(r5);	 //Catch:{ JSONException -> 0x00fa }
        if (r2 == 0) goto L_0x00d1;
    L_0x0123:
        r2 = r12.equals(r6);	 //Catch:{ JSONException -> 0x00fa }
        if (r2 == 0) goto L_0x00d1;
    L_0x0129:
        r2 = "c";
        r0 = r18;
        r2 = r0.getInt(r2);	 //Catch:{ JSONException -> 0x00fa }
        r11 = "c";
        r11 = r10.getInt(r11);	 //Catch:{ JSONException -> 0x00fa }
        r11 = r11 + r2;
        r2 = "s";
        r2 = r10.optString(r2);	 //Catch:{ JSONException -> 0x00fa }
        if (r2 == 0) goto L_0x0148;
    L_0x0140:
        r12 = "";
        r12 = r2.equalsIgnoreCase(r12);	 //Catch:{ JSONException -> 0x00fa }
        if (r12 == 0) goto L_0x014a;
    L_0x0148:
        r2 = "0|";
    L_0x014a:
        r12 = "t";
        r0 = r18;
        r12 = r0.getLong(r12);	 //Catch:{ JSONException -> 0x00fa }
        r14 = "t";
        r14 = r10.getLong(r14);	 //Catch:{ JSONException -> 0x00fa }
        r12 = r12 - r14;
        r14 = new java.lang.StringBuilder;	 //Catch:{ JSONException -> 0x00fa }
        r14.<init>();	 //Catch:{ JSONException -> 0x00fa }
        r2 = r14.append(r2);	 //Catch:{ JSONException -> 0x00fa }
        r2 = r2.append(r12);	 //Catch:{ JSONException -> 0x00fa }
        r12 = "|";
        r2 = r2.append(r12);	 //Catch:{ JSONException -> 0x00fa }
        r1 = r2.toString();	 //Catch:{ JSONException -> 0x00fa }
        r2 = "c";
        r10.remove(r2);	 //Catch:{ JSONException -> 0x01c3 }
        r2 = "c";
        r10.put(r2, r11);	 //Catch:{ JSONException -> 0x01c3 }
        r2 = "s";
        r10.put(r2, r1);	 //Catch:{ JSONException -> 0x01c3 }
    L_0x017f:
        if (r3 >= r4) goto L_0x0184;
    L_0x0181:
        monitor-exit(r9);	 //Catch:{ all -> 0x00ee }
        goto L_0x0035;
    L_0x0184:
        r0 = r17;
        r1 = r0.d;	 //Catch:{ JSONException -> 0x0190 }
        r0 = r18;
        r1.put(r4, r0);	 //Catch:{ JSONException -> 0x0190 }
    L_0x018d:
        monitor-exit(r9);	 //Catch:{ all -> 0x00ee }
        goto L_0x0035;
    L_0x0190:
        r1 = move-exception;
        r2 = "stat";
        com.baidu.mobstat.a.c.a(r2, r1);	 //Catch:{ all -> 0x00ee }
        goto L_0x018d;
    L_0x0197:
        r0 = r17;
        r2 = r0.d;
        monitor-enter(r2);
        r0 = r17;
        r1 = r0.d;	 //Catch:{ all -> 0x01b9 }
        r1 = r1.length();	 //Catch:{ all -> 0x01b9 }
        r3 = "s";
        r4 = "0";
        r0 = r18;
        r0.put(r3, r4);	 //Catch:{ JSONException -> 0x01bc }
        r0 = r17;
        r3 = r0.d;	 //Catch:{ JSONException -> 0x01bc }
        r0 = r18;
        r3.put(r1, r0);	 //Catch:{ JSONException -> 0x01bc }
    L_0x01b6:
        monitor-exit(r2);	 //Catch:{ all -> 0x01b9 }
        goto L_0x0035;
    L_0x01b9:
        r1 = move-exception;
        monitor-exit(r2);	 //Catch:{ all -> 0x01b9 }
        throw r1;
    L_0x01bc:
        r1 = move-exception;
        r3 = "stat";
        com.baidu.mobstat.a.c.a(r3, r1);	 //Catch:{ all -> 0x01b9 }
        goto L_0x01b6;
    L_0x01c3:
        r1 = move-exception;
        r2 = r1;
        r1 = r3;
        goto L_0x00fb;
    L_0x01c8:
        r3 = r1;
        goto L_0x017f;
        */

    }

    public void putException(long r7j, String r9_String, String r10_String) {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(QsbkDatabase.T, r7j);
            r1_JSONObject.put(Rotate3dAnimation.ROTATE_Y, r10_String);
            if (r9_String.getBytes().length > 5120) {
                byte[] r0_byteA = new byte[5120];
                r9_String.getBytes(0, 5120, r0_byteA, 0);
                c.a("exception bytes=" + r0_byteA.length);
                r1_JSONObject.put(KEYS.CTXTSETTING, new String(r0_byteA));
                putException(r1_JSONObject, false);
            } else {
                r1_JSONObject.put(KEYS.CTXTSETTING, r9_String);
                putException(r1_JSONObject, false);
            }
        } catch (JSONException e) {
            c.a("stat", e);
        }
    }

    public void putException(JSONObject r5_JSONObject, boolean r6z) {
        if (r5_JSONObject == null || r6z) {
            synchronized (this.f) {
                try {
                    this.f.put(this.f.length(), r5_JSONObject);
                } catch (JSONException e) {
                    c.a("stat", e);
                }
            }
        } else {
            int r0i = r5_JSONObject.toString().getBytes().length;
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "stat";
            r1_ObjectA[1] = "putException:addSize is:";
            r1_ObjectA[2] = Integer.valueOf(r0i);
            c.a(r1_ObjectA);
            if (r0i + this.a > 204800) {
                c.a("stat", "putException: size is full!");
                return;
            }
            synchronized (this.f) {
                this.f.put(this.f.length(), r5_JSONObject);
            }
        }
    }

    public void putSession(String r5_String) {
        if (r5_String.equals("{}") || r5_String.equals(RContactStorage.PRIMARY_KEY)) {
        } else {
            try {
                JSONObject r0_JSONObject = new JSONObject(r5_String);
                putSession(r0_JSONObject, false);
                c.a("stat", "Load last session:" + r0_JSONObject);
            } catch (JSONException e) {
                c.a("stat", "putSession()" + e);
            }
        }
    }

    public void putSession(JSONObject r5_JSONObject, boolean r6z) {
        if (r5_JSONObject == null || r6z) {
            synchronized (this.c) {
                try {
                    this.c.put(this.c.length(), r5_JSONObject);
                } catch (JSONException e) {
                    c.a("stat", e);
                }
            }
        } else {
            int r0i = r5_JSONObject.toString().getBytes().length;
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[0] = "stat";
            r1_ObjectA[1] = "putSession:addSize is:";
            r1_ObjectA[2] = Integer.valueOf(r0i);
            c.a(r1_ObjectA);
            if (r0i + this.a > 204800) {
                c.a("stat", "putSession: size is full!");
                return;
            }
            synchronized (this.c) {
                this.c.put(this.c.length(), r5_JSONObject);
            }
        }
    }

    public boolean sendLogData(Context r13_Context) {
        boolean r0z = false;
        c.a("stat", "sendLogData() begin.");
        JSONObject r2_JSONObject;
        String r2_String;
        if (CooperService.a() != null) {
            if (CooperService.a().c == null || RContactStorage.PRIMARY_KEY.equalsIgnoreCase(CooperService.a().c)) {
                CooperService.a().a(r13_Context);
                if (CooperService.a().c == null || RContactStorage.PRIMARY_KEY.equalsIgnoreCase(CooperService.a().c)) {
                    c.c("\u4e0d\u80fd\u5728manifest.xml\u4e2d\u627e\u5230APP Key||can't find app key in manifest.xml.");
                } else {
                    r2_JSONObject = new JSONObject();
                    synchronized (b) {
                        try {
                            b.put(QsbkDatabase.T, System.currentTimeMillis());
                            b.put("ss", q.b().d());
                            r2_JSONObject.put("he", b);
                            synchronized (this.c) {
                                try {
                                    r2_JSONObject.put("pr", this.c);
                                    synchronized (this.d) {
                                        try {
                                            r2_JSONObject.put("ev", this.d);
                                            synchronized (this.e) {
                                                try {
                                                    synchronized (this.f) {
                                                        try {
                                                            r2_JSONObject.put("ex", this.f);
                                                            r2_String = r2_JSONObject.toString();
                                                            c.a("stat", "---Send Data Is:" + r2_String);
                                                            x.a(r13_Context, "http://hmma.baidu.com/app.gif", r2_String, 50000, 50000);
                                                            c.a("stat", "sendLogData() send_sucess.data=" + r2_String);
                                                            r0z = true;
                                                            c.a("stat", "send log data over. result=" + r0z + "data=" + r2_String);
                                                            if (r0z) {
                                                                a(false);
                                                                this.f = new JSONArray();
                                                                this.d = new JSONArray();
                                                                this.c = new JSONArray();
                                                                flush(r13_Context);
                                                                q.b().c();
                                                                k.a().c(r13_Context);
                                                            }
                                                        } catch (JSONException e) {
                                                            c.a("stat", e);
                                                        }
                                                    }
                                                } catch (Throwable th) {
                                                }
                                            }
                                        } catch (JSONException e_2) {
                                            c.a("stat", e_2);
                                        }
                                    }
                                } catch (JSONException e_3) {
                                    c.a("stat", e_3.toString());
                                }
                            }
                        } catch (JSONException e_4) {
                            c.a("stat", e_4);
                        }
                    }
                    c.a("stat", "sendLogData() end.");
                }
            } else {
                r2_JSONObject = new JSONObject();
                synchronized (b) {
                    b.put(QsbkDatabase.T, System.currentTimeMillis());
                    b.put("ss", q.b().d());
                    r2_JSONObject.put("he", b);
                    synchronized (this.c) {
                        r2_JSONObject.put("pr", this.c);
                        synchronized (this.d) {
                            r2_JSONObject.put("ev", this.d);
                            synchronized (this.e) {
                                synchronized (this.f) {
                                    r2_JSONObject.put("ex", this.f);
                                    r2_String = r2_JSONObject.toString();
                                    c.a("stat", "---Send Data Is:" + r2_String);
                                    try {
                                        x.a(r13_Context, "http://hmma.baidu.com/app.gif", r2_String, 50000, 50000);
                                        c.a("stat", "sendLogData() send_sucess.data=" + r2_String);
                                        r0z = true;
                                    } catch (Exception e_5) {
                                        Exception r1_Exception = e_5;
                                        Object[] r8_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                                        r8_ObjectA[0] = "stat";
                                        r8_ObjectA[1] = "send error++++++" + r1_Exception;
                                        c.c(r8_ObjectA);
                                    }
                                    c.a("stat", "send log data over. result=" + r0z + "data=" + r2_String);
                                    if (r0z) {
                                    } else {
                                        a(false);
                                        this.f = new JSONArray();
                                        this.d = new JSONArray();
                                        this.c = new JSONArray();
                                        flush(r13_Context);
                                        q.b().c();
                                        k.a().c(r13_Context);
                                    }
                                }
                            }
                        }
                    }
                }
                c.a("stat", "sendLogData() end.");
            }
        } else {
            r2_JSONObject = new JSONObject();
            synchronized (b) {
                b.put(QsbkDatabase.T, System.currentTimeMillis());
                b.put("ss", q.b().d());
                r2_JSONObject.put("he", b);
                synchronized (this.c) {
                    r2_JSONObject.put("pr", this.c);
                    synchronized (this.d) {
                        r2_JSONObject.put("ev", this.d);
                        synchronized (this.e) {
                            synchronized (this.f) {
                                r2_JSONObject.put("ex", this.f);
                                r2_String = r2_JSONObject.toString();
                                c.a("stat", "---Send Data Is:" + r2_String);
                                x.a(r13_Context, "http://hmma.baidu.com/app.gif", r2_String, 50000, 50000);
                                c.a("stat", "sendLogData() send_sucess.data=" + r2_String);
                                r0z = true;
                                c.a("stat", "send log data over. result=" + r0z + "data=" + r2_String);
                                if (r0z) {
                                    a(false);
                                    this.f = new JSONArray();
                                    this.d = new JSONArray();
                                    this.c = new JSONArray();
                                    flush(r13_Context);
                                    q.b().c();
                                    k.a().c(r13_Context);
                                }
                            }
                        }
                    }
                }
            }
            c.a("stat", "sendLogData() end.");
        }
        return r0z;
    }

    public void setAppChannel(Context r5_Context, String r6_String, boolean r7z) {
        if (r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "sdkstat";
            r0_ObjectA[1] = "\u8bbe\u7f6e\u7684\u6e20\u9053\u4e0d\u80fd\u4e3a\u7a7a\u6216\u8005\u4e3anull || The channel that you have been set is null or empty, please check it.";
            c.c(r0_ObjectA);
            CooperService.a().j = r6_String;
            if ((!r7z) || r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
                if (r7z) {
                    BasicStoreTools.getInstance().c(r5_Context, RContactStorage.PRIMARY_KEY);
                    BasicStoreTools.getInstance().c(r5_Context, false);
                }
            } else {
                BasicStoreTools.getInstance().c(r5_Context, r6_String);
                BasicStoreTools.getInstance().c(r5_Context, true);
                if (r7z) {
                } else {
                    BasicStoreTools.getInstance().c(r5_Context, RContactStorage.PRIMARY_KEY);
                    BasicStoreTools.getInstance().c(r5_Context, false);
                }
            }
        } else {
            CooperService.a().j = r6_String;
            if (r7z || r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
                if (r7z) {
                    BasicStoreTools.getInstance().c(r5_Context, RContactStorage.PRIMARY_KEY);
                    BasicStoreTools.getInstance().c(r5_Context, false);
                }
            } else {
                BasicStoreTools.getInstance().c(r5_Context, r6_String);
                BasicStoreTools.getInstance().c(r5_Context, true);
                if (r7z) {
                } else {
                    BasicStoreTools.getInstance().c(r5_Context, RContactStorage.PRIMARY_KEY);
                    BasicStoreTools.getInstance().c(r5_Context, false);
                }
            }
        }
    }

    public void setAppChannel(String r4_String) {
        if (r4_String == null || r4_String.equals(RContactStorage.PRIMARY_KEY)) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "sdkstat";
            r0_ObjectA[1] = "\u8bbe\u7f6e\u7684\u6e20\u9053\u4e0d\u80fd\u4e3a\u7a7a\u6216\u8005\u4e3anull || The channel that you have been set is null or empty, please check it.";
            c.c(r0_ObjectA);
            CooperService.a().j = r4_String;
        } else {
            CooperService.a().j = r4_String;
        }
    }

    public void setAppKey(String r2_String) {
        CooperService.a().c = r2_String;
    }
}