package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.model.UserInfo;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.Base64;

public class BaseInfoAdapter extends DefaultAdapter<UserInfo> {
    private Drawable e;
    private Drawable f;

    public static class ViewHolder {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;

        static qsbk.app.adapter.BaseInfoAdapter.ViewHolder a(View r2_View) {
            Object r0_Object = r2_View.getTag();
            if (r0_Object != null && r0_Object instanceof qsbk.app.adapter.BaseInfoAdapter.ViewHolder) {
                return (qsbk.app.adapter.BaseInfoAdapter.ViewHolder) r0_Object;
            }
            qsbk.app.adapter.BaseInfoAdapter.ViewHolder r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder = new qsbk.app.adapter.BaseInfoAdapter.ViewHolder();
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.a = (TextView) r2_View.findViewById(R.id.age);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.b = (TextView) r2_View.findViewById(R.id.astrology);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.c = (TextView) r2_View.findViewById(R.id.location);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.d = (TextView) r2_View.findViewById(R.id.hobby);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.e = (TextView) r2_View.findViewById(R.id.introduce);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.f = (TextView) r2_View.findViewById(R.id.mobile_brand);
            r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder.g = (TextView) r2_View.findViewById(R.id.create_time);
            return r1_qsbk_app_adapter_BaseInfoAdapter_ViewHolder;
        }
    }

    public BaseInfoAdapter(ArrayList<UserInfo> r3_ArrayList_UserInfo, Activity r4_Activity) {
        super(r3_ArrayList_UserInfo, r4_Activity);
        if (this.b == null || this.b.size() != 1) {
            throw new IllegalArgumentException("Data Source can not be null, and must have only one item.");
        } else {
            this.e = this.c.getResources().getDrawable(R.drawable.one_profile_male_left);
            this.f = this.c.getResources().getDrawable(R.drawable.one_profile_female_left);
        }
    }

    private void a(TextView r2_TextView, String r3_String) {
        if (r3_String == null || r3_String.trim().length() == 0) {
            r2_TextView.setText(RContactStorage.PRIMARY_KEY);
        } else {
            r2_TextView.setText(r3_String);
        }
    }

    private void a(TextView r6_TextView, UserInfo r7_UserInfo) {
        if (r7_UserInfo.create_at > 0) {
            r6_TextView.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(r7_UserInfo.create_at * 1000)));
        } else {
            r6_TextView.setText(RContactStorage.PRIMARY_KEY);
        }
    }

    private void a(ViewHolder r3_ViewHolder, UserInfo r4_UserInfo) {
        c(r3_ViewHolder.a, r4_UserInfo);
        b(r3_ViewHolder.b, r4_UserInfo);
        a(r3_ViewHolder.g, r4_UserInfo);
        a(r3_ViewHolder.d, r4_UserInfo.hobby);
        a(r3_ViewHolder.e, r4_UserInfo.introduce);
        a(r3_ViewHolder.c, r4_UserInfo.location);
        a(r3_ViewHolder.f, r4_UserInfo.mobile_brand);
    }

    private void b(TextView r6_TextView, UserInfo r7_UserInfo) {
        if (TextUtils.isEmpty(r7_UserInfo.astrology)) {
            if (r7_UserInfo.birthday > 0) {
                Calendar r0_Calendar = Calendar.getInstance();
                r0_Calendar.setTimeInMillis(r7_UserInfo.birthday * 1000);
                r6_TextView.setText(AstrologyUtils.date2Astrology(r0_Calendar));
            } else {
                r6_TextView.setText(RContactStorage.PRIMARY_KEY);
            }
        } else {
            r6_TextView.setText(r7_UserInfo.astrology);
        }
    }

    private void c(TextView r10_TextView, UserInfo r11_UserInfo) {
        int r1i = 99;
        int r0i = REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH;
        r10_TextView.setVisibility(0);
        int r2i = r10_TextView.getPaddingBottom();
        int r3i = r10_TextView.getPaddingTop();
        int r4i = r10_TextView.getPaddingRight();
        int r5i = r10_TextView.getPaddingLeft();
        if (NearbySelectView.GENDER_FEMALE.equals(r11_UserInfo.gender)) {
            r10_TextView.setCompoundDrawablesWithIntrinsicBounds(this.f, null, null, null);
            r10_TextView.setBackgroundResource(R.drawable.one_profile_gender_female);
        } else if (NearbySelectView.GENDER_MALE.equals(r11_UserInfo.gender)) {
            r10_TextView.setCompoundDrawablesWithIntrinsicBounds(this.e, null, null, null);
            r10_TextView.setBackgroundResource(R.drawable.one_profile_gender_male);
        } else {
            r10_TextView.setVisibility(Base64.DONT_BREAK_LINES);
        }
        r10_TextView.setPadding(r5i, r3i, r4i, r2i);
        if (r11_UserInfo.age > 0) {
            if (r11_UserInfo.age > 99) {
                if (r1i >= 12) {
                    r10_TextView.setText(" " + r0i);
                } else {
                    r0i = r1i;
                    r10_TextView.setText(" " + r0i);
                }
            } else {
                r1i = r11_UserInfo.age;
                if (r1i >= 12) {
                    r0i = r1i;
                }
                r10_TextView.setText(" " + r0i);
            }
        } else {
            if (r11_UserInfo.birthday > 0) {
                Calendar r0_Calendar = Calendar.getInstance();
                r0_Calendar.setTimeInMillis(r11_UserInfo.birthday * 1000);
                r10_TextView.setText(" " + String.valueOf(AstrologyUtils.getAge(r0_Calendar)));
            }
        }
    }

    public View getView(int r4i, View r5_View, ViewGroup r6_ViewGroup) {
        if (r5_View == null) {
            r5_View = this.d.inflate(R.layout.one_profile_base_info, null);
        }
        a(ViewHolder.a(r5_View), (UserInfo) this.b.get(0));
        return r5_View;
    }
}