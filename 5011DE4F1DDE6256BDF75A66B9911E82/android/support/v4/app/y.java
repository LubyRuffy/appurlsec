package android.support.v4.app;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

// compiled from: NoSaveStateFrameLayout.java
class y extends FrameLayout {
    public y(Context r1_Context) {
        super(r1_Context);
    }

    static ViewGroup a(View r3_View) {
        ViewGroup r0_ViewGroup = new y(r3_View.getContext());
        LayoutParams r1_LayoutParams = r3_View.getLayoutParams();
        if (r1_LayoutParams != null) {
            r0_ViewGroup.setLayoutParams(r1_LayoutParams);
        }
        r3_View.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        r0_ViewGroup.addView(r3_View);
        return r0_ViewGroup;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> r1_SparseArray_Parcelable) {
        dispatchThawSelfOnly(r1_SparseArray_Parcelable);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> r1_SparseArray_Parcelable) {
        dispatchFreezeSelfOnly(r1_SparseArray_Parcelable);
    }
}