package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R;

public class ActionBarPolicy {
    private Context a;

    private ActionBarPolicy(Context r1_Context) {
        this.a = r1_Context;
    }

    public static ActionBarPolicy get(Context r1_Context) {
        return new ActionBarPolicy(r1_Context);
    }

    public boolean enableHomeButtonByDefault() {
        return this.a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons() {
        return this.a.getResources().getInteger(R.integer.abc_max_action_buttons);
    }

    public int getStackedTabMaxWidth() {
        return this.a.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight() {
        TypedArray r1_TypedArray = this.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int r0i = r1_TypedArray.getLayoutDimension(1, 0);
        Resources r2_Resources = this.a.getResources();
        if (!hasEmbeddedTabs()) {
            r0i = Math.min(r0i, r2_Resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        r1_TypedArray.recycle();
        return r0i;
    }

    public boolean hasEmbeddedTabs() {
        return this.a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
    }

    public boolean showsOverflowMenuButton() {
        return VERSION.SDK_INT >= 11;
    }
}