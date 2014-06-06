package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

// compiled from: SourceFile
public abstract class aA<V> extends aE<V> {
    private final Closeable a;
    private final boolean b;

    protected aA(Closeable r1_Closeable, boolean r2z) {
        this.a = r1_Closeable;
        this.b = r2z;
    }

    protected final void b() throws IOException {
        if (this.a instanceof Flushable) {
            ((Flushable) this.a).flush();
        }
        if (this.b) {
            try {
                this.a.close();
            } catch (IOException e) {
            }
        } else {
            this.a.close();
        }
    }
}