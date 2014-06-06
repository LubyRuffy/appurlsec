package com.baidu.kirin.d;

import com.baidu.kirin.KirinConfig;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import qsbk.app.bean.Base;

public class g {
    public static long a;
    public static String b;

    static {
        a = 256;
        b = " HTTP.UTF_8";
    }

    public static e a(String r6_String, String r7_String) {
        Exception r0_Exception;
        d.a("Post_URL : " + r6_String);
        String r0_String = RContactStorage.PRIMARY_KEY;
        e r2_e = new e();
        HttpParams r0_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(r0_HttpParams, KirinConfig.CONNECT_TIME_OUT);
        HttpConnectionParams.setSoTimeout(r0_HttpParams, KirinConfig.READ_TIME_OUT);
        HttpClient r1_HttpClient = new DefaultHttpClient(r0_HttpParams);
        HttpUriRequest r0_HttpUriRequest = new HttpPost(r6_String);
        try {
            HttpEntity r3_HttpEntity = new StringEntity("content=" + r7_String, Base.UTF8);
            d.a("postdata content : " + r7_String);
            r3_HttpEntity.setContentType("application/x-www-form-urlencoded");
            r0_HttpUriRequest.setEntity(r3_HttpEntity);
            HttpResponse r0_HttpResponse = r1_HttpClient.execute(r0_HttpUriRequest);
            d.a("Response_Length : " + ((int) r0_HttpResponse.getEntity().getContentLength()));
            int r1i = r0_HttpResponse.getStatusLine().getStatusCode();
            d.a("Status : " + r1i);
            r0_String = EntityUtils.toString(r0_HttpResponse.getEntity());
            d.a("Return_Content : " + r0_String);
            switch (r1i) {
                case 200:
                    try {
                        r2_e.a(Integer.parseInt(new JSONObject(r0_String).getString("errNum")));
                        r2_e.a(true);
                    } catch (Exception e) {
                        r0_Exception = e;
                        d.c("Parse Response error!");
                        String r1_String = r0_Exception.toString();
                        r2_e.a(false);
                        r0_Exception.printStackTrace();
                        r0_String = r1_String;
                    }
                    r2_e.a(r0_String);
                    d.a("ServerResponse : " + r2_e.c());
                    return r2_e;
            }
            d.c("Stauts : " + r1i + "; RetrunConetent : " + r0_String);
            r2_e.a(false);
            r2_e.a(r0_String);
        } catch (Exception e_2) {
            r0_Exception = e_2;
            JSONObject r1_JSONObject = new JSONObject();
            r0_Exception.printStackTrace();
            r1_JSONObject.put("err", r0_Exception.toString());
            r0_String = r1_JSONObject.toString();
            r2_e.a(false);
            r2_e.a(r0_String);
        }
        d.a("ServerResponse : " + r2_e.c());
        return r2_e;
    }
}