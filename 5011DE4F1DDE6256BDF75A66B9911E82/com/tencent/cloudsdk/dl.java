package com.tencent.cloudsdk;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.tencent.cloudsdk.common.utils.ClientIdManager;
import com.tencent.tauth.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: SourceFile
public class dl {
    private static AtomicBoolean a;

    static {
        a = new AtomicBoolean(false);
    }

    public static void a() {
        if (a.compareAndSet(false, true)) {
            b();
        }
    }

    static void b() {
        long r0j = bv.c().e();
        en.a.postDelayed(new di(), r0j);
    }

    public void a(cj r5_cj) {
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putInt(Constants.PARAM_TYPE_ID, 1000184);
        r0_Bundle.putString("sdkv", "1.2.0");
        r0_Bundle.putLong("dip", ep.c(r5_cj.a));
        r0_Bundle.putInt("dport", r5_cj.b);
        r0_Bundle.putInt("dtype", r5_cj.e);
        r0_Bundle.putInt("nt", r5_cj.f);
        r0_Bundle.putInt("sp", r5_cj.g);
        r0_Bundle.putInt("os", 1);
        r0_Bundle.putString("osv", VERSION.RELEASE);
        r0_Bundle.putInt("cnt", r5_cj.d);
        r0_Bundle.putInt("ec", r5_cj.c);
        r0_Bundle.putInt("proto", r5_cj.h);
        r0_Bundle.putInt("seq", ClientIdManager.getInstance().getClientId());
        List r1_List = new ArrayList();
        r1_List.add(r0_Bundle);
        cz.a().a(r1_List);
    }
}