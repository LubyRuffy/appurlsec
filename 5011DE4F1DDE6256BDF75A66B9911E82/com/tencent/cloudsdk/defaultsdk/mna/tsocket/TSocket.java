package com.tencent.cloudsdk.defaultsdk.mna.tsocket;

import com.tencent.cloudsdk.ay;
import com.tencent.cloudsdk.bv;
import com.tencent.cloudsdk.cd;
import com.tencent.cloudsdk.cf;
import com.tencent.cloudsdk.cj;
import com.tencent.cloudsdk.ck;
import com.tencent.cloudsdk.cn;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.cz;
import com.tencent.cloudsdk.eh;
import com.tencent.cloudsdk.ei;
import com.tencent.cloudsdk.ej;
import com.tencent.cloudsdk.ek;
import com.tencent.cloudsdk.el;
import com.tencent.cloudsdk.em;
import com.tencent.cloudsdk.en;
import com.tencent.cloudsdk.eo;
import com.tencent.cloudsdk.ep;
import com.tencent.cloudsdk.eq;
import com.tencent.cloudsdk.er;
import com.tencent.cloudsdk.et;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: SourceFile
public class TSocket {
    private static final Map g;
    private Socket a;
    private el b;
    private String c;
    private String d;
    private int e;
    private int f;

    static {
        g = Collections.synchronizedMap(new cd(256));
    }

    public TSocket() {
        if (GlobalContext.getContext() == null) {
            throw new IllegalArgumentException("You should call GlobalContext.setContext(ctx) first");
        } else {
            a(null, null, -1, -1);
            this.a = new Socket();
            a();
        }
    }

    public TSocket(String r6_String, int r7i) throws UnknownHostException, IOException {
        if (GlobalContext.getContext() == null) {
            throw new IllegalArgumentException("You should call GlobalContext.setContext(ctx) first");
        } else {
            a(r6_String.trim(), null, r7i, -1);
            this.a = new Socket();
            a();
            if (ep.b(this.c)) {
                this.d = this.c;
                er.a("TSocket", "\u4f20\u7684\u662fIp\uff0c\u8fdb\u5165\u76f4\u8fde\u901a\u9053");
                this.a.connect(new InetSocketAddress(this.c, r7i));
                this.b = new el(this.a.getOutputStream(), this.c, this.d, this.f);
            } else {
                connect(this.c, r7i);
            }
        }
    }

    public TSocket(String r3_String, String r4_String, int r5i, String r6_String, int r7i, int r8i) throws IOException {
        if (GlobalContext.getContext() == null) {
            throw new IllegalArgumentException("You should call GlobalContext.setContext(ctx) first");
        } else {
            a(r3_String, r6_String, r7i, 0);
            this.a = new Socket();
            a();
            a(r6_String, r7i, r8i);
            a(r4_String, r5i);
        }
    }

    private cn a(ck r7_ck, cn r8_cn) throws eq {
        int r1i = r8_cn.d;
        List r2_List = ep.a(r7_ck);
        Iterator r3_Iterator = r2_List.iterator();
        while (r3_Iterator.hasNext()) {
            cn r0_cn = (cn) r3_Iterator.next();
            if (r0_cn.d == r1i) {
                er.a("TSocket", new StringBuilder("\u83b7\u53d6\u5230\u4e86\u4e0eOC\u76f8\u540c\u8fd0\u8425\u5546\u7684RSIP\nip:").append(r0_cn.a).toString());
                return r0_cn;
            }
        }
        er.a("TSocket", new StringBuilder("\u672a\u83b7\u53d6\u5230\u4e0eOC\u76f8\u540c\u8fd0\u8425\u5546\u7684RSIP\nip:").append(((cn) r2_List.get(0)).a).toString());
        return (cn) r2_List.get(0);
    }

    private void a() {
        try {
            this.a.setTcpNoDelay(true);
        } catch (SocketException e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("TSocket", r0_Throwable.getMessage(), r0_Throwable);
        }
    }

    private void a(int r4i) {
        cf.a(GlobalContext.getContext()).a(this.c);
        et.c(GlobalContext.getContext(), this.c);
        ay r0_ay = new ay(this.c);
        r0_ay.a(new ej(this, this.c, r4i));
        r0_ay.a(false);
    }

    private void a(cj r5_cj) {
        cj r0_cj = (cj) r5_cj.clone();
        int r1i = bv.c().f();
        r0_cj.d = r1i;
        en.a.post(new ei(this, r0_cj, r1i));
    }

