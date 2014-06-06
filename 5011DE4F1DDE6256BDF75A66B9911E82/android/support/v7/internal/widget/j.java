package android.support.v7.internal.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: ActivityChooserView.java
class j implements OnGlobalLayoutListener {
    final /* synthetic */ ActivityChooserView a;

    j(ActivityChooserView r1_ActivityChooserView) {
        this.a = r1_ActivityChooserView;
    }

    public void onGlobalLayout() {
        if (this.a.isShowingPopup()) {
            if (this.a.isShown()) {
                this.a.a().show();
                if (this.a.a != null) {
                    this.a.a.subUiVisibilityChanged(true);
                }
            } else {
                this.a.a().dismiss();
            }
        }
    }
}