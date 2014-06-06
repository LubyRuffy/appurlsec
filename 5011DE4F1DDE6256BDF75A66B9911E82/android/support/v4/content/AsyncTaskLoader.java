package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

public abstract class AsyncTaskLoader<D> extends Loader<D> {
    volatile a a;
    volatile a b;
    long c;
    long d;
    Handler e;

    final class a extends ModernAsyncTask<Void, Void, D> implements Runnable {
        D a;
        boolean b;
        private CountDownLatch d;

        a() {
            this.d = new CountDownLatch(1);
        }

        protected D a_(Void ... r2_VoidA) {
            this.a = AsyncTaskLoader.this.c();
            return this.a;
        }

        protected void a_() {
            AsyncTaskLoader.this.a(this, this.a);
            this.d.countDown();
        }

        protected void a_(D r3_D) {
            AsyncTaskLoader.this.b(this, r3_D);
            this.d.countDown();
        }

        public void run() {
            this.b = false;
            AsyncTaskLoader.this.b();
        }
    }

    public AsyncTaskLoader(Context r3_Context) {
        super(r3_Context);
        this.d = -10000;
    }

    protected void a() {
        super.a();
        cancelLoad();
        this.a = new a();
        b();
    }

    void a(a r3_a, D r4_D) {
        onCanceled(r4_D);
        if (this.b == r3_a) {
            rollbackContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.b = null;
            b();
        }
    }

    void b() {
        if (this.b != null || this.a == null) {
        } else {
            if (this.a.b) {
                this.a.b = false;
                this.e.removeCallbacks(this.a);
            }
            if (this.c <= 0 || SystemClock.uptimeMillis() >= this.d + this.c) {
                this.a.executeOnExecutor(ModernAsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
            } else {
                this.a.b = true;
                this.e.postAtTime(this.a, this.d + this.c);
            }
        }
    }

    void b(a r3_a, D r4_D) {
        if (this.a != r3_a) {
            a(r3_a, r4_D);
        } else if (isAbandoned()) {
            onCanceled(r4_D);
        } else {
            commitContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.a = null;
            deliverResult(r4_D);
        }
    }

    protected D c() {
        return loadInBackground();
    }

    public boolean cancelLoad() {
        boolean r0z = false;
        if (this.a != null) {
            if (this.b != null) {
                if (this.a.b) {
                    this.a.b = false;
                    this.e.removeCallbacks(this.a);
                }
                this.a = null;
            } else if (this.a.b) {
                this.a.b = false;
                this.e.removeCallbacks(this.a);
                this.a = null;
            } else {
                r0z = this.a.cancel(r0z);
                if (r0z) {
                    this.b = this.a;
                }
                this.a = null;
            }
        }
        return r0z;
    }

    public void dump(String r5_String, FileDescriptor r6_FileDescriptor, PrintWriter r7_PrintWriter, String[] r8_StringA) {
        super.dump(r5_String, r6_FileDescriptor, r7_PrintWriter, r8_StringA);
        if (this.a != null) {
            r7_PrintWriter.print(r5_String);
            r7_PrintWriter.print("mTask=");
            r7_PrintWriter.print(this.a);
            r7_PrintWriter.print(" waiting=");
            r7_PrintWriter.println(this.a.b);
        }
        if (this.b != null) {
            r7_PrintWriter.print(r5_String);
            r7_PrintWriter.print("mCancellingTask=");
            r7_PrintWriter.print(this.b);
            r7_PrintWriter.print(" waiting=");
            r7_PrintWriter.println(this.b.b);
        }
        if (this.c != 0) {
            r7_PrintWriter.print(r5_String);
            r7_PrintWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.c, r7_PrintWriter);
            r7_PrintWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.d, SystemClock.uptimeMillis(), r7_PrintWriter);
            r7_PrintWriter.println();
        }
    }

    public abstract D loadInBackground();

    public void onCanceled(D r1_D) {
    }

    public void setUpdateThrottle(long r3j) {
        this.c = r3j;
        if (r3j != 0) {
            this.e = new Handler();
        }
    }

    public void waitForLoader() {
        a r0_a = this.a;
        if (r0_a != null) {
            try {
                r0_a.d.await();
            } catch (InterruptedException e) {
            }
        }
    }
}