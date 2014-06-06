package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public abstract class BaseReq {
    public String transaction;

    abstract boolean a();

    public void fromBundle(Bundle r2_Bundle) {
        this.transaction = r2_Bundle.getString("_wxapi_basereq_transaction");
    }

    public abstract int getType();

    public void toBundle(Bundle r3_Bundle) {
        r3_Bundle.putInt("_wxapi_command_type", getType());
        r3_Bundle.putString("_wxapi_basereq_transaction", this.transaction);
    }
}