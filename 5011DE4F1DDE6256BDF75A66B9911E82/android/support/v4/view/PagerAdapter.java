package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private DataSetObservable a;

    public PagerAdapter() {
        this.a = new DataSetObservable();
    }

    public void destroyItem(View r3_View, int r4i, Object r5_Object) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup r1_ViewGroup, int r2i, Object r3_Object) {
        destroyItem((View)r1_ViewGroup, r2i, r3_Object);
    }

    public void finishUpdate(View r1_View) {
    }

    public void finishUpdate(ViewGroup r1_ViewGroup) {
        finishUpdate((View)r1_ViewGroup);
    }

    public abstract int getCount();

    public int getItemPosition(Object r2_Object) {
        return POSITION_UNCHANGED;
    }

    public CharSequence getPageTitle(int r2i) {
        return null;
    }

    public float getPageWidth(int r2i) {
        return 1.0f;
    }

    public Object instantiateItem(View r3_View, int r4i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup r2_ViewGroup, int r3i) {
        return instantiateItem((View)r2_ViewGroup, r3i);
    }

    public abstract boolean isViewFromObject(View r1_View, Object r2_Object);

    public void notifyDataSetChanged() {
        this.a.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver r2_DataSetObserver) {
        this.a.registerObserver(r2_DataSetObserver);
    }

    public void restoreState(Parcelable r1_Parcelable, ClassLoader r2_ClassLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(View r1_View, int r2i, Object r3_Object) {
    }

    public void setPrimaryItem(ViewGroup r1_ViewGroup, int r2i, Object r3_Object) {
        setPrimaryItem((View)r1_ViewGroup, r2i, r3_Object);
    }

    public void startUpdate(View r1_View) {
    }

    public void startUpdate(ViewGroup r1_ViewGroup) {
        startUpdate((View)r1_ViewGroup);
    }

    public void unregisterDataSetObserver(DataSetObserver r2_DataSetObserver) {
        this.a.unregisterObserver(r2_DataSetObserver);
    }
}