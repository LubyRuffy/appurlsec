package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider.VisibilityListener;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

// compiled from: MenuItemWrapperJB.java
class f extends MenuItemWrapperICS {

    // compiled from: MenuItemWrapperJB.java
    class a extends a implements VisibilityListener {
        ActionProvider.VisibilityListener c;

        public a(android.support.v4.view.ActionProvider r2_android_support_v4_view_ActionProvider) {
            super(r2_android_support_v4_view_ActionProvider);
        }

        public boolean isVisible() {
            return this.a.isVisible();
        }

        public void onActionProviderVisibilityChanged(boolean r2z) {
            if (this.c != null) {
                this.c.onActionProviderVisibilityChanged(r2z);
            }
        }

        public View onCreateActionView(MenuItem r2_MenuItem) {
            return this.a.onCreateActionView(r2_MenuItem);
        }

        public boolean overridesItemVisibility() {
            return this.a.overridesItemVisibility();
        }

        public void refreshVisibility() {
            this.a.refreshVisibility();
        }

        public void setVisibilityListener(ActionProvider.VisibilityListener r2_ActionProvider_VisibilityListener) {
            this.c = r2_ActionProvider_VisibilityListener;
            android.support.v4.view.ActionProvider r0_android_support_v4_view_ActionProvider = this.a;
            if (r2_ActionProvider_VisibilityListener != null) {
                r0_android_support_v4_view_ActionProvider.setVisibilityListener(this);
            } else {
                this = null;
                r0_android_support_v4_view_ActionProvider.setVisibilityListener(this);
            }
        }
    }

    f(MenuItem r2_MenuItem) {
        super(r2_MenuItem, false);
    }

    a a(android.support.v4.view.ActionProvider r2_android_support_v4_view_ActionProvider) {
        return new a(r2_android_support_v4_view_ActionProvider);
    }
}