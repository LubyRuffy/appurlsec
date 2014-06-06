package com.crashlytics.android;

import android.os.AsyncTask;

// compiled from: SourceFile
final class ao extends AsyncTask<Void, Void, Void> {
    private /* synthetic */ long a;
    private /* synthetic */ CrashTest b;

    ao(CrashTest r1_CrashTest, long r2j) {
        this.b = r1_CrashTest;
        this.a = r2j;
    }

    private Void a() {
        try {
            Thread.sleep(this.a);
        } catch (InterruptedException e) {
        }
        this.b.throwRuntimeException("Background thread crash");
        return null;
    }

    protected final /* synthetic */ Object doInBackground(Object[] r2_ObjectA) {
        return a();
    }
}