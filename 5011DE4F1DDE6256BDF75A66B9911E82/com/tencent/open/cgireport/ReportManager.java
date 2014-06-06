package com.tencent.open.cgireport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.OpenConfig;
import com.tencent.tauth.Constants;
import java.util.ArrayList;
import java.util.Random;
import org.apache.cordova.NetworkManager;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.thirdparty.UsersAPI;

// compiled from: ProGuard
public class ReportManager {
    private static ReportManager a;
    private long b;
    private int c;
    private boolean d;
    private Random e;
    private ReportDataModal f;
    private ArrayList g;
    private ArrayList h;

    static {
        a = null;
    }

    public ReportManager() {
        this.b = 0;
        this.c = 3;
        this.d = false;
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.e = new Random();
    }

    public static ReportManager a() {
        if (a == null) {
            a = new ReportManager();
        }
        return a;
    }

    private void a(Context r2_Context, String r3_String, String r4_String, Bundle r5_Bundle) {
        new b(this, r3_String, r2_Context, r5_Bundle).start();
    }

    private boolean a(Context r5_Context) {
        int r0i = OpenConfig.a(r5_Context, null).b("Common_CGIReportFrequency");
        Log.d("OpenConfig_test", "config 4:Common_CGIReportTimeinterval     config_value:" + r0i);
        if (r0i == 0) {
            r0i = REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
        }
        Log.d("OpenConfig_test", "config 4:Common_CGIReportTimeinterval     result_value:" + r0i);
        if (this.e.nextInt(100) < r0i) {
            Log.i("cgi_report_debug", "ReportManager availableForFrequency = ture");
            return true;
        } else {
            Log.i("cgi_report_debug", "ReportManager availableForFrequency = false");
            return false;
        }
    }

