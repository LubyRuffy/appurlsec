package qsbk.app.model;

import android.text.TextUtils;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.push.Utils;
import qsbk.app.utils.Util;

public class Article extends QbBean {
    public boolean allow_comment;
    public boolean anonymous;
    public int comment_count;
    public String content;
    public long created_at;
    public String id;
    public String image;
    public ImageSizes image_size;
    public String location;
    public String login;
    public long published_at;
    public Integer random;
    public String state;
    public String tag;
    public String user_icon;
    public String user_id;
    public int vote_down;
    public int vote_up;

    public Article(JSONObject r2_JSONObject) throws QiushibaikeException {
        a(r2_JSONObject);
        this.random = Integer.valueOf(Util.getRandom());
    }

    private void a(JSONObject r3_JSONObject) throws QiushibaikeException {
        try {
            this.id = r3_JSONObject.getString(LocaleUtil.INDONESIAN);
            this.content = r3_JSONObject.getString(Utils.RESPONSE_CONTENT);
            if (!r3_JSONObject.isNull("comments_count")) {
                this.comment_count = r3_JSONObject.getInt("comments_count");
            }
            if (!r3_JSONObject.isNull("tag")) {
                this.tag = r3_JSONObject.getString("tag");
            }
            if (!r3_JSONObject.isNull(QsbkDatabase.STATE)) {
                this.state = r3_JSONObject.getString(QsbkDatabase.STATE);
            }
            JSONObject r0_JSONObject = r3_JSONObject.optJSONObject(QsbkDatabase.VOTE_TABLE_NAME);
            if (!r0_JSONObject.isNull("up")) {
                this.vote_up = r0_JSONObject.getInt("up");
            }
            if (!r0_JSONObject.isNull("down")) {
                this.vote_down = r0_JSONObject.getInt("down");
            }
            if (!r3_JSONObject.isNull(QsbkDatabase.CREATE_AT)) {
                this.created_at = r3_JSONObject.getLong(QsbkDatabase.CREATE_AT);
            }
            if (!r3_JSONObject.isNull("image")) {
                this.image = r3_JSONObject.getString("image");
            }
            if (!r3_JSONObject.isNull("allow_comment")) {
                this.allow_comment = r3_JSONObject.getBoolean("allow_comment");
            }
            r0_JSONObject = r3_JSONObject.optJSONObject(OneProfileActivity.USER);
            if (r0_JSONObject != null) {
                this.user_id = r0_JSONObject.getString(LocaleUtil.INDONESIAN);
                if (!r0_JSONObject.isNull(QsbkDatabase.LOGIN)) {
                    this.login = r0_JSONObject.getString(QsbkDatabase.LOGIN);
                    if (!TextUtils.isEmpty(this.login)) {
                        this.login = this.login.trim();
                    }
                }
                if (!r0_JSONObject.isNull(QsbkDatabase.ICON)) {
                    this.user_icon = r0_JSONObject.getString(QsbkDatabase.ICON);
                }
                this.anonymous = false;
            } else {
                this.anonymous = true;
            }
            if (!r3_JSONObject.isNull("published_at")) {
                this.published_at = r3_JSONObject.getLong("published_at");
            }
            if (!r3_JSONObject.isNull("image_size")) {
                this.image_size = ImageSizes.newImageSizes(r3_JSONObject.optJSONObject("image_size"));
            }
            if (!r3_JSONObject.isNull(NearByListActivity.DIALOG_KEY_REQ_LOCATION)) {
                this.location = r3_JSONObject.getString(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
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
        Article r5_Article = (Article) r5_Object;
        if (this.id == null) {
            return r5_Article.id == null;
        } else {
            if (this.id.equals(r5_Article.id)) {
                return true;
            }
            return false;
        }
    }

    public String getContent() {
        return this.content;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject r1_JSONObject;
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put(LocaleUtil.INDONESIAN, this.id);
        r0_JSONObject.put(Utils.RESPONSE_CONTENT, this.content);
        r0_JSONObject.put("comments_count", this.comment_count);
        r0_JSONObject.put("tag", this.tag);
        r0_JSONObject.put(QsbkDatabase.STATE, this.state);
        r0_JSONObject.put(QsbkDatabase.CREATE_AT, this.created_at);
        r0_JSONObject.put("image", this.image);
        r0_JSONObject.put("allow_comment", this.allow_comment);
        r0_JSONObject.put("published_at", this.published_at);
        if (this.anonymous) {
            r0_JSONObject.put(OneProfileActivity.USER, null);
        } else {
            r1_JSONObject = new JSONObject();
            r1_JSONObject.put(LocaleUtil.INDONESIAN, this.user_id);
            r1_JSONObject.put(QsbkDatabase.LOGIN, this.login);
            r1_JSONObject.put(QsbkDatabase.ICON, this.user_icon);
            r0_JSONObject.put(OneProfileActivity.USER, r1_JSONObject);
        }
        r1_JSONObject = new JSONObject();
        r1_JSONObject.put("up", this.vote_up);
        r1_JSONObject.put("down", this.vote_down);
        r0_JSONObject.put(QsbkDatabase.VOTE_TABLE_NAME, r1_JSONObject);
        r0_JSONObject.put("image_size", ImageSizes.toJSONObject(this.image_size));
        r0_JSONObject.put(NearByListActivity.DIALOG_KEY_REQ_LOCATION, this.location);
        return r0_JSONObject;
    }
}