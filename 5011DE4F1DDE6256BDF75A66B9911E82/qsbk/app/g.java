package qsbk.app;

import android.os.Message;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;

// compiled from: Qiushibaike.java
class g implements OnAsyncLoadListener {
    String a;
    String b;
    Message c;
    final /* synthetic */ Qiushibaike d;

    g(Qiushibaike r3_Qiushibaike) {
        this.d = r3_Qiushibaike;
        this.a = null;
        this.b = RContactStorage.PRIMARY_KEY;
        this.c = null;
    }

    public void onFinishListener(String r5_String) {
        DebugUtil.debug("\u52a0\u8f7d\u6570\u636e\u5b8c\u6210");
        if (!TextUtils.isEmpty(r5_String)) {
            this.d.a(r5_String);
            Qiushibaike r0_Qiushibaike = this.d;
            r0_Qiushibaike.d++;
            this.d.a.notifyDataSetChanged();
        }
        this.d.n.setVisibility(Base64.DONT_BREAK_LINES);
        this.d.o.setVisibility(0);
        this.d.p.setVisibility(0);
        this.d.q.setVisibility(Base64.DONT_BREAK_LINES);
        DebugUtil.debug("dataSource.size--" + this.d.b.size());
        if (this.d.b.size() <= 0) {
            this.d.p.setText("\u91cd\u65b0\u52a0\u8f7d");
        }
    }

    public void onPrepareListener() {
        this.a = "http://m2.qiushibaike.com/article/list/suggest?page=" + this.d.d + "&count=" + Constants.pageCount;
        this.d.e.trackView("/\u6b22\u8fce\u9875/" + this.d.d);
        DebugUtil.debug("\u6570\u636e\u52a0\u8f7d\u51c6\u5907\u9636\u6bb5");
        DebugUtil.debug(this.a);
    }

    public String onStartListener() {
        try {
            DebugUtil.debug("\u6570\u636e\u52a0\u8f7d\u4e2d");
            this.b = HttpClient.getIntentce().get(this.a);
        } catch (QiushibaikeException e) {
            QiushibaikeException r0_QiushibaikeException = e;
            DebugUtil.debug("\u6570\u636e\u52a0\u8f7d\u5931\u8d25");
            this.c = this.d.g.obtainMessage(r0_QiushibaikeException.getStatusCode(), r0_QiushibaikeException.getMessage());
            this.c.sendToTarget();
        }
        return this.b;
    }
}