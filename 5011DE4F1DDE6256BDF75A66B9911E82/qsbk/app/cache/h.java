package qsbk.app.cache;

import java.io.File;
import qsbk.app.cache.SecureFileCache.Callback;

// compiled from: SecureFileCache.java
class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Callback b;
    final /* synthetic */ SecureFileCache c;

    h(SecureFileCache r1_SecureFileCache, String r2_String, Callback r3_Callback) {
        this.c = r1_SecureFileCache;
        this.a = r2_String;
        this.b = r3_Callback;
    }

    public void run() {
        File r0_File = this.c.b(this.c.c(this.a));
        this.b.onFinished(r0_File, SecureFileCache.b(r0_File));
    }
}