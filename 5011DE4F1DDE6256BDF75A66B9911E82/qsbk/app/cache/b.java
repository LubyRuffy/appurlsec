package qsbk.app.cache;

import android.content.Context;

// compiled from: FileCache.java
final class b extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    b(String r1_String, Context r2_Context, String r3_String, String r4_String) {
        this.a = r2_Context;
        this.b = r3_String;
        this.c = r4_String;
        super(r1_String);
    }

    public void run() {
        if (FileCache.b(this.a, this.b)) {
            FileCache.writeFile(this.a, this.b, this.c);
        }
    }
}