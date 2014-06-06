package android.support.v7.internal.widget;

import android.database.DataSetObserver;

// compiled from: ActivityChooserView.java
class i extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    i(ActivityChooserView r1_ActivityChooserView) {
        this.a = r1_ActivityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        this.a.b.notifyDataSetChanged();
    }

    public void onInvalidated() {
        super.onInvalidated();
        this.a.b.notifyDataSetInvalidated();
    }
}