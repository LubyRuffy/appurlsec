package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.google.analytics.tracking.android.ModelFields;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.open.AsynLoadImg;
import com.tencent.open.OpenApi;
import com.tencent.open.OpenUi;
import com.tencent.open.TContext;
import com.tencent.open.TemporaryStorage;
import com.tencent.open.TencentStat;
import com.tencent.open.Util;
import org.json.JSONObject;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.push.Utils;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
public class Tencent {
    private OpenApi a;
    private OpenUi b;
    private TContext c;

    private Tencent(String r3_String, Context r4_Context) {
        this.c = new TContext(r3_String, r4_Context);
        this.a = new OpenApi(this.c);
        this.b = new OpenUi(this.c);
        TencentStat.a(this.c, r3_String);
    }

    private String a(String r8_String, Bundle r9_Bundle) {
        String r0_String = "...";
        r9_Bundle.putString(QsbkDatabase.ACTION, "shareToQQ");
        r9_Bundle.putString(ModelFields.APP_ID, getAppId());
        r9_Bundle.putString("sdkp", QsbkDatabase.A);
        r9_Bundle.putString("sdkv", Constants.SDK_VERSION);
        r9_Bundle.putString("status_os", VERSION.RELEASE);
        r9_Bundle.putString("status_machine", Build.MODEL);
        String r1_String = Utils.RESPONSE_CONTENT;
        if ((!r9_Bundle.containsKey(r1_String)) || r9_Bundle.getString(r1_String).length() <= 40) {
            r1_String = Constants.PARAM_SUMMARY;
            if ((!r9_Bundle.containsKey(r1_String)) || r9_Bundle.getString(r1_String).length() <= 80) {
                return r8_String + "&" + Util.a(r9_Bundle).replaceAll("\\+", "%20");
            } else {
                r9_Bundle.putString(r1_String, r9_Bundle.getString(r1_String).substring(0, 80) + r0_String);
                return r8_String + "&" + Util.a(r9_Bundle).replaceAll("\\+", "%20");
            }
        } else {
            r9_Bundle.putString(r1_String, r9_Bundle.getString(r1_String).substring(0, 40) + r0_String);
            r1_String = Constants.PARAM_SUMMARY;
            if (r9_Bundle.containsKey(r1_String) || r9_Bundle.getString(r1_String).length() <= 80) {
                return r8_String + "&" + Util.a(r9_Bundle).replaceAll("\\+", "%20");
            } else {
                r9_Bundle.putString(r1_String, r9_Bundle.getString(r1_String).substring(0, 80) + r0_String);
                return r8_String + "&" + Util.a(r9_Bundle).replaceAll("\\+", "%20");
            }
        }
    }

