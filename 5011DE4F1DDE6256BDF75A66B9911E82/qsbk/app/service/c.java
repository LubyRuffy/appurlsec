package qsbk.app.service;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: VerifyUserInfoService.java
class c extends Handler {
    final /* synthetic */ VerifyUserInfoService a;

    c(VerifyUserInfoService r1_VerifyUserInfoService) {
        this.a = r1_VerifyUserInfoService;
    }

    public void handleMessage(Message r5_Message) {
        switch (r5_Message.what) {
            case XListViewHeader.STATE_NORMAL:
                DebugUtil.debug("\u9a8c\u8bc1\u672a\u901a\u8fc7,\u53d1\u9001\u5e7f\u64ad");
                Builder r0_Builder = new Builder(QsbkApp.mContext);
                r0_Builder.setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u7cdf\u7cd5, \u597d\u50cf\u4f3c\u4e4e\u4f60\u7684\u767b\u5f55\u6001\u5df2\u7ecf\u5931\u6548\u4e86,\u8d76\u7d27\u91cd\u65b0\u767b\u5f55\u5427!").setPositiveButton("\u786e\u5b9a", new d(this));
                AlertDialog r0_AlertDialog = r0_Builder.create();
                r0_AlertDialog.getWindow().setType(2008);
                r0_AlertDialog.getWindow().setType(2003);
                r0_AlertDialog.setCanceledOnTouchOutside(false);
                r0_AlertDialog.show();
                break;
            case XListViewHeader.STATE_READY:
                QsbkApp.mContext.startService(new Intent(QsbkApp.mContext, VersionCheckService.class));
                DebugUtil.debug("\u9a8c\u8bc1\u7528\u6237\u670d\u52a1\u81ea\u6740\u4e86");
                this.a.stopSelf();
                break;
        }
    }
}