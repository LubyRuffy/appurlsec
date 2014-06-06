package com.crashlytics.android.internal;

import android.content.Context;

// compiled from: SourceFile
public abstract class p {
    private Context a;
    private boolean b;

    protected final synchronized void a(Context r4_Context) {
        if (this.b) {
        } else if (r4_Context == null) {
            throw new IllegalArgumentException("context cannot be null.");
        } else {
            this.a = new cm(r4_Context.getApplicationContext(), getName());
            this.b = true;
            c();
        }
    }

    protected abstract void c();

    public String getComponentPath() {
        return new StringBuilder(".TwitterSdk/").append(getName()).toString();
    }

    public final Context getContext() {
        return this.a;
    }

    public final String getName() {
        return getClass().getSimpleName().toLowerCase();
    }

    public abstract String getVersion();

    public final synchronized boolean isInitialized() {
        return this.b;
    }
}