package com.amap.api.location.core;

import com.aps.p;

public class CoordinateConvert {
    public static GeoPoint fromGpsToAMap(double r7d, double r9d) {
        double[] r0_doubleA = p.a(r9d, r7d);
        return new GeoPoint((int) (r0_doubleA[1] * 1000000.0d), (int) (r0_doubleA[0] * 1000000.0d));
    }

    public static double[] fromSeveralGpsToAMap(String r9_String) {
        String[] r2_StringA = r9_String.split(",");
        int r3i = r2_StringA.length;
        double[] r4_doubleA = new double[r3i];
        int r0i = 0;
        while (r0i < r3i / 2) {
            double[] r5_doubleA = p.a(Double.parseDouble(r2_StringA[r0i * 2]), Double.parseDouble(r2_StringA[r0i * 2 + 1]));
            r4_doubleA[r0i * 2] = r5_doubleA[0];
            r4_doubleA[r0i * 2 + 1] = r5_doubleA[1];
            r0i++;
        }
        return r4_doubleA;
    }

    public static double[] fromSeveralGpsToAMap(double[] r8_doubleA) {
        int r2i = r8_doubleA.length;
        double[] r3_doubleA = new double[r2i];
        int r0i = 0;
        while (r0i < r2i / 2) {
            double[] r4_doubleA = p.a(r8_doubleA[r0i * 2], r8_doubleA[r0i * 2 + 1]);
            r3_doubleA[r0i * 2] = r4_doubleA[0];
            r3_doubleA[r0i * 2 + 1] = r4_doubleA[1];
            r0i++;
        }
        return r3_doubleA;
    }
}