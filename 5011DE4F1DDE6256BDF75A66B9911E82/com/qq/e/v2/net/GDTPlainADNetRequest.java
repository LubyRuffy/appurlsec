package com.qq.e.v2.net;

import com.qq.e.v2.net.GDTADNetRequest.Method;
import com.qq.e.v2.util.GDTLogger;
import org.apache.http.HttpResponse;

public class GDTPlainADNetRequest extends GDTADNetRequest {
    private CallBack a;

    public static interface CallBack {
        public void onError(Exception r1_Exception);

        public void onResponse(GDTPlainADNetRequest r1_GDTPlainADNetRequest, GDTPlainADNetResponse r2_GDTPlainADNetResponse);
    }

    public GDTPlainADNetRequest(String r1_String, Method r2_Method, byte[] r3_byteA, CallBack r4_CallBack) {
        super(r1_String, r2_Method, r3_byteA);
        this.a = r4_CallBack;
    }

    public void onError(Exception r3_Exception) {
        if (this.a != null) {
            this.a.onError(r3_Exception);
        } else {
            GDTLogger.e(new StringBuilder("Exception for plain Request to url:").append(getUrlWithParas()).toString(), r3_Exception);
        }
    }

    public void onResponse(HttpResponse r3_HttpResponse) {
        if (this.a != null) {
            this.a.onResponse(this, new GDTPlainADNetResponse(r3_HttpResponse, this));
        }
    }
}