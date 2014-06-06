package qsbk.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class AppRegister extends BroadcastReceiver {
    public void onReceive(Context r3_Context, Intent r4_Intent) {
        WXAPIFactory.createWXAPI(r3_Context, null).registerApp(Constants.APP_ID);
    }
}