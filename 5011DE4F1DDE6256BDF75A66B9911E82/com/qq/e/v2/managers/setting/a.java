package com.qq.e.v2.managers.setting;

import android.content.Context;
import android.util.Base64;
import android.util.Pair;
import com.qq.e.v2.constants.Constants.KEYS;
import com.qq.e.v2.constants.Constants.SETTING;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.GDTST;
import com.qq.e.v2.util.JsonUtil;
import com.qq.e.v2.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.widget.listview.XListViewFooter;

public class a {
    private Map<String, Object> a;
    private Map<String, Map<String, Object>> b;

    public a() {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
    }

    public a(String r7_String) {
        this();
        GDTLogger.d(new StringBuilder("Initialize GDTAPPSetting,Json=").append(r7_String).toString());
        if (!StringUtil.isEmpty(r7_String)) {
            try {
                JSONObject r1_JSONObject = new JSONObject(r7_String);
                if (r1_JSONObject.has(KEYS.PLACEMENTS)) {
                    JSONObject r2_JSONObject = r1_JSONObject.getJSONObject(KEYS.PLACEMENTS);
                    Iterator r3_Iterator = r2_JSONObject.keys();
                    while (r3_Iterator.hasNext()) {
                        String r0_String = (String) r3_Iterator.next();
                        Map r4_Map = new HashMap();
                        JsonUtil.jsonToMap(r2_JSONObject.getJSONObject(r0_String), r4_Map);
                        this.b.put(r0_String, r4_Map);
                    }
                    r1_JSONObject.remove(KEYS.PLACEMENTS);
                }
                JsonUtil.jsonToMap(r1_JSONObject, this.a);
            } catch (JSONException e) {
                GDTLogger.report("JsonException While build GDTAPPSetting Instance from JSON", e);
            }
        }
    }

    private static Pair<String, String> a_(Context r6_Context, String r7_String) {
        File r0_File = r6_Context.getDir(SETTING.SETTINGDIR, 0);
        if (!r0_File.exists()) {
            return null;
        }
        File r2_File = new File(r0_File, r7_String + ".sig");
        File r3_File = new File(r0_File, r7_String + ".cfg");
        if (!r2_File.exists() || !r3_File.exists()) {
            return null;
        }
        try {
            return new Pair(StringUtil.readAll(r2_File), StringUtil.readAll(r3_File));
        } catch (IOException e) {
            return null;
        }
    }

    public static com.qq.e.comm.a a_(Context r7_Context) {
        Pair r4_Pair = a(r7_Context, SETTING.DEV_CLOUD_SETTING);
        if (r4_Pair == null) {
            return null;
        }
        try {
            if (GDTST.getToolInstance().verifyString((String) r4_Pair.first, (String) r4_Pair.second)) {
                return new com.qq.e.comm.a((String) r4_Pair.first, new a(new String(Base64.decode((String) r4_Pair.second, 0), Base.UTF8)));
            }
            GDTLogger.e("verify local dev cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local dev cloud setting", th);
            return null;
        }
    }

    public static boolean a_(Context r1_Context, String r2_String, String r3_String) {
        return a(r1_Context, SETTING.SDK_CLOUD_SETTING, r2_String, r3_String);
    }

    private static boolean a_(Context r5_Context, String r6_String, String r7_String, String r8_String) {
        int r2i = XListViewFooter.STATE_NOMORE;
        Object[] r2_ObjectA;
        if (StringUtil.isEmpty(r7_String) || StringUtil.isEmpty(r8_String)) {
            r2_ObjectA = new Object[r2i];
            r2_ObjectA[0] = r6_String;
            r2_ObjectA[1] = r7_String;
            r2_ObjectA[2] = r8_String;
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig or setting is empty,name=%s\tsig=%s\tsetting=%s", r2_ObjectA));
            return false;
        } else {
            if (GDTST.getToolInstance().verifyString(r7_String, r8_String)) {
                return b(r5_Context, r6_String, r7_String, r8_String);
            }
            r2_ObjectA = new Object[r2i];
            r2_ObjectA[0] = r6_String;
            r2_ObjectA[1] = r7_String;
            r2_ObjectA[2] = r8_String;
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig verify fail,name=%s\tsig=%s\tsetting=%s", r2_ObjectA));
            return false;
        }
    }

