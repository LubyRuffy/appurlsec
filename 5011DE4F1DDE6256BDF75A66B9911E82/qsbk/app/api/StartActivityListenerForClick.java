package qsbk.app.api;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.adapter.EditUserInfoAdapter.UserInfoItem;

public class StartActivityListenerForClick implements OnClickListener {
    private final Intent a;
    private final Activity b;
    private final int c;

    public StartActivityListenerForClick(Intent r1_Intent, Activity r2_Activity, int r3i) {
        this.a = r1_Intent;
        this.b = r2_Activity;
        this.c = r3i;
    }

    public void onClick(View r4_View) {
        if (r4_View.getTag() instanceof UserInfoItem) {
            this.a.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, ((UserInfoItem) r4_View.getTag()).getInnerValue());
        }
        this.b.startActivityForResult(this.a, this.c);
    }
}