package qsbk.app.thirdparty.sso;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.sina.sso.RemoteSSO;
import com.sina.sso.RemoteSSO.Stub;
import qsbk.app.thirdparty.ThirdParty;

// compiled from: SsoHandler.java
class a implements ServiceConnection {
    final /* synthetic */ SsoHandler a;

    a(SsoHandler r1_SsoHandler) {
        this.a = r1_SsoHandler;
    }

    public void onServiceConnected(ComponentName r6_ComponentName, IBinder r7_IBinder) {
        RemoteSSO r0_RemoteSSO = Stub.asInterface(r7_IBinder);
        try {
            SsoHandler.a(r0_RemoteSSO.getPackageName());
            SsoHandler.b(r0_RemoteSSO.getActivityName());
            if (!SsoHandler.a(this.a, SsoHandler.a(this.a), ThirdParty.app_key, new String[0], SsoHandler.d(this.a))) {
                SsoHandler.c(this.a).startAuthDialog(SsoHandler.a(this.a), SsoHandler.b(this.a));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onServiceDisconnected(ComponentName r4_ComponentName) {
        SsoHandler.c(this.a).startAuthDialog(SsoHandler.a(this.a), SsoHandler.b(this.a));
    }
}