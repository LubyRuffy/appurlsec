package com.tencent.mm.sdk.plugin;

import android.net.Uri;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.conversation.RConversationStorage;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import java.lang.reflect.Field;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;

public class Profile extends MAutoDBItem {
    public static final Uri CONTENT_URI;
    private static final Field[] a;
    public static String[] columns;
    public String field_alias;
    public String field_avatar;
    public String field_bindemail;
    public String field_bindmobile;
    public long field_bindqq;
    public String field_city;
    public String field_nickname;
    public String field_province;
    public String field_signature;
    public String field_username;
    public String field_weibo;

    static {
        CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/profile");
        String[] r0_StringA = new String[11];
        r0_StringA[0] = RConversationStorage.PRIMARY_KEY;
        r0_StringA[1] = "bindqq";
        r0_StringA[2] = "bindmobile";
        r0_StringA[3] = "bindemail";
        r0_StringA[4] = RContact.COL_ALIAS;
        r0_StringA[5] = RContact.COL_NICKNAME;
        r0_StringA[6] = EDIT_TYPE.TYPE_SIGNATURE;
        r0_StringA[7] = "province";
        r0_StringA[8] = "city";
        r0_StringA[9] = "weibo";
        r0_StringA[10] = "avatar";
        columns = r0_StringA;
        a = MAutoDBItem.getValidFields(Profile.class);
    }

    protected Field[] fields() {
        return a;
    }
}