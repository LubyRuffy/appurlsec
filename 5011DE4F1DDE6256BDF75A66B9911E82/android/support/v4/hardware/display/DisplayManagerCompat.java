package android.support.v4.hardware.display;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> a;

    private static class a extends DisplayManagerCompat {
        private final Object a;

        public a(Context r2_Context) {
            this.a = a.getDisplayManager(r2_Context);
        }

        public Display getDisplay(int r2i) {
            return a.getDisplay(this.a, r2i);
        }

        public Display[] getDisplays() {
            return a.getDisplays(this.a);
        }

        public Display[] getDisplays(String r2_String) {
            return a.getDisplays(this.a, r2_String);
        }
    }

    private static class b extends DisplayManagerCompat {
        private final WindowManager a;

        public b(Context r2_Context) {
            this.a = (WindowManager) r2_Context.getSystemService("window");
        }

        public Display getDisplay(int r3i) {
            Display r0_Display = this.a.getDefaultDisplay();
            return r0_Display.getDisplayId() == r3i ? r0_Display : null;
        }

        public Display[] getDisplays() {
            Display[] r0_DisplayA = new Display[1];
            r0_DisplayA[0] = this.a.getDefaultDisplay();
            return r0_DisplayA;
        }

        public Display[] getDisplays(String r2_String) {
            return r2_String == null ? getDisplays() : new Display[0];
        }
    }

    static {
        a = new WeakHashMap();
    }

    DisplayManagerCompat() {
    }

    public static DisplayManagerCompat getInstance(Context r3_Context) {
        DisplayManagerCompat r0_DisplayManagerCompat;
        synchronized (a) {
            r0_DisplayManagerCompat = (DisplayManagerCompat) a.get(r3_Context);
            if (r0_DisplayManagerCompat == null) {
                r0_DisplayManagerCompat = VERSION.SDK_INT >= 17 ? new a(r3_Context) : new b(r3_Context);
                a.put(r3_Context, r0_DisplayManagerCompat);
            }
        }
        return r0_DisplayManagerCompat;
    }

    public abstract Display getDisplay(int r1i);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String r1_String);
}