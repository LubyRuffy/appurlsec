package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: FragmentTabHost.java
final class t implements Creator<SavedState> {
    t() {
    }

    public SavedState createFromParcel(Parcel r3_Parcel) {
        return new SavedState(null);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}