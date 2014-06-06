package com.crashlytics.android;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

// compiled from: SourceFile
final class ak extends FileOutputStream {
    public static final FilenameFilter a;
    private final String b;
    private File c;
    private boolean d;

    static {
        a = new al();
    }

    public ak(File r4_File, String r5_String) throws FileNotFoundException {
        super(new File(r4_File, r5_String + ".cls_temp"));
        this.d = false;
        this.b = r4_File + File.separator + r5_String;
        this.c = new File(this.b + ".cls_temp");
    }

    public final void a() throws IOException {
        if (this.d) {
        } else {
            this.d = true;
            super.flush();
            super.close();
        }
    }

    public final synchronized void close() throws IOException {
        if (this.d) {
        } else {
            this.d = true;
            super.flush();
            super.close();
            File r1_File = new File(this.b + ".cls");
            if (this.c.renameTo(r1_File)) {
                this.c = null;
            } else {
                String r0_String = RContactStorage.PRIMARY_KEY;
                if (r1_File.exists()) {
                    r0_String = " (target already exists)";
                } else if (!this.c.exists()) {
                    r0_String = " (source does not exist)";
                }
                throw new IOException(new StringBuilder("Could not rename temp file: ").append(this.c).append(" -> ").append(r1_File).append(r0_String).toString());
            }
        }
    }
}