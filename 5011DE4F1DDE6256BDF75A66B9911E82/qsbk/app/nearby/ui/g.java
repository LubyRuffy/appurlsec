package qsbk.app.nearby.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EditBirthActivity;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.LogUtil;

// compiled from: InfoCompleteActivity.java
class g implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    g(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void onClick(View r7_View) {
        Intent r0_Intent = new Intent(this.a, EditBirthActivity.class);
        LogUtil.d("msettted:" + this.a.y);
        r0_Intent.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, this.a.y / 1000);
        this.a.startActivityForResult(r0_Intent, ShareUtils.SHARE_COPY);
    }
}