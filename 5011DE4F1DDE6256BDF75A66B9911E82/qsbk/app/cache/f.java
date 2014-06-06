package qsbk.app.cache;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

// compiled from: SecureFileCache.java
class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ SecureFileCache c;

    f(SecureFileCache r1_SecureFileCache, String r2_String, String r3_String) {
        this.c = r1_SecureFileCache;
        this.a = r2_String;
        this.b = r3_String;
    }

    public void run() {
        try {
            OutputStream r1_OutputStream = new FileOutputStream(this.c.b(this.c.c(this.a)), false);
            OutputStreamWriter r0_OutputStreamWriter = new OutputStreamWriter(r1_OutputStream, "utf8");
            r0_OutputStreamWriter.write(this.b);
            r0_OutputStreamWriter.flush();
            r0_OutputStreamWriter.close();
            r1_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}