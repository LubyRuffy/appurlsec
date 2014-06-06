package qsbk.app.message.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.base.CommDialogActivity;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.message.ChatMsgEntity;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.message.ChatMsgViewAdapter;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.api.ChatEngine.Conversation;
import qsbk.app.message.api.ChatEngine.MessageBody;
import qsbk.app.message.api.ChatEngine.User;
import qsbk.app.message.util.ACache;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class MessageSendActivity extends SecDefaultActivity {
    private static int B = 0;
    private static SparseArray<ArrayList<ChatMsgEntity>> D = null;
    private static int G = 0;
    private static int H = 0;
    private static int J = 0;
    public static final String MSG_SOURCE = "msg_source";
    private long A;
    private MsgHandler C;
    private TextView E;
    private ChatMsgSource F;
    private String I;
    Handler n;
    private String o;
    private String[] p;
    private String[] q;
    private String[] r;
    private Button s;
    private EditText t;
    private View u;
    private ListView v;
    private ChatMsgViewAdapter w;
    private List<ChatMsgEntity> x;
    private int y;
    private long z;

    public static class MsgHandler extends Handler {
        static qsbk.app.message.ui.MessageSendActivity.MsgHandler a;
        private MessageSendActivity b;
        private boolean c;

        static {
            a = null;
        }

        private MsgHandler() {
            this.b = null;
            this.c = false;
        }

        public static synchronized qsbk.app.message.ui.MessageSendActivity.MsgHandler getInstance() {
            qsbk.app.message.ui.MessageSendActivity.MsgHandler r0_qsbk_app_message_ui_MessageSendActivity_MsgHandler;
            synchronized (qsbk.app.message.ui.MessageSendActivity.MsgHandler.class) {
                if (a == null) {
                    a = new qsbk.app.message.ui.MessageSendActivity.MsgHandler();
                }
                r0_qsbk_app_message_ui_MessageSendActivity_MsgHandler = a;
            }
            return r0_qsbk_app_message_ui_MessageSendActivity_MsgHandler;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(Message r14_Message) {
            /*
            r13_this = this;
            r11 = 1;
            r9 = 0;
            r1 = r14.what;
            switch(r1) {
                case 123: goto L_0x002c;
                case 124: goto L_0x019b;
                case 125: goto L_0x010a;
                case 126: goto L_0x0152;
                default: goto L_0x0007;
            };
        L_0x0007:
            r1 = r9;
        L_0x0008:
            r2 = r13.b;
            r2 = r2.x;
            r2 = r2.size();
            if (r2 <= 0) goto L_0x002b;
        L_0x0014:
            if (r1 == 0) goto L_0x002b;
        L_0x0016:
            r1 = r13.b;
            r1 = r1.v;
            r2 = r13.b;
            r2 = r2.x;
            r2 = r2.size();
            r2 = r2 + -1;
            r1.setSelection(r2);
        L_0x002b:
            return;
        L_0x002c:
            r1 = r13.c;
            if (r1 == 0) goto L_0x0007;
        L_0x0030:
            r1 = r14.arg1;
            r2 = r13.b;
            r2 = r2.q;
            r2 = r2[r9];
            r2 = java.lang.Integer.parseInt(r2);
            if (r1 == r2) goto L_0x0042;
        L_0x0040:
            r1 = r9;
            goto L_0x0008;
        L_0x0042:
            r1 = r13.b;	 //Catch:{ Exception -> 0x01cd }
            r1 = r1.x;	 //Catch:{ Exception -> 0x01cd }
            r1.clear();	 //Catch:{ Exception -> 0x01cd }
            r1 = r13.b;	 //Catch:{ Exception -> 0x01cd }
            r1 = qsbk.app.message.util.ACache.get(r1);	 //Catch:{ Exception -> 0x01cd }
            r2 = r13.b;	 //Catch:{ Exception -> 0x01cd }
            r2 = r2.q;	 //Catch:{ Exception -> 0x01cd }
            r3 = 0;
            r2 = r2[r3];	 //Catch:{ Exception -> 0x01cd }
            r1 = r1.getAsObject(r2);	 //Catch:{ Exception -> 0x01cd }
            r1 = (java.util.ArrayList) r1;	 //Catch:{ Exception -> 0x01cd }
            r12 = r1.iterator();	 //Catch:{ Exception -> 0x01cd }
            r10 = r9;
        L_0x0065:
            r1 = r12.hasNext();	 //Catch:{ Exception -> 0x0102 }
            if (r1 == 0) goto L_0x00b7;
        L_0x006b:
            r1 = r12.next();	 //Catch:{ Exception -> 0x0102 }
            r0 = r1;
            r0 = (qsbk.app.message.api.ChatEngine.MessageBody) r0;	 //Catch:{ Exception -> 0x0102 }
            r8 = r0;
            r6 = r8.come_to;	 //Catch:{ Exception -> 0x0102 }
            r1 = new qsbk.app.message.ChatMsgEntity;	 //Catch:{ Exception -> 0x0102 }
            r2 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r2 = r2.q;	 //Catch:{ Exception -> 0x0102 }
            r2 = r2[r6];	 //Catch:{ Exception -> 0x0102 }
            r3 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r3 = r3.p;	 //Catch:{ Exception -> 0x0102 }
            r3 = r3[r6];	 //Catch:{ Exception -> 0x0102 }
            r4 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r4 = r4.r;	 //Catch:{ Exception -> 0x0102 }
            r4 = r4[r6];	 //Catch:{ Exception -> 0x0102 }
            r5 = r8.msg;	 //Catch:{ Exception -> 0x0102 }
            if (r6 != 0) goto L_0x00b5;
        L_0x0093:
            r6 = r11;
        L_0x0094:
            r7 = r8.timestamp;	 //Catch:{ Exception -> 0x0102 }
            r1.<init>(r2, r3, r4, r5, r6, r7);	 //Catch:{ Exception -> 0x0102 }
            r2 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r2 = r2.y;	 //Catch:{ Exception -> 0x0102 }
            r3 = r8.mid;	 //Catch:{ Exception -> 0x0102 }
            if (r2 >= r3) goto L_0x00aa;
        L_0x00a3:
            r2 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r3 = r8.mid;	 //Catch:{ Exception -> 0x0102 }
            r2.y = r3;	 //Catch:{ Exception -> 0x0102 }
        L_0x00aa:
            r2 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r2 = r2.x;	 //Catch:{ Exception -> 0x0102 }
            r2.add(r1);	 //Catch:{ Exception -> 0x0102 }
            r10 = r11;
            goto L_0x0065;
        L_0x00b5:
            r6 = r9;
            goto L_0x0094;
        L_0x00b7:
            r1 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1.x;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1.size();	 //Catch:{ Exception -> 0x0102 }
            if (r1 >= r11) goto L_0x00f7;
        L_0x00c3:
            r1 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1.v;	 //Catch:{ Exception -> 0x0102 }
            r2 = 8;
            r1.setVisibility(r2);	 //Catch:{ Exception -> 0x0102 }
        L_0x00ce:
            r1 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1.d();	 //Catch:{ Exception -> 0x0102 }
            r1 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1.w;	 //Catch:{ Exception -> 0x0102 }
            r1.notifyDataSetChanged();	 //Catch:{ Exception -> 0x0102 }
            r1 = r14.obj;	 //Catch:{ Exception -> 0x0102 }
            if (r1 == 0) goto L_0x00f4;
        L_0x00e0:
            r1 = r14.obj;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1 instanceof org.json.JSONObject;	 //Catch:{ Exception -> 0x0102 }
            if (r1 == 0) goto L_0x00f4;
        L_0x00e6:
            r1 = "show msg source";
            qsbk.app.utils.LogUtil.d(r1);	 //Catch:{ Exception -> 0x0102 }
            r2 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1 = r14.obj;	 //Catch:{ Exception -> 0x0102 }
            r1 = (org.json.JSONObject) r1;	 //Catch:{ Exception -> 0x0102 }
            r2.updateChatMsgSourceFromServer(r1);	 //Catch:{ Exception -> 0x0102 }
        L_0x00f4:
            r1 = r10;
            goto L_0x0008;
        L_0x00f7:
            r1 = r13.b;	 //Catch:{ Exception -> 0x0102 }
            r1 = r1.v;	 //Catch:{ Exception -> 0x0102 }
            r2 = 0;
            r1.setVisibility(r2);	 //Catch:{ Exception -> 0x0102 }
            goto L_0x00ce;
        L_0x0102:
            r1 = move-exception;
            r2 = r1;
            r1 = r10;
        L_0x0105:
            r2.printStackTrace();
            goto L_0x0008;
        L_0x010a:
            r1 = r14.arg1;
            r2 = r14.arg2;
            qsbk.app.message.ui.MessageSendActivity.b(r1, r2, r11);
            r1 = r13.c;
            if (r1 == 0) goto L_0x0007;
        L_0x0115:
            r1 = r14.arg1;
            r2 = r13.b;
            r2 = r2.q;
            r2 = r2[r9];
            r2 = java.lang.Integer.parseInt(r2);
            if (r1 == r2) goto L_0x0128;
        L_0x0125:
            r1 = r9;
            goto L_0x0008;
        L_0x0128:
            r1 = r13.b;
            r1 = r1.x;
            r1 = r1.size();
            r1 = r1 + -1;
            r2 = r1;
        L_0x0135:
            if (r2 < 0) goto L_0x014b;
        L_0x0137:
            r1 = r13.b;
            r1 = r1.x;
            r1 = r1.get(r2);
            r1 = (qsbk.app.message.ChatMsgEntity) r1;
            r3 = r1.sendingId;
            r4 = r14.arg2;
            if (r3 != r4) goto L_0x014e;
        L_0x0149:
            r1.sendingId = r9;
        L_0x014b:
            r1 = r9;
            goto L_0x0008;
        L_0x014e:
            r1 = r2 + -1;
            r2 = r1;
            goto L_0x0135;
        L_0x0152:
            r1 = r14.arg1;
            r2 = r14.arg2;
            qsbk.app.message.ui.MessageSendActivity.b(r1, r2, r9);
            r1 = r13.c;
            if (r1 == 0) goto L_0x0007;
        L_0x015d:
            r1 = r14.arg1;
            r2 = r13.b;
            r2 = r2.q;
            r2 = r2[r9];
            r2 = java.lang.Integer.parseInt(r2);
            if (r1 == r2) goto L_0x0170;
        L_0x016d:
            r1 = r9;
            goto L_0x0008;
        L_0x0170:
            r1 = r13.b;
            r1 = r1.x;
            r1 = r1.size();
            r1 = r1 + -1;
            r2 = r1;
        L_0x017d:
            if (r2 < 0) goto L_0x0194;
        L_0x017f:
            r1 = r13.b;
            r1 = r1.x;
            r1 = r1.get(r2);
            r1 = (qsbk.app.message.ChatMsgEntity) r1;
            r3 = r1.sendingId;
            r4 = r14.arg2;
            if (r3 != r4) goto L_0x0197;
        L_0x0191:
            r2 = -1;
            r1.sendingId = r2;
        L_0x0194:
            r1 = r9;
            goto L_0x0008;
        L_0x0197:
            r1 = r2 + -1;
            r2 = r1;
            goto L_0x017d;
        L_0x019b:
            r1 = r13.c;
            if (r1 == 0) goto L_0x0007;
        L_0x019f:
            r1 = java.lang.System.currentTimeMillis();
            r3 = r13.b;
            r3 = r3.z;
            r3 = r1 - r3;
            r5 = 5999; // 0x176f float:8.406E-42 double:2.964E-320;
            r3 = (r3 > r5 ? 1 : (r3 == r5? 0 : -1));
            if (r3 <= 0) goto L_0x0007;
        L_0x01b1:
            r3 = qsbk.app.message.api.ChatEngine.getInstance();
            r4 = r13.b;
            r4 = r4.q;
            r4 = r4[r9];
            r5 = r13.b;
            r5 = r5.y;
            r3.pullConversationDelta(r13, r4, r5, r11);
            r3 = r13.b;
            r3.z = r1;
            goto L_0x0007;
        L_0x01cd:
            r1 = move-exception;
            r2 = r1;
            r1 = r9;
            goto L_0x0105;
            */

        }

        public boolean ismHandleMsg() {
            return this.c;
        }

        public void pauseAndClear() {
            this.c = false;
        }

        public void reinitAndStart(MessageSendActivity r2_MessageSendActivity) {
            this.b = r2_MessageSendActivity;
            this.c = true;
        }
    }

    static {
        B = 1;
        D = new SparseArray();
        G = 1;
        H = 2;
        J = 1;
    }

    public MessageSendActivity() {
        String[] r0_StringA = new String[2];
        r0_StringA[0] = RContactStorage.PRIMARY_KEY;
        r0_StringA[1] = RContactStorage.PRIMARY_KEY;
        this.p = r0_StringA;
        r0_StringA = new String[2];
        r0_StringA[0] = "0";
        r0_StringA[1] = "0";
        this.q = r0_StringA;
        r0_StringA = new String[2];
        r0_StringA[0] = "null";
        r0_StringA[1] = "null";
        this.r = r0_StringA;
        this.x = new ArrayList();
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.C = null;
        this.E = null;
        this.F = null;
        this.I = null;
        this.n = new i(this);
    }

    private void a(int r6i) {
        int r2i = 1;
        int r1i = 0;
        String r0_String = RContactStorage.PRIMARY_KEY;
        LogUtil.d("respCode:" + r6i);
        String r3_String;
        int r0i;
        Object[] r2_ObjectA;
        switch (r6i) {
            case XListViewHeader.STATE_READY:
                r3_String = ChatEngine.BLACK_LIST;
                r0i = 0;
                if (HttpUtils.netIsAvailable()) {
                    r2_ObjectA = new Object[r2i];
                    r2_ObjectA[r1i] = this.q[r1i];
                    a(String.format(r3_String, r2_ObjectA), r0i);
                } else {
                    Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 1).show();
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                r3_String = ChatEngine.REPORT;
                r0i = 0;
                if (HttpUtils.netIsAvailable()) {
                    Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 1).show();
                } else {
                    r2_ObjectA = new Object[r2i];
                    r2_ObjectA[r1i] = this.q[r1i];
                    a(String.format(r3_String, r2_ObjectA), r0i);
                }
                break;
            case XListViewFooter.STATE_NOMORE:
                r3_String = ChatEngine.DELETE;
                r0i = 1;
                if (HttpUtils.netIsAvailable()) {
                    r2_ObjectA = new Object[r2i];
                    r2_ObjectA[r1i] = this.q[r1i];
                    a(String.format(r3_String, r2_ObjectA), r0i);
                } else {
                    Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 1).show();
                }
                break;
        }
    }

    private void a(int r3i, ChatMsgEntity r4_ChatMsgEntity) {
        ArrayList r0_ArrayList = (ArrayList) D.get(r3i);
        if (r0_ArrayList == null) {
            r0_ArrayList = new ArrayList();
            D.put(r3i, r0_ArrayList);
        }
        r0_ArrayList.add(r4_ChatMsgEntity);
        a(r4_ChatMsgEntity);
    }

    private void a(String r3_String, int r4i) {
        new j(this, "qbk-Message-option", r4i, r3_String).start();
    }

    private void a(ChatMsgEntity r9_ChatMsgEntity) {
        Serializable r0_Serializable = (HashMap) ACache.get((Context)this).getAsObject("message_" + (QsbkApp.currentUser == null ? Integer.valueOf(0) : QsbkApp.currentUser.userId));
        if (r0_Serializable == null) {
        } else {
            if (r0_Serializable.containsKey(this.q[0])) {
                ((Conversation) r0_Serializable.get(this.q[0])).content = r9_ChatMsgEntity.text;
                ((Conversation) r0_Serializable.get(this.q[0])).timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
            } else {
                try {
                    User r1_User = new User(r9_ChatMsgEntity.userName, r9_ChatMsgEntity.userId, r9_ChatMsgEntity.userIcon);
                    Conversation r2_Conversation = new Conversation();
                    r2_Conversation.content = r9_ChatMsgEntity.text;
                    r2_Conversation.from = r1_User.mId;
                    r2_Conversation.unread = 0;
                    r2_Conversation.mUser = r1_User;
                    r2_Conversation.timestamp = Calendar.getInstance().getTimeInMillis() / 1000;
                    r0_Serializable.put(this.q[0], r2_Conversation);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ACache.get(QsbkApp.mContext).put("message_" + (QsbkApp.currentUser == null ? Integer.valueOf(0) : QsbkApp.currentUser.userId), r0_Serializable);
        }
    }

    private static void b(int r5i, int r6i, boolean r7z) {
        ArrayList r0_ArrayList = (ArrayList) D.get(r5i);
        if (r0_ArrayList == null) {
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = Integer.valueOf(r5i);
            r2_ObjectA[1] = Integer.valueOf(r6i);
            Log.e("MSG", String.format("message to %d with seq[%d] should in sending list", r2_ObjectA));
        } else {
            Iterator r2_Iterator = r0_ArrayList.iterator();
            while (r2_Iterator.hasNext()) {
                ChatMsgEntity r1_ChatMsgEntity = (ChatMsgEntity) r2_Iterator.next();
                if (r1_ChatMsgEntity.sendingId == r6i) {
                    if (r7z) {
                        r0_ArrayList.remove(r1_ChatMsgEntity);
                        return;
                    } else {
                        r1_ChatMsgEntity.sendingId = -1;
                        return;
                    }
                }
            }
        }
    }

    private void d() {
        if (this.x.size() >= 1) {
            if (this.u == null || this.v.getHeaderViewsCount() <= 0) {
            } else {
                this.v.removeHeaderView(this.u);
            }
        } else if (this.u == null || this.v.getHeaderViewsCount() != 0) {
        } else {
            this.v.addHeaderView(this.u);
        }
    }

    private void e() {
        this.P.setOnClickListener(new f(this));
        this.s.setOnTouchListener(QsbkApp.TouchDark);
        this.s.setOnClickListener(new g(this));
        this.Q.setOnClickListener(new h(this));
    }

    private void h() {
        int r8i = 1;
        if (System.currentTimeMillis() - this.A < 2000) {
            this.S.setVisibility(Base64.DONT_BREAK_LINES);
            this.Q.setVisibility(0);
            Toast.makeText(this.O, "\u7cfb\u7edf\u63d0\u793a\uff1a\u8bf7\u4e0d\u8981\u53d1\u7684\u592a\u9891\u7e41", 0).show();
        } else {
            this.A = System.currentTimeMillis();
            String r3_String = this.t.getText().toString().trim();
            if (r3_String.length() > 0) {
                int r4i = B;
                B = r4i + 1;
                ChatEngine.getInstance().postConversation(this.C, this.q[0], r3_String, r4i, this.F);
                ChatMsgEntity r5_ChatMsgEntity = new ChatMsgEntity(this.q[r8i], this.p[r8i], this.r[r8i], r3_String, false);
                r5_ChatMsgEntity.sendingId = r4i;
                this.x.add(r5_ChatMsgEntity);
                this.w.notifyDataSetChanged();
                this.t.setText(RContactStorage.PRIMARY_KEY);
                this.v.setSelection(this.v.getCount() - 1);
                this.S.setVisibility(Base64.DONT_BREAK_LINES);
                a(Integer.valueOf(this.q[0]).intValue(), r5_ChatMsgEntity);
            }
        }
    }

    private ChatMsgSource i() {
        String r1_String = getIntent().getStringExtra(MSG_SOURCE);
        if (!TextUtils.isEmpty(r1_String)) {
            ChatMsgSource r0_ChatMsgSource = new ChatMsgSource();
            try {
                r0_ChatMsgSource.parseFromJSONObject(new JSONObject(r1_String));
                return r0_ChatMsgSource;
            } catch (Exception e) {
            }
        }
        return null;
    }

    private void j() {
        Intent r0_Intent = new Intent(this, CommDialogActivity.class);
        String[] r1_StringA = new String[3];
        r1_StringA[0] = "\u52a0\u5165\u9ed1\u540d\u5355";
        r1_StringA[1] = "\u4e3e\u62a5\u7528\u6237";
        r1_StringA[2] = "\u5220\u9664\u5bf9\u8bdd";
        r0_Intent.putExtra(CommDialogActivity.KEY_ITEMS, r1_StringA);
        r0_Intent.putExtra(CommDialogActivity.KEY_ACTIONS, new int[]{1, 2, 3});
        startActivityForResult(r0_Intent, H);
    }

    protected void c() {
        super.c();
        this.v = (ListView) findViewById(R.id.listview);
        this.u = LayoutInflater.from(this).inflate(R.layout.activity_msg_send_hint, null);
        if (this.o.equals("entry")) {
            this.P.setBackgroundResource(R.drawable.icon_close_large);
        } else {
            this.P.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.Q.setBackgroundResource(R.drawable.icon_actionsheet);
        this.Q.setVisibility(0);
        this.s = (Button) findViewById(R.id.send);
        this.t = (EditText) findViewById(R.id.sendContent);
        this.E = (TextView) findViewById(R.id.msg_source_tv);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_left, R.anim.roll_left);
    }

    public String getCustomerTitle() {
        return this.p[0];
    }

    public void initChatMsgSource() {
        ChatMsgSource r0_ChatMsgSource;
        r0_ChatMsgSource = this.F != null ? this.F : ChatMsgSource.getMsgSourceFromLocalById(this.q[0]);
        if (r0_ChatMsgSource != null) {
            showChatMsgSourceUI(r0_ChatMsgSource);
        }
    }

    public void initData() {
        try {
            ArrayList r1_ArrayList = (ArrayList) ACache.get((Context)this).getAsObject(this.q[0]);
            if (r1_ArrayList != null) {
                Iterator r10_Iterator = r1_ArrayList.iterator();
                while (r10_Iterator.hasNext()) {
                    MessageBody r8_MessageBody = (MessageBody) r10_Iterator.next();
                    int r6i = r8_MessageBody.come_to;
                    ChatMsgEntity r1_ChatMsgEntity = new ChatMsgEntity(this.q[r6i], this.p[r6i], this.r[r6i], r8_MessageBody.msg, r6i == 0, r8_MessageBody.timestamp);
                    this.y = r8_MessageBody.mid;
                    this.x.add(r1_ChatMsgEntity);
                }
            }
            r1_ArrayList = (ArrayList) D.get(Integer.parseInt(this.q[0]));
            if (r1_ArrayList != null) {
                Iterator r2_Iterator = r1_ArrayList.iterator();
                while (r2_Iterator.hasNext()) {
                    this.x.add((ChatMsgEntity) r2_Iterator.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int r3i, int r4i, Intent r5_Intent) {
        LogUtil.d("on activit result with request code:" + r3i);
        if (r3i == G) {
            if (r4i == J) {
                if (this.I != null) {
                    StringUtils.copyToClipboard(this.I, this);
                    ToastUtil.Short("\u5bf9\u8bdd\u5185\u5bb9\u5df2\u590d\u5236\u5230\u7c98\u8d34\u677f");
                }
                LogUtil.d("copy success");
            }
        } else {
            if (r3i == H) {
                a(r4i);
            }
        }
    }

    public void onBackPressed() {
        finish();
    }

    protected void onCreate(Bundle r6_Bundle) {
        super.onCreate(r6_Bundle);
        setContentView(R.layout.activity_messagesend);
        this.p[0] = getIntent().getStringExtra("userName");
        this.q[0] = getIntent().getStringExtra("userId");
        this.r[0] = getIntent().getStringExtra("userIcon");
        this.o = getIntent().getStringExtra(OneProfileActivity.SOURCE);
        this.q[1] = QsbkApp.currentUser == null ? "0" : QsbkApp.currentUser.userId;
        this.r[1] = QsbkApp.currentUser == null ? RContactStorage.PRIMARY_KEY : QsbkApp.currentUser.userIcon;
        c();
        e();
        initData();
        this.C = MsgHandler.getInstance();
        this.w = new ChatMsgViewAdapter(this, this.x);
        d();
        this.v.setAdapter(this.w);
        if (this.x.size() > 0) {
            this.v.setSelection(this.x.size() - 1);
        }
    }

    public void onPause() {
        this.C.pauseAndClear();
        super.onPause();
    }

    public void onStart() {
        int r6i = 0;
        super.onStart();
        this.C.reinitAndStart(this);
        long r0j = System.currentTimeMillis();
        if (this.x.isEmpty()) {
            if (r0j - this.z > 1000) {
                ChatEngine.getInstance().pullConversation(this.C, this.q[0]);
                this.z = r0j;
            }
        } else if (r0j - this.z > 3000) {
            ChatEngine.getInstance().pullConversationDelta(this.C, this.q[r6i], this.y, true);
            this.z = r0j;
        }
        this.F = i();
        initChatMsgSource();
    }

    public void openCopyDialog(String r6_String) {
        Intent r0_Intent = new Intent(this, CommDialogActivity.class);
        String[] r1_StringA = new String[1];
        r1_StringA[0] = "\u590d\u5236";
        int[] r2_intA = new int[1];
        r2_intA[0] = J;
        r0_Intent.putExtra(CommDialogActivity.KEY_ITEMS, r1_StringA);
        r0_Intent.putExtra(CommDialogActivity.KEY_ACTIONS, r2_intA);
        this.I = r6_String;
        LogUtil.d("string to copy is:" + this.I);
        startActivityForResult(r0_Intent, G);
    }

    public void showChatMsgSourceUI(ChatMsgSource r3_ChatMsgSource) {
        LogUtil.d("show chat msg source ui");
        this.E.setText(r3_ChatMsgSource.getPresentText());
        this.E.setVisibility(0);
        this.E.setOnClickListener(new e(this, r3_ChatMsgSource));
        this.E.invalidate();
    }

    public void updateChatMsgSourceFromServer(JSONObject r3_JSONObject) {
        ChatMsgSource r0_ChatMsgSource = new ChatMsgSource();
        r0_ChatMsgSource.parseFromJSONObject(r3_JSONObject);
        ChatMsgSource.putMsgSourceToLocal(r0_ChatMsgSource);
        if (this.F != null) {
        } else {
            showChatMsgSourceUI(r0_ChatMsgSource);
        }
    }
}