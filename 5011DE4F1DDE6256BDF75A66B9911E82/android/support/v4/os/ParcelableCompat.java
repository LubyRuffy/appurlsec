package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ParcelableCompat {

    static class a<T> implements Creator<T> {
        final ParcelableCompatCreatorCallbacks<T> a;

        public a(ParcelableCompatCreatorCallbacks<T> r1_ParcelableCompatCreatorCallbacks_T) {
            this.a = r1_ParcelableCompatCreatorCallbacks_T;
        }

        public T createFromParcel(Parcel r3_Parcel) {
            return this.a.createFromParcel(r3_Parcel, null);
        }

        public T[] newArray(int r2i) {
            return this.a.newArray(r2i);
        }
    }

    public static <T> Creator<T> newCreator(ParcelableCompatCreatorCallbacks<T> r2_ParcelableCompatCreatorCallbacks_T) {
        if (VERSION.SDK_INT >= 13) {
            c.a(r2_ParcelableCompatCreatorCallbacks_T);
        }
        return new a(r2_ParcelableCompatCreatorCallbacks_T);
    }
}