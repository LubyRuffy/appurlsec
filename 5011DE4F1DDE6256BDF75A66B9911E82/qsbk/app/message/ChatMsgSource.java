package qsbk.app.message;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.json.JSONAble;

public class ChatMsgSource extends JSONAble {
    public static final int FROM_ARTICLE = 2;
    public static final int FROM_COMMENT = 3;
    public static final int FROM_NEARBY = 1;
    public String from_id;
    public String to_id;
    public int type;
    public ChatMsgValueObj valueObj;

    public ChatMsgSource() {
        this.valueObj = new ChatMsgValueObj();
    }

    public ChatMsgSource(int r3i, String r4_String, String r5_String) {
        this.valueObj = new ChatMsgValueObj();
        this.type = r3i;
        this.to_id = r4_String;
        this.from_id = QsbkApp.currentUser.userId;
        if (this.type == 1) {
            try {
                this.valueObj.distance = Integer.parseInt(r5_String);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.type == 2 || this.type == 3) {
            this.valueObj.artid = r5_String;
        }
    }

    public static ChatMsgSource getMsgSourceFromLocalById(String r4_String) {
        if (QsbkApp.currentUser != null) {
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = QsbkApp.currentUser.userId;
            r2_ObjectA[1] = r4_String;
            String r0_String = SharePreferenceUtils.getSharePreferencesValue(String.format("msg_source_%s_%s", r2_ObjectA));
            if (!TextUtils.isEmpty(r0_String)) {
                try {
                    JSONObject r1_JSONObject = new JSONObject(r0_String);
                    ChatMsgSource r0_ChatMsgSource = new ChatMsgSource();
                    r0_ChatMsgSource.parseFromJSONObject(r1_JSONObject);
                    return r0_ChatMsgSource;
                } catch (Exception e) {
                    LogUtil.d("get msg source from local fail");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void putMsgSourceToLocal(ChatMsgSource r5_ChatMsgSource) {
        if (QsbkApp.currentUser == null || r5_ChatMsgSource == null) {
        } else {
            String r0_String;
            String r1_String = QsbkApp.currentUser.userId;
            r0_String = r1_String.equals(r5_ChatMsgSource.from_id) ? r5_ChatMsgSource.to_id : r5_ChatMsgSource.from_id;
            Object[] r3_ObjectA = new Object[2];
            r3_ObjectA[0] = r1_String;
            r3_ObjectA[1] = r0_String;
            SharePreferenceUtils.setSharePreferencesValue(String.format("msg_source_%s_%s", r3_ObjectA), r5_ChatMsgSource.encodeToJsonObject().toString());
        }
    }

    public JSONObject encodeToJsonObject() {
        JSONObject r1_JSONObject = super.encodeToJsonObject();
        try {
            r1_JSONObject.putOpt(SharedPref.VALUE, this.valueObj.encodeToJsonObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r1_JSONObject;
    }

    public SpannableString getPresentText() {
        String r1_String = RContactStorage.PRIMARY_KEY;
        Object[] r2_ObjectA;
        String r2_String;
        Object[] r3_ObjectA;
        String r3_String;
        SpannableString r0_SpannableString;
        if (this.type == 1) {
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r1_String;
            r1_String = String.format("%s\u901a\u8fc7 ", r2_ObjectA);
            r2_String = "[nearby]";
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = NearbyUser.getDistanceStr(this.valueObj.distance);
            r3_String = String.format("\u9644\u8fd1(%s)", r3_ObjectA);
            r0_SpannableString = new SpannableString(r1_String + r2_String + r3_String + " \u53d1\u8d77\u4f1a\u8bdd");
            Drawable r4_Drawable = QsbkApp.mContext.getResources().getDrawable(R.drawable.a_nearby_icon);
            r4_Drawable.setBounds(0, 0, r4_Drawable.getIntrinsicWidth(), r4_Drawable.getIntrinsicHeight());
            r0_SpannableString.setSpan(new ImageSpan(r4_Drawable, 1), r1_String.length(), (r1_String + r2_String).length(), R.styleable.ActionBar_progressBarPadding);
            r0_SpannableString.setSpan(new ForegroundColorSpan(-1), (r1_String + r2_String).length(), (r1_String + r2_String + r3_String).length(), R.styleable.ActionBar_progressBarPadding);
            return r0_SpannableString;
        } else if (this.type == 2) {
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r1_String;
            r2_String = String.format("%s\u901a\u8fc7 ", r2_ObjectA);
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = this.valueObj.artid;
            r3_String = String.format("#\u7cd7\u4e8b%s", r3_ObjectA);
            r4_String = r2_String + r3_String + " \u53d1\u8d77\u4f1a\u8bdd \u2192";
            r5_ObjectA = new Object[2];
            r5_ObjectA[0] = r1_String;
            r5_ObjectA[1] = this.valueObj.artid;
            r0_SpannableString = new SpannableString(String.format(r4_String, r5_ObjectA));
            r0_SpannableString.setSpan(new ForegroundColorSpan(-1), r2_String.length(), (r2_String + r3_String).length(), R.styleable.ActionBar_progressBarPadding);
            return r0_SpannableString;
        } else {
            if (this.type != 3) {
                return null;
            }
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r1_String;
            r2_String = String.format("%s\u901a\u8fc7 ", r2_ObjectA);
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = this.valueObj.artid;
            r3_String = String.format("#\u7cd7\u4e8b%s\u7684\u8bc4\u8bba", r3_ObjectA);
            r4_String = r2_String + r3_String + " \u53d1\u8d77\u4f1a\u8bdd \u2192";
            r5_ObjectA = new Object[2];
            r5_ObjectA[0] = r1_String;
            r5_ObjectA[1] = this.valueObj.artid;
            r0_SpannableString = new SpannableString(String.format(r4_String, r5_ObjectA));
            r0_SpannableString.setSpan(new ForegroundColorSpan(-1), r2_String.length(), (r2_String + r3_String).length(), R.styleable.ActionBar_progressBarPadding);
            return r0_SpannableString;
        }
    }

    public void parseFromJSONObject(JSONObject r4_JSONObject) {
        JSONObject r0_JSONObject;
        super.parseFromJSONObject(r4_JSONObject);
        try {
            r0_JSONObject = new JSONObject(r4_JSONObject.optString(SharedPref.VALUE));
        } catch (Exception e) {
            e.printStackTrace();
            r0_JSONObject = null;
        }
        this.valueObj.parseFromJSONObject(r0_JSONObject);
    }
}