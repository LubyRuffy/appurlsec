package com.tencent.open;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.lang.ref.WeakReference;

// compiled from: ProGuard
public class ServerSetting {
    public static final int ASK_URL = 8;
    public static final int AUTHORIZE_CGI = 2;
    public static final int BRAG_URL = 7;
    public static final int ENVIRONMENT_EXPERIENCE = 1;
    public static final int ENVIRONMENT_NORMOL = 0;
    public static final int GIFT_URL = 9;
    public static final int GRAPH_BASE_URL = 6;
    public static final int INVITE_URL = 4;
    public static final int LOCAL_STORAGE_URL = 10;
    public static final int REDIRECT_URL = 1;
    public static final int REPORT_URL = 5;
    public static final int STORY_URL = 3;
    private static final String a;
    private static ServerSetting b;
    private WeakReference c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    static {
        a = ServerSetting.class.getName();
        b = null;
    }

    public ServerSetting() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public static ServerSetting getInstance() {
        if (b == null) {
            b = new ServerSetting();
        }
        return b;
    }

    public void changeServer() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public String getSettingUrl(Context r4_Context, int r5i) {
        if (r4_Context != null) {
            if (this.c == null || this.c.get() == null) {
                this.c = new WeakReference(r4_Context.getSharedPreferences("ServerPrefs", ENVIRONMENT_NORMOL));
            }
        }
        switch (r5i) {
            case REDIRECT_URL:
                if (r4_Context == null) {
                    return "auth://tauth.qq.com/";
                }
                if (this.d == null) {
                    this.d = ((SharedPreferences) this.c.get()).getString("RedirectUrl", "auth://tauth.qq.com/");
                }
                return this.d;
            case AUTHORIZE_CGI:
                if (r4_Context == null) {
                    return "https://openmobile.qq.com/oauth2.0/m_authorize?";
                }
                if (this.e == null) {
                    this.e = ((SharedPreferences) this.c.get()).getString("AuthorizeCgi", "https://openmobile.qq.com/oauth2.0/m_authorize?");
                }
                return this.e;
            case STORY_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?";
                }
                if (this.f == null) {
                    this.f = ((SharedPreferences) this.c.get()).getString("StoryUrl", "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?");
                }
                return this.f;
            case INVITE_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?";
                }
                if (this.g == null) {
                    this.g = ((SharedPreferences) this.c.get()).getString("InviteUrl", "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?");
                }
                return this.g;
            case REPORT_URL:
                if (r4_Context == null) {
                    return "http://wspeed.qq.com/w.cgi";
                }
                if (this.h == null) {
                    this.h = ((SharedPreferences) this.c.get()).getString("ReportUrl", "http://wspeed.qq.com/w.cgi");
                }
                return this.h;
            case GRAPH_BASE_URL:
                if (r4_Context == null) {
                    return Constants.GRAPH_BASE;
                }
                if (this.i == null) {
                    this.i = ((SharedPreferences) this.c.get()).getString("GraphBaseUrl", Constants.GRAPH_BASE);
                }
                return this.i;
            case BRAG_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com/open/mobile/brag/sdk_brag.html?";
                }
                if (this.j == null) {
                    this.j = ((SharedPreferences) this.c.get()).getString("BragUrl", "http://qzs.qq.com/open/mobile/brag/sdk_brag.html?");
                }
                return this.j;
            case ASK_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com/open/mobile/request/sdk_request.html?";
                }
                if (this.k == null) {
                    this.k = ((SharedPreferences) this.c.get()).getString("AskUrl", "http://qzs.qq.com/open/mobile/request/sdk_request.html?");
                }
                return this.k;
            case GIFT_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com/open/mobile/request/sdk_request.html?";
                }
                if (this.l == null) {
                    this.l = ((SharedPreferences) this.c.get()).getString("GiftUrl", "http://qzs.qq.com/open/mobile/request/sdk_request.html?");
                }
                return this.l;
            case LOCAL_STORAGE_URL:
                if (r4_Context == null) {
                    return "http://qzs.qq.com";
                }
                if (this.m == null) {
                    this.m = ((SharedPreferences) this.c.get()).getString("LocalStorageUrl", "http://qzs.qq.com");
                }
                return this.m;
        }
        return RContactStorage.PRIMARY_KEY;
    }

    public void setEnvironment(Context r5_Context, int r6i) {
        int r2i = REDIRECT_URL;
        if (r5_Context != null) {
            if (this.c == null || this.c.get() == null) {
                this.c = new WeakReference(r5_Context.getSharedPreferences("ServerPrefs", ENVIRONMENT_NORMOL));
            }
        }
        if (r6i == 0 || r6i == 1) {
            Editor r0_Editor;
            switch (r6i) {
                case ENVIRONMENT_NORMOL:
                    r0_Editor = ((SharedPreferences) this.c.get()).edit();
                    r0_Editor.putInt("ServerType", ENVIRONMENT_NORMOL);
                    r0_Editor.putString("RedirectUrl", "auth://tauth.qq.com/");
                    r0_Editor.putString("AuthorizeCgi", "https://openmobile.qq.com/oauth2.0/m_authorize?");
                    r0_Editor.putString("StoryUrl", "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?");
                    r0_Editor.putString("InviteUrl", "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?");
                    r0_Editor.putString("ReportUrl", "http://wspeed.qq.com/w.cgi");
                    r0_Editor.putString("GraphBaseUrl", Constants.GRAPH_BASE);
                    r0_Editor.putString("BragUrl", "http://qzs.qq.com/open/mobile/brag/sdk_brag.html?");
                    r0_Editor.putString("AskUrl", "http://qzs.qq.com/open/mobile/request/sdk_request.html?");
                    r0_Editor.putString("GiftUrl", "http://qzs.qq.com/open/mobile/request/sdk_request.html?");
                    r0_Editor.putString("LocalStorageUrl", "http://qzs.qq.com");
                    r0_Editor.commit();
                    changeServer();
                    Toast.makeText(r5_Context, "\u5df2\u5207\u6362\u5230\u6b63\u5f0f\u73af\u5883", ENVIRONMENT_NORMOL).show();
                case REDIRECT_URL:
                    r0_Editor = ((SharedPreferences) this.c.get()).edit();
                    r0_Editor.putInt("ServerType", r2i);
                    r0_Editor.putString("RedirectUrl", "auth://tauth.qq.com/");
                    r0_Editor.putString("AuthorizeCgi", "https://test.openmobile.qq.com/oauth2.0/m_authorize?");
                    r0_Editor.putString("StoryUrl", "http://testmobile.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?");
                    r0_Editor.putString("InviteUrl", "http://testmobile.qq.com/open/mobile/invite/sdk_invite.html?");
                    r0_Editor.putString("ReportUrl", "http://wspeed.qq.com/w.cgi");
                    r0_Editor.putString("GraphBaseUrl", "https://test.openmobile.qq.com/");
                    r0_Editor.putString("BragUrl", "http://testmobile.qq.com/open/mobile/brag/sdk_brag.html?");
                    r0_Editor.putString("AskUrl", "http://testmobile.qq.com/open/mobile/request/sdk_request.html?");
                    r0_Editor.putString("GiftUrl", "http://testmobile.qq.com/open/mobile/request/sdk_request.html?");
                    r0_Editor.putString("LocalStorageUrl", "http://test.openmobile.qq.com");
                    r0_Editor.commit();
                    changeServer();
                    Toast.makeText(r5_Context, "\u5df2\u5207\u6362\u5230\u4f53\u9a8c\u73af\u5883", ENVIRONMENT_NORMOL).show();
            }
        } else {
            Log.e(a, "\u5207\u6362\u73af\u5883\u53c2\u6570\u9519\u8bef\uff0c\u6b63\u5f0f\u73af\u5883\u4e3a0\uff0c\u4f53\u9a8c\u73af\u5883\u4e3a1");
        }
    }
}