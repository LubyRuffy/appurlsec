package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

public class ActionBarContainer extends FrameLayout {
    private boolean a;
    private View b;
    private ActionBarView c;
    private Drawable d;
    private Drawable e;
    private Drawable f;
    private boolean g;
    private boolean h;

    public ActionBarContainer(Context r2_Context) {
        this(r2_Context, null);
    }

    public ActionBarContainer(Context r6_Context, AttributeSet r7_AttributeSet) {
        boolean r0z = true;
        super(r6_Context, r7_AttributeSet);
        setBackgroundDrawable(null);
        TypedArray r2_TypedArray = r6_Context.obtainStyledAttributes(r7_AttributeSet, R.styleable.ActionBar);
        this.d = r2_TypedArray.getDrawable(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        this.e = r2_TypedArray.getDrawable(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
        if (getId() == R.id.split_action_bar) {
            this.g = true;
            this.f = r2_TypedArray.getDrawable(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
        }
        r2_TypedArray.recycle();
        if (this.g) {
            if (this.f == null) {
                setWillNotDraw(r0z);
            } else {
                r0z = false;
            }
        } else if (!(this.d == null && this.e == null)) {
            r0z = false;
        }
        setWillNotDraw(r0z);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.d == null || (!this.d.isStateful())) {
            if (this.e == null || (!this.e.isStateful())) {
                if (this.f == null || (!this.f.isStateful())) {
                } else {
                    this.f.setState(getDrawableState());
                }
            } else {
                this.e.setState(getDrawableState());
                if (this.f == null || this.f.isStateful()) {
                } else {
                    this.f.setState(getDrawableState());
                }
            }
        } else {
            this.d.setState(getDrawableState());
            if (this.e == null || this.e.isStateful()) {
                if (this.f == null || this.f.isStateful()) {
                } else {
                    this.f.setState(getDrawableState());
                }
            } else {
                this.e.setState(getDrawableState());
                if (this.f == null || this.f.isStateful()) {
                } else {
                    this.f.setState(getDrawableState());
                }
            }
        }
    }

    public View getTabContainer() {
        return this.b;
    }

    public void onDraw(Canvas r2_Canvas) {
        if (getWidth() == 0 || getHeight() == 0) {
        } else if (this.g) {
            if (this.f != null) {
                this.f.draw(r2_Canvas);
            }
        } else {
            if (this.d != null) {
                this.d.draw(r2_Canvas);
            }
            if (this.e == null || (!this.h)) {
            } else {
                this.e.draw(r2_Canvas);
            }
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.c = (ActionBarView) findViewById(R.id.action_bar);
    }

    public boolean onHoverEvent(MotionEvent r2_MotionEvent) {
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent r2_MotionEvent) {
        return this.a || super.onInterceptTouchEvent(r2_MotionEvent);
    }

    public void onLayout(boolean r9z, int r10i, int r11i, int r12i, int r13i) {
        int r0i;
        int r1i = 1;
        boolean r2z = false;
        super.onLayout(r9z, r10i, r11i, r12i, r13i);
        r0i = (this.b == null || this.b.getVisibility() == 8) ? 0 : 1;
        int r3i;
        if (this.b == null || this.b.getVisibility() == 8) {
            if (this.g) {
                if (this.f != null) {
                    this.f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                } else {
                    r1i = 0;
                }
            } else {
                if (this.d != null) {
                    this.d.setBounds(this.c.getLeft(), this.c.getTop(), this.c.getRight(), this.c.getBottom());
                    r3i = 1;
                } else {
                    r3i = 0;
                }
                if (r0i == 0 || this.e == null) {
                    this.h = r2z;
                } else {
                    r2z = true;
                    this.h = r2z;
                }
                if (r2z) {
                    this.e.setBounds(this.b.getLeft(), this.b.getTop(), this.b.getRight(), this.b.getBottom());
                } else {
                    r1i = r3i;
                }
            }
            if (r1i != 0) {
                invalidate();
            }
        } else {
            r3i = getMeasuredHeight();
            int r4i = this.b.getMeasuredHeight();
            if ((this.c.getDisplayOptions() & 2) == 0) {
                int r5i = getChildCount();
                r3i = 0;
                while (r3i < r5i) {
                    View r6_View = getChildAt(r3i);
                    if (r6_View == this.b || this.c.isCollapsed()) {
                        r3i++;
                    } else {
                        r6_View.offsetTopAndBottom(r4i);
                        r3i++;
                    }
                }
                this.b.layout(r10i, 0, r12i, r4i);
                if (this.g) {
                    if (this.d != null) {
                        r3i = 0;
                    } else {
                        this.d.setBounds(this.c.getLeft(), this.c.getTop(), this.c.getRight(), this.c.getBottom());
                        r3i = 1;
                    }
                    if (r0i == 0 || this.e == null) {
                        this.h = r2z;
                    } else {
                        r2z = true;
                        this.h = r2z;
                    }
                    if (r2z) {
                        r1i = r3i;
                    } else {
                        this.e.setBounds(this.b.getLeft(), this.b.getTop(), this.b.getRight(), this.b.getBottom());
                    }
                } else if (this.f != null) {
                    r1i = 0;
                } else {
                    this.f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                }
                if (r1i != 0) {
                } else {
                    invalidate();
                }
            } else {
                this.b.layout(r10i, r3i - r4i, r12i, r3i);
                if (this.g) {
                    if (this.f != null) {
                        this.f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                    } else {
                        r1i = 0;
                    }
                } else {
                    if (this.d != null) {
                        this.d.setBounds(this.c.getLeft(), this.c.getTop(), this.c.getRight(), this.c.getBottom());
                        r3i = 1;
                    } else {
                        r3i = 0;
                    }
                    if (r0i == 0 || this.e == null) {
                        this.h = r2z;
                    } else {
                        r2z = true;
                        this.h = r2z;
                    }
                    if (r2z) {
                        this.e.setBounds(this.b.getLeft(), this.b.getTop(), this.b.getRight(), this.b.getBottom());
                    } else {
                        r1i = r3i;
                    }
                }
                if (r1i != 0) {
                    invalidate();
                }
            }
        }
    }

    public void onMeasure(int r5i, int r6i) {
        super.onMeasure(r5i, r6i);
        if (this.c == null) {
        } else {
            int r0i;
            LayoutParams r0_LayoutParams = (LayoutParams) this.c.getLayoutParams();
            r0i = this.c.isCollapsed() ? 0 : r0_LayoutParams.bottomMargin + this.c.getMeasuredHeight() + r0_LayoutParams.topMargin;
            if (this.b == null || this.b.getVisibility() == 8 || MeasureSpec.getMode(r6i) != -2147483648) {
            } else {
                setMeasuredDimension(getMeasuredWidth(), Math.min(r0i + this.b.getMeasuredHeight(), MeasureSpec.getSize(r6i)));
            }
        }
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        super.onTouchEvent(r2_MotionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable r5_Drawable) {
        boolean r0z = true;
        if (this.d != null) {
            this.d.setCallback(null);
            unscheduleDrawable(this.d);
        }
        this.d = r5_Drawable;
        if (r5_Drawable != null) {
            r5_Drawable.setCallback(this);
        }
        if (this.g) {
            if (this.f == null) {
                setWillNotDraw(r0z);
                invalidate();
            } else {
                r0z = false;
            }
        } else if (!(this.d == null && this.e == null)) {
            r0z = false;
        }
        setWillNotDraw(r0z);
        invalidate();
    }

    public void setSplitBackground(Drawable r5_Drawable) {
        boolean r0z = true;
        if (this.f != null) {
            this.f.setCallback(null);
            unscheduleDrawable(this.f);
        }
        this.f = r5_Drawable;
        if (r5_Drawable != null) {
            r5_Drawable.setCallback(this);
        }
        if (this.g) {
            if (this.f == null) {
                setWillNotDraw(r0z);
                invalidate();
            } else {
                r0z = false;
            }
        } else if (!(this.d == null && this.e == null)) {
            r0z = false;
        }
        setWillNotDraw(r0z);
        invalidate();
    }

    public void setStackedBackground(Drawable r5_Drawable) {
        boolean r0z = true;
        if (this.e != null) {
            this.e.setCallback(null);
            unscheduleDrawable(this.e);
        }
        this.e = r5_Drawable;
        if (r5_Drawable != null) {
            r5_Drawable.setCallback(this);
        }
        if (this.g) {
            if (this.f == null) {
                setWillNotDraw(r0z);
                invalidate();
            } else {
                r0z = false;
            }
        } else if (!(this.d == null && this.e == null)) {
            r0z = false;
        }
        setWillNotDraw(r0z);
        invalidate();
    }

    public void setTabContainer(ScrollingTabContainerView r3_ScrollingTabContainerView) {
        if (this.b != null) {
            removeView(this.b);
        }
        this.b = r3_ScrollingTabContainerView;
        if (r3_ScrollingTabContainerView != null) {
            addView(r3_ScrollingTabContainerView);
            ViewGroup.LayoutParams r0_ViewGroup_LayoutParams = r3_ScrollingTabContainerView.getLayoutParams();
            r0_ViewGroup_LayoutParams.width = -1;
            r0_ViewGroup_LayoutParams.height = -2;
            r3_ScrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean r2z) {
        this.a = r2z;
        setDescendantFocusability(r2z ? 393216 : AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
    }

    public void setVisibility(int r4i) {
        boolean r0z;
        super.setVisibility(r4i);
        r0z = r4i == 0;
        if (this.d != null) {
            this.d.setVisible(r0z, false);
        }
        if (this.e != null) {
            this.e.setVisible(r0z, false);
        }
        if (this.f != null) {
            this.f.setVisible(r0z, false);
        }
    }

    public ActionMode startActionModeForChild(View r2_View, Callback r3_Callback) {
        return null;
    }

    protected boolean verifyDrawable(Drawable r2_Drawable) {
        if (r2_Drawable == this.d && !(this.g)) {
            return true;
        }
        if (r2_Drawable == this.e && this.h) {
            return true;
        }
        if ((r2_Drawable == this.f && this.g) || super.verifyDrawable(r2_Drawable)) {
            return true;
        }
        return false;
    }
}