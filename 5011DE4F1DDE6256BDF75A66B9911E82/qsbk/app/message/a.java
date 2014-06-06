package qsbk.app.message;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

// compiled from: ChatMsgViewAdapter.java
class a implements OnLongClickListener {
    final /* synthetic */ ChatMsgViewAdapter a;

    a(ChatMsgViewAdapter r1_ChatMsgViewAdapter) {
        this.a = r1_ChatMsgViewAdapter;
    }

    public boolean onLongClick(View r3_View) {
        ChatMsgViewAdapter.a(this.a).openCopyDialog(((TextView) r3_View).getText().toString());
        return true;
    }
}