package com.crashlytics.android.internal;

// compiled from: SourceFile
public enum ap {
    WIFI_MAC_ADDRESS(1),
    BLUETOOTH_MAC_ADDRESS(2),
    ANDROID_ID(100),
    ANDROID_DEVICE_ID(101),
    ANDROID_SERIAL(102);

    public final int f;

    static {
        a = new ap("WIFI_MAC_ADDRESS", 0, 1);
        b = new ap("BLUETOOTH_MAC_ADDRESS", 1, 2);
        c = new ap("ANDROID_ID", 2, 100);
        d = new ap("ANDROID_DEVICE_ID", 3, 101);
        e = new ap("ANDROID_SERIAL", 4, 102);
        ap[] r0_apA = new ap[5];
        r0_apA[0] = a;
        r0_apA[1] = b;
        r0_apA[2] = c;
        r0_apA[3] = d;
        r0_apA[4] = e;
        g = r0_apA;
    }

    private ap(int r3i) {
        this.f = r3i;
    }
}