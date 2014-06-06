package qsbk.app.activity.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qiubai.library.adview.AdViewLayout;

// compiled from: SecDefaultActivity.java
class ap implements OnClickListener {
    final /* synthetic */ AdViewLayout a;
    final /* synthetic */ SecDefaultActivity b;

    ap(SecDefaultActivity r1_SecDefaultActivity, AdViewLayout r2_AdViewLayout) {
        this.b = r1_SecDefaultActivity;
        this.a = r2_AdViewLayout;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        this.a.setClosed(true);
        this.b.cleanAdViewLayout();
        r3_DialogInterface.dismiss();
    }
}