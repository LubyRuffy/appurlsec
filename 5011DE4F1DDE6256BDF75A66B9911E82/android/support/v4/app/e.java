package android.support.v4.app;

import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.util.LogWriter;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: BackStackRecord.java
final class e extends FragmentTransaction implements BackStackEntry, Runnable {
    final l a;
    a b;
    a c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    boolean k;
    boolean l;
    String m;
    boolean n;
    int o;
    int p;
    CharSequence q;
    int r;
    CharSequence s;

    // compiled from: BackStackRecord.java
    static final class a {
        a a;
        a b;
        int c;
        Fragment d;
        int e;
        int f;
        int g;
        int h;
        ArrayList<Fragment> i;

        a() {
        }
    }

    public e(l r2_l) {
        this.l = true;
        this.o = -1;
        this.a = r2_l;
    }

    private void a(int r4i, Fragment r5_Fragment, String r6_String, int r7i) {
        r5_Fragment.B = this.a;
        if (r6_String != null) {
            if (r5_Fragment.H == null || r6_String.equals(r5_Fragment.H)) {
                r5_Fragment.H = r6_String;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + r5_Fragment + ": was " + r5_Fragment.H + " now " + r6_String);
            }
        }
        if (r4i != 0) {
            if (r5_Fragment.F == 0 || r5_Fragment.F == r4i) {
                r5_Fragment.F = r4i;
                r5_Fragment.G = r4i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + r5_Fragment + ": was " + r5_Fragment.F + " now " + r4i);
            }
        }
        a r0_a = new a();
        r0_a.c = r7i;
        r0_a.d = r5_Fragment;
        a(r0_a);
    }

    int a(boolean r5z) {
        if (this.n) {
            throw new IllegalStateException("commit already called");
        } else {
            if (l.a) {
                Log.v("FragmentManager", "Commit: " + this);
                dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
            }
            this.n = true;
            if (this.k) {
                this.o = this.a.allocBackStackIndex(this);
            } else {
                this.o = -1;
            }
            this.a.enqueueAction(this, r5z);
            return this.o;
        }
    }

    void a(int r7i) {
        if (this.k) {
            if (l.a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + r7i);
            }
            a r2_a = this.b;
            while (r2_a != null) {
                Fragment r0_Fragment;
                if (r2_a.d != null) {
                    r0_Fragment = r2_a.d;
                    r0_Fragment.A += r7i;
                    if (l.a) {
                        Log.v("FragmentManager", "Bump nesting of " + r2_a.d + " to " + r2_a.d.A);
                    }
                }
                if (r2_a.i != null) {
                    int r1i = r2_a.i.size() - 1;
                    while (r1i >= 0) {
                        r0_Fragment = (Fragment) r2_a.i.get(r1i);
                        r0_Fragment.A += r7i;
                        if (l.a) {
                            Log.v("FragmentManager", "Bump nesting of " + r0_Fragment + " to " + r0_Fragment.A);
                        }
                        r1i--;
                    }
                }
                r2_a = r2_a.a;
            }
        }
    }

    void a(a r2_a) {
        if (this.b == null) {
            this.c = r2_a;
            this.b = r2_a;
        } else {
            r2_a.b = this.c;
            this.c.a = r2_a;
            this.c = r2_a;
        }
        r2_a.e = this.e;
        r2_a.f = this.f;
        r2_a.g = this.g;
        r2_a.h = this.h;
        this.d++;
    }

    public FragmentTransaction add(int r3i, Fragment r4_Fragment) {
        a(r3i, r4_Fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int r2i, Fragment r3_Fragment, String r4_String) {
        a(r2i, r3_Fragment, r4_String, 1);
        return this;
    }

    public FragmentTransaction add(Fragment r3_Fragment, String r4_String) {
        a(0, r3_Fragment, r4_String, 1);
        return this;
    }

    public FragmentTransaction addToBackStack(String r3_String) {
        if (this.l) {
            this.k = true;
            this.m = r3_String;
            return this;
        } else {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
    }

    public FragmentTransaction attach(Fragment r3_Fragment) {
        a r0_a = new a();
        r0_a.c = 7;
        r0_a.d = r3_Fragment;
        a(r0_a);
        return this;
    }

    public int commit() {
        return a(false);
    }

    public int commitAllowingStateLoss() {
        return a(true);
    }

    public FragmentTransaction detach(Fragment r3_Fragment) {
        a r0_a = new a();
        r0_a.c = 6;
        r0_a.d = r3_Fragment;
        a(r0_a);
        return this;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if (this.k) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else {
            this.l = false;
            return this;
        }
    }

    public void dump(String r2_String, FileDescriptor r3_FileDescriptor, PrintWriter r4_PrintWriter, String[] r5_StringA) {
        dump(r2_String, r4_PrintWriter, true);
    }

    public void dump(String r8_String, PrintWriter r9_PrintWriter, boolean r10z) {
        if (r10z) {
            r9_PrintWriter.print(r8_String);
            r9_PrintWriter.print("mName=");
            r9_PrintWriter.print(this.m);
            r9_PrintWriter.print(" mIndex=");
            r9_PrintWriter.print(this.o);
            r9_PrintWriter.print(" mCommitted=");
            r9_PrintWriter.println(this.n);
            if (this.i != 0) {
                r9_PrintWriter.print(r8_String);
                r9_PrintWriter.print("mTransition=#");
                r9_PrintWriter.print(Integer.toHexString(this.i));
                r9_PrintWriter.print(" mTransitionStyle=#");
                r9_PrintWriter.println(Integer.toHexString(this.j));
            }
            if (this.e == 0 && this.f == 0) {
                if (this.g == 0 && this.h == 0) {
                    if (this.p == 0 && this.q == null) {
                        if (!(this.r == 0 && this.s == null)) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                            r9_PrintWriter.print(Integer.toHexString(this.r));
                            r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                            r9_PrintWriter.println(this.s);
                        }
                    } else {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.p));
                        r9_PrintWriter.print(" mBreadCrumbTitleText=");
                        r9_PrintWriter.println(this.q);
                        if (this.r == 0 || this.s == null) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                            r9_PrintWriter.print(Integer.toHexString(this.r));
                            r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                            r9_PrintWriter.println(this.s);
                        }
                    }
                } else {
                    r9_PrintWriter.print(r8_String);
                    r9_PrintWriter.print("mPopEnterAnim=#");
                    r9_PrintWriter.print(Integer.toHexString(this.g));
                    r9_PrintWriter.print(" mPopExitAnim=#");
                    r9_PrintWriter.println(Integer.toHexString(this.h));
                    if (this.p == 0 || this.q == null) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.p));
                        r9_PrintWriter.print(" mBreadCrumbTitleText=");
                        r9_PrintWriter.println(this.q);
                        if (this.r == 0 || this.s == null) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                            r9_PrintWriter.print(Integer.toHexString(this.r));
                            r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                            r9_PrintWriter.println(this.s);
                        }
                    } else if (this.r == 0 || this.s == null) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.r));
                        r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                        r9_PrintWriter.println(this.s);
                    }
                }
            } else {
                r9_PrintWriter.print(r8_String);
                r9_PrintWriter.print("mEnterAnim=#");
                r9_PrintWriter.print(Integer.toHexString(this.e));
                r9_PrintWriter.print(" mExitAnim=#");
                r9_PrintWriter.println(Integer.toHexString(this.f));
                if (this.g == 0 || this.h == 0) {
                    r9_PrintWriter.print(r8_String);
                    r9_PrintWriter.print("mPopEnterAnim=#");
                    r9_PrintWriter.print(Integer.toHexString(this.g));
                    r9_PrintWriter.print(" mPopExitAnim=#");
                    r9_PrintWriter.println(Integer.toHexString(this.h));
                    if (this.p == 0 || this.q == null) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.p));
                        r9_PrintWriter.print(" mBreadCrumbTitleText=");
                        r9_PrintWriter.println(this.q);
                        if (this.r == 0 || this.s == null) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                            r9_PrintWriter.print(Integer.toHexString(this.r));
                            r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                            r9_PrintWriter.println(this.s);
                        }
                    } else if (this.r == 0 || this.s == null) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.r));
                        r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                        r9_PrintWriter.println(this.s);
                    }
                } else if (this.p == 0 || this.q == null) {
                    r9_PrintWriter.print(r8_String);
                    r9_PrintWriter.print("mBreadCrumbTitleRes=#");
                    r9_PrintWriter.print(Integer.toHexString(this.p));
                    r9_PrintWriter.print(" mBreadCrumbTitleText=");
                    r9_PrintWriter.println(this.q);
                    if (this.r == 0 || this.s == null) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                        r9_PrintWriter.print(Integer.toHexString(this.r));
                        r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                        r9_PrintWriter.println(this.s);
                    }
                } else if (this.r == 0 || this.s == null) {
                    r9_PrintWriter.print(r8_String);
                    r9_PrintWriter.print("mBreadCrumbShortTitleRes=#");
                    r9_PrintWriter.print(Integer.toHexString(this.r));
                    r9_PrintWriter.print(" mBreadCrumbShortTitleText=");
                    r9_PrintWriter.println(this.s);
                }
            }
        }
        if (this.b != null) {
            r9_PrintWriter.print(r8_String);
            r9_PrintWriter.println("Operations:");
            String r4_String = r8_String + "    ";
            int r2i = 0;
            a r3_a = this.b;
            while (r3_a != null) {
                String r0_String;
                int r0i;
                switch (r3_a.c) {
                    case XListViewHeader.STATE_NORMAL:
                        r0_String = "NULL";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.e == 0 && r3_a.f == 0) {
                                if (!(r3_a.g == 0 && r3_a.h == 0)) {
                                    r9_PrintWriter.print(r8_String);
                                    r9_PrintWriter.print("popEnterAnim=#");
                                    r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                    r9_PrintWriter.print(" popExitAnim=#");
                                    r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                }
                            } else {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("enterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                                r9_PrintWriter.print(" exitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                                if (r3_a.g == 0 || r3_a.h == 0) {
                                    r9_PrintWriter.print(r8_String);
                                    r9_PrintWriter.print("popEnterAnim=#");
                                    r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                    r9_PrintWriter.print(" popExitAnim=#");
                                    r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                }
                            }
                        }
                        if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    r9_PrintWriter.print("Removed: ");
                                } else {
                                    if (r0i == 0) {
                                        r9_PrintWriter.println("Removed:");
                                    }
                                    r9_PrintWriter.print(r4_String);
                                    r9_PrintWriter.print("  #");
                                    r9_PrintWriter.print(r0i);
                                    r9_PrintWriter.print(": ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case XListViewHeader.STATE_READY:
                        r0_String = "ADD";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.e == 0 || r3_a.f == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("enterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                            r9_PrintWriter.print(" exitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                            if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                if (r3_a.i == null || r3_a.i.size() <= 0) {
                                    r3_a = r3_a.a;
                                    r2i++;
                                } else {
                                    r0i = 0;
                                    while (r0i < r3_a.i.size()) {
                                        r9_PrintWriter.print(r4_String);
                                        if (r3_a.i.size() == 1) {
                                            r9_PrintWriter.print("Removed: ");
                                        } else {
                                            if (r0i == 0) {
                                                r9_PrintWriter.println("Removed:");
                                            }
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                        r9_PrintWriter.println(r3_a.i.get(r0i));
                                        r0i++;
                                    }
                                    r3_a = r3_a.a;
                                    r2i++;
                                }
                            } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.g == 0 || r3_a.h == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("popEnterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                            r9_PrintWriter.print(" popExitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        r9_PrintWriter.print("Removed: ");
                                    } else {
                                        if (r0i == 0) {
                                            r9_PrintWriter.println("Removed:");
                                        }
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    if (r0i == 0) {
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    } else {
                                        r9_PrintWriter.println("Removed:");
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                } else {
                                    r9_PrintWriter.print("Removed: ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        r0_String = "REPLACE";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.e == 0 || r3_a.f == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("enterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                                r9_PrintWriter.print(" exitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                                if (r3_a.g == 0 || r3_a.h == 0) {
                                    r9_PrintWriter.print(r8_String);
                                    r9_PrintWriter.print("popEnterAnim=#");
                                    r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                    r9_PrintWriter.print(" popExitAnim=#");
                                    r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                }
                            } else if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            }
                        }
                        if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    r9_PrintWriter.print("Removed: ");
                                } else {
                                    if (r0i == 0) {
                                        r9_PrintWriter.println("Removed:");
                                    }
                                    r9_PrintWriter.print(r4_String);
                                    r9_PrintWriter.print("  #");
                                    r9_PrintWriter.print(r0i);
                                    r9_PrintWriter.print(": ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        r0_String = "REMOVE";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.e == 0 || r3_a.f == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("enterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                            r9_PrintWriter.print(" exitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                            if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                if (r3_a.i == null || r3_a.i.size() <= 0) {
                                    r3_a = r3_a.a;
                                    r2i++;
                                } else {
                                    r0i = 0;
                                    while (r0i < r3_a.i.size()) {
                                        r9_PrintWriter.print(r4_String);
                                        if (r3_a.i.size() == 1) {
                                            r9_PrintWriter.print("Removed: ");
                                        } else {
                                            if (r0i == 0) {
                                                r9_PrintWriter.println("Removed:");
                                            }
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                        r9_PrintWriter.println(r3_a.i.get(r0i));
                                        r0i++;
                                    }
                                    r3_a = r3_a.a;
                                    r2i++;
                                }
                            } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.g == 0 || r3_a.h == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("popEnterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                            r9_PrintWriter.print(" popExitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        r9_PrintWriter.print("Removed: ");
                                    } else {
                                        if (r0i == 0) {
                                            r9_PrintWriter.println("Removed:");
                                        }
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    if (r0i == 0) {
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    } else {
                                        r9_PrintWriter.println("Removed:");
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                } else {
                                    r9_PrintWriter.print("Removed: ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case XListViewFooter.STATE_NODATA:
                        r0_String = "HIDE";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.e == 0 || r3_a.f == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("enterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                                r9_PrintWriter.print(" exitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                                if (r3_a.g == 0 || r3_a.h == 0) {
                                    r9_PrintWriter.print(r8_String);
                                    r9_PrintWriter.print("popEnterAnim=#");
                                    r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                    r9_PrintWriter.print(" popExitAnim=#");
                                    r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                }
                            } else if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            }
                        }
                        if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    r9_PrintWriter.print("Removed: ");
                                } else {
                                    if (r0i == 0) {
                                        r9_PrintWriter.println("Removed:");
                                    }
                                    r9_PrintWriter.print(r4_String);
                                    r9_PrintWriter.print("  #");
                                    r9_PrintWriter.print(r0i);
                                    r9_PrintWriter.print(": ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case ShareUtils.SHARE_SMS:
                        r0_String = "SHOW";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.e == 0 || r3_a.f == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("enterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                            r9_PrintWriter.print(" exitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                            if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                if (r3_a.i == null || r3_a.i.size() <= 0) {
                                    r3_a = r3_a.a;
                                    r2i++;
                                } else {
                                    r0i = 0;
                                    while (r0i < r3_a.i.size()) {
                                        r9_PrintWriter.print(r4_String);
                                        if (r3_a.i.size() == 1) {
                                            r9_PrintWriter.print("Removed: ");
                                        } else {
                                            if (r0i == 0) {
                                                r9_PrintWriter.println("Removed:");
                                            }
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                        r9_PrintWriter.println(r3_a.i.get(r0i));
                                        r0i++;
                                    }
                                    r3_a = r3_a.a;
                                    r2i++;
                                }
                            } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.g == 0 || r3_a.h == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("popEnterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                            r9_PrintWriter.print(" popExitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        r9_PrintWriter.print("Removed: ");
                                    } else {
                                        if (r0i == 0) {
                                            r9_PrintWriter.println("Removed:");
                                        }
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    if (r0i == 0) {
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    } else {
                                        r9_PrintWriter.println("Removed:");
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                } else {
                                    r9_PrintWriter.print("Removed: ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case ShareUtils.SHARE_COPY:
                        r0_String = "DETACH";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.e == 0 || r3_a.f == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("enterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                                r9_PrintWriter.print(" exitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                                if (r3_a.g == 0 || r3_a.h == 0) {
                                    r9_PrintWriter.print(r8_String);
                                    r9_PrintWriter.print("popEnterAnim=#");
                                    r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                    r9_PrintWriter.print(" popExitAnim=#");
                                    r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                }
                            } else if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            }
                        }
                        if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    r9_PrintWriter.print("Removed: ");
                                } else {
                                    if (r0i == 0) {
                                        r9_PrintWriter.println("Removed:");
                                    }
                                    r9_PrintWriter.print(r4_String);
                                    r9_PrintWriter.print("  #");
                                    r9_PrintWriter.print(r0i);
                                    r9_PrintWriter.print(": ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                    case ShareUtils.SHARE_COLLECT:
                        r0_String = "ATTACH";
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("  Op #");
                        r9_PrintWriter.print(r2i);
                        r9_PrintWriter.print(": ");
                        r9_PrintWriter.print(r0_String);
                        r9_PrintWriter.print(" ");
                        r9_PrintWriter.println(r3_a.d);
                        if (r10z) {
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.e == 0 || r3_a.f == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("enterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                            r9_PrintWriter.print(" exitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                            if (r3_a.g == 0 || r3_a.h == 0) {
                                r9_PrintWriter.print(r8_String);
                                r9_PrintWriter.print("popEnterAnim=#");
                                r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                                r9_PrintWriter.print(" popExitAnim=#");
                                r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                                if (r3_a.i == null || r3_a.i.size() <= 0) {
                                    r3_a = r3_a.a;
                                    r2i++;
                                } else {
                                    r0i = 0;
                                    while (r0i < r3_a.i.size()) {
                                        r9_PrintWriter.print(r4_String);
                                        if (r3_a.i.size() == 1) {
                                            r9_PrintWriter.print("Removed: ");
                                        } else {
                                            if (r0i == 0) {
                                                r9_PrintWriter.println("Removed:");
                                            }
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                        r9_PrintWriter.println(r3_a.i.get(r0i));
                                        r0i++;
                                    }
                                    r3_a = r3_a.a;
                                    r2i++;
                                }
                            } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        if (r0i == 0) {
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        } else {
                                            r9_PrintWriter.println("Removed:");
                                            r9_PrintWriter.print(r4_String);
                                            r9_PrintWriter.print("  #");
                                            r9_PrintWriter.print(r0i);
                                            r9_PrintWriter.print(": ");
                                        }
                                    } else {
                                        r9_PrintWriter.print("Removed: ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.g == 0 || r3_a.h == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("popEnterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                            r9_PrintWriter.print(" popExitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                            if (r3_a.i == null || r3_a.i.size() <= 0) {
                                r3_a = r3_a.a;
                                r2i++;
                            } else {
                                r0i = 0;
                                while (r0i < r3_a.i.size()) {
                                    r9_PrintWriter.print(r4_String);
                                    if (r3_a.i.size() == 1) {
                                        r9_PrintWriter.print("Removed: ");
                                    } else {
                                        if (r0i == 0) {
                                            r9_PrintWriter.println("Removed:");
                                        }
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                    r9_PrintWriter.println(r3_a.i.get(r0i));
                                    r0i++;
                                }
                                r3_a = r3_a.a;
                                r2i++;
                            }
                        } else if (r3_a.i == null || r3_a.i.size() <= 0) {
                            r3_a = r3_a.a;
                            r2i++;
                        } else {
                            r0i = 0;
                            while (r0i < r3_a.i.size()) {
                                r9_PrintWriter.print(r4_String);
                                if (r3_a.i.size() == 1) {
                                    if (r0i == 0) {
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    } else {
                                        r9_PrintWriter.println("Removed:");
                                        r9_PrintWriter.print(r4_String);
                                        r9_PrintWriter.print("  #");
                                        r9_PrintWriter.print(r0i);
                                        r9_PrintWriter.print(": ");
                                    }
                                } else {
                                    r9_PrintWriter.print("Removed: ");
                                }
                                r9_PrintWriter.println(r3_a.i.get(r0i));
                                r0i++;
                            }
                            r3_a = r3_a.a;
                            r2i++;
                        }
                        break;
                }
                r0_String = "cmd=" + r3_a.c;
                r9_PrintWriter.print(r8_String);
                r9_PrintWriter.print("  Op #");
                r9_PrintWriter.print(r2i);
                r9_PrintWriter.print(": ");
                r9_PrintWriter.print(r0_String);
                r9_PrintWriter.print(" ");
                r9_PrintWriter.println(r3_a.d);
                if (r10z) {
                    if (r3_a.e == 0 || r3_a.f == 0) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("enterAnim=#");
                        r9_PrintWriter.print(Integer.toHexString(r3_a.e));
                        r9_PrintWriter.print(" exitAnim=#");
                        r9_PrintWriter.println(Integer.toHexString(r3_a.f));
                        if (r3_a.g == 0 || r3_a.h == 0) {
                            r9_PrintWriter.print(r8_String);
                            r9_PrintWriter.print("popEnterAnim=#");
                            r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                            r9_PrintWriter.print(" popExitAnim=#");
                            r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                        }
                    } else if (r3_a.g == 0 || r3_a.h == 0) {
                        r9_PrintWriter.print(r8_String);
                        r9_PrintWriter.print("popEnterAnim=#");
                        r9_PrintWriter.print(Integer.toHexString(r3_a.g));
                        r9_PrintWriter.print(" popExitAnim=#");
                        r9_PrintWriter.println(Integer.toHexString(r3_a.h));
                    }
                }
                if (r3_a.i == null || r3_a.i.size() <= 0) {
                    r3_a = r3_a.a;
                    r2i++;
                } else {
                    r0i = 0;
                    while (r0i < r3_a.i.size()) {
                        r9_PrintWriter.print(r4_String);
                        if (r3_a.i.size() == 1) {
                            r9_PrintWriter.print("Removed: ");
                        } else {
                            if (r0i == 0) {
                                r9_PrintWriter.println("Removed:");
                            }
                            r9_PrintWriter.print(r4_String);
                            r9_PrintWriter.print("  #");
                            r9_PrintWriter.print(r0i);
                            r9_PrintWriter.print(": ");
                        }
                        r9_PrintWriter.println(r3_a.i.get(r0i));
                        r0i++;
                    }
                    r3_a = r3_a.a;
                    r2i++;
                }
            }
        }
    }

    public CharSequence getBreadCrumbShortTitle() {
        return this.r != 0 ? this.a.o.getText(this.r) : this.s;
    }

    public int getBreadCrumbShortTitleRes() {
        return this.r;
    }

    public CharSequence getBreadCrumbTitle() {
        return this.p != 0 ? this.a.o.getText(this.p) : this.q;
    }

    public int getBreadCrumbTitleRes() {
        return this.p;
    }

    public int getId() {
        return this.o;
    }

    public String getName() {
        return this.m;
    }

    public int getTransition() {
        return this.i;
    }

    public int getTransitionStyle() {
        return this.j;
    }

    public FragmentTransaction hide(Fragment r3_Fragment) {
        a r0_a = new a();
        r0_a.c = 4;
        r0_a.d = r3_Fragment;
        a(r0_a);
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.l;
    }

    public boolean isEmpty() {
        return this.d == 0;
    }

    public void popFromBackStack(boolean r8z) {
        if (l.a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        a(-1);
        a r3_a = this.c;
        while (r3_a != null) {
            Fragment r0_Fragment;
            switch (r3_a.c) {
                case XListViewHeader.STATE_READY:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.h;
                    this.a.removeFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    r3_a = r3_a.b;
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    r0_Fragment = r3_a.d;
                    if (r0_Fragment != null) {
                        r0_Fragment.P = r3_a.h;
                        this.a.removeFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    }
                    if (r3_a.i != null) {
                        int r1i = 0;
                        while (r1i < r3_a.i.size()) {
                            r0_Fragment = (Fragment) r3_a.i.get(r1i);
                            r0_Fragment.P = r3_a.g;
                            this.a.addFragment(r0_Fragment, false);
                            r1i++;
                        }
                    }
                    r3_a = r3_a.b;
                    break;
                case XListViewFooter.STATE_NOMORE:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.g;
                    this.a.addFragment(r0_Fragment, false);
                    r3_a = r3_a.b;
                    break;
                case XListViewFooter.STATE_NODATA:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.g;
                    this.a.showFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    r3_a = r3_a.b;
                    break;
                case ShareUtils.SHARE_SMS:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.h;
                    this.a.hideFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    r3_a = r3_a.b;
                    break;
                case ShareUtils.SHARE_COPY:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.g;
                    this.a.attachFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    r3_a = r3_a.b;
                    break;
                case ShareUtils.SHARE_COLLECT:
                    r0_Fragment = r3_a.d;
                    r0_Fragment.P = r3_a.g;
                    this.a.detachFragment(r0_Fragment, l.reverseTransit(this.i), this.j);
                    r3_a = r3_a.b;
                    break;
            }
            throw new IllegalArgumentException("Unknown cmd: " + r3_a.c);
        }
        if (r8z) {
            this.a.a(this.a.n, l.reverseTransit(this.i), this.j, true);
        }
        if (this.o >= 0) {
            this.a.freeBackStackIndex(this.o);
            this.o = -1;
        }
    }

    public FragmentTransaction remove(Fragment r3_Fragment) {
        a r0_a = new a();
        r0_a.c = 3;
        r0_a.d = r3_Fragment;
        a(r0_a);
        return this;
    }

    public FragmentTransaction replace(int r2i, Fragment r3_Fragment) {
        return replace(r2i, r3_Fragment, null);
    }

    public FragmentTransaction replace(int r3i, Fragment r4_Fragment, String r5_String) {
        if (r3i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else {
            a(r3i, r4_Fragment, r5_String, XListViewHeader.STATE_REFRESHING);
            return this;
        }
    }

    public void run() {
        if (l.a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if ((!this.k) || this.o >= 0) {
            a(1);
            a r4_a = this.b;
            while (r4_a != null) {
                Fragment r0_Fragment;
                switch (r4_a.c) {
                    case XListViewHeader.STATE_READY:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.e;
                        this.a.addFragment(r0_Fragment, false);
                        r4_a = r4_a.a;
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        Fragment r3_Fragment;
                        r0_Fragment = r4_a.d;
                        if (this.a.g != null) {
                            int r1i = 0;
                            r3_Fragment = r0_Fragment;
                            while (r1i < this.a.g.size()) {
                                r0_Fragment = (Fragment) this.a.g.get(r1i);
                                if (l.a) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + r3_Fragment + " old=" + r0_Fragment);
                                }
                                if (r3_Fragment == null || r0_Fragment.G == r3_Fragment.G) {
                                    if (r0_Fragment == r3_Fragment) {
                                        r3_Fragment = null;
                                        r4_a.d = null;
                                        r1i++;
                                    } else {
                                        if (r4_a.i == null) {
                                            r4_a.i = new ArrayList();
                                        }
                                        r4_a.i.add(r0_Fragment);
                                        r0_Fragment.P = r4_a.f;
                                        if (this.k) {
                                            r0_Fragment.A++;
                                            if (l.a) {
                                                Log.v("FragmentManager", "Bump nesting of " + r0_Fragment + " to " + r0_Fragment.A);
                                            }
                                        }
                                        this.a.removeFragment(r0_Fragment, this.i, this.j);
                                        r1i++;
                                    }
                                } else {
                                    r1i++;
                                }
                            }
                        } else {
                            r3_Fragment = r0_Fragment;
                        }
                        if (r3_Fragment != null) {
                            r3_Fragment.P = r4_a.e;
                            this.a.addFragment(r3_Fragment, false);
                        }
                        r4_a = r4_a.a;
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.f;
                        this.a.removeFragment(r0_Fragment, this.i, this.j);
                        r4_a = r4_a.a;
                        break;
                    case XListViewFooter.STATE_NODATA:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.f;
                        this.a.hideFragment(r0_Fragment, this.i, this.j);
                        r4_a = r4_a.a;
                        break;
                    case ShareUtils.SHARE_SMS:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.e;
                        this.a.showFragment(r0_Fragment, this.i, this.j);
                        r4_a = r4_a.a;
                        break;
                    case ShareUtils.SHARE_COPY:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.f;
                        this.a.detachFragment(r0_Fragment, this.i, this.j);
                        r4_a = r4_a.a;
                        break;
                    case ShareUtils.SHARE_COLLECT:
                        r0_Fragment = r4_a.d;
                        r0_Fragment.P = r4_a.e;
                        this.a.attachFragment(r0_Fragment, this.i, this.j);
                        r4_a = r4_a.a;
                        break;
                }
                throw new IllegalArgumentException("Unknown cmd: " + r4_a.c);
            }
            this.a.a(this.a.n, this.i, this.j, true);
            if (this.k) {
                this.a.a(this);
            }
        } else {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
    }

    public FragmentTransaction setBreadCrumbShortTitle(int r2i) {
        this.r = r2i;
        this.s = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence r2_CharSequence) {
        this.r = 0;
        this.s = r2_CharSequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int r2i) {
        this.p = r2i;
        this.q = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence r2_CharSequence) {
        this.p = 0;
        this.q = r2_CharSequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int r2i, int r3i) {
        return setCustomAnimations(r2i, r3i, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int r1i, int r2i, int r3i, int r4i) {
        this.e = r1i;
        this.f = r2i;
        this.g = r3i;
        this.h = r4i;
        return this;
    }

    public FragmentTransaction setTransition(int r1i) {
        this.i = r1i;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int r1i) {
        this.j = r1i;
        return this;
    }

    public FragmentTransaction show(Fragment r3_Fragment) {
        a r0_a = new a();
        r0_a.c = 5;
        r0_a.d = r3_Fragment;
        a(r0_a);
        return this;
    }

    public String toString() {
        StringBuilder r0_StringBuilder = new StringBuilder(128);
        r0_StringBuilder.append("BackStackEntry{");
        r0_StringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.o >= 0) {
            r0_StringBuilder.append(" #");
            r0_StringBuilder.append(this.o);
        }
        if (this.m != null) {
            r0_StringBuilder.append(" ");
            r0_StringBuilder.append(this.m);
        }
        r0_StringBuilder.append("}");
        return r0_StringBuilder.toString();
    }
}