package com.qiubai.library.adview.base64;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class BASE64Decoder extends CharacterDecoder {
    private static final char[] b;
    private static final byte[] c;
    byte[] a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 0;
        r1 = 64;
        r1 = new char[r1];
        r1 = 
        // error: 0x0005: FILL_ARRAY  (r1 ?[short, char][]) = 
        b = r1;
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r1 = new byte[r1];
        c = r1;
        r1 = r0;
    L_0x0011:
        r2 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r1 < r2) goto L_0x001b;
    L_0x0015:
        r1 = b;
        r1 = r1.length;
        if (r0 < r1) goto L_0x0023;
    L_0x001a:
        return;
    L_0x001b:
        r2 = c;
        r3 = -1;
        r2[r1] = r3;
        r1 = r1 + 1;
        goto L_0x0011;
    L_0x0023:
        r1 = c;
        r2 = b;
        r2 = r2[r0];
        r3 = (byte) r0;
        r1[r2] = r3;
        r0 = r0 + 1;
        goto L_0x0015;
        */

    }

    public BASE64Decoder() {
        this.a = new byte[4];
    }

    protected int a() {
        return XListViewFooter.STATE_NODATA;
    }

    protected void a(PushbackInputStream r9_PushbackInputStream, OutputStream r10_OutputStream, int r11i) throws IOException {
        int r1i = XListViewHeader.STATE_REFRESHING;
        int r0i = -1;
        if (r11i < 2) {
            throw new CEFormatException("BASE64Decoder: Not enough bytes for an atom.");
        }
        while (true) {
            int r2i = r9_PushbackInputStream.read();
            if (r2i == -1) {
                throw new CEStreamExhausted();
            } else if (r2i == 10 || r2i == 13) {
            } else {
                this.a[0] = (byte) r2i;
                if (a(r9_PushbackInputStream, this.a, 1, r11i - 1) == -1) {
                    throw new CEStreamExhausted();
                } else {
                    int r4i;
                    byte r1b;
                    byte r2b;
                    byte r3b;
                    r2i = (r11i <= 3 || this.a[3] != 61) ? r11i : 3;
                    r4i = (r2i <= 2 || this.a[2] != 61) ? r2i : 2;
                    switch (r4i) {
                        case XListViewHeader.STATE_REFRESHING:
                            r1b = (byte) -1;
                            r2b = c[this.a[1] & 255];
                            r3b = c[this.a[0] & 255];
                            switch (r4i) {
                                case XListViewHeader.STATE_REFRESHING:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    break;
                                case XListViewFooter.STATE_NOMORE:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                                    break;
                                case XListViewFooter.STATE_NODATA:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                                    r10_OutputStream.write((byte) ((r0i & 63) | ((r1b << 6) & 192)));
                                    break;
                            }
                            return;
                        case XListViewFooter.STATE_NOMORE:
                            break;
                        case XListViewFooter.STATE_NODATA:
                            r0i = c[this.a[3] & 255];
                            break;
                        default:
                            r1b = (byte) -1;
                            r2b = (byte) -1;
                            r3b = (byte) -1;
                            switch (r4i) {
                                case XListViewHeader.STATE_REFRESHING:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    break;
                                case XListViewFooter.STATE_NOMORE:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                                    break;
                                case XListViewFooter.STATE_NODATA:
                                    r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                                    r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                                    r10_OutputStream.write((byte) ((r0i & 63) | ((r1b << 6) & 192)));
                                    break;
                            }
                            return;
                    }
                    r1b = c[this.a[r1i] & 255];
                    r2b = c[this.a[1] & 255];
                    r3b = c[this.a[0] & 255];
                    switch (r4i) {
                        case XListViewHeader.STATE_REFRESHING:
                            r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                            break;
                        case XListViewFooter.STATE_NOMORE:
                            r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                            r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                            break;
                        case XListViewFooter.STATE_NODATA:
                            r10_OutputStream.write((byte) (((r3b << 2) & 252) | ((r2b >>> 4) & 3)));
                            r10_OutputStream.write((byte) (((r2b << 4) & 240) | ((r1b >>> 2) & 15)));
                            r10_OutputStream.write((byte) ((r0i & 63) | ((r1b << 6) & 192)));
                            break;
                    }
                    return;
                }
            }
        }
    }

    protected int b() {
        return 72;
    }
}