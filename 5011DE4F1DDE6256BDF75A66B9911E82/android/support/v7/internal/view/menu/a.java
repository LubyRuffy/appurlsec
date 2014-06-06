package android.support.v7.internal.view.menu;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: ActionMenuPresenter.java
final class a implements Creator<SavedState> {
    a() {
    }

    public SavedState createFromParcel(Parcel r2_Parcel) {
        return new SavedState(r2_Parcel);
    }

    public SavedState[] newArray(int r2i) {
        return new SavedState[r2i];
    }
}