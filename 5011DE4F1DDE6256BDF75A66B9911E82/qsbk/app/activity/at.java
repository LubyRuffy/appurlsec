package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

// compiled from: LoginActivity.java
class at implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    at(LoginActivity r1_LoginActivity) {
        this.a = r1_LoginActivity;
    }

    public void onClick(View r5_View) {
        Builder r0_Builder = new Builder(QsbkApp.mContext);
        r0_Builder.setTitle("\u5fd8\u8bb0\u8d26\u53f7\u6216\u5bc6\u7801").setMessage("\u8d26\u53f7\u6216\u8005\u5bc6\u7801\u5fd8\u8bb0\u4e86\u600e\u4e48\u529e\uff1f\uff0c\u522b\u6025\uff0c\u53ef\u4ee5\u8bd5\u7740\u627e\u56de").setPositiveButton("\u627e\u56de\u8d26\u53f7\u5bc6\u7801", new av(this)).setNegativeButton("\u53d6\u6d88", new au(this));
        AlertDialog r0_AlertDialog = r0_Builder.create();
        r0_AlertDialog.getWindow().setType(2008);
        r0_AlertDialog.getWindow().setType(2003);
        r0_AlertDialog.setCanceledOnTouchOutside(false);
        r0_AlertDialog.show();
    }
}