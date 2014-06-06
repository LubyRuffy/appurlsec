package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: BackStackRecord.java
final class f implements Creator<BackStackState> {
    f() {
    }

    public BackStackState createFromParcel(Parcel r2_Parcel) {
        return new BackStackState(r2_Parcel);
    }

    public BackStackState[] newArray(int r2i) {
        return new BackStackState[r2i];
    }
}