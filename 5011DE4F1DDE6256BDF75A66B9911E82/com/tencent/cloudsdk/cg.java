package com.tencent.cloudsdk;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.channels.SelectionKey;
import java.security.SecureRandom;

// compiled from: SourceFile
final class cg {
    private static SecureRandom a;

    static {
        a = new SecureRandom();
    }

    cg() {
    }

    private static void a(SelectionKey r6_SelectionKey, long r7j) throws IOException, SocketTimeoutException {
        long r1j = r7j - System.currentTimeMillis();
        int r0i = 0;
        if (r1j > 0) {
            r0i = r6_SelectionKey.selector().select(r1j);
        } else if (r1j == 0) {
            r0i = r6_SelectionKey.selector().selectNow();
        }
        if (r0i == 0) {
            throw new SocketTimeoutException();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(String r9_String, byte[] r10_byteA) throws IOException, SocketTimeoutException {
        /*
        r8_this = this;
        r1 = 0;
        r0 = java.nio.channels.DatagramChannel.open();	 //Catch:{ all -> 0x00bc }
        r2 = 0;
        r0.configureBlocking(r2);	 //Catch:{ all -> 0x00bc }
        r2 = java.nio.channels.Selector.open();	 //Catch:{ all -> 0x00bc }
        r3 = 1;
        r2 = r0.register(r2, r3);	 //Catch:{ all -> 0x00bc }
        r0 = r2.channel();	 //Catch:{ all -> 0x0097 }
        r0 = (java.nio.channels.DatagramChannel) r0;	 //Catch:{ all -> 0x0097 }
        r3 = a;	 //Catch:{ all -> 0x0097 }
        r4 = 64511; // 0xfbff float:9.0399E-41 double:3.18727E-319;
        r3 = r3.nextInt(r4);	 //Catch:{ all -> 0x0097 }
        r3 = r3 + 1024;
        r4 = new java.net.InetSocketAddress;	 //Catch:{ all -> 0x0097 }
        r4.<init>(r3);	 //Catch:{ all -> 0x0097 }
        r3 = r0.socket();	 //Catch:{ all -> 0x0097 }
        r3.bind(r4);	 //Catch:{ all -> 0x0097 }
        r3 = new java.net.InetSocketAddress;	 //Catch:{ all -> 0x0097 }
        r4 = java.net.InetAddress.getByName(r9);	 //Catch:{ all -> 0x0097 }
        r5 = 53;
        r3.<init>(r4, r5);	 //Catch:{ all -> 0x0097 }
        r0.connect(r3);	 //Catch:{ all -> 0x0097 }
        r3 = java.nio.ByteBuffer.wrap(r10);	 //Catch:{ all -> 0x0097 }
        r0.write(r3);	 //Catch:{ all -> 0x0097 }
        r3 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r3 = new byte[r3];	 //Catch:{ all -> 0x0097 }
        r4 = java.lang.System.currentTimeMillis();	 //Catch:{ all -> 0x0097 }
        r6 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r4 = r4 + r6;
    L_0x004f:
        r6 = r2.isReadable();	 //Catch:{ all -> 0x008b }
        if (r6 == 0) goto L_0x0087;
    L_0x0055:
        r4 = r2.isValid();	 //Catch:{ all -> 0x0097 }
        if (r4 == 0) goto L_0x005f;
    L_0x005b:
        r4 = 0;
        r2.interestOps(r4);	 //Catch:{ all -> 0x0097 }
    L_0x005f:
        r4 = java.nio.ByteBuffer.wrap(r3);	 //Catch:{ all -> 0x0097 }
        r0 = r0.read(r4);	 //Catch:{ all -> 0x0097 }
        r4 = (long) r0;	 //Catch:{ all -> 0x0097 }
        r6 = 0;
        r0 = (r4 > r6 ? 1 : (r4 == r6? 0 : -1));
        if (r0 <= 0) goto L_0x00aa;
    L_0x006e:
        r1 = (int) r4;	 //Catch:{ all -> 0x0097 }
        r0 = new byte[r1];	 //Catch:{ all -> 0x0097 }
        r4 = 0;
        r5 = 0;
        java.lang.System.arraycopy(r3, r4, r0, r5, r1);	 //Catch:{ all -> 0x0097 }
        if (r2 == 0) goto L_0x0086;
    L_0x0078:
        r1 = r2.selector();
        r1.close();
        r1 = r2.channel();
        r1.close();
    L_0x0086:
        return r0;
    L_0x0087:
        a(r2, r4);	 //Catch:{ all -> 0x008b }
        goto L_0x004f;
    L_0x008b:
        r0 = move-exception;
        r1 = r2.isValid();	 //Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x0096;
    L_0x0092:
        r1 = 0;
        r2.interestOps(r1);	 //Catch:{ all -> 0x0097 }
    L_0x0096:
        throw r0;	 //Catch:{ all -> 0x0097 }
    L_0x0097:
        r0 = move-exception;
        r1 = r2;
    L_0x0099:
        if (r1 == 0) goto L_0x00a9;
    L_0x009b:
        r2 = r1.selector();
        r2.close();
        r1 = r1.channel();
        r1.close();
    L_0x00a9:
        throw r0;
    L_0x00aa:
        if (r2 == 0) goto L_0x00ba;
    L_0x00ac:
        r0 = r2.selector();
        r0.close();
        r0 = r2.channel();
        r0.close();
    L_0x00ba:
        r0 = r1;
        goto L_0x0086;
    L_0x00bc:
        r0 = move-exception;
        goto L_0x0099;
        */

    }
}