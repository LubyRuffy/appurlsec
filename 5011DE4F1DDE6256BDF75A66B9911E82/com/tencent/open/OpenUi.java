package com.tencent.open;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.plus.ImageActivity;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.apache.cordova.NetworkManager;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.share.ShareUtils;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
public class OpenUi {
    private TContext a;
    private int b;
    private SparseArray c;

    public OpenUi(TContext r2_TContext) {
        this.b = 5656;
        this.c = new SparseArray();
        this.a = r2_TContext;
    }

    private int a(String r9_String, String r10_String) {
        int r0i = -1;
        String[] r4_StringA = r9_String.split("\\.");
        String[] r5_StringA = r10_String.split("\\.");
        int r3i = 0;
        while (true) {
            try {
                if (r3i >= r4_StringA.length || r3i >= r5_StringA.length) {
                    if (r4_StringA.length > r3i) {
                        r0i = 1;
                        return r0i;
                    } else {
                        if (r5_StringA.length <= r3i) {
                            r0i = 0;
                        }
                        return r0i;
                    }
                } else {
                    int r6i = Integer.parseInt(r4_StringA[r3i]);
                    int r7i = Integer.parseInt(r5_StringA[r3i]);
                    if (r6i < r7i) {
                        return r0i;
                    }
                    if (r6i > r7i) {
                        r0i = 1;
                        return r0i;
                    } else {
                        r3i++;
                    }
                }
            } catch (Exception e) {
                r0i = 0;
            }
        }
    }

    private Intent a(Context r4_Context, String r5_String) {
        Intent r0_Intent = new Intent();
        if (Constants.ACTION_AVATAR.equals(r5_String)) {
            r0_Intent.setClass(r4_Context, ImageActivity.class);
            return r0_Intent;
        } else {
            r0_Intent.setClassName(Constants.PACKAGE_AGENT, "com.tencent.open.agent.AgentActivity");
            r0_Intent.putExtra("key_request_code", b());
            return b(r4_Context, r0_Intent) ? r0_Intent : null;
        }
    }

    private Intent a(Context r5_Context, String r6_String, String r7_String) {
        Intent r1_Intent;
        try {
            r1_Intent = new Intent("android.intent.action.VIEW", Uri.parse(r7_String));
        } catch (Exception e) {
            e.printStackTrace();
            r1_Intent = null;
        }
        if (r1_Intent == null) {
            return null;
        }
        r1_Intent.putExtra("key_request_code", b());
        return a(r5_Context, r1_Intent) ? r1_Intent : null;
    }

