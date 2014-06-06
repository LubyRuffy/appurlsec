package qsbk.app.activity.group;

// compiled from: TopActivityGroup.java
class e implements Runnable {
    final /* synthetic */ TopActivityGroup a;

    e(TopActivityGroup r1_TopActivityGroup) {
        this.a = r1_TopActivityGroup;
    }

    public void run() {
        this.a.checkAndGotoMarketIfNecessary(this.a, false);
        TopActivityGroup.a(true);
    }
}