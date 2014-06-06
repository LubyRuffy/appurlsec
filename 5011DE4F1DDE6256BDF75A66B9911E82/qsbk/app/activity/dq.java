package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.NetworkManager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: UserSettingNavi.java
class dq implements OnClickListener {
    final /* synthetic */ UserSettingNavi a;

    dq(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (r5i) {
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
        this.a.c.setText(this.a.a(r0_String));
        r4_DialogInterface.dismiss();
    }
}