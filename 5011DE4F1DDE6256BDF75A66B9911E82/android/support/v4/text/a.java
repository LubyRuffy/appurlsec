package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// compiled from: ICUCompatIcs.java
class a {
    private static Method a;
    private static Method b;

    static {
        try {
            Class r0_Class = Class.forName("libcore.icu.ICU");
            if (r0_Class != null) {
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = String.class;
                a = r0_Class.getMethod("getScript", r2_ClassA);
                r2_ClassA = new Class[1];
                r2_ClassA[0] = String.class;
                b = r0_Class.getMethod("addLikelySubtags", r2_ClassA);
            }
        } catch (Exception e) {
            Log.w("ICUCompatIcs", e);
        }
    }

    public static String addLikelySubtags(String r3_String) {
        try {
            if (b != null) {
                Object[] r0_ObjectA = new Object[1];
                r0_ObjectA[0] = r3_String;
                return (String) b.invoke(null, r0_ObjectA);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompatIcs", e);
        } catch (InvocationTargetException e_2) {
            Log.w("ICUCompatIcs", e_2);
        }
        return r3_String;
    }

    public static String getScript(String r4_String) {
        try {
            if (a != null) {
                Object[] r0_ObjectA = new Object[1];
                r0_ObjectA[0] = r4_String;
                return (String) a.invoke(null, r0_ObjectA);
            }
        } catch (IllegalAccessException e) {
            Log.w("ICUCompatIcs", e);
        } catch (InvocationTargetException e_2) {
            Log.w("ICUCompatIcs", e_2);
        }
        return null;
    }
}