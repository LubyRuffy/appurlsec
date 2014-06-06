package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public abstract class BaseResp {
    public int errCode;
    public String errStr;
    public String transaction;

    public static interface ErrCode {
        public static final int ERR_AUTH_DENIED = -4;
        public static final int ERR_COMM = -1;
        public static final int ERR_OK = 0;
        public static final int ERR_SENT_FAILED = -3;
        public static final int ERR_UNSUPPORT = -5;
        public static final int ERR_USER_CANCEL = -2;
    }

    abstract boolean a();

    public void fromBundle(Bundle r2_Bundle) {
        this.errCode = r2_Bundle.getInt("_wxapi_baseresp_errcode");
        this.errStr = r2_Bundle.getString("_wxapi_baseresp_errstr");
        this.transaction = r2_Bundle.getString("_wxapi_baseresp_transaction");
    }

    public abstract int getType();

    public void toBundle(Bundle r3_Bundle) {
        r3_Bundle.putInt("_wxapi_command_type", getType());
        r3_Bundle.putInt("_wxapi_baseresp_errcode", this.errCode);
        r3_Bundle.putString("_wxapi_baseresp_errstr", this.errStr);
        r3_Bundle.putString("_wxapi_baseresp_transaction", this.transaction);
    }
}