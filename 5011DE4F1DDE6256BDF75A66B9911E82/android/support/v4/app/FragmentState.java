package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    Bundle j;
    Fragment k;

    static {
        CREATOR = new s();
    }

    public FragmentState(Parcel r4_Parcel) {
        boolean r1z = true;
        this.a = r4_Parcel.readString();
        this.b = r4_Parcel.readInt();
        this.c = r4_Parcel.readInt() != 0;
        this.d = r4_Parcel.readInt();
        this.e = r4_Parcel.readInt();
        this.f = r4_Parcel.readString();
        this.g = r4_Parcel.readInt() != 0;
        if (r4_Parcel.readInt() != 0) {
            this.h = r1z;
            this.i = r4_Parcel.readBundle();
            this.j = r4_Parcel.readBundle();
        } else {
            r1z = false;
            this.h = r1z;
            this.i = r4_Parcel.readBundle();
            this.j = r4_Parcel.readBundle();
        }
    }

    public FragmentState(Fragment r2_Fragment) {
        this.a = r2_Fragment.getClass().getName();
        this.b = r2_Fragment.o;
        this.c = r2_Fragment.x;
        this.d = r2_Fragment.F;
        this.e = r2_Fragment.G;
        this.f = r2_Fragment.H;
        this.g = r2_Fragment.K;
        this.h = r2_Fragment.J;
        this.i = r2_Fragment.q;
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentActivity r4_FragmentActivity, Fragment r5_Fragment) {
        if (this.k != null) {
            return this.k;
        }
        if (this.i != null) {
            this.i.setClassLoader(r4_FragmentActivity.getClassLoader());
        }
        this.k = Fragment.instantiate(r4_FragmentActivity, this.a, this.i);
        if (this.j != null) {
            this.j.setClassLoader(r4_FragmentActivity.getClassLoader());
            this.k.m = this.j;
        }
        this.k.a(this.b, r5_Fragment);
        this.k.x = this.c;
        this.k.z = true;
        this.k.F = this.d;
        this.k.G = this.e;
        this.k.H = this.f;
        this.k.K = this.g;
        this.k.J = this.h;
        this.k.B = r4_FragmentActivity.b;
        if (l.a) {
            Log.v("FragmentManager", "Instantiated fragment " + this.k);
        }
        return this.k;
    }

    public void writeToParcel(Parcel r4_Parcel, int r5i) {
        int r1i = 1;
        r4_Parcel.writeString(this.a);
        r4_Parcel.writeInt(this.b);
        r4_Parcel.writeInt(this.c ? 1 : 0);
        r4_Parcel.writeInt(this.d);
        r4_Parcel.writeInt(this.e);
        r4_Parcel.writeString(this.f);
        r4_Parcel.writeInt(this.g ? 1 : 0);
        if (this.h) {
            r4_Parcel.writeInt(r1i);
            r4_Parcel.writeBundle(this.i);
            r4_Parcel.writeBundle(this.j);
        } else {
            r1i = 0;
            r4_Parcel.writeInt(r1i);
            r4_Parcel.writeBundle(this.i);
            r4_Parcel.writeBundle(this.j);
        }
    }
}