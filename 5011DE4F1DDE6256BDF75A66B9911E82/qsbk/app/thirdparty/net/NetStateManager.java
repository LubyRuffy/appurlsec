package qsbk.app.thirdparty.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import org.apache.cordova.NetworkManager;
import org.apache.http.HttpHost;

public class NetStateManager {
    public static NetState CUR_NETSTATE;
    private static Context a;

    public static enum NetState {
        Mobile,
        WIFI,
        NOWAY;


        static {
            Mobile = new qsbk.app.thirdparty.net.NetStateManager.NetState("Mobile", 0);
            WIFI = new qsbk.app.thirdparty.net.NetStateManager.NetState("WIFI", 1);
            NOWAY = new qsbk.app.thirdparty.net.NetStateManager.NetState("NOWAY", 2);
            qsbk.app.thirdparty.net.NetStateManager.NetState[] r0_qsbk_app_thirdparty_net_NetStateManager_NetStateA = new qsbk.app.thirdparty.net.NetStateManager.NetState[3];
            r0_qsbk_app_thirdparty_net_NetStateManager_NetStateA[0] = Mobile;
            r0_qsbk_app_thirdparty_net_NetStateManager_NetStateA[1] = WIFI;
            r0_qsbk_app_thirdparty_net_NetStateManager_NetStateA[2] = NOWAY;
            a = r0_qsbk_app_thirdparty_net_NetStateManager_NetStateA;
        }
    }

    public class NetStateReceive extends BroadcastReceiver {
        public void onReceive(Context r3_Context, Intent r4_Intent) {
            a = r3_Context;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(r4_Intent.getAction())) {
                WifiManager r0_WifiManager = (WifiManager) r3_Context.getSystemService(NetworkManager.WIFI);
                WifiInfo r1_WifiInfo = r0_WifiManager.getConnectionInfo();
                if ((!r0_WifiManager.isWifiEnabled()) || -1 == r1_WifiInfo.getNetworkId()) {
                    CUR_NETSTATE = Mobile;
                }
            }
        }
    }

    static {
        CUR_NETSTATE = NetState.Mobile;
    }

    public static HttpHost getAPN() {
        Cursor r0_Cursor;
        HttpHost r2_HttpHost = null;
        r0_Cursor = a != null ? a.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null) : null;
        if (r0_Cursor == null || (!r0_Cursor.moveToFirst())) {
            return r2_HttpHost;
        }
        String r1_String = r0_Cursor.getString(r0_Cursor.getColumnIndex("proxy"));
        if (r1_String == null || r1_String.trim().length() <= 0) {
            r0_Cursor.close();
        } else {
            r2_HttpHost = new HttpHost(r1_String, 80);
            r0_Cursor.close();
        }
        return r2_HttpHost;
    }
}