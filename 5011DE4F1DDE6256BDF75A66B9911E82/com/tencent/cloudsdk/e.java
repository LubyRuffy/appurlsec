package com.tencent.cloudsdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class e implements Callback, w {
    public static final String a;
    private static final byte[] b;
    private static e c;
    private Handler d;
    private HashMap e;
    private int f;

    static {
        a = e.class.getSimpleName();
        b = new byte[0];
        c = null;
    }

    private e() {
        this.d = new Handler(aq.a.getLooper(), this);
        this.e = new HashMap();
        this.f = 0;
    }

    public static e a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new e();
                }
            }
        }
        return c;
    }

    private boolean a(a r6_a, File r7_File, String r8_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        String r1_String = f.a().b();
        if (!au.b(r1_String, r8_String)) {
            r0_String = "\u521b\u5efa\u63d2\u4ef6\u6587\u4ef6\u5931\u8d25\uff01";
        }
        r1_String = new StringBuilder(String.valueOf(r1_String)).append(File.separator).append(r8_String).toString();
        synchronized (b.a) {
            try {
                if (!au.a(new FileInputStream(r7_File), new File(r1_String))) {
                    r0_String = "COPY\u63d2\u4ef6\u6587\u4ef6\u5931\u8d25\uff01";
                }
                if (!r7_File.delete()) {
                    r0_String = "\u5220\u9664\u63d2\u4ef6\u4e34\u65f6\u6587\u4ef6\u5931\u8d25\uff01";
                }
            } catch (FileNotFoundException e) {
                r0_String = e.toString();
            }
        }
        if (RContactStorage.PRIMARY_KEY.equals(r0_String)) {
            f.a().a(r6_a, r1_String);
            WnsClientLog.i(a, new StringBuilder(">>> copyPlugins I: copy File: ").append(r8_String).append(" \u6210\u529f").toString());
            return true;
        } else {
            WnsClientLog.e(a, new StringBuilder(">>> copyPlugins E: copy File: ").append(r8_String).append(" \u5931\u8d25  --- ").append(r0_String).toString());
            return false;
        }
    }

    public void a(int r7i, u r8_u) {
        if (r8_u == null) {
        } else {
            switch (r8_u.d) {
                case XListViewHeader.STATE_REFRESHING:
                    Bundle r0_Bundle = r8_u.b;
                    int r1i = r0_Bundle.getInt("TYPE_ID");
                    long r2j = r0_Bundle.getLong("START_TIME");
                    i r0_i = (i) this.e.get(Integer.valueOf(r1i));
                    if (r0_i != null) {
                        r0_i.d = (int) (System.currentTimeMillis() - r2j);
                        r0_i.e = r7i;
                        av.a(a, new StringBuilder("\u4e0b\u8f7d\u51fa\u9519  errorcode: ").append(r7i).append(" \u8017\u65f6\uff1a ").append(r0_i.d).toString());
                        Message r0_Message = Message.obtain();
                        r0_Message.what = 5;
                        this.d.sendMessage(r0_Message);
                    }
            }
        }
    }

    public void a(HttpResponse r10_HttpResponse, u r11_u) throws Exception {
        if (r10_HttpResponse == null) {
            throw new t("HttpRespone is null!");
        } else if (r11_u == null) {
            throw new IllegalArgumentException();
        } else {
            switch (r11_u.d) {
                case XListViewHeader.STATE_REFRESHING:
                    HttpEntity r0_HttpEntity = r10_HttpResponse.getEntity();
                    if (r0_HttpEntity == null) {
                        throw new t("entity is null!");
                    } else {
                        long r1j = r0_HttpEntity.getContentLength();
                        if (r1j <= 0) {
                            throw new t("\u8fd4\u56de\u6570\u636e\u4e3a\u7a7a");
                        } else if (ax.a(r1j * 2)) {
                            InputStream r0_InputStream = r0_HttpEntity.getContent();
                            if (r0_InputStream == null) {
                                throw new t("InputStream is null!");
                            } else {
                                String r1_String = ax.a(r11_u.a);
                                if (ax.b(r1_String)) {
                                    String r2_String = f.a().b();
                                    File r3_File = new File(new StringBuilder(String.valueOf(r2_String)).append(File.separator).append("dlock").toString());
                                    if (r3_File.exists() || au.a(r2_String, "dlock")) {
                                        ar r2_ar = new at();
                                        try {
                                            if (r2_ar.a(r3_File) == null) {
                                                throw new t("\u6587\u4ef6\u9501\u83b7\u53d6\u5931\u8d25");
                                            } else {
                                                String r3_String = f.a().c();
                                                if (au.b(r3_String, r1_String)) {
                                                    File r4_File = new File(new StringBuilder(String.valueOf(r3_String)).append(File.separator).append(r1_String).toString());
                                                    if (au.a(r0_InputStream, r4_File)) {
                                                        Bundle r0_Bundle = r11_u.b;
                                                        int r3i = r0_Bundle.getInt("TYPE_ID");
                                                        int r5i = (int) (System.currentTimeMillis() - r0_Bundle.getLong("START_TIME"));
                                                        av.a(a, new StringBuilder("\u4e0b\u8f7d\u63d2\u4ef6\uff1a ").append(a.a(r3i).a()).append(" \u5b8c\u6210\uff01 \u8017\u65f6\uff1a ").append(r5i).toString());
                                                        if (x.a(r4_File).equalsIgnoreCase(r0_Bundle.getString("MD5"))) {
                                                            if (a(a.a(r3i), r4_File, r1_String)) {
                                                                i r0_i = (i) this.e.get(Integer.valueOf(r3i));
                                                                if (r0_i != null) {
                                                                    r0_i.d = r5i;
                                                                    r0_i.e = 0;
                                                                    Message r0_Message = Message.obtain();
                                                                    r0_Message.what = 5;
                                                                    this.d.sendMessage(r0_Message);
                                                                }
                                                                if (r2_ar != null) {
                                                                    r2_ar.a();
                                                                }
                                                            } else {
                                                                throw new q("copy \u63d2\u4ef6\u5931\u8d25\uff01");
                                                            }
                                                        } else if (r4_File.delete()) {
                                                            throw new s("md5\u68c0\u9a8c\u5931\u8d25\uff01");
                                                        } else {
                                                            throw new Exception("\u5220\u9664\u4e0b\u8f7d\u4e34\u65f6\u6587\u4ef6\u5931\u8d25\uff01");
                                                        }
                                                    } else {
                                                        throw new Exception("\u4fdd\u5b58\u4e0b\u8f7d\u4e34\u65f6\u6587\u4ef6\u5931\u8d25\uff01");
                                                    }
                                                } else {
                                                    throw new Exception("\u521b\u5efa\u4e0b\u8f7d\u4e34\u65f6\u6587\u4ef6\u5931\u8d25\uff01 ");
                                                }
                                            }
                                        } catch (Throwable th) {
                                            if (r2_ar != null) {
                                                r2_ar.a();
                                            }
                                        }
                                    } else {
                                        throw new Exception("\u521b\u5efa\u4e0b\u8f7d\u9501\u6587\u4ef6\u5931\u8d25\uff01 ");
                                    }
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                        } else {
                            throw new r("\u6ca1\u6709\u8db3\u591f\u5b58\u50a8\u7a7a\u95f4");
                        }
                    }
            }
        }
    }

    public void a(h[] r11_hA) {
        if (r11_hA == null || r11_hA.length == 0) {
        } else {
            this.e.clear();
            int r8i = r11_hA.length;
            int r7i = 0;
            while (r7i < r8i) {
                h r9_h = r11_hA[r7i];
                i r0_i = new i();
                r0_i.a = r9_h.a;
                r0_i.c = r9_h.c;
                r0_i.b = r9_h.b;
                this.e.put(Integer.valueOf(r9_h.a), r0_i);
                synchronized (b) {
                    this.f++;
                }
                Bundle r6_Bundle = new Bundle();
                r6_Bundle.putInt("TYPE_ID", r9_h.a);
                r6_Bundle.putLong("START_TIME", System.currentTimeMillis());
                r6_Bundle.putString("MD5", r9_h.b);
                new u(r9_h.c, false, 2, null, this, r6_Bundle).start();
                av.a(a, new StringBuilder("\u4e0b\u8f7d\u63d2\u4ef6 : ").append(a.a(r9_h.a).a()).append("\u5f00\u59cb !").toString());
                r7i++;
            }
        }
    }

    public boolean handleMessage(Message r10_Message) {
        switch (r10_Message.what) {
            case ShareUtils.SHARE_SMS:
                synchronized (b) {
                    if (this.f == 0) {
                    } else {
                        this.f--;
                        if (this.f == 0) {
                            l r7_l = new l();
                            r7_l.a = ax.a();
                            r7_l.b = (i[]) this.e.values().toArray(new i[0]);
                            m r0_m = new m();
                            r0_m.a = "sdk.aplugin.report";
                            r0_m.b = r7_l.a();
                            try {
                                new u("http://mna.qcloud.com/api", true, 3, r0_m.a().toString().getBytes(AdViewNetFetchThread.NetEncoding), null).start();
                                av.a(a, new StringBuilder("\u4e0b\u8f7d\u4fe1\u606f\u4e0a\u62a5  json: ").append(r7_l.a().toString()).toString());
                            } catch (UnsupportedEncodingException e) {
                                Throwable r0_Throwable = e;
                                WnsClientLog.e(a, new StringBuilder(">>> handleMessage E: ").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
                            }
                        }
                    }
                }
                break;
        }
        return true;
    }
}