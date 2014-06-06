package com.tencent.cloudsdk.common.record.info;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public final class Global {
    private static Context a;
    private static final byte[] b;
    private static boolean c;

    static {
        b = new byte[0];
        c = false;
    }

    public static final boolean bindService(Intent r1_Intent, ServiceConnection r2_ServiceConnection, int r3i) {
        return getContext().bindService(r1_Intent, r2_ServiceConnection, r3i);
    }

    public static final int checkCallingOrSelfPermission(String r1_String) {
        return getContext().checkCallingOrSelfPermission(r1_String);
    }

    public static final int checkCallingOrSelfUriPermission(Uri r1_Uri, int r2i) {
        return getContext().checkCallingOrSelfUriPermission(r1_Uri, r2i);
    }

    public static final int checkCallingPermission(String r1_String) {
        return getContext().checkCallingPermission(r1_String);
    }

    public static final int checkCallingUriPermission(Uri r1_Uri, int r2i) {
        return getContext().checkCallingUriPermission(r1_Uri, r2i);
    }

    public static final int checkPermission(String r1_String, int r2i, int r3i) {
        return getContext().checkPermission(r1_String, r2i, r3i);
    }

    public static final int checkUriPermission(Uri r1_Uri, int r2i, int r3i, int r4i) {
        return getContext().checkUriPermission(r1_Uri, r2i, r3i, r4i);
    }

    public static final int checkUriPermission(Uri r7_Uri, String r8_String, String r9_String, int r10i, int r11i, int r12i) {
        return getContext().checkUriPermission(r7_Uri, r8_String, r9_String, r10i, r11i, r12i);
    }

    public static final void clearWallpaper() throws IOException {
        getContext().clearWallpaper();
    }

    public static final Context createPackageContext(String r1_String, int r2i) throws NameNotFoundException {
        return getContext().createPackageContext(r1_String, r2i);
    }

    public static final String currentProcessName() {
        ActivityManager r0_ActivityManager = (ActivityManager) getSystemService("activity");
        if (r0_ActivityManager == null) {
            return null;
        }
        List r0_List = r0_ActivityManager.getRunningAppProcesses();
        if (r0_List == null) {
            return null;
        }
        int r2i = Process.myPid();
        Iterator r3_Iterator = r0_List.iterator();
        while (r3_Iterator.hasNext()) {
            RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r3_Iterator.next();
            if (r2i == r0_RunningAppProcessInfo.pid) {
                return r0_RunningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static final boolean deleteFile(String r1_String) {
        return getContext().deleteFile(r1_String);
    }

    public static final void enforceCallingOrSelfPermission(String r1_String, String r2_String) {
        getContext().enforceCallingOrSelfPermission(r1_String, r2_String);
    }

    public static final void enforceCallingOrSelfUriPermission(Uri r1_Uri, int r2i, String r3_String) {
        getContext().enforceCallingOrSelfUriPermission(r1_Uri, r2i, r3_String);
    }

    public static final void enforceCallingPermission(String r1_String, String r2_String) {
        getContext().enforceCallingPermission(r1_String, r2_String);
    }

    public static final void enforceCallingUriPermission(Uri r1_Uri, int r2i, String r3_String) {
        getContext().enforceCallingUriPermission(r1_Uri, r2i, r3_String);
    }

    public static final void enforcePermission(String r1_String, int r2i, int r3i, String r4_String) {
        getContext().enforcePermission(r1_String, r2i, r3i, r4_String);
    }

    public static final void enforceUriPermission(Uri r6_Uri, int r7i, int r8i, int r9i, String r10_String) {
        getContext().enforceUriPermission(r6_Uri, r7i, r8i, r9i, r10_String);
    }

    public static final void enforceUriPermission(Uri r8_Uri, String r9_String, String r10_String, int r11i, int r12i, int r13i, String r14_String) {
        getContext().enforceUriPermission(r8_Uri, r9_String, r10_String, r11i, r12i, r13i, r14_String);
    }

    public static final String[] fileList() {
        return getContext().fileList();
    }

    public static final Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    public static final ApplicationInfo getApplicationInfo() {
        return getContext().getApplicationInfo();
    }

    public static final AssetManager getAssets() {
        return getContext().getAssets();
    }

    public static final File getCacheDir() {
        return getContext().getCacheDir();
    }

    public static final ClassLoader getClassLoader() {
        return getContext().getClassLoader();
    }

    public static final ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    public static final Context getContext() {
        return a == null ? null : a;
    }

    public static final File getDir(String r1_String, int r2i) {
        return getContext().getDir(r1_String, r2i);
    }

    public static final File getExternalCacheDir() {
        return getContext().getExternalCacheDir();
    }

    public static final File getExternalFilesDir(String r1_String) {
        return getContext().getExternalFilesDir(r1_String);
    }

    public static final File getFileStreamPath(String r1_String) {
        return getContext().getFileStreamPath(r1_String);
    }

    public static final File getFilesDir() {
        return getContext().getFilesDir();
    }

    public static final Looper getMainLooper() {
        return getContext().getMainLooper();
    }

    public static final String getPackageCodePath() {
        return getContext().getPackageCodePath();
    }

    public static final PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    public static final String getPackageName() {
        return getContext().getPackageName();
    }

    public static final String getPackageResourcePath() {
        return getContext().getPackageResourcePath();
    }

    public static final Resources getResources() {
        return getContext().getResources();
    }

    public static final SharedPreferences getSharedPreferences(String r1_String, int r2i) {
        return getContext().getSharedPreferences(r1_String, r2i);
    }

    public static final Object getSystemService(String r1_String) {
        return getContext().getSystemService(r1_String);
    }

    public static final Theme getTheme() {
        return getContext().getTheme();
    }

    public static final Drawable getWallpaper() {
        return getContext().getWallpaper();
    }

    public static final int getWallpaperDesiredMinimumHeight() {
        return getContext().getWallpaperDesiredMinimumHeight();
    }

    public static final int getWallpaperDesiredMinimumWidth() {
        return getContext().getWallpaperDesiredMinimumWidth();
    }

    public static final void grantUriPermission(String r1_String, Uri r2_Uri, int r3i) {
        getContext().grantUriPermission(r1_String, r2_Uri, r3i);
    }

    public static final void init(Context r2_Context) {
        if (!c) {
            synchronized (b) {
                if (!c) {
                    setContext(r2_Context);
                    c = true;
                }
            }
        }
    }

    public static final boolean isMainProcess() {
        String r2_String = currentProcessName();
        return r2_String != null && r2_String.indexOf(AdViewUtil.NETWORK_TYPE_ZHIDIAN) < 1;
    }

    public static final boolean isRestricted() {
        return getContext().isRestricted();
    }

    public static final FileInputStream openFileInput(String r1_String) throws FileNotFoundException {
        return getContext().openFileInput(r1_String);
    }

    public static final FileOutputStream openFileOutput(String r1_String, int r2i) throws FileNotFoundException {
        return getContext().openFileOutput(r1_String, r2i);
    }

    public static final Drawable peekWallpaper() {
        return getContext().peekWallpaper();
    }

    public static final Intent registerReceiver(BroadcastReceiver r1_BroadcastReceiver, IntentFilter r2_IntentFilter) {
        return getContext().registerReceiver(r1_BroadcastReceiver, r2_IntentFilter);
    }

    public static final Intent registerReceiver(BroadcastReceiver r1_BroadcastReceiver, IntentFilter r2_IntentFilter, String r3_String, Handler r4_Handler) {
        return getContext().registerReceiver(r1_BroadcastReceiver, r2_IntentFilter, r3_String, r4_Handler);
    }

    public static final void removeStickyBroadcast(Intent r1_Intent) {
        getContext().removeStickyBroadcast(r1_Intent);
    }

    public static final void revokeUriPermission(Uri r1_Uri, int r2i) {
        getContext().revokeUriPermission(r1_Uri, r2i);
    }

    public static final void sendBroadcast(Intent r1_Intent) {
        getContext().sendBroadcast(r1_Intent);
    }

    public static final void sendBroadcast(Intent r1_Intent, String r2_String) {
        getContext().sendBroadcast(r1_Intent, r2_String);
    }

    public static final void sendOrderedBroadcast(Intent r1_Intent, String r2_String) {
        getContext().sendOrderedBroadcast(r1_Intent, r2_String);
    }

    public static final void sendOrderedBroadcast(Intent r8_Intent, String r9_String, BroadcastReceiver r10_BroadcastReceiver, Handler r11_Handler, int r12i, String r13_String, Bundle r14_Bundle) {
        getContext().sendOrderedBroadcast(r8_Intent, r9_String, r10_BroadcastReceiver, r11_Handler, r12i, r13_String, r14_Bundle);
    }

    public static final void sendStickyBroadcast(Intent r1_Intent) {
        getContext().sendStickyBroadcast(r1_Intent);
    }

    public static final void sendStickyOrderedBroadcast(Intent r7_Intent, BroadcastReceiver r8_BroadcastReceiver, Handler r9_Handler, int r10i, String r11_String, Bundle r12_Bundle) {
        getContext().sendStickyOrderedBroadcast(r7_Intent, r8_BroadcastReceiver, r9_Handler, r10i, r11_String, r12_Bundle);
    }

    public static final void setContext(Context r0_Context) {
        a = r0_Context;
    }

    public static final void setTheme(int r1i) {
        getContext().setTheme(r1i);
    }

    public static final void setWallpaper(Bitmap r1_Bitmap) throws IOException {
        getContext().setWallpaper(r1_Bitmap);
    }

    public static final void setWallpaper(InputStream r1_InputStream) throws IOException {
        getContext().setWallpaper(r1_InputStream);
    }

    public static final void startActivity(Intent r1_Intent) {
        getContext().startActivity(r1_Intent);
    }

    public static final boolean startInstrumentation(ComponentName r1_ComponentName, String r2_String, Bundle r3_Bundle) {
        return getContext().startInstrumentation(r1_ComponentName, r2_String, r3_Bundle);
    }

    public static final void startIntentSender(IntentSender r6_IntentSender, Intent r7_Intent, int r8i, int r9i, int r10i) throws SendIntentException {
        getContext().startIntentSender(r6_IntentSender, r7_Intent, r8i, r9i, r10i);
    }

    public static final ComponentName startService(Intent r1_Intent) {
        return getContext().startService(r1_Intent);
    }

    public static final boolean stopService(Intent r1_Intent) {
        return getContext().stopService(r1_Intent);
    }

    public static final void unbindService(ServiceConnection r1_ServiceConnection) {
        getContext().unbindService(r1_ServiceConnection);
    }

    public static final void unregisterReceiver(BroadcastReceiver r1_BroadcastReceiver) {
        getContext().unregisterReceiver(r1_BroadcastReceiver);
    }
}