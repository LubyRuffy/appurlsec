package qsbk.app.message.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.activity.base.CommDialogActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.message.MessageListAdapter;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.api.ChatEngine.Conversation;
import qsbk.app.message.util.ACache;
import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.widget.ProfileHeaderListView;

public class MessageListActivity extends StatFragmentActivity {
    public static final int REQ_CODE_MENU = 1;
    public static final int RESP_CODE_DELETE_CONV = 1;
    protected Context n;
    protected MessageListAdapter o;
    protected ArrayList<Conversation> p;
    protected ListView q;
    protected TextView r;
    private a s;
    private long t;
    private boolean u;
    private int v;
    private ActivityExitHelper w;
    private int x;

    protected class MyOnClickListener implements OnClickListener {
        protected MyOnClickListener() {
        }

        public void onClick(View r6_View) {
            if (MessageListActivity.this.p.size() < ChatEngine.totalCount) {
                ((TextView) r6_View).setText("\u6b63\u5728\u52a0\u8f7d\u4e2d...");
                r6_View.setClickable(false);
                MessageListActivity.c(MessageListActivity.this);
                ChatEngine.getInstance().pullMessageList(MessageListActivity.this.s, MessageListActivity.this.v, Constants.pageCount, "LOAD_MORE");
            }
        }
    }

    static class a extends Handler {
        static a a;
        private MessageListActivity b;
        private boolean c;

        static {
            a = null;
        }

        private a() {
            this.b = null;
            this.c = false;
        }

        public static a getInstance() {
            if (a == null) {
                a = new a();
            }
            return a;
        }

