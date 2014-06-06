package qsbk.app.model;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.io.Serializable;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;

public class UserInfo extends ExpandUserInfo implements Serializable {
    public int a;
    public String email;
    public String qq;
    public String state;
    public int t;
    public int t_all;
    public String token;
    public String wb;

    public UserInfo() {
        this.email = RContactStorage.PRIMARY_KEY;
        this.wb = RContactStorage.PRIMARY_KEY;
        this.qq = RContactStorage.PRIMARY_KEY;
        this.t_all = 0;
        this.t = 0;
        this.a = 0;
    }

    public UserInfo(String r3_String) {
        this.email = RContactStorage.PRIMARY_KEY;
        this.wb = RContactStorage.PRIMARY_KEY;
        this.qq = RContactStorage.PRIMARY_KEY;
        this.t_all = 0;
        this.t = 0;
        this.a = 0;
        try {
            parseFromJSONObject(new JSONObject(r3_String));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserInfo fromServerJson(JSONObject r5_JSONObject) {
        UserInfo r2_UserInfo = new UserInfo();
        if (r5_JSONObject == null) {
            return r2_UserInfo;
        }
        r2_UserInfo.token = r5_JSONObject.optString(QsbkDatabase.TOKEN);
        JSONObject r3_JSONObject = r5_JSONObject.optJSONObject(OneProfileActivity.USER);
        if (r3_JSONObject != null) {
            String r1_String;
            r2_UserInfo.userId = r3_JSONObject.optString(LocaleUtil.INDONESIAN);
            r2_UserInfo.userIcon = r3_JSONObject.optString(QsbkDatabase.ICON);
            r2_UserInfo.userName = r3_JSONObject.optString(QsbkDatabase.LOGIN, RContactStorage.PRIMARY_KEY).replace("\"", RContactStorage.PRIMARY_KEY);
            r2_UserInfo.state = r3_JSONObject.optString(QsbkDatabase.STATE);
            String r0_String = r3_JSONObject.optString(QsbkDatabase.USER_EMAIL, RContactStorage.PRIMARY_KEY);
            if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                r0_String = RContactStorage.PRIMARY_KEY;
                r1_String = RContactStorage.PRIMARY_KEY;
            } else {
                r1_String = RContactStorage.PRIMARY_KEY;
            }
            if (!r3_JSONObject.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                r1_String = r3_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA, RContactStorage.PRIMARY_KEY);
                if (TextUtils.isEmpty(r1_String) || r1_String.equals("null")) {
                    r1_String = RContactStorage.PRIMARY_KEY;
                }
                if (r3_JSONObject.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                    r1_String = r3_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    if (TextUtils.isEmpty(r1_String) || r1_String.equals("null")) {
                        r1_String = RContactStorage.PRIMARY_KEY;
                    }
                }
                r2_UserInfo.qq = r1_String;
                r2_UserInfo.email = r0_String;
            }
            r2_UserInfo.wb = r1_String;
            r1_String = RContactStorage.PRIMARY_KEY;
            if (r3_JSONObject.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                r2_UserInfo.qq = r1_String;
                r2_UserInfo.email = r0_String;
            } else {
                r1_String = r3_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                if (TextUtils.isEmpty(r1_String) || r1_String.equals("null")) {
                    r1_String = RContactStorage.PRIMARY_KEY;
                    r2_UserInfo.qq = r1_String;
                    r2_UserInfo.email = r0_String;
                } else {
                    r2_UserInfo.qq = r1_String;
                    r2_UserInfo.email = r0_String;
                }
            }
        }
        JSONObject r0_JSONObject = r5_JSONObject.optJSONObject("userdata");
        if (r0_JSONObject == null || (!r0_JSONObject.has("articles"))) {
            return r2_UserInfo;
        }
        r0_JSONObject = r0_JSONObject.optJSONObject("articles");
        r2_UserInfo.t_all = r0_JSONObject.optInt(QsbkDatabase.T_ALL);
        r2_UserInfo.t = r0_JSONObject.optInt(QsbkDatabase.T);
        r2_UserInfo.a = r0_JSONObject.optInt(QsbkDatabase.A);
        return r2_UserInfo;
    }