    private void a(Activity r10_Activity, Bundle r11_Bundle, IUiListener r12_IUiListener) {
        Object r0_Object = TemporaryStorage.a("shareToQQ", r12_IUiListener);
        if (r0_Object != null) {
            ((IUiListener) r0_Object).onCancel();
        }
        String r7_String = r11_Bundle.getString(Constants.PARAM_IMAGE_URL);
        String r3_String = r11_Bundle.getString(Constants.PARAM_TITLE);
        String r4_String = r11_Bundle.getString(Constants.PARAM_SUMMARY);
        Log.v("shareToQQ", "imageUrl:" + r7_String + ", title:" + r3_String + ",summary:" + r4_String);
        if (r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY) || (!r7_String.contains(HttpUtils.http))) {
            b(r10_Activity, r11_Bundle, r12_IUiListener);
        } else if (Util.e(r3_String) && Util.e(r4_String) && !Util.b()) {
            r12_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
            Log.v("shareToQQ", Constants.MSG_SHARE_NOSD_ERROR);
        } else {
            new AsynLoadImg(r10_Activity).a(r7_String, new a(this, r11_Bundle, r3_String, r4_String, r12_IUiListener, r10_Activity));
        }
    }

    private void b(Activity r13_Activity, Bundle r14_Bundle, IUiListener r15_IUiListener) {
        if (r14_Bundle == null) {
            r14_Bundle = new Bundle();
        }
        String r0_String = "mqqapi://share/to_fri?src_type=app&version=1&file_type=news";
        String r2_String = r14_Bundle.getString(Constants.PARAM_IMAGE_URL);
        String r3_String = r14_Bundle.getString(Constants.PARAM_TITLE);
        String r4_String = r14_Bundle.getString(Constants.PARAM_SUMMARY);
        String r5_String = r14_Bundle.getString(Constants.PARAM_TARGET_URL);
        String r1_String = r14_Bundle.getString(Constants.PARAM_APPNAME);
        String r6_String = r14_Bundle.getString("imageLocalUrl");
        String r7_String = getAppId();
        if (!Util.e(r2_String)) {
            r0_String = r0_String + "&image_url=" + Base64.encodeToString(r2_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        if (!Util.e(r6_String)) {
            r0_String = r0_String + "&file_data=" + Base64.encodeToString(r6_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        if (!Util.e(r3_String)) {
            r0_String = r0_String + "&title=" + Base64.encodeToString(r3_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        if (!Util.e(r4_String)) {
            r0_String = r0_String + "&description=" + Base64.encodeToString(r4_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        if (!Util.e(r7_String)) {
            r0_String = r0_String + "&share_id=" + r7_String;
        }
        if (!Util.e(r5_String)) {
            r0_String = r0_String + "&url=" + Base64.encodeToString(r5_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        if (!Util.e(r1_String)) {
            if (r1_String.length() > 20) {
                r1_String = r1_String.substring(0, OneProfileActivity.REQ_CODE_SHARE) + "...";
            }
            r0_String = r0_String + "&app_name=" + Base64.encodeToString(r1_String.getBytes(), XListViewHeader.STATE_REFRESHING);
        }
        Log.v("shareToQQ", r0_String);
        TContext r1_TContext = this.c;
        String[] r3_StringA = new String[1];
        r3_StringA[0] = "shareToNativeQQ";
        TencentStat.a(r1_TContext, "requireApi", r3_StringA);
        Bundle r1_Bundle = new Bundle();
        r1_Bundle.putString("scheme", r0_String);
        if (this.b.b(r13_Activity, Constants.ACTION_SHARE_QQ, r1_Bundle, r15_IUiListener) || r15_IUiListener == null) {
        } else {
            r15_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_TO_QQ_ERROR, null));
        }
    }

    private void c(Activity r7_Activity, Bundle r8_Bundle, IUiListener r9_IUiListener) {
        String r1_String = "http://openmobile.qq.com/api/check?page=shareindex.html&style=9";
        Object r0_Object = TemporaryStorage.a("shareToQQ", r9_IUiListener);
        if (r0_Object != null) {
            ((IUiListener) r0_Object).onCancel();
        }
        if (r8_Bundle == null) {
            r8_Bundle = new Bundle();
        }
        String r0_String = a(r1_String, r8_Bundle);
        TContext r1_TContext = this.c;
        String[] r3_StringA = new String[1];
        r3_StringA[0] = "shareToH5QQ";
        TencentStat.a(r1_TContext, "requireApi", r3_StringA);
        if (Util.a((Context)r7_Activity, r0_String) || r9_IUiListener == null) {
        } else {
            r9_IUiListener.onError(new UiError(-6, Constants.MSG_OPEN_BROWSER_ERROR, null));
        }
    }

    public static Tencent createInstance(String r3_String, Context r4_Context) {
        try {
            r4_Context.getPackageManager().getActivityInfo(new ComponentName(r4_Context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            return new Tencent(r3_String, r4_Context);
        } catch (NameNotFoundException e) {
            Log.e("Tencent", ("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity,\u8bf7\u52a0\u4e0acom.tencent.open.AuthActivity,\u5e76\u914d\u7f6e<data android:scheme=\"tencent" + r3_String + "\" />,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.") + "\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n     <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + r3_String + "\" />\n" + "</intent-filter>\n" + "</activity>");
            return null;
        }
    }

    public int ask(Activity r3_Activity, Bundle r4_Bundle, IUiListener r5_IUiListener) {
        r4_Bundle.putString(QsbkDatabase.TYPE, "request");
        return this.b.a(r3_Activity, Constants.ACTION_ASK, r4_Bundle, r5_IUiListener);
    }

    public int brag(Activity r3_Activity, Bundle r4_Bundle, IUiListener r5_IUiListener) {
        r4_Bundle.putString(QsbkDatabase.TYPE, "brag");
        return this.b.a(r3_Activity, Constants.ACTION_BRAG, r4_Bundle, r5_IUiListener);
    }

    public int challenge(Activity r3_Activity, Bundle r4_Bundle, IUiListener r5_IUiListener) {
        r4_Bundle.putString(QsbkDatabase.TYPE, "pk");
        return this.b.a(r3_Activity, Constants.ACTION_CHALLENGE, r4_Bundle, r5_IUiListener);
    }

    public String getAccessToken() {
        return this.c.b();
    }

    public void getAppFriends(IRequestListener r2_IRequestListener) {
        this.a.a(r2_IRequestListener);
    }

    public String getAppId() {
        return this.c.d();
    }

    public String getOpenId() {
        return this.c.c();
    }

    public String getSDKVersion() {
        return OpenApi.a();
    }

    public int gift(Activity r3_Activity, Bundle r4_Bundle, IUiListener r5_IUiListener) {
        r4_Bundle.putString(QsbkDatabase.TYPE, "freegift");
        return this.b.a(r3_Activity, Constants.ACTION_GIFT, r4_Bundle, r5_IUiListener);
    }

    public int invite(Activity r6_Activity, Bundle r7_Bundle, IUiListener r8_IUiListener) {
        TContext r0_TContext = this.c;
        String[] r2_StringA = new String[1];
        r2_StringA[0] = "invite";
        TencentStat.a(r0_TContext, "requireApi", r2_StringA);
        return this.b.a(r6_Activity, Constants.ACTION_INVITE, r7_Bundle, r8_IUiListener);
    }

    public boolean isSessionValid() {
        return this.c.a();
    }

    public int login(Activity r4_Activity, String r5_String, IUiListener r6_IUiListener) {
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putString(QQDialogAuthorizeActivity.SCOPE, r5_String);
        return this.b.a(r4_Activity, Constants.ACTION_LOGIN, r0_Bundle, r6_IUiListener);
    }

    public void logout(Context r2_Context) {
        CookieSyncManager.createInstance(r2_Context);
        setAccessToken(null, null);
        setOpenId(null);
    }

    public boolean onActivityResult(int r2i, int r3i, Intent r4_Intent) {
        return this.b.a(r2i, r3i, r4_Intent);
    }

    public int pay(Activity r4_Activity, IUiListener r5_IUiListener) {
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putString(QQDialogAuthorizeActivity.SCOPE, RContactStorage.PRIMARY_KEY);
        return this.b.a(r4_Activity, Constants.ACTION_PAY, r0_Bundle, r5_IUiListener);
    }

    public int reAuth(Activity r7_Activity, String r8_String, IUiListener r9_IUiListener) {
        Bundle r3_Bundle = new Bundle();
        r3_Bundle.putString(QQDialogAuthorizeActivity.SCOPE, r8_String);
        r3_Bundle.putString("isadd", "1");
        return this.b.a(r7_Activity, Constants.ACTION_LOGIN, r3_Bundle, r9_IUiListener, true);
    }

    public JSONObject request(String r3_String, Bundle r4_Bundle, String r5_String) {
        return this.a.a(this.c.f(), r3_String, r4_Bundle, r5_String);
    }

    public void requestAsync(String r8_String, Bundle r9_Bundle, String r10_String, IRequestListener r11_IRequestListener, Object r12_Object) {
        this.a.a(this.c.f(), r8_String, r9_Bundle, r10_String, r11_IRequestListener, r12_Object);
    }

    public void setAccessToken(String r2_String, String r3_String) {
        this.c.a(r2_String, r3_String);
    }

    public void setAvatar(Activity r2_Activity, Bundle r3_Bundle) {
        setAvatar(r2_Activity, r3_Bundle, null);
    }

    public void setAvatar(Activity r4_Activity, Bundle r5_Bundle, IUiListener r6_IUiListener) {
        r5_Bundle.putString(Constants.PARAM_APP_ID, this.c.d());
        r5_Bundle.putString(ThirdParty.KEY_TOKEN, this.c.b());
        r5_Bundle.putLong(ThirdParty.KEY_EXPIRES, this.c.e());
        r5_Bundle.putString(Constants.PARAM_OPEN_ID, this.c.c());
        this.b.b(r4_Activity, Constants.ACTION_AVATAR, r5_Bundle, r6_IUiListener);
    }

    public void setAvatar(Activity r2_Activity, Bundle r3_Bundle, IUiListener r4_IUiListener, int r5i, int r6i) {
        r3_Bundle.putInt("exitAnim", r6i);
        r2_Activity.overridePendingTransition(r5i, 0);
        setAvatar(r2_Activity, r3_Bundle, r4_IUiListener);
    }

    public void setOpenId(String r2_String) {
        this.c.a(r2_String);
        TencentStat.b(this.c, r2_String);
    }

    public void shareToQQ(Activity r11_Activity, Bundle r12_Bundle, IUiListener r13_IUiListener) {
        String r0_String = r12_Bundle.getString(Constants.PARAM_IMAGE_URL);
        String r2_String = r12_Bundle.getString(Constants.PARAM_TITLE);
        String r3_String = r12_Bundle.getString(Constants.PARAM_SUMMARY);
        String r1_String = r12_Bundle.getString(Constants.PARAM_TARGET_URL);
        if (Util.e(r0_String) || r0_String.contains(HttpUtils.http) || r0_String.contains(HttpUtils.https)) {
            if (Util.e(r1_String) || r1_String.contains(HttpUtils.http) || r1_String.contains(HttpUtils.https)) {
                if (Util.b()) {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    Log.v("shareToQQ", Constants.MSG_SHARE_NOSD_ERROR);
                } else if ((Util.e(r2_String) && Util.e(r3_String) && Util.e(r0_String)) || Util.e(r1_String)) {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                } else {
                    r0_String = "...";
                    if (Util.e(r2_String) || r2_String.length() <= 40) {
                    } else {
                        r12_Bundle.putString(Constants.PARAM_TITLE, r2_String.substring(0, 40) + r0_String);
                    }
                    if (Util.e(r3_String) || r3_String.length() <= 80) {
                    } else {
                        r12_Bundle.putString(Constants.PARAM_SUMMARY, r3_String.substring(0, 80) + r0_String);
                    }
                    if (Util.b((Context)r11_Activity)) {
                        c(r11_Activity, r12_Bundle, r13_IUiListener);
                    } else {
                        a(r11_Activity, r12_Bundle, r13_IUiListener);
                    }
                }
            } else {
                r1_String = RContactStorage.PRIMARY_KEY;
                if (Util.b()) {
                    if ((Util.e(r2_String) || Util.e(r3_String) || Util.e(r0_String)) && Util.e(r1_String)) {
                        r0_String = "...";
                        if (Util.e(r2_String) || r2_String.length() <= 40) {
                        } else {
                            r12_Bundle.putString(Constants.PARAM_TITLE, r2_String.substring(0, 40) + r0_String);
                        }
                        if (Util.e(r3_String) || r3_String.length() <= 80) {
                        } else {
                            r12_Bundle.putString(Constants.PARAM_SUMMARY, r3_String.substring(0, 80) + r0_String);
                        }
                        if (Util.b((Context)r11_Activity)) {
                            c(r11_Activity, r12_Bundle, r13_IUiListener);
                        } else {
                            a(r11_Activity, r12_Bundle, r13_IUiListener);
                        }
                    } else {
                        r13_IUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                    }
                } else {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    Log.v("shareToQQ", Constants.MSG_SHARE_NOSD_ERROR);
                }
            }
        } else {
            r0_String = RContactStorage.PRIMARY_KEY;
            if (Util.e(r1_String) || r1_String.contains(HttpUtils.http) || r1_String.contains(HttpUtils.https)) {
                if (Util.b()) {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    Log.v("shareToQQ", Constants.MSG_SHARE_NOSD_ERROR);
                } else if ((Util.e(r2_String) || Util.e(r3_String) || Util.e(r0_String)) && Util.e(r1_String)) {
                    r0_String = "...";
                    if (Util.e(r2_String) || r2_String.length() <= 40) {
                    } else {
                        r12_Bundle.putString(Constants.PARAM_TITLE, r2_String.substring(0, 40) + r0_String);
                    }
                    if (Util.e(r3_String) || r3_String.length() <= 80) {
                    } else {
                        r12_Bundle.putString(Constants.PARAM_SUMMARY, r3_String.substring(0, 80) + r0_String);
                    }
                    if (Util.b((Context)r11_Activity)) {
                        a(r11_Activity, r12_Bundle, r13_IUiListener);
                    } else {
                        c(r11_Activity, r12_Bundle, r13_IUiListener);
                    }
                } else {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                }
            } else {
                r1_String = RContactStorage.PRIMARY_KEY;
                if (Util.b()) {
                    if ((Util.e(r2_String) || Util.e(r3_String) || Util.e(r0_String)) && Util.e(r1_String)) {
                        r0_String = "...";
                        if (Util.e(r2_String) || r2_String.length() <= 40) {
                        } else {
                            r12_Bundle.putString(Constants.PARAM_TITLE, r2_String.substring(0, 40) + r0_String);
                        }
                        if (Util.e(r3_String) || r3_String.length() <= 80) {
                        } else {
                            r12_Bundle.putString(Constants.PARAM_SUMMARY, r3_String.substring(0, 80) + r0_String);
                        }
                        if (Util.b((Context)r11_Activity)) {
                            c(r11_Activity, r12_Bundle, r13_IUiListener);
                        } else {
                            a(r11_Activity, r12_Bundle, r13_IUiListener);
                        }
                    } else {
                        r13_IUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                    }
                } else {
                    r13_IUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                    Log.v("shareToQQ", Constants.MSG_SHARE_NOSD_ERROR);
                }
            }
        }
    }

    public int story(Activity r6_Activity, Bundle r7_Bundle, IUiListener r8_IUiListener) {
        TContext r0_TContext = this.c;
        String[] r2_StringA = new String[1];
        r2_StringA[0] = "story";
        TencentStat.a(r0_TContext, "requireApi", r2_StringA);
        return this.b.a(r6_Activity, Constants.ACTION_STORY, r7_Bundle, r8_IUiListener);
    }
}