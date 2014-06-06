package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

// compiled from: ActionBarDrawerToggleHoneycomb.java
class a {
    private static final int[] a;

    // compiled from: ActionBarDrawerToggleHoneycomb.java
    private static class a {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        a(Activity r9_Activity) {
            try {
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Drawable.class;
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", r2_ClassA);
                r2_ClassA = new Class[1];
                r2_ClassA[0] = Integer.TYPE;
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", r2_ClassA);
            } catch (NoSuchMethodException e) {
                View r0_View = r9_Activity.findViewById(16908332);
                if (r0_View != null) {
                    ViewGroup r0_ViewGroup = (ViewGroup) r0_View.getParent();
                    if (r0_ViewGroup.getChildCount() == 2) {
                        View r1_View = r0_ViewGroup.getChildAt(0);
                        r0_View = r0_ViewGroup.getChildAt(1);
                        if (r1_View.getId() == 16908332) {
                            if (!r0_View instanceof ImageView) {
                                this.upIndicatorView = (ImageView) r0_View;
                            }
                        } else {
                            r0_View = r1_View;
                            if (r0_View instanceof ImageView) {
                            } else {
                                this.upIndicatorView = (ImageView) r0_View;
                            }
                        }
                    }
                }
            }
        }
    }

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = 16843531;
        a = r0_intA;
    }

    public static Drawable getThemeUpIndicator(Activity r2_Activity) {
        TypedArray r0_TypedArray = r2_Activity.obtainStyledAttributes(a);
        Drawable r1_Drawable = r0_TypedArray.getDrawable(0);
        r0_TypedArray.recycle();
        return r1_Drawable;
    }

    public static Object setActionBarDescription(Object r6_Object, Activity r7_Activity, int r8i) {
        a r1_a;
        r1_a = r6_Object == null ? new a(r7_Activity) : r6_Object;
        a r0_a = r1_a;
        if (r0_a.setHomeAsUpIndicator != null) {
            try {
                ActionBar r2_ActionBar = r7_Activity.getActionBar();
                Method r0_Method = r0_a.setHomeActionContentDescription;
                Object[] r3_ObjectA = new Object[1];
                r3_ObjectA[0] = Integer.valueOf(r8i);
                r0_Method.invoke(r2_ActionBar, r3_ObjectA);
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return r1_a;
    }

    public static Object setActionBarUpIndicator(Object r6_Object, Activity r7_Activity, Drawable r8_Drawable, int r9i) {
        a r1_a;
        r1_a = r6_Object == null ? new a(r7_Activity) : r6_Object;
        a r0_a = r1_a;
        if (r0_a.setHomeAsUpIndicator != null) {
            try {
                ActionBar r2_ActionBar = r7_Activity.getActionBar();
                Method r3_Method = r0_a.setHomeAsUpIndicator;
                Object[] r4_ObjectA = new Object[1];
                r4_ObjectA[0] = r8_Drawable;
                r3_Method.invoke(r2_ActionBar, r4_ObjectA);
                Method r0_Method = r0_a.setHomeActionContentDescription;
                Object[] r3_ObjectA = new Object[1];
                r3_ObjectA[0] = Integer.valueOf(r9i);
                r0_Method.invoke(r2_ActionBar, r3_ObjectA);
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        } else if (r0_a.upIndicatorView != null) {
            r0_a.upIndicatorView.setImageDrawable(r8_Drawable);
        } else {
            Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator");
        }
        return r1_a;
    }
}