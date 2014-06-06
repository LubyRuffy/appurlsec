package android.support.v7.app;

import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.Menu;

// compiled from: ActionBarActivityDelegateBase.java
class c implements Runnable {
    final /* synthetic */ b a;

    c(b r1_b) {
        this.a = r1_b;
    }

    public void run() {
        MenuBuilder r0_MenuBuilder = b.a(this.a);
        if (this.a.a.a(0, (Menu)r0_MenuBuilder) && this.a.a.a(0, null, r0_MenuBuilder)) {
            b.a(this.a, r0_MenuBuilder);
        } else {
            b.a(this.a, null);
        }
        b.a(this.a, false);
    }
}