    public static UserInfo updateServerJson(UserInfo r4_UserInfo, JSONObject r5_JSONObject) {
        if (r4_UserInfo == null) {
            r4_UserInfo = new UserInfo();
        }
        if (r5_JSONObject.has(QsbkDatabase.TOKEN)) {
            r4_UserInfo.token = r5_JSONObject.optString(QsbkDatabase.TOKEN);
        }
        JSONObject r1_JSONObject = r5_JSONObject.optJSONObject(OneProfileActivity.USER);
        if (r1_JSONObject != null) {
            String r0_String;
            if (r1_JSONObject.has(LocaleUtil.INDONESIAN)) {
                r4_UserInfo.userId = r1_JSONObject.optString(LocaleUtil.INDONESIAN);
            }
            if (r1_JSONObject.has(QsbkDatabase.ICON)) {
                r4_UserInfo.userIcon = r1_JSONObject.optString(QsbkDatabase.ICON);
            }
            if (r1_JSONObject.has(QsbkDatabase.LOGIN)) {
                r4_UserInfo.userName = r1_JSONObject.optString(QsbkDatabase.LOGIN, RContactStorage.PRIMARY_KEY).replace("\"", RContactStorage.PRIMARY_KEY);
            }
            if (r1_JSONObject.has(QsbkDatabase.STATE)) {
                r4_UserInfo.state = r1_JSONObject.optString(QsbkDatabase.STATE);
            }
            if (r1_JSONObject.has(QsbkDatabase.USER_EMAIL)) {
                r0_String = r1_JSONObject.optString(QsbkDatabase.USER_EMAIL, RContactStorage.PRIMARY_KEY);
                if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                    r0_String = RContactStorage.PRIMARY_KEY;
                    r4_UserInfo.email = r0_String;
                } else {
                    r4_UserInfo.email = r0_String;
                }
            }
            if (r1_JSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                r0_String = r1_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA, RContactStorage.PRIMARY_KEY);
                if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                    r0_String = RContactStorage.PRIMARY_KEY;
                    r4_UserInfo.wb = r0_String;
                } else {
                    r4_UserInfo.wb = r0_String;
                }
            }
            if (r1_JSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                r0_String = r1_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, RContactStorage.PRIMARY_KEY);
                if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                    r0_String = RContactStorage.PRIMARY_KEY;
                    r4_UserInfo.qq = r0_String;
                } else {
                    r4_UserInfo.qq = r0_String;
                }
            }
        }
        JSONObject r0_JSONObject = r5_JSONObject.optJSONObject("userdata");
        if (r0_JSONObject == null || (!r0_JSONObject.has("articles"))) {
            return r4_UserInfo;
        }
        r0_JSONObject = r0_JSONObject.optJSONObject("articles");
        if (r0_JSONObject.has(QsbkDatabase.T_ALL)) {
            r4_UserInfo.t_all = r0_JSONObject.optInt(QsbkDatabase.T_ALL);
        }
        if (r0_JSONObject.has(QsbkDatabase.T)) {
            r4_UserInfo.t = r0_JSONObject.optInt(QsbkDatabase.T);
        }
        if (r0_JSONObject.has(QsbkDatabase.A)) {
            r4_UserInfo.a = r0_JSONObject.optInt(QsbkDatabase.A);
        }
        return r4_UserInfo;
    }

    public static UserInfo updateServerJsonNearby(UserInfo r3_UserInfo, JSONObject r4_JSONObject) {
        if (r3_UserInfo == null) {
            r3_UserInfo = new UserInfo();
        }
        if (r4_JSONObject.has(LocaleUtil.INDONESIAN)) {
            r3_UserInfo.userId = r4_JSONObject.optString(LocaleUtil.INDONESIAN);
        }
        if (r4_JSONObject.has(QsbkDatabase.LOGIN)) {
            r3_UserInfo.userName = r4_JSONObject.optString(QsbkDatabase.LOGIN, RContactStorage.PRIMARY_KEY).replace("\"", RContactStorage.PRIMARY_KEY);
        }
        if (r4_JSONObject.has(EDIT_TYPE.TYPE_SIGNATURE)) {
            r3_UserInfo.signature = r4_JSONObject.optString(EDIT_TYPE.TYPE_SIGNATURE);
        }
        if (r4_JSONObject.has("birthday")) {
            r3_UserInfo.birthday = r4_JSONObject.optLong("birthday");
        }
        if (r4_JSONObject.has(QsbkDatabase.USER_EMAIL)) {
            r3_UserInfo.email = r4_JSONObject.optString(QsbkDatabase.USER_EMAIL);
        }
        if (r4_JSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
            r3_UserInfo.qq = r4_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
        }
        if (r4_JSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
            r3_UserInfo.wb = r4_JSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        }
        if (r4_JSONObject.has("age")) {
            r3_UserInfo.age = r4_JSONObject.optInt("age");
        }
        if (r4_JSONObject.has("astrology")) {
            r3_UserInfo.astrology = r4_JSONObject.optString("astrology");
        }
        if (r4_JSONObject.has(NearByListActivity.DIALOG_KEY_REQ_LOCATION)) {
            r3_UserInfo.location = r4_JSONObject.optString(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        }
        if (r4_JSONObject.has(QsbkDatabase.ICON)) {
            r3_UserInfo.userIcon = r4_JSONObject.optString(QsbkDatabase.ICON);
        }
        if (r4_JSONObject.has(QsbkDatabase.CREATE_AT)) {
            r3_UserInfo.create_at = r4_JSONObject.optLong(QsbkDatabase.CREATE_AT);
        }
        if (r4_JSONObject.has(EDIT_TYPE.TYPE_HOBBY)) {
            r3_UserInfo.hobby = r4_JSONObject.optString(EDIT_TYPE.TYPE_HOBBY);
        }
        if (r4_JSONObject.has("mobile_brand")) {
            r3_UserInfo.mobile_brand = r4_JSONObject.optString("mobile_brand");
        }
        if (r4_JSONObject.has(EDIT_TYPE.TYPE_INTRODUCE)) {
            r3_UserInfo.introduce = r4_JSONObject.optString(EDIT_TYPE.TYPE_INTRODUCE);
        }
        if (r4_JSONObject.has(EDIT_TYPE.TYPE_GENDER)) {
            r3_UserInfo.gender = r4_JSONObject.optString(EDIT_TYPE.TYPE_GENDER);
        }
        return r3_UserInfo;
    }

    public String toString() {
        return encodeToJsonObject().toString();
    }
}