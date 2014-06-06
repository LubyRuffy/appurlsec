package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.androidquery.util.Constants;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

// compiled from: SourceFile
final class ag implements OnClickListener {
    static String a;
    static String b;
    private static volatile String c;
    private static volatile String d;
    private static volatile String e;
    private static String f;
    private static int g;
    private static volatile long z;
    private String h;
    private String i;
    private String j;
    private long k;
    private long l;
    private long m;
    private al n;
    private boolean o;
    private volatile boolean p;
    private String q;
    private Map r;
    private Handler s;
    private boolean t;
    private transient Map u;
    private h v;
    private List w;
    private Map x;
    private AppCircleCallback y;

    static {
        c = "market://";
        d = "market://details?id=";
        e = "https://market.android.com/details?id=";
        f = "com.flurry.android.ACTION_CATALOG";
        a = "FlurryAgent";
        Random r0_Random = new Random(System.currentTimeMillis());
        g = 5000;
        b = RContactStorage.PRIMARY_KEY;
        z = 0;
    }

    public ag() {
        this.o = true;
        this.r = new HashMap();
        this.u = new HashMap();
        this.w = new ArrayList();
        this.x = new HashMap();
        this.n = new al();
    }

    private Offer a(String r9_String, ah r10_ah) {
        String r6_String;
        int r7i;
        ab r3_ab = new ab(r9_String, (byte) 3, k());
        a(r3_ab);
        r3_ab.a(new r((byte) 2, k()));
        r3_ab.b = r10_ah;
        m r0_m = this.n.a(r10_ah.a);
        r6_String = r0_m == null ? RContactStorage.PRIMARY_KEY : r0_m.a;
        r7i = r0_m == null ? 0 : r0_m.c;
        long r1j = z + 1;
        z = r1j;
        OfferInSdk r0_OfferInSdk = new OfferInSdk(r1j, r3_ab, r10_ah.h, r10_ah.d, r6_String, r7i);
        this.x.put(Long.valueOf(r0_OfferInSdk.a), r0_OfferInSdk);
        Offer r0_Offer = new Offer(r0_OfferInSdk.a, r0_OfferInSdk.f, r0_OfferInSdk.c, r0_OfferInSdk.d, r0_OfferInSdk.e);
        return r7_Offer;
    }

    private String a(ab r7_ab, Long r8_Long) {
        ah r0_ah = r7_ab.b;
        StringBuilder r2_StringBuilder = new StringBuilder().append("?apik=").append(this.j).append("&cid=").append(r0_ah.e).append("&adid=").append(r0_ah.a).append("&pid=").append(this.q).append("&iid=").append(this.k).append("&sid=").append(this.l).append("&its=").append(r7_ab.a()).append("&hid=").append(ad.a(r7_ab.a)).append("&ac=").append(a(r0_ah.g));
        if (this.r == null || this.r.isEmpty()) {
            r2_StringBuilder.append("&ats=");
            if (r8_Long == null) {
                r2_StringBuilder.append(r8_Long);
            }
            return r2_StringBuilder.toString();
        } else {
            Iterator r3_Iterator = this.r.entrySet().iterator();
            while (r3_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r3_Iterator.next();
                String r1_String = "c_" + ad.a((String) r0_Entry.getKey());
                r2_StringBuilder.append("&").append(r1_String).append("=").append(ad.a((String) r0_Entry.getValue()));
            }
            r2_StringBuilder.append("&ats=");
            if (r8_Long == null) {
                return r2_StringBuilder.toString();
            }
            r2_StringBuilder.append(r8_Long);
            return r2_StringBuilder.toString();
        }
    }

