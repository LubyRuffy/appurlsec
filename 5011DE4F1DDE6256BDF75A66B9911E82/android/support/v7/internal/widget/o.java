package android.support.v7.internal.widget;

import android.view.View;

// compiled from: ScrollingTabContainerView.java
class o implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ ScrollingTabContainerView b;

    o(ScrollingTabContainerView r1_ScrollingTabContainerView, View r2_View) {
        this.b = r1_ScrollingTabContainerView;
        this.a = r2_View;
    }

    public void run() {
        this.b.smoothScrollTo(this.a.getLeft() - (this.b.getWidth() - this.a.getWidth()) / 2, 0);
        this.b.a = null;
    }
}