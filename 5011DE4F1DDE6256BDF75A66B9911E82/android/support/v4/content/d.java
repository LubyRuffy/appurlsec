package android.support.v4.content;

import android.content.Context;
import java.io.File;

// compiled from: ContextCompatKitKat.java
class d {
    public static File[] getExternalCacheDirs(Context r1_Context) {
        return r1_Context.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context r1_Context, String r2_String) {
        return r1_Context.getExternalFilesDirs(r2_String);
    }

    public static File[] getObbDirs(Context r1_Context) {
        return r1_Context.getObbDirs();
    }
}