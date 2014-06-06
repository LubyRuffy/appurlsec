package com.tencent.cloudsdk;

import android.os.Handler;
import android.os.Message;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;

// compiled from: SourceFile
public class er {
    private static Handler a;

    public static void a(String r3_String, String r4_String) {
        if (a != null) {
            es r0_es = new es();
            r0_es.a = r3_String;
            r0_es.b = r4_String;
            Message r1_Message = a.obtainMessage(0);
            r1_Message.obj = r0_es;
            a.sendMessage(r1_Message);
        }
        WnsClientLog.i(r3_String, r4_String);
    }
}