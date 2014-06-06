package com.androidquery.callback;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.View;
import com.androidquery.auth.AccountHandle;
import com.androidquery.auth.GoogleHandle;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.Progress;
import com.androidquery.util.XmlDom;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.xmlpull.v1.XmlPullParser;
import qsbk.app.bean.Base;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class AbstractAjaxCallback<T, K> implements Runnable {
    private static Transformer J;
    private static final Class<?>[] L;
    private static ExecutorService P;
    private static SocketFactory Q;
    private static DefaultHttpClient R;
    private static ProxyHandle S;
    private static int T;
    private static int i;
    private static String j;
    private static int k;
    private static boolean l;
    private static boolean m;
    private static boolean n;
    private int A;
    private boolean B;
    private long C;
    private String D;
    private WeakReference<Activity> E;
    private int F;
    private HttpUriRequest G;
    private boolean H;
    private int I;
    private HttpHost K;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean U;
    protected Map<String, Object> a;
    protected Map<String, String> b;
    protected Map<String, String> c;
    protected T d;
    protected AccountHandle e;
    protected AjaxStatus f;
    protected boolean g;
    protected boolean h;
    private Class<T> o;
    private Reference<Object> p;
    private Object q;
    private String r;
    private WeakReference<Object> s;
    private String t;
    private String u;
    private Transformer v;
    private int w;
    private File x;
    private File y;
    private boolean z;

    static {
        i = 30000;
        j = null;
        k = 4;
        l = true;
        m = true;
        n = false;
        Class[] r0_ClassA = new Class[3];
        r0_ClassA[0] = String.class;
        r0_ClassA[1] = Object.class;
        r0_ClassA[2] = AjaxStatus.class;
        L = r0_ClassA;
        T = 200;
    }

    public AbstractAjaxCallback() {
        this.w = 0;
        this.A = 0;
        this.B = true;
        this.D = Base.UTF8;
        this.F = 4;
        this.H = true;
        this.I = 0;
    }

    private File a(File r4_File) throws IOException {
        File r0_File = new File(new StringBuilder(String.valueOf(r4_File.getAbsolutePath())).append(".tmp").toString());
        r0_File.createNewFile();
        return r0_File;
    }

    private static String a(Uri r3_Uri) {
        String r0_String = new StringBuilder(String.valueOf(r3_Uri.getScheme())).append("://").append(r3_Uri.getAuthority()).append(r3_Uri.getPath()).toString();
        String r1_String = r3_Uri.getFragment();
        return r1_String != null ? new StringBuilder(String.valueOf(r0_String)).append("#").append(r1_String).toString() : r0_String;
    }

    private static String a(String r5_String, String r6_String) {
        byte[] r0_byteA = new StringBuilder(String.valueOf(r5_String)).append(":").append(r6_String).toString().getBytes();
        return new StringBuilder("Basic ").append(new String(AQUtility.encode64(r0_byteA, 0, r0_byteA.length))).toString();
    }

    private String a(HttpEntity r3_HttpEntity) {
        if (r3_HttpEntity == null) {
            return null;
        }
        Header r1_Header = r3_HttpEntity.getContentEncoding();
        return r1_Header != null ? r1_Header.getValue() : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String a(byte[] r5_byteA, String r6_String, AjaxStatus r7_AjaxStatus) {
        /*
        r4_this = this;
        r2 = 0;
        r0 = "utf-8";
        r0 = r0.equalsIgnoreCase(r6);	 //Catch:{ Exception -> 0x0026 }
        if (r0 != 0) goto L_0x000f;
    L_0x0009:
        r0 = new java.lang.String;	 //Catch:{ Exception -> 0x0026 }
        r0.<init>(r5, r6);	 //Catch:{ Exception -> 0x0026 }
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = "Content-Type";
        r0 = r7.getHeader(r0);	 //Catch:{ Exception -> 0x0026 }
        r1 = r4.d(r0);	 //Catch:{ Exception -> 0x0026 }
        r0 = "parsing header";
        com.androidquery.util.AQUtility.debug(r0, r1);	 //Catch:{ Exception -> 0x0026 }
        if (r1 == 0) goto L_0x002d;
    L_0x0020:
        r0 = new java.lang.String;	 //Catch:{ Exception -> 0x0026 }
        r0.<init>(r5, r1);	 //Catch:{ Exception -> 0x0026 }
        goto L_0x000e;
    L_0x0026:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0029:
        com.androidquery.util.AQUtility.report(r1);
        goto L_0x000e;
    L_0x002d:
        r1 = new java.lang.String;	 //Catch:{ Exception -> 0x0026 }
        r0 = "utf-8";
        r1.<init>(r5, r0);	 //Catch:{ Exception -> 0x0026 }
        r2 = r4.c(r1);	 //Catch:{ Exception -> 0x005d }
        r0 = "parsing needed";
        com.androidquery.util.AQUtility.debug(r0, r2);	 //Catch:{ Exception -> 0x005d }
        if (r2 == 0) goto L_0x0062;
    L_0x003f:
        r0 = "utf-8";
        r0 = r0.equalsIgnoreCase(r2);	 //Catch:{ Exception -> 0x005d }
        if (r0 != 0) goto L_0x0062;
    L_0x0047:
        r0 = "correction needed";
        com.androidquery.util.AQUtility.debug(r0, r2);	 //Catch:{ Exception -> 0x005d }
        r0 = new java.lang.String;	 //Catch:{ Exception -> 0x005d }
        r0.<init>(r5, r2);	 //Catch:{ Exception -> 0x005d }
        r1 = "utf-8";
        r1 = r0.getBytes(r1);	 //Catch:{ Exception -> 0x005b }
        r7.a(r1);	 //Catch:{ Exception -> 0x005b }
        goto L_0x000e;
    L_0x005b:
        r1 = move-exception;
        goto L_0x0029;
    L_0x005d:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x0029;
    L_0x0062:
        r0 = r1;
        goto L_0x000e;
        */

    }

    private HttpResponse a(HttpUriRequest r5_HttpUriRequest, DefaultHttpClient r6_DefaultHttpClient, HttpContext r7_HttpContext) throws ClientProtocolException, IOException {
        if (!r5_HttpUriRequest.getURI().getAuthority().contains("_")) {
            return r6_DefaultHttpClient.execute(r5_HttpUriRequest, r7_HttpContext);
        }
        URL r1_URL = r5_HttpUriRequest.getURI().toURL();
        return r6_DefaultHttpClient.execute(r1_URL.getPort() == -1 ? new HttpHost(r1_URL.getHost(), 80, r1_URL.getProtocol()) : new HttpHost(r1_URL.getHost(), r1_URL.getPort(), r1_URL.getProtocol()), r5_HttpUriRequest, r7_HttpContext);
    }

    private void a(int r4i) throws IOException {
        if (r4i <= 1) {
            p();
        } else {
            int r0i = 0;
            while (r0i < r4i) {
                try {
                    p();
                    return;
                } catch (IOException e) {
                    IOException r1_IOException = e;
                    if (r0i == r4i - 1) {
                        throw r1_IOException;
                    } else {
                        r0i++;
                    }
                }
            }
        }
    }

    private static void a(DataOutputStream r2_DataOutputStream, String r3_String, Object r4_Object) throws IOException {
        if (r4_Object == null) {
        } else if (r4_Object instanceof File) {
            File r4_File = (File) r4_Object;
            a(r2_DataOutputStream, r3_String, r4_File.getName(), new FileInputStream(r4_File));
        } else if (r4_Object instanceof byte[]) {
            a(r2_DataOutputStream, r3_String, r3_String, new ByteArrayInputStream((byte[]) r4_Object));
        } else if (r4_Object instanceof InputStream) {
            a(r2_DataOutputStream, r3_String, r3_String, (InputStream) r4_Object);
        } else {
            a(r2_DataOutputStream, r3_String, r4_Object.toString());
        }
    }

    private static void a(DataOutputStream r2_DataOutputStream, String r3_String, String r4_String) throws IOException {
        r2_DataOutputStream.writeBytes("--*****\r\n");
        r2_DataOutputStream.writeBytes(new StringBuilder("Content-Disposition: form-data; name=\"").append(r3_String).append("\"").toString());
        r2_DataOutputStream.writeBytes("\r\n");
        r2_DataOutputStream.writeBytes("\r\n");
        r2_DataOutputStream.write(r4_String.getBytes(Base.UTF8));
        r2_DataOutputStream.writeBytes("\r\n");
    }

    private static void a(DataOutputStream r2_DataOutputStream, String r3_String, String r4_String, InputStream r5_InputStream) throws IOException {
        r2_DataOutputStream.writeBytes("--*****\r\n");
        r2_DataOutputStream.writeBytes(new StringBuilder("Content-Disposition: form-data; name=\"").append(r3_String).append("\";").append(" filename=\"").append(r4_String).append("\"").append("\r\n").toString());
        r2_DataOutputStream.writeBytes("Content-Type: application/octet-stream");
        r2_DataOutputStream.writeBytes("\r\n");
        r2_DataOutputStream.writeBytes("Content-Transfer-Encoding: binary");
        r2_DataOutputStream.writeBytes("\r\n");
        r2_DataOutputStream.writeBytes("\r\n");
        AQUtility.copy(r5_InputStream, r2_DataOutputStream);
        r2_DataOutputStream.writeBytes("\r\n");
    }

    private void a(InputStream r3_InputStream, OutputStream r4_OutputStream, int r5i) throws IOException {
        Object r1_Object;
        Progress r0_Progress = null;
        r1_Object = this.s != null ? this.s.get() : null;
        if (r1_Object != null) {
            r0_Progress = new Progress(r1_Object);
        }
        AQUtility.copy(r3_InputStream, r4_OutputStream, r5i, r0_Progress);
    }

    private void a(InputStream r3_InputStream, OutputStream r4_OutputStream, int r5i, File r6_File, File r7_File) throws IOException {
        if (r7_File == null) {
            a(r3_InputStream, r4_OutputStream, r5i);
        } else {
            try {
                a(r3_InputStream, r4_OutputStream, r5i);
                r3_InputStream.close();
                r4_OutputStream.close();
                r6_File.renameTo(r7_File);
            } catch (IOException e) {
                AQUtility.debug((Object)"copy failed, deleting files");
                r6_File.delete();
                r7_File.delete();
                AQUtility.close(r3_InputStream);
                AQUtility.close(r4_OutputStream);
                throw e;
            }
        }
    }

    private void a(String r3_String, AjaxStatus r4_AjaxStatus) throws IOException {
        AQUtility.debug("get", r3_String);
        String r0_String = f(r3_String);
        a(new HttpGet(r0_String), r0_String, r4_AjaxStatus);
    }

    private void a(String r2_String, Map<String, Object> r3_Map_String__Object, AjaxStatus r4_AjaxStatus) throws ClientProtocolException, IOException {
        AQUtility.debug("post", r2_String);
        a(r2_String, new HttpPost(r2_String), (Map)r3_Map_String__Object, r4_AjaxStatus);
    }

    private void a(String r6_String, HttpEntityEnclosingRequestBase r7_HttpEntityEnclosingRequestBase, Map<String, Object> r8_Map_String__Object, AjaxStatus r9_AjaxStatus) throws ClientProtocolException, IOException {
        HttpEntity r0_HttpEntity;
        r7_HttpEntityEnclosingRequestBase.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        Object r0_Object = r8_Map_String__Object.get(Constants.POST_ENTITY);
        if (r0_Object instanceof HttpEntity) {
            r0_HttpEntity = (HttpEntity) r0_Object;
        } else {
            List r1_List = new ArrayList();
            Iterator r2_Iterator = r8_Map_String__Object.entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                Object r3_Object = r0_Entry.getValue();
                if (r3_Object != null) {
                    r1_List.add(new BasicNameValuePair((String) r0_Entry.getKey(), r3_Object.toString()));
                }
            }
            r0_HttpEntity = new UrlEncodedFormEntity(r1_List, Base.UTF8);
        }
        if (this.b == null || this.b.containsKey("Content-Type")) {
            r7_HttpEntityEnclosingRequestBase.setEntity(r0_HttpEntity);
            a((HttpUriRequest)r7_HttpEntityEnclosingRequestBase, r6_String, r9_AjaxStatus);
        } else {
            this.b.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            r7_HttpEntityEnclosingRequestBase.setEntity(r0_HttpEntity);
            a((HttpUriRequest)r7_HttpEntityEnclosingRequestBase, r6_String, r9_AjaxStatus);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(HttpUriRequest r20_HttpUriRequest, String r21_String, AjaxStatus r22_AjaxStatus) throws ClientProtocolException, IOException {
        /*
        r19_this = this;
        r12 = r();
        r2 = S;
        if (r2 == 0) goto L_0x0011;
    L_0x0008:
        r2 = S;
        r0 = r19;
        r1 = r20;
        r2.applyProxy(r0, r1, r12);
    L_0x0011:
        r2 = j;
        if (r2 == 0) goto L_0x00d5;
    L_0x0015:
        r2 = "User-Agent";
        r3 = j;
        r0 = r20;
        r0.addHeader(r2, r3);
    L_0x001e:
        r0 = r19;
        r2 = r0.b;
        if (r2 == 0) goto L_0x0036;
    L_0x0024:
        r0 = r19;
        r2 = r0.b;
        r2 = r2.keySet();
        r4 = r2.iterator();
    L_0x0030:
        r2 = r4.hasNext();
        if (r2 != 0) goto L_0x00e8;
    L_0x0036:
        r2 = l;
        if (r2 == 0) goto L_0x0055;
    L_0x003a:
        r0 = r19;
        r2 = r0.b;
        if (r2 == 0) goto L_0x004c;
    L_0x0040:
        r0 = r19;
        r2 = r0.b;
        r3 = "Accept-Encoding";
        r2 = r2.containsKey(r3);
        if (r2 != 0) goto L_0x0055;
    L_0x004c:
        r2 = "Accept-Encoding";
        r3 = "gzip";
        r0 = r20;
        r0.addHeader(r2, r3);
    L_0x0055:
        r0 = r19;
        r2 = r0.e;
        if (r2 == 0) goto L_0x0066;
    L_0x005b:
        r0 = r19;
        r2 = r0.e;
        r0 = r19;
        r1 = r20;
        r2.applyToken(r0, r1);
    L_0x0066:
        r2 = r19.s();
        if (r2 == 0) goto L_0x0073;
    L_0x006c:
        r3 = "Cookie";
        r0 = r20;
        r0.addHeader(r3, r2);
    L_0x0073:
        r2 = r20.getParams();
        r0 = r19;
        r3 = r0.K;
        if (r3 == 0) goto L_0x0086;
    L_0x007d:
        r3 = "http.route.default-proxy";
        r0 = r19;
        r4 = r0.K;
        r2.setParameter(r3, r4);
    L_0x0086:
        r0 = r19;
        r3 = r0.A;
        if (r3 <= 0) goto L_0x00a6;
    L_0x008c:
        r3 = "http.connection.timeout";
        r0 = r19;
        r4 = r0.A;
        r4 = java.lang.Integer.valueOf(r4);
        r2.setParameter(r3, r4);
        r3 = "http.socket.timeout";
        r0 = r19;
        r4 = r0.A;
        r4 = java.lang.Integer.valueOf(r4);
        r2.setParameter(r3, r4);
    L_0x00a6:
        r0 = r19;
        r3 = r0.B;
        if (r3 != 0) goto L_0x00b2;
    L_0x00ac:
        r3 = "http.protocol.handle-redirects";
        r4 = 0;
        r2.setBooleanParameter(r3, r4);
    L_0x00b2:
        r13 = new org.apache.http.protocol.BasicHttpContext;
        r13.<init>();
        r3 = new org.apache.http.impl.client.BasicCookieStore;
        r3.<init>();
        r4 = "http.cookie-store";
        r13.setAttribute(r4, r3);
        r0 = r20;
        r1 = r19;
        r1.G = r0;
        r0 = r19;
        r3 = r0.U;
        if (r3 == 0) goto L_0x00ff;
    L_0x00cd:
        r2 = new java.io.IOException;
        r3 = "Aborted";
        r2.<init>(r3);
        throw r2;
    L_0x00d5:
        r2 = j;
        if (r2 != 0) goto L_0x001e;
    L_0x00d9:
        r2 = l;
        if (r2 == 0) goto L_0x001e;
    L_0x00dd:
        r2 = "User-Agent";
        r3 = "gzip";
        r0 = r20;
        r0.addHeader(r2, r3);
        goto L_0x001e;
    L_0x00e8:
        r2 = r4.next();
        r2 = (java.lang.String) r2;
        r0 = r19;
        r3 = r0.b;
        r3 = r3.get(r2);
        r3 = (java.lang.String) r3;
        r0 = r20;
        r0.addHeader(r2, r3);
        goto L_0x0030;
    L_0x00ff:
        r3 = n;
        if (r3 == 0) goto L_0x010b;
    L_0x0103:
        r2 = new java.io.IOException;
        r3 = "Simulated Error";
        r2.<init>(r3);
        throw r2;
    L_0x010b:
        r0 = r19;
        r1 = r20;
        r2 = r0.a(r1, r12, r13);	 //Catch:{ HttpHostConnectException -> 0x01a1 }
        r8 = r2;
    L_0x0114:
        r10 = 0;
        r2 = r8.getStatusLine();
        r14 = r2.getStatusCode();
        r2 = r8.getStatusLine();
        r15 = r2.getReasonPhrase();
        r9 = 0;
        r5 = r8.getEntity();
        r7 = 0;
        r6 = 0;
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r14 < r2) goto L_0x0134;
    L_0x0130:
        r2 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r14 < r2) goto L_0x01d8;
    L_0x0134:
        r2 = 0;
        if (r5 == 0) goto L_0x02aa;
    L_0x0137:
        r2 = r5.getContent();	 //Catch:{ Exception -> 0x01bf, all -> 0x01ce }
        r0 = r19;
        r3 = r0.a(r5);	 //Catch:{ Exception -> 0x029a, all -> 0x028f }
        r0 = r19;
        r4 = r0.a(r3, r2);	 //Catch:{ Exception -> 0x029a, all -> 0x028f }
        r3 = new java.lang.String;	 //Catch:{ Exception -> 0x029a, all -> 0x028f }
        r5 = "UTF-8";
        r3.<init>(r4, r5);	 //Catch:{ Exception -> 0x029a, all -> 0x028f }
        r4 = "error";
        com.androidquery.util.AQUtility.debug(r4, r3);	 //Catch:{ Exception -> 0x02a2, all -> 0x028f }
    L_0x0153:
        com.androidquery.util.AQUtility.close(r2);
        r9 = r3;
        r2 = r21;
    L_0x0159:
        r3 = "response";
        r4 = java.lang.Integer.valueOf(r14);
        com.androidquery.util.AQUtility.debug(r3, r4);
        if (r10 == 0) goto L_0x016e;
    L_0x0164:
        r3 = r10.length;
        r3 = java.lang.Integer.valueOf(r3);
        r0 = r21;
        com.androidquery.util.AQUtility.debug(r3, r0);
    L_0x016e:
        r0 = r22;
        r3 = r0.code(r14);
        r3 = r3.message(r15);
        r3 = r3.a(r9);
        r2 = r3.b(r2);
        r3 = new java.util.Date;
        r3.<init>();
        r2 = r2.a(r3);
        r2 = r2.a(r10);
        r2 = r2.a(r7);
        r2 = r2.a(r12);
        r2 = r2.a(r13);
        r3 = r8.getAllHeaders();
        r2.a(r3);
        return;
    L_0x01a1:
        r3 = move-exception;
        r0 = r19;
        r4 = r0.K;
        if (r4 == 0) goto L_0x01be;
    L_0x01a8:
        r3 = "proxy failed, retrying without proxy";
        com.androidquery.util.AQUtility.debug(r3);
        r3 = "http.route.default-proxy";
        r4 = 0;
        r2.setParameter(r3, r4);
        r0 = r19;
        r1 = r20;
        r2 = r0.a(r1, r12, r13);
        r8 = r2;
        goto L_0x0114;
    L_0x01be:
        throw r3;
    L_0x01bf:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
    L_0x01c5:
        com.androidquery.util.AQUtility.debug(r2);	 //Catch:{ all -> 0x0297 }
        com.androidquery.util.AQUtility.close(r3);
        r2 = r21;
        goto L_0x0159;
    L_0x01ce:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
    L_0x01d4:
        com.androidquery.util.AQUtility.close(r3);
        throw r2;
    L_0x01d8:
        r2 = "http.target_host";
        r2 = r13.getAttribute(r2);
        r2 = (org.apache.http.HttpHost) r2;
        r3 = "http.request";
        r3 = r13.getAttribute(r3);
        r3 = (org.apache.http.client.methods.HttpUriRequest) r3;
        r4 = new java.lang.StringBuilder;
        r2 = r2.toURI();
        r2 = java.lang.String.valueOf(r2);
        r4.<init>(r2);
        r2 = r3.getURI();
        r2 = r4.append(r2);
        r11 = r2.toString();
        r2 = 32;
        r3 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r16 = r5.getContentLength();
        r0 = r16;
        r4 = (int) r0;
        r3 = java.lang.Math.min(r3, r4);
        r16 = java.lang.Math.max(r2, r3);
        r4 = 0;
        r3 = 0;
        r7 = r19.n();	 //Catch:{ all -> 0x0287 }
        if (r7 != 0) goto L_0x025e;
    L_0x021c:
        r2 = new com.androidquery.util.PredefinedBAOS;	 //Catch:{ all -> 0x0287 }
        r0 = r16;
        r2.<init>(r0);	 //Catch:{ all -> 0x0287 }
        r4 = r2;
    L_0x0224:
        r3 = r5.getContent();	 //Catch:{ all -> 0x0287 }
        r2 = "gzip";
        r0 = r19;
        r16 = r0.a(r5);	 //Catch:{ all -> 0x0287 }
        r0 = r16;
        r2 = r2.equalsIgnoreCase(r0);	 //Catch:{ all -> 0x0287 }
        if (r2 == 0) goto L_0x023e;
    L_0x0238:
        r2 = new java.util.zip.GZIPInputStream;	 //Catch:{ all -> 0x0287 }
        r2.<init>(r3);	 //Catch:{ all -> 0x0287 }
        r3 = r2;
    L_0x023e:
        r16 = r5.getContentLength();	 //Catch:{ all -> 0x0287 }
        r0 = r16;
        r5 = (int) r0;	 //Catch:{ all -> 0x0287 }
        r2 = r19;
        r2.a(r3, r4, r5, r6, r7);	 //Catch:{ all -> 0x0287 }
        if (r7 != 0) goto L_0x0274;
    L_0x024c:
        r0 = r4;
        r0 = (com.androidquery.util.PredefinedBAOS) r0;	 //Catch:{ all -> 0x0287 }
        r2 = r0;
        r2 = r2.toByteArray();	 //Catch:{ all -> 0x0287 }
    L_0x0254:
        com.androidquery.util.AQUtility.close(r3);
        com.androidquery.util.AQUtility.close(r4);
        r10 = r2;
        r2 = r11;
        goto L_0x0159;
    L_0x025e:
        r0 = r19;
        r6 = r0.a(r7);	 //Catch:{ all -> 0x0287 }
        r2 = new java.io.BufferedOutputStream;	 //Catch:{ all -> 0x0287 }
        r16 = new java.io.FileOutputStream;	 //Catch:{ all -> 0x0287 }
        r0 = r16;
        r0.<init>(r6);	 //Catch:{ all -> 0x0287 }
        r0 = r16;
        r2.<init>(r0);	 //Catch:{ all -> 0x0287 }
        r4 = r2;
        goto L_0x0224;
    L_0x0274:
        r2 = r7.exists();	 //Catch:{ all -> 0x0287 }
        if (r2 == 0) goto L_0x0284;
    L_0x027a:
        r5 = r7.length();	 //Catch:{ all -> 0x0287 }
        r16 = 0;
        r2 = (r5 > r16 ? 1 : (r5 == r16? 0 : -1));
        if (r2 != 0) goto L_0x02a8;
    L_0x0284:
        r7 = 0;
        r2 = r10;
        goto L_0x0254;
    L_0x0287:
        r2 = move-exception;
        com.androidquery.util.AQUtility.close(r3);
        com.androidquery.util.AQUtility.close(r4);
        throw r2;
    L_0x028f:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x01d4;
    L_0x0297:
        r2 = move-exception;
        goto L_0x01d4;
    L_0x029a:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x01c5;
    L_0x02a2:
        r4 = move-exception;
        r9 = r3;
        r3 = r2;
        r2 = r4;
        goto L_0x01c5;
    L_0x02a8:
        r2 = r10;
        goto L_0x0254;
    L_0x02aa:
        r3 = r9;
        goto L_0x0153;
        */

    }

    private static boolean a(Map<String, Object> r3_Map_String__Object) {
        Iterator r1_Iterator = r3_Map_String__Object.entrySet().iterator();
        while (r1_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r1_Iterator.next();
            Object r2_Object = r0_Entry.getValue();
            AQUtility.debug(r0_Entry.getKey(), r2_Object);
            if (r2_Object instanceof File || r2_Object instanceof byte[]) {
                return true;
            }
            if (r2_Object instanceof InputStream) {
                return true;
            }
        }
        return false;
    }

    private byte[] a(String r2_String, InputStream r3_InputStream) throws IOException {
        if ("gzip".equalsIgnoreCase(r2_String)) {
            r3_InputStream = new GZIPInputStream(r3_InputStream);
        }
        return AQUtility.toBytes(r3_InputStream);
    }

    private static Map<String, Object> b(Uri r9_Uri) {
        Map<String, Object> r2_Map_String__Object = new HashMap();
        String[] r3_StringA = r9_Uri.getQuery().split("&");
        int r4i = r3_StringA.length;
        int r0i = 0;
        while (r0i < r4i) {
            String[] r5_StringA = r3_StringA[r0i].split("=");
            if (r5_StringA.length >= 2) {
                r2_Map_String__Object.put(r5_StringA[0], r5_StringA[1]);
            } else if (r5_StringA.length == 1) {
                r2_Map_String__Object.put(r5_StringA[0], RContactStorage.PRIMARY_KEY);
            }
            r0i++;
        }
        return r2_Map_String__Object;
    }

    private void b(Context r3_Context) {
        Object r0_Object = b(this.t);
        if (r0_Object != null) {
            this.d = r0_Object;
            this.f.a((int)XListViewFooter.STATE_NODATA).done();
            a();
        } else {
            this.x = AQUtility.getCacheDir(r3_Context, this.w);
            execute(this);
        }
    }

    private void b(String r3_String, AjaxStatus r4_AjaxStatus) throws IOException {
        AQUtility.debug("get", r3_String);
        String r0_String = f(r3_String);
        a(new HttpDelete(r0_String), r0_String, r4_AjaxStatus);
    }

    private void b(String r2_String, Map<String, Object> r3_Map_String__Object, AjaxStatus r4_AjaxStatus) throws ClientProtocolException, IOException {
        AQUtility.debug("put", r2_String);
        a(r2_String, new HttpPut(r2_String), (Map)r3_Map_String__Object, r4_AjaxStatus);
    }

    private String c(String r3_String) {
        Matcher r0_Matcher = Pattern.compile("<meta [^>]*http-equiv[^>]*\"Content-Type\"[^>]*>", XListViewHeader.STATE_REFRESHING).matcher(r3_String);
        return r0_Matcher.find() ? d(r0_Matcher.group()) : null;
    }

    private void c(String r10_String, Map<String, Object> r11_Map_String__Object, AjaxStatus r12_AjaxStatus) throws IOException {
        Proxy r0_Proxy;
        HttpURLConnection r2_HttpURLConnection;
        String r0_String;
        byte[] r1_byteA;
        AQUtility.debug("multipart", r10_String);
        URL r1_URL = new URL(r10_String);
        if (this.K != null) {
            AQUtility.debug("proxy", this.K);
            r0_Proxy = new Proxy(Type.HTTP, new InetSocketAddress(this.K.getHostName(), this.K.getPort()));
        } else if (S != null) {
            r0_Proxy = S.makeProxy(this);
        } else {
            r0_Proxy = null;
        }
        r2_HttpURLConnection = r0_Proxy == null ? (HttpURLConnection) r1_URL.openConnection() : (HttpURLConnection) r1_URL.openConnection(r0_Proxy);
        r2_HttpURLConnection.setInstanceFollowRedirects(false);
        r2_HttpURLConnection.setConnectTimeout(i * 4);
        r2_HttpURLConnection.setDoInput(true);
        r2_HttpURLConnection.setDoOutput(true);
        r2_HttpURLConnection.setUseCaches(false);
        r2_HttpURLConnection.setRequestMethod(UsersAPI.HTTPMETHOD_POST);
        r2_HttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        r2_HttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=*****");
        if (this.b != null) {
            Iterator r4_Iterator = this.b.keySet().iterator();
            while (r4_Iterator.hasNext()) {
                r0_String = (String) r4_Iterator.next();
                r2_HttpURLConnection.setRequestProperty(r0_String, (String) this.b.get(r0_String));
            }
        }
        r0_String = s();
        if (r0_String != null) {
            r2_HttpURLConnection.setRequestProperty("Cookie", r0_String);
        }
        if (this.e != null) {
            this.e.applyToken(this, r2_HttpURLConnection);
        }
        DataOutputStream r4_DataOutputStream = new DataOutputStream(r2_HttpURLConnection.getOutputStream());
        Iterator r5_Iterator = r11_Map_String__Object.entrySet().iterator();
        while (r5_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r5_Iterator.next();
            a(r4_DataOutputStream, (String) r0_Entry.getKey(), r0_Entry.getValue());
        }
        r4_DataOutputStream.writeBytes("--*****--\r\n");
        r4_DataOutputStream.flush();
        r4_DataOutputStream.close();
        r2_HttpURLConnection.connect();
        int r4i = r2_HttpURLConnection.getResponseCode();
        String r5_String = r2_HttpURLConnection.getResponseMessage();
        String r1_String = r2_HttpURLConnection.getContentEncoding();
        if (r4i < 200 || r4i >= 300) {
            r0_String = new String(a(r1_String, r2_HttpURLConnection.getErrorStream()), Base.UTF8);
            AQUtility.debug(QQDialogAuthorizeActivity.ERROR_RET, r0_String);
            r1_byteA = null;
        } else {
            r1_byteA = a(r1_String, r2_HttpURLConnection.getInputStream());
            r0_String = null;
        }
        AQUtility.debug("response", Integer.valueOf(r4i));
        if (r1_byteA != null) {
            AQUtility.debug(Integer.valueOf(r1_byteA.length), r10_String);
        }
        r12_AjaxStatus.code(r4i).message(r5_String).b(r10_String).a(new Date()).a(r1_byteA).a(r0_String).a(null);
    }

    public static void cancel() {
        if (P != null) {
            P.shutdownNow();
            P = null;
        }
        BitmapAjaxCallback.e();
    }

    protected static int d() {
        return T;
    }

    private String d(String r4_String) {
        if (r4_String == null) {
            return null;
        }
        int r1i = r4_String.indexOf("charset");
        if (r1i == -1) {
            return null;
        }
        int r0i = r4_String.indexOf(";", r1i);
        if (r0i == -1) {
            r0i = r4_String.length();
        }
        return r4_String.substring(r1i + 7, r0i).replaceAll("[^\\w-]", RContactStorage.PRIMARY_KEY);
    }

    private K e() {
        return this;
    }

    private String e(String r2_String) {
        if (this.u != null) {
            r2_String = this.u;
        }
        return this.e != null ? this.e.getNetworkUrl(r2_String) : r2_String;
    }

    public static void execute(Runnable r1_Runnable) {
        if (P == null) {
            P = Executors.newFixedThreadPool(k);
        }
        P.execute(r1_Runnable);
    }

    private static String f(String r3_String) {
        return r3_String.replaceAll(" ", "%20").replaceAll("\\|", "%7C");
    }

    private void f() {
        this.p = null;
        this.q = null;
        this.s = null;
        this.G = null;
        this.v = null;
        this.e = null;
        this.E = null;
    }

    private void g() {
        if (this.N) {
            synchronized (this) {
                try {
                    notifyAll();
                } catch (Exception e) {
                }
            }
        }
    }

    public static int getActiveCount() {
        return P instanceof ThreadPoolExecutor ? ((ThreadPoolExecutor) P).getActiveCount() : 0;
    }

    private boolean h() {
        if (this.E == null) {
            return true;
        }
        Activity r0_Activity = (Activity) this.E.get();
        return r0_Activity != null && !r0_Activity.isFinishing();
    }

    private void i() {
        if (this.z || (!this.g)) {
            if (this.d != null) {
                l();
            }
            if (this.d == null) {
                m();
            }
        } else {
            k();
            if (this.d != null) {
                if (this.d == null) {
                } else {
                    m();
                }
            } else {
                l();
                if (this.d == null) {
                    m();
                }
            }
        }
    }

    private String j() {
        return this.e != null ? this.e.getCacheUrl(this.t) : this.t;
    }

    private void k() {
        File r0_File = a(this.x, j());
        if (r0_File != null) {
            this.f.a((int)XListViewFooter.STATE_NOMORE);
            this.d = a(this.t, r0_File, this.f);
            if (this.d != null) {
                this.f.a(new Date(r0_File.lastModified())).done();
            }
        }
    }

    private void l() {
        this.d = a(this.t);
        if (this.d != null) {
            this.f.a((int)XListViewHeader.STATE_REFRESHING).done();
        }
    }

    private void m() {
        if (this.t == null) {
            this.f.code(AjaxStatus.NETWORK_ERROR).done();
        } else {
            byte[] r1_byteA = null;
            try {
                a(this.I + 1);
                if (this.e == null || (!this.e.expired(this, this.f)) || this.O) {
                    r1_byteA = this.f.e();
                    try {
                    } catch (Exception e) {
                        AQUtility.debug(e);
                    }
                } else {
                    AQUtility.debug("reauth needed", this.f.getMessage());
                    this.O = true;
                    if (this.e.reauth(this)) {
                        p();
                        r1_byteA = this.f.e();
                    } else {
                        this.f.b(true);
                        return;
                    }
                }
            } catch (IOException e_2) {
                AQUtility.debug((Object)"IOException");
                String r0_String = e_2.getMessage();
                if (r0_String == null || (!r0_String.contains("No authentication challenges found"))) {
                    this.f.code(AjaxStatus.NETWORK_ERROR).message("network error");
                } else {
                    this.f.code(401).message(r0_String);
                }
                this.d = transform(this.t, r1_byteA, this.f);
                if (this.d != null || r1_byteA == null) {
                    T = this.f.getCode();
                    this.f.done();
                } else {
                    this.f.code(AjaxStatus.TRANSFORM_ERROR).message("transform error");
                    T = this.f.getCode();
                    this.f.done();
                }
            } catch (Exception e_3) {
                AQUtility.debug(e_3);
                this.f.code(AjaxStatus.NETWORK_ERROR).message("network error");
            }
            this.d = transform(this.t, r1_byteA, this.f);
            if (this.d != null || r1_byteA == null) {
                T = this.f.getCode();
                this.f.done();
            } else {
                this.f.code(AjaxStatus.TRANSFORM_ERROR).message("transform error");
                T = this.f.getCode();
                this.f.done();
            }
        }
    }

    private File n() {
        File r0_File;
        if (c()) {
            if (this.y != null) {
                r0_File = this.y;
            } else if (this.g) {
                r0_File = b();
            } else {
                r0_File = AQUtility.getTempDir();
                if (r0_File == null) {
                    r0_File = this.x;
                }
                r0_File = AQUtility.getCacheFile(r0_File, this.t);
            }
        } else {
            r0_File = null;
        }
        if (r0_File == null || r0_File.exists()) {
            return r0_File;
        }
        try {
            r0_File.getParentFile().mkdirs();
            r0_File.createNewFile();
            return r0_File;
        } catch (Exception e) {
            AQUtility.report(e);
            return null;
        }
    }

    private void o() {
        if (this.d == null || (!this.g)) {
            if (this.f.getCode() == -103) {
                File r0_File = b();
                if (r0_File.exists()) {
                    r0_File.delete();
                    AQUtility.debug((Object)"invalidated cache due to transform error");
                }
            }
        } else {
            byte[] r0_byteA = this.f.e();
            if (r0_byteA != null) {
                try {
                    if (this.f.getSource() == 1) {
                        File r1_File = b();
                        if (this.f.d()) {
                            if (r1_File.exists()) {
                                r1_File.delete();
                            }
                        } else {
                            a(this.t, this.d, r1_File, r0_byteA);
                        }
                    }
                } catch (Exception e) {
                    AQUtility.debug(e);
                }
            }
            this.f.a(null);
        }
    }

    private void p() throws IOException {
        String r1_String = this.t;
        Map r0_Map = this.a;
        if (r0_Map != null || r1_String.length() <= 2000) {
            r1_String = e(r1_String);
            if (2 != this.F) {
                b(r1_String, this.f);
            } else if (3 != this.F) {
                b(r1_String, r0_Map, this.f);
            } else {
                if (1 == this.F && r0_Map == null) {
                    r0_Map = new HashMap();
                    if (r0_Map != null) {
                        if (a(r0_Map)) {
                            c(r1_String, r0_Map, this.f);
                            return;
                        } else {
                            a(r1_String, r0_Map, this.f);
                            return;
                        }
                    }
                } else if (r0_Map != null) {
                    a(r1_String, this.f);
                } else if (a(r0_Map)) {
                    c(r1_String, r0_Map, this.f);
                    return;
                } else {
                    a(r1_String, r0_Map, this.f);
                    return;
                }
                a(r1_String, this.f);
            }
        } else {
            Uri r0_Uri = Uri.parse(r1_String);
            r1_String = a(r0_Uri);
            r0_Map = b(r0_Uri);
            r1_String = e(r1_String);
            if (2 != this.F) {
                if (3 != this.F) {
                    if (1 == this.F || r0_Map == null) {
                        if (r0_Map != null) {
                            a(r1_String, this.f);
                        } else if (a(r0_Map)) {
                            c(r1_String, r0_Map, this.f);
                            return;
                        } else {
                            a(r1_String, r0_Map, this.f);
                            return;
                        }
                    } else {
                        r0_Map = new HashMap();
                        if (r0_Map != null) {
                            if (a(r0_Map)) {
                                a(r1_String, r0_Map, this.f);
                                return;
                            } else {
                                c(r1_String, r0_Map, this.f);
                                return;
                            }
                        }
                    }
                    a(r1_String, this.f);
                } else {
                    b(r1_String, r0_Map, this.f);
                }
            } else {
                b(r1_String, this.f);
            }
        }
    }

    private void q() {
        if (this.t == null || (!this.h)) {
            a();
            f();
        } else {
            a(this.t, this.d);
            a();
            f();
        }
    }

    private static DefaultHttpClient r() {
        if (R != null && m) {
            return R;
        }
        AQUtility.debug((Object)"creating http client");
        HttpParams r1_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(r1_HttpParams, i);
        HttpConnectionParams.setSoTimeout(r1_HttpParams, i);
        ConnManagerParams.setMaxConnectionsPerRoute(r1_HttpParams, new ConnPerRouteBean(25));
        HttpConnectionParams.setSocketBufferSize(r1_HttpParams, Utils.IO_BUFFER_SIZE);
        SchemeRegistry r2_SchemeRegistry = new SchemeRegistry();
        r2_SchemeRegistry.register(new Scheme(ImageFetcher.HTTP_CACHE_DIR, PlainSocketFactory.getSocketFactory(), 80));
        r2_SchemeRegistry.register(new Scheme("https", Q == null ? SSLSocketFactory.getSocketFactory() : Q, 443));
        R = new DefaultHttpClient(new ThreadSafeClientConnManager(r1_HttpParams, r2_SchemeRegistry), r1_HttpParams);
        return R;
    }

    private String s() {
        if (this.c == null || this.c.size() == 0) {
            return null;
        }
        Iterator r2_Iterator = this.c.keySet().iterator();
        StringBuilder r3_StringBuilder = new StringBuilder();
        while (r2_Iterator.hasNext()) {
            String r0_String = (String) r2_Iterator.next();
            r3_StringBuilder.append(r0_String);
            r3_StringBuilder.append("=");
            r3_StringBuilder.append((String) this.c.get(r0_String));
            if (r2_Iterator.hasNext()) {
                r3_StringBuilder.append("; ");
            }
        }
        return r3_StringBuilder.toString();
    }

    public static void setAgent(String r0_String) {
        j = r0_String;
    }

    public static void setGZip(boolean r0z) {
        l = r0z;
    }

    public static void setNetworkLimit(int r2i) {
        k = Math.max(1, Math.min(AdViewUtil.NETWORK_TYPE_WIYUN, r2i));
        P = null;
        AQUtility.debug("setting network limit", Integer.valueOf(k));
    }

    public static void setProxyHandle(ProxyHandle r0_ProxyHandle) {
        S = r0_ProxyHandle;
    }

    public static void setReuseHttpClient(boolean r1z) {
        m = r1z;
        R = null;
    }

    public static void setSSF(SocketFactory r1_SocketFactory) {
        Q = r1_SocketFactory;
        R = null;
    }

    public static void setSimulateError(boolean r0z) {
        n = r0z;
    }

    public static void setTimeout(int r0i) {
        i = r0i;
    }

    public static void setTransformer(Transformer r0_Transformer) {
        J = r0_Transformer;
    }

    protected File a(File r7_File, String r8_String) {
        if (this.C < 0) {
            return null;
        }
        File r1_File = AQUtility.getExistedCacheByUrl(r7_File, r8_String);
        return (r1_File == null || this.C == 0 || System.currentTimeMillis() - r1_File.lastModified() <= this.C) ? r1_File : null;
    }

    protected T a(String r2_String) {
        return null;
    }

    protected T a(String r3_String, File r4_File, AjaxStatus r5_AjaxStatus) {
        T r0_T = null;
        byte[] r1_byteA;
        try {
            byte[] r1_byteA_2;
            if (c()) {
                r5_AjaxStatus.a(r4_File);
                r1_byteA_2 = null;
            } else {
                r1_byteA_2 = AQUtility.toBytes(new FileInputStream(r4_File));
            }
            r0_T = transform(r3_String, r1_byteA_2, r5_AjaxStatus);
        } catch (Exception e) {
            AQUtility.debug(e);
        }
        return r0_T;
    }

    void a() {
        int r3i = XListViewFooter.STATE_NOMORE;
        a(false);
        this.M = true;
        if (h()) {
            if (this.r != null) {
                Object r0_Object = getHandler();
                Class[] r4_ClassA = new Class[r3i];
                r4_ClassA[0] = String.class;
                r4_ClassA[1] = this.o;
                r4_ClassA[2] = AjaxStatus.class;
                String r1_String = this.r;
                Class[] r5_ClassA = L;
                Object[] r6_ObjectA = new Object[r3i];
                r6_ObjectA[0] = this.t;
                r6_ObjectA[1] = this.d;
                r6_ObjectA[2] = this.f;
                AQUtility.invokeHandler(r0_Object, r1_String, true, true, r4_ClassA, r5_ClassA, r6_ObjectA);
            } else {
                try {
                    callback(this.t, this.d, this.f);
                } catch (Exception e) {
                    AQUtility.report(e);
                }
            }
        } else {
            a(this.t, this.d, this.f);
        }
        o();
        if (!this.N) {
            this.f.close();
        }
        g();
        AQUtility.debugNotify();
    }

    protected void a(String r1_String, T r2_T) {
    }

    protected void a(String r1_String, T r2_T, AjaxStatus r3_AjaxStatus) {
    }

    protected void a(String r3_String, T r4_T, File r5_File, byte[] r6_byteA) {
        if (r5_File == null || r6_byteA == null) {
        } else {
            AQUtility.storeAsync(r5_File, r6_byteA, 0);
        }
    }

    protected void a(boolean r3z) {
        Object r0_Object;
        r0_Object = this.s == null ? null : this.s.get();
        if (r0_Object != null) {
            if (AQUtility.isUIThread()) {
                Common.showProgress(r0_Object, this.t, r3z);
            } else {
                AQUtility.post(new a(this, r0_Object, r3z));
            }
        }
    }

    protected boolean a(Context r3_Context) {
        return this.g && AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(r3_Context, this.w), this.t) != null;
    }

    public void abort() {
        this.U = true;
        if (this.G == null || this.G.isAborted()) {
        } else {
            this.G.abort();
        }
    }

    public void async(Activity r3_Activity) {
        if (r3_Activity.isFinishing()) {
            AQUtility.warn("Warning", "Possible memory leak. Calling ajax with a terminated activity.");
        }
        if (this.o == null) {
            AQUtility.warn("Warning", "type() is not called with response type.");
        } else {
            this.E = new WeakReference(r3_Activity);
            async((Context)r3_Activity);
        }
    }

    public void async(Context r3_Context) {
        if (this.f == null) {
            this.f = new AjaxStatus();
            this.f.b(this.t).a(this.z);
        } else if (this.f.b()) {
            this.f.a();
            this.d = null;
        }
        a(true);
        if (this.e == null || this.e.authenticated()) {
            b(r3_Context);
        } else {
            AQUtility.debug("auth needed", this.t);
            this.e.auth(this);
        }
    }

    public K auth(Activity r3_Activity, String r4_String, String r5_String) {
        if (VERSION.SDK_INT < 5 || (!r4_String.startsWith("g."))) {
            return e();
        }
        this.e = new GoogleHandle(r3_Activity, r4_String, r5_String);
        return e();
    }

    public K auth(AccountHandle r2_AccountHandle) {
        this.e = r2_AccountHandle;
        return e();
    }

    protected File b() {
        return AQUtility.getCacheFile(this.x, j());
    }

    protected T b(String r2_String) {
        return null;
    }

    public void block() {
        if (AQUtility.isUIThread()) {
            throw new IllegalStateException("Cannot block UI thread.");
        } else if (this.M) {
        } else {
            try {
                synchronized (this) {
                    this.N = true;
                    wait((long) (i + 5000));
                }
            } catch (Exception e) {
            }
        }
    }

    protected boolean c() {
        return File.class.equals(this.o) || XmlPullParser.class.equals(this.o) || InputStream.class.equals(this.o) || XmlDom.class.equals(this.o);
    }

    public void callback(String r1_String, T r2_T, AjaxStatus r3_AjaxStatus) {
    }

    public K cookie(String r2_String, String r3_String) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        this.c.put(r2_String, r3_String);
        return e();
    }

    public K cookies(Map<String, String> r2_Map_String__String) {
        this.c = r2_Map_String__String;
        return e();
    }

    public K encoding(String r2_String) {
        this.D = r2_String;
        return e();
    }

    public K expire(long r2j) {
        this.C = r2j;
        return e();
    }

    public void failure(int r2i, String r3_String) {
        if (this.f != null) {
            this.f.code(r2i).message(r3_String).done();
            if (this.H) {
                AQUtility.post(this);
            } else {
                q();
            }
        }
    }

    public K fileCache(boolean r2z) {
        this.g = r2z;
        return e();
    }

    public String getCallback() {
        return this.r;
    }

    public String getEncoding() {
        return this.D;
    }

    public Object getHandler() {
        if (this.q != null) {
            return this.q;
        }
        if (this.p == null) {
            return null;
        }
        return this.p.get();
    }

    public T getResult() {
        return this.d;
    }

    public AjaxStatus getStatus() {
        return this.f;
    }

    public Class<T> getType() {
        return this.o;
    }

    public String getUrl() {
        return this.t;
    }

    public K handler(Object r2_Object, String r3_String) {
        this.q = r2_Object;
        this.r = r3_String;
        this.p = null;
        return e();
    }

    public K header(String r2_String, String r3_String) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        this.b.put(r2_String, r3_String);
        return e();
    }

    public K headers(Map<String, String> r2_Map_String__String) {
        this.b = r2_Map_String__String;
        return e();
    }

    public K memCache(boolean r2z) {
        this.h = r2z;
        return e();
    }

    public K method(int r2i) {
        this.F = r2i;
        return e();
    }

    public K networkUrl(String r2_String) {
        this.u = r2_String;
        return e();
    }

    public K param(String r2_String, Object r3_Object) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(r2_String, r3_Object);
        return e();
    }

    public K params(Map<String, ?> r2_Map_String__) {
        this.a = r2_Map_String__;
        return e();
    }

    public K policy(int r2i) {
        this.w = r2i;
        return e();
    }

    public K progress(Dialog r2_Dialog) {
        return progress((Object)r2_Dialog);
    }

    public K progress(View r2_View) {
        return progress((Object)r2_View);
    }

    public K progress(Object r2_Object) {
        if (r2_Object != null) {
            this.s = new WeakReference(r2_Object);
        }
        return e();
    }

    public K proxy(String r2_String, int r3i) {
        this.K = new HttpHost(r2_String, r3i);
        return e();
    }

    public K proxy(String r3_String, int r4i, String r5_String, String r6_String) {
        proxy(r3_String, r4i);
        String r0_String = a(r5_String, r6_String);
        AQUtility.debug("proxy auth", r0_String);
        return header("Proxy-Authorization", r0_String);
    }

    public K redirect(boolean r2z) {
        this.B = r2z;
        return e();
    }

    public K refresh(boolean r2z) {
        this.z = r2z;
        return e();
    }

    public K retry(int r2i) {
        this.I = r2i;
        return e();
    }

    public void run() {
        if (this.f.b()) {
            q();
        } else {
            try {
                i();
            } catch (Throwable th) {
                AQUtility.debug(th);
                this.f.code(AjaxStatus.NETWORK_ERROR).done();
            }
            if (!this.f.c()) {
                if (this.H) {
                    AQUtility.post(this);
                } else {
                    q();
                }
            }
        }
    }

    public K targetFile(File r2_File) {
        this.y = r2_File;
        return e();
    }

    public K timeout(int r2i) {
        this.A = r2i;
        return e();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected T transform(String r7_String, byte[] r8_byteA, AjaxStatus r9_AjaxStatus) {
        /*
        r6_this = this;
        r1 = 0;
        r0 = r6.o;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r9.f();
        if (r8 == 0) goto L_0x00ce;
    L_0x000c:
        r0 = r6.o;
        r2 = android.graphics.Bitmap.class;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x001d;
    L_0x0016:
        r0 = 0;
        r1 = r8.length;
        r1 = android.graphics.BitmapFactory.decodeByteArray(r8, r0, r1);
        goto L_0x0005;
    L_0x001d:
        r0 = r6.o;
        r2 = org.json.JSONObject.class;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0045;
    L_0x0027:
        r2 = new java.lang.String;	 //Catch:{ Exception -> 0x003b }
        r0 = r6.D;	 //Catch:{ Exception -> 0x003b }
        r2.<init>(r8, r0);	 //Catch:{ Exception -> 0x003b }
        r0 = new org.json.JSONTokener;	 //Catch:{ Exception -> 0x013c }
        r0.<init>(r2);	 //Catch:{ Exception -> 0x013c }
        r0 = r0.nextValue();	 //Catch:{ Exception -> 0x013c }
        r0 = (org.json.JSONObject) r0;	 //Catch:{ Exception -> 0x013c }
    L_0x0039:
        r1 = r0;
        goto L_0x0005;
    L_0x003b:
        r0 = move-exception;
        r2 = r1;
    L_0x003d:
        com.androidquery.util.AQUtility.debug(r0);
        com.androidquery.util.AQUtility.debug(r2);
        r0 = r1;
        goto L_0x0039;
    L_0x0045:
        r0 = r6.o;
        r2 = org.json.JSONArray.class;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0069;
    L_0x004f:
        r0 = new java.lang.String;	 //Catch:{ Exception -> 0x0063 }
        r2 = r6.D;	 //Catch:{ Exception -> 0x0063 }
        r0.<init>(r8, r2);	 //Catch:{ Exception -> 0x0063 }
        r2 = new org.json.JSONTokener;	 //Catch:{ Exception -> 0x0063 }
        r2.<init>(r0);	 //Catch:{ Exception -> 0x0063 }
        r0 = r2.nextValue();	 //Catch:{ Exception -> 0x0063 }
        r0 = (org.json.JSONArray) r0;	 //Catch:{ Exception -> 0x0063 }
    L_0x0061:
        r1 = r0;
        goto L_0x0005;
    L_0x0063:
        r0 = move-exception;
        com.androidquery.util.AQUtility.debug(r0);
        r0 = r1;
        goto L_0x0061;
    L_0x0069:
        r0 = r6.o;
        r2 = java.lang.String.class;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x009b;
    L_0x0073:
        r0 = r9.getSource();
        r2 = 1;
        if (r0 != r2) goto L_0x0086;
    L_0x007a:
        r0 = "network";
        com.androidquery.util.AQUtility.debug(r0);
        r0 = r6.D;
        r1 = r6.a(r8, r0, r9);
        goto L_0x0005;
    L_0x0086:
        r0 = "file";
        com.androidquery.util.AQUtility.debug(r0);
        r0 = new java.lang.String;	 //Catch:{ Exception -> 0x0095 }
        r2 = r6.D;	 //Catch:{ Exception -> 0x0095 }
        r0.<init>(r8, r2);	 //Catch:{ Exception -> 0x0095 }
        r1 = r0;
        goto L_0x0005;
    L_0x0095:
        r0 = move-exception;
        com.androidquery.util.AQUtility.debug(r0);
        goto L_0x0005;
    L_0x009b:
        r0 = r6.o;
        r2 = byte[].class;
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x00a8;
    L_0x00a5:
        r1 = r8;
        goto L_0x0005;
    L_0x00a8:
        r0 = r6.v;
        if (r0 == 0) goto L_0x00bb;
    L_0x00ac:
        r0 = r6.v;
        r2 = r6.o;
        r3 = r6.D;
        r1 = r7;
        r4 = r8;
        r5 = r9;
        r1 = r0.transform(r1, r2, r3, r4, r5);
        goto L_0x0005;
    L_0x00bb:
        r0 = J;
        if (r0 == 0) goto L_0x0005;
    L_0x00bf:
        r0 = J;
        r2 = r6.o;
        r3 = r6.D;
        r1 = r7;
        r4 = r8;
        r5 = r9;
        r1 = r0.transform(r1, r2, r3, r4, r5);
        goto L_0x0005;
    L_0x00ce:
        if (r0 == 0) goto L_0x0005;
    L_0x00d0:
        r2 = r6.o;
        r3 = java.io.File.class;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00dd;
    L_0x00da:
        r1 = r0;
        goto L_0x0005;
    L_0x00dd:
        r2 = r6.o;
        r3 = com.androidquery.util.XmlDom.class;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00fd;
    L_0x00e7:
        r2 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x00f7 }
        r2.<init>(r0);	 //Catch:{ Exception -> 0x00f7 }
        r0 = new com.androidquery.util.XmlDom;	 //Catch:{ Exception -> 0x00f7 }
        r0.<init>(r2);	 //Catch:{ Exception -> 0x00f7 }
        r9.a(r2);	 //Catch:{ Exception -> 0x00f7 }
        r1 = r0;
        goto L_0x0005;
    L_0x00f7:
        r0 = move-exception;
        com.androidquery.util.AQUtility.report(r0);
        goto L_0x0005;
    L_0x00fd:
        r2 = r6.o;
        r3 = org.xmlpull.v1.XmlPullParser.class;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0121;
    L_0x0107:
        r8 = android.util.Xml.newPullParser();
        r2 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x011b }
        r2.<init>(r0);	 //Catch:{ Exception -> 0x011b }
        r0 = r6.D;	 //Catch:{ Exception -> 0x011b }
        r8.setInput(r2, r0);	 //Catch:{ Exception -> 0x011b }
        r9.a(r2);	 //Catch:{ Exception -> 0x011b }
        r1 = r8;
        goto L_0x0005;
    L_0x011b:
        r0 = move-exception;
        com.androidquery.util.AQUtility.report(r0);
        goto L_0x0005;
    L_0x0121:
        r2 = r6.o;
        r3 = java.io.InputStream.class;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x012b:
        r8 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0136 }
        r8.<init>(r0);	 //Catch:{ Exception -> 0x0136 }
        r9.a(r8);	 //Catch:{ Exception -> 0x0136 }
        r1 = r8;
        goto L_0x0005;
    L_0x0136:
        r0 = move-exception;
        com.androidquery.util.AQUtility.report(r0);
        goto L_0x0005;
    L_0x013c:
        r0 = move-exception;
        goto L_0x003d;
        */

    }

    public K transformer(Transformer r2_Transformer) {
        this.v = r2_Transformer;
        return e();
    }

    public K type(Class<T> r2_Class_T) {
        this.o = r2_Class_T;
        return e();
    }

    public K uiCallback(boolean r2z) {
        this.H = r2z;
        return e();
    }

    public K url(String r2_String) {
        this.t = r2_String;
        return e();
    }

    public K weakHandler(Object r2_Object, String r3_String) {
        this.p = new WeakReference(r2_Object);
        this.r = r3_String;
        this.q = null;
        return e();
    }
}