package com.androidquery.service;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.tauth.Constants;
import java.util.Locale;
import org.json.JSONObject;
import org.xml.sax.XMLReader;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.ProfileHeaderListView;

public class MarketService {
    public static final int MAJOR = 2;
    public static final int MINOR = 1;
    public static final int REVISION = 0;
    private static ApplicationInfo n;
    private static PackageInfo o;
    private Activity a;
    private AQuery b;
    private a c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private int h;
    private long i;
    private String j;
    private boolean k;
    private boolean l;
    private int m;

    private class a implements OnClickListener, TagHandler {
        private a() {
        }

        private void a_(String r3_String, JSONObject r4_JSONObject, AjaxStatus r5_AjaxStatus) {
            if (!MarketService.this.l) {
                MarketService.this.l = true;
                MarketService.this.h = 0;
                MarketService.this.a(r3_String, r4_JSONObject, r5_AjaxStatus);
            }
        }

        public void detailCb(String r4_String, String r5_String, AjaxStatus r6_AjaxStatus) {
            if (r5_String == null || r5_String.length() <= 1000) {
            } else {
                String r0_String = MarketService.this.d();
                AjaxCallback r1_AjaxCallback = new AjaxCallback();
                ((AjaxCallback) ((AjaxCallback) r1_AjaxCallback.url(r0_String)).type(JSONObject.class)).handler(this, "marketCb");
                r1_AjaxCallback.param("html", r5_String);
                ((AQuery) MarketService.this.b.progress(MarketService.this.h)).ajax(r1_AjaxCallback);
            }
        }

        public void handleTag(boolean r2z, String r3_String, Editable r4_Editable, XMLReader r5_XMLReader) {
            if ("li".equals(r3_String)) {
                if (r2z) {
                    r4_Editable.append("  ");
                    r4_Editable.append("\u2022");
                    r4_Editable.append("  ");
                } else {
                    r4_Editable.append("\n");
                }
            }
        }

        public void marketCb(String r4_String, JSONObject r5_JSONObject, AjaxStatus r6_AjaxStatus) {
            boolean r2z = true;
            if (MarketService.this.isFinishing()) {
            } else if (r5_JSONObject != null) {
                String r0_String = r5_JSONObject.optString(RMsgInfo.COL_STATUS);
                if ("1".equals(r0_String)) {
                    if (r5_JSONObject.has("dialog")) {
                        a(r4_String, r5_JSONObject, r6_AjaxStatus);
                    }
                    if (!MarketService.this.k && r5_JSONObject.optBoolean("fetch", false) && r6_AjaxStatus.getSource() == 1) {
                        MarketService.this.k = r2z;
                        r0_String = r5_JSONObject.optString("marketUrl", null);
                        AjaxCallback r1_AjaxCallback = new AjaxCallback();
                        ((AjaxCallback) ((AjaxCallback) r1_AjaxCallback.url(r0_String)).type(String.class)).handler(this, "detailCb");
                        ((AQuery) MarketService.this.b.progress(MarketService.this.h)).ajax(r1_AjaxCallback);
                    }
                } else if ("0".equals(r0_String)) {
                    r6_AjaxStatus.invalidate();
                } else {
                    a(r4_String, r5_JSONObject, r6_AjaxStatus);
                }
            } else {
                a(r4_String, r5_JSONObject, r6_AjaxStatus);
            }
        }

        public void onClick(DialogInterface r3_DialogInterface, int r4i) {
            switch (r4i) {
                case Constants.ERROR_URL:
                    MarketService.b(MarketService.this, MarketService.this.j);
                    break;
                case RequestListener.DEFAULT_LOADED_SIZE:
                    MarketService.b(MarketService.this, MarketService.this.f);
                    break;
                case ProfileHeaderListView.INVALID_TAB_ID:
                    MarketService.b(MarketService.this, MarketService.this.e);
                    break;
            }
        }
    }

    public MarketService(Activity r3_Activity) {
        this.i = 720000;
        this.m = 0;
        this.a = r3_Activity;
        this.b = new AQuery(r3_Activity);
        this.c = new a(null);
        this.d = Locale.getDefault().toString();
        this.e = i();
        this.f = this.e;
    }

