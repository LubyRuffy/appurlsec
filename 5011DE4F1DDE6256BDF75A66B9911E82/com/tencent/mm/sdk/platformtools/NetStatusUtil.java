package com.tencent.mm.sdk.platformtools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NetStatusUtil {
    public static final int CMNET = 6;
    public static final int CMWAP = 5;
    public static final int CTNET = 8;
    public static final int CTWAP = 7;
    public static final int LTE = 10;
    public static final int MOBILE = 9;
    public static final int NET_3G = 4;
    public static final int NON_NETWORK = -1;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    public static final int TBACKGROUND_DATA_LIMITED = 2;
    public static final int TBACKGROUND_NOT_LIMITED = 0;
    public static final int TBACKGROUND_PROCESS_LIMITED = 1;
    public static final int TBACKGROUND_WIFI_LIMITED = 3;
    public static final int UNINET = 1;
    public static final int UNIWAP = 2;
    public static final int WAP_3G = 3;
    public static final int WIFI = 0;

    private static Intent a(Context r9_Context, String r10_String) {
        try {
            PackageManager r5_PackageManager = r9_Context.getPackageManager();
            List r6_List = r5_PackageManager.getInstalledPackages(TBACKGROUND_NOT_LIMITED);
            if (r6_List == null || r6_List.size() <= 0) {
                return null;
            }
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("package  size").append(r6_List.size()).toString());
            int r4i = 0;
            while (r4i < r6_List.size()) {
                int r3i;
                try {
                    int r3i_2;
                    Log.e("MicroMsg.NetStatusUtil", new StringBuilder("package ").append(((PackageInfo) r6_List.get(r4i)).packageName).toString());
                    Intent r1_Intent = new Intent();
                    r1_Intent.setPackage(((PackageInfo) r6_List.get(r4i)).packageName);
                    List r7_List = r5_PackageManager.queryIntentActivities(r1_Intent, TBACKGROUND_NOT_LIMITED);
                    r3i_2 = r7_List != null ? r7_List.size() : 0;
                    if (r3i_2 > 0) {
                        try {
                            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("activityName count ").append(r3i_2).toString());
                            int r1i = 0;
                            while (r1i < r3i_2) {
                                ActivityInfo r8_ActivityInfo = ((ResolveInfo) r7_List.get(r1i)).activityInfo;
                                if (r8_ActivityInfo.name.contains(r10_String)) {
                                    Intent r0_Intent = new Intent("/");
                                    r0_Intent.setComponent(new ComponentName(r8_ActivityInfo.packageName, r8_ActivityInfo.name));
                                    r0_Intent.setAction("android.intent.action.VIEW");
                                    r9_Context.startActivity(r0_Intent);
                                    return r0_Intent;
                                } else {
                                    r1i++;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e_2) {
                    e_2.printStackTrace();
                }
                r4i++;
            }
            return null;
        } catch (Exception e_3) {
            e_3.printStackTrace();
        }
    }

    public static boolean checkFromXml(int r9i) {
        boolean r0z;
        try {
            runRootCommand();
            NodeList r4_NodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File("/data/system/netpolicy.xml"))).getDocumentElement().getElementsByTagName("uid-policy");
            int r3i = 0;
            while (r3i < r4_NodeList.getLength()) {
                Element r0_Element = (Element) r4_NodeList.item(r3i);
                String r5_String = r0_Element.getAttribute("uid");
                String r0_String = r0_Element.getAttribute("policy");
                Log.e("MicroMsg.NetStatusUtil", new StringBuilder("uid is ").append(r5_String).append("  policy is ").append(r0_String).toString());
                if (r5_String.equals(Integer.toString(r9i))) {
                    if (Integer.parseInt(r0_String) == 1) {
                        r0z = true;
                        return r0z;
                    } else if (Integer.parseInt(r0_String) == 0) {
                        r0z = false;
                        return r0z;
                    }
                }
                r3i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        r0z = false;
        return r0z;
    }

    public static void dumpNetStatus(Context r4_Context) {
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r4_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("isAvailable ").append(r0_NetworkInfo.isAvailable()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("isConnected ").append(r0_NetworkInfo.isConnected()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("isRoaming ").append(r0_NetworkInfo.isRoaming()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("isFailover ").append(r0_NetworkInfo.isFailover()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("getSubtypeName ").append(r0_NetworkInfo.getSubtypeName()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("getExtraInfo ").append(r0_NetworkInfo.getExtraInfo()).toString());
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("activeNetInfo ").append(r0_NetworkInfo.toString()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getBackgroundLimitType(Context r6_Context) {
        if (VERSION.SDK_INT >= 14) {
            try {
                Class r0_Class = Class.forName("android.app.ActivityManagerNative");
                Object r0_Object = r0_Class.getMethod("getDefault", new Class[0]).invoke(r0_Class, new Object[0]);
                if (((Integer) r0_Object.getClass().getMethod("getProcessLimit", new Class[0]).invoke(r0_Object, new Object[0])).intValue() == 0) {
                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            int r0i = getWifiSleeepPolicy(r6_Context);
            if (r0i == 2) {
                return 0;
            }
            if (r0i == 1 || r0i == 0) {
                return WAP_3G;
            }
            return 0;
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    public static int getNetType(Context r5_Context) {
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r5_Context.getSystemService("connectivity");
        if (r0_ConnectivityManager == null) {
            return -1;
        }
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return -1;
        }
        if (r0_NetworkInfo.getType() == 1) {
            return TBACKGROUND_NOT_LIMITED;
        }
        Log.d("MicroMsg.NetStatusUtil", new StringBuilder("activeNetInfo.getExtraInfo()  ").append(r0_NetworkInfo.getExtraInfo()).toString());
        Log.d("MicroMsg.NetStatusUtil", new StringBuilder("activeNetInfo.getType()  ").append(r0_NetworkInfo.getType()).toString());
        if (r0_NetworkInfo.getExtraInfo() != null) {
            if (r0_NetworkInfo.getExtraInfo().equals("uninet")) {
                return 1;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("uniwap")) {
                return UNIWAP;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("3gwap")) {
                return WAP_3G;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("3gnet")) {
                return NET_3G;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("cmwap")) {
                return CMWAP;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("cmnet")) {
                return CMNET;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("ctwap")) {
                return CTWAP;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("ctnet")) {
                return CTNET;
            }
            if (r0_NetworkInfo.getExtraInfo().equals("LTE")) {
                return LTE;
            }
        }
        return MOBILE;
    }

    public static String getNetTypeString(Context r4_Context) {
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r4_Context.getSystemService("connectivity");
        if (r0_ConnectivityManager == null) {
            return "NON_NETWORK";
        }
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return "NON_NETWORK";
        }
        if (r0_NetworkInfo.getType() == 1) {
            return "WIFI";
        }
        Log.d("MicroMsg.NetStatusUtil", new StringBuilder("activeNetInfo.getExtraInfo()  ").append(r0_NetworkInfo.getExtraInfo()).toString());
        Log.d("MicroMsg.NetStatusUtil", new StringBuilder("activeNetInfo.getType()  ").append(r0_NetworkInfo.getType()).toString());
        return r0_NetworkInfo.getExtraInfo() != null ? r0_NetworkInfo.getExtraInfo() : "MOBILE";
    }

    public static int getWifiSleeepPolicy(Context r3_Context) {
        return System.getInt(r3_Context.getContentResolver(), "wifi_sleep_policy", UNIWAP);
    }

    public static boolean is2G(int r2i) {
        return r2i == 1 || r2i == 2 || r2i == 5 || r2i == 6 || r2i == 7 || r2i == 8;
    }

    public static boolean is2G(Context r5_Context) {
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r5_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean r0z;
            if (r0_NetworkInfo.getType() == 1) {
                r0z = false;
                return r0z;
            } else {
                r0z = r0_NetworkInfo.getSubtype() == 2 || r0_NetworkInfo.getSubtype() == 1;
                return r0z;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is3G(int r1i) {
        return r1i == 3 || r1i == 4;
    }

    public static boolean is3G(Context r5_Context) {
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r5_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean r0z;
            if (r0_NetworkInfo.getType() == 1) {
                r0z = false;
                return r0z;
            } else {
                r0z = r0_NetworkInfo.getSubtype() >= 5 && r0_NetworkInfo.getSubtype() < 13;
                return r0z;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is4G(int r1i) {
        return r1i == 10;
    }

    public static boolean is4G(Context r4_Context) {
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r4_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean r0z;
            if (r0_NetworkInfo.getType() == 1) {
                r0z = false;
                return r0z;
            } else {
                if (r0_NetworkInfo.getSubtype() >= 13) {
                    r0z = true;
                    return r0z;
                }
                r0z = false;
                return r0z;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected(Context r2_Context) {
        try {
            return ((ConnectivityManager) r2_Context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isImmediatelyDestroyActivities(Context r3_Context) {
        return System.getInt(r3_Context.getContentResolver(), "always_finish_activities", TBACKGROUND_NOT_LIMITED) != 0;
    }

    public static boolean isLimited(int r2i) {
        return r2i == 2 || r2i == 1 || r2i == 3;
    }

    public static boolean isMobile(int r1i) {
        return is3G(r1i) || is2G(r1i) || is4G(r1i);
    }

    public static boolean isMobile(Context r2_Context) {
        int r0i = getNetType(r2_Context);
        return is3G(r0i) || is2G(r0i) || is4G(r0i);
    }

    public static boolean isRestrictBacground(Context r9_Context) {
        int r3i = r9_Context.getApplicationInfo().uid;
        try {
            Class r0_Class = Class.forName("android.net.NetworkPolicyManager");
            Class[] r5_ClassA = new Class[1];
            r5_ClassA[0] = Context.class;
            Method r4_Method = r0_Class.getMethod("getSystemService", r5_ClassA);
            Object[] r5_ObjectA = new Object[1];
            r5_ObjectA[0] = r9_Context;
            Object r4_Object = r4_Method.invoke(r0_Class, r5_ObjectA);
            Field r0_Field = r0_Class.getDeclaredField("mService");
            r0_Field.setAccessible(true);
            Object r0_Object = r0_Field.get(r4_Object);
            Class r4_Class = r0_Object.getClass();
            Class[] r6_ClassA = new Class[1];
            r6_ClassA[0] = Integer.TYPE;
            r4_Method = r4_Class.getMethod("getUidPolicy", r6_ClassA);
            r5_ObjectA = new Object[1];
            r5_ObjectA[0] = Integer.valueOf(r3i);
            int r0i = ((Integer) r4_Method.invoke(r0_Object, r5_ObjectA)).intValue();
            Log.e("MicroMsg.NetStatusUtil", new StringBuilder("policy is ").append(r0i).toString());
            if (r0i == 1) {
                return true;
            }
            if (r0i == 0) {
                return false;
            }
            return checkFromXml(r3i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWap(int r1i) {
        return r1i == 2 || r1i == 5 || r1i == 7 || r1i == 3;
    }

    public static boolean isWap(Context r1_Context) {
        return isWap(getNetType(r1_Context));
    }

    public static boolean isWifi(int r1i) {
        return r1i == 0;
    }

    public static boolean isWifi(Context r1_Context) {
        return isWifi(getNetType(r1_Context));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean runRootCommand() {
        /*
        r2 = 0;
        r0 = java.lang.Runtime.getRuntime();	 //Catch:{ Exception -> 0x002e, all -> 0x0059 }
        r1 = "su";
        r3 = r0.exec(r1);	 //Catch:{ Exception -> 0x002e, all -> 0x0059 }
        r1 = new java.io.DataOutputStream;	 //Catch:{ Exception -> 0x0074, all -> 0x006b }
        r0 = r3.getOutputStream();	 //Catch:{ Exception -> 0x0074, all -> 0x006b }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x0074, all -> 0x006b }
        r0 = "exit\n";
        r1.writeBytes(r0);	 //Catch:{ Exception -> 0x0078, all -> 0x006f }
        r1.flush();	 //Catch:{ Exception -> 0x0078, all -> 0x006f }
        r3.waitFor();	 //Catch:{ Exception -> 0x0078, all -> 0x006f }
        r1.close();	 //Catch:{ Exception -> 0x0029 }
        if (r3 == 0) goto L_0x0027;
    L_0x0024:
        r3.destroy();	 //Catch:{ Exception -> 0x0029 }
    L_0x0027:
        r0 = 1;
    L_0x0028:
        return r0;
    L_0x0029:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0027;
    L_0x002e:
        r0 = move-exception;
        r1 = r2;
    L_0x0030:
        r3 = "MicroMsg.NetStatusUtil";
        r4 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0072 }
        r5 = "the device is not rooted\uff0c error message\uff1a ";
        r4.<init>(r5);	 //Catch:{ all -> 0x0072 }
        r0 = r0.getMessage();	 //Catch:{ all -> 0x0072 }
        r0 = r4.append(r0);	 //Catch:{ all -> 0x0072 }
        r0 = r0.toString();	 //Catch:{ all -> 0x0072 }
        com.tencent.mm.sdk.platformtools.Log.d(r3, r0);	 //Catch:{ all -> 0x0072 }
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        r1.close();	 //Catch:{ Exception -> 0x0054 }
    L_0x004d:
        if (r2 == 0) goto L_0x0052;
    L_0x004f:
        r2.destroy();	 //Catch:{ Exception -> 0x0054 }
    L_0x0052:
        r0 = 0;
        goto L_0x0028;
    L_0x0054:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0052;
    L_0x0059:
        r0 = move-exception;
        r1 = r2;
    L_0x005b:
        if (r1 == 0) goto L_0x0060;
    L_0x005d:
        r1.close();	 //Catch:{ Exception -> 0x0066 }
    L_0x0060:
        if (r2 == 0) goto L_0x0065;
    L_0x0062:
        r2.destroy();	 //Catch:{ Exception -> 0x0066 }
    L_0x0065:
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0065;
    L_0x006b:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x005b;
    L_0x006f:
        r0 = move-exception;
        r2 = r3;
        goto L_0x005b;
    L_0x0072:
        r0 = move-exception;
        goto L_0x005b;
    L_0x0074:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0030;
    L_0x0078:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0030;
        */

    }

    public static void startSettingItent(Context r4_Context, int r5i) {
        Intent r0_Intent;
        switch (r5i) {
            case UNINET:
                try {
                    r0_Intent = new Intent("/");
                    r0_Intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings"));
                    r0_Intent.setAction("android.intent.action.VIEW");
                    r4_Context.startActivity(r0_Intent);
                } catch (Exception e) {
                    a(r4_Context, "DevelopmentSettings");
                }
                break;
            case UNIWAP:
                try {
                    r0_Intent = new Intent("/");
                    r0_Intent.setComponent(new ComponentName("com.android.providers.subscribedfeeds", "com.android.settings.ManageAccountsSettings"));
                    r0_Intent.setAction("android.intent.action.VIEW");
                    r4_Context.startActivity(r0_Intent);
                } catch (Exception e_2) {
                    r0_Intent = new Intent("/");
                    r0_Intent.setComponent(new ComponentName("com.htc.settings.accountsync", "com.htc.settings.accountsync.ManageAccountsSettings"));
                    r0_Intent.setAction("android.intent.action.VIEW");
                    r4_Context.startActivity(r0_Intent);
                }
                break;
            case WAP_3G:
                try {
                    r0_Intent = new Intent();
                    r0_Intent.setAction("android.settings.WIFI_IP_SETTINGS");
                    r4_Context.startActivity(r0_Intent);
                } catch (Exception e_3) {
                    a(r4_Context, "AdvancedSettings");
                }
                break;
        }
    }
}