    private static String a(byte[] r4_byteA) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r0i = 0;
        while (r0i < r4_byteA.length) {
            int r2i = (r4_byteA[r0i] >> 4) & 15;
            if (r2i < 10) {
                r1_StringBuilder.append((char) (r2i + 48));
            } else {
                r1_StringBuilder.append((char) (r2i + 65 - 10));
            }
            r2i = r4_byteA[r0i] & 15;
            if (r2i < 10) {
                r1_StringBuilder.append((char) (r2i + 48));
            } else {
                r1_StringBuilder.append((char) (r2i + 65 - 10));
            }
            r0i++;
        }
        return r1_StringBuilder.toString();
    }

    private List a(List r9_List, Long r10_Long) {
        if (r9_List == null || r9_List.isEmpty() || (!this.n.b())) {
            return Collections.emptyList();
        }
        ah[] r0_ahA = this.n.a((String) r9_List.get(0));
        if (r0_ahA == null || r0_ahA.length <= 0) {
            return Collections.emptyList();
        }
        List r1_List = new ArrayList(Arrays.asList(r0_ahA));
        Collections.shuffle(r1_List);
        if (r10_Long != null) {
            Iterator r2_Iterator = r1_List.iterator();
            while (r2_Iterator.hasNext()) {
                if (((ah) r2_Iterator.next()).a == r10_Long.longValue()) {
                    r2_Iterator.remove();
                    break;
                }
            }
        }
        return r1_List.subList(0, Math.min(r1_List.size(), r9_List.size()));
    }

    private void a(ab r4_ab) {
        if (this.w.size() < 32767) {
            this.w.add(r4_ab);
            this.u.put(Long.valueOf(r4_ab.a()), r4_ab);
        }
    }

    static /* synthetic */ void a(ag r4_ag, Context r5_Context, String r6_String) {
        if (r6_String.startsWith(d)) {
            String r0_String = r6_String.substring(d.length());
            if (r4_ag.o) {
                try {
                    i.a(a, "Launching Android Market for app " + r0_String);
                    r5_Context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(r6_String)));
                } catch (Exception e) {
                    i.c(a, "Cannot launch Marketplace url " + r6_String, e);
                }
            } else {
                i.a(a, "Launching Android Market website for app " + r0_String);
                r5_Context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(e + r0_String)));
            }
        } else {
            i.d(a, "Unexpected android market url scheme: " + r6_String);
        }
    }

    private static void a(Runnable r1_Runnable) {
        new Handler().post(r1_Runnable);
    }

    private void b(Context r5_Context, ab r6_ab, String r7_String) {
        Intent r0_Intent = new Intent(p());
        r0_Intent.addCategory("android.intent.category.DEFAULT");
        r0_Intent.putExtra("u", r7_String);
        if (r6_ab != null) {
            r0_Intent.putExtra("o", r6_ab.a());
        }
        r5_Context.startActivity(r0_Intent);
    }

    private String d(String r6_String) {
        try {
            if (!r6_String.startsWith(c)) {
                HttpResponse r1_HttpResponse = new DefaultHttpClient().execute(new HttpGet(r6_String));
                int r2i = r1_HttpResponse.getStatusLine().getStatusCode();
                if (r2i == 200) {
                    r6_String = EntityUtils.toString(r1_HttpResponse.getEntity());
                    if (!r6_String.startsWith(c)) {
                        r6_String = d(r6_String);
                    }
                } else {
                    i.c(a, "Cannot process with responseCode " + r2i);
                    e("Error when fetching application's android market ID, responseCode " + r2i);
                }
            }
        } catch (UnknownHostException e) {
            UnknownHostException r1_UnknownHostException = e;
            i.c(a, "Unknown host: " + r1_UnknownHostException.getMessage());
            if (this.y != null) {
                e("Unknown host: " + r1_UnknownHostException.getMessage());
            }
            r6_String = null;
        } catch (Exception e_2) {
            i.c(a, "Failed on url: " + r6_String, e_2);
            r6_String = null;
        }
        return r6_String;
    }

    private void e(String r2_String) {
        a(new f(this, r2_String));
    }

    private synchronized AdImage o() {
        return q() ? this.n.a((short) 1) : null;
    }

    private static String p() {
        return FlurryAgent.a != null ? FlurryAgent.a : f;
    }

    private boolean q() {
        if (!this.p) {
            i.d(a, "AppCircle is not initialized");
        }
        if (this.q == null) {
            i.d(a, "Cannot identify UDID.");
        }
        return this.p;
    }

    final synchronized View a(Context r3_Context, String r4_String, int r5i) {
        aa r0_aa;
        if (q()) {
            r0_aa = new aa(this, r3_Context, r4_String, r5i);
            this.v.a(r0_aa);
        } else {
            r0_aa = null;
        }
        return r0_aa;
    }

    final synchronized AdImage a(long r2j) {
        return q() ? this.n.b(r2j) : null;
    }

    final synchronized List a(Context r12_Context, List r13_List, Long r14_Long, int r15i, boolean r16z) {
        List r0_List;
        if (q()) {
            if ((!this.n.b()) || r13_List == null) {
                r0_List = Collections.emptyList();
            } else {
                List r9_List = a(r13_List, r14_Long);
                int r10i = Math.min(r13_List.size(), r9_List.size());
                List r7_List = new ArrayList();
                int r8i = 0;
                while (r8i < r10i) {
                    String r0_String = (String) r13_List.get(r8i);
                    q r4_q = this.n.b(r0_String);
                    if (r4_q != null) {
                        ab r3_ab = new ab((String) r13_List.get(r8i), (byte) 1, k());
                        a(r3_ab);
                        if (r8i < r9_List.size()) {
                            r3_ab.b = (ah) r9_List.get(r8i);
                            r3_ab.a(new r((byte) 2, k()));
                            ak r0_ak = new ak(r12_Context, this, r3_ab, r4_q, r15i, r16z);
                            r0_ak.a(a(r3_ab, null));
                            r7_List.add(r0_ak);
                        }
                    } else {
                        i.d(a, "Cannot find hook: " + r0_String);
                    }
                    r8i++;
                }
                r0_List = r7_List;
            }
        } else {
            r0_List = Collections.emptyList();
        }
        return r0_List;
    }

    final synchronized void a() {
        this.w.clear();
    }

    final synchronized void a(int r2i) {
        if (this.y != null) {
            a(new e(this, r2i));
        }
    }

    final synchronized void a(Context r8_Context, long r9j) {
        if (q()) {
            OfferInSdk r0_OfferInSdk = (OfferInSdk) this.x.get(Long.valueOf(r9j));
            if (r0_OfferInSdk == null) {
                i.b(a, "Cannot find offer " + r9j);
            } else {
                ab r1_ab = r0_OfferInSdk.b;
                r1_ab.a(new r((byte) 4, k()));
                String r2_String = FlurryAgent.d() + a(r1_ab, Long.valueOf(r1_ab.a()));
                i.a(a, "Offer " + r0_OfferInSdk.a + " accepted. Sent with cookies: " + this.r);
                a(r8_Context, r1_ab, r2_String);
            }
        }
    }

    final synchronized void a(Context r6_Context, a r7_a) {
        boolean r0z = true;
        synchronized (this) {
            if (!this.p) {
                this.h = r7_a.e;
                this.i = r7_a.f;
                this.j = r7_a.a;
                this.k = r7_a.b;
                this.l = r7_a.c;
                this.m = r7_a.d;
                this.s = r7_a.g;
                this.v = new h(this.s, g);
                r6_Context.getResources().getDisplayMetrics();
                this.x.clear();
                this.u.clear();
                this.n.a(r6_Context, this, r7_a);
                this.r.clear();
                PackageManager r1_PackageManager = r6_Context.getPackageManager();
                String r2_String = d + r6_Context.getPackageName();
                Intent r3_Intent = new Intent("android.intent.action.VIEW");
                r3_Intent.setData(Uri.parse(r2_String));
                if (r1_PackageManager.queryIntentActivities(r3_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION).size() > 0) {
                    this.o = r0z;
                    a();
                    this.p = true;
                } else {
                    r0z = false;
                    this.o = r0z;
                    a();
                    this.p = true;
                }
            }
        }
    }

    final synchronized void a(Context r3_Context, ab r4_ab, String r5_String) {
        if (q()) {
            this.s.post(new l(this, r5_String, r3_Context, r4_ab));
        }
    }

    final synchronized void a(Context r6_Context, String r7_String) {
        if (q()) {
            List r0_List;
            try {
                String[] r0_StringA = new String[1];
                r0_StringA[0] = r7_String;
                r0_List = a(Arrays.asList(r0_StringA), null);
            } catch (Exception e) {
                i.d(a, "Failed to launch promotional canvas for hook: " + r7_String, e);
            }
            if (r0_List == null || r0_List.isEmpty()) {
                Intent r0_Intent = new Intent(p());
                r0_Intent.addCategory("android.intent.category.DEFAULT");
                r6_Context.startActivity(r0_Intent);
            } else {
                ab r3_ab = new ab(r7_String, (byte) 2, SystemClock.elapsedRealtime() - this.m);
                r3_ab.b = (ah) r0_List.get(0);
                a(r3_ab);
                b(r6_Context, r3_ab, this.h + a(r3_ab, Long.valueOf(System.currentTimeMillis())));
            }
        }
    }

    final void a(AppCircleCallback r1_AppCircleCallback) {
        this.y = r1_AppCircleCallback;
    }

    final void a(String r1_String) {
        this.q = r1_String;
    }

    final synchronized void a(String r2_String, String r3_String) {
        this.r.put(r2_String, r3_String);
    }

    final synchronized void a(List r4_List) {
        if (q()) {
            Iterator r1_Iterator = r4_List.iterator();
            while (r1_Iterator.hasNext()) {
                this.x.remove((Long) r1_Iterator.next());
            }
        }
    }

    final synchronized void a(Map r8_Map, Map r9_Map, Map r10_Map, Map r11_Map, Map r12_Map, Map r13_Map) {
        if (q()) {
            this.n.a(r8_Map, r9_Map, r10_Map, r11_Map, r12_Map, r13_Map);
            Log.i("FlurryAgent", this.n.toString());
        }
    }

    final void a(boolean r1z) {
        this.t = r1z;
    }

    final synchronized Offer b(String r6_String) {
        Offer r0_Offer = null;
        synchronized (this) {
            if (q()) {
                String[] r1_StringA = new String[1];
                r1_StringA[0] = r6_String;
                List r1_List = a(Arrays.asList(r1_StringA), null);
                if (r1_List == null || r1_List.isEmpty()) {
                } else {
                    r0_Offer = a(r6_String, (ah) r1_List.get(0));
                    i.a(a, "Impression for offer with ID " + r0_Offer.getId());
                }
            }
        }
        return r0_Offer;
    }

    final synchronized ab b(long r3j) {
        return (ab) this.u.get(Long.valueOf(r3j));
    }

    final boolean b() {
        return this.p;
    }

    final synchronized List c(String r6_String) {
        List r0_List;
        if (q()) {
            if (this.n.b()) {
                ah[] r2_ahA = this.n.a(r6_String);
                r0_List = new ArrayList();
                if (r2_ahA == null || r2_ahA.length <= 0) {
                    i.a(a, "Impressions for " + r0_List.size() + " offers.");
                } else {
                    int r3i = r2_ahA.length;
                    int r1i = 0;
                    while (r1i < r3i) {
                        r0_List.add(a(r6_String, r2_ahA[r1i]));
                        r1i++;
                    }
                    i.a(a, "Impressions for " + r0_List.size() + " offers.");
                }
            } else {
                r0_List = Collections.emptyList();
            }
        } else {
            r0_List = Collections.emptyList();
        }
        return r0_List;
    }

    final synchronized void c() {
        if (q()) {
            this.n.d();
        }
    }

    final synchronized void d() {
        if (q()) {
            this.n.e();
        }
    }

    final synchronized long e() {
        return q() ? this.n.c() : 0;
    }

    final synchronized Set f() {
        return q() ? this.n.a() : Collections.emptySet();
    }

    final synchronized List g() {
        return this.w;
    }

    final synchronized void h() {
        this.u.clear();
    }

    final boolean i() {
        return this.t;
    }

    final String j() {
        return this.h;
    }

    final long k() {
        return SystemClock.elapsedRealtime() - this.m;
    }

    final synchronized void l() {
        this.r.clear();
    }

    final synchronized AdImage m() {
        return q() ? o() : null;
    }

    final synchronized boolean n() {
        return q() ? this.n.b() : false;
    }

    public final synchronized void onClick(View r8_View) {
        ak r1_ak = (ak) r8_View;
        ab r2_ab = r1_ak.a();
        r2_ab.a(new r((byte) 4, k()));
        if (this.t) {
            b(r8_View.getContext(), r2_ab, r1_ak.b(this.h));
        } else {
            a(r8_View.getContext(), r2_ab, r1_ak.b(this.i));
        }
    }

    public final String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("[adLogs=").append(this.w).append("]");
        return r0_StringBuilder.toString();
    }
}