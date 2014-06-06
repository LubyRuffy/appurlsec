package android.support.v7.internal.widget;

import android.database.DataSetObserver;

// compiled from: ActivityChooserView.java
class k extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    k(ActivityChooserView r1_ActivityChooserView) {
        this.a = r1_ActivityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        this.a.b();
    }
}