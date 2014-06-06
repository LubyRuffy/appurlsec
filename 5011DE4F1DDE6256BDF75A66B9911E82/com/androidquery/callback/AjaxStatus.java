package com.androidquery.callback;

import com.androidquery.util.AQUtility;
import java.io.Closeable;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

public class AjaxStatus {
    public static final int AUTH_ERROR = -102;
    public static final int DATASTORE = 2;
    public static final int DEVICE = 5;
    public static final int FILE = 3;
    public static final int MEMORY = 4;
    public static final int NETWORK = 1;
    public static final int NETWORK_ERROR = -101;
    public static final int TRANSFORM_ERROR = -103;
    private int a;
    private String b;
    private String c;
    private byte[] d;
    private File e;
    private Date f;
    private boolean g;
    private DefaultHttpClient h;
    private long i;
    private int j;
    private long k;
    private boolean l;
    private boolean m;
    private boolean n;
    private String o;
    private HttpContext p;
    private Header[] q;
    private Closeable r;

    public AjaxStatus() {
        this.a = 200;
        this.b = "OK";
        this.f = new Date();
        this.j = 1;
        this.k = System.currentTimeMillis();
    }

    public AjaxStatus(int r3i, String r4_String) {
        this.a = 200;
        this.b = "OK";
        this.f = new Date();
        this.j = 1;
        this.k = System.currentTimeMillis();
        this.a = r3i;
        this.b = r4_String;
    }

    protected AjaxStatus a() {
        this.i = System.currentTimeMillis() - this.k;
        this.l = false;
        close();
        return this;
    }

    protected AjaxStatus a(int r1i) {
        this.j = r1i;
        return this;
    }

    protected AjaxStatus a(File r1_File) {
        this.e = r1_File;
        return this;
    }

    protected AjaxStatus a(String r1_String) {
        this.o = r1_String;
        return this;
    }

    protected AjaxStatus a(Date r1_Date) {
        this.f = r1_Date;
        return this;
    }

    protected AjaxStatus a(DefaultHttpClient r1_DefaultHttpClient) {
        this.h = r1_DefaultHttpClient;
        return this;
    }

    protected AjaxStatus a(HttpContext r1_HttpContext) {
        this.p = r1_HttpContext;
        return this;
    }

    protected AjaxStatus a(boolean r1z) {
        this.g = r1z;
        return this;
    }

    protected AjaxStatus a(byte[] r1_byteA) {
        this.d = r1_byteA;
        return this;
    }

    protected AjaxStatus a(Header[] r1_HeaderA) {
        this.q = r1_HeaderA;
        return this;
    }

    protected void a(Closeable r1_Closeable) {
        this.r = r1_Closeable;
    }

    protected AjaxStatus b(String r1_String) {
        this.c = r1_String;
        return this;
    }

    protected AjaxStatus b(boolean r1z) {
        this.n = r1z;
        return this;
    }

    protected boolean b() {
        return this.l;
    }

    protected boolean c() {
        return this.n;
    }

    public void close() {
        AQUtility.close(this.r);
        this.r = null;
    }

    public AjaxStatus code(int r1i) {
        this.a = r1i;
        return this;
    }

    protected boolean d() {
        return this.m;
    }

    public AjaxStatus done() {
        this.i = System.currentTimeMillis() - this.k;
        this.l = true;
        this.n = false;
        return this;
    }

    protected byte[] e() {
        return this.d;
    }

    public boolean expired(long r6j) {
        long r1j = this.f.getTime();
        return ((System.currentTimeMillis() - r1j) > r6j ? 1 : ((System.currentTimeMillis() - r1j) == r6j? 0 : -1)) > 0 && getSource() != 1;
    }

    protected File f() {
        return this.e;
    }

    public DefaultHttpClient getClient() {
        return this.h;
    }

    public int getCode() {
        return this.a;
    }

    public List<Cookie> getCookies() {
        if (this.p == null) {
            return Collections.emptyList();
        }
        CookieStore r0_CookieStore = (CookieStore) this.p.getAttribute("http.cookie-store");
        return r0_CookieStore == null ? Collections.emptyList() : r0_CookieStore.getCookies();
    }

    public long getDuration() {
        return this.i;
    }

    public String getError() {
        return this.o;
    }

    public String getHeader(String r4_String) {
        if (this.q == null) {
            return null;
        }
        int r0i = 0;
        while (r0i < this.q.length) {
            if (r4_String.equalsIgnoreCase(this.q[r0i].getName())) {
                return this.q[r0i].getValue();
            }
            r0i++;
        }
        return null;
    }

    public List<Header> getHeaders() {
        return this.q == null ? Collections.emptyList() : Arrays.asList(this.q);
    }

    public String getMessage() {
        return this.b;
    }

    public String getRedirect() {
        return this.c;
    }

    public boolean getRefresh() {
        return this.g;
    }

    public int getSource() {
        return this.j;
    }

    public Date getTime() {
        return this.f;
    }

    public AjaxStatus invalidate() {
        this.m = true;
        return this;
    }

    public AjaxStatus message(String r1_String) {
        this.b = r1_String;
        return this;
    }
}