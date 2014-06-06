package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

// compiled from: DisplayManagerJellybeanMr1.java
final class a {
    public static Display getDisplay(Object r1_Object, int r2i) {
        return ((DisplayManager) r1_Object).getDisplay(r2i);
    }

    public static Object getDisplayManager(Context r1_Context) {
        return r1_Context.getSystemService("display");
    }

    public static Display[] getDisplays(Object r1_Object) {
        return ((DisplayManager) r1_Object).getDisplays();
    }

    public static Display[] getDisplays(Object r1_Object, String r2_String) {
        return ((DisplayManager) r1_Object).getDisplays(r2_String);
    }
}