package android.support.v4.widget;

// compiled from: ContentLoadingProgressBar.java
class b implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar a;

    b(ContentLoadingProgressBar r1_ContentLoadingProgressBar) {
        this.a = r1_ContentLoadingProgressBar;
    }

    public void run() {
        this.a.c = false;
        if (!this.a.d) {
            this.a.a = System.currentTimeMillis();
            this.a.setVisibility(0);
        }
    }
}