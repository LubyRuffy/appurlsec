package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

// compiled from: BackStackRecord.java
final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR;
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;

    static {
        CREATOR = new f();
    }

    public BackStackState(Parcel r2_Parcel) {
        this.a = r2_Parcel.createIntArray();
        this.b = r2_Parcel.readInt();
        this.c = r2_Parcel.readInt();
        this.d = r2_Parcel.readString();
        this.e = r2_Parcel.readInt();
        this.f = r2_Parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r2_Parcel);
        this.h = r2_Parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r2_Parcel);
    }

    public BackStackState(l r9_l, e r10_e) {
        a r1_a = r10_e.b;
        int r0i = 0;
        while (r1_a != null) {
            if (r1_a.i != null) {
                r0i += r1_a.i.size();
            }
            r1_a = r1_a.a;
        }
        this.a = new int[(r0i + r10_e.d * 7)];
        if (r10_e.k) {
            a r5_a = r10_e.b;
            r0i = 0;
            while (r5_a != null) {
                int r2i = r0i + 1;
                this.a[r0i] = r5_a.c;
                int r4i = r2i + 1;
                this.a[r2i] = r5_a.d != null ? r5_a.d.o : -1;
                int r1i = r4i + 1;
                this.a[r4i] = r5_a.e;
                r2i = r1i + 1;
                this.a[r1i] = r5_a.f;
                r1i = r2i + 1;
                this.a[r2i] = r5_a.g;
                r2i = r1i + 1;
                this.a[r1i] = r5_a.h;
                if (r5_a.i != null) {
                    int r6i = r5_a.i.size();
                    r1i = r2i + 1;
                    this.a[r2i] = r6i;
                    r2i = 0;
                    while (r2i < r6i) {
                        this.a[r1i] = ((Fragment) r5_a.i.get(r2i)).o;
                        r2i++;
                        r1i++;
                    }
                    r0i = r1i;
                } else {
                    r0i = r2i + 1;
                    this.a[r2i] = 0;
                }
                r5_a = r5_a.a;
            }
            this.b = r10_e.i;
            this.c = r10_e.j;
            this.d = r10_e.m;
            this.e = r10_e.o;
            this.f = r10_e.p;
            this.g = r10_e.q;
            this.h = r10_e.r;
            this.i = r10_e.s;
        } else {
            throw new IllegalStateException("Not on back stack");
        }
    }

    public int describeContents() {
        return 0;
    }

    public e instantiate(l r12_l) {
        e r6_e = new e(r12_l);
        int r1i = 0;
        int r0i = 0;
        while (r0i < this.a.length) {
            a r7_a = new a();
            int r4i = r0i + 1;
            r7_a.c = this.a[r0i];
            if (l.a) {
                Log.v("FragmentManager", "Instantiate " + r6_e + " op #" + r1i + " base fragment #" + this.a[r4i]);
            }
            int r3i = r4i + 1;
            r0i = this.a[r4i];
            if (r0i >= 0) {
                r7_a.d = (Fragment) r12_l.f.get(r0i);
            } else {
                r7_a.d = null;
            }
            r4i = r3i + 1;
            r7_a.e = this.a[r3i];
            r3i = r4i + 1;
            r7_a.f = this.a[r4i];
            r4i = r3i + 1;
            r7_a.g = this.a[r3i];
            int r5i = r4i + 1;
            r7_a.h = this.a[r4i];
            r3i = r5i + 1;
            int r8i = this.a[r5i];
            if (r8i > 0) {
                r7_a.i = new ArrayList(r8i);
                r4i = 0;
                while (r4i < r8i) {
                    if (l.a) {
                        Log.v("FragmentManager", "Instantiate " + r6_e + " set remove fragment #" + this.a[r3i]);
                    }
                    r7_a.i.add((Fragment) r12_l.f.get(this.a[r3i]));
                    r4i++;
                    r3i++;
                }
            }
            r6_e.a(r7_a);
            r1i++;
            r0i = r3i;
        }
        r6_e.i = this.b;
        r6_e.j = this.c;
        r6_e.m = this.d;
        r6_e.o = this.e;
        r6_e.k = true;
        r6_e.p = this.f;
        r6_e.q = this.g;
        r6_e.r = this.h;
        r6_e.s = this.i;
        r6_e.a(1);
        return r6_e;
    }

    public void writeToParcel(Parcel r3_Parcel, int r4i) {
        r3_Parcel.writeIntArray(this.a);
        r3_Parcel.writeInt(this.b);
        r3_Parcel.writeInt(this.c);
        r3_Parcel.writeString(this.d);
        r3_Parcel.writeInt(this.e);
        r3_Parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, r3_Parcel, 0);
        r3_Parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, r3_Parcel, 0);
    }
}