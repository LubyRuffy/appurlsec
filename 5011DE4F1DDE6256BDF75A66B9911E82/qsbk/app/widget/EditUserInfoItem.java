package qsbk.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.R;
import qsbk.app.adapter.EditUserInfoAdapter.UserInfoItem;
import qsbk.app.api.StartActivityListenerForClick;
import qsbk.app.widget.listview.XListViewFooter;

public class EditUserInfoItem {
    private final UserInfoItem a;
    private final RelativeLayout b;
    private final View c;
    private final Context d;
    private final TextView e;
    private final TextView f;
    private OnClickListener g;

    public EditUserInfoItem(UserInfoItem r2_UserInfoItem, Context r3_Context) {
        this(r2_UserInfoItem, r3_Context, null, null);
    }

    public EditUserInfoItem(UserInfoItem r2_UserInfoItem, Context r3_Context, ViewGroup r4_ViewGroup) {
        this(r2_UserInfoItem, r3_Context, null, r4_ViewGroup);
    }

    public EditUserInfoItem(UserInfoItem r2_UserInfoItem, Context r3_Context, StartActivityListenerForClick r4_StartActivityListenerForClick) {
        this(r2_UserInfoItem, r3_Context, r4_StartActivityListenerForClick, null);
    }

    public EditUserInfoItem(UserInfoItem r4_UserInfoItem, Context r5_Context, StartActivityListenerForClick r6_StartActivityListenerForClick, ViewGroup r7_ViewGroup) {
        this.a = r4_UserInfoItem;
        this.d = r5_Context;
        this.g = r6_StartActivityListenerForClick;
        if (r7_ViewGroup != null) {
            this.b = (RelativeLayout) LayoutInflater.from(this.d).inflate(R.layout.layout_user_info_item, r7_ViewGroup, false);
        } else {
            this.b = (RelativeLayout) LayoutInflater.from(this.d).inflate(R.layout.layout_user_info_item, null);
        }
        this.c = this.b.findViewById(R.id.edit);
        this.e = (TextView) this.b.findViewById(R.id.description);
        this.f = (TextView) this.b.findViewById(R.id.value);
        a();
    }

    private void a() {
        if (this.g != null) {
            setOnEditListener(this.g);
        }
        a(this.f, this.a);
        b(this.e, this.a);
        a(this.c, this.a);
    }

    private void a(View r2_View, UserInfoItem r3_UserInfoItem) {
        if (r3_UserInfoItem.isUserEditable()) {
            r2_View.setVisibility(0);
        } else {
            r2_View.setVisibility(XListViewFooter.STATE_NODATA);
        }
    }

    private void a(TextView r4_TextView, UserInfoItem r5_UserInfoItem) {
        CharSequence r0_CharSequence = r5_UserInfoItem.getDisplayValue();
        if (TextUtils.isEmpty(r0_CharSequence)) {
            r0_CharSequence = r5_UserInfoItem.getDefaultDisplayValue();
            r4_TextView.setTextColor(this.d.getResources().getColor(R.color.g_txt_small));
        } else {
            r4_TextView.setTextColor(this.d.getResources().getColor(R.color.g_txt_middle));
        }
        if (r0_CharSequence == null) {
            r0_CharSequence = RContactStorage.PRIMARY_KEY;
        }
        r4_TextView.setText(r0_CharSequence);
    }

    private void b(TextView r2_TextView, UserInfoItem r3_UserInfoItem) {
        r2_TextView.setText(r3_UserInfoItem.getDisplayDesription());
    }

    public UserInfoItem getUserInfo() {
        return this.a;
    }

    public TextView getValueView() {
        return this.f;
    }

    public View getView() {
        return this.b;
    }

    public void setOnEditListener(OnClickListener r3_OnClickListener) {
        this.c.setTag(this.a);
        this.c.setOnClickListener(r3_OnClickListener);
        this.b.setTag(this.a);
        this.b.setOnClickListener(r3_OnClickListener);
    }
}