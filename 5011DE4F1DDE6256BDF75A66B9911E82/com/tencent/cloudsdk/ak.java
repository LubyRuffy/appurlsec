package com.tencent.cloudsdk;

import android.os.Environment;

// compiled from: SourceFile
public class ak {
    public static boolean a() {
        String r0_String = Environment.getExternalStorageState();
        return "mounted".equals(r0_String) || "mounted_ro".equals(r0_String);
    }

    public static al b() {
        return a() ? al.b(Environment.getExternalStorageDirectory()) : null;
    }
}