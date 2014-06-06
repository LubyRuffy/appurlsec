package qsbk.app.utils.audit;

import qsbk.app.utils.audit.RequestManager.RequestFilter;

// compiled from: RequestManager.java
class a implements RequestFilter {
    final /* synthetic */ Object a;
    final /* synthetic */ RequestManager b;

    a(RequestManager r1_RequestManager, Object r2_Object) {
        this.b = r1_RequestManager;
        this.a = r2_Object;
    }

    public boolean apply(Request r3_Request) {
        return r3_Request.getTag() == this.a;
    }
}