package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.ViewPager.SavedState;

// compiled from: ViewPager.java
final class ae implements ParcelableCompatCreatorCallbacks<SavedState> {
    ae() {
    }

    public SavedState createFromParcel(Parcel r2_Parcel, ClassLoader r3_ClassLoader) {
        return new SavedState(r2_Parcel, r3_ClassLoader);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}