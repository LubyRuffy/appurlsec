package com.tencent.cloudsdk;

import java.io.File;

// compiled from: SourceFile
public class b {
    public static final byte[] a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;

    static {
        a = new byte[0];
        b = new StringBuilder("Tencent").append(File.separator).append("CloudSdk").toString();
        c = new StringBuilder(String.valueOf(b)).append(File.separator).append("Plugins").toString();
        d = new StringBuilder(String.valueOf(b)).append(File.separator).append("Plugins").append(File.separator).append("Download").toString();
        e = new StringBuilder(String.valueOf(b)).append(File.separator).append("Plugins").append(File.separator).append("OpDexs").toString();
    }
}