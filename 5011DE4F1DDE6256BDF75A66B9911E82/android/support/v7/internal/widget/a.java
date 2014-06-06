package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// compiled from: AbsActionBarView.java
abstract class a extends ViewGroup {
    protected ActionMenuView a;
    protected ActionMenuPresenter b;
    protected ActionBarContainer c;
    protected boolean d;
    protected boolean e;
    protected int f;

    a(Context r1_Context) {
        super(r1_Context);
    }

    a(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    a(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
    }

    protected int a_(View r3_View, int r4i, int r5i, int r6i) {
        r3_View.measure(MeasureSpec.makeMeasureSpec(r4i, ExploreByTouchHelper.INVALID_ID), r5i);
        return Math.max(0, r4i - r3_View.getMeasuredWidth() - r6i);
    }

    public void animateToVisibility(int r3i) {
        clearAnimation();
        if (r3i != getVisibility()) {
            Animation r0_Animation = AnimationUtils.loadAnimation(getContext(), r3i == 0 ? R.anim.abc_fade_in : R.anim.abc_fade_out);
            startAnimation(r0_Animation);
            setVisibility(r3i);
            if (this.c == null || this.a == null) {
            } else {
                this.a.startAnimation(r0_Animation);
                this.a.setVisibility(r3i);
            }
        }
    }

    protected int b(View r5_View, int r6i, int r7i, int r8i) {
        int r0i = r5_View.getMeasuredWidth();
        int r1i = r5_View.getMeasuredHeight();
        int r2i = (r8i - r1i) / 2 + r7i;
        r5_View.layout(r6i, r2i, r6i + r0i, r1i + r2i);
        return r0i;
    }

    protected int c(View r5_View, int r6i, int r7i, int r8i) {
        int r0i = r5_View.getMeasuredWidth();
        int r1i = r5_View.getMeasuredHeight();
        int r2i = (r8i - r1i) / 2 + r7i;
        r5_View.layout(r6i - r0i, r2i, r6i, r1i + r2i);
        return r0i;
    }

    public void dismissPopupMenus() {
        if (this.b != null) {
            this.b.dismissPopupMenus();
        }
    }

    public int getAnimatedVisibility() {
        return getVisibility();
    }

    public int getContentHeight() {
        return this.f;
    }

    public boolean hideOverflowMenu() {
        return this.b != null ? this.b.hideOverflowMenu() : false;
    }

    public boolean isOverflowMenuShowing() {
        return this.b != null ? this.b.isOverflowMenuShowing() : false;
    }

    public boolean isOverflowReserved() {
        return this.b != null && this.b.isOverflowReserved();
    }

    protected void onConfigurationChanged(Configuration r6_Configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(r6_Configuration);
        }
        TypedArray r0_TypedArray = getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        setContentHeight(r0_TypedArray.getLayoutDimension(1, 0));
        r0_TypedArray.recycle();
        if (this.e) {
            setSplitActionBar(getContext().getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow));
        }
        if (this.b != null) {
            this.b.onConfigurationChanged(r6_Configuration);
        }
    }

    public void postShowOverflowMenu() {
        post(new b(this));
    }

    public void setContentHeight(int r1i) {
        this.f = r1i;
        requestLayout();
    }

    public void setSplitActionBar(boolean r1z) {
        this.d = r1z;
    }

    public void setSplitView(ActionBarContainer r1_ActionBarContainer) {
        this.c = r1_ActionBarContainer;
    }

    public void setSplitWhenNarrow(boolean r1z) {
        this.e = r1z;
    }

    public void setVisibility(int r2i) {
        if (r2i != getVisibility()) {
            super.setVisibility(r2i);
        }
    }

    public boolean showOverflowMenu() {
        return this.b != null ? this.b.showOverflowMenu() : false;
    }
}