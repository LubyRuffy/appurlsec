package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder.ItemInvoker;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements ItemInvoker, MenuView, OnItemClickListener {
    private MenuBuilder a;
    private int b;

    public ExpandedMenuView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        setOnItemClickListener(this);
    }

    public int getWindowAnimations() {
        return this.b;
    }

    public void initialize(MenuBuilder r1_MenuBuilder) {
        this.a = r1_MenuBuilder;
    }

    public boolean invokeItem(MenuItemImpl r3_MenuItemImpl) {
        return this.a.performItemAction(r3_MenuItemImpl, 0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView r2_AdapterView, View r3_View, int r4i, long r5j) {
        invokeItem((MenuItemImpl) getAdapter().getItem(r4i));
    }
}