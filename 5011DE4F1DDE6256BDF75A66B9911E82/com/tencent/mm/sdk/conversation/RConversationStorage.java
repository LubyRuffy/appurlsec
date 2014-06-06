package com.tencent.mm.sdk.conversation;

import android.content.Context;
import com.tencent.mm.sdk.storage.ISQLiteDatabase;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import com.tencent.mm.sdk.storage.MAutoStorage;

public class RConversationStorage extends MAutoStorage<RConversation> {
    public static final String AUTHORITY = "com.tencent.mm.sdk.conversation.provider";
    public static final String PRIMARY_KEY = "username";
    public static final String TABLE = "rconversation";

    private RConversationStorage(ISQLiteDatabase r1_ISQLiteDatabase) {
        super(r1_ISQLiteDatabase);
    }

    public static RConversationStorage create(Context r2_Context) {
        return new RConversationStorage(new RConversationDB(r2_Context));
    }

    public RConversation get(String r5_String) {
        MAutoDBItem r0_MAutoDBItem = new RConversation();
        r0_MAutoDBItem.field_username = r5_String;
        String[] r1_StringA = new String[1];
        r1_StringA[0] = PRIMARY_KEY;
        return super.get(r0_MAutoDBItem, r1_StringA) ? r0_MAutoDBItem : null;
    }

    public String[] getColumns() {
        return COLUMNS;
    }

    public String getPrimaryKey() {
        return PRIMARY_KEY;
    }

    public String getTableName() {
        return TABLE;
    }
}