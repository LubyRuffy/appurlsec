package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command implements Parcelable {
    public static final String APPEND_CACHE_BUSTER = "appendCacheBuster";
    public static final String APPEND_QUEUE_TIME = "appendQueueTime";
    public static final String APPEND_VERSION = "appendVersion";
    public static final Creator<Command> CREATOR;
    private String a;
    private String b;
    private String c;

    static {
        CREATOR = new a();
    }

    private Command(Parcel r1_Parcel) {
        a(r1_Parcel);
    }

    public Command(String r1_String, String r2_String, String r3_String) {
        this.a = r1_String;
        this.b = r2_String;
        this.c = r3_String;
    }

    private void a(Parcel r2_Parcel) {
        this.a = r2_Parcel.readString();
        this.b = r2_Parcel.readString();
        this.c = r2_Parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.a;
    }

    public String getUrlParam() {
        return this.b;
    }

    public String getValue() {
        return this.c;
    }

    public void writeToParcel(Parcel r2_Parcel, int r3i) {
        r2_Parcel.writeString(this.a);
        r2_Parcel.writeString(this.b);
        r2_Parcel.writeValue(this.c);
    }
}