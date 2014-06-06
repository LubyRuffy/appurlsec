package com.tencent.qc.stat.event;

// compiled from: ProGuard
public enum EventType {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001);

    private int f;

    static {
        a = new EventType("PAGE_VIEW", 0, 1);
        b = new EventType("SESSION_ENV", 1, 2);
        c = new EventType("ERROR", 2, 3);
        d = new EventType("CUSTOM", 3, 1000);
        e = new EventType("ADDITION", 4, 1001);
        EventType[] r0_EventTypeA = new EventType[5];
        r0_EventTypeA[0] = a;
        r0_EventTypeA[1] = b;
        r0_EventTypeA[2] = c;
        r0_EventTypeA[3] = d;
        r0_EventTypeA[4] = e;
        g = r0_EventTypeA;
    }

    private EventType(int r3i) {
        this.f = r3i;
    }

    public int a() {
        return this.f;
    }
}