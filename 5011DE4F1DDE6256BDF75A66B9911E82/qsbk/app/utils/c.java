package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: BrightnessSetting.java
class c implements OnClickListener {
    final /* synthetic */ BrightnessSetting a;

    c(BrightnessSetting r1_BrightnessSetting) {
        this.a = r1_BrightnessSetting;
    }

    public void onClick(DialogInterface r1_DialogInterface, int r2i) {
        r1_DialogInterface.dismiss();
    }
}