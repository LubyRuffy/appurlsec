package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D> {
    int m;
    OnLoadCompleteListener<D> n;
    Context o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;

    public static interface OnLoadCompleteListener<D> {
        public void onLoadComplete(Loader<D> r1_Loader_D, D r2_D);
    }

    public final class ForceLoadContentObserver extends ContentObserver {
        public ForceLoadContentObserver() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean r2z) {
            Loader.this.onContentChanged();
        }
    }

    public Loader(Context r3_Context) {
        this.p = false;
        this.q = false;
        this.r = true;
        this.s = false;
        this.t = false;
        this.o = r3_Context.getApplicationContext();
    }

    protected void a() {
    }

    public void abandon() {
        this.q = true;
        g();
    }

    public void commitContentChanged() {
        this.t = false;
    }

    protected void d() {
    }

    public String dataToString(D r3_D) {
        StringBuilder r0_StringBuilder = new StringBuilder(64);
        DebugUtils.buildShortClassTag(r3_D, r0_StringBuilder);
        r0_StringBuilder.append("}");
        return r0_StringBuilder.toString();
    }

    public void deliverResult(D r2_D) {
        if (this.n != null) {
            this.n.onLoadComplete(this, r2_D);
        }
    }

    public void dump(String r2_String, FileDescriptor r3_FileDescriptor, PrintWriter r4_PrintWriter, String[] r5_StringA) {
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mId=");
        r4_PrintWriter.print(this.m);
        r4_PrintWriter.print(" mListener=");
        r4_PrintWriter.println(this.n);
        if (this.p || this.s || this.t) {
            r4_PrintWriter.print(r2_String);
            r4_PrintWriter.print("mStarted=");
            r4_PrintWriter.print(this.p);
            r4_PrintWriter.print(" mContentChanged=");
            r4_PrintWriter.print(this.s);
            r4_PrintWriter.print(" mProcessingChange=");
            r4_PrintWriter.println(this.t);
            if (this.q || this.r) {
                r4_PrintWriter.print(r2_String);
                r4_PrintWriter.print("mAbandoned=");
                r4_PrintWriter.print(this.q);
                r4_PrintWriter.print(" mReset=");
                r4_PrintWriter.println(this.r);
            }
        } else if (this.q || this.r) {
            r4_PrintWriter.print(r2_String);
            r4_PrintWriter.print("mAbandoned=");
            r4_PrintWriter.print(this.q);
            r4_PrintWriter.print(" mReset=");
            r4_PrintWriter.println(this.r);
        }
    }

    protected void e() {
    }

    protected void f() {
    }

    public void forceLoad() {
        a();
    }

    protected void g() {
    }

    public Context getContext() {
        return this.o;
    }

    public int getId() {
        return this.m;
    }

    public boolean isAbandoned() {
        return this.q;
    }

    public boolean isReset() {
        return this.r;
    }

    public boolean isStarted() {
        return this.p;
    }

    public void onContentChanged() {
        if (this.p) {
            forceLoad();
        } else {
            this.s = true;
        }
    }

    public void registerListener(int r3i, OnLoadCompleteListener<D> r4_OnLoadCompleteListener_D) {
        if (this.n != null) {
            throw new IllegalStateException("There is already a listener registered");
        } else {
            this.n = r4_OnLoadCompleteListener_D;
            this.m = r3i;
        }
    }

    public void reset() {
        f();
        this.r = true;
        this.p = false;
        this.q = false;
        this.s = false;
        this.t = false;
    }

    public void rollbackContentChanged() {
        if (this.t) {
            this.s = true;
        }
    }

    public final void startLoading() {
        this.p = true;
        this.r = false;
        this.q = false;
        d();
    }

    public void stopLoading() {
        this.p = false;
        e();
    }

    public boolean takeContentChanged() {
        boolean r0z = this.s;
        this.s = false;
        this.t |= r0z;
        return r0z;
    }

    public String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, r0_StringBuilder);
        r0_StringBuilder.append(" id=");
        r0_StringBuilder.append(this.m);
        r0_StringBuilder.append("}");
        return r0_StringBuilder.toString();
    }

    public void unregisterListener(OnLoadCompleteListener<D> r3_OnLoadCompleteListener_D) {
        if (this.n == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.n != r3_OnLoadCompleteListener_D) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.n = null;
        }
    }
}