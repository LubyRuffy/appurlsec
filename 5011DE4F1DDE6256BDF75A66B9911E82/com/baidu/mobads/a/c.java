package com.baidu.mobads.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.widget.listview.XListViewFooter;

final class c extends Thread {
    final /* synthetic */ File a;
    final /* synthetic */ Context b;

    c(File r1_File, Context r2_Context) {
        this.a = r1_File;
        this.b = r2_Context;
    }

    public void run() {
        try {
            Object[] r0_ObjectA = new Object[XListViewFooter.STATE_NOMORE];
            r0_ObjectA[0] = "start version check in ";
            r0_ObjectA[1] = Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
            r0_ObjectA[2] = "s";
            d.a(r0_ObjectA);
            sleep(ChatEngine.mQueryConvInterval);
            d.a(">>>start version check");
            double r0d = Double.parseDouble(new JarFile(this.a).getManifest().getMainAttributes().getValue("Implementation-Version"));
            List r2_List = new ArrayList();
            r2_List.add(new BasicNameValuePair("v", RContactStorage.PRIMARY_KEY + r0d));
            r2_List.add(new BasicNameValuePair("tp", Build.MODEL));
            r2_List.add(new BasicNameValuePair("os", "android"));
            r2_List.add(new BasicNameValuePair("bdr", VERSION.SDK));
            String r2_String = "http://mobads.baidu.com/ads/pa/" + a.f + "?" + URLEncodedUtils.format(r2_List, AdViewNetFetchThread.NetEncoding);
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "update req url is:";
            r3_ObjectA[1] = r2_String;
            d.a(r3_ObjectA);
            r2_String = b.a(this.b, r2_String);
            r3_ObjectA = new Object[2];
            r3_ObjectA[0] = "update ret url is:";
            r3_ObjectA[1] = r2_String;
            d.a(r3_ObjectA);
            JSONObject r3_JSONObject = new JSONObject(r2_String);
            double r4d = r3_JSONObject.getDouble("version");
            r2_String = r3_JSONObject.getString(Constants.PARAM_URL);
            r3_ObjectA = new Object[6];
            r3_ObjectA[0] = "update serverVersion";
            r3_ObjectA[1] = Double.valueOf(r4d);
            r3_ObjectA[2] = "localVersion";
            r3_ObjectA[3] = Double.valueOf(r0d);
            r3_ObjectA[4] = "downloadUrl";
            r3_ObjectA[5] = r2_String;
            d.a(r3_ObjectA);
            if (r4d <= r0d || RContactStorage.PRIMARY_KEY.equals(r2_String)) {
            } else {
                b.b(false, this.b, new URL(r2_String), this.a.getName());
            }
        } catch (Exception e) {
            b.a(false);
            d.b(e);
        }
    }
}