package android.support.v4.view;

// compiled from: ViewPager.java
class ad implements Runnable {
    final /* synthetic */ ViewPager a;

    ad(ViewPager r1_ViewPager) {
        this.a = r1_ViewPager;
    }

    public void run() {
        ViewPager.a(this.a, 0);
        this.a.c();
    }
}