        public void handleMessage(Message r10_Message) {
            switch (r10_Message.what) {
                case ProfileHeaderListView.INVALID_TAB_ID:
                    ((MessageListActivityGroup) this.b.getParent()).a(true);
                    break;
                case ChatEngine.HAVE_CONV_MSG:
                    if (this.c) {
                        try {
                            HashMap r0_HashMap = (HashMap) ACache.get(this.b).getAsObject("message_" + (QsbkApp.currentUser == null ? Integer.valueOf(0) : QsbkApp.currentUser.userId));
                            this.b.r.setText("\u52a0\u8f7d\u66f4\u591a");
                            if (r10_Message.obj == null || (!"LOAD_MORE".equals(r10_Message.obj.toString()))) {
                                this.b.a(r0_HashMap);
                            } else {
                                this.b.r.setText("\u52a0\u8f7d\u66f4\u591a");
                                this.b.r.setClickable(true);
                                this.b.a(r0_HashMap);
                            }
                            if (ChatEngine.totalCount <= this.b.p.size()) {
                                this.b.q.removeFooterView(this.b.r);
                            } else if (this.b.q.getFooterViewsCount() == 0) {
                                this.b.q.addFooterView(this.b.r);
                            }
                            this.b.o.notifyDataSetChanged();
                            ((MessageListActivityGroup) this.b.getParent()).a(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case ChatEngine.QUERY_CONV:
                    if (this.c) {
                        long r1j = System.currentTimeMillis();
                        if (r1j - this.b.t > 5999) {
                            this.b.onRefresh();
                            ((MessageListActivityGroup) this.b.getParent()).a(false);
                            ChatEngine.getInstance().pullMessageList(this.b.s, RESP_CODE_DELETE_CONV);
                            this.b.t = r1j;
                        } else {
                            this.b.o.notifyDataSetChanged();
                        }
                    }
                    ((MessageListActivityGroup) this.b.getParent()).a(true);
                    break;
            }
        }

        public void pauseAndClear() {
            this.c = false;
        }

        public void reinitAndStart(MessageListActivity r2_MessageListActivity) {
            this.b = r2_MessageListActivity;
            this.c = true;
        }
    }

    class b implements OnItemClickListener {
        b() {
        }

        public void onItemClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
            Object r0_Object = r4_AdapterView_.getAdapter().getItem(r6i);
            if (r0_Object == null || (!r0_Object instanceof Conversation)) {
            } else {
                Conversation r0_Conversation = (Conversation) r0_Object;
                r0_Conversation.unread = 0;
                MessageListActivity.this.a(r0_Conversation);
                MessageListActivity.this.u = true;
                MessageListActivity.this.w.setCanback(false);
            }
        }
    }

    private class c implements OnItemLongClickListener {
        private c() {
        }

        public boolean onItemLongClick(AdapterView<?> r3_AdapterView_, View r4_View, int r5i, long r6j) {
            MessageListActivity.this.x = r5i;
            Conversation r0_Conversation = MessageListActivity.this.o.getItem(r5i);
            if (r0_Conversation != null) {
                MessageListActivity.this.openMenuDialog(r0_Conversation.mUser.mLogin);
            }
            return true;
        }
    }

    public MessageListActivity() {
        this.n = this;
        this.p = new ArrayList();
        this.s = null;
        this.t = 0;
        this.u = false;
        this.v = 1;
        this.r = null;
        this.w = new c(this);
        this.x = 0;
    }

    private synchronized void a(HashMap<String, Conversation> r11_HashMap_String__Conversation) {
        if (r11_HashMap_String__Conversation == null) {
        } else {
            if (this.v == 1) {
                this.p.clear();
            }
            Set r1_Set = r11_HashMap_String__Conversation.keySet();
            int r8i = this.p.size();
            Iterator r9_Iterator = r1_Set.iterator();
            int r4i = 0;
            while (r9_Iterator.hasNext()) {
                int r1i;
                String r2_String = (String) r9_Iterator.next();
                Conversation r3_Conversation = (Conversation) r11_HashMap_String__Conversation.get(r2_String);
                if (r8i == 0) {
                    this.p.add(r3_Conversation);
                    r1i = r4i;
                } else {
                    int r2i;
                    int r7i = 0;
                    r1i = r4i;
                    while (r7i < r8i) {
                        r4i = r1i + 1;
                        if (((Conversation) this.p.get(r7i)).from.equals(r2_String)) {
                            r2i = 1;
                            r1i = r4i;
                            break;
                        } else {
                            r7i++;
                            r1i = r4i;
                        }
                    }
                    r2i = 0;
                    if (r2i != 0) {
                        this.p.set(r7i, r3_Conversation);
                    } else {
                        this.p.add(r3_Conversation);
                    }
                }
                r4i = r1i;
            }
            sort(this.p);
        }
    }

    private void a(Conversation r4_Conversation) {
        Intent r0_Intent = new Intent(this.n, MessageSendActivity.class);
        r0_Intent.setFlags(67108864);
        r0_Intent.putExtra(OneProfileActivity.SOURCE, "list");
        r0_Intent.putExtra("userName", r4_Conversation.mUser.mLogin);
        r0_Intent.putExtra("userId", r4_Conversation.mUser.mId);
        r0_Intent.putExtra("userIcon", r4_Conversation.mUser.mIcon);
        startActivity(r0_Intent);
        ((MessageListActivityGroup) getParent()).overridePendingAnimation();
    }

    static /* synthetic */ int c(MessageListActivity r2_MessageListActivity) {
        int r0i = r2_MessageListActivity.v;
        r2_MessageListActivity.v = r0i + 1;
        return r0i;
    }

    private void c() {
        this.q = (ListView) findViewById(R.id.xListView);
        this.o = new MessageListAdapter(this, this.p);
        this.r = (TextView) LayoutInflater.from(this).inflate(R.layout.activity_message_list_bottom, null);
        this.r.setText("\u52a0\u8f7d\u66f4\u591a");
        this.q.addFooterView(this.r);
        this.q.setAdapter(this.o);
    }

    private void d() {
        this.q.setOnItemClickListener(new b());
        this.r.setOnClickListener(new MyOnClickListener());
        this.q.setOnItemLongClickListener(new c(null));
    }

    private void e() {
        ((MessageListActivityGroup) getParent()).a(false);
    }

    private void f() {
        ((MessageListActivityGroup) getParent()).a(true);
    }

    public void deleteConversasiton(String r4_String) {
        new b(this, r4_String).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    protected void onActivityResult(int r3i, int r4i, Intent r5_Intent) {
        if (r3i == 1 && r4i == 1) {
            Conversation r0_Conversation = this.o.getItem(this.x);
            if (r0_Conversation != null) {
                deleteConversasiton(r0_Conversation.mUser.mId);
            }
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        setContentView(View.inflate(this, R.layout.activity_messagelist, null));
        this.s = a.getInstance();
        c();
        d();
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        if (r2i == 82) {
            ((GroupBaseActivity) getParent()).resumeMenuLayout();
            return true;
        } else {
            if (r2i == 4) {
                return this.w.handleBackPressed();
            }
            return super.onKeyDown(r2i, r3_KeyEvent);
        }
    }

    public void onPause() {
        this.s.pauseAndClear();
        super.onPause();
    }

    public void onRefresh() {
        HashMap r0_HashMap = (HashMap) ACache.get(QsbkApp.mContext).getAsObject("message_" + (QsbkApp.currentUser == null ? Integer.valueOf(0) : QsbkApp.currentUser.userId));
        if (r0_HashMap == null) {
            r0_HashMap = new HashMap();
        }
        a(r0_HashMap);
        this.o.notifyDataSetChanged();
        this.r.setText("\u6b63\u5728\u52a0\u8f7d\u4e2d...");
        ((MessageListActivityGroup) getParent()).a(false);
        ChatEngine.getInstance().pullMessageList(this.s, RESP_CODE_DELETE_CONV);
    }

    protected void onResume() {
        super.onResume();
        if (this.u) {
            this.o.notifyDataSetChanged();
            this.u = false;
        }
        this.w.resumeCanbackInOnResume();
    }

    public void onStart() {
        super.onStart();
        this.s.reinitAndStart(this);
        onRefresh();
    }

    public void openMenuDialog(String r7_String) {
        Intent r0_Intent = new Intent(this, CommDialogActivity.class);
        String[] r1_StringA = new String[1];
        r1_StringA[0] = "\u5220\u9664";
        int[] r2_intA = new int[1];
        r2_intA[0] = 1;
        r0_Intent.putExtra(CommDialogActivity.KEY_ITEMS, r1_StringA);
        r0_Intent.putExtra(CommDialogActivity.KEY_ACTIONS, r2_intA);
        String r1_String = CommDialogActivity.KEY_TITLE;
        Object[] r3_ObjectA = new Object[1];
        r3_ObjectA[0] = r7_String;
        r0_Intent.putExtra(r1_String, String.format("\u4e0e'%s'\u7684\u5bf9\u8bdd", r3_ObjectA));
        startActivityForResult(r0_Intent, RESP_CODE_DELETE_CONV);
    }

    public void sort(ArrayList<Conversation> r8_ArrayList_Conversation) {
        int r1i = 0;
        while (r1i < r8_ArrayList_Conversation.size()) {
            int r2i = r1i;
            while (r2i < r8_ArrayList_Conversation.size()) {
                if (((Conversation) r8_ArrayList_Conversation.get(r1i)).timestamp < ((Conversation) r8_ArrayList_Conversation.get(r2i)).timestamp) {
                    r8_ArrayList_Conversation.set(r1i, r8_ArrayList_Conversation.get(r2i));
                    r8_ArrayList_Conversation.set(r2i, (Conversation) r8_ArrayList_Conversation.get(r1i));
                }
                r2i++;
            }
            r1i++;
        }
    }
}