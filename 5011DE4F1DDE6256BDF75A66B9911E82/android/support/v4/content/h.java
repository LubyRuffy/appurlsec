package android.support.v4.content;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: ModernAsyncTask.java
final class h implements ThreadFactory {
    private final AtomicInteger a;

    h() {
        this.a = new AtomicInteger(1);
    }

    public Thread newThread(Runnable r4_Runnable) {
        return new Thread(r4_Runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
    }
}