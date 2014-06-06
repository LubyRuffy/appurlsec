package qsbk.app.common;

import android.os.Build;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.UUID;
import org.apache.cordova.Globalization;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;

public class AuditPlugin extends CordovaPlugin {
    private JSONObject a() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("Source", "android_" + Constants.localVersionName);
            r1_JSONObject.put("Model", Build.MODEL);
            if (QsbkApp.currentUser != null) {
                r1_JSONObject.put("Qbtoken", QsbkApp.currentUser.token);
            }
            r1_JSONObject.put("Uuid", DeviceUtils.getAndroidId());
            r1_JSONObject.put("Deviceidinfo", DeviceUtils.getDeviceIdInfo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r1_JSONObject;
    }

    private JSONObject a(String r7_String) {
        JSONObject r1_JSONObject = new JSONObject();
        String r0_String;
        try {
            String r0_String_2;
            JSONObject r0_JSONObject = new JSONObject(r7_String);
            String r2_String = UUID.randomUUID().toString().trim().replaceAll("-", RContactStorage.PRIMARY_KEY);
            String r3_String = RContactStorage.PRIMARY_KEY;
            r0_String_2 = (r0_JSONObject.isNull(LocaleUtil.INDONESIAN) || r0_JSONObject.isNull(KEYS.RET) || r0_JSONObject.isNull(Globalization.TIME) || r0_JSONObject.length() != 3) ? HttpClient.getIntentce().verifyStringEncode(r7_String, UUID.randomUUID().toString().trim().replaceAll("-", RContactStorage.PRIMARY_KEY)) : HttpClient.getIntentce().verifyStringEncode(r7_String, r2_String);
            r1_JSONObject.put("qb", r2_String);
            r1_JSONObject.put("Url-Verify", r0_String_2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r1_JSONObject;
    }

    private void b() {
        QsbkApp.audit.finish();
        QsbkApp.audit = null;
    }

    public boolean execute(String r4_String, JSONArray r5_JSONArray, CallbackContext r6_CallbackContext) throws JSONException {
        boolean r0z = false;
        if (r4_String.equals("reviewquit")) {
            b();
            return true;
        } else if (r4_String.equals("reviewsign")) {
            if (r5_JSONArray.length() == 1) {
                r6_CallbackContext.success(a((String) r5_JSONArray.get(r0z)));
            }
            return true;
        } else {
            if (!r4_String.equals("headers")) {
                return false;
            }
            r6_CallbackContext.success(a());
            return true;
        }
    }

    public Object onMessage(String r2_String, Object r3_Object) {
        return super.onMessage(r2_String, r3_Object);
    }
}