    private void a(String r10_String, int r11i) throws IOException {
        byte[] r1_byteA = new byte[16];
        r1_byteA[0] = (byte) -128;
        r1_byteA[1] = (byte) 32;
        r1_byteA[2] = (byte) 0;
        r1_byteA[3] = (byte) 16;
        byte[] r0_byteA = ep.a(r10_String);
        r1_byteA[4] = r0_byteA[0];
        r1_byteA[5] = r0_byteA[1];
        r1_byteA[6] = r0_byteA[2];
        r1_byteA[7] = r0_byteA[3];
        r0_byteA = eo.a((short) r11i);
        r1_byteA[8] = r0_byteA[0];
        r1_byteA[9] = r0_byteA[1];
        int r0i = REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
        while (r0i < 16) {
            r1_byteA[r0i] = (byte) 0;
            r0i++;
        }
        this.b = new el(this.a.getOutputStream(), this.c, this.d, this.f);
        DataOutputStream r0_DataOutputStream = new DataOutputStream(this.b);
        r0_DataOutputStream.write(r1_byteA);
        r0_DataOutputStream.flush();
    }

    private void a(String r12_String, int r13i, int r14i) throws IOException {
        int r0i = 102;
        int r1i = -1;
        try {
            this.a.connect(new InetSocketAddress(r12_String, r13i), r14i);
            this.b = new el(this.a.getOutputStream(), this.c, this.d, this.f);
            cz.b().a(this.c, r12_String, this.f, 0);
            g.remove(b(r12_String, r13i, r1i));
            g.remove(b(r12_String, r13i, r0i));
        } catch (IOException e) {
            IOException r2_IOException = e;
            if (ep.b(r12_String)) {
                int r3i;
                Object[] r6_ObjectA;
                cj r4_cj;
                Object[] r7_ObjectA;
                if (r2_IOException instanceof SocketTimeoutException) {
                    r3i = em.b();
                    if (r3i == -1) {
                        r6_ObjectA = new Object[3];
                        r6_ObjectA[0] = r12_String;
                        r6_ObjectA[1] = Integer.valueOf(r13i);
                        r6_ObjectA[2] = Integer.valueOf(r0i);
                        er.a("TSocket", String.format("\u8fde\u63a5\u5931\u8d25\uff0cip = %s, port = %d, errCode = %d", r6_ObjectA));
                        r4_cj = new cj();
                        r4_cj.a = r12_String;
                        r4_cj.b = r13i;
                        r4_cj.i = System.currentTimeMillis();
                        r4_cj.h = 0;
                        r4_cj.e = this.f;
                        r4_cj.g = em.a();
                        r4_cj.f = r3i;
                        r4_cj.c = r0i;
                        b(r4_cj);
                        cf.a(GlobalContext.getContext()).a(r4_cj, 1);
                        r3i = bv.c().f();
                        if (c(r12_String, r13i, r0i).d < r3i) {
                            r7_ObjectA = new Object[1];
                            r7_ObjectA[0] = Integer.valueOf(r3i);
                            er.a("TSocket", String.format("\u8fde\u63a5\u8fde\u7eed\u5931\u8d25\u8d85\u8fc7%d\u6b21\uff0c\u6e05\u7a7a\u8be5\u57df\u540d\u7f13\u5b58\uff0c\u5e76\u91cd\u65b0\u67e5\u8be2, \u5e76\u4e0a\u62a5\u9519\u8bef", r7_ObjectA));
                            a(r13i);
                            a(r4_cj);
                            g.remove(b(r12_String, r13i, r0i));
                        }
                        cz.b().a(this.c, r12_String, this.f, -1);
                    } else {
                        WnsClientLog.i("TSocket", "handleConnectionFailReport:No network when connect, so no need handle this fail.");
                    }
                } else {
                    r0i = -1;
                    r3i = em.b();
                    if (r3i == -1) {
                        WnsClientLog.i("TSocket", "handleConnectionFailReport:No network when connect, so no need handle this fail.");
                    } else {
                        r6_ObjectA = new Object[3];
                        r6_ObjectA[0] = r12_String;
                        r6_ObjectA[1] = Integer.valueOf(r13i);
                        r6_ObjectA[2] = Integer.valueOf(r0i);
                        er.a("TSocket", String.format("\u8fde\u63a5\u5931\u8d25\uff0cip = %s, port = %d, errCode = %d", r6_ObjectA));
                        r4_cj = new cj();
                        r4_cj.a = r12_String;
                        r4_cj.b = r13i;
                        r4_cj.i = System.currentTimeMillis();
                        r4_cj.h = 0;
                        r4_cj.e = this.f;
                        r4_cj.g = em.a();
                        r4_cj.f = r3i;
                        r4_cj.c = r0i;
                        b(r4_cj);
                        cf.a(GlobalContext.getContext()).a(r4_cj, 1);
                        r3i = bv.c().f();
                        if (c(r12_String, r13i, r0i).d < r3i) {
                            cz.b().a(this.c, r12_String, this.f, -1);
                        } else {
                            r7_ObjectA = new Object[1];
                            r7_ObjectA[0] = Integer.valueOf(r3i);
                            er.a("TSocket", String.format("\u8fde\u63a5\u8fde\u7eed\u5931\u8d25\u8d85\u8fc7%d\u6b21\uff0c\u6e05\u7a7a\u8be5\u57df\u540d\u7f13\u5b58\uff0c\u5e76\u91cd\u65b0\u67e5\u8be2, \u5e76\u4e0a\u62a5\u9519\u8bef", r7_ObjectA));
                            a(r13i);
                            a(r4_cj);
                            g.remove(b(r12_String, r13i, r0i));
                            cz.b().a(this.c, r12_String, this.f, -1);
                        }
                    }
                }
            }
            throw r2_IOException;
        }
    }

