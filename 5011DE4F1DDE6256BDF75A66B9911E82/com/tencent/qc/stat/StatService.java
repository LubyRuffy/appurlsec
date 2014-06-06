package com.tencent.qc.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.StatLogger;
import com.tencent.qc.stat.common.StatPreferences;
import com.tencent.qc.stat.event.AdditionEvent;
import com.tencent.qc.stat.event.CustomEvent;
import com.tencent.qc.stat.event.Event;
import com.tencent.qc.stat.event.SessionEnv;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.nearby.ui.NearbySelectView;

// compiled from: ProGuard
public class StatService {
    private static Handler a;
    private static Map b;
    private static volatile long c;
    private static volatile long d;
    private static volatile int e;
    private static StatLogger f;

    static {
        b = new WeakHashMap();
        c = 0;
        d = 0;
        e = 0;
        f = StatCommonHelper.b();
    }

    static int a(Context r7_Context, boolean r8z) {
        long r0j = System.currentTimeMillis();
        if ((!r8z) || r0j - c < ((long) StatConfig.d())) {
            c = r0j;
            if (d != 0) {
                d = StatCommonHelper.c();
            }
            if (r0j >= d) {
                d = StatCommonHelper.c();
                if (StatStore.a(r7_Context).b(r7_Context).c() != 1) {
                    StatStore.a(r7_Context).b(r7_Context).a(1);
                }
                c(r7_Context);
            }
            if (e == 0) {
                c(r7_Context);
            }
            return e;
        } else {
            c(r7_Context);
            c = r0j;
            if (d != 0) {
                if (r0j >= d) {
                    if (e == 0) {
                        return e;
                    }
                    c(r7_Context);
                    return e;
                } else {
                    d = StatCommonHelper.c();
                    if (StatStore.a(r7_Context).b(r7_Context).c() != 1) {
                        c(r7_Context);
                        if (e == 0) {
                            c(r7_Context);
                        }
                        return e;
                    } else {
                        StatStore.a(r7_Context).b(r7_Context).a(1);
                        c(r7_Context);
                        if (e == 0) {
                            return e;
                        }
                        c(r7_Context);
                        return e;
                    }
                }
            } else {
                d = StatCommonHelper.c();
                if (r0j >= d) {
                    d = StatCommonHelper.c();
                    if (StatStore.a(r7_Context).b(r7_Context).c() != 1) {
                        StatStore.a(r7_Context).b(r7_Context).a(1);
                    }
                    c(r7_Context);
                }
                if (e == 0) {
                    c(r7_Context);
                }
                return e;
            }
        }
    }

    static JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            JSONObject r0_JSONObject = new JSONObject();
            if (StatConfig.b.d != 0) {
                r0_JSONObject.put("v", StatConfig.b.d);
            }
            r1_JSONObject.put(Integer.toString(StatConfig.b.a), r0_JSONObject);
            r0_JSONObject = new JSONObject();
            if (StatConfig.a.d != 0) {
                r0_JSONObject.put("v", StatConfig.a.d);
            }
            r1_JSONObject.put(Integer.toString(StatConfig.a.a), r0_JSONObject);
        } catch (JSONException e) {
            f.b(e);
        }
        return r1_JSONObject;
    }

    static void a(Context r2_Context) {
        if (r2_Context != null && a == null && b(r2_Context)) {
            HandlerThread r0_HandlerThread = new HandlerThread("StatService");
            r0_HandlerThread.start();
            l.a(r2_Context);
            a = new Handler(r0_HandlerThread.getLooper());
            StatStore.a(r2_Context);
            StatStore.a(r2_Context).b();
            if (StatConfig.a() == StatReportStrategy.d) {
                f.h("StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH");
                if (StatCommonHelper.h(r2_Context)) {
                    StatStore.a(r2_Context).a(-1);
                }
            }
        }
    }

    public static void a(Context r1_Context, String r2_String) {
        if (r2_String == null) {
            r2_String = RContactStorage.PRIMARY_KEY;
        }
        if (!StatConfig.d.equals(r2_String)) {
            StatConfig.d = r2_String;
            a(r1_Context, null);
        }
    }

    public static void a(Context r3_Context, String r4_String, String ... r5_StringA) {
        if (StatConfig.c()) {
            if (r3_Context == null) {
                f.e("The Context of StatService.trackCustomEvent() can not be null!");
            } else if (a(r4_String)) {
                f.e("The event_id of StatService.trackCustomEvent() can not be null or empty.");
            } else {
                Event r0_Event = new CustomEvent(r3_Context, a(r3_Context, false), r4_String);
                r0_Event.a(r5_StringA);
                if (d(r3_Context) != null) {
                    d(r3_Context).post(new t(r0_Event));
                }
            }
        }
    }

    static void a(Context r3_Context, Map r4_Map) {
        if (StatConfig.c()) {
            if (r3_Context == null) {
                f.e("The Context of StatService.sendAdditionEvent() can not be null!");
            } else {
                Event r0_Event = new AdditionEvent(r3_Context, a(r3_Context, false), r4_Map);
                if (d(r3_Context) != null) {
                    d(r3_Context).post(new t(r0_Event));
                }
            }
        }
    }

    static boolean a(String r1_String) {
        return r1_String == null || r1_String.length() == 0;
    }

    public static void b(String r2_String) {
        StatConfig.a("Aqc" + r2_String);
        StatConfig.b("QQConnect");
        StatConfig.c(false);
        StatConfig.b(true);
        StatConfig.a(1800000);
        StatConfig.b((int)NearbySelectView.TIME_1DAY);
        StatConfig.a(StatReportStrategy.f);
    }

    static boolean b(Context r5_Context) {
        if (StatCommonHelper.b("0.6.12") > StatPreferences.a(r5_Context, StatConfig.c, 0)) {
            return true;
        }
        StatConfig.a(false);
        return false;
    }

    static void c(Context r5_Context) {
        e = StatCommonHelper.a();
        if (d(r5_Context) != null) {
            d(r5_Context).post(new t(new SessionEnv(r5_Context, e, a())));
        }
    }

    private static Handler d(Context r1_Context) {
        a(r1_Context);
        return a;
    }
}