package com.qq.e.v2.plugininterfaces;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

public interface ServiceDelegate {
    public IBinder onBind(Intent r1_Intent);

    public void onConfigurationChanged(Configuration r1_Configuration);

    public void onCreate();

    public void onDestroy();

    public void onLowMemory();

    public void onRebind(Intent r1_Intent);

    public int onStartCommand(Intent r1_Intent, int r2i, int r3i);

    public void onTaskRemoved(Intent r1_Intent);

    public void onTrimMemory(int r1i);

    public boolean onUnbind(Intent r1_Intent);
}