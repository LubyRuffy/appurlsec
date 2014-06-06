package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider.VisibilityListener;

// compiled from: MenuItemWrapperICS.java
class e implements VisibilityListener {
    final /* synthetic */ MenuItemWrapperICS a;
    final /* synthetic */ a b;

    e(a r1_a, MenuItemWrapperICS r2_MenuItemWrapperICS) {
        this.b = r1_a;
        this.a = r2_MenuItemWrapperICS;
    }

    public void onActionProviderVisibilityChanged(boolean r2z) {
        if (this.b.a.overridesItemVisibility() && this.b.b.c) {
            this.b.b.a(r2z);
        }
    }
}