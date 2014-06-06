package com.crashlytics.android;

import android.os.AsyncTask;
import com.crashlytics.android.internal.v;
import com.qq.e.comm.DownloadService;

// compiled from: SourceFile
public class CrashTest {
    public void crashAsyncTask(long r5j) {
        AsyncTask r0_AsyncTask = new ao(this, r5j);
        Void[] r1_VoidA = new Void[1];
        r1_VoidA[0] = null;
        r0_AsyncTask.execute(r1_VoidA);
    }

    public void indexOutOfBounds() {
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Out of bounds value: ").append(new int[2][10]).toString());
    }

    public void stackOverflow() {
        stackOverflow();
    }

    public void throwFiveChainedExceptions() {
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            throw new RuntimeException(DownloadService.V2, e);
        } catch (Exception e_2) {
            throw new RuntimeException("3", e_2);
        }
    }

    public void throwRuntimeException(String r2_String) {
        throw new RuntimeException(r2_String);
    }
}