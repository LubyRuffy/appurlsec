package com.androidquery.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.androidquery.AQuery;
import com.androidquery.WebDialog;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.baidu.location.BDLocation;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.ThirdParty;

public class FacebookHandle extends AccountHandle {
    public static final String FB_APP_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    private static Boolean j;
    private String a;
    private Activity b;
    private WebDialog c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private int i;

    private class a extends WebViewClient implements OnCancelListener {
        private a() {
        }

        private boolean a_(String r6_String) {
            boolean r1z = false;
            if (r6_String.startsWith("https://www.facebook.com/connect/login_success.html")) {
                String r2_String = FacebookHandle.f(r6_String).getString("error_reason");
                AQUtility.debug(QQDialogAuthorizeActivity.ERROR_RET, r2_String);
                if (r2_String == null) {
                    FacebookHandle.this.d = FacebookHandle.this.d(r6_String);
                }
                if (FacebookHandle.this.d != null) {
                    FacebookHandle.this.b();
                    FacebookHandle.this.b(FacebookHandle.this.d, FacebookHandle.this.e);
                    FacebookHandle.this.g = r1z;
                    FacebookHandle.this.a(FacebookHandle.this.d);
                    FacebookHandle.this.a(FacebookHandle.this.b);
                    return true;
                } else {
                    FacebookHandle.this.e();
                    return true;
                }
            } else {
                if (!r6_String.startsWith("fbconnect:cancel")) {
                    return false;
                }
                AQUtility.debug((Object)"cancelled");
                FacebookHandle.this.e();
                return true;
            }
        }

        public void onCancel(DialogInterface r2_DialogInterface) {
            FacebookHandle.this.e();
        }

        public void onPageFinished(WebView r2_WebView, String r3_String) {
            super.onPageFinished(r2_WebView, r3_String);
            FacebookHandle.this.c();
            AQUtility.debug("finished", r3_String);
        }

        public void onPageStarted(WebView r2_WebView, String r3_String, Bitmap r4_Bitmap) {
            AQUtility.debug("started", r3_String);
            if (!a(r3_String)) {
                super.onPageStarted(r2_WebView, r3_String, r4_Bitmap);
            }
        }

        public void onReceivedError(WebView r2_WebView, int r3i, String r4_String, String r5_String) {
            FacebookHandle.this.e();
        }

        public boolean shouldOverrideUrlLoading(WebView r3_WebView, String r4_String) {
            AQUtility.debug(new StringBuilder("return url: ").append(r4_String).toString());
            return a(r4_String);
        }
    }

    public FacebookHandle(Activity r2_Activity, String r3_String, String r4_String) {
        this(r2_Activity, r3_String, r4_String, null);
    }

    public FacebookHandle(Activity r2_Activity, String r3_String, String r4_String, String r5_String) {
        this.a = r3_String;
        this.b = r2_Activity;
        this.e = r4_String;
        this.d = r5_String;
        if (this.d == null && a(r4_String, i())) {
            this.d = h();
            this.g = this.d == null;
        } else {
            if (this.d == null) {
            }
            this.g = this.d == null;
        }
    }

