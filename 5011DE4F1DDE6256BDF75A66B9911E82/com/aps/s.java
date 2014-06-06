package com.aps;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;
import qsbk.app.share.ShareUtils;

public final class s {
    private boolean a;
    private String b;
    private boolean c;
    private double d;
    private double e;

    protected s(List r5_List, String r6_String, String r7_String, String r8_String) {
        this.a = false;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = false;
        this.d = 0.0d;
        this.e = 0.0d;
        this.b = r8_String;
        d();
    }

    private void d() {
        int r0i;
        int r3i = 0;
        String r4_String = this.b;
        if (r4_String == null || r4_String.length() <= 8) {
            r0i = 0;
        } else {
            r0i = 1;
            int r2i = 0;
            while (r0i < r4_String.length() - 3) {
                r2i ^= r4_String.charAt(r0i);
                r0i++;
            }
            if (Integer.toHexString(r2i).equalsIgnoreCase(r4_String.substring(r4_String.length() - 2, r4_String.length()))) {
                r0i = 1;
            }
            r0i = 0;
        }
        if (r0i != 0) {
            String r2_String = this.b.substring(0, this.b.length() - 3);
            r0i = 0;
            while (r3i < r2_String.length()) {
                if (r2_String.charAt(r3i) == ',') {
                    r0i++;
                }
                r3i++;
            }
            String[] r0_StringA = r2_String.split(",", r0i + 1);
            if (r0_StringA.length < ShareUtils.SHARE_COPY) {
                return;
            }
            if (r0_StringA[2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 3].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 1].equals(RContactStorage.PRIMARY_KEY)) {
                this.a = this.c;
            } else {
                Integer.valueOf(r0_StringA[2]).intValue();
                this.d = Double.valueOf(r0_StringA[r0_StringA.length - 3]).doubleValue();
                this.e = Double.valueOf(r0_StringA[r0_StringA.length - 2]).doubleValue();
                this.c = true;
            }
        }
        this.a = this.c;
    }

    protected final boolean a() {
        return this.a;
    }

    protected final double b() {
        return this.d;
    }

    protected final double c() {
        return this.e;
    }
}