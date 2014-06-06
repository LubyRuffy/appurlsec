package com.baidu.android.silentupdate;

import android.content.Context;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class b {
    public static ClassLoader a(String r6_String, String r7_String, String r8_String, Context r9_Context) {
        try {
            return new DexClassLoader(r6_String, r7_String, r8_String, r9_Context.getClassLoader());
        } catch (Exception e) {
            DexFile r2_DexFile = new DexFile(r9_Context.getPackageResourcePath());
            Class r1_Class = Class.forName("dalvik.system.DexFile");
            Class[] r3_ClassA = new Class[3];
            r3_ClassA[0] = String.class;
            r3_ClassA[1] = String.class;
            r3_ClassA[2] = Integer.TYPE;
            Method r0_Method = r1_Class.getDeclaredMethod("openDexFile", r3_ClassA);
            r0_Method.setAccessible(true);
            Object[] r3_ObjectA = new Object[3];
            r3_ObjectA[0] = r6_String;
            r3_ObjectA[1] = r7_String;
            r3_ObjectA[2] = Integer.valueOf(0);
            int r0i = ((Integer) r0_Method.invoke(r2_DexFile, r3_ObjectA)).intValue();
            Field r3_Field = r1_Class.getDeclaredField("mCookie");
            r3_Field.setAccessible(true);
            r3_Field.set(r2_DexFile, Integer.valueOf(r0i));
            Field r0_Field = r1_Class.getDeclaredField("mFileName");
            r0_Field.setAccessible(true);
            r0_Field.set(r2_DexFile, r6_String);
            ClassLoader r1_ClassLoader = new PathClassLoader(r9_Context.getPackageResourcePath(), r9_Context.getClassLoader());
            r0_Field = Class.forName("dalvik.system.BaseDexClassLoader").getDeclaredField("pathList");
            r0_Field.setAccessible(true);
            Object r0_Object = r0_Field.get(r1_ClassLoader);
            r3_Field = Class.forName("dalvik.system.DexPathList").getDeclaredField("dexElements");
            r3_Field.setAccessible(true);
            Object[] r0_ObjectA = (Object[]) r3_Field.get(r0_Object);
            if (r0_ObjectA.length > 0) {
                r3_Field = Class.forName("dalvik.system.DexPathList$Element").getDeclaredField("dexFile");
                r3_Field.setAccessible(true);
                r3_Field.set(r0_ObjectA[0], r2_DexFile);
                return r1_ClassLoader;
            }
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(ClassLoader r3_ClassLoader, ClassLoader r4_ClassLoader) {
        /*
        r0 = 1;
        r1 = r4;
    L_0x0002:
        if (r1 == 0) goto L_0x0013;
    L_0x0004:
        if (r1 != r3) goto L_0x000e;
    L_0x0006:
        r1 = "PushClassloader";
        r2 = "the classloader has been inserted";
        android.util.Log.d(r1, r2);
    L_0x000d:
        return r0;
    L_0x000e:
        r1 = r1.getParent();
        goto L_0x0002;
    L_0x0013:
        r1 = "java.lang.ClassLoader";
        r1 = java.lang.Class.forName(r1);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        r2 = "parent";
        r1 = r1.getDeclaredField(r2);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        r2 = 1;
        r1.setAccessible(r2);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        r2 = r1.get(r4);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        r1.set(r3, r2);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        r1.set(r4, r3);	 //Catch:{ ClassNotFoundException -> 0x002e, SecurityException -> 0x0037, NoSuchFieldException -> 0x0035, IllegalArgumentException -> 0x0033, IllegalAccessException -> 0x0031 }
        goto L_0x000d;
    L_0x002e:
        r0 = move-exception;
    L_0x002f:
        r0 = 0;
        goto L_0x000d;
    L_0x0031:
        r0 = move-exception;
        goto L_0x002f;
    L_0x0033:
        r0 = move-exception;
        goto L_0x002f;
    L_0x0035:
        r0 = move-exception;
        goto L_0x002f;
    L_0x0037:
        r0 = move-exception;
        goto L_0x002f;
        */

    }
}