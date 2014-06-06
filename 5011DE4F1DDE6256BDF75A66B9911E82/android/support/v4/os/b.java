package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

// compiled from: ParcelableCompatHoneycombMR2.java
class b<T> implements ClassLoaderCreator<T> {
    private final ParcelableCompatCreatorCallbacks<T> a;

    public b(ParcelableCompatCreatorCallbacks<T> r1_ParcelableCompatCreatorCallbacks_T) {
        this.a = r1_ParcelableCompatCreatorCallbacks_T;
    }

    public T createFromParcel(Parcel r3_Parcel) {
        return this.a.createFromParcel(r3_Parcel, null);
    }

    public T createFromParcel(Parcel r2_Parcel, ClassLoader r3_ClassLoader) {
        return this.a.createFromParcel(r2_Parcel, r3_ClassLoader);
    }

    public T[] newArray(int r2i) {
        return this.a.newArray(r2i);
    }
}