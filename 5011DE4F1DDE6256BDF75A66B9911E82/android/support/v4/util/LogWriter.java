package android.support.v4.util;

import android.util.Log;
import java.io.Writer;

public class LogWriter extends Writer {
    private final String a;
    private StringBuilder b;

    public LogWriter(String r3_String) {
        this.b = new StringBuilder(128);
        this.a = r3_String;
    }

    private void a() {
        if (this.b.length() > 0) {
            Log.d(this.a, this.b.toString());
            this.b.delete(0, this.b.length());
        }
    }

    public void close() {
        a();
    }

    public void flush() {
        a();
    }

    public void write(char[] r4_charA, int r5i, int r6i) {
        int r0i = 0;
        while (r0i < r6i) {
            char r1c = r4_charA[r5i + r0i];
            if (r1c == '\n') {
                a();
            } else {
                this.b.append(r1c);
            }
            r0i++;
        }
    }
}