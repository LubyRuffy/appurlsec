package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: FragmentManager.java
final class r implements Creator<FragmentManagerState> {
    r() {
    }

    public FragmentManagerState createFromParcel(Parcel r2_Parcel) {
        return new FragmentManagerState(r2_Parcel);
    }

    public FragmentManagerState[] newArray(int r2i) {
        return new FragmentManagerState[r2i];
    }
}