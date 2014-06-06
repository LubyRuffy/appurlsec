package android.support.v7.app;

import android.app.ActionBar;
import android.app.ActionBar.OnMenuVisibilityListener;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.Tab;
import android.view.View;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// compiled from: ActionBarImplICS.java
class h extends ActionBar {
    final Activity a;
    final a b;
    final ActionBar c;
    FragmentTransaction d;
    private ArrayList<WeakReference<a>> e;

    // compiled from: ActionBarImplICS.java
    static class a implements OnMenuVisibilityListener {
        final ActionBar.OnMenuVisibilityListener a;

        public a(ActionBar.OnMenuVisibilityListener r1_ActionBar_OnMenuVisibilityListener) {
            this.a = r1_ActionBar_OnMenuVisibilityListener;
        }

        public void onMenuVisibilityChanged(boolean r2z) {
            this.a.onMenuVisibilityChanged(r2z);
        }
    }

    // compiled from: ActionBarImplICS.java
    static class b implements OnNavigationListener {
        private final ActionBar.OnNavigationListener a;

        public b(ActionBar.OnNavigationListener r1_ActionBar_OnNavigationListener) {
            this.a = r1_ActionBar_OnNavigationListener;
        }

        public boolean onNavigationItemSelected(int r2i, long r3j) {
            return this.a.onNavigationItemSelected(r2i, r3j);
        }
    }

    // compiled from: ActionBarImplICS.java
    class c extends Tab implements TabListener {
        final ActionBar.Tab a;
        private Object c;
        private CharSequence d;
        private ActionBar.TabListener e;

        public c(ActionBar.Tab r2_ActionBar_Tab) {
            this.a = r2_ActionBar_Tab;
        }

        public CharSequence getContentDescription() {
            return this.d;
        }

        public View getCustomView() {
            return this.a.getCustomView();
        }

        public Drawable getIcon() {
            return this.a.getIcon();
        }

        public int getPosition() {
            return this.a.getPosition();
        }

        public Object getTag() {
            return this.c;
        }

        public CharSequence getText() {
            return this.a.getText();
        }

        public void onTabReselected(ActionBar.Tab r3_ActionBar_Tab, android.app.FragmentTransaction r4_android_app_FragmentTransaction) {
            this.e.onTabReselected(this, r4_android_app_FragmentTransaction != null ? h.this.a() : null);
            h.this.b();
        }

        public void onTabSelected(ActionBar.Tab r3_ActionBar_Tab, android.app.FragmentTransaction r4_android_app_FragmentTransaction) {
            this.e.onTabSelected(this, r4_android_app_FragmentTransaction != null ? h.this.a() : null);
            h.this.b();
        }

        public void onTabUnselected(ActionBar.Tab r3_ActionBar_Tab, android.app.FragmentTransaction r4_android_app_FragmentTransaction) {
            this.e.onTabUnselected(this, r4_android_app_FragmentTransaction != null ? h.this.a() : null);
        }

        public void select() {
            this.a.select();
        }

        public Tab setContentDescription(int r2i) {
            this.d = h.this.a.getText(r2i);
            return this;
        }

        public Tab setContentDescription(CharSequence r1_CharSequence) {
            this.d = r1_CharSequence;
            return this;
        }

        public Tab setCustomView(int r2i) {
            this.a.setCustomView(r2i);
            return this;
        }

        public Tab setCustomView(View r2_View) {
            this.a.setCustomView(r2_View);
            return this;
        }

        public Tab setIcon(int r2i) {
            this.a.setIcon(r2i);
            return this;
        }

        public Tab setIcon(Drawable r2_Drawable) {
            this.a.setIcon(r2_Drawable);
            return this;
        }

        public Tab setTabListener(ActionBar.TabListener r3_ActionBar_TabListener) {
            this.e = r3_ActionBar_TabListener;
            this.a.setTabListener(r3_ActionBar_TabListener != null ? this : null);
            return this;
        }