    private static String a(Bundle r6_Bundle) {
        if (r6_Bundle == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r2_StringBuilder = new StringBuilder();
        Iterator r3_Iterator = r6_Bundle.keySet().iterator();
        int r1i = 1;
        while (r3_Iterator.hasNext()) {
            String r0_String = (String) r3_Iterator.next();
            if (r1i != 0) {
                r1i = 0;
            } else {
                r2_StringBuilder.append("&");
            }
            r2_StringBuilder.append(new StringBuilder(String.valueOf(r0_String)).append("=").append(r6_Bundle.getString(r0_String)).toString());
        }
        return r2_StringBuilder.toString();
    }

    private boolean a(Activity r6_Activity, String r7_String, String r8_String, int r9i) {
        boolean r0z = true;
        Intent r2_Intent = new Intent();
        r2_Intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        r2_Intent.putExtra(QQDialogAuthorizeActivity.CLIENT_ID, r7_String);
        if (r8_String != null) {
            r2_Intent.putExtra(QQDialogAuthorizeActivity.SCOPE, r8_String);
        }
        if (!a((Context)r6_Activity, r2_Intent)) {
            return false;
        }
        try {
            r6_Activity.startActivityForResult(r2_Intent, r9i);
        } catch (ActivityNotFoundException e) {
            r0z = false;
        }
        return r0z;
    }

    private boolean a(Context r7_Context, Intent r8_Intent) {
        PackageManager r1_PackageManager = r7_Context.getPackageManager();
        ResolveInfo r2_ResolveInfo = r1_PackageManager.resolveActivity(r8_Intent, 0);
        if (r2_ResolveInfo == null) {
            return false;
        }
        try {
            Signature[] r2_SignatureA = r1_PackageManager.getPackageInfo(r2_ResolveInfo.activityInfo.packageName, RContact.MM_CONTACTFLAG_FAVOURCONTACT).signatures;
            int r3i = r2_SignatureA.length;
            int r1i = 0;
            while (r1i < r3i) {
                if (r2_SignatureA[r1i].toCharsString().equals(FB_APP_SIGNATURE)) {
                    return true;
                }
                r1i++;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private boolean a(String r7_String, String r8_String) {
        if (r7_String == null) {
            return true;
        }
        if (r8_String == null) {
            return false;
        }
        Set r3_Set = new HashSet(Arrays.asList(r8_String.split("[,\\s]+")));
        String[] r4_StringA = r7_String.split("[,\\s]+");
        int r0i = 0;
        while (r0i < r4_StringA.length) {
            if (r3_Set.contains(r4_StringA[r0i])) {
                r0i++;
            } else {
                AQUtility.debug((Object)"perm mismatch");
                return false;
            }
        }
        return true;
    }

    private void b() {
        if (this.c != null) {
            new AQuery(this.b).dismiss(this.c);
            this.c = null;
        }
    }

    private void b(String r4_String, String r5_String) {
        Editor r0_Editor = PreferenceManager.getDefaultSharedPreferences(this.b).edit();
        r0_Editor.putString("aq.fb.token", r4_String).putString("aq.fb.permission", r5_String);
        AQUtility.apply(r0_Editor);
    }

    private void c() {
        if (this.c != null) {
            new AQuery(this.b).show(this.c);
        }
    }

    private void c(String r3_String) {
        b();
        a(this.b, AjaxStatus.AUTH_ERROR, r3_String);
    }

    private String d(String r3_String) {
        String r0_String = Uri.parse(r3_String.replace('#', '?')).getQueryParameter(ThirdParty.KEY_TOKEN);
        AQUtility.debug(QsbkDatabase.TOKEN, r0_String);
        return r0_String;
    }

    private void d() {
        if (this.c != null) {
            try {
                this.c.hide();
            } catch (Exception e) {
                AQUtility.debug(e);
            }
        }
    }

    private static Bundle e(String r8_String) {
        Bundle r2_Bundle = new Bundle();
        if (r8_String != null) {
            String[] r3_StringA = r8_String.split("&");
            int r4i = r3_StringA.length;
            int r0i = 0;
            while (r0i < r4i) {
                String[] r5_StringA = r3_StringA[r0i].split("=");
                r2_Bundle.putString(r5_StringA[0], r5_StringA[1]);
                r0i++;
            }
        }
        return r2_Bundle;
    }

    private void e() {
        c("cancel");
    }

    private static Bundle f(String r2_String) {
        try {
            URL r1_URL = new URL(r2_String);
            Bundle r0_Bundle = e(r1_URL.getQuery());
            r0_Bundle.putAll(e(r1_URL.getRef()));
            return r0_Bundle;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    private boolean f() {
        return this.h ? a(this.b, this.a, this.e, this.i) : false;
    }

    private void g() {
        AQUtility.debug((Object)"web auth");
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putString(QQDialogAuthorizeActivity.CLIENT_ID, this.a);
        r0_Bundle.putString(QsbkDatabase.TYPE, "user_agent");
        if (this.e != null) {
            r0_Bundle.putString(QQDialogAuthorizeActivity.SCOPE, this.e);
        }
        r0_Bundle.putString("redirect_uri", "https://www.facebook.com/connect/login_success.html");
        String r0_String = new StringBuilder("https://graph.facebook.com/oauth/authorize?").append(a(r0_Bundle)).toString();
        Object r1_Object = new a(null);
        this.c = new WebDialog(this.b, r0_String, r1_Object);
        this.c.setLoadingMessage(this.f);
        this.c.setOnCancelListener(r1_Object);
        c();
        if (this.g && this.d == null) {
            this.c.load();
            AQUtility.debug((Object)"auth started");
        } else {
            AQUtility.debug((Object)"auth hide");
            d();
            this.c.load();
            AQUtility.debug((Object)"auth started");
        }
    }

    public static String getToken(Context r3_Context) {
        return PreferenceManager.getDefaultSharedPreferences(r3_Context).getString("aq.fb.token", null);
    }

    private String h() {
        return PreferenceManager.getDefaultSharedPreferences(this.b).getString("aq.fb.token", null);
    }

    private String i() {
        return PreferenceManager.getDefaultSharedPreferences(this.b).getString("aq.fb.permission", null);
    }

    protected void a() {
        if (this.b.isFinishing()) {
        } else {
            boolean r0z = f();
            AQUtility.debug("authing", Boolean.valueOf(r0z));
            if (!r0z) {
                g();
            }
        }
    }

    protected void a(String r1_String) {
    }

    public void ajaxProfile(AjaxCallback<JSONObject> r3_AjaxCallback_JSONObject) {
        ajaxProfile(r3_AjaxCallback_JSONObject, 0);
    }

    public void ajaxProfile(AjaxCallback<JSONObject> r7_AjaxCallback_JSONObject, long r8j) {
        ((AQuery) new AQuery(this.b).auth(this)).ajax("https://graph.facebook.com/me", JSONObject.class, r8j, r7_AjaxCallback_JSONObject);
    }

    public boolean authenticated() {
        return this.d != null;
    }

    public boolean expired(AbstractAjaxCallback<?, ?> r8_AbstractAjaxCallback___, AjaxStatus r9_AjaxStatus) {
        int r2i = r9_AjaxStatus.getCode();
        if (r2i == 200) {
            return false;
        }
        String r3_String = r9_AjaxStatus.getError();
        if (r3_String == null || (!r3_String.contains("OAuthException"))) {
            r3_String = r8_AbstractAjaxCallback___.getUrl();
            if (r2i == 400) {
                if (r3_String.endsWith("/likes") || r3_String.endsWith("/comments") || r3_String.endsWith("/checkins")) {
                    return false;
                }
            }
            if (r2i == 403) {
                if (r3_String.endsWith("/feed") || r3_String.contains("method=delete")) {
                    return false;
                }
            }
            return r2i == 400 || r2i == 401 || r2i == 403;
        } else {
            AQUtility.debug((Object)"fb token expired");
            return true;
        }
    }

    public String getCacheUrl(String r2_String) {
        return getNetworkUrl(r2_String);
    }

    public String getNetworkUrl(String r3_String) {
        return new StringBuilder(String.valueOf(r3_String.indexOf(BDLocation.TypeNetWorkException) == -1 ? new StringBuilder(String.valueOf(r3_String)).append("?").toString() : new StringBuilder(String.valueOf(r3_String)).append("&").toString())).append("access_token=").append(this.d).toString();
    }

    public String getToken() {
        return this.d;
    }

    public boolean isSSOAvailable() {
        if (j == null) {
            Intent r0_Intent = new Intent();
            r0_Intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
            j = Boolean.valueOf(a(this.b, r0_Intent));
        }
        return j.booleanValue();
    }

    public FacebookHandle message(String r1_String) {
        this.f = r1_String;
        return this;
    }

    public void onActivityResult(int r3i, int r4i, Intent r5_Intent) {
        AQUtility.debug("on result", Integer.valueOf(r4i));
        if (r4i == -1) {
            String r0_String = r5_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_RET);
            if (r0_String == null) {
                r0_String = r5_Intent.getStringExtra("error_type");
            }
            if (r0_String != null) {
                AQUtility.debug(QQDialogAuthorizeActivity.ERROR_RET, r0_String);
                if (r0_String.equals("service_disabled") || r0_String.equals("AndroidAuthKillSwitchException")) {
                    g();
                } else {
                    r0_String = r5_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_DES);
                    AQUtility.debug("fb error", r0_String);
                    Log.e("fb error", r0_String);
                    c(r0_String);
                }
            } else {
                this.d = r5_Intent.getStringExtra(ThirdParty.KEY_TOKEN);
                AQUtility.debug("onComplete", this.d);
                if (this.d != null) {
                    b(this.d, this.e);
                    this.g = false;
                    a(this.d);
                    a(this.b);
                } else {
                    e();
                }
            }
        } else {
            if (r4i == 0) {
                e();
            }
        }
    }

    public boolean reauth(AbstractAjaxCallback<?, ?> r2_AbstractAjaxCallback___) {
        AQUtility.debug((Object)"reauth requested");
        this.d = null;
        AQUtility.post(new a(this, r2_AbstractAjaxCallback___));
        return false;
    }

    public FacebookHandle setLoadingMessage(int r2i) {
        this.f = this.b.getString(r2i);
        return this;
    }

    public FacebookHandle sso(int r2i) {
        this.h = true;
        this.i = r2i;
        return this;
    }

    public void unauth() {
        this.d = null;
        CookieSyncManager.createInstance(this.b);
        CookieManager.getInstance().removeAllCookie();
        b(null, null);
    }
}