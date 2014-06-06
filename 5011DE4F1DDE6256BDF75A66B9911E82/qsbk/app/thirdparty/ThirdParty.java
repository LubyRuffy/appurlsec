package qsbk.app.thirdparty;

import android.content.Context;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Tencent;
import org.apache.cordova.NetworkManager;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.Utility.Utility;
import qsbk.app.thirdparty.ui.ThirdPartyDialog;

public class ThirdParty {
    public static final String KEY_EXPIRES = "expires_in";
    public static final String KEY_REFRESHTOKEN = "refresh_token";
    public static final String KEY_TOKEN = "access_token";
    public static String QQ_OAUTH2_ACCESS_AUTHORIZE;
    public static String SINA_OAUTH2_ACCESS_AUTHORIZE;
    private static ThirdParty a;
    public static String app_key;
    public static String redirecturl;
    public static String thirdparty_type;
    public Oauth2AccessToken accessToken;

    static {
        SINA_OAUTH2_ACCESS_AUTHORIZE = "https://open.weibo.cn/oauth2/authorize";
        QQ_OAUTH2_ACCESS_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
        a = null;
        app_key = RContactStorage.PRIMARY_KEY;
        redirecturl = RContactStorage.PRIMARY_KEY;
        thirdparty_type = RContactStorage.PRIMARY_KEY;
    }

    public ThirdParty() {
        this.accessToken = null;
    }

    public static synchronized ThirdParty getInstance(String r5_String, String r6_String, String r7_String) {
        ThirdParty r0_ThirdParty;
        int r0i = 1;
        synchronized (ThirdParty.class) {
            int r2i;
            r2i = a == null ? 1 : 0;
            if (a == null || (!app_key.equals(r5_String))) {
                r0i = 0;
                if ((r0i | r2i) == 0) {
                    a = new ThirdParty();
                }
                app_key = r5_String;
                redirecturl = r6_String;
                thirdparty_type = r7_String;
                r0_ThirdParty = a;
            } else if ((r0i | r2i) == 0) {
                app_key = r5_String;
                redirecturl = r6_String;
                thirdparty_type = r7_String;
                r0_ThirdParty = a;
            } else {
                a = new ThirdParty();
                app_key = r5_String;
                redirecturl = r6_String;
                thirdparty_type = r7_String;
                r0_ThirdParty = a;
            }
        }
        return r0_ThirdParty;
    }

    public static Tencent getTencentInstance(String r1_String, Context r2_Context) {
        return Tencent.createInstance(r1_String, r2_Context);
    }

    public void authorize(Context r1_Context, ThirdPartyAuthListener r2_ThirdPartyAuthListener) {
        startAuthDialog(r1_Context, r2_ThirdPartyAuthListener);
    }

    public void setupConsumerConfig(String r1_String, String r2_String) {
        app_key = r1_String;
        redirecturl = r2_String;
    }

    public void startAuthDialog(Context r3_Context, ThirdPartyAuthListener r4_ThirdPartyAuthListener) {
        startDialog(r3_Context, new ThirdPartyParameters(), new a(this, r4_ThirdPartyAuthListener));
    }

    public void startDialog(Context r4_Context, ThirdPartyParameters r5_ThirdPartyParameters, ThirdPartyAuthListener r6_ThirdPartyAuthListener) {
        r5_ThirdPartyParameters.add(QQDialogAuthorizeActivity.CLIENT_ID, app_key);
        r5_ThirdPartyParameters.add("response_type", QsbkDatabase.TOKEN);
        r5_ThirdPartyParameters.add("redirect_uri", redirecturl);
        r5_ThirdPartyParameters.add("display", NetworkManager.MOBILE);
        String r0_String;
        if (this.accessToken == null || (!this.accessToken.isSessionValid())) {
            r0_String = (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equals(thirdparty_type) ? QQ_OAUTH2_ACCESS_AUTHORIZE : SINA_OAUTH2_ACCESS_AUTHORIZE) + "?" + Utility.encodeUrl(r5_ThirdPartyParameters);
            if (r4_Context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
                Utility.showAlert(r4_Context, "Error", "Application requires permission to access the Internet");
            } else {
                new ThirdPartyDialog(r4_Context, r0_String, r6_ThirdPartyAuthListener).show();
            }
        } else {
            r5_ThirdPartyParameters.add(KEY_TOKEN, this.accessToken.getToken());
            if (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equals(thirdparty_type)) {
            }
            r0_String = (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equals(thirdparty_type) ? QQ_OAUTH2_ACCESS_AUTHORIZE : SINA_OAUTH2_ACCESS_AUTHORIZE) + "?" + Utility.encodeUrl(r5_ThirdPartyParameters);
            if (r4_Context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
                new ThirdPartyDialog(r4_Context, r0_String, r6_ThirdPartyAuthListener).show();
            } else {
                Utility.showAlert(r4_Context, "Error", "Application requires permission to access the Internet");
            }
        }
    }
}