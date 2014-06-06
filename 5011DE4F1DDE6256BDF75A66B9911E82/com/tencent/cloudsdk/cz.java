package com.tencent.cloudsdk;

import android.content.Context;
import com.tencent.cloudsdk.common.utils.ClientIdManager;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class cz {
    private static ec a;
    private static du b;
    private static dn c;
    private static ds d;

    public static eb a() {
        return a.a(1);
    }

    public static void a(Context r6_Context) {
        a = new ec();
        b = new du();
        c = new dn();
        d = new ds(b, c, a.a(XListViewHeader.STATE_REFRESHING), a.a(XListViewHeader.STATE_REFRESHING));
        dl.a();
        d.b();
        d.a();
        ClientIdManager.getInstance();
        bv.a();
    }

    public static du b() {
        return b;
    }

    public static dn c() {
        return c;
    }

    public static dt d() {
        return d.f();
    }
}