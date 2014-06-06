package android.support.v4.app;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// compiled from: FragmentManager.java
class q implements AnimationListener {
    final /* synthetic */ Fragment a;
    final /* synthetic */ l b;

    q(l r1_l, Fragment r2_Fragment) {
        this.b = r1_l;
        this.a = r2_Fragment;
    }

    public void onAnimationEnd(Animation r7_Animation) {
        if (this.a.k != null) {
            this.a.k = null;
            this.b.a(this.a, this.a.l, 0, 0, false);
        }
    }

    public void onAnimationRepeat(Animation r1_Animation) {
    }

    public void onAnimationStart(Animation r1_Animation) {
    }
}