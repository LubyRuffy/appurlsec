package com.tencent.open.cgireport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: ProGuard
public class ReportDataModal {
    private SQLiteDatabase a;
    private a b;
    private int c;

    public ReportDataModal(Context r7_Context) {
        this.b = new a(this, r7_Context, "sdk_cgi_report.db", null, 1);
        this.c = c().size();
    }

    public synchronized int a(ArrayList r7_ArrayList) {
        int r1i;
        Log.i("cgi_report_debug", "ReportDataModal backupOldItems count = " + r7_ArrayList.size());
        Iterator r2_Iterator = r7_ArrayList.iterator();
        r1i = 0;
        while (r2_Iterator.hasNext()) {
            reportItem r0_reportItem = (reportItem) r2_Iterator.next();
            ContentValues r3_ContentValues = new ContentValues();
            r3_ContentValues.put("apn", r0_reportItem.a());
            r3_ContentValues.put("frequency", r0_reportItem.b());
            r3_ContentValues.put("commandid", r0_reportItem.c());
            r3_ContentValues.put("resultcode", r0_reportItem.d());
            r3_ContentValues.put("tmcost", r0_reportItem.e());
            r3_ContentValues.put("reqsize", r0_reportItem.f());
            r3_ContentValues.put("rspsize", r0_reportItem.g());
            try {
                this.a = this.b.getWritableDatabase();
                this.a.insertOrThrow("olddata", null, r3_ContentValues);
                this.a.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            r1i++;
        }
        Log.i("cgi_report_debug", "ReportDataModal backupOldItems succ_count = " + r1i);
        return r1i;
    }

    public synchronized boolean a() {
        boolean r0z = false;
        synchronized (this) {
            Log.i("cgi_report_debug", "ReportDataModal deleteOldItems start");
            try {
                this.a = this.b.getWritableDatabase();
                this.a.execSQL("delete from olddata;");
                this.a.close();
                Log.i("cgi_report_debug", "ReportDataModal deleteOldItems success");
                r0z = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r0z;
    }

    public synchronized boolean a(String r6_String, String r7_String, String r8_String, int r9i, long r10j, long r12j, long r14j) {
        boolean r0z = false;
        synchronized (this) {
            if (r8_String.contains("?")) {
                r8_String = r8_String.split("\\?")[0];
            }
            Log.i("cgi_report_debug", "ReportDataModal addNewItem apn=" + r6_String + ",frequency=" + r7_String + ",commandid=" + r8_String + ",resultCode=" + r9i + ",costTime=" + r10j + ",reqSzie=" + r12j + ",rspSize=" + r14j);
            ContentValues r1_ContentValues = new ContentValues();
            r1_ContentValues.put("apn", r6_String + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("frequency", r7_String + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("commandid", r8_String + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("resultcode", r9i + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("tmcost", r10j + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("reqsize", r12j + RContactStorage.PRIMARY_KEY);
            r1_ContentValues.put("rspsize", r14j + RContactStorage.PRIMARY_KEY);
            try {
                this.a = this.b.getWritableDatabase();
                this.a.insertOrThrow("newdata", null, r1_ContentValues);
                this.a.close();
                Log.i("cgi_report_debug", "ReportDataModal addNewItem success");
                this.c++;
                r0z = true;
            } catch (Exception e) {
                Exception r1_Exception = e;
                Log.e("cgi_report_debug", "ReportDataModal addNewItem failed");
                r1_Exception.printStackTrace();
            }
        }
        return r0z;
    }

    public synchronized boolean b() {
        boolean r0z = false;
        synchronized (this) {
            Log.i("cgi_report_debug", "ReportDataModal deleteNewItems start");
            try {
                this.a = this.b.getWritableDatabase();
                this.a.execSQL("delete from newdata;");
                this.c = 0;
                this.a.close();
                Log.i("cgi_report_debug", "ReportDataModal deleteNewItems start");
                r0z = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r0z;
    }

    public synchronized ArrayList c() {
        ArrayList r0_ArrayList;
        Log.i("cgi_report_debug", "ReportDataModal getNewItems start");
        ArrayList r8_ArrayList = new ArrayList();
        try {
            this.a = this.b.getReadableDatabase();
            Cursor r9_Cursor = this.a.rawQuery("select * from newdata", new String[0]);
            r9_Cursor.moveToFirst();
            while (!r9_Cursor.isAfterLast()) {
                r8_ArrayList.add(new reportItem(r9_Cursor.getString(r9_Cursor.getColumnIndex("apn")), r9_Cursor.getString(r9_Cursor.getColumnIndex("frequency")), r9_Cursor.getString(r9_Cursor.getColumnIndex("commandid")), r9_Cursor.getString(r9_Cursor.getColumnIndex("resultcode")), r9_Cursor.getString(r9_Cursor.getColumnIndex("tmcost")), r9_Cursor.getString(r9_Cursor.getColumnIndex("reqsize")), r9_Cursor.getString(r9_Cursor.getColumnIndex("rspsize"))));
                r9_Cursor.moveToNext();
            }
            r9_Cursor.close();
            this.a.close();
            Log.i("cgi_report_debug", "ReportDataModal getNewItems success, count = " + r8_ArrayList.size());
            r0_ArrayList = r8_ArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            r0_ArrayList = r8_ArrayList;
        }
        return r0_ArrayList;
    }

    public synchronized ArrayList d() {
        ArrayList r0_ArrayList;
        Log.i("cgi_report_debug", "ReportDataModal getOldItems start");
        ArrayList r8_ArrayList = new ArrayList();
        try {
            this.a = this.b.getReadableDatabase();
            Cursor r9_Cursor = this.a.rawQuery("select * from olddata", new String[0]);
            r9_Cursor.moveToFirst();
            while (!r9_Cursor.isAfterLast()) {
                r8_ArrayList.add(new reportItem(r9_Cursor.getString(r9_Cursor.getColumnIndex("apn")), r9_Cursor.getString(r9_Cursor.getColumnIndex("frequency")), r9_Cursor.getString(r9_Cursor.getColumnIndex("commandid")), r9_Cursor.getString(r9_Cursor.getColumnIndex("resultcode")), r9_Cursor.getString(r9_Cursor.getColumnIndex("tmcost")), r9_Cursor.getString(r9_Cursor.getColumnIndex("reqsize")), r9_Cursor.getString(r9_Cursor.getColumnIndex("rspsize"))));
                r9_Cursor.moveToNext();
            }
            r9_Cursor.close();
            this.a.close();
            Log.i("cgi_report_debug", "ReportDataModal getOldItems success, count = " + r8_ArrayList.size());
            r0_ArrayList = r8_ArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            r0_ArrayList = r8_ArrayList;
        }
        return r0_ArrayList;
    }

    public int e() {
        Log.i("cgi_report_debug", "ReportDataModal getTotalCount count = " + this.c);
        return this.c;
    }
}