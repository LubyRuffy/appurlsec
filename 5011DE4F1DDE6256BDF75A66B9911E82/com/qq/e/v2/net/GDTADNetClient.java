package com.qq.e.v2.net;

import com.qq.e.v2.constants.Constants;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.net.GDTADNetRequest.Method;
import com.qq.e.v2.util.GDTLogger;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.bean.Base;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewHeader;

public class GDTADNetClient {
    private static ThreadLocal<Boolean> a;
    private static final GDTADNetClient b;
    private static final HttpClient d;
    private ThreadPoolExecutor c;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Method.values().length];
            try {
                a[Method.POST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a[Method.GET.ordinal()] = 2;
        }
    }

    public static enum Priority {
        High(1),
        Mid(2),
        Low(3);

        private int a;

        static {
            High = new com.qq.e.v2.net.GDTADNetClient.Priority("High", 0, 1);
            Mid = new com.qq.e.v2.net.GDTADNetClient.Priority("Mid", 1, 2);
            Low = new com.qq.e.v2.net.GDTADNetClient.Priority("Low", 2, 3);
            com.qq.e.v2.net.GDTADNetClient.Priority[] r0_com_qq_e_v2_net_GDTADNetClient_PriorityA = new com.qq.e.v2.net.GDTADNetClient.Priority[3];
            r0_com_qq_e_v2_net_GDTADNetClient_PriorityA[0] = High;
            r0_com_qq_e_v2_net_GDTADNetClient_PriorityA[1] = Mid;
            r0_com_qq_e_v2_net_GDTADNetClient_PriorityA[2] = Low;
            b = r0_com_qq_e_v2_net_GDTADNetClient_PriorityA;
        }

        private Priority(int r3i) {
            this.a = r3i;
        }

        public final int value() {
            return this.a;
        }
    }

    static class a implements Comparable<a>, Runnable {
        boolean a;
        private com.qq.e.v2.net.GDTADNetClient.Priority b;
        private GDTADNetRequest c;
        private int d;

        public a(com.qq.e.v2.net.GDTADNetClient.Priority r2_com_qq_e_v2_net_GDTADNetClient_Priority, GDTADNetRequest r3_GDTADNetRequest) {
            this(r2_com_qq_e_v2_net_GDTADNetClient_Priority, r3_GDTADNetRequest, 1);
        }

        public a(com.qq.e.v2.net.GDTADNetClient.Priority r2_com_qq_e_v2_net_GDTADNetClient_Priority, GDTADNetRequest r3_GDTADNetRequest, int r4i) {
            this.d = 1;
            this.a = false;
            this.b = r2_com_qq_e_v2_net_GDTADNetClient_Priority;
            this.c = r3_GDTADNetRequest;
            this.d = r4i;
        }

        private void a_(Exception r5_Exception) {
            int r0i = this.d - 1;
            if (r0i > 0) {
                GDTADNetClient.getInstance().excute(this.c, this.b, r0i);
            } else {
                this.c.onError(r5_Exception);
            }
        }

        private void a_(HttpRequestBase r4_HttpRequestBase) {
            Iterator r2_Iterator = this.c.getHeaders().entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                r4_HttpRequestBase.setHeader((String) r0_Entry.getKey(), (String) r0_Entry.getValue());
            }
            r4_HttpRequestBase.setHeader("User-Agent", new StringBuilder("GDTADNetClient-[").append(System.getProperty("http.agent")).append("]").toString());
            HttpParams r0_HttpParams = r4_HttpRequestBase.getParams();
            if (r0_HttpParams == null) {
                r0_HttpParams = new BasicHttpParams();
            }
            if (this.c.getConnectionTimeOut() > 0) {
                HttpConnectionParams.setConnectionTimeout(r0_HttpParams, this.c.getConnectionTimeOut());
            }
            if (this.c.getSocketTimeOut() > 0) {
                HttpConnectionParams.setSoTimeout(r0_HttpParams, this.c.getSocketTimeOut());
            }
            r4_HttpRequestBase.setParams(r0_HttpParams);
        }

        public final /* synthetic */ int compareTo(Object r3_Object) {
            a r3_a = (a) r3_Object;
            return r3_a == null ? -1 : com.qq.e.v2.net.GDTADNetClient.Priority.a(this.b) - com.qq.e.v2.net.GDTADNetClient.Priority.a(r3_a.b);
        }

        public final void run() {
            boolean r1z = true;
            boolean r2z = false;
            Boolean r0_Boolean = (Boolean) a.get();
            ThreadLocal r3_ThreadLocal = a;
            HttpClient r4_HttpClient;
            HttpResponse r3_HttpResponse;
            String r1_String;
            HttpRequestBase r5_HttpRequestBase;
            byte[] r1_byteA;
            ThreadLocal r1_ThreadLocal;
            Object[] r3_ObjectA;
            if ((r0_Boolean == null || (!r0_Boolean.booleanValue())) && (!this.a)) {
                r1z = false;
                r3_ThreadLocal.set(Boolean.valueOf(r1z));
                try {
                    r4_HttpClient = d;
                    r3_HttpResponse = null;
                    switch (AnonymousClass_1.a[this.c.getMethod().ordinal()]) {
                        case XListViewHeader.STATE_READY:
                            r1_String = this.c.getUrlWithParas();
                            if (!this.c.getUrl().startsWith(Constants.SDK_SERVER_URL)) {
                                r1_String = r1_String.contains("?") ? r1_String + "?queueSize=" + GDTADNetClient.getInstance().a() : r1_String + "&queueSize=" + GDTADNetClient.getInstance().a();
                            }
                            r5_HttpRequestBase = new HttpPost(r1_String);
                            a(r5_HttpRequestBase);
                            try {
                                r1_byteA = this.c.getPostData();
                            } catch (Exception e) {
                                a(e);
                                r5_HttpRequestBase.abort();
                                if (0 == 0 || null.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                } else {
                                    null.getEntity().consumeContent();
                                }
                            }
                            if (r1_byteA == null || r1_byteA.length <= 0) {
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                } else {
                                    try {
                                        r3_HttpResponse.getEntity().consumeContent();
                                    } catch (Exception e_2) {
                                        GDTLogger.w(new StringBuilder("GDTNetwork Exception,url:").append(this.c.getUrlWithParas()).toString(), e_2);
                                    }
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                }
                            } else {
                                r5_HttpRequestBase.setEntity(new ByteArrayEntity(r1_byteA));
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                }
                            }
                        case XListViewHeader.STATE_REFRESHING:
                            r5_HttpRequestBase = new HttpGet(this.c.getUrlWithParas());
                            r5_HttpRequestBase.addHeader("Accept-Encoding", "gzip");
                            a(r5_HttpRequestBase);
                            try {
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                            } catch (Exception e_3) {
                                a(e_3);
                                r5_HttpRequestBase.abort();
                                if (0 == 0 || null.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                } else {
                                    null.getEntity().consumeContent();
                                }
                            }
                            if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                r1_ThreadLocal = a;
                                if (r0_Boolean != null) {
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r2z = r0_Boolean.booleanValue();
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                }
                            } else {
                                try {
                                    r3_HttpResponse.getEntity().consumeContent();
                                } catch (Exception e_4) {
                                    GDTLogger.w(new StringBuilder("GDTNetwork Exception,url:").append(this.c.getUrlWithParas()).toString(), e_4);
                                }
                                r1_ThreadLocal = a;
                                if (r0_Boolean != null) {
                                    r2z = r0_Boolean.booleanValue();
                                }
                                r1_ThreadLocal.set(Boolean.valueOf(r2z));
                            }
                    }
                    r3_ObjectA = new Object[2];
                    r3_ObjectA[0] = this.c.getMethod();
                    r3_ObjectA[1] = this.c.getUrl();
                    GDTLogger.e(String.format("Unsupported HTTP method %s for url %s", r3_ObjectA));
                } catch (Throwable th) {
                    GDTLogger.e("Exception while excute ADNetTask", th);
                }
                r1_ThreadLocal = a;
                if (r0_Boolean != null) {
                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                } else {
                    r2z = r0_Boolean.booleanValue();
                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                }
            } else {
                r3_ThreadLocal.set(Boolean.valueOf(r1z));
                r4_HttpClient = d;
                r3_HttpResponse = null;
                switch (AnonymousClass_1.a[this.c.getMethod().ordinal()]) {
                    case XListViewHeader.STATE_READY:
                        r1_String = this.c.getUrlWithParas();
                        if (this.c.getUrl().startsWith(Constants.SDK_SERVER_URL)) {
                            r5_HttpRequestBase = new HttpPost(r1_String);
                            a(r5_HttpRequestBase);
                            r1_byteA = this.c.getPostData();
                            if (r1_byteA == null || r1_byteA.length <= 0) {
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            } else {
                                r5_HttpRequestBase.setEntity(new ByteArrayEntity(r1_byteA));
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            }
                        } else if (r1_String.contains("?")) {
                            r5_HttpRequestBase = new HttpPost(r1_String);
                            a(r5_HttpRequestBase);
                            r1_byteA = this.c.getPostData();
                            if (r1_byteA == null || r1_byteA.length <= 0) {
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            } else {
                                r5_HttpRequestBase.setEntity(new ByteArrayEntity(r1_byteA));
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            }
                        } else {
                            r5_HttpRequestBase = new HttpPost(r1_String);
                            a(r5_HttpRequestBase);
                            r1_byteA = this.c.getPostData();
                            if (r1_byteA == null || r1_byteA.length <= 0) {
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            } else {
                                r5_HttpRequestBase.setEntity(new ByteArrayEntity(r1_byteA));
                                r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                                this.c.onResponse(r3_HttpResponse);
                                r5_HttpRequestBase.abort();
                                if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r2z = r0_Boolean.booleanValue();
                                    }
                                    r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                } else {
                                    r3_HttpResponse.getEntity().consumeContent();
                                    r1_ThreadLocal = a;
                                    if (r0_Boolean != null) {
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    } else {
                                        r2z = r0_Boolean.booleanValue();
                                        r1_ThreadLocal.set(Boolean.valueOf(r2z));
                                    }
                                }
                            }
                        }
                    case XListViewHeader.STATE_REFRESHING:
                        r5_HttpRequestBase = new HttpGet(this.c.getUrlWithParas());
                        r5_HttpRequestBase.addHeader("Accept-Encoding", "gzip");
                        a(r5_HttpRequestBase);
                        r3_HttpResponse = r4_HttpClient.execute(r5_HttpRequestBase);
                        this.c.onResponse(r3_HttpResponse);
                        r5_HttpRequestBase.abort();
                        if (r3_HttpResponse == null || r3_HttpResponse.getEntity() == null) {
                            r1_ThreadLocal = a;
                            if (r0_Boolean != null) {
                                r2z = r0_Boolean.booleanValue();
                            }
                            r1_ThreadLocal.set(Boolean.valueOf(r2z));
                        } else {
                            r3_HttpResponse.getEntity().consumeContent();
                            r1_ThreadLocal = a;
                            if (r0_Boolean != null) {
                                r1_ThreadLocal.set(Boolean.valueOf(r2z));
                            } else {
                                r2z = r0_Boolean.booleanValue();
                                r1_ThreadLocal.set(Boolean.valueOf(r2z));
                            }
                        }
                }
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = this.c.getMethod();
                r3_ObjectA[1] = this.c.getUrl();
                GDTLogger.e(String.format("Unsupported HTTP method %s for url %s", r3_ObjectA));
                r1_ThreadLocal = a;
                if (r0_Boolean != null) {
                    r2z = r0_Boolean.booleanValue();
                }
                r1_ThreadLocal.set(Boolean.valueOf(r2z));
            }
        }
    }

    static {
        a = new ThreadLocal();
        b = new GDTADNetClient();
        HttpParams r0_HttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(r0_HttpParams, ChatEngine.mQueryUnreadInterval);
        HttpConnectionParams.setConnectionTimeout(r0_HttpParams, 30000);
        HttpConnectionParams.setSoTimeout(r0_HttpParams, 30000);
        ConnManagerParams.setMaxConnectionsPerRoute(r0_HttpParams, new ConnPerRouteBean(3));
        ConnManagerParams.setMaxTotalConnections(r0_HttpParams, REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        HttpProtocolParams.setVersion(r0_HttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(r0_HttpParams, Base.UTF8);
        HttpProtocolParams.setUserAgent(r0_HttpParams, new StringBuilder("GDTADNetClient-[").append(System.getProperty("http.agent")).append("]").toString());
        SchemeRegistry r1_SchemeRegistry = new SchemeRegistry();
        r1_SchemeRegistry.register(new Scheme(ImageFetcher.HTTP_CACHE_DIR, PlainSocketFactory.getSocketFactory(), 80));
        d = new DefaultHttpClient(new ThreadSafeClientConnManager(r0_HttpParams, r1_SchemeRegistry), r0_HttpParams);
    }

    private GDTADNetClient() {
        this.c = new ThreadPoolExecutor(5, 10, 180, TimeUnit.SECONDS, new PriorityBlockingQueue(15));
    }

    private boolean d() {
        int r1i;
        int r2i = this.c.getQueue().size();
        r1i = (GDTADManager.getInstance() == null || GDTADManager.getInstance().getSM() == null) ? 0 : GDTADManager.getInstance().getSM().getInteger("max_adNet_queue_size");
        if (r1i > 10) {
            return r2i <= r1i;
        } else {
            r1i = OneProfileActivity.REQ_CODE_SHARE;
            if (r2i <= r1i) {
            }
        }
    }

    public static final GDTADNetClient getInstance() {
        return b;
    }

    protected final int a() {
        return this.c.getQueue().size();
    }

    public void excute(GDTADNetRequest r3_GDTADNetRequest, Priority r4_Priority) {
        if (d()) {
            if (r3_GDTADNetRequest != null) {
                r3_GDTADNetRequest.onError(new Exception("GDT AD Network Queue is full,check network state"));
            }
        } else {
            this.c.execute(new a(r4_Priority, r3_GDTADNetRequest));
        }
    }

    public void excute(GDTADNetRequest r4_GDTADNetRequest, Priority r5_Priority, int r6i) {
        int r1i;
        int r0i = ShareUtils.SHARE_SMS;
        r1i = r6i <= 0 ? 1 : r6i;
        if (r1i > 5) {
            if (d()) {
                this.c.execute(new a(r5_Priority, r4_GDTADNetRequest, r0i));
            } else {
                if (r4_GDTADNetRequest == null) {
                    r4_GDTADNetRequest.onError(new Exception("GDT AD Network Queue is full,check network state"));
                }
            }
        } else {
            r0i = r1i;
            if (d()) {
                this.c.execute(new a(r5_Priority, r4_GDTADNetRequest, r0i));
            } else if (r4_GDTADNetRequest == null) {
            } else {
                r4_GDTADNetRequest.onError(new Exception("GDT AD Network Queue is full,check network state"));
            }
        }
    }

    public void excuteErrorReportTask(GDTADNetRequest r3_GDTADNetRequest) {
        if (d()) {
        } else {
            Boolean r0_Boolean = (Boolean) a.get();
            if (r0_Boolean == null || (!r0_Boolean.booleanValue())) {
                Runnable r0_Runnable = new a(Priority.Low, r3_GDTADNetRequest);
                r0_Runnable.a = true;
                this.c.execute(r0_Runnable);
            }
        }
    }
}