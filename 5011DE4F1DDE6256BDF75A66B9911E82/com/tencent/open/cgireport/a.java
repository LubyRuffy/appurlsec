package com.tencent.open.cgireport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// compiled from: ProGuard
class a extends SQLiteOpenHelper {
    final /* synthetic */ ReportDataModal a;

    public a(ReportDataModal r1_ReportDataModal, Context r2_Context, String r3_String, CursorFactory r4_CursorFactory, int r5i) {
        this.a = r1_ReportDataModal;
        super(r2_Context, r3_String, r4_CursorFactory, r5i);
    }

    public void onCreate(SQLiteDatabase r4_SQLiteDatabase) {
        try {
            Log.i("cgi_report_debug", "ReportDataModal onCreate sql1 = create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text)");
            r4_SQLiteDatabase.execSQL("create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text)");
            Log.i("cgi_report_debug", "ReportDataModal onCreate sql2 = create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text)");
            r4_SQLiteDatabase.execSQL("create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text)");
        } catch (Exception e) {
            Log.e("cgi_report_debug", "ReportDataModal onCreate failed");
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase r1_SQLiteDatabase, int r2i, int r3i) {
    }
}