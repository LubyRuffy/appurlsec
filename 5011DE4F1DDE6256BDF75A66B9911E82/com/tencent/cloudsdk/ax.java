package com.tencent.cloudsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.common.utils.ClientIdManager;
import com.tencent.cloudsdk.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.cordova.NetworkManager;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ax {
    private static int a;

    static {
        a = 8;
    }

    public static p a() {
        p r0_p = new p();
        r0_p.a = "1.2.0";
        r0_p.d = VERSION.RELEASE;
        r0_p.c = ClientIdManager.getInstance().getClientId();
        r0_p.b = b(GlobalContext.getContext());
        r0_p.e = b();
        return r0_p;
    }

    public static String a(Context r4_Context) {
        if (r4_Context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
            return RContactStorage.PRIMARY_KEY;
        }
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r4_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        if (r0_NetworkInfo.getType() == 1) {
            return NetworkManager.WIFI;
        }
        String r0_String = r0_NetworkInfo.getExtraInfo();
        if (r0_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        r0_String = r0_String.toLowerCase();
        WnsClientLog.i("Util", new StringBuilder(">>> AccessPoint: ").append(r0_String).toString());
        return r0_String;
    }

    public static String a(String r5_String) {
        if (r5_String == null || RContactStorage.PRIMARY_KEY.equals(r5_String)) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            return r5_String.substring(r5_String.lastIndexOf("/") + 1);
        } catch (IndexOutOfBoundsException e) {
            Throwable r1_Throwable = e;
            WnsClientLog.e("Util", new StringBuilder(">>> getFileNameFromUrl E: ").append(r1_Throwable.getMessage()).toString(), r1_Throwable);
            return r0_String;
        }
    }

    public static void a(Handler r1_Handler, Message r2_Message) {
        if (r1_Handler == null || r2_Message == null) {
        } else {
            if (r1_Handler.hasMessages(r2_Message.what)) {
                r1_Handler.removeMessages(r2_Message.what);
            }
            r1_Handler.sendMessage(r2_Message);
        }
    }

    public static void a(OutputStream r4_OutputStream) {
        if (r4_OutputStream != null) {
            try {
                r4_OutputStream.close();
            } catch (IOException e) {
                WnsClientLog.e("Util", new StringBuilder(">>> closeOutputQuietly E: ").append(e.toString()).toString());
            }
        }
    }

    public static boolean a(long r9j) {
        StatFs r0_StatFs = new StatFs(GlobalContext.getContext().getFilesDir().getAbsolutePath());
        long r1j = (long) r0_StatFs.getBlockSize();
        long r5j = ((long) r0_StatFs.getAvailableBlocks()) * r1j;
        WnsClientLog.i("Util", new StringBuilder("data storage capacity: ").append(r5j).append(" B").append(" total: ").append(r1j * ((long) r0_StatFs.getBlockCount())).append("B").toString());
        return (r5j > r9j ? 1 : (r5j == r9j? 0 : -1)) > 0;
    }

    public static byte[] a(InputStream r8_InputStream) {
        int r1i = 0;
        if (r8_InputStream == null) {
            return null;
        }
        Object r1_Object = new Object[r1i];
        Object r3_Object = new Object[1024];
        while (true) {
            try {
                int r4i = r8_InputStream.read(r3_Object);
                if (r4i == -1) {
                    b(r8_InputStream);
                    return r1_Object;
                } else {
                    Object r2_Object = new Object[(r1_Object.length + r4i)];
                    if (r1_Object.length > 0) {
                        System.arraycopy(r1_Object, 0, r2_Object, 0, r1_Object.length);
                    }
                    System.arraycopy(r3_Object, 0, r2_Object, r1_Object.length, r4i);
                    r1_Object = r2_Object;
                }
            } catch (Exception e) {
                WnsClientLog.e("Util", new StringBuilder(">>> readData E: ").append(e.toString()).toString());
                b(r8_InputStream);
                return null;
            }
        }
    }

    public static int b() {
        if (f.a().h()) {
            return a;
        }
        try {
            if (GlobalContext.getContext() != null) {
                ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) GlobalContext.getContext().getSystemService("connectivity");
                if (r0_ConnectivityManager == null) {
                    return 16;
                }
                NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
                if (r0_NetworkInfo == null) {
                    return -1;
                }
                if (!r0_NetworkInfo.isAvailable() || !r0_NetworkInfo.isConnected()) {
                    return -1;
                }
                if (r0_NetworkInfo.getType() == 1) {
                    return Base64.DONT_BREAK_LINES;
                }
                if (r0_NetworkInfo.getType() != 0) {
                    return 16;
                }
                switch (r0_NetworkInfo.getSubtype()) {
                    case XListViewHeader.STATE_READY:
                    case XListViewHeader.STATE_REFRESHING:
                    case XListViewFooter.STATE_NODATA:
                    case ShareUtils.SHARE_COLLECT:
                    case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                        return 1;
                    case XListViewFooter.STATE_NOMORE:
                    case ShareUtils.SHARE_SMS:
                    case ShareUtils.SHARE_COPY:
                    case Base64.DONT_BREAK_LINES:
                    case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                    case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                    case NearbySelectView.TIME_15MIN:
                        return XListViewHeader.STATE_REFRESHING;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
                        return XListViewFooter.STATE_NODATA;
                }
                return 16;
            }
        } catch (Exception e) {
            WnsClientLog.e("Util", new StringBuilder(">>> getNetworkType() E : ").append(e.getMessage()).toString());
        }
        return -1;
    }

    public static String b(Context r2_Context) {
        if (r2_Context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == -1) {
            return RContactStorage.PRIMARY_KEY;
        }
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r2_Context.getSystemService("phone");
        if (r0_TelephonyManager == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = r0_TelephonyManager.getDeviceId();
        return r0_String == null ? RContactStorage.PRIMARY_KEY : r0_String;
    }

    public static void b(InputStream r4_InputStream) {
        if (r4_InputStream != null) {
            try {
                r4_InputStream.close();
            } catch (IOException e) {
                WnsClientLog.e("Util", new StringBuilder(">>> closeInputQuietly E: ").append(e.toString()).toString());
            }
        }
    }

    public static boolean b(String r5_String) {
        boolean r0z = false;
        if (r5_String != null) {
            RContactStorage.PRIMARY_KEY.equals(r5_String);
        }
        try {
            String r2_String = r5_String.substring(r5_String.indexOf("."));
            if (r2_String == null || RContactStorage.PRIMARY_KEY.equals(r2_String)) {
                return r0z;
            }
            if (".zip".equals(r2_String)) {
                if (VERSION.SDK_INT >= 14) {
                    return r0z;
                }
                r0z = true;
                return r0z;
            } else {
                if (!".apk".equals(r2_String) && !".jar".equals(r2_String)) {
                    return r0z;
                }
                r0z = true;
                return r0z;
            }
        } catch (IndexOutOfBoundsException e) {
            Throwable r1_Throwable = e;
            WnsClientLog.e("Util", new StringBuilder(">>> isFileNameLegal E: ").append(r1_Throwable.getMessage()).toString(), r1_Throwable);
        }
    }
}