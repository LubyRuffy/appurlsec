package qsbk.app.message.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.message.ChatMsgSource;

// compiled from: MessageSendActivity.java
class e implements OnClickListener {
    final /* synthetic */ ChatMsgSource a;
    final /* synthetic */ MessageSendActivity b;

    e(MessageSendActivity r1_MessageSendActivity, ChatMsgSource r2_ChatMsgSource) {
        this.b = r1_MessageSendActivity;
        this.a = r2_ChatMsgSource;
    }

    public void onClick(View r4_View) {
        if (this.a.type == 1) {
        } else if (this.a.type == 3 || this.a.type == 2) {
            Intent r0_Intent = new Intent(this.b, SingleArticle.class);
            r0_Intent.putExtra("article_id", String.valueOf(this.a.valueObj.artid));
            r0_Intent.putExtra(OneProfileActivity.SOURCE, SingleArticle.KEY_ONLY_ARTICLE_ID);
            this.b.startActivity(r0_Intent);
        }
    }
}