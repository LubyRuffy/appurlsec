package com.tencent.cloudsdk;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public class ep {
    private static final String a;

    static {
        a = ep.class.getSimpleName();
    }

    public static ck a(List r8_List) throws eq {
        if (r8_List == null || r8_List.size() == 0) {
            throw new eq("getAllIpInfos() return null");
        } else {
            ck r1_ck = new ck();
            List r2_List = new ArrayList();
            List r3_List = new ArrayList();
            List r4_List = new ArrayList();
            Iterator r5_Iterator = r8_List.iterator();
            while (r5_Iterator.hasNext()) {
                cn r0_cn = (cn) r5_Iterator.next();
                if (r0_cn.b == 0) {
                    r2_List.add(r0_cn);
                }
                if (r0_cn.b == 1) {
                    r3_List.add(r0_cn);
                }
                if (r0_cn.b == 2) {
                    r4_List.add(r0_cn);
                }
            }
            r1_ck.a = r2_List;
            r1_ck.b = r3_List;
            r1_ck.c = r4_List;
            return r1_ck;
        }
    }

    public static String a(long r4j) {
        StringBuffer r0_StringBuffer = new StringBuffer(RContactStorage.PRIMARY_KEY);
        r0_StringBuffer.append(String.valueOf(r4j >>> 24));
        r0_StringBuffer.append(".");
        r0_StringBuffer.append(String.valueOf((16777215 & r4j) >>> 16));
        r0_StringBuffer.append(".");
        r0_StringBuffer.append(String.valueOf((65535 & r4j) >>> 8));
        r0_StringBuffer.append(".");
        r0_StringBuffer.append(String.valueOf(255 & r4j));
        return r0_StringBuffer.toString();
    }

    public static List a(Context r5_Context, String r6_String, boolean r7z) throws eq {
        cl r0_cl = bb.a(r6_String, r7z);
        if (r0_cl == null || r0_cl.f == null || r0_cl.f.size() == 0) {
            throw new eq("queryFromOc() return null");
        } else {
            et.a(((long) r0_cl.e) * 1000);
            List r0_List = r0_cl.f;
            if (r0_List == null || r0_List.size() == 0) {
                throw new eq("getIpInfosFromDB return null");
            } else {
                List r1_List = new ArrayList();
                Iterator r2_Iterator = r0_List.iterator();
                while (r2_Iterator.hasNext()) {
                    ch r0_ch = (ch) r2_Iterator.next();
                    cn r3_cn = new cn();
                    r3_cn.a = r0_ch.a();
                    r3_cn.c = r0_ch.e();
                    r3_cn.d = r0_ch.c();
                    r3_cn.b = r0_ch.b();
                    r1_List.add(r3_cn);
                }
                return r1_List;
            }
        }
    }

    public static List a(ck r2_ck) throws eq {
        if (r2_ck != null && r2_ck.b != null && r2_ck.b.size() > 0) {
            return r2_ck.b;
        }
        throw new eq("getRsIpItem return null");
    }

    public static void a(String r6_String, String r7_String, String r8_String, boolean r9z) {
        String r2_String = em.c();
        if (r7_String.equals(r8_String)) {
        } else {
            cf.a(GlobalContext.getContext()).a(r6_String, r2_String, r9z, r7_String, r8_String);
        }
    }

    public static byte[] a(String r4_String) {
        byte[] r1_byteA = new byte[4];
        String[] r2_StringA = r4_String.split("\\.");
        int r0i = 0;
        while (r0i < r2_StringA.length) {
            try {
                r1_byteA[r0i] = (byte) Integer.parseInt(r2_StringA[r0i]);
            } catch (NumberFormatException e) {
            }
            r0i++;
        }
        return r1_byteA;
    }

    public static boolean b(String r1_String) {
        return r1_String.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    }

    public static long c(String r12_String) {
        if (r12_String == null || r12_String.equals("0") || TextUtils.isEmpty(r12_String)) {
            return 0;
        }
        String r0_String = r12_String.trim();
        long[] r1_longA = new long[4];
        int r2i = r0_String.indexOf(".");
        int r3i = r0_String.indexOf(".", r2i + 1);
        int r4i = r0_String.indexOf(".", r3i + 1);
        try {
            r1_longA[0] = Long.parseLong(r0_String.substring(0, r2i));
            r1_longA[1] = Long.parseLong(r0_String.substring(r2i + 1, r3i));
            r1_longA[2] = Long.parseLong(r0_String.substring(r3i + 1, r4i));
            r1_longA[3] = Long.parseLong(r0_String.substring(r4i + 1));
        } catch (NumberFormatException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e(a, r0_Throwable.getMessage(), r0_Throwable);
        }
        return r1_longA[3] + r1_longA[0] << 24 + r1_longA[1] << 16 + r1_longA[2] << 8;
    }
}