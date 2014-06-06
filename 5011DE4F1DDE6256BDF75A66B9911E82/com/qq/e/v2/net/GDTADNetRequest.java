package com.qq.e.v2.net;

import com.qq.e.v2.util.StringUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;

public abstract class GDTADNetRequest {
    private int a;
    private int b;
    private String c;
    private Map<String, String> d;
    private Map<String, String> e;
    private Map<String, String> f;
    private Map<String, String> g;
    private Method h;
    private byte[] i;

    public static enum Method {
        GET,
        POST;


        static {
            GET = new com.qq.e.v2.net.GDTADNetRequest.Method(HttpManager.HTTPMETHOD_GET, 0);
            POST = new com.qq.e.v2.net.GDTADNetRequest.Method(UsersAPI.HTTPMETHOD_POST, 1);
            com.qq.e.v2.net.GDTADNetRequest.Method[] r0_com_qq_e_v2_net_GDTADNetRequest_MethodA = new com.qq.e.v2.net.GDTADNetRequest.Method[2];
            r0_com_qq_e_v2_net_GDTADNetRequest_MethodA[0] = GET;
            r0_com_qq_e_v2_net_GDTADNetRequest_MethodA[1] = POST;
            a = r0_com_qq_e_v2_net_GDTADNetRequest_MethodA;
        }
    }

    public GDTADNetRequest(String r2_String, Method r3_Method, byte[] r4_byteA) {
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = Collections.unmodifiableMap(this.d);
        this.g = Collections.unmodifiableMap(this.e);
        this.c = r2_String;
        this.h = r3_Method;
        if (r4_byteA == null) {
            this.i = null;
        } else {
            this.i = (byte[]) r4_byteA.clone();
        }
    }

    public void addHeader(String r2_String, String r3_String) {
        if (StringUtil.isEmpty(r2_String) || StringUtil.isEmpty(r3_String)) {
        } else {
            this.d.put(r2_String, r3_String);
        }
    }

    public void addParam(String r2_String, String r3_String) {
        this.e.put(r2_String, r3_String);
    }

    public int getConnectionTimeOut() {
        return this.a;
    }

    public Map<String, String> getHeaders() {
        return this.f;
    }

    public Method getMethod() {
        return this.h;
    }

    public Map<String, String> getParas() {
        return this.g;
    }

    public byte[] getPostData() {
        return this.i;
    }

    public int getSocketTimeOut() {
        return this.b;
    }

    public String getUrl() {
        return this.c;
    }

    public String getUrlWithParas() {
        if (getParas().isEmpty()) {
            return getUrl();
        }
        StringBuilder r2_StringBuilder = new StringBuilder();
        Iterator r3_Iterator = getParas().entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r2_StringBuilder.append((String) r0_Entry.getKey()).append("=").append((String) r0_Entry.getValue()).append("&");
        }
        String r0_String = getUrl();
        if (r0_String == null) {
            return r2_StringBuilder.substring(0, r2_StringBuilder.length() - 1);
        }
        if (r0_String.contains("?")) {
            return r0_String + "&" + r2_StringBuilder.substring(0, r2_StringBuilder.length() - 1);
        }
        return r0_String + "?" + r2_StringBuilder.substring(0, r2_StringBuilder.length() - 1);
    }

    public abstract void onError(Exception r1_Exception);

    public abstract void onResponse(HttpResponse r1_HttpResponse);

    public void setConnectionTimeOut(int r1i) {
        this.a = r1i;
    }

    public void setSocketTimeOut(int r1i) {
        this.b = r1i;
    }
}