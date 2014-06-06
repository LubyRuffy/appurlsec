package android.support.v4.widget;

import qsbk.app.utils.Base64;

// compiled from: ContentLoadingProgressBar.java
class a implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar a;

    a(ContentLoadingProgressBar r1_ContentLoadingProgressBar) {
        this.a = r1_ContentLoadingProgressBar;
    }

    public void run() {
        this.a.b = false;
        this.a.a = -1;
        this.a.setVisibility(Base64.DONT_BREAK_LINES);
    }
}