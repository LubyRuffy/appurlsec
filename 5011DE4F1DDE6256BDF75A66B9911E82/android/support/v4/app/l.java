package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import qsbk.app.utils.Base64;

// compiled from: FragmentManager.java
final class l extends FragmentManager {
    static final Interpolator A;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static final Interpolator B;
    static final Interpolator C;
    static boolean a;
    static final boolean b;
    static final Interpolator z;
    ArrayList<Runnable> c;
    Runnable[] d;
    boolean e;
    ArrayList<Fragment> f;
    ArrayList<Fragment> g;
    ArrayList<Integer> h;
    ArrayList<e> i;
    ArrayList<Fragment> j;
    ArrayList<e> k;
    ArrayList<Integer> l;
    ArrayList<OnBackStackChangedListener> m;
    int n;
    FragmentActivity o;
    k p;
    Fragment q;
    boolean r;
    boolean s;
    boolean t;
    String u;
    boolean v;
    Bundle w;
    SparseArray<Parcelable> x;
    Runnable y;

    static {
        boolean r0z = false;
        a = false;
        if (VERSION.SDK_INT >= 11) {
            r0z = true;
        }
        b = r0z;
        z = new DecelerateInterpolator(2.5f);
        A = new DecelerateInterpolator(1.5f);
        B = new AccelerateInterpolator(2.5f);
        C = new AccelerateInterpolator(1.5f);
    }

    l() {
        this.n = 0;
        this.w = null;
        this.x = null;
        this.y = new m(this);
    }

    static Animation a(Context r3_Context, float r4f, float r5f) {
        Animation r0_Animation = new AlphaAnimation(r4f, r5f);
        r0_Animation.setInterpolator(A);
        r0_Animation.setDuration(220);
        return r0_Animation;
    }

    static Animation a(Context r10_Context, float r11f, float r12f, float r13f, float r14f) {
        Animation r9_Animation = new AnimationSet(false);
        Animation r0_Animation = new ScaleAnimation(r11f, r12f, r11f, r12f, 1, 0.5f, 1, 0.5f);
        r0_Animation.setInterpolator(z);
        r0_Animation.setDuration(220);
        r9_Animation.addAnimation(r0_Animation);
        r0_Animation = new AlphaAnimation(r13f, r14f);
        r0_Animation.setInterpolator(A);
        r0_Animation.setDuration(220);
        r9_Animation.addAnimation(r0_Animation);
        return r9_Animation;
    }

    private void a(RuntimeException r6_RuntimeException) {
        Log.e("FragmentManager", r6_RuntimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter r1_PrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
        if (this.o != null) {
            try {
                this.o.dump("  ", null, r1_PrintWriter, new String[0]);
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", null, r1_PrintWriter, new String[0]);
            } catch (Exception e_2) {
                Log.e("FragmentManager", "Failed dumping state", e_2);
            }
        }
        throw r6_RuntimeException;
    }

    private void e() {
        if (this.s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.u);
        }
    }

    public static int reverseTransit(int r1i) {
        switch (r1i) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                return FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return 0;
    }

