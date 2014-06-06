package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.DayActivity;
import qsbk.app.activity.MonthActivity;
import qsbk.app.activity.WeekActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.UIHelper;

public class EssenceActivityGroup extends GroupBaseActivity implements OnClickListener {
    Button p;
    Button q;
    Button r;

    private void b(String r6_String) {
        Window r0_Window;
        this.c.removeAllViews();
        j();
        if ("Day".equals(r6_String)) {
            r0_Window = getLocalActivityManager().startActivity("Day", new Intent(this, DayActivity.class).addFlags(67108864));
        } else if ("Week".equals(r6_String)) {
            r0_Window = getLocalActivityManager().startActivity("Week", new Intent(this, WeekActivity.class).addFlags(67108864));
        } else {
            r0_Window = getLocalActivityManager().startActivity("Day", new Intent(this, MonthActivity.class).addFlags(67108864));
        }
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void j() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("Day") != null) {
                getLocalActivityManager().destroyActivity("Day", true);
            }
            if (getLocalActivityManager().getActivity("Week") != null) {
                getLocalActivityManager().destroyActivity("Week", true);
            }
            if (getLocalActivityManager().getActivity("Month") != null) {
                getLocalActivityManager().destroyActivity("Month", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        this.p = (Button) this.b.findViewById(R.id.tab_left);
        this.q = (Button) this.b.findViewById(R.id.tab_center);
        this.r = (Button) this.b.findViewById(R.id.tab_right);
    }

    protected void b() {
        super.b();
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        EssenceActivityGroup r5_EssenceActivityGroup = (EssenceActivityGroup) r5_Object;
        if ("EssenceActivityGroup" == null) {
            return "EssenceActivityGroup" == null;
        } else {
            if ("EssenceActivityGroup".equals("EssenceActivityGroup")) {
                return true;
            }
            return false;
        }
    }

    protected int f() {
        return R.layout.activity_group_three_tab;
    }

    public int hashCode() {
        return ("EssenceActivityGroup" == null ? 0 : "EssenceActivityGroup".hashCode()) + 31;
    }

    public void onClick(View r7_View) {
        String r0_String = (String) r7_View.getTag();
        switch (r7_View.getId()) {
            case R.id.tab_left:
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.p.setTag("active");
                        this.p.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.p.setTextColor(this.h);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.j);
                    } else {
                        this.p.setTag("active");
                        this.p.setBackgroundResource(R.drawable.top_tab_active);
                        this.p.setTextColor(this.g);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.i);
                    }
                    b("Day");
                } else {
                    h();
                }
                break;
            case R.id.tab_center:
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.q.setTextColor(this.h);
                        this.q.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.j);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.j);
                    } else {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active);
                        this.q.setTextColor(this.g);
                        this.q.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.i);
                        this.r.setTag("normal");
                        this.r.setBackgroundResource(R.drawable.top_tab_normal);
                        this.r.setTextColor(this.i);
                    }
                    b("Week");
                } else {
                    h();
                }
                break;
            case R.id.tab_right:
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.r.setTextColor(this.h);
                        this.r.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.j);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                    } else {
                        this.r.setTag("active");
                        this.r.setBackgroundResource(R.drawable.top_tab_active);
                        this.r.setTextColor(this.g);
                        this.r.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.i);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                    }
                    b("Month");
                } else {
                    h();
                }
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        String r0_String = getIntent().getStringExtra("subActivity");
        if (TextUtils.isEmpty(r0_String) || "Day".equals(r0_String)) {
            b("Day");
        } else {
            b(r0_String);
        }
    }
}