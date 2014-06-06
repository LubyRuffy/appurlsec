package com.zkmm.adsdk;

import android.content.Context;
import android.os.AsyncTask;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class bh extends AsyncTask {
    private /* synthetic */ bg a;
    private final /* synthetic */ Context b;

    bh(bg r1_bg, Context r2_Context) {
        this.a = r1_bg;
        this.b = r2_Context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ArrayList a() {
        /*
        r6_this = this;
        r1 = 0;
        r0 = "http://apiconfig.adwo.com/adwo/a2";
        r2 = new java.net.URL;	 //Catch:{ Exception -> 0x008c }
        r2.<init>(r0);	 //Catch:{ Exception -> 0x008c }
        r0 = r2.openConnection();	 //Catch:{ Exception -> 0x008c }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x008c }
        r2 = 1;
        r0.setDoOutput(r2);	 //Catch:{ Exception -> 0x0091 }
        r2 = 1;
        r0.setDoInput(r2);	 //Catch:{ Exception -> 0x0091 }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r2);	 //Catch:{ Exception -> 0x0091 }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setReadTimeout(r2);	 //Catch:{ Exception -> 0x0091 }
        r2 = "POST";
        r0.setRequestMethod(r2);	 //Catch:{ Exception -> 0x0091 }
        r2 = new java.io.DataOutputStream;	 //Catch:{ Exception -> 0x0091 }
        r3 = r0.getOutputStream();	 //Catch:{ Exception -> 0x0091 }
        r2.<init>(r3);	 //Catch:{ Exception -> 0x0091 }
        r3 = r6.a;	 //Catch:{ Exception -> 0x0091 }
        r4 = r6.b;	 //Catch:{ Exception -> 0x0091 }
        r3 = com.zkmm.adsdk.bg.b(r4);	 //Catch:{ Exception -> 0x0091 }
        r2.writeBytes(r3);	 //Catch:{ Exception -> 0x0091 }
        r2.flush();	 //Catch:{ Exception -> 0x0091 }
        r2.close();	 //Catch:{ Exception -> 0x0091 }
        r0.connect();	 //Catch:{ Exception -> 0x0091 }
        r4 = r0.getInputStream();	 //Catch:{ Exception -> 0x0091 }
        r2 = r0.getResponseCode();	 //Catch:{ Exception -> 0x0097 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r3) goto L_0x0050;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        return r0;
    L_0x0050:
        r3 = new java.io.BufferedReader;	 //Catch:{ Exception -> 0x0097 }
        r2 = new java.io.InputStreamReader;	 //Catch:{ Exception -> 0x0097 }
        r2.<init>(r4);	 //Catch:{ Exception -> 0x0097 }
        r3.<init>(r2);	 //Catch:{ Exception -> 0x0097 }
        r2 = new java.util.ArrayList;	 //Catch:{ Exception -> 0x009d }
        r2.<init>();	 //Catch:{ Exception -> 0x009d }
    L_0x005f:
        r1 = r3.readLine();	 //Catch:{ Exception -> 0x007b }
        if (r1 != 0) goto L_0x0077;
    L_0x0065:
        r1 = r0;
        r0 = r2;
    L_0x0067:
        if (r4 == 0) goto L_0x006c;
    L_0x0069:
        r4.close();	 //Catch:{ IOException -> 0x008a }
    L_0x006c:
        if (r3 == 0) goto L_0x0071;
    L_0x006e:
        r3.close();	 //Catch:{ IOException -> 0x008a }
    L_0x0071:
        if (r1 == 0) goto L_0x004f;
    L_0x0073:
        r1.disconnect();
        goto L_0x004f;
    L_0x0077:
        r2.add(r1);	 //Catch:{ Exception -> 0x007b }
        goto L_0x005f;
    L_0x007b:
        r1 = move-exception;
        r5 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r0;
        r0 = r5;
    L_0x0082:
        r0.printStackTrace();
        r0 = r1;
        r1 = r4;
        r4 = r3;
        r3 = r2;
        goto L_0x0067;
    L_0x008a:
        r2 = move-exception;
        goto L_0x0071;
    L_0x008c:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r4 = r1;
        goto L_0x0082;
    L_0x0091:
        r2 = move-exception;
        r3 = r1;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        goto L_0x0082;
    L_0x0097:
        r2 = move-exception;
        r3 = r4;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        goto L_0x0082;
    L_0x009d:
        r2 = move-exception;
        r5 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r0;
        r0 = r5;
        goto L_0x0082;
        */

    }

    protected final /* synthetic */ Object doInBackground(Object ... r2_ObjectA) {
        return a();
    }

    protected final /* synthetic */ void onPostExecute(Object r10_Object) {
        ArrayList r10_ArrayList = (ArrayList) r10_Object;
        if (r10_ArrayList == null || r10_ArrayList.size() <= 1) {
            this.a.c(this.b);
        } else {
            bg.h = (String) r10_ArrayList.get(0);
            bg.a(this.a, "strategy", bg.h, this.b);
            int r0i = Integer.parseInt((String) r10_ArrayList.get(1));
            if (r0i >= 20) {
                bg.a = r0i;
            }
            bg.a(this.a, "requestInterval", r0i, this.b);
            r0i = Integer.parseInt((String) r10_ArrayList.get(XListViewHeader.STATE_REFRESHING));
            if (r0i > 20) {
                m.S = r0i;
            }
            bg.a(this.a, "fs_requestInterval", r0i, this.b);
            r0i = Integer.parseInt((String) r10_ArrayList.get(XListViewFooter.STATE_NOMORE));
            if (r0i > 0) {
                m.T = r0i;
            }
            bg.a(this.a, "fs_request_limit", r0i, this.b);
            m.V = Integer.parseInt((String) r10_ArrayList.get(XListViewFooter.STATE_NODATA));
            String r0_String = (String) r10_ArrayList.get(ShareUtils.SHARE_SMS);
            if (!RContactStorage.PRIMARY_KEY.equals(r0_String)) {
                s.a(this.b, (String) r10_ArrayList.get(ShareUtils.SHARE_COPY), r0_String);
            }
            r0_String = (String) r10_ArrayList.get(ShareUtils.SHARE_COLLECT);
            if (RContactStorage.PRIMARY_KEY.equals(r0_String) || "0".equals(r0_String)) {
                m.E = new int[0];
            } else {
                String[] r3_StringA = r0_String.split(",");
                m.E = new int[r3_StringA.length];
                int r1i = 0;
                while (r1i < r3_StringA.length) {
                    m.E[r1i] = Integer.parseInt(r3_StringA[r1i]);
                    r1i++;
                }
            }
            bg.a(this.a, "filter_ad_list", r0_String, this.b);
            int r3i = r10_ArrayList.size();
            int r2i = 8;
            while (r2i < r3i) {
                r0_String = (String) r10_ArrayList.get(r2i);
                StringTokenizer r4_StringTokenizer = new StringTokenizer(r0_String, "=");
                bi r5_bi = new bi();
                r5_bi.b = r4_StringTokenizer.nextToken();
                r5_bi.a = Double.parseDouble(r4_StringTokenizer.nextToken());
                r5_bi.c = r4_StringTokenizer.nextToken();
                try {
                    String r1_String = r4_StringTokenizer.nextToken();
                    if (r1_String != null) {
                        r5_bi.e = Integer.decode(r1_String).intValue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    r5_bi.d = r4_StringTokenizer.nextToken();
                } catch (Exception e_2) {
                }
                this.a.g.add(r5_bi);
                bg.a(this.a, new StringBuilder(String.valueOf(r2i)).toString(), r0_String, this.b);
                r0i = this.a.e;
                bg r1_bg = this.a;
                r1_bg.e = r1_bg.e + ((int) (r5_bi.a * 10.0d));
                while (r0i < this.a.e) {
                    this.a.f.put(Integer.valueOf(r0i), Integer.valueOf(this.a.g.size() - 1));
                    r0i++;
                }
                r2i++;
            }
            bg r0_bg = this.a;
            r0_bg.e = r0_bg.e - 1;
            bg.a(this.a, "update_date", m.M.format(new Date()), this.b);
            bg.a(this.a, "configure_version", "4.0", this.b);
        }
    }
}