package com.zkmm.adsdk;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.VideoView;
import java.util.StringTokenizer;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class cs extends WebView {
    protected static boolean a;
    protected static long b;
    protected static int c;
    protected static String d;
    private static ScaleAnimation n;
    private j e;
    private E f;
    private Vibrator g;
    private VideoView h;
    private O i;
    private ae j;
    private int k;
    private int l;
    private int m;
    private long o;
    private Handler p;

    static {
        a = false;
        n = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
        b = 0;
        c = -1;
        d = null;
    }

    protected cs(j r7_j, Context r8_Context) {
        super(r8_Context);
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 1;
        this.l = 0;
        this.m = 50;
        this.o = 0;
        this.p = new a(this);
        this.f = new E(this);
        this.e = r7_j;
        setFocusableInTouchMode(true);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(this.f);
        getSettings().setLoadsImagesAutomatically(true);
        getSettings().setPluginsEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setSaveFormData(false);
        getSettings().setSavePassword(false);
        getSettings().setRenderPriority(RenderPriority.HIGH);
        getSettings().setLightTouchEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setDatabaseEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setCacheMode(-1);
        getSettings().setAppCacheMaxSize(8388608);
        if (m.a == null) {
            m.a = new StringBuilder("/data/data/").append(getContext().getPackageName()).append("/cache").toString();
        }
        getSettings().setAppCachePath(m.a);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setSupportZoom(false);
        getSettings().setGeolocationEnabled(true);
        requestFocusFromTouch();
        setAnimationCacheEnabled(true);
        setScrollBarStyle(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setBackgroundColor(0);
        setInitialScale((int) (((((double) m.A) * m.L) / 320.0d) * 100.0d));
        addJavascriptInterface(new d(this), "adwo");
        if (this.e != null) {
            setFocusable(true);
            setClickable(true);
            setVisibility(Base64.DONT_BREAK_LINES);
            String r0_String = this.e.b();
            if (r0_String != null) {
                loadUrl(r0_String);
            } else {
                setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    static /* synthetic */ void a(cs r0_cs, StringTokenizer r1_StringTokenizer) {
    }

    private void c() {
        if (this.j != null) {
            this.j.b();
            this.j = null;
        }
    }

    static /* synthetic */ void g(cs r2_cs) {
        if (r2_cs.h != null) {
            r2_cs.h.stopPlayback();
            try {
                ((ZKMMAdView) r2_cs.getParent()).removeView(r2_cs.h);
            } catch (Exception e) {
            }
            r2_cs.h = null;
        }
    }

    protected final j a() {
        return this.e;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            bj.a(getContext()).a();
            this.p.sendEmptyMessage(1);
            this.p.sendEmptyMessage(ShareUtils.SHARE_SMS);
            this.p.removeMessages(XListViewHeader.STATE_REFRESHING);
            this.p = null;
            c();
            if (this.i != null) {
                this.i.dismiss();
                this.i = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}