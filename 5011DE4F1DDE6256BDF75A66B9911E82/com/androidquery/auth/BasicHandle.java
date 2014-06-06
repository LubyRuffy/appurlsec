package com.androidquery.auth;

import android.net.Uri;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import java.net.HttpURLConnection;
import org.apache.http.HttpRequest;

public class BasicHandle extends AccountHandle {
    private String a;
    private String b;

    public BasicHandle(String r1_String, String r2_String) {
        this.a = r1_String;
        this.b = r2_String;
    }

    protected void a() {
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r6_AbstractAjaxCallback___, HttpURLConnection r7_HttpURLConnection) {
        byte[] r0_byteA = new StringBuilder(String.valueOf(this.a)).append(":").append(this.b).toString().getBytes();
        String r0_String = new StringBuilder("Basic ").append(new String(AQUtility.encode64(r0_byteA, 0, r0_byteA.length))).toString();
        r7_HttpURLConnection.setRequestProperty("Host", Uri.parse(r6_AbstractAjaxCallback___.getUrl()).getHost());
        r7_HttpURLConnection.setRequestProperty("Authorization", r0_String);
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r6_AbstractAjaxCallback___, HttpRequest r7_HttpRequest) {
        byte[] r0_byteA = new StringBuilder(String.valueOf(this.a)).append(":").append(this.b).toString().getBytes();
        String r0_String = new StringBuilder("Basic ").append(new String(AQUtility.encode64(r0_byteA, 0, r0_byteA.length))).toString();
        r7_HttpRequest.addHeader("Host", Uri.parse(r6_AbstractAjaxCallback___.getUrl()).getHost());
        r7_HttpRequest.addHeader("Authorization", r0_String);
    }

    public boolean authenticated() {
        return true;
    }

    public boolean expired(AbstractAjaxCallback<?, ?> r2_AbstractAjaxCallback___, AjaxStatus r3_AjaxStatus) {
        return false;
    }

    public boolean reauth(AbstractAjaxCallback<?, ?> r2_AbstractAjaxCallback___) {
        return false;
    }
}