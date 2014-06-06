package com.tencent.mm.sdk.storage;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.tencent.mm.sdk.platformtools.Log;

public abstract class ContentProviderDB<T> implements ISQLiteDatabase {
    private final Context a;

    public ContentProviderDB(Context r1_Context) {
        this.a = r1_Context;
    }

    public int delete(String r4_String, String r5_String, String[] r6_StringA) {
        Uri r0_Uri = getUriFromTable(r4_String);
        if (r0_Uri != null) {
            return this.a.getContentResolver().delete(r0_Uri, r5_String, r6_StringA);
        }
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("get uri from table failed, table=").append(r4_String).toString());
        return 0;
    }

    public boolean execSQL(String r4_String, String r5_String) {
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("do not support, execSQL sql=").append(r5_String).toString());
        return false;
    }

    public abstract Uri getUriFromTable(String r1_String);

    public long insert(String r4_String, String r5_String, ContentValues r6_ContentValues) {
        Uri r0_Uri = getUriFromTable(r4_String);
        if (r0_Uri != null) {
            return ContentUris.parseId(this.a.getContentResolver().insert(r0_Uri, r6_ContentValues));
        }
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("get uri from table failed, table=").append(r4_String).toString());
        return -1;
    }

    public Cursor query(String r7_String, String[] r8_StringA, String r9_String, String[] r10_StringA, String r11_String, String r12_String, String r13_String) {
        Uri r1_Uri = getUriFromTable(r7_String);
        if (r1_Uri == null) {
            Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("get uri from table failed, table=").append(r7_String).toString());
            return new MatrixCursor(r8_StringA);
        } else {
            Cursor r0_Cursor = this.a.getContentResolver().query(r1_Uri, r8_StringA, r9_String, r10_StringA, r13_String);
            return r0_Cursor == null ? new MatrixCursor(r8_StringA) : r0_Cursor;
        }
    }

    public Cursor rawQuery(String r4_String, String[] r5_StringA) {
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("do not support, rawQuery sql=").append(r4_String).toString());
        return null;
    }

    public long replace(String r4_String, String r5_String, ContentValues r6_ContentValues) {
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("do not support, replace table=").append(r4_String).toString());
        return 0;
    }

    public int update(String r4_String, ContentValues r5_ContentValues, String r6_String, String[] r7_StringA) {
        Uri r0_Uri = getUriFromTable(r4_String);
        if (r0_Uri != null) {
            return this.a.getContentResolver().update(r0_Uri, r5_ContentValues, r6_String, r7_StringA);
        }
        Log.e("MicroMsg.SDK.MContentProviderDB", new StringBuilder("get uri from table failed, table=").append(r4_String).toString());
        return 0;
    }
}