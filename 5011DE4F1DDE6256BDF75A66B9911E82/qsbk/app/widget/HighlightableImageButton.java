package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class HighlightableImageButton extends ImageButton {
    private static final int[] a;
    private boolean b;

    static {
        int[] r0_intA = new int[1];
        r0_intA[0] = 2130772158;
        a = r0_intA;
    }

    public HighlightableImageButton(Context r2_Context) {
        super(r2_Context);
        this.b = false;
    }

    public HighlightableImageButton(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.b = false;
    }

    public HighlightableImageButton(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.b = false;
    }

    public boolean isHighlighted() {
        return this.b;
    }

    public int[] onCreateDrawableState(int r3i) {
        if (!(this.b)) {
            return super.onCreateDrawableState(r3i);
        }
        int[] r0_intA = super.onCreateDrawableState(r3i + 1);
        mergeDrawableStates(r0_intA, a);
        return r0_intA;
    }

    public void setHighlighted(boolean r2z) {
        if (this.b != r2z) {
            this.b = r2z;
            refreshDrawableState();
        }
    }
}