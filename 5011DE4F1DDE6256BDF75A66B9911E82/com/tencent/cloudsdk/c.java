package com.tencent.cloudsdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class c implements Callback, w {
    public static final String a;
    private static final byte[] b;
    private static c d;
    private Handler c;
    private ArrayList e;

    static {
        a = c.class.getSimpleName();
        b = new byte[0];
        d = null;
    }

    private c() {
        this.c = new Handler(aq.a.getLooper(), this);
        this.e = new ArrayList();
    }

    public static c a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new c();
                }
            }
        }
        return d;
    }

    private void b() {
        long r0j = f.a().g();
        long r2j = System.currentTimeMillis();
        av.a(a, "\u63d2\u4ef6\u66f4\u65b0\u5f00\u59cb\uff01\uff01\uff01");
        if (r2j > r0j) {
            av.a(a, new StringBuilder("\u5230\u8fbe\u63d2\u4ef6\u66f4\u65b0\u65f6\u95f4\uff01interval: ").append(f.a().e()).toString());
            f.a().a(r2j);
            f.a().f();
            Message r0_Message = Message.obtain();
            r0_Message.what = 2;
            ax.a(this.c, r0_Message);
        } else {
            av.a(a, new StringBuilder("\u6ca1\u6709\u5230\u63d2\u4ef6\u66f4\u65b0\u65f6\u95f4  \uff01  nextUpdateTime\uff1a ").append(r0j).toString());
        }
    }

    private void b(a ... r5_aA) {
        this.e.clear();
        int r1i = r5_aA.length;
        int r0i = 0;
        while (r0i < r1i) {
            Object r2_Object = r5_aA[r0i];
            if (!this.e.contains(r2_Object)) {
                this.e.add(r2_Object);
            }
            r0i++;
        }
    }

    public void a(int r4i, u r5_u) {
        if (r5_u == null) {
        } else {
            switch (r5_u.d) {
                case XListViewHeader.STATE_READY:
                    av.a(a, new StringBuilder(">>> CHECK onDealHttpError E: check \u534f\u8bae\u5931\u8d25\uff01 errorCode: ").append(r4i).toString());
                    WnsClientLog.e(a, new StringBuilder(">>> CHECK onDealHttpError E: check \u534f\u8bae\u5931\u8d25\uff01 errorCode: ").append(r4i).toString());
            }
        }
    }

    public void a(HttpResponse r8_HttpResponse, u r9_u) throws Exception {
        if (r8_HttpResponse == null) {
            throw new t("HttpRespone is null!");
        } else if (r9_u == null) {
            throw new IllegalArgumentException();
        } else {
            switch (r9_u.d) {
                case XListViewHeader.STATE_READY:
                    HttpEntity r0_HttpEntity = r8_HttpResponse.getEntity();
                    if (r0_HttpEntity == null) {
                        throw new t("entity is null!");
                    } else {
                        InputStream r0_InputStream = r0_HttpEntity.getContent();
                        if (r0_InputStream == null) {
                            throw new t("InputStream is null!");
                        } else {
                            byte[] r0_byteA = ax.a(r0_InputStream);
                            if (r0_byteA == null || r0_byteA.length == 0) {
                                throw new t("data is null!");
                            } else {
                                Bundle r1_Bundle = r9_u.b;
                                if (r1_Bundle != null) {
                                    av.a(a, new StringBuilder(">>> Check \u534f\u8bae \u5b8c\u6bd5\uff0c resJson: ").append(new JSONObject(new String(r0_byteA, AdViewNetFetchThread.NetEncoding)).toString()).append("\u8017\u65f6\uff1a ").append((int) (System.currentTimeMillis() - r1_Bundle.getLong("START_TIME"))).append(" ms!").toString());
                                }
                                o r1_o = new o();
                                r1_o.a(new JSONObject(new String(r0_byteA, AdViewNetFetchThread.NetEncoding)));
                                int r0i = r1_o.a;
                                if (r0i == 0) {
                                    n r0_n = new n();
                                    r0_n.a(r1_o.b);
                                    Message r1_Message = Message.obtain();
                                    r1_Message.what = 3;
                                    r1_Message.obj = r0_n;
                                    ax.a(this.c, r1_Message);
                                } else {
                                    WnsClientLog.e(a, new StringBuilder(">>> CHECK onReceiveResponseData Errocode: ").append(r0i).toString());
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }

    public void a(a ... r3_aA) {
        synchronized (b) {
            b(r3_aA);
            b();
        }
    }

    public boolean handleMessage(Message r10_Message) {
        switch (r10_Message.what) {
            case XListViewHeader.STATE_REFRESHING:
                ArrayList r1_ArrayList = new ArrayList();
                Iterator r2_Iterator = this.e.iterator();
                while (r2_Iterator.hasNext()) {
                    a r0_a = (a) r2_Iterator.next();
                    j r3_j = new j();
                    r3_j.a = r0_a.b();
                    r3_j.b = x.a(new File(f.a().a(r0_a)));
                    r1_ArrayList.add(r3_j);
                }
                k r7_k = new k();
                r7_k.a = ax.a();
                r7_k.b = (j[]) r1_ArrayList.toArray(new j[0]);
                m r0_m = new m();
                r0_m.a = "sdk.aplugin.get";
                r0_m.b = r7_k.a();
                try {
                    byte[] r4_byteA = r0_m.a().toString().getBytes(AdViewNetFetchThread.NetEncoding);
                    Bundle r6_Bundle = new Bundle();
                    r6_Bundle.putLong("START_TIME", System.currentTimeMillis());
                    new u("http://mna.qcloud.com/api", true, 1, r4_byteA, this, r6_Bundle).start();
                    av.a(a, new StringBuilder(">>> Check \u534f\u8bae \u5f00\u59cb, reqJson: ").append(r7_k.a().toString()).toString());
                } catch (UnsupportedEncodingException e) {
                    Throwable r0_Throwable = e;
                    WnsClientLog.e(a, new StringBuilder(">>> handleMessage E: ").append(r0_Throwable.toString()).toString(), r0_Throwable);
                }
                break;
            case XListViewFooter.STATE_NOMORE:
                n r0_n = (n) r10_Message.obj;
                if (f.a().e() != r0_n.b) {
                    f.a().a(r0_n.b);
                    f.a().f();
                }
                if (ax.b() == -1 || (ax.b() & r0_n.c) <= 0) {
                    av.a(a, new StringBuilder("\u5f53\u524d\u7f51\u7edc\u4e0b\u4e0d\u5141\u8bb8\u4e0b\u8f7d  netType \uff1a ").append(ax.b()).toString());
                } else {
                    h[] r0_hA = r0_n.a;
                    if (r0_hA == null || r0_hA.length == 0) {
                        av.a(a, "\u6ca1\u6709\u63d2\u4ef6\u53ef\u66f4\u65b0\uff01");
                    } else {
                        av.a(a, "\u5f00\u59cb\u66f4\u65b0\u63d2\u4ef6");
                        e.a().a(r0_hA);
                    }
                }
                break;
        }
        return true;
    }
}