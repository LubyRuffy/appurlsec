package qsbk.app.model;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;

public class AppInfo {
    public String appName;
    public String packageName;
    public String versionName;

    public AppInfo(String r2_String, String r3_String, String r4_String) {
        this.appName = RContactStorage.PRIMARY_KEY;
        this.packageName = RContactStorage.PRIMARY_KEY;
        this.versionName = RContactStorage.PRIMARY_KEY;
        this.appName = r2_String;
        this.packageName = r3_String;
        this.versionName = r4_String;
    }

    public String toString() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("n", this.appName);
            r1_JSONObject.put("p", this.packageName);
            r1_JSONObject.put("vn", this.versionName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r1_JSONObject.toString();
    }
}