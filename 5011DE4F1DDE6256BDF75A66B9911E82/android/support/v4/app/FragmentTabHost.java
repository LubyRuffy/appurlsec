package android.support.v4.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<b> a;
    private FrameLayout b;
    private Context c;
    private FragmentManager d;
    private int e;
    private OnTabChangeListener f;
    private b g;
    private boolean h;


    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        String a;

        static {
            CREATOR = new t();
        }

        private SavedState(Parcel r2_Parcel) {
            super(r2_Parcel);
            this.a = r2_Parcel.readString();
        }

        SavedState(Parcelable r1_Parcelable) {
            super(r1_Parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.a + "}";
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            super.writeToParcel(r2_Parcel, r3i);
            r2_Parcel.writeString(this.a);
        }
    }

    static class a implements TabContentFactory {
        private final Context a;

        public a(Context r1_Context) {
            this.a = r1_Context;
        }

        public View createTabContent(String r4_String) {
            View r0_View = new View(this.a);
            r0_View.setMinimumWidth(0);
            r0_View.setMinimumHeight(0);
            return r0_View;
        }
    }

    static final class b {
        private final String a;
        private final Class<?> b;
        private final Bundle c;
        private Fragment d;

        b(String r1_String, Class<?> r2_Class_, Bundle r3_Bundle) {
            this.a = r1_String;
            this.b = r2_Class_;
            this.c = r3_Bundle;
        }
    }

    public FragmentTabHost(Context r3_Context) {
        super(r3_Context, null);
        this.a = new ArrayList();
        a(r3_Context, null);
    }

    public FragmentTabHost(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.a = new ArrayList();
        a(r2_Context, r3_AttributeSet);
    }

    private FragmentTransaction a(String r5_String, FragmentTransaction r6_FragmentTransaction) {
        b r1_b = null;
        int r2i = 0;
        while (r2i < this.a.size()) {
            b r0_b = (b) this.a.get(r2i);
            if (r0_b.a.equals(r5_String)) {
                r2i++;
                r1_b = r0_b;
            } else {
                r0_b = r1_b;
                r2i++;
                r1_b = r0_b;
            }
        }
        if (r1_b == null) {
            throw new IllegalStateException("No tab known for tag " + r5_String);
        } else {
            if (this.g != r1_b) {
                if (r6_FragmentTransaction == null) {
                    r6_FragmentTransaction = this.d.beginTransaction();
                }
                if (this.g == null || this.g.d == null) {
                    if (r1_b == null) {
                        if (r1_b.d != null) {
                            r1_b.d = Fragment.instantiate(this.c, r1_b.b.getName(), r1_b.c);
                            r6_FragmentTransaction.add(this.e, r1_b.d, r1_b.a);
                        } else {
                            r6_FragmentTransaction.attach(r1_b.d);
                        }
                    }
                    this.g = r1_b;
                } else {
                    r6_FragmentTransaction.detach(this.g.d);
                    if (r1_b == null) {
                        this.g = r1_b;
                    } else if (r1_b.d != null) {
                        r6_FragmentTransaction.attach(r1_b.d);
                        this.g = r1_b;
                    } else {
                        r1_b.d = Fragment.instantiate(this.c, r1_b.b.getName(), r1_b.c);
                        r6_FragmentTransaction.add(this.e, r1_b.d, r1_b.a);
                        this.g = r1_b;
                    }
                }
            }
            return r6_FragmentTransaction;
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.e);
            }
        }
    }

    private void a(Context r8_Context) {
        int r2i = 16908307;
        if (findViewById(16908307) == null) {
            View r0_View = new LinearLayout(r8_Context);
            r0_View.setOrientation(1);
            addView(r0_View, new LayoutParams(-1, -1));
            View r1_View = new TabWidget(r8_Context);
            r1_View.setId(r2i);
            r1_View.setOrientation(0);
            r0_View.addView(r1_View, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            r1_View = new FrameLayout(r8_Context);
            r1_View.setId(16908305);
            r0_View.addView(r1_View, new LinearLayout.LayoutParams(0, 0, 0.0f));
            r1_View = new FrameLayout(r8_Context);
            this.b = r1_View;
            this.b.setId(this.e);
            r0_View.addView(r1_View, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    private void a(Context r4_Context, AttributeSet r5_AttributeSet) {
        int[] r0_intA = new int[1];
        r0_intA[0] = 16842995;
        TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, r0_intA, 0, 0);
        this.e = r0_TypedArray.getResourceId(0, 0);
        r0_TypedArray.recycle();
        super.setOnTabChangedListener(this);
    }

    public void addTab(TabSpec r4_TabSpec, Class<?> r5_Class_, Bundle r6_Bundle) {
        r4_TabSpec.setContent(new a(this.c));
        String r0_String = r4_TabSpec.getTag();
        b r1_b = new b(r0_String, r5_Class_, r6_Bundle);
        if (this.h) {
            r1_b.d = this.d.findFragmentByTag(r0_String);
            if (r1_b.d == null || r1_b.d.isDetached()) {
                this.a.add(r1_b);
                addTab(r4_TabSpec);
            } else {
                FragmentTransaction r0_FragmentTransaction = this.d.beginTransaction();
                r0_FragmentTransaction.detach(r1_b.d);
                r0_FragmentTransaction.commit();
            }
        }
        this.a.add(r1_b);
        addTab(r4_TabSpec);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String r3_String = getCurrentTabTag();
        FragmentTransaction r1_FragmentTransaction = null;
        int r2i = 0;
        while (r2i < this.a.size()) {
            b r0_b = (b) this.a.get(r2i);
            r0_b.d = this.d.findFragmentByTag(r0_b.a);
            if (r0_b.d == null || r0_b.d.isDetached()) {
                r2i++;
            } else if (r0_b.a.equals(r3_String)) {
                this.g = r0_b;
                r2i++;
            } else {
                if (r1_FragmentTransaction == null) {
                    r1_FragmentTransaction = this.d.beginTransaction();
                }
                r1_FragmentTransaction.detach(r0_b.d);
                r2i++;
            }
        }
        this.h = true;
        FragmentTransaction r0_FragmentTransaction = a(r3_String, r1_FragmentTransaction);
        if (r0_FragmentTransaction != null) {
            r0_FragmentTransaction.commit();
            this.d.executePendingTransactions();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected void onRestoreInstanceState(Parcelable r2_Parcelable) {
        SavedState r2_SavedState = (SavedState) r2_Parcelable;
        super.onRestoreInstanceState(r2_SavedState.getSuperState());
        setCurrentTabByTag(r2_SavedState.a);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable r1_Parcelable = new SavedState(super.onSaveInstanceState());
        r1_Parcelable.a = getCurrentTabTag();
        return r1_Parcelable;
    }

    public void onTabChanged(String r2_String) {
        if (this.h) {
            FragmentTransaction r0_FragmentTransaction = a(r2_String, null);
            if (r0_FragmentTransaction != null) {
                r0_FragmentTransaction.commit();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(r2_String);
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener r1_OnTabChangeListener) {
        this.f = r1_OnTabChangeListener;
    }

    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context r1_Context, FragmentManager r2_FragmentManager) {
        a(r1_Context);
        super.setup();
        this.c = r1_Context;
        this.d = r2_FragmentManager;
        a();
    }

    public void setup(Context r3_Context, FragmentManager r4_FragmentManager, int r5i) {
        a(r3_Context);
        super.setup();
        this.c = r3_Context;
        this.d = r4_FragmentManager;
        this.e = r5i;
        a();
        this.b.setId(r5i);
        if (getId() == -1) {
            setId(16908306);
        }
    }
}