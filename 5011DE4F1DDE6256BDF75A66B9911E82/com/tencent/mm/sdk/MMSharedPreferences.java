package com.tencent.mm.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.Resolver;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import qsbk.app.database.QsbkDatabase;

public class MMSharedPreferences implements SharedPreferences {
    private final ContentResolver a;
    private final String[] b;
    private final HashMap<String, Object> c;
    private a d;

    private static class a implements Editor {
        private Map<String, Object> a;
        private Set<String> b;
        private boolean c;
        private ContentResolver d;

        public a(ContentResolver r2_ContentResolver) {
            this.a = new HashMap();
            this.b = new HashSet();
            this.c = false;
            this.d = r2_ContentResolver;
        }

        public void apply() {
        }

        public Editor clear() {
            this.c = true;
            return this;
        }

        public boolean commit() {
            ContentResolver r3_ContentResolver;
            Uri r4_Uri;
            String[] r6_StringA;
            ContentValues r1_ContentValues = new ContentValues();
            if (this.c) {
                this.d.delete(SharedPref.CONTENT_URI, null, null);
                this.c = false;
            }
            Iterator r2_Iterator = this.b.iterator();
            while (r2_Iterator.hasNext()) {
                r3_ContentResolver = this.d;
                r4_Uri = SharedPref.CONTENT_URI;
                r6_StringA = new String[1];
                r6_StringA[0] = (String) r2_Iterator.next();
                r3_ContentResolver.delete(r4_Uri, "key = ?", r6_StringA);
            }
            r2_Iterator = this.a.entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                if (Resolver.unresolveObj(r1_ContentValues, r0_Entry.getValue())) {
                    r3_ContentResolver = this.d;
                    r4_Uri = SharedPref.CONTENT_URI;
                    r6_StringA = new String[1];
                    r6_StringA[0] = (String) r0_Entry.getKey();
                    r3_ContentResolver.update(r4_Uri, r1_ContentValues, "key = ?", r6_StringA);
                }
            }
            return true;
        }

        public Editor putBoolean(String r3_String, boolean r4z) {
            this.a.put(r3_String, Boolean.valueOf(r4z));
            this.b.remove(r3_String);
            return this;
        }

        public Editor putFloat(String r3_String, float r4f) {
            this.a.put(r3_String, Float.valueOf(r4f));
            this.b.remove(r3_String);
            return this;
        }

        public Editor putInt(String r3_String, int r4i) {
            this.a.put(r3_String, Integer.valueOf(r4i));
            this.b.remove(r3_String);
            return this;
        }

        public Editor putLong(String r3_String, long r4j) {
            this.a.put(r3_String, Long.valueOf(r4j));
            this.b.remove(r3_String);
            return this;
        }

        public Editor putString(String r2_String, String r3_String) {
            this.a.put(r2_String, r3_String);
            this.b.remove(r2_String);
            return this;
        }

        public Editor putStringSet(String r2_String, Set<String> r3_Set_String) {
            return null;
        }

        public Editor remove(String r2_String) {
            this.b.add(r2_String);
            return this;
        }
    }

    public MMSharedPreferences(Context r4_Context) {
        String[] r0_StringA = new String[4];
        r0_StringA[0] = "_id";
        r0_StringA[1] = SharedPref.KEY;
        r0_StringA[2] = QsbkDatabase.TYPE;
        r0_StringA[3] = SharedPref.VALUE;
        this.b = r0_StringA;
        this.c = new HashMap();
        this.d = null;
        this.a = r4_Context.getContentResolver();
    }

    private Object a(String r8_String) {
        Object r0_Object;
        try {
            ContentResolver r0_ContentResolver = this.a;
            Uri r1_Uri = SharedPref.CONTENT_URI;
            String[] r2_StringA = this.b;
            String[] r4_StringA = new String[1];
            r4_StringA[0] = r8_String;
            Cursor r1_Cursor = r0_ContentResolver.query(r1_Uri, r2_StringA, "key = ?", r4_StringA, null);
            if (r1_Cursor == null) {
                r0_Object = null;
                return r0_Object;
            } else {
                r0_Object = r1_Cursor.moveToFirst() ? Resolver.resolveObj(r1_Cursor.getInt(r1_Cursor.getColumnIndex(QsbkDatabase.TYPE)), r1_Cursor.getString(r1_Cursor.getColumnIndex(SharedPref.VALUE))) : null;
                r1_Cursor.close();
                return r0_Object;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0_Object = null;
        }
    }

    public boolean contains(String r2_String) {
        return a(r2_String) != null;
    }

    public Editor edit() {
        if (this.d == null) {
            this.d = new a(this.a);
        }
        return this.d;
    }

    public Map<String, ?> getAll() {
        Map r0_Map;
        try {
            Cursor r0_Cursor = this.a.query(SharedPref.CONTENT_URI, this.b, null, null, null);
            if (r0_Cursor == null) {
                r0_Map = null;
                return r0_Map;
            } else {
                int r1i = r0_Cursor.getColumnIndex(SharedPref.KEY);
                int r2i = r0_Cursor.getColumnIndex(QsbkDatabase.TYPE);
                int r3i = r0_Cursor.getColumnIndex(SharedPref.VALUE);
                while (r0_Cursor.moveToNext()) {
                    this.c.put(r0_Cursor.getString(r1i), Resolver.resolveObj(r0_Cursor.getInt(r2i), r0_Cursor.getString(r3i)));
                }
                r0_Cursor.close();
                r0_Map = this.c;
                return r0_Map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0_Map = this.c;
        }
    }

    public boolean getBoolean(String r3_String, boolean r4z) {
        Object r0_Object = a(r3_String);
        return (r0_Object == null || (!r0_Object instanceof Boolean)) ? r4z : ((Boolean) r0_Object).booleanValue();
    }

    public float getFloat(String r3_String, float r4f) {
        Object r0_Object = a(r3_String);
        return (r0_Object == null || (!r0_Object instanceof Float)) ? r4f : ((Float) r0_Object).floatValue();
    }

    public int getInt(String r3_String, int r4i) {
        Object r0_Object = a(r3_String);
        return (r0_Object == null || (!r0_Object instanceof Integer)) ? r4i : ((Integer) r0_Object).intValue();
    }

    public long getLong(String r3_String, long r4j) {
        Object r0_Object = a(r3_String);
        return (r0_Object == null || (!r0_Object instanceof Long)) ? r4j : ((Long) r0_Object).longValue();
    }

    public String getString(String r3_String, String r4_String) {
        Object r0_Object = a(r3_String);
        return (r0_Object == null || (!r0_Object instanceof String)) ? r4_String : (String) r0_Object;
    }

    public Set<String> getStringSet(String r2_String, Set<String> r3_Set_String) {
        return null;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener r1_OnSharedPreferenceChangeListener) {
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener r1_OnSharedPreferenceChangeListener) {
    }
}