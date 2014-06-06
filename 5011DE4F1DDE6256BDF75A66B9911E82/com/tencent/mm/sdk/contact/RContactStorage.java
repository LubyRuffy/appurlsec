package com.tencent.mm.sdk.contact;

import android.content.Context;
import com.tencent.mm.sdk.conversation.RConversationStorage;
import com.tencent.mm.sdk.storage.ISQLiteDatabase;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import com.tencent.mm.sdk.storage.MAutoStorage;

public class RContactStorage extends MAutoStorage<RContact> {
    public static final String AUTHORITY = "com.tencent.mm.sdk.contact.provider";
    public static final String PRIMARY_KEY = "";
    public static final String TABLE = "rcontact";

    private RContactStorage(ISQLiteDatabase r1_ISQLiteDatabase) {
        super(r1_ISQLiteDatabase);
    }

    public static RContactStorage create(Context r2_Context) {
        return new RContactStorage(new RContactDB(r2_Context));
    }

    public RContact get(String r5_String) {
        MAutoDBItem r0_MAutoDBItem = new RContact();
        r0_MAutoDBItem.field_username = r5_String;
        String[] r1_StringA = new String[1];
        r1_StringA[0] = RConversationStorage.PRIMARY_KEY;
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