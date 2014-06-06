package qsbk.app.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.widget.listview.XListViewFooter;

public class ArticleReporter {
    Handler a;
    private Activity b;

    public ArticleReporter(Activity r2_Activity) {
        this.a = new a(this);
        this.b = r2_Activity;
    }

    public void chooseReportOption() {
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u4e3e\u62a5\u54e6\uff01", 0).show();
            this.b.startActivity(new Intent(this.b, LoginActivity.class));
            this.b.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else {
            this.b.startActivityForResult(new Intent(this.b, ReportActivity.class), XListViewFooter.STATE_NOMORE);
        }
    }

    public void reportArticle(String r4_String, int r5i) {
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "\u767b\u5f55\u540e\u624d\u80fd\u4e3e\u62a5\u54e6\uff01", 0).show();
            this.b.startActivity(new Intent(this.b, LoginActivity.class));
            this.b.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        } else {
            new b(this, "qbk-ReportArt", r5i, r4_String).start();
        }
    }
}