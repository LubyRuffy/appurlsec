package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;
import qsbk.app.database.QsbkDatabase;

public abstract class MAutoStorage<T extends MAutoDBItem> extends MStorage {
    private ISQLiteDatabase a;
    private final String b;
    private final String[] c;

    public MAutoStorage(ISQLiteDatabase r2_ISQLiteDatabase) {
        this.a = r2_ISQLiteDatabase;
        this.b = Util.isNullOrNil(getPrimaryKey()) ? MAutoDBItem.SYSTEM_ROWID_FIELD : getPrimaryKey();
        this.c = getColumns();
    }

    private static StringBuilder a(ContentValues r6_ContentValues, String ... r7_StringA) {
        StringBuilder r0_StringBuilder = new StringBuilder();
        int r2i = r7_StringA.length;
        int r1i = 0;
        while (r1i < r2i) {
            String r3_String = r7_StringA[r1i];
            r0_StringBuilder.append(r3_String + " = ? AND ");
            if (r6_ContentValues.get(r3_String) == null) {
                return null;
            }
            r1i++;
        }
        r0_StringBuilder.append(" 1=1");
        return r0_StringBuilder;
    }

    private void a(String r4_String) {
        Log.d("MicroMsg.SDK.MAutoStorage", getTableName() + ":" + r4_String);
    }

    private boolean a(ContentValues r9_ContentValues) {
        ISQLiteDatabase r0_ISQLiteDatabase = this.a;
        String r1_String = getTableName();
        String[] r2_StringA = this.c;
        String r3_String = this.b + " = ?";
        String[] r4_StringA = new String[1];
        r4_StringA[0] = Util.nullAsNil(r9_ContentValues.getAsString(this.b));
        Cursor r0_Cursor = r0_ISQLiteDatabase.query(r1_String, r2_StringA, r3_String, r4_StringA, null, null, null);
        boolean r1z = MAutoDBItem.checkIOEqual(r9_ContentValues, r0_Cursor);
        r0_Cursor.close();
        return r1z;
    }

    private static String[] a(String[] r3_StringA, ContentValues r4_ContentValues) {
        String[] r1_StringA = new String[r3_StringA.length];
        int r0i = 0;
        while (r0i < r1_StringA.length) {
            r1_StringA[r0i] = Util.nullAsNil(r4_ContentValues.getAsString(r3_StringA[r0i]));
            r0i++;
        }
        return r1_StringA;
    }

    private void b(String r4_String) {
        Log.e("MicroMsg.SDK.MAutoStorage", getTableName() + ":" + r4_String);
    }

