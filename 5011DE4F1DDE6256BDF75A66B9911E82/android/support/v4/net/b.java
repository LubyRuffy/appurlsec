package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ConnectivityManagerCompatHoneycombMR2.java
class b {
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
            case ShareUtils.SHARE_COLLECT:
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                return false;
        }
        return true;
    }
}