    public static d b(Context r7_Context) {
        Pair r4_Pair = a(r7_Context, SETTING.SDK_CLOUD_SETTING);
        if (r4_Pair == null) {
            return null;
        }
        try {
            if (GDTST.getToolInstance().verifyString((String) r4_Pair.first, (String) r4_Pair.second)) {
                return new d(new b(new String(Base64.decode((String) r4_Pair.second, 0), Base.UTF8)), (byte) 0);
            }
            GDTLogger.e("verify local sdk cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local sdk cloud setting", th);
            return null;
        }
    }

    public static boolean b(Context r1_Context, String r2_String, String r3_String) {
        return a(r1_Context, SETTING.DEV_CLOUD_SETTING, r2_String, r3_String);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(Context r7_Context, String r8_String, String r9_String, String r10_String) {
        /*
        r2 = 0;
        r0 = 0;
        r1 = "e_qq_com_setting";
        r1 = r7.getDir(r1, r0);
        r3 = r1.exists();
        if (r3 != 0) goto L_0x0011;
    L_0x000e:
        r1.mkdirs();
    L_0x0011:
        r4 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r8);
        r5 = ".cfg";
        r3 = r3.append(r5);
        r3 = r3.toString();
        r4.<init>(r1, r3);
        r5 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r8);
        r6 = ".sig";
        r3 = r3.append(r6);
        r3 = r3.toString();
        r5.<init>(r1, r3);
        r3 = new java.io.FileWriter;	 //Catch:{ Exception -> 0x0059, all -> 0x006e }
        r3.<init>(r4);	 //Catch:{ Exception -> 0x0059, all -> 0x006e }
        r3.write(r10);	 //Catch:{ Exception -> 0x0086, all -> 0x007d }
        r1 = new java.io.FileWriter;	 //Catch:{ Exception -> 0x0086, all -> 0x007d }
        r1.<init>(r5);	 //Catch:{ Exception -> 0x0086, all -> 0x007d }
        r1.write(r9);	 //Catch:{ Exception -> 0x008a, all -> 0x007f }
        r3.close();	 //Catch:{ Exception -> 0x008d }
        r1.close();	 //Catch:{ Exception -> 0x008d }
    L_0x0057:
        r0 = 1;
    L_0x0058:
        return r0;
    L_0x0059:
        r1 = move-exception;
        r1 = r2;
    L_0x005b:
        r4.delete();	 //Catch:{ all -> 0x0082 }
        r5.delete();	 //Catch:{ all -> 0x0082 }
        if (r2 == 0) goto L_0x0066;
    L_0x0063:
        r2.close();	 //Catch:{ Exception -> 0x006c }
    L_0x0066:
        if (r1 == 0) goto L_0x0058;
    L_0x0068:
        r1.close();	 //Catch:{ Exception -> 0x006c }
        goto L_0x0058;
    L_0x006c:
        r1 = move-exception;
        goto L_0x0058;
    L_0x006e:
        r0 = move-exception;
        r3 = r2;
    L_0x0070:
        if (r3 == 0) goto L_0x0075;
    L_0x0072:
        r3.close();	 //Catch:{ Exception -> 0x007b }
    L_0x0075:
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.close();	 //Catch:{ Exception -> 0x007b }
    L_0x007a:
        throw r0;
    L_0x007b:
        r1 = move-exception;
        goto L_0x007a;
    L_0x007d:
        r0 = move-exception;
        goto L_0x0070;
    L_0x007f:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0070;
    L_0x0082:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x0070;
    L_0x0086:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x005b;
    L_0x008a:
        r2 = move-exception;
        r2 = r3;
        goto L_0x005b;
    L_0x008d:
        r0 = move-exception;
        goto L_0x0057;
        */

    }

    final Object a_(String r2_String) {
        return this.a.get(r2_String);
    }

    final Object a_(String r2_String, String r3_String) {
        return this.b.containsKey(r3_String) ? ((Map) this.b.get(r3_String)).get(r2_String) : null;
    }

    final void a_(String r2_String, Object r3_Object) {
        this.a.put(r2_String, r3_Object);
    }

    final void a_(String r3_String, Object r4_Object, String r5_String) {
        if (!this.b.containsKey(r5_String)) {
            this.b.put(r5_String, new ConcurrentHashMap());
        }
        ((Map) this.b.get(r5_String)).put(r3_String, r4_Object);
    }
}