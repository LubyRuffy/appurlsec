package com.baidu.mobads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.widget.RelativeLayout;
import com.baidu.mobads.a.b;
import com.baidu.mobads.a.d;
import java.lang.reflect.Method;

public final class MultiFuncService {
    private static Class<?> a;
    private static final MultiFuncService b;
    private static Object c;

    static {
        b = new MultiFuncService();
        c = null;
    }

    private MultiFuncService() {
    }

    public static MultiFuncService getInstance(Context r5_Context) {
        try {
            if (c == null) {
                if (a == null) {
                    a = b.b(r5_Context, "com.baidu.mobads.remote.MultiFuncService");
                }
                Class r0_Class = a;
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Context.class;
                Method r0_Method = r0_Class.getDeclaredMethod("getInstance", r2_ClassA);
                r0_Method.setAccessible(true);
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r5_Context;
                c = r0_Method.invoke(null, r2_ObjectA);
            }
        } catch (Exception e) {
            d.b(e);
        }
        return b;
    }

    public void removeFromFloatView(Activity r6_Activity, AdView r7_AdView) {
        try {
            if (a == null || c == null) {
                d.b("You should invode like MultiFuncService.getInstance().removeFromFloatView(...)");
            } else {
                Class r0_Class = a;
                Class[] r2_ClassA = new Class[2];
                r2_ClassA[0] = Activity.class;
                r2_ClassA[1] = RelativeLayout.class;
                Method r0_Method = r0_Class.getDeclaredMethod("removeFromFloatView", r2_ClassA);
                r0_Method.setAccessible(true);
                Object r1_Object = c;
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[0] = r6_Activity;
                r2_ObjectA[1] = r7_AdView;
                r0_Method.invoke(r1_Object, r2_ObjectA);
            }
        } catch (Exception e) {
            d.b(e);
        }
    }

    public void showInFloatView(Activity r6_Activity, AdView r7_AdView, Rect r8_Rect) {
        try {
            if (a == null || c == null) {
                d.b("You should invode like MultiFuncService.getInstance().showInFloatView(...)");
            } else {
                Class r0_Class = a;
                Class[] r2_ClassA = new Class[3];
                r2_ClassA[0] = Activity.class;
                r2_ClassA[1] = RelativeLayout.class;
                r2_ClassA[2] = Rect.class;
                Method r0_Method = r0_Class.getDeclaredMethod("showInFloatView", r2_ClassA);
                r0_Method.setAccessible(true);
                Object r1_Object = c;
                Object[] r2_ObjectA = new Object[3];
                r2_ObjectA[0] = r6_Activity;
                r2_ObjectA[1] = r7_AdView;
                r2_ObjectA[2] = r8_Rect;
                r0_Method.invoke(r1_Object, r2_ObjectA);
            }
        } catch (Exception e) {
            d.b(e);
        }
    }

    public void videoPreLoad(Activity r6_Activity, String r7_String) {
        try {
            if (a == null || c == null) {
                d.b("You should invode like MultiFuncService.getInstance().videoPreLoad(...)");
            } else {
                Class r0_Class = a;
                Class[] r2_ClassA = new Class[2];
                r2_ClassA[0] = Activity.class;
                r2_ClassA[1] = String.class;
                Method r0_Method = r0_Class.getDeclaredMethod("videoPreLoad", r2_ClassA);
                r0_Method.setAccessible(true);
                Object r1_Object = c;
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[0] = r6_Activity;
                r2_ObjectA[1] = r7_String;
                r0_Method.invoke(r1_Object, r2_ObjectA);
            }
        } catch (Exception e) {
            d.b(e);
        }
    }
}