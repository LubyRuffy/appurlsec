package com.baidu.location;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.qiubai.library.adview.util.AdViewUtil;
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

final class h extends Thread {
    h() {
    }

    public void run() {
        int r1i = g.a();
        while (r1i > 0) {
            try {
                HttpPost r0_HttpPost = new HttpPost(j.do());
                List r2_List = new ArrayList();
                r2_List.add(new BasicNameValuePair("bloc", g.b()));
                if (g.d() != null) {
                    r2_List.add(new BasicNameValuePair("up", g.d()));
                }
                r0_HttpPost.setEntity(new UrlEncodedFormEntity(r2_List, AdViewNetFetchThread.NetEncoding));
                HttpClient r2_HttpClient = new DefaultHttpClient();
                r2_HttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.e()));
                r2_HttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.e()));
                HttpProtocolParams.setUseExpectContinue(r2_HttpClient.getParams(), false);
                j.if(g.g(), "apn type : " + g.h());
                HttpResponse r2_HttpResponse;
                int r3i;
                String r0_String;
                Message r2_Message;
                Message r0_Message;
                if ((g.h() == 1 || g.h() == 4) && (g.a() - r1i) % 2 == 0) {
                    j.if(g.g(), "apn type : ADD PROXY" + g.i() + g.j());
                    r2_HttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(g.i(), g.j(), ImageFetcher.HTTP_CACHE_DIR));
                    r2_HttpResponse = r2_HttpClient.execute(r0_HttpPost);
                    r3i = r2_HttpResponse.getStatusLine().getStatusCode();
                    j.if(g.g(), "===status error : " + r3i);
                    if (r3i != 200) {
                        r0_String = EntityUtils.toString(r2_HttpResponse.getEntity(), AdViewNetFetchThread.NetEncoding);
                        j.if(g.g(), "status error : " + r2_HttpResponse.getEntity().getContentType());
                        r2_Message = g.k().obtainMessage(AdViewUtil.NETWORK_TYPE_WOOBOO);
                        r2_Message.obj = r0_String;
                        r2_Message.sendToTarget();
                        g.a(null);
                        break;
                    } else {
                        r0_HttpPost.abort();
                        r0_Message = g.k().obtainMessage(BDLocation.TypeNetWorkException);
                        r0_Message.obj = "HttpStatus error";
                        r0_Message.sendToTarget();
                        r1i--;
                    }
                } else {
                    r2_HttpResponse = r2_HttpClient.execute(r0_HttpPost);
                    r3i = r2_HttpResponse.getStatusLine().getStatusCode();
                    j.if(g.g(), "===status error : " + r3i);
                    if (r3i != 200) {
                        r0_HttpPost.abort();
                        r0_Message = g.k().obtainMessage(BDLocation.TypeNetWorkException);
                        r0_Message.obj = "HttpStatus error";
                        r0_Message.sendToTarget();
                        r1i--;
                    } else {
                        r0_String = EntityUtils.toString(r2_HttpResponse.getEntity(), AdViewNetFetchThread.NetEncoding);
                        j.if(g.g(), "status error : " + r2_HttpResponse.getEntity().getContentType());
                        r2_Message = g.k().obtainMessage(AdViewUtil.NETWORK_TYPE_WOOBOO);
                        r2_Message.obj = r0_String;
                        r2_Message.sendToTarget();
                        g.a(null);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (r1i > 0 || g.k() == null) {
            g.a(null);
            g.a(false);
        } else {
            j.if(g.g(), "have tried 3 times...");
            g.k().obtainMessage(BDLocation.TypeCriteriaException).sendToTarget();
            g.a(null);
            g.a(false);
        }
    }
}