package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.SpinnerAdapter;

public abstract class ActionBar {
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;
    public static final int NAVIGATION_MODE_LIST = 1;
    public static final int NAVIGATION_MODE_STANDARD = 0;
    public static final int NAVIGATION_MODE_TABS = 2;

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;

        public LayoutParams(int r3i) {
            this(-2, -1, r3i);
        }

        public LayoutParams(int r2i, int r3i) {
            super(r2i, r3i);
            this.gravity = -1;
            this.gravity = 19;
        }

        public LayoutParams(int r2i, int r3i, int r4i) {
            super(r2i, r3i);
            this.gravity = -1;
            this.gravity = r4i;
        }

        public LayoutParams(Context r4_Context, AttributeSet r5_AttributeSet) {
            super(r4_Context, r5_AttributeSet);
            this.gravity = -1;
            TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, R.styleable.ActionBarLayout);
            this.gravity = r0_TypedArray.getInt(NAVIGATION_MODE_STANDARD, -1);
            r0_TypedArray.recycle();
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams r2_android_support_v7_app_ActionBar_LayoutParams) {
            super(r2_android_support_v7_app_ActionBar_LayoutParams);
            this.gravity = -1;
            this.gravity = r2_android_support_v7_app_ActionBar_LayoutParams.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
            super(r2_android_view_ViewGroup_LayoutParams);
            this.gravity = -1;
        }
    }

    public static interface OnMenuVisibilityListener {
        public void onMenuVisibilityChanged(boolean r1z);
    }

    public static interface OnNavigationListener {
        public boolean onNavigationItemSelected(int r1i, long r2j);
    }

    public static abstract class Tab {
        public static final int INVALID_POSITION = -1;

        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract Object getTag();

        public abstract CharSequence getText();

        public abstract void select();

        public abstract android.support.v7.app.ActionBar.Tab setContentDescription(int r1i);

        public abstract android.support.v7.app.ActionBar.Tab setContentDescription(CharSequence r1_CharSequence);

        public abstract android.support.v7.app.ActionBar.Tab setCustomView(int r1i);

        public abstract android.support.v7.app.ActionBar.Tab setCustomView(View r1_View);

        public abstract android.support.v7.app.ActionBar.Tab setIcon(int r1i);

        public abstract android.support.v7.app.ActionBar.Tab setIcon(Drawable r1_Drawable);

        public abstract android.support.v7.app.ActionBar.Tab setTabListener(android.support.v7.app.ActionBar.TabListener r1_android_support_v7_app_ActionBar_TabListener);

        public abstract android.support.v7.app.ActionBar.Tab setTag(Object r1_Object);

        public abstract android.support.v7.app.ActionBar.Tab setText(int r1i);

        public abstract android.support.v7.app.ActionBar.Tab setText(CharSequence r1_CharSequence);
    }

    public static interface TabListener {
        public void onTabReselected(android.support.v7.app.ActionBar.Tab r1_android_support_v7_app_ActionBar_Tab, FragmentTransaction r2_FragmentTransaction);

        public void onTabSelected(android.support.v7.app.ActionBar.Tab r1_android_support_v7_app_ActionBar_Tab, FragmentTransaction r2_FragmentTransaction);

        public void onTabUnselected(android.support.v7.app.ActionBar.Tab r1_android_support_v7_app_ActionBar_Tab, FragmentTransaction r2_FragmentTransaction);
    }

    static interface a {
        public FragmentManager getSupportFragmentManager();
    }

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener r1_OnMenuVisibilityListener);

    public abstract void addTab(Tab r1_Tab);

    public abstract void addTab(Tab r1_Tab, int r2i);

    public abstract void addTab(Tab r1_Tab, int r2i, boolean r3z);

    public abstract void addTab(Tab r1_Tab, boolean r2z);

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public abstract int getHeight();

    public abstract int getNavigationItemCount();

    public abstract int getNavigationMode();

    public abstract int getSelectedNavigationIndex();

    public abstract Tab getSelectedTab();

    public abstract CharSequence getSubtitle();

    public abstract Tab getTabAt(int r1i);

    public abstract int getTabCount();

    public Context getThemedContext() {
        return null;
    }

    public abstract CharSequence getTitle();

    public abstract void hide();

    public abstract boolean isShowing();

    public abstract Tab newTab();

    public abstract void removeAllTabs();

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener r1_OnMenuVisibilityListener);

    public abstract void removeTab(Tab r1_Tab);

    public abstract void removeTabAt(int r1i);

    public abstract void selectTab(Tab r1_Tab);

    public abstract void setBackgroundDrawable(Drawable r1_Drawable);

    public abstract void setCustomView(int r1i);

    public abstract void setCustomView(View r1_View);

    public abstract void setCustomView(View r1_View, LayoutParams r2_LayoutParams);

    public abstract void setDisplayHomeAsUpEnabled(boolean r1z);

    public abstract void setDisplayOptions(int r1i);

    public abstract void setDisplayOptions(int r1i, int r2i);

    public abstract void setDisplayShowCustomEnabled(boolean r1z);

    public abstract void setDisplayShowHomeEnabled(boolean r1z);

    public abstract void setDisplayShowTitleEnabled(boolean r1z);

    public abstract void setDisplayUseLogoEnabled(boolean r1z);

    public void setHomeButtonEnabled(boolean r1z) {
    }

    public abstract void setIcon(int r1i);

    public abstract void setIcon(Drawable r1_Drawable);

    public abstract void setListNavigationCallbacks(SpinnerAdapter r1_SpinnerAdapter, OnNavigationListener r2_OnNavigationListener);

    public abstract void setLogo(int r1i);

    public abstract void setLogo(Drawable r1_Drawable);

    public abstract void setNavigationMode(int r1i);

    public abstract void setSelectedNavigationItem(int r1i);

    public void setSplitBackgroundDrawable(Drawable r1_Drawable) {
    }

    public void setStackedBackgroundDrawable(Drawable r1_Drawable) {
    }

    public abstract void setSubtitle(int r1i);

    public abstract void setSubtitle(CharSequence r1_CharSequence);

    public abstract void setTitle(int r1i);

    public abstract void setTitle(CharSequence r1_CharSequence);

    public abstract void show();
}