package com.tencent.cloudsdk;

import android.os.Bundle;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public class dz extends dx {
    private String c(Bundle r6_Bundle) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        if (r6_Bundle != null) {
            Iterator r2_Iterator = r6_Bundle.keySet().iterator();
            while (r2_Iterator.hasNext()) {
                String r0_String = (String) r2_Iterator.next();
                if (r1_StringBuilder.length() == 0) {
                    r1_StringBuilder.append(new StringBuilder(String.valueOf(r0_String)).append("=").append(r6_Bundle.get(r0_String)).toString());
                } else {
                    r1_StringBuilder.append(new StringBuilder("&").append(r0_String).append("=").append(r6_Bundle.get(r0_String)).toString());
                }
            }
        }
        return r1_StringBuilder.toString();
    }

    protected boolean a() {
        return false;
    }

    protected byte[] b(Bundle r4_Bundle) {
        String r0_String = c(r4_Bundle);
        byte[] r1_byteA = eo.a((short) r0_String.length());
        ByteBuffer r2_ByteBuffer = ByteBuffer.allocate(r0_String.getBytes().length + 2);
        r2_ByteBuffer.put(r1_byteA);
        r2_ByteBuffer.put(r0_String.getBytes());
        return r2_ByteBuffer.array();
    }

    protected byte[] c(List r3_List, ea r4_ea) {
        throw new RuntimeException("pkgBatchFrame not supported!");
    }
}