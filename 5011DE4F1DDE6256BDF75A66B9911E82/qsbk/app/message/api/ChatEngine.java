package qsbk.app.message.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.cordova.Globalization;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.message.ui.MessageSendActivity;
import qsbk.app.message.ui.MessageSendActivity.MsgHandler;
import qsbk.app.message.util.ACache;
import qsbk.app.push.Utils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;

public class ChatEngine {
    public static final String BLACK_LIST = "http://msg.qiushibaike.com/messages/buddy/%s/blacklist?src=android";
    public static final String CHAT_SERVER = "http://msg.qiushibaike.com/messages";
    public static final String DELETE = "http://msg.qiushibaike.com/messages/conv/%s?src=android";
    public static final int ERR = -1;
    public static final int HAVE_CONV_MSG = 123;
    public static final int POST_CONV_FAIL = 126;
    public static final int POST_CONV_OK = 125;
    public static final int QUERY_CONV = 124;
    public static final String REPORT = "http://msg.qiushibaike.com/messages/buddy/%s/report?src=android";
    public static final String UNREAD = "http://msg.qiushibaike.com/messages/unread?src=android";
    private static ChatEngine a = null;
    public static final long mMinQueryConvInterval = 5999;
    public static final long mQueryConvInterval = 10000;
    public static final long mQueryListInterval = 20000;
    public static final long mQueryUnreadInterval = 30000;
    public static int totalCount;
    public static int unreadCount;
    public UnReadCountChanger unReadCountChangerListener;

    public static interface UnReadCountChanger {
        public void changeUi();
    }

    public static class Conversation implements Serializable {
        public String content;
        public String from;
        public qsbk.app.message.api.ChatEngine.User mUser;
        public long timestamp;
        public int unread;

        public Conversation(JSONObject r5_JSONObject) throws JSONException {
            this.unread = r5_JSONObject.getInt("unread");
            this.mUser = new qsbk.app.message.api.ChatEngine.User(r5_JSONObject.getJSONObject(OneProfileActivity.USER));
            JSONObject r0_JSONObject = r5_JSONObject.getJSONObject(Utils.EXTRA_MESSAGE);
            this.content = r0_JSONObject.getString(Utils.RESPONSE_CONTENT);
            this.timestamp = r0_JSONObject.getLong(QsbkDatabase.CREATE_AT);
            this.from = this.mUser.mId;
            int r0i = this.content.indexOf("@ART:");
            if (r0i > ERR) {
                this.content = this.content.substring(0, r0i) + "@..#";
            }
        }
    }

    public static class DeleteConvTask extends AsyncTask<String, Void, Pair<Integer, String>> {
        protected String a;

        public DeleteConvTask(String r1_String) {
            this.a = r1_String;
        }

