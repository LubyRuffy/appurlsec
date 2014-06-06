package com.tencent.cloudsdk;

import android.os.Handler;
import android.os.Message;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.lang.ref.WeakReference;

// compiled from: SourceFile
public class av {
    private static WeakReference a;

    static {
        a = new WeakReference(null);
    }

    public static void a(String r3_String, String r4_String) {
        if (a.get() != null) {
            aw r1_aw = new aw();
            r1_aw.a = r3_String;
            r1_aw.b = r4_String;
            Message r2_Message = ((Handler) a.get()).obtainMessage(0);
            r2_Message.obj = r1_aw;
            ((Handler) a.get()).sendMessage(r2_Message);
        }
        WnsClientLog.i(r3_String, r4_String);
    }
}