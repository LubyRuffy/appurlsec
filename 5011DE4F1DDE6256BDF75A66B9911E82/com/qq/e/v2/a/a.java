package com.qq.e.v2.a;

import android.content.Context;
import com.qq.e.v2.constants.Constants;
import com.qq.e.v2.constants.Constants.KEYS;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.managers.plugin.PM;
import com.qq.e.v2.managers.setting.SM;
import com.qq.e.v2.managers.status.SDKStatus;
import com.qq.e.v2.net.GDTADNetClient;
import com.qq.e.v2.net.GDTADNetClient.Priority;
import com.qq.e.v2.net.GDTSecurityADNetRequest;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.JsonUtil;
import com.qq.e.v2.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

public final class a {
    private static final a a;
    private volatile Boolean b;

    static {
        a = new a();
    }

    public a() {
        this.b = Boolean.valueOf(false);
    }

    public static a a_() {
        return a;
    }

    public final void a_(Context r9_Context, long r10j) {
        if (this.b.booleanValue()) {
        } else {
            synchronized (this.b) {
                if (this.b.booleanValue()) {
                } else {
                    SM r2_SM = GDTADManager.getInstance().getSM();
                    PM r3_PM = GDTADManager.getInstance().getPM();
                    Map r0_Map = com.qq.e.comm.a.c();
                    r0_Map.put(KEYS.SIG, com.qq.e.comm.a.d());
                    r0_Map.put(KEYS.DEVINFO, com.qq.e.comm.a.e());
                    r0_Map.put(KEYS.APPINFO, com.qq.e.comm.a.f());
                    r0_Map.put(KEYS.CTXTSETTING, com.qq.e.comm.a.g());
                    String r4_String = KEYS.SDKINFO;
                    Map r5_Map = new HashMap();
                    r5_Map.put("sdkv", SDKStatus.SDKV);
                    r5_Map.put("jsv", SDKStatus.JSV);
                    r0_Map.put(r4_String, r5_Map);
                    Map r4_Map = new HashMap();
                    r5_Map = new HashMap();
                    r4_Map.put("performance", r5_Map);
                    if (r9_Context != null) {
                        r5_Map.putAll(r9_Context.getSharedPreferences(Constants.SP_KEY, 0).getAll());
                    }
                    r0_Map.put(KEYS.BIZ, r4_Map);
                    r5_Map.put("sdk_init_time", Long.valueOf(System.currentTimeMillis() - r10j));
                    r4_String = JsonUtil.mapToJson(r0_Map).toString();
                    GDTLogger.d(new StringBuilder("RequestInfo=").append(r4_String).toString());
                    String r0_String = Constants.ACT_URL;
                    if (!StringUtil.isEmpty(r2_SM.getSuid())) {
                        r0_String = Constants.LAC_URL;
                    }
                    GDTADNetClient.getInstance().excute(new GDTSecurityADNetRequest(r0_String, r4_String, new b(this, r2_SM, r3_PM)), Priority.High);
                    this.b = Boolean.valueOf(true);
                }
            }
        }
    }
}