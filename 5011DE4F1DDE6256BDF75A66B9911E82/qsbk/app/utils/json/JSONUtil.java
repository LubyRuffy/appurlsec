package qsbk.app.utils.json;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;
import qsbk.app.utils.LogUtil;

public class JSONUtil {
    static HashMap<Class, List<Field>> a;

    static {
        a = new HashMap();
    }

    private static List<Field> a(Class r6_Class) {
        if (r6_Class == null) {
            return null;
        }
        List<Field> r2_List_Field = new LinkedList();
        Class r0_Class = r6_Class;
        while (true) {
            Field[] r3_FieldA = r0_Class.getDeclaredFields();
            int r1i = 0;
            while (r1i < r3_FieldA.length) {
                int r4i = r3_FieldA[r1i].getModifiers();
                if (Modifier.isProtected(r4i) || Modifier.isPublic(r4i)) {
                    if (!Modifier.isStatic(r4i) || r3_FieldA[r1i].getAnnotation(JsonPrivate.class) == null) {
                        r1i++;
                    } else {
                        r2_List_Field.add(r3_FieldA[r1i]);
                        r1i++;
                    }
                } else if (Modifier.isPrivate(r4i) && r0_Class == r6_Class && !Modifier.isStatic(r4i) && r3_FieldA[r1i].getAnnotation(JsonPrivate.class) == null) {
                    r2_List_Field.add(r3_FieldA[r1i]);
                    r1i++;
                } else {
                    r1i++;
                }
            }
            r0_Class = r0_Class.getSuperclass();
            if (r0_Class == JSONAble.class) {
                return r2_List_Field;
            }
        }
    }

    public static JSONObject encodeToJsonObject(IJSONAble r10_IJSONAble) {
        Class r1_Class = r10_IJSONAble.getClass();
        List r0_List = (List) a.get(r1_Class);
        if (r0_List == null) {
            r0_List = a(r1_Class);
            a.put(r1_Class, r0_List);
        }
        List r2_List = r0_List;
        JSONObject r7_JSONObject = new JSONObject();
        int r3i = 0;
        while (r3i < r2_List.size()) {
            String r5_String;
            Field r0_Field = (Field) r2_List.get(r3i);
            JsonKeyName r1_JsonKeyName = (JsonKeyName) r0_Field.getAnnotation(JsonKeyName.class);
            r5_String = (r1_JsonKeyName == null || TextUtils.isEmpty(r1_JsonKeyName.value())) ? r0_Field.getName() : r1_JsonKeyName.value();
            if (r0_Field.getAnnotation(JsonPrivate.class) != null) {
                r3i++;
            } else {
                Type r1_Type = r0_Field.getGenericType();
                try {
                    r0_Field.setAccessible(true);
                    if (r1_Type == Boolean.TYPE) {
                        JsonToInt r1_JsonToInt = (JsonToInt) r0_Field.getAnnotation(JsonToInt.class);
                        boolean r8z = r0_Field.getBoolean(r10_IJSONAble);
                        if (r1_JsonToInt != null) {
                            r7_JSONObject.put(r5_String, r8z ? 1 : 0);
                        } else {
                            r7_JSONObject.put(r5_String, r8z);
                        }
                    } else if (r1_Type == Integer.TYPE) {
                        r7_JSONObject.put(r5_String, r0_Field.getInt(r10_IJSONAble));
                    } else if (r1_Type == Long.TYPE) {
                        r7_JSONObject.put(r5_String, r0_Field.getLong(r10_IJSONAble));
                    } else if (r1_Type == String.class) {
                        Object r1_Object = r0_Field.get(r10_IJSONAble);
                        if (r1_Object != null) {
                            r7_JSONObject.put(r5_String, r1_Object);
                        }
                    } else if (r1_Type == Double.TYPE) {
                        r7_JSONObject.put(r5_String, r0_Field.getDouble(r10_IJSONAble));
                    } else {
                        LogUtil.e("un handle json field:" + r5_String);
                    }
                    r0_Field.setAccessible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    r0_Field.setAccessible(false);
                }
                r3i++;
            }
        }
        return r7_JSONObject;
    }

    public static void parseFromJSONObject(JSONObject r9_JSONObject, IJSONAble r10_IJSONAble) {
        if (r9_JSONObject == null) {
            LogUtil.e("obj is null");
        } else {
            Class r1_Class = r10_IJSONAble.getClass();
            List r0_List = (List) a.get(r1_Class);
            if (r0_List == null) {
                r0_List = a(r1_Class);
                a.put(r1_Class, r0_List);
            }
            List r2_List = r0_List;
            r10_IJSONAble.initJSONDefaultValue();
            int r6i = r2_List.size();
            int r5i = 0;
            while (r5i < r6i) {
                String r1_String;
                Field r0_Field = (Field) r2_List.get(r5i);
                JsonKeyName r1_JsonKeyName = (JsonKeyName) r0_Field.getAnnotation(JsonKeyName.class);
                r1_String = (r1_JsonKeyName == null || TextUtils.isEmpty(r1_JsonKeyName.value())) ? r0_Field.getName() : r1_JsonKeyName.value();
                if (r0_Field.getAnnotation(JsonPrivate.class) == null && r9_JSONObject.has(r1_String) && (!r9_JSONObject.isNull(r1_String))) {
                    Type r7_Type = r0_Field.getGenericType();
                    try {
                        r0_Field.setAccessible(true);
                        if (r7_Type == Boolean.TYPE) {
                            String r7_String = r9_JSONObject.optString(r1_String).toLowerCase();
                            if (TextUtils.isDigitsOnly(r7_String)) {
                                r0_Field.setBoolean(r10_IJSONAble, Integer.parseInt(r7_String) != 0);
                            } else {
                                r0_Field.setBoolean(r10_IJSONAble, r9_JSONObject.optBoolean(r1_String));
                            }
                        } else if (r7_Type == String.class) {
                            r0_Field.set(r10_IJSONAble, r9_JSONObject.optString(r1_String));
                        } else if (r7_Type == Long.TYPE) {
                            r0_Field.set(r10_IJSONAble, Long.valueOf(r9_JSONObject.optLong(r1_String)));
                        } else if (r7_Type == Integer.TYPE) {
                            r0_Field.set(r10_IJSONAble, Integer.valueOf(r9_JSONObject.optInt(r1_String)));
                        } else if (r7_Type == Double.TYPE) {
                            r0_Field.set(r10_IJSONAble, Double.valueOf(r9_JSONObject.optDouble(r1_String)));
                        } else {
                            LogUtil.e("unhandeld json field:" + r1_String);
                        }
                        r0_Field.setAccessible(false);
                    } catch (Exception e) {
                        Exception r1_Exception = e;
                        LogUtil.e("set fields error");
                        r1_Exception.printStackTrace();
                        r0_Field.setAccessible(false);
                    }
                    r5i++;
                } else {
                    r5i++;
                }
            }
        }
    }
}