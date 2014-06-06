package qsbk.app.share;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX.Req;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.openapi.WXTextObject;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.push.Utils;
import qsbk.app.utils.ImageUtils;

public class WXEntryActivity extends FragmentActivity implements OnClickListener {
    private IWXAPI n;
    private String o;
    private String p;
    private String q;
    private boolean r;

    public WXEntryActivity() {
        this.r = false;
    }

    private void a(int r6i) {
        String r0_String = Constants.CONTENT_IMAGE_URL;
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(this.q).intValue() / 10000);
        r1_ObjectA[1] = this.q;
        r1_ObjectA[2] = "small";
        r1_ObjectA[3] = this.p;
        r0_String = String.format(r0_String, r1_ObjectA);
        IMediaObject r1_IMediaObject = new WXWebpageObject();
        r1_IMediaObject.webpageUrl = "http://www.qiushibaike.com/article/" + this.q + "?source=qqwx";
        WXMediaMessage r2_WXMediaMessage = new WXMediaMessage(r1_IMediaObject);
        r2_WXMediaMessage.title = "\uff08\u6765\u81ea\u7cd7\u767e\uff09" + this.o;
        r2_WXMediaMessage.description = this.o;
        Bitmap r0_Bitmap = QsbkApp.getInstance().getImageWorker(getApplicationContext()).getBitmap(r0_String);
        if (r0_Bitmap != null) {
            r2_WXMediaMessage.thumbData = ImageUtils.bmpToByteArray(r0_Bitmap, false);
        }
        BaseReq r0_BaseReq = new Req();
        r0_BaseReq.transaction = System.currentTimeMillis() + RContactStorage.PRIMARY_KEY;
        r0_BaseReq.message = r2_WXMediaMessage;
        r0_BaseReq.scene = r6i;
        this.n.sendReq(r0_BaseReq);
        finish();
    }

    private void b(int r6i) {
        IMediaObject r0_IMediaObject = new WXTextObject();
        r0_IMediaObject.text = "\uff08\u6765\u81ea\u7cd7\u767e\uff09" + this.o;
        WXMediaMessage r1_WXMediaMessage = new WXMediaMessage();
        r1_WXMediaMessage.mediaObject = r0_IMediaObject;
        r1_WXMediaMessage.description = this.o;
        BaseReq r0_BaseReq = new Req();
        r0_BaseReq.transaction = System.currentTimeMillis() + RContactStorage.PRIMARY_KEY;
        r0_BaseReq.message = r1_WXMediaMessage;
        r0_BaseReq.scene = r6i;
        this.n.sendReq(r0_BaseReq);
        finish();
    }

    private void c() {
        findViewById(R.id.cancle).setOnClickListener(this);
        findViewById(R.id.share2firend).setOnClickListener(this);
        findViewById(R.id.share2firends).setOnClickListener(this);
    }

    public void onClick(View r5_View) {
        switch (r5_View.getId()) {
            case R.id.share2firend:
                StatService.onEvent(this, "weixin_1", "pass", 1);
                if (this.r) {
                    a(0);
                } else {
                    b(0);
                }
                finish();
                break;
            case R.id.share2firends:
                StatService.onEvent(this, "weixin_2", "pass", 1);
                if (this.r) {
                    a(1);
                } else {
                    b(1);
                }
                finish();
                break;
            case R.id.cancle:
                finish();
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        setContentView(R.layout.weixinshare);
        this.o = getIntent().getStringExtra(Utils.RESPONSE_CONTENT);
        this.q = getIntent().getStringExtra("articleId");
        this.p = getIntent().getStringExtra("image");
        if (!TextUtils.isEmpty(this.p)) {
            this.r = true;
        }
        this.n = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        c();
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        finish();
        return true;
    }
}