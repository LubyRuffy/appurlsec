package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.Map;

// compiled from: SourceFile
public enum a {
    MNA("mna", 1);

    private static Map d;
    private String b;
    private int c;

    static {
        int r0i = 0;
        String r3_String = "mna";
        a = new a("MNA", 0, "mna", 1);
        a[] r1_aA = new a[1];
        r1_aA[0] = a;
        e = r1_aA;
        d = new HashMap();
        r1_aA = values();
        int r2i = r1_aA.length;
        while (r0i < r2i) {
            a r3_a = r1_aA[r0i];
            d.put(Integer.valueOf(r3_a.b()), r3_a);
            r0i++;
        }
    }

    private a(String r4_String, int r5i) {
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = 0;
        this.b = r4_String;
        this.c = r5i;
    }

    public static a a_(int r2i) {
        return (a) d.get(Integer.valueOf(r2i));
    }

    public String a_() {
        return this.b;
    }

    public int b() {
        return this.c;
    }
}