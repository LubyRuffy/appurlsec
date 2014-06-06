package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Serializable;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.widget.listview.XListViewFooter;

public class EditUserInfoAdapter extends DefaultAdapter<UserInfoItem> {

    public static class UserInfoItem {
        private final String a;
        private String b;
        private final String c;
        private Serializable d;
        private final boolean e;

        public UserInfoItem(String r1_String, String r2_String, String r3_String, Serializable r4_Serializable, boolean r5z) {
            this.a = r1_String;
            this.b = r2_String;
            this.c = r3_String;
            this.d = r4_Serializable;
            this.e = r5z;
        }

        public String getDefaultDisplayValue() {
            return this.c;
        }

        public String getDisplayDesription() {
            return this.a;
        }

        public String getDisplayValue() {
            return this.b;
        }

        public Serializable getInnerValue() {
            return this.d;
        }

        public boolean isUserEditable() {
            return this.e;
        }

        public void setDisplayValue(String r1_String) {
            this.b = r1_String;
        }

        public void setInnerValue(Serializable r1_Serializable) {
            this.d = r1_Serializable;
        }
    }

    static class a {
        private TextView a;
        private TextView b;
        private View c;

        private a() {
        }

        static a a_(View r2_View) {
            if (r2_View.getTag() != null && r2_View.getTag() instanceof a) {
                return (a) r2_View.getTag();
            }
            a r1_a = new a();
            r1_a.a = (TextView) r2_View.findViewById(R.id.description);
            r1_a.b = (TextView) r2_View.findViewById(R.id.value);
            r1_a.c = r2_View.findViewById(R.id.edit);
            return r1_a;
        }
    }

    public EditUserInfoAdapter(ArrayList<UserInfoItem> r1_ArrayList_UserInfoItem, Activity r2_Activity) {
        super(r1_ArrayList_UserInfoItem, r2_Activity);
    }

    private void a(View r2_View, UserInfoItem r3_UserInfoItem) {
        if (r3_UserInfoItem.e) {
            r2_View.setVisibility(0);
        } else {
            r2_View.setVisibility(XListViewFooter.STATE_NODATA);
        }
    }

    private void a(TextView r4_TextView, UserInfoItem r5_UserInfoItem) {
        CharSequence r0_CharSequence = r5_UserInfoItem.b;
        if (TextUtils.isEmpty(r0_CharSequence)) {
            r0_CharSequence = r5_UserInfoItem.c;
            r4_TextView.setTextColor(this.c.getResources().getColor(R.color.g_txt_small));
        } else {
            r4_TextView.setTextColor(this.c.getResources().getColor(R.color.g_txt_middle));
        }
        if (r0_CharSequence == null) {
            r0_CharSequence = RContactStorage.PRIMARY_KEY;
        }
        r4_TextView.setText(r0_CharSequence);
    }

    private void b(TextView r2_TextView, UserInfoItem r3_UserInfoItem) {
        r2_TextView.setText(r3_UserInfoItem.a);
    }

    public View getView(int r4i, View r5_View, ViewGroup r6_ViewGroup) {
        if (r5_View == null) {
            r5_View = this.d.inflate(R.layout.layout_user_info_item, null);
        }
        a r1_a = a.a(r5_View);
        UserInfoItem r0_UserInfoItem = (UserInfoItem) getItem(r4i);
        a(r1_a.b, r0_UserInfoItem);
        a(r1_a.c, r0_UserInfoItem);
        b(r1_a.a, r0_UserInfoItem);
        return r5_View;
    }
}