        public Tab setTag(Object r1_Object) {
            this.c = r1_Object;
            return this;
        }

        public Tab setText(int r2i) {
            this.a.setText(r2i);
            return this;
        }

        public Tab setText(CharSequence r2_CharSequence) {
            this.a.setText(r2_CharSequence);
            return this;
        }
    }

    public h(Activity r2_Activity, a r3_a) {
        this(r2_Activity, r3_a, true);
    }

    h(Activity r2_Activity, a r3_a, boolean r4z) {
        this.e = new ArrayList();
        this.a = r2_Activity;
        this.b = r3_a;
        this.c = r2_Activity.getActionBar();
        if ((!r4z) || (getDisplayOptions() & 4) == 0) {
        } else {
            setHomeButtonEnabled(true);
        }
    }

    private a a(ActionBar.OnMenuVisibilityListener r4_ActionBar_OnMenuVisibilityListener) {
        int r1i = 0;
        while (r1i < this.e.size()) {
            a r0_a = (a) ((WeakReference) this.e.get(r1i)).get();
            if (r0_a == null) {
                this.e.remove(r1i);
                r1i--;
            } else if (r0_a.a == r4_ActionBar_OnMenuVisibilityListener) {
                this.e.remove(r1i);
                return r0_a;
            }
            r1i++;
        }
        return null;
    }

    FragmentTransaction a() {
        if (this.d == null) {
            this.d = this.b.getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        }
        return this.d;
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener r4_ActionBar_OnMenuVisibilityListener) {
        if (r4_ActionBar_OnMenuVisibilityListener != null) {
            OnMenuVisibilityListener r0_OnMenuVisibilityListener = new a(r4_ActionBar_OnMenuVisibilityListener);
            this.e.add(new WeakReference(r0_OnMenuVisibilityListener));
            this.c.addOnMenuVisibilityListener(r0_OnMenuVisibilityListener);
        }
    }

    public void addTab(Tab r3_Tab) {
        this.c.addTab(((c) r3_Tab).a);
    }

    public void addTab(Tab r3_Tab, int r4i) {
        this.c.addTab(((c) r3_Tab).a, r4i);
    }

    public void addTab(Tab r3_Tab, int r4i, boolean r5z) {
        this.c.addTab(((c) r3_Tab).a, r4i, r5z);
    }

    public void addTab(Tab r3_Tab, boolean r4z) {
        this.c.addTab(((c) r3_Tab).a, r4z);
    }

    void b() {
        if (this.d == null || this.d.isEmpty()) {
            this.d = null;
        } else {
            this.d.commit();
            this.d = null;
        }
    }

    public View getCustomView() {
        return this.c.getCustomView();
    }

    public int getDisplayOptions() {
        return this.c.getDisplayOptions();
    }

    public int getHeight() {
        return this.c.getHeight();
    }

    public int getNavigationItemCount() {
        return this.c.getNavigationItemCount();
    }

    public int getNavigationMode() {
        return this.c.getNavigationMode();
    }

    public int getSelectedNavigationIndex() {
        return this.c.getSelectedNavigationIndex();
    }

    public Tab getSelectedTab() {
        return (Tab) this.c.getSelectedTab().getTag();
    }

    public CharSequence getSubtitle() {
        return this.c.getSubtitle();
    }

    public Tab getTabAt(int r2i) {
        return (Tab) this.c.getTabAt(r2i).getTag();
    }

    public int getTabCount() {
        return this.c.getTabCount();
    }

    public Context getThemedContext() {
        return this.c.getThemedContext();
    }

    public CharSequence getTitle() {
        return this.c.getTitle();
    }

    public void hide() {
        this.c.hide();
    }

    public boolean isShowing() {
        return this.c.isShowing();
    }

