package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: AbsSpinnerICS.java
final class c implements Creator<SavedState> {
    c() {
    }

    public SavedState createFromParcel(Parcel r3_Parcel) {
        return new SavedState(null);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}