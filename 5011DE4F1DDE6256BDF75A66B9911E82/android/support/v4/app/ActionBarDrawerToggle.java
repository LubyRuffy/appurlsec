package android.support.v4.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;

public class ActionBarDrawerToggle implements DrawerListener {
    private static final a a;
    private final Activity b;
    private final Delegate c;
    private final DrawerLayout d;
    private boolean e;
    private Drawable f;
    private Drawable g;
    private d h;
    private final int i;
    private final int j;
    private final int k;
    private Object l;


    public static interface Delegate {
        public Drawable getThemeUpIndicator();

        public void setActionBarDescription(int r1i);

        public void setActionBarUpIndicator(Drawable r1_Drawable, int r2i);
    }

    public static interface DelegateProvider {
        public android.support.v4.app.ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
    }

    private static interface a {
        public Drawable getThemeUpIndicator(Activity r1_Activity);

        public Object setActionBarDescription(Object r1_Object, Activity r2_Activity, int r3i);

        public Object setActionBarUpIndicator(Object r1_Object, Activity r2_Activity, Drawable r3_Drawable, int r4i);
    }

    private static class b implements a {
        private b() {
        }

        public Drawable getThemeUpIndicator(Activity r2_Activity) {
            return null;
        }

        public Object setActionBarDescription(Object r1_Object, Activity r2_Activity, int r3i) {
            return r1_Object;
        }

        public Object setActionBarUpIndicator(Object r1_Object, Activity r2_Activity, Drawable r3_Drawable, int r4i) {
            return r1_Object;
        }
    }

    private static class c implements a {
        private c() {
        }

        public Drawable getThemeUpIndicator(Activity r2_Activity) {
            return a.getThemeUpIndicator(r2_Activity);
        }

        public Object setActionBarDescription(Object r2_Object, Activity r3_Activity, int r4i) {
            return a.setActionBarDescription(r2_Object, r3_Activity, r4i);
        }

        public Object setActionBarUpIndicator(Object r2_Object, Activity r3_Activity, Drawable r4_Drawable, int r5i) {
            return a.setActionBarUpIndicator(r2_Object, r3_Activity, r4_Drawable, r5i);
        }
    }

    private class d extends LevelListDrawable implements Callback {
        private final boolean b;
        private final Rect c;
        private float d;
        private float e;

        private d(Drawable r6_Drawable) {
            this.b = VERSION.SDK_INT > 18;
            this.c = new Rect();
            if (DrawableCompat.isAutoMirrored(r6_Drawable)) {
                DrawableCompat.setAutoMirrored(this, true);
            }
            addLevel(0, 0, r6_Drawable);
        }

        public void draw(Canvas r7_Canvas) {
            int r1i;
            int r0i = 1;
            copyBounds(this.c);
            r7_Canvas.save();
            r1i = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.b.getWindow().getDecorView()) == 1 ? 1 : 0;
            if (r1i != 0) {
                r0i = -1;
            }
            int r2i = this.c.width();
            r7_Canvas.translate(((float) r0i) * (((-this.e) * ((float) r2i)) * this.d), 0.0f);
            if (r1i == 0 || this.b) {
                super.draw(r7_Canvas);
                r7_Canvas.restore();
            } else {
                r7_Canvas.translate((float) r2i, 0.0f);
                r7_Canvas.scale(-1.0f, 1.0f);
                super.draw(r7_Canvas);
                r7_Canvas.restore();
            }
        }

        public float getPosition() {
            return this.d;
        }

        public void setOffset(float r1f) {
            this.e = r1f;
            invalidateSelf();
        }

        public void setPosition(float r1f) {
            this.d = r1f;
            invalidateSelf();
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public ActionBarDrawerToggle(Activity r4_Activity, DrawerLayout r5_DrawerLayout, int r6i, int r7i, int r8i) {
        this.e = true;
        this.b = r4_Activity;
        if (r4_Activity instanceof DelegateProvider) {
            this.c = ((DelegateProvider) r4_Activity).getDrawerToggleDelegate();
        } else {
            this.c = null;
        }
        this.d = r5_DrawerLayout;
        this.i = r6i;
        this.j = r7i;
        this.k = r8i;
        this.f = a();
        this.g = r4_Activity.getResources().getDrawable(r6i);
        this.h = new d(this.g, null);
        this.h.setOffset(0.33333334f);
    }

    Drawable a() {
        return this.c != null ? this.c.getThemeUpIndicator() : a.getThemeUpIndicator(this.b);
    }

    void a(int r4i) {
        if (this.c != null) {
            this.c.setActionBarDescription(r4i);
        } else {
            this.l = a.setActionBarDescription(this.l, this.b, r4i);
        }
    }

    void a(Drawable r4_Drawable, int r5i) {
        if (this.c != null) {
            this.c.setActionBarUpIndicator(r4_Drawable, r5i);
        } else {
            this.l = a.setActionBarUpIndicator(this.l, this.b, r4_Drawable, r5i);
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.e;
    }

    public void onConfigurationChanged(Configuration r3_Configuration) {
        this.f = a();
        this.g = this.b.getResources().getDrawable(this.i);
        syncState();
    }

    public void onDrawerClosed(View r3_View) {
        this.h.setPosition(0.0f);
        if (this.e) {
            a(this.j);
        }
    }

    public void onDrawerOpened(View r3_View) {
        this.h.setPosition(1.0f);
        if (this.e) {
            a(this.k);
        }
    }

    public void onDrawerSlide(View r5_View, float r6f) {
        float r2f = 0.5f;
        float r0f = this.h.getPosition();
        this.h.setPosition((r6f > 0.5f ? 1 : (r6f == 0.5f? 0 : -1)) > 0 ? Math.max(r0f, Math.max(0.0f, r6f - r2f) * 2.0f) : Math.min(r0f, r6f * 2.0f));
    }

    public void onDrawerStateChanged(int r1i) {
    }

    public boolean onOptionsItemSelected(MenuItem r4_MenuItem) {
        if (r4_MenuItem == null || r4_MenuItem.getItemId() != 16908332 || !(this.e)) {
            return false;
        }
        if (this.d.isDrawerVisible((int)GravityCompat.START)) {
            this.d.closeDrawer((int)GravityCompat.START);
        } else {
            this.d.openDrawer((int)GravityCompat.START);
        }
        return true;
    }

    public void setDrawerIndicatorEnabled(boolean r4z) {
        if (r4z != this.e) {
            if (r4z) {
                a(this.h, this.d.isDrawerOpen((int)GravityCompat.START) ? this.k : this.j);
            } else {
                a(this.f, 0);
            }
            this.e = r4z;
        }
    }

    public void syncState() {
        if (this.d.isDrawerOpen((int)GravityCompat.START)) {
            this.h.setPosition(1.0f);
        } else {
            this.h.setPosition(0.0f);
        }
        if (this.e) {
            a(this.h, this.d.isDrawerOpen((int)GravityCompat.START) ? this.k : this.j);
        }
    }
}