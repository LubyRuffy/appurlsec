package com.google.analytics.tracking.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.analytics.internal.Command;
import com.google.android.gms.analytics.internal.IAnalyticsService;
import com.google.android.gms.analytics.internal.IAnalyticsService.Stub;
import java.util.List;
import java.util.Map;

class AnalyticsGmsCoreClient implements c {
    public static final int BIND_FAILED = 1;
    public static final String KEY_APP_PACKAGE_NAME = "app_package_name";
    public static final int REMOTE_EXECUTION_FAILED = 2;
    private ServiceConnection a;
    private OnConnectedListener b;
    private OnConnectionFailedListener c;
    private Context d;
    private IAnalyticsService e;

    public static interface OnConnectedListener {
        public void onConnected();

        public void onDisconnected();
    }

    public static interface OnConnectionFailedListener {
        public void onConnectionFailed(int r1i, Intent r2_Intent);
    }

    final class a implements ServiceConnection {
        a() {
        }

        public void onServiceConnected(ComponentName r4_ComponentName, IBinder r5_IBinder) {
            z.b("service connected, binder: " + r5_IBinder);
            try {
                if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(r5_IBinder.getInterfaceDescriptor())) {
                    z.b("bound to service");
                    AnalyticsGmsCoreClient.this.e = Stub.asInterface(r5_IBinder);
                    AnalyticsGmsCoreClient.this.c();
                    return;
                }
            } catch (RemoteException e) {
            }
            AnalyticsGmsCoreClient.this.d.unbindService(this);
            AnalyticsGmsCoreClient.this = null;
            AnalyticsGmsCoreClient.this.c.onConnectionFailed(REMOTE_EXECUTION_FAILED, null);
        }

        public void onServiceDisconnected(ComponentName r3_ComponentName) {
            z.b("service disconnected: " + r3_ComponentName);
            AnalyticsGmsCoreClient.this = null;
            AnalyticsGmsCoreClient.this.b.onDisconnected();
        }
    }

    public AnalyticsGmsCoreClient(Context r1_Context, OnConnectedListener r2_OnConnectedListener, OnConnectionFailedListener r3_OnConnectionFailedListener) {
        this.d = r1_Context;
        this.b = r2_OnConnectedListener;
        this.c = r3_OnConnectionFailedListener;
    }

    private IAnalyticsService b() {
        a();
        return this.e;
    }

    private void c() {
        d();
    }

    private void d() {
        this.b.onConnected();
    }

    protected void a() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void clearHits() {
        try {
            b().clearHits();
        } catch (RemoteException e) {
            z.c("clear hits failed: " + e);
        }
    }

    public void connect() {
        Intent r0_Intent = new Intent("com.google.android.gms.analytics.service.START");
        r0_Intent.putExtra(KEY_APP_PACKAGE_NAME, this.d.getPackageName());
        if (this.a != null) {
            z.c("Calling connect() while still connected, missing disconnect().");
        } else {
            this.a = new a();
            boolean r1z = this.d.bindService(r0_Intent, this.a, 129);
            z.e("connect: bindService returned " + r1z + " for " + r0_Intent);
            if (!r1z) {
                this.a = null;
                this.c.onConnectionFailed(BIND_FAILED, null);
            }
        }
    }

    public void disconnect() {
        this.e = null;
        if (this.a != null) {
            this.d.unbindService(this.a);
            this.a = null;
            this.b.onDisconnected();
        }
    }

    public boolean isConnected() {
        return this.e != null;
    }

    public void sendHit(Map<String, String> r7_Map_String__String, long r8j, String r10_String, List<Command> r11_List_Command) {
        try {
            b().sendHit(r7_Map_String__String, r8j, r10_String, r11_List_Command);
        } catch (RemoteException e) {
            z.c("sendHit failed: " + e);
        }
    }
}