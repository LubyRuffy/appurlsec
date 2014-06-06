package android.support.v4.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import qsbk.app.utils.Base64;

public class ContentLoadingProgressBar extends ProgressBar {
    private long a;
    private boolean b;
    private boolean c;
    private boolean d;
    private final Runnable e;
    private final Runnable f;

    public ContentLoadingProgressBar(Context r2_Context) {
        this(r2_Context, null);
    }

    public ContentLoadingProgressBar(Context r4_Context, AttributeSet r5_AttributeSet) {
        super(r4_Context, r5_AttributeSet, 0);
        this.a = -1;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = new a(this);
        this.f = new b(this);
    }

    private void a() {
        removeCallbacks(this.e);
        removeCallbacks(this.f);
    }

    public void hide() {
        this.d = true;
        removeCallbacks(this.f);
        long r0j = System.currentTimeMillis() - this.a;
        if (r0j >= 500 || this.a == -1) {
            setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            if (!this.b) {
                postDelayed(this.e, 500 - r0j);
                this.b = true;
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void show() {
        this.a = -1;
        this.d = false;
        removeCallbacks(this.e);
        if (!this.c) {
            postDelayed(this.f, 500);
            this.c = true;
        }
    }
}