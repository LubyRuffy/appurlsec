package com.tencent.plus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.Button;

// compiled from: ProGuard
class b extends View {
    final /* synthetic */ ImageActivity a;

    public b(ImageActivity r1_ImageActivity, Context r2_Context) {
        this.a = r1_ImageActivity;
        super(r2_Context);
    }

    public void a(Button r6_Button) {
        Drawable r0_Drawable = new StateListDrawable();
        Drawable r1_Drawable = ImageActivity.a(this.a, "com.tencent.plus.blue_normal.png");
        Drawable r2_Drawable = ImageActivity.a(this.a, "com.tencent.plus.blue_down.png");
        Drawable r3_Drawable = ImageActivity.a(this.a, "com.tencent.plus.blue_disable.png");
        r0_Drawable.addState(View.PRESSED_ENABLED_STATE_SET, r2_Drawable);
        r0_Drawable.addState(View.ENABLED_FOCUSED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.ENABLED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.FOCUSED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.EMPTY_STATE_SET, r3_Drawable);
        r6_Button.setBackgroundDrawable(r0_Drawable);
    }

    public void b_(Button r6_Button) {
        Drawable r0_Drawable = new StateListDrawable();
        Drawable r1_Drawable = ImageActivity.a(this.a, "com.tencent.plus.gray_normal.png");
        Drawable r2_Drawable = ImageActivity.a(this.a, "com.tencent.plus.gray_down.png");
        Drawable r3_Drawable = ImageActivity.a(this.a, "com.tencent.plus.gray_disable.png");
        r0_Drawable.addState(View.PRESSED_ENABLED_STATE_SET, r2_Drawable);
        r0_Drawable.addState(View.ENABLED_FOCUSED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.ENABLED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.FOCUSED_STATE_SET, r1_Drawable);
        r0_Drawable.addState(View.EMPTY_STATE_SET, r3_Drawable);
        r6_Button.setBackgroundDrawable(r0_Drawable);
    }
}