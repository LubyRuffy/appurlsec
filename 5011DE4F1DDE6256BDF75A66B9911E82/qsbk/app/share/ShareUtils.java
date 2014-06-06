package qsbk.app.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.activity.base.CommDialogActivity;
import qsbk.app.report.ArticleReporter;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

public class ShareUtils {
    public static final int BIND_STATE_FAIL = 2;
    public static final int BIND_STATE_OK = 3;
    public static final int BIND_STATE_UNBIND = 1;
    public static final int SHARE_COLLECT = 7;
    public static final int SHARE_COPY = 6;
    public static final int SHARE_QZONE = 3;
    public static final int SHARE_REPORT = 8;
    public static final int SHARE_SINA = 1;
    public static final int SHARE_SMS = 5;
    public static final int SHARE_TENCENT = 2;
    public static final int SHARE_WEIXIN = 4;
    public static final int TO_DIALOG_AUTHORIZE = 2;
    public static final int TO_REPORT = 3;
    public static final int TO_SHARE = 1;
    public String QQ_ACCESS_TOKEN;
    public String RENREN_ACCESS_TOKEN;
    public String SINA_ACCESS_TOKEN;
    Handler a;
    private ArticleReporter b;

    public ShareUtils() {
        this.SINA_ACCESS_TOKEN = RContactStorage.PRIMARY_KEY;
        this.QQ_ACCESS_TOKEN = RContactStorage.PRIMARY_KEY;
        this.RENREN_ACCESS_TOKEN = RContactStorage.PRIMARY_KEY;
        this.a = new e(this);
        this.b = null;
        if (QsbkApp.currentUser != null) {
            this.SINA_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_sina_access_token";
            this.QQ_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_qq_access_token";
            this.RENREN_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_renren_access_token";
        }
    }

    private void a(Context r4_Context, String r5_String) {
        ((ClipboardManager) r4_Context.getSystemService("clipboard")).setText(r5_String + "@\u7cd7\u4e8b\u767e\u79d1");
        Toast.makeText(QsbkApp.mContext, "\u7cd7\u4e8b\u5df2\u590d\u5236", 0).show();
    }

    private void a(Context r5_Context, String r6_String, String r7_String) {
        Intent r0_Intent = new Intent("android.intent.action.SEND");
        if (TextUtils.isEmpty(r7_String) || r7_String.equals("null")) {
            r0_Intent.setType("text/plain");
        } else {
            copyFile(r5_Context.getCacheDir() + "/pre/" + r7_String, DeviceUtils.getSDPath() + "/qsbk/pre/", r7_String);
            r0_Intent.putExtra("android.intent.extra.STREAM", Uri.parse("file:///sdcard/qsbk/pre/" + r7_String));
            r0_Intent.setType("image/*");
        }
        r0_Intent.putExtra("sms_body", r6_String + "@\u7cd7\u4e8b\u767e\u79d1");
        r0_Intent.putExtra("address", RContactStorage.PRIMARY_KEY);
        r5_Context.startActivity(r0_Intent);
    }

    private void a(Context r1_Context, String r2_String, String r3_String, int r4i) {
    }

    private void a(String r2_String, boolean r3z) {
        new g(this, r2_String, r3z).run();
    }

    public static boolean checkAndAlertNetworkStatus(Context r4_Context) {
        if (HttpUtils.isNetworkConnected(r4_Context)) {
            return true;
        }
        Toast.makeText(QsbkApp.mContext, r4_Context.getResources().getString(R.string.network_not_connected), 0).show();
        return false;
    }

