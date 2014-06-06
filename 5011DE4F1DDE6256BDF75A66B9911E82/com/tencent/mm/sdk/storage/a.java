package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.share.ShareUtils;

class a {
    private static final Map<Class<?>, Method> a;
    private static final Map<Class<?>, Method> b;
    private static final Map<Class<?>, String> c;

    static {
        a = new HashMap();
        b = new HashMap();
        c = new HashMap();
        try {
            Map r0_Map = a;
            Class[] r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(byte[].class, a.class.getMethod("keep_setBlob", r4_ClassA));
            r0_Map = a;
            Class r1_Class = Short.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setShort", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Short.class, a.class.getMethod("keep_setShort", r4_ClassA));
            r0_Map = a;
            r1_Class = Boolean.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setBoolean", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Boolean.class, a.class.getMethod("keep_setBoolean", r4_ClassA));
            r0_Map = a;
            r1_Class = Integer.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setInt", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Integer.class, a.class.getMethod("keep_setInt", r4_ClassA));
            r0_Map = a;
            r1_Class = Float.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setFloat", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Float.class, a.class.getMethod("keep_setFloat", r4_ClassA));
            r0_Map = a;
            r1_Class = Double.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setDouble", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Double.class, a.class.getMethod("keep_setDouble", r4_ClassA));
            r0_Map = a;
            r1_Class = Long.TYPE;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(r1_Class, a.class.getMethod("keep_setLong", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(Long.class, a.class.getMethod("keep_setLong", r4_ClassA));
            r0_Map = a;
            r4_ClassA = new Class[4];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = Cursor.class;
            r4_ClassA[3] = Integer.TYPE;
            r0_Map.put(String.class, a.class.getMethod("keep_setString", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(byte[].class, a.class.getMethod("keep_getBlob", r4_ClassA));
            r0_Map = b;
            r1_Class = Short.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getShort", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Short.class, a.class.getMethod("keep_getShort", r4_ClassA));
            r0_Map = b;
            r1_Class = Boolean.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getBoolean", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Boolean.class, a.class.getMethod("keep_getBoolean", r4_ClassA));
            r0_Map = b;
            r1_Class = Integer.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getInt", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Integer.class, a.class.getMethod("keep_getInt", r4_ClassA));
            r0_Map = b;
            r1_Class = Float.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getFloat", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Float.class, a.class.getMethod("keep_getFloat", r4_ClassA));
            r0_Map = b;
            r1_Class = Double.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getDouble", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Double.class, a.class.getMethod("keep_getDouble", r4_ClassA));
            r0_Map = b;
            r1_Class = Long.TYPE;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(r1_Class, a.class.getMethod("keep_getLong", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(Long.class, a.class.getMethod("keep_getLong", r4_ClassA));
            r0_Map = b;
            r4_ClassA = new Class[3];
            r4_ClassA[0] = Field.class;
            r4_ClassA[1] = Object.class;
            r4_ClassA[2] = ContentValues.class;
            r0_Map.put(String.class, a.class.getMethod("keep_getString", r4_ClassA));
            c.put(byte[].class, "BLOB");
            c.put(Short.TYPE, "SHORT");
            c.put(Short.class, "SHORT");
            c.put(Boolean.TYPE, "INTEGER");
            c.put(Boolean.class, "INTEGER");
            c.put(Integer.TYPE, "INTEGER");
            c.put(Integer.class, "INTEGER");
            c.put(Float.TYPE, "FLOAT");
            c.put(Float.class, "FLOAT");
            c.put(Double.TYPE, "DOUBLE");
            c.put(Double.class, "DOUBLE");
            c.put(Long.TYPE, "LONG");
            c.put(Long.class, "LONG");
            c.put(String.class, "TEXT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    a() {
    }

    public static Method get(Class<?> r1_Class_, boolean r2z) {
        return r2z ? (Method) b.get(r1_Class_) : (Method) a.get(r1_Class_);
    }

    public static void keep_getBlob(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), (byte[]) r2_Field.get(r3_Object));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getBoolean(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), Integer.valueOf(r2_Field.getBoolean(r3_Object) ? 1 : 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getDouble(Field r3_Field, Object r4_Object, ContentValues r5_ContentValues) {
        try {
            if (r3_Field.getType().equals(Double.TYPE)) {
                r5_ContentValues.put(r3_Field.getName().substring(ShareUtils.SHARE_COPY), Double.valueOf(r3_Field.getDouble(r4_Object)));
            } else {
                r5_ContentValues.put(r3_Field.getName().substring(ShareUtils.SHARE_COPY), (Double) r3_Field.get(r4_Object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getFloat(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            if (r2_Field.getType().equals(Float.TYPE)) {
                r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), Float.valueOf(r2_Field.getFloat(r3_Object)));
            } else {
                r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), (Float) r2_Field.get(r3_Object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getInt(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            if (r2_Field.getType().equals(Integer.TYPE)) {
                r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), Integer.valueOf(r2_Field.getInt(r3_Object)));
            } else {
                r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), (Integer) r2_Field.get(r3_Object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getLong(Field r3_Field, Object r4_Object, ContentValues r5_ContentValues) {
        try {
            if (r3_Field.getType().equals(Long.TYPE)) {
                r5_ContentValues.put(r3_Field.getName().substring(ShareUtils.SHARE_COPY), Long.valueOf(r3_Field.getLong(r4_Object)));
            } else {
                r5_ContentValues.put(r3_Field.getName().substring(ShareUtils.SHARE_COPY), (Long) r3_Field.get(r4_Object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getShort(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), Short.valueOf(r2_Field.getShort(r3_Object)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_getString(Field r2_Field, Object r3_Object, ContentValues r4_ContentValues) {
        try {
            r4_ContentValues.put(r2_Field.getName().substring(ShareUtils.SHARE_COPY), (String) r2_Field.get(r3_Object));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setBlob(Field r1_Field, Object r2_Object, Cursor r3_Cursor, int r4i) {
        try {
            r1_Field.set(r2_Object, r3_Cursor.getBlob(r4i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setBoolean(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Boolean.TYPE)) {
                r2_Field.setBoolean(r3_Object, r4_Cursor.getInt(r5i) != 0);
            } else {
                r2_Field.set(r3_Object, Integer.valueOf(r4_Cursor.getInt(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setDouble(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Double.TYPE)) {
                r2_Field.setDouble(r3_Object, r4_Cursor.getDouble(r5i));
            } else {
                r2_Field.set(r3_Object, Double.valueOf(r4_Cursor.getDouble(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setFloat(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Float.TYPE)) {
                r2_Field.setFloat(r3_Object, r4_Cursor.getFloat(r5i));
            } else {
                r2_Field.set(r3_Object, Float.valueOf(r4_Cursor.getFloat(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setInt(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Integer.TYPE)) {
                r2_Field.setInt(r3_Object, r4_Cursor.getInt(r5i));
            } else {
                r2_Field.set(r3_Object, Integer.valueOf(r4_Cursor.getInt(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setLong(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Long.TYPE)) {
                r2_Field.setLong(r3_Object, r4_Cursor.getLong(r5i));
            } else {
                r2_Field.set(r3_Object, Long.valueOf(r4_Cursor.getLong(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setShort(Field r2_Field, Object r3_Object, Cursor r4_Cursor, int r5i) {
        try {
            if (r2_Field.getType().equals(Short.TYPE)) {
                r2_Field.setShort(r3_Object, r4_Cursor.getShort(r5i));
            } else {
                r2_Field.set(r3_Object, Short.valueOf(r4_Cursor.getShort(r5i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void keep_setString(Field r1_Field, Object r2_Object, Cursor r3_Cursor, int r4i) {
        try {
            r1_Field.set(r2_Object, r3_Cursor.getString(r4i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String type(Class<?> r1_Class_) {
        return (String) c.get(r1_Class_);
    }
}