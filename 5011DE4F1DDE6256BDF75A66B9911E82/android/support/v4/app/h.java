package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment.SavedState;

// compiled from: Fragment.java
final class h implements Creator<SavedState> {
    h() {
    }

    public SavedState createFromParcel(Parcel r3_Parcel) {
        return new SavedState(r3_Parcel, null);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}