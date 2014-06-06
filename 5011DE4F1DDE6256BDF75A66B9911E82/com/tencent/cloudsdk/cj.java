package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;

// compiled from: SourceFile
public class cj implements Cloneable {
    public String a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public long i;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("FailCountItem", r0_Throwable.getMessage(), r0_Throwable);
            return null;
        }
    }
}