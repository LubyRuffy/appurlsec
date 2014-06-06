package com.tencent.cloudsdk;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.common.utils.ClientIdManager;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ay {
    private static final String a;
    private static final byte[] h;
    private static final Map p;
    private int b;
    private cl c;
    private boolean d;
    private String e;
    private int f;
    private short g;
    private List i;
    private long j;
    private int k;
    private long l;
    private long m;
    private long n;
    private bc o;

    static {
        a = ay.class.getName();
        h = new byte[1];
        p = Collections.synchronizedMap(new cd(512));
    }

    public ay(String r2_String) {
        this.b = 3;
        this.c = null;
        this.d = false;
        this.i = new ArrayList();
        this.e = r2_String;
    }

    private void a(int r6i, String r7_String, long r8j) {
        synchronized (h) {
            Bundle r0_Bundle = new Bundle();
            r0_Bundle.putInt(Constants.PARAM_TYPE_ID, 1000157);
            r0_Bundle.putInt(KEYS.RET, r6i);
            r0_Bundle.putLong("tc", System.currentTimeMillis() - r8j);
            r0_Bundle.putString("dm", this.e);
            r0_Bundle.putLong("aip", ep.c(r7_String));
            r0_Bundle.putInt("sp", this.g);
            r0_Bundle.putInt("nt", this.f);
            r0_Bundle.putInt("os", 1);
            r0_Bundle.putString("osv", VERSION.RELEASE);
            r0_Bundle.putString("sdkv", "1.2.0");
            r0_Bundle.putInt("apv", 1);
            r0_Bundle.putLong("dtc", this.j);
            r0_Bundle.putInt("dret", this.k);
            r0_Bundle.putLong("ctc", this.l);
            r0_Bundle.putLong("stc", this.m);
            r0_Bundle.putLong("rtc", this.n);
            r0_Bundle.putInt("seq", ClientIdManager.getInstance().getClientId());
            this.i.add(r0_Bundle);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(List r9_List, String r10_String, short r11s, boolean r12z) {
        /*
        r8_this = this;
        r0 = r9.size();
        r1 = r8.b;
        if (r0 >= r1) goto L_0x000e;
    L_0x0008:
        r0 = r9.size();
        r8.b = r0;
    L_0x000e:
        r7 = com.tencent.cloudsdk.bj.a(r10, r11, r12);
        r0 = com.tencent.cloudsdk.bv.c();
        r3 = r0.l();
        r0 = 0;
        r6 = r0;
    L_0x001c:
        r0 = r8.b;
        if (r6 < r0) goto L_0x0045;
    L_0x0020:
        r0 = r8.c();
        if (r0 == 0) goto L_0x0062;
    L_0x0026:
        r0 = r8.c;
        if (r0 == 0) goto L_0x0034;
    L_0x002a:
        r0 = r8.c;
        r0 = r0.b;
        r0 = r10.equals(r0);
        if (r0 != 0) goto L_0x0044;
    L_0x0034:
        r0 = new com.tencent.cloudsdk.ba;
        r0.<init>();
        r1 = java.lang.System.currentTimeMillis();
        r0.a = r1;
        r1 = p;
        r1.put(r10, r0);
    L_0x0044:
        return;
    L_0x0045:
        r0 = new com.tencent.cloudsdk.bd;
        r1 = r9.get(r6);
        r1 = (java.lang.String) r1;
        r2 = 8000; // 0x1f40 float:1.121E-41 double:3.9525E-320;
        r4 = r7.a();
        r5 = new com.tencent.cloudsdk.ac;
        r5.<init>(r8, r12);
        r0.<init>(r1, r2, r3, r4, r5);
        r0.start();
        r0 = r6 + 1;
        r6 = r0;
        goto L_0x001c;
    L_0x0062:
        r0 = 10;
        java.lang.Thread.sleep(r0);	 //Catch:{ InterruptedException -> 0x0068 }
        goto L_0x0020;
    L_0x0068:
        r0 = move-exception;
        goto L_0x0020;
        */

    }

    private void a(byte[] r9_byteA, String r10_String, int r11i, long r12j, boolean r14z) {
        synchronized (h) {
            if (!this.d) {
                bo r2_bo;
                List r0_List;
                bp r0_bp = bq.a(r9_byteA);
                bf r4_bf = r0_bp.a();
                if (r4_bf.b() == (short) 0) {
                    try {
                        r2_bo = (bo) r0_bp.d();
                    } catch (Exception e) {
                        WnsClientLog.e(a, "parse data exception on threadDone.", e);
                    }
                } else {
                    r2_bo = null;
                }
                r0_List = r2_bo != null ? r2_bo.a() : null;
                if (r4_bf.b() != (short) 0 || r0_List == null || r0_List.size() <= 0) {
                    b();
                } else {
                    this.c = new cl();
                    this.c.b = this.e;
                    this.c.c = this.g;
                    this.c.d = ((long) (r2_bo.c() * 1000)) + System.currentTimeMillis();
                    this.c.e = r2_bo.d();
                    this.c.f = r0_List;
                    cf.a(GlobalContext.getContext()).b(this.c, r14z);
                    if (bv.n() != r2_bo.e()) {
                        bv.a(r10_String, r11i, r2_bo.e());
                    }
                    a(r2_bo.b().b(), r10_String, r12j);
                    this.d = true;
                }
                if (this.o != null) {
                    this.o.a();
                }
            }
        }
    }

    private void b() {
        synchronized (h) {
            this.b--;
            if (this.b <= 0) {
                this.d = true;
            }
        }
    }

    private boolean c() {
        boolean r0z;
        synchronized (h) {
            r0z = this.d;
        }
        return r0z;
    }

    public cl a(boolean r12z) {
        ba r0_ba = (ba) p.get(this.e);
        int r3i = bv.c().h();
        long r4j = System.currentTimeMillis();
        if (r0_ba == null || r4j - r0_ba.a <= ((long) r3i)) {
            if (r0_ba != null && r4j - r0_ba.a <= ((long) r3i)) {
                return null;
            }
            this.f = em.b();
        } else {
            p.remove(this.e);
        }
        this.f = em.b();
        if (this.f == -1) {
            er.a(a, ">>>\u7f51\u7edc\u672a\u8fde\u63a5\uff0c\u76f4\u63a5\u8fd4\u56deNULL\uff0c\u67e5\u8be2\u7ed3\u675f");
            return null;
        } else {
            if (this.f == 1) {
                this.g = (short) 0;
            } else {
                this.g = em.a();
            }
            er.a(a, new StringBuilder(">>>\u5f53\u524d\u7f51\u7edc\u7c7b\u578b:").append(this.f).append(", \u5f53\u524d\u8fd0\u8425\u5546:").append(this.g).toString());
            this.c = cf.a(GlobalContext.getContext()).a(this.e, this.g, r12z);
            if (this.c != null && this.c.f != null && this.c.f.size() != 0) {
                return this.c;
            }
            er.a(a, ">>>\u7f13\u5b58\u6570\u636e\u4e0d\u5b58\u5728\u6216\u5df2\u7ecf\u8fc7\u671f\uff0c\u5f00\u59cb\u7f51\u7edc\u67e5\u8be2");
            if (this.g == (short) 0) {
                er.a(a, ">>>WIFI\u7f51\u7edc\u6216\u8005\u83b7\u53d6SP\u5931\u8d25\uff0c\u5f00\u59cb114DNS\u89e3\u6790[ans.qcloud.com]");
                long r3j = System.currentTimeMillis();
                InetAddress[] r5_InetAddressA = ct.a("ans.qcloud.com");
                if (r5_InetAddressA == null || r5_InetAddressA.length <= 0) {
                    er.a(a, new StringBuilder(">>>114DNS\u89e3\u6790\u5931\u8d25\uff0c\u67e5\u8be2\u7ed3\u675f\u3002\u8017\u65f6\uff1a").append(System.currentTimeMillis() - r3j).toString());
                    this.k = 1;
                    a(Utils.IO_BUFFER_SIZE, RContactStorage.PRIMARY_KEY, System.currentTimeMillis());
                    this.c = null;
                } else {
                    er.a(a, new StringBuilder(">>>114DNS\u89e3\u6790\u6210\u529f\uff0c\u8017\u65f6\uff1a").append(System.currentTimeMillis() - r3j).toString());
                    this.j = System.currentTimeMillis() - r3j;
                    this.k = 0;
                    List r3_List = new ArrayList();
                    int r1i = 0;
                    while (r1i < r5_InetAddressA.length) {
                        r3_List.add(r5_InetAddressA[r1i].getHostAddress());
                        er.a(a, new StringBuilder(">>>114DNS\u8fd4\u56deIP:").append((String) r3_List.get(r1i)).toString());
                        r1i++;
                    }
                    a(r3_List, this.e, (short) 0, r12z);
                }
                if (this.i.size() <= 0) {
                    WnsClientLog.v(a, new StringBuilder("\u5f00\u59cb\u4e0a\u62a5\uff1a").append(this.e).append(",\u6570\u91cf:").append(this.i.size()).toString());
                    cz.a().a(this.i);
                }
                return this.c;
            } else {
                switch (this.g) {
                    case XListViewHeader.STATE_READY:
                        a(bv.c().o(), this.e, (short) 1, r12z);
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        a(bv.c().p(), this.e, (short) 2, r12z);
                        break;
                    case XListViewFooter.STATE_NODATA:
                        a(bv.c().q(), this.e, (short) 4, r12z);
                        break;
                }
            }
            if (this.i.size() <= 0) {
                return this.c;
            }
            WnsClientLog.v(a, new StringBuilder("\u5f00\u59cb\u4e0a\u62a5\uff1a").append(this.e).append(",\u6570\u91cf:").append(this.i.size()).toString());
            cz.a().a(this.i);
            return this.c;
        }
    }

    public void a(bc r1_bc) {
        this.o = r1_bc;
    }
}