    private Bundle a(String r5_String, Bundle r6_Bundle) {
        Bundle r1_Bundle = new Bundle(r6_Bundle);
        if (Constants.ACTION_LOGIN.equals(r5_String) || Constants.ACTION_PAY.equals(r5_String)) {
            if (this.a != null) {
                r1_Bundle.putString(QQDialogAuthorizeActivity.CLIENT_ID, this.a.d());
                r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
                r1_Bundle.putString("need_pay", "1");
            }
            return r1_Bundle;
        } else {
            if (this.a != null) {
                r1_Bundle.putString(Constants.PARAM_CONSUMER_KEY, this.a.d());
                if (this.a.a()) {
                    r1_Bundle.putString(ThirdParty.KEY_TOKEN, this.a.b());
                }
                String r0_String = this.a.c();
                if (r0_String != null) {
                    r1_Bundle.putString(Constants.PARAM_OPEN_ID, r0_String);
                }
                try {
                    r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, this.a.f().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, "openmobile_android"));
                } catch (Exception e) {
                    e.printStackTrace();
                    r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
                }
            }
            return r1_Bundle;
        }
    }

    private boolean a(Context r3_Context, Intent r4_Intent) {
        return r3_Context.getPackageManager().resolveActivity(r4_Intent, 0) != null;
    }

    private int b() {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.OpenUi.b():int");
        /* JADX: method processing error */
/*
        Error: java.lang.StackOverflowError: Deep code hierarchy
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:23)
	at jadx.api.Decompiler.processClass(Decompiler.java:185)
	at jadx.api.JavaClass.decompile(JavaClass.java:46)
	at jadx.api.Decompiler$1.run(Decompiler.java:119)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:895)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:918)
	at java.lang.Thread.run(Thread.java:695)
*/
        /*
        private int b() {
            r3_this = this;
        L_0x0000:
            r0 = r3.b;
            r0 = r0 + 1;
            r3.b = r0;
            r0 = r3.b;
            r1 = 6656; // 0x1a00 float:9.327E-42 double:3.2885E-320;
            if (r0 != r1) goto L_0x0010;
        L_0x000c:
            r0 = 5656; // 0x1618 float:7.926E-42 double:2.7944E-320;
            r3.b = r0;
        L_0x0010:
            r0 = r3.c;
            r1 = r3.b;
            r0 = r0.get(r1);
            r0 = (com.tencent.open.w) r0;
            r1 = r3.c;
            r2 = r3.b;
            r1.remove(r2);
            if (r0 == 0) goto L_0x002c;
        L_0x0023:
            r1 = r0.a;
            if (r1 == 0) goto L_0x002c;
        L_0x0027:
            r1 = r0.a;
            r1.onCancel();
        L_0x002c:
            if (r0 != 0) goto L_0x0000;
        L_0x002e:
            r0 = r3.b;
            return r0;
        }
        */
    }

    private Bundle b(String r5_String, Bundle r6_Bundle) {
        Bundle r1_Bundle = new Bundle(r6_Bundle);
        if (Constants.ACTION_STORY.equals(r5_String) && r1_Bundle.containsKey(Constants.PARAM_SHARE_URL)) {
            r1_Bundle.putString(Constants.PARAM_URL, r1_Bundle.getString(Constants.PARAM_SHARE_URL));
        } else if (Constants.ACTION_PAY.equals(r5_String)) {
            if (this.a != null) {
                r1_Bundle.putString(Constants.PARAM_CONSUMER_KEY, this.a.d());
                r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
                r1_Bundle.putString("need_pay", "1");
                r0_String = this.a.c();
                if (r0_String != null) {
                    r1_Bundle.putString(Constants.PARAM_HOPEN_ID, r0_String);
                } else {
                    r1_Bundle.putString(Constants.PARAM_HOPEN_ID, RContactStorage.PRIMARY_KEY);
                }
            }
        } else if (this.a != null) {
            r1_Bundle.putString(Constants.PARAM_APP_ID, this.a.d());
            if (this.a.a()) {
                r1_Bundle.putString(Constants.PARAM_KEY_STR, this.a.b());
                r1_Bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
            }
            r0_String = this.a.c();
            if (r0_String != null) {
                r1_Bundle.putString(Constants.PARAM_HOPEN_ID, r0_String);
            }
            r1_Bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
            try {
                r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, this.a.f().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, "openmobile_android"));
            } catch (Exception e) {
                e.printStackTrace();
                r1_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
            }
        }
        return r1_Bundle;
    }

    private boolean b(Context r3_Context, Intent r4_Intent) {
        ResolveInfo r1_ResolveInfo = r3_Context.getPackageManager().resolveActivity(r4_Intent, 0);
        return r1_ResolveInfo == null ? false : b(r3_Context, r1_ResolveInfo.activityInfo.packageName);
    }

    private boolean b(Context r7_Context, String r8_String) {
        try {
            Signature[] r2_SignatureA = r7_Context.getPackageManager().getPackageInfo(r8_String, RContact.MM_CONTACTFLAG_FAVOURCONTACT).signatures;
            int r3i = r2_SignatureA.length;
            int r1i = 0;
            while (r1i < r3i) {
                if (Util.f(r2_SignatureA[r1i].toCharsString()).equals("ec96e9ac1149251acbb1b0c5777cae95")) {
                    return true;
                }
                r1i++;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private String c(String r6_String, Bundle r7_Bundle) {
        r7_Bundle.putString("display", NetworkManager.MOBILE);
        StringBuilder r0_StringBuilder = new StringBuilder();
        if (Constants.ACTION_LOGIN.equals(r6_String) || Constants.ACTION_PAY.equals(r6_String)) {
            r7_Bundle.putString("response_type", QsbkDatabase.TOKEN);
            r7_Bundle.putString("redirect_uri", ServerSetting.getInstance().getSettingUrl(this.a.f(), 1));
            r7_Bundle.putString("cancel_display", "1");
            r7_Bundle.putString("switch", "1");
            r7_Bundle.putString("sdkp", QsbkDatabase.A);
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r7_Bundle.putString("status_userip", Util.a());
            r7_Bundle.putString("status_os", VERSION.RELEASE);
            r7_Bundle.putString("status_version", VERSION.SDK);
            r7_Bundle.putString("status_machine", Build.MODEL);
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), XListViewHeader.STATE_REFRESHING));
            r0_StringBuilder.append(Util.a(r7_Bundle));
        } else if (Constants.ACTION_STORY.equals(r6_String)) {
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), XListViewFooter.STATE_NOMORE));
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_StringBuilder.append(Util.a(r7_Bundle));
        } else if (Constants.ACTION_INVITE.equals(r6_String)) {
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), XListViewFooter.STATE_NODATA));
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_StringBuilder.append(Util.a(r7_Bundle));
        } else if (Constants.ACTION_CHALLENGE.equals(r6_String) || Constants.ACTION_BRAG.equals(r6_String)) {
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), ShareUtils.SHARE_COLLECT));
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_StringBuilder.append(Util.a(r7_Bundle));
        } else if (Constants.ACTION_ASK.equals(r6_String)) {
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), Base64.DONT_BREAK_LINES));
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_StringBuilder.append(Util.a(r7_Bundle));
        } else if (Constants.ACTION_GIFT.equals(r6_String)) {
            r0_StringBuilder.append(ServerSetting.getInstance().getSettingUrl(this.a.f(), REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY));
            r7_Bundle.putString("sdkv", Constants.SDK_VERSION);
            r0_StringBuilder.append(Util.a(r7_Bundle));
        }
        return r0_StringBuilder.toString();
    }

    public int a(Activity r7_Activity, String r8_String, Bundle r9_Bundle, IUiListener r10_IUiListener) {
        return a(r7_Activity, r8_String, r9_Bundle, r10_IUiListener, false);
    }

    public int a(Activity r7_Activity, String r8_String, Bundle r9_Bundle, IUiListener r10_IUiListener, boolean r11z) {
        boolean r5z = true;
        if (Constants.ACTION_CHALLENGE.equals(r8_String) || Constants.ACTION_BRAG.equals(r8_String) || Constants.ACTION_INVITE.equals(r8_String) || Constants.ACTION_STORY.equals(r8_String) || Constants.ACTION_ASK.equals(r8_String) || Constants.ACTION_GIFT.equals(r8_String)) {
            b(r7_Activity, r8_String, r9_Bundle, r10_IUiListener, false);
            return XListViewHeader.STATE_REFRESHING;
        } else {
            if (!r11z) {
                String r0_String = this.a.b();
                String r1_String = this.a.c();
                String r2_String = this.a.d();
                Log.d("toddtest", "OpenUI showUi");
                if (r0_String == null || r1_String == null || r2_String == null || r0_String.length() <= 0 || r1_String.length() <= 0 || r2_String.length() <= 0 || (!Constants.ACTION_LOGIN.equals(r8_String))) {
                } else {
                    b(r7_Activity, Constants.ACTION_CHECK_TOKEN, r9_Bundle, r10_IUiListener, r5z);
                    return XListViewFooter.STATE_NOMORE;
                }
            }
            if (!b(r7_Activity, r8_String, r9_Bundle, r10_IUiListener)) {
                return a((Context)r7_Activity, r8_String, r9_Bundle, r10_IUiListener);
            }
            if (!r11z) {
                return 1;
            }
            Util.a((Context)r7_Activity, "10785", 0, this.a.d());
            return 1;
        }
    }

    public int a(Context r5_Context, String r6_String, Bundle r7_Bundle, IUiListener r8_IUiListener) {
        CookieSyncManager.createInstance(r5_Context);
        String r1_String = c(r6_String, a(r6_String, r7_Bundle));
        if (Constants.ACTION_LOGIN.equals(r6_String)) {
            r8_IUiListener = new l(this, r8_IUiListener, true, false);
        } else if (Constants.ACTION_PAY.equals(r6_String)) {
            r8_IUiListener = new l(this, r8_IUiListener, true, true);
        }
        if (Constants.ACTION_CHALLENGE.equals(r6_String) || Constants.ACTION_BRAG.equals(r6_String)) {
            new PKDialog(r5_Context, r1_String, r8_IUiListener).show();
        } else {
            new TDialog(r5_Context, r1_String, r8_IUiListener, this.a).show();
        }
        return XListViewHeader.STATE_REFRESHING;
    }

    public String a(Activity r4_Activity) {
        try {
            return r4_Activity.getPackageManager().getPackageInfo(Constants.PACKAGE_AGENT, 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public void a() {
        String r1_String = "tencent&sdk&qazxc***14969%%";
        String r2_String = this.a.b();
        String r3_String = this.a.d();
        String r4_String = this.a.c();
        r1_String = (r2_String == null || r2_String.length() <= 0 || r3_String == null || r3_String.length() <= 0 || r4_String == null || r4_String.length() <= 0) ? null : Util.f(r1_String + r2_String + r3_String + r4_String + "qzone3.4");
        WebView r0_WebView = new WebView(this.a.f());
        WebSettings r2_WebSettings = r0_WebView.getSettings();
        r2_WebSettings.setDomStorageEnabled(true);
        r2_WebSettings.setJavaScriptEnabled(true);
        r2_WebSettings.setDatabaseEnabled(true);
        r2_String = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.a.c() + "_" + this.a.d() + "\"]=\"" + r1_String + "\";</script></head><body></body></html>";
        r1_String = ServerSetting.getInstance().getSettingUrl(this.a.f(), REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        r0_WebView.loadDataWithBaseURL(r1_String, r2_String, "text/html", AdViewNetFetchThread.NetEncoding, r1_String);
    }

    public boolean a(int r6i, int r7i, Intent r8_Intent) {
        int r1i = 0;
        Log.v("shareToQQ", "OpenUi onActivityResult:" + r6i + ",resultCode:" + r7i);
        if (r6i < Constants.CODE_REQUEST_MIN || r6i > 6656) {
            return false;
        }
        w r0_w = (w) this.c.get(r6i);
        this.c.remove(r6i);
        if (r0_w == null || r0_w.a == null) {
            return false;
        }
        if (r7i == -1) {
            r1i = r8_Intent.getIntExtra(Constants.KEY_ERROR_CODE, r1i);
            if (r1i == 0) {
                String r1_String = r8_Intent.getStringExtra(Constants.KEY_RESPONSE);
                if (r1_String != null) {
                    try {
                        r0_w.a.onComplete(Util.d(r1_String));
                    } catch (JSONException e) {
                        r0_w.a.onError(new UiError(-4, Constants.MSG_JSON_ERROR, r1_String));
                    }
                } else {
                    r0_w.a.onComplete(null);
                }
            } else {
                r0_w.a.onError(new UiError(r1i, r8_Intent.getStringExtra(Constants.KEY_ERROR_MSG), r8_Intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
            }
        } else if (r7i == 0) {
            r0_w.a.onCancel();
        }
        return true;
    }

    public boolean a(Activity r8_Activity, Bundle r9_Bundle, IUiListener r10_IUiListener) {
        Intent r0_Intent;
        String r4_String = a(r8_Activity);
        if (r4_String == null) {
            r0_Intent = b(r8_Activity);
            if (r0_Intent == null) {
                return false;
            }
            this.a.a = "QQ";
        } else {
            String r0_String = OpenConfig.a((Context)r8_Activity, this.a.d()).a("Common_SSO_QzoneVersion");
            if ((TextUtils.isEmpty(r0_String) ? 0 : 1) == 0) {
                r0_String = "3.7";
            }
            if (a(r4_String, "3.4") < 0 || a(r4_String, r0_String) >= 0) {
                r0_Intent = b(r8_Activity);
                if (r0_Intent != null) {
                    this.a.a = "QQ";
                } else {
                    r0_Intent = c(r8_Activity);
                }
                if (r0_Intent == null) {
                    return false;
                }
                this.a.a = "qzone";
            } else {
                r0_Intent = c(r8_Activity);
                if (r0_Intent == null) {
                    return false;
                }
                this.a.a = "qzone";
            }
        }
        Bundle r3_Bundle = new Bundle(r9_Bundle);
        if (this.a != null) {
            r3_Bundle.putString(QQDialogAuthorizeActivity.CLIENT_ID, this.a.d());
        }
        r3_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
        r3_Bundle.putString("need_pay", "1");
        r0_Intent.putExtra("key_request_code", b());
        r0_Intent.putExtra(Constants.KEY_ACTION, Constants.ACTION_LOGIN);
        r0_Intent.putExtra(Constants.KEY_PARAMS, r3_Bundle);
        try {
            int r3i = r0_Intent.getIntExtra("key_request_code", 0);
            r8_Activity.startActivityForResult(r0_Intent, r3i);
            this.c.put(r3i, new w(r8_Activity, Constants.ACTION_LOGIN, r9_Bundle, new l(this, r10_IUiListener, false, false)));
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    public Intent b(Activity r4_Activity) {
        Intent r0_Intent = new Intent();
        r0_Intent.setClassName(Constants.PACKAGE_QQ, "com.tencent.open.agent.AgentActivity");
        return r4_Activity.getPackageManager().resolveActivity(r0_Intent, 0) != null ? r0_Intent : null;
    }

    public void b(Activity r12_Activity, String r13_String, Bundle r14_Bundle, IUiListener r15_IUiListener, boolean r16z) {
        Log.d("toddtest", "OpenUI getEncryToken");
        Intent r9_Intent = new Intent();
        r9_Intent.setClassName(Constants.PACKAGE_AGENT, "com.tencent.open.agent.EncryTokenActivity");
        r9_Intent.putExtra("key_request_code", b());
        Intent r10_Intent = new Intent();
        r10_Intent.setClassName(Constants.PACKAGE_AGENT, "com.tencent.open.agent.AgentActivity");
        IUiListener r0_IUiListener = new k(this, r12_Activity, r14_Bundle, r15_IUiListener, this.a.d(), this.a.c(), this.a.b(), r13_String);
        String r1_String = "qzone3.4";
        if (b(r12_Activity.getApplicationContext(), r9_Intent)) {
            r1_String = "qzone3.5_up";
        } else if (b(r12_Activity.getApplicationContext(), r10_Intent)) {
            r1_String = "qzone3.4";
        } else {
            r1_String = "qzone3.3_below";
        }
        if (r1_String.equals("qzone3.5_up")) {
            Log.d("toddtest", "OpenUI checkToken qzone exist, version = " + r1_String);
            r9_Intent.putExtra(Constants.KEY_ACTION, Constants.ACTION_CHECK_TOKEN);
            r9_Intent.putExtra(Constants.PARAM_CONSUMER_KEY, this.a.d());
            r9_Intent.putExtra(Constants.PARAM_OPEN_ID, this.a.c());
            r9_Intent.putExtra(ThirdParty.KEY_TOKEN, this.a.b());
            int r1i = b();
            r12_Activity.startActivityForResult(r9_Intent, r1i);
            this.c.put(r1i, new w(r12_Activity, Constants.ACTION_CHECK_TOKEN, r14_Bundle, r0_IUiListener));
        } else {
            r1_String = "tencent&sdk&qazxc***14969%%";
            String r2_String = this.a.b();
            String r3_String = this.a.d();
            String r4_String = this.a.c();
            String r5_String = "qzone3.4";
            JSONObject r2_JSONObject;
            if (r2_String == null || r2_String.length() <= 0 || r3_String == null || r3_String.length() <= 0 || r4_String == null || r4_String.length() <= 0) {
                r2_JSONObject = new JSONObject();
                try {
                    r2_JSONObject.put(Constants.PARAM_ENCRY_EOKEN, RContactStorage.PRIMARY_KEY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                r0_IUiListener.onComplete(r2_JSONObject);
            } else {
                r1_String = Util.f(r1_String + r2_String + r3_String + r4_String + r5_String);
                r2_JSONObject = new JSONObject();
                try {
                    r2_JSONObject.put(Constants.PARAM_ENCRY_EOKEN, r1_String);
                } catch (JSONException e_2) {
                    e_2.printStackTrace();
                }
                r0_IUiListener.onComplete(r2_JSONObject);
            }
        }
    }

    public boolean b(Activity r7_Activity, String r8_String, Bundle r9_Bundle, IUiListener r10_IUiListener) {
        if (Constants.ACTION_LOGIN.equals(r8_String)) {
            return a(r7_Activity, r9_Bundle, r10_IUiListener);
        }
        Intent r2_Intent;
        Bundle r3_Bundle = b(r8_String, r9_Bundle);
        r2_Intent = Constants.ACTION_SHARE_QQ.equals(r8_String) ? a((Context)r7_Activity, r8_String, r9_Bundle.getString("scheme")) : a((Context)r7_Activity, r8_String);
        if (r2_Intent == null) {
            return false;
        }
        r2_Intent.putExtra(Constants.KEY_ACTION, r8_String);
        r2_Intent.putExtra(Constants.KEY_PARAMS, r3_Bundle);
        try {
            int r3i = r2_Intent.getIntExtra("key_request_code", 0);
            r7_Activity.startActivityForResult(r2_Intent, r3i);
            if (Constants.ACTION_PAY.equals(r8_String)) {
                r10_IUiListener = new l(this, r10_IUiListener, false, true);
            }
            this.c.put(r3i, new w(r7_Activity, r8_String, r9_Bundle, r10_IUiListener));
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("OpenUi", "not such activity", e);
            return false;
        }
    }

    public Intent c(Activity r4_Activity) {
        Intent r0_Intent = new Intent();
        r0_Intent.setClassName(Constants.PACKAGE_AGENT, "com.tencent.open.agent.AgentActivity");
        return b((Context)r4_Activity, r0_Intent) ? r0_Intent : null;
    }
}