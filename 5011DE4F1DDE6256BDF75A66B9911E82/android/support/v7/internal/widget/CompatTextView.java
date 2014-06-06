package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v7.appcompat.R;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class CompatTextView extends TextView {

    private static class a implements TransformationMethod {
        private final Locale a;

        public a(Context r2_Context) {
            this.a = r2_Context.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence r3_CharSequence, View r4_View) {
            return r3_CharSequence != null ? r3_CharSequence.toString().toUpperCase(this.a) : null;
        }

        public void onFocusChanged(View r1_View, CharSequence r2_CharSequence, boolean r3z, int r4i, Rect r5_Rect) {
        }
    }

    public CompatTextView(Context r2_Context) {
        this(r2_Context, null);
    }

    public CompatTextView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public CompatTextView(Context r3_Context, AttributeSet r4_AttributeSet, int r5i) {
        super(r3_Context, r4_AttributeSet, r5i);
        TypedArray r0_TypedArray = r3_Context.obtainStyledAttributes(r4_AttributeSet, R.styleable.CompatTextView, r5i, 0);
        boolean r1z = r0_TypedArray.getBoolean(0, false);
        r0_TypedArray.recycle();
        if (r1z) {
            setTransformationMethod(new a(r3_Context));
        }
    }
}