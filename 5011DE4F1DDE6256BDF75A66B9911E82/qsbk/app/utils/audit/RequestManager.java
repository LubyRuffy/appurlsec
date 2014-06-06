package qsbk.app.utils.audit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {
    private final Set<Request> a;
    private BlockingQueue<Request> b;
    private RequestWorker c;

    public static interface RequestFilter {
        public boolean apply(Request r1_Request);
    }

    public RequestManager() {
        this.a = new HashSet(5);
        this.b = new LinkedBlockingQueue();
    }

    public void add(Request r3_Request) {
        if (r3_Request.getRequestListener() == null) {
            throw new IllegalArgumentException("Request's listener must not be null");
        } else {
            synchronized (this.a) {
                this.a.add(r3_Request);
            }
            this.b.add(r3_Request);
        }
    }

    public void cancelAll(Object r3_Object) {
        if (r3_Object == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        } else {
            cancelAll(new a(this, r3_Object));
        }
    }

    public void cancelAll(RequestFilter r5_RequestFilter) {
        synchronized (this.a) {
            Iterator r2_Iterator = this.a.iterator();
            while (r2_Iterator.hasNext()) {
                Request r0_Request = (Request) r2_Iterator.next();
                if (r5_RequestFilter.apply(r0_Request)) {
                    r0_Request.cancel();
                }
            }
        }
    }

    public void start() {
        stop();
        this.c = new RequestWorker(this.b);
        this.c.start();
    }

    public void stop() {
        if (this.c != null) {
            this.c.quit();
        }
    }
}