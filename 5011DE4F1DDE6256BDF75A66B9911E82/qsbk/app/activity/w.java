package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LogUtil;

// compiled from: EditInfoBaseActivity.java
class w implements OnClickListener {
    final /* synthetic */ EditInfoBaseActivity a;

    w(EditInfoBaseActivity r1_EditInfoBaseActivity) {
        this.a = r1_EditInfoBaseActivity;
    }

    public void onClick(View r5_View) {
        boolean r0z = this.a.onSure(r5_View);
        LogUtil.d("on sure:" + r0z);
        if (this.a.p && r0z) {
            this.a.a(this.a.getPostParams(), this.a.getPostUrl());
            if (r0z) {
                LogUtil.d("result data:" + this.a.getResultData());
                this.a.setResult(-1, this.a.getResultData());
            } else {
                this.a.setResult(0);
            }
            this.a.finish();
        } else {
            if (r0z) {
                LogUtil.d("result data:" + this.a.getResultData());
                this.a.setResult(-1, this.a.getResultData());
            } else {
                this.a.setResult(0);
            }
            this.a.finish();
        }
    }
}