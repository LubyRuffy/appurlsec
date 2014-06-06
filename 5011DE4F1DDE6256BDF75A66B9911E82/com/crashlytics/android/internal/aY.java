package com.crashlytics.android.internal;

import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.bean.Base;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;

// compiled from: SourceFile
public final class ay {
    private static aB a;
    private HttpURLConnection b;
    private URL c;
    private final String d;
    private aF e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;

    static {
        a = aB.a;
    }

    private ay(CharSequence r3_CharSequence, String r4_String) throws aD {
        this.b = null;
        this.g = true;
        this.h = false;
        this.i = 8192;
        try {
            this.c = new URL(r3_CharSequence.toString());
            this.d = r4_String;
        } catch (MalformedURLException e) {
            throw new aD(e);
        }
    }

    private ay a(InputStream r7_InputStream, OutputStream r8_OutputStream) throws IOException {
        return (ay) new bz(this, r7_InputStream, this.g, r7_InputStream, r8_OutputStream).call();
    }

    public static ay a(CharSequence r2_CharSequence) throws aD {
        return new ay(r2_CharSequence, "PUT");
    }

    public static ay a(CharSequence r3_CharSequence, Map<?, ?> r4_Map___, boolean r5z) {
        return new ay(c(a(r3_CharSequence, (Map)r4_Map___)), HttpManager.HTTPMETHOD_GET);
    }

    private ay a(String r3_String, String r4_String, String r5_String) throws IOException {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("form-data; name=\"").append(r3_String);
        if (r4_String != null) {
            r0_StringBuilder.append("\"; filename=\"").append(r4_String);
        }
        r0_StringBuilder.append('\"');
        d("Content-Disposition", r0_StringBuilder.toString());
        if (r5_String != null) {
            d("Content-Type", r5_String);
        }
        return d((CharSequence)"\r\n");
    }

