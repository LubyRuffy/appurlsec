package qsbk.app.activity.group;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

// compiled from: TopActivityGroup.java
class i implements Runnable {
    final /* synthetic */ TopActivityGroup a;

    i(TopActivityGroup r1_TopActivityGroup) {
        this.a = r1_TopActivityGroup;
    }

    public void run() {
        AlertDialog r0_AlertDialog = new Builder(this.a).setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u5f00\u542f\u63a8\u9001\uff0c\u9a6c\u4e0a\u4eab\u53d7\u4fbf\u4fbf\u541b\u7cbe\u9009\u7cd7\u4e8b\u63a8\u8350").setPositiveButton("\u4ee5\u540e\u518d\u8bf4", new k(this)).setNegativeButton("\u679c\u65ad\u5f00\u542f", new j(this)).create();
        r0_AlertDialog.setCanceledOnTouchOutside(false);
        r0_AlertDialog.show();
    }
}