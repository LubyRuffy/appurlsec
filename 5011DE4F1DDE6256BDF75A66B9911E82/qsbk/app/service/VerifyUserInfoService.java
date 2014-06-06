package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import org.json.JSONObject;
import qsbk.app.QsbkApp;

public class VerifyUserInfoService extends Service {
    private static int a;
    private Handler b;

    static {
        a = 0;
    }

    public VerifyUserInfoService() {
        this.b = new c(this);
    }

    static /* synthetic */ int a() {
        int r0i = a;
        a = r0i + 1;
        return r0i;
    }

    private void b() {
        new f(this, "qbk-VerifyUserInfo2").start();
    }

    private void c() {
        new h(this, "qbk-VerifyUserInfo4").start();
    }

    public IBinder onBind(Intent r2_Intent) {
        return null;
    }

    public void onStart(Intent r4_Intent, int r5i) {
        super.onStart(r4_Intent, r5i);
        if (QsbkApp.currentUser != null) {
            new e(this, "qbk-VerifyUserInfo1").start();
        } else {
            QsbkApp.mContext.startService(new Intent(QsbkApp.mContext, VersionCheckService.class));
            stopSelf();
        }
    }

    public int onStartCommand(Intent r2_Intent, int r3i, int r4i) {
        return super.onStartCommand(r2_Intent, r3i, r4i);
    }

    public void updateUserInfo(JSONObject r3_JSONObject) {
        new g(this, "qbk-VerifyUserInfo3", r3_JSONObject).start();
    }
}