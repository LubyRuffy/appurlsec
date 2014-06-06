package com.baidu.location;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContact;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import qsbk.app.utils.image.ImageFetcher;

final class k extends Thread {
    k() {
    }

    public void run() {
        int r0i = g.a();
        while (r0i > 0) {
            try {
                HttpPost r1_HttpPost = new HttpPost(j.do());
                List r2_List = new ArrayList();
                r2_List.add(new BasicNameValuePair("bloc", g.l()));
                if (g.m() != null) {
                    r2_List.add(new BasicNameValuePair("up", g.m()));
                }
                r1_HttpPost.setEntity(new UrlEncodedFormEntity(r2_List, AdViewNetFetchThread.NetEncoding));
                HttpClient r2_HttpClient = new DefaultHttpClient();
                r2_HttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.e()));
                r2_HttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.e()));
                HttpProtocolParams.setUseExpectContinue(r2_HttpClient.getParams(), false);
                if (g.h() == 1) {
                    r2_HttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(g.i(), g.j(), ImageFetcher.HTTP_CACHE_DIR));
                }
                HttpResponse r2_HttpResponse = r2_HttpClient.execute(r1_HttpPost);
                int r3i = r2_HttpResponse.getStatusLine().getStatusCode();
                j.if(g.g(), "===status error : " + r3i);
                if (r3i == 200) {
                    String r1_String = EntityUtils.toString(r2_HttpResponse.getEntity(), AdViewNetFetchThread.NetEncoding);
                    Message r2_Message = g.n().obtainMessage(AdViewUtil.NETWORK_TYPE_ADCHINA);
                    r2_Message.obj = r1_String;
                    r2_Message.sendToTarget();
                    g.a(null);
                    break;
                } else {
                    r1_HttpPost.abort();
                    Message r1_Message = g.n().obtainMessage(BDLocation.TypeCacheLocation);
                    r1_Message.obj = "HttpStatus error";
                    r1_Message.sendToTarget();
                    r0i--;
                }
            } catch (Exception e) {
            }
        }
        if (r0i > 0 || g.n() == null) {
            g.b(null);
            g.b(false);
        } else {
            j.if(g.g(), "have tried 3 times...");
            g.n().obtainMessage(RContact.MM_CONTACTFLAG_FAVOURCONTACT).sendToTarget();
            g.b(null);
            g.b(false);
        }
    }
}