    private String b(Context r5_Context) {
        String r0_String;
        try {
            ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r5_Context.getSystemService("connectivity");
            if (r0_ConnectivityManager == null) {
                Log.e("cgi_report_debug", "ReportManager getAPN failed:ConnectivityManager == null");
                r0_String = "no_net";
                return r0_String;
            } else {
                NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
                if (r0_NetworkInfo == null || (!r0_NetworkInfo.isAvailable())) {
                    Log.e("cgi_report_debug", "ReportManager getAPN failed:NetworkInfo == null");
                    r0_String = "no_net";
                } else if (r0_NetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                    Log.i("cgi_report_debug", "ReportManager getAPN type = wifi");
                    r0_String = NetworkManager.WIFI;
                } else {
                    r0_String = r0_NetworkInfo.getExtraInfo();
                    if (r0_String == null) {
                        Log.e("cgi_report_debug", "ReportManager getAPN failed:extraInfo == null");
                        r0_String = "mobile_unknow";
                    } else {
                        r0_String = r0_String.toLowerCase();
                        Log.i("cgi_report_debug", "ReportManager getAPN type = " + r0_String);
                    }
                }
                return r0_String;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0_String = "unknow";
        }
    }

    private void b(Context r14_Context, String r15_String, long r16j, long r18j, long r20j, int r22i, String r23_String) {
        int r4i;
        long r7j = SystemClock.elapsedRealtime() - r16j;
        Log.i("cgi_report_debug", "ReportManager updateDB url=" + r15_String + ",resultCode=" + r22i + ",timeCost=" + r7j + ",reqSize=" + r18j + ",rspSize=" + r20j);
        int r2i = OpenConfig.a(r14_Context, null).b("Common_CGIReportFrequency");
        Log.d("OpenConfig_test", "config 4:Common_CGIReportFrequency     config_value:" + r2i);
        if (r2i == 0) {
            r2i = REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
        }
        Log.d("OpenConfig_test", "config 4:Common_CGIReportFrequency     result_value:" + r2i);
        r2i = 100 / r2i;
        if (r2i <= 0) {
            r4i = 1;
        } else if (r2i > 100) {
            r4i = 100;
        } else {
            r4i = r2i;
        }
        this.f.a(b(r14_Context), r4i + RContactStorage.PRIMARY_KEY, r15_String, r22i, r7j, r18j, r20j);
    }

    private boolean c(Context r9_Context) {
        long r0j = OpenConfig.a(r9_Context, null).c("Common_CGIReportTimeinterval");
        Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     config_value:" + r0j);
        if (r0j == 0) {
            r0j = 1200;
        }
        Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     result_value:" + r0j);
        long r2j = System.currentTimeMillis() / 1000;
        if (this.b == 0 || r0j + this.b <= r2j) {
            this.b = r2j;
            Log.i("cgi_report_debug", "ReportManager availableForTime = ture");
            return true;
        } else {
            Log.i("cgi_report_debug", "ReportManager availableForTime = false");
            return false;
        }
    }

    private boolean d(Context r5_Context) {
        int r0i = OpenConfig.a(r5_Context, null).b("Common_CGIReportMaxcount");
        Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     config_value:" + r0i);
        if (r0i == 0) {
            r0i = OneProfileActivity.REQ_CODE_SHARE;
        }
        Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     result_value:" + r0i);
        if (this.f.e() >= r0i) {
            Log.i("cgi_report_debug", "ReportManager availableForCount = ture");
            return true;
        } else {
            Log.i("cgi_report_debug", "ReportManager availableForCount = false");
            return false;
        }
    }

    private void e(Context r6_Context) {
        Log.i("cgi_report_debug", "ReportManager doUpload start");
        this.d = true;
        this.g = this.f.c();
        this.f.b();
        this.h = this.f.d();
        this.f.a();
        Bundle r2_Bundle = new Bundle();
        r2_Bundle.putString(Constants.PARAM_APP_ID, "1000067");
        r2_Bundle.putString("releaseversion", "QQConnect_SDK_Android_1_6");
        r2_Bundle.putString("device", Build.DEVICE);
        r2_Bundle.putString("qua", Constants.SDK_QUA);
        r2_Bundle.putString(SharedPref.KEY, "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize");
        int r1i = 0;
        while (r1i < this.g.size()) {
            r2_Bundle.putString(r1i + "_1", ((reportItem) this.g.get(r1i)).a());
            r2_Bundle.putString(r1i + "_2", ((reportItem) this.g.get(r1i)).b());
            r2_Bundle.putString(r1i + "_3", ((reportItem) this.g.get(r1i)).c());
            r2_Bundle.putString(r1i + "_4", ((reportItem) this.g.get(r1i)).d());
            r2_Bundle.putString(r1i + "_5", ((reportItem) this.g.get(r1i)).e());
            r2_Bundle.putString(r1i + "_6", ((reportItem) this.g.get(r1i)).f());
            r2_Bundle.putString(r1i + "_7", ((reportItem) this.g.get(r1i)).g());
            r1i++;
        }
        r1i = this.g.size();
        while (r1i < this.h.size() + this.g.size()) {
            int r3i = r1i - this.g.size();
            r2_Bundle.putString(r1i + "_1", ((reportItem) this.h.get(r3i)).a());
            r2_Bundle.putString(r1i + "_2", ((reportItem) this.h.get(r3i)).b());
            r2_Bundle.putString(r1i + "_3", ((reportItem) this.h.get(r3i)).c());
            r2_Bundle.putString(r1i + "_4", ((reportItem) this.h.get(r3i)).d());
            r2_Bundle.putString(r1i + "_5", ((reportItem) this.h.get(r3i)).e());
            r2_Bundle.putString(r1i + "_6", ((reportItem) this.h.get(r3i)).f());
            r2_Bundle.putString(r1i + "_7", ((reportItem) this.h.get(r3i)).g());
            r1i++;
        }
        a(r6_Context, "http://wspeed.qq.com/w.cgi", UsersAPI.HTTPMETHOD_POST, r2_Bundle);
    }

    public void a(Context r3_Context, String r4_String, long r5j, long r7j, long r9j, int r11i, String r12_String) {
        if (this.f == null) {
            this.f = new ReportDataModal(r3_Context);
        }
        if (a(r3_Context)) {
            b(r3_Context, r4_String, r5j, r7j, r9j, r11i, r12_String);
            if (this.d) {
            } else if (c(r3_Context)) {
                e(r3_Context);
            } else if (d(r3_Context)) {
                e(r3_Context);
            }
        }
    }
}