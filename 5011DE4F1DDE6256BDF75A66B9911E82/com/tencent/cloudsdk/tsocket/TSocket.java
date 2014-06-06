package com.tencent.cloudsdk.tsocket;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class TSocket {
    private static final String a;
    private d b;

    static {
        a = TSocket.class.getName();
    }

    public TSocket() {
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.tsocket.TSocket", "com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket", new Class[r1i], new Object[r1i]);
        }
    }

    public TSocket(String r7_String, int r8i) throws UnknownHostException, IOException {
        int r3i = XListViewHeader.STATE_REFRESHING;
        int r2i = 1;
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            Class[] r4_ClassA = new Class[r3i];
            r4_ClassA[r1i] = String.class;
            r4_ClassA[r2i] = Integer.TYPE;
            Object[] r5_ObjectA = new Object[r3i];
            r5_ObjectA[r1i] = r7_String;
            r5_ObjectA[r2i] = Integer.valueOf(r8i);
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.tsocket.TSocket", "com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket", r4_ClassA, r5_ObjectA);
        }
    }

    protected TSocket(String r10_String, String r11_String, int r12i, String r13_String, int r14i, int r15i) throws IOException {
        int r3i = 1;
        int r2i = 0;
        this.b = new d();
        if (this.b != null) {
            Class[] r4_ClassA = new Class[6];
            r4_ClassA[r2i] = String.class;
            r4_ClassA[r3i] = String.class;
            r4_ClassA[2] = Integer.TYPE;
            r4_ClassA[3] = String.class;
            r4_ClassA[4] = Integer.TYPE;
            r4_ClassA[5] = Integer.TYPE;
            Object[] r5_ObjectA = new Object[6];
            r5_ObjectA[r2i] = r10_String;
            r5_ObjectA[r3i] = r11_String;
            r5_ObjectA[2] = Integer.valueOf(r12i);
            r5_ObjectA[3] = r13_String;
            r5_ObjectA[4] = Integer.valueOf(r14i);
            r5_ObjectA[5] = Integer.valueOf(r15i);
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.tsocket.TSocket", "com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket", r4_ClassA, r5_ObjectA);
        }
    }

    public void close() throws IOException {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("close", new Class[r1i], new Object[r1i]);
        }
    }

    public void connect(String r2_String, int r3i) throws IOException {
        connect(r2_String, r3i, 0);
    }

    public void connect(String r7_String, int r8i, int r9i) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b != null) {
            Class[] r0_ClassA = new Class[3];
            r0_ClassA[r2i] = String.class;
            r0_ClassA[r3i] = Integer.TYPE;
            r0_ClassA[2] = Integer.TYPE;
            Object[] r1_ObjectA = new Object[3];
            r1_ObjectA[r2i] = r7_String;
            r1_ObjectA[r3i] = Integer.valueOf(r8i);
            r1_ObjectA[2] = Integer.valueOf(r9i);
            this.b.a("connect", r0_ClassA, r1_ObjectA);
        }
    }

    public void connect(SocketAddress r2_SocketAddress) throws IOException {
        connect(r2_SocketAddress, 0);
    }

    public void connect(SocketAddress r6_SocketAddress, int r7i) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b != null) {
            Class[] r0_ClassA = new Class[2];
            r0_ClassA[r2i] = SocketAddress.class;
            r0_ClassA[r3i] = Integer.TYPE;
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[r2i] = r6_SocketAddress;
            r1_ObjectA[r3i] = Integer.valueOf(r7i);
            this.b.a("connect", r0_ClassA, r1_ObjectA);
        }
    }

    public InetAddress getInetAddress() {
        int r1i = 0;
        return this.b != null ? (InetAddress) this.b.a("getInetAddress", new Class[r1i], new Object[r1i]) : null;
    }

    public InputStream getInputStream() throws IOException {
        int r1i = 0;
        return this.b != null ? (InputStream) this.b.a("getInputStream", new Class[r1i], new Object[r1i]) : null;
    }

    public OutputStream getOutputStream() throws IOException {
        int r1i = 0;
        return this.b != null ? (OutputStream) this.b.a("getOutputStream", new Class[r1i], new Object[r1i]) : null;
    }

    public int getPort() {
        int r0i = 0;
        return this.b != null ? ((Integer) this.b.a("getPort", new Class[r0i], new Object[r0i])).intValue() : 0;
    }

    public SocketAddress getRemoteSocketAddress() {
        int r1i = 0;
        return this.b != null ? (SocketAddress) this.b.a("getRemoteSocketAddress", new Class[r1i], new Object[r1i]) : null;
    }

    public Socket getSocket() {
        int r1i = 0;
        return this.b != null ? (Socket) this.b.a("getSocket", new Class[r1i], new Object[r1i]) : null;
    }
}