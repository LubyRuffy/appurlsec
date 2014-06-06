package qsbk.app.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: ProfileHeaderListView.java
class b implements OnGlobalLayoutListener {
    final /* synthetic */ ProfileHeaderListView a;

    b(ProfileHeaderListView r1_ProfileHeaderListView) {
        this.a = r1_ProfileHeaderListView;
    }

    public void onGlobalLayout() {
        this.a.a = this.a.i.getTop();
        this.a.b = this.a.i.getHeight();
        this.a.c = this.a.i.getTabBarHeight();
        this.a.n = this.a.b - this.a.c;
        this.a.i.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}