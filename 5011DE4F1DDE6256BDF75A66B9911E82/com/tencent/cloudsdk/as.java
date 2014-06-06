package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// compiled from: SourceFile
public class as implements ar {
    private static final String d;
    public FileChannel a;
    public FileLock b;
    public FileInputStream c;

    static {
        d = as.class.getSimpleName();
    }

    public as() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public synchronized FileLock a(File r8_File) {
        FileLock r0_FileLock;
        if (r8_File != null) {
            this.c = new FileInputStream(r8_File);
            this.a = this.c.getChannel();
            int r6i = 0;
            while (true) {
                this.b = this.a.tryLock(0, MAlarmHandler.NEXT_FIRE_INTERVAL, true);
                if (this.b != null) {
                    r0_FileLock = this.b;
                } else {
                    WnsClientLog.i(d, "\u5176\u4ed6\u4eba\u5728\u4f7f\u7528\u6587\u4ef6\uff0c\u7b49\u5f85\u4e00\u4f1a\u513f~~");
                    int r0i = r6i + 300;
                    if (r0i > 10000) {
                        r0_FileLock = null;
                    } else {
                        Thread.sleep(300);
                        r6i = r0i;
                    }
                }
            }
        } else {
            r0_FileLock = null;
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