    public static void copyFile(String r6_String, String r7_String, String r8_String) {
        int r0i = 0;
        try {
            if (new File(r6_String).exists()) {
                InputStream r1_InputStream = new FileInputStream(r6_String);
                File r2_File = new File(r7_String);
                if (!r2_File.exists()) {
                    r2_File.mkdirs();
                }
                FileOutputStream r2_FileOutputStream = new FileOutputStream(r7_String + r8_String);
                byte[] r3_byteA = new byte[1444];
                while (true) {
                    int r4i = r1_InputStream.read(r3_byteA);
                    if (r4i != -1) {
                        r0i += r4i;
                        r2_FileOutputStream.write(r3_byteA, 0, r4i);
                    } else {
                        r1_InputStream.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean needNetwork(int r2i) {
        return r2i == 4 || r2i == 3 || r2i == 1 || r2i == 2;
    }

    public static void openShareDialog(Activity r6_Activity, int r7i, boolean r8z) {
        boolean r3z = false;
        String[] r0_StringA = new String[7];
        r0_StringA[0] = "\u5206\u4eab\u5230\u5fae\u4fe1";
        r0_StringA[1] = "\u5206\u4eab\u5230QQ";
        r0_StringA[2] = "\u5206\u4eab\u5230\u65b0\u6d6a\u5fae\u535a";
        r0_StringA[3] = "\u817e\u8baf\u5fae\u535a";
        r0_StringA[4] = "\u590d\u5236";
        r0_StringA[5] = "\u6536\u85cf";
        r0_StringA[6] = "\u4e3e\u62a5";
        int[] r1_intA = new int[]{4, 3, 1, 2, 6, 7, 8};
        if (r8z) {
            r0_StringA[5] = "\u53d6\u6d88\u6536\u85cf";
        }
        WXAPIFactory.createWXAPI(r6_Activity, Constants.APP_ID, r3z).registerApp(Constants.APP_ID);
        Intent r2_Intent = new Intent(r6_Activity, CommDialogActivity.class);
        r2_Intent.putExtra(CommDialogActivity.KEY_ACTIONS, r1_intA);
        r2_Intent.putExtra(CommDialogActivity.KEY_ITEMS, r0_StringA);
        r6_Activity.startActivityForResult(r2_Intent, r7i);
    }

    public void Share(Context r7_Context, String r8_String, int r9i) {
        Map r4_Map = new HashMap();
        Toast.makeText(QsbkApp.mContext, "\u5206\u4eab\u4e2d...", TO_SHARE).show();
        CharSequence r1_CharSequence = RContactStorage.PRIMARY_KEY;
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r9i) {
            case TO_SHARE:
                r1_CharSequence = getAccessToken(SharePreferenceUtils.getSharePreferencesValue(this.SINA_ACCESS_TOKEN));
                r4_Map.put("sina", r1_CharSequence);
                r0_String = "weibo";
                break;
            case TO_DIALOG_AUTHORIZE:
                r1_CharSequence = getAccessToken(SharePreferenceUtils.getSharePreferencesValue(this.QQ_ACCESS_TOKEN));
                r4_Map.put(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, r1_CharSequence);
                r0_String = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                break;
            case TO_REPORT:
                r1_CharSequence = getAccessToken(SharePreferenceUtils.getSharePreferencesValue(this.QQ_ACCESS_TOKEN));
                r4_Map.put("qzone", r1_CharSequence);
                r0_String = "qzone";
                break;
        }
        LogUtil.d("share token:" + r1_CharSequence);
        StatService.onEvent(QsbkApp.mContext, r0_String, "pass", TO_SHARE);
        if (TextUtils.isEmpty(r1_CharSequence)) {
            Toast.makeText(QsbkApp.mContext, "\u7ed1\u5b9a\u4fe1\u606f\u51fa\u9519\uff0c\u8bf7\u91cd\u65b0\u7ed1\u5b9a", TO_SHARE).show();
        } else {
            new f(this, "qbk-ShareUtl", r8_String, r4_Map, r9i).start();
        }
    }

    public void Share(Context r5_Context, String r6_String, String r7_String, int r8i, int r9i) {
        switch (r8i) {
            case SHARE_WEIXIN:
                a(r5_Context, r6_String, r7_String, r9i);
                StatService.onEvent(QsbkApp.mContext, "weixin", "pass", TO_SHARE);
                break;
            case SHARE_SMS:
                a(r5_Context, r6_String, r7_String);
                break;
            case SHARE_COPY:
                a(r5_Context, r6_String);
                StatService.onEvent(QsbkApp.mContext, "copy", "pass", TO_SHARE);
                break;
        }
    }

    public void buidBindUrl(Integer r3_Integer) {
        if (r3_Integer.intValue() == 1) {
            ShareUrl.weiboUrl = ShareUrl.sinaUrl;
        } else if (r3_Integer.intValue() == 2 || r3_Integer.intValue() == 3) {
            ShareUrl.weiboUrl = ShareUrl.qqZoneUrl;
        } else {
            ShareUrl.weiboUrl = ShareUrl.renrenUrl;
        }
    }

    public Integer checkAccessToken(int r5i) {
        int r0i;
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r5i) {
            case TO_SHARE:
                r0_String = this.SINA_ACCESS_TOKEN;
                break;
            case TO_DIALOG_AUTHORIZE:
            case TO_REPORT:
                r0_String = this.QQ_ACCESS_TOKEN;
                break;
        }
        r0_String = SharePreferenceUtils.getSharePreferencesValue(r0_String);
        if (TextUtils.isEmpty(r0_String)) {
            r0i = 1;
        } else if (Long.valueOf(r0_String.split("&")[1].split("=")[1]).longValue() > System.currentTimeMillis()) {
            r0i = TO_REPORT;
        } else {
            r0i = TO_DIALOG_AUTHORIZE;
        }
        return Integer.valueOf(r0i);
    }

    public String getAccessToken(String r3_String) {
        String[] r0_StringA = r3_String.split("&");
        return r0_StringA.length > 0 ? r0_StringA[0].split("=")[1] : RContactStorage.PRIMARY_KEY;
    }

    public ArticleReporter getArticleReporter(Activity r2_Activity) {
        if (this.b == null) {
            this.b = new ArticleReporter(r2_Activity);
        }
        return this.b;
    }

    public String getTarget(int r2i) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r2i) {
            case TO_SHARE:
                return "\u65b0\u6d6a\u5fae\u535a";
            case TO_DIALOG_AUTHORIZE:
                return "\u817e\u8baf\u5fae\u535a";
            case TO_REPORT:
                return "QQ\u7a7a\u95f4";
        }
        return r0_String;
    }

    public void tryCollection(View r4_View, Activity r5_Activity, String r6_String) {
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u6536\u85cf\u54e6\uff01", 0).show();
            r5_Activity.startActivity(new Intent(r5_Activity, LoginActivity.class));
            r5_Activity.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else if (HttpUtils.isNetworkConnected(r5_Activity)) {
            if (r4_View.getTag().equals("enable")) {
                r4_View.setTag("active");
                a(r6_String, true);
            } else {
                r4_View.setTag("enable");
                a(r6_String, false);
            }
        } else {
            Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 0).show();
        }
    }
}