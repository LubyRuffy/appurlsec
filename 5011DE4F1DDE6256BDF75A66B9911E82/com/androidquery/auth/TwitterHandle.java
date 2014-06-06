package com.androidquery.auth;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.androidquery.AQuery;
import com.androidquery.WebDialog;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import java.net.HttpURLConnection;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import org.apache.http.HttpRequest;
import qsbk.app.database.QsbkDatabase;

public class TwitterHandle extends AccountHandle {
    private Activity a;
    private WebDialog b;
    private CommonsHttpOAuthConsumer c;
    private CommonsHttpOAuthProvider d;
    private String e;
    private String f;

    private class a extends AsyncTask<String, String, String> implements OnCancelListener, Runnable {
        private AbstractAjaxCallback<?, ?> b;

        private a() {
        }

        protected String a_(String ... r4_StringA) {
            try {
                return TwitterHandle.this.d.retrieveRequestToken(TwitterHandle.this.c, "twitter://callback");
            } catch (Exception e) {
                AQUtility.report(e);
                return null;
            }
        }

        protected void a_(String r7_String) {
            if (r7_String != null) {
                TwitterHandle.this.b = new WebDialog(TwitterHandle.this, r7_String, new c(null));
                TwitterHandle.this.b.setOnCancelListener(this);
                TwitterHandle.this.c();
                TwitterHandle.this.b.load();
            } else {
                TwitterHandle.this.d();
            }
        }

        protected /* synthetic */ Object doInBackground(Object ... r2_ObjectA) {
            return a((String[]) r2_ObjectA);
        }

        public void onCancel(DialogInterface r2_DialogInterface) {
            TwitterHandle.this.d();
        }

        protected /* synthetic */ void onPostExecute(Object r1_Object) {
            a((String) r1_Object);
        }

        public void run() {
            TwitterHandle.this.auth(this.b);
        }
    }

    private class b extends AsyncTask<String, String, String> {
        private b() {
        }

        protected String a(String ... r4_StringA) {
            try {
                TwitterHandle.this.d.retrieveAccessToken(TwitterHandle.this.c, r4_StringA[0]);
                return RContactStorage.PRIMARY_KEY;
            } catch (Exception e) {
                AQUtility.report(e);
                return null;
            }
        }

        protected void a(String r6_String) {
            if (r6_String != null) {
                TwitterHandle.this.e = TwitterHandle.this.c.getToken();
                TwitterHandle.this.f = TwitterHandle.this.c.getTokenSecret();
                AQUtility.debug(QsbkDatabase.TOKEN, TwitterHandle.this.e);
                AQUtility.debug(OAuth.SECRET, TwitterHandle.this.f);
                TwitterHandle.this.a("aq.tw.token", TwitterHandle.this.e, "aq.tw.secret", TwitterHandle.this.f);
                TwitterHandle.this.b();
                TwitterHandle.this.a(TwitterHandle.this);
                TwitterHandle.this.a(TwitterHandle.this.f, TwitterHandle.this.e);
            } else {
                TwitterHandle.this.d();
                TwitterHandle.this.a(null, null);
            }
        }

        protected /* synthetic */ Object doInBackground(Object ... r2_ObjectA) {
            return a((String[]) r2_ObjectA);
        }

        protected /* synthetic */ void onPostExecute(Object r1_Object) {
            a((String) r1_Object);
        }
    }

    private class c extends WebViewClient {
        private c() {
        }

        private boolean a(String r7_String) {
            if (r7_String.startsWith("twitter://callback")) {
                String r2_String = TwitterHandle.this.b(r7_String, "oauth_verifier");
                TwitterHandle.this.b();
                b r3_b = new b(null);
                String[] r4_StringA = new String[1];
                r4_StringA[0] = r2_String;
                r3_b.execute(r4_StringA);
                return true;
            } else {
                if (!r7_String.startsWith("twitter://cancel")) {
                    return false;
                }
                TwitterHandle.this.d();
                return true;
            }
        }

        public void onPageFinished(WebView r2_WebView, String r3_String) {
            AQUtility.debug("finished", r3_String);
            super.onPageFinished(r2_WebView, r3_String);
            TwitterHandle.this.c();
        }

        public void onPageStarted(WebView r2_WebView, String r3_String, Bitmap r4_Bitmap) {
            AQUtility.debug("started", r3_String);
            if (!a(r3_String)) {
                super.onPageStarted(r2_WebView, r3_String, r4_Bitmap);
            }
        }

        public void onReceivedError(WebView r2_WebView, int r3i, String r4_String, String r5_String) {
            TwitterHandle.this.d();
        }

        public boolean shouldOverrideUrlLoading(WebView r2_WebView, String r3_String) {
            return a(r3_String);
        }
    }

    public TwitterHandle(Activity r5_Activity, String r6_String, String r7_String) {
        this.a = r5_Activity;
        this.c = new CommonsHttpOAuthConsumer(r6_String, r7_String);
        this.e = a("aq.tw.token");
        this.f = a("aq.tw.secret");
        if (this.e == null || this.f == null) {
            this.d = new CommonsHttpOAuthProvider("https://api.twitter.com/oauth/request_token", "https://api.twitter.com/oauth/access_token", "https://api.twitter.com/oauth/authorize");
        } else {
            this.c.setTokenWithSecret(this.e, this.f);
            this.d = new CommonsHttpOAuthProvider("https://api.twitter.com/oauth/request_token", "https://api.twitter.com/oauth/access_token", "https://api.twitter.com/oauth/authorize");
        }
    }

    private String a(String r3_String) {
        return PreferenceManager.getDefaultSharedPreferences(this.a).getString(r3_String, null);
    }

    private void a(String r2_String, String r3_String, String r4_String, String r5_String) {
        PreferenceManager.getDefaultSharedPreferences(this.a).edit().putString(r2_String, r3_String).putString(r4_String, r5_String).commit();
    }

    private String b(String r2_String, String r3_String) {
        return Uri.parse(r2_String).getQueryParameter(r3_String);
    }

    private void b() {
        if (this.b != null) {
            new AQuery(this.a).dismiss(this.b);
            this.b = null;
        }
    }

    private void c() {
        if (this.b != null) {
            new AQuery(this.a).show(this.b);
        }
    }

    private void d() {
        b();
        a(this.a, 401, "cancel");
    }

    protected void a() {
        new a(null).execute(new String[0]);
    }

    protected void a(String r1_String, String r2_String) {
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r4_AbstractAjaxCallback___, HttpURLConnection r5_HttpURLConnection) {
        AQUtility.debug("apply token multipart", r4_AbstractAjaxCallback___.getUrl());
        DefaultOAuthConsumer r0_DefaultOAuthConsumer = new DefaultOAuthConsumer(this.c.getConsumerKey(), this.c.getConsumerSecret());
        r0_DefaultOAuthConsumer.setTokenWithSecret(this.c.getToken(), this.c.getTokenSecret());
        try {
            r0_DefaultOAuthConsumer.sign(r5_HttpURLConnection);
        } catch (Exception e) {
            AQUtility.report(e);
        }
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r3_AbstractAjaxCallback___, HttpRequest r4_HttpRequest) {
        AQUtility.debug("apply token", r3_AbstractAjaxCallback___.getUrl());
        try {
            this.c.sign(r4_HttpRequest);
        } catch (Exception e) {
            AQUtility.report(e);
        }
    }

    public void authenticate(boolean r3z) {
        if (r3z || this.e == null || this.f == null) {
            a();
        } else {
            a(this.f, this.e);
        }
    }

    public boolean authenticated() {
        return this.e != null && this.f != null;
    }

    public boolean expired(AbstractAjaxCallback<?, ?> r3_AbstractAjaxCallback___, AjaxStatus r4_AjaxStatus) {
        int r0i = r4_AjaxStatus.getCode();
        return r0i == 400 || r0i == 401;
    }

    public String getSecret() {
        return this.f;
    }

    public String getToken() {
        return this.e;
    }

    public boolean reauth(AbstractAjaxCallback<?, ?> r4_AbstractAjaxCallback___) {
        this.e = null;
        this.f = null;
        a("aq.tw.token", null, "aq.tw.secret", null);
        new a(null).b = r4_AbstractAjaxCallback___;
        AQUtility.post(r4_AbstractAjaxCallback___);
        return false;
    }

    public void unauth() {
        this.e = null;
        this.f = null;
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().removeAllCookie();
        a("aq.tw.token", null, "aq.tw.secret", null);
    }
}