package com.qiubai.library.adview.base64;

import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.IOException;
import java.io.OutputStream;
import qsbk.app.widget.listview.XListViewFooter;

public class BASE64Encoder extends CharacterEncoder {
    private static final char[] b;

    static {
        b = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    }

    protected int a() {
        return XListViewFooter.STATE_NOMORE;
    }

    protected void a(OutputStream r6_OutputStream, byte[] r7_byteA, int r8i, int r9i) throws IOException {
        byte r0b;
        if (r9i == 1) {
            r0b = r7_byteA[r8i];
            r6_OutputStream.write(b[(r0b >>> 2) & 63]);
            r6_OutputStream.write(b[(r0b << 4) & 48 + 0]);
            r6_OutputStream.write(BDLocation.TypeGpsLocation);
            r6_OutputStream.write(BDLocation.TypeGpsLocation);
        } else if (r9i == 2) {
            r0b = r7_byteA[r8i];
            r1b = r7_byteA[r8i + 1];
            r6_OutputStream.write(b[(r0b >>> 2) & 63]);
            r6_OutputStream.write(b[(r0b << 4) & 48 + (r1b >>> 4) & 15]);
            r6_OutputStream.write(b[(r1b << 2) & 60 + 0]);
            r6_OutputStream.write(BDLocation.TypeGpsLocation);
        } else {
            r0b = r7_byteA[r8i];
            r1b = r7_byteA[r8i + 1];
            byte r2b = r7_byteA[r8i + 2];
            r6_OutputStream.write(b[(r0b >>> 2) & 63]);
            r6_OutputStream.write(b[(r0b << 4) & 48 + (r1b >>> 4) & 15]);
            r6_OutputStream.write(b[(r1b << 2) & 60 + (r2b >>> 6) & 3]);
            r6_OutputStream.write(b[r2b & 63]);
        }
    }

    protected int b() {
        return AdViewUtil.NETWORK_TYPE_PUNCHBOX;
    }
}