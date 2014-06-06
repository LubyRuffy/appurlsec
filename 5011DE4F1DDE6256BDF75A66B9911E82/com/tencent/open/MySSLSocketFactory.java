package com.tencent.open;

import com.qq.e.v2.constants.Constants.PLUGIN;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

// compiled from: ProGuard
public class MySSLSocketFactory extends SSLSocketFactory {
    private final SSLContext a;
    private final javax.net.ssl.SSLSocketFactory b;
    private final HostNameResolver c;

    public MySSLSocketFactory() {
        super("TLS", null, null, null, null, null);
        this.a = SSLContext.getInstance("TLS");
        SSLContext r0_SSLContext = this.a;
        TrustManager[] r1_TrustManagerA = new TrustManager[1];
        r1_TrustManagerA[0] = new s(this);
        r0_SSLContext.init(null, r1_TrustManagerA, new SecureRandom());
        this.b = this.a.getSocketFactory();
        this.c = null;
    }

    public Socket connectSocket(Socket r6_Socket, String r7_String, int r8i, InetAddress r9_InetAddress, int r10i, HttpParams r11_HttpParams) {
        if (r7_String == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        } else if (r11_HttpParams == null) {
            throw new IllegalArgumentException("Parameters may not be null.");
        } else {
            SSLSocket r0_SSLSocket = (SSLSocket) (r6_Socket != null ? r6_Socket : createSocket());
            int r2i;
            int r3i;
            if (r9_InetAddress != null || r10i > 0) {
                if (r10i < 0) {
                    r10i = 0;
                }
                r0_SSLSocket.bind(new InetSocketAddress(r9_InetAddress, r10i));
                r2i = HttpConnectionParams.getConnectionTimeout(r11_HttpParams);
                r3i = HttpConnectionParams.getSoTimeout(r11_HttpParams);
                r0_SSLSocket.connect(this.c == null ? new InetSocketAddress(this.c.resolve(r7_String), r8i) : new InetSocketAddress(r7_String, r8i), r2i);
                r0_SSLSocket.setSoTimeout(r3i);
                return r0_SSLSocket;
            } else {
                r2i = HttpConnectionParams.getConnectionTimeout(r11_HttpParams);
                r3i = HttpConnectionParams.getSoTimeout(r11_HttpParams);
                if (this.c == null) {
                }
                r0_SSLSocket.connect(this.c == null ? new InetSocketAddress(this.c.resolve(r7_String), r8i) : new InetSocketAddress(r7_String, r8i), r2i);
                r0_SSLSocket.setSoTimeout(r3i);
                return r0_SSLSocket;
            }
        }
    }

    public Socket createSocket() {
        return (SSLSocket) this.b.createSocket();
    }

    public Socket createSocket(Socket r3_Socket, String r4_String, int r5i, boolean r6z) {
        return r5i == -1 ? (SSLSocket) this.b.createSocket(r3_Socket, r4_String, PLUGIN.ASSET_PLUGIN_VERSION, r6z) : (SSLSocket) this.b.createSocket(r3_Socket, r4_String, r5i, r6z);
    }
}