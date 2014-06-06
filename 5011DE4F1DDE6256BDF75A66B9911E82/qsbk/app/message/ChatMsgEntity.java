package qsbk.app.message;

import com.tencent.mm.sdk.platformtools.LocaleUtil;
import org.json.JSONObject;

public class ChatMsgEntity {
    public String extraArticle;
    public boolean isComMeg;
    public int sendingId;
    public String text;
    public long timestamp;
    public String userIcon;
    public String userId;
    public String userName;

    public ChatMsgEntity() {
        this.extraArticle = null;
        this.isComMeg = true;
        this.sendingId = 0;
    }

    public ChatMsgEntity(String r5_String, String r6_String, String r7_String, String r8_String, boolean r9z) {
        this.extraArticle = null;
        this.isComMeg = true;
        this.sendingId = 0;
        this.userId = r5_String;
        this.userName = r6_String;
        this.userIcon = r7_String;
        this.text = r8_String;
        this.isComMeg = r9z;
        this.timestamp = (long) ((int) (System.currentTimeMillis() / 1000));
        a();
    }

    public ChatMsgEntity(String r3_String, String r4_String, String r5_String, String r6_String, boolean r7z, int r8i) {
        this.extraArticle = null;
        this.isComMeg = true;
        this.sendingId = 0;
        this.userId = r3_String;
        this.userName = r4_String;
        this.userIcon = r5_String;
        this.text = r6_String;
        this.isComMeg = r7z;
        this.timestamp = (long) r8i;
        a();
    }

    private void a() {
        int r0i = this.text.indexOf("@ART:");
        if (r0i > -1) {
            this.extraArticle = this.text.substring(r0i + 5);
            try {
                this.text = this.text.substring(0, r0i);
                String r0_String = new JSONObject(this.extraArticle).getString(LocaleUtil.INDONESIAN);
                StringBuilder r1_StringBuilder = new StringBuilder().append(this.text);
                Object[] r3_ObjectA = new Object[1];
                r3_ObjectA[0] = r0_String;
                this.text = r1_StringBuilder.append(String.format("@%s#(\u70b9\u51fb\u6211)", r3_ObjectA)).toString();
            } catch (Exception e) {
                this.extraArticle = null;
            }
        }
    }
}