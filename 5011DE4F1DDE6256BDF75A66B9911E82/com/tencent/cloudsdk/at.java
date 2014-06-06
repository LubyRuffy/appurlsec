package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// compiled from: SourceFile
public class at implements ar {
    private static final String d;
    public FileChannel a;
    public FileLock b;
    public FileOutputStream c;

    static {
        d = at.class.getSimpleName();
    }

    public at() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public synchronized FileLock a(File r6_File) {
        FileLock r0_FileLock = null;
        synchronized (this) {
            if (r6_File != null) {
                try {
                    this.c = new FileOutputStream(r6_File);
                    this.a = this.c.getChannel();
                    this.b = this.a.tryLock();
                    if (this.b != null) {
                        r0_FileLock = this.b;
                    }
                } catch (Exception e) {
                    Throwable r1_Throwable = e;
                    WnsClientLog.e(d, new StringBuilder(">>> tryLock E: ").append(r1_Throwable.getMessage()).toString(), r1_Throwable);
                }
            }
        }
        return r0_FileLock;
    }

    public synchronized void a() {
        try {
            if (this.b != null) {
                this.b.release();
            }
            if (this.a != null) {
                this.a.close();
            }
            if (this.c != null) {
                this.c.close();
            }
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e(d, new StringBuilder(">>> releaseLock E: ").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
        }
    }
}