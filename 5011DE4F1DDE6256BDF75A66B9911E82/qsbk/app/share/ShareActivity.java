package qsbk.app.share;

import android.os.Bundle;
import android.view.MotionEvent;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import qsbk.app.Constants;
import qsbk.app.activity.base.CommDialogActivity;

public class ShareActivity extends CommDialogActivity {
    private IWXAPI h;
    private boolean i;

    public ShareActivity() {
        this.i = false;
    }

    public void finish() {
        if (!this.i) {
            this.i = true;
            if (this.b == null || (!this.b.isShowing())) {
                super.finish();
            } else {
                this.b.dismiss();
                super.finish();
            }
        }
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        this.h = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        this.h.registerApp(Constants.APP_ID);
        if (getIntent().getBooleanExtra("collect", true)) {
        }
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        finish();
        return super.onTouchEvent(r2_MotionEvent);
    }
}