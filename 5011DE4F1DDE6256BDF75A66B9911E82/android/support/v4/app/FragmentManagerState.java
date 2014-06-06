package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR;
    FragmentState[] a;
    int[] b;
    BackStackState[] c;

    static {
        CREATOR = new r();
    }

    public FragmentManagerState(Parcel r2_Parcel) {
        this.a = (FragmentState[]) r2_Parcel.createTypedArray(FragmentState.CREATOR);
        this.b = r2_Parcel.createIntArray();
        this.c = (BackStackState[]) r2_Parcel.createTypedArray(BackStackState.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel r2_Parcel, int r3i) {
        r2_Parcel.writeTypedArray(this.a, r3i);
        r2_Parcel.writeIntArray(this.b);
        r2_Parcel.writeTypedArray(this.c, r3i);
    }
}