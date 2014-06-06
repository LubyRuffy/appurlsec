package com.tencent.mm.sdk.platformtools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

public class SensorController extends BroadcastReceiver implements SensorEventListener {
    private static float a;
    private static float d;
    private SensorManager b;
    private float c;
    private SensorEventCallBack e;
    private Sensor f;
    private final boolean g;
    private boolean h;
    private boolean i;

    public static interface SensorEventCallBack {
        public void onSensorEvent(boolean r1z);
    }

    static {
        a = 4.2949673E9f;
        d = 0.5f;
    }

    public SensorController(Context r4_Context) {
        this.h = false;
        this.i = false;
        this.b = (SensorManager) r4_Context.getSystemService("sensor");
        this.f = this.b.getDefaultSensor(Base64.DONT_BREAK_LINES);
        this.g = this.f != null;
        this.c = d + 1.0f;
    }

    public boolean isSensorEnable() {
        return this.g;
    }

    public void onAccuracyChanged(Sensor r1_Sensor, int r2i) {
    }

    public void onReceive(Context r5_Context, Intent r6_Intent) {
        if (r6_Intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            int r0i = r6_Intent.getIntExtra(QsbkDatabase.STATE, 0);
            if (r0i == 1) {
                this.h = true;
            }
            if (r0i == 0) {
                this.h = false;
            }
        }
    }

    public void onSensorChanged(SensorEvent r5_SensorEvent) {
        if (this.h) {
        } else {
            float r0f = r5_SensorEvent.values[0];
            switch (r5_SensorEvent.sensor.getType()) {
                case Base64.DONT_BREAK_LINES:
                    if (r0f < a) {
                        a = r0f;
                        d = 0.5f + r0f;
                    }
                    if (this.c < d || r0f >= d) {
                        if (this.c > d || r0f <= d || this.e == null) {
                            this.c = r0f;
                        } else {
                            Log.v("MicroMsg.SensorController", "sensor event true");
                            this.e.onSensorEvent(true);
                            this.c = r0f;
                        }
                    } else {
                        if (this.e != null) {
                            Log.v("MicroMsg.SensorController", "sensor event false");
                            this.e.onSensorEvent(false);
                        }
                        this.c = r0f;
                    }
            }
        }
    }

    public void removeSensorCallBack() {
        Log.v("MicroMsg.SensorController", "sensor callback removed");
        this.b.unregisterListener(this, this.f);
        this.b.unregisterListener(this);
        this.i = false;
        this.e = null;
    }

    public void setSensorCallBack(SensorEventCallBack r4_SensorEventCallBack) {
        Log.v("MicroMsg.SensorController", "sensor callback set");
        if (!this.i) {
            this.b.registerListener(this, this.f, XListViewHeader.STATE_REFRESHING);
            this.i = true;
        }
        this.e = r4_SensorEventCallBack;
    }
}