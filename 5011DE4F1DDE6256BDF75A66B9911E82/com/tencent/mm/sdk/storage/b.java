package com.tencent.mm.sdk.storage;

import com.tencent.mm.sdk.storage.MStorage.IOnStorageChange;

class b extends MStorageEvent<IOnStorageChange, String> {
    final /* synthetic */ MStorage a;

    b(MStorage r1_MStorage) {
        this.a = r1_MStorage;
    }

    protected /* synthetic */ void a(Object r2_Object, Object r3_Object) {
        MStorage r0_MStorage = this.a;
        ((IOnStorageChange) r2_Object).onNotifyChange((String) r3_Object);
    }
}