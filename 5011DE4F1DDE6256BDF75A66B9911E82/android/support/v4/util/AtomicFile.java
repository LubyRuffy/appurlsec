package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private final File a;
    private final File b;

    public AtomicFile(File r4_File) {
        this.a = r4_File;
        this.b = new File(r4_File.getPath() + ".bak");
    }

    static boolean a(FileOutputStream r1_FileOutputStream) {
        if (r1_FileOutputStream != null) {
            try {
                r1_FileOutputStream.getFD().sync();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public void delete() {
        this.a.delete();
        this.b.delete();
    }

    public void failWrite(FileOutputStream r4_FileOutputStream) {
        if (r4_FileOutputStream != null) {
            a(r4_FileOutputStream);
            try {
                r4_FileOutputStream.close();
                this.a.delete();
                this.b.renameTo(this.a);
            } catch (IOException e) {
                Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    public void finishWrite(FileOutputStream r4_FileOutputStream) {
        if (r4_FileOutputStream != null) {
            a(r4_FileOutputStream);
            try {
                r4_FileOutputStream.close();
                this.b.delete();
            } catch (IOException e) {
                Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    public File getBaseFile() {
        return this.a;
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if (this.b.exists()) {
            this.a.delete();
            this.b.renameTo(this.a);
        }
        return new FileInputStream(this.a);
    }

    public byte[] readFully() throws IOException {
        int r0i = 0;
        FileInputStream r3_FileInputStream = openRead();
        Object r1_Object = new Object[r3_FileInputStream.available()];
        while (true) {
            int r2i = r3_FileInputStream.read(r1_Object, r0i, r1_Object.length - r0i);
            if (r2i <= 0) {
                r3_FileInputStream.close();
                return r1_Object;
            } else {
                Object r0_Object;
                r2i += r0i;
                r0i = r3_FileInputStream.available();
                if (r0i > r1_Object.length - r2i) {
                    r0_Object = new Object[(r0i + r2i)];
                    System.arraycopy(r1_Object, 0, r0_Object, 0, r2i);
                } else {
                    r0_Object = r1_Object;
                }
                r1_Object = r0_Object;
                r0i = r2i;
            }
        }
    }

    public FileOutputStream startWrite() throws IOException {
        if (this.a.exists()) {
            if (this.b.exists()) {
                this.a.delete();
            } else if (!this.a.renameTo(this.b)) {
                Log.w("AtomicFile", "Couldn't rename file " + this.a + " to backup file " + this.b);
            }
        }
        try {
            return new FileOutputStream(this.a);
        } catch (FileNotFoundException e) {
            if (this.a.getParentFile().mkdir()) {
                return new FileOutputStream(this.a);
            }
            throw new IOException("Couldn't create directory " + this.a);
        }
    }
}