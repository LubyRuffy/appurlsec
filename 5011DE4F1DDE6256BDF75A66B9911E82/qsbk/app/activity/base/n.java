package qsbk.app.activity.base;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.mm.sdk.conversation.RConversation;
import qsbk.app.R;
import qsbk.app.activity.publish.Publish;

// compiled from: GroupBaseActivity.java
class n implements OnClickListener {
    final /* synthetic */ GroupBaseActivity a;

    n(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public void onClick(View r4_View) {
        Intent r0_Intent = new Intent(this.a.a, Publish.class);
        r0_Intent.putExtra(RConversation.COL_FLAG, "article");
        this.a.a.startActivity(r0_Intent);
        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }
}