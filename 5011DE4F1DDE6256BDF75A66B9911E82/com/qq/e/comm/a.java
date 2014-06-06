package com.qq.e.comm;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Base64;
import com.androidquery.util.Constants;
import com.qq.e.ads.AdActivity;
import com.qq.e.v2.constants.Constants.KEYS;
import com.qq.e.v2.constants.Constants.PLUGIN;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.managers.plugin.PM;
import com.qq.e.v2.managers.setting.SM;
import com.qq.e.v2.managers.status.APPStatus;
import com.qq.e.v2.managers.status.DeviceStatus;
import com.qq.e.v2.util.FileUtil;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private String a;
    private com.qq.e.v2.managers.setting.a b;

    public a(String r1_String, com.qq.e.v2.managers.setting.a r2_com_qq_e_v2_managers_setting_a) {
        this.a = r1_String;
        this.b = r2_com_qq_e_v2_managers_setting_a;
    }

    private static void a_(String r0_String, Object r1_Object, Map<String, Object> r2_Map_String__Object) {
        if (r1_Object != null) {
            r2_Map_String__Object.put(r0_String, r1_Object);
        }
    }

    public static boolean a_(Context r4_Context) {
        Class[] r2_ClassA = new Class[1];
        r2_ClassA[0] = AdActivity.class;
        return a(r4_Context, r2_ClassA) && b(r4_Context) && c(r4_Context);
    }

    public static boolean a_(Context r6_Context, File r7_File, File r8_File) {
        AssetManager r2_AssetManager = r6_Context.getAssets();
        try {
            if (Arrays.binarySearch(r2_AssetManager.list(PLUGIN.ASSET_PLUGIN_DIR), PLUGIN.ASSET_PLUGIN_NAME) < 0) {
                return false;
            }
            String r3_String = new StringBuilder(PLUGIN.ASSET_PLUGIN_DIR).append(File.separator).append(PLUGIN.ASSET_PLUGIN_NAME).toString();
            String r1_String = PLUGIN.ASSET_PLUGIN_SIG;
            if (r1_String == null) {
                r1_String = RContactStorage.PRIMARY_KEY;
            }
            StringUtil.writeTo(new StringBuilder("443#####").append(r1_String).toString(), r8_File);
            return FileUtil.copyTo(r2_AssetManager.open(r3_String), r7_File);
        } catch (Throwable th) {
            GDTLogger.report("Exception while init default plugin manager", th);
            return false;
        }
    }

    private static boolean a_(Context r5_Context, Class<?> ... r6_Class_A) {
        int r1i = 0;
        while (true) {
            try {
                if (r1i >= r6_Class_A.length) {
                    return true;
                }
                Intent r2_Intent = new Intent();
                r2_Intent.setClass(r5_Context, r6_Class_A[r1i]);
                if (r5_Context.getPackageManager().resolveActivity(r2_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION) == null) {
                    GDTLogger.e(new StringBuilder("Can not found ").append(r6_Class_A[r1i].getName()).append(", please set it in AndroidManifest.xml").toString());
                    return false;
                } else {
                    r1i++;
                }
            } catch (Throwable th) {
                GDTLogger.report("Check required Activities error", th);
                return false;
            }
        }
    }

    public static byte[] a_(byte[] r4_byteA) {
        if (r4_byteA == null || r4_byteA.length == 0) {
            return r4_byteA;
        }
        byte[] r0_byteA = com.qq.e.v2.net.a.a(c(r4_byteA));
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("v", "1.1");
            r1_JSONObject.put("d", Base64.encodeToString(r0_byteA, 0));
            return c(r1_JSONObject.toString().getBytes());
        } catch (JSONException e) {
            GDTLogger.report("Json Exception in GDTSecurityPackageUtil.packageByteArray", e);
            return null;
        }
    }

    private static boolean b(Context r4_Context) {
        try {
            Intent r1_Intent = new Intent();
            r1_Intent.setClass(r4_Context, DownloadService.class);
            if (r4_Context.getPackageManager().resolveService(r1_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION) != null) {
                return true;
            }
            GDTLogger.e("Can not found DownloadService, please set it in AndroidManifest.xml");
            return false;
        } catch (Throwable th) {
            GDTLogger.report("Check required Services error", th);
            return false;
        }
    }

    public static byte[] b(byte[] r3_byteA) {
        byte[] r0_byteA = null;
        String r1_String;
        try {
            String r1_String_2;
            JSONObject r1_JSONObject = new JSONObject(new String(d(r3_byteA)));
            r1_String_2 = r1_JSONObject.has("d") ? r1_JSONObject.getString("d") : null;
            if (StringUtil.isEmpty(r1_String_2)) {
                return r0_byteA;
            }
            r0_byteA = d(com.qq.e.v2.net.a.b(Base64.decode(r1_String_2, 0)));
            return r0_byteA;
        } catch (JSONException e) {
            GDTLogger.report("Json Exception in GDTSecurityPackageUtil.unpackByteArray", e);
        } catch (Exception e_2) {
            GDTLogger.report("Exception in GDTSecurityPackageUtil.unpackByteArray", e_2);
        }
    }

    public static Map<String, Object> c() {
        GDTLogger.d(new StringBuilder("TIMESTAMP_BEFORE_BUILDCOMMONINFO:").append(System.currentTimeMillis()).toString());
        Map r0_Map = new HashMap();
        SM r1_SM = GDTADManager.getInstance().getSM();
        if (r1_SM != null) {
            a(KEYS.SUID, r1_SM.getSuid(), r0_Map);
            a(KEYS.SID, r1_SM.getSid(), r0_Map);
        }
        GDTLogger.d(new StringBuilder("TIMESTAMP_AFTER_BUILDCOMMONINFO:").append(System.currentTimeMillis()).toString());
        return r0_Map;
    }

    private static boolean c(Context r3_Context) {
        boolean r0z = false;
        try {
            if (r3_Context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
                GDTLogger.e("Can not found android.permission.INTERNET, please set it in AndroidManifest.xml");
                return r0z;
            } else if (r3_Context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
                GDTLogger.e("Can not found android.permission.ACCESS_NETWORK_STATE, please set it in AndroidManifest.xml");
                return r0z;
            } else if (r3_Context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == -1) {
                GDTLogger.e("Can not found android.permission.ACCESS_WIFI_STATE, please set it in AndroidManifest.xml");
                return r0z;
            } else if (r3_Context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == -1) {
                GDTLogger.e("Can not found android.permission.READ_PHONE_STATE, please set it in AndroidManifest.xml");
                return r0z;
            } else if (r3_Context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
                GDTLogger.e("Can not found android.permission.WRITE_EXTERNAL_STORAGE, please set it in AndroidManifest.xml");
                return r0z;
            } else {
                r0z = true;
                return r0z;
            }
        } catch (Throwable th) {
            GDTLogger.report("Check required Permissions error", th);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] c(byte[] r4_byteA) {
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r4.length;
        if (r1 != 0) goto L_0x0008;
    L_0x0006:
        r0 = r4;
    L_0x0007:
        return r0;
    L_0x0008:
        r3 = new java.io.ByteArrayOutputStream;
        r3.<init>();
        r2 = new java.util.zip.GZIPOutputStream;	 //Catch:{ Exception -> 0x0028, all -> 0x003b }
        r2.<init>(r3);	 //Catch:{ Exception -> 0x0028, all -> 0x003b }
        r2.write(r4);	 //Catch:{ Exception -> 0x004e }
        r2.finish();	 //Catch:{ Exception -> 0x004e }
        r0 = r3.toByteArray();	 //Catch:{ Exception -> 0x004e }
        r2.close();	 //Catch:{ Exception -> 0x0023 }
        r3.close();	 //Catch:{ Exception -> 0x0023 }
        goto L_0x0007;
    L_0x0023:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0028:
        r1 = move-exception;
        r2 = r0;
    L_0x002a:
        r1.printStackTrace();	 //Catch:{ all -> 0x004c }
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 //Catch:{ Exception -> 0x0036 }
    L_0x0032:
        r3.close();	 //Catch:{ Exception -> 0x0036 }
        goto L_0x0007;
    L_0x0036:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x003b:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x003e:
        if (r2 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();	 //Catch:{ Exception -> 0x0047 }
    L_0x0043:
        r3.close();	 //Catch:{ Exception -> 0x0047 }
    L_0x0046:
        throw r0;
    L_0x0047:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0046;
    L_0x004c:
        r0 = move-exception;
        goto L_0x003e;
    L_0x004e:
        r1 = move-exception;
        goto L_0x002a;
        */

    }

    public static Map<String, Object> d() {
        GDTLogger.d(new StringBuilder("TimeStampBeforeBuildSigInfo:").append(System.currentTimeMillis()).toString());
        Map r0_Map = new HashMap();
        SM r1_SM = GDTADManager.getInstance().getSM();
        PM r2_PM = GDTADManager.getInstance().getPM();
        if (r1_SM != null) {
            a(KEYS.APPINFO, r1_SM.getDevCloudSettingSig(), r0_Map);
            a(KEYS.SDKINFO, r1_SM.getSdkCloudSettingSig(), r0_Map);
        }
        if (r2_PM != null) {
            a(KEYS.JAR, r2_PM.getLocalSig(), r0_Map);
            a(KEYS.PLUGIN_VERSION, Integer.valueOf(r2_PM.getPluginVersion()), r0_Map);
        }
        GDTLogger.d(new StringBuilder("TimeStampAfterBuildSigInfo:").append(System.currentTimeMillis()).toString());
        return r0_Map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] d(byte[] r7_byteA) {
        /*
        r0 = 0;
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r7.length;
        if (r1 != 0) goto L_0x0008;
    L_0x0006:
        r0 = r7;
    L_0x0007:
        return r0;
    L_0x0008:
        r3 = new java.io.ByteArrayInputStream;
        r3.<init>(r7);
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>();
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];
        r2 = new java.util.zip.GZIPInputStream;	 //Catch:{ Exception -> 0x0068, all -> 0x0052 }
        r2.<init>(r3);	 //Catch:{ Exception -> 0x0068, all -> 0x0052 }
    L_0x001b:
        r5 = r2.read(r1);	 //Catch:{ Exception -> 0x0027 }
        r6 = -1;
        if (r5 == r6) goto L_0x003c;
    L_0x0022:
        r6 = 0;
        r4.write(r1, r6, r5);	 //Catch:{ Exception -> 0x0027 }
        goto L_0x001b;
    L_0x0027:
        r1 = move-exception;
    L_0x0028:
        r1.printStackTrace();	 //Catch:{ all -> 0x0066 }
        if (r2 == 0) goto L_0x0030;
    L_0x002d:
        r2.close();	 //Catch:{ Exception -> 0x0037 }
    L_0x0030:
        r3.close();	 //Catch:{ Exception -> 0x0037 }
        r4.close();	 //Catch:{ Exception -> 0x0037 }
        goto L_0x0007;
    L_0x0037:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x003c:
        r4.flush();	 //Catch:{ Exception -> 0x0027 }
        r0 = r4.toByteArray();	 //Catch:{ Exception -> 0x0027 }
        r2.close();	 //Catch:{ Exception -> 0x004d }
        r3.close();	 //Catch:{ Exception -> 0x004d }
        r4.close();	 //Catch:{ Exception -> 0x004d }
        goto L_0x0007;
    L_0x004d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0052:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0055:
        if (r2 == 0) goto L_0x005a;
    L_0x0057:
        r2.close();	 //Catch:{ Exception -> 0x0061 }
    L_0x005a:
        r3.close();	 //Catch:{ Exception -> 0x0061 }
        r4.close();	 //Catch:{ Exception -> 0x0061 }
    L_0x0060:
        throw r0;
    L_0x0061:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0060;
    L_0x0066:
        r0 = move-exception;
        goto L_0x0055;
    L_0x0068:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0028;
        */

    }

    public static Map<String, Object> e() {
        GDTLogger.d(new StringBuilder("TimeStampBeforeBuildDevInfo:").append(System.currentTimeMillis()).toString());
        Map r0_Map = new HashMap();
        DeviceStatus r1_DeviceStatus = GDTADManager.getInstance().getDeviceStatus();
        if (r1_DeviceStatus != null) {
            a("did", r1_DeviceStatus.getDid(), r0_Map);
            a("md", r1_DeviceStatus.model, r0_Map);
            a("lg", r1_DeviceStatus.getLanguage(), r0_Map);
            a("w", Integer.valueOf(r1_DeviceStatus.getDeviceWidth()), r0_Map);
            a("h", Integer.valueOf(r1_DeviceStatus.getDeviceHeight()), r0_Map);
            a("dd", Integer.valueOf(r1_DeviceStatus.getDeviceDensity()), r0_Map);
            a("apil", Integer.valueOf(r1_DeviceStatus.getVersion()), r0_Map);
            a("os", (Object)"android", r0_Map);
            a("op", r1_DeviceStatus.getOperator(), r0_Map);
        }
        GDTLogger.d(new StringBuilder("TimeStampAfterBuildDevInfo:").append(System.currentTimeMillis()).toString());
        return r0_Map;
    }

    public static Map<String, Object> f() {
        Map r0_Map = new HashMap();
        APPStatus r1_APPStatus = GDTADManager.getInstance().getAppStatus();
        if (r1_APPStatus != null) {
            a("an", r1_APPStatus.getAPPName(), r0_Map);
            a("appkey", r1_APPStatus.getAPPID(), r0_Map);
            a("appv", r1_APPStatus.getAPPVersion(), r0_Map);
        }
        return r0_Map;
    }

    public static Map<String, Object> g() {
        GDTLogger.d(new StringBuilder("TimeStampBeforeBuildCTXInfo:").append(System.currentTimeMillis()).toString());
        Map r0_Map = new HashMap();
        DeviceStatus r1_DeviceStatus = GDTADManager.getInstance().getDeviceStatus();
        if (r1_DeviceStatus != null) {
            a("so", r1_DeviceStatus.getScreenOrientation(), r0_Map);
            a("dn", r1_DeviceStatus.getDataNet(), r0_Map);
            a("lat", r1_DeviceStatus.getLat(), r0_Map);
            a("lng", r1_DeviceStatus.getLng(), r0_Map);
            r0_Map.putAll(r1_DeviceStatus.getLacAndCeilId());
        }
        GDTLogger.d(new StringBuilder("TimeStampAfterBuildCTXInfo:").append(System.currentTimeMillis()).toString());
        return r0_Map;
    }

    public String a_() {
        return this.a;
    }

    public com.qq.e.v2.managers.setting.a b() {
        return this.b;
    }
}