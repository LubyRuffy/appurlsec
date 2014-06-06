package qsbk.app.compat;

import android.os.Build.VERSION;
import qsbk.app.R;

public final class ThemeCompat {
    public static int getSimpleListItem() {
        return preHoneycomb() ? R.layout.text_item : 17367043;
    }

    public static boolean preHoneycomb() {
        return VERSION.SDK_INT < 11;
    }
}