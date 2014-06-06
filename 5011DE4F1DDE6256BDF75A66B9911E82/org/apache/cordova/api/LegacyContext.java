package org.apache.cordova.api;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import java.util.concurrent.ExecutorService;

public class LegacyContext implements CordovaInterface {
    private static final String LOG_TAG = "Deprecation Notice";
    private CordovaInterface cordova;

    public LegacyContext(CordovaInterface r1_CordovaInterface) {
        this.cordova = r1_CordovaInterface;
    }

    public boolean bindService(Intent r3_Intent, ServiceConnection r4_ServiceConnection, int r5i) {
        Log.i(LOG_TAG, "Replace ctx.bindService() with cordova.getActivity().bindService()");
        return this.cordova.getActivity().bindService(r3_Intent, r4_ServiceConnection, r5i);
    }

    public void cancelLoadUrl() {
        Log.i(LOG_TAG, "Replace ctx.cancelLoadUrl() with cordova.cancelLoadUrl()");
    }

    public Activity getActivity() {
        Log.i(LOG_TAG, "Replace ctx.getActivity() with cordova.getActivity()");
        return this.cordova.getActivity();
    }

    public Context getApplicationContext() {
        Log.i(LOG_TAG, "Replace ctx.getApplicationContext() with cordova.getActivity().getApplicationContext()");
        return this.cordova.getActivity().getApplicationContext();
    }

    public AssetManager getAssets() {
        Log.i(LOG_TAG, "Replace ctx.getAssets() with cordova.getActivity().getAssets()");
        return this.cordova.getActivity().getAssets();
    }

    public Context getContext() {
        Log.i(LOG_TAG, "Replace ctx.getContext() with cordova.getContext()");
        return this.cordova.getActivity();
    }

    public PackageManager getPackageManager() {
        Log.i(LOG_TAG, "Replace ctx.getPackageManager() with cordova.getActivity().getPackageManager()");
        return this.cordova.getActivity().getPackageManager();
    }

    public Resources getResources() {
        Log.i(LOG_TAG, "Replace ctx.getResources() with cordova.getActivity().getResources()");
        return this.cordova.getActivity().getResources();
    }

    public SharedPreferences getSharedPreferences(String r3_String, int r4i) {
        Log.i(LOG_TAG, "Replace ctx.getSharedPreferences() with cordova.getActivity().getSharedPreferences()");
        return this.cordova.getActivity().getSharedPreferences(r3_String, r4i);
    }

    public Object getSystemService(String r3_String) {
        Log.i(LOG_TAG, "Replace ctx.getSystemService() with cordova.getActivity().getSystemService()");
        return this.cordova.getActivity().getSystemService(r3_String);
    }

    public ExecutorService getThreadPool() {
        Log.i(LOG_TAG, "Replace ctx.getThreadPool() with cordova.getThreadPool()");
        return this.cordova.getThreadPool();
    }

    public Object onMessage(String r3_String, Object r4_Object) {
        Log.i(LOG_TAG, "Replace ctx.onMessage() with cordova.onMessage()");
        return this.cordova.onMessage(r3_String, r4_Object);
    }

    public void runOnUiThread(Runnable r3_Runnable) {
        Log.i(LOG_TAG, "Replace ctx.runOnUiThread() with cordova.getActivity().runOnUiThread()");
        this.cordova.getActivity().runOnUiThread(r3_Runnable);
    }

    public void setActivityResultCallback(CordovaPlugin r3_CordovaPlugin) {
        Log.i(LOG_TAG, "Replace ctx.setActivityResultCallback() with cordova.setActivityResultCallback()");
        this.cordova.setActivityResultCallback(r3_CordovaPlugin);
    }

    public void startActivity(Intent r3_Intent) {
        Log.i(LOG_TAG, "Replace ctx.startActivity() with cordova.getActivity().startActivity()");
        this.cordova.getActivity().startActivity(r3_Intent);
    }

    public void startActivityForResult(CordovaPlugin r3_CordovaPlugin, Intent r4_Intent, int r5i) {
        Log.i(LOG_TAG, "Replace ctx.startActivityForResult() with cordova.startActivityForResult()");
        this.cordova.startActivityForResult(r3_CordovaPlugin, r4_Intent, r5i);
    }

    public ComponentName startService(Intent r3_Intent) {
        Log.i(LOG_TAG, "Replace ctx.startService() with cordova.getActivity().startService()");
        return this.cordova.getActivity().startService(r3_Intent);
    }

    public void unbindService(ServiceConnection r3_ServiceConnection) {
        Log.i(LOG_TAG, "Replace ctx.unbindService() with cordova.getActivity().unbindService()");
        this.cordova.getActivity().unbindService(r3_ServiceConnection);
    }

    public void unregisterReceiver(BroadcastReceiver r3_BroadcastReceiver) {
        Log.i(LOG_TAG, "Replace ctx.unregisterReceiver() with cordova.getActivity().unregisterReceiver()");
        this.cordova.getActivity().unregisterReceiver(r3_BroadcastReceiver);
    }
}