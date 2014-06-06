package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider.VisibilityListener;

// compiled from: MenuItemImpl.java
class d implements VisibilityListener {
    final /* synthetic */ MenuItemImpl a;

    d(MenuItemImpl r1_MenuItemImpl) {
        this.a = r1_MenuItemImpl;
    }

    public void onActionProviderVisibilityChanged(boolean r3z) {
        this.a.l.a(this.a);
    }
}