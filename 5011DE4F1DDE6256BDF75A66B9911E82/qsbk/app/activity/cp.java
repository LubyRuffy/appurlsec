package qsbk.app.activity;

import android.text.TextUtils;
import qsbk.app.Constants;
import qsbk.app.adapter.MyContentsAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpClient;

// compiled from: SingleArticle.java
class cp implements OnAsyncLoadListener {
    String a;
    String b;
    final /* synthetic */ SingleArticle c;

    cp(SingleArticle r2_SingleArticle) {
        this.c = r2_SingleArticle;
        this.a = null;
        this.b = null;
    }

    public void onFinishListener(String r6_String) {
        if (TextUtils.isEmpty(r6_String)) {
            this.c.Z = this.c.X != null ? this.c.X.comment_count : 0;
        } else {
            this.c.b(r6_String);
            SingleArticle.n(this.c);
            this.c.v.setAdapter(this.c.r);
            this.c.r.notifyDataSetChanged();
            this.b = null;
        }
        if (this.c.Z == 0) {
            this.c.H.setVisibility(Base64.DONT_BREAK_LINES);
            this.c.w.setVisibility(0);
            if (TextUtils.isEmpty(r6_String) && this.c.ae) {
                this.c.e();
            } else {
                this.c.o();
            }
        } else {
            this.c.w.setVisibility(Base64.DONT_BREAK_LINES);
            this.c.I.setText("\u8fd8\u6709" + this.c.Z + "\u6761\u8bc4\u8bba");
            if (!MyContentsAdapter.PUBLISH.equals(this.c.X.state)) {
                this.c.I.setText("\u8be5\u6587\u7ae0\u88ab\u4e3e\u62a5");
            }
            this.c.I.setVisibility(0);
            this.c.J.setVisibility(Base64.DONT_BREAK_LINES);
        }
        this.c.o();
    }

    public void onPrepareListener() {
        String r0_String = new StringBuffer(Constants.COMMENT).append("?page=" + this.c.aa + "&count=" + Constants.CommentCount).toString();
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = this.c.af;
        this.a = String.format(r0_String, r1_ObjectA);
        if (this.c.ae) {
            this.a += "&article=1";
        }
        this.c.T.trackView("\u7cd7\u4e8b\u5355\u8d34/" + this.c.af + "/" + this.c.aa);
    }

    public String onStartListener() {
        try {
            this.b = HttpClient.getIntentce().get(this.a);
        } catch (QiushibaikeException e) {
        }
        return this.b;
    }
}