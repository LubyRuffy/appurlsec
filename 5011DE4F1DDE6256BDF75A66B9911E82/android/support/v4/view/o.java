package android.support.v4.view;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

// compiled from: PagerTitleStripIcs.java
class o {

    // compiled from: PagerTitleStripIcs.java
    private static class a extends SingleLineTransformationMethod {
        private Locale a;

        public a(Context r2_Context) {
            this.a = r2_Context.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence r3_CharSequence, View r4_View) {
            CharSequence r0_CharSequence = super.getTransformation(r3_CharSequence, r4_View);
            return r0_CharSequence != null ? r0_CharSequence.toString().toUpperCase(this.a) : null;
        }
    }

    public static void setSingleLineAllCaps(TextView r2_TextView) {
        r2_TextView.setTransformationMethod(new a(r2_TextView.getContext()));
    }
}