package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Fragment.java
final class s implements Creator<FragmentState> {
    s() {
    }

    public FragmentState createFromParcel(Parcel r2_Parcel) {
        return new FragmentState(r2_Parcel);
    }

    public FragmentState[] newArray(int r2i) {
        return new FragmentState[r2i];
    }
}