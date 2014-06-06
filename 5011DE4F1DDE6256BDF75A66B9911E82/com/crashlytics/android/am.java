package com.crashlytics.android;

import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class am implements Flushable {
    private final byte[] a;
    private final int b;
    private int c;
    private final OutputStream d;

    private am(OutputStream r2_OutputStream, byte[] r3_byteA) {
        this.d = r2_OutputStream;
        this.a = r3_byteA;
        this.c = 0;
        this.b = r3_byteA.length;
    }

    public static int a(int r1i) {
        return c(af.a(r1i, 0));
    }

    public static am a(OutputStream r2_OutputStream) {
        return new am(r2_OutputStream, new byte[4096]);
    }

    private void a() throws IOException {
        if (this.d == null) {
            throw new an();
        } else {
            this.d.write(this.a, 0, this.c);
            this.c = 0;
        }
    }

    private void a(long r5j) throws IOException {
        while ((-128 & r5j) != 0) {
            d((((int) r5j) & 127) | 128);
            r5j >>>= 7;
        }
        d((int) r5j);
    }

    public static int b(int r1i, float r2f) {
        return a(1) + 4;
    }

    public static int b(int r6i, long r7j) {
        int r0i;
        int r1i = a(r6i);
        if ((-128 & r7j) == 0) {
            r0i = 1;
        } else if ((-16384 & r7j) == 0) {
            r0i = XListViewHeader.STATE_REFRESHING;
        } else if ((-2097152 & r7j) == 0) {
            r0i = XListViewFooter.STATE_NOMORE;
        } else if ((-268435456 & r7j) == 0) {
            r0i = XListViewFooter.STATE_NODATA;
        } else if ((-34359738368L & r7j) == 0) {
            r0i = ShareUtils.SHARE_SMS;
        } else if ((-4398046511104L & r7j) == 0) {
            r0i = ShareUtils.SHARE_COPY;
        } else if ((-562949953421312L & r7j) == 0) {
            r0i = ShareUtils.SHARE_COLLECT;
        } else if ((-72057594037927936L & r7j) == 0) {
            r0i = Base64.DONT_BREAK_LINES;
        } else if ((-9223372036854775808L & r7j) == 0) {
            r0i = REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY;
        } else {
            r0i = REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
        }
        return r0i + r1i;
    }

    public static int b(int r3i, ai r4_ai) {
        return a(r3i) + c(r4_ai.a()) + r4_ai.a();
    }

    public static int b(int r1i, boolean r2z) {
        return a(r1i) + 1;
    }

    public static int c(int r1i) {
        if ((r1i & -128) == 0) {
            return 1;
        }
        if ((r1i & -16384) == 0) {
            return XListViewHeader.STATE_REFRESHING;
        }
        if ((-2097152 & r1i) == 0) {
            return XListViewFooter.STATE_NOMORE;
        }
        if ((-268435456 & r1i) == 0) {
            return XListViewFooter.STATE_NODATA;
        }
        return ShareUtils.SHARE_SMS;
    }

    public static int d(int r2i, int r3i) {
        return a(r2i) + c(r3i);
    }

    private void d(int r5i) throws IOException {
        byte r0b = (byte) r5i;
        if (this.c == this.b) {
            a();
        }
        byte[] r1_byteA = this.a;
        int r2i = this.c;
        this.c = r2i + 1;
        r1_byteA[r2i] = r0b;
    }

    private static int e(int r2i) {
        return (r2i << 1) ^ (r2i >> 31);
    }

    public static int e(int r2i, int r3i) {
        return (r3i >= 0 ? c(r3i) : REQUEST_CODE.REQUEST_CODE_EDIT_INTRO) + a(r2i);
    }

    public static int f(int r2i, int r3i) {
        return a((int)XListViewHeader.STATE_REFRESHING) + c(e(r3i));
    }

    public final void a(int r3i, float r4f) throws IOException {
        g(1, ShareUtils.SHARE_SMS);
        int r0i = Float.floatToRawIntBits(r4f);
        d(r0i & 255);
        d((r0i >> 8) & 255);
        d((r0i >> 16) & 255);
        d(r0i >>> 24);
    }

    public final void a(int r2i, int r3i) throws IOException {
        g(r2i, 0);
        b(r3i);
    }

    public final void a(int r2i, long r3j) throws IOException {
        g(r2i, 0);
        a(r3j);
    }

    public final void a(int r9i, ai r10_ai) throws IOException {
        g(r9i, XListViewHeader.STATE_REFRESHING);
        b(r10_ai.a());
        int r0i = r10_ai.a();
        if (this.b - this.c >= r0i) {
            r10_ai.a(this.a, 0, this.c, r0i);
            this.c = r0i + this.c;
        } else {
            int r1i = this.b - this.c;
            r10_ai.a(this.a, 0, this.c, r1i);
            int r2i = r1i + 0;
            r0i -= r1i;
            this.c = this.b;
            a();
            if (r0i <= this.b) {
                r10_ai.a(this.a, r2i, 0, r0i);
                this.c = r0i;
            } else {
                InputStream r1_InputStream = r10_ai.b();
                if (((long) r2i) != r1_InputStream.skip((long) r2i)) {
                    throw new IllegalStateException("Skip failed.");
                }
                while (r0i > 0) {
                    r2i = Math.min(r0i, this.b);
                    int r3i = r1_InputStream.read(this.a, 0, r2i);
                    if (r3i != r2i) {
                        throw new IllegalStateException("Read failed.");
                    } else {
                        this.d.write(this.a, 0, r3i);
                        r0i -= r3i;
                    }
                }
            }
        }
    }

    public final void a(int r3i, String r4_String) throws IOException {
        g(1, XListViewHeader.STATE_REFRESHING);
        byte[] r0_byteA = r4_String.getBytes(Base.UTF8);
        b(r0_byteA.length);
        a(r0_byteA);
    }

    public final void a(int r2i, boolean r3z) throws IOException {
        int r0i = 0;
        g(r2i, 0);
        if (r3z) {
            r0i = 1;
        }
        d(r0i);
    }

    public final void a(byte[] r6_byteA) throws IOException {
        int r0i = r6_byteA.length;
        if (this.b - this.c >= r0i) {
            System.arraycopy(r6_byteA, 0, this.a, this.c, r0i);
            this.c = r0i + this.c;
        } else {
            int r1i = this.b - this.c;
            System.arraycopy(r6_byteA, 0, this.a, this.c, r1i);
            int r2i = r1i + 0;
            r0i -= r1i;
            this.c = this.b;
            a();
            if (r0i <= this.b) {
                System.arraycopy(r6_byteA, r2i, this.a, 0, r0i);
                this.c = r0i;
            } else {
                this.d.write(r6_byteA, r2i, r0i);
            }
        }
    }

    public final void b(int r2i) throws IOException {
        while ((r2i & -128) != 0) {
            d((r2i & 127) | 128);
            r2i >>>= 7;
        }
        d(r2i);
    }

    public final void b(int r3i, int r4i) throws IOException {
        g(r3i, 0);
        if (r4i >= 0) {
            b(r4i);
        } else {
            a((long) r4i);
        }
    }

    public final void c(int r3i, int r4i) throws IOException {
        g(XListViewHeader.STATE_REFRESHING, 0);
        b(e(r4i));
    }

    public final void flush() throws IOException {
        if (this.d != null) {
            a();
        }
    }

    public final void g(int r2i, int r3i) throws IOException {
        b(af.a(r2i, r3i));
    }
}