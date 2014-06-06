package com.tencent.open;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.open.BrowserAuth.Auth;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ProGuard
public class TDialog extends Dialog {
    static final LayoutParams a;
    static Toast b;
    private static WeakReference c;
    private static WeakReference d;
    private static WeakReference e;
    private String f;
    private i g;
    private IUiListener h;
    private FrameLayout i;
    private WebView j;
    private FrameLayout k;
    private ProgressBar l;
    private Handler m;
    private boolean n;
    private TContext o;

    static {
        a = new LayoutParams(-1, -1);
        b = null;
    }

    public TDialog(Context r4_Context, String r5_String, IUiListener r6_IUiListener, TContext r7_TContext) {
        super(r4_Context, 16973840);
        this.n = false;
        this.o = null;
        c = new WeakReference(r4_Context);
        this.f = r5_String;
        this.g = new i(r6_IUiListener);
        this.m = new j(this.g, r4_Context.getMainLooper());
        this.h = r6_IUiListener;
        this.o = r7_TContext;
    }

    static /* synthetic */ String a(TDialog r2_TDialog, Object r3_Object) {
        String r0_String = r2_TDialog.f + r3_Object;
        r2_TDialog.f = r0_String;
        return r0_String;
    }

    private void c() {
        this.l = new ProgressBar((Context) c.get());
        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = new LayoutParams(-2, -2);
        r0_ViewGroup_LayoutParams.gravity = 17;
        this.l.setLayoutParams(r0_ViewGroup_LayoutParams);
        new TextView((Context) c.get()).setText("test");
        this.k = new FrameLayout((Context) c.get());
        r0_ViewGroup_LayoutParams = new LayoutParams(-1, -2);
        r0_ViewGroup_LayoutParams.bottomMargin = 40;
        r0_ViewGroup_LayoutParams.leftMargin = 80;
        r0_ViewGroup_LayoutParams.rightMargin = 80;
        r0_ViewGroup_LayoutParams.topMargin = 40;
        r0_ViewGroup_LayoutParams.gravity = 17;
        this.k.setLayoutParams(r0_ViewGroup_LayoutParams);
        this.k.setBackgroundResource(17301504);
        this.k.addView(this.l);
        ViewGroup.LayoutParams r1_ViewGroup_LayoutParams = new LayoutParams(-1, -1);
        this.j = new WebView((Context) c.get());
        this.j.setLayoutParams(r1_ViewGroup_LayoutParams);
        this.i = new FrameLayout((Context) c.get());
        r1_ViewGroup_LayoutParams.gravity = 17;
        this.i.setLayoutParams(r1_ViewGroup_LayoutParams);
        this.i.addView(this.j);
        this.i.addView(this.k);
        d = new WeakReference(this.k);
        setContentView(this.i);
    }

    private static void c(Context r4_Context, String r5_String) {
        try {
            JSONObject r0_JSONObject = Util.d(r5_String);
            int r1i = r0_JSONObject.getInt(QsbkDatabase.TYPE);
            CharSequence r0_CharSequence = r0_JSONObject.getString(Constants.PARAM_SEND_MSG);
            if (r1i == 0) {
                if (b == null) {
                    b = Toast.makeText(r4_Context, r0_CharSequence, 0);
                } else {
                    b.setView(b.getView());
                    b.setText(r0_CharSequence);
                    b.setDuration(0);
                }
                b.show();
            } else {
                if (r1i == 1) {
                    if (b == null) {
                        b = Toast.makeText(r4_Context, r0_CharSequence, 1);
                    } else {
                        b.setView(b.getView());
                        b.setText(r0_CharSequence);
                        b.setDuration(1);
                    }
                    b.show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient(new g(this, null));
        this.j.setWebChromeClient(new WebChromeClient());
        this.j.clearFormData();
        WebSettings r1_WebSettings = this.j.getSettings();
        r1_WebSettings.setSavePassword(false);
        r1_WebSettings.setSaveFormData(false);
        r1_WebSettings.setCacheMode(-1);
        r1_WebSettings.setNeedInitialFocus(false);
        r1_WebSettings.setBuiltInZoomControls(true);
        r1_WebSettings.setSupportZoom(true);
        r1_WebSettings.setRenderPriority(RenderPriority.HIGH);
        r1_WebSettings.setJavaScriptEnabled(true);
        if (c == null || c.get() == null) {
            r1_WebSettings.setDomStorageEnabled(true);
            this.j.addJavascriptInterface(new h(this, null), "sdk_js_if");
            this.j.loadUrl(this.f);
            this.j.setLayoutParams(a);
            this.j.setVisibility(XListViewFooter.STATE_NODATA);
            this.j.getSettings().setSavePassword(false);
        } else {
            r1_WebSettings.setDatabaseEnabled(true);
            r1_WebSettings.setDatabasePath(((Context) c.get()).getApplicationContext().getDir("databases", 0).getPath());
            r1_WebSettings.setDomStorageEnabled(true);
            this.j.addJavascriptInterface(new h(this, null), "sdk_js_if");
            this.j.loadUrl(this.f);
            this.j.setLayoutParams(a);
            this.j.setVisibility(XListViewFooter.STATE_NODATA);
            this.j.getSettings().setSavePassword(false);
        }
    }

    private static void d(Context r3_Context, String r4_String) {
        if (r3_Context == null || r4_String == null) {
        } else {
            int r1i;
            CharSequence r2_CharSequence;
            try {
                JSONObject r0_JSONObject = Util.d(r4_String);
                r1i = r0_JSONObject.getInt(QsbkDatabase.ACTION);
                r2_CharSequence = r0_JSONObject.getString(Constants.PARAM_SEND_MSG);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (r1i == 1) {
                if (e == null) {
                    ProgressDialog r0_ProgressDialog = new ProgressDialog(r3_Context);
                    r0_ProgressDialog.setMessage(r2_CharSequence);
                    e = new WeakReference(r0_ProgressDialog);
                    r0_ProgressDialog.show();
                } else {
                    ((ProgressDialog) e.get()).setMessage(r2_CharSequence);
                    if (!((ProgressDialog) e.get()).isShowing()) {
                        ((ProgressDialog) e.get()).show();
                    }
                }
            } else if (r1i != 0 || e == null || e.get() == null || (!((ProgressDialog) e.get()).isShowing())) {
            } else {
                ((ProgressDialog) e.get()).dismiss();
                e = null;
            }
        }
    }

    private boolean e() {
        BrowserAuth r1_BrowserAuth = BrowserAuth.a();
        String r2_String = r1_BrowserAuth.c();
        Auth r3_Auth = new Auth();
        r3_Auth.a = this.h;
        r3_Auth.b = this;
        r3_Auth.c = r2_String;
        String r1_String = r1_BrowserAuth.a(r3_Auth);
        String r3_String = this.f.substring(0, this.f.indexOf("?"));
        Bundle r4_Bundle = Util.b(this.f);
        r4_Bundle.putString("token_key", r2_String);
        r4_Bundle.putString("serial", r1_String);
        r4_Bundle.putString("browser", "1");
        this.f = r3_String + "?" + Util.a(r4_Bundle);
        return (c == null || c.get() == null) ? false : Util.a((Context) c.get(), this.f);
    }

    public void a(String r4_String, String r5_String) {
        this.j.loadUrl("javascript:" + r4_String + "(" + r5_String + ");void(" + System.currentTimeMillis() + ");");
    }

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        requestWindowFeature(1);
        c();
        d();
    }

    protected void onStop() {
        if (!this.n) {
            this.g.onCancel();
        }
        super.onStop();
    }
}