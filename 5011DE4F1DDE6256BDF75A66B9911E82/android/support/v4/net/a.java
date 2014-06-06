package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ConnectivityManagerCompatGingerbread.java
class a {
    public static boolean isActiveNetworkMetered(ConnectivityManager r2_ConnectivityManager) {
        NetworkInfo r1_NetworkInfo = r2_ConnectivityManager.getActiveNetworkInfo();
        if (r1_NetworkInfo == null) {
            return true;
        }
        switch (r1_NetworkInfo.getType()) {
            case XListViewHeader.STATE_NORMAL:
            case XListViewHeader.STATE_REFRESHING:
            case XListViewFooter.STATE_NOMORE:
            case XListViewFooter.STATE_NODATA:
            case ShareUtils.SHARE_SMS:
            case ShareUtils.SHARE_COPY:
                return true;
            case XListViewHeader.STATE_READY:
                return false;
        }
        return true;
    }
}