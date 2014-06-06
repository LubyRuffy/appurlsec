package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;

public interface ISQLiteDatabase {
    public int delete(String r1_String, String r2_String, String[] r3_StringA);

    public boolean execSQL(String r1_String, String r2_String);

    public long insert(String r1_String, String r2_String, ContentValues r3_ContentValues);

    public Cursor query(String r1_String, String[] r2_StringA, String r3_String, String[] r4_StringA, String r5_String, String r6_String, String r7_String);

    public Cursor rawQuery(String r1_String, String[] r2_StringA);

    public long replace(String r1_String, String r2_String, ContentValues r3_ContentValues);

    public int update(String r1_String, ContentValues r2_ContentValues, String r3_String, String[] r4_StringA);
}