package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Command.java
final class a implements Creator<Command> {
    a() {
    }

    public Command createFromParcel(Parcel r3_Parcel) {
        return new Command(null);
    }

    public Command[] newArray(int r2i) {
        return new Command[r2i];
    }
}