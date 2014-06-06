package qsbk.app.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.AppManager;

// compiled from: UIHelper.java
final class a implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;

    a(String r1_String, Context r2_Context) {
        this.a = r1_String;
        this.b = r2_Context;
    }

    public void onClick(DialogInterface r6_DialogInterface, int r7i) {
        r6_DialogInterface.dismiss();
        Intent r0_Intent = new Intent("android.intent.action.SEND");
        r0_Intent.setType("message/rfc822");
        String[] r2_StringA = new String[1];
        r2_StringA[0] = "qiushibaike@gmail.com";
        r0_Intent.putExtra("android.intent.extra.EMAIL", r2_StringA);
        r0_Intent.putExtra("android.intent.extra.SUBJECT", "\u7cd7\u4e8b\u767e\u79d1Android\u5ba2\u6237\u7aef - \u9519\u8bef\u62a5\u544a");
        r0_Intent.putExtra("android.intent.extra.TEXT", this.a);
        this.b.startActivity(Intent.createChooser(r0_Intent, "\u53d1\u9001\u9519\u8bef\u62a5\u544a"));
        AppManager.getAppManager().AppExit(this.b);
    }
}