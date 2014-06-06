package com.baidu.kirin;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.baidu.kirin.b.a;
import com.baidu.kirin.c.c;
import com.baidu.kirin.d.d;
import com.baidu.kirin.objects.KirinCheckState;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import org.apache.cordova.Globalization;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.HttpUtils;

public class StatUpdateAgent {
    static HandlerThread a;
    private static Handler b;
    private static JSONObject c;
    private static JSONObject d;

    static {
        a = new HandlerThread("CheckUpdateManagerKirinAgent");
        c = null;
        d = null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static JSONObject a(Context r6_Context, String r7_String) {
        /*
        r1 = 0;
        r0 = com.baidu.kirin.b.a.a(r6);
        r0 = r0.a(r7);
        if (r0 != 0) goto L_0x000f;
    L_0x000b:
        r0 = com.baidu.kirin.KirinConfig.DEBUG_MODE;
        if (r0 == 0) goto L_0x0061;
    L_0x000f:
        r0 = "can update!";
        com.baidu.kirin.d.d.a(r0);
        r2 = new com.baidu.kirin.c.b;
        r0 = "/kirinsdk/updatequery";
        r2.<init>(r6, r0);
        r0 = "updateMoment";
        r2.a(r0, r7);
        r0 = r2.c();	 //Catch:{ Exception -> 0x0054 }
        r1 = com.baidu.kirin.KirinConfig.DEBUG_MODE;
        if (r1 == 0) goto L_0x0030;
    L_0x0028:
        r1 = r2.a();
        c = r1;
        d = r0;
    L_0x0030:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "updateResult is : ";
        r1 = r1.append(r3);
        r3 = r0.toString();
        r1 = r1.append(r3);
        r1 = r1.toString();
        com.baidu.kirin.d.d.a(r1);
        r1 = "returncode";
        r2 = r2.d();	 //Catch:{ JSONException -> 0x005c }
        r0.put(r1, r2);	 //Catch:{ JSONException -> 0x005c }
    L_0x0053:
        return r0;
    L_0x0054:
        r0 = move-exception;
        r0 = "send update query error!!";
        com.baidu.kirin.d.d.c(r0);
        r0 = r1;
        goto L_0x0053;
    L_0x005c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0053;
    L_0x0061:
        r0 = "can not update";
        com.baidu.kirin.d.d.a(r0);
        r0 = new org.json.JSONObject;	 //Catch:{ JSONException -> 0x00a5 }
        r0.<init>();	 //Catch:{ JSONException -> 0x00a5 }
        r1 = "need_update";
        r2 = "0";
        r0.put(r1, r2);	 //Catch:{ JSONException -> 0x00a0 }
        r1 = "returncode";
        r2 = 0;
        r0.put(r1, r2);	 //Catch:{ JSONException -> 0x00a0 }
        r1 = com.baidu.kirin.KirinConfig.DEBUG_MODE;	 //Catch:{ JSONException -> 0x00a0 }
        if (r1 == 0) goto L_0x0053;
    L_0x007c:
        r1 = new org.json.JSONObject;	 //Catch:{ JSONException -> 0x00a0 }
        r1.<init>();	 //Catch:{ JSONException -> 0x00a0 }
        c = r1;	 //Catch:{ JSONException -> 0x00a0 }
        r1 = c;	 //Catch:{ JSONException -> 0x00a0 }
        r2 = "Send";
        r3 = new java.lang.StringBuilder;	 //Catch:{ JSONException -> 0x00a0 }
        r3.<init>();	 //Catch:{ JSONException -> 0x00a0 }
        r4 = "didn't send request! at moment : ";
        r3 = r3.append(r4);	 //Catch:{ JSONException -> 0x00a0 }
        r3 = r3.append(r7);	 //Catch:{ JSONException -> 0x00a0 }
        r3 = r3.toString();	 //Catch:{ JSONException -> 0x00a0 }
        r1.put(r2, r3);	 //Catch:{ JSONException -> 0x00a0 }
        d = r0;	 //Catch:{ JSONException -> 0x00a0 }
        goto L_0x0053;
    L_0x00a0:
        r1 = move-exception;
    L_0x00a1:
        r1.printStackTrace();
        goto L_0x0053;
    L_0x00a5:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x00a1;
        */

    }

    private static void a() {
        if (!a.isAlive()) {
            a.start();
            b = new Handler(a.getLooper());
        }
        if (b == null) {
            b = new Handler(a.getLooper());
        }
    }

    private static boolean a(JSONObject r2_JSONObject, HashMap<String, String> r3_HashMap_String__String) {
        try {
            r3_HashMap_String__String.put("updatetype", r2_JSONObject.getString("updatetype"));
            r3_HashMap_String__String.put("note", r2_JSONObject.getString("note"));
            r3_HashMap_String__String.put(Globalization.TIME, r2_JSONObject.getString(Globalization.TIME));
            r3_HashMap_String__String.put("appurl", r2_JSONObject.getString("appurl"));
            r3_HashMap_String__String.put("appname", r2_JSONObject.getString("appname"));
            r3_HashMap_String__String.put("version", r2_JSONObject.getString("version"));
            r3_HashMap_String__String.put("buildid", r2_JSONObject.getString("buildid"));
            r3_HashMap_String__String.put("attach", r2_JSONObject.getJSONArray("attach").toString());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void b(Context r4_Context, int r5i, PostChoiceListener r6_PostChoiceListener) {
        if (a.a(r4_Context).c()) {
            c r0_c = new c(r4_Context, KirinConfig.POST_CHOICE);
            r0_c.a("updateType", r5i + RContactStorage.PRIMARY_KEY);
            JSONObject r1_JSONObject = r0_c.c();
            if (r6_PostChoiceListener != null) {
                r6_PostChoiceListener.PostUpdateChoiceResponse(r1_JSONObject);
            }
            if (KirinConfig.DEBUG_MODE) {
                c = r0_c.a();
                d = r1_JSONObject;
            }
        }
    }

    private static void b(Context r5_Context, boolean r6z, CheckUpdateListener r7_CheckUpdateListener) {
        KirinCheckState r0_KirinCheckState;
        if (r7_CheckUpdateListener == null) {
        } else {
            JSONObject r0_JSONObject;
            HashMap r2_HashMap = new HashMap();
            KirinCheckState r1_KirinCheckState = KirinCheckState.ERROR_CHECK_VERSION;
            r0_JSONObject = r6z ? a(r5_Context, KirinConfig.ATSTART) : a(r5_Context, KirinConfig.ATSETTING);
            if (r0_JSONObject == null) {
                d.c("updateResult is null, net error!");
                r7_CheckUpdateListener.checkUpdateResponse(r1_KirinCheckState, new HashMap());
            } else {
                int r1i = r0_JSONObject.getInt("returncode");
                d.a("updateQuery's retCode is : " + r1i);
                if (r1i == 0) {
                    if (Integer.parseInt(r0_JSONObject.getString("need_update")) == 1) {
                        if (Integer.parseInt(r0_JSONObject.getString("buildid")) > com.baidu.kirin.a.a.d(r5_Context)) {
                            if (a(r0_JSONObject, r2_HashMap)) {
                                if (RContactStorage.PRIMARY_KEY.endsWith(r0_JSONObject.getString("appurl")) || r0_JSONObject.getString("appurl") == null) {
                                    d.c("appurl is null or appurl'size is 0!");
                                    r0_KirinCheckState = KirinCheckState.ALREADY_UP_TO_DATE;
                                    r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                                } else if (r0_JSONObject.getString("appurl").startsWith(HttpUtils.http)) {
                                    r0_KirinCheckState = KirinCheckState.NEWER_VERSION_FOUND;
                                    r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                                } else {
                                    d.c("appurl is not start with http://");
                                    r0_KirinCheckState = KirinCheckState.ERROR_CHECK_VERSION;
                                    r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                                }
                            } else {
                                r7_CheckUpdateListener.checkUpdateResponse(KirinCheckState.ALREADY_UP_TO_DATE, r2_HashMap);
                            }
                        } else {
                            r0_KirinCheckState = KirinCheckState.ALREADY_UP_TO_DATE;
                            r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                        }
                    } else {
                        r0_KirinCheckState = KirinCheckState.ALREADY_UP_TO_DATE;
                        r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                    }
                } else {
                    d.b("KirinSDK protocol error when mutual with backend");
                    r0_KirinCheckState = KirinCheckState.ALREADY_UP_TO_DATE;
                    r7_CheckUpdateListener.checkUpdateResponse(r0_KirinCheckState, r2_HashMap);
                }
            }
        }
    }

    public static void checkUpdate(Context r3_Context, boolean r4z, CheckUpdateListener r5_CheckUpdateListener) {
        a();
        if (r5_CheckUpdateListener == null) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "sdkstat";
            r0_ObjectA[1] = "The param of CheckUpdateListener is null, please new a instance of CheckUpdateListener";
            com.baidu.mobstat.a.c.c(r0_ObjectA);
        } else {
            b.post(new a(r3_Context, r4z, r5_CheckUpdateListener));
        }
    }

    public static void postUserChoice(Context r2_Context, int r3i, PostChoiceListener r4_PostChoiceListener) {
        a();
        b.post(new b(r2_Context, r3i, r4_PostChoiceListener));
    }

    public static void setTestMode() {
        KirinConfig.DEBUG_MODE = true;
        KirinConfig.DEFAULT_UPDATE_INTERVAL = 0;
    }
}