package com.crashlytics.android.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.crashlytics.android.Crashlytics;
import java.io.File;

// compiled from: SourceFile
public class D extends u {
    private String a;
    private String b;
    private String c;
    private ao d;
    private aJ e;
    private long f;
    private av g;
    private ac h;

    public static D a() {
        return (D) v.a().a(D.class);
    }

    static /* synthetic */ void a(D r14_D) {
        Context r13_Context = r14_D.getContext();
        try {
            o r9_o = new o(new bh(), new ah(), new aj(v.a().h(), "session_analytics.tap", "session_analytics_to_send"));
            String r3_String = r14_D.d.b();
            String r4_String = r14_D.d.g();
            String r5_String = r14_D.d.c();
            String r6_String = r14_D.d.d();
            Application r1_Application = v.a().d();
            int r0i;
            aX r0_aX;
            if (r1_Application == null || VERSION.SDK_INT < 14) {
                r14_D.h = new ac(r13_Context.getPackageName(), r3_String, r4_String, r5_String, r6_String, r14_D.b, r14_D.c, r9_o, r14_D.g);
                r0j = r14_D.f;
                if (r14_D.e.a().getBoolean("analytics_launched", false)) {
                    if ((((System.currentTimeMillis() - r0j) > 3600000 ? 1 : ((System.currentTimeMillis() - r0j) == 3600000? 0 : -1)) >= 0 ? 1 : 0) == 0) {
                    }
                }
                if (r0i == 0) {
                    v.a().b().a(Crashlytics.TAG, "First launch");
                    if (r14_D.h == null) {
                        r14_D.h.b();
                        r14_D.e.a(r14_D.e.b().putBoolean("analytics_launched", true));
                    }
                }
                try {
                    aS.a().a(r13_Context, r14_D.g, r14_D.b, r14_D.c, r14_D.b()).c();
                    r0_aX = aS.a().b();
                    if (r0_aX == null) {
                        if (r0_aX.d.c) {
                            ab.c("Disabling analytics collection based on settings flag value.");
                            r14_D.h.a();
                        } else {
                            r14_D.h.a(r0_aX.e, r14_D.b());
                        }
                    }
                } catch (Exception e) {
                    v.a().b().a(Crashlytics.TAG, "Error dealing with settings", e);
                }
            } else {
                r14_D.h = new g(r1_Application, r13_Context.getPackageName(), r3_String, r4_String, r5_String, r6_String, r14_D.b, r14_D.c, r9_o, r14_D.g);
                r0j = r14_D.f;
                if (r14_D.e.a().getBoolean("analytics_launched", false)) {
                } else {
                    if (((System.currentTimeMillis() - r0j) > 3600000 ? 1 : ((System.currentTimeMillis() - r0j) == 3600000? 0 : -1)) >= 0) {
                    }
                    r0i = (((System.currentTimeMillis() - r0j) > 3600000 ? 1 : ((System.currentTimeMillis() - r0j) == 3600000? 0 : -1)) >= 0 ? 1 : 0) == 0 ? 0 : 1;
                }
                if (r0i == 0) {
                    aS.a().a(r13_Context, r14_D.g, r14_D.b, r14_D.c, r14_D.b()).c();
                    r0_aX = aS.a().b();
                    if (r0_aX == null) {
                    } else if (r0_aX.d.c) {
                        r14_D.h.a(r0_aX.e, r14_D.b());
                    } else {
                        ab.c("Disabling analytics collection based on settings flag value.");
                        r14_D.h.a();
                    }
                } else {
                    v.a().b().a(Crashlytics.TAG, "First launch");
                    if (r14_D.h == null) {
                        aS.a().a(r13_Context, r14_D.g, r14_D.b, r14_D.c, r14_D.b()).c();
                        r0_aX = aS.a().b();
                        if (r0_aX == null) {
                            if (r0_aX.d.c) {
                                ab.c("Disabling analytics collection based on settings flag value.");
                                r14_D.h.a();
                            } else {
                                r14_D.h.a(r0_aX.e, r14_D.b());
                            }
                        }
                    } else {
                        r14_D.h.b();
                        r14_D.e.a(r14_D.e.b().putBoolean("analytics_launched", true));
                        aS.a().a(r13_Context, r14_D.g, r14_D.b, r14_D.c, r14_D.b()).c();
                        r0_aX = aS.a().b();
                        if (r0_aX == null) {
                        } else if (r0_aX.d.c) {
                            r14_D.h.a(r0_aX.e, r14_D.b());
                        } else {
                            ab.c("Disabling analytics collection based on settings flag value.");
                            r14_D.h.a();
                        }
                    }
                }
            }
        } catch (Exception e_2) {
            ab.d("Crashlytics failed to initialize session analytics.");
        }
    }

    private String b() {
        return ab.a(getContext(), "com.crashlytics.ApiEndpoint");
    }

    public final void a(af r3_af) {
        if (this.h != null) {
            this.h.a(r3_af.a());
        }
    }

    public final void a(ag r3_ag) {
        if (this.h != null) {
            this.h.b(r3_ag.a());
        }
    }

    protected final void c() {
        try {
            this.g = new av(v.a().b());
            this.e = new aJ(v.a().a(D.class));
            Context r1_Context = getContext();
            PackageManager r0_PackageManager = r1_Context.getPackageManager();
            this.d = new ao(r1_Context);
            this.a = r1_Context.getPackageName();
            PackageInfo r2_PackageInfo = r0_PackageManager.getPackageInfo(this.a, 0);
            this.b = Integer.toString(r2_PackageInfo.versionCode);
            this.c = r2_PackageInfo.versionName == null ? "0.0" : r2_PackageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                this.f = r2_PackageInfo.firstInstallTime;
                new Thread(new e(this), "Crashlytics Initializer").start();
            } else {
                this.f = new File(r1_Context.getPackageManager().getApplicationInfo(r1_Context.getPackageName(), 0).sourceDir).lastModified();
                new Thread(new e(this), "Crashlytics Initializer").start();
            }
        } catch (Exception e) {
            v.a().b().a(Crashlytics.TAG, "Error setting up app properties", e);
        }
    }

    public String getVersion() {
        return v.a().getVersion();
    }
}