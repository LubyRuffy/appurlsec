package qsbk.app.widget.listview;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: XListView.java
class a implements OnGlobalLayoutListener {
    final /* synthetic */ XListView a;

    a(XListView r1_XListView) {
        this.a = r1_XListView;
    }

    public void onGlobalLayout() {
        XListView.a(this.a, XListView.a(this.a).getHeight());
        this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}