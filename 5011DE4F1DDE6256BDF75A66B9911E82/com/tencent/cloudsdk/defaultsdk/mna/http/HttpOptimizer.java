package com.tencent.cloudsdk.defaultsdk.mna.http;

import com.tencent.cloudsdk.cn;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.cloudsdk.eh;
import com.tencent.cloudsdk.ep;
import com.tencent.cloudsdk.eq;
import com.tencent.cloudsdk.er;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class HttpOptimizer {
    public static final String TAG = "HttpOptimizer";
    private URL a;

    public HttpOptimizer(String r2_String) throws MalformedURLException {
        this.a = new URL(r2_String);
    }

    private static cn a(List r0_List, cn r1_cn) {
        return r1_cn;
    }

    private static void a(cn r7_cn) {
        int r3i = XListViewHeader.STATE_REFRESHING;
        String r0_String = "[OCIP]";
        if (r7_cn.b == 1) {
            r0_String = "[RSIP]";
        } else if (r7_cn.b == 2) {
            r0_String = "[RS_SPEED_IP]";
        }
        String r1_String = TAG;
        Object[] r3_ObjectA = new Object[r3i];
        r3_ObjectA[0] = r7_cn.a;
        r3_ObjectA[1] = r0_String;
        er.a(r1_String, String.format("\u67e5\u627e\u66f4\u5feb\u7684IP\uff0c\u6700\u7ec8\u9009\u62e9%s%s", r3_ObjectA));
    }

    public static cn findBetterAddress(String r5_String, int r6i) {
        cn r1_cn = new cn();
        r1_cn.a = r5_String;
        if (r6i < 0) {
            r6i = 80;
        }
        r1_cn.c = r6i;
        if (GlobalContext.getContext() == null) {
            return r1_cn;
        }
        if (ep.b(r5_String)) {
            return r1_cn;
        }
        try {
            List r2_List = ep.a(GlobalContext.getContext(), r5_String, true);
            eh.a().a(r5_String, r1_cn.c, true);
            if (r2_List == null || r2_List.size() <= 0) {
                return r1_cn;
            }
            cn r0_cn = (cn) r2_List.get(0);
            if (r0_cn.b != 0) {
                r0_cn.c = r1_cn.c;
            }
            r0_cn = a(r2_List, r0_cn);
            a(r0_cn);
            return r0_cn;
        } catch (eq e) {
            Throwable r0_Throwable = e;
            WnsClientLog.w(TAG, r0_Throwable.getMessage(), r0_Throwable);
            return r1_cn;
        } catch (NoClassDefFoundError e_2) {
            WnsClientLog.e(TAG, "NoClassDefFoundError", e_2);
            return r1_cn;
        }
    }

    public String findBetterUrl() {
        URL r1_URL = this.a;
        if (!r1_URL.getProtocol().equals(ImageFetcher.HTTP_CACHE_DIR)) {
            return r1_URL.toString();
        }
        URL r0_URL;
        cn r2_cn = findBetterAddress(r1_URL.getHost(), r1_URL.getPort());
        try {
            r0_URL = new URL(ImageFetcher.HTTP_CACHE_DIR, r2_cn.a, r2_cn.c, r1_URL.getFile());
        } catch (MalformedURLException e) {
            WnsClientLog.e(TAG, new StringBuilder("findBetterUrl, ").append(r1_URL).toString(), e);
            r0_URL = r1_URL;
        }
        return r0_URL.toString();
    }
}