    private void a(String r1_String, String r2_String, int r3i, int r4i) {
        this.c = r1_String;
        this.d = r2_String;
        this.e = r3i;
        this.f = r4i;
    }

    private String b(String r3_String, int r4i, int r5i) {
        return new StringBuilder(String.valueOf(r3_String)).append(":").append(r4i).append(":").append(r5i).toString();
    }

    private void b(cj r5_cj) {
        String r1_String = b(r5_cj.a, r5_cj.b, r5_cj.c);
        cj r0_cj = c(r5_cj.a, r5_cj.b, r5_cj.c);
        if (r0_cj == null) {
            r0_cj = (cj) r5_cj.clone();
            r0_cj.d = 1;
            g.put(r1_String, r0_cj);
        } else {
            r0_cj.d++;
        }
    }

    private cj c(String r3_String, int r4i, int r5i) {
        return (cj) g.get(b(r3_String, r4i, r5i));
    }

    public void bind(SocketAddress r2_SocketAddress) throws IOException {
        this.a.bind(r2_SocketAddress);
    }

    public synchronized void close() throws IOException {
        this.a.close();
    }

    public void connect(String r2_String, int r3i) throws IOException {
        connect(r2_String, r3i, 0);
    }

    public void connect(String r6_String, int r7i, int r8i) throws IOException {
        a(r6_String, null, r7i, -1);
        try {
            List r0_List = ep.a(GlobalContext.getContext(), r6_String, false);
            ck r1_ck = ep.a(r0_List);
            cn r0_cn = (cn) r0_List.get(0);
            if (r0_cn.b == 0) {
                this.d = r0_cn.a;
                this.f = r0_cn.b;
                er.a("TSocket", new StringBuilder("\u9009\u62e9\u7684\u662fOCIP\nIP:").append(r0_cn.a).append("\nport:").append(r0_cn.c).append("\nsp:").append(r0_cn.d).toString());
                a(r0_cn.a, r0_cn.c, r8i);
                a(a(r1_ck, r0_cn).a, r7i);
                eh.a().a(r6_String, r7i, false);
            } else {
                if (r0_cn.b == 2) {
                    this.d = null;
                    this.f = -1;
                    er.a("TSocket", new StringBuilder("\u9009\u62e9\u7684\u662fSpeedIp\uff0c\u76f4\u8fde\u57df\u540d\uff1a").append(r6_String).toString());
                    a(r6_String, r7i, r8i);
                } else if (r0_cn.b == 1) {
                    this.d = r0_cn.a;
                    this.f = r0_cn.b;
                    er.a("TSocket", new StringBuilder("\u9009\u62e9\u7684\u662fRsIp\nIP:").append(r0_cn.a).append("\nport:").append(r7i).append("\nsp:").append(r0_cn.d).toString());
                    a(r0_cn.a, r7i, r8i);
                } else {
                    this.d = null;
                    this.f = -1;
                    er.a("TSocket", new StringBuilder("\u4eceOC\u67e5\u8be2\u83b7\u53d6\u5230\u7684\u7ed3\u679c\u5f02\u5e38\uff0c\u8d70\u76f4\u8fde\nIP:").append(r0_cn.a).append("\nport:").append(r7i).append("\nsp:").append(r0_cn.d).append("\n").toString());
                    a(r6_String, r7i, r8i);
                }
                eh.a().a(r6_String, r7i, false);
            }
        } catch (eq e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("TSocket", r0_Throwable.getMessage(), r0_Throwable);
            er.a("TSocket", "\u4eceans\u67e5\u8be2\u8fc7\u7a0b\u6709\u5f02\u5e38\uff0c\u8d70\u57df\u540d\u76f4\u8fde");
            a(r6_String, r7i, r8i);
        }
    }

    public void connect(SocketAddress r2_SocketAddress) throws IOException {
        connect(r2_SocketAddress, 0);
    }

