package qsbk.app.message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import org.apache.cordova.Globalization;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.message.ui.MessageSendActivity;
import qsbk.app.message.util.TimeUtils;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

public class ChatMsgViewAdapter extends BaseAdapter {
    private List<ChatMsgEntity> a;
    private MessageSendActivity b;
    private LayoutInflater c;

    public static interface IMsgViewType {
        public static final int IMVT_COM_MSG = 0;
        public static final int IMVT_TO_MSG = 1;
    }

    public static class UserHeadClickListener implements OnClickListener {
        ChatMsgEntity a;
        private Context b;

        UserHeadClickListener(ChatMsgEntity r2_ChatMsgEntity, Context r3_Context) {
            this.a = null;
            this.a = r2_ChatMsgEntity;
            this.b = r3_Context;
        }

        public void onClick(View r5_View) {
            BaseUserInfo r0_BaseUserInfo = new BaseUserInfo();
            r0_BaseUserInfo.userId = this.a.userId;
            r0_BaseUserInfo.userName = this.a.userName;
            Intent r1_Intent = new Intent(this.b, OneProfileActivity.class);
            r1_Intent.putExtra(OneProfileActivity.USER, r0_BaseUserInfo.encodeToJsonObject().toString());
            this.b.startActivity(r1_Intent);
        }
    }

    static class a implements OnClickListener {
        private String a;
        private Context b;

        public a(Context r2_Context, String r3_String) {
            this.a = null;
            this.b = null;
            this.a = r3_String;
            this.b = r2_Context;
        }

        public void onClick(View r4_View) {
            Intent r0_Intent = new Intent(this.b, SingleArticle.class);
            r0_Intent.putExtra("FROM_MSG", true);
            r0_Intent.putExtra("ARTICLEJSON", this.a);
            r0_Intent.putExtra(OneProfileActivity.SOURCE, "suggest/1");
            this.b.startActivity(r0_Intent);
            ((Activity) this.b).overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        }
    }

    static class b {
        public boolean isComMsg;
        public TextView sendDate;
        public TextView tvContent;
        public ImageView userAvatar;

        b() {
            this.isComMsg = true;
        }
    }

    public ChatMsgViewAdapter(MessageSendActivity r2_MessageSendActivity, List<ChatMsgEntity> r3_List_ChatMsgEntity) {
        this.b = r2_MessageSendActivity;
        this.a = r3_List_ChatMsgEntity;
        this.c = LayoutInflater.from(r2_MessageSendActivity);
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int r2i) {
        return this.a.get(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public int getItemViewType(int r2i) {
        return ((ChatMsgEntity) this.a.get(r2i)).isComMeg ? 0 : 1;
    }

    public String getTextOfContent(View r2_View) {
        return (r2_View.getTag() == null || (!r2_View.getTag() instanceof b)) ? null : ((b) r2_View.getTag()).tvContent.getText().toString();
    }

    public View getView(int r12i, View r13_View, ViewGroup r14_ViewGroup) {
        b r2_b;
        ChatMsgEntity r1_ChatMsgEntity;
        int r1i;
        ChatMsgEntity r0_ChatMsgEntity = (ChatMsgEntity) this.a.get(r12i);
        boolean r7z = r0_ChatMsgEntity.isComMeg;
        if (r13_View == null) {
            View r2_View;
            r2_View = r7z ? this.c.inflate(R.layout.chatting_item_msg_text_left, null) : this.c.inflate(R.layout.chatting_item_msg_text_right, null);
            b r6_b = new b();
            r6_b.tvContent = (TextView) r2_View.findViewById(R.id.tv_chatcontent);
            r6_b.userAvatar = (ImageView) r2_View.findViewById(R.id.iv_userhead);
            r6_b.sendDate = (TextView) r2_View.findViewById(R.id.sendDate);
            r6_b.isComMsg = r7z;
            r2_View.setTag(r6_b);
            r13_View = r2_View;
            r2_b = r6_b;
        } else {
            r2_b = (b) r13_View.getTag();
        }
        r1_ChatMsgEntity = r12i > 1 ? (ChatMsgEntity) this.a.get(r12i - 1) : null;
        if (r1_ChatMsgEntity != null) {
            if (r0_ChatMsgEntity.timestamp - r1_ChatMsgEntity.timestamp > 1200) {
                r2_b.sendDate.setVisibility(0);
                r2_b.sendDate.setText("- " + TimeUtils.getLastLoginStr(r0_ChatMsgEntity.timestamp * 1000) + " -");
            } else {
                r2_b.sendDate.setVisibility(Base64.DONT_BREAK_LINES);
            }
        } else {
            r2_b.sendDate.setVisibility(Base64.DONT_BREAK_LINES);
        }
        r2_b.tvContent.setText(r0_ChatMsgEntity.text);
        if (TextUtils.isEmpty(r0_ChatMsgEntity.userIcon) || r0_ChatMsgEntity.userIcon == null || r0_ChatMsgEntity.userIcon.equals("null")) {
            r2_b.userAvatar.setImageResource(R.drawable.default_users_avatar);
        } else {
            String r1_String = Constants.ARATAR_URL;
            Object[] r6_ObjectA = new Object[4];
            r6_ObjectA[0] = Integer.valueOf(Integer.valueOf(r0_ChatMsgEntity.userId).intValue() / 10000);
            r6_ObjectA[1] = r0_ChatMsgEntity.userId;
            r6_ObjectA[2] = Globalization.MEDIUM;
            r6_ObjectA[3] = r0_ChatMsgEntity.userIcon;
            QsbkApp.getInstance().getAvatarWorker(this.b).loadImage(String.format(r1_String, r6_ObjectA), r2_b.userAvatar);
        }
        try {
            r1i = (Integer.parseInt(r0_ChatMsgEntity.userId) <= 0 || r0_ChatMsgEntity.userId.equals(QsbkApp.currentUser.userId)) ? 0 : 1;
        } catch (Exception e) {
            r1i = 0;
        }
        if (r1i != 0) {
            r2_b.userAvatar.setOnClickListener(new UserHeadClickListener(r0_ChatMsgEntity, this.b));
        } else {
            r2_b.userAvatar.setOnClickListener(null);
        }
        if (r0_ChatMsgEntity.extraArticle != null) {
            r2_b.tvContent.setOnClickListener(new a(this.b, r0_ChatMsgEntity.extraArticle));
        } else {
            r2_b.tvContent.setOnClickListener(null);
        }
        r2_b.tvContent.setLongClickable(true);
        r2_b.tvContent.setOnLongClickListener(new a(this));
        return r13_View;
    }

    public int getViewTypeCount() {
        return XListViewHeader.STATE_REFRESHING;
    }
}