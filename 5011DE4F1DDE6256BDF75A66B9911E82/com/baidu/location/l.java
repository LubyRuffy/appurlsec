package com.baidu.location;

import com.qiubai.library.adview.util.AdViewNetFetchThread;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

final class l extends Thread {
    l() {
    }

    public void run() {
        try {
            HttpUriRequest r3_HttpUriRequest = new HttpPost(j.do());
            List r4_List = new ArrayList();
            int r1i = 0;
            while (r1i < g.o().size()) {
                if (g.p() == 1) {
                    r4_List.add(new BasicNameValuePair("cldc[" + r1i + "]", (String) g.o().get(r1i)));
                } else {
                    r4_List.add(new BasicNameValuePair("cltr[" + r1i + "]", (String) g.o().get(r1i)));
                }
                r1i++;
            }
            r3_HttpUriRequest.setEntity(new UrlEncodedFormEntity(r4_List, AdViewNetFetchThread.NetEncoding));
            HttpClient r0_HttpClient = new DefaultHttpClient();
            r0_HttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.e()));
            r0_HttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.e()));
            if (r0_HttpClient.execute(r3_HttpUriRequest).getStatusLine().getStatusCode() == 200) {
                j.if(g.g(), "Status ok1...");
                g.o().clear();
                g.a(null);
            } else {
                j.if(g.g(), "Status err1...");
            }
            g.c(false);
        } catch (Exception e) {
            g.c(false);
        }
    }
}