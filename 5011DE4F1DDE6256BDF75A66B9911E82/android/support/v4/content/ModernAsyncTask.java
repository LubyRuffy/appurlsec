package android.support.v4.content;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import qsbk.app.widget.listview.XListViewHeader;

abstract class ModernAsyncTask<Params, Progress, Result> {
    public static final Executor THREAD_POOL_EXECUTOR;
    private static final ThreadFactory a;
    private static final BlockingQueue<Runnable> b;
    private static final b c;
    private static volatile Executor d;
    private final c<Params, Result> e;
    private final FutureTask<Result> f;
    private volatile Status g;
    private final AtomicBoolean h;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[android.support.v4.content.ModernAsyncTask.Status.values().length];
            try {
                a[android.support.v4.content.ModernAsyncTask.Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a[android.support.v4.content.ModernAsyncTask.Status.FINISHED.ordinal()] = 2;
        }
    }

    public static enum Status {
        PENDING,
        RUNNING,
        FINISHED;


        static {
            PENDING = new android.support.v4.content.ModernAsyncTask.Status("PENDING", 0);
            RUNNING = new android.support.v4.content.ModernAsyncTask.Status("RUNNING", 1);
            FINISHED = new android.support.v4.content.ModernAsyncTask.Status("FINISHED", 2);
            android.support.v4.content.ModernAsyncTask.Status[] r0_android_support_v4_content_ModernAsyncTask_StatusA = new android.support.v4.content.ModernAsyncTask.Status[3];
            r0_android_support_v4_content_ModernAsyncTask_StatusA[0] = PENDING;
            r0_android_support_v4_content_ModernAsyncTask_StatusA[1] = RUNNING;
            r0_android_support_v4_content_ModernAsyncTask_StatusA[2] = FINISHED;
            a = r0_android_support_v4_content_ModernAsyncTask_StatusA;
        }
    }

    private static class a<Data> {
        final ModernAsyncTask a;
        final Data[] b;

        a(ModernAsyncTask r1_ModernAsyncTask, Data ... r2_DataA) {
            this.a = r1_ModernAsyncTask;
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

    private static abstract class c<Params, Result> implements Callable<Result> {
        Params[] b;

        private c() {
        }
    }

    static {
        a = new h();
        b = new LinkedBlockingQueue(10);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, b, a);
        c = new b();
        d = THREAD_POOL_EXECUTOR;
    }

    public ModernAsyncTask() {
        this.g = Status.PENDING;
        this.h = new AtomicBoolean();
        this.e = new i(this);
        this.f = new j(this, this.e);
    }

    private void c(Result r2_Result) {
        if (!this.h.get()) {
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
        a();
    }

    protected void b(Progress ... r1_ProgressA) {
    }

    public final boolean cancel(boolean r2z) {
        return this.f.cancel(r2z);
    }

    public final ModernAsyncTask<Params, Progress, Result> execute(Params ... r2_ParamsA) {
        return executeOnExecutor(d, r2_ParamsA);
    }

    public final ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor r3_Executor, Params ... r4_ParamsA) {
        if (this.g != Status.PENDING) {
            switch (AnonymousClass_1.a[this.g.ordinal()]) {
                case XListViewHeader.STATE_READY:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case XListViewHeader.STATE_REFRESHING:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.g = Status.RUNNING;
        b();
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
        return this.f.isCancelled();
    }
}