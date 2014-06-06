package qsbk.app.message.ui;

import android.util.Pair;
import java.util.ArrayList;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.api.ChatEngine.DeleteConvTask;
import qsbk.app.message.util.ACache;
import qsbk.app.utils.ToastUtil;

// compiled from: MessageListActivity.java
class b extends DeleteConvTask {
    final /* synthetic */ MessageListActivity b;

    b(MessageListActivity r1_MessageListActivity, String r2_String) {
        this.b = r1_MessageListActivity;
        super(r2_String);
    }

    protected void a() {
        MessageListActivity.b(this.b);
    }

    protected void a(Pair<Integer, String> r4_Pair_Integer__String) {
        if (((Integer) r4_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            this.b.o.deleteChatItemByUid(this.a);
            ChatEngine.deleteLocalConv(this.b.getApplicationContext(), this.a);
            ACache.get(this.b).put(this.a, new ArrayList());
            this.b.o.notifyDataSetChanged();
        } else {
            ToastUtil.Short((String) r4_Pair_Integer__String.second);
        }
        MessageListActivity.a(this.b);
    }
}