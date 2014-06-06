package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.InetAddress;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;

// compiled from: SourceFile
public class ct {
    private static final String a;

    static {
        a = ct.class.getName();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static InetAddress[] a(String r7_String) {
        /*
        r1 = 0;
        r0 = b(r7);
        if (r0 == 0) goto L_0x0011;
    L_0x0007:
        r2 = r0.trim();
        r2 = r2.length();
        if (r2 > 0) goto L_0x0013;
    L_0x0011:
        r0 = r1;
    L_0x0012:
        return r0;
    L_0x0013:
        r3 = r0.trim();
        r0 = com.tencent.cloudsdk.cb.a();
        r0 = r0.a(r3);
        if (r0 != 0) goto L_0x0012;
    L_0x0021:
        r2 = new com.tencent.cloudsdk.cu;	 //Catch:{ UnknownHostException -> 0x004f, cy -> 0x0075, SocketTimeoutException -> 0x009b, IOException -> 0x00c2, Exception -> 0x00e9 }
        r0 = "114.114.114.114";
        r2.<init>(r0);	 //Catch:{ UnknownHostException -> 0x004f, cy -> 0x0075, SocketTimeoutException -> 0x009b, IOException -> 0x00c2, Exception -> 0x00e9 }
        r0 = r2.b(r3);	 //Catch:{ UnknownHostException -> 0x0200, cy -> 0x01fd, SocketTimeoutException -> 0x01fa, IOException -> 0x01f7, Exception -> 0x01f4 }
        if (r0 == 0) goto L_0x0031;
    L_0x002e:
        r4 = r0.length;	 //Catch:{ UnknownHostException -> 0x0200, cy -> 0x01fd, SocketTimeoutException -> 0x01fa, IOException -> 0x01f7, Exception -> 0x01f4 }
        if (r4 > 0) goto L_0x0012;
    L_0x0031:
        r0 = r2;
    L_0x0032:
        if (r0 != 0) goto L_0x010f;
    L_0x0034:
        r0 = new com.tencent.cloudsdk.cu;	 //Catch:{ UnknownHostException -> 0x0116, cy -> 0x013b, SocketTimeoutException -> 0x0160, IOException -> 0x0185, Exception -> 0x01aa }
        r2 = "8.8.8.8";
        r0.<init>(r2);	 //Catch:{ UnknownHostException -> 0x0116, cy -> 0x013b, SocketTimeoutException -> 0x0160, IOException -> 0x0185, Exception -> 0x01aa }
    L_0x003b:
        r0 = r0.b(r3);	 //Catch:{ UnknownHostException -> 0x0116, cy -> 0x013b, SocketTimeoutException -> 0x0160, IOException -> 0x0185, Exception -> 0x01aa }
        if (r0 == 0) goto L_0x0044;
    L_0x0041:
        r2 = r0.length;	 //Catch:{ UnknownHostException -> 0x0116, cy -> 0x013b, SocketTimeoutException -> 0x0160, IOException -> 0x0185, Exception -> 0x01aa }
        if (r2 > 0) goto L_0x0012;
    L_0x0044:
        r0 = java.net.InetAddress.getAllByName(r3);	 //Catch:{ UnknownHostException -> 0x01cf }
        if (r0 == 0) goto L_0x004d;
    L_0x004a:
        r2 = r0.length;	 //Catch:{ UnknownHostException -> 0x01cf }
        if (r2 > 0) goto L_0x0012;
    L_0x004d:
        r0 = r1;
        goto L_0x0012;
    L_0x004f:
        r0 = move-exception;
        r2 = r1;
    L_0x0051:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r6 = "UnknownHostException cause[";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r6 = "][114.114.114.114].";
        r5 = r5.append(r6);
        r0 = r0.getMessage();
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r0);
        r0 = r2;
        goto L_0x0032;
    L_0x0075:
        r0 = move-exception;
        r2 = r1;
    L_0x0077:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r6 = "WireParseException cause[";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r6 = "][114.114.114.114].";
        r5 = r5.append(r6);
        r0 = r0.getMessage();
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r0);
        r0 = r2;
        goto L_0x0032;
    L_0x009b:
        r0 = move-exception;
        r2 = r1;
    L_0x009d:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r6 = "SocketTimeoutException cause[";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r6 = "][114.114.114.114].";
        r5 = r5.append(r6);
        r0 = r0.getMessage();
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r0);
        r0 = r2;
        goto L_0x0032;
    L_0x00c2:
        r0 = move-exception;
        r2 = r1;
    L_0x00c4:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r6 = "IOException cause[";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r6 = "][114.114.114.114].";
        r5 = r5.append(r6);
        r0 = r0.getMessage();
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r0);
        r0 = r2;
        goto L_0x0032;
    L_0x00e9:
        r0 = move-exception;
        r2 = r1;
    L_0x00eb:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r6 = "Exception cause[";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r6 = "][114.114.114.114].";
        r5 = r5.append(r6);
        r0 = r0.getMessage();
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r0);
        goto L_0x0031;
    L_0x010f:
        r2 = "8.8.8.8";
        r0.a(r2);	 //Catch:{ UnknownHostException -> 0x0116, cy -> 0x013b, SocketTimeoutException -> 0x0160, IOException -> 0x0185, Exception -> 0x01aa }
        goto L_0x003b;
    L_0x0116:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "UnknownHostException cause[";
        r4.<init>(r5);
        r4 = r4.append(r3);
        r5 = "][8.8.8.8].";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x0044;
    L_0x013b:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "WireParseException cause[";
        r4.<init>(r5);
        r4 = r4.append(r3);
        r5 = "][8.8.8.8].";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x0044;
    L_0x0160:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "SocketTimeoutException cause[";
        r4.<init>(r5);
        r4 = r4.append(r3);
        r5 = "][8.8.8.8].";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x0044;
    L_0x0185:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "IOException cause[";
        r4.<init>(r5);
        r4 = r4.append(r3);
        r5 = "][8.8.8.8].";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x0044;
    L_0x01aa:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "Exception cause[";
        r4.<init>(r5);
        r4 = r4.append(r3);
        r5 = "][8.8.8.8].";
        r4 = r4.append(r5);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x0044;
    L_0x01cf:
        r0 = move-exception;
        r2 = a;
        r4 = new java.lang.StringBuilder;
        r5 = "UnknownHostException cause[";
        r4.<init>(r5);
        r3 = r4.append(r3);
        r4 = "][LDNS].";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r0);
        goto L_0x004d;
    L_0x01f4:
        r0 = move-exception;
        goto L_0x00eb;
    L_0x01f7:
        r0 = move-exception;
        goto L_0x00c4;
    L_0x01fa:
        r0 = move-exception;
        goto L_0x009d;
    L_0x01fd:
        r0 = move-exception;
        goto L_0x0077;
    L_0x0200:
        r0 = move-exception;
        goto L_0x0051;
        */

    }

    public static String b(String r6_String) {
        if (r6_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = r6_String.trim();
        String r1_String = r0_String.toLowerCase();
        int r1i;
        if (r1_String.startsWith(HttpUtils.http)) {
            r1i = r0_String.indexOf("/", Base64.DONT_BREAK_LINES);
            return r1i > 7 ? r0_String.substring(ShareUtils.SHARE_COLLECT, r1i) : r0_String.substring(ShareUtils.SHARE_COLLECT);
        } else if (r1_String.startsWith(HttpUtils.https)) {
            r1i = r0_String.indexOf("/", REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
            return r1i > 8 ? r0_String.substring(Base64.DONT_BREAK_LINES, r1i) : r0_String.substring(Base64.DONT_BREAK_LINES);
        } else {
            if (r0_String.indexOf("/", 1) > 1) {
                return r0_String.substring(0, r0_String.indexOf("/", 1));
            }
            return r0_String;
        }
    }
}