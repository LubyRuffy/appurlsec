package qsbk.app.thirdparty.net;

import com.baidu.kirin.KirinConfig;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import qsbk.app.bean.Base;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.Utility.Utility;
import qsbk.app.utils.image.ImageFetcher;

public class HttpManager {
    public static final String HTTPMETHOD_GET = "GET";
    private static final String a;
    private static final String b;
    private static final String c;

    private static class a extends SSLSocketFactory {
        SSLContext a;

        public a(KeyStore r6_KeyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(r6_KeyStore);
            this.a = SSLContext.getInstance("TLS");
            b r0_b = new b(this);
            SSLContext r1_SSLContext = this.a;
            TrustManager[] r2_TrustManagerA = new TrustManager[1];
            r2_TrustManagerA[0] = r0_b;
            r1_SSLContext.init(null, r2_TrustManagerA, null);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket r2_Socket, String r3_String, int r4i, boolean r5z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(r2_Socket, r3_String, r4i, r5z);
        }
    }

    static {
        a = a();
        b = "--" + a;
        c = "--" + a + "--";
    }

    static String a() {
        StringBuffer r1_StringBuffer = new StringBuffer();
        int r0i = 1;
        while (r0i < 12) {
            long r2j = System.currentTimeMillis() + ((long) r0i);
            if (r2j % 3 == 0) {
                r1_StringBuffer.append(((char) ((int) r2j)) % 9);
            } else if (r2j % 3 == 1) {
                r1_StringBuffer.append((char) ((int) (r2j % 26 + 65)));
            } else {
                r1_StringBuffer.append((char) ((int) (r2j % 26 + 97)));
            }
            r0i++;
        }
        return r1_StringBuffer.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String a(HttpResponse r7_HttpResponse) {
        /*
        r6 = -1;
        r1 = "";
        r0 = r7.getEntity();
        r2 = r0.getContent();	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r3 = new java.io.ByteArrayOutputStream;	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r3.<init>();	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r0 = "Content-Encoding";
        r0 = r7.getFirstHeader(r0);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        if (r0 == 0) goto L_0x004b;
    L_0x0018:
        r0 = r0.getValue();	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r0 = r0.toLowerCase();	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r4 = "gzip";
        r0 = r0.indexOf(r4);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        if (r0 <= r6) goto L_0x004b;
    L_0x0028:
        r0 = new java.util.zip.GZIPInputStream;	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r0.<init>(r2);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
    L_0x002d:
        r2 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r2 = new byte[r2];	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
    L_0x0031:
        r4 = r0.read(r2);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        if (r4 == r6) goto L_0x003f;
    L_0x0037:
        r5 = 0;
        r3.write(r2, r5, r4);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        goto L_0x0031;
    L_0x003c:
        r0 = move-exception;
    L_0x003d:
        r0 = r1;
    L_0x003e:
        return r0;
    L_0x003f:
        r0 = new java.lang.String;	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r2 = r3.toByteArray();	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        r0.<init>(r2);	 //Catch:{ IllegalStateException -> 0x003c, IOException -> 0x0049 }
        goto L_0x003e;
    L_0x0049:
        r0 = move-exception;
        goto L_0x003d;
    L_0x004b:
        r0 = r2;
        goto L_0x002d;
        */

    }

    private static HttpClient b() {
        try {
            KeyStore r0_KeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            r0_KeyStore.load(null, null);
            SocketFactory r1_SocketFactory = new a(r0_KeyStore);
            r1_SocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams r2_HttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(r2_HttpParams, 10000);
            HttpConnectionParams.setSoTimeout(r2_HttpParams, 10000);
            HttpProtocolParams.setVersion(r2_HttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(r2_HttpParams, Base.UTF8);
            SchemeRegistry r0_SchemeRegistry = new SchemeRegistry();
            r0_SchemeRegistry.register(new Scheme(ImageFetcher.HTTP_CACHE_DIR, PlainSocketFactory.getSocketFactory(), 80));
            r0_SchemeRegistry.register(new Scheme("https", r1_SocketFactory, 443));
            ClientConnectionManager r1_ClientConnectionManager = new ThreadSafeClientConnManager(r2_HttpParams, r0_SchemeRegistry);
            HttpConnectionParams.setConnectionTimeout(r2_HttpParams, KirinConfig.READ_TIME_OUT);
            HttpConnectionParams.setSoTimeout(r2_HttpParams, 20000);
            return new DefaultHttpClient(r1_ClientConnectionManager, r2_HttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static String openUrl(String r5_String, String r6_String, ThirdPartyParameters r7_ThirdPartyParameters) throws ThirdPartyException {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            HttpClient r1_HttpClient = b();
            HttpUriRequest r0_HttpUriRequest = null;
            r1_HttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
            if (r6_String.equals(HTTPMETHOD_GET)) {
                r0_HttpUriRequest = new HttpGet(r5_String + "?" + Utility.encodeUrl(r7_ThirdPartyParameters));
            } else if (r6_String.equals(UsersAPI.HTTPMETHOD_POST)) {
                r0_HttpUriRequest = new HttpPost(r5_String);
                String r2_String = r7_ThirdPartyParameters.getValue("content-type");
                ByteArrayOutputStream r3_ByteArrayOutputStream = new ByteArrayOutputStream();
                if (r2_String != null) {
                    r7_ThirdPartyParameters.remove("content-type");
                    r0_HttpUriRequest.setHeader("Content-Type", r2_String);
                } else {
                    r0_HttpUriRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
                }
                r3_ByteArrayOutputStream.write(Utility.encodeParameters(r7_ThirdPartyParameters).getBytes(Base.UTF8));
                byte[] r2_byteA = r3_ByteArrayOutputStream.toByteArray();
                r3_ByteArrayOutputStream.close();
                r0_HttpUriRequest.setEntity(new ByteArrayEntity(r2_byteA));
            } else if (r6_String.equals("DELETE")) {
                r0_HttpUriRequest = new HttpDelete(r5_String);
            }
            HttpResponse r0_HttpResponse = r1_HttpClient.execute(r0_HttpUriRequest);
            int r1i = r0_HttpResponse.getStatusLine().getStatusCode();
            if (r1i == 200) {
                return a(r0_HttpResponse);
            }
            throw new ThirdPartyException(a(r0_HttpResponse), r1i);
        } catch (IOException e) {
            throw new ThirdPartyException(e);
        }
    }
}