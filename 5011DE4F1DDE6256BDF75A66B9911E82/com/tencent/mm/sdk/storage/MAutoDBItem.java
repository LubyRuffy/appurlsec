package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;
import qsbk.app.share.ShareUtils;

public abstract class MAutoDBItem implements MDBItem {
    public static final String SYSTEM_ROWID_FIELD = "rowid";
    public long systemRowid;

    public MAutoDBItem() {
        this.systemRowid = -1;
    }

    public static boolean checkIOEqual(ContentValues r9_ContentValues, Cursor r10_Cursor) {
        if (r9_ContentValues == null) {
            return r10_Cursor == null;
        } else {
            if (r10_Cursor == null || r10_Cursor.getCount() != 1) {
                return false;
            }
            r10_Cursor.moveToFirst();
            int r3i = r10_Cursor.getColumnCount();
            int r0i = r9_ContentValues.size();
            if (r9_ContentValues.containsKey(SYSTEM_ROWID_FIELD)) {
                r0i--;
            }
            if (r10_Cursor.getColumnIndex(SYSTEM_ROWID_FIELD) != -1) {
                r3i--;
            }
            if (r0i != r3i) {
                return false;
            }
            try {
                Iterator r4_Iterator = r9_ContentValues.valueSet().iterator();
                while (r4_Iterator.hasNext()) {
                    String r0_String = (String) ((Entry) r4_Iterator.next()).getKey();
                    if (!r0_String.equals(SYSTEM_ROWID_FIELD)) {
                        r3i = r10_Cursor.getColumnIndex(r0_String);
                        if (r3i == -1) {
                            return false;
                        }
                        if (r9_ContentValues.get(r0_String) instanceof byte[]) {
                            byte[] r0_byteA = (byte[]) r9_ContentValues.get(r0_String);
                            byte[] r5_byteA = r10_Cursor.getBlob(r3i);
                            if (r0_byteA.length != r5_byteA.length) {
                                return false;
                            }
                            r3i = 0;
                            while (r3i < r0_byteA.length) {
                                if (r0_byteA[r3i] != r5_byteA[r3i]) {
                                    return false;
                                }
                                r3i++;
                            }
                        } else {
                            if (r10_Cursor.getString(r3i) == null && r9_ContentValues.get(r0_String) != null) {
                                return false;
                            }
                            if (r9_ContentValues.get(r0_String) == null) {
                                return false;
                            }
                            if (!r9_ContentValues.get(r0_String).toString().equals(r10_Cursor.getString(r3i))) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static Cursor getCursorForProjection(ContentValues r3_ContentValues, String[] r4_StringA) {
        Object[] r1_ObjectA = new Object[r4_StringA.length];
        int r0i = 0;
        while (r0i < r1_ObjectA.length) {
            r1_ObjectA[r0i] = r3_ContentValues.get(r4_StringA[r0i]);
            r0i++;
        }
        Cursor r0_Cursor = new MatrixCursor(r4_StringA);
        r0_Cursor.addRow(r1_ObjectA);
        return r0_Cursor;
    }

    public static String[] getFullColumns(Field[] r4_FieldA) {
        String[] r1_StringA = new String[(r4_FieldA.length + 1)];
        int r0i = 0;
        while (r0i < r4_FieldA.length) {
            r1_StringA[r0i] = r4_FieldA[r0i].getName().substring(ShareUtils.SHARE_COPY);
            r0i++;
        }
        r1_StringA[r4_FieldA.length] = SYSTEM_ROWID_FIELD;
        return r1_StringA;
    }

    public static Field[] getValidFields(Class<?> r9_Class_) {
        List r2_List = new LinkedList();
        Field[] r3_FieldA = r9_Class_.getDeclaredFields();
        int r4i = r3_FieldA.length;
        int r0i = 0;
        while (r0i < r4i) {
            Field r5_Field = r3_FieldA[r0i];
            int r6i = r5_Field.getModifiers();
            String r7_String = r5_Field.getName();
            if (r7_String == null || (!r7_String.startsWith("field_")) || (!Modifier.isPublic(r6i)) || Modifier.isFinal(r6i)) {
                r0i++;
            } else {
                if (r7_String.endsWith("field_rowid")) {
                    Assert.assertTrue("field_rowid reserved by MAutoDBItem, change now!", false);
                }
                r2_List.add(r5_Field);
                r0i++;
            }
        }
        return (Field[]) r2_List.toArray(new Field[0]);
    }

    public static Map<String, String> identify(Field[] r6_FieldA, StringBuilder r7_StringBuilder, String r8_String) {
        Map<String, String> r2_Map_String__String = new HashMap();
        int r0i = 0;
        while (r0i < r6_FieldA.length) {
            String r3_String = a.type(r6_FieldA[r0i].getType());
            if (r3_String == null) {
                Log.e("MicroMsg.SDK.MAutoDBItem", new StringBuilder("failed identify on column: ").append(r6_FieldA[r0i].getName()).append(", skipped").toString());
            } else {
                String r4_String = r6_FieldA[r0i].getName().substring(ShareUtils.SHARE_COPY);
                if (r7_StringBuilder != null) {
                    r7_StringBuilder.append(r4_String + " " + r3_String + (r4_String.equals(r8_String) ? " PRIMARY KEY " : RContactStorage.PRIMARY_KEY));
                    r7_StringBuilder.append(r0i == r6_FieldA.length + -1 ? RContactStorage.PRIMARY_KEY : ", ");
                }
                r2_Map_String__String.put(r4_String, r3_String);
            }
            r0i++;
        }
        return r2_Map_String__String;
    }

    public void convertFrom(Cursor r11_Cursor) {
        int r0i;
        Field[] r3_FieldA = fields();
        int r4i = r3_FieldA.length;
        int r2i = 0;
        while (r2i < r4i) {
            Field r0_Field = r3_FieldA[r2i];
            try {
                int r5i = r11_Cursor.getColumnIndexOrThrow(r0_Field.getName().substring(ShareUtils.SHARE_COPY));
                if (r5i != -1) {
                    Method r6_Method = a.get(r0_Field.getType(), false);
                    if (r6_Method == null) {
                        r2i++;
                    } else {
                        Object[] r8_ObjectA = new Object[4];
                        r8_ObjectA[0] = r0_Field;
                        r8_ObjectA[1] = this;
                        r8_ObjectA[2] = r11_Cursor;
                        r8_ObjectA[3] = Integer.valueOf(r5i);
                        r6_Method.invoke(null, r8_ObjectA);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            r2i++;
        }
        try {
            r0i = r11_Cursor.getColumnIndexOrThrow(SYSTEM_ROWID_FIELD);
        } catch (Exception e_2) {
            r0i = -1;
        }
        if (r0i > 0) {
            this.systemRowid = r11_Cursor.getLong(r0i);
        }
    }

    public ContentValues convertTo() {
        ContentValues r2_ContentValues = new ContentValues();
        Field[] r3_FieldA = fields();
        int r4i = r3_FieldA.length;
        int r1i = 0;
        while (r1i < r4i) {
            Field r0_Field = r3_FieldA[r1i];
            try {
                Method r5_Method = a.get(r0_Field.getType(), true);
                if (r5_Method == null) {
                    r1i++;
                } else {
                    Object[] r7_ObjectA = new Object[3];
                    r7_ObjectA[0] = r0_Field;
                    r7_ObjectA[1] = this;
                    r7_ObjectA[2] = r2_ContentValues;
                    r5_Method.invoke(null, r7_ObjectA);
                    r1i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.systemRowid > 0) {
            r2_ContentValues.put(SYSTEM_ROWID_FIELD, Long.valueOf(this.systemRowid));
        }
        return r2_ContentValues;
    }

    protected abstract Field[] fields();
}