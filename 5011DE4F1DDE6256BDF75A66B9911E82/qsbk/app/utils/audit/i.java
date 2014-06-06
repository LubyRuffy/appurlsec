package qsbk.app.utils.audit;

import qsbk.app.utils.audit.RequestManager.RequestFilter;

// compiled from: SimpleImageLoader.java
class i implements RequestFilter {
    final /* synthetic */ String a;
    final /* synthetic */ SimpleImageLoader b;

    i(SimpleImageLoader r1_SimpleImageLoader, String r2_String) {
        this.b = r1_SimpleImageLoader;
        this.a = r2_String;
    }

    public boolean apply(Request r3_Request) {
        return r3_Request.getUrl().equalsIgnoreCase(this.a);
    }
}