    private ay a(String r3_String, String r4_String, String r5_String, String r6_String) throws aD {
        try {
            j();
            a(r3_String, r4_String, null);
            this.e.a(r6_String);
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private static String a(CharSequence r8_CharSequence, Map<?, ?> r9_Map___) {
        String r0_String = r8_CharSequence.toString();
        if (r9_Map___ == null || r9_Map___.isEmpty()) {
            return r0_String;
        }
        Iterator r2_Iterator;
        Entry r0_Entry;
        Object r0_Object;
        StringBuilder r1_StringBuilder = new StringBuilder(r0_String);
        if (r0_String.indexOf(AdViewUtil.NETWORK_TYPE_ZHIDIAN) + 2 == r0_String.lastIndexOf(AdViewUtil.NETWORK_TYPE_SUIZONG)) {
            r1_StringBuilder.append('/');
        }
        int r2i = r0_String.indexOf(BDLocation.TypeNetWorkException);
        int r3i = r1_StringBuilder.length() - 1;
        if (r2i == -1) {
            r1_StringBuilder.append('?');
        } else {
            if (r2i >= r3i || r0_String.charAt(r3i) == '&') {
                r2_Iterator = r9_Map___.entrySet().iterator();
                r0_Entry = (Entry) r2_Iterator.next();
                r1_StringBuilder.append(r0_Entry.getKey().toString());
                r1_StringBuilder.append('=');
                r0_Object = r0_Entry.getValue();
            } else {
                r1_StringBuilder.append('&');
            }
            if (r0_Object == null) {
                r1_StringBuilder.append(r0_Object);
            }
            while (r2_Iterator.hasNext()) {
                r1_StringBuilder.append('&');
                r0_Entry = (Entry) r2_Iterator.next();
                r1_StringBuilder.append(r0_Entry.getKey().toString());
                r1_StringBuilder.append('=');
                r0_Object = r0_Entry.getValue();
                if (r0_Object != null) {
                    r1_StringBuilder.append(r0_Object);
                }
            }
            return r1_StringBuilder.toString();
        }
        r2_Iterator = r9_Map___.entrySet().iterator();
        r0_Entry = (Entry) r2_Iterator.next();
        r1_StringBuilder.append(r0_Entry.getKey().toString());
        r1_StringBuilder.append('=');
        r0_Object = r0_Entry.getValue();
        if (r0_Object == null) {
            while (r2_Iterator.hasNext()) {
                r1_StringBuilder.append('&');
                r0_Entry = (Entry) r2_Iterator.next();
                r1_StringBuilder.append(r0_Entry.getKey().toString());
                r1_StringBuilder.append('=');
                r0_Object = r0_Entry.getValue();
                if (r0_Object != null) {
                } else {
                    r1_StringBuilder.append(r0_Object);
                }
            }
            return r1_StringBuilder.toString();
        } else {
            r1_StringBuilder.append(r0_Object);
            while (r2_Iterator.hasNext()) {
                r1_StringBuilder.append('&');
                r0_Entry = (Entry) r2_Iterator.next();
                r1_StringBuilder.append(r0_Entry.getKey().toString());
                r1_StringBuilder.append('=');
                r0_Object = r0_Entry.getValue();
                if (r0_Object != null) {
                    r1_StringBuilder.append(r0_Object);
                }
            }
            return r1_StringBuilder.toString();
        }
    }

    public static ay b(CharSequence r2_CharSequence) throws aD {
        return new ay(r2_CharSequence, "DELETE");
    }

    public static ay b(CharSequence r3_CharSequence, Map<?, ?> r4_Map___, boolean r5z) {
        return new ay(c(a(r3_CharSequence, (Map)r4_Map___)), UsersAPI.HTTPMETHOD_POST);
    }

    private ay b(String r2_String, String r3_String, String r4_String) throws aD {
        return a(r2_String, r3_String, null, r4_String);
    }

    private static String c(CharSequence r6_CharSequence) throws aD {
        try {
            URL r4_URL = new URL(r6_CharSequence.toString());
            String r2_String = r4_URL.getHost();
            int r0i = r4_URL.getPort();
            if (r0i != -1) {
                r2_String = r2_String + ':' + Integer.toString(r0i);
            }
            String r0_String = new URI(r4_URL.getProtocol(), r2_String, r4_URL.getPath(), r4_URL.getQuery(), null).toASCIIString();
            int r1i = r0_String.indexOf(BDLocation.TypeNetWorkException);
            return (r1i <= 0 || r1i + 1 >= r0_String.length()) ? r0_String : r0_String.substring(0, r1i + 1) + r0_String.substring(r1i + 1).replace("+", "%2B");
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private static String c(String r1_String) {
        return (r1_String == null || r1_String.length() <= 0) ? Base.UTF8 : r1_String;
    }

    private static String c(String r9_String, String r10_String) {
        if (r9_String == null || r9_String.length() == 0) {
            return null;
        }
        int r1i = r9_String.length();
        int r3i = r9_String.indexOf(59) + 1;
        if (r3i == 0 || r3i == r1i) {
            return null;
        }
        int r0i = r9_String.indexOf(59, r3i);
        if (r0i == -1) {
            r0i = r3i;
            r3i = r1i;
        } else {
            r0i = r3i;
            r3i = r0i;
        }
        while (r0i < r3i) {
            int r4i = r9_String.indexOf(BDLocation.TypeGpsLocation, r0i);
            if (r4i == -1 || r4i >= r3i || (!r10_String.equals(r9_String.substring(r0i, r4i).trim()))) {
                r3i++;
                r0i = r9_String.indexOf(59, r3i);
                if (r0i != -1) {
                    r0i = r1i;
                }
                r0i = r3i;
                r3i = r0i;
            } else {
                String r0_String = r9_String.substring(r4i + 1, r3i).trim();
                r4i = r0_String.length();
                if (r4i != 0) {
                    return (r4i > 2 && '\"' == r0_String.charAt(0) && '\"' == r0_String.charAt(r4i - 1)) ? r0_String.substring(1, r4i - 1) : r0_String;
                }
                r3i++;
                r0i = r9_String.indexOf(59, r3i);
                if (r0i != -1) {
                    r0i = r3i;
                    r3i = r0i;
                } else {
                    r0i = r1i;
                    r0i = r3i;
                    r3i = r0i;
                }
            }
        }
        return null;
    }

    private ay d(CharSequence r3_CharSequence) throws aD {
        try {
            i();
            this.e.a(r3_CharSequence.toString());
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private ay d(String r3_String, String r4_String) throws aD {
        return d((CharSequence)r3_String).d((CharSequence)": ").d((CharSequence)r4_String).d((CharSequence)"\r\n");
    }

    private String d(String r5_String) throws aD {
        OutputStream r0_OutputStream;
        h();
        int r1i = a().getHeaderFieldInt("Content-Length", -1);
        r0_OutputStream = r1i > 0 ? new ByteArrayOutputStream(r1i) : new ByteArrayOutputStream();
        try {
            a(new BufferedInputStream(f(), this.i), r0_OutputStream);
            return r0_OutputStream.toString(c(r5_String));
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private HttpURLConnection e() {
        try {
            HttpURLConnection r0_HttpURLConnection = a.a(this.c);
            r0_HttpURLConnection.setRequestMethod(this.d);
            return r0_HttpURLConnection;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private InputStream f() throws aD {
        if (b() < 400) {
            try {
                return a().getInputStream();
            } catch (IOException e) {
                throw new aD(e);
            }
        } else {
            InputStream r0_InputStream = a().getErrorStream();
            if (r0_InputStream != null) {
                return r0_InputStream;
            }
            try {
                return a().getInputStream();
            } catch (IOException e_2) {
                throw new aD(e_2);
            }
        }
    }

    private ay g() throws IOException {
        if (this.e == null) {
            return this;
        }
        if (this.f) {
            this.e.a("\r\n--00content0boundary00--\r\n");
        }
        if (this.g) {
            try {
                this.e.close();
            } catch (IOException e) {
            }
        } else {
            this.e.close();
        }
        this.e = null;
        return this;
    }

    private ay h() throws aD {
        try {
            return g();
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private ay i() throws IOException {
        if (this.e != null) {
            return this;
        }
        a().setDoOutput(true);
        this.e = new aF(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.i);
        return this;
    }

    private ay j() throws IOException {
        if (this.f) {
            this.e.a("\r\n--00content0boundary00\r\n");
        } else {
            this.f = true;
            a("Content-Type", "multipart/form-data; boundary=00content0boundary00").i();
            this.e.a("--00content0boundary00\r\n");
        }
        return this;
    }

    public final ay a(int r3i) {
        a().setConnectTimeout(10000);
        return this;
    }

    public final ay a(String r3_String, Number r4_Number) throws aD {
        return b(r3_String, null, r4_Number != null ? r4_Number.toString() : null);
    }

    public final ay a(String r2_String, String r3_String) {
        a().setRequestProperty(r2_String, r3_String);
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final ay a(String r4_String, String r5_String, String r6_String, File r7_File) throws aD {
        /*
        r3_this = this;
        r2 = 0;
        r1 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x0013, all -> 0x0026 }
        r0 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x0013, all -> 0x0026 }
        r0.<init>(r7);	 //Catch:{ IOException -> 0x0013, all -> 0x0026 }
        r1.<init>(r0);	 //Catch:{ IOException -> 0x0013, all -> 0x0026 }
        r0 = r3.a(r4, r5, r6, r1);	 //Catch:{ IOException -> 0x0029 }
        r1.close();	 //Catch:{ IOException -> 0x0022 }
    L_0x0012:
        return r0;
    L_0x0013:
        r0 = move-exception;
        r1 = r2;
    L_0x0015:
        r2 = new com.crashlytics.android.internal.aD;	 //Catch:{ all -> 0x001b }
        r2.<init>(r0);	 //Catch:{ all -> 0x001b }
        throw r2;	 //Catch:{ all -> 0x001b }
    L_0x001b:
        r0 = move-exception;
    L_0x001c:
        if (r1 == 0) goto L_0x0021;
    L_0x001e:
        r1.close();	 //Catch:{ IOException -> 0x0024 }
    L_0x0021:
        throw r0;
    L_0x0022:
        r1 = move-exception;
        goto L_0x0012;
    L_0x0024:
        r1 = move-exception;
        goto L_0x0021;
    L_0x0026:
        r0 = move-exception;
        r1 = r2;
        goto L_0x001c;
    L_0x0029:
        r0 = move-exception;
        goto L_0x0015;
        */

    }

    public final ay a(String r3_String, String r4_String, String r5_String, InputStream r6_InputStream) throws aD {
        try {
            j();
            a(r3_String, r4_String, r5_String);
            a(r6_InputStream, this.e);
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final ay a(Entry<String, String> r3_Entry_String__String) {
        return a((String) r3_Entry_String__String.getKey(), (String) r3_Entry_String__String.getValue());
    }

    public final ay a(boolean r3z) {
        a().setUseCaches(false);
        return this;
    }

    public final String a(String r2_String) throws aD {
        h();
        return a().getHeaderField(r2_String);
    }

    public final HttpURLConnection a() {
        if (this.b == null) {
            this.b = e();
        }
        return this.b;
    }

    public final int b() throws aD {
        try {
            g();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final ay b(String r2_String, String r3_String) {
        return b(r2_String, null, r3_String);
    }

    public final String c() throws aD {
        return d(c(a("Content-Type"), "charset"));
    }

    public final String d() {
        return a().getRequestMethod();
    }

    public final String toString() {
        return a().getRequestMethod() + ' ' + a().getURL();
    }
}