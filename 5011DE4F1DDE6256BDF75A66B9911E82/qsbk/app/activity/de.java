package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: UserSetting.java
class de implements OnClickListener {
    final /* synthetic */ UserSetting a;

    de(UserSetting r1_UserSetting) {
        this.a = r1_UserSetting;
    }

    public void onClick(View r5_View) {
        Builder r0_Builder = new Builder(this.a).setTitle("\u9009\u62e9\u5934\u50cf");
        CharSequence[] r1_CharSequenceA = new CharSequence[2];
        r1_CharSequenceA[0] = "\u624b\u673a\u62cd\u7167";
        r1_CharSequenceA[1] = "\u624b\u673a\u76f8\u518c";
        r0_Builder.setItems(r1_CharSequenceA, new dg(this)).setNegativeButton("\u53d6\u6d88", new df(this)).show();
    }
}