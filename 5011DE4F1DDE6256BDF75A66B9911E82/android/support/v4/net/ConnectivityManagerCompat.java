package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import qsbk.app.widget.listview.XListViewHeader;

public class ConnectivityManagerCompat {
    private static final b a;

    static interface b {
        public boolean isActiveNetworkMetered(ConnectivityManager r1_ConnectivityManager);
    }

    static class a implements b {
        a() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager r3_ConnectivityManager) {
            NetworkInfo r1_NetworkInfo = r3_ConnectivityManager.getActiveNetworkInfo();
            if (r1_NetworkInfo == null) {
                return true;
            }
            switch (r1_NetworkInfo.getType()) {
                case XListViewHeader.STATE_NORMAL:
                    return true;
                case XListViewHeader.STATE_READY:
                    return false;
            }
            return true;
        }
    }

    static class c implements b {
        c() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager r2_ConnectivityManager) {
            return a.isActiveNetworkMetered(r2_ConnectivityManager);
        }
    }

    static class d implements b {
        d() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager r2_ConnectivityManager) {
            return b.isActiveNetworkMetered(r2_ConnectivityManager);
        }
    }

    static class e implements b {
        e() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager r2_ConnectivityManager) {
            return c.isActiveNetworkMetered(r2_ConnectivityManager);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new e();
        } else if (VERSION.SDK_INT >= 13) {
            a = new d();
        } else if (VERSION.SDK_INT >= 8) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager r1_ConnectivityManager, Intent r2_Intent) {
        return r1_ConnectivityManager.getNetworkInfo(((NetworkInfo) r2_Intent.getParcelableExtra("networkInfo")).getType());
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager r1_ConnectivityManager) {
        return a.isActiveNetworkMetered(r1_ConnectivityManager);
    }
}