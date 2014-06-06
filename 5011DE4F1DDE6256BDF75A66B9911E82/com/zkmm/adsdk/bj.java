package com.zkmm.adsdk;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class bj {
    static Context j;
    protected static FullScreenAdListener o;
    private static bj s;
    private static View t;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    PopupWindow a;
    ae b;
    WebView c;
    RelativeLayout d;
    ImageView e;
    ImageView f;
    ImageView g;
    RelativeLayout h;
    ProgressBar i;
    int k;
    int l;
    Handler m;
    boolean n;
    int p;
    int q;
    short r;
    private int u;
    private RelativeLayout v;
    private ImageView w;
    private DisplayMetrics x;
    private int y;
    private int z;

    private bj(Context r12_Context) {
        this.a = null;
        this.u = 602;
        this.x = null;
        this.k = 30;
        this.y = 1;
        this.l = 50;
        this.m = new bk(this);
        this.n = false;
        this.z = 0;
        this.A = 0;
        this.B = 600;
        this.C = 601;
        this.D = 602;
        this.E = 603;
        this.p = -1;
        this.q = 1;
        this.r = (short) 0;
        this.F = 0;
        this.x = r12_Context.getResources().getDisplayMetrics();
        try {
            Rect r0_Rect = new Rect();
            ((Activity) r12_Context).getWindow().getDecorView().getWindowVisibleDisplayFrame(r0_Rect);
            int r7i;
            int r8i;
            WebSettings r0_WebSettings;
            int r0i;
            int r1i;
            LayoutParams r4_LayoutParams;
            LayoutParams r1_LayoutParams;
            Animation r0_Animation;
            if (r0_Rect.top == 0) {
                this.k = 30;
                a();
                r7i = this.x.widthPixels;
                r8i = this.x.heightPixels;
                a();
                this.d = new RelativeLayout(j);
                this.d.setGravity(Base64.URL_SAFE);
                this.c = new WebView(j);
                this.c.setId(this.u);
                this.c.setWebViewClient(new aD(this));
                this.c.setWebChromeClient(new ax(this));
                this.c.setScrollBarStyle(0);
                this.c.setVerticalScrollBarEnabled(false);
                this.c.setHorizontalScrollBarEnabled(false);
                CookieManager.getInstance().setAcceptCookie(true);
                r0_WebSettings = this.c.getSettings();
                r0_WebSettings.setDefaultTextEncodingName(Base.UTF8);
                r0_WebSettings.setGeolocationEnabled(true);
                r0_WebSettings.setSupportZoom(false);
                r0_WebSettings.setBuiltInZoomControls(false);
                r0_WebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                r0_WebSettings.setJavaScriptEnabled(true);
                r0_WebSettings.setPluginsEnabled(true);
                r0_WebSettings.setSaveFormData(true);
                r0_WebSettings.setLightTouchEnabled(true);
                r0_WebSettings.setAppCacheEnabled(true);
                r0_WebSettings.setDatabaseEnabled(true);
                r0_WebSettings.setDomStorageEnabled(true);
                r0_WebSettings.setCacheMode(-1);
                r0_WebSettings.setAppCacheMaxSize(8388608);
                if (m.a != null) {
                    m.a = new StringBuilder("/data/data/").append(j.getPackageName()).append("/cache").toString();
                }
                r0_WebSettings.setAppCachePath(m.a);
                this.c.setOnTouchListener(new bn(this));
                this.c.addJavascriptInterface(new bo(this), "adwo");
                this.d.addView(this.c);
                this.h = new RelativeLayout(j);
                this.h.setBackgroundResource(17301658);
                this.g = new ImageButton(j);
                this.g.setBackgroundResource(17301527);
                this.g.setOnClickListener(new bp(this));
                this.e = new ImageButton(j);
                this.e.setVisibility(XListViewFooter.STATE_NODATA);
                this.e.setBackgroundResource(17301538);
                this.e.setOnClickListener(new bq(this));
                this.f = new ImageButton(j);
                this.f.setVisibility(XListViewFooter.STATE_NODATA);
                this.f.setBackgroundResource(17301541);
                this.f.setOnClickListener(new br(this));
                this.w = new ImageButton(j);
                this.w.setBackgroundResource(17301599);
                this.w.setOnClickListener(new bs(this));
                r0i = r8i <= r7i ? r7i / 16 : r7i / 8;
                r1i = r0i / 3 + r0i;
                r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                r4_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
                this.g.setId(this.B);
                r4_LayoutParams.setMargins(0, 0, 0, 0);
                this.h.addView(this.g, r4_LayoutParams);
                r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                r4_LayoutParams.addRule(0, this.B);
                this.w.setId(this.D);
                r4_LayoutParams.setMargins(r1i, 0, r1i, 0);
                this.h.addView(this.w, r4_LayoutParams);
                r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                r4_LayoutParams.addRule(0, this.D);
                this.e.setId(this.E);
                r4_LayoutParams.setMargins(r1i, 0, 0, 0);
                this.h.addView(this.e, r4_LayoutParams);
                r1_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                this.f.setId(this.C);
                r1_LayoutParams.addRule(0, this.E);
                r1_LayoutParams.setMargins(0, 0, 0, 0);
                this.h.addView(this.f, r1_LayoutParams);
                r1_LayoutParams = new RelativeLayout.LayoutParams(-1, r0i);
                r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
                r1_LayoutParams.addRule(XListViewFooter.STATE_NOMORE, this.u);
                this.d.addView(this.h, r1_LayoutParams);
                r0_Animation = new RotateAnimation(-90.0f, 0.0f, 1, 1.0f, 1, 0.0f);
                r0_Animation.setDuration(500);
                this.h.setAnimation(r0_Animation);
                a(r7i, r8i);
            } else {
                this.k = r0_Rect.top;
                a();
                r7i = this.x.widthPixels;
                r8i = this.x.heightPixels;
                a();
                this.d = new RelativeLayout(j);
                this.d.setGravity(Base64.URL_SAFE);
                this.c = new WebView(j);
                this.c.setId(this.u);
                this.c.setWebViewClient(new aD(this));
                this.c.setWebChromeClient(new ax(this));
                this.c.setScrollBarStyle(0);
                this.c.setVerticalScrollBarEnabled(false);
                this.c.setHorizontalScrollBarEnabled(false);
                CookieManager.getInstance().setAcceptCookie(true);
                r0_WebSettings = this.c.getSettings();
                r0_WebSettings.setDefaultTextEncodingName(Base.UTF8);
                r0_WebSettings.setGeolocationEnabled(true);
                r0_WebSettings.setSupportZoom(false);
                r0_WebSettings.setBuiltInZoomControls(false);
                r0_WebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                r0_WebSettings.setJavaScriptEnabled(true);
                r0_WebSettings.setPluginsEnabled(true);
                r0_WebSettings.setSaveFormData(true);
                r0_WebSettings.setLightTouchEnabled(true);
                r0_WebSettings.setAppCacheEnabled(true);
                r0_WebSettings.setDatabaseEnabled(true);
                r0_WebSettings.setDomStorageEnabled(true);
                r0_WebSettings.setCacheMode(-1);
                r0_WebSettings.setAppCacheMaxSize(8388608);
                if (m.a != null) {
                    r0_WebSettings.setAppCachePath(m.a);
                    this.c.setOnTouchListener(new bn(this));
                    this.c.addJavascriptInterface(new bo(this), "adwo");
                    this.d.addView(this.c);
                    this.h = new RelativeLayout(j);
                    this.h.setBackgroundResource(17301658);
                    this.g = new ImageButton(j);
                    this.g.setBackgroundResource(17301527);
                    this.g.setOnClickListener(new bp(this));
                    this.e = new ImageButton(j);
                    this.e.setVisibility(XListViewFooter.STATE_NODATA);
                    this.e.setBackgroundResource(17301538);
                    this.e.setOnClickListener(new bq(this));
                    this.f = new ImageButton(j);
                    this.f.setVisibility(XListViewFooter.STATE_NODATA);
                    this.f.setBackgroundResource(17301541);
                    this.f.setOnClickListener(new br(this));
                    this.w = new ImageButton(j);
                    this.w.setBackgroundResource(17301599);
                    this.w.setOnClickListener(new bs(this));
                    if (r8i <= r7i) {
                    }
                    r1i = r0i / 3 + r0i;
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
                    this.g.setId(this.B);
                    r4_LayoutParams.setMargins(0, 0, 0, 0);
                    this.h.addView(this.g, r4_LayoutParams);
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(0, this.B);
                    this.w.setId(this.D);
                    r4_LayoutParams.setMargins(r1i, 0, r1i, 0);
                    this.h.addView(this.w, r4_LayoutParams);
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(0, this.D);
                    this.e.setId(this.E);
                    r4_LayoutParams.setMargins(r1i, 0, 0, 0);
                    this.h.addView(this.e, r4_LayoutParams);
                    r1_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    this.f.setId(this.C);
                    r1_LayoutParams.addRule(0, this.E);
                    r1_LayoutParams.setMargins(0, 0, 0, 0);
                    this.h.addView(this.f, r1_LayoutParams);
                    r1_LayoutParams = new RelativeLayout.LayoutParams(-1, r0i);
                    r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
                    r1_LayoutParams.addRule(XListViewFooter.STATE_NOMORE, this.u);
                    this.d.addView(this.h, r1_LayoutParams);
                    r0_Animation = new RotateAnimation(-90.0f, 0.0f, 1, 1.0f, 1, 0.0f);
                    r0_Animation.setDuration(500);
                    this.h.setAnimation(r0_Animation);
                    a(r7i, r8i);
                } else {
                    m.a = new StringBuilder("/data/data/").append(j.getPackageName()).append("/cache").toString();
                    r0_WebSettings.setAppCachePath(m.a);
                    this.c.setOnTouchListener(new bn(this));
                    this.c.addJavascriptInterface(new bo(this), "adwo");
                    this.d.addView(this.c);
                    this.h = new RelativeLayout(j);
                    this.h.setBackgroundResource(17301658);
                    this.g = new ImageButton(j);
                    this.g.setBackgroundResource(17301527);
                    this.g.setOnClickListener(new bp(this));
                    this.e = new ImageButton(j);
                    this.e.setVisibility(XListViewFooter.STATE_NODATA);
                    this.e.setBackgroundResource(17301538);
                    this.e.setOnClickListener(new bq(this));
                    this.f = new ImageButton(j);
                    this.f.setVisibility(XListViewFooter.STATE_NODATA);
                    this.f.setBackgroundResource(17301541);
                    this.f.setOnClickListener(new br(this));
                    this.w = new ImageButton(j);
                    this.w.setBackgroundResource(17301599);
                    this.w.setOnClickListener(new bs(this));
                    if (r8i <= r7i) {
                    }
                    r1i = r0i / 3 + r0i;
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
                    this.g.setId(this.B);
                    r4_LayoutParams.setMargins(0, 0, 0, 0);
                    this.h.addView(this.g, r4_LayoutParams);
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(0, this.B);
                    this.w.setId(this.D);
                    r4_LayoutParams.setMargins(r1i, 0, r1i, 0);
                    this.h.addView(this.w, r4_LayoutParams);
                    r4_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    r4_LayoutParams.addRule(0, this.D);
                    this.e.setId(this.E);
                    r4_LayoutParams.setMargins(r1i, 0, 0, 0);
                    this.h.addView(this.e, r4_LayoutParams);
                    r1_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
                    this.f.setId(this.C);
                    r1_LayoutParams.addRule(0, this.E);
                    r1_LayoutParams.setMargins(0, 0, 0, 0);
                    this.h.addView(this.f, r1_LayoutParams);
                    r1_LayoutParams = new RelativeLayout.LayoutParams(-1, r0i);
                    r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
                    r1_LayoutParams.addRule(XListViewFooter.STATE_NOMORE, this.u);
                    this.d.addView(this.h, r1_LayoutParams);
                    r0_Animation = new RotateAnimation(-90.0f, 0.0f, 1, 1.0f, 1, 0.0f);
                    r0_Animation.setDuration(500);
                    this.h.setAnimation(r0_Animation);
                    a(r7i, r8i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static bj a(Context r1_Context) {
        j = r1_Context;
        if (s == null) {
            s = new bj(r1_Context);
        }
        return s;
    }

    private void a(int r5i, int r6i) {
        this.a = new PopupWindow(j);
        this.a.setOutsideTouchable(false);
        this.a.setClippingEnabled(false);
        this.a.setTouchable(true);
        this.a.setFocusable(true);
        this.a.setAnimationStyle(16973910);
        this.a.setBackgroundDrawable(new ColorDrawable(-1));
        this.a.setWidth(r5i);
        this.a.setHeight(r6i);
        if (this.v != null) {
            this.v.removeView(this.d);
        }
        this.v = new RelativeLayout(j);
        this.v.addView(this.d);
        this.a.setContentView(this.v);
        this.a.setOnDismissListener(new bm(this));
    }

    private void a(View r8_View, int r9i, int r10i, int r11i, int r12i) {
        try {
            a(this.x.widthPixels, this.x.heightPixels);
            r8_View.post(new bl(this, r8_View, r9i, r10i, r11i, r12i));
        } catch (Exception e) {
            Log.e("Adwo SDK", "Adwo Ad window Exception...");
            e.printStackTrace();
            this.F++;
            if (this.F < XListViewFooter.STATE_NOMORE) {
                a(r8_View, r9i, r10i, r11i, r12i);
            } else {
                this.F = 0;
            }
        }
    }

    static /* synthetic */ void a(bj r1_bj) {
        try {
            AdListener r0_AdListener = ((ZKMMAdView) t.getParent()).c();
            if (r0_AdListener != null) {
                r0_AdListener.onDismissScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final void a() {
        try {
            if (this.a != null) {
                this.a.dismiss();
            }
            b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final void a(View r10_View, String r11_String, int r12i, int r13i, short r14s) {
        int r0i;
        t = r10_View;
        try {
            AdListener r0_AdListener = ((ZKMMAdView) t.getParent()).c();
            if (r0_AdListener != null) {
                r0_AdListener.onPresentScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.p = r12i;
        this.q = r13i;
        this.r = r14s;
        this.c.getSettings().setBlockNetworkImage(true);
        this.c.clearHistory();
        this.c.loadUrl(r11_String);
        int r1i = this.x.widthPixels;
        int r2i = this.x.heightPixels - this.k;
        r0i = r2i <= r1i ? r1i / 16 : r1i / 8;
        this.c.setLayoutParams(new RelativeLayout.LayoutParams(r1i, r2i - r0i));
        this.c.requestLayout();
        r1i = r0i / 3 + r0i;
        LayoutParams r2_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
        r2_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
        r2_LayoutParams.setMargins(0, 0, 0, 0);
        this.g.setLayoutParams(r2_LayoutParams);
        this.g.requestLayout();
        r2_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
        r2_LayoutParams.addRule(0, this.B);
        r2_LayoutParams.setMargins(r1i, 0, r1i, 0);
        this.w.setLayoutParams(r2_LayoutParams);
        this.w.requestLayout();
        r2_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
        r2_LayoutParams.addRule(0, this.D);
        r2_LayoutParams.setMargins(r1i, 0, 0, 0);
        this.e.setLayoutParams(r2_LayoutParams);
        this.e.requestLayout();
        LayoutParams r1_LayoutParams = new RelativeLayout.LayoutParams(r0i, r0i);
        r1_LayoutParams.addRule(0, this.E);
        r1_LayoutParams.setMargins(0, 0, 0, 0);
        this.f.setLayoutParams(r1_LayoutParams);
        this.f.requestLayout();
        r1_LayoutParams = new RelativeLayout.LayoutParams(-1, r0i);
        r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
        this.h.setLayoutParams(r1_LayoutParams);
        this.h.requestLayout();
        int r5i = this.x.widthPixels;
        int r6i = this.x.heightPixels;
        Configuration r0_Configuration = j.getResources().getConfiguration();
        try {
            if (this.y != r0_Configuration.orientation) {
                this.y = r0_Configuration.orientation;
                if (this.y == 1) {
                    this.c.loadUrl("javascript:adwoFSAdOrientationChanged(0);");
                } else {
                    this.c.loadUrl("javascript:adwoFSAdOrientationChanged(1);");
                }
            }
            r10_View.post(new bt(this, r10_View, 0, 0, r5i, r6i));
        } catch (Exception e_2) {
            Log.e("Adwo SDK", "Adwo Ad window Exception...");
            e_2.printStackTrace();
            a(r10_View, 0, 0, r5i, r6i);
        }
        this.n = true;
    }

    void b() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
    }
}