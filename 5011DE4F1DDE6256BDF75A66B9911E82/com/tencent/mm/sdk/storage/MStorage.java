package com.tencent.mm.sdk.storage;

import android.os.Looper;

public abstract class MStorage {
    private final MStorageEvent<IOnStorageChange, String> a;
    private final MStorageEvent<IOnStorageLoaded, String> b;

    public static interface IOnStorageChange {
        public void onNotifyChange(String r1_String);
    }

    public static interface IOnStorageLoaded {
        public void onNotifyLoaded();
    }

    public MStorage() {
        this.a = new b(this);
        this.b = new c(this);
    }

    public void add(IOnStorageChange r3_IOnStorageChange) {
        this.a.add(r3_IOnStorageChange, Looper.getMainLooper());
    }

    public void addLoadedListener(IOnStorageLoaded r3_IOnStorageLoaded) {
        this.b.add(r3_IOnStorageLoaded, Looper.getMainLooper());
    }

    public void doNotify() {
        this.a.event("*");
        this.a.doNotify();
    }

    public void doNotify(String r2_String) {
        this.a.event(r2_String);
        this.a.doNotify();
    }

    public void lock() {
        this.a.lock();
    }

    public void remove(IOnStorageChange r2_IOnStorageChange) {
        this.a.remove(r2_IOnStorageChange);
    }

    public void removeLoadedListener(IOnStorageLoaded r2_IOnStorageLoaded) {
        this.b.remove(r2_IOnStorageLoaded);
    }

    public void unlock() {
        this.a.unlock();
    }
}