package com.qiubai.library.adview.util;

import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import qsbk.app.thirdparty.UsersAPI;

public class AdViewNetFetchThread extends Thread {
    public static final int FETCH_ERROR = -1;
    public static final int FETCH_OK = 0;
    public static final int FETCH_UI_START = 101;
    public static final String NetEncoding = "utf-8";
    public static final int REASON_GENERAL = 0;
    public static final int REASON_NET = 1;
    public static final int TYPE_BINARY = 1;
    public static final int TYPE_TEXT = 0;
    public static final int mTimeOut = 25000;
    Handler a;
    private b b;
    private String c;
    private String d;
    private int e;
    private int f;
    private Object g;
    private Object h;

    AdViewNetFetchThread(String r3_String, String r4_String) {
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = new Object();
        this.h = null;
        this.a = new a(this);
        this.c = r3_String;
        this.d = r4_String;
    }

    private void a(int r2i) {
        a(this, r2i, null);
    }

    private void a(AdViewNetFetchThread r3_AdViewNetFetchThread, int r4i, Object r5_Object) {
        Message r0_Message = new Message();
        r0_Message.what = 0;
        r0_Message.arg1 = r4i;
        r0_Message.arg2 = 0;
        r0_Message.obj = r5_Object;
        this.a.sendMessage(r0_Message);
    }

    private void a(String r6_String, String r7_String) {
        try {
            a(FETCH_UI_START);
            HttpURLConnection r0_HttpURLConnection = (HttpURLConnection) new URL(r6_String).openConnection();
            if (r0_HttpURLConnection != null) {
                r0_HttpURLConnection.setConnectTimeout(mTimeOut);
                r0_HttpURLConnection.setReadTimeout(mTimeOut);
                r0_HttpURLConnection.setUseCaches(false);
                if (r7_String != null) {
                    r0_HttpURLConnection.setRequestMethod(UsersAPI.HTTPMETHOD_POST);
                    r0_HttpURLConnection.setRequestProperty("Content-Length", r7_String.length());
                    r0_HttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    r0_HttpURLConnection.setDoOutput(true);
                    r0_HttpURLConnection.setDoInput(true);
                    DataOutputStream r1_DataOutputStream = new DataOutputStream(r0_HttpURLConnection.getOutputStream());
                    r1_DataOutputStream.write(r7_String.getBytes());
                    r1_DataOutputStream.flush();
                    r1_DataOutputStream.close();
                }
                if (r0_HttpURLConnection.getResponseCode() == 200) {
                    String r1_String = r0_HttpURLConnection.getHeaderField("Content-Type");
                    if (r1_String.toLowerCase().contains("text") || r1_String.toLowerCase().contains("json")) {
                        setContentType(TYPE_TEXT);
                    } else {
                        setContentType(TYPE_BINARY);
                    }
                    a(this, TYPE_TEXT, getContentObject(r0_HttpURLConnection.getInputStream()));
                } else {
                    b((int)TYPE_BINARY);
                }
                r0_HttpURLConnection.disconnect();
            } else {
                b((int)TYPE_BINARY);
            }
        } catch (IOException e) {
            AdViewUtil.logInfo(e.toString());
            b((int)TYPE_BINARY);
        }
    }

    private void b(int r3i) {
        a(this, FETCH_ERROR, Integer.valueOf(r3i));
    }

    public static byte[] getContentData(InputStream r3_InputStream) throws IOException {
        ByteArrayOutputStream r0_ByteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int r1i = r3_InputStream.read();
            if (r1i == -1) {
                byte[] r1_byteA = r0_ByteArrayOutputStream.toByteArray();
                r0_ByteArrayOutputStream.close();
                return r1_byteA;
            } else {
                r0_ByteArrayOutputStream.write(r1i);
            }
        }
    }

    public static String getContentString(InputStream r4_InputStream) throws IOException {
        StringBuilder r1_StringBuilder = new StringBuilder();
        BufferedReader r2_BufferedReader = new BufferedReader(new InputStreamReader(r4_InputStream, NetEncoding));
        String r0_String = r2_BufferedReader.readLine();
        while (r0_String != null) {
            r1_StringBuilder.append(r0_String);
            r0_String = r2_BufferedReader.readLine();
        }
        return r1_StringBuilder.toString();
    }

    public Object getContentObject(InputStream r3_InputStream) throws IOException {
        return 1 == this.e ? getContentData(r3_InputStream) : getContentString(r3_InputStream);
    }

    public Object getUserObject() {
        return this.h;
    }

    public void run() {
        a(this.c, this.d);
    }

    public void setContentType(int r1i) {
        this.e = r1i;
    }

    public void setFetchId(int r1i) {
        this.f = r1i;
    }

    public void setFetchListener(b r3_b) {
        synchronized (this.g) {
            this.b = r3_b;
        }
    }

    public void setPostString(String r1_String) {
        this.d = r1_String;
    }

    public void setUserObject(Object r1_Object) {
        this.h = r1_Object;
    }
}