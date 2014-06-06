package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: DrawerLayout.java
final class d implements Creator<SavedState> {
    d() {
    }

    public SavedState createFromParcel(Parcel r2_Parcel) {
        return new SavedState(r2_Parcel);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}