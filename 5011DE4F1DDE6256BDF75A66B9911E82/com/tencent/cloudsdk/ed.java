package com.tencent.cloudsdk;

import android.os.Bundle;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

// compiled from: SourceFile
public class ed extends dx {
    private byte[] a(String r5_String) {
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream r0_GZIPOutputStream = new GZIPOutputStream(r1_OutputStream);
            r0_GZIPOutputStream.write(r5_String.getBytes());
            r0_GZIPOutputStream.close();
        } catch (IOException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("ZipReportTool", r0_Throwable.getMessage(), r0_Throwable);
        }
        return r1_OutputStream.toByteArray();
    }

    private byte[] b(byte[] r3_byteA) {
        byte[] r0_byteA = eo.a((short) r3_byteA.length);
        ByteBuffer r1_ByteBuffer = ByteBuffer.allocate(r3_byteA.length + 2);
        r1_ByteBuffer.put(r0_byteA);
        r1_ByteBuffer.put(r3_byteA);
        return r1_ByteBuffer.array();
    }

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

    private byte[] c(byte[] r3_byteA) {
        short r0s = (short) (r3_byteA.length + 5);
        byte[] r1_byteA = eo.a(r0s);
        ByteBuffer r0_ByteBuffer = ByteBuffer.allocate(r0s + 2);
        r0_ByteBuffer.put(r1_byteA);
        r0_ByteBuffer.put("gzip\u0000".getBytes());
        r0_ByteBuffer.put(r3_byteA);
        return r0_ByteBuffer.array();
    }

    protected boolean a() {
        return true;
    }

    protected byte[] b(Bundle r2_Bundle) {
        return c(a(c(r2_Bundle)));
    }

    protected byte[] c(List r14_List, ea r15_ea) {
        int r0i;
        GZIPOutputStream r1_GZIPOutputStream;
        Throwable r0_Throwable;
        String r2_String = null;
        int r1i = 0;
        OutputStream r6_OutputStream = new ByteArrayOutputStream();
        try {
            r0i = r1i;
            r1_GZIPOutputStream = new GZIPOutputStream(r6_OutputStream);
        } catch (IOException e) {
            r0_Throwable = e;
            WnsClientLog.e("ZipReportTool", r0_Throwable.getMessage(), r0_Throwable);
            r1_GZIPOutputStream = null;
            r0i = 1;
        }
        Iterator r7_Iterator = r14_List.iterator();
        String r4_String = r2_String;
        String r5_String = r2_String;
        int r2i = r0i;
        while (r7_Iterator.hasNext()) {
            String r0_String = c((Bundle) r7_Iterator.next());
            if (r5_String == null) {
                r4_String = r0_String;
                r5_String = r0_String;
            } else {
                r5_String = new StringBuilder("\n").append(r0_String).toString();
                r4_String = new StringBuilder(String.valueOf(r4_String)).append("\n").append(r0_String).toString();
            }
            if (r2i == 0) {
                try {
                    r1_GZIPOutputStream.write(r5_String.getBytes());
                    r1_GZIPOutputStream.flush();
                    r0i = r2i;
                } catch (IOException e_2) {
                    r0_Throwable = e_2;
                    WnsClientLog.e("ZipReportTool", r0_Throwable.getMessage(), r0_Throwable);
                    r0i = 1;
                }
            } else {
                r0i = r2i;
            }
            if (r15_ea != null) {
                if (r0i == 0 || ((long) r4_String.getBytes().length) <= r15_ea.a()) {
                    if (r0i != 0 || ((long) r6_OutputStream.toByteArray().length) <= r15_ea.a()) {
                        r2i = r0i;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            r2i = r0i;
        }
        r0i = r2i;
        if (r1_GZIPOutputStream != null) {
            try {
                r1_GZIPOutputStream.close();
            } catch (IOException e_3) {
                Throwable r1_Throwable = e_3;
                WnsClientLog.e("ZipReportTool", r1_Throwable.getMessage(), r1_Throwable);
            }
        }
        return r0i != 0 ? b(r4_String.getBytes()) : c(r6_OutputStream.toByteArray());
    }
}