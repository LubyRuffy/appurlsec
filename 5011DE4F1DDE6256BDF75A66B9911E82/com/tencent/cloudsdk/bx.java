package com.tencent.cloudsdk;

import android.content.SharedPreferences.Editor;
import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
class bx implements be {
    final /* synthetic */ bw a;

    bx(bw r1_bw) {
        this.a = r1_bw;
    }

    public void a(long r1j) {
    }

    public void a(String r1_String, int r2i, long r3j, long r5j) {
    }

    public void a(String r5_String, int r6i, long r7j, String r9_String, int r10i, long r11j) {
        er.a("AnsSetting", new StringBuilder(">>>\u62c9\u53d6\u5931\u8d25\uff1a[msg\uff1a").append(r9_String).append("][\u8017\u65f6:").append(System.currentTimeMillis() - r7j).append("][").append(r11j).append("]").toString());
    }

    public void a(String r5_String, int r6i, long r7j, byte[] r9_byteA, long r10j) {
        if (bf.a(r9_byteA).b() == (short) 0) {
            Object r0_Object = new Object[(r9_byteA.length - 5)];
            System.arraycopy(r9_byteA, ShareUtils.SHARE_SMS, r0_Object, 0, r0_Object.length);
            String r1_String = new String(r0_Object);
            bv.a(bv.c(), r1_String);
            Editor r0_Editor = this.a.e.edit();
            r0_Editor.putInt("ver", bv.r());
            r0_Editor.commit();
            er.a("AnsSetting", new StringBuilder(">>>\u62c9\u53d6\u5b8c\u6bd5\uff1a[data\uff1a").append(r1_String).append("][\u8017\u65f6:").append(System.currentTimeMillis() - r7j).append("][").append(r10j).append("]").toString());
        }
    }

    public void b(long r1j) {
    }

    public void c(long r1j) {
    }
}