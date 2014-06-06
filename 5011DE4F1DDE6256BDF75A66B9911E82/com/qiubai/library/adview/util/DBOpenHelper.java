package com.qiubai.library.adview.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.qiubai.library.adview.util.AdViewReqManager.ReqInfoItem;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import qsbk.app.widget.listview.XListViewHeader;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(Context r3_Context, String r4_String) {
        super(r3_Context, r4_String, null, 1);
    }

    public synchronized void addVariable(int r2i, String r3_String, String r4_String) {
        addVariable(r2i, r3_String, r4_String, false);
    }

    public synchronized void addVariable(int r6i, String r7_String, String r8_String, boolean r9z) {
        Object[] r2_ObjectA;
        try {
            Object[] r2_ObjectA_2;
            SQLiteDatabase r1_SQLiteDatabase = getWritableDatabase();
            if (r9z) {
                r2_ObjectA_2 = new Object[1];
                r2_ObjectA_2[0] = r7_String;
                r1_SQLiteDatabase.execSQL("delete from variable where name=?", r2_ObjectA_2);
            }
            r1_SQLiteDatabase.beginTransaction();
            r2_ObjectA = new Object[3];
            r2_ObjectA[0] = Integer.valueOf(r6i);
            r2_ObjectA[1] = r7_String;
            r2_ObjectA[2] = r8_String;
            r1_SQLiteDatabase.execSQL("insert into variable(id, name, value) values(?, ?, ?)", r2_ObjectA);
            r1_SQLiteDatabase.setTransactionSuccessful();
            r1_SQLiteDatabase.endTransaction();
            r1_SQLiteDatabase.close();
        } catch (Throwable th) {
        }
    }

    public synchronized void delVariable(int r4i) {
        delVariable(Integer.valueOf(r4i), null, null);
    }

    public synchronized void delVariable(Integer r5_Integer, String r6_String, String r7_String) {
        SQLiteDatabase r0_SQLiteDatabase = getWritableDatabase();
        Object[] r2_ObjectA;
        if (r5_Integer != null) {
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r5_Integer;
            r0_SQLiteDatabase.execSQL("delete from variable where id=?", r2_ObjectA);
        } else if (r6_String == null || r7_String == null) {
            if (r6_String != null) {
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r6_String;
                r0_SQLiteDatabase.execSQL("delete from variable where name=?", r2_ObjectA);
            } else if (r7_String != null) {
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r7_String;
                r0_SQLiteDatabase.execSQL("delete from variable where value=?", r2_ObjectA);
            }
        } else {
            r2_ObjectA = new Object[2];
            r2_ObjectA[0] = r6_String;
            r2_ObjectA[1] = r7_String;
            r0_SQLiteDatabase.execSQL("delete from variable where name=? and value=?", r2_ObjectA);
        }
        r0_SQLiteDatabase.close();
    }

    public synchronized void delVariable(String r3_String) {
        delVariable(null, r3_String, null);
    }

    public synchronized int getMaxId() {
        int r0i = 0;
        synchronized (this) {
            SQLiteDatabase r1_SQLiteDatabase = getReadableDatabase();
            Cursor r2_Cursor = r1_SQLiteDatabase.rawQuery("select max(id) from variable", new String[0]);
            if (r2_Cursor.moveToNext()) {
                r0i = r2_Cursor.getInt(0);
            }
            r2_Cursor.close();
            r1_SQLiteDatabase.close();
        }
        return r0i;
    }

    public synchronized ArrayList<ReqInfoItem> getReqInfoItems() {
        ArrayList<ReqInfoItem> r3_ArrayList_ReqInfoItem;
        SQLiteDatabase r1_SQLiteDatabase = getReadableDatabase();
        Cursor r2_Cursor = r1_SQLiteDatabase.rawQuery("select id,name,value from variable", new String[0]);
        r3_ArrayList_ReqInfoItem = new ArrayList();
        while (r2_Cursor.moveToNext()) {
            try {
                ReqInfoItem r0_ReqInfoItem = new ReqInfoItem();
                r0_ReqInfoItem.b = r2_Cursor.getInt(0);
                r0_ReqInfoItem.a = Long.parseLong(r2_Cursor.getString(1));
                r0_ReqInfoItem.d = new JSONArray(r2_Cursor.getString(XListViewHeader.STATE_REFRESHING));
                r3_ArrayList_ReqInfoItem.add(r0_ReqInfoItem);
            } catch (Exception e) {
                AdViewUtil.logError(RContactStorage.PRIMARY_KEY, e);
            }
        }
        r2_Cursor.close();
        r1_SQLiteDatabase.close();
        return r3_ArrayList_ReqInfoItem;
    }

    public synchronized ArrayList<String> getVariable(String r5_String) {
        ArrayList<String> r2_ArrayList_String;
        SQLiteDatabase r0_SQLiteDatabase = getReadableDatabase();
        String[] r2_StringA = new String[1];
        r2_StringA[0] = r5_String;
        Cursor r1_Cursor = r0_SQLiteDatabase.rawQuery("select value from variable where name=?", r2_StringA);
        r2_ArrayList_String = new ArrayList();
        while (r1_Cursor.moveToNext()) {
            r2_ArrayList_String.add(r1_Cursor.getString(0));
        }
        r1_Cursor.close();
        r0_SQLiteDatabase.close();
        return r2_ArrayList_String;
    }

    public synchronized void onCreate(SQLiteDatabase r2_SQLiteDatabase) {
        r2_SQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS variable(id integer primary key,name varchar(100), value varchar(1000))");
    }

    public synchronized void onUpgrade(SQLiteDatabase r2_SQLiteDatabase, int r3i, int r4i) {
        r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS variable");
        onCreate(r2_SQLiteDatabase);
    }

    public synchronized void testVariable() {
        addVariable(0, "test_0", "test_value_0");
        addVariable(1, "test_0", "test_value_1");
        Iterator r1_Iterator = getVariable("test_0").iterator();
        while (r1_Iterator.hasNext()) {
            AdViewUtil.logInfo((String) r1_Iterator.next());
        }
        delVariable("test_0");
        getVariable("test_0");
    }
}