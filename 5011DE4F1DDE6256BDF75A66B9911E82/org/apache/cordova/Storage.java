package org.apache.cordova;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class Storage extends CordovaPlugin {
    private static final String ALTER = "alter";
    private static final String CREATE = "create";
    private static final String DROP = "drop";
    private static final String TRUNCATE = "truncate";
    String dbName;
    SQLiteDatabase myDb;
    String path;

    public Storage() {
        this.myDb = null;
        this.path = null;
        this.dbName = null;
    }

    private boolean isDDL(String r3_String) {
        String r0_String = r3_String.toLowerCase();
        return r0_String.startsWith(DROP) || r0_String.startsWith(CREATE) || r0_String.startsWith(ALTER) || r0_String.startsWith(TRUNCATE);
    }

    public boolean execute(String r9_String, JSONArray r10_JSONArray, CallbackContext r11_CallbackContext) throws JSONException {
        int r1i = 0;
        if (r9_String.equals("openDatabase")) {
            openDatabase(r10_JSONArray.getString(r1i), r10_JSONArray.getString(1), r10_JSONArray.getString(XListViewHeader.STATE_REFRESHING), r10_JSONArray.getLong(XListViewFooter.STATE_NOMORE));
        } else {
            if (!r9_String.equals("executeSql")) {
                return false;
            }
            String[] r0_StringA;
            if (r10_JSONArray.isNull(1)) {
                r0_StringA = new String[0];
            } else {
                JSONArray r3_JSONArray = r10_JSONArray.getJSONArray(1);
                int r4i = r3_JSONArray.length();
                r0_StringA = new String[r4i];
                int r2i = 0;
                while (r2i < r4i) {
                    r0_StringA[r2i] = r3_JSONArray.getString(r2i);
                    r2i++;
                }
            }
            executeSql(r10_JSONArray.getString(r1i), r0_StringA, r10_JSONArray.getString(XListViewHeader.STATE_REFRESHING));
        }
        r11_CallbackContext.success();
        return true;
    }

    public void executeSql(String r5_String, String[] r6_StringA, String r7_String) {
        try {
            if (isDDL(r5_String)) {
                this.myDb.execSQL(r5_String);
                this.webView.sendJavascript("cordova.require('cordova/plugin/android/storage').completeQuery('" + r7_String + "', '');");
            } else {
                Cursor r0_Cursor = this.myDb.rawQuery(r5_String, r6_StringA);
                processResults(r0_Cursor, r7_String);
                r0_Cursor.close();
            }
        } catch (SQLiteException e) {
            SQLiteException r0_SQLiteException = e;
            r0_SQLiteException.printStackTrace();
            System.out.println("Storage.executeSql(): Error=" + r0_SQLiteException.getMessage());
            this.webView.sendJavascript("cordova.require('cordova/plugin/android/storage').failQuery('" + r0_SQLiteException.getMessage() + "','" + r7_String + "');");
        }
    }

    public void onDestroy() {
        if (this.myDb != null) {
            this.myDb.close();
            this.myDb = null;
        }
    }

    public void onReset() {
        onDestroy();
    }

    public void openDatabase(String r5_String, String r6_String, String r7_String, long r8j) {
        if (this.myDb != null) {
            this.myDb.close();
        }
        if (this.path == null) {
            this.path = this.cordova.getActivity().getApplicationContext().getDir("database", 0).getPath();
        }
        this.dbName = this.path + File.separator + r5_String + ".db";
        File r0_File = new File(this.path + File.pathSeparator + r5_String + ".db");
        if (r0_File.exists()) {
            File r1_File = new File(this.path);
            File r2_File = new File(this.dbName);
            r1_File.mkdirs();
            r0_File.renameTo(r2_File);
        }
        this.myDb = SQLiteDatabase.openOrCreateDatabase(this.dbName, null);
    }

    public void processResults(Cursor r7_Cursor, String r8_String) {
        String r0_String = "[]";
        if (r7_Cursor.moveToFirst()) {
            JSONArray r1_JSONArray = new JSONArray();
            r0_String = RContactStorage.PRIMARY_KEY;
            r0_String = RContactStorage.PRIMARY_KEY;
            int r2i = r7_Cursor.getColumnCount();
            while (true) {
                JSONObject r3_JSONObject = new JSONObject();
                int r0i = 0;
                while (r0i < r2i) {
                    r3_JSONObject.put(r7_Cursor.getColumnName(r0i), r7_Cursor.getString(r0i));
                    r0i++;
                }
                r1_JSONArray.put(r3_JSONObject);
                if (!r7_Cursor.moveToNext()) {
                    r0_String = r1_JSONArray.toString();
                }
            }
        }
        this.webView.sendJavascript("cordova.require('cordova/plugin/android/storage').completeQuery('" + r8_String + "', " + r0_String + ");");
    }
}