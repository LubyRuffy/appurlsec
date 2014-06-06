package qsbk.app.widget.slidingmenu.ui;

import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView.MenuOnGlobalLayoutListener;

// compiled from: MenuHorizontalScrollView.java
class a implements Runnable {
    final /* synthetic */ MenuOnGlobalLayoutListener a;

    a(MenuOnGlobalLayoutListener r1_MenuOnGlobalLayoutListener) {
        this.a = r1_MenuOnGlobalLayoutListener;
    }

    public void run() {
        MenuHorizontalScrollView.a(this.a.a).scrollBy(MenuHorizontalScrollView.b(this.a.a), 0);
        MenuHorizontalScrollView.a(this.a.a).setVisibility(0);
        MenuHorizontalScrollView.c(this.a.a).setVisibility(0);
    }
}