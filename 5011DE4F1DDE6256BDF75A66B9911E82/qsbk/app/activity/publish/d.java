package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: Publish.java
class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c r1_c) {
        this.a = r1_c;
    }

    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
        Message r0_Message = this.a.a.u.obtainMessage(0, "\u8349\u7a3f\u5df2\u7ecf\u5220\u9664");
        if (r6i == 0) {
            SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a.a), this.a.a.n.getText().toString());
            Toast.makeText(QsbkApp.mContext, "\u5185\u5bb9\u4ee5\u4fdd\u5b58\u4e3a\u8349\u7a3f", 1).show();
            this.a.a.finish();
        } else if (r6i == 1) {
            r0_Message.sendToTarget();
        } else {
            r5_DialogInterface.dismiss();
        }
    }
}