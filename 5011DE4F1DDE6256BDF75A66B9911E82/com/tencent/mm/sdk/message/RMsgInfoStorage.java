package com.tencent.mm.sdk.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.mm.sdk.storage.ISQLiteDatabase;
import com.tencent.mm.sdk.storage.MStorage;
import qsbk.app.push.Utils;

public class RMsgInfoStorage extends MStorage {
    public static final String AUTHORITY = "com.tencent.mm.sdk.msginfo.provider";
    public static final String PRIMARY_KEY = "msgId";
    ISQLiteDatabase a;

    private RMsgInfoStorage(ISQLiteDatabase r2_ISQLiteDatabase) {
        this.a = null;
        this.a = r2_ISQLiteDatabase;
    }

    public static RMsgInfoStorage create(Context r2_Context) {
        return new RMsgInfoStorage(new RMsgInfoDB(r2_Context));
    }

    public int doDelete(long r7j) {
        ISQLiteDatabase r0_ISQLiteDatabase = this.a;
        String r1_String = Utils.EXTRA_MESSAGE;
        String[] r3_StringA = new String[1];
        r3_StringA[0] = String.valueOf(r7j);
        int r0i = r0_ISQLiteDatabase.delete(r1_String, "msgId=?", r3_StringA);
        if (r0i > 0) {
            notify();
        }
        return r0i;
    }

    public long doInsert(RMsgInfo r5_RMsgInfo) {
        if (r5_RMsgInfo == null || Util.isNullOrNil(r5_RMsgInfo.field_talker)) {
            return -1;
        }
        long r0j = this.a.insert(Utils.EXTRA_MESSAGE, PRIMARY_KEY, r5_RMsgInfo.convertTo());
        if (r0j <= 0) {
            return r0j;
        }
        notify();
        return r0j;
    }

    public int doUpdate(long r8j, RMsgInfo r10_RMsgInfo) {
        if (r10_RMsgInfo == null || Util.isNullOrNil(r10_RMsgInfo.field_talker)) {
            return -1;
        }
        ContentValues r0_ContentValues = r10_RMsgInfo.convertTo();
        ISQLiteDatabase r1_ISQLiteDatabase = this.a;
        String r2_String = Utils.EXTRA_MESSAGE;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = String.valueOf(r8j);
        int r0i = r1_ISQLiteDatabase.update(r2_String, r0_ContentValues, "msgId=?", r4_StringA);
        if (r0i <= 0) {
            return r0i;
        }
        notify();
        return r0i;
    }

    public RMsgInfo getMsgById(long r9j) {
        ISQLiteDatabase r0_ISQLiteDatabase = this.a;
        String r1_String = Utils.EXTRA_MESSAGE;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = String.valueOf(r9j);
        Cursor r0_Cursor = r0_ISQLiteDatabase.query(r1_String, null, "msgId=?", r4_StringA, null, null, null);
        if (r0_Cursor == null) {
            return null;
        }
        if (r0_Cursor.getCount() == 0) {
            r0_Cursor.close();
            return null;
        } else {
            RMsgInfo r2_RMsgInfo = new RMsgInfo();
            r2_RMsgInfo.convertFrom(r0_Cursor);
            return r2_RMsgInfo;
        }
    }

    public Cursor getMsgByTalker(String r9_String) {
        ISQLiteDatabase r0_ISQLiteDatabase = this.a;
        String r1_String = Utils.EXTRA_MESSAGE;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = r9_String;
        return r0_ISQLiteDatabase.query(r1_String, null, "talker=?", r4_StringA, null, null, null);
    }
}