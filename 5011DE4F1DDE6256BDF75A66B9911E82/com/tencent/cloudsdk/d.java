package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// compiled from: SourceFile
public class d {
    private static final String a;
    private Object b;
    private Class c;

    static {
        a = d.class.getSimpleName();
    }

    public d() {
        this.b = null;
        this.c = null;
    }

    public static Object a(a r4_a, String r5_String, String r6_String, String r7_String, Class[] r8_ClassA, Object[] r9_ObjectA) {
        Class r1_Class = g.a().a(r4_a, r5_String, r6_String);
        if (r1_Class == null) {
            return null;
        }
        try {
            Method r1_Method = r1_Class.getDeclaredMethod(r7_String, r8_ClassA);
            if (r1_Method == null) {
                return null;
            }
            r1_Method.setAccessible(true);
            return r1_Method.invoke(null, r9_ObjectA);
        } catch (Exception e) {
            Throwable r1_Throwable = e;
            WnsClientLog.d(a, r1_Throwable.getMessage(), r1_Throwable);
            return null;
        }
    }

    public Class a() {
        return this.c;
    }

    public Object a(String r4_String, Class[] r5_ClassA, Object[] r6_ObjectA) {
        if (a() == null || b() == null) {
            return null;
        }
        try {
            Method r0_Method = a().getDeclaredMethod(r4_String, r5_ClassA);
            if (r0_Method != null) {
                r0_Method.setAccessible(true);
                return r0_Method.invoke(b(), r6_ObjectA);
            }
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e(a, r0_Throwable.getMessage(), r0_Throwable);
        }
        return null;
    }

    public void a(a r4_a, String r5_String, String r6_String, Class[] r7_ClassA, Object[] r8_ObjectA) {
        Class r0_Class = g.a().a(r4_a, r5_String, r6_String);
        if (r0_Class != null) {
            this.c = r0_Class;
            try {
                Constructor r0_Constructor = r0_Class.getDeclaredConstructor(r7_ClassA);
                if (r0_Constructor != null) {
                    r0_Constructor.setAccessible(true);
                    this.b = r0_Constructor.newInstance(r8_ObjectA);
                }
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                WnsClientLog.d(a, r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }

    public Object b() {
        return this.b;
    }
}