package qsbk.app.thirdparty;

import com.tencent.tauth.Constants;
import qsbk.app.thirdparty.net.AsyncWeiboRunner;
import qsbk.app.thirdparty.net.RequestListener;

public class UsersAPI {
    public static final String HTTPMETHOD_GET = "GET";
    public static final String HTTPMETHOD_POST = "POST";
    private Oauth2AccessToken a;
    private String b;

    public UsersAPI(Oauth2AccessToken r2_Oauth2AccessToken) {
        this.a = r2_Oauth2AccessToken;
        if (this.a != null) {
            this.b = this.a.getToken();
        }
    }

    protected void a(String r1_String, ThirdPartyParameters r2_ThirdPartyParameters, String r3_String, RequestListener r4_RequestListener) {
        AsyncWeiboRunner.request(r1_String, r2_ThirdPartyParameters, r3_String, r4_RequestListener);
    }

    public void getQQOpenId(RequestListener r4_RequestListener) {
        ThirdPartyParameters r0_ThirdPartyParameters = new ThirdPartyParameters();
        r0_ThirdPartyParameters.add(ThirdParty.KEY_TOKEN, this.b);
        a("https://graph.qq.com/oauth2.0/me", r0_ThirdPartyParameters, HTTPMETHOD_GET, r4_RequestListener);
    }

    public void getQQUser(String r4_String, String r5_String, RequestListener r6_RequestListener) {
        ThirdPartyParameters r0_ThirdPartyParameters = new ThirdPartyParameters();
        r0_ThirdPartyParameters.add(Constants.PARAM_OPEN_ID, r4_String);
        r0_ThirdPartyParameters.add(Constants.PARAM_CONSUMER_KEY, r5_String);
        r0_ThirdPartyParameters.add(ThirdParty.KEY_TOKEN, this.b);
        a("https://graph.qq.com/user/get_user_info", r0_ThirdPartyParameters, HTTPMETHOD_GET, r6_RequestListener);
    }

    public void getSinaUser(long r4j, RequestListener r6_RequestListener) {
        ThirdPartyParameters r0_ThirdPartyParameters = new ThirdPartyParameters();
        r0_ThirdPartyParameters.add("uid", r4j);
        r0_ThirdPartyParameters.add(ThirdParty.KEY_TOKEN, this.b);
        a("https://api.weibo.com/2/users/show.json", r0_ThirdPartyParameters, HTTPMETHOD_GET, r6_RequestListener);
    }
}