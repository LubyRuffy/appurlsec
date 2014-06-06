package com.androidquery;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidquery.util.AQUtility;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;

public class WebDialog extends Dialog {
    private String a;
    private WebViewClient b;
    private WebView c;
    private LinearLayout d;
    private String e;

    private class a extends WebViewClient {
        private a() {
        }

        public void onPageFinished(WebView r3_WebView, String r4_String) {
            WebDialog.this.a(false);
            WebDialog.this.b.onPageFinished(r3_WebView, r4_String);
        }

        public void onPageStarted(WebView r2_WebView, String r3_String, Bitmap r4_Bitmap) {
            WebDialog.this.b.onPageStarted(r2_WebView, r3_String, r4_Bitmap);
        }

        public void onReceivedError(WebView r2_WebView, int r3i, String r4_String, String r5_String) {
            WebDialog.this.b.onReceivedError(r2_WebView, r3i, r4_String, r5_String);
        }

        public boolean shouldOverrideUrlLoading(WebView r2_WebView, String r3_String) {
            return WebDialog.this.b.shouldOverrideUrlLoading(r2_WebView, r3_String);
        }
    }

    public WebDialog(Context r2_Context, String r3_String, WebViewClient r4_WebViewClient) {
        super(r2_Context, 16973830);
        this.a = r3_String;
        this.b = r4_WebViewClient;
    }

    private void a(RelativeLayout r6_RelativeLayout) {
        Context r0_Context = getContext();
        this.d = new LinearLayout(r0_Context);
        View r1_View = new ProgressBar(r0_Context);
        int r2i = AQUtility.dip2pixel(r0_Context, 30.0f);
        this.d.addView(r1_View, new LayoutParams(r2i, r2i));
        if (this.e != null) {
            r1_View = new TextView(r0_Context);
            ViewGroup.LayoutParams r2_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
            r2_ViewGroup_LayoutParams.leftMargin = AQUtility.dip2pixel(r0_Context, 5.0f);
            r2_ViewGroup_LayoutParams.gravity = 16;
            r1_View.setText(this.e);
            this.d.addView(r1_View, r2_ViewGroup_LayoutParams);
        }
        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        r0_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
        r6_RelativeLayout.addView(this.d, r0_ViewGroup_LayoutParams);
    }

    private void a(boolean r3z) {
        if (this.d != null) {
            if (r3z) {
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    private void b(RelativeLayout r5_RelativeLayout) {
        this.c = new WebView(getContext());
        this.c.setVerticalScrollBarEnabled(false);
        this.c.setHorizontalScrollBarEnabled(false);
        if (this.b == null) {
            this.b = new WebViewClient();
        }
        this.c.setWebViewClient(new a(null));
        this.c.getSettings().setJavaScriptEnabled(true);
        r5_RelativeLayout.addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }

    public void load() {
        if (this.c != null) {
            this.c.loadUrl(this.a);
        }
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        RelativeLayout r0_RelativeLayout = new RelativeLayout(getContext());
        r0_RelativeLayout.setBackgroundColor(-1);
        b(r0_RelativeLayout);
        a(r0_RelativeLayout);
        addContentView(r0_RelativeLayout, new FrameLayout.LayoutParams(-1, -1));
    }

    public void setLoadingMessage(String r1_String) {
        this.e = r1_String;
    }
}