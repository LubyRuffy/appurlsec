package com.crashlytics.android;

import android.os.Process;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.ao;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class aj {
    private static final AtomicLong a;
    private static String b;

    static {
        a = new AtomicLong(0);
    }

    public aj(ao r13_ao) {
        byte[] r0_byteA = new byte[10];
        long r1j = new Date().getTime();
        ByteBuffer r5_ByteBuffer = ByteBuffer.allocate(XListViewFooter.STATE_NODATA);
        r5_ByteBuffer.putInt((int) (r1j / 1000));
        r5_ByteBuffer.order(ByteOrder.BIG_ENDIAN);
        r5_ByteBuffer.position(0);
        byte[] r3_byteA = r5_ByteBuffer.array();
        r0_byteA[0] = r3_byteA[0];
        r0_byteA[1] = r3_byteA[1];
        r0_byteA[2] = r3_byteA[2];
        r0_byteA[3] = r3_byteA[3];
        byte[] r1_byteA = a(r1j % 1000);
        r0_byteA[4] = r1_byteA[0];
        r0_byteA[5] = r1_byteA[1];
        r1_byteA = a(a.incrementAndGet());
        r0_byteA[6] = r1_byteA[0];
        r0_byteA[7] = r1_byteA[1];
        r1_byteA = a((long) Integer.valueOf(Process.myPid()).shortValue());
        r0_byteA[8] = r1_byteA[0];
        r0_byteA[9] = r1_byteA[1];
        String r1_String = ab.a(r13_ao.b());
        String r0_String = ab.a(r0_byteA);
        Locale r2_Locale = Locale.US;
        Object[] r4_ObjectA = new Object[4];
        r4_ObjectA[0] = r0_String.substring(0, REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
        r4_ObjectA[1] = r0_String.substring(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, Base64.URL_SAFE);
        r4_ObjectA[2] = r0_String.subSequence(Base64.URL_SAFE, OneProfileActivity.REQ_CODE_SHARE);
        r4_ObjectA[3] = r1_String.substring(0, REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
        b = String.format(r2_Locale, "%s-%s-%s-%s", r4_ObjectA).toUpperCase(Locale.US);
    }

    private static byte[] a(long r2j) {
        ByteBuffer r0_ByteBuffer = ByteBuffer.allocate(XListViewHeader.STATE_REFRESHING);
        r0_ByteBuffer.putShort((short) ((int) r2j));
        r0_ByteBuffer.order(ByteOrder.BIG_ENDIAN);
        r0_ByteBuffer.position(0);
        return r0_ByteBuffer.array();
    }

    public final String toString() {
        return b;
    }
}