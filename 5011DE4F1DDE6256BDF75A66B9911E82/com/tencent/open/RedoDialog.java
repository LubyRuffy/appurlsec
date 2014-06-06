package com.tencent.open;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.tencent.tauth.IUiListener;
import org.json.JSONObject;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ProGuard
public class RedoDialog extends Dialog {
    static final LayoutParams a;
    public JSONObject b;
    public String c;
    public String d;
    private String e;
    private IUiListener f;
    private ProgressDialog g;
    private WebView h;
    private FrameLayout i;

    static {
        a = new LayoutParams(-1, -1);
    }

    private void a(int r8i) {
        View r0_View = new LinearLayout(getContext());
        this.h = new WebView(getContext());
        this.h.setVerticalScrollBarEnabled(false);
        this.h.setHorizontalScrollBarEnabled(false);
        this.h.setWebViewClient(new t(this, null));
        this.h.getSettings().setJavaScriptEnabled(true);
        this.h.addJavascriptInterface(new q(this, null), "sdk_js_if");
        this.h.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.h.loadUrl(this.e);
        this.h.setLayoutParams(a);
        this.h.setVisibility(XListViewFooter.STATE_NODATA);
        this.h.getSettings().setSavePassword(false);
        r0_View.setPadding(0, 0, 0, 0);
        r0_View.addView(this.h);
        this.i.addView(r0_View, -1, -1);
    }

    public void dismiss() {
        this.f.onCancel();
        super.dismiss();
    }

    protected void onCreate(Bundle r5_Bundle) {
        super.onCreate(r5_Bundle);
        this.g = new ProgressDialog(getContext());
        this.g.requestWindowFeature(1);
        this.g.setMessage("Loading...");
        requestWindowFeature(1);
        this.i = new FrameLayout(getContext());
        a(0);
        addContentView(this.i, new ViewGroup.LayoutParams(-1, -1));
    }
}