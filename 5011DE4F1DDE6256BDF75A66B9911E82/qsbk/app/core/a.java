package qsbk.app.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: AsyncTask.java
final class a implements ThreadFactory {
    private final AtomicInteger a;

    a() {
        this.a = new AtomicInteger(1);
    }

    public Thread newThread(Runnable r4_Runnable) {
        Thread r0_Thread = new Thread(r4_Runnable, "qsbk-AsyncTask #" + this.a.getAndIncrement());
        r0_Thread.setPriority(XListViewFooter.STATE_NODATA);
        return r0_Thread;
    }
}