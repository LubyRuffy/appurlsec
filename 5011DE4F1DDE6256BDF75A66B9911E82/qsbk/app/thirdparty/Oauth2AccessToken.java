package qsbk.app.thirdparty;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;

public class Oauth2AccessToken {
    private String a;
    private String b;
    private long c;

    public Oauth2AccessToken() {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = 0;
    }

    public Oauth2AccessToken(String r3_String) {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = 0;
        if (r3_String == null || r3_String.indexOf("{") < 0) {
        } else {
            try {
                JSONObject r0_JSONObject = new JSONObject(r3_String);
                setToken(r0_JSONObject.optString(ThirdParty.KEY_TOKEN));
                setExpiresIn(r0_JSONObject.optString(ThirdParty.KEY_EXPIRES));
                setRefreshToken(r0_JSONObject.optString(ThirdParty.KEY_REFRESHTOKEN));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Oauth2AccessToken(String r7_String, String r8_String) {
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = 0;
        this.a = r7_String;
        this.c = System.currentTimeMillis() + Long.parseLong(r8_String) * 1000;
    }

    public long getExpiresTime() {
        return this.c;
    }

    public String getRefreshToken() {
        return this.b;
    }

    public String getToken() {
        return this.a;
    }

    public boolean isSessionValid() {
        if (!TextUtils.isEmpty(this.a)) {
            if (this.c == 0 || System.currentTimeMillis() < this.c) {
                return true;
            }
        }
        return false;
    }

    public void setExpiresIn(String r7_String) {
        if (r7_String == null || r7_String.equals("0")) {
        } else {
            setExpiresTime(System.currentTimeMillis() + Long.parseLong(r7_String) * 1000);
        }
    }

    public void setExpiresTime(long r1j) {
        this.c = r1j;
    }

    public void setRefreshToken(String r1_String) {
        this.b = r1_String;
    }

    public void setToken(String r1_String) {
        this.a = r1_String;
    }
}