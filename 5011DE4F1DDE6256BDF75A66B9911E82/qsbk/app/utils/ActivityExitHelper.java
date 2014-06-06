package qsbk.app.utils;

import android.os.Handler;
import android.widget.Toast;
import qsbk.app.QsbkApp;

public class ActivityExitHelper {
    private Boolean a;
    Handler b;
    Runnable c;
    private Boolean d;
    private boolean e;

    public ActivityExitHelper() {
        this.a = Boolean.valueOf(false);
        this.d = Boolean.valueOf(false);
        this.e = true;
        this.b = new Handler();
        this.c = new a(this);
    }

    public boolean handleBackPressed() {
        if (!(this.e) || handleCustomBackPressed()) {
            return true;
        }
        if (this.a.booleanValue()) {
            QsbkApp.getInstance().exit();
            return true;
        } else {
            this.a = Boolean.valueOf(true);
            Toast.makeText(QsbkApp.mContext, "\u518d\u6309\u4e00\u6b21\u8fd4\u56de\u952e\u9000\u51fa", 0).show();
            if (!this.d.booleanValue()) {
                this.d = Boolean.valueOf(true);
                this.b.postDelayed(this.c, 3000);
            }
            return true;
        }
    }

    public boolean handleCustomBackPressed() {
        return false;
    }

    public void resumeCanbackInOnResume() {
        new Handler().postDelayed(new b(this), 800);
    }

    public void setCanback(boolean r1z) {
        this.e = r1z;
    }
}