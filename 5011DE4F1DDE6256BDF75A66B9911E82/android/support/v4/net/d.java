package android.support.v4.net;

// compiled from: TrafficStatsCompat.java
class d extends ThreadLocal<a> {
    final /* synthetic */ a a;

    d(a r1_a) {
        this.a = r1_a;
    }

    protected a a() {
        return new a();
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }
}