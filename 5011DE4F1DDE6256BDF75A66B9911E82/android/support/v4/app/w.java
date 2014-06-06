package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

// compiled from: LoaderManager.java
class w extends LoaderManager {
    static boolean a;
    final SparseArrayCompat<a> b;
    final SparseArrayCompat<a> c;
    final String d;
    FragmentActivity e;
    boolean f;
    boolean g;
    boolean h;

    // compiled from: LoaderManager.java
    final class a implements OnLoadCompleteListener<Object> {
        final int a;
        final Bundle b;
        LoaderCallbacks<Object> c;
        Loader<Object> d;
        boolean e;
        boolean f;
        Object g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        a n;

        public a(int r2i, Bundle r3_Bundle, LoaderCallbacks<Object> r4_LoaderCallbacks_Object) {
            this.a = r2i;
            this.b = r3_Bundle;
            this.c = r4_LoaderCallbacks_Object;
        }

        void a_() {
            if (this.i && this.j) {
                this.h = true;
            } else {
                if (!this.h) {
                    this.h = true;
                    if (a) {
                        Log.v("LoaderManager", "  Starting: " + this);
                    }
                    if (this.d != null || this.c == null) {
                    } else {
                        this.d = this.c.onCreateLoader(this.a, this.b);
                    }
                    if (this.d != null) {
                        if ((!this.d.getClass().isMemberClass()) || Modifier.isStatic(this.d.getClass().getModifiers())) {
                            if (!this.m) {
                                this.d.registerListener(this.a, this);
                                this.m = true;
                            }
                            this.d.startLoading();
                        } else {
                            throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.d);
                        }
                    }
                }
            }
        }

        void a_(Loader<Object> r5_Loader_Object, Object r6_Object) {
            if (this.c != null) {
                String r1_String;
                if (w.this.e != null) {
                    String r0_String = w.this.e.b.u;
                    w.this.e.b.u = "onLoadFinished";
                    r1_String = r0_String;
                } else {
                    r1_String = null;
                }
                try {
                    if (a) {
                        Log.v("LoaderManager", "  onLoadFinished in " + r5_Loader_Object + ": " + r5_Loader_Object.dataToString(r6_Object));
                    }
                    this.c.onLoadFinished(r5_Loader_Object, r6_Object);
                    if (w.this.e != null) {
                        w.this.e.b.u = r1_String;
                    }
                    this.f = true;
                } catch (Throwable th) {
                    if (w.this.e != null) {
                        w.this.e.b.u = r1_String;
                    }
                }
            }
        }

        void b() {
            if (a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.i = true;
            this.j = this.h;
            this.h = false;
            this.c = null;
        }

        void c() {
            if (this.i) {
                if (a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.i = false;
                if (this.h == this.j || this.h) {
                } else {
                    e();
                }
            }
            if (this.h && this.e && !(this.k)) {
                a(this.d, this.g);
            }
        }

        void d() {
            if (this.h && this.k) {
                this.k = false;
                if (this.e) {
                    a(this.d, this.g);
                }
            }
        }

        public void dump(String r4_String, FileDescriptor r5_FileDescriptor, PrintWriter r6_PrintWriter, String[] r7_StringA) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mId=");
            r6_PrintWriter.print(this.a);
            r6_PrintWriter.print(" mArgs=");
            r6_PrintWriter.println(this.b);
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mCallbacks=");
            r6_PrintWriter.println(this.c);
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mLoader=");
            r6_PrintWriter.println(this.d);
            if (this.d != null) {
                this.d.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
            }
            if (this.e || this.f) {
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mHaveData=");
                r6_PrintWriter.print(this.e);
                r6_PrintWriter.print("  mDeliveredData=");
                r6_PrintWriter.println(this.f);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mData=");
                r6_PrintWriter.println(this.g);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mStarted=");
                r6_PrintWriter.print(this.h);
                r6_PrintWriter.print(" mReportNextStart=");
                r6_PrintWriter.print(this.k);
                r6_PrintWriter.print(" mDestroyed=");
                r6_PrintWriter.println(this.l);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mRetaining=");
                r6_PrintWriter.print(this.i);
                r6_PrintWriter.print(" mRetainingStarted=");
                r6_PrintWriter.print(this.j);
                r6_PrintWriter.print(" mListenerRegistered=");
                r6_PrintWriter.println(this.m);
                if (this.n == null) {
                    r6_PrintWriter.print(r4_String);
                    r6_PrintWriter.println("Pending Loader ");
                    r6_PrintWriter.print(this.n);
                    r6_PrintWriter.println(":");
                    this.n.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
                }
            } else {
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mStarted=");
                r6_PrintWriter.print(this.h);
                r6_PrintWriter.print(" mReportNextStart=");
                r6_PrintWriter.print(this.k);
                r6_PrintWriter.print(" mDestroyed=");
                r6_PrintWriter.println(this.l);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("mRetaining=");
                r6_PrintWriter.print(this.i);
                r6_PrintWriter.print(" mRetainingStarted=");
                r6_PrintWriter.print(this.j);
                r6_PrintWriter.print(" mListenerRegistered=");
                r6_PrintWriter.println(this.m);
                if (this.n == null) {
                } else {
                    r6_PrintWriter.print(r4_String);
                    r6_PrintWriter.println("Pending Loader ");
                    r6_PrintWriter.print(this.n);
                    r6_PrintWriter.println(":");
                    this.n.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
                }
            }
        }

        void e() {
            if (a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.h = false;
            if (this.i || this.d == null || (!this.m)) {
            } else {
                this.m = false;
                this.d.unregisterListener(this);
                this.d.stopLoading();
            }
        }

        void f() {
            String r1_String;
            if (a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.l = true;
            boolean r0z = this.f;
            this.f = false;
            if (this.c == null || this.d == null || (!this.e) || (!r0z)) {
                this.c = null;
                this.g = null;
                this.e = false;
                if (this.d == null) {
                    if (!this.m) {
                        this.m = false;
                        this.d.unregisterListener(this);
                    }
                    this.d.reset();
                }
                if (this.n == null) {
                    this.n.f();
                }
            } else {
                if (a) {
                    Log.v("LoaderManager", "  Reseting: " + this);
                }
                if (w.this.e != null) {
                    String r0_String = w.this.e.b.u;
                    w.this.e.b.u = "onLoaderReset";
                    r1_String = r0_String;
                } else {
                    r1_String = null;
                }
                try {
                    this.c.onLoaderReset(this.d);
                    if (w.this.e != null) {
                        w.this.e.b.u = r1_String;
                    }
                } catch (Throwable th) {
                    if (w.this.e != null) {
                        w.this.e.b.u = r1_String;
                    }
                }
                this.c = null;
                this.g = null;
                this.e = false;
                if (this.d == null) {
                    if (this.n == null) {
                    } else {
                        this.n.f();
                    }
                } else if (this.m) {
                    this.d.reset();
                    if (this.n == null) {
                        this.n.f();
                    }
                } else {
                    this.m = false;
                    this.d.unregisterListener(this);
                    this.d.reset();
                    if (this.n == null) {
                    } else {
                        this.n.f();
                    }
                }
            }
        }

        public void onLoadComplete(Loader<Object> r6_Loader_Object, Object r7_Object) {
            if (a) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (this.l) {
                if (a) {
                    Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
                }
            } else if (w.this.b.get(this.a) != this) {
                if (a) {
                    Log.v("LoaderManager", "  Ignoring load complete -- not active");
                }
            } else {
                a r0_a = this.n;
                if (r0_a != null) {
                    if (a) {
                        Log.v("LoaderManager", "  Switching to pending loader: " + r0_a);
                    }
                    this.n = null;
                    w.this.b.put(this.a, null);
                    f();
                    w.this.a(r0_a);
                } else if (this.g == r7_Object && this.e) {
                    r0_a = (a) w.this.c.get(this.a);
                    if (r0_a == null || r0_a == this) {
                    } else {
                        r0_a.f = false;
                        r0_a.f();
                        w.this.c.remove(this.a);
                    }
                    if (w.this.e == null || w.this.hasRunningLoaders()) {
                    } else {
                        w.this.e.b.a();
                    }
                } else {
                    this.g = r7_Object;
                    this.e = true;
                    if (this.h) {
                        a(r6_Loader_Object, r7_Object);
                    }
                    r0_a = (a) w.this.c.get(this.a);
                    if (r0_a == null || r0_a == this) {
                    } else {
                        r0_a.f = false;
                        r0_a.f();
                        w.this.c.remove(this.a);
                    }
                    if (w.this.e == null || w.this.hasRunningLoaders()) {
                    } else {
                        w.this.e.b.a();
                    }
                }
            }
        }

        public String toString() {
            StringBuilder r0_StringBuilder = new StringBuilder(64);
            r0_StringBuilder.append("LoaderInfo{");
            r0_StringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            r0_StringBuilder.append(" #");
            r0_StringBuilder.append(this.a);
            r0_StringBuilder.append(" : ");
            DebugUtils.buildShortClassTag(this.d, r0_StringBuilder);
            r0_StringBuilder.append("}}");
            return r0_StringBuilder.toString();
        }
    }

    static {
        a = false;
    }

    w(String r2_String, FragmentActivity r3_FragmentActivity, boolean r4z) {
        this.b = new SparseArrayCompat();
        this.c = new SparseArrayCompat();
        this.d = r2_String;
        this.e = r3_FragmentActivity;
        this.f = r4z;
    }

    private a a(int r3i, Bundle r4_Bundle, LoaderCallbacks<Object> r5_LoaderCallbacks_Object) {
        a r0_a = new a(r3i, r4_Bundle, r5_LoaderCallbacks_Object);
        r0_a.d = r5_LoaderCallbacks_Object.onCreateLoader(r3i, r4_Bundle);
        return r0_a;
    }

    private a b(int r3i, Bundle r4_Bundle, LoaderCallbacks<Object> r5_LoaderCallbacks_Object) {
        this.h = true;
        a r0_a = a(r3i, r4_Bundle, r5_LoaderCallbacks_Object);
        a(r0_a);
        this.h = false;
        return r0_a;
    }

    void a() {
        if (a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.f) {
            Throwable r0_Throwable = new RuntimeException("here");
            r0_Throwable.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, r0_Throwable);
        } else {
            this.f = true;
            int r1i = this.b.size() - 1;
            while (r1i >= 0) {
                ((a) this.b.valueAt(r1i)).a();
                r1i--;
            }
        }
    }

    void a(FragmentActivity r1_FragmentActivity) {
        this.e = r1_FragmentActivity;
    }

    void a(a r3_a) {
        this.b.put(r3_a.a, r3_a);
        if (this.f) {
            r3_a.a();
        }
    }

    void b() {
        if (a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.f) {
            int r1i = this.b.size() - 1;
            while (r1i >= 0) {
                ((a) this.b.valueAt(r1i)).e();
                r1i--;
            }
            this.f = false;
        } else {
            Throwable r0_Throwable = new RuntimeException("here");
            r0_Throwable.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, r0_Throwable);
        }
    }

    void c() {
        if (a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.f) {
            this.g = true;
            this.f = false;
            int r1i = this.b.size() - 1;
            while (r1i >= 0) {
                ((a) this.b.valueAt(r1i)).b();
                r1i--;
            }
        } else {
            Throwable r0_Throwable = new RuntimeException("here");
            r0_Throwable.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, r0_Throwable);
        }
    }

    void d() {
        if (this.g) {
            if (a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.g = false;
            int r1i = this.b.size() - 1;
            while (r1i >= 0) {
                ((a) this.b.valueAt(r1i)).c();
                r1i--;
            }
        }
    }

    public void destroyLoader(int r4i) {
        if (this.h) {
            throw new IllegalStateException("Called while creating a loader");
        } else {
            if (a) {
                Log.v("LoaderManager", "destroyLoader in " + this + " of " + r4i);
            }
            int r1i = this.b.indexOfKey(r4i);
            if (r1i >= 0) {
                this.b.removeAt(r1i);
                ((a) this.b.valueAt(r1i)).f();
            }
            r1i = this.c.indexOfKey(r4i);
            if (r1i >= 0) {
                this.c.removeAt(r1i);
                ((a) this.c.valueAt(r1i)).f();
            }
            if (this.e == null || hasRunningLoaders()) {
            } else {
                this.e.b.a();
            }
        }
    }

    public void dump(String r6_String, FileDescriptor r7_FileDescriptor, PrintWriter r8_PrintWriter, String[] r9_StringA) {
        a r0_a;
        int r2i = 0;
        if (this.b.size() > 0) {
            r8_PrintWriter.print(r6_String);
            r8_PrintWriter.println("Active Loaders:");
            String r3_String = r6_String + "    ";
            int r1i = 0;
            while (r1i < this.b.size()) {
                r0_a = (a) this.b.valueAt(r1i);
                r8_PrintWriter.print(r6_String);
                r8_PrintWriter.print("  #");
                r8_PrintWriter.print(this.b.keyAt(r1i));
                r8_PrintWriter.print(": ");
                r8_PrintWriter.println(r0_a.toString());
                r0_a.dump(r3_String, r7_FileDescriptor, r8_PrintWriter, r9_StringA);
                r1i++;
            }
        }
        if (this.c.size() > 0) {
            r8_PrintWriter.print(r6_String);
            r8_PrintWriter.println("Inactive Loaders:");
            String r1_String = r6_String + "    ";
            while (r2i < this.c.size()) {
                r0_a = (a) this.c.valueAt(r2i);
                r8_PrintWriter.print(r6_String);
                r8_PrintWriter.print("  #");
                r8_PrintWriter.print(this.c.keyAt(r2i));
                r8_PrintWriter.print(": ");
                r8_PrintWriter.println(r0_a.toString());
                r0_a.dump(r1_String, r7_FileDescriptor, r8_PrintWriter, r9_StringA);
                r2i++;
            }
        }
    }

    void e() {
        int r1i = this.b.size() - 1;
        while (r1i >= 0) {
            ((a) this.b.valueAt(r1i)).k = true;
            r1i--;
        }
    }

    void f() {
        int r1i = this.b.size() - 1;
        while (r1i >= 0) {
            ((a) this.b.valueAt(r1i)).d();
            r1i--;
        }
    }

    void g() {
        int r1i;
        if (!this.g) {
            if (a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            r1i = this.b.size() - 1;
            while (r1i >= 0) {
                ((a) this.b.valueAt(r1i)).f();
                r1i--;
            }
            this.b.clear();
        }
        if (a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        r1i = this.c.size() - 1;
        while (r1i >= 0) {
            ((a) this.c.valueAt(r1i)).f();
            r1i--;
        }
        this.c.clear();
    }

    public <D> Loader<D> getLoader(int r3i) {
        if (this.h) {
            throw new IllegalStateException("Called while creating a loader");
        } else {
            a r0_a = (a) this.b.get(r3i);
            if (r0_a == null) {
                return null;
            }
            if (r0_a.n != null) {
                return r0_a.n.d;
            }
            return r0_a.d;
        }
    }

    public boolean hasRunningLoaders() {
        int r4i = this.b.size();
        int r2i = 0;
        boolean r3z = false;
        while (r2i < r4i) {
            int r0i;
            a r0_a = (a) this.b.valueAt(r2i);
            r0i = ((!r0_a.h) || r0_a.f) ? 0 : 1;
            r3z |= r0i;
            r2i++;
        }
        return r3z;
    }

    public <D> Loader<D> initLoader(int r5i, Bundle r6_Bundle, LoaderCallbacks<D> r7_LoaderCallbacks_D) {
        if (this.h) {
            throw new IllegalStateException("Called while creating a loader");
        } else {
            a r0_a = (a) this.b.get(r5i);
            if (a) {
                Log.v("LoaderManager", "initLoader in " + this + ": args=" + r6_Bundle);
            }
            if (r0_a == null) {
                r0_a = b(r5i, r6_Bundle, r7_LoaderCallbacks_D);
                if (a) {
                    Log.v("LoaderManager", "  Created new loader " + r0_a);
                }
            } else {
                if (a) {
                    Log.v("LoaderManager", "  Re-using existing loader " + r0_a);
                }
                r0_a.c = r7_LoaderCallbacks_D;
            }
            if (!(r0_a.e) || !(this.f)) {
                return r0_a.d;
            }
            r0_a.a(r0_a.d, r0_a.g);
            return r0_a.d;
        }
    }

    public <D> Loader<D> restartLoader(int r6i, Bundle r7_Bundle, LoaderCallbacks<D> r8_LoaderCallbacks_D) {
        if (this.h) {
            throw new IllegalStateException("Called while creating a loader");
        } else {
            a r0_a = (a) this.b.get(r6i);
            if (a) {
                Log.v("LoaderManager", "restartLoader in " + this + ": args=" + r7_Bundle);
            }
            if (r0_a != null) {
                a r1_a = (a) this.c.get(r6i);
                if (r1_a != null) {
                    if (r0_a.e) {
                        if (a) {
                            Log.v("LoaderManager", "  Removing last inactive loader: " + r0_a);
                        }
                        r1_a.f = false;
                        r1_a.f();
                        r0_a.d.abandon();
                        this.c.put(r6i, r0_a);
                    } else if (r0_a.h) {
                        if (r0_a.n != null) {
                            if (a) {
                                Log.v("LoaderManager", "  Removing pending loader: " + r0_a.n);
                            }
                            r0_a.n.f();
                            r0_a.n = null;
                        }
                        if (a) {
                            Log.v("LoaderManager", "  Enqueuing as new pending loader");
                        }
                        r0_a.n = a(r6i, r7_Bundle, r8_LoaderCallbacks_D);
                        return r0_a.n.d;
                    } else {
                        if (a) {
                            Log.v("LoaderManager", "  Current loader is stopped; replacing");
                        }
                        this.b.put(r6i, null);
                        r0_a.f();
                    }
                } else {
                    if (a) {
                        Log.v("LoaderManager", "  Making last loader inactive: " + r0_a);
                    }
                    r0_a.d.abandon();
                    this.c.put(r6i, r0_a);
                }
            }
            return b(r6i, r7_Bundle, r8_LoaderCallbacks_D).d;
        }
    }

    public String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder(128);
        r0_StringBuilder.append("LoaderManager{");
        r0_StringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        r0_StringBuilder.append(" in ");
        DebugUtils.buildShortClassTag(this.e, r0_StringBuilder);
        r0_StringBuilder.append("}}");
        return r0_StringBuilder.toString();
    }
}