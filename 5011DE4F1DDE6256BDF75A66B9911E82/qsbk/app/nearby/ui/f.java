package qsbk.app.nearby.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EditGenderActivity;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.share.ShareUtils;

// compiled from: InfoCompleteActivity.java
class f implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    f(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void onClick(View r4_View) {
        Intent r0_Intent = new Intent(this.a, EditGenderActivity.class);
        if (this.a.x != null) {
            r0_Intent.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, this.a.x);
        }
        this.a.startActivityForResult(r0_Intent, ShareUtils.SHARE_SMS);
    }
}