package com.qq.e.v2.managers.status;

public enum Carrier {
    UNKNOWN(0),
    CMCC(1),
    UNICOM(2),
    TELECOM(3);

    private int a;

    static {
        UNKNOWN = new Carrier("UNKNOWN", 0, 0);
        CMCC = new Carrier("CMCC", 1, 1);
        UNICOM = new Carrier("UNICOM", 2, 2);
        TELECOM = new Carrier("TELECOM", 3, 3);
        Carrier[] r0_CarrierA = new Carrier[4];
        r0_CarrierA[0] = UNKNOWN;
        r0_CarrierA[1] = CMCC;
        r0_CarrierA[2] = UNICOM;
        r0_CarrierA[3] = TELECOM;
        b = r0_CarrierA;
    }

    private Carrier(int r3i) {
        this.a = r3i;
    }

    public final int getValue() {
        return this.a;
    }
}