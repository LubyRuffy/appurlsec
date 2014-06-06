package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.security.EmailBindActivity;

// compiled from: EditInfoEntranceActivity.java
class ae implements OnClickListener {
    final /* synthetic */ EditInfoEntranceActivity a;

    ae(EditInfoEntranceActivity r1_EditInfoEntranceActivity) {
        this.a = r1_EditInfoEntranceActivity;
    }

    public void onClick(View r5_View) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.email)) {
            this.a.startActivityForResult(new Intent(this.a, EmailBindActivity.class), this.a.G);
            this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else {
            Builder r0_Builder = new Builder(this.a).setTitle("\u64cd\u4f5c");
            CharSequence[] r1_CharSequenceA = new CharSequence[2];
            r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
            r1_CharSequenceA[1] = "\u53d6\u6d88";
            r0_Builder.setItems(r1_CharSequenceA, new af(this)).show();
        }
    }
}