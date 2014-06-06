package com.tencent.qc.stat;

// compiled from: ProGuard
public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6);

    int g;

    static {
        a = new StatReportStrategy("INSTANT", 0, 1);
        b = new StatReportStrategy("ONLY_WIFI", 1, 2);
        c = new StatReportStrategy("BATCH", 2, 3);
        d = new StatReportStrategy("APP_LAUNCH", 3, 4);
        e = new StatReportStrategy("DEVELOPER", 4, 5);
        f = new StatReportStrategy("PERIOD", 5, 6);
        StatReportStrategy[] r0_StatReportStrategyA = new StatReportStrategy[6];
        r0_StatReportStrategyA[0] = a;
        r0_StatReportStrategyA[1] = b;
        r0_StatReportStrategyA[2] = c;
        r0_StatReportStrategyA[3] = d;
        r0_StatReportStrategyA[4] = e;
        r0_StatReportStrategyA[5] = f;
        h = r0_StatReportStrategyA;
    }

    private StatReportStrategy(int r3i) {
        this.g = r3i;
    }

    public static StatReportStrategy a(int r5i) {
        StatReportStrategy[] r2_StatReportStrategyA = a();
        int r3i = r2_StatReportStrategyA.length;
        int r1i = 0;
        while (r1i < r3i) {
            StatReportStrategy r0_StatReportStrategy = r2_StatReportStrategyA[r1i];
            if (r5i == r0_StatReportStrategy.b()) {
                return r0_StatReportStrategy;
            }
            r1i++;
        }
        return null;
    }

    public static StatReportStrategy[] a() {
        return (StatReportStrategy[]) h.clone();
    }

    public int b() {
        return this.g;
    }
}