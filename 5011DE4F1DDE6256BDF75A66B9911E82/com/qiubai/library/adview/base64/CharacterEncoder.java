package com.qiubai.library.adview.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public abstract class CharacterEncoder {
    protected PrintStream a;

    private byte[] a(ByteBuffer r5_ByteBuffer) {
        byte[] r0_byteA;
        if (r5_ByteBuffer.hasArray()) {
            r0_byteA = r5_ByteBuffer.array();
            if (r0_byteA.length == r5_ByteBuffer.capacity() && r0_byteA.length == r5_ByteBuffer.remaining()) {
                r5_ByteBuffer.position(r5_ByteBuffer.limit());
            } else {
                r0_byteA = null;
            }
        } else {
            r0_byteA = null;
        }
        if (r0_byteA != null) {
            return r0_byteA;
        }
        r0_byteA = new byte[r5_ByteBuffer.remaining()];
        r5_ByteBuffer.get(r0_byteA);
        return r0_byteA;
    }

    protected abstract int a();

    protected int a(InputStream r4_InputStream, byte[] r5_byteA) throws IOException {
        int r0i = 0;
        while (r0i < r5_byteA.length) {
            int r1i = r4_InputStream.read();
            if (r1i == -1) {
                return r0i;
            }
            r5_byteA[r0i] = (byte) r1i;
            r0i++;
        }
        return r5_byteA.length;
    }

    protected void a(OutputStream r2_OutputStream) throws IOException {
        this.a = new PrintStream(r2_OutputStream);
    }

    protected void a(OutputStream r1_OutputStream, int r2i) throws IOException {
    }

    protected abstract void a(OutputStream r1_OutputStream, byte[] r2_byteA, int r3i, int r4i) throws IOException;

    protected abstract int b();

    protected void b(OutputStream r1_OutputStream) throws IOException {
    }

    protected void c(OutputStream r2_OutputStream) throws IOException {
        this.a.println();
    }

    public String encode(ByteBuffer r2_ByteBuffer) {
        return encode(a(r2_ByteBuffer));
    }

    public String encode(byte[] r3_byteA) {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(r3_byteA), r0_OutputStream);
            return r0_OutputStream.toString("8859_1");
        } catch (Exception e) {
            throw new Error("CharacterEncoder.encode internal error");
        }
    }

    public void encode(InputStream r5_InputStream, OutputStream r6_OutputStream) throws IOException {
        byte[] r1_byteA = new byte[b()];
        a(r6_OutputStream);
        while (true) {
            int r2i = a(r5_InputStream, r1_byteA);
            if (r2i == 0) {
            } else {
                a(r6_OutputStream, r2i);
                int r0i = 0;
                while (r0i < r2i) {
                    if (a() + r0i <= r2i) {
                        a(r6_OutputStream, r1_byteA, r0i, a());
                    } else {
                        a(r6_OutputStream, r1_byteA, r0i, r2i - r0i);
                    }
                    r0i += a();
                }
                if (r2i >= b()) {
                    c(r6_OutputStream);
                }
            }
            b(r6_OutputStream);
            return;
        }
    }

    public void encode(ByteBuffer r2_ByteBuffer, OutputStream r3_OutputStream) throws IOException {
        encode(a(r2_ByteBuffer), r3_OutputStream);
    }

    public void encode(byte[] r2_byteA, OutputStream r3_OutputStream) throws IOException {
        encode(new ByteArrayInputStream(r2_byteA), r3_OutputStream);
    }

    public String encodeBuffer(ByteBuffer r2_ByteBuffer) {
        return encodeBuffer(a(r2_ByteBuffer));
    }

    public String encodeBuffer(byte[] r3_byteA) {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        try {
            encodeBuffer(new ByteArrayInputStream(r3_byteA), r0_OutputStream);
            return r0_OutputStream.toString();
        } catch (Exception e) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
    }

    public void encodeBuffer(InputStream r5_InputStream, OutputStream r6_OutputStream) throws IOException {
        byte[] r1_byteA = new byte[b()];
        a(r6_OutputStream);
        while (true) {
            int r2i = a(r5_InputStream, r1_byteA);
            if (r2i == 0) {
            } else {
                a(r6_OutputStream, r2i);
                int r0i = 0;
                while (r0i < r2i) {
                    if (a() + r0i <= r2i) {
                        a(r6_OutputStream, r1_byteA, r0i, a());
                    } else {
                        a(r6_OutputStream, r1_byteA, r0i, r2i - r0i);
                    }
                    r0i += a();
                }
                c(r6_OutputStream);
                if (r2i < b()) {
                }
            }
            b(r6_OutputStream);
            return;
        }
    }

    public void encodeBuffer(ByteBuffer r2_ByteBuffer, OutputStream r3_OutputStream) throws IOException {
        encodeBuffer(a(r2_ByteBuffer), r3_OutputStream);
    }

    public void encodeBuffer(byte[] r2_byteA, OutputStream r3_OutputStream) throws IOException {
        encodeBuffer(new ByteArrayInputStream(r2_byteA), r3_OutputStream);
    }
}