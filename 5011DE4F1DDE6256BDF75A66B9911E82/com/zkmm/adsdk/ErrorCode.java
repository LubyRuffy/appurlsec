package com.zkmm.adsdk;

// compiled from: SourceFile
public final class ErrorCode {
    int a;
    String b;

    protected ErrorCode(int r1i, String r2_String) {
        this.a = r1i;
        this.b = r2_String;
    }

    public final int getErrorCode() {
        return this.a;
    }

    public final String getErrorString() {
        return this.b;
    }

    public final String toString() {
        return this.b;
    }
}