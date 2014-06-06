package qsbk.app.nearby.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.utils.LogUtil;

// compiled from: NearByListActivity.java
class n implements OnItemClickListener {
    final /* synthetic */ NearByListActivity a;

    n(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onItemClick(AdapterView<?> r6_AdapterView_, View r7_View, int r8i, long r9j) {
        LogUtil.d("position:" + r8i);
        NearbyUser r0_NearbyUser = this.a.q.getItem(r8i - 1);
        Intent r1_Intent = new Intent(this.a, OneProfileActivity.class);
        r1_Intent.putExtra(OneProfileActivity.USER, r0_NearbyUser.encodeToJsonObject().toString());
        r1_Intent.putExtra(OneProfileActivity.MSG_SOURCE, new ChatMsgSource(1, r0_NearbyUser.userId, String.valueOf(r0_NearbyUser.mDistance)).encodeToJsonObject().toString());
        this.a.startActivity(r1_Intent);
    }
}