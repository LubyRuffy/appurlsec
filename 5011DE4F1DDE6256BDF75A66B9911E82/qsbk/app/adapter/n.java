package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.NetworkManager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: UserMenuLayoutAdapter.java
class n implements OnClickListener {
    final /* synthetic */ UserMenuLayoutAdapter a;

    n(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter) {
        this.a = r1_UserMenuLayoutAdapter;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r4i) {
            case XListViewHeader.STATE_NORMAL:
                r0_String = "auto";
                break;
            case XListViewHeader.STATE_READY:
                r0_String = NetworkManager.WIFI;
                break;
            case XListViewHeader.STATE_REFRESHING:
                r0_String = "textonly";
                break;
        }
        SharePreferenceUtils.setSharePreferencesValue("imageLoadWay", r0_String);
        r3_DialogInterface.dismiss();
        this.a.resume();
    }
}