package com.baidu.mobstat;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.baidu.mobstat.a.c;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Timer;
import org.apache.cordova.NetworkManager;

class k {
    private static HandlerThread a;
    private static Handler i;
    private static k j;
    private boolean b;
    private SendStrategyEnum c;
    private int d;
    private Timer e;
    private int f;
    private boolean g;
    private WeakReference<Context> h;

    static {
        a = new HandlerThread("LogSenderThread");
        j = new k();
    }

    private k() {
        this.b = false;
        this.c = SendStrategyEnum.APP_START;
        this.d = 1;
        this.f = 0;
        this.g = false;
        a.start();
        i = new Handler(a.getLooper());
    }

    public static k a() {
        return j;
    }

    private void e(Context r4_Context) {
        if (r4_Context == null) {
            c.a("sdkstat", "initContext context=" + null);
        }
        if (this.h != null || r4_Context == null) {
        } else {
            this.h = new WeakReference(r4_Context);
        }
    }

    public void a(int r2i) {
        if (r2i < 0 || r2i > 30) {
        } else {
            this.f = r2i;
        }
    }

    public void a(Context r6_Context) {
        String r1_String;
        e(r6_Context);
        SendStrategyEnum r0_SendStrategyEnum = SendStrategyEnum.APP_START;
        try {
            r1_String = x.a(r6_Context, "BaiduMobAd_EXCEPTION_LOG");
            if (!r1_String.equals(RContactStorage.PRIMARY_KEY)) {
                if (r1_String.equals("true")) {
                    i.a().a(r6_Context);
                    BasicStoreTools.getInstance().a(r6_Context, true);
                } else if (r1_String.equals("false")) {
                    BasicStoreTools.getInstance().a(r6_Context, false);
                }
            }
        } catch (Exception e) {
            c.a(e);
        }
        try {
            r1_String = x.a(r6_Context, "BaiduMobAd_SEND_STRATEGY");
            if (!r1_String.equals(RContactStorage.PRIMARY_KEY)) {
                if (r1_String.equals(SendStrategyEnum.APP_START.name())) {
                    r0_SendStrategyEnum = SendStrategyEnum.APP_START;
                    BasicStoreTools.getInstance().a(r6_Context, r0_SendStrategyEnum.ordinal());
                } else if (r1_String.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    r0_SendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    BasicStoreTools.getInstance().a(r6_Context, r0_SendStrategyEnum.ordinal());
                    BasicStoreTools.getInstance().b(r6_Context, (int)AdViewUtil.NETWORK_TYPE_CASEE);
                } else if (r1_String.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    r0_SendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    BasicStoreTools.getInstance().a(r6_Context, r0_SendStrategyEnum.ordinal());
                }
            }
        } catch (Exception e_2) {
            c.a(e_2);
            r0_SendStrategyEnum = r0_SendStrategyEnum;
        }
        try {
            r1_String = x.a(r6_Context, "BaiduMobAd_TIME_INTERVAL");
            if (!r1_String.equals(RContactStorage.PRIMARY_KEY)) {
                int r1i = Integer.parseInt(r1_String);
                if (r0_SendStrategyEnum.ordinal() != SendStrategyEnum.SET_TIME_INTERVAL.ordinal() || r1i <= 0 || r1i > 24) {
                    try {
                    } catch (Exception e_3) {
                        c.a(e_3);
                    }
                } else {
                    BasicStoreTools.getInstance().b(r6_Context, r1i);
                }
            }
        } catch (Exception e_4) {
            c.a(e_4);
        }
        String r0_String = x.a(r6_Context, "BaiduMobAd_ONLY_WIFI");
        if (!r0_String.equals(RContactStorage.PRIMARY_KEY)) {
            if (r0_String.equals("true")) {
                BasicStoreTools.getInstance().b(r6_Context, true);
            } else if (r0_String.equals("false")) {
                BasicStoreTools.getInstance().b(r6_Context, false);
            }
        }
    }

    public void a(Context r4_Context, SendStrategyEnum r5_SendStrategyEnum, int r6i, boolean r7z) {
        if (r5_SendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            if (r6i <= 0 || r6i > 24) {
                Object[] r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "setSendLogStrategy";
                r0_ObjectA[1] = "time_interval is invalid, new strategy does not work";
                c.c(r0_ObjectA);
            } else {
                this.d = r6i;
                this.c = SendStrategyEnum.SET_TIME_INTERVAL;
                BasicStoreTools.getInstance().a(r4_Context, this.c.ordinal());
                BasicStoreTools.getInstance().b(r4_Context, this.d);
            }
        } else {
            this.c = r5_SendStrategyEnum;
            BasicStoreTools.getInstance().a(r4_Context, this.c.ordinal());
            if (r5_SendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                BasicStoreTools.getInstance().b(r4_Context, (int)AdViewUtil.NETWORK_TYPE_CASEE);
            }
        }
        this.b = r7z;
        BasicStoreTools.getInstance().b(r4_Context, this.b);
        c.a("sdkstat", "sstype is:" + this.c.name() + " And time_interval is:" + this.d + " And m_only_wifi:" + this.b);
    }

    protected void a(Context r3_Context, boolean r4z) {
        if (r4z) {
            try {
                if (!((WifiManager) r3_Context.getSystemService(NetworkManager.WIFI)).isWifiEnabled()) {
                    c.a("stat", "sendLogData() does not send because of only_wifi setting");
                    return;
                }
            } catch (Exception e) {
                c.a("stat", "sendLogData exception when get wifimanager");
            }
        }
        DataCore.getInstance().sendLogData(r3_Context);
    }

    public void a(boolean r4z, Context r5_Context) {
        e(r5_Context);
        this.g = r4z;
        c.a("sdkstat", "APP_ANALYSIS_EXCEPTION is:" + this.g);
        BasicStoreTools.getInstance().a(r5_Context, this.g);
    }

    public void b(Context r3_Context) {
        e(r3_Context);
        if (r3_Context != null || this.h.get() == null) {
            i.post(new l(this, r3_Context));
        } else {
            r3_Context = (Context) this.h.get();
            i.post(new l(this, r3_Context));
        }
    }

    public void c(Context r4_Context) {
        BasicStoreTools.getInstance().a(r4_Context, new Date().getTime());
    }

    public void d(Context r7_Context) {
        this.e = new Timer();
        this.e.schedule(new n(this, r7_Context), (long) (this.d * 3600000), (long) (this.d * 3600000));
    }
}