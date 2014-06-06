package com.androidquery.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

public class WebImage extends WebViewClient {
    private static String g;
    private Object a;
    private WebView b;
    private String c;
    private boolean d;
    private boolean e;
    private int f;

    public WebImage(WebView r1_WebView, String r2_String, Object r3_Object, boolean r4z, boolean r5z, int r6i) {
        this.b = r1_WebView;
        this.c = r2_String;
        this.a = r3_Object;
        this.d = r4z;
        this.e = r5z;
        this.f = r6i;
    }

    private static String a(Context r2_Context) {
        if (g == null) {
            try {
                g = new String(AQUtility.toBytes(r2_Context.getClassLoader().getResourceAsStream("com/androidquery/util/web_image.html")));
            } catch (Exception e) {
                AQUtility.debug(e);
            }
        }
        return g;
    }

    private void a() {
        this.b.setPictureListener(new d(this));
        this.b.loadData("<html></html>", "text/html", AdViewNetFetchThread.NetEncoding);
        this.b.setBackgroundColor(this.f);
    }

    private void a(WebView r4_WebView) {
        if (this.a != null) {
            r4_WebView.setVisibility(0);
            Common.showProgress(this.a, this.c, false);
        }
        r4_WebView.setWebViewClient(null);
    }

    private void b() {
        String r2_String = a(this.b.getContext()).replace("@src", this.c).replace("@color", Integer.toHexString(this.f));
        this.b.setWebViewClient(this);
        this.b.loadDataWithBaseURL(null, r2_String, "text/html", AdViewNetFetchThread.NetEncoding, null);
        this.b.setBackgroundColor(this.f);
    }

    private static void b(Context r4_Context) {
        SharedPreferences r0_SharedPreferences = r4_Context.getSharedPreferences("WebViewSettings", 0);
        if (r0_SharedPreferences.getInt("double_tap_toast_count", 1) > 0) {
            r0_SharedPreferences.edit().putInt("double_tap_toast_count", 0).commit();
        }
    }

    private static void b(WebView r6_WebView) {
        int r5i = 1;
        if (VERSION.SDK_INT < 11) {
        } else {
            WebSettings r0_WebSettings = r6_WebView.getSettings();
            Class[] r4_ClassA = new Class[r5i];
            r4_ClassA[0] = Boolean.TYPE;
            Object[] r5_ObjectA = new Object[r5i];
            r5_ObjectA[0] = Boolean.valueOf(false);
            AQUtility.invokeHandler(r0_WebSettings, "setDisplayZoomControls", false, false, r4_ClassA, r5_ObjectA);
        }
    }

    public void load() {
        if (this.c.equals(this.b.getTag(Constants.TAG_URL))) {
        } else {
            this.b.setTag(Constants.TAG_URL, this.c);
            if (VERSION.SDK_INT <= REQUEST_CODE.REQUEST_CODE_EDIT_INTRO) {
                this.b.setDrawingCacheEnabled(true);
            }
            b(this.b.getContext());
            WebSettings r0_WebSettings = this.b.getSettings();
            r0_WebSettings.setSupportZoom(this.d);
            r0_WebSettings.setBuiltInZoomControls(this.d);
            if (!this.e) {
                b(this.b);
            }
            r0_WebSettings.setJavaScriptEnabled(true);
            this.b.setBackgroundColor(this.f);
            if (this.a != null) {
                Common.showProgress(this.a, this.c, true);
            }
            if (this.b.getWidth() > 0) {
                b();
            } else {
                a();
            }
        }
    }

    public void onPageFinished(WebView r1_WebView, String r2_String) {
        a(r1_WebView);
    }

    public void onReceivedError(WebView r1_WebView, int r2i, String r3_String, String r4_String) {
        a(r1_WebView);
    }

    public void onScaleChanged(WebView r1_WebView, float r2f, float r3f) {
    }
}