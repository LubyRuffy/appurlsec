package com.baidu.mobads;

import android.app.Activity;
import com.baidu.mobads.a.b;
import com.baidu.mobads.a.d;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class IconsAd {
    private static Class<?> a;
    private Object b;

    public IconsAd(Activity r2_Activity) {
        this(r2_Activity, new int[0]);
    }

    public IconsAd(Activity r5_Activity, int[] r6_intA) {
        try {
            if (a == null) {
                a = b.b(r5_Activity, "com.baidu.mobads.remote.IconsAd");
            }
            if (r6_intA == null) {
                r6_intA = new int[0];
            }
            Class r0_Class = a;
            Class[] r1_ClassA = new Class[2];
            r1_ClassA[0] = Activity.class;
            r1_ClassA[1] = int[].class;
            Constructor r0_Constructor = r0_Class.getConstructor(r1_ClassA);
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = r5_Activity;
            r1_ObjectA[1] = r6_intA;
            this.b = r0_Constructor.newInstance(r1_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }

    public void loadAd(Activity r6_Activity) {
        try {
            Class r0_Class = a;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = Activity.class;
            Method r0_Method = r0_Class.getDeclaredMethod("loadAd", r2_ClassA);
            r0_Method.setAccessible(true);
            Object r1_Object = this.b;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r6_Activity;
            r0_Method.invoke(r1_Object, r2_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
    }
}