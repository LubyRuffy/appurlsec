package com.qq.e.v2.net;

import com.qq.e.comm.a;
import com.qq.e.v2.net.GDTADNetRequest.Method;
import com.qq.e.v2.util.GDTLogger;
import org.apache.http.HttpResponse;

public class GDTSecurityADNetRequest extends GDTADNetRequest {
    private CallBack a;

    public static interface CallBack {
        public void onErr(Exception r1_Exception);

        public void onResponse(GDTSecurityADNetRequest r1_GDTSecurityADNetRequest, GDTSecurityADNetResponse r2_GDTSecurityADNetResponse);
    }

    public GDTSecurityADNetRequest(String r3_String, String r4_String, CallBack r5_CallBack) {
        super(r3_String, Method.POST, r4_String == null ? null : a.a(r4_String.getBytes()));
        this.a = r5_CallBack;
    }

    public GDTSecurityADNetRequest(String r3_String, byte[] r4_byteA, CallBack r5_CallBack) {
        super(r3_String, Method.POST, a.a(r4_byteA));
        this.a = r5_CallBack;
    }

    public void onError(Exception r2_Exception) {
        if (this.a != null) {
            this.a.onErr(r2_Exception);
        } else {
            GDTLogger.e("excute security ad request error", r2_Exception);
        }
    }

    public void onResponse(HttpResponse r3_HttpResponse) {
        if (this.a != null) {
            this.a.onResponse(this, new GDTSecurityADNetResponse(r3_HttpResponse, this));
        }
    }
}