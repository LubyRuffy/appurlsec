package qsbk.app.bean;

import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.tauth.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.QbBean;

public class AdBean extends QbBean {
    public int confirm;
    public String content;
    public int count;
    public String id;
    public String imageUrl;
    public String optionText;
    public int pos;
    public String size;
    public String title;
    public int type;
    public String url;
    public String usersNum;

    public AdBean(JSONObject r2_JSONObject) {
        this.count = 0;
        this.pos = 5;
        try {
            if (!r2_JSONObject.isNull(LocaleUtil.INDONESIAN)) {
                this.id = r2_JSONObject.getString(LocaleUtil.INDONESIAN);
            }
            if (!r2_JSONObject.isNull(QsbkDatabase.TYPE)) {
                this.type = r2_JSONObject.getInt(QsbkDatabase.TYPE);
            }
            if (!r2_JSONObject.isNull("count")) {
                this.count = r2_JSONObject.getInt("count");
            }
            if (!r2_JSONObject.isNull(Constants.PARAM_URL)) {
                this.url = r2_JSONObject.getString(Constants.PARAM_URL);
            }
            if (!r2_JSONObject.isNull("confirm")) {
                this.confirm = r2_JSONObject.getInt("confirm");
            }
            if (!r2_JSONObject.isNull(Constants.PARAM_TITLE)) {
                this.title = r2_JSONObject.getString(Constants.PARAM_TITLE);
            }
            if (!r2_JSONObject.isNull("body")) {
                this.content = r2_JSONObject.getString("body");
            }
            if (!r2_JSONObject.isNull(Constants.PARAM_IMG_URL)) {
                this.imageUrl = r2_JSONObject.getString(Constants.PARAM_IMG_URL);
            }
            if (!r2_JSONObject.isNull("usersNum")) {
                this.usersNum = r2_JSONObject.getString("usersNum");
            }
            if (!r2_JSONObject.isNull("size")) {
                this.size = r2_JSONObject.getString("size");
            }
            if (!r2_JSONObject.isNull("optionText")) {
                this.optionText = r2_JSONObject.getString("optionText");
            }
            if (!r2_JSONObject.isNull("pos")) {
                this.pos = r2_JSONObject.getInt("pos");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}