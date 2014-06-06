package com.baidu.frontia;

import android.content.Context;
import com.baidu.frontia.framework.IModule;
import java.lang.reflect.Method;

class a {
    public static void a_(Context r7_Context, String r8_String) {
        try {
            String[] r2_StringA = a();
            if (r2_StringA != null) {
                int r1i = 0;
                while (r1i < r2_StringA.length) {
                    String r0_String = r2_StringA[r1i];
                    if (r0_String == null || r0_String.length() <= 0) {
                        r1i++;
                    } else {
                        Class r0_Class = Class.forName(r0_String);
                        if (r0_Class != null) {
                            Class[] r4_ClassA = new Class[1];
                            r4_ClassA[0] = Context.class;
                            Method r0_Method = r0_Class.getMethod("newInstance", r4_ClassA);
                            if (r0_Method != null) {
                                Object[] r4_ObjectA = new Object[1];
                                r4_ObjectA[0] = r7_Context;
                                IModule r0_IModule = (IModule) r0_Method.invoke(null, r4_ObjectA);
                                if (r0_IModule != null) {
                                    r0_IModule.init(r8_String);
                                }
                            }
                        }
                        r1i++;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static String[] a_() {
        String[] r0_StringA = new String[1];
        r0_StringA[0] = "com.baidu.frontia.api.FrontiaPush";
        return r0_StringA;
    }
}