    public static int transitToStyleIndex(int r1i, boolean r2z) {
        switch (r1i) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
                return r2z ? ANIM_STYLE_OPEN_ENTER : ANIM_STYLE_OPEN_EXIT;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                return r2z ? ANIM_STYLE_FADE_ENTER : ANIM_STYLE_FADE_EXIT;
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                return r2z ? ANIM_STYLE_CLOSE_ENTER : ANIM_STYLE_CLOSE_EXIT;
        }
        return -1;
    }

    Animation a(Fragment r7_Fragment, int r8i, boolean r9z, int r10i) {
        Animation r0_Animation = r7_Fragment.onCreateAnimation(r8i, r9z, r7_Fragment.P);
        if (r0_Animation != null) {
            return r0_Animation;
        }
        int r0i;
        if (r7_Fragment.P != 0) {
            r0_Animation = AnimationUtils.loadAnimation(this.o, r7_Fragment.P);
            if (r0_Animation != null) {
                return r0_Animation;
            }
            if (r8i == 0) {
                return null;
            }
            r0i = transitToStyleIndex(r8i, r9z);
            if (r0i < 0) {
                return null;
            }
            switch (r0i) {
                case ANIM_STYLE_OPEN_ENTER:
                    return a(this.o, 1.125f, 1.0f, 0.0f, 1.0f);
                case ANIM_STYLE_OPEN_EXIT:
                    return a(this.o, 1.0f, 0.975f, 1.0f, 0.0f);
                case ANIM_STYLE_CLOSE_ENTER:
                    return a(this.o, 0.975f, 1.0f, 0.0f, 1.0f);
                case ANIM_STYLE_CLOSE_EXIT:
                    return a(this.o, 1.0f, 1.075f, 1.0f, 0.0f);
                case ANIM_STYLE_FADE_ENTER:
                    return a(this.o, 0.0f, 1.0f);
                case ANIM_STYLE_FADE_EXIT:
                    return a(this.o, 1.0f, 0.0f);
            }
            if (r10i != 0 || this.o.getWindow() == null) {
                if (r10i != 0) {
                    return null;
                }
            } else {
                r10i = this.o.getWindow().getAttributes().windowAnimations;
                if (r10i != 0) {
                    return null;
                }
            }
            return null;
        }
        if (r8i == 0) {
            return null;
        }
        r0i = transitToStyleIndex(r8i, r9z);
        if (r0i < 0) {
            return null;
        }
        switch (r0i) {
            case ANIM_STYLE_OPEN_ENTER:
                return a(this.o, 1.125f, 1.0f, 0.0f, 1.0f);
            case ANIM_STYLE_OPEN_EXIT:
                return a(this.o, 1.0f, 0.975f, 1.0f, 0.0f);
            case ANIM_STYLE_CLOSE_ENTER:
                return a(this.o, 0.975f, 1.0f, 0.0f, 1.0f);
            case ANIM_STYLE_CLOSE_EXIT:
                return a(this.o, 1.0f, 1.075f, 1.0f, 0.0f);
            case ANIM_STYLE_FADE_ENTER:
                return a(this.o, 0.0f, 1.0f);
            case ANIM_STYLE_FADE_EXIT:
                return a(this.o, 1.0f, 0.0f);
        }
        if (r10i != 0 || this.o.getWindow() == null) {
            if (r10i != 0) {
                return null;
            }
        } else {
            r10i = this.o.getWindow().getAttributes().windowAnimations;
            if (r10i != 0) {
                return null;
            }
        }
        return null;
    }

    void a() {
        if (this.f == null) {
        } else {
            int r1i = 0;
            while (r1i < this.f.size()) {
                Fragment r0_Fragment = (Fragment) this.f.get(r1i);
                if (r0_Fragment != null) {
                    performPendingDeferredStart(r0_Fragment);
                }
                r1i++;
            }
        }
    }

    void a(int r9i, int r10i, int r11i, boolean r12z) {
        if (this.o != null || r9i == 0) {
            if (r12z || this.n != r9i) {
                this.n = r9i;
                if (this.f != null) {
                    int r6i = 0;
                    int r7i = 0;
                    while (r6i < this.f.size()) {
                        int r1i;
                        Fragment r1_Fragment = (Fragment) this.f.get(r6i);
                        if (r1_Fragment != null) {
                            a(r1_Fragment, r9i, r10i, r11i, false);
                            if (r1_Fragment.V != null) {
                                r1i = r7i | r1_Fragment.V.hasRunningLoaders();
                            }
                            r1i = r7i;
                        } else {
                            r1i = r7i;
                        }
                        r6i++;
                        r7i = r1i;
                    }
                    if (r7i == 0) {
                        a();
                    }
                    if (this.r && this.o != null && this.n == 5) {
                        this.o.supportInvalidateOptionsMenu();
                        this.r = false;
                    }
                }
            }
        } else {
            throw new IllegalStateException("No activity");
        }
    }

    void a(int r2i, boolean r3z) {
        a(r2i, 0, 0, r3z);
    }

    void a(Parcelable r9_Parcelable, ArrayList<Fragment> r10_ArrayList_Fragment) {
        if (r9_Parcelable == null) {
        } else {
            FragmentManagerState r9_FragmentManagerState = (FragmentManagerState) r9_Parcelable;
            if (r9_FragmentManagerState.a != null) {
                int r1i;
                Fragment r0_Fragment;
                if (r10_ArrayList_Fragment != null) {
                    r1i = 0;
                    while (r1i < r10_ArrayList_Fragment.size()) {
                        r0_Fragment = (Fragment) r10_ArrayList_Fragment.get(r1i);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + r0_Fragment);
                        }
                        FragmentState r3_FragmentState = r9_FragmentManagerState.a[r0_Fragment.o];
                        r3_FragmentState.k = r0_Fragment;
                        r0_Fragment.n = null;
                        r0_Fragment.A = 0;
                        r0_Fragment.y = false;
                        r0_Fragment.u = false;
                        r0_Fragment.r = null;
                        if (r3_FragmentState.j != null) {
                            r3_FragmentState.j.setClassLoader(this.o.getClassLoader());
                            r0_Fragment.n = r3_FragmentState.j.getSparseParcelableArray("android:view_state");
                        }
                        r1i++;
                    }
                }
                this.f = new ArrayList(r9_FragmentManagerState.a.length);
                if (this.h != null) {
                    this.h.clear();
                }
                int r0i = 0;
                while (r0i < r9_FragmentManagerState.a.length) {
                    FragmentState r1_FragmentState = r9_FragmentManagerState.a[r0i];
                    if (r1_FragmentState != null) {
                        Fragment r3_Fragment = r1_FragmentState.instantiate(this.o, this.q);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + r0i + ": " + r3_Fragment);
                        }
                        this.f.add(r3_Fragment);
                        r1_FragmentState.k = null;
                    } else {
                        this.f.add(null);
                        if (this.h == null) {
                            this.h = new ArrayList();
                        }
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + r0i);
                        }
                        this.h.add(Integer.valueOf(r0i));
                    }
                    r0i++;
                }
                if (r10_ArrayList_Fragment != null) {
                    int r3i = 0;
                    while (r3i < r10_ArrayList_Fragment.size()) {
                        r0_Fragment = (Fragment) r10_ArrayList_Fragment.get(r3i);
                        if (r0_Fragment.s >= 0) {
                            if (r0_Fragment.s < this.f.size()) {
                                r0_Fragment.r = (Fragment) this.f.get(r0_Fragment.s);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + r0_Fragment + " target no longer exists: " + r0_Fragment.s);
                                r0_Fragment.r = null;
                            }
                        }
                        r3i++;
                    }
                }
                if (r9_FragmentManagerState.b != null) {
                    this.g = new ArrayList(r9_FragmentManagerState.b.length);
                    r1i = 0;
                    while (r1i < r9_FragmentManagerState.b.length) {
                        r0_Fragment = (Fragment) this.f.get(r9_FragmentManagerState.b[r1i]);
                        if (r0_Fragment == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + r9_FragmentManagerState.b[r1i]));
                        }
                        r0_Fragment.u = true;
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + r1i + ": " + r0_Fragment);
                        }
                        if (this.g.contains(r0_Fragment)) {
                            throw new IllegalStateException("Already added!");
                        } else {
                            this.g.add(r0_Fragment);
                            r1i++;
                        }
                    }
                } else {
                    this.g = null;
                }
                if (r9_FragmentManagerState.c != null) {
                    this.i = new ArrayList(r9_FragmentManagerState.c.length);
                    r0i = 0;
                    while (r0i < r9_FragmentManagerState.c.length) {
                        e r1_e = r9_FragmentManagerState.c[r0i].instantiate(this);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + r0i + " (index " + r1_e.o + "): " + r1_e);
                            r1_e.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                        }
                        this.i.add(r1_e);
                        if (r1_e.o >= 0) {
                            setBackStackIndex(r1_e.o, r1_e);
                        }
                        r0i++;
                    }
                } else {
                    this.i = null;
                }
            }
        }
    }

    void a(Fragment r7_Fragment) {
        a(r7_Fragment, this.n, 0, 0, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(Fragment r10_Fragment, int r11i, int r12i, int r13i, boolean r14z) {
        /*
        r9_this = this;
        r8 = 4;
        r6 = 3;
        r3 = 0;
        r5 = 1;
        r7 = 0;
        r0 = r10.u;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r10.J;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r11 <= r5) goto L_0x0010;
    L_0x000f:
        r11 = r5;
    L_0x0010:
        r0 = r10.v;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r10.j;
        if (r11 <= r0) goto L_0x001a;
    L_0x0018:
        r11 = r10.j;
    L_0x001a:
        r0 = r10.T;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r10.j;
        if (r0 >= r8) goto L_0x0025;
    L_0x0022:
        if (r11 <= r6) goto L_0x0025;
    L_0x0024:
        r11 = r6;
    L_0x0025:
        r0 = r10.j;
        if (r0 >= r11) goto L_0x0240;
    L_0x0029:
        r0 = r10.x;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r10.y;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r10.k;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r10.k = r7;
        r2 = r10.l;
        r0 = r9;
        r1 = r10;
        r4 = r3;
        r0.a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r10.j;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0126;
            case 2: goto L_0x01ef;
            case 3: goto L_0x01ef;
            case 4: goto L_0x0210;
            default: goto L_0x0045;
        };
    L_0x0045:
        r10.j = r11;
        goto L_0x0031;
    L_0x0048:
        r0 = a;
        if (r0 == 0) goto L_0x0064;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0064:
        r0 = r10.m;
        if (r0 == 0) goto L_0x009d;
    L_0x0068:
        r0 = r10.m;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r10.n = r0;
        r0 = r10.m;
        r1 = "android:target_state";
        r0 = r9.getFragment(r0, r1);
        r10.r = r0;
        r0 = r10.r;
        if (r0 == 0) goto L_0x008a;
    L_0x0080:
        r0 = r10.m;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r10.t = r0;
    L_0x008a:
        r0 = r10.m;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r10.U = r0;
        r0 = r10.U;
        if (r0 != 0) goto L_0x009d;
    L_0x0098:
        r10.T = r5;
        if (r11 <= r6) goto L_0x009d;
    L_0x009c:
        r11 = r6;
    L_0x009d:
        r0 = r9.o;
        r10.C = r0;
        r0 = r9.q;
        r10.E = r0;
        r0 = r9.q;
        if (r0 == 0) goto L_0x00d9;
    L_0x00a9:
        r0 = r9.q;
        r0 = r0.D;
    L_0x00ad:
        r10.B = r0;
        r10.O = r3;
        r0 = r9.o;
        r10.onAttach(r0);
        r0 = r10.O;
        if (r0 != 0) goto L_0x00de;
    L_0x00ba:
        r0 = new android.support.v4.app.ae;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00d9:
        r0 = r9.o;
        r0 = r0.b;
        goto L_0x00ad;
    L_0x00de:
        r0 = r10.E;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e2:
        r0 = r9.o;
        r0.onAttachFragment(r10);
    L_0x00e7:
        r0 = r10.L;
        if (r0 != 0) goto L_0x00f0;
    L_0x00eb:
        r0 = r10.m;
        r10.b(r0);
    L_0x00f0:
        r10.L = r3;
        r0 = r10.x;
        if (r0 == 0) goto L_0x0126;
    L_0x00f6:
        r0 = r10.m;
        r0 = r10.getLayoutInflater(r0);
        r1 = r10.m;
        r0 = r10.a(r0, r7, r1);
        r10.R = r0;
        r0 = r10.R;
        if (r0 == 0) goto L_0x0239;
    L_0x0108:
        r0 = r10.R;
        r10.S = r0;
        r0 = r10.R;
        r0 = android.support.v4.app.y.a(r0);
        r10.R = r0;
        r0 = r10.I;
        if (r0 == 0) goto L_0x011f;
    L_0x0118:
        r0 = r10.R;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x011f:
        r0 = r10.R;
        r1 = r10.m;
        r10.onViewCreated(r0, r1);
    L_0x0126:
        if (r11 <= r5) goto L_0x01ef;
    L_0x0128:
        r0 = a;
        if (r0 == 0) goto L_0x0144;
    L_0x012c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0144:
        r0 = r10.x;
        if (r0 != 0) goto L_0x01df;
    L_0x0148:
        r0 = r10.G;
        if (r0 == 0) goto L_0x0397;
    L_0x014c:
        r0 = r9.p;
        r1 = r10.G;
        r0 = r0.findViewById(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x019b;
    L_0x0158:
        r1 = r10.z;
        if (r1 != 0) goto L_0x019b;
    L_0x015c:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No view found for id 0x";
        r2 = r2.append(r3);
        r3 = r10.G;
        r3 = java.lang.Integer.toHexString(r3);
        r2 = r2.append(r3);
        r3 = " (";
        r2 = r2.append(r3);
        r3 = r10.getResources();
        r4 = r10.G;
        r3 = r3.getResourceName(r4);
        r2 = r2.append(r3);
        r3 = ") for fragment ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        r1.<init>(r2);
        r9.a(r1);
    L_0x019b:
        r10.Q = r0;
        r1 = r10.m;
        r1 = r10.getLayoutInflater(r1);
        r2 = r10.m;
        r1 = r10.a(r1, r0, r2);
        r10.R = r1;
        r1 = r10.R;
        if (r1 == 0) goto L_0x023d;
    L_0x01af:
        r1 = r10.R;
        r10.S = r1;
        r1 = r10.R;
        r1 = android.support.v4.app.y.a(r1);
        r10.R = r1;
        if (r0 == 0) goto L_0x01cd;
    L_0x01bd:
        r1 = r9.a(r10, r12, r5, r13);
        if (r1 == 0) goto L_0x01c8;
    L_0x01c3:
        r2 = r10.R;
        r2.startAnimation(r1);
    L_0x01c8:
        r1 = r10.R;
        r0.addView(r1);
    L_0x01cd:
        r0 = r10.I;
        if (r0 == 0) goto L_0x01d8;
    L_0x01d1:
        r0 = r10.R;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01d8:
        r0 = r10.R;
        r1 = r10.m;
        r10.onViewCreated(r0, r1);
    L_0x01df:
        r0 = r10.m;
        r10.c(r0);
        r0 = r10.R;
        if (r0 == 0) goto L_0x01ed;
    L_0x01e8:
        r0 = r10.m;
        r10.a(r0);
    L_0x01ed:
        r10.m = r7;
    L_0x01ef:
        if (r11 <= r6) goto L_0x0210;
    L_0x01f1:
        r0 = a;
        if (r0 == 0) goto L_0x020d;
    L_0x01f5:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x020d:
        r10.d();
    L_0x0210:
        if (r11 <= r8) goto L_0x0045;
    L_0x0212:
        r0 = a;
        if (r0 == 0) goto L_0x022e;
    L_0x0216:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x022e:
        r10.w = r5;
        r10.e();
        r10.m = r7;
        r10.n = r7;
        goto L_0x0045;
    L_0x0239:
        r10.S = r7;
        goto L_0x0126;
    L_0x023d:
        r10.S = r7;
        goto L_0x01df;
    L_0x0240:
        r0 = r10.j;
        if (r0 <= r11) goto L_0x0045;
    L_0x0244:
        r0 = r10.j;
        switch(r0) {
            case 1: goto L_0x024b;
            case 2: goto L_0x02cb;
            case 3: goto L_0x02aa;
            case 4: goto L_0x0289;
            case 5: goto L_0x0265;
            default: goto L_0x0249;
        };
    L_0x0249:
        goto L_0x0045;
    L_0x024b:
        if (r11 >= r5) goto L_0x0045;
    L_0x024d:
        r0 = r9.t;
        if (r0 == 0) goto L_0x025c;
    L_0x0251:
        r0 = r10.k;
        if (r0 == 0) goto L_0x025c;
    L_0x0255:
        r0 = r10.k;
        r10.k = r7;
        r0.clearAnimation();
    L_0x025c:
        r0 = r10.k;
        if (r0 == 0) goto L_0x0338;
    L_0x0260:
        r10.l = r11;
        r11 = r5;
        goto L_0x0045;
    L_0x0265:
        r0 = 5;
        if (r11 >= r0) goto L_0x0289;
    L_0x0268:
        r0 = a;
        if (r0 == 0) goto L_0x0284;
    L_0x026c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0284:
        r10.g();
        r10.w = r3;
    L_0x0289:
        if (r11 >= r8) goto L_0x02aa;
    L_0x028b:
        r0 = a;
        if (r0 == 0) goto L_0x02a7;
    L_0x028f:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02a7:
        r10.h();
    L_0x02aa:
        if (r11 >= r6) goto L_0x02cb;
    L_0x02ac:
        r0 = a;
        if (r0 == 0) goto L_0x02c8;
    L_0x02b0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02c8:
        r10.i();
    L_0x02cb:
        r0 = 2;
        if (r11 >= r0) goto L_0x024b;
    L_0x02ce:
        r0 = a;
        if (r0 == 0) goto L_0x02ea;
    L_0x02d2:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02ea:
        r0 = r10.R;
        if (r0 == 0) goto L_0x02fd;
    L_0x02ee:
        r0 = r9.o;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x02fd;
    L_0x02f6:
        r0 = r10.n;
        if (r0 != 0) goto L_0x02fd;
    L_0x02fa:
        r9.d(r10);
    L_0x02fd:
        r10.j();
        r0 = r10.R;
        if (r0 == 0) goto L_0x0330;
    L_0x0304:
        r0 = r10.Q;
        if (r0 == 0) goto L_0x0330;
    L_0x0308:
        r0 = r9.n;
        if (r0 <= 0) goto L_0x0394;
    L_0x030c:
        r0 = r9.t;
        if (r0 != 0) goto L_0x0394;
    L_0x0310:
        r0 = r9.a(r10, r12, r3, r13);
    L_0x0314:
        if (r0 == 0) goto L_0x0329;
    L_0x0316:
        r1 = r10.R;
        r10.k = r1;
        r10.l = r11;
        r1 = new android.support.v4.app.q;
        r1.<init>(r9, r10);
        r0.setAnimationListener(r1);
        r1 = r10.R;
        r1.startAnimation(r0);
    L_0x0329:
        r0 = r10.Q;
        r1 = r10.R;
        r0.removeView(r1);
    L_0x0330:
        r10.Q = r7;
        r10.R = r7;
        r10.S = r7;
        goto L_0x024b;
    L_0x0338:
        r0 = a;
        if (r0 == 0) goto L_0x0354;
    L_0x033c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0354:
        r0 = r10.L;
        if (r0 != 0) goto L_0x035b;
    L_0x0358:
        r10.k();
    L_0x035b:
        r10.O = r3;
        r10.onDetach();
        r0 = r10.O;
        if (r0 != 0) goto L_0x0383;
    L_0x0364:
        r0 = new android.support.v4.app.ae;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0383:
        if (r14 != 0) goto L_0x0045;
    L_0x0385:
        r0 = r10.L;
        if (r0 != 0) goto L_0x038e;
    L_0x0389:
        r9.c(r10);
        goto L_0x0045;
    L_0x038e:
        r10.C = r7;
        r10.B = r7;
        goto L_0x0045;
    L_0x0394:
        r0 = r7;
        goto L_0x0314;
    L_0x0397:
        r0 = r7;
        goto L_0x019b;
        */

    }

    void a(e r2_e) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(r2_e);
        b();
    }

    boolean a(Handler r9_Handler, String r10_String, int r11i, int r12i) {
        if (this.i == null) {
            return false;
        }
        int r0i;
        if (r10_String == null && r11i < 0 && (r12i & 1) == 0) {
            r0i = this.i.size() - 1;
            if (r0i < 0) {
                return false;
            }
            ((e) this.i.remove(r0i)).popFromBackStack(true);
            b();
            return true;
        } else {
            r0i = -1;
            int r1i;
            ArrayList r5_ArrayList;
            int r6i;
            int r4i;
            if (r10_String != null || r11i >= 0) {
                r1i = this.i.size() - 1;
                while (r1i >= 0) {
                    r0_e = (e) this.i.get(r1i);
                    if (r10_String == null || (!r10_String.equals(r0_e.getName()))) {
                        if (r11i < 0 || r11i != r0_e.o) {
                            r1i--;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    if ((r12i & 1) == 0) {
                        r1i--;
                        while (r1i >= 0) {
                            r0_e = (e) this.i.get(r1i);
                            if (r10_String == null || (!r10_String.equals(r0_e.getName()))) {
                                if (r11i < 0 || r11i != r0_e.o) {
                                    break;
                                    break;
                                } else {
                                    r1i--;
                                }
                            } else {
                                r1i--;
                            }
                        }
                    }
                    r0i = r1i;
                    if (r0i == this.i.size() - 1) {
                        return false;
                    }
                    r5_ArrayList = new ArrayList();
                    r1i = this.i.size() - 1;
                    while (r1i > r0i) {
                        r5_ArrayList.add(this.i.remove(r1i));
                        r1i--;
                    }
                    r6i = r5_ArrayList.size() - 1;
                    r4i = 0;
                    while (r4i <= r6i) {
                        if (!a) {
                            Log.v("FragmentManager", "Popping back stack state: " + r5_ArrayList.get(r4i));
                        }
                        ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                        r4i++;
                    }
                    b();
                    return true;
                }
                if (r1i < 0) {
                    return false;
                }
                if ((r12i & 1) == 0) {
                    r0i = r1i;
                    if (r0i == this.i.size() - 1) {
                        return false;
                    }
                    r5_ArrayList = new ArrayList();
                    r1i = this.i.size() - 1;
                    while (r1i > r0i) {
                        r5_ArrayList.add(this.i.remove(r1i));
                        r1i--;
                    }
                    r6i = r5_ArrayList.size() - 1;
                    r4i = 0;
                    while (r4i <= r6i) {
                        if (a) {
                            if (r4i != r6i) {
                            }
                            ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                            r4i++;
                        } else {
                            Log.v("FragmentManager", "Popping back stack state: " + r5_ArrayList.get(r4i));
                            if (r4i != r6i) {
                            }
                            ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                            r4i++;
                        }
                    }
                    b();
                    return true;
                } else {
                    r1i--;
                    while (r1i >= 0) {
                        r0_e = (e) this.i.get(r1i);
                        if (r10_String == null || r10_String.equals(r0_e.getName())) {
                            if (r11i < 0 || r11i != r0_e.o) {
                                break;
                                break;
                            } else {
                                r1i--;
                            }
                        } else {
                            r1i--;
                        }
                    }
                    r0i = r1i;
                    if (r0i == this.i.size() - 1) {
                        return false;
                    }
                    r5_ArrayList = new ArrayList();
                    r1i = this.i.size() - 1;
                    while (r1i > r0i) {
                        r5_ArrayList.add(this.i.remove(r1i));
                        r1i--;
                    }
                    r6i = r5_ArrayList.size() - 1;
                    r4i = 0;
                    while (r4i <= r6i) {
                        if (a) {
                            Log.v("FragmentManager", "Popping back stack state: " + r5_ArrayList.get(r4i));
                        }
                        if (r4i != r6i) {
                        }
                        ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                        r4i++;
                    }
                    b();
                    return true;
                }
            } else {
                if (r0i == this.i.size() - 1) {
                    return false;
                }
                r5_ArrayList = new ArrayList();
                r1i = this.i.size() - 1;
                while (r1i > r0i) {
                    r5_ArrayList.add(this.i.remove(r1i));
                    r1i--;
                }
                r6i = r5_ArrayList.size() - 1;
                r4i = 0;
                while (r4i <= r6i) {
                    if (a) {
                        if (r4i != r6i) {
                        }
                        ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                        r4i++;
                    } else {
                        Log.v("FragmentManager", "Popping back stack state: " + r5_ArrayList.get(r4i));
                        if (r4i != r6i) {
                        }
                        ((e) r5_ArrayList.get(r4i)).popFromBackStack(r4i != r6i);
                        r4i++;
                    }
                }
                b();
                return true;
            }
        }
    }

    public void addFragment(Fragment r5_Fragment, boolean r6z) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (a) {
            Log.v("FragmentManager", "add: " + r5_Fragment);
        }
        b(r5_Fragment);
        if (!r5_Fragment.J) {
            if (this.g.contains(r5_Fragment)) {
                throw new IllegalStateException("Fragment already added: " + r5_Fragment);
            } else {
                this.g.add(r5_Fragment);
                r5_Fragment.u = true;
                r5_Fragment.v = false;
                if (r5_Fragment.M && r5_Fragment.N) {
                    this.r = true;
                    if (r6z) {
                        a(r5_Fragment);
                    }
                } else if (r6z) {
                    a(r5_Fragment);
                }
            }
        }
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener r2_OnBackStackChangedListener) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.m.add(r2_OnBackStackChangedListener);
    }

    public int allocBackStackIndex(e r5_e) {
        int r0i;
        synchronized (this) {
            if (this.l == null || this.l.size() <= 0) {
                if (this.k == null) {
                    this.k = new ArrayList();
                }
                r0i = this.k.size();
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + r0i + " to " + r5_e);
                }
                this.k.add(r5_e);
            } else {
                r0i = ((Integer) this.l.remove(this.l.size() - 1)).intValue();
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + r0i + " with " + r5_e);
                }
                this.k.set(r0i, r5_e);
            }
        }
        return r0i;
    }

    public void attachActivity(FragmentActivity r3_FragmentActivity, k r4_k, Fragment r5_Fragment) {
        if (this.o != null) {
            throw new IllegalStateException("Already attached");
        } else {
            this.o = r3_FragmentActivity;
            this.p = r4_k;
            this.q = r5_Fragment;
        }
    }

    public void attachFragment(Fragment r7_Fragment, int r8i, int r9i) {
        if (a) {
            Log.v("FragmentManager", "attach: " + r7_Fragment);
        }
        if (r7_Fragment.J) {
            r7_Fragment.J = false;
            if (!r7_Fragment.u) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                if (this.g.contains(r7_Fragment)) {
                    throw new IllegalStateException("Fragment already added: " + r7_Fragment);
                } else {
                    if (a) {
                        Log.v("FragmentManager", "add from attach: " + r7_Fragment);
                    }
                    this.g.add(r7_Fragment);
                    r7_Fragment.u = true;
                    if (r7_Fragment.M && r7_Fragment.N) {
                        this.r = true;
                        a(r7_Fragment, this.n, r8i, r9i, false);
                    } else {
                        a(r7_Fragment, this.n, r8i, r9i, false);
                    }
                }
            }
        }
    }

    void b() {
        if (this.m != null) {
            int r1i = 0;
            while (r1i < this.m.size()) {
                ((OnBackStackChangedListener) this.m.get(r1i)).onBackStackChanged();
                r1i++;
            }
        }
    }

    void b(Fragment r4_Fragment) {
        if (r4_Fragment.o >= 0) {
        } else {
            if (this.h == null || this.h.size() <= 0) {
                if (this.f == null) {
                    this.f = new ArrayList();
                }
                r4_Fragment.a(this.f.size(), this.q);
                this.f.add(r4_Fragment);
            } else {
                r4_Fragment.a(((Integer) this.h.remove(this.h.size() - 1)).intValue(), this.q);
                this.f.set(r4_Fragment.o, r4_Fragment);
            }
            if (a) {
                Log.v("FragmentManager", "Allocated fragment index " + r4_Fragment);
            }
        }
    }

    public FragmentTransaction beginTransaction() {
        return new e(this);
    }

    ArrayList<Fragment> c() {
        ArrayList<Fragment> r1_ArrayList_Fragment = null;
        if (this.f != null) {
            int r3i = 0;
            while (r3i < this.f.size()) {
                Fragment r0_Fragment = (Fragment) this.f.get(r3i);
                if (r0_Fragment == null || (!r0_Fragment.K)) {
                    r3i++;
                } else {
                    if (r1_ArrayList_Fragment == null) {
                        r1_ArrayList_Fragment = new ArrayList();
                    }
                    r1_ArrayList_Fragment.add(r0_Fragment);
                    r0_Fragment.L = true;
                    r0_Fragment.s = r0_Fragment.r != null ? r0_Fragment.r.o : -1;
                    if (a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + r0_Fragment);
                    }
                    r3i++;
                }
            }
        }
        return r1_ArrayList_Fragment;
    }

    void c(Fragment r4_Fragment) {
        if (r4_Fragment.o < 0) {
        } else {
            if (a) {
                Log.v("FragmentManager", "Freeing fragment index " + r4_Fragment);
            }
            this.f.set(r4_Fragment.o, null);
            if (this.h == null) {
                this.h = new ArrayList();
            }
            this.h.add(Integer.valueOf(r4_Fragment.o));
            this.o.a(r4_Fragment.p);
            r4_Fragment.b();
        }
    }

    Parcelable d() {
        BackStackState[] r3_BackStackStateA = null;
        execPendingActions();
        if (b) {
            this.s = true;
        }
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        int r6i = this.f.size();
        FragmentState[] r7_FragmentStateA = new FragmentState[r6i];
        int r5i = 0;
        int r2i = 0;
        while (r5i < r6i) {
            int r0i;
            Fragment r0_Fragment = (Fragment) this.f.get(r5i);
            if (r0_Fragment != null) {
                if (r0_Fragment.o < 0) {
                    a(new IllegalStateException("Failure saving state: active " + r0_Fragment + " has cleared index: " + r0_Fragment.o));
                }
                FragmentState r2_FragmentState = new FragmentState(r0_Fragment);
                r7_FragmentStateA[r5i] = r2_FragmentState;
                if (r0_Fragment.j <= 0 || r2_FragmentState.j != null) {
                    r2_FragmentState.j = r0_Fragment.m;
                } else {
                    r2_FragmentState.j = e(r0_Fragment);
                    if (r0_Fragment.r != null) {
                        if (r0_Fragment.r.o < 0) {
                            a(new IllegalStateException("Failure saving state: " + r0_Fragment + " has target not in fragment manager: " + r0_Fragment.r));
                        }
                        if (r2_FragmentState.j == null) {
                            r2_FragmentState.j = new Bundle();
                        }
                        putFragment(r2_FragmentState.j, "android:target_state", r0_Fragment.r);
                        if (r0_Fragment.t != 0) {
                            r2_FragmentState.j.putInt("android:target_req_state", r0_Fragment.t);
                        }
                    }
                }
                if (a) {
                    Log.v("FragmentManager", "Saved state of " + r0_Fragment + ": " + r2_FragmentState.j);
                }
                r0i = 1;
            } else {
                r0i = r2i;
            }
            r5i++;
            r2i = r0i;
        }
        if (r2i == 0) {
            if (!(a)) {
                return null;
            }
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        } else {
            int[] r1_intA;
            if (this.g != null) {
                r5i = this.g.size();
                if (r5i > 0) {
                    r1_intA = new int[r5i];
                    r2i = 0;
                    while (r2i < r5i) {
                        r1_intA[r2i] = ((Fragment) this.g.get(r2i)).o;
                        if (r1_intA[r2i] < 0) {
                            a(new IllegalStateException("Failure saving state: active " + this.g.get(r2i) + " has cleared index: " + r1_intA[r2i]));
                        }
                        if (a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + r2i + ": " + this.g.get(r2i));
                        }
                        r2i++;
                    }
                }
                r1_intA = null;
            } else {
                r1_intA = null;
            }
            if (this.i != null) {
                r5i = this.i.size();
                if (r5i > 0) {
                    r3_BackStackStateA = new BackStackState[r5i];
                    r2i = 0;
                    while (r2i < r5i) {
                        r3_BackStackStateA[r2i] = new BackStackState(this, (e) this.i.get(r2i));
                        if (a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + r2i + ": " + this.i.get(r2i));
                        }
                        r2i++;
                    }
                }
            }
            FragmentManagerState r0_FragmentManagerState = new FragmentManagerState();
            r0_FragmentManagerState.a = r7_FragmentStateA;
            r0_FragmentManagerState.b = r1_intA;
            r0_FragmentManagerState.c = r3_BackStackStateA;
            return r0_FragmentManagerState;
        }
    }

    void d(Fragment r3_Fragment) {
        if (r3_Fragment.S == null) {
        } else {
            if (this.x == null) {
                this.x = new SparseArray();
            } else {
                this.x.clear();
            }
            r3_Fragment.S.saveHierarchyState(this.x);
            if (this.x.size() > 0) {
                r3_Fragment.n = this.x;
                this.x = null;
            }
        }
    }

    public void detachFragment(Fragment r7_Fragment, int r8i, int r9i) {
        if (a) {
            Log.v("FragmentManager", "detach: " + r7_Fragment);
        }
        if (!r7_Fragment.J) {
            r7_Fragment.J = true;
            if (r7_Fragment.u) {
                if (this.g != null) {
                    if (a) {
                        Log.v("FragmentManager", "remove from detach: " + r7_Fragment);
                    }
                    this.g.remove(r7_Fragment);
                }
                if (r7_Fragment.M && r7_Fragment.N) {
                    this.r = true;
                    r7_Fragment.u = false;
                    a(r7_Fragment, (int)ANIM_STYLE_OPEN_ENTER, r8i, r9i, false);
                } else {
                    r7_Fragment.u = false;
                    a(r7_Fragment, (int)ANIM_STYLE_OPEN_ENTER, r8i, r9i, false);
                }
            }
        }
    }

    public void dispatchActivityCreated() {
        this.s = false;
        a((int)ANIM_STYLE_OPEN_EXIT, false);
    }

    public void dispatchConfigurationChanged(Configuration r3_Configuration) {
        if (this.g != null) {
            int r1i = 0;
            while (r1i < this.g.size()) {
                Fragment r0_Fragment = (Fragment) this.g.get(r1i);
                if (r0_Fragment != null) {
                    r0_Fragment.a(r3_Configuration);
                }
                r1i++;
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem r4_MenuItem) {
        if (this.g == null) {
            return false;
        }
        int r1i = 0;
        while (r1i < this.g.size()) {
            Fragment r0_Fragment = (Fragment) this.g.get(r1i);
            if (r0_Fragment != null && r0_Fragment.b(r4_MenuItem)) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    public void dispatchCreate() {
        this.s = false;
        a((int)ANIM_STYLE_OPEN_ENTER, false);
    }

    public boolean dispatchCreateOptionsMenu(Menu r7_Menu, MenuInflater r8_MenuInflater) {
        boolean r2z;
        Fragment r0_Fragment;
        int r4i = 0;
        ArrayList r1_ArrayList = null;
        if (this.g != null) {
            int r3i = 0;
            r2z = false;
            while (r3i < this.g.size()) {
                r0_Fragment = (Fragment) this.g.get(r3i);
                if (r0_Fragment == null || (!r0_Fragment.a(r7_Menu, r8_MenuInflater))) {
                    r3i++;
                    r2z = r2z;
                } else {
                    r2z = true;
                    if (r1_ArrayList == null) {
                        r1_ArrayList = new ArrayList();
                    }
                    r1_ArrayList.add(r0_Fragment);
                    r3i++;
                    r2z = r2z;
                }
            }
        } else {
            r2z = false;
        }
        if (this.j != null) {
            while (r4i < this.j.size()) {
                r0_Fragment = (Fragment) this.j.get(r4i);
                if (r1_ArrayList == null || (!r1_ArrayList.contains(r0_Fragment))) {
                    r0_Fragment.onDestroyOptionsMenu();
                    r4i++;
                } else {
                    r4i++;
                }
            }
            this.j = r1_ArrayList;
            return r2z;
        } else {
            this.j = r1_ArrayList;
            return r2z;
        }
    }

    public void dispatchDestroy() {
        this.t = true;
        execPendingActions();
        a(0, false);
        this.o = null;
        this.p = null;
        this.q = null;
    }

    public void dispatchDestroyView() {
        a((int)ANIM_STYLE_OPEN_ENTER, false);
    }

    public void dispatchLowMemory() {
        if (this.g != null) {
            int r1i = 0;
            while (r1i < this.g.size()) {
                Fragment r0_Fragment = (Fragment) this.g.get(r1i);
                if (r0_Fragment != null) {
                    r0_Fragment.f();
                }
                r1i++;
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem r4_MenuItem) {
        if (this.g == null) {
            return false;
        }
        int r1i = 0;
        while (r1i < this.g.size()) {
            Fragment r0_Fragment = (Fragment) this.g.get(r1i);
            if (r0_Fragment != null && r0_Fragment.a(r4_MenuItem)) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu r3_Menu) {
        if (this.g != null) {
            int r1i = 0;
            while (r1i < this.g.size()) {
                Fragment r0_Fragment = (Fragment) this.g.get(r1i);
                if (r0_Fragment != null) {
                    r0_Fragment.b(r3_Menu);
                }
                r1i++;
            }
        }
    }

    public void dispatchPause() {
        a((int)ANIM_STYLE_CLOSE_EXIT, false);
    }

    public boolean dispatchPrepareOptionsMenu(Menu r4_Menu) {
        if (this.g == null) {
            return false;
        }
        int r1i = 0;
        boolean r2z = false;
        while (r1i < this.g.size()) {
            Fragment r0_Fragment = (Fragment) this.g.get(r1i);
            if (r0_Fragment == null || (!r0_Fragment.a(r4_Menu))) {
                r1i++;
            } else {
                r2z = true;
                r1i++;
            }
        }
        return r2z;
    }

    public void dispatchReallyStop() {
        a((int)ANIM_STYLE_OPEN_EXIT, false);
    }

    public void dispatchResume() {
        this.s = false;
        a((int)ANIM_STYLE_FADE_ENTER, false);
    }

    public void dispatchStart() {
        this.s = false;
        a((int)ANIM_STYLE_CLOSE_EXIT, false);
    }

    public void dispatchStop() {
        this.s = true;
        a((int)ANIM_STYLE_CLOSE_ENTER, false);
    }

    public void dump(String r7_String, FileDescriptor r8_FileDescriptor, PrintWriter r9_PrintWriter, String[] r10_StringA) {
        int r4i;
        int r2i;
        int r1i = 0;
        String r3_String = r7_String + "    ";
        if (this.f != null) {
            r4i = this.f.size();
            if (r4i > 0) {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.print("Active Fragments in ");
                r9_PrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
                r9_PrintWriter.println(":");
                r2i = 0;
                while (r2i < r4i) {
                    Fragment r0_Fragment = (Fragment) this.f.get(r2i);
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.print("  #");
                    r9_PrintWriter.print(r2i);
                    r9_PrintWriter.print(": ");
                    r9_PrintWriter.println(r0_Fragment);
                    if (r0_Fragment != null) {
                        r0_Fragment.dump(r3_String, r8_FileDescriptor, r9_PrintWriter, r10_StringA);
                    }
                    r2i++;
                }
            }
        }
        if (this.g != null) {
            r4i = this.g.size();
            if (r4i > 0) {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.println("Added Fragments:");
                r2i = 0;
                while (r2i < r4i) {
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.print("  #");
                    r9_PrintWriter.print(r2i);
                    r9_PrintWriter.print(": ");
                    r9_PrintWriter.println(((Fragment) this.g.get(r2i)).toString());
                    r2i++;
                }
            }
        }
        if (this.j != null) {
            r4i = this.j.size();
            if (r4i > 0) {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.println("Fragments Created Menus:");
                r2i = 0;
                while (r2i < r4i) {
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.print("  #");
                    r9_PrintWriter.print(r2i);
                    r9_PrintWriter.print(": ");
                    r9_PrintWriter.println(((Fragment) this.j.get(r2i)).toString());
                    r2i++;
                }
            }
        }
        if (this.i != null) {
            r4i = this.i.size();
            if (r4i > 0) {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.println("Back Stack:");
                r2i = 0;
                while (r2i < r4i) {
                    e r0_e = (e) this.i.get(r2i);
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.print("  #");
                    r9_PrintWriter.print(r2i);
                    r9_PrintWriter.print(": ");
                    r9_PrintWriter.println(r0_e.toString());
                    r0_e.dump(r3_String, r8_FileDescriptor, r9_PrintWriter, r10_StringA);
                    r2i++;
                }
            }
        }
        synchronized (this) {
            if (this.k != null) {
                int r3i = this.k.size();
                if (r3i > 0) {
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.println("Back Stack Indices:");
                    r2i = 0;
                    while (r2i < r3i) {
                        r9_PrintWriter.print(r7_String);
                        r9_PrintWriter.print("  #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.println((e) this.k.get(r2i));
                        r2i++;
                    }
                }
            }
            if (this.l == null || this.l.size() <= 0) {
            } else {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.print("mAvailBackStackIndices: ");
                r9_PrintWriter.println(Arrays.toString(this.l.toArray()));
            }
        }
        if (this.c != null) {
            r2i = this.c.size();
            if (r2i > 0) {
                r9_PrintWriter.print(r7_String);
                r9_PrintWriter.println("Pending Actions:");
                while (r1i < r2i) {
                    r9_PrintWriter.print(r7_String);
                    r9_PrintWriter.print("  #");
                    r9_PrintWriter.print(r1i);
                    r9_PrintWriter.print(": ");
                    r9_PrintWriter.println((Runnable) this.c.get(r1i));
                    r1i++;
                }
            }
        }
        r9_PrintWriter.print(r7_String);
        r9_PrintWriter.println("FragmentManager misc state:");
        r9_PrintWriter.print(r7_String);
        r9_PrintWriter.print("  mActivity=");
        r9_PrintWriter.println(this.o);
        r9_PrintWriter.print(r7_String);
        r9_PrintWriter.print("  mContainer=");
        r9_PrintWriter.println(this.p);
        if (this.q != null) {
            r9_PrintWriter.print(r7_String);
            r9_PrintWriter.print("  mParent=");
            r9_PrintWriter.println(this.q);
        }
        r9_PrintWriter.print(r7_String);
        r9_PrintWriter.print("  mCurState=");
        r9_PrintWriter.print(this.n);
        r9_PrintWriter.print(" mStateSaved=");
        r9_PrintWriter.print(this.s);
        r9_PrintWriter.print(" mDestroyed=");
        r9_PrintWriter.println(this.t);
        if (this.r) {
            r9_PrintWriter.print(r7_String);
            r9_PrintWriter.print("  mNeedMenuInvalidate=");
            r9_PrintWriter.println(this.r);
        }
        if (this.u != null) {
            r9_PrintWriter.print(r7_String);
            r9_PrintWriter.print("  mNoTransactionsBecause=");
            r9_PrintWriter.println(this.u);
        }
        if (this.h == null || this.h.size() <= 0) {
        } else {
            r9_PrintWriter.print(r7_String);
            r9_PrintWriter.print("  mAvailIndices: ");
            r9_PrintWriter.println(Arrays.toString(this.h.toArray()));
        }
    }

    Bundle e(Fragment r4_Fragment) {
        Bundle r0_Bundle;
        if (this.w == null) {
            this.w = new Bundle();
        }
        r4_Fragment.d(this.w);
        if (this.w.isEmpty()) {
            r0_Bundle = null;
        } else {
            r0_Bundle = this.w;
            this.w = null;
        }
        if (r4_Fragment.R != null) {
            d(r4_Fragment);
        }
        if (r4_Fragment.n != null) {
            if (r0_Bundle == null) {
                r0_Bundle = new Bundle();
            }
            r0_Bundle.putSparseParcelableArray("android:view_state", r4_Fragment.n);
        }
        if (!r4_Fragment.U) {
            if (r0_Bundle == null) {
                r0_Bundle = new Bundle();
            }
            r0_Bundle.putBoolean("android:user_visible_hint", r4_Fragment.U);
        }
        return r0_Bundle;
    }

    public void enqueueAction(Runnable r3_Runnable, boolean r4z) {
        if (!r4z) {
            e();
        }
        synchronized (this) {
            if (this.t || this.o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            } else {
                if (this.c == null) {
                    this.c = new ArrayList();
                }
                this.c.add(r3_Runnable);
                if (this.c.size() == 1) {
                    this.o.a.removeCallbacks(this.y);
                    this.o.a.post(this.y);
                }
            }
        }
    }

    public boolean execPendingActions() {
        if (this.e) {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        } else if (Looper.myLooper() != this.o.a.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of process");
        } else {
            boolean r1z = false;
            while (true) {
                synchronized (this) {
                    int r3i;
                    if (this.c == null || this.c.size() == 0) {
                        if (this.v) {
                            r3i = 0;
                            int r4i = 0;
                            while (r3i < this.f.size()) {
                                Fragment r0_Fragment = (Fragment) this.f.get(r3i);
                                if (r0_Fragment == null || r0_Fragment.V == null) {
                                    r3i++;
                                } else {
                                    r4i |= r0_Fragment.V.hasRunningLoaders();
                                    r3i++;
                                }
                            }
                            if (r4i == 0) {
                                this.v = false;
                                a();
                            }
                        }
                        return r1z;
                    } else {
                        r3i = this.c.size();
                        int r1i;
                        if (this.d == null || this.d.length < r3i) {
                            this.d = new Runnable[r3i];
                            this.c.toArray(this.d);
                            this.c.clear();
                            this.o.a.removeCallbacks(this.y);
                            this.e = true;
                            r1i = 0;
                            while (r1i < r3i) {
                                this.d[r1i].run();
                                this.d[r1i] = null;
                                r1i++;
                            }
                            this.e = false;
                            r1z = true;
                        } else {
                            this.c.toArray(this.d);
                            this.c.clear();
                            this.o.a.removeCallbacks(this.y);
                            this.e = true;
                            r1i = 0;
                            while (r1i < r3i) {
                                this.d[r1i].run();
                                this.d[r1i] = null;
                                r1i++;
                            }
                            this.e = false;
                            r1z = true;
                        }
                    }
                }
            }
        }
    }

    public boolean executePendingTransactions() {
        return execPendingActions();
    }

    public Fragment findFragmentById(int r4i) {
        int r1i;
        Fragment r0_Fragment;
        if (this.g != null) {
            r1i = this.g.size() - 1;
            while (r1i >= 0) {
                r0_Fragment = (Fragment) this.g.get(r1i);
                if (r0_Fragment != null && r0_Fragment.F == r4i) {
                    return r0_Fragment;
                }
                r1i--;
            }
        }
        if (this.f != null) {
            r1i = this.f.size() - 1;
            while (r1i >= 0) {
                r0_Fragment = (Fragment) this.f.get(r1i);
                if (r0_Fragment != null && r0_Fragment.F == r4i) {
                    return r0_Fragment;
                }
                r1i--;
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String r4_String) {
        int r1i;
        Fragment r0_Fragment;
        if (this.g == null || r4_String == null) {
        } else {
            r1i = this.g.size() - 1;
            while (r1i >= 0) {
                r0_Fragment = (Fragment) this.g.get(r1i);
                if (r0_Fragment != null && r4_String.equals(r0_Fragment.H)) {
                    return r0_Fragment;
                }
                r1i--;
            }
        }
        if (this.f == null || r4_String == null) {
            return null;
        }
        r1i = this.f.size() - 1;
        while (r1i >= 0) {
            r0_Fragment = (Fragment) this.f.get(r1i);
            if (r0_Fragment != null && r4_String.equals(r0_Fragment.H)) {
                return r0_Fragment;
            }
            r1i--;
        }
        return null;
    }

    public Fragment findFragmentByWho(String r3_String) {
        if (this.f == null || r3_String == null) {
            return null;
        }
        int r1i = this.f.size() - 1;
        while (r1i >= 0) {
            Fragment r0_Fragment = (Fragment) this.f.get(r1i);
            if (r0_Fragment != null) {
                r0_Fragment = r0_Fragment.a(r3_String);
                if (r0_Fragment != null) {
                    return r0_Fragment;
                }
            }
            r1i--;
        }
        return null;
    }

    public void freeBackStackIndex(int r4i) {
        synchronized (this) {
            this.k.set(r4i, null);
            if (this.l == null) {
                this.l = new ArrayList();
            }
            if (a) {
                Log.v("FragmentManager", "Freeing back stack index " + r4i);
            }
            this.l.add(Integer.valueOf(r4i));
        }
    }

    public BackStackEntry getBackStackEntryAt(int r2i) {
        return (BackStackEntry) this.i.get(r2i);
    }

    public int getBackStackEntryCount() {
        return this.i != null ? this.i.size() : 0;
    }

    public Fragment getFragment(Bundle r6_Bundle, String r7_String) {
        int r1i = r6_Bundle.getInt(r7_String, -1);
        if (r1i == -1) {
            return null;
        }
        if (r1i >= this.f.size()) {
            a(new IllegalStateException("Fragement no longer exists for key " + r7_String + ": index " + r1i));
        }
        Fragment r0_Fragment = (Fragment) this.f.get(r1i);
        if (r0_Fragment != null) {
            return r0_Fragment;
        }
        a(new IllegalStateException("Fragement no longer exists for key " + r7_String + ": index " + r1i));
        return r0_Fragment;
    }

    public List<Fragment> getFragments() {
        return this.f;
    }

    public void hideFragment(Fragment r5_Fragment, int r6i, int r7i) {
        if (a) {
            Log.v("FragmentManager", "hide: " + r5_Fragment);
        }
        if (!r5_Fragment.I) {
            r5_Fragment.I = true;
            if (r5_Fragment.R != null) {
                Animation r0_Animation = a(r5_Fragment, r6i, false, r7i);
                if (r0_Animation != null) {
                    r5_Fragment.R.startAnimation(r0_Animation);
                }
                r5_Fragment.R.setVisibility(Base64.DONT_BREAK_LINES);
            }
            if (r5_Fragment.u && r5_Fragment.M && r5_Fragment.N) {
                this.r = true;
                r5_Fragment.onHiddenChanged(true);
            } else {
                r5_Fragment.onHiddenChanged(true);
            }
        }
    }

    public void noteStateNotSaved() {
        this.s = false;
    }

    public void performPendingDeferredStart(Fragment r7_Fragment) {
        if (r7_Fragment.T) {
            if (this.e) {
                this.v = true;
            } else {
                r7_Fragment.T = false;
                a(r7_Fragment, this.n, 0, 0, false);
            }
        }
    }

    public void popBackStack() {
        enqueueAction(new n(this), false);
    }

    public void popBackStack(int r4i, int r5i) {
        if (r4i < 0) {
            throw new IllegalArgumentException("Bad id: " + r4i);
        } else {
            enqueueAction(new p(this, r4i, r5i), false);
        }
    }

    public void popBackStack(String r3_String, int r4i) {
        enqueueAction(new o(this, r3_String, r4i), false);
    }

    public boolean popBackStackImmediate() {
        e();
        executePendingTransactions();
        return a(this.o.a, null, -1, 0);
    }

    public boolean popBackStackImmediate(int r4i, int r5i) {
        e();
        executePendingTransactions();
        if (r4i >= 0) {
            return a(this.o.a, null, r4i, r5i);
        }
        throw new IllegalArgumentException("Bad id: " + r4i);
    }

    public boolean popBackStackImmediate(String r3_String, int r4i) {
        e();
        executePendingTransactions();
        return a(this.o.a, r3_String, -1, r4i);
    }

    public void putFragment(Bundle r4_Bundle, String r5_String, Fragment r6_Fragment) {
        if (r6_Fragment.o < 0) {
            a(new IllegalStateException("Fragment " + r6_Fragment + " is not currently in the FragmentManager"));
        }
        r4_Bundle.putInt(r5_String, r6_Fragment.o);
    }

    public void removeFragment(Fragment r7_Fragment, int r8i, int r9i) {
        if (a) {
            Log.v("FragmentManager", "remove: " + r7_Fragment + " nesting=" + r7_Fragment.A);
        }
        int r0i = r7_Fragment.a() ? 0 : 1;
        if (!(r7_Fragment.J && r0i == 0)) {
            if (this.g != null) {
                this.g.remove(r7_Fragment);
            }
            if (r7_Fragment.M && r7_Fragment.N) {
                this.r = true;
                r7_Fragment.u = false;
                r7_Fragment.v = true;
                a(r7_Fragment, r0i == 0 ? 1 : 0, r8i, r9i, false);
            } else {
                r7_Fragment.u = false;
                r7_Fragment.v = true;
                if (r0i == 0) {
                }
                a(r7_Fragment, r0i == 0 ? 1 : 0, r8i, r9i, false);
            }
        }
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener r2_OnBackStackChangedListener) {
        if (this.m != null) {
            this.m.remove(r2_OnBackStackChangedListener);
        }
    }

    public SavedState saveFragmentInstanceState(Fragment r5_Fragment) {
        if (r5_Fragment.o < 0) {
            a(new IllegalStateException("Fragment " + r5_Fragment + " is not currently in the FragmentManager"));
        }
        if (r5_Fragment.j <= 0) {
            return null;
        }
        Bundle r1_Bundle = e(r5_Fragment);
        return r1_Bundle != null ? new SavedState(r1_Bundle) : null;
    }

    public void setBackStackIndex(int r5i, e r6_e) {
        synchronized (this) {
            if (this.k == null) {
                this.k = new ArrayList();
            }
            int r0i = this.k.size();
            if (r5i < r0i) {
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + r5i + " to " + r6_e);
                }
                this.k.set(r5i, r6_e);
            } else {
                while (r0i < r5i) {
                    this.k.add(null);
                    if (this.l == null) {
                        this.l = new ArrayList();
                    }
                    if (a) {
                        Log.v("FragmentManager", "Adding available back stack index " + r0i);
                    }
                    this.l.add(Integer.valueOf(r0i));
                    r0i++;
                }
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + r5i + " with " + r6_e);
                }
                this.k.add(r6_e);
            }
        }
    }

    public void showFragment(Fragment r6_Fragment, int r7i, int r8i) {
        if (a) {
            Log.v("FragmentManager", "show: " + r6_Fragment);
        }
        if (r6_Fragment.I) {
            r6_Fragment.I = false;
            if (r6_Fragment.R != null) {
                Animation r0_Animation = a(r6_Fragment, r7i, true, r8i);
                if (r0_Animation != null) {
                    r6_Fragment.R.startAnimation(r0_Animation);
                }
                r6_Fragment.R.setVisibility(0);
            }
            if (r6_Fragment.u && r6_Fragment.M && r6_Fragment.N) {
                this.r = true;
                r6_Fragment.onHiddenChanged(false);
            } else {
                r6_Fragment.onHiddenChanged(false);
            }
        }
    }

    public String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder(128);
        r0_StringBuilder.append("FragmentManager{");
        r0_StringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        r0_StringBuilder.append(" in ");
        if (this.q != null) {
            DebugUtils.buildShortClassTag(this.q, r0_StringBuilder);
        } else {
            DebugUtils.buildShortClassTag(this.o, r0_StringBuilder);
        }
        r0_StringBuilder.append("}}");
        return r0_StringBuilder.toString();
    }
}