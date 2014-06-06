package qsbk.app.model;

import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.push.Utils;

public class AuditArticle implements Serializable {
    public String content;
    public String id;
    public String image;
    public String tag;

    public AuditArticle(JSONObject r1_JSONObject) throws QiushibaikeException {
        a(r1_JSONObject);
    }

    private void a(JSONObject r2_JSONObject) throws QiushibaikeException {
        try {
            this.id = r2_JSONObject.getString(LocaleUtil.INDONESIAN);
            if (!r2_JSONObject.isNull(Utils.RESPONSE_CONTENT)) {
                this.content = r2_JSONObject.getString(Utils.RESPONSE_CONTENT);
            }
            if (!r2_JSONObject.isNull("tag")) {
                this.tag = r2_JSONObject.getString("tag");
            }
            if (!r2_JSONObject.isNull("image")) {
                this.image = r2_JSONObject.getString("image");
            }
        } catch (Exception e) {
        }
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        AuditArticle r5_AuditArticle = (AuditArticle) r5_Object;
        if (this.id == null) {
            return r5_AuditArticle.id == null;
        } else {
            if (this.id.equals(r5_AuditArticle.id)) {
                return true;
            }
            return false;
        }
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put(LocaleUtil.INDONESIAN, this.id);
        r0_JSONObject.put(Utils.RESPONSE_CONTENT, this.content);
        r0_JSONObject.put("tag", this.tag);
        r0_JSONObject.put("image", this.image);
        return r0_JSONObject;
    }
}