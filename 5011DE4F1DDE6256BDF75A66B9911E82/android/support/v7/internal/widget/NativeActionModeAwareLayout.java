package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.View;
import android.widget.LinearLayout;

public class NativeActionModeAwareLayout extends LinearLayout {
    private OnActionModeForChildListener a;

    public static interface OnActionModeForChildListener {
        public Callback onActionModeForChild(Callback r1_Callback);
    }

    public NativeActionModeAwareLayout(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    public void setActionModeForChildListener(OnActionModeForChildListener r1_OnActionModeForChildListener) {
        this.a = r1_OnActionModeForChildListener;
    }

    public ActionMode startActionModeForChild(View r2_View, Callback r3_Callback) {
        if (this.a != null) {
            r3_Callback = this.a.onActionModeForChild(r3_Callback);
        }
        return super.startActionModeForChild(r2_View, r3_Callback);
    }
}