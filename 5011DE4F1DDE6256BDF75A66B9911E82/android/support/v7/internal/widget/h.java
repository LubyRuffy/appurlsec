package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: ActionBarView.java
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