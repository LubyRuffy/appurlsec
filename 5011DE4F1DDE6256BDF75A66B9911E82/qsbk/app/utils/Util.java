package qsbk.app.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import java.util.Random;

public class Util {
    static boolean[] a;

    static {
        a = new boolean[2000];
    }

    public static boolean containsStamp(float r3f, float r4f, View r5_View) {
        Float r0_Float = Float.valueOf(r3f);
        Float r1_Float = Float.valueOf(r4f);
        Rect r2_Rect = new Rect();
        r5_View.getGlobalVisibleRect(r2_Rect);
        return r2_Rect.contains(r0_Float.intValue(), r1_Float.intValue());
    }

    public static int getRandom() {
        new Random().nextInt(2000);
        while (true) {
            int r0i = new Random().nextInt(2000);
            if (!a[r0i]) {
                a[r0i] = true;
                return r0i;
            }
        }
    }

    public static int getStatusBarHeight(Activity r2_Activity) {
        Rect r0_Rect = new Rect();
        r2_Activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r0_Rect);
        return r0_Rect.top;
    }
}