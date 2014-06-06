package qsbk.app.activity.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qiubai.library.adview.AdViewLayout;

// compiled from: GroupBaseActivity.java
class f implements OnClickListener {
    final /* synthetic */ AdViewLayout a;
    final /* synthetic */ GroupBaseActivity b;

    f(GroupBaseActivity r1_GroupBaseActivity, AdViewLayout r2_AdViewLayout) {
        this.b = r1_GroupBaseActivity;
        this.a = r2_AdViewLayout;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        this.a.setClosed(true);
        this.b.cleanAdViewLayout();
        r3_DialogInterface.dismiss();
    }
}