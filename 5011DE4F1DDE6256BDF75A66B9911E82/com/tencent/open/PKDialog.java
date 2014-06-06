package com.tencent.open;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.tencent.open.KeyboardDetectorRelativeLayout.IKeyboardChanged;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;

// compiled from: ProGuard
public class PKDialog extends Dialog implements IKeyboardChanged {
    static Toast a;
    private static final String b;
    private static WeakReference i;
    private String c;
    private IUiListener d;
    private d e;
    private Handler f;
    private KeyboardDetectorRelativeLayout g;
    private WebView h;
    private int j;

    static {
        b = PKDialog.class.getName();
        a = null;
    }

    public PKDialog(Context r4_Context, String r5_String, IUiListener r6_IUiListener) {
        super(r4_Context, 16973840);
        i = new WeakReference(r4_Context);
        this.c = r5_String;
        this.e = new d(r6_IUiListener);
        this.f = new e(this.e, r4_Context.getMainLooper());
        this.d = r6_IUiListener;
        this.j = Math.round(185.0f * r4_Context.getResources().getDisplayMetrics().density);
        Log.e(b, "density=" + r4_Context.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.j);
    }

    private void c() {
        this.g = new KeyboardDetectorRelativeLayout((Context) i.get());
        this.g.setBackgroundColor(1711276032);
        this.g.setLayoutParams(new LayoutParams(-1, -1));
        this.h = new WebView((Context) i.get());
        this.h.setBackgroundColor(0);
        this.h.setBackgroundDrawable(null);
        if (VERSION.SDK_INT >= REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE) {
            try {
                Class[] r2_ClassA = new Class[2];
                r2_ClassA[0] = Integer.TYPE;
                r2_ClassA[1] = Paint.class;
                Method r0_Method = View.class.getMethod("setLayerType", r2_ClassA);
                WebView r1_WebView = this.h;
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[0] = Integer.valueOf(1);
                r2_ObjectA[1] = new Paint();
                r0_Method.invoke(r1_WebView, r2_ObjectA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = new LayoutParams(-1, this.j);
        r0_ViewGroup_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, -1);
        this.h.setLayoutParams(r0_ViewGroup_LayoutParams);
        this.g.addView(this.h);
        this.g.a(this);
        setContentView(this.g);
    }

    private static void c(Context r4_Context, String r5_String) {
        try {
            JSONObject r0_JSONObject = Util.d(r5_String);
            int r1i = r0_JSONObject.getInt(QsbkDatabase.TYPE);
            CharSequence r0_CharSequence = r0_JSONObject.getString(Constants.PARAM_SEND_MSG);
            if (r1i == 0) {
                if (a == null) {
                    a = Toast.makeText(r4_Context, r0_CharSequence, 0);
                } else {
                    a.setView(a.getView());
                    a.setText(r0_CharSequence);
                    a.setDuration(0);
                }
                a.show();
            } else {
                if (r1i == 1) {
                    if (a == null) {
                        a = Toast.makeText(r4_Context, r0_CharSequence, 1);
                    } else {
                        a.setView(a.getView());
                        a.setText(r0_CharSequence);
                        a.setDuration(1);
                    }
                    a.show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void d() {
        this.h.setVerticalScrollBarEnabled(false);
        this.h.setHorizontalScrollBarEnabled(false);
        this.h.setWebViewClient(new b(this, null));
        this.h.setWebChromeClient(new WebChromeClient());
        this.h.clearFormData();
        WebSettings r1_WebSettings = this.h.getSettings();
        r1_WebSettings.setSavePassword(false);
        r1_WebSettings.setSaveFormData(false);
        r1_WebSettings.setCacheMode(-1);
        r1_WebSettings.setNeedInitialFocus(false);
        r1_WebSettings.setBuiltInZoomControls(true);
        r1_WebSettings.setSupportZoom(true);
        r1_WebSettings.setRenderPriority(RenderPriority.HIGH);
        r1_WebSettings.setJavaScriptEnabled(true);
        if (i == null || i.get() == null) {
            r1_WebSettings.setDomStorageEnabled(true);
            this.h.addJavascriptInterface(new c(this, null), "sdk_js_if");
            this.h.clearView();
            this.h.loadUrl(this.c);
            this.h.getSettings().setSavePassword(false);
        } else {
            r1_WebSettings.setDatabaseEnabled(true);
            r1_WebSettings.setDatabasePath(((Context) i.get()).getApplicationContext().getDir("databases", 0).getPath());
            r1_WebSettings.setDomStorageEnabled(true);
            this.h.addJavascriptInterface(new c(this, null), "sdk_js_if");
            this.h.clearView();
            this.h.loadUrl(this.c);
            this.h.getSettings().setSavePassword(false);
        }
    }

    private static void d(Context r3_Context, String r4_String) {
        if (r3_Context == null || r4_String == null) {
        } else {
            try {
                JSONObject r0_JSONObject = Util.d(r4_String);
                int r1i = r0_JSONObject.getInt(QsbkDatabase.ACTION);
                r0_JSONObject.getString(Constants.PARAM_SEND_MSG);
                if (r1i == 1) {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void callJs(String r3_String, String r4_String) {
        this.h.loadUrl("javascript:" + r3_String + "(" + r4_String + ")");
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        requestWindowFeature(1);
        getWindow().setSoftInputMode(Base64.URL_SAFE);
        getWindow().setSoftInputMode(1);
        c();
        d();
    }

    public void onKeyboardHidden() {
        this.h.getLayoutParams().height = this.j;
        Log.e(b, "keyboard hide");
    }

    public void onKeyboardShown(int r3i) {
        if (i == null || i.get() == null) {
            Log.e(b, "keyboard show");
        } else {
            if (r3i >= this.j || 2 != ((Context) i.get()).getResources().getConfiguration().orientation) {
                this.h.getLayoutParams().height = this.j;
            } else {
                this.h.getLayoutParams().height = r3i;
            }
            Log.e(b, "keyboard show");
        }
    }
}