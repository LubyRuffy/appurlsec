package qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import qsbk.app.R;

public class ToastAndDialog {

    static class a implements AnimationListener {
        private View a;
        private TextView b;

        public a(View r1_View, TextView r2_TextView) {
            this.a = r1_View;
            this.b = r2_TextView;
        }

        public void onAnimationEnd(Animation r3_Animation) {
            this.a.setClickable(true);
            this.b.setVisibility(Base64.DONT_BREAK_LINES);
            this.b = null;
        }

        public void onAnimationRepeat(Animation r1_Animation) {
        }

        public void onAnimationStart(Animation r1_Animation) {
        }
    }

    public static Toast makeText(Context r2_Context, int r3i) {
        return makeText(r2_Context, r2_Context.getResources().getString(r3i), Integer.valueOf(1));
    }

    public static Toast makeText(Context r1_Context, String r2_String) {
        return makeText(r1_Context, r2_String, Integer.valueOf(1));
    }

    public static Toast makeText(Context r3_Context, String r4_String, Integer r5_Integer) {
        View r0_View = LayoutInflater.from(r3_Context).inflate(R.layout.my_toast, null);
        Toast r1_Toast = new Toast(r3_Context);
        r1_Toast.setDuration(r5_Integer.intValue());
        r1_Toast.setView(r0_View);
        ((TextView) r0_View.findViewById(R.id.TextViewInfo)).setText(r4_String);
        return r1_Toast;
    }

    public static void makeText(Context r10_Context, View r11_View, String r12_String, int r13i, int r14i) {
        r11_View.setClickable(false);
        int[] r0_intA = new int[2];
        r11_View.getLocationOnScreen(r0_intA);
        int r1i = r0_intA[0];
        int r0i = r0_intA[1];
        int r2i = r11_View.getMeasuredHeight();
        int r3i = r11_View.getMeasuredWidth();
        View r4_View = new TextView(r10_Context);
        r4_View.setText(r12_String);
        r4_View.setTextColor(r14i);
        r4_View.setTextSize(22.0f);
        r4_View.setTypeface(Typeface.DEFAULT_BOLD, 1);
        r4_View.setClickable(false);
        r4_View.measure(0, 0);
        int r5i = r4_View.getMeasuredWidth();
        int r6i = r4_View.getMeasuredHeight();
        ((Activity) r10_Context).addContentView(r4_View, new LayoutParams(-2, -2));
        float r0f = (float) (r0i - (r2i * 3) / 2 - r6i);
        float r1f = (float) (r1i + r3i / 2 - r5i / 2);
        Animation r3_Animation = new TranslateAnimation(r1f, r1f, r0f, r0f - 20.0f);
        r3_Animation.setDuration((long) r13i);
        Animation r0_Animation = new AlphaAnimation(1.0f, 0.0f);
        r0_Animation.setDuration((long) r13i);
        Animation r1_Animation = new AnimationSet(true);
        r1_Animation.addAnimation(r3_Animation);
        r1_Animation.addAnimation(r0_Animation);
        r1_Animation.setFillAfter(true);
        r4_View.startAnimation(r1_Animation);
        r1_Animation.setAnimationListener(new a(r11_View, r4_View));
    }

    public static void makeTextSingleArticle(Context r10_Context, View r11_View, String r12_String, int r13i, int r14i) {
        r11_View.setClickable(false);
        int[] r0_intA = new int[2];
        r11_View.getLocationOnScreen(r0_intA);
        int r1i = r0_intA[0];
        int r0i = r0_intA[1];
        int r2i = r11_View.getMeasuredHeight();
        int r3i = r11_View.getMeasuredWidth();
        View r4_View = new TextView(r10_Context);
        r4_View.setText(r12_String);
        r4_View.setTextColor(r14i);
        r4_View.setTextSize(22.0f);
        r4_View.setTypeface(Typeface.DEFAULT_BOLD, 1);
        r4_View.setClickable(false);
        r4_View.measure(0, 0);
        int r5i = r4_View.getMeasuredWidth();
        int r6i = r4_View.getMeasuredHeight();
        ((Activity) r10_Context).addContentView(r4_View, new LayoutParams(-2, -2));
        float r0f = (float) (r0i - r2i / 2 - r6i / 2);
        float r1f = (float) (r1i + r3i / 2 - r5i / 2);
        Animation r3_Animation = new TranslateAnimation(r1f, r1f, r0f, r0f - 20.0f);
        r3_Animation.setDuration((long) r13i);
        Animation r0_Animation = new AlphaAnimation(1.0f, 0.0f);
        r0_Animation.setDuration((long) r13i);
        Animation r1_Animation = new AnimationSet(true);
        r1_Animation.addAnimation(r3_Animation);
        r1_Animation.addAnimation(r0_Animation);
        r1_Animation.setFillAfter(true);
        r4_View.startAnimation(r1_Animation);
        r1_Animation.setAnimationListener(new a(r11_View, r4_View));
    }

    public static void tipsScroll(Toast r1_Toast, Context r2_Context, int r3i, int r4i, int r5i, int r6i) {
        View r0_View = new ImageView(r2_Context);
        r0_View.setImageResource(r3i);
        r1_Toast.setView(r0_View);
        r1_Toast.setGravity(r4i, r5i, r6i);
        r1_Toast.setDuration(0);
        r1_Toast.show();
    }
}