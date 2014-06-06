package qsbk.app.nearby.ui;

import android.content.Context;
import android.graphics.drawable.LevelListDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import org.apache.cordova.Globalization;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.widget.RoundImageView;
import qsbk.app.widget.listview.XListViewHeader;

public class NearbyListAdapter extends BaseAdapter {
    private Context a;
    private ArrayList<NearbyUser> b;
    private LayoutInflater c;

    class a {
        public TextView gender_age;
        public TextView lastLogin;
        public TextView signature;
        public TextView userName;
        public RoundImageView userhead;

        a() {
        }
    }

    public NearbyListAdapter(Context r2_Context, ArrayList<NearbyUser> r3_ArrayList_NearbyUser) {
        this.a = r2_Context;
        this.b = r3_ArrayList_NearbyUser;
        this.c = LayoutInflater.from(this.a);
    }

    private String a(int r3i) {
        if (r3i < 10) {
            return " " + String.valueOf(r3i);
        }
        if (r3i > 99) {
            return "99";
        }
        return String.valueOf(r3i);
    }

    public int getCount() {
        return this.b.size();
    }

    public NearbyUser getItem(int r2i) {
        return (this.b == null || this.b.size() <= 0) ? null : (NearbyUser) this.b.get(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public View getView(int r9i, View r10_View, ViewGroup r11_ViewGroup) {
        a r1_a;
        if (r10_View == null) {
            r10_View = this.c.inflate(R.layout.nearby_list_item, null);
            r1_a = new a();
            r1_a.userhead = (RoundImageView) r10_View.findViewById(R.id.userhead);
            r1_a.userhead.setRoundX(10.0f);
            r1_a.userhead.setRoundY(10.0f);
            r1_a.userName = (TextView) r10_View.findViewById(R.id.user_name);
            r1_a.lastLogin = (TextView) r10_View.findViewById(R.id.last_login);
            r1_a.gender_age = (TextView) r10_View.findViewById(R.id.gender_age);
            r1_a.signature = (TextView) r10_View.findViewById(R.id.signature);
            r10_View.setTag(r1_a);
        } else {
            r1_a = (a) r10_View.getTag();
        }
        NearbyUser r2_NearbyUser = getItem(r9i);
        if (TextUtils.isEmpty(r2_NearbyUser.userIcon) || "null".equals(r2_NearbyUser.userIcon)) {
            r1_a.userhead.setImageResource(R.drawable.default_users_avatar);
        } else {
            String r0_String = Constants.ARATAR_URL;
            Object[] r3_ObjectA = new Object[4];
            r3_ObjectA[0] = Integer.valueOf(Integer.valueOf(r2_NearbyUser.userId).intValue() / 10000);
            r3_ObjectA[1] = r2_NearbyUser.userId;
            r3_ObjectA[2] = Globalization.MEDIUM;
            r3_ObjectA[3] = r2_NearbyUser.userIcon;
            QsbkApp.getInstance().getAvatarWorker(this.a).loadImage(String.format(r0_String, r3_ObjectA), r1_a.userhead);
        }
        r1_a.userName.setText(r2_NearbyUser.userName);
        LevelListDrawable r0_LevelListDrawable = (LevelListDrawable) r1_a.gender_age.getBackground();
        if (NearbySelectView.GENDER_FEMALE.equals(r2_NearbyUser.gender)) {
            r0_LevelListDrawable.setLevel(1);
        } else {
            r0_LevelListDrawable.setLevel(XListViewHeader.STATE_REFRESHING);
        }
        r1_a.gender_age.setText(a(r2_NearbyUser.age));
        r1_a.signature.setText(r2_NearbyUser.signature);
        r1_a.lastLogin.setText(r2_NearbyUser.getLastLogin());
        return r10_View;
    }
}