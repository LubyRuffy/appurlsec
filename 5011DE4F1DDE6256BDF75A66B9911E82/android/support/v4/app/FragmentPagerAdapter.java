package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private final FragmentManager a;
    private FragmentTransaction b;
    private Fragment c;

    public FragmentPagerAdapter(FragmentManager r2_FragmentManager) {
        this.b = null;
        this.c = null;
        this.a = r2_FragmentManager;
    }

    private static String a(int r2i, long r3j) {
        return "android:switcher:" + r2i + ":" + r3j;
    }

    public void destroyItem(ViewGroup r2_ViewGroup, int r3i, Object r4_Object) {
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        this.b.detach((Fragment) r4_Object);
    }

    public void finishUpdate(ViewGroup r2_ViewGroup) {
        if (this.b != null) {
            this.b.commitAllowingStateLoss();
            this.b = null;
            this.a.executePendingTransactions();
        }
    }

    public abstract Fragment getItem(int r1i);

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public Object instantiateItem(ViewGroup r8_ViewGroup, int r9i) {
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        long r1j = getItemId(r9i);
        Fragment r0_Fragment = this.a.findFragmentByTag(a(r8_ViewGroup.getId(), r1j));
        if (r0_Fragment != null) {
            this.b.attach(r0_Fragment);
        } else {
            r0_Fragment = getItem(r9i);
            this.b.add(r8_ViewGroup.getId(), r0_Fragment, a(r8_ViewGroup.getId(), r1j));
        }
        if (r0_Fragment != this.c) {
            r0_Fragment.setMenuVisibility(false);
            r0_Fragment.setUserVisibleHint(false);
        }
        return r0_Fragment;
    }

    public boolean isViewFromObject(View r2_View, Object r3_Object) {
        return ((Fragment) r3_Object).getView() == r2_View;
    }

    public void restoreState(Parcelable r1_Parcelable, ClassLoader r2_ClassLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(ViewGroup r4_ViewGroup, int r5i, Object r6_Object) {
        Fragment r6_Fragment = (Fragment) r6_Object;
        if (r6_Fragment != this.c) {
            if (this.c != null) {
                this.c.setMenuVisibility(false);
                this.c.setUserVisibleHint(false);
            }
            if (r6_Fragment != null) {
                r6_Fragment.setMenuVisibility(true);
                r6_Fragment.setUserVisibleHint(true);
            }
            this.c = r6_Fragment;
        }
    }

    public void startUpdate(ViewGroup r1_ViewGroup) {
    }
}