    public static List<String> getCreateSQLs(Field[] r8_FieldA, String r9_String, String r10_String, String ... r11_StringA) {
        LinkedList r1_LinkedList = new LinkedList();
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(r9_String).append(" ( ").toString());
        Map r2_Map = MAutoDBItem.identify(r8_FieldA, r0_StringBuilder, r10_String);
        r0_StringBuilder.append(");");
        r1_LinkedList.addFirst(r0_StringBuilder.toString());
        if (r11_StringA == null || r11_StringA.length <= 0) {
            return r1_LinkedList;
        }
        int r3i = r11_StringA.length;
        int r0i = 0;
        while (r0i < r3i) {
            String r4_String = r11_StringA[r0i];
            if (r4_String == null || r4_String.length() <= 0) {
                r0i++;
            } else {
                if (r2_Map.get(r4_String) == null) {
                    Log.e("MicroMsg.SDK.MAutoStorage", new StringBuilder("skipped invalid index: ").append(r4_String).append(", not found in fields").toString());
                }
                r1_LinkedList.add(new StringBuilder("CREATE INDEX IF NOT EXISTS _mindex_").append(r9_String).append("_").append(r4_String).append("_ ON ").append(r9_String).append("(").append(r4_String).append(");").toString());
                r0i++;
            }
        }
        return r1_LinkedList;
    }

    public static List<String> getUpdateSQLs(Field[] r11_FieldA, String r12_String, ISQLiteDatabase r13_ISQLiteDatabase) {
        List<String> r6_List_String = new LinkedList();
        Map r7_Map = new HashMap();
        Cursor r0_Cursor = r13_ISQLiteDatabase.rawQuery(new StringBuilder("PRAGMA table_info( ").append(r12_String).append(" )").toString(), null);
        while (r0_Cursor.moveToNext()) {
            r7_Map.put(r0_Cursor.getString(r0_Cursor.getColumnIndex("name")), r0_Cursor.getString(r0_Cursor.getColumnIndex(QsbkDatabase.TYPE)));
        }
        r0_Cursor.close();
        Iterator r8_Iterator = MAutoDBItem.identify(r11_FieldA, null, null).entrySet().iterator();
        while (r8_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r8_Iterator.next();
            String r1_String = (String) r0_Entry.getValue();
            String r0_String = (String) r0_Entry.getKey();
            if (((r1_String == null ? 1 : 0) | (r1_String.length() <= 0 ? 1 : 0)) == 0) {
                String r2_String = (String) r7_Map.get(r0_String);
                if (r2_String == null) {
                    r6_List_String.add(new StringBuilder("ALTER TABLE ").append(r12_String).append(" ADD COLUMN ").append(r0_String).append(" ").append(r1_String).append(";").toString());
                    r7_Map.remove(r0_String);
                } else if (!r2_String.equalsIgnoreCase(r1_String)) {
                    Log.e("MicroMsg.SDK.MAutoStorage", new StringBuilder("conflicting alter table on column: ").append(r0_String).append(", ").append(r2_String).append("<o-n>").append(r1_String).toString());
                    r7_Map.remove(r0_String);
                }
            }
        }
        return r6_List_String;
    }

    public boolean delete(long r8j) {
        boolean r0z = true;
        ISQLiteDatabase r2_ISQLiteDatabase = this.a;
        String r3_String = getTableName();
        String[] r5_StringA = new String[1];
        r5_StringA[0] = String.valueOf(r8j);
        if (r2_ISQLiteDatabase.delete(r3_String, "rowid = ?", r5_StringA) > 0) {
            if (!r0z) {
                notify();
            }
            return r0z;
        } else {
            r0z = false;
            if (r0z) {
                return r0z;
            }
            notify();
            return r0z;
        }
    }

    public boolean delete(T r9_T, String ... r10_StringA) {
        boolean r0z = false;
        ContentValues r2_ContentValues = r9_T.convertTo();
        if (r2_ContentValues == null || r2_ContentValues.size() <= 0) {
            b("delete failed, value.size <= 0");
            return false;
        } else if (r10_StringA == null || r10_StringA.length <= 0) {
            a("delete with primary key");
            ISQLiteDatabase r3_ISQLiteDatabase = this.a;
            String r4_String = getTableName();
            String r5_String = this.b + " = ?";
            String[] r6_StringA = new String[1];
            r6_StringA[0] = Util.nullAsNil(r2_ContentValues.getAsString(this.b));
            if (r3_ISQLiteDatabase.delete(r4_String, r5_String, r6_StringA) > 0) {
                r0z = true;
            }
            if (!r0z) {
                return r0z;
            }
            doNotify();
            return r0z;
        } else {
            StringBuilder r3_StringBuilder = a(r2_ContentValues, r10_StringA);
            if (r3_StringBuilder == null) {
                b("delete failed, check keys failed");
                return false;
            } else if (this.a.delete(getTableName(), r3_StringBuilder.toString(), a(r10_StringA, r2_ContentValues)) > 0) {
                doNotify(this.b);
                return true;
            } else {
                b("delete failed");
                return false;
            }
        }
    }

    public boolean get(long r11j, T r13_T) {
        ISQLiteDatabase r0_ISQLiteDatabase = this.a;
        String r1_String = getTableName();
        String[] r2_StringA = this.c;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = String.valueOf(r11j);
        Cursor r0_Cursor = r0_ISQLiteDatabase.query(r1_String, r2_StringA, "rowid = ?", r4_StringA, null, null, null);
        if (r0_Cursor.moveToFirst()) {
            r13_T.convertFrom(r0_Cursor);
            r0_Cursor.close();
            return true;
        } else {
            r0_Cursor.close();
            return false;
        }
    }

    public boolean get(T r11_T, String ... r12_StringA) {
        ContentValues r6_ContentValues = r11_T.convertTo();
        if (r6_ContentValues == null || r6_ContentValues.size() <= 0) {
            b("get failed, value.size <= 0");
            return false;
        } else if (r12_StringA == null || r12_StringA.length <= 0) {
            a("get with primary key");
            ISQLiteDatabase r0_ISQLiteDatabase = this.a;
            String r1_String = getTableName();
            String[] r2_StringA = this.c;
            String r3_String = this.b + " = ?";
            String[] r4_StringA = new String[1];
            r4_StringA[0] = Util.nullAsNil(r6_ContentValues.getAsString(this.b));
            r0_Cursor = r0_ISQLiteDatabase.query(r1_String, r2_StringA, r3_String, r4_StringA, null, null, null);
            if (r0_Cursor.moveToFirst()) {
                r11_T.convertFrom(r0_Cursor);
                r0_Cursor.close();
                return true;
            } else {
                r0_Cursor.close();
                return false;
            }
        } else {
            StringBuilder r3_StringBuilder = a(r6_ContentValues, r12_StringA);
            if (r3_StringBuilder == null) {
                b("get failed, check keys failed");
                return false;
            } else {
                r0_Cursor = this.a.query(getTableName(), this.c, r3_StringBuilder.toString(), a(r12_StringA, r6_ContentValues), null, null, null);
                if (r0_Cursor.moveToFirst()) {
                    r11_T.convertFrom(r0_Cursor);
                    r0_Cursor.close();
                    return true;
                } else {
                    r0_Cursor.close();
                    a("get failed, not found");
                    return false;
                }
            }
        }
    }

    public Cursor getAll() {
        return this.a.query(getTableName(), this.c, null, null, null, null, null);
    }

    public abstract String[] getColumns();

    public int getCount() {
        int r0i = 0;
        Cursor r1_Cursor = rawQuery(new StringBuilder("select count(*) from ").append(getTableName()).toString(), new String[0]);
        if (r1_Cursor == null) {
            return 0;
        }
        r1_Cursor.moveToFirst();
        r0i = r1_Cursor.getInt(r0i);
        r1_Cursor.close();
        return r0i;
    }

    public abstract String getPrimaryKey();

    public abstract String getTableName();

    public boolean insert(T r6_T) {
        ContentValues r1_ContentValues = r6_T.convertTo();
        if (r1_ContentValues == null || r1_ContentValues.size() <= 0) {
            b("insert failed, value.size <= 0");
            return false;
        } else {
            r6_T.systemRowid = this.a.insert(getTableName(), this.b, r6_T.convertTo());
            if (r6_T.systemRowid <= 0) {
                b("insert failed");
                return false;
            } else {
                doNotify(this.b);
                return true;
            }
        }
    }

    public Cursor rawQuery(String r2_String, String ... r3_StringA) {
        return this.a.rawQuery(r2_String, r3_StringA);
    }

    public boolean replace(T r8_T) {
        Assert.assertTrue("replace primaryKey == null", !Util.isNullOrNil(this.b));
        ContentValues r3_ContentValues = r8_T.convertTo();
        if (r3_ContentValues != null) {
            if (r3_ContentValues.size() != (r3_ContentValues.containsKey(MAutoDBItem.SYSTEM_ROWID_FIELD) ? 1 : 0) + r8_T.fields().length) {
                b("replace failed, cv.size() != item.fields().length");
            } else if (a(r3_ContentValues)) {
                a("no need replace , fields no change");
                return true;
            } else if (this.a.replace(getTableName(), this.b, r3_ContentValues) > 0) {
                doNotify(this.b);
                return true;
            } else {
                b("replace failed");
                return false;
            }
        }
        b("replace failed, cv.size() != item.fields().length");
        return false;
    }

    public boolean update(long r12j, T r14_T) {
        ContentValues r10_ContentValues = r14_T.convertTo();
        if (r10_ContentValues == null || r10_ContentValues.size() <= 0) {
            b("update failed, value.size <= 0");
            return false;
        } else {
            ISQLiteDatabase r0_ISQLiteDatabase = this.a;
            String r1_String = getTableName();
            String[] r2_StringA = this.c;
            String[] r4_StringA = new String[1];
            r4_StringA[0] = String.valueOf(r12j);
            Cursor r0_Cursor = r0_ISQLiteDatabase.query(r1_String, r2_StringA, "rowid = ?", r4_StringA, null, null, null);
            if (MAutoDBItem.checkIOEqual(r10_ContentValues, r0_Cursor)) {
                r0_Cursor.close();
                a("no need replace , fields no change");
                return true;
            } else {
                boolean r0z;
                r0_Cursor.close();
                r0_ISQLiteDatabase = this.a;
                r1_String = getTableName();
                String[] r3_StringA = new String[1];
                r3_StringA[0] = String.valueOf(r12j);
                r0z = r0_ISQLiteDatabase.update(r1_String, r10_ContentValues, "rowid = ?", r3_StringA) > 0;
                if (!r0z) {
                    return r0z;
                }
                doNotify();
                return r0z;
            }
        }
    }

    public boolean update(T r9_T, String ... r10_StringA) {
        boolean r0z = false;
        ContentValues r2_ContentValues = r9_T.convertTo();
        if (r2_ContentValues == null || r2_ContentValues.size() <= 0) {
            b("update failed, value.size <= 0");
            return false;
        } else if (r10_StringA == null || r10_StringA.length <= 0) {
            a("update with primary key");
            if (a(r2_ContentValues)) {
                a("no need replace , fields no change");
                return true;
            } else {
                ISQLiteDatabase r3_ISQLiteDatabase = this.a;
                String r4_String = getTableName();
                String r5_String = this.b + " = ?";
                String[] r6_StringA = new String[1];
                r6_StringA[0] = Util.nullAsNil(r2_ContentValues.getAsString(this.b));
                if (r3_ISQLiteDatabase.update(r4_String, r2_ContentValues, r5_String, r6_StringA) > 0) {
                    r0z = true;
                }
                if (!r0z) {
                    return r0z;
                }
                doNotify();
                return r0z;
            }
        } else {
            StringBuilder r3_StringBuilder = a(r2_ContentValues, r10_StringA);
            if (r3_StringBuilder == null) {
                b("update failed, check keys failed");
                return false;
            } else if (this.a.update(getTableName(), r2_ContentValues, r3_StringBuilder.toString(), a(r10_StringA, r2_ContentValues)) > 0) {
                doNotify(this.b);
                return true;
            } else {
                b("update failed");
                return false;
            }
        }
    }
}