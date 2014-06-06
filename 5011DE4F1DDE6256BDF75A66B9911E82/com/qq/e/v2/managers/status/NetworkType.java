package com.qq.e.v2.managers.status;

public enum NetworkType {
    UNKNOWN(0, 1),
    WIFI(1, 2),
    NET_2G(2, 4),
    NET_3G(3, 8),
    NET_4G(4, 22);

    private int a;
    private int b;

    static {
        UNKNOWN = new NetworkType("UNKNOWN", 0, 0, 1);
        WIFI = new NetworkType("WIFI", 1, 1, 2);
        NET_2G = new NetworkType("NET_2G", 2, 2, 4);
        NET_3G = new NetworkType("NET_3G", 3, 3, 8);
        NET_4G = new NetworkType("NET_4G", 4, 4, 22);
        NetworkType[] r0_NetworkTypeA = new NetworkType[5];
        r0_NetworkTypeA[0] = UNKNOWN;
        r0_NetworkTypeA[1] = WIFI;
        r0_NetworkTypeA[2] = NET_2G;
        r0_NetworkTypeA[3] = NET_3G;
        r0_NetworkTypeA[4] = NET_4G;
        c = r0_NetworkTypeA;
    }

    private NetworkType(int r3i, int r4i) {
        this.a = r3i;
        this.b = r4i;
    }

    public final int getConnValue() {
        return this.a;
    }

    public final int getPermValue() {
        return this.b;
    }
}