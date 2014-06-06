package qsbk.app.nearby.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class SizeChangeScrollView extends ScrollView {
    private OnSizeChangeListener a;

    public static interface OnSizeChangeListener {
        public void onSizeChange(int r1i, int r2i, int r3i, int r4i);
    }

    public SizeChangeScrollView(Context r1_Context) {
        super(r1_Context);
    }

    public SizeChangeScrollView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public SizeChangeScrollView(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    protected void onSizeChanged(int r2i, int r3i, int r4i, int r5i) {
        super.onSizeChanged(r2i, r3i, r4i, r5i);
        if (this.a != null) {
            this.a.onSizeChange(r2i, r3i, r4i, r5i);
        }
    }

    public void setOnSizeChangeListner(OnSizeChangeListener r1_OnSizeChangeListener) {
        this.a = r1_OnSizeChangeListener;
    }
}