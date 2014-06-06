package com.crashlytics.android.internal;

import com.tencent.mm.sdk.platformtools.LVBuffer;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
public class aq implements Closeable {
    private static final Logger a;
    private final RandomAccessFile b;
    private int c;
    private int d;
    private bw e;
    private bw f;
    private final byte[] g;

    static {
        a = Logger.getLogger(aq.class.getName());
    }

    public aq(File r8_File) throws IOException {
        this.g = new byte[16];
        if (!r8_File.exists()) {
            File r0_File = new File(r8_File.getPath() + ".tmp");
            RandomAccessFile r1_RandomAccessFile = a(r0_File);
            r1_RandomAccessFile.setLength(4096);
            r1_RandomAccessFile.seek(0);
            byte[] r2_byteA = new byte[16];
            a(r2_byteA, new int[]{4096, 0, 0, 0});
            r1_RandomAccessFile.write(r2_byteA);
            r1_RandomAccessFile.close();
            if (!r0_File.renameTo(r8_File)) {
                throw new IOException("Rename failed!");
            }
        }
        this.b = a(r8_File);
        this.b.seek(0);
        this.b.readFully(this.g);
        this.c = a(this.g, 0);
        if (((long) this.c) > this.b.length()) {
            throw new IOException(new StringBuilder("File is truncated. Expected length: ").append(this.c).append(", Actual length: ").append(this.b.length()).toString());
        } else {
            this.d = a(this.g, (int)XListViewFooter.STATE_NODATA);
            int r0i = a(this.g, (int)Base64.DONT_BREAK_LINES);
            int r1i = a(this.g, (int)REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
            this.e = a(r0i);
            this.f = a(r1i);
        }
    }

    private static int a(byte[] r2_byteA, int r3i) {
        return (r2_byteA[r3i] & 255) << 24 + (r2_byteA[r3i + 1] & 255) << 16 + (r2_byteA[r3i + 2] & 255) << 8 + r2_byteA[r3i + 3] & 255;
    }

    private bw a(int r4i) throws IOException {
        if (r4i == 0) {
            return bw.a;
        }
        this.b.seek((long) r4i);
        return new bw(r4i, this.b.readInt());
    }

    private static RandomAccessFile a(File r2_File) throws FileNotFoundException {
        return new RandomAccessFile(r2_File, "rwd");
    }

    private void a(int r4i, int r5i, int r6i, int r7i) throws IOException {
        byte[] r0_byteA = this.g;
        int[] r1_intA = new int[4];
        r1_intA[0] = r4i;
        r1_intA[1] = r5i;
        r1_intA[2] = r6i;
        r1_intA[3] = r7i;
        a(r0_byteA, r1_intA);
        this.b.seek(0);
        this.b.write(this.g);
    }

    private void a(int r6i, byte[] r7_byteA, int r8i, int r9i) throws IOException {
        int r0i = b(r6i);
        if (r0i + r9i <= this.c) {
            this.b.seek((long) r0i);
            this.b.write(r7_byteA, r8i, r9i);
        } else {
            int r1i = this.c - r0i;
            this.b.seek((long) r0i);
            this.b.write(r7_byteA, r8i, r1i);
            this.b.seek(16);
            this.b.write(r7_byteA, r8i + r1i, r9i - r1i);
        }
    }

    private static void a(byte[] r2_byteA, int r3i, int r4i) {
        r2_byteA[r3i] = r4i >> 24;
        r2_byteA[r3i + 1] = (byte) (r4i >> 16);
        r2_byteA[r3i + 2] = (byte) (r4i >> 8);
        r2_byteA[r3i + 3] = (byte) r4i;
    }

    private static void a(byte[] r4_byteA, int ... r5_intA) {
        int r0i = 0;
        int r2i = r5_intA.length;
        int r1i = 0;
        while (r0i < r2i) {
            a(r4_byteA, r1i, r5_intA[r0i]);
            r1i += 4;
            r0i++;
        }
    }

    private int b(int r3i) {
        return r3i < this.c ? r3i : r3i + 16 - this.c;
    }

    private static <T> T b(T r1_T, String r2_String) {
        if (r1_T != null) {
            return r1_T;
        }
        throw new NullPointerException(r2_String);
    }

    private void b(int r6i, byte[] r7_byteA, int r8i, int r9i) throws IOException {
        int r0i = b(r6i);
        if (r0i + r9i <= this.c) {
            this.b.seek((long) r0i);
            this.b.readFully(r7_byteA, r8i, r9i);
        } else {
            int r1i = this.c - r0i;
            this.b.seek((long) r0i);
            this.b.readFully(r7_byteA, r8i, r1i);
            this.b.seek(16);
            this.b.readFully(r7_byteA, r8i + r1i, r9i - r1i);
        }
    }

    private synchronized void b(byte[] r7_byteA, int r8i, int r9i) throws IOException {
        b(r7_byteA, "buffer");
        if ((r9i | 0) < 0 || r9i > r7_byteA.length) {
            throw new IndexOutOfBoundsException();
        } else {
            c(r9i);
            boolean r1z = b();
            bw r2_bw = new bw(r1z ? Base64.URL_SAFE : b(this.f.b + 4 + this.f.c), r9i);
            a(this.g, 0, r9i);
            a(r2_bw.b, this.g, 0, (int)XListViewFooter.STATE_NODATA);
            a(r2_bw.b + 4, r7_byteA, 0, r9i);
            a(this.c, this.d + 1, r1z ? r2_bw.b : this.e.b, r2_bw.b);
            this.f = r2_bw;
            this.d++;
            if (r1z) {
                this.e = this.f;
            }
        }
    }

    private void c(int r9i) throws IOException {
        int r2i = r9i + 4;
        int r1i = this.c - a();
        if (r1i >= r2i) {
        } else {
            int r0i = this.c;
            while (true) {
                r1i += r0i;
                int r6i = r0i << 1;
                if (r1i >= r2i) {
                    d(r6i);
                    r1i = b(this.f.b + 4 + this.f.c);
                    if (r1i < this.e.b) {
                        FileChannel r0_FileChannel = this.b.getChannel();
                        r0_FileChannel.position((long) this.c);
                        int r7i = r1i - 4;
                        if (r0_FileChannel.transferTo(16, (long) r7i, r0_FileChannel) != ((long) r7i)) {
                            throw new AssertionError("Copied insufficient number of bytes!");
                        }
                    }
                    if (this.f.b < this.e.b) {
                        r0i = this.c + this.f.b - 16;
                        a(r6i, this.d, this.e.b, r0i);
                        this.f = new bw(r0i, this.f.c);
                    } else {
                        a(r6i, this.d, this.e.b, this.f.b);
                    }
                    this.c = r6i;
                } else {
                    r0i = r6i;
                }
            }
        }
    }

    private synchronized void d() throws IOException {
        a(LVBuffer.LENGTH_ALLOC_PER_NEW, 0, 0, 0);
        this.d = 0;
        this.e = bw.a;
        this.f = bw.a;
        if (this.c > 4096) {
            d(LVBuffer.LENGTH_ALLOC_PER_NEW);
        }
        this.c = 4096;
    }

    private void d(int r4i) throws IOException {
        this.b.setLength((long) r4i);
        this.b.getChannel().force(true);
    }

    public final int a() {
        if (this.d == 0) {
            return Base64.URL_SAFE;
        }
        if (this.f.b >= this.e.b) {
            return this.f.b - this.e.b + 4 + this.f.c + 16;
        }
        return this.f.b + 4 + this.f.c + this.c - this.e.b;
    }

    public final synchronized void a(au r5_au) throws IOException {
        int r0i = 0;
        synchronized (this) {
            int r1i = this.e.b;
            while (r0i < this.d) {
                bw r1_bw = a(r1i);
                r5_au.a(new bx(this, r1_bw, (byte) 0), r1_bw.c);
                r1i = b(r1_bw.c + r1_bw.b + 4);
                r0i++;
            }
        }
    }

    public final void a(byte[] r3_byteA) throws IOException {
        b(r3_byteA, 0, r3_byteA.length);
    }

    public final boolean a(int r2i, int r3i) {
        return (a() + 4) + r2i <= r3i;
    }

    public final synchronized boolean b() {
        return this.d == 0;
    }

    public final synchronized void c() throws IOException {
        if (b()) {
            throw new NoSuchElementException();
        } else if (this.d == 1) {
            d();
        } else {
            int r0i = b(this.e.b + 4 + this.e.c);
            b(r0i, this.g, 0, XListViewFooter.STATE_NODATA);
            int r1i = a(this.g, 0);
            a(this.c, this.d - 1, r0i, this.f.b);
            this.d--;
            this.e = new bw(r0i, r1i);
        }
    }

    public synchronized void close() throws IOException {
        this.b.close();
    }

    public String toString() {
        StringBuilder r1_StringBuilder = new StringBuilder();
        r1_StringBuilder.append(getClass().getSimpleName()).append('[');
        r1_StringBuilder.append("fileLength=").append(this.c);
        r1_StringBuilder.append(", size=").append(this.d);
        r1_StringBuilder.append(", first=").append(this.e);
        r1_StringBuilder.append(", last=").append(this.f);
        r1_StringBuilder.append(", element lengths=[");
        try {
            a(new bv(this, r1_StringBuilder));
        } catch (IOException e) {
            a.log(Level.WARNING, "read error", e);
        }
        r1_StringBuilder.append("]]");
        return r1_StringBuilder.toString();
    }
}