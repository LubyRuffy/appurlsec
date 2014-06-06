package org.apache.cordova.api;

import android.app.Activity;
import android.content.Intent;
import java.util.concurrent.ExecutorService;

public interface CordovaInterface {
    public Activity getActivity();

    public ExecutorService getThreadPool();

    public Object onMessage(String r1_String, Object r2_Object);

    public void setActivityResultCallback(CordovaPlugin r1_CordovaPlugin);

    public void startActivityForResult(CordovaPlugin r1_CordovaPlugin, Intent r2_Intent, int r3i);
}