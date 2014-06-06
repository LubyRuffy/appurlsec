package com.tencent.cloudsdk;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

// compiled from: SourceFile
public class bd extends Thread {
    private Socket a;
    private String b;
    private int c;
    private byte[] d;
    private be e;
    private int f;

    public bd(String r2_String, int r3i, int r4i, byte[] r5_byteA, be r6_be) {
        this.a = null;
        this.b = r2_String;
        this.c = r3i;
        this.f = r4i;
        this.d = r5_byteA;
        this.e = r6_be;
    }

    private ByteArrayOutputStream a(InputStream r8_InputStream) throws IOException {
        ByteArrayOutputStream r1_ByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] r2_byteA = new byte[1000];
        int r0i = r8_InputStream.read(r2_byteA);
        if (r0i > 0) {
            byte[] r3_byteA = new byte[2];
            r3_byteA[0] = r2_byteA[0];
            r3_byteA[1] = r2_byteA[1];
            int r3i = eo.b(r3_byteA);
            r1_ByteArrayOutputStream.write(r2_byteA, 0, r0i);
            while (r0i < r3i) {
                int r4i = r8_InputStream.read(r2_byteA);
                if (r4i > 0) {
                    r1_ByteArrayOutputStream.write(r2_byteA, r0i, r4i);
                    r0i += r4i;
                } else {
                    break;
                }
            }
        }
        if (r8_InputStream != null) {
            r8_InputStream.close();
        }
        return r1_ByteArrayOutputStream;
    }

    public void run() {
        long r6j;
        long r4j;
        Throwable r1_Throwable;
        r6j = Thread.currentThread().getId();
        r4j = System.currentTimeMillis();
        try {
            if (this.e != null) {
                this.e.a(this.b, this.c, r4j, r6j);
            }
            this.a = new Socket();
            this.a.connect(new InetSocketAddress(this.b, this.c), this.f);
            this.a.setSoTimeout(this.f * 2);
            if (this.e != null) {
                this.e.a(System.currentTimeMillis() - r4j);
            }
            long r1j = System.currentTimeMillis();
            DataOutputStream r8_DataOutputStream = new DataOutputStream(this.a.getOutputStream());
            r8_DataOutputStream.write(this.d);
            r8_DataOutputStream.flush();
            if (this.e != null) {
                this.e.b(System.currentTimeMillis() - r1j);
            }
            r1j = System.currentTimeMillis();
            ByteArrayOutputStream r3_ByteArrayOutputStream = a(this.a.getInputStream());
            if (this.e != null) {
                this.e.c(System.currentTimeMillis() - r1j);
            }
            if (r3_ByteArrayOutputStream.size() <= 0) {
                if (this.e != null) {
                    this.e.a(this.b, this.c, r4j, "Not data response.", AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY, r6j);
                }
            } else if (this.e != null) {
                this.e.a(this.b, this.c, r4j, r3_ByteArrayOutputStream.toByteArray(), r6j);
            }
            if (this.a == null || this.a.isClosed()) {
                this.a = null;
            } else {
                try {
                    this.a.close();
                } catch (IOException e) {
                    r1_Throwable = e;
                    WnsClientLog.e("QueryTcpClient", r1_Throwable.getMessage(), r1_Throwable);
                }
                this.a = null;
            }
        } catch (Throwable th) {
            if (this.a == null || this.a.isClosed()) {
            } else {
                this.a.close();
            }
        }
    }
}