    public void connect(SocketAddress r6_SocketAddress, int r7i) throws IOException {
        if (r6_SocketAddress instanceof InetSocketAddress) {
            InetSocketAddress r6_InetSocketAddress = (InetSocketAddress) r6_SocketAddress;
            this.c = r6_InetSocketAddress.getHostName();
            this.e = r6_InetSocketAddress.getPort();
            this.d = null;
            this.f = -1;
            if (ep.b(this.c)) {
                er.a("TSocket", "\u4f20\u7684\u662fIp\uff0c\u8fdb\u5165\u76f4\u8fde\u901a\u9053");
                this.a.connect(r6_InetSocketAddress, r7i);
                this.b = new el(this.a.getOutputStream(), this.c, this.d, this.f);
            } else {
                connect(this.c, this.e, r7i);
            }
        } else {
            throw new IllegalArgumentException(new StringBuilder("Remote address not an InetSocketAddress: ").append(r6_SocketAddress.getClass()).toString());
        }
    }

    public SocketChannel getChannel() {
        return this.a.getChannel();
    }

    public InetAddress getInetAddress() {
        if (this.a == null || (!this.a.isConnected())) {
            return null;
        }
        try {
            if (ep.b(this.c)) {
                return InetAddress.getByAddress(ep.a(this.c));
            }
            if (this.d == null) {
                return InetAddress.getByName(this.c);
            }
            return InetAddress.getByAddress(this.c, ep.a(this.d));
        } catch (UnknownHostException e) {
            Throwable r1_Throwable = e;
            WnsClientLog.e("TSocket", r1_Throwable.getMessage(), r1_Throwable);
            return null;
        }
    }

    public InputStream getInputStream() throws IOException {
        return new ek(this.a.getInputStream(), this.c, this.d, this.f);
    }

    public boolean getKeepAlive() throws SocketException {
        return this.a.getKeepAlive();
    }

    public InetAddress getLocalAddress() {
        return this.a.getLocalAddress();
    }

    public int getLocalPort() {
        return this.a.getLocalPort();
    }

    public SocketAddress getLocalSocketAddress() {
        return this.a.getLocalSocketAddress();
    }

    public boolean getOOBInline() throws SocketException {
        return this.a.getOOBInline();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.b;
    }

    public int getPort() {
        return (this.a == null || (!this.a.isConnected())) ? 0 : this.e;
    }

    public synchronized int getReceiveBufferSize() throws SocketException {
        return this.a.getReceiveBufferSize();
    }

    public SocketAddress getRemoteSocketAddress() {
        return (this.a == null || (!this.a.isConnected())) ? null : new InetSocketAddress(this.c, this.e);
    }

    public boolean getReuseAddress() throws SocketException {
        return this.a.getReuseAddress();
    }

    public synchronized int getSendBufferSize() throws SocketException {
        return this.a.getSendBufferSize();
    }

    public int getSoLinger() throws SocketException {
        return this.a.getSoLinger();
    }

    public synchronized int getSoTimeout() throws SocketException {
        return this.a.getSoTimeout();
    }

    public Socket getSocket() {
        return this.a;
    }

    public boolean getTcpNoDelay() throws SocketException {
        return this.a.getTcpNoDelay();
    }

    public int getTrafficClass() throws SocketException {
        return this.a.getTrafficClass();
    }

    public boolean isBound() {
        return this.a.isBound();
    }

    public boolean isClosed() {
        return this.a.isClosed();
    }

    public boolean isConnected() {
        return this.a.isConnected();
    }

    public boolean isInputShutdown() {
        return this.a.isInputShutdown();
    }

    public boolean isOutputShutdown() {
        return this.a.isOutputShutdown();
    }

    public void sendUrgentData(int r2i) throws IOException {
        this.a.sendUrgentData(r2i);
    }

    public void setKeepAlive(boolean r2z) throws SocketException {
        this.a.setKeepAlive(r2z);
    }

    public void setOOBInline(boolean r2z) throws SocketException {
        this.a.setOOBInline(r2z);
    }

    public synchronized void setReceiveBufferSize(int r2i) throws SocketException {
        this.a.setReceiveBufferSize(r2i);
    }

    public void setReuseAddress(boolean r2z) throws SocketException {
        this.a.setReuseAddress(r2z);
    }

    public synchronized void setSendBufferSize(int r2i) throws SocketException {
        this.a.setSendBufferSize(r2i);
    }

    public void setSoLinger(boolean r2z, int r3i) throws SocketException {
        this.a.setSoLinger(r2z, r3i);
    }

    public synchronized void setSoTimeout(int r2i) throws SocketException {
        this.a.setSoTimeout(r2i);
    }

    public void setTcpNoDelay(boolean r2z) throws SocketException {
        this.a.setTcpNoDelay(r2z);
    }

    public void setTrafficClass(int r2i) throws SocketException {
        this.a.setTrafficClass(r2i);
    }

    public void shutdownInput() throws IOException {
        this.a.shutdownInput();
    }

    public void shutdownOutput() throws IOException {
        this.a.shutdownOutput();
    }
}