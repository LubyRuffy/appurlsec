package com.flurry.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import qsbk.app.bean.Base;

// compiled from: SourceFile
final class ac extends WebViewClient {
    private /* synthetic */ CatalogActivity a;

    ac(CatalogActivity r1_CatalogActivity) {
        this.a = r1_CatalogActivity;
    }

    public final void onPageFinished(WebView r6_WebView, String r7_String) {
        try {
            ab r0_ab = this.a.f;
            r r1_r = new r((byte) 5, this.a.e.k());
            long r2j = this.a.f.c;
            r0_ab.d.add(r1_r);
            r0_ab.c = r2j;
        } catch (Exception e) {
        }
    }

    public final void onReceivedError(WebView r4_WebView, int r5i, String r6_String, String r7_String) {
        i.c("FlurryAgent", "Failed to load url: " + r7_String + " with an errorCode of " + r5i);
        r4_WebView.loadData("Cannot find Android Market information. <p>Please check your network", "text/html", Base.UTF8);
    }

    public final boolean shouldOverrideUrlLoading(WebView r6_WebView, String r7_String) {
        if (r7_String == null) {
            return false;
        }
        if (this.a.f != null) {
            this.a.f.a(new r((byte) 6, this.a.e.k()));
        }
        this.a.e.a(r6_WebView.getContext(), this.a.f, r7_String);
        return true;
    }
}