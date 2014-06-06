package qsbk.app.message;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import org.apache.cordova.Globalization;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.message.api.ChatEngine.Conversation;
import qsbk.app.message.util.TimeUtils;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.Base64;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.CircularImage;

public class MessageListAdapter extends BaseAdapter {
    private Context a;
    private ArrayList<Conversation> b;
    private LayoutInflater c;

    public static class UserHeadClickListener implements OnClickListener {
        Conversation a;
        private Context b;

        UserHeadClickListener(Conversation r2_Conversation, Context r3_Context) {
            this.a = null;
            this.a = r2_Conversation;
            this.b = r3_Context;
        }

        public void onClick(View r5_View) {
            LogUtil.d("msg clicked");
            BaseUserInfo r0_BaseUserInfo = new BaseUserInfo();
            r0_BaseUserInfo.userId = this.a.mUser.mId;
            r0_BaseUserInfo.userName = this.a.mUser.mLogin;
            Intent r1_Intent = new Intent(this.b, OneProfileActivity.class);
            r1_Intent.putExtra(OneProfileActivity.USER, r0_BaseUserInfo.encodeToJsonObject().toString());
            this.b.startActivity(r1_Intent);
        }
    }

    class a {
        public TextView lastMessage;
        public TextView lastMessageDate;
        public TextView unread;
        public TextView userName;
        public CircularImage userhead;

        a() {
        }
    }

    public MessageListAdapter(Context r2_Context, ArrayList<Conversation> r3_ArrayList_Conversation) {
        this.a = r2_Context;
        this.b = r3_ArrayList_Conversation;
        this.c = LayoutInflater.from(this.a);
    }

    public Conversation deleteChatItemByUid(String r3_String) {
        if (this.b == null || this.b.size() <= 0) {
            return null;
        }
        int r1i = 0;
        while (r1i < this.b.size()) {
            if (((Conversation) this.b.get(r1i)).mUser.mId.equals(r3_String)) {
                return (Conversation) this.b.remove(r1i);
            }
            r1i++;
        }
        return null;
    }

    public int getCount() {
        return this.b.size();
    }

    public Conversation getItem(int r2i) {
        return (this.b == null || this.b.size() <= 0) ? null : (Conversation) this.b.get(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public View getView(int r10i, View r11_View, ViewGroup r12_ViewGroup) {
        a r0_a;
        int r1i;
        if (r11_View == null) {
            r11_View = this.c.inflate(R.layout.item_message_row, null);
            a r1_a = new a();
            r1_a.userhead = (CircularImage) r11_View.findViewById(R.id.userhead);
            r1_a.userName = (TextView) r11_View.findViewById(R.id.userName);
            r1_a.lastMessage = (TextView) r11_View.findViewById(R.id.lastMessage);
            r1_a.unread = (TextView) r11_View.findViewById(R.id.unread);
            r1_a.lastMessageDate = (TextView) r11_View.findViewById(R.id.lastMessageDate);
            r11_View.setTag(r1_a);
            r0_a = r1_a;
        } else {
            r0_a = (a) r11_View.getTag();
        }
        Conversation r4_Conversation = getItem(r10i);
        if (TextUtils.isEmpty(r4_Conversation.mUser.mIcon) || r4_Conversation.mUser.mIcon == null || r4_Conversation.mUser.mIcon.equals("null")) {
            r0_a.userhead.setImageResource(R.drawable.default_users_avatar);
        } else {
            String r1_String = Constants.ARATAR_URL;
            Object[] r5_ObjectA = new Object[4];
            r5_ObjectA[0] = Integer.valueOf(Integer.valueOf(r4_Conversation.mUser.mId).intValue() / 10000);
            r5_ObjectA[1] = r4_Conversation.mUser.mId;
            r5_ObjectA[2] = Globalization.MEDIUM;
            r5_ObjectA[3] = r4_Conversation.mUser.mIcon;
            QsbkApp.getInstance().getAvatarWorker(this.a).loadImage(String.format(r1_String, r5_ObjectA), r0_a.userhead);
        }
        r0_a.userName.setText(r4_Conversation.mUser.mLogin);
        if (r4_Conversation.unread == 0) {
            r0_a.unread.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r0_a.unread.setVisibility(0);
            r0_a.unread.setText(String.valueOf(r4_Conversation.unread));
        }
        try {
            r1i = (Integer.parseInt(r4_Conversation.mUser.mId) <= 0 || r4_Conversation.mUser.mId.equals(QsbkApp.currentUser.userId)) ? 0 : 1;
        } catch (Exception e) {
            r1i = 0;
        }
        if (r1i != 0) {
            r0_a.userhead.setOnClickListener(new UserHeadClickListener(r4_Conversation, this.a));
        } else {
            r0_a.userhead.setOnClickListener(null);
        }
        r0_a.lastMessage.setText(r4_Conversation.content);
        r0_a.lastMessageDate.setText(TimeUtils.getLastLoginStr(r4_Conversation.timestamp * 1000));
        return r11_View;
    }
}