        protected Pair<Integer, String> a(String ... r5_StringA) {
            try {
                String r0_String = DELETE;
                Object[] r1_ObjectA = new Object[1];
                r1_ObjectA[0] = this.a;
                r0_String = HttpClient.getIntentce().delete(String.format(r0_String, r1_ObjectA));
                Pair r1_Pair = new Pair(null, null);
                if (r0_String != null) {
                    JSONObject r1_JSONObject = new JSONObject(r0_String);
                    return new Pair(Integer.valueOf(r1_JSONObject.getInt("err")), r1_JSONObject.getString("err_msg"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
        }
    }

    public static class MessageBody implements Serializable {
        public int come_to;
        public int mid;
        public String msg;
        public int timestamp;

        public MessageBody(int r1i, int r2i, int r3i, String r4_String) {
            this.mid = r1i;
            this.timestamp = r2i;
            this.come_to = r3i;
            this.msg = r4_String;
        }
    }

    public static class User implements Serializable {
        public String mIcon;
        public String mId;
        public String mLogin;

        public User(String r1_String, String r2_String, String r3_String) throws JSONException {
            this.mLogin = r1_String;
            this.mId = r2_String;
            this.mIcon = r3_String;
        }

        public User(JSONObject r2_JSONObject) throws JSONException {
            this.mLogin = r2_JSONObject.getString(QsbkDatabase.LOGIN);
            this.mId = r2_JSONObject.getString(LocaleUtil.INDONESIAN);
            this.mIcon = r2_JSONObject.getString(QsbkDatabase.ICON);
        }
    }

    private static class a extends Thread {
        private WeakReference<Handler> a;
        private String b;
        private boolean c;
        private String d;
        private int e;

        public a(Handler r5_Handler, String r6_String) {
            Object[] r1_ObjectA = new Object[1];
            r1_ObjectA[0] = r6_String;
            super(String.format("qsbk-PullConv-%s-full", r1_ObjectA));
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = "since";
            this.a = new WeakReference(r5_Handler);
            this.b = r6_String;
        }

        public a(Handler r7_Handler, String r8_String, int r9i) {
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = r8_String;
            r1_ObjectA[1] = Integer.valueOf(r9i);
            super(String.format("qsbk-PullConv-%s-since-%d", r1_ObjectA));
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = "since";
            this.a = new WeakReference(r7_Handler);
            this.b = r8_String;
            this.c = true;
            this.e = r9i;
        }

        public a(Handler r7_Handler, String r8_String, int r9i, boolean r10z) {
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = r8_String;
            r1_ObjectA[1] = Integer.valueOf(r9i);
            super(String.format("qsbk-PullConv-%s-since-%d", r1_ObjectA));
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = "since";
            this.a = new WeakReference(r7_Handler);
            this.b = r8_String;
            this.c = true;
            this.d = "before";
            this.e = r9i;
        }

        public void run() {
            String r1_String;
            Object[] r1_ObjectA;
            if (this.c) {
                r1_ObjectA = new Object[4];
                r1_ObjectA[0] = CHAT_SERVER;
                r1_ObjectA[1] = this.b;
                r1_ObjectA[2] = this.d;
                r1_ObjectA[3] = Integer.valueOf(this.e);
                r1_String = String.format("%s/conv/%s?%s=%d&src=android", r1_ObjectA);
            } else {
                r1_ObjectA = new Object[2];
                r1_ObjectA[0] = CHAT_SERVER;
                r1_ObjectA[1] = this.b;
                r1_String = String.format("%s/conv/%s?src=android", r1_ObjectA);
            }
            try {
                if (this.a.get() == null || ((MsgHandler) this.a.get()).ismHandleMsg()) {
                    Handler r0_Handler;
                    if (HttpUtils.netIsAvailable()) {
                        JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().get(r1_String));
                        if (r1_JSONObject.getInt("err") == 0) {
                            boolean r0z = r1_JSONObject.getBoolean(Globalization.FULL);
                            int r5i = Integer.parseInt(this.b);
                            JSONArray r6_JSONArray = r1_JSONObject.getJSONArray("messages");
                            JSONObject r7_JSONObject = r1_JSONObject.optJSONObject(MessageSendActivity.MSG_SOURCE);
                            if (r6_JSONArray.length() > 0) {
                                ArrayList r2_ArrayList = null;
                                synchronized (this.b) {
                                    TreeSet r0_TreeSet;
                                    TreeSet r1_TreeSet;
                                    Iterator r3_Iterator;
                                    Object r1_Object;
                                    int r4i;
                                    int r2i;
                                    JSONObject r9_JSONObject;
                                    int r10i;
                                    if (r0z) {
                                        r0_TreeSet = null;
                                        if (r2_ArrayList != null) {
                                            r1_TreeSet = new TreeSet();
                                            r3_Iterator = r2_ArrayList.iterator();
                                            while (r3_Iterator.hasNext()) {
                                                r1_TreeSet.add(Integer.valueOf(((qsbk.app.message.api.ChatEngine.MessageBody) r3_Iterator.next()).mid));
                                            }
                                            r0_TreeSet = r1_TreeSet;
                                            r1_Object = r2_ArrayList;
                                        } else {
                                            r1_Object = new ArrayList();
                                        }
                                        r4i = 0;
                                        r2i = 0;
                                        while (r4i < r6_JSONArray.length()) {
                                            r9_JSONObject = r6_JSONArray.getJSONObject(r4i);
                                            r10i = r9_JSONObject.getInt(LocaleUtil.INDONESIAN);
                                            if (r0_TreeSet == null || (!r0_TreeSet.contains(Integer.valueOf(r10i)))) {
                                                r2i = 1;
                                                r1_Object.add(new qsbk.app.message.api.ChatEngine.MessageBody(r10i, r9_JSONObject.getInt(QsbkDatabase.CREATE_AT), r9_JSONObject.getInt("from") != r5i ? 1 : 0, r9_JSONObject.getString(Utils.RESPONSE_CONTENT)));
                                                r4i++;
                                            } else {
                                                r4i++;
                                            }
                                        }
                                        if (r2i == 0) {
                                        } else {
                                            ACache.get(QsbkApp.mContext).put(this.b, (Serializable)r1_Object);
                                        }
                                    } else {
                                        r2_ArrayList = (ArrayList) ACache.get(QsbkApp.mContext).getAsObject(this.b);
                                        r0_TreeSet = null;
                                        if (r2_ArrayList != null) {
                                            r1_Object = new ArrayList();
                                        } else {
                                            r1_TreeSet = new TreeSet();
                                            r3_Iterator = r2_ArrayList.iterator();
                                            while (r3_Iterator.hasNext()) {
                                                r1_TreeSet.add(Integer.valueOf(((qsbk.app.message.api.ChatEngine.MessageBody) r3_Iterator.next()).mid));
                                            }
                                            r0_TreeSet = r1_TreeSet;
                                            r1_Object = r2_ArrayList;
                                        }
                                        r4i = 0;
                                        r2i = 0;
                                        while (r4i < r6_JSONArray.length()) {
                                            r9_JSONObject = r6_JSONArray.getJSONObject(r4i);
                                            r10i = r9_JSONObject.getInt(LocaleUtil.INDONESIAN);
                                            if (r0_TreeSet == null || r0_TreeSet.contains(Integer.valueOf(r10i))) {
                                                r2i = 1;
                                                if (r9_JSONObject.getInt("from") != r5i) {
                                                }
                                                r1_Object.add(new qsbk.app.message.api.ChatEngine.MessageBody(r10i, r9_JSONObject.getInt(QsbkDatabase.CREATE_AT), r9_JSONObject.getInt("from") != r5i ? 1 : 0, r9_JSONObject.getString(Utils.RESPONSE_CONTENT)));
                                                r4i++;
                                            } else {
                                                r4i++;
                                            }
                                        }
                                        if (r2i == 0) {
                                            ACache.get(QsbkApp.mContext).put(this.b, (Serializable)r1_Object);
                                        }
                                    }
                                }
                                r0_Handler = (Handler) this.a.get();
                                if (r0_Handler != null) {
                                    Message r1_Message = new Message();
                                    r1_Message.arg1 = Integer.parseInt(this.b);
                                    if (r7_JSONObject != null) {
                                        r1_Message.obj = r7_JSONObject;
                                    }
                                    r1_Message.what = 123;
                                    r0_Handler.sendMessage(r1_Message);
                                }
                            }
                        }
                    }
                    r0_Handler = (Handler) this.a.get();
                    if (r0_Handler == null || QsbkApp.currentUser == null) {
                    } else {
                        r0_Handler.sendEmptyMessageDelayed(QUERY_CONV, mQueryConvInterval);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class b extends Thread {
        private WeakReference<Handler> a;
        private int b;
        private int c;
        private String d;

        public b(Handler r6_Handler, int r7i, int r8i, String r9_String) {
            Object[] r1_ObjectA = new Object[1];
            r1_ObjectA[0] = Integer.valueOf(r8i);
            super(String.format("qsbk-PullConv-since-%d", r1_ObjectA));
            this.a = null;
            this.b = 30;
            this.c = 1;
            this.d = RContactStorage.PRIMARY_KEY;
            this.a = new WeakReference(r6_Handler);
            this.b = r8i;
            this.c = r7i;
            this.d = r9_String;
        }

        public void run() {
            Handler r0_Handler;
            Message r1_Message;
            int r0i = 0;
            Object[] r2_ObjectA = new Object[3];
            r2_ObjectA[0] = CHAT_SERVER;
            r2_ObjectA[1] = Integer.valueOf(this.c);
            r2_ObjectA[2] = Integer.valueOf(this.b);
            String r1_String = String.format("%s/list?page=%d&count=%d&src=android", r2_ObjectA);
            try {
                if (HttpUtils.netIsAvailable()) {
                    JSONObject r2_JSONObject = new JSONObject(HttpClient.getIntentce().get(r1_String));
                    Serializable r1_Serializable = new HashMap();
                    if (r2_JSONObject.getInt("err") == 0) {
                        if (!r2_JSONObject.isNull("unread")) {
                            unreadCount = r2_JSONObject.getInt("unread");
                        }
                        if (!r2_JSONObject.isNull("total")) {
                            totalCount = r2_JSONObject.getInt("total");
                        }
                        JSONArray r2_JSONArray = r2_JSONObject.getJSONArray("conversations");
                        if (r2_JSONArray.length() > 0) {
                            while (r0i < r2_JSONArray.length()) {
                                qsbk.app.message.api.ChatEngine.Conversation r3_qsbk_app_message_api_ChatEngine_Conversation = new qsbk.app.message.api.ChatEngine.Conversation(r2_JSONArray.getJSONObject(r0i));
                                r1_Serializable.put(r3_qsbk_app_message_api_ChatEngine_Conversation.mUser.mId, r3_qsbk_app_message_api_ChatEngine_Conversation);
                                r0i++;
                            }
                            ACache.get(QsbkApp.mContext).put("message_" + (QsbkApp.currentUser == null ? Integer.valueOf(0) : QsbkApp.currentUser.userId), r1_Serializable);
                            r0_Handler = (Handler) this.a.get();
                            if (r0_Handler == null) {
                                r1_Message = r0_Handler.obtainMessage();
                                r1_Message.obj = this.d;
                                r1_Message.what = 123;
                                r0_Handler.sendMessage(r1_Message);
                            }
                        } else {
                            r0_Handler = (Handler) this.a.get();
                            if (r0_Handler == null) {
                                r0_Handler = (Handler) this.a.get();
                            } else {
                                r1_Message = r0_Handler.obtainMessage();
                                r1_Message.obj = this.d;
                                r1_Message.what = 123;
                                r0_Handler.sendMessage(r1_Message);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                r0_Handler = (Handler) this.a.get();
                if (r0_Handler != null) {
                    r1_Message = r0_Handler.obtainMessage();
                    r1_Message.what = -1;
                    r0_Handler.sendMessage(r1_Message);
                }
            }
            r0_Handler = (Handler) this.a.get();
            if (r0_Handler == null || QsbkApp.currentUser == null) {
            } else {
                r0_Handler.sendEmptyMessageDelayed(QUERY_CONV, mQueryListInterval);
            }
        }
    }

    static {
        unreadCount = 0;
        totalCount = 0;
    }

    private ChatEngine() {
    }

    public static void deleteLocalConv(Context r4_Context, String r5_String) {
        Serializable r0_Serializable = (HashMap) ACache.get(r4_Context).getAsObject("message_" + QsbkApp.currentUser.userId);
        r0_Serializable.remove(r5_String);
        ACache.get(r4_Context).put("message_" + QsbkApp.currentUser.userId, r0_Serializable);
    }

    public static synchronized ChatEngine getInstance() {
        ChatEngine r0_ChatEngine;
        synchronized (ChatEngine.class) {
            if (a == null) {
                a = new ChatEngine();
            }
            r0_ChatEngine = a;
        }
        return r0_ChatEngine;
    }

    public void postConversation(Handler r9_Handler, String r10_String, String r11_String, int r12i, ChatMsgSource r13_ChatMsgSource) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r10_String;
        new b(this, String.format("qsbk-SendMsg-%s", r2_ObjectA), r9_Handler, r10_String, r11_String, r13_ChatMsgSource, r12i).start();
    }

    public void pullConversation(Handler r2_Handler, String r3_String) {
        new a(r2_Handler, r3_String).start();
    }

    public void pullConversationDelta(Handler r3_Handler, String r4_String, int r5i, boolean r6z) {
        if (r6z) {
            new a(r3_Handler, r4_String, r5i).start();
        } else {
            new a(r3_Handler, r4_String, r5i, false).start();
        }
    }

    public void pullMessageList(Handler r4_Handler, int r5i) {
        new b(r4_Handler, r5i, 30, RContactStorage.PRIMARY_KEY).start();
    }

    public void pullMessageList(Handler r2_Handler, int r3i, int r4i, String r5_String) {
        new b(r2_Handler, r3i, r4i, r5_String).start();
    }

    public void updateTick_unread() {
        new a(this, "qbk-Message-unread").start();
    }
}