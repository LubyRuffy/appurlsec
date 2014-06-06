package android.support.v4.content;

import android.content.Context;
import java.io.File;

// compiled from: ContextCompatFroyo.java
class a {
    public static File getExternalCacheDir(Context r1_Context) {
        return r1_Context.getExternalCacheDir();
    }

    public static File getExternalFilesDir(Context r1_Context, String r2_String) {
        return r1_Context.getExternalFilesDir(r2_String);
    }
}