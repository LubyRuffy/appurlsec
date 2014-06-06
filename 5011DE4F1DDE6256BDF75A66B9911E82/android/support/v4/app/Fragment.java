package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment implements ComponentCallbacks, OnCreateContextMenuListener {
    private static final SimpleArrayMap<String, Class<?>> a;
    int A;
    l B;
    FragmentActivity C;
    l D;
    Fragment E;
    int F;
    int G;
    String H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    boolean M;
    boolean N;
    boolean O;
    int P;
    ViewGroup Q;
    View R;
    View S;
    boolean T;
    boolean U;
    w V;
    boolean W;
    boolean X;
    int j;
    View k;
    int l;
    Bundle m;
    SparseArray<Parcelable> n;
    int o;
    String p;
    Bundle q;
    Fragment r;
    int s;
    int t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    boolean z;

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String r1_String, Exception r2_Exception) {
            super(r1_String, r2_Exception);
        }
    }

    public static class SavedState implements Parcelable {
        public static final Creator<android.support.v4.app.Fragment.SavedState> CREATOR;
        final Bundle a;

        static {
            CREATOR = new h();
        }

        SavedState(Bundle r1_Bundle) {
            this.a = r1_Bundle;
        }

        SavedState(Parcel r2_Parcel, ClassLoader r3_ClassLoader) {
            this.a = r2_Parcel.readBundle();
            if (r3_ClassLoader == null || this.a == null) {
            } else {
                this.a.setClassLoader(r3_ClassLoader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel r2_Parcel, int r3i) {
            r2_Parcel.writeBundle(this.a);
        }
    }

    static {
        a = new SimpleArrayMap();
    }

    public Fragment() {
        this.j = 0;
        this.o = -1;
        this.s = -1;
        this.N = true;
        this.U = true;
    }

    static boolean a(Context r2_Context, String r3_String) {
        try {
            Class r0_Class = (Class) a.get(r3_String);
            if (r0_Class == null) {
                r0_Class = r2_Context.getClassLoader().loadClass(r3_String);
                a.put(r3_String, r0_Class);
            }
            return Fragment.class.isAssignableFrom(r0_Class);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Fragment instantiate(Context r1_Context, String r2_String) {
        return instantiate(r1_Context, r2_String, null);
    }

    public static Fragment instantiate(Context r4_Context, String r5_String, Bundle r6_Bundle) {
        try {
            Class r0_Class = (Class) a.get(r5_String);
            if (r0_Class == null) {
                r0_Class = r4_Context.getClassLoader().loadClass(r5_String);
                a.put(r5_String, r0_Class);
            }
            Fragment r0_Fragment = (Fragment) r0_Class.newInstance();
            if (r6_Bundle != null) {
                r6_Bundle.setClassLoader(r0_Fragment.getClass().getClassLoader());
                r0_Fragment.q = r6_Bundle;
            }
            return r0_Fragment;
        } catch (ClassNotFoundException e) {
            throw new InstantiationException("Unable to instantiate fragment " + r5_String + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (InstantiationException e_2) {
            throw new InstantiationException("Unable to instantiate fragment " + r5_String + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e_2);
        } catch (IllegalAccessException e_3) {
            throw new InstantiationException("Unable to instantiate fragment " + r5_String + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e_3);
        }
    }

    Fragment a(String r2_String) {
        if (r2_String.equals(this.p)) {
            return this;
        }
        if (this.D != null) {
            return this.D.findFragmentByWho(r2_String);
        }
        return null;
    }

    View a(LayoutInflater r2_LayoutInflater, ViewGroup r3_ViewGroup, Bundle r4_Bundle) {
        if (this.D != null) {
            this.D.noteStateNotSaved();
        }
        return onCreateView(r2_LayoutInflater, r3_ViewGroup, r4_Bundle);
    }

    final void a(int r3i, Fragment r4_Fragment) {
        this.o = r3i;
        if (r4_Fragment != null) {
            r4_Fragment.p += ":" + this.o;
        } else {
            this.p = "android:fragment:" + this.o;
        }
    }

    void a(Configuration r2_Configuration) {
        onConfigurationChanged(r2_Configuration);
        if (this.D != null) {
            this.D.dispatchConfigurationChanged(r2_Configuration);
        }
    }

    final void a(Bundle r4_Bundle) {
        if (this.n != null) {
            this.S.restoreHierarchyState(this.n);
            this.n = null;
        }
        this.O = false;
        onViewStateRestored(r4_Bundle);
        if (!this.O) {
            throw new ae("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    final boolean a() {
        return this.A > 0;
    }

    boolean a(Menu r3_Menu) {
        boolean r0z = false;
        if (this.I) {
            return false;
        }
        if (this.M && this.N) {
            r0z = true;
            onPrepareOptionsMenu(r3_Menu);
            return this.D == null ? r0z : r0z | this.D.dispatchPrepareOptionsMenu(r3_Menu);
        } else if (this.D == null) {
        }
    }

    boolean a(Menu r3_Menu, MenuInflater r4_MenuInflater) {
        boolean r0z = false;
        if (this.I) {
            return false;
        }
        if (this.M && this.N) {
            r0z = true;
            onCreateOptionsMenu(r3_Menu, r4_MenuInflater);
            return this.D == null ? r0z : r0z | this.D.dispatchCreateOptionsMenu(r3_Menu, r4_MenuInflater);
        } else if (this.D == null) {
        }
    }

    boolean a(MenuItem r3_MenuItem) {
        if (!this.I) {
            if (this.M && this.N && onOptionsItemSelected(r3_MenuItem)) {
                return true;
            }
            if (this.D != null && this.D.dispatchOptionsItemSelected(r3_MenuItem)) {
                return true;
            }
        }
        return false;
    }

    void b() {
        this.o = -1;
        this.p = null;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = null;
        this.C = null;
        this.F = 0;
        this.G = 0;
        this.H = null;
        this.I = false;
        this.J = false;
        this.L = false;
        this.V = null;
        this.W = false;
        this.X = false;
    }

    void b(Bundle r4_Bundle) {
        if (this.D != null) {
            this.D.noteStateNotSaved();
        }
        this.O = false;
        onCreate(r4_Bundle);
        if (this.O) {
            if (r4_Bundle != null) {
                Parcelable r0_Parcelable = r4_Bundle.getParcelable("android:support:fragments");
                if (r0_Parcelable != null) {
                    if (this.D == null) {
                        c();
                    }
                    this.D.a(r0_Parcelable, null);
                    this.D.dispatchCreate();
                }
            }
        } else {
            throw new ae("Fragment " + this + " did not call through to super.onCreate()");
        }
    }

    void b(Menu r2_Menu) {
        if (!this.I) {
            if (this.M && this.N) {
                onOptionsMenuClosed(r2_Menu);
                if (this.D == null) {
                } else {
                    this.D.dispatchOptionsMenuClosed(r2_Menu);
                }
            } else if (this.D == null) {
                this.D.dispatchOptionsMenuClosed(r2_Menu);
            }
        }
    }

    boolean b(MenuItem r3_MenuItem) {
        if (!this.I) {
            if (onContextItemSelected(r3_MenuItem)) {
                return true;
            }
            if (this.D != null && this.D.dispatchContextItemSelected(r3_MenuItem)) {
                return true;
            }
        }
        return false;
    }

    void c() {
        this.D = new l();
        this.D.attachActivity(this.C, new g(this), this);
    }

    void c(Bundle r4_Bundle) {
        if (this.D != null) {
            this.D.noteStateNotSaved();
        }
        this.O = false;
        onActivityCreated(r4_Bundle);
        if (this.O) {
            if (this.D != null) {
                this.D.dispatchActivityCreated();
            }
        } else {
            throw new ae("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    void d() {
        if (this.D != null) {
            this.D.noteStateNotSaved();
            this.D.execPendingActions();
        }
        this.O = false;
        onStart();
        if (this.O) {
            if (this.D != null) {
                this.D.dispatchStart();
            }
            if (this.V != null) {
                this.V.f();
            }
        } else {
            throw new ae("Fragment " + this + " did not call through to super.onStart()");
        }
    }

    void d(Bundle r3_Bundle) {
        onSaveInstanceState(r3_Bundle);
        if (this.D != null) {
            Parcelable r0_Parcelable = this.D.d();
            if (r0_Parcelable != null) {
                r3_Bundle.putParcelable("android:support:fragments", r0_Parcelable);
            }
        }
    }

    public void dump(String r4_String, FileDescriptor r5_FileDescriptor, PrintWriter r6_PrintWriter, String[] r7_StringA) {
        r6_PrintWriter.print(r4_String);
        r6_PrintWriter.print("mFragmentId=#");
        r6_PrintWriter.print(Integer.toHexString(this.F));
        r6_PrintWriter.print(" mContainerId=#");
        r6_PrintWriter.print(Integer.toHexString(this.G));
        r6_PrintWriter.print(" mTag=");
        r6_PrintWriter.println(this.H);
        r6_PrintWriter.print(r4_String);
        r6_PrintWriter.print("mState=");
        r6_PrintWriter.print(this.j);
        r6_PrintWriter.print(" mIndex=");
        r6_PrintWriter.print(this.o);
        r6_PrintWriter.print(" mWho=");
        r6_PrintWriter.print(this.p);
        r6_PrintWriter.print(" mBackStackNesting=");
        r6_PrintWriter.println(this.A);
        r6_PrintWriter.print(r4_String);
        r6_PrintWriter.print("mAdded=");
        r6_PrintWriter.print(this.u);
        r6_PrintWriter.print(" mRemoving=");
        r6_PrintWriter.print(this.v);
        r6_PrintWriter.print(" mResumed=");
        r6_PrintWriter.print(this.w);
        r6_PrintWriter.print(" mFromLayout=");
        r6_PrintWriter.print(this.x);
        r6_PrintWriter.print(" mInLayout=");
        r6_PrintWriter.println(this.y);
        r6_PrintWriter.print(r4_String);
        r6_PrintWriter.print("mHidden=");
        r6_PrintWriter.print(this.I);
        r6_PrintWriter.print(" mDetached=");
        r6_PrintWriter.print(this.J);
        r6_PrintWriter.print(" mMenuVisible=");
        r6_PrintWriter.print(this.N);
        r6_PrintWriter.print(" mHasMenu=");
        r6_PrintWriter.println(this.M);
        r6_PrintWriter.print(r4_String);
        r6_PrintWriter.print("mRetainInstance=");
        r6_PrintWriter.print(this.K);
        r6_PrintWriter.print(" mRetaining=");
        r6_PrintWriter.print(this.L);
        r6_PrintWriter.print(" mUserVisibleHint=");
        r6_PrintWriter.println(this.U);
        if (this.B != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mFragmentManager=");
            r6_PrintWriter.println(this.B);
        }
        if (this.C != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mActivity=");
            r6_PrintWriter.println(this.C);
        }
        if (this.E != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mParentFragment=");
            r6_PrintWriter.println(this.E);
        }
        if (this.q != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mArguments=");
            r6_PrintWriter.println(this.q);
        }
        if (this.m != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mSavedFragmentState=");
            r6_PrintWriter.println(this.m);
        }
        if (this.n != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mSavedViewState=");
            r6_PrintWriter.println(this.n);
        }
        if (this.r != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mTarget=");
            r6_PrintWriter.print(this.r);
            r6_PrintWriter.print(" mTargetRequestCode=");
            r6_PrintWriter.println(this.t);
        }
        if (this.P != 0) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mNextAnim=");
            r6_PrintWriter.println(this.P);
        }
        if (this.Q != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mContainer=");
            r6_PrintWriter.println(this.Q);
        }
        if (this.R != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mView=");
            r6_PrintWriter.println(this.R);
        }
        if (this.S != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mInnerView=");
            r6_PrintWriter.println(this.R);
        }
        if (this.k != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mAnimatingAway=");
            r6_PrintWriter.println(this.k);
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("mStateAfterAnimating=");
            r6_PrintWriter.println(this.l);
        }
        if (this.V != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.println("Loader Manager:");
            this.V.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
        }
        if (this.D != null) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.println("Child " + this.D + ":");
            this.D.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
        }
    }

    void e() {
        if (this.D != null) {
            this.D.noteStateNotSaved();
            this.D.execPendingActions();
        }
        this.O = false;
        onResume();
        if (this.O) {
            if (this.D != null) {
                this.D.dispatchResume();
                this.D.execPendingActions();
            }
        } else {
            throw new ae("Fragment " + this + " did not call through to super.onResume()");
        }
    }

    public final boolean equals(Object r2_Object) {
        return super.equals(r2_Object);
    }

    void f() {
        onLowMemory();
        if (this.D != null) {
            this.D.dispatchLowMemory();
        }
    }

    void g() {
        if (this.D != null) {
            this.D.dispatchPause();
        }
        this.O = false;
        onPause();
        if (!this.O) {
            throw new ae("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    public final FragmentActivity getActivity() {
        return this.C;
    }

    public final Bundle getArguments() {
        return this.q;
    }

    public final FragmentManager getChildFragmentManager() {
        if (this.D == null) {
            c();
            if (this.j >= 5) {
                this.D.dispatchResume();
            } else if (this.j >= 4) {
                this.D.dispatchStart();
            } else if (this.j >= 2) {
                this.D.dispatchActivityCreated();
            } else if (this.j >= 1) {
                this.D.dispatchCreate();
            }
        }
        return this.D;
    }

    public final FragmentManager getFragmentManager() {
        return this.B;
    }

    public final int getId() {
        return this.F;
    }

    public LayoutInflater getLayoutInflater(Bundle r2_Bundle) {
        return this.C.getLayoutInflater();
    }

    public LoaderManager getLoaderManager() {
        if (this.V != null) {
            return this.V;
        }
        if (this.C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        } else {
            this.X = true;
            this.V = this.C.a(this.p, this.W, true);
            return this.V;
        }
    }

    public final Fragment getParentFragment() {
        return this.E;
    }

    public final Resources getResources() {
        if (this.C != null) {
            return this.C.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final boolean getRetainInstance() {
        return this.K;
    }

    public final String getString(int r2i) {
        return getResources().getString(r2i);
    }

    public final String getString(int r2i, Object ... r3_ObjectA) {
        return getResources().getString(r2i, r3_ObjectA);
    }

    public final String getTag() {
        return this.H;
    }

    public final Fragment getTargetFragment() {
        return this.r;
    }

    public final int getTargetRequestCode() {
        return this.t;
    }

    public final CharSequence getText(int r2i) {
        return getResources().getText(r2i);
    }

    public boolean getUserVisibleHint() {
        return this.U;
    }

    public View getView() {
        return this.R;
    }

    void h() {
        if (this.D != null) {
            this.D.dispatchStop();
        }
        this.O = false;
        onStop();
        if (!this.O) {
            throw new ae("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    public final boolean hasOptionsMenu() {
        return this.M;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void i() {
        if (this.D != null) {
            this.D.dispatchReallyStop();
        }
        if (this.W) {
            this.W = false;
            if (!this.X) {
                this.X = true;
                this.V = this.C.a(this.p, this.W, false);
            }
            if (this.V != null) {
                if (this.C.h) {
                    this.V.c();
                } else {
                    this.V.b();
                }
            }
        }
    }

    public final boolean isAdded() {
        return this.C != null && this.u;
    }

    public final boolean isDetached() {
        return this.J;
    }

    public final boolean isHidden() {
        return this.I;
    }

    public final boolean isInLayout() {
        return this.y;
    }

    public final boolean isMenuVisible() {
        return this.N;
    }

    public final boolean isRemoving() {
        return this.v;
    }

    public final boolean isResumed() {
        return this.w;
    }

    public final boolean isVisible() {
        return isAdded() && !isHidden() && this.R != null && this.R.getWindowToken() != null && this.R.getVisibility() == 0;
    }

    void j() {
        if (this.D != null) {
            this.D.dispatchDestroyView();
        }
        this.O = false;
        onDestroyView();
        if (this.O) {
            if (this.V != null) {
                this.V.e();
            }
        } else {
            throw new ae("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void k() {
        if (this.D != null) {
            this.D.dispatchDestroy();
        }
        this.O = false;
        onDestroy();
        if (!this.O) {
            throw new ae("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }

    public void onActivityCreated(Bundle r2_Bundle) {
        this.O = true;
    }

    public void onActivityResult(int r1i, int r2i, Intent r3_Intent) {
    }

    public void onAttach(Activity r2_Activity) {
        this.O = true;
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        this.O = true;
    }

    public boolean onContextItemSelected(MenuItem r2_MenuItem) {
        return false;
    }

    public void onCreate(Bundle r2_Bundle) {
        this.O = true;
    }

    public Animation onCreateAnimation(int r2i, boolean r3z, int r4i) {
        return null;
    }

    public void onCreateContextMenu(ContextMenu r2_ContextMenu, View r3_View, ContextMenuInfo r4_ContextMenuInfo) {
        getActivity().onCreateContextMenu(r2_ContextMenu, r3_View, r4_ContextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu r1_Menu, MenuInflater r2_MenuInflater) {
    }

    public View onCreateView(LayoutInflater r2_LayoutInflater, ViewGroup r3_ViewGroup, Bundle r4_Bundle) {
        return null;
    }

    public void onDestroy() {
        boolean r1z = true;
        this.O = true;
        if (!this.X) {
            this.X = r1z;
            this.V = this.C.a(this.p, this.W, false);
        }
        if (this.V != null) {
            this.V.g();
        }
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.O = true;
    }

    public void onDetach() {
        this.O = true;
    }

    public void onHiddenChanged(boolean r1z) {
    }

    public void onInflate(Activity r2_Activity, AttributeSet r3_AttributeSet, Bundle r4_Bundle) {
        this.O = true;
    }

    public void onLowMemory() {
        this.O = true;
    }

    public boolean onOptionsItemSelected(MenuItem r2_MenuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu r1_Menu) {
    }

    public void onPause() {
        this.O = true;
    }

    public void onPrepareOptionsMenu(Menu r1_Menu) {
    }

    public void onResume() {
        this.O = true;
    }

    public void onSaveInstanceState(Bundle r1_Bundle) {
    }

    public void onStart() {
        boolean r1z = true;
        this.O = true;
        if (!this.W) {
            this.W = true;
            if (!this.X) {
                this.X = r1z;
                this.V = this.C.a(this.p, this.W, false);
            }
            if (this.V != null) {
                this.V.a();
            }
        }
    }

    public void onStop() {
        this.O = true;
    }

    public void onViewCreated(View r1_View, Bundle r2_Bundle) {
    }

    public void onViewStateRestored(Bundle r2_Bundle) {
        this.O = true;
    }

    public void registerForContextMenu(View r1_View) {
        r1_View.setOnCreateContextMenuListener(this);
    }

    public void setArguments(Bundle r3_Bundle) {
        if (this.o >= 0) {
            throw new IllegalStateException("Fragment already active");
        } else {
            this.q = r3_Bundle;
        }
    }

    public void setHasOptionsMenu(boolean r2z) {
        if (this.M != r2z) {
            this.M = r2z;
            if ((!isAdded()) || isHidden()) {
            } else {
                this.C.supportInvalidateOptionsMenu();
            }
        }
    }

    public void setInitialSavedState(SavedState r3_SavedState) {
        if (this.o >= 0) {
            throw new IllegalStateException("Fragment already active");
        } else {
            Bundle r0_Bundle;
            r0_Bundle = (r3_SavedState == null || r3_SavedState.a == null) ? null : r3_SavedState.a;
            this.m = r0_Bundle;
        }
    }

    public void setMenuVisibility(boolean r2z) {
        if (this.N != r2z) {
            this.N = r2z;
            if (this.M && isAdded() && !isHidden()) {
                this.C.supportInvalidateOptionsMenu();
            }
        }
    }

    public void setRetainInstance(boolean r3z) {
        if ((!r3z) || this.E == null) {
            this.K = r3z;
        } else {
            throw new IllegalStateException("Can't retain fragements that are nested in other fragments");
        }
    }

    public void setTargetFragment(Fragment r1_Fragment, int r2i) {
        this.r = r1_Fragment;
        this.t = r2i;
    }

    public void setUserVisibleHint(boolean r3z) {
        if (this.U || (!r3z) || this.j >= 4) {
            this.U = r3z;
            this.T = r3z;
        } else {
            this.B.performPendingDeferredStart(this);
            this.U = r3z;
            if (r3z) {
            }
            this.T = r3z;
        }
    }

    public void startActivity(Intent r4_Intent) {
        if (this.C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        } else {
            this.C.startActivityFromFragment(this, r4_Intent, -1);
        }
    }

    public void startActivityForResult(Intent r4_Intent, int r5i) {
        if (this.C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        } else {
            this.C.startActivityFromFragment(this, r4_Intent, r5i);
        }
    }

    public String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, r0_StringBuilder);
        if (this.o >= 0) {
            r0_StringBuilder.append(" #");
            r0_StringBuilder.append(this.o);
        }
        if (this.F != 0) {
            r0_StringBuilder.append(" id=0x");
            r0_StringBuilder.append(Integer.toHexString(this.F));
        }
        if (this.H != null) {
            r0_StringBuilder.append(" ");
            r0_StringBuilder.append(this.H);
        }
        r0_StringBuilder.append('}');
        return r0_StringBuilder.toString();
    }

    public void unregisterForContextMenu(View r2_View) {
        r2_View.setOnCreateContextMenuListener(null);
    }
}