    private ApplicationInfo a() {
        if (n == null) {
            n = this.a.getApplicationInfo();
        }
        return n;
    }

    private static String a(Context r3_Context) {
        return PreferenceManager.getDefaultSharedPreferences(r3_Context).getString("aqs.skip", null);
    }

    private static String a(String r2_String) {
        return new StringBuilder("<small>").append(r2_String).append("</small>").toString();
    }

    private boolean a(String r5_String, int r6i) {
        if (r5_String.equals(a(this.a))) {
            return false;
        }
        String r1_String = g();
        return (r1_String.equals(r5_String) || h() > r6i) ? false : a(r1_String, r5_String, this.m);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(String r7_String, String r8_String, int r9i) {
        /*
        r6_this = this;
        r5 = 3;
        r0 = 0;
        r1 = 1;
        r2 = r7.equals(r8);
        if (r2 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r2 = "\\.";
        r2 = r7.split(r2);	 //Catch:{ Exception -> 0x0059 }
        r3 = "\\.";
        r3 = r8.split(r3);	 //Catch:{ Exception -> 0x0059 }
        r4 = r2.length;	 //Catch:{ Exception -> 0x0059 }
        if (r4 < r5) goto L_0x001c;
    L_0x0019:
        r4 = r3.length;	 //Catch:{ Exception -> 0x0059 }
        if (r4 >= r5) goto L_0x001e;
    L_0x001c:
        r0 = r1;
        goto L_0x0009;
    L_0x001e:
        switch(r9) {
            case 0: goto L_0x0023;
            case 1: goto L_0x0035;
            case 2: goto L_0x0047;
            default: goto L_0x0021;
        };	 //Catch:{ Exception -> 0x0059 }
    L_0x0021:
        r0 = r1;
        goto L_0x0009;
    L_0x0023:
        r4 = r2.length;	 //Catch:{ Exception -> 0x0059 }
        r4 = r4 + -1;
        r4 = r2[r4];	 //Catch:{ Exception -> 0x0059 }
        r5 = r3.length;	 //Catch:{ Exception -> 0x0059 }
        r5 = r5 + -1;
        r5 = r3[r5];	 //Catch:{ Exception -> 0x0059 }
        r4 = r4.equals(r5);	 //Catch:{ Exception -> 0x0059 }
        if (r4 != 0) goto L_0x0035;
    L_0x0033:
        r0 = r1;
        goto L_0x0009;
    L_0x0035:
        r4 = r2.length;	 //Catch:{ Exception -> 0x0059 }
        r4 = r4 + -2;
        r4 = r2[r4];	 //Catch:{ Exception -> 0x0059 }
        r5 = r3.length;	 //Catch:{ Exception -> 0x0059 }
        r5 = r5 + -2;
        r5 = r3[r5];	 //Catch:{ Exception -> 0x0059 }
        r4 = r4.equals(r5);	 //Catch:{ Exception -> 0x0059 }
        if (r4 != 0) goto L_0x0047;
    L_0x0045:
        r0 = r1;
        goto L_0x0009;
    L_0x0047:
        r4 = r2.length;	 //Catch:{ Exception -> 0x0059 }
        r4 = r4 + -3;
        r2 = r2[r4];	 //Catch:{ Exception -> 0x0059 }
        r4 = r3.length;	 //Catch:{ Exception -> 0x0059 }
        r4 = r4 + -3;
        r3 = r3[r4];	 //Catch:{ Exception -> 0x0059 }
        r2 = r2.equals(r3);	 //Catch:{ Exception -> 0x0059 }
        if (r2 != 0) goto L_0x0009;
    L_0x0057:
        r0 = r1;
        goto L_0x0009;
    L_0x0059:
        r0 = move-exception;
        com.androidquery.util.AQUtility.report(r0);
        r0 = r1;
        goto L_0x0009;
        */

    }

    private PackageInfo b() {
        if (o == null) {
            try {
                o = this.a.getPackageManager().getPackageInfo(e(), 0);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    private static void b(Context r2_Context, String r3_String) {
        PreferenceManager.getDefaultSharedPreferences(r2_Context).edit().putString("aqs.skip", r3_String).commit();
    }

    private static boolean b(Activity r4_Activity, String r5_String) {
        if (r5_String == null) {
            return false;
        }
        try {
            r4_Activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(r5_String)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String c() {
        return "https://androidquery.appspot.com";
    }

    private String d() {
        String r0_String = new StringBuilder(String.valueOf(c())).append("/api/market?app=").append(e()).append("&locale=").append(this.d).append("&version=").append(g()).append("&code=").append(h()).append("&aq=").append(com.androidquery.util.Constants.VERSION).toString();
        return this.g ? new StringBuilder(String.valueOf(r0_String)).append("&force=true").toString() : r0_String;
    }

    private String e() {
        return a().packageName;
    }

    private Drawable f() {
        return a().loadIcon(this.a.getPackageManager());
    }

    private String g() {
        return b().versionName;
    }

    private int h() {
        return b().versionCode;
    }

    private String i() {
        return new StringBuilder("market://details?id=").append(e()).toString();
    }

    private boolean j() {
        return !this.a.isFinishing();
    }

    protected void a(String r6_String, JSONObject r7_JSONObject, AjaxStatus r8_AjaxStatus) {
        if (r7_JSONObject == null) {
        } else {
            String r0_String = r7_JSONObject.optString("version", "0");
            int r1i = r7_JSONObject.optInt("code", 0);
            AQUtility.debug("version", new StringBuilder(String.valueOf(g())).append("->").append(r0_String).append(":").append(h()).append("->").append(r1i).toString());
            AQUtility.debug("outdated", Boolean.valueOf(a(r0_String, r1i)));
            if (this.g || a(r0_String, r1i)) {
                a(r7_JSONObject);
            }
        }
    }

    protected void a(JSONObject r10_JSONObject) {
        if (r10_JSONObject != null && this.j == null && j()) {
            JSONObject r0_JSONObject = r10_JSONObject.optJSONObject("dialog");
            CharSequence r1_CharSequence = r0_JSONObject.optString(KEYS.UPDATEINFO, "Update");
            CharSequence r2_CharSequence = r0_JSONObject.optString("skip", "Skip");
            CharSequence r3_CharSequence = r0_JSONObject.optString("rate", "Rate");
            String r4_String = r0_JSONObject.optString("wbody", RContactStorage.PRIMARY_KEY);
            CharSequence r0_CharSequence = r0_JSONObject.optString(Constants.PARAM_TITLE, "Update Available");
            AQUtility.debug("wbody", r4_String);
            this.j = r10_JSONObject.optString("version", null);
            Dialog r0_Dialog = new Builder(this.a).setIcon(f()).setTitle(r0_CharSequence).setPositiveButton(r3_CharSequence, this.c).setNeutralButton(r2_CharSequence, this.c).setNegativeButton(r1_CharSequence, this.c).create();
            r0_Dialog.setMessage(Html.fromHtml(a(r4_String), null, this.c));
            this.b.show(r0_Dialog);
        }
    }

    public void checkVersion() {
        String r0_String = d();
        AjaxCallback r2_AjaxCallback = new AjaxCallback();
        ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) r2_AjaxCallback.url(r0_String)).type(JSONObject.class)).handler(this.c, "marketCb")).fileCache(!(this.g))).expire(this.i);
        ((AQuery) this.b.progress(this.h)).ajax(r2_AjaxCallback);
    }

    public MarketService expire(long r1j) {
        this.i = r1j;
        return this;
    }

    public MarketService force(boolean r1z) {
        this.g = r1z;
        return this;
    }

    public MarketService level(int r1i) {
        this.m = r1i;
        return this;
    }

    public MarketService locale(String r1_String) {
        this.d = r1_String;
        return this;
    }

    public MarketService progress(int r1i) {
        this.h = r1i;
        return this;
    }

    public MarketService rateUrl(String r1_String) {
        this.e = r1_String;
        return this;
    }

    public MarketService updateUrl(String r1_String) {
        this.f = r1_String;
        return this;
    }
}