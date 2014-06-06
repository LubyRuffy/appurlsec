package qsbk.app.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import qsbk.app.R;

public class XListViewHeader extends LinearLayout {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;
    private LinearLayout a;
    private ImageView b;
    private ProgressBar c;
    private int d;
    private Animation e;
    private Animation f;
    private final int g;
    public int measureHeight;

    public XListViewHeader(Context r2_Context) {
        super(r2_Context);
        this.d = 0;
        this.g = 180;
        a(r2_Context);
    }

    public XListViewHeader(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.d = 0;
        this.g = 180;
        a(r2_Context);
    }

    private void a(Context r15_Context) {
        LayoutParams r2_LayoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.a = (LinearLayout) LayoutInflater.from(r15_Context).inflate(R.layout.xlistview_header, null);
        a(this.a);
        this.measureHeight = this.a.getMeasuredHeight();
        addView(this.a, r2_LayoutParams);
        setGravity(80);
        this.b = (ImageView) findViewById(R.id.xlistview_header_arrow);
        this.c = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
        this.e = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        this.e.setDuration(180);
        this.e.setFillAfter(true);
        this.f = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f.setDuration(180);
        this.f.setFillAfter(true);
    }

    private void a(View r5_View) {
        LayoutParams r0_LayoutParams = r5_View.getLayoutParams();
        if (r0_LayoutParams == null) {
            r0_LayoutParams = new LayoutParams(-1, -2);
        }
        int r1i = ViewGroup.getChildMeasureSpec(STATE_NORMAL, STATE_NORMAL, r0_LayoutParams.width);
        int r0i = r0_LayoutParams.height;
        r5_View.measure(r1i, r0i > 0 ? MeasureSpec.makeMeasureSpec(r0i, 1073741824) : MeasureSpec.makeMeasureSpec(STATE_NORMAL, STATE_NORMAL));
    }

    public int getVisiableHeight() {
        return this.a.getHeight();
    }

    public void setState(int r6i) {
        if (r6i == this.d) {
        } else {
            if (r6i == 2) {
                this.b.clearAnimation();
                this.b.setVisibility(XListViewFooter.STATE_NODATA);
                this.c.setVisibility(STATE_NORMAL);
            } else {
                this.b.setVisibility(STATE_NORMAL);
                this.c.setVisibility(XListViewFooter.STATE_NODATA);
            }
            switch (r6i) {
                case STATE_NORMAL:
                    if (this.d == 1) {
                        this.b.startAnimation(this.f);
                    }
                    if (this.d == 2) {
                        this.b.clearAnimation();
                    }
                    break;
                case STATE_READY:
                    if (this.d != 1) {
                        this.b.clearAnimation();
                        this.b.startAnimation(this.e);
                    }
                    break;
            }
            this.d = r6i;
        }
    }

    public void setVisiableHeight(int r3i) {
        if (r3i < 0) {
            r3i = STATE_NORMAL;
        }
        LinearLayout.LayoutParams r0_LinearLayout_LayoutParams = (LinearLayout.LayoutParams) this.a.getLayoutParams();
        r0_LinearLayout_LayoutParams.height = r3i;
        this.a.setLayoutParams(r0_LinearLayout_LayoutParams);
    }
}