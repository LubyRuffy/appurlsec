package qsbk.app.widget.slidingmenu.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.slidingmenu.callback.SizeCallBack;

public class MenuHorizontalScrollView extends HorizontalScrollView {
    public static int BUFFER_WIDTH;
    public static int ENLARGE_WIDTH;
    public static int TOUCHE_CONTROL_WIDTH;
    public static boolean menuOut;
    private MenuHorizontalScrollView a;
    private ListView b;
    private LinearLayout c;
    private int d;
    private float e;
    private ImageButton f;
    private int g;
    private int h;
    private int i;

    public class MenuOnGlobalLayoutListener implements OnGlobalLayoutListener {
        private ViewGroup b;
        private View[] c;
        private SizeCallBack d;

        public MenuOnGlobalLayoutListener(ViewGroup r2_ViewGroup, View[] r3_ViewA, SizeCallBack r4_SizeCallBack) {
            this.b = r2_ViewGroup;
            this.c = r3_ViewA;
            this.d = r4_SizeCallBack;
        }

        public void onGlobalLayout() {
            MenuHorizontalScrollView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            this.d.onGlobalLayout();
            this.b.removeViewsInLayout(0, this.c.length);
            int r2i = MenuHorizontalScrollView.this.getMeasuredWidth();
            int r3i = MenuHorizontalScrollView.this.getMeasuredHeight();
            int[] r4_intA = new int[2];
            MenuHorizontalScrollView.this.h = 0;
            int r0i = 0;
            while (r0i < this.c.length) {
                this.d.getViewSize(r0i, r2i, r3i, r4_intA);
                this.c[r0i].setVisibility(0);
                this.b.addView(this.c[r0i], r4_intA[0], r4_intA[1]);
                if (r0i == 0) {
                    MenuHorizontalScrollView.b(MenuHorizontalScrollView.this, r4_intA[0]);
                }
                r0i++;
            }
            MenuHorizontalScrollView.this.h = MenuHorizontalScrollView.this.h + BUFFER_WIDTH;
            new Handler().post(new a(this));
        }
    }

    static {
        ENLARGE_WIDTH = 30;
        BUFFER_WIDTH = 90;
        TOUCHE_CONTROL_WIDTH = 60;
    }

    public MenuHorizontalScrollView(Context r2_Context) {
        super(r2_Context);
        this.e = -1.0f;
        this.i = 0;
        a(r2_Context);
    }

    public MenuHorizontalScrollView(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.e = -1.0f;
        this.i = 0;
        a(r2_Context);
    }

    public MenuHorizontalScrollView(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.e = -1.0f;
        this.i = 0;
        a(r2_Context);
    }

    private void a() {
        if (this.c.getVisibility() != 0) {
            this.c.setVisibility(0);
        }
    }

    private void a(Context r4_Context) {
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        this.a = this;
        this.a.setVisibility(XListViewFooter.STATE_NODATA);
        menuOut = false;
        this.i = r4_Context.getResources().getDisplayMetrics().widthPixels;
        if (QsbkApp.isPad) {
            ENLARGE_WIDTH = this.i / 2;
            BUFFER_WIDTH = 0;
        } else {
            ENLARGE_WIDTH = getResources().getDimensionPixelSize(R.dimen.enlarge_width);
            BUFFER_WIDTH = getResources().getDimensionPixelSize(R.dimen.buffer_width);
        }
        TOUCHE_CONTROL_WIDTH = getResources().getDimensionPixelSize(R.dimen.touch_control_width);
    }

    private void a(View r3_View) {
        LayoutParams r0_LayoutParams = r3_View.getLayoutParams();
        r0_LayoutParams.width = getResources().getDisplayMetrics().widthPixels / 2;
        r3_View.setLayoutParams(r0_LayoutParams);
    }

    static /* synthetic */ int b(MenuHorizontalScrollView r1_MenuHorizontalScrollView, int r2i) {
        int r0i = r1_MenuHorizontalScrollView.h + r2i;
        r1_MenuHorizontalScrollView.h = r0i;
        return r0i;
    }

    private void b() {
        if (this.d == BUFFER_WIDTH) {
            menuOut = true;
        } else {
            menuOut = false;
        }
        this.a.smoothScrollTo(this.d, 0);
    }

    public void clickMenuBtn() {
        a();
        if (menuOut) {
            if (QsbkApp.isPad) {
                this.d = this.i - ENLARGE_WIDTH + BUFFER_WIDTH;
            } else {
                this.d = this.b.getMeasuredWidth() - this.f.getMeasuredWidth() - ENLARGE_WIDTH + BUFFER_WIDTH;
            }
        } else {
            this.d = BUFFER_WIDTH;
        }
        b();
    }

    public void clickMenuItem() {
        if (menuOut) {
            this.a.smoothScrollTo(0, 0);
        }
    }

    public void initViews(View[] r5_ViewA, SizeCallBack r6_SizeCallBack, ListView r7_ListView, LinearLayout r8_LinearLayout) {
        int r1i = 0;
        this.b = r7_ListView;
        this.c = r8_LinearLayout;
        if (QsbkApp.isPad) {
            a(this.b);
            a(this.c);
        }
        ViewGroup r0_ViewGroup = (ViewGroup) getChildAt(0);
        while (r1i < r5_ViewA.length) {
            r5_ViewA[r1i].setVisibility(XListViewFooter.STATE_NODATA);
            r0_ViewGroup.addView(r5_ViewA[r1i]);
            r1i++;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new MenuOnGlobalLayoutListener(r0_ViewGroup, r5_ViewA, r6_SizeCallBack));
    }

    public boolean onInterceptTouchEvent(MotionEvent r2_MotionEvent) {
        return false;
    }

    protected void onScrollChanged(int r3i, int r4i, int r5i, int r6i) {
        super.onScrollChanged(r3i, r4i, r5i, r6i);
        if (QsbkApp.isPad) {
            if (r3i < (this.i - ENLARGE_WIDTH) / 2) {
                this.d = BUFFER_WIDTH;
            } else {
                this.d = this.i - ENLARGE_WIDTH + BUFFER_WIDTH;
            }
        } else if (r3i < ((this.b.getMeasuredWidth() - this.f.getMeasuredWidth()) - ENLARGE_WIDTH) / 2) {
            this.d = BUFFER_WIDTH;
        } else {
            this.d = this.b.getWidth() - this.f.getMeasuredWidth() - ENLARGE_WIDTH + BUFFER_WIDTH;
        }
        this.g = r3i;
    }

    public boolean onTouchEvent(MotionEvent r6_MotionEvent) {
        if (r6_MotionEvent.getPointerCount() > 1) {
            r6_MotionEvent.setAction(XListViewFooter.STATE_NOMORE);
            return super.onTouchEvent(r6_MotionEvent);
        } else {
            int r2i = (int) r6_MotionEvent.getRawX();
            if (r6_MotionEvent.getAction() == 0) {
                a();
                this.e = (float) ((int) r6_MotionEvent.getRawX());
            }
            if (this.g == BUFFER_WIDTH && r2i < this.h - BUFFER_WIDTH * 2) {
                return false;
            }
            if (this.g == this.h * 2 && r2i > ENLARGE_WIDTH + BUFFER_WIDTH) {
                return false;
            }
            if (!(menuOut) && this.e > ((float) TOUCHE_CONTROL_WIDTH)) {
                return true;
            }
            if (r6_MotionEvent.getAction() == 1) {
                if (this.g != BUFFER_WIDTH || r2i <= this.h - BUFFER_WIDTH * 2) {
                } else {
                    this.d = this.h - BUFFER_WIDTH;
                }
                this.d = this.d < BUFFER_WIDTH ? BUFFER_WIDTH : this.d;
                b();
                return false;
            } else {
                boolean r0z;
                try {
                    r0z = super.onTouchEvent(r6_MotionEvent);
                } catch (IllegalArgumentException e) {
                    r0z = false;
                } catch (ArrayIndexOutOfBoundsException e_2) {
                    r0z = false;
                }
                return r0z;
            }
        }
    }

    public void setMenuBtn(ImageButton r1_ImageButton) {
        this.f = r1_ImageButton;
    }
}