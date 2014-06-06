package qsbk.app.utils.image.issue;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;

public class IOExceptionWrapper extends IOException {
    private final int a;
    private final IOException b;
    private final String c;

    public IOExceptionWrapper(int r2i, IOException r3_IOException) {
        this.a = r2i;
        this.b = r3_IOException;
        this.c = null;
    }

    public IOExceptionWrapper(int r1i, String r2_String, IOException r3_IOException) {
        this.a = r1i;
        this.b = r3_IOException;
        this.c = r2_String;
    }

    public int getResponseCode() {
        return this.a;
    }

    public String toString() {
        return this.b.toString() + "&responseCode=" + this.a + (this.c == null ? RContactStorage.PRIMARY_KEY : "&extra=" + this.c);
    }
}