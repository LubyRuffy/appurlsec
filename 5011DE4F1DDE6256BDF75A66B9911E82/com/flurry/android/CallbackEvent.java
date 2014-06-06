package com.flurry.android;

// compiled from: SourceFile
public final class CallbackEvent {
    public static final int ADS_LOADED_FROM_CACHE = 201;
    public static final int ADS_UPDATED = 202;
    public static final int ERROR_MARKET_LAUNCH = 101;
    private int a;
    private long b;
    private String c;

    CallbackEvent(int r3i) {
        this.a = r3i;
        this.b = System.currentTimeMillis();
    }

    public final String getMessage() {
        return this.c;
    }

    public final long getTimestamp() {
        return this.b;
    }

    public final int getType() {
        return this.a;
    }

    public final void setMessage(String r1_String) {
        this.c = r1_String;
    }

    public final void setTimestamp(long r1j) {
        this.b = r1j;
    }
}