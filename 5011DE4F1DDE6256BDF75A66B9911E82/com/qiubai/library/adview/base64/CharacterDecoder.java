package com.qiubai.library.adview.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

public abstract class CharacterDecoder {
    protected abstract int a();

    protected int a(InputStream r5_InputStream, byte[] r6_byteA, int r7i, int r8i) throws IOException {
        int r1i = 0;
        while (r1i < r8i) {
            int r2i = r5_InputStream.read();
            if (r2i == -1) {
                return r1i != 0 ? r1i : -1;
            } else {
                r6_byteA[r1i + r7i] = (byte) r2i;
                r1i++;
            }
        }
        return r8i;
    }

    protected void a(PushbackInputStream r1_PushbackInputStream, OutputStream r2_OutputStream) throws IOException {
    }

    protected void a(PushbackInputStream r2_PushbackInputStream, OutputStream r3_OutputStream, int r4i) throws IOException {
        throw new CEStreamExhausted();
    }

    protected abstract int b();

    protected void b(PushbackInputStream r1_PushbackInputStream, OutputStream r2_OutputStream) throws IOException {
    }

    protected int c(PushbackInputStream r2_PushbackInputStream, OutputStream r3_OutputStream) throws IOException {
        return b();
    }

    protected void d(PushbackInputStream r1_PushbackInputStream, OutputStream r2_OutputStream) throws IOException {
    }

    public void decodeBuffer(InputStream r7_InputStream, OutputStream r8_OutputStream) throws IOException {
        PushbackInputStream r3_PushbackInputStream = new PushbackInputStream(r7_InputStream);
        a(r3_PushbackInputStream, r8_OutputStream);
        int r0i = 0;
        while (true) {
            try {
                int r4i = c(r3_PushbackInputStream, r8_OutputStream);
                int r1i = 0;
                while (a() + r1i < r4i) {
                    a(r3_PushbackInputStream, r8_OutputStream, a());
                    r0i += a();
                    r1i += a();
                }
                if (a() + r1i == r4i) {
                    a(r3_PushbackInputStream, r8_OutputStream, a());
                    r0i += a();
                } else {
                    a(r3_PushbackInputStream, r8_OutputStream, r4i - r1i);
                    r0i += r4i - r1i;
                }
                d(r3_PushbackInputStream, r8_OutputStream);
            } catch (CEStreamExhausted e) {
                b(r3_PushbackInputStream, r8_OutputStream);
            }
        }
    }

    public byte[] decodeBuffer(InputStream r2_InputStream) throws IOException {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        decodeBuffer(r2_InputStream, r0_OutputStream);
        return r0_OutputStream.toByteArray();
    }

    public byte[] decodeBuffer(String r4_String) throws IOException {
        byte[] r0_byteA = new byte[r4_String.length()];
        r4_String.getBytes(0, r4_String.length(), r0_byteA, 0);
        InputStream r1_InputStream = new ByteArrayInputStream(r0_byteA);
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        decodeBuffer(r1_InputStream, r0_OutputStream);
        return r0_OutputStream.toByteArray();
    }

    public ByteBuffer decodeBufferToByteBuffer(InputStream r2_InputStream) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(r2_InputStream));
    }

    public ByteBuffer decodeBufferToByteBuffer(String r2_String) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(r2_String));
    }
}