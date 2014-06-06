package qsbk.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.model.Article;

public class SharePreferenceUtils {
    static SharedPreferences a;
    static Editor b;

    private static Editor a() {
        if (b == null) {
            b = a(QsbkApp.mContext).edit();
        }
        return b;
    }

    private static SharedPreferences a(Context r1_Context) {
        if (a == null) {
            a = PreferenceManager.getDefaultSharedPreferences(r1_Context);
        }
        return a;
    }

    public static void getCollections() {
        CharSequence r0_CharSequence = RContactStorage.PRIMARY_KEY;
        SharedPreferences r1_SharedPreferences = a(QsbkApp.mContext);
        if (r1_SharedPreferences != null) {
            r0_CharSequence = r1_SharedPreferences.getString("collection", RContactStorage.PRIMARY_KEY);
        }
        if (!TextUtils.isEmpty(r0_CharSequence)) {
            String[] r1_StringA = r0_CharSequence.split(",");
            int r2i = r1_StringA.length;
            int r0i = 0;
            while (r0i < r2i) {
                if (TextUtils.isEmpty(r1_StringA[r0i]) || QsbkApp.allCollection.contains(r1_StringA[r0i])) {
                    r0i++;
                } else {
                    QsbkApp.allCollection.add(r1_StringA[r0i]);
                    r0i++;
                }
            }
        }
    }

    public static int getSharePreferencesIntValue(String r2_String) {
        int r0i = 0;
        SharedPreferences r1_SharedPreferences = a(QsbkApp.mContext);
        return r1_SharedPreferences != null ? r1_SharedPreferences.getInt(r2_String, r0i) : 0;
    }

    public static String getSharePreferencesValue(String r2_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        SharedPreferences r1_SharedPreferences = a(QsbkApp.mContext);
        return r1_SharedPreferences != null ? r1_SharedPreferences.getString(r2_String, RContactStorage.PRIMARY_KEY) : r0_String;
    }

    public static void remove(String r1_String) {
        a().remove(r1_String).commit();
    }

    public static void setCollections(ArrayList<String> r4_ArrayList_String) {
        remove("collection");
        StringBuffer r1_StringBuffer = new StringBuffer();
        Iterator r2_Iterator = r4_ArrayList_String.iterator();
        while (r2_Iterator.hasNext()) {
            r1_StringBuffer.append((String) r2_Iterator.next()).append(",");
        }
        a().putString("collection", r1_StringBuffer.toString());
        a().commit();
    }

    public static void setCollectionsByArticle(ArrayList<Object> r4_ArrayList_Object) {
        remove("collection");
        StringBuffer r1_StringBuffer = new StringBuffer();
        Iterator r2_Iterator = r4_ArrayList_Object.iterator();
        while (r2_Iterator.hasNext()) {
            r1_StringBuffer.append(((Article) r2_Iterator.next()).id).append(",");
        }
        a().putString("collection", r1_StringBuffer.toString());
        a().commit();
    }

    public static void setSharePreferencesValue(String r1_String, int r2i) {
        a().putInt(r1_String, r2i);
        a().commit();
    }

    public static void setSharePreferencesValue(String r1_String, String r2_String) {
        a().putString(r1_String, r2_String);
        a().commit();
    }
}