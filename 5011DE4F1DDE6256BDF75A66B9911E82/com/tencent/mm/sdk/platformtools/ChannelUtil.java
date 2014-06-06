package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import java.util.Map;

public final class ChannelUtil {
    public static final int FLAG_NULL = 0;
    public static final int FLAG_UPDATE_EXTERNAL = 1;
    public static final int FLAG_UPDATE_NOTIP = 2;
    public static int buildRev;
    public static int channelId;
    public static boolean fullVersionInfo;
    public static String marketURL;
    public static String profileDeviceType;
    public static int updateMode;

    static {
        channelId = 0;
        profileDeviceType = VERSION.SDK_INT;
        updateMode = 0;
        buildRev = 0;
        marketURL = new StringBuilder("market://details?id=").append(MMApplicationContext.getPackageName()).toString();
        fullVersionInfo = false;
    }

    private ChannelUtil() {
    }

    public static String formatVersion(Context r5_Context, int r6i) {
        String r0_String = ((r6i >> 24) & 15) + "." + ((r6i >> 16) & 255);
        int r1i = 65535 & r6i;
        if (r5_Context != null) {
            try {
                PackageInfo r2_PackageInfo = r5_Context.getPackageManager().getPackageInfo(r5_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                if (r2_PackageInfo != null) {
                    r1i = r2_PackageInfo.versionCode;
                    r0_String = r2_PackageInfo.versionName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!(fullVersionInfo)) {
            return r0_String;
        }
        r0_String = r0_String + " r" + r1i + "(build." + buildRev + ")";
        Log.d("MicroMsg.SDK.ChannelUtil", new StringBuilder("full version: ").append(r0_String).toString());
        return r0_String;
    }

    public static void loadProfile(Context r4_Context) {
        try {
            Map r1_Map = KVConfig.parseIni(Util.convertStreamToString(r4_Context.getAssets().open("profile.ini")));
            String r0_String = Util.nullAsNil((String) r1_Map.get("PROFILE_DEVICE_TYPE"));
            profileDeviceType = r0_String;
            if (r0_String.length() <= 0) {
                profileDeviceType = VERSION.SDK_INT;
            }
            updateMode = Integer.parseInt((String) r1_Map.get("UPDATE_MODE"));
            buildRev = Integer.parseInt((String) r1_Map.get("BUILD_REVISION"));
            Log.w("MicroMsg.SDK.ChannelUtil", new StringBuilder("profileDeviceType=").append(profileDeviceType).toString());
            Log.w("MicroMsg.SDK.ChannelUtil", new StringBuilder("updateMode=").append(updateMode).toString());
            r0_String = (String) r1_Map.get("MARKET_URL");
            if (r0_String == null || r0_String.trim().length() == 0 || Uri.parse(r0_String) == null) {
                Log.w("MicroMsg.SDK.ChannelUtil", new StringBuilder("marketURL=").append(marketURL).toString());
            } else {
                marketURL = r0_String;
                Log.w("MicroMsg.SDK.ChannelUtil", new StringBuilder("marketURL=").append(marketURL).toString());
            }
        } catch (Exception e) {
            Log.e("MicroMsg.SDK.ChannelUtil", "setup profile from profile.ini failed");
            e.printStackTrace();
        }
    }

    public static void setupChannelId(Context r3_Context) {
        try {
            channelId = Integer.parseInt((String) KVConfig.parseIni(Util.convertStreamToString(r3_Context.getAssets().open("channel.ini"))).get("CHANNEL"));
        } catch (Exception e) {
            Log.e("MicroMsg.SDK.ChannelUtil", "setup channel id from channel.ini failed");
            e.printStackTrace();
        }
    }
}