    public Tab newTab() {
        ActionBar.Tab r0_ActionBar_Tab = this.c.newTab();
        Tab r1_Tab = new c(r0_ActionBar_Tab);
        r0_ActionBar_Tab.setTag(r1_Tab);
        return r1_Tab;
    }

    public void removeAllTabs() {
        this.c.removeAllTabs();
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener r3_ActionBar_OnMenuVisibilityListener) {
        this.c.removeOnMenuVisibilityListener(a(r3_ActionBar_OnMenuVisibilityListener));
    }

    public void removeTab(Tab r3_Tab) {
        this.c.removeTab(((c) r3_Tab).a);
    }

    public void removeTabAt(int r2i) {
        this.c.removeTabAt(r2i);
    }

    public void selectTab(Tab r3_Tab) {
        this.c.selectTab(((c) r3_Tab).a);
    }

    public void setBackgroundDrawable(Drawable r2_Drawable) {
        this.c.setBackgroundDrawable(r2_Drawable);
    }

    public void setCustomView(int r2i) {
        this.c.setCustomView(r2i);
    }

    public void setCustomView(View r2_View) {
        this.c.setCustomView(r2_View);
    }

    public void setCustomView(View r3_View, LayoutParams r4_LayoutParams) {
        ActionBar.LayoutParams r0_ActionBar_LayoutParams = new ActionBar.LayoutParams(r4_LayoutParams);
        r0_ActionBar_LayoutParams.gravity = r4_LayoutParams.gravity;
        this.c.setCustomView(r3_View, r0_ActionBar_LayoutParams);
    }

    public void setDisplayHomeAsUpEnabled(boolean r2z) {
        this.c.setDisplayHomeAsUpEnabled(r2z);
    }

    public void setDisplayOptions(int r2i) {
        this.c.setDisplayOptions(r2i);
    }

    public void setDisplayOptions(int r2i, int r3i) {
        this.c.setDisplayOptions(r2i, r3i);
    }

    public void setDisplayShowCustomEnabled(boolean r2z) {
        this.c.setDisplayShowCustomEnabled(r2z);
    }

    public void setDisplayShowHomeEnabled(boolean r2z) {
        this.c.setDisplayShowHomeEnabled(r2z);
    }

    public void setDisplayShowTitleEnabled(boolean r2z) {
        this.c.setDisplayShowTitleEnabled(r2z);
    }

    public void setDisplayUseLogoEnabled(boolean r2z) {
        this.c.setDisplayUseLogoEnabled(r2z);
    }

    public void setHomeButtonEnabled(boolean r2z) {
        this.c.setHomeButtonEnabled(r2z);
    }

    public void setIcon(int r2i) {
        this.c.setIcon(r2i);
    }

    public void setIcon(Drawable r2_Drawable) {
        this.c.setIcon(r2_Drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter r3_SpinnerAdapter, ActionBar.OnNavigationListener r4_ActionBar_OnNavigationListener) {
        this.c.setListNavigationCallbacks(r3_SpinnerAdapter, r4_ActionBar_OnNavigationListener != null ? new b(r4_ActionBar_OnNavigationListener) : null);
    }

    public void setLogo(int r2i) {
        this.c.setLogo(r2i);
    }

    public void setLogo(Drawable r2_Drawable) {
        this.c.setLogo(r2_Drawable);
    }

    public void setNavigationMode(int r2i) {
        this.c.setNavigationMode(r2i);
    }

    public void setSelectedNavigationItem(int r2i) {
        this.c.setSelectedNavigationItem(r2i);
    }

    public void setSubtitle(int r2i) {
        this.c.setSubtitle(r2i);
    }

    public void setSubtitle(CharSequence r2_CharSequence) {
        this.c.setSubtitle(r2_CharSequence);
    }

    public void setTitle(int r2i) {
        this.c.setTitle(r2i);
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.c.setTitle(r2_CharSequence);
    }

    public void show() {
        this.c.show();
    }
}