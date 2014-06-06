package com.tencent.cloudsdk;

import android.os.Handler;
import android.os.HandlerThread;

// compiled from: SourceFile
public class en {
    public static Handler a;
    private static HandlerThread b;

    static {
        b = null;
        a = null;
        b = new HandlerThread("asynThread");
        b.start();
        a = new Handler(b.getLooper());
    }
}