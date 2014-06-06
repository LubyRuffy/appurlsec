package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private final FragmentManager a;
    private FragmentTransaction b;
    private ArrayList<SavedState> c;
    private ArrayList<Fragment> d;
    private Fragment e;

    public FragmentStatePagerAdapter(FragmentManager r3_FragmentManager) {
        this.b = null;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = null;
        this.a = r3_FragmentManager;
    }

    public void destroyItem(ViewGroup r4_ViewGroup, int r5i, Object r6_Object) {
        Fragment r6_Fragment = (Fragment) r6_Object;
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        while (this.c.size() <= r5i) {
            this.c.add(null);
        }
        this.c.set(r5i, this.a.saveFragmentInstanceState(r6_Fragment));
        this.d.set(r5i, null);
        this.b.remove(r6_Fragment);
    }

    public void finishUpdate(ViewGroup r2_ViewGroup) {
        if (this.b != null) {
            this.b.commitAllowingStateLoss();
            this.b = null;
            this.a.executePendingTransactions();
        }
    }

    public abstract Fragment getItem(int r1i);

    public Object instantiateItem(ViewGroup r5_ViewGroup, int r6i) {
        if (this.d.size() > r6i) {
            Fragment r0_Fragment = (Fragment) this.d.get(r6i);
            if (r0_Fragment != null) {
                return r0_Fragment;
            }
        }
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        Fragment r1_Fragment = getItem(r6i);
        if (this.c.size() > r6i) {
            SavedState r0_SavedState = (SavedState) this.c.get(r6i);
            if (r0_SavedState != null) {
                r1_Fragment.setInitialSavedState(r0_SavedState);
            }
        }
        while (this.d.size() <= r6i) {
            this.d.add(null);
        }
        r1_Fragment.setMenuVisibility(false);
        r1_Fragment.setUserVisibleHint(false);
        this.d.set(r6i, r1_Fragment);
        this.b.add(r5_ViewGroup.getId(), r1_Fragment);
        return r1_Fragment;
    }

    public boolean isViewFromObject(View r2_View, Object r3_Object) {
        return ((Fragment) r3_Object).getView() == r2_View;
    }

    public void restoreState(Parcelable r7_Parcelable, ClassLoader r8_ClassLoader) {
        if (r7_Parcelable != null) {
            Bundle r7_Bundle = (Bundle) r7_Parcelable;
            r7_Bundle.setClassLoader(r8_ClassLoader);
            Parcelable[] r3_ParcelableA = r7_Bundle.getParcelableArray("states");
            this.c.clear();
            this.d.clear();
            if (r3_ParcelableA != null) {
                int r1i = 0;
                while (r1i < r3_ParcelableA.length) {
                    this.c.add((SavedState) r3_ParcelableA[r1i]);
                    r1i++;
                }
            }
            Iterator r1_Iterator = r7_Bundle.keySet().iterator();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                if (r0_String.startsWith("f")) {
                    int r3i = Integer.parseInt(r0_String.substring(1));
                    Fragment r4_Fragment = this.a.getFragment(r7_Bundle, r0_String);
                    if (r4_Fragment != null) {
                        while (this.d.size() <= r3i) {
                            this.d.add(null);
                        }
                        r4_Fragment.setMenuVisibility(false);
                        this.d.set(r3i, r4_Fragment);
                    } else {
                        Log.w("FragmentStatePagerAdapter", "Bad fragment at key " + r0_String);
                    }
                }
            }
        }
    }

    public Parcelable saveState() {
        Bundle r0_Bundle = null;
        if (this.c.size() > 0) {
            r0_Bundle = new Bundle();
            Parcelable[] r1_ParcelableA = new Parcelable[this.c.size()];
            this.c.toArray(r1_ParcelableA);
            r0_Bundle.putParcelableArray("states", r1_ParcelableA);
        }
        int r1i = 0;
        Bundle r2_Bundle = r0_Bundle;
        while (r1i < this.d.size()) {
            Fragment r0_Fragment = (Fragment) this.d.get(r1i);
            if (r0_Fragment != null) {
                if (r2_Bundle == null) {
                    r2_Bundle = new Bundle();
                }
                this.a.putFragment(r2_Bundle, "f" + r1i, r0_Fragment);
            }
            r1i++;
        }
        return r2_Bundle;
    }

    public void setPrimaryItem(ViewGroup r4_ViewGroup, int r5i, Object r6_Object) {
        Fragment r6_Fragment = (Fragment) r6_Object;
        if (r6_Fragment != this.e) {
            if (this.e != null) {
                this.e.setMenuVisibility(false);
                this.e.setUserVisibleHint(false);
            }
            if (r6_Fragment != null) {
                r6_Fragment.setMenuVisibility(true);
                r6_Fragment.setUserVisibleHint(true);
            }
            this.e = r6_Fragment;
        }
    }

    public void startUpdate(ViewGroup r1_ViewGroup) {
    }
}