package qsbk.app.cache;

import android.content.Context;
import java.io.File;

// compiled from: FileCache.java
final class c extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    c(String r1_String, Context r2_Context, String r3_String, String r4_String) {
        this.a = r2_Context;
        this.b = r3_String;
        this.c = r4_String;
        super(r1_String);
    }

    public void run() {
        File r0_File = FileCache.getDiskCacheDir(this.a, this.b);
        if (r0_File.exists()) {
            r0_File.delete();
        }
        FileCache.writeFile(this.a, this.b, this.c);
    }
}