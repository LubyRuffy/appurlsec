package com.aps;

// compiled from: Reflect.java
public class l {
    static int a(Object r5_Object, String r6_String, Object ... r7_ObjectA) throws Exception {
        Class r1_Class = r5_Object.getClass();
        Class[] r2_ClassA = new Class[r7_ObjectA.length];
        int r0i = 0;
        int r3i = r7_ObjectA.length;
        while (r0i < r3i) {
            r2_ClassA[r0i] = r7_ObjectA[r0i].getClass();
            r0i++;
        }
        return ((Integer) r1_Class.getMethod(r6_String, r2_ClassA).invoke(r5_Object, r7_ObjectA)).intValue();
    }

    static Object a(String r2_String, String r3_String) throws Exception {
        Class r0_Class = Class.forName(r2_String);
        return r0_Class.getField(r3_String).get(r0_Class);
    }

    static Object a(String r2_String, String r3_String, Object[] r4_ObjectA, Class<?>[] r5_Class_A) throws Exception {
        return Class.forName(r2_String).getMethod(r3_String, r5_Class_A).invoke(null, r4_ObjectA);
    }

    static int b(String r1_String, String r2_String) throws Exception {
        return ((Integer) a(r1_String, r2_String)).intValue();
    }
}