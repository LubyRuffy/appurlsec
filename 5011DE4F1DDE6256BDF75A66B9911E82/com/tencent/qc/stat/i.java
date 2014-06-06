package com.tencent.qc.stat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// compiled from: ProGuard
class i extends SQLiteOpenHelper {
    private static String a;
    private static int b;

    static {
        a = "qc_tencent_analysis.db";
        b = 2;
    }

    public i(Context r4_Context) {
        super(r4_Context, a, null, b);
    }

    public void onCreate(SQLiteDatabase r2_SQLiteDatabase) {
        r2_SQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        r2_SQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        r2_SQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        r2_SQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
    }

    public void onUpgrade(SQLiteDatabase r4_SQLiteDatabase, int r5i, int r6i) {
        StatStore.e.g("upgrade DB from oldVersion " + r5i + " to newVersion " + r6i);
        if (r5i == 1) {
            r4_SQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
        }
    }
}