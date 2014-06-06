package com.tencent.mm.sdk.openapi;

import android.content.Intent;

public interface IWXAPI {
    public int getWXAppSupportAPI();

    public boolean handleIntent(Intent r1_Intent, IWXAPIEventHandler r2_IWXAPIEventHandler);

    public boolean isWXAppInstalled();

    public boolean isWXAppSupportAPI();

    public boolean openWXApp();

    public boolean registerApp(String r1_String);

    public boolean sendReq(BaseReq r1_BaseReq);

    public boolean sendResp(BaseResp r1_BaseResp);

    public void unregisterApp();
}