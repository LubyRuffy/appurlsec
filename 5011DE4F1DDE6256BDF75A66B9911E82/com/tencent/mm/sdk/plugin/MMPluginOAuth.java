package com.tencent.mm.sdk.plugin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.androidquery.util.Constants;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import java.util.HashMap;
import java.util.Map;

public class MMPluginOAuth {
    private final IResult a;
    private final Context b;
    private String c;
    private String d;
    private Handler e;

    public static interface IResult {
        public void onResult(MMPluginOAuth r1_MMPluginOAuth);

        public void onSessionTimeOut();
    }

    public static class Receiver extends BroadcastReceiver {
        private static final Map<String, MMPluginOAuth> a;
        private final MMPluginOAuth b;

        static {
            a = new HashMap();
        }

        public Receiver() {
            this(null);
        }

        public Receiver(MMPluginOAuth r1_MMPluginOAuth) {
            this.b = r1_MMPluginOAuth;
        }

        public static void register(String r1_String, MMPluginOAuth r2_MMPluginOAuth) {
            a.put(r1_String, r2_MMPluginOAuth);
        }

        public static void unregister(String r1_String) {
            a.remove(r1_String);
        }

        public void onReceive(Context r5_Context, Intent r6_Intent) {
            MMPluginOAuth r0_MMPluginOAuth;
            Log.d("MicroMsg.SDK.MMPluginOAuth", "receive oauth result");
            String r1_String = r6_Intent.getStringExtra(PluginIntent.REQUEST_TOKEN);
            String r2_String = r6_Intent.getStringExtra(PluginIntent.ACCESS_TOKEN);
            if (this.b != null) {
                r0_MMPluginOAuth = this.b;
            } else {
                r0_MMPluginOAuth = (MMPluginOAuth) a.get(r1_String);
                if (r0_MMPluginOAuth == null) {
                    Log.e("MicroMsg.SDK.MMPluginOAuth", new StringBuilder("oauth unregistered, request token = ").append(r1_String).toString());
                    return;
                } else {
                    unregister(r0_MMPluginOAuth.d);
                }
            }
            new Handler().post(new b(this, r0_MMPluginOAuth, r2_String));
        }
    }

    public MMPluginOAuth(Context r1_Context, IResult r2_IResult) {
        this.b = r1_Context;
        this.a = r2_IResult;
    }

    static /* synthetic */ void a(MMPluginOAuth r3_MMPluginOAuth, String r4_String) {
        Receiver.unregister(r3_MMPluginOAuth.d);
        r3_MMPluginOAuth.c = r4_String;
        Log.i("MicroMsg.SDK.MMPluginOAuth", new StringBuilder("access token: ").append(r4_String).toString());
        if (r3_MMPluginOAuth.a != null) {
            r3_MMPluginOAuth.a.onResult(r3_MMPluginOAuth);
        }
    }

    public String getAccessToken() {
        return this.c;
    }

    public String getRequestToken() {
        return this.d;
    }

    public void start() {
        start(null);
    }

    public boolean start(Handler r10_Handler) {
        if (r10_Handler == null) {
            r10_Handler = new Handler();
        }
        this.e = r10_Handler;
        ContentResolver r0_ContentResolver = this.b.getContentResolver();
        Uri r1_Uri = OAuth.CONTENT_URI;
        String[] r4_StringA = new String[2];
        r4_StringA[0] = this.b.getPackageName();
        r4_StringA[1] = OAuth.ACTION_REQUEST_TOKEN;
        Cursor r0_Cursor = r0_ContentResolver.query(r1_Uri, null, null, r4_StringA, null);
        if (r0_Cursor != null) {
            if ((!r0_Cursor.moveToFirst()) || r0_Cursor.getColumnCount() < 2) {
                r0_Cursor.close();
            } else {
                this.d = r0_Cursor.getString(0);
                this.c = r0_Cursor.getString(1);
                r0_Cursor.close();
            }
        }
        Log.i("MicroMsg.SDK.MMPluginOAuth", new StringBuilder("request token = ").append(this.d).toString());
        if (this.d == null) {
            Log.e("MicroMsg.SDK.MMPluginOAuth", "request token failed");
            return false;
        } else if (this.c != null) {
            this.e.post(new a(this));
            return true;
        } else {
            int r0i;
            Log.d("MicroMsg.SDK.MMPluginOAuth", "begin to show user oauth page");
            Intent r0_Intent = new Intent();
            r0_Intent.setClassName(PluginIntent.APP_PACKAGE_PATTERN, "com.tencent.mm.plugin.PluginOAuthUI");
            r0_Intent.putExtra(PluginIntent.REQUEST_TOKEN, this.d);
            r0_Intent.putExtra(PluginIntent.PACKAGE, this.b.getPackageName());
            if (this.b.getPackageManager().resolveActivity(r0_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION) == null) {
                Log.e("MicroMsg.SDK.MMPluginOAuth", "show oauth page failed, activity not found");
                r0i = 0;
            } else {
                if (!this.b instanceof Activity) {
                    r0_Intent.setFlags(268435456);
                }
                this.b.startActivity(r0_Intent);
                r0i = 1;
            }
            if (r0i == 0) {
                return false;
            }
            Receiver.register(this.d, this);
            return true;
        }
    }
}