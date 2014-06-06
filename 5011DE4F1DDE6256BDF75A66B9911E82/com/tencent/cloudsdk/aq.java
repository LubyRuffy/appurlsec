package com.tencent.cloudsdk;

import android.os.HandlerThread;

// compiled from: SourceFile
public class aq {
    public static HandlerThread a;

    static {
        a = null;
        a = new HandlerThread("asynThread");
        a.start();
    }
}