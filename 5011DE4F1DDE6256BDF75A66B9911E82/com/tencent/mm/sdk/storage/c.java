package com.tencent.mm.sdk.storage;

import com.tencent.mm.sdk.storage.MStorage.IOnStorageLoaded;

class c extends MStorageEvent<IOnStorageLoaded, String> {
    final /* synthetic */ MStorage a;

    c(MStorage r1_MStorage) {
        this.a = r1_MStorage;
    }

    protected /* synthetic */ void a(Object r2_Object, Object r3_Object) {
        MStorage r0_MStorage = this.a;
        ((IOnStorageLoaded) r2_Object).onNotifyLoaded();
    }
}