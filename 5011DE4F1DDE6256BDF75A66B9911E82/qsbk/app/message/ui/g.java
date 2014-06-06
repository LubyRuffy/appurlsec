package qsbk.app.message.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.Base64;

// compiled from: MessageSendActivity.java
class g implements OnClickListener {
    final /* synthetic */ MessageSendActivity a;

    g(MessageSendActivity r1_MessageSendActivity) {
        this.a = r1_MessageSendActivity;
    }

    public void onClick(View r3_View) {
        MessageSendActivity.a(this.a).setVisibility(0);
        MessageSendActivity.b(this.a).setVisibility(Base64.DONT_BREAK_LINES);
        MessageSendActivity.c(this.a);
    }
}