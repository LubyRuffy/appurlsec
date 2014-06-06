package qsbk.app.core;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class AsyncTask<Params, Progress, Result> {
    public static final Executor DUAL_THREAD_EXECUTOR;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static final ThreadFactory a;
    private static final BlockingQueue<Runnable> b;
    private static final b c;
    private static volatile Executor d;
    private final d<Params, Result> e;
    private final FutureTask<Result> f;
    private volatile Status g;
    private final AtomicBoolean h;
    private final AtomicBoolean i;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[qsbk.app.core.AsyncTask.Status.values().length];
            try {
                a[qsbk.app.core.AsyncTask.Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a[qsbk.app.core.AsyncTask.Status.FINISHED.ordinal()] = 2;
        }
    }

    public static enum Status {
        PENDING,
        RUNNING,
        FINISHED;


        static {
            PENDING = new qsbk.app.core.AsyncTask.Status("PENDING", 0);
            RUNNING = new qsbk.app.core.AsyncTask.Status("RUNNING", 1);
            FINISHED = new qsbk.app.core.AsyncTask.Status("FINISHED", 2);
            qsbk.app.core.AsyncTask.Status[] r0_qsbk_app_core_AsyncTask_StatusA = new qsbk.app.core.AsyncTask.Status[3];
            r0_qsbk_app_core_AsyncTask_StatusA[0] = PENDING;
            r0_qsbk_app_core_AsyncTask_StatusA[1] = RUNNING;
            r0_qsbk_app_core_AsyncTask_StatusA[2] = FINISHED;
            a = r0_qsbk_app_core_AsyncTask_StatusA;
        }
    }

    private static class a<Data> {
        final AsyncTask a;
        final Data[] b;

        a(AsyncTask r1_AsyncTask, Data ... r2_DataA) {
            this.a = r1_AsyncTask;
            this.b = r2_DataA;
        }
    }

    private static class b extends Handler {
        private b() {
        }

        public void handleMessage(Message r4_Message) {
            a r0_a = (a) r4_Message.obj;
            switch (r4_Message.what) {
                case XListViewHeader.STATE_READY:
                    r0_a.a.e(r0_a.b[0]);
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    r0_a.a.b(r0_a.b);
                    break;
            }
        }
    }

    private static class c implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private c() {
            this.a = new ArrayDeque();
        }

        protected synchronized void a() {
            Runnable r0_Runnable = (Runnable) this.a.poll();
            this.b = r0_Runnable;
            if (r0_Runnable != null) {
                THREAD_POOL_EXECUTOR.execute(this.b);
            }
        }

        public synchronized void execute(Runnable r3_Runnable) {
            this.a.offer(new d(this, r3_Runnable));
            if (this.b == null) {
                a();
            }
        }
    }

    private static abstract class d<Params, Result> implements Callable<Result> {
        Params[] b;

        private d() {
        }
    }

    static {
        a = new a();
        b = new LinkedBlockingQueue(10);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, b, a, new DiscardOldestPolicy());
        SERIAL_EXECUTOR = new c();
        DUAL_THREAD_EXECUTOR = Executors.newFixedThreadPool(XListViewFooter.STATE_NOMORE, a);
        c = new b();
        d = SERIAL_EXECUTOR;
    }

    public AsyncTask() {
        this.g = Status.PENDING;
        this.h = new AtomicBoolean();
        this.i = new AtomicBoolean();
        this.e = new b(this);
        this.f = new c(this, this.e);
    }

    private void c(Result r2_Result) {
        if (!this.i.get()) {
            d(r2_Result);
        }
    }

    private Result d(Result r6_Result) {
        b r0_b = c;
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r6_Result;
        r0_b.obtainMessage(1, new a(this, r2_ObjectA)).sendToTarget();
        return r6_Result;
    }

    private void e(Result r2_Result) {
        if (isCancelled()) {
            b((Object)r2_Result);
        } else {
            a((Object)r2_Result);
        }
        this.g = Status.FINISHED;
    }

    public static void execute(Runnable r1_Runnable) {
        d.execute(r1_Runnable);
    }

    public static void init() {
        c.getLooper();
    }

    public static void setDefaultExecutor(Executor r0_Executor) {
        d = r0_Executor;
    }

    protected abstract Result a(Params ... r1_ParamsA);

    protected void a() {
    }

    protected void a(Result r1_Result) {
    }

    protected void b() {
    }

    protected void b(Result r1_Result) {
        b();
    }

    protected void b(Progress ... r1_ProgressA) {
    }

    public final boolean cancel(boolean r3z) {
        this.h.set(true);
        return this.f.cancel(r3z);
    }

    public final AsyncTask<Params, Progress, Result> execute(Params ... r2_ParamsA) {
        return executeOnExecutor(d, r2_ParamsA);
    }

    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor r3_Executor, Params ... r4_ParamsA) {
        if (this.g != Status.PENDING) {
            switch (AnonymousClass_1.a[this.g.ordinal()]) {
                case XListViewHeader.STATE_READY:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case XListViewHeader.STATE_REFRESHING:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.g = Status.RUNNING;
        a();
        this.e.b = r4_ParamsA;
        r3_Executor.execute(this.f);
        return this;
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.f.get();
    }

    public final Result get(long r2j, TimeUnit r4_TimeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.f.get(r2j, r4_TimeUnit);
    }

    public final Status getStatus() {
        return this.g;
    }

    public final boolean isCancelled() {
        return this.h.get();
    }
}