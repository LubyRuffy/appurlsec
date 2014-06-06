package qsbk.app.activity.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.Oauth2AccessToken;

public class AccessTokenKeeper {
    public static void clear(Context r2_Context) {
        Editor r0_Editor = r2_Context.getSharedPreferences("com_weibo_sdk_android", AccessibilityNodeInfoCompat.ACTION_PASTE).edit();
        r0_Editor.clear();
        r0_Editor.commit();
    }

    public static void keepAccessToken(Context r4_Context, Oauth2AccessToken r5_Oauth2AccessToken) {
        Editor r0_Editor = r4_Context.getSharedPreferences("com_weibo_sdk_android", AccessibilityNodeInfoCompat.ACTION_PASTE).edit();
        r0_Editor.putString(QsbkDatabase.TOKEN, r5_Oauth2AccessToken.getToken());
        r0_Editor.putLong("expiresTime", r5_Oauth2AccessToken.getExpiresTime());
        r0_Editor.commit();
    }

    public static Oauth2AccessToken readAccessToken(Context r5_Context) {
        Oauth2AccessToken r0_Oauth2AccessToken = new Oauth2AccessToken();
        SharedPreferences r1_SharedPreferences = r5_Context.getSharedPreferences("com_weibo_sdk_android", AccessibilityNodeInfoCompat.ACTION_PASTE);
        r0_Oauth2AccessToken.setToken(r1_SharedPreferences.getString(QsbkDatabase.TOKEN, RContactStorage.PRIMARY_KEY));
        r0_Oauth2AccessToken.setExpiresTime(r1_SharedPreferences.getLong("expiresTime", 0));
        return r0_Oauth2AccessToken;
    }
}