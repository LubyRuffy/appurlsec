package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.SpinnerAdapter;

abstract class AbsSpinnerICS extends AdapterViewICS<SpinnerAdapter> {
    private DataSetObserver F;
    private Rect G;
    SpinnerAdapter a;
    int b;
    int c;
    boolean d;
    int e;
    int f;
    int g;
    int h;
    final Rect i;
    final a j;


    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        long a;
        int b;

        static {
            CREATOR = new c();
        }

        private SavedState(Parcel r3_Parcel) {
            super(r3_Parcel);
            this.a = r3_Parcel.readLong();
            this.b = r3_Parcel.readInt();
        }

        SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.a + " position=" + this.b + "}";
        }

        public void writeToParcel(Parcel r3_Parcel, int r4i) {
            super.writeToParcel(r3_Parcel, r4i);
            r3_Parcel.writeLong(this.a);
            r3_Parcel.writeInt(this.b);
        }
    }

    class a {
        private final SparseArray<View> b;

        a() {
            this.b = new SparseArray();
        }

        View a_(int r3i) {
            View r0_View = (View) this.b.get(r3i);
            if (r0_View != null) {
                this.b.delete(r3i);
            }
            return r0_View;
        }

        void a_() {
            SparseArray r2_SparseArray = this.b;
            int r3i = r2_SparseArray.size();
            int r1i = 0;
            while (r1i < r3i) {
                View r0_View = (View) r2_SparseArray.valueAt(r1i);
                if (r0_View != null) {
                    AbsSpinnerICS.this.removeDetachedView(r0_View, true);
                }
                r1i++;
            }
            r2_SparseArray.clear();
        }

        public void put(int r2i, View r3_View) {
            this.b.put(r2i, r3_View);
        }
    }

    AbsSpinnerICS(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
        super(r2_Context, r3_AttributeSet, r4i);
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = new Rect();
        this.j = new a();
        j();
    }

    private void j() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    int a(View r2_View) {
        return r2_View.getMeasuredHeight();
    }

    void a() {
        this.v = false;
        this.p = false;
        removeAllViewsInLayout();
        this.C = -1;
        this.D = -9223372036854775808L;
        a(-1);
        b(-1);
        invalidate();
    }

    void a(int r2i, boolean r3z) {
        if (r2i != this.C) {
            this.d = true;
            b(r2i);
            b(r2i - this.y, r3z);
            this.d = false;
        }
    }

    int b(View r2_View) {
        return r2_View.getMeasuredWidth();
    }

    void b() {
        int r1i = getChildCount();
        a r2_a = this.j;
        int r3i = this.k;
        int r0i = 0;
        while (r0i < r1i) {
            r2_a.put(r3i + r0i, getChildAt(r0i));
            r0i++;
        }
    }

    abstract void b(int r1i, boolean r2z);

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public SpinnerAdapter getAdapter() {
        return this.a;
    }

    public int getCount() {
        return this.A;
    }

    public View getSelectedView() {
        return (this.A <= 0 || this.y < 0) ? null : getChildAt(this.y - this.k);
    }

    protected void onMeasure(int r10i, int r11i) {
        int r6i = MeasureSpec.getMode(r10i);
        int r0i = getPaddingLeft();
        int r1i = getPaddingTop();
        int r2i = getPaddingRight();
        int r3i = getPaddingBottom();
        Rect r7_Rect = this.i;
        View r0_View;
        if (r0i > this.e) {
            r7_Rect.left = r0i;
            this.i.top = r1i <= this.f ? r1i : this.f;
            this.i.right = r2i <= this.g ? r2i : this.g;
            this.i.bottom = r3i <= this.h ? r3i : this.h;
            if (!this.v) {
                f();
            }
            r1i = getSelectedItemPosition();
            if (r1i < 0 || this.a == null || r1i >= this.a.getCount()) {
                r2i = 1;
                r0i = 0;
                r1i = 0;
            } else {
                r0_View = this.j.a(r1i);
                if (r0_View == null) {
                    r0_View = this.a.getView(r1i, null, this);
                }
                if (r0_View == null) {
                    this.j.put(r1i, r0_View);
                }
                if (r0_View == null) {
                    if (r0_View.getLayoutParams() != null) {
                        this.d = true;
                        r0_View.setLayoutParams(generateDefaultLayoutParams());
                        this.d = false;
                    }
                    measureChild(r0_View, r10i, r11i);
                    r1i = a(r0_View) + this.i.top + this.i.bottom;
                    r0i = b(r0_View) + this.i.left + this.i.right;
                    r2i = 0;
                }
                r2i = 1;
                r0i = 0;
                r1i = 0;
            }
            if (r2i != 0) {
                r1i = this.i.top + this.i.bottom;
                if (r6i == 0) {
                    r0i = this.i.left + this.i.right;
                }
            }
            setMeasuredDimension(resolveSize(Math.max(r0i, getSuggestedMinimumWidth()), r10i), resolveSize(Math.max(r1i, getSuggestedMinimumHeight()), r11i));
            this.b = r11i;
            this.c = r10i;
        } else {
            r0i = this.e;
            r7_Rect.left = r0i;
            if (r1i <= this.f) {
            }
            this.i.top = r1i <= this.f ? r1i : this.f;
            if (r2i <= this.g) {
            }
            this.i.right = r2i <= this.g ? r2i : this.g;
            if (r3i <= this.h) {
            }
            this.i.bottom = r3i <= this.h ? r3i : this.h;
            if (this.v) {
                r1i = getSelectedItemPosition();
                if (r1i < 0 || this.a == null || r1i >= this.a.getCount()) {
                    r2i = 1;
                    r0i = 0;
                    r1i = 0;
                } else {
                    r0_View = this.j.a(r1i);
                    if (r0_View == null) {
                        if (r0_View == null) {
                            if (r0_View == null) {
                                r2i = 1;
                                r0i = 0;
                                r1i = 0;
                            } else if (r0_View.getLayoutParams() != null) {
                                measureChild(r0_View, r10i, r11i);
                                r1i = a(r0_View) + this.i.top + this.i.bottom;
                                r0i = b(r0_View) + this.i.left + this.i.right;
                                r2i = 0;
                            } else {
                                this.d = true;
                                r0_View.setLayoutParams(generateDefaultLayoutParams());
                                this.d = false;
                                measureChild(r0_View, r10i, r11i);
                                r1i = a(r0_View) + this.i.top + this.i.bottom;
                                r0i = b(r0_View) + this.i.left + this.i.right;
                                r2i = 0;
                            }
                        } else {
                            this.j.put(r1i, r0_View);
                            if (r0_View == null) {
                                if (r0_View.getLayoutParams() != null) {
                                    this.d = true;
                                    r0_View.setLayoutParams(generateDefaultLayoutParams());
                                    this.d = false;
                                }
                                measureChild(r0_View, r10i, r11i);
                                r1i = a(r0_View) + this.i.top + this.i.bottom;
                                r0i = b(r0_View) + this.i.left + this.i.right;
                                r2i = 0;
                            }
                            r2i = 1;
                            r0i = 0;
                            r1i = 0;
                        }
                    } else {
                        r0_View = this.a.getView(r1i, null, this);
                        if (r0_View == null) {
                            this.j.put(r1i, r0_View);
                        }
                        if (r0_View == null) {
                            r2i = 1;
                            r0i = 0;
                            r1i = 0;
                        } else if (r0_View.getLayoutParams() != null) {
                            measureChild(r0_View, r10i, r11i);
                            r1i = a(r0_View) + this.i.top + this.i.bottom;
                            r0i = b(r0_View) + this.i.left + this.i.right;
                            r2i = 0;
                        } else {
                            this.d = true;
                            r0_View.setLayoutParams(generateDefaultLayoutParams());
                            this.d = false;
                            measureChild(r0_View, r10i, r11i);
                            r1i = a(r0_View) + this.i.top + this.i.bottom;
                            r0i = b(r0_View) + this.i.left + this.i.right;
                            r2i = 0;
                        }
                    }
                }
                if (r2i != 0) {
                    setMeasuredDimension(resolveSize(Math.max(r0i, getSuggestedMinimumWidth()), r10i), resolveSize(Math.max(r1i, getSuggestedMinimumHeight()), r11i));
                    this.b = r11i;
                    this.c = r10i;
                } else {
                    r1i = this.i.top + this.i.bottom;
                    if (r6i == 0) {
                        setMeasuredDimension(resolveSize(Math.max(r0i, getSuggestedMinimumWidth()), r10i), resolveSize(Math.max(r1i, getSuggestedMinimumHeight()), r11i));
                        this.b = r11i;
                        this.c = r10i;
                    } else {
                        r0i = this.i.left + this.i.right;
                        setMeasuredDimension(resolveSize(Math.max(r0i, getSuggestedMinimumWidth()), r10i), resolveSize(Math.max(r1i, getSuggestedMinimumHeight()), r11i));
                        this.b = r11i;
                        this.c = r10i;
                    }
                }
            } else {
                f();
                r1i = getSelectedItemPosition();
                if (r1i < 0 || this.a == null || r1i >= this.a.getCount()) {
                    r2i = 1;
                    r0i = 0;
                    r1i = 0;
                } else {
                    r0_View = this.j.a(r1i);
                    if (r0_View == null) {
                        r0_View = this.a.getView(r1i, null, this);
                    }
                    if (r0_View == null) {
                        if (r0_View == null) {
                            if (r0_View.getLayoutParams() != null) {
                                this.d = true;
                                r0_View.setLayoutParams(generateDefaultLayoutParams());
                                this.d = false;
                            }
                            measureChild(r0_View, r10i, r11i);
                            r1i = a(r0_View) + this.i.top + this.i.bottom;
                            r0i = b(r0_View) + this.i.left + this.i.right;
                            r2i = 0;
                        }
                        r2i = 1;
                        r0i = 0;
                        r1i = 0;
                    } else {
                        this.j.put(r1i, r0_View);
                        if (r0_View == null) {
                            r2i = 1;
                            r0i = 0;
                            r1i = 0;
                        } else if (r0_View.getLayoutParams() != null) {
                            measureChild(r0_View, r10i, r11i);
                            r1i = a(r0_View) + this.i.top + this.i.bottom;
                            r0i = b(r0_View) + this.i.left + this.i.right;
                            r2i = 0;
                        } else {
                            this.d = true;
                            r0_View.setLayoutParams(generateDefaultLayoutParams());
                            this.d = false;
                            measureChild(r0_View, r10i, r11i);
                            r1i = a(r0_View) + this.i.top + this.i.bottom;
                            r0i = b(r0_View) + this.i.left + this.i.right;
                            r2i = 0;
                        }
                    }
                }
                if (r2i != 0) {
                    r1i = this.i.top + this.i.bottom;
                    if (r6i == 0) {
                        r0i = this.i.left + this.i.right;
                    }
                }
                setMeasuredDimension(resolveSize(Math.max(r0i, getSuggestedMinimumWidth()), r10i), resolveSize(Math.max(r1i, getSuggestedMinimumHeight()), r11i));
                this.b = r11i;
                this.c = r10i;
            }
        }
    }

    public void onRestoreInstanceState(Parcelable r6_Parcelable) {
        SavedState r6_SavedState = (SavedState) r6_Parcelable;
        super.onRestoreInstanceState(r6_SavedState.getSuperState());
        if (r6_SavedState.a >= 0) {
            this.v = true;
            this.p = true;
            this.n = r6_SavedState.a;
            this.m = r6_SavedState.b;
            this.q = 0;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        r1_Parcelable.a = getSelectedItemId();
        if (r1_Parcelable.a >= 0) {
            r1_Parcelable.b = getSelectedItemPosition();
        } else {
            r1_Parcelable.b = -1;
        }
        return r1_Parcelable;
    }

    public int pointToPosition(int r5i, int r6i) {
        Rect r0_Rect = this.G;
        if (r0_Rect == null) {
            this.G = new Rect();
            r0_Rect = this.G;
        }
        int r1i = getChildCount() - 1;
        while (r1i >= 0) {
            View r2_View = getChildAt(r1i);
            if (r2_View.getVisibility() == 0) {
                r2_View.getHitRect(r0_Rect);
                if (r0_Rect.contains(r5i, r6i)) {
                    return this.k + r1i;
                }
            }
            r1i--;
        }
        return -1;
    }

    public void requestLayout() {
        if (!this.d) {
            super.requestLayout();
        }
    }

    public void setAdapter(SpinnerAdapter r4_SpinnerAdapter) {
        int r0i = -1;
        if (this.a != null) {
            this.a.unregisterDataSetObserver(this.F);
            a();
        }
        this.a = r4_SpinnerAdapter;
        this.C = -1;
        this.D = -9223372036854775808L;
        if (this.a != null) {
            this.B = this.A;
            this.A = this.a.getCount();
            d();
            this.F = new a(this);
            this.a.registerDataSetObserver(this.F);
            if (this.A > 0) {
                r0i = 0;
            }
            a(r0i);
            b(r0i);
            if (this.A == 0) {
                g();
            }
        } else {
            d();
            a();
            g();
        }
        requestLayout();
    }

    public void setSelection(int r1i) {
        b(r1i);
        requestLayout();
        invalidate();
    }

    public void setSelection(int r3i, boolean r4z) {
        boolean r0z;
        r0z = r4z && this.k <= r3i && r3i <= this.k + getChildCount() - 1;